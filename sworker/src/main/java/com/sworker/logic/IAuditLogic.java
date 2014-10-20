package com.sworker.logic;

import com.sworker.entity.SwAuditApplyEntity;
import com.sworker.entity.SwAuditRecordsEntity;

import java.util.List;

/**
 * Created by cuijh on 2014/9/5.
 */
public interface IAuditLogic {

    /**
     * 申请审核
     * @param auditApplyEntity 审核申请实体
     */
    public void apply(SwAuditApplyEntity auditApplyEntity);

    /**
     * 删除申请
     * @param auditApplyEntity 需要被删除的审核申请实体
     */
    public void deleteApply(SwAuditApplyEntity auditApplyEntity);

    /**
     * 审核申请
     * @param auditRecordsEntity 审核记录实体
     * @param nextAuditorId      下一个审核人Id
     */
    public void auditing(SwAuditRecordsEntity auditRecordsEntity, Long nextAuditorId);

    /**
     * 检索某申请信息
     * @param applicationId 申请Id
     * @return 审核申请实体
     */
    public SwAuditApplyEntity getApplyInfoByApplyId(Long applicationId);

    /**
     * 检索某应用中的所有审核申请信息
     * @param enterpriseId 企业Id
     * @param appKey       应用Key
     * @param pageIndex    页码
     * @param pageSize     每页的条数
     * @return 审核申请实体组成的列表
     */
    public List<SwAuditApplyEntity> getAuditInfoByAppKey(Long enterpriseId, String appKey, int pageIndex, int pageSize);

    /**
     * 检索关于某对象的审核申请信息
     * @param enterpriseId 企业Id
     * @param objectId     对象Id
     * @return 审核申请实体组成的列表
     */
    public List<SwAuditApplyEntity> getAuditInfoByObject(Long enterpriseId, Long objectId);

    /**
     * 检索某申请人的审核申请信息
     * @param enterpriseId 企业Id
     * @param proposerId   申请人Id
     * @param pageIndex    页码
     * @param pageSize     每页的条数
     * @return 审核申请实体组成的列表
     */
    public List<SwAuditApplyEntity> getAuditInfoByProposer(Long enterpriseId, Long proposerId, int pageIndex, int pageSize);

    /**
     * 检索某审核员的审核申请信息
     * @param enterpriseId 企业Id
     * @param auditorId    审核员Id
     * @param pageIndex    页码
     * @param pageSize     每页的条数
     * @return 审核申请实体组成的列表
     */
    public List<SwAuditApplyEntity> getNeedAuditByAuditor(Long enterpriseId, Long auditorId, int pageIndex, int pageSize);

    /**
     * 检索某审核员的审核记录信息
     * @param enterpriseId 企业Id
     * @param auditorId    审核员Id
     * @param pageIndex    页码
     * @param pageSize     每页的条数
     * @return 审核实体组成的列表
     */
    public List<SwAuditRecordsEntity> getAuditInfoByAuditor(Long enterpriseId, Long auditorId, int pageIndex, int pageSize);

    /**
     * 检索某申请的审核信息
     * @param applicationId 申请Id
     * @return 审核实体组成的列表
     */
    public List<SwAuditRecordsEntity> getAuditInfoByApplyId(Long applicationId);
}
