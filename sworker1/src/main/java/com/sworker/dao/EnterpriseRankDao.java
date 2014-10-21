package com.sworker.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwEnterpriseInfoEntity;
import com.sworker.entity.SwRankInfoEntity;
import com.sworker.model.EnterpriseRankParam;

/**
 * 
 * @author wanggang 2014/09/11
 *
 */
@Component("enterpriserankdao")
public class EnterpriseRankDao extends AbstractHibernateDao<SwRankInfoEntity> implements IEnterpriseRankDao {

	
	public EnterpriseRankDao(){
		super();
		 this.setClazz(SwRankInfoEntity.class);
	}

	/**
	 * 查询所有企业等级
	 * @return 企业等级实体列表
	 */
	@Override
	@Transactional
	public List<SwRankInfoEntity> findAllRank() {
			Session session=getCurrentSession();
			Query query=session.createQuery("from SwRankInfoEntity r order by r.pointlower DESC");
			return query.list();
	}


	/**
	 * 检查公司等级存在
	 * @param rank 等级
	 * @return 等级列表
	 */
	@Override
	@Transactional
	public List<SwRankInfoEntity> checkRankExist(Integer rank) {
			Session session =getCurrentSession();
			Query query=session.createQuery("from SwRankInfoEntity where rank=(:rank)");
			query.setLong("rank", rank);
			return query.list();
	}

	/**
	 * 保存企业等级实体
	 * @param rankentity 等级实体
	 * @return 保存操作结果，成功返回true，失败返回false
	 */
	@Override
	@Transactional
	public void saveRankInfo(SwRankInfoEntity rankentity) {
		create(rankentity);
	}

	
	/**
	 * 修改企业等级
	 * @param rankentity 企业等级实体
	 */
	@Override
	@Transactional
	public void updateRankInfo(SwRankInfoEntity rankentity) {
		Session session =getCurrentSession();
		//存在疑问，是否可以直接save
		session.update(rankentity);
		//session.saveOrUpdate("SwRankInfoEntity",rankentity);
		
		
	}

	/**
	 * 删除等级信息
	 * @param rank 等级
	 */
	@Override
	@Transactional
	public void deleteRankInfo(Integer rank) {
		Session session =getCurrentSession();
		String hql="delete from SwRankInfoEntity where rank=(:rank)";
		Query query=session.createQuery(hql);
		query.setParameter("rank", rank);
		query.executeUpdate();
	}

	
	/**
	 * 获取所有企业积分
	 * @return 包装后的企业积分参数 
	 */
	@Override
	@Transactional
	public List<EnterpriseRankParam> getEnterprisePoint() {
		Session session=getCurrentSession();
		String hql="select new com.sworker.model.EnterpriseRankParam(enterpiseid,totalpoint) from SwEnterpriseInfoEntity";
		Query query=session.createQuery(hql);
		return query.list();
	}

	
	/**
	 * 更新企业基本信息表企业等级 
	 * @param enterpriseid 企业ID
	 * @param level 企业等级
	 */
	@Override
	@Transactional
	public void UpdateEnterpriseRank(Long enterpriseid,Integer level) {
		Session session =getCurrentSession();
		String hql="from SwEnterpriseInfoEntity as en where en.enterpiseid =(:id)";
		Query query =session.createQuery(hql);
		query.setParameter("id", enterpriseid);
		SwEnterpriseInfoEntity enterprise=(SwEnterpriseInfoEntity)query.uniqueResult();
		enterprise.setLevel(level);
		session.update(enterprise);
	}
	
}
