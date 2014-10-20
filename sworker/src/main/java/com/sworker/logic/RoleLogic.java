package com.sworker.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.dao.IRoleDao;
import com.sworker.entity.SwRoleInfoEntity;
import com.sworker.model.RoleParam;

/**
 * 
 * @author wanggang 2014/9/03.
 *
 */
@Component("rolelogic")
public class RoleLogic implements IRoleLogic {
	
	@Resource
	private IRoleDao roledao;
	
	/**
	 * 判断角色是否已经存在
	 * @param role 角色
	 * @param enterpriseId 企业ID
	 * @param level 级别
	 * @param subLevel 子级别
	 * @return 检索如果存在则返回true，如果检索不存在则返回false
	 */
	@Override
	@Transactional
	public Boolean isExist(String role, Long enterpriseId, Integer level,
			Integer subLevel) {

		List<SwRoleInfoEntity> result=roledao.tryToFindByParam(role, enterpriseId, level, subLevel);
		
		if(result.size()>0){ return true; }
		
		else{return false;}
	}

	/**
	 * 判断角色是否可以更改
	 * @param roleId 角色ID
	 * @return 如果检索的类别是内置，则不可以更改，返回false；否则为true
	 */
	@Override
	@Transactional
	public Boolean isAlterable(Long roleId) {
		RoleParam role=	roledao.findParam(roleId);
		//1代表状态可用
		if(role.getStatus().equals(1)){
			//1代表角色为内置类型
			if(role.getRoletype().equals(1)){
				return false;
			}else {return true;}
		}else {return false;}
	}
	
	/**
	 * 判断角色是否可用
	 * @param roleId 角色ID
	 * @return 如果检索的状态是1-正常，则可用，返回true；否则为false
	 */
	@Override
	@Transactional
	public Boolean isEnable(Long roleId) {
		RoleParam role=roledao.findParam(roleId);
		if(role.getStatus().equals(1)){
			return true;
		}
		return false;
	}

	/**
	 * 添加角色
	 * @param roleEntity 角色实体
	 */
	@Override
	@Transactional
	public void addRole(SwRoleInfoEntity roleEntity) {
		if(roledao.tryToFindByParam(roleEntity.getRole(), 
				roleEntity.getEnterpriseid(), roleEntity.getLevel(), 
				roleEntity.getSublevel() ).size()==0){
			//返回结果列表如果为空说明角色不存在，可以创建角色
			roledao.addRole(roleEntity);
		}
	}
	
	
	/**
	 * 获取角色基本信息
	 * @param roleId 角色ID
	 * @return 角色实体
	 */
	@Override
	@Transactional
	public SwRoleInfoEntity getRole(Long roleId) {
		return roledao.getRole(roleId) ;
	}

	
	/**
	 * 获取企业内的所有角色
	 * @param enterpriseId 企业ID
	 * @return 角色实体列表
	 */
	@Override
	@Transactional
	public List<SwRoleInfoEntity> getAllRoles(Long enterpriseId,Integer pageIndex,Integer pageSize) {
		return	roledao.getRoleList(enterpriseId, pageIndex, pageSize);
	}

	/**
	 * 删除角色
	 * @param roleId 角色ID
	 */
	@Override
	@Transactional
	public void removeRole(Long roleId) {
		// TODO 在权限服务或者用户服务中判断该账号是否有删除权限，有则继续
		if(isAlterable(roleId)){
		//是否可修改，可以则继续	
			if(roledao.checkUser(roleId).size()==0){
				//TODO 删除角色权限
				roledao.deleteRoleAuthority(roleId);
				roledao.deleteRole(roleId);
			}
		}
	}
	
	/**
	 * 更新角色状态
	 * @param roleId 角色ID
	 * @param status 角色状态
	 */
	@Override
	@Transactional
	public void modifyRoleStatus(Long roleId, Integer status) {
		roledao.updateRoleStatus(roleId, status);
	}

