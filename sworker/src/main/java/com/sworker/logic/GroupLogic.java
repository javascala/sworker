package com.sworker.logic;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.common.util.DateUtil;
import com.sworker.dao.IGroupDao;
import com.sworker.entity.SwGroupAppEntity;
import com.sworker.entity.SwGroupApplyEntity;
import com.sworker.entity.SwGroupInfoEntity;
import com.sworker.entity.SwGroupMemberEntity;
import com.sworker.entity.SwGroupStatisticsEntity;

@SuppressWarnings("unchecked")
@Component
public class GroupLogic implements IGroupLogic {
		
	@SuppressWarnings("rawtypes")
	@Autowired
	private IGroupDao groupDao;

	/**
	 * 指定成员是否是指定群组成员
	 * @param userID 成员id
	 * @param groupID 群组id
	 * @return 存在时返回true，不存在返回false
	 */
	@Override
	@Transactional
	public Boolean isMember(Long userID, Long groupID) {
		return groupDao.findMember(userID, groupID).size() != 0;
	}
	
	/**
	 * 指定成员是否是群组管理员
	 * @param userID 用户id
	 * @param groupsID 群组id
	 * @return 是群组管理员返回true,否则false
	 */
	@Override
	@Transactional
	public Boolean isGroupAdmin(Long userID, Long groupsID) {
		return groupDao.findRole(userID, groupsID, "管理员") != null;
	}

	/**
	 * 指定成员是否是群组创建者
	 * @param userID 用户id
	 * @param groupsID 群组id
	 * @return 是群组创建者返回true,否则false
	 */
	@Override
	@Transactional
	public Boolean isGroupCreator(Long userID, Long groupsID) {
		return groupDao.findRole(userID, groupsID, "创建者") != null;
	}

	/**
	 * 指定成员是否被禁言
	 * @param userID 用户id
	 * @param groupsID 群组id
	 * @return 被禁言返回true,否则false
	 */
	@Override
	@Transactional
	public Boolean isForbidden(Long userID, Long groupsID) {
		return groupDao.findRole(userID, groupsID, "禁言") != null;
	}

	/**
	 * 添加新成员
	 * @param groupId 群组id
	 * @param memberId 成员id
	 * @param roleId 角色id
	 * @return
	 */
	@Override
	@Transactional
	public String addMember(Long groupId, Long memberId,
			Long roleId) {
		if (!isMember(memberId, groupId)) {
			SwGroupMemberEntity member = new SwGroupMemberEntity();
			member.setGroupid(groupId);
			member.setMemberid(memberId);
			member.setRoleid(roleId);
			member.setEnterdate(DateUtil.dateToTimeStamp(new Date()));
			groupDao.create(member);
			//更新群组成员数
			SwGroupInfoEntity groupInfo = groupDao.findGroupById(groupId);
			if (groupInfo != null) {
				groupInfo.setMemberqty(groupInfo.getMemberqty() + 1);
				groupDao.update(groupInfo);
				return "创建成功";
			}
			return "创建失败";
		} else {
			return "该人员已存在";
		}
		
	}

	/**
	 * 添加成员申请
	 * @param groupId 群组id
	 * @param memberId 成员id
	 * @param applyForReason 申请理由
	 * @param applyForStatus 申请状态
	 * @param applyForTime  申请时间
	 */
	@Override
	@Transactional
	public String addApplyFor(Long groupId, Long memberId,
			String applyForReason, Integer applyForStatus, Date applyForTime) {
		if (!isMember(memberId, groupId)) {
			Integer status = getMemApplyFor(groupId, memberId);
			if (status == null) {
				SwGroupApplyEntity apply = new SwGroupApplyEntity();
				apply.setMemberid(memberId);
				apply.setGroupid(groupId);
				apply.setApplyforreason(applyForReason);
				apply.setApplyforstatus(applyForStatus);
				apply.setApplyfordate(new java.sql.Date(applyForTime.getTime()));
				groupDao.create(apply);
				return "申请成功";
			}
			return "申请失败";
		} else {
			return "该人员已存在";
		}
	}

	/**
	 * 删除群组成员
	 * @param groupId 群组id
	 * @param memberId 成员id
	 */
	@Override
	@Transactional
	public void removeMember(Long groupId, Long memberId) {
		if (!isGroupCreator(memberId, groupId)) {
			groupDao.deleteMember(groupId, memberId);
			//更新群组成员数
			SwGroupInfoEntity groupInfo = groupDao.findGroupById(groupId);
			if (groupInfo != null) {
				groupInfo.setMemberqty(groupInfo.getMemberqty() - 1);
				groupDao.update(groupInfo);
			}
		}
	}

