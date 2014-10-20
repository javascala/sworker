package com.sworker.dao;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwApplicationEntity;
import com.sworker.model.TempEnterpriseAppEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangying on 2014/9/17.
 */
@Repository
@Component(value = "applicationDao")
public class ApplicationDao extends AbstractHibernateDao<SwApplicationEntity> implements IApplicationDao{
    public ApplicationDao() {
        super();
        this.setClazz(SwApplicationEntity.class);
    }

    /**
     * 对应用表进行全检索
     * @return 应用实体列表
     */
    @Override
    @Transactional
    public List<SwApplicationEntity> searchAllApps() {
        Session session = getCurrentSession();
        Query query = session.createQuery("from SwApplicationEntity order by status,appscope," +
                "apptype,chargetype,version desc");
        return query.list();
    }

    /**
     * 根据应用Key获取某种应用基本信息
     * @param appKey 应用Key
     * @return 应用实体列表
     */
    @Override
    @Transactional
    public List<SwApplicationEntity> searchAppByKey(String appKey) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from SwApplicationEntity s where s.appkey in(:akey) order by " +
                "version desc");
        query.setString("akey", appKey);
        return query.list();
    }

    /**
     * 根据收费类型获取应用信息
     * @param appId id
     * @return 应用实体列表
     */
    @Override
    @Transactional
    public List<SwApplicationEntity> searchAppById(long appId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from SwApplicationEntity s where s.id = :id");
        query.setLong("id", appId);
        return query.list();
    }

    /**
     * 根据收费类型获取应用信息
     * @param chargeType 收费类型
     * @param status 状态
     * @return 应用实体列表
     */
    @Override
    @Transactional
    public List<SwApplicationEntity> searchAppByChargeType(Integer chargeType, Integer status) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from SwApplicationEntity s where s.chargetype = :ctype and s.status = :sta");
        query.setInteger("ctype", chargeType);
        query.setInteger("sta",status);
        return query.list();
    }

    /**
     * 根据应用名称获取某种应用基本信息
     * @param appName 应用Name
     * @return 应用实体列表
     */
    @Override
    @Transactional
    public List<SwApplicationEntity> getAppInfoByName(String appName) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from SwApplicationEntity s where s.appname in(:aname)" +
                "order by version desc ");
        query.setString("aname", appName);
        return query.list();
    }

    @Override
    @Transactional
    public List<TempEnterpriseAppEntity> getOrder(Long enterpriseId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from TempEnterpriseAppEntity s where s.enterpriseId in(:eid)" +
                "order by appscope,apptype,appkey ");
        query.setLong("eid",enterpriseId);
        return query.list();
    }
}
