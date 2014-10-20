package com.sworker.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.dao.common.AbstractHibernateDao;
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
@Repository("groupDao")
@SuppressWarnings("unchecked")
public class GroupDao<T extends Serializable> extends AbstractHibernateDao<T> implements IGroupDao<T> {
	
	/**
	 * 查找指定的群组，指定成员的信息
	 * @param userId 用户id
	 * @param groupId 群组id
	 * @param roleId 角色id
	 * @return 返回成员信息
	 */
	@Override
	@Transactional
	public SwGroupMemberEntity findMember(Long userId, Long groupId,Long roleId) {
		Session session = getCurrentSession();
		Query query = session.createQuery("from SwGroupMemberEntity s where s.memberid = :user and "
				+ "s.groupid = :group and s.roleid = :role");
		query.setLong("user", userId);
		query.setLong("group", groupId);
		query.setLong("role", roleId);
		return (SwGroupMemberEntity)query.uniqueResult();		
	}
	
	/**
	 * 查找指定的群组，指定成员的信息
	 * @param userId 用户id
	 * @param groupId 群组id
	 * @return 返回成员信息
	 */
	@Override
	@Transactional
	public List<SwGroupMemberEntity> findMember(Long userId, Long groupId) {
		Session session = getCurrentSession();
		Query query = session.createQuery("from SwGroupMemberEntity s where s.memberid = :user and s.groupid = :group");
		query.setLong("user", userId);
		query.setLong("group", groupId);
		return query.list();		
	}
	
	/**
	 * 获取特定成员角色身份id
	 * @param userId 用户id
	 * @param groupId 群组id
	 * @param roleType 角色类型
	 * @return 存在返回true，否则返回false
	 */
	@Override
	@Transactional
	public Long findRole(Long userId, Long groupId, String role) {
		Session session = getCurrentSession();
		String hql = "select r.id from SwGroupMemberEntity m, SwRoleInfoEntity r where m.memberid = :userid and "
				+ "m.groupid = :groupid and m.roleid = r.id and r.level = 3 and r.role = :role";
		Query query = session.createQuery(hql);
		query.setLong("userid", userId);
		query.setLong("groupid", groupId);
		query.setString("role", role);
		return (Long) query.uniqueResult();
	}
	
	/**
	 * 通过群组id查找角色id
	 * @param groupId 群组id
	 * @param role 角色
	 * @return 角色id
	 */
	@Override
	@Transactional
	public Long findRole(Long groupId, String role) {
		Session session = getCurrentSession();
		String hql = "select r.id from SwGroupInfoEntity m, SwRoleInfoEntity r where m.id = :groupid and "
				+ "m.enterpriseid = r.enterpriseid and r.level = 3 and r.role = :role";
		Query query =session.createQuery(hql);
		query.setLong("groupid", groupId);
		query.setString("role", role);
		return (Long) query.uniqueResult();
	}
	
	/**
	 * 通过企业id获取角色id
	 * @param role 角色
	 * @param enterpriseId 企业id
	 * @return 角色的id
	 */
	@Override
	@Transactional
	public Long findRole(String role, Long enterpriseId) {
		Session session = getCurrentSession();	
		Query query = session.createQuery("select distinct s.id from SwRoleInfoEntity s ,SwGroupInfoEntity g "
			+  "where g.enterpriseid = :id and s.enterpriseid = g.enterpriseid and s.role = :role and s.level = 3");
		query.setLong("id", enterpriseId);
		query.setString("role", role);
		return (Long) query.uniqueResult();
	}
	
	/**
	 * 删除成员
	 * @param groupId 群组id
	 * @param memberId 成员id
	 */
	@Override
	@Transactional
	public void deleteMember(Long groupId, Long memberId) {
		Session session = getCurrentSession();
		Query query = session.createQuery("delete from SwGroupMemberEntity s where s.groupid = :groupid and "
				+ "s.memberid = :memberid");
		query.setLong("groupid", groupId);
		query.setLong("memberid", memberId);
		query.executeUpdate();
	}
	
	/**
	 * 更改成员角色
	 * @param groupId 群组id
	 * @param memberId 成员id
	 * @param roleNew 新角色id
	 */
	@Override
	@Transactional
	public void updateMemberRole(Long groupId, Long memberId, Long roleNew) {
		Session session = getCurrentSession();
		Query query = session.createQuery("update SwGroupMemberEntity s set s.roleid = :role where "
				+ "s.groupid = :groupid and s.memberid = :memberid");
		query.setLong("role", roleNew);
		query.setLong("groupid", groupId);
		query.setLong("memberid", memberId);
		query.executeUpdate();
	}
	
