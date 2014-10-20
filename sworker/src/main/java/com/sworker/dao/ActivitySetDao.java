package com.sworker.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwActivitySetEntity;

/**
 * Created by jiangcy on 2014/8/21.
 */
@Repository
@Component(value = "setDao")
public class ActivitySetDao extends AbstractHibernateDao<SwActivitySetEntity> implements IActivitySetDao {
	
	public ActivitySetDao() {
		super();
		setClazz(SwActivitySetEntity.class);
	}

	/**
	 * 通过userid和项目id查找用户设置
	 * @param: userId 用户id 
	 * @param activityItemKey 动态项目的id
	 * @return 返回是否接收动态
	 */
	@Override
	@Transactional
	public Integer findSetting(Long userId, String activityItemKey){
		Session session = getCurrentSession();
		Query query = session.createQuery("select s.isreceived from SwActivitySetEntity s"
				+ "where s.userid = :u and s.itemkey = :k");
		query.setLong("u", userId);
		query.setString("k", activityItemKey);
		return (Integer)query.uniqueResult();
	}
	
	/**
	 * 根据用户id删除设置
	 * @param userId 用户id
	 */
	@Override
	@Transactional
	public void deleteByUserId(Long userId){
		Session session = getCurrentSession();
		Query query = session.createQuery("delete from SwActivitySetEntity s where s.userid = :u");
		query.setLong("u", userId);
		query.executeUpdate();
	}
}
