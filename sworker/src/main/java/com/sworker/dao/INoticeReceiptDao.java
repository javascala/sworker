package com.sworker.dao;

import com.sworker.entity.SwNoticeReceiptEntity;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by wangying on 2014/9/5.
 */
@Repository
public interface INoticeReceiptDao {
    /**
     * 创建回执
     * @param noticeReceiptEntity 回执实体
     */
    public void insertReceipt(SwNoticeReceiptEntity noticeReceiptEntity);

    /**
     *查询公告回执总条数
     * @param announcementId 公告ID
     * @return 公告回执总条数
     */
    public Integer findAnnouncementReceiptNum(Long announcementId);

    /**
     *查询公告已回执数
     * @param announcementId 公告ID
     * @param isReceipt 是否回执
     * @return 公告已回执数
     */
    public Integer findAnnouncementReceiptedNum(Long announcementId,Integer isReceipt);

    /**
     *查询回执列表
     * @param announcementId 公告ID
     * @return 回执实体列表
     */
    public List<SwNoticeReceiptEntity> findReceiptList(Long announcementId);

    /**
     *修改回执信息
     * @param announcementId 公告ID
     * @param receiptPersonId 回执人ID
     */
    public void updateReceiptInfo(Long announcementId,Long receiptPersonId,Timestamp receiptTime);
}