	/**
	 * 更新成员在群组中角色
	 * @param groupId 群组id
	 * @param memberId 成员id
	 * @param roleIdNew 新角色id
	 */
	@Override
	@Transactional
	public void modifyMemberRole(Long groupId, Long memberId,
			Long roleIdNew) {
		groupDao.updateMemberRole(groupId, memberId, roleIdNew);
	}

	/**
	 * 更改申请理由
	 * @param groupId 群组id
	 * @param memberId 成员id
	 */
	@Override
	@Transactional
	public void modifyApplyForReason(Long groupId, Long memberId,
			String applyForReason) {
		SwGroupApplyEntity apply = groupDao.findApply(groupId, memberId);
		if (apply != null) {
			apply.setApplyforreason(applyForReason);
			groupDao.update(apply);
		}
	}

	/**
	 * 更该申请状态
	 * @param groupId 群组id
	 * @param memberId 成员id
	 * @param applyForStatus 申请状态
	 */
	@Override
	@Transactional
	public void modifyApplyForStatus(Long groupId, Long memberId,
			Integer applyForStatus) {
		//更改申请状态
		SwGroupApplyEntity apply = groupDao.findApply(groupId, memberId);
		if (apply != null) {
			apply.setApplyforstatus(applyForStatus);
			groupDao.update(apply);
			//判断申请状态为申请成功，0为申请中，1为申请成功，2为申请失败
			if (applyForStatus == 1) {
				//向成员关系表中插入新成员
				Long roleId = groupDao.findRole(groupId, "普通成员");
				addMember(groupId, memberId, roleId);
				//更改当前群组的成员数
				SwGroupInfoEntity groupInfo = groupDao.findGroupById(groupId);
				if (groupInfo != null) {
					groupInfo.setMemberqty(groupInfo.getMemberqty() + 1);
					groupDao.update(groupInfo);
				}
			}
		}
	}

	/**
	 * 屏蔽指定群组信息，0为屏蔽状态，1为不屏蔽状态
	 * @param groupId 群组id
	 * @param userId 成员id
	 * @return 成功返回true，否则返回false
	 */
	@Override
	@Transactional
	public Boolean shieldedGroup(Long groupId, Long userId) {
		List<SwGroupMemberEntity> memberList = groupDao.findMember(userId, groupId);
		if (memberList.size() != 0) {
			Iterator<SwGroupMemberEntity> it = memberList.iterator();
			while (it.hasNext()) {
				SwGroupMemberEntity member = it.next();
				member.setIsforbidden(1);
				groupDao.update(member);			
			}
			return true;
		}
		return false;
	}

	/**
	 *取消指定群组屏蔽信息，0为屏蔽状态，1为不屏蔽状态
	 * @param groupId 群组id
	 * @param userId 成员id
	 * @return 成功返回true，否则返回false
	 */
	@Override
	@Transactional
	public Boolean shieldedCancle(Long groupId, Long userId) {
		List<SwGroupMemberEntity> memberList = groupDao.findMember(userId, groupId);
		if (memberList.size() != 0) {
			Iterator<SwGroupMemberEntity> it = memberList.iterator();
			while (it.hasNext()) {
				SwGroupMemberEntity member = it.next();
				member.setIsforbidden(0);
				groupDao.update(member);			
			}
			return true;
		}
		return false;
	}

	/**
	 * 根据群组id获取成员信息
	 * @param groupId 群组id
	 * @return 返回成员信息列表
	 */
	@Override
	@Transactional
	public <T>List<T> getMembers(Long groupId) {
		return groupDao.findMembersInfo(groupId);
	}

	/**
	 * 根据群组id查找群组申请信息
	 * @param groupId 群组id
	 * @return 返回群组信息列表
	 */
	@Override
	@Transactional
	public List<SwGroupApplyEntity> getApplyFor(Long groupId) {
		return groupDao.findApply(groupId);
	}

	/**
	 * 查找群组中成员申请状态
	 * @param groupId 群组id
	 * @param memberId 成员id
	 * @return 成员的申请状态
	 */
	@Override
	@Transactional
	public Integer getMemApplyFor(Long groupId, Long memberId) {
		SwGroupApplyEntity apply = groupDao.findApply(groupId, memberId);
		if (apply == null) {
			return null;
		}
		return apply.getApplyforstatus();
	}

