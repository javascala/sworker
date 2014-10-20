package com.sworker.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwAtInfoEntity;

/**
 * 
 * @author wanggang 2014/8/22.
 *
 */
@SuppressWarnings("unchecked")
@Component("atdao")
public class AtDao extends AbstractHibernateDao<SwAtInfoEntity> implements IAtDao {
	
	public AtDao(){
		
		super();
		this.setClazz(SwAtInfoEntity.class);
		
	}
	
	/**
	 * 批量插入atObj
	 */
	@Transactional
	@Override
	public Boolean insertAll(List<SwAtInfoEntity> atObjEntityList) {
		Session session=getCurrentSession();
		Iterator<SwAtInfoEntity> it = atObjEntityList.iterator();
		
		try{
			int count=0;
			while(it.hasNext()){
				SwAtInfoEntity atinfo=(SwAtInfoEntity)it.next();
				create(atinfo);
					
				if(++count % 30==0){
					session.flush();
					session.clear();
					}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	
		return true;
	}
	
	/**
	 *通过参数删除atObj
	 */
	@Transactional
	@Override
	public void deleteAtObjByParams(Long associateId, String appTypeId) {
		Session session = getCurrentSession();
			String hql="delete SwAtInfoEntity  where associateId=? and appTypeId=?";
			Query query=session.createQuery(hql);
			query.setLong(0, associateId);
			query.setString(1, appTypeId);
			query.executeUpdate();
	}
	
	/**
	 * 通过参数查询atObj列表
	 */
	@Transactional
	@Override
	public List<SwAtInfoEntity> getAtObjsByParams(Long associateId, String appTypeId) {
		
		Session session =getCurrentSession();
		String hql="from SwAtInfoEntity  where associateId=? and appTypeId=?";
		Query query=session.createQuery(hql);
		query.setLong(0, associateId);
		query.setString(1, appTypeId);
		
		return query.list();
	}

	/**
	 * 通过id列表查询atObj
	 */
	@Transactional
	@Override
	public List<SwAtInfoEntity> getAtObjsByIdList(List<Long> atObjIdList) {
		Session session=getCurrentSession();
		Query query=session.createQuery("from SwAtInfoEntity  where id in (:ids)");
		query.setParameterList("ids", atObjIdList);
		
		return query.list();
		
	}
	
}
