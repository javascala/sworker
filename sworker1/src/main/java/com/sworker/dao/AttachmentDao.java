package com.sworker.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwAttachmentEntity;

/**
 * 
 * @author wanggang 2014/8/28.
 *
 */
@Component("attachmentdao")
public class AttachmentDao extends AbstractHibernateDao<SwAttachmentEntity> implements IAttachmentDao {

	/**
	 * 将附件实体保存进数据库
	 * @param att 附件实体
	 */
	@Override
	@Transactional
	public void saveAttachment(SwAttachmentEntity att) {
		 Session session=getCurrentSession();
		
		 session.persist(att);
		
	}

	/**
	 * 根据附件ID删除附件
	 * @param id 附件ID
	 */
	@Override
	@Transactional
	public void deleteAttachmentById(Long id) {
		Session session =getCurrentSession();
		
		String hql="delete from SwAttachmentEntity where id=(:id)";
		
		Query query=session.createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
		
	}
	
	/**
	 * 根据业务ID删除附件
	 * @param businessid 业务ID
	 */
	@Override
	@Transactional
	public void deleteAttachmentByBizID(Long businessid) {
		Session session =getCurrentSession();
		
		String hql="delete from SwAttachmentEntity as att where att.businessid=(:id)";
		Query query =session.createQuery(hql);
		query.setParameter("id", businessid);
		query.executeUpdate();
	}

	/**
	 *通过userid 连接查询得到附件列表
	 * @param userid 用户ID
	 * @return 附件列表
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<SwAttachmentEntity> findAttachmentsByUserId(Long userid) {
		Session session = getCurrentSession();
		
		String hql="select att.id from SwAttachmentEntity as att inner join att.swFileinfoEntity as file where file.creator=(:id)";
		Query query =session.createQuery(hql);
		query.setParameter("id", userid);
		
		List<Integer> ids=query.list();
		
		String hql2="from SwAttachmentEntity where id in (:ids)";
		
		Query query2=session.createQuery(hql2);
		query2.setParameterList("ids", ids);
		
		return query2.list();
	}

	
	/**
	 * 通过businessid 连接查询得到附件列表
	 * @param businessid 业务ID
	 * @return 附件列表
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<SwAttachmentEntity> findAttachmentsByBizID(Long businessid) {
			
			Session session =getCurrentSession();
			
			//String hql="select id from SwAttachmentEntity a inner join SwFileinfoEntity f on a.fileid=f.fileid where a.businessid=(:id)";
		
			//String hql="select a.id from SwAttachmentEntity as a ,com.sworker.entity.SwFileinfoEntity as f where a.fileid=f.fileid and a.businessid=(:id)";
			String hql="select att.id from SwAttachmentEntity as att inner join att.swFileinfoEntity as file where att.businessid=(:id)";
			Query query=session.createQuery(hql);
			
			query.setParameter("id", businessid);
		
			List<Integer> ids=query.list();
			String hql2="from SwAttachmentEntity where id in (:ids)";
			Query query2=session.createQuery(hql2);
			query2.setParameterList("ids", ids);
			return query2.list();
	}

	
	/**
	 * 根据附件ID获取附件的详细信息
	 * @param id 附件ID
	 * @return 附件实体
	 */
	@Override
	@Transactional
	public SwAttachmentEntity getAttachment(Long id) {
		Session session=getCurrentSession();
		//TODO
		
		String hql="select att.id from SwAttachmentEntity as att inner join att.swFileinfoEntity as file where att.id=(:id) ";
		Query query =session.createQuery(hql);
		query.setParameter("id", id);
		return (SwAttachmentEntity)query.uniqueResult();
	}


	
	
	
	
	
	
	
}
