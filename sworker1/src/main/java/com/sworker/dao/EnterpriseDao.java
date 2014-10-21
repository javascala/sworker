package com.sworker.dao;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwEnterpriseInfoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jiangcy on 2014/8/29.
 */
@Repository("entitypriseDao")
@SuppressWarnings("unchecked")
public class EnterpriseDao extends AbstractHibernateDao<SwEnterpriseInfoEntity>
		implements IEnterpriseDao {

	public EnterpriseDao() {
		super();
		setClazz(SwEnterpriseInfoEntity.class);
	}

	/**
	 * 通过企业的名字查找企业帐户信息
	 * @param name 企业名
	 * @return 返回企业账号实体列表
	 */
	@Override
	@Transactional
	public List<SwEnterpriseInfoEntity> findByName(String name) {
		Session session = getCurrentSession();
		Query query = session.createQuery("from SwEnterpriseInfoEntity s "
				+ "where s.enterpisename = :name");
		query.setString("name", name);
		return query.list();
	}
	
	/**
	 * 改变企业的当前状态
	 * @param enterpriseId 企业的id
	 * @param status 0为删除;1为激活;2为关闭
	 * @return 改变成功返回true,否则false
	 */
	@Override
	@Transactional
	public Boolean modifyStatus(Long enterpriseId, Integer status) {
		SwEnterpriseInfoEntity enterpriseEntity = findById(enterpriseId);
		if (enterpriseEntity != null) {
			enterpriseEntity.setStatus(status);
			update(enterpriseEntity);
			return true;
		}
		return false;
	}
	
	/**
	 * 查看企业文件储存的剩余容量
	 * @param enterpriseId 企业的id
	 * @return 企业的剩余容量
	 */
	@Override
	@Transactional
	public Long findLeftDisk(Long enterpriseId) {
		Session session = getCurrentSession();
		Query query = session.createQuery("select s.maxspacemount-s.usagespacemount from SwEnterpriseInfoEntity s where "
				+ "s.enterpiseid = :id and s.status <> :status");
		query.setLong("id", enterpriseId);
		query.setString("status", "0");
		return (Long)query.uniqueResult();		 
	}
	
	/**
	 * 更改企业已用空间量
	 * @param enterpriseId 企业的id
	 * @param fileSize 需改变的大小
	 * @return 操作成功返回true
	 */
	@Override
	@Transactional
	public Boolean midifyUsagespace(Long enterpriseId, Integer fileSize) {
		SwEnterpriseInfoEntity enterpriseEntity = findById(enterpriseId);
		enterpriseEntity.setUsagespacemount(enterpriseEntity.getUsagespacemount() + fileSize);
		update(enterpriseEntity);
		return true;
	}

// wangying on 2014/9/19.


    /**
     * 检索企业的等级
     * @param level 等级
     * @return 企业Id
     */
    @Override
    @Transactional
    public List<Long> searchEnterpriseLevel(Integer level) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select s.enterpiseid from SwEnterpriseInfoEntity s " +
                "where s.level in(lev)order by enterpiseid");
        query.setInteger("lev",level);
        return query.list();
    }

    /**
     * 检索企业的等级
     * @param enterpriseId 企业Id
     * @return 等级
     */
    @Override
    @Transactional
    public Integer searchEnterpriseLevel(Long enterpriseId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select s.level from SwEnterpriseInfoEntity s " +
                "where s.enterpiseid = :eid ");
        query.setLong("eid",enterpriseId);
        return ((Long)query.uniqueResult()).intValue();
    }
}