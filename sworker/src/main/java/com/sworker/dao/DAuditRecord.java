package com.sworker.dao;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwAuditRecordsEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cuijh on 2014/9/5.
 */
@Repository
public class DAuditRecord extends AbstractHibernateDao<SwAuditRecordsEntity> implements IAuditRecord {
    public DAuditRecord() {
        super();
        setClazz(SwAuditRecordsEntity.class);
    }

    /**
     * 检索某申请的审核状况
     * @param applyId 申请Id
     * @return 符合条件的审核记录列表
     */
    @Override
    public List<SwAuditRecordsEntity> getRecordsByApplyId(Long applyId) {

        String hql = "from SwAuditRecordsEntity as SAR" +
                " where SAR.applicationid = ?" +
                " order by SAR.auditdate asc";
        Object[] params = new Object[1];
        params[0] = applyId;

        return findByHql(hql, params);

    }

    /**
     * 检索某审核人的审核状况
     * @param enterpriseId 企业Id
     * @param auditorId    审核人Id
     * @param startIndex   从第startIndex条开始
     * @param pageSize     每页的条数
     * @return 符合条件的审核记录列表
     */
    @Override
    public List<SwAuditRecordsEntity> getRecordsByAuditorId(Long enterpriseId, Long auditorId, int startIndex, int pageSize) {

        String hql = "from SwAuditRecordsEntity as SAR" +
                " where SAR.enterpriseid = ? and SAR.auditorid = ?" +
                " order by SAR.applicationid desc,SAR.auditdate asc";
        Object[] params = new Object[2];
        params[0] = enterpriseId;
        params[1] = auditorId;

        return findByPage(hql, params, startIndex, pageSize);

    }

    /**
     * 新增审核记录
     * @param auditRecordsEntity 新增的审核记录实体
     */
    @Transactional
    @Override
    public void addAuditRecord(SwAuditRecordsEntity auditRecordsEntity) {
        create(auditRecordsEntity);
    }

    /**
     * 删除审核记录表
     * @param applyId 申请Id
     */
    @Transactional
    @Override
    public void deleteAuditRecords(Long applyId) {
        Session session = getCurrentSession();
        session.createQuery("delete SwAuditRecordsEntity as SAR where SAR.applicationid = ?")
                .setLong(0, applyId).executeUpdate();
    }
}
