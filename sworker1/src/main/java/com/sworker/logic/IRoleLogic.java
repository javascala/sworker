package com.sworker.logic;

import java.util.List;

import com.sworker.entity.SwRoleInfoEntity;

/**
 * 
 * @author wanggang 2014/9/03.
 *
 */
public interface IRoleLogic {

	/**
	 * 判断角色是否已经存在
	 * @param role 角色
	 * @param enterpriseId 企业ID
	 * @param level 级别
	 * @param subLevel 子级别
	 * @return 检索如果存在则返回true，如果检索不存在则返回false
	 */
	public Boolean isExist(String role,Long enterpriseId ,Integer level,Integer subLevel);
	
	/**
	 * 判断角色是否可以更改
	 * @param roleId 角色ID
	 * @return 如果检索的类别是内置，则不可以更改，返回false；否则为true
	 */
	public Boolean isAlterable(Long roleId);
	
	/**
	 * 判断角色是否可用
	 * @param roleId 角色ID
	 * @return 如果检索的状态是1-正常，则可用，返回true；否则为false
	 */
	public Boolean isEnable(Long roleId);
	
	/**
	 * 添加角色
	 * @param roleEntity
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
	 * @return 角色实体列表
	 */
	public List<SwRoleInfoEntity> getAllRoles(Long enterpriseId,Integer pageIndex,Integer pageSize);
	
	/**
	 * 删除角色
	 * @param roleId 角色ID
	 */
	public void removeRole(Long roleId);
	
	/**
	 * 更新角色状态
	 * @param roleId 角色ID
	 * @param status 角色状态
	 */
	public void modifyRoleStatus(Long roleId,Integer status);
	
	/**
	 * 更新角色描述
	 * @param roleId 角色ID
	 * @param description 角色描述
	 */
	public void modifyRoleDesc(Long roleId,String description);
	
	/**
	 * 判断角色中是否已经有了某种权限
	 * @param roleId 角色ID 
	 * @param authoritykey 权限Key
	 * @return  如果检索结果有，就返回true；否则返回false
	 */
	public Boolean isAuthorityExist(Long roleId,String authoritykey);
	
	/**
	 * 添加权限
	 * @param roleId 角色ID
	 * @param authoritykey 权限KEY
	 */
	public void addRoleAuthority(Long roleId,String authoritykey);
	
	/**
	 * 获取角色拥有的权限
	 * @param roleId 角色ID
	 * @return 权限Key列表
	 */
	public List<String> getRoleAuthorities(Long roleId);
	
	/**
	 * 删除角色的权限
	 * @param roleId 角色ID
	 * @param authoritykey 权限Key
	 */
	public void removeRoleAuthority(Long roleId,String authoritykey);
	
	/**
	 * 删除角色全部权限
	 * @param roleId 角色ID
	 */
	public void removeAllRoleAuthorities(Long roleId);
	
	
	/**
	 * 判断角色中是否已经有了某个账号
	 * @param roleId 角色ID
	 * @param userId 用户ID
	 * @return 如果检索结果有，就返回true；否则返回false
	 */
	public Boolean isUserExist(Long roleId,Long userId);
	
	/**
	 * 添加人员账号
	 * @param roleId 角色ID
	 * @param userId 用户ID
	 */
	public void addRoleUser(Long roleId,Long userId);
	
	/**
	 * 获取相同角色的账号人员列表
	 * @param roleId 角色ID
	 * @param pageIndex 开始页
	 * @param pageSize 分页大小
	 * @return 账号ID列表
	 */
	public List<Long> getRoleUsers(Long roleId,Integer pageIndex,Integer pageSize);
	
	/**
	 * 删除使用角色的账号
	 * @param roleId 角色Id
	 * @param userId 账号Id
	 */
	public void removeRoleUser(Long roleId,Long userId);
	
	
	/**
	 * 删除使用角色的所有账号
	 * @param roleId 角色ID
	 */
	public void removeAllRoleUsers(Long roleId);
	
	
}
