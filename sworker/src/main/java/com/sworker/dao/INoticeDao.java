package com.sworker.dao;

import com.sworker.dao.common.IOperations;
import com.sworker.entity.SwNoticeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangying on 2014/9/3.
 */
@Repository
public interface INoticeDao extends IOperations<SwNoticeEntity> {

    /**
     * 查询公告信息
     * @param announcementId 公告ID
     * @return 公告实体
     */
    public SwNoticeEntity findAnnouncementInfo(long announcementId);

    /**
     *修改公告信息
     * @param announcementId 公告ID
     * @return  公告实体
     */
    public SwNoticeEntity updateAnnouncementStatus(long announcementId,Integer status);

    /**
     * 删除公告信息
     * @param announcementId 公告ID
     * @return 布尔型 是否删除成功
     */
    public Boolean deleteAnnouncementInfo(long announcementId);

    /**
     *查询公告列表
     * @param enterpriseId 实体ID
     * @return  公告列表
     */
    public List<SwNoticeEntity> findEnterpriseList(Long enterpriseId,Integer pageNum, Integer pageSize);
}
