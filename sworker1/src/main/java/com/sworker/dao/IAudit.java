package com.sworker.dao;

import com.sworker.entity.SwAuditApplyEntity;

import java.util.List;

/**
 * Created by cuijh on 2014/9/4.
 */
public interface IAudit {
    /**
     * 新增审核申请
     * @param auditApplyEntity 新增的审核申请实体
     */
    public void addAuditApply(SwAuditApplyEntity auditApplyEntity);

    /**
     * 更新审核申请
     * @param auditApplyEntity 需要被更新成的申请实体
     */
    public void updateAuditApply(SwAuditApplyEntity auditApplyEntity);

    /**
     * 更新审核申请
     * @param applyId   申请Id
     * @param auditorId 审核人Id
     * @param status    审核状态
     * @param result    审核结果
     */
    public void upadteAuditApplyById(Long applyId, Long auditorId, Integer status, Integer result);

    /**
     * 根据申请id检索审核申请
     * @param applyId 申请id
     * @return 符合条件的审核申请实体
     */
    public SwAuditApplyEntity getApplyById(Long applyId);

    /**
     * 根据应用Key检索某应用中的审核申请
     * @param enterpriseId 企业Id
     * @param appKey       应用Key
     * @return 符合条件的审核申请实体列表
     */
    public List<SwAuditApplyEntity> getApplicationsByAppKey(Long enterpriseId, String appKey);

    /**
     * 根据应用Key检索某应用中的审核申请（带分页）
     * @param enterpriseId 企业Id
     * @param appKey       应用Key
     * @param startIndex   从第startIndex条开始
     * @param pageSize     每页的条数
     * @return 符合条件的审核申请实体列表
     */
    public List<SwAuditApplyEntity> getApplicationsByAppKey(Long enterpriseId, String appKey, int startIndex, int pageSize);

    /**
     * 根据用户Id检索某审核人需要审核的申请
     * @param enterpriseId 企业Id
     * @param auditorId    审核人Id
     * @param startIndex   从第startIndex条开始
     * @param pageSize     每页的条数
     * @return 符合条件的审核申请实体列表
     */
    public List<SwAuditApplyEntity> getApplicationsByAuditor(Long enterpriseId, Long auditorId, int startIndex, int pageSize);

    /**
     * 检索关于某对象的审核申请
     * @param enterpriseId 企业Id
     * @param objectId     对象Id
     * @return 符合条件的审核申请实体
     */
    public List<SwAuditApplyEntity> getApplyByObjectId(Long enterpriseId, Long objectId);

    /**
     * 检索某申请人申请的所有审核申请
     * @param enterpriseId 企业Id
     * @param proposerId   申请人Id
     * @param startIndex   从第startIndex条开始
     * @param pageSize     每页的条数
     * @return 符合条件的审核申请实体列表
     */
    public List<SwAuditApplyEntity> getApplicationsByProposerId(Long enterpriseId, Long proposerId, int startIndex, int pageSize);

    /**
     * 实体删除
     * @param auditApplyEntity 需要被删除的审核申请实体
     */
    public void deleteApplication(SwAuditApplyEntity auditApplyEntity);

}