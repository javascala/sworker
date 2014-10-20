package com.sworker.logic;

import com.sworker.entity.SwNoticeEntity;
import com.sworker.entity.SwNoticeReceiptEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by wangying on 2014/9/11.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class NoticeTest {
    @Resource
    private INoticeLogic noticeLogic;

    /**
     *公告内容的发布
     */
//    done
    @Test
    public void saveAnnouncementTest(){
        SwNoticeEntity noticeEntity = new SwNoticeEntity();
        noticeEntity.setEnterpriseid(4L);
        noticeEntity.setTitle("公告");
        noticeEntity.setStatus(0);
        noticeEntity.setContent("内容");
        noticeEntity.setUpdatetime(Timestamp.valueOf("2014-09-09 09:01:12"));
        noticeEntity.setCreatedate(Timestamp.valueOf("2014-09-12 09:23:18"));
        noticeEntity.setIscomment(0);
        noticeEntity.setIsreceipt(0);
        noticeEntity.setCreatepersonid(12L);
        noticeLogic.saveAnnouncement(noticeEntity);
    }

    /**
     * 查询企业所有公告
     */
//    done
    @Test
    public void searchAnnouncementsTest(){
        Long enterpriseId = 4L;
        Integer pageNum = 4;
        Integer pageSize = 3;
        List<SwNoticeEntity> aa= null;
       aa =  noticeLogic.searchAnnouncements(enterpriseId,pageNum,pageSize);
    }

    /**
     * 查询公告信息详细
     */
//    done
    @Test
    public void searchAnnounceByIdTest(){
        long Id = 1;
        SwNoticeEntity noticeEntity = new SwNoticeEntity();
        noticeEntity = noticeLogic.searchAnnounceById(Id);
    }

    /**
     * 删除公告信息
     */
//    done
    @Test
    public void deleteAnnouncementTest(){
        long Id = 32768;
        noticeLogic.deleteAnnouncement(Id);
    }

    /**
     * 修改公告信息
     */
//    done
    @Test
    public  void updateAnnouncementTest(){
        SwNoticeEntity noticeEntity = new SwNoticeEntity();
        noticeEntity.setId(131072);
        noticeEntity.setTitle("公告标题");
        noticeEntity.setStatus(1);
        noticeEntity.setContent("内容");
        noticeEntity.setUpdatetime(Timestamp.valueOf("2014-09-09 09:01:12"));
        noticeEntity.setExpirationdate(Timestamp.valueOf("2015-12-31 23:59:59"));
        noticeEntity.setIscomment(0);
        noticeEntity.setIsreceipt(0);
        noticeLogic.updateAnnouncement(noticeEntity);
    }

    /**
     * 公告信息状态为过期
     */
//    done
    @Test
    public void announcementOutdateTest(){
        long Id = 131072;
        Integer status = 1;
        noticeLogic.announcementOutdate(Id,status);
    }

    /**
     * 创建公告回执信息
     */
//    done
    @Test
    public void saveReceiptTest(){
        SwNoticeReceiptEntity noticeReceiptEntity = new SwNoticeReceiptEntity();
        noticeReceiptEntity.setAnnouncementid(Long.valueOf("33"));
        noticeReceiptEntity.setReceiptpersonid(Long.valueOf("11"));
        noticeReceiptEntity.setReceiptstatus(Integer.valueOf("0"));
        noticeLogic.saveReceipt(noticeReceiptEntity);
    }

    /**
     * 查询公告回执信息
     */
//    done
    @Test
    public void searchReceiptTest(){
        Long announcementId = 33L;
        Map<String,Integer> resultMap = noticeLogic.searchReceipt(announcementId);
//        Assert.assertEquals(3,resultMap.size());
    }

    /**
     * 根据公告ID查询回执信息列表
     */
//    done
    @Test
    public void searchReceiptListTest(){
        Long announcementId = 33L;
        List <SwNoticeReceiptEntity> bb= null;
        bb = noticeLogic.searchReceiptList(announcementId);
    }

    /**
     * 公告回执，修改回执信息
     */
//    done
    @Test
    public void updateReceiptTest(){
        Long receiptPersonId = 11L;
        Long announcementId = 33L;
        Timestamp receiptTime = Timestamp.valueOf("2014-09-07 14:30:30");
        noticeLogic.updateReceipt(receiptPersonId,announcementId,receiptTime);
    }
}
