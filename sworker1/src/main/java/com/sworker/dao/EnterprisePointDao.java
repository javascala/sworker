package com.sworker.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.entity.SwEnterpriseInfoEntity;
import com.sworker.entity.SwExpiredpointEntity;
import com.sworker.entity.SwPointRecordsEntity;
import com.sworker.entity.SwPointRuleEntity;
import com.sworker.model.EnterprisePointParam;

/**
 * 
 * @author wanggang 2014/09/16
 *
 */
@Component("pointdao")
public class EnterprisePointDao  implements IEnterprisePointDao {

	
	 @Autowired
	 private SessionFactory sessionFactory;
	 
	 /**
	  * 查询积分列表
	  * @param level 企业等级
	  * @param enterprisename 企业名称
	  * @param pageIndex 起始记录
	  * @param pageSize 分页大小
	  * @return 积分列表
	  */
	@Override
	@Transactional
	public List<EnterprisePointParam> searchPoint(Integer level,
			String enterprisename, Integer pageIndex, Integer pageSize) {
		Session session=sessionFactory.getCurrentSession();
		String hql="select new com.sworker.model.EnterprisePointParam(a.enterpiseid,a.point,a.level,a.growthvalue,a.totalpoint,b.expiredpoint,b.enddate) "
				+"from SwEnterpriseInfoEntity as a left join a.expiredpoint as b "
				+ "where b.enddate=(select min(enddate) from SwExpiredpointEntity) and a.level=(:level) and a.enterpisename like (:enterprisename) ";
		Query query =session.createQuery(hql);
			query.setParameter("level", level);
			query.setParameter("enterprisename", enterprisename);
			query.setFirstResult((pageIndex-1)*pageSize);
			query.setMaxResults(pageSize);
			return	query.list();
	}

	/**
	 * 保存积分统计
	 * @param record 积分统计
	 */
	@Override
	@Transactional
	public Boolean savePointStatistics(SwPointRecordsEntity record) {
		try{
			Session session=sessionFactory.getCurrentSession();
			session.save(record);
			return true;
		}catch(Exception ex){
				ex.printStackTrace();
				return false;
			}
	}

