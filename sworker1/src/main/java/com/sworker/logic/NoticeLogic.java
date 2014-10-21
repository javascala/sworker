package com.sworker.logic;

import com.sworker.dao.INoticeDao;
import com.sworker.dao.INoticeReceiptDao;
import com.sworker.entity.SwNoticeEntity;
import com.sworker.entity.SwNoticeReceiptEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangying on 2014/9/9.
 */
@Component
public class NoticeLogic implements INoticeLogic{
    @Resource
    private INoticeDao noticeDao;

    @Autowired
    private INoticeReceiptDao noticereceiptDao;

    /**
     *公告内容的发布
     * @param noticeEntity 公告实体
     * @return 公告Id
     */
    @Transactional
    @Override
    public Long saveAnnouncement(SwNoticeEntity noticeEntity) {
        try{
           if (noticeEntity.getEnterpriseid() != null && noticeEntity.getTitle() != null &&
                   noticeEntity.getStatus() != null && noticeEntity.getContent() != null &&
                   noticeEntity.getUpdatetime() != null && noticeEntity.getCreatedate() != null &&
                   noticeEntity.getIscomment() != null && noticeEntity.getIsreceipt() != null &&
                   noticeEntity.getCreatepersonid() != null) {
               noticeDao.create(noticeEntity);
               return noticeEntity.getId();
           }
           }catch (Exception e){
            e.printStackTrace();
        }
         return null;
    }

    /**
     * 查询企业所有公告
     * @param enterpriseId 企业Id
     * @param pageNum 页码
     * @param pageSize 显示条数
     * @return 公告列表
     */
    @Override
    @Transactional
    public List<SwNoticeEntity> searchAnnouncements(Long enterpriseId, Integer pageNum, Integer pageSize) {
            try{
                if (enterpriseId != null && pageNum != null && pageSize != null) {
                    return noticeDao.findEnterpriseList(enterpriseId, pageNum, pageSize);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        return null;
    }

    /**
     *查询公告信息详细
     * @param Id 公告ID
     * @return 公告实体
     */
    @Override
    @Transactional
    public SwNoticeEntity searchAnnounceById(long Id) {
           try{
               return noticeDao.findAnnouncementInfo(Id);
           }catch (Exception e){
               e.printStackTrace();
           }
        return null;
    }

    /**
     *删除公告信息
     * @param Id 公告ID
     */
    @Override
    @Transactional
    public void deleteAnnouncement(long Id) {
        noticeDao.deleteAnnouncementInfo(Id);
    }

    /**
     *修改公告信息
     * @param noticeEntity 公告实体
     * @return 布尔型 true 成功, false  失败
     */
    @Override
    @Transactional
    public Boolean updateAnnouncement(SwNoticeEntity noticeEntity){
        if( noticeEntity.getTitle() != null && noticeEntity.getExpirationdate() != null &&
              noticeEntity.getStatus() != null && noticeEntity.getContent() != null &&
                noticeEntity.getUpdatetime() != null && noticeEntity.getIscomment() != null &&
                 noticeEntity.getIsreceipt() != null){
            noticeDao.update(noticeEntity);
            return true;
        }
        return false;
    }

    /**
     * 公告信息状态为过期
     * @param Id 公告实体
     * @param status 状态
     * @return 布尔值
     */
    @Override
    @Transactional
    public Boolean announcementOutdate(long Id, Integer status) {
            if (status != null) {
                noticeDao.updateAnnouncementStatus(Id, status);
                return true;
            }
        return false;
    }

    /**
     * 创建公告回执信息
     * @param noticeReceiptEntity 回执实体
     */
    @Override
    @Transactional
    public void saveReceipt(SwNoticeReceiptEntity noticeReceiptEntity) {
           if (noticeReceiptEntity.getAnnouncementid() != null && noticeReceiptEntity.getReceiptpersonid() != null
                   && noticeReceiptEntity.getReceiptstatus() != null) {
               noticereceiptDao.insertReceipt(noticeReceiptEntity);
           }
    }

    /**
     * 查询公告回执信息
     * @param announcementId  公告Id
     * @return 回执数
     */
    @Override
    @Transactional
    public Map<String, Integer> searchReceipt(Long announcementId) {
        Map<String,Integer> resultMap = new HashMap<String,Integer>();
        if (announcementId != null) {
            Integer receiptNum = noticereceiptDao.findAnnouncementReceiptNum(announcementId);
            Integer receiptedNum = noticereceiptDao.findAnnouncementReceiptedNum(announcementId, 2);
            resultMap.put("receiptNum", receiptNum);
            resultMap.put("receiptedNum", receiptedNum);
            Integer notReceipt = receiptNum - receiptedNum;
            resultMap.put("notReceiptNum", notReceipt);
            return resultMap;
        }
        return null;
    }

    /**
     * 根据公告ID查询回执信息列表
     * @param announcementId 公告Id
     * @return 回执实体列表
     */
    @Override
    @Transactional
    public List<SwNoticeReceiptEntity> searchReceiptList(Long announcementId) {
            if (announcementId != null) {
                return noticereceiptDao.findReceiptList(announcementId);
            }
        return null;
    }

    /**
     * 公告回执，修改回执信息
     * @param receiptPersonId 回执人ID
     * @param announcementId 公告ID
     * @param receiptTime 回执时间
     * @return 布尔型 true 公告修改成功标识 false 失败
     */
    @Override
    @Transactional
    public Boolean updateReceipt(Long receiptPersonId, Long announcementId, Timestamp receiptTime) {
        if (receiptPersonId != null && announcementId !=  null && receiptTime != null ) {
            noticereceiptDao.updateReceiptInfo(announcementId, receiptPersonId,receiptTime);
            return true;
        }
        return false;
    }
}
