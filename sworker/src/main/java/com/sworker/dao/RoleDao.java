package com.sworker.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwRoleAccountEntity;
import com.sworker.entity.SwRoleAuthorityEntity;
import com.sworker.entity.SwRoleInfoEntity;
import com.sworker.model.RoleParam;

/**
 * 
 * @author wanggang 2014/9/03.
 *
 */
@Component("roledao")
public class RoleDao extends AbstractHibernateDao<SwRoleInfoEntity> implements IRoleDao {

	public RoleDao(){
			super();
			this.setClazz(SwRoleInfoEntity.class);
		}

	
	/**
	 * 尝试查找角色
	 * @param role 角色
	 * @param enterpriseId 企业ID
	 * @param level 级别
	 * @param subLevel 子级别
	 * @return 检索如果存在则返回角色列表
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<SwRoleInfoEntity> tryToFindByParam(String role, Long enterpriseId, Integer level,
			Integer subLevel) {
		Session session =getCurrentSession();
		//QBC检索
		List<SwRoleInfoEntity> resultList=session.createCriteria(SwRoleInfoEntity.class)
				.add(Restrictions.eq("role",role))
				.add(Restrictions.eq("enterpriseid", enterpriseId))
				.add(Restrictions.eq("level",level)).
				add(Restrictions.eq("sublevel", subLevel)).list();
		return resultList;
	}


	/**
	 * 通过参数去查找角色
	 * @param roleId 角色ID
	 * @return 将查找到的角色包装成一个自定义类返回
	 */
	@Override
	@Transactional
	public RoleParam findParam(Long roleId) {
		Session session=getCurrentSession();
		Query query=session.createQuery("select new com.sworker.model.RoleParam(role.roletype,role.status ) from SwRoleInfoEntity as role where role.id=:id");
		query.setParameter("id", roleId);
		return (RoleParam)query.uniqueResult();
	}


	/**
	 * 添加角色
	 * @param roleEntity 角色实体
	 */
	@Override
	@Transactional
	public void addRole(SwRoleInfoEntity roleEntity) {
		//调用父类方法
		create(roleEntity);
	}

	
	/**
	 * 获取角色基本信息
	 * @param roleId 角色ID
	 * @return 角色实体
	 */
	@Override
	@Transactional
	public SwRoleInfoEntity getRole(Long roleId) {
		Session session =getCurrentSession();
		String hql="from SwRoleInfoEntity where id=(:id)";
		Query query=session.createQuery(hql);
		query.setParameter("id", roleId);
		return (SwRoleInfoEntity) query.uniqueResult();
	}


	/**
	 * 获取企业内的所有角色,分页查询
	 * @param enterpriseId 企业ID
	 * @return 角色列表
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<SwRoleInfoEntity> getRoleList(Long enterpriseId,Integer pageIndex,Integer pageSize) {
		Session session =getCurrentSession();
		//QBC
		Criteria criteria=session.createCriteria(SwRoleInfoEntity.class)
			.add(Restrictions.eq("enterpriseid", enterpriseId))
			.addOrder(Order.asc("id"));
		//分页查询
		criteria.setFirstResult((pageIndex-1)*pageSize);
		criteria.setMaxResults(pageSize);
		return criteria.list();
	}

	/**
	 * 判断角色对应的用户是否存在
	 * @param roleId 角色ID
	 * @return 用户ID列表
	 */
	@Override
	@Transactional
	public List<Integer> checkUser(Long roleId) {
		Session session =getCurrentSession();
		String hql="select userid from com.sworker.entity.SwRoleAccountEntity  "
				+ "where roleid=:id order by userid asc";
		Query query =session.createQuery(hql);
		query.setLong("id", roleId);
		return query.list();
	}

	/**
	 * 删除角色全部权限
	 * @param roleId
	 */
	@Override
	@Transactional
	public void deleteRoleAuthority(Long roleId) {
		Session session =getCurrentSession();
		String hql="delete from com.sworker.entity.SwRoleAuthorityEntity where id=(:id)";
		Query query=session.createQuery(hql);
		query.setParameter("id", roleId);
		query.executeUpdate();
	}

	/**
	 * 删除角色
	 * @param roleId 角色ID
	 */
	@Override
	@Transactional
	public void deleteRole(Long roleId) {
		Session session =getCurrentSession();
		String hql="delete from SwRoleInfoEntity where id=(:id)";
		Query query=session.createQuery(hql);
		query.setParameter("id",roleId);
		query.executeUpdate();
	}

	
	
	/**
	 * 更新角色状态
	 * @param roleId 角色ID
	 * @param status 角色状态
	 */
	@Override
	@Transactional
	public void updateRoleStatus(Long roleId, Integer status) {
		Session session=getCurrentSession();
		String hql="from SwRoleInfoEntity where id=(:id)";
		Query query=session.createQuery(hql);
		query.setParameter("id",roleId);
		SwRoleInfoEntity role=(SwRoleInfoEntity)query.uniqueResult();
		role.setStatus(status);
		session.update(role);
	}

