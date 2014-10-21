package com.sworker.dao;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwUserAppEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangying on 2014/9/18.
 */
@Repository
@Component(value = "userAppDao")
public class UserAppDao extends AbstractHibernateDao<SwUserAppEntity> implements IUserAppDao{
    public UserAppDao() {
        super();
        this.setClazz(SwUserAppEntity.class);
    }

    /**
     * 获取应用被安装数量
     * @param appKey 应用Key
     * @return 安装数量
     */
    @Override
    @Transactional
    public Integer getAppInstallNum(String appKey) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select count(r.appkey)from SwEnterpriseAppEntity s ,SwUserAppEntity r " +
                "where r.appkey = :akey and r.enterpriseid = s.enterpriseid and s.istrial = :trial");
        query.setString("akey",appKey);
        query.setInteger("trial",0);
        return ((Long)query.uniqueResult()).intValue();
    }

    /**
     * 获取应用被安装数量
     * @param appId 应用Id
     * @return 安装数量
     */
    @Override
    @Transactional
    public Integer getAppInstallNum(Long appId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select count(r.appkey)from SwEnterpriseAppEntity s ,SwUserAppEntity r " +
                "where r.appid = :aid and r.enterpriseid = s.enterpriseid and s.istrial = :trial");
        query.setLong("aid",appId);
        query.setInteger("trial",0);
        return ((Long)query.uniqueResult()).intValue();
    }

    /**
     * 检索某用户的应用
     * @param enterpriseId 企业id
     * @param userId 用户Id
     * @return 个人应用列表
     */
    @Override
    @Transactional
    public List<SwUserAppEntity> searchUseApp(Long enterpriseId, Long userId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from SwUserAppEntity s " +
                "where s.enterpriseid in(:eid) and s.userid in(:uid) order by appkey,appid");
        query.setLong("eid",enterpriseId);
        query.setLong("uid", userId);
        return query.list();
    }

    /**
     * 插入用户实体
     * @param appEntity 用户实体
     * @return 用户实体
     */
    @Override
    @Transactional
    public void insertEntity(SwUserAppEntity appEntity) {
        create(appEntity);
    }

    /**
     * 更新某企业的某应用版本
     * @param enterpriseId 企业id
     * @param appIdOld 旧的应用Id
     * @param appIdNew 新的应用Id
     */
    @Override
    @Transactional
    public void updateEnterpriseAppVersion(Long enterpriseId, Long appIdOld,Long appIdNew) {
        Session session = getCurrentSession();
        Query query = session.createQuery("update  SwUserAppEntity s set s.appid = :aid " +
                " where s.enterpriseid = :eid and s.appid = :oid ");
        query.setLong("eid", enterpriseId);
        query.setLong("aid", appIdNew);
        query.setLong("oid", appIdOld);
        query.executeUpdate();
    }

    /**
     * 删除所有用户与某应用的关系
     * @param appId 应用Id
     */
    @Override
    @Transactional
    public void deleteEnterpriseAppRelative(Long appId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("delete from SwUserAppEntity s where s.appid =:aid");
        query.setLong("aid",appId);
        query.executeUpdate();
    }

    /**
     * 删除所有用户与某应用的关系
     * @param enterpriseId 企业id
     * @param appId 应用Id
     */
    @Override
    @Transactional
    public void deleteEnterpriseAppRelative(Long enterpriseId, Long appId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("delete from SwUserAppEntity s where s.enterpriseid " +
                "= :eid and s.appid = :aid ");
        query.setLong("eid", enterpriseId);
        query.setLong("aid",appId);
        query.executeUpdate();
    }

    /**
     * 删除所有用户与某应用的关系
     * @param enterpriseId 企业id
     * @param userId 用户Id
     * @param appId 应用Id
     */
    @Override
    @Transactional
    public void deleteEnterpriseAppRelative(Long enterpriseId, Long userId, Long appId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("delete from SwUserAppEntity s where s.enterpriseid " +
                "= :eid and s.appid = :aid and s.userid =:uid");
        query.setLong("eid", enterpriseId);
        query.setLong("aid",appId);
        query.setLong("uid",userId);
        query.executeUpdate();
    }
}