	/**
	 * 根据群组id获取成员信息
	 * @param groupId 群组id
	 * @return 返回群组成员的信息列表
	 */
	@Override
	@Transactional
	public List<T> findMembersInfo(Long groupId) {
		String hql = "from SwGroupMemberEntity m, SwRoleInfoEntity r "
				+ "where m.roleid = r.id and m.groupid = ? order by m.memberid desc";
		Object[] object = {groupId};
		return findByHql(hql, object);
	}
	
	/**
	 * 根据群组id检索群组所有申请情况
	 * @param groupId 群组id
	 * @return 群组申请信息列表
	 */
	@Override
	@Transactional
	public List<SwGroupApplyEntity> findApply(Long groupId) {
		String hql = "from SwGroupApplyEntity a where a.groupid = ? order by a.applyfordate asc";
		Object[] object = {groupId};
		return (List<SwGroupApplyEntity>) findByHql(hql, object);
	}
	
	/**
	 * 根据成员id检索群组申请信息
	 * @param groupId 群组id
	 * @param memberId 成员id
	 * @return 群组成员申请信息
	 */
	@Override
	@Transactional
	public SwGroupApplyEntity findApply(Long groupId, Long memberId){
		Session session = getCurrentSession();
		Query query = session.createQuery("from SwGroupApplyEntity a where a.memberid = :member and a.groupid = :group");
		query.setLong("member", memberId);
		query.setLong("group", groupId);
		return (SwGroupApplyEntity) query.uniqueResult();
	}
	
	/**
	 * 更改成员的禁止状态
	 * @param groupId 群组id
	 * @param memberId 成员id
	 * @param status 1为禁止状态，0为正常状态
	 */
	@Override
	@Transactional
	public void updateMemberStatus(Long groupId, Long memberId, Integer status) {
		Session session = getCurrentSession();
		Query query = session.createQuery("update SwGroupMemberEntity s set s.status = :status "
				+ "where s.groupid = :group and s.memberid = :member");
		query.setInteger("status", status);
		query.setLong("group", groupId);
		query.setLong("member", memberId);
		query.executeUpdate();
	}
	
	/**
	 *查找群组信息
	 *@param groupName 群组名
	 *@param goupType 群组类型
	 *@param enterpriseId 企业id
	 *@return 返回群组信息实体
	 */
	@Override
	@Transactional
	public SwGroupInfoEntity findGroup(String groupName, Long enterpriseId) {
		Session session = getCurrentSession();
		Query query = session.createQuery("from SwGroupInfoEntity s where s.name = :name and s.enterpriseid = :enterprise");
		query.setString("name", groupName);
		query.setLong("enterprise", enterpriseId);
		return (SwGroupInfoEntity) query.uniqueResult();
	}
	
	/**
	 * 根据群组id和类型id查找群组信息
	 * @param groupId 群组id
	 * @param typeId 群组类型id
	 * @return 群组信息
	 */
	@Override
	@Transactional
	public SwGroupInfoEntity findGroup(Long groupId, Long typeId) {
		Session session = getCurrentSession();
		Query query = session.createQuery("from SwGroupInfoEntity s where s.id = :group and s.typeid = :type");
		query.setLong("group", groupId);
		query.setLong("type", typeId);
		return (SwGroupInfoEntity) query.uniqueResult();
	}
	
	/**
	 * 通过群组名查找群组信息
	 * @param name 群组名
	 * @return 群组信息
	 */
	@Override
	@Transactional
	public SwGroupInfoEntity findGroupByName(String name) {
		Session session  = getCurrentSession();
		Query query = session.createQuery("from SwGroupInfoEntity s where s.name = :name");
		query.setString("name", name);
		return (SwGroupInfoEntity) query.uniqueResult(); 
	}
	
	/**
	 * 查找某人创建的群组
	 * @param memberId 成员id
	 * @param roleId 角色id
	 * @return 群组成员信息列表
	 */
	@Override
	@Transactional
	public List<SwGroupMemberEntity> findMemberByroleId(Long memberId, Long roleId) {
		String hql = "from SwGroupMemberEntity s where s.memberid = ? and s.roleid = ?";
		Object[] params = {memberId, roleId};
		return (List<SwGroupMemberEntity>) findByHql(hql, params);
	}
	
	/**
	 * 查找某人所属的群组id
	 * @param memberId 成员id
	 * @return 群组成员信息列表
	 */
	@Override
	@Transactional
	public List<Long> findGroupId(Long memberId) {
		String hql = "select distinct s.groupid from SwGroupMemberEntity s where s.memberid = ?";
		Object[] params = {memberId};
		return (List<Long>) findByHql(hql, params);
	}
	
