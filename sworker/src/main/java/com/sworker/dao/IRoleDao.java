package com.sworker.dao;

import java.util.List;

import com.sworker.entity.SwRoleInfoEntity;
import com.sworker.model.RoleParam;

/**
 * 
 * @author wanggang 2014/9/03.
 *
 */
public interface IRoleDao {
	
	/**
	 * 判断角色是否已经存在
	 * @param role 角色
	 * @param enterpriseId 企业ID
	 * @param level 级别
	 * @param subLevel 子级别
	 * @return 检索如果存在则返回true，如果检索不存在则返回false
	 */
	public List<SwRoleInfoEntity> tryToFindByParam(String role,Long enterpriseId ,Integer level,Integer subLevel);
	
	/**
	 * 判断角色是否可以更改
	 * @param roleId 角色ID
	 * @return 如果检索的类别是内置，则不可以更改，返回false；否则为true
	 */
	public RoleParam findParam(Long roleId);
	
	/**
	 * 添加角色
	 * @param roleEntity 角色实体
	 */
	public void addRole(SwRoleInfoEntity roleEntity);
	
	/**
	 * 获取角色基本信息
	 * @param roleId 角色ID
	 * @return 角色实体
	 */
	public SwRoleInfoEntity getRole(Long roleId);
	
	/**
	 * 获取企业内的所有角色
	 * @param enterpriseId 企业ID
	 * @return 角色列表
	 */
	public List<SwRoleInfoEntity> getRoleList(Long enterpriseId,Integer pageIndex,Integer pageSize);
	
	/**
	 * 判断角色对应的用户是否存在
	 * @param roleId 角色ID
	 * @return 用户ID
	 */
	public List<Integer> checkUser(Long roleId);
	
	/**
	 * 删除角色全部权限
	 * @param roleId 角色ID
	 */
	public void deleteRoleAuthority(Long roleId);
	
	/**
	 * 删除角色
	 * @param roleId 角色ID
	 */
	public void deleteRole(Long roleId);
	
	/**
	 * 更新角色状态
	 * @param roleId 角色ID
	 * @param status 角色状态
	 */
	public void updateRoleStatus(Long roleId, Integer status);
	
	/**
	 * 更新角色描述
	 * @param roleId 角色ID
	 * @param description 角色描述
	 */
	public void updateRoleDesc(Long roleId,String description);
	
	/**
	 * 检查角色权限是否存在
	 * @param roleId 角色ID
	 * @param authoritykey 权限key
	 * @return 角色权限ID
	 */
	public List<Long> checkAuthority(Long roleId, String authoritykey);
	
	/**
	 * 添加权限
	 * @param roleId 角色ID
	 * @param authoritykey 权限Key
	 */
	public void addAuthority(Long roleId, String authoritykey);
	
	/**
	 * 检索角色权限
	 * @param roleId 角色ID
	 * @return 角色权限
	 */
	public List<String> getAuthoritykeys(Long roleId);
	
	/**
	 * 删除角色权限关系
	 * @param roleId 角色ID
	 * @param authoritykey 权限Key
	 */
	public void deleteRoleAuthority(Long roleId, String authoritykey);
	
	
	/**
	 * 判断角色中是否已经有了某个账号
	 * @param roleId 角色ID
	 * @param userId 用户ID
	 * @return ID列表
	 */
	public Long checkUserExist(Long roleId,Long userId);
	
	/**
	 * 添加人员账号
	 * @param roleId 角色ID
	 * @param userId 用户ID
	 */
	public void addRoleUser(Long roleId,Long userId);
	
	/**
	 * 获取相同角色的账号人员列表
	 * @param roleId 角色ID
	 * @param pageIndex 起始页
	 * @param pageSize 分页大小
	 * @return userId列表
	 */
	public List<Long> getRoleUsers(Long roleId, Integer pageIndex,Integer pageSize);
	
	/**
	 * 删除使用角色的账号
	 * @param roleId 角色ID
	 * @param userId 用户ID
	 */
	public void deleteRoleUser(Long roleId,Long userId);
	
	/**
	 * 删除使用角色的所有账号
	 * @param roleId 角色ID
	 */
	public void deleteRoleUser(Long roleId);
	
}
