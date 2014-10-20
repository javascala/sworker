package com.sworker.dao;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwVisibleRangeEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

@Repository("visibleDao")
public class VisibleDao extends AbstractHibernateDao<SwVisibleRangeEntity> implements IVisibleDao {


    public VisibleDao() {

        super();
        this.setClazz(SwVisibleRangeEntity.class);

    }

    /**
     * 查询可见范围群组ID列表
     *
     * @param objectid 对象id
     * @return 可见范围实体列表
     */
    @Override
    @Transactional
    public List<SwVisibleRangeEntity> findViewRangeID(Long objectid) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from SwVisibleRangeEntity s where s.objectid = :oid");
        query.setLong("oid", objectid);
        return query.list();
    }

    /**
     * 插入可见范围实体
     *
     * @param visibleRangeEntityList 可见范围列表
     */
    @Transactional
    @Override
    public void insertAll(List<SwVisibleRangeEntity> visibleRangeEntityList) {
        Session session = getCurrentSession();
        Iterator<SwVisibleRangeEntity> visible = visibleRangeEntityList.iterator();
        try {
            int count = 0;
            while (visible.hasNext()) {
                SwVisibleRangeEntity visibleEntity = (SwVisibleRangeEntity) visible.next();
                create(visibleEntity);

                if (++count % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 删除可见范围
     * @param objectid 对象id
     * @return 是否删除成功 true:成功 false:失败
     */
    @Override
    @Transactional
    public boolean deleteByObjectId(Long objectid) {
        Iterator<SwVisibleRangeEntity> visible = findByObjectId(objectid).iterator();
        if (visible != null && visible != null) {
            while (visible.hasNext()) {
                delete(visible.next());
            }
            return true;
        }
        return false;
    }

//todo

    /**
     * 查询可见范围实体列表列表
     *
     * @param objectid 对象id
     * @return 可见范围实体列表
     */
    @Override
    @Transactional
    public List<SwVisibleRangeEntity> findByObjectId(Long objectid) {
        Session session = getCurrentSession();
        Query query = session.createQuery(" from SwVisibleRangeEntity s where s.objectid = :oid");
        query.setLong("oid", objectid);
        return query.list();
    }

    /**
     * 查询群组ID列表
     *
     * @param objectid      用户Id
     * @param viewrangetype 范围类型
     * @return Id列表
     */
    @Override
    @Transactional
    public List<BigInteger> findgroupIdList(Long objectid, String viewrangetype) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select  groupeviewrangeid from SwVisibleRangeEntity s where " +
                "s.objectid = :oid and s.viewrangetype = :type");
        query.setLong("oid", objectid);
        query.setString("type", viewrangetype);
        if ("群组".equals(viewrangetype)){
            return query.list();}
        return null;
    }

    /**
     * 查询用户ID列表
     * @param objectid      用户Id
     * @param viewrangetype 范围类型
     * @return
     */
    @Override
    @Transactional
    public List<BigInteger> finduserIdList(Long objectid, String viewrangetype) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select  userviewrangeid from SwVisibleRangeEntity s where " +
                "s.objectid = :oid and s.viewrangetype = :type");
        query.setLong("oid", objectid);
        query.setString("type", viewrangetype);
        if ("用户".equals(viewrangetype)) {
            return query.list();
        }
        return null;
    }
}
