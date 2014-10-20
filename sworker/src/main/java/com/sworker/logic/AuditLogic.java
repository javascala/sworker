package com.sworker.logic;

import com.sworker.dao.IAudit;
import com.sworker.dao.IAuditRecord;
import com.sworker.entity.SwAuditApplyEntity;
import com.sworker.entity.SwAuditRecordsEntity;
import org.apache.lucene.store.NativeUnixDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cuijh on 2014/9/5.
 */
@Component
public class AuditLogic implements IAuditLogic {

    @Autowired
    private IAudit auditDao;

    @Autowired
    private IAuditRecord auditRecordDao;

    /**
     * 申请审核
     * @param auditApplyEntity 审核申请实体
     */
    @Transactional
    @Override
    public void apply(SwAuditApplyEntity auditApplyEntity) {

        if (auditApplyEntity.getId() == 0) {
            auditDao.addAuditApply(auditApplyEntity);
        } else {
            auditDao.updateAuditApply(auditApplyEntity);
        }

    }

    /**
     * 删除申请
     * @param auditApplyEntity 需要被删除的审核申请实体
     */
    @Transactional
    @Override
    public void deleteApply(SwAuditApplyEntity auditApplyEntity) {

        if (auditApplyEntity != null) {
            //auditApplyEntity.getStatus:1,审核中；2,审核完
            if (auditApplyEntity.getStatus() == 2) {
                //删除审核申请
                auditDao.deleteApplication(auditApplyEntity);
                //删除审核记录
                auditRecordDao.deleteAuditRecords(auditApplyEntity.getId());
            } else {//todo 返回消息还是状态字
            }
        }

    }

    /**
     * 审核申请
     * @param auditRecordsEntity 审核记录实体
     * @param nextAuditorId      下一个审核人Id
     */
    @Transactional
    @Override
    public void auditing(SwAuditRecordsEntity auditRecordsEntity, Long nextAuditorId) {
        //插入审核表
        auditRecordDao.addAuditRecord(auditRecordsEntity);
        //更新审核申请表
        auditDao.upadteAuditApplyById(auditRecordsEntity.getApplicationid(), nextAuditorId, 1, auditRecordsEntity.getAuditopinion());
    }

    /**
     * 检索某申请信息
     * @param applicationId 申请Id
     * @return 审核申请实体
     */
    @Transactional
    @Override
    public SwAuditApplyEntity getApplyInfoByApplyId(Long applicationId) {
        return auditDao.getApplyById(applicationId);
    }

    /**
     * 检索某应用中的所有审核申请信息
     * @param enterpriseId 企业Id
     * @param appKey       应用Key
     * @param pageIndex    页码
     * @param pageSize     每页的条数
     * @return 审核申请实体组成的列表
     */
    @Transactional
    @Override
    public List<SwAuditApplyEntity> getAuditInfoByAppKey(Long enterpriseId, String appKey, int pageIndex, int pageSize) {
        return auditDao.getApplicationsByAppKey(enterpriseId, appKey, (pageIndex - 1) * pageSize, pageSize);
    }

    /**
     * 检索关于某对象的审核申请信息
     * @param enterpriseId 企业Id
     * @param objectId     对象Id
     * @return 审核申请实体组成的列表
     */
    @Transactional
    @Override
    public List<SwAuditApplyEntity> getAuditInfoByObject(Long enterpriseId, Long objectId) {
        return auditDao.getApplyByObjectId(enterpriseId, objectId);
    }

    /**
     * 检索某申请人的审核申请信息
     * @param enterpriseId 企业Id
     * @param proposerId   申请人Id
     * @param pageIndex    页码
     * @param pageSize     每页的条数
     * @return 审核申请实体组成的列表
     */
    @Transactional
    @Override
    public List<SwAuditApplyEntity> getAuditInfoByProposer(Long enterpriseId, Long proposerId, int pageIndex, int pageSize) {
        return auditDao.getApplicationsByProposerId(enterpriseId, proposerId, (pageIndex - 1) * pageSize, pageSize);
    }

    /**
     * 检索某审核员需要审核的审核申请信息
     * @param enterpriseId 企业Id
     * @param auditorId    审核员Id
     * @param pageIndex    页码
     * @param pageSize     每页的条数
     * @return 审核申请实体组成的列表
     */
    @Transactional
    @Override
    public List<SwAuditApplyEntity> getNeedAuditByAuditor(Long enterpriseId, Long auditorId, int pageIndex, int pageSize) {
        return auditDao.getApplicationsByAuditor(enterpriseId, auditorId, (pageIndex - 1) * pageSize, pageSize);
    }

    /**
     * 检索某审核员的审核记录信息
     * @param enterpriseId 企业Id
     * @param auditorId    审核员Id
     * @param pageIndex    页码
     * @param pageSize     每页的条数
     * @return 审核实体组成的列表
     */
    @Transactional
    @Override
    public List<SwAuditRecordsEntity> getAuditInfoByAuditor(Long enterpriseId, Long auditorId, int pageIndex, int pageSize) {
        return auditRecordDao.getRecordsByAuditorId(enterpriseId, auditorId, (pageIndex - 1) * pageSize, pageSize);
    }

    /**
     * 检索某申请的审核信息
     * @param applicationId 申请Id
     * @return 审核实体组成的列表
     */
    @Transactional
    @Override
    public List<SwAuditRecordsEntity> getAuditInfoByApplyId(Long applicationId) {
        return auditRecordDao.getRecordsByApplyId(applicationId);
    }
}
