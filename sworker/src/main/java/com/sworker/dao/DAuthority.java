package com.sworker.dao;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwAuthorityEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cuijh on 2014/8/25.
 */
@Repository
public class DAuthority extends AbstractHibernateDao<SwAuthorityEntity> implements IAuthority {

    public DAuthority() {
        super();
        setClazz(SwAuthorityEntity.class);
    }

    //@Autowired

    /**
     * 检索所有的权限实体列表
     * @return 权限实体列表
     */
    @Transactional
    @Override
    public List<SwAuthorityEntity> allAuthorityEntities() {
        return findAll();
    }

    /**
     * 检索符合条件的权限实体列表
     * @param authorityKey 权限Key
     * @param authotityName 权限名称
     * @return 权限实体列表 //todo 有没有更好的方法呢,应该不能适应所有的需要，以后要再斟酌
     */
    @Transactional
    @Override
    public SwAuthorityEntity getAuthorityEntity(String authorityKey, String authotityName) {

        Session session = getCurrentSession();
        SwAuthorityEntity swAuthorityEntity = null;
        if (authorityKey != null && authorityKey.length() > 0) {
            swAuthorityEntity = (SwAuthorityEntity) session.createQuery("from SwAuthorityEntity as AE where AE.authoritykey = ?")
                    .setString(0, authorityKey).uniqueResult();
        }
        if (authotityName != null && authorityKey.length() > 0) {
            swAuthorityEntity = (SwAuthorityEntity) session.createQuery("from SwAuthorityEntity  as AE where AE.authorityname = ?")
                    .setString(0, authotityName).uniqueResult();
        }

        return swAuthorityEntity;

    }

    /**
     * 添加权限
     * @param authorityEntity 新增权限实体
     */
    @Transactional
    @Override
    public void addAuthority(SwAuthorityEntity authorityEntity) {
        create(authorityEntity);
    }

    /**
     * 更新权限
     * @param authorityEntity 需要更新的权限实体
     * @return 更新好的实体
     */
    @Transactional
    @Override
    public SwAuthorityEntity updateAuthority(SwAuthorityEntity authorityEntity) {

        return update(authorityEntity);
    }

    /**
     * 删除权限
     * @param authorityKey 需要删除的权限实体
     */
    @Transactional
    @Override
    public void deleteByKey(String authorityKey) {

        Session session = getCurrentSession();
        session.createQuery("delete SwAuthorityEntity  as AE where AE.authoritykey = ?")
                .setString(0, authorityKey).executeUpdate();

    }

    /**
     * 查询某账号对某资源有权限的操作类型
     * @param userId      账号id
     * @param sourceKey   资源Key
     * @param operateType 操作类型
     * @return 有权操作的类型列表
     */
    @Transactional
    @Override
    public List<Long> operateTypes(int userId, String sourceKey, String operateType) {

        Session session = getCurrentSession();

        String hql = "select AE.id from SwRoleAuthorityEntity as RA"
                + " inner join RA.authorityEntity AE"
                + " inner join RA.roleAccountEntity RU"
                + " where AE.resourcekey = ? and RU.userid = ? and AE.operatetype like ?";
        Query query = session.createQuery(hql);
        query.setString(0, sourceKey);
        query.setInteger(1, userId);
        query.setString(2, "%" + operateType + "%");

        return query.list();

    }

}