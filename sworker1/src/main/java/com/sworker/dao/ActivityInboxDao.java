package com.sworker.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwActivityInboxEntity;

/**
 * Created by jiangcy on 2014/8/21.
 */
@Repository
@Component(value = "inboxDao")
public class ActivityInboxDao extends AbstractHibernateDao<SwActivityInboxEntity> implements IActivityInboxDao {
	
	public ActivityInboxDao() {
		super();
		setClazz(SwActivityInboxEntity.class);
	}

}