	/**
	 * 更新角色描述
	 * @param roleId 角色ID
	 * @param description 角色描述
	 */
	@Override
	@Transactional
	public void modifyRoleDesc(Long roleId, String description) {
		roledao.updateRoleDesc(roleId, description);
	}
	
	/**
	 * 判断角色中是否已经有了某种权限
	 * @param roleId 角色ID 
	 * @param authoritykey 权限Key
	 * @return  如果检索结果有，就返回true；否则返回false
	 */
	@Override
	@Transactional
	public Boolean isAuthorityExist(Long roleId, String authoritykey) {
		List<Long> resultList=roledao.checkAuthority(roleId, authoritykey);
		if(resultList.size()>0){
			return true;
		}else{
			return false;
		}
	
	}

	
	/**
	 * 添加权限
	 * @param roleId 角色ID
	 * @param authoritykey 权限KEY
	 */
	@Override
	@Transactional
	public void addRoleAuthority(Long roleId, String authoritykey) {
		if(isEnable(roleId)){
			if(isAlterable(roleId)){
				//isEnable和isAlterable两个方法都会调用RoleDao的findParam方法会重复查询，怎么优化？
				List<Long> result=roledao.checkAuthority(roleId, authoritykey);
				//如果返回列表为空，说明此角色不具有该权限
				if(result.size()==0){
					roledao.addAuthority(roleId, authoritykey);
				}
			}
		}
	}

	/**
	 * 获取角色拥有的权限
	 * @param roleId 角色ID
	 * @return 权限Key列表
	 */
	@Override
	@Transactional
	public List<String> getRoleAuthorities(Long roleId) {
		return  roledao.getAuthoritykeys(roleId);
	}

	/**
	 * 删除角色的权限
	 * @param roleId 角色ID
	 * @param authoritykey 权限Key
	 */
	@Override
	@Transactional
	public void removeRoleAuthority(Long roleId, String authoritykey) {
		roledao.deleteRoleAuthority(roleId, authoritykey);
	}
	
	/**
	 * 删除角色全部权限
	 * @param roleId 角色ID
	 */
	@Override
	@Transactional
	public void removeAllRoleAuthorities(Long roleId) {
		roledao.deleteRoleAuthority(roleId);
	}


	/**
	 * 判断角色中是否已经有了某个账号
	 * @param roleId 角色ID
	 * @param userId 用户ID
	 * @return 如果检索结果有，就返回true；否则返回false
	 */
	@Override
	@Transactional
	public Boolean isUserExist(Long roleId, Long userId) {
		Long result=roledao.checkUserExist(roleId, userId);
		if(result != null){
			return true;
		}
		return false;
	}

	/**
	 * 添加人员账号
	 * @param roleId 角色ID
	 * @param userId 用户ID
	 */
	@Override
	@Transactional
	public void addRoleUser(Long roleId, Long userId) {
	 if(isEnable(roleId)){
		Long result=roledao.checkUserExist(roleId, userId);	
		if(result==null){
			roledao.addRoleUser(roleId, userId);
		}
			}
	}
	
	/**
	 * 获取相同角色的账号人员列表
	 * @param roleId 角色ID
	 * @param pageIndex 开始页
	 * @param pageSize 分页大小
	 * @return 账号ID列表
	 */
	@Override
	@Transactional
	public List<Long> getRoleUsers(Long roleId, Integer pageIndex,
			Integer pageSize) {
		return 	roledao.getRoleUsers(roleId, pageIndex, pageSize);
	}
		/**
	 * 删除使用角色的账号
	 * @param roleId 角色Id
	 * @param userId 账号Id
	 */
	@Override
	@Transactional
	public void removeRoleUser(Long roleId, Long userId) {
		roledao.deleteRoleUser(roleId, userId);
	}

	/**
	 * 删除使用角色的所有账号
	 * @param roleId 角色ID
	 */
	@Override
	@Transactional
	public void removeAllRoleUsers(Long roleId) {
		roledao.deleteRoleUser(roleId);
		
	}
}