	/**
	 * 修改群组状态
	 * @param groupTypeId 群组类型id
	 * @param enterpriseId 群组企业id
	 * @param status 0为正常状态，1为群组类型级关闭，2为群组级关闭
	 */
	@Override
	@Transactional
	public void modifyGroupType(Long groupTypeId, Long enterpriseId, Integer status, Timestamp time) {
		Session session = getCurrentSession();
		Query query = session.createQuery("update SwGroupInfoEntity s set s.status = :status,"
				+ "s.closedate = :close where s.typeid = :type and s.enterpriseid = :enterprise");
		query.setInteger("status", status);
		query.setTimestamp("close", time);
		query.setLong("type", groupTypeId);
		query.setLong("enterprise",enterpriseId);
		query.executeUpdate();
	}
	
	/**
	 * 更改群组状态
	 * @param groupId 群组id
	 * @param status 0为正常状态，1为群组类型级关闭，2为群组级关闭
	 */
	@Override
	@Transactional
	public void modifyGroupStatus(Long groupId, Integer status, Timestamp time) {
		Session session = getCurrentSession();
		Query query = session.createQuery("update SwGroupInfoEntity s set s.status = :status,"
				+ "s.closedate = :close where s.id = :groupId");
		query.setLong("groupId", groupId);
		query.setInteger("status", status);
		query.setTimestamp("close", time);
		query.executeUpdate();
	}
	
	/**
	 * 查找群组指定定的应用信息
	 * @param groupId 群组id
	 * @param appKey 应用key
	 * @return 群组应用信息
	 */
	@Override
	@Transactional
	public SwGroupAppEntity findApp(Long groupId, String appKey) {
		Session session = getCurrentSession();
		Query query = session.createQuery("from SwGroupAppEntity app where app.groupid = :groupid and app.appkey = :key");
		query.setLong("groupid", groupId);
		query.setString("key", appKey);
		return (SwGroupAppEntity) query.uniqueResult();
	}
	
	/**
	 * 获取群组应用的key
	 * @param groupId 群组id
	 * @return 群组应用key
	 */
	@Override
	@Transactional
	public List<String> findAppKey(Long groupId) {
		Session session = getCurrentSession();
		Query query = session.createQuery("select app.appkey from SwGroupAppEntity app "
				+ "where app.groupid = :group");
		query.setLong("group", groupId);
		return query.list();
	}
	
	/**
	 * 修改企业的应用状态
	 * @param groupId 群组id
	 * @param appKey 应用key
	 * @param status 1为禁止状态，0为正常状态
	 */
	@Override
	@Transactional
	public void modifyAppStatus(Long groupId, String appKey, Integer status) {
		Session session = getCurrentSession();
		Query query = session.createQuery("update SwGroupAppEntity app set app.status = :status where "
				+ "app.groupid = :group and app.appkey = :key");
		query.setInteger("status", status);
		query.setLong("group", groupId);
		query.setString("key", appKey);
		query.executeUpdate();
	}
	
	/**
	 * 删除群组的具体应用
	 * @param groupId 群组id
	 * @param appKey 应用key
	 */
	@Override
	@Transactional
	public void deleteApp(Long groupId, String appKey) {
		Session session = getCurrentSession();
		Query query = session.createQuery("delete from SwGroupAppEntity app where app.groupid = :group "
				+ "and app.appkey = :key");
		query.setLong("group",groupId);
		query.setString("key", appKey);
		query.executeUpdate();
	}
	
	/**
	 * 通过id获取群组信息
	 * @param id 群组id
	 * @return 群组信息实体
	 */
	@Override
	@Transactional
	public SwGroupInfoEntity findGroupById(Long id) {
		Session session = getCurrentSession();
		Query query = session.createQuery("from SwGroupInfoEntity s where s.id = :id");
		return (SwGroupInfoEntity) query.setLong("id", id).uniqueResult();
	}
	
	/**
	 * 通过id删除群组
	 * @param id 群组id
	 */
	@Override
	@Transactional
	public void deleteGroupById(Long id) {
		Session session = getCurrentSession();
		Query query = session.createQuery("delete from SwGroupInfoEntity s where s.id = :id");
		query.setLong("id", id);
		query.executeUpdate();
	}
	
	/**
	 * 查找所有群组数据信息
	 * @return 群组信息列表
	 */
	@Override
	@Transactional
	public List<SwGroupStatisticsEntity> findStatistics() {
		Session session = getCurrentSession();
		return session.createQuery("from SwGroupStatisticsEntity").list();
	}
}
