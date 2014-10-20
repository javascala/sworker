package com.sworker.dao;

/**
 * Created by wangying on 2014/9/3.
 */

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwNoticeEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("noticeDao")
public class NoticeDao extends AbstractHibernateDao<SwNoticeEntity> implements INoticeDao {
    public NoticeDao(){
        super();
        this.setClazz(SwNoticeEntity.class);
    }


    /**
     * 查询公告信息
     * @param announcementId 公告ID
     * @return 公告实体
     */
    @Override
    @Transactional
    public SwNoticeEntity findAnnouncementInfo(long announcementId) {
         return findById(announcementId);
    }

    /**
     *修改公告信息（状态）
     * @param announcementId 公告ID
     * @return  公告实体
     */
    @Override
    @Transactional
    public SwNoticeEntity updateAnnouncementStatus(long announcementId,Integer status) {
        SwNoticeEntity noticeEntity = findById(announcementId);
        noticeEntity.setStatus(status);
        update(noticeEntity);
        return noticeEntity;
    }

    /**
     * 删除公告信息
     * @param announcementId 公告ID
     * @return 布尔型 是否删除成功
     */
    @Override
    @Transactional
    public Boolean deleteAnnouncementInfo(long announcementId) {
        SwNoticeEntity noticeEntity = findById(announcementId);
        if(noticeEntity != null){
            deleteById(announcementId);
            return true;
        }
        else
            return false;
    }

    /**
     *查询公告列表
     * @param enterpriseId 实体ID
     * @return  公告列表
     */
    @Override
    @Transactional
    public List<SwNoticeEntity> findEnterpriseList(Long enterpriseId,Integer pageNum, Integer pageSize) {
        String hql = "from SwNoticeEntity s where s.enterpriseid = ?"
                             + "order by s.updatetime desc";
        Object[] params = {enterpriseId};
        return findByPage(hql,params,(pageNum-1)*pageSize,pageSize);
//        Session session = getCurrentSession();
//        Query query = session.createQuery("from SwNoticeEntity s where s.enterpriseid in(:sid) order by updatetime desc");
//        query.setLong("sid", enterpriseId);
//        query.setFirstResult((pageNum -1) * pageSize);
//        query.setMaxResults(pageSize);
//        return query.list();

    }
}
