package com.sworker.dao;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.AcitvityEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhangjin on 2014/8/7.
 */
@Component("blogDao")
public class BlogDao extends AbstractHibernateDao<AcitvityEntity> implements IBlogDao {

    @Autowired
    public SessionFactory sessionFactory;

    public BlogDao() {
        super();
        this.setClazz(AcitvityEntity.class);
    }

    @Transactional
    @Override
    public List<AcitvityEntity> dynamicList(){
        Session session = getCurrentSession();

        List<AcitvityEntity> list = session.createQuery("from AcitvityEntity").list();
        return list;
    }

    @Transactional
    @Override
    public void addActivity(AcitvityEntity entity) {
        create(entity);
    }

}
