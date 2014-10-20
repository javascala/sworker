package com.sworker.logic;

import java.util.Date;
import java.util.List;

import com.sworker.entity.SwGroupApplyEntity;
import com.sworker.entity.SwGroupInfoEntity;
import com.sworker.entity.SwGroupStatisticsEntity;

/**
 * 
 * Created by jiangcy on 2014/9/9
 *
 */
public interface IGroupLogic {
	
	/**
	 * 指定成员是否是指定群组成员
	 * @param userID 成员id
	 * @param groupID 群组id
	 * @return 存在时返回true，不存在返回false
	 */
	public Boolean isMember(Long userID, Long groupID);
	/**
	 * 指定成员是否是群组管理员
	 * @param userID 用户id
	 * @param groupsID 群组id
	 * @return 是群组管理员返回true,否则false
	 */
	public Boolean isGroupAdmin(Long userID, Long groupsID);
	/**
	 * 指定成员是否是群组创建者
	 * @param userID 用户id
	 * @param groupsID 群组id
	 * @return 是群组创建者返回true,否则false
	 */
	public Boolean isGroupCreator(Long userID, Long groupsID);
	/**
	 * 指定成员是否被禁言
	 * @param userID 用户id
	 * @param groupsID 群组id
	 * @return 被禁言返回true,否则false
	 */
	public Boolean isForbidden(Long userID, Long groupsID);
	/**
	 * 添加新成员
	 * @param groupId 群组id
	 * @param memberId 成员id
	 * @param roleId 角色id
	 */
	public String addMember(Long groupId, Long memberId, Long roleId);
	/**
	 * 添加成员申请
	 * @param groupId 群组id
	 * @param memberId 成员id
	 * @param applyForReason 申请理由
	 * @param applyForStatus 申请状态
	 * @param applyForTime  申请时间
	 */
	public String addApplyFor(Long groupId, Long memberId, String applyForReason,
			Integer applyForStatus, Date applyForTime);
	/**
	 * 删除群组成员
	 * @param groupId 群组id
	 * @param memberId 成员id
	 */
	public void removeMember(Long groupId, Long memberId);
	/**
	 * 更新成员在群组中角色
	 * @param groupId 群组id
	 * @param memberId 成员id
	 * @param roleIdNew 新角色id
	 */
	public void modifyMemberRole(Long groupId, Long memberId, Long roleIdNew);
	/**
	 * 更改申请理由
	 * @param groupId 群组id
	 * @param memberId 成员id
	 */
	public void modifyApplyForReason(Long groupId, Long memberId, String applyForReason);
	/**
	 * 更该申请状态
	 * @param groupId 群组id
	 * @param memberId 成员id
	 * @param applyForStatus 申请状态
	 */
	public void modifyApplyForStatus(Long groupId, Long memberId, Integer applyForStatus);
	/**
	 * 屏蔽指定群组信息，0为屏蔽状态，1为不屏蔽状态
	 * @param groupId 群组id
	 * @param userId 成员id
	 * @return 成功返回true，否则返回false
	 */
	public Boolean shieldedGroup(Long groupId, Long userId);
	/**
	 *取消指定群组屏蔽信息，0为屏蔽状态，1为不屏蔽状态
	 * @param groupId 群组id
	 * @param userId 成员id
	 * @return 成功返回true，否则返回false
	 */
	public Boolean shieldedCancle(Long groupId, Long userId);
	/**
	 * 根据群组id获取成员信息
	 * @param groupId 群组id
	 * @return 返回成员信息列表
	 */
	public <T>List<T> getMembers(Long groupId);
	/**
	 * 根据群组id查找群组申请信息
	 * @param groupId 群组id
	 * @return 返回群组信息列表
	 */
	public List<SwGroupApplyEntity> getApplyFor(Long groupId);
	/**
	 * 查找群组中成员申请状态
	 * @param groupId 群组id
	 * @param memberId 成员id
	 * @return 成员的申请状态
	 */
	public Integer getMemApplyFor(Long groupId, Long memberId);
	/**
	 * 禁止群组中具体成员，1为禁止状态
	 * @param groupId 群组id
	 * @param memberId 成员id
	 */
	public void banMember(Long groupId, Long memberId);
	/**
	 * 解禁群组中具体成员，0为正常状态
	 * @param groupId 群组id
	 * @param memberId 成员id
	 */
	public void unbanMember(Long groupId, Long memberId);
	/**
	 * 查看群组是否存在
	 * @param groupName 群组名
	 * @param groupType 群组类型
	 * @param groupEnterprise 企业id
	 * @return 存在返回true,不存在返回false
	 */
	public Boolean isGroupExist(String groupName, String groupType, Long groupEnterprise);
	/**
	 * 创建群组
	 * @param groupEntity 群组信息实体
	 * @param userId 用户id
	 */
	public void createGroup(SwGroupInfoEntity groupEntity, Long userId);
	/**
	 * 删除群组
	 * @param groupId 群组id
	 */
	public void removeGroup(Long groupId);
	/**
	 * 更新群组皮肤
	 * @param groupId 群组id
	 * @param skinId 皮肤id
	 */
	public void modifySkin(Long groupId, Long skinId);
	/**
	 * 更新群组信息表
	 * @param groupEntity 群组信息实体
	 */
	public void modifyGroupInfo(SwGroupInfoEntity groupEntity);
	/**
	 * 查找某人创建的群组信息
	 * @param memberId 成员id
	 * @param roleId 角色id
	 * @return 群组信息列表
	 */
	public List<SwGroupInfoEntity> getGroupCreat(Long memberId, Long roleId);
	/**
	 * 查找某人所属的群组信息
	 * @param memberId 成员id
	 * @param typeId 群组类型id
	 * @return 群组信息列表
	 */
	public List<SwGroupInfoEntity> getGroupBelong(Long memberId, Long typeId);
	/**
	 * 获取所有群组计数信息
	 * @return 所有的群组计数信息
	 */
	public List<SwGroupStatisticsEntity> getCounteInfo();
	/**
	 * 根据id获取群组信息
	 * @param groupId 群组id
	 * @return 群组信息
	 */
	public SwGroupInfoEntity getGroupInfo(Long groupId);
	/**
	 * 群组类型级关闭
	 * @param groupTypeId 群组类型id
	 * @param groupEnterprise 企业id
	 */
	public void closeGroupType(Long groupTypeId, Long groupEnterprise);
	/**
	 * 关闭具体群组
	 * @param groupId 群组id
	 */
	public void closeGroup(Long groupId);
	/**
	 * 启用群组类型
	 * @param groupTypeId 群组id
	 * @param groupEnterprise 企业id
	 */
	public void restartGroupType(Long groupTypeId, Long groupEnterprise);
	/**
	 * 启用群组
	 * @param groupId 群组id
	 */
	public void restartGroup(Long groupId);
	/**
	 * 检查某应用是否被群组使用
	 * @param appKey 应用key
	 * @param groupId 群组id
	 * @return 已使用返回true,未使用返回false
	 */
	public Boolean isApplicationExist(String appKey, Long groupId);
	/**
	 * 添加群组应用
	 * @param groupId 群组id
	 * @param appKey 应用key
	 */
	public void addApplication(Long groupId, String appKey);
	/**
	 * 获取指定群组应用的key列表
	 * @param groupID 群组id
	 * @return 群组应用key列表
	 */
	public List<String> getAppKeys(Long groupID);
	/**
	 * 禁用群组的具体应用
	 * @param groupId 群组id
	 * @param appKey 群组应用key
	 */
	public void banApplication(Long groupId, String appKey);
	/**
	 * 解禁群组的具体应用
	 * @param groupId 群组id
	 * @param appKey 群组应用key
	 */
	public void unbanApplication(Long groupId, String appKey);
	/**
	 * 移除群组的具体应用
	 * @param groupId 群组id
	 * @param appKey 群组应用key
	 */
	public void removeApplication(Long groupId, String appKey);
	
}
