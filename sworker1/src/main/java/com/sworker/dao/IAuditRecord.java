package com.sworker.dao;

import com.sworker.entity.SwAuditRecordsEntity;

import java.util.List;

/**
 * Created by cuijh on 2014/9/5.
 */
public interface IAuditRecord {
    /**
     * 检索某申请的审核状况
     * @param applyId 申请Id
     * @return 符合条件的审核记录列表
     */
    public List<SwAuditRecordsEntity> getRecordsByApplyId(Long applyId);

    /**
     * 检索某审核人的审核状况
     * @param enterpriseId 企业Id
     * @param auditorId    审核人Id
     * @param startIndex   从第startIndex条开始
     * @param pageSize     每页的条数
     * @return 符合条件的审核记录列表
     */
    public List<SwAuditRecordsEntity> getRecordsByAuditorId(Long enterpriseId, Long auditorId, int startIndex, int pageSize);

    /**
     * 新增审核记录
     * @param auditRecordsEntity 新增的审核记录实体
     */
    public void addAuditRecord(SwAuditRecordsEntity auditRecordsEntity);

    /**
     * 删除审核记录表
     * @param applyId 申请Id
     */
    public void deleteAuditRecords(Long applyId);
}
