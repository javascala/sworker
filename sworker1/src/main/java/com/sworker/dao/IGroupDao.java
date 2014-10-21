package com.sworker.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.sworker.dao.common.IOperations;
import com.sworker.entity.SwGroupAppEntity;
import com.sworker.entity.SwGroupApplyEntity;
import com.sworker.entity.SwGroupInfoEntity;
import com.sworker.entity.SwGroupMemberEntity;
import com.sworker.entity.SwGroupStatisticsEntity;

/**
 * 
 * Created by jiangcy on 2014/9/9
 *
 */
public interface IGroupDao<T extends Serializable> extends IOperations<T> {

	/**
	 * 查找指定的群组，指定成员的信息
	 * @param userId 用户id
	 * @param groupId 群组id
	 * @param roleId 角色id
	 * @return 返回成员信息
	 */
	public SwGroupMemberEntity findMember(Long userId, Long groupId, Long roleId);
	/**
	 * 查找指定的群组，指定成员的信息
	 * @param userId 用户id
	 * @param groupId 群组id
	 * @return 返回成员信息
	 */
	public List<SwGroupMemberEntity> findMember(Long userId, Long groupId);
	/**
	 * 判断成员的角色身份
	 * @param userId 用户id
	 * @param groupId 群组id
	 * @param roleType 角色类型
	 * @return 存在返回true，否则返回false
	 */
	public Long findRole(Long userId, Long groupId, String roleType);
	/**
	 * 通过群组id查找角色id
	 * @param groupId 群组id
	 * @param role 角色
	 * @return 角色id
	 */
	public Long findRole(Long groupId, String role);
	/**
	 * 获取角色id
	 * @param role 角色
	 * @param enterpriseId 企业id
	 * @return 角色的id
	 */
	public Long findRole(String role, Long enterpriseId);
	/**
	 * 删除成员
	 * @param groupId 群组id
	 * @param memberId 成员id
	 */
	public void deleteMember(Long groupId, Long memberId);
	/**
	 * 更改成员角色
	 * @param groupId 群组id
	 * @param memberId 成员id
	 * @param roleNew 新角色id
	 */
	public void updateMemberRole(Long groupId, Long memberId, Long roleNew);
	/**
	 * 根据群组id获取成员信息
	 * @param groupId 群组id
	 * @return 返回群组成员的信息列表
	 */
	public List<T> findMembersInfo(Long groupId);
	/**
	 * 根据群组id检索群组所有申请情况
	 * @param groupId 群组id
	 * @return 群组申请信息列表
	 */
	public List<SwGroupApplyEntity> findApply(Long groupId);
	/**
	 * 根据成员id检索群组申请信息
	 * @param groupId 群组id
	 * @param memberId 成员id
	 * @return 群组申请信息列表
	 */
	public SwGroupApplyEntity findApply(Long groupId, Long memberId);
	/**
	 * 更改成员的禁止状态
	 * @param groupId 群组id
	 * @param memberId 成员id
	 * @param status 1为禁止状态，0为正常状态
	 */
	public void updateMemberStatus(Long groupId, Long memberId, Integer status);
	/**
	 *查找群组信息
	 *@param groupName 群组名
	 *@param goupType 群组类型
	 *@param enterpriseId 企业id
	 *@return 返回群组信息实体
	 */
	public SwGroupInfoEntity findGroup(String groupName, Long enterpriseId);
	/**
	 * 根据群组id和类型id查找群组信息
	 * @param groupId 群组id
	 * @param typeId 群组类型id
	 * @return 群组信息
	 */
	public SwGroupInfoEntity findGroup(Long groupId, Long typeId);
	/**
	 * 通过群组名查找群组信息
	 * @param name 群组名
	 * @return 群组信息
	 */
	public SwGroupInfoEntity findGroupByName(String name);
	/**
	 * 查找某人创建的群组
	 * @param memberId 成员id
	 * @param roleId 角色id
	 * @return 群组成员信息列表
	 */
	public List<SwGroupMemberEntity> findMemberByroleId(Long memberId, Long roleId);
	/**
	 * 查找某人所属的群组信息
	 * @param memberId 成员id
	 * @return 群组成员信息列表
	 */
	public List<Long> findGroupId(Long memberId);
	/**
	 * 修改群组状态
	 * @param groupTypeId 群组类型id
	 * @param enterpriseId 群组企业id
	 * @param status 0为正常状态，1为群组类型级关闭，2为群组级关闭
	 */
	public void modifyGroupType(Long groupTypeId, Long enterpriseId, Integer status, Timestamp time);
	/**
	 * 更改群组状态
	 * @param groupId 群组id
	 * @param status 0为正常状态，1为群组类型级关闭，2为群组级关闭
	 */
	public void modifyGroupStatus(Long groupId, Integer status, Timestamp time);
	/**
	 * 查找群组指定定的应用信息
	 * @param groupId 群组id
	 * @param appKey 应用key
	 * @return 群组应用信息
	 */
	public SwGroupAppEntity findApp(Long groupId, String appKey);
	/**
	 * 获取群组应用的主键
	 * @param groupId 群组id
	 * @return 群组应用key
	 */
	public List<String> findAppKey(Long groupId);
	/**
	 * 修改企业的应用状态
	 * @param groupId 群组id
	 * @param appKey 应用key
	 * @param status 1为禁止状态，0为正常状态
	 */
	public void modifyAppStatus(Long groupId, String appKey, Integer status);
	/**
	 * 删除群组的具体应用
	 * @param groupId 群组id
	 * @param appKey 应用key
	 */
	public void deleteApp(Long groupId, String appKey);
	/**
	 * 通过id获取群组信息
	 * @param id 群组id
	 * @return 群组信息实体
	 */
	public SwGroupInfoEntity findGroupById(Long id);
	/**
	 * 通过id删除群组
	 * @param id 群组id
	 */
	public void deleteGroupById(Long id);
	/**
	 * 查找所有群组数据信息
	 * @return 群组信息列表
	 */
	public List<SwGroupStatisticsEntity> findStatistics();
	
}
