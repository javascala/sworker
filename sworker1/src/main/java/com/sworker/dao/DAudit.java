package com.sworker.dao;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwAuditApplyEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cuijh on 2014/9/4.
 */
@Repository
public class DAudit extends AbstractHibernateDao<SwAuditApplyEntity> implements IAudit{
    public DAudit(){
        super();
        setClazz(SwAuditApplyEntity.class);
    }

    /**
     * 新增审核申请
     * @param auditApplyEntity 新增的审核申请实体
     */
    @Transactional
    @Override
    public void addAuditApply(SwAuditApplyEntity auditApplyEntity) {
        create(auditApplyEntity);
    }

    /**
     * 更新审核申请
     * @param auditApplyEntity 需要被更新成的申请实体
     */
    @Transactional
    @Override
    public void updateAuditApply(SwAuditApplyEntity auditApplyEntity) {
        update(auditApplyEntity);
    }

    /**
     * 更新审核申请
     * @param applyId   申请Id
     * @param auditorId 审核人Id
     * @param status    审核状态
     * @param result    审核结果
     */
    @Transactional
    @Override
    public void upadteAuditApplyById(Long applyId, Long auditorId, Integer status, Integer result) {
        Session session = getCurrentSession();
        String hql = "update SwAuditApplyEntity as SAE" +
                " set SAE.auditorid = ?,SAE.status = ?,SAE.result = ?" +
                " where SAE.id = ?";
        Query query= session.createQuery(hql);
        query.setLong(0, auditorId);
        query.setInteger(1, status);
        query.setInteger(2, result);
        query.setLong(3, applyId);
        query.executeUpdate();
    }

    /**
     * 根据申请id检索审核申请
     * @param applyId 申请id
     * @return 符合条件的审核申请实体
     */
    @Override
    public SwAuditApplyEntity getApplyById(Long applyId) {
        return findById(applyId);
    }

    /**
     * 根据应用Key检索某应用中的审核申请
     * @param enterpriseId 企业Id
     * @param appKey       应用Key
     * @return 符合条件的审核申请实体列表
     */
    @Override
    public List<SwAuditApplyEntity> getApplicationsByAppKey(Long enterpriseId, String appKey) {

        String hql = "from SwAuditApplyEntity as SAE" +
                " where SAE.enterpriseid = ? and SAE.appkey = ?" +
                " order by SAE.applyfordate desc";
        Object[] params = new Object[2];
        params[0] = enterpriseId;
        params[1] = appKey;
        return findByHql( hql, params );

    }

    /**
     * 根据应用Key检索某应用中的审核申请（带分页）
     * @param enterpriseId 企业Id
     * @param appKey 应用Key
     * @param startIndex 从第startIndex条开始
     * @param pageSize 每页的条数
     * @return 符合条件的审核申请实体列表
     */
    @Override
    public List<SwAuditApplyEntity> getApplicationsByAppKey(Long enterpriseId, String appKey, int startIndex, int pageSize) {

        String hql = "from SwAuditApplyEntity as SAE" +
                " where SAE.enterpriseid = ? and SAE.appkey = ?" +
                " order by SAE.applyfordate desc";
        Object[] params = new Object[2];
        params[0] = enterpriseId;
        params[1] = appKey;
        return findByPage(hql, params, startIndex, pageSize);

    }
    /**
     * 根据用户Id检索某审核人需要审核的申请
     * @param enterpriseId 企业Id
     * @param auditorId    审核人Id
     * @param startIndex 从第startIndex条开始
     * @param pageSize 每页的条数
     * @return 符合条件的审核申请实体列表
     */
    @Override
    public List<SwAuditApplyEntity> getApplicationsByAuditor(Long enterpriseId, Long auditorId, int startIndex, int pageSize) {

        String hql = "from SwAuditApplyEntity as SAE" +
                " where SAE.enterpriseid = ? and SAE.auditorid = ?" +
                " order by SAE.applyfordate asc";
        Object[] params = new Object[2];
        params[0] = enterpriseId;
        params[1] = auditorId;
        return findByPage(hql, params, startIndex, pageSize);

    }

    /**
     * 检索关于某对象的审核申请
     * @param enterpriseId 企业Id
     * @param objectId     对象Id
     * @return 符合条件的审核申请实体
     */
    @Override
    public List<SwAuditApplyEntity> getApplyByObjectId(Long enterpriseId, Long objectId) {

        String hql = "from SwAuditApplyEntity as SAE" +
                " where SAE.enterpriseid = ? and SAE.objectid = ?" +
                " order by SAE.applyfordate desc";
        Object[] params = new Object[2];
        params[0] = enterpriseId;
        params[1] = objectId;
        return findByHql( hql, params );

    }

    /**
     * 检索某申请人申请的所有审核申请
     * @param enterpriseId 企业Id
     * @param proposerId   申请人Id
     * @param startIndex 从第startIndex条开始
     * @param pageSize 每页的条数
     * @return 符合条件的审核申请实体列表
     */
    @Override
    public List<SwAuditApplyEntity> getApplicationsByProposerId(Long enterpriseId, Long proposerId, int startIndex, int pageSize) {

        String hql = "from SwAuditApplyEntity as SAE" +
                " where SAE.enterpriseid = ? and SAE.proposerid = ?" +
                " order by SAE.applyfordate desc";
        Object[] params = new Object[2];
        params[0] = enterpriseId;
        params[1] = proposerId;
        return findByPage(hql, params, startIndex, pageSize);

    }

    /**
     * 实体删除
     * @param auditApplyEntity 需要被删除的审核申请实体
     */
    @Transactional
    @Override
    public void deleteApplication(SwAuditApplyEntity auditApplyEntity) {

        delete(auditApplyEntity);

    }
}