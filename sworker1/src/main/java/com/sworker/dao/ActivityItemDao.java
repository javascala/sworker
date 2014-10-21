package com.sworker.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwActivityItemEntity;

/**
 * Created by jiangcy on 2014/8/21.
 */
@Repository
@Component(value = "itemDao")
public class ActivityItemDao extends AbstractHibernateDao<SwActivityItemEntity> implements IActivityItemDao {
	
	public ActivityItemDao() {
		super();
		setClazz(SwActivityItemEntity.class);
	}

	/**
	 * 通过id删除动态项目
	 * @param key 动态项目的id
	 */
	@Override
	@Transactional
	public void deleteByKey(String key){
		Session session = getCurrentSession();
		session.delete(findByKey(key));
	}
	
	/**
	 * 通过动态项目id查找动态项目
	 * @param key 动态项目的id
	 * @return 返回动态项目实体
	 */
	@Override
	@Transactional
	public SwActivityItemEntity findByKey(String key){
		Session session = getCurrentSession();
		Query query = session.createQuery("from SwActivityItemEntity s where s.activityitemkey = :k");
		query.setString("k", key);
		return (SwActivityItemEntity)query.uniqueResult();
	}
	
}
