package com.sworker.dao;

import com.sworker.dao.common.AbstractHibernateDao;
import com.sworker.entity.SwRoleAuthorityEntity;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cuijh on 2014/8/26.
 */
@Repository
public class DRoleAuthority extends AbstractHibernateDao<SwRoleAuthorityEntity> implements IRoleAuthrity {
    public DRoleAuthority(){
        super();
        setClazz(SwRoleAuthorityEntity.class);
    }

//    @Autowired
//    public SessionFactory sessionFactory;

    /**
     * 检索符合条件的角色Id列表
     * @param authotityKey:权限Key
     * @return 角色Id列表
     */
    @Transactional
    @Override
    public List<Long> getRoleIds(String authotityKey){
        Session session = getCurrentSession();
        List<Long> list = session.createQuery("select RAE.roleid from SwRoleAuthorityEntity as RAE where RAE.authoritykey = ?")
                .setString(0,authotityKey).list();
        return list;
    }

    /**
     * 检索某权限与某角色的对应关系id
     * @param authorityKey 权限Key
     * @param roleId 角色Id
     * @return 角色权限关系表id
     */
    @Transactional
    @Override
    public Long getRoleAuthrityId(String authorityKey,Long roleId){
        Session session = getCurrentSession();

        String hql = "select RAE.id from SwRoleAuthorityEntity as RAE where RAE.authoritykey = ? and RAE.roleid = ?";
        Query query= session.createQuery(hql);
        query.setString(0, authorityKey);
        query.setLong(1, roleId);
        Long reid = (Long)query.uniqueResult();

        return  reid;
    }

    /**
     * 为角色添加权限
     * @param roleAuthorityEntity:新增权限实体
     */
    @Transactional
    @Override
    public void addRoleAuthority(SwRoleAuthorityEntity roleAuthorityEntity){
        create(roleAuthorityEntity);
    }

    /**
     * 删除使用权限的所有角色
     * @param authorityKey 权限Key
     */
    @Transactional
    @Override
    public void deleteRolesByKey(String authorityKey){
        Session session = getCurrentSession();
        session.createQuery("delete SwRoleAuthorityEntity as SAE where SAE.authoritykey = ?")
                .setString(0, authorityKey).executeUpdate();
    }

    /**
     * 删除使用某权限的某角色
     * @param authorityKey 权限Key
     * @param roleId 需要删除的角色Id
     */
    @Transactional
    @Override
    public void deleteRoleByKey(String authorityKey,Integer roleId){
        Session session = getCurrentSession();
        String hql = "delete SwRoleAuthorityEntity as SAE where SAE.authoritykey = ? and SAE.roleid = ? ";
        Query query= session.createQuery(hql);
        query.setString(0, authorityKey);
        query.setInteger(1, roleId);
        query.executeUpdate();
    }

}
