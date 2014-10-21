package com.sworker.dao;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwEnterpriseAppEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * Created by wangying on 2014/9/17.
 */
@Repository
@Component(value = "enterpriseDao")
public class EnterpriseAppDao extends AbstractHibernateDao<SwEnterpriseAppEntity> implements IEnterpriseAppDao{
    public EnterpriseAppDao() {
        super();
        setClazz(SwEnterpriseAppEntity.class);
    }

    /**
     * 获取应用被安装数量
     * @param appKey 应用Key
     * @param installStatus 安装状态
     * @param isTrial 是否试用
     * @return 数量
     */
    @Override
    @Transactional
    public Integer getInstallationsNumByKey(String appKey, Integer installStatus, Integer isTrial) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select count(s.id)from SwEnterpriseAppEntity s where s.appkey = :akey" +
                " and s.installstatus = :istatus and s.istrial = :trial");
        query.setString("akey",appKey);
        query.setInteger("istatus", installStatus);
        query.setInteger("trial",isTrial);
        return ((Long)query.uniqueResult()).intValue();
    }

    /**
     * 获取应用被安装数量
     * @param appId 应用Id
     * @param installStatus 安装状态
     * @param isTrial 是否试用
     * @return 数量
     */
    @Override
    @Transactional
    public Integer getInstallationsNumById(Long appId, Integer installStatus, Integer isTrial) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select count(s.enterpriseid)from SwEnterpriseAppEntity s where s.appid = :aid" +
                " and s.installstatus = :istatus and s.istrial = :trial");
        query.setLong("aid", appId);
        query.setInteger("istatus", installStatus);
        query.setInteger("trial",isTrial);
        return ((Long)query.uniqueResult()).intValue();
    }

    /**
     * 检索某企业内的应用
     * @param enterpriseId 企业Id
     * @return 企业应用列表
     */
    @Override
    @Transactional
    public List<SwEnterpriseAppEntity> searchEnterpriseApp(Long enterpriseId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from SwEnterpriseAppEntity s where s.enterpriseid in(:eid) order by " +
                "appkey,appid");
        query.setLong("eid", enterpriseId);
        return query.list();
    }

    /**
     * 检索企业内某种应用
     * @param enterpriseId 企业Id
     * @param appKey 应用Key
     * @param installStatus 安装状态
     * @return appId列表
     */
    @Override
    @Transactional
    public List<Long> searchEnterpriseAppById(Long enterpriseId,String appKey,Integer installStatus) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select appid from SwEnterpriseAppEntity s where s.enterpriseid " +
                "= :eid and s.appkey = :akey and s.installstatus = :istatus");
        query.setLong("eid", enterpriseId);
        query.setString("akey",appKey);
        query.setInteger("istatus", installStatus);
        return query.list();
    }

    /**
     * 检索企业内某应用的状况
     * @param enterpriseId 企业Id
     * @param appId 企业Id
     * @return 临时企业应用实体列表
     */
    @Override
    @Transactional
    public List<SwEnterpriseAppEntity> searchEnterpriseAppStatus(Long enterpriseId, Long appId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from SwEnterpriseAppEntity s where s.enterpriseid = :eid " +
                "and s.appid = :aid");
        query.setLong("eid", enterpriseId);
        query.setLong("aid", appId);
        return query.list();
    }

    /**
     * 删除所有企业与某应用的关系
     * @param appId 企业Id
     */
    @Override
    @Transactional
    public void deleteEnterprise(Long appId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("delete from SwEnterpriseAppEntity s where s.appid = :aid ");
        query.setLong("aid",appId);
        query.executeUpdate();
    }

    /**
     * 企业新增可使用的应用
     * @param enterpriseAppEntity 企业应用实体
     */
    @Override
    @Transactional
    public void insertEnterprise(SwEnterpriseAppEntity enterpriseAppEntity) {
        create(enterpriseAppEntity);
    }

    /**
     * 更新企业应用表
     * @param enterpriseId 企业Id
     * @param appId 企业Id
     * @param installStatus 安装状态
     */
//    todo 更新时间
    @Override
    @Transactional
    public void updateEnterpriseApp(Long enterpriseId, Long appId,Integer installStatus) {
        Session session = getCurrentSession();
        Query query = session.createQuery("update  SwEnterpriseAppEntity s set s.installstatus = :istatus ," +
                "s.installdate = :idate  where s.enterpriseid = :eid and s.appid = :aid ");
        query.setLong("eid", enterpriseId);
        query.setLong("aid",appId);
        query.setInteger("istatus", installStatus);
        query.executeUpdate();
    }

    /**
     * 更新企业应用表
     * @param enterpriseId 企业Id
     * @param appId 企业Id
     * @param installStatus 安装状态
     * @param installDate 安装日期
     */
    @Override
    @Transactional
    public void updateEnterpriseAppSec(Long enterpriseId, Long appId,Integer installStatus,Date installDate) {
        Session session = getCurrentSession();
        Query query = session.createQuery("update  SwEnterpriseAppEntity s set s.installstatus = :istatus, " +
                "s.installdate = :idate  where s.enterpriseid = :eid and s.appid = :aid ");
        query.setLong("eid", enterpriseId);
        query.setLong("aid",appId);
        query.setInteger("istatus", installStatus);
        query.setDate("idate",installDate);
        query.executeUpdate();
    }
}