	/**
	 * 禁止群组中具体成员，1为禁止状态
	 * @param groupId 群组id
	 * @param memberId 成员id
	 */
	@Override
	@Transactional
	public void banMember(Long groupId, Long memberId) {
		groupDao.updateMemberStatus(groupId, memberId, 1);
	}

	/**
	 * 解禁群组中具体成员，0为正常状态
	 * @param groupId 群组id
	 * @param memberId 成员id
	 */
	@Override
	@Transactional
	public void unbanMember(Long groupId, Long memberId) {
		groupDao.updateMemberStatus(groupId, memberId, 0);
	}

	/**
	 * 查看群组是否存在
	 * @param groupName 群组名
	 * @param groupType 群组类型
	 * @param groupEnterprise 企业id
	 * @return 存在返回true,不存在返回false
	 */
	@Override
	@Transactional
	public Boolean isGroupExist(String groupName, String groupType,
			Long groupEnterprise) {
		return groupDao.findGroup(groupName, groupEnterprise) != null ? true : false;
	}

	/**
	 * 创建群组
	 * @param groupEntity 群组信息实体
	 * @param userId 用户id
	 */
	@Override
	@Transactional
	public void createGroup(SwGroupInfoEntity groupEntity, Long userId) {
		String name = groupEntity.getName();
		Long enterpriseId = groupEntity.getEnterpriseid();
		//判断关键信息不为null
		if (name != null && groupEntity.getAbbr() !=null 
				&& !isGroupExist(name,null , enterpriseId)) {			
			//插入群组实体
			groupEntity.setMemberqty(1);
			groupDao.create(groupEntity);
			//插入创建者的群组成员信息			
			SwGroupMemberEntity member = new SwGroupMemberEntity();
			Timestamp time = groupEntity.getCreatedate();
			Long groupId = groupDao.findGroupByName(name).getId();

			member.setGroupid(groupId);
			member.setMemberid(userId);
			member.setIsforbidden(0);
			member.setRoleid(groupDao.findRole("创建者", enterpriseId));
			member.setEnterdate(time);
			groupDao.create(member);
			//插入管理员的群组成员信息
			SwGroupMemberEntity member2 = new SwGroupMemberEntity();
			
			member2.setGroupid(groupId);
			member2.setIsforbidden(0);
			member2.setEnterdate(time);
			member2.setMemberid(userId);
			member2.setRoleid(groupDao.findRole("管理员", enterpriseId));
			groupDao.create(member2);
		}
	}

	/**
	 * 删除群组
	 * @param groupId 群组id
	 */
	@Override
	@Transactional
	public void removeGroup(Long groupId) {
		 Integer count =  groupDao.findGroupById(groupId).getMemberqty();
		if (count == null || count <= 1) {
			groupDao.deleteGroupById(groupId);
		}
	}

	/**
	 * 更新群组皮肤
	 * @param groupId 群组id
	 * @param skinId 皮肤id
	 */
	@Override
	@Transactional
	public void modifySkin(Long groupId, Long skinId) {
		SwGroupInfoEntity groupInfo = groupDao.findGroupById(groupId);
		if (groupInfo != null) {
			groupInfo.setSkinid(skinId);
			groupDao.update(groupInfo);
		}
	}

	/**
	 * 更新群组信息表
	 * @param groupEntity 群组信息实体
	 */
	@Override
	@Transactional
	public void modifyGroupInfo(SwGroupInfoEntity groupEntity) {
		if (groupEntity.getId() != null && groupEntity.getName() != null && groupEntity.getAbbr() != null) {
			groupDao.update(groupEntity);
		}
	}

	/**
	 * 查找某人创建的群组信息
	 * @param memberId 成员id
	 * @param roleId 角色id
	 * @return 群组信息列表
	 */
	@Override
	@Transactional
	public List<SwGroupInfoEntity> getGroupCreat(Long memberId,
			Long roleId) {
		List<SwGroupMemberEntity> memberList = groupDao.findMemberByroleId(memberId, roleId);
		if(memberList.size() != 0) {
			List<SwGroupInfoEntity> groupInfoList = new ArrayList<SwGroupInfoEntity>();
			Iterator<SwGroupMemberEntity> it = memberList.iterator();
			while (it.hasNext()) {
				SwGroupInfoEntity group = groupDao.findGroupById(it.next().getGroupid());
				if (group != null) {
					groupInfoList.add(group);
				}			
			}
			return groupInfoList;
		}
		return null;
	}

