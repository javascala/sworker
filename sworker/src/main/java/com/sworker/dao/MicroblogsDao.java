package com.sworker.dao;

import java.util.List;

import com.sworker.entity.SwMicroblogsEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.dao.common.AbstractHibernateDao;

/**
 * Created by jiangcy on 2014/8/21.
 */
@Repository("microblogsDao")
@SuppressWarnings("unchecked")
public class MicroblogsDao extends AbstractHibernateDao<SwMicroblogsEntity> implements IMicroblogsDao {
	
	public MicroblogsDao() {
		super();
		setClazz(SwMicroblogsEntity.class);
	}
	
	/**
	 * 更改微博转发个数
	 * @param id 微博的id
	 * @param count 需要更改数
	 */
	@Transactional
	@Override
	public void updateForwardCount(Long id, Long count) {
		SwMicroblogsEntity microblogsEntity = findById(id);
		Long newCount = microblogsEntity.getForwardedcount() + count;
		microblogsEntity.setForwardedcount(newCount);
		update(microblogsEntity);
	}
	
	/**
	 * 通过用户id查找微博
	 * @param userIdList 用户的id
	 * @return 返回微博列表
	 */
	@Transactional
	@Override
	public List<SwMicroblogsEntity> findByList(List<Long> user){
			Session session = getCurrentSession();
			Query query = session.createQuery("from SwMicroblogsEntity s where s.userid in(:list) order by datecreated desc");
			query.setParameterList("list", user);
			return query.list();
	}
	
}