	/**
	 * 保存到期积分实体
	 * @param expiredpoint 到期积分实体
	 * @return 保存结果标志
	 */
	@Override
	@Transactional
	public Boolean saveEndDatePoints(SwExpiredpointEntity expiredpoint) {
		try{
			Session session=sessionFactory.getCurrentSession();
			session.save(expiredpoint);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	
	/**
	 * 修改到期积分信息
	 * @param expiredpoint 到期积分实体
	 * @return 修改标志
	 */
	@Override
	@Transactional
	public Boolean updateEndDatePoints(SwExpiredpointEntity expiredpoint) {
		try{
			Session session=sessionFactory.getCurrentSession();
			//前台传递过来的SwExpiredpointEntity应该包括id
			String hql="from SwExpiredpointEntity where id=(:id)";
			Query query=session.createQuery(hql);
			query.setParameter("id", expiredpoint.getId());
			SwExpiredpointEntity ex=(SwExpiredpointEntity)query.uniqueResult();
				ex.setExpiredpoint(ex.getExpiredpoint()+expiredpoint.getExpiredpoint());
			session.update(ex);
			
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	

	/**
	 * 查询到期积分实体
	 * @param enterpriseid 企业ID
	 * @param enddate 到期时间
	 * @return 到期积分实体的ID
	 */
	@Override
	@Transactional
	public Long checkEndDateExist(Long enterpriseid,
			Date enddate) {
		Session session=sessionFactory.getCurrentSession();
		String hql="select ex.id from SwExpiredpointEntity as ex where ex.enterpriseid=(:id) and ex.enddate=(:enddate)";
		Query query=session.createQuery(hql);
		query.setParameter("id", enterpriseid);
		query.setParameter("enddate", enddate);
		return (Long)query.uniqueResult();
	}

	
	/**
	 * 修改企业积分累计积分值，积分值
	 * @param totalpoint 累计积分值
	 * @param point 积分值
	 * @return 修改结果标志
	 */
	@Override
	@Transactional
	public Boolean modifyEnterprisePoint(Long enterpriseid, Long point) {
		try{
			Session session =sessionFactory.getCurrentSession();
			Query query=session.createQuery("from SwEnterpriseInfoEntity "
					+ "where enterpiseid =(:id)");
				query.setParameter("id", enterpriseid);
			SwEnterpriseInfoEntity enterprise=(SwEnterpriseInfoEntity)query.uniqueResult();
			enterprise.setTotalpoint(enterprise.getTotalpoint()+point);
			enterprise.setPoint(enterprise.getPoint()+point);
			session.update(enterprise);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	
	
	/**
	 * 修改企业积分值
	 * @param point 积分值
	 * @return 修改标志
	 */
	@Override
	@Transactional
	public Boolean modifyEnterprisePointx(Long enterpriseid, Long point) {
			try{
				Session session=sessionFactory.getCurrentSession();
				Query query=session.createQuery("from SwEnterpriseInfoEntity where enterpiseid=(:id)");
				query.setParameter("id", enterpriseid);
				SwEnterpriseInfoEntity enterprise=(SwEnterpriseInfoEntity)query.uniqueResult();
				enterprise.setPoint(enterprise.getPoint()+point);
				session.update(enterprise);
				return true;
			}catch(Exception ex){
				ex.printStackTrace();
				return false;
			}
	}

	/**
	 * 查询到期积分
	 * @param enterpriseid 企业ID
	 * @return 到期积分值
	 */
	@Override
	@Transactional
	public Long searchEndDatePoint(Long enterpriseid) {
		Session session=sessionFactory.getCurrentSession();
		String hql="select min(enddate) from SwExpiredpointEntity where enterpriseid=(:id)";
		Query query=session.createQuery(hql);
			query.setParameter("id", enterpriseid);
			Date enddate=(Date)query.uniqueResult();
		String hql2="select ex.expiredpoint from SwExpiredpointEntity as ex where ex.enddate=(:date) and ex.enterpriseid=(:id)";
			Query query2=session.createQuery(hql2);
			query2.setParameter("date", enddate);
			query2.setParameter("id", enterpriseid);
		return (Long)query2.uniqueResult();
	}

	
	/**
	 * 修改到期积分
	 * @param enterpriseid 企业ID
	 * @param point 积分值
	 */
	@Override
	@Transactional
	public void modifyExpiredPoint(Long enterpriseid, Long point) {
		Session session =sessionFactory.getCurrentSession();
		String hql="select min(enddate) from SwExpiredpointEntity where enterpriseid=(:id)";
		Query query =session.createQuery(hql);
		query.setParameter("id", enterpriseid);
		Date enddate=(Date)query.uniqueResult();
		String hql2="from SwExpiredpointEntity as ex where ex.enddate=(:date) and ex.enterpriseid=(:id)";
		Query query2=session.createQuery(hql2);
		query2.setParameter("date", enddate);
		query2.setParameter("id", enterpriseid);
		
		SwExpiredpointEntity expired=(SwExpiredpointEntity)query2.uniqueResult();
		expired.setExpiredpoint(expired.getExpiredpoint()-point);
		session.update(expired);
	}

	
	
	/**
	 * 删除到期积分
	 * @param enterpriseid 企业ID
	 */
	@Override
	@Transactional
	public void deleteExpiredPoint(Long enterpriseid) {
		Session session=sessionFactory.getCurrentSession();
		String hql="select min(enddate) from SwExpiredpointEntity where enterpriseid=(:id)";
		Query query =session.createQuery(hql);
		query.setParameter("id", enterpriseid);
		Date enddate=(Date)query.uniqueResult();
		String hql2="delete from SwExpiredpointEntity as ex where ex.enddate=(:date) and ex.enterpriseid=(:id)";
		Query query2=session.createQuery(hql2);
		query2.setParameter("date", enddate);
		query2.setParameter("id", enterpriseid);
		query2.executeUpdate();
	}

	
	/**
	 * 删除到期积分 方法重载
	 * @param enterpriseid 企业ID
	 * @param enddate 到期日期
	 */
	@Override
	@Transactional
	public Boolean deleteExpiredPoint(Long enterpriseid, Date enddate) {
		try{
			Session session =sessionFactory.getCurrentSession();
			String hql="delete from SwExpiredpointEntity as ex where ex.enddate=(:date) and ex.enterpriseid=(:id)";
			Query query=session.createQuery(hql);
			query.setParameter("date", enddate);
			query.setParameter("id", enterpriseid);
			query.executeUpdate();
			
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			
			return false;
		}
	}

	

	/**
	 * 查询企业的到期日期 SQL 13
	 * @param enterpriseid 企业ID
	 * @return 到期积分实体，封装date和point 
	 */
	@Override
	@Transactional
	public SwExpiredpointEntity searchEndDate(Long enterpriseid) {
		Session session=sessionFactory.getCurrentSession();
		String hql="select min(enddate) from SwExpiredpointEntity where enterpriseid=(:id)";
		Query query=session.createQuery(hql);
			query.setParameter("id", enterpriseid);
			Date enddate=(Date)query.uniqueResult();
			String hql2="from SwExpiredpointEntity as ex where ex.enddate=(:date) and ex.enterpriseid=(:id)";
			Query query2=session.createQuery(hql2);
			query2.setParameter("date", enddate);
			query2.setParameter("id", enterpriseid);
			return (SwExpiredpointEntity)query2.uniqueResult();
	}

	
	
	/**
	 * 修改到期积分
	 * @param enterpriseid 企业ID
	 * @param date 新的到期日期
	 * @param ExPoint 参数.积分值
	 */
	@Override
	@Transactional
	public void updateExpiredPoint(Long enterpriseid,Date date,Long ExPoint) {
		Session session =sessionFactory.getCurrentSession();
		//先查出整个对象，修改后再update
		String hql="from SwExpiredpointEntity where ex.enterpriseid=(:id) and ex.enddate=(:date)";
		Query query=session.createQuery(hql);
		SwExpiredpointEntity expired=(SwExpiredpointEntity)query.uniqueResult();
		expired.setExpiredpoint(expired.getExpiredpoint()+ExPoint);
		session.update(expired);
		
	}

	
	/**
	 * 查询企业基本信息积分值  SQL19
	 * @param enterpriseid 企业ID
	 * @return  积分值
	 */
	@Override
	@Transactional
	public Long searchEnterprisePoint(Long enterpriseid) {
		Session session=sessionFactory.getCurrentSession();
		String hql="select ep.point from SwEnterpriseInfoEntity as ep where ep.id=(:id)";
		Query query=session.createQuery(hql);
		query.setParameter("id", enterpriseid);
		
		return (Long)query.uniqueResult();
		
	}


	
	/**
	 * 删除所有的积分规则
	 * @return 是否成功
	 */
	@Override
	@Transactional
	public Boolean deleteAllPointRules() {
		Session session=sessionFactory.getCurrentSession();
		try{
			//删除所有规则
			Query query=session.createQuery("delete from SwPointRuleEntity");
			query.executeUpdate();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * 保存积分规则实体
	 * @param rule 积分规则实体
	 */
	@Override
	@Transactional
	public void savePointRule(SwPointRuleEntity rule) {
		Session session =sessionFactory.getCurrentSession();
		session.save(rule);
	}

	
	
	/**
	 * 查询积分规则实体
	 * @param type 规则类型
	 * @return 积分规则实体列表
	 */
	@Override
	@Transactional
	public List<SwPointRuleEntity> getPointRuleByType(Integer type) {
		Session session =sessionFactory.getCurrentSession();
		String hql="from SwPointRuleEntity as rule where rule.type=(:type)";
		Query query=session.createQuery(hql);
		query.setParameter("type", type);
		return  query.list();
	}

	
	/**
	 * 根据类型等级标准值返回积分值
	 * @param rank 等级
	 * @param type 类型
	 * @param standard 标准值（消费的金钱数）
	 * @return 积分值
	 */
	@Override
	@Transactional
	public Long getPointByRankRule(Integer rank, Integer type, Long standard) {
	
		Session session =sessionFactory.getCurrentSession();
		String hql="select rule.point from SwPointRuleEntity as rule where rule.type=(:type) and rule.rank=(:rank) and rule.standard<=(:standard)";
		Query query=session.createQuery(hql);
		query.setParameter("type", type);
		query.setParameter("rank", rank);
		query.setParameter("standard", standard);
		return (Long)query.uniqueResult();
	}


	/**
	 * 查询企业积分统计
	 * @param enterpriseid 企业ID
	 * @param time 年份
	 * @param type 统计类型
	 * @return 积分统计实体列表
	 */
	@Override
	@Transactional
	public List<SwPointRecordsEntity> searchPointRecord(Long enterpriseid,
			Timestamp begindate,Timestamp enddate, Integer type) {
		Session session=sessionFactory.getCurrentSession();
		String hql="from SwPointRecordsEntity as pr where pr.enterpriseid=(:id) "
				+ "and pr.time>=(:begindate) and pr.time<(:enddate) and pr.type=(:type) order by pr.time";
		Query query=session.createQuery(hql);
		query.setParameter("id", enterpriseid);
		query.setParameter("begindate", begindate);
		query.setParameter("enddate", enddate);
		query.setParameter("type", type);
		return query.list();
		
	}

	
	/**
	 * 查询企业积分信息实体
	 * @param enterpriseid 企业ID
	 * @return 积分信息实体 封装字段形成的实体
	 */
	@Override
	@Transactional
	public EnterprisePointParam searchPointInfoById(Long enterpriseid) {
		Session session=sessionFactory.getCurrentSession();
		String hql="select new com.sworker.model.EnterprisePointParam(a.enterpiseid,a.point,a.level,a.growthvalue,a.totalpoint,b.expiredpoint,b.enddate) "
				+"from SwEnterpriseInfoEntity as a left join a.expiredpoint as b "
				+ "where b.enddate=(select min(enddate) from SwExpiredpointEntity) and a.enterpiseid=(:id) ";
		Query query =session.createQuery(hql);
		query.setParameter("id", enterpriseid);
		return (EnterprisePointParam)query.uniqueResult();
	}
	
	
	
	
	
}