	/**
	 * 查找某人所属的群组信息
	 * @param memberId 成员id
	 * @param typeId 群组类型id
	 * @return 群组信息列表
	 */
	@Override
	@Transactional
	public List<SwGroupInfoEntity> getGroupBelong(Long memberId,
			Long typeId) {
		List<Long> memberList = groupDao.findGroupId(memberId);
		if (memberList.size() != 0) {
			List<SwGroupInfoEntity> groupInfoList = new ArrayList<SwGroupInfoEntity>();
			Iterator<Long> it = memberList.iterator();
			while (it.hasNext()) {
				SwGroupInfoEntity group = null;
				if (typeId == null) {
					group = groupDao.findGroupById(it.next());
				} else {
					group = groupDao.findGroup(it.next(),typeId);
				}
				
				if (group != null) {
					groupInfoList.add(group);
				}			
			}
			return groupInfoList;
		}
		return null;
	}

	/**
	 * 获取所有群组计数信息
	 * @return 所有的群组计数信息
	 */
	@Override
	@Transactional
	public List<SwGroupStatisticsEntity> getCounteInfo() {
		return groupDao.findStatistics();
	}

	/**
	 * 根据id获取群组信息
	 * @param groupId 群组id
	 * @return 群组信息
	 */
	@Override
	@Transactional
	public SwGroupInfoEntity getGroupInfo(Long groupId) {
		return groupDao.findGroupById(groupId);
	}

	/**
	 * 群组类型级关闭
	 * @param groupTypeId 群组类型id
	 * @param groupEnterprise 企业id
	 */
	@Override
	@Transactional
	public void closeGroupType(Long groupTypeId,
			Long groupEnterprise) {
		groupDao.modifyGroupType(groupTypeId, groupEnterprise, 1, DateUtil.dateToTimeStamp(new Date()));
	}

	/**
	 * 关闭具体群组
	 * @param groupId 群组id
	 */
	@Override
	@Transactional
	public void closeGroup(Long groupId) {
		groupDao.modifyGroupStatus(groupId, 2, DateUtil.dateToTimeStamp(new Date()));
	}

	/**
	 * 启用群组类型
	 * @param groupTypeId 群组id
	 * @param groupEnterprise 企业id
	 */
	@Override
	@Transactional
	public void restartGroupType(Long groupTypeId,
			Long groupEnterprise) {
		groupDao.modifyGroupType(groupTypeId, groupEnterprise, 0, null);
	}

	/**
	 * 启用群组
	 * @param groupId 群组id
	 */
	@Override
	@Transactional
	public void restartGroup(Long groupId) {
		groupDao.modifyGroupStatus(groupId, 0, null);
	}

	/**
	 * 检查某应用是否被群组使用
	 * @param appKey 应用key
	 * @param groupId 群组id
	 * @return 已使用返回true,未使用返回false
	 */
	@Override
	@Transactional
	public Boolean isApplicationExist(String appKey,
			Long groupId) {
		return groupDao.findApp(groupId, appKey) != null;
	}

	/**
	 * 添加群组应用
	 * @param groupId 群组id
	 * @param appKey 应用key
	 */
	@Override
	@Transactional
	public void addApplication(Long groupId, String appKey) {
		if (!isApplicationExist(appKey, groupId)) {
			SwGroupAppEntity app = new SwGroupAppEntity();
			app.setAppkey(appKey);
			app.setGroupid(groupId);
			groupDao.create(app);
		}
	}

	/**
	 * 获取指定群组应用的key列表
	 * @param groupID 群组id
	 * @return 群组应用key列表
	 */
	@Override
	@Transactional
	public List<String> getAppKeys(Long groupID) {
		return groupDao.findAppKey(groupID);
	}

	/**
	 * 禁用群组的具体应用
	 * @param groupId 群组id
	 * @param appKey 群组应用key
	 */
	@Override
	@Transactional
	public void banApplication(Long groupId, String appKey) {
		groupDao.modifyAppStatus(groupId, appKey, 1);
	}

	/**
	 * 解禁群组的具体应用
	 * @param groupId 群组id
	 * @param appKey 群组应用key
	 */
	@Override
	@Transactional
	public void unbanApplication(Long groupId, String appKey) {
		groupDao.modifyAppStatus(groupId, appKey, 0);
	}

	/**
	 * 移除群组的具体应用
	 * @param groupId 群组id
	 * @param appKey 群组应用key
	 */
	@Override
	@Transactional
	public void removeApplication(Long groupId, String appKey) {
		groupDao.deleteApp(groupId, appKey);
	}

}
