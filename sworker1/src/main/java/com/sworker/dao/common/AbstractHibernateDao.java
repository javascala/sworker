package com.sworker.dao.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

//import com.google.common.base.Preconditions;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDao<T extends Serializable> implements
        IOperations<T> {
    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    // API
    protected final void setClazz(final Class<T> clazzToSet) {
        // clazz = Preconditions.checkNotNull(clazzToSet);
        clazz = clazzToSet;
    }

    /**
     * 按实体名和id获取实体
     * 
     * @param entityClass
     * @param id
     * @return
     */
    // @Override
    public final T findById(final Long id) {
        Session session = getCurrentSession();
        // session.beginTransaction();
        T entity = (T) session.get(clazz, id);
        // session.getTransaction().commit();
        return entity;
    }

//    public T find(String name) {
//
//        Field[] fields = clazz.getDeclaredFields();
//
//        for (Field f : fields) {
//            if (null != f.getAnnotation(Name.class)) {
//                String sql = String.format("from %s where %s = ?",
//                        clazz.getName(), f.getName());
//                Query query = sessionFactory.getCurrentSession().createQuery(
//                        sql);
//                query.setString(0, name);
//                return (T) query.uniqueResult();
//            }
//        }
//        return null;
//    }

    /**
     * 查找所有实体
     * 
     * @param orderString 排序条件
     * @return
     */
    @Override
    public final List<T> findAll(String orderString) {
        Session session = getCurrentSession();
        // session.beginTransaction();
        String hql = "from " + clazz.getName() + orderString;
        Query query = session.createQuery(hql);
        List<T> list = query.list();
        // session.getTransaction().commit();
        return list;

    }

    /**
     * 查找所有实体
     * 
     * @param orderString 排序条件
     * @return
     */
    @Override
    public final List<T> findAll() {
        Session session = getCurrentSession();
        // session.beginTransaction();
        String hql = "from " + clazz.getName();
        Query query = session.createQuery(hql);
        List<T> list = query.list();
        // session.getTransaction().commit();
        return list;
    }
    
    /**
     * 分页使用
     * 
     * @param entityClass
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<T> findByPage(String hql, Object[] params, int start, int limit) {
        Session session = sessionFactory.getCurrentSession();
        // session.beginTransaction();
        List<T> list = null;
        try {
            Query query = session.createQuery(hql);
            if (params != null && params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    query.setParameter(i, params[i]);
                }
            }

            if (limit != 0) {
                query.setFirstResult(start).setMaxResults(limit);
            }
            list = query.list();
            // session.getTransaction().commit();
        } catch (Exception ef) {
            ef.printStackTrace();
            // session.getTransaction().rollback();
        }

        return list;
    }

    /**
     * 分页使用
     * 
     * @param entityClass
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<T> findByPage(int start, int limit) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select o from " + clazz.getName() + " o";
        // session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setFirstResult(start).setMaxResults(limit);
        List<T> list = query.list();
        // session.getTransaction().commit();
        return list;
    }

    /**
     * 添加实体
     */
    @Override
    public final void create(final T entity) {
        // Preconditions.checkNotNull(entity);
        // getCurrentSession().persist(entity);
        getCurrentSession().saveOrUpdate(entity);
    }

    /**
     * 更新实体
     */
    @Override
    public final T update(final T entity) {
        // Preconditions.checkNotNull(entity);
        return (T) getCurrentSession().merge(entity);
    }

    /**
     * 删除实体
     */
    @Override
    public final void delete(final T entity) {
        // Preconditions.checkNotNull(entity);
        getCurrentSession().delete(entity);
    }

    /**
     * 按id删除实体
     */
    @Override
    public final void deleteById(final Long entityId) {
        final T entity = findById(entityId);
        // Preconditions.checkState(entity != null);
        delete(entity);
    }

    @Override
    public void deleteByList(List<T> entityList) {
        for (T t : entityList) {
            this.delete(t);
        }
    }

    /**
     * 按自定义的hql取得结果集
     */
    @Override
    public List<T> findByHql(String hql, Object[] params) {
        Session session = sessionFactory.getCurrentSession();
        // session.beginTransaction();
        Query query = session.createQuery(hql);

        if (null != params && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }

        List<T> list = query.list();
        // session.getTransaction().commit();

        return list;
    }

    /**
     * 分页检索
     * @param hql 检索语句
     * @param params 参数
     * @param pageIndex 页码
     * @param pageSize 每页条数
     * @return 检索结果
     */
    @Override
    public List<T> findByPageNew(String hql, Object[] params, int pageIndex, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        // session.beginTransaction();
        List<T> list = null;
        try {
            Query query = session.createQuery(hql);
            if (params != null && params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    query.setParameter(i, params[i]);
                }
            }

            if (pageSize != 0 && pageIndex > 0) {
                query.setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize);
            }
            list = query.list();
            // session.getTransaction().commit();
        } catch (Exception ef) {
            ef.printStackTrace();
            // session.getTransaction().rollback();
        }

        return list;
    }
}