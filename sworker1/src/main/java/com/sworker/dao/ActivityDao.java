package com.sworker.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwActivityEntity;

/**
 * Created by jiangcy on 2014/8/21.
 */
@Repository
@SuppressWarnings("unchecked")
public class ActivityDao extends AbstractHibernateDao<SwActivityEntity> implements IActivtityDao {

	public ActivityDao() {
		super();
		setClazz(SwActivityEntity.class);
	}
	
	/**
	 * 更行动态的私有属性
	 * @param lastTime 最后更新时间
	 * @param isPrivate 是否为私有
	 */
	@Override
	@Transactional
	public void updatePrivate(Long userId, Integer isPrivate) {
		Session session = getCurrentSession();
		Query query = session.createQuery("update SwActivityEntity s set"
				+ "s.isprivate = :n where s.userid = :u");
		query.setLong("n", userId);
		query.setInteger("u", isPrivate);
	}
	
	/**
	 * 查找制定用户的更新数量
	 * @param activity 更新实体 
	 * @param userId 用户的id
	 * @return 返回更新的数量
	 */
	@Override
	@Transactional
	public Integer findCount(SwActivityEntity activity, Long userId) {
		Session session = getCurrentSession();
		Query query = session.createQuery("select count(s.activityid) from SwActivityEntity s and SwActivityInboxEntity i"
				+ "where s.lastmodified > :n and i.userid = :u and s.activityid = i.activityid order by lastmodified dec");
		query.setDate("n", activity.getLastmodified());
		query.setLong("u", userId);
		return (Integer)query.uniqueResult();
	}
	

	/**
	 * 查找用户动态
	 * @param lastTime 最后更新时间
	 * @param userId 用户id
	 * @return 返回动态实体列表
	 */
	@Transactional
	@Override
	public List<SwActivityEntity> findActivities(Timestamp lastTime, Long userId) {
		Session session = getCurrentSession();
		Query query = session.createQuery("select s from SwActivityEntity s and SwActivityInboxEntity i"
				+ "where s.lastmodified > :n and i.userid = :u and s.activityid = i.activityid order by lastmodified dec");
		query.setDate("n", lastTime);
		query.setLong("u", userId);
		return query.list();
	}
	
	/**
	 * 查找动态
	 * @param userId 用户id
	 * @param followGroupType 关注类型，-2相互关注，-3悄悄关注
	 * @param appKey 应用主键
	 * @param mediaType 媒体类型
	 * @param isOriginal 是否原创
	 * @param pageIndex 当前页
	 * @param pageSize 每页数据个数
	 * @return 动态实体列表
	 */
	@Override
	@Transactional
	public List<SwActivityEntity> findActivities(Long userId, Integer followGroupType, String appKey,
			Integer mediaType, Integer isOriginal, Integer pageIndex, Integer pageSize) {
		String type = null;
			
		if (mediaType == 4) {
			type = "hasmusic";
		}
		if (mediaType == 1) {
			type = "hasimage";
		}
		if (mediaType == 2) {
			type = "hasvideo";
		}
		
		List<Long> userIdList = null;
		if (followGroupType == -2) {
			userIdList = getUserId("ismutual", userId);
		} 
		if(followGroupType == -3) {
			userIdList = getUserId("isquietly", userId);
		}
		
		String hql = "select a from SwActivityEntity a inner join SwActivityInBoxEntity b "
				+ "on a.activityid = b.activityid where b.userid = ? and a.userid = ? and "
				+ "a.appkey = ? and a."+type+" = 1 and a.isoriginalthread = ? order by a.lastmodified desc";
	
		Object[] params = {userId, userIdList, appKey, isOriginal};
		return findByPage(hql, params, pageIndex, pageSize);
	}
	
	/**
	 * 查找关注群组用户id列表
	 * @param type 关注类型
	 * @param userId 用户id
	 * @return 用户id列表
	 */
	public List<Long> getUserId(String type, Long userId) {
		Session session = getCurrentSession();
		Query query = session.createQuery("select userid from SwFollowUserEntity f where "
				+ "f.isquietly = :quiet and f."+type+" = 1");
		query.setLong("user", userId);
		return query.list();
		
	}
}