	/**
	 * 更新角色描述
	 * @param roleId 角色ID
	 * @param description 角色描述
	 */
	@Override
	@Transactional
	public void updateRoleDesc(Long roleId, String description) {
		Session session=getCurrentSession();
		String hql="from SwRoleInfoEntity where id=(:id)";
		Query query=session.createQuery(hql);
		query.setParameter("id", roleId);
		SwRoleInfoEntity role=(SwRoleInfoEntity)query.uniqueResult();
		   role.setDescription(description);
		   session.update(role);
	}


	/**
	 * 检查角色权限是否存在
	 * @param roleId 角色ID
	 * @param authoritykey 权限key
	 * @return 角色权限ID
	 */
	@Override
	@Transactional
	public List<Long> checkAuthority(Long roleId, String authoritykey) {
		Session session=getCurrentSession();
		String hql="select ra.id from SwRoleAuthorityEntity as ra where ra.roleid=(:id) and ra.authoritykey=(:key)";
		Query query=session.createQuery(hql);
		query.setParameter("id", roleId);
		query.setParameter("key", authoritykey);
		return query.list();
	}

	
	/**
	 * 添加权限
	 * @param roleId 角色ID
	 * @param authoritykey 权限Key
	 */
	@Override
	@Transactional
	public void addAuthority(Long roleId, String authoritykey) {
		Session session =getCurrentSession();
		SwRoleAuthorityEntity auth=new SwRoleAuthorityEntity();
	 	   auth.setRoleid(roleId);
	 	   auth.setAuthoritykey(authoritykey);
	 	   session.save(auth);
	}

	/**
	 * 检索角色权限
	 * @param roleId 角色ID
	 * @return 角色权限
	 */
	@Override
	@Transactional
	public List<String> getAuthoritykeys(Long roleId) {
		Session session=getCurrentSession();
		String hql="select auth.authoritykey from SwRoleAuthorityEntity as auth where auth.roleid=(:id) order by auth.authoritykey asc";
		Query query=session.createQuery(hql);
		query.setParameter("id", roleId);
		return	query.list();
	}

	/**
	 * 删除角色权限关系
	 * @param roleId 角色ID
	 * @param authoritykey 权限Key
	 */
	@Override
	@Transactional
	public void deleteRoleAuthority(Long roleId, String authoritykey) {
		Session session=getCurrentSession();
		String hql="delete from SwRoleAuthorityEntity where roleid=(:id) and authoritykey=(:key)";
		Query query=session.createQuery(hql);
		query.setParameter("id", roleId);
		query.setParameter("key", authoritykey);
		query.executeUpdate();
	}

	/**
	 * 判断角色中是否已经有了某个账号
	 * @param roleId 角色ID
	 * @param userId 用户ID
	 * @return ID列表
	 */
	@Override
	@Transactional
	public Long checkUserExist(Long roleId, Long userId) {
		Session session=getCurrentSession();
		//SwRoleAccountEntity
		String hql="select id from SwRoleAccountEntity where roleid=(:roleid) and userid=(:userid) ";
		Query query=session.createQuery(hql);
		query.setParameter("roleid", roleId);
		query.setParameter("userid", userId);
		return	(Long)query.uniqueResult();
	}

	/**
	 * 添加人员账号
	 * @param roleId 角色ID
	 * @param userId 用户ID
	 */
	@Override
	@Transactional
	public void addRoleUser(Long roleId, Long userId) {
		Session session =getCurrentSession();
		SwRoleAccountEntity roleaccount=new SwRoleAccountEntity();
		roleaccount.setRoleid(roleId);
		roleaccount.setUserid(userId);
		session.save(roleaccount);
	}

	/**
	 * 获取相同角色的账号人员列表
	 * @param roleId 角色ID
	 * @param pageIndex 起始页
	 * @param pageSize 分页大小
	 * @return userId列表
	 */
	@Override
	@Transactional
	public List<Long> getRoleUsers(Long roleId, Integer pageIndex,
			Integer pageSize) {
		Session session =getCurrentSession();
		String hql="select userid from SwRoleAccountEntity where roleid=(:id) order by userid asc ";
		Query query=session.createQuery(hql);
		query.setParameter("id", roleId);
		query.setFirstResult((pageIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
		
	}

	/**
	 * 删除使用角色的账号
	 * @param roleId 角色ID
	 * @param userId 用户ID
	 */
	@Override
	@Transactional
	public void deleteRoleUser(Long roleId, Long userId) {
		Session session=getCurrentSession();
		String hql="delete from SwRoleAccountEntity where roleid=(:roleid) and userid=(:userid)";
		Query query =session.createQuery(hql);
		query.setParameter("roleid", roleId);
		query.setParameter("userid", userId);
		query.executeUpdate();
	}


	/**
	 * 删除使用角色的所有账号
	 * @param roleId 角色ID
	 */
	@Override
	@Transactional
	public void deleteRoleUser(Long roleId) {
		Session session=getCurrentSession();
		String hql="delete from SwRoleAccountEntity where roleid=(:roleid)";
		Query query=session.createQuery(hql);
		query.setParameter("roleid", roleId);
		query.executeUpdate();
	}
}
