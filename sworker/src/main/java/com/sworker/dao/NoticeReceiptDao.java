package com.sworker.dao;
import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwNoticeReceiptEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by wangying on 2014/9/5.
 */
@Repository("ReceiptDAo")
public class NoticeReceiptDao extends AbstractHibernateDao<SwNoticeReceiptEntity> implements INoticeReceiptDao {
    /**
     * 创建回执
     * @param noticeReceiptEntity 回执实体
     */
    @Override
    @Transactional
    public void insertReceipt(SwNoticeReceiptEntity noticeReceiptEntity) {
        create(noticeReceiptEntity);
    }

    /**
     *查询公告回执总条数
     * @param announcementId 公告ID
     * @return 公告回执总条数
     */
    @Override
    @Transactional
    public Integer findAnnouncementReceiptNum(Long announcementId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select  count(s.id) from SwNoticeReceiptEntity s " +
                "where s.announcementid = :aid  ");
        query.setLong("aid", announcementId);
        return ((Long)query.uniqueResult()).intValue();
    }

    /**
     *查询公告已回执数
     * @param announcementId 公告ID
     * @param isReceipt 是否回执 02表示已回执
     * @return 公告已回执数
     */
    @Override
    @Transactional
    public Integer findAnnouncementReceiptedNum(Long announcementId, Integer isReceipt) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select  count(s.id)  from SwNoticeReceiptEntity s " +
                "where s.announcementid = :aid  and s.receiptstatus = :rs");
        query.setLong("aid", announcementId);
        query.setInteger("rs" ,isReceipt);
        return ((Long) query.uniqueResult()).intValue();
    }

    /**
     *查询回执列表
     * @param announcementId 公告ID
     * @return 回执实体列表
     */
    @Override
    @Transactional
    public List<SwNoticeReceiptEntity> findReceiptList(Long announcementId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from SwNoticeReceiptEntity s where s.announcementid in (:aid) order by receipttime desc");
        query.setLong("aid", announcementId);
        return query.list();
    }

    /**
     *修改回执信息
     * @param announcementId 公告ID
     * @param receiptPersonId 回执人ID
     */
//    receiptstatus -> 2
    @Override
    @Transactional
    public void updateReceiptInfo(Long announcementId, Long receiptPersonId,Timestamp receiptTime) {
        Session session = getCurrentSession();
        Query query = session.createQuery("update SwNoticeReceiptEntity s set s.receiptstatus = 2,s.receipttime = :rtime" +
                "  where s.announcementid = :aid and s.receiptpersonid = :rid");
        query.setTimestamp("rtime",receiptTime);
        query.setLong("aid", announcementId);
        query.setLong("rid", receiptPersonId);
        query.executeUpdate();
    }
}

