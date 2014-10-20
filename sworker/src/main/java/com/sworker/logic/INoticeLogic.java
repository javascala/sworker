package com.sworker.logic;

import com.sworker.entity.SwNoticeEntity;
import com.sworker.entity.SwNoticeReceiptEntity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by wangying on 2014/9/9.
 */
public interface INoticeLogic {
    /**
     *公告内容的发布
     * @param noticeEntity 公告实体
     * @return 公告Id
     */
    public  Long saveAnnouncement(SwNoticeEntity noticeEntity);

    /**
     * 查询企业所有公告
     * @param enterpriseId 企业Id
     * @param pageNum 页码
     * @param pageSize 显示条数
     * @return
     */
    public List<SwNoticeEntity> searchAnnouncements(Long enterpriseId, Integer pageNum, Integer pageSize);

    /**
     *查询公告信息详细
     * @param Id 公告ID
     * @return 公告实体
     */
    public SwNoticeEntity searchAnnounceById(long Id);

    /**
     *删除公告信息
     * @param Id 公告ID
     */
    public void deleteAnnouncement(long Id);

    /**
     *修改公告信息
     * @param noticeEntity 公告实体
     * @return 布尔型 true 成功, false  失败
     */
    public Boolean updateAnnouncement(SwNoticeEntity noticeEntity);

    /**
     * 公告信息状态为过期
     * @param Id 公告实体
     * @param status 状态
     * @return 布尔值
     */
    public Boolean announcementOutdate(long Id, Integer status);

    /**
     * 创建公告回执信息
     * @param noticeReceiptEntity 回执实体
     */
    public void saveReceipt(SwNoticeReceiptEntity noticeReceiptEntity);

    /**
     * 查询公告回执信息
     * @param announcementId  公告Id
     * @return 回执数
     */
    public Map<String, Integer> searchReceipt(Long announcementId);

    /**
     * 根据公告ID查询回执信息列表
     * @param announcementId 公告Id
     * @return 回执实体列表
     */
    public List<SwNoticeReceiptEntity> searchReceiptList(Long announcementId);

    /**
     * 公告回执，修改回执信息
     * @param receiptPersonId 回执人ID
     * @param announcementId 公告ID
     * @param receiptTime 回执时间
     * @return 布尔型 true 公告修改成功标识 false 失败
     */
    public Boolean updateReceipt(Long receiptPersonId, Long announcementId, Timestamp receiptTime);
}
