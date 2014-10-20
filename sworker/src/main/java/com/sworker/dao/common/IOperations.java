package com.sworker.dao.common;

import java.io.Serializable;
import java.util.List;

public interface IOperations<T extends Serializable> {

    /**
     *
     * 根据id查询XXX
     * @param id
     * @return
     */
    T findById(final Long id);

    List<T> findAll();

    List<T> findAll(String orderString);

    void create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final Long entityId);
    
    void deleteByList(List<T> entityList);

    List<T> findByHql(String hql, Object[] params);
    
    List<T> findByPage(String hql, Object[] params, int start, int limit);
    
    List<T> findByPage(int start, int limit);

    List<T> findByPageNew(String hql,Object[] params,int pageIndex,int pageSize);
}
