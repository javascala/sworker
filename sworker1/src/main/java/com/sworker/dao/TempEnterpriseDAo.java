package com.sworker.dao;

import com.sworker.model.TempEnterpriseAppEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangying on 2014/9/25.
 */
@Component("tementerprisedao")
public class TempEnterpriseDAo implements ITempEnterpriseDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public List<TempEnterpriseAppEntity> orderPage(Long enterpriseId,Integer pageIndex, Integer pageSize) {
        Session session=sessionFactory.getCurrentSession();
        Query query =session.createQuery("from TemEnterpriseEntity s where s.enterpriseid = :eid");
        query.setLong("eid",enterpriseId);
        query.setFirstResult((pageIndex-1)*pageSize);
        query.setMaxResults(pageSize);
        return	query.list();
    }
}
