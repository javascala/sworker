package com.sworker.logic;

import com.sworker.dao.IAuthority;

import com.sworker.dao.IRoleAuthrity;
import com.sworker.entity.SwAuthorityEntity;
import com.sworker.entity.SwRoleAuthorityEntity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cuijh on 2014/8/26.
 */
@Component
public class AuthorityLogic implements IAuthorityLogic {

    @Autowired
    private IAuthority authorityDao;

    @Autowired
    private IRoleAuthrity roleAuthrityDao;

    /**
     * 创建权限
     * @param authorityEntity 新创建的权限实体
     */
    @Transactional
    @Override
    public void createAuthority(SwAuthorityEntity authorityEntity) {

        SwAuthorityEntity swAuthorityEntity;
        swAuthorityEntity = authorityDao.getAuthorityEntity(authorityEntity.getAuthoritykey(), authorityEntity.getAuthorityname());
        if (swAuthorityEntity == null) {
            authorityDao.addAuthority(authorityEntity);
        } else {
            //todo:返回消息呢还是int？返回整型吧，在service判断返回值，来显示不同消息 已存在不可以重复
        }

    }

    /**
     * 删除权限
     * @param authorityKey 需要被删除的权限Key
     */
    @Transactional
    @Override
    public void deleteAuthority(String authorityKey) {

        List<Long> roleIds;
        roleIds = roleAuthrityDao.getRoleIds(authorityKey);
        if (roleIds == null || roleIds.isEmpty()) {
            authorityDao.deleteByKey(authorityKey);
        } else {
            //todo:返回消息呢还是int？返回整型吧，在service判断返回值，来显示不同消息 正在使用不可以删除
        }

    }

    /**
     * 修改权限本身参数，不涉及与角色的关系
     * @param authorityEntity 被修改成的权限实体
     */
    @Transactional
    @Override
    public void modifyAuthority(SwAuthorityEntity authorityEntity) {

        //判断权限key和权限name是否重复
        SwAuthorityEntity swAuthorityEntity;
        swAuthorityEntity = authorityDao.getAuthorityEntity(authorityEntity.getAuthoritykey(), authorityEntity.getAuthorityname());
        if (swAuthorityEntity == null ||
                (swAuthorityEntity != null && (swAuthorityEntity.getId() == authorityEntity.getId()))) {
            authorityDao.updateAuthority(authorityEntity);//todo 更新不需要返回的吧，那样的话需要修改dao
        }

    }

    /**
     * 检索所有的权限
     * @return 所有的权限实体列表
     */
    public List<SwAuthorityEntity> getAllAuthorities() {

        return authorityDao.allAuthorityEntities();
        //todo 获取最新的公共函数，dao加排序
    }

    /**
     * 检索某权限的具体内容
     * @param authorityKey 被检索的权限key
     * @return 某权限的具体内容
     */
    public SwAuthorityEntity getAuthority(String authorityKey) {
        return authorityDao.getAuthorityEntity(authorityKey, null);
    }

    /**
     * 验证某用户对某应用的操作权限
     * @param userId      用户Id
     * @param appKey      应用Key
     * @param operateType 操作类型
     * @return 可操作，返回True；否则返回False
     */
    public Boolean verifyAuthority(int userId, String appKey, String operateType) {

        List<Long> oqk = authorityDao.operateTypes(userId, appKey, operateType);
        if (oqk != null && oqk.size() > 0) {
            return true;
        } else
            return false;

    }

    /**
     * 给角色分配权限
     * @param roleAuthorityEntity 角色权限关系实体
     */
    @Transactional
    @Override
    public void addAuthorityRole(SwRoleAuthorityEntity roleAuthorityEntity) {

        Long raid = roleAuthrityDao.getRoleAuthrityId(roleAuthorityEntity.getAuthoritykey(), roleAuthorityEntity.getRoleid());
        if (raid == null) {
            roleAuthrityDao.addRoleAuthority(roleAuthorityEntity);
        } else {
            //todo:返回消息呢还是int？返回整型吧，在service判断返回值，来显示不同消息 正在使用不可以删除
        }

    }

    /**
     * 删除使用某权限的某角色
     * @param authorityKey 权限Key
     * @param roleId       角色Id
     */
    @Transactional
    @Override
    public void deleteAuthorityRole(String authorityKey, Integer roleId) {
        roleAuthrityDao.deleteRoleByKey(authorityKey, roleId);
    }

    /**
     * 删除使用某权限的所有角色
     * @param authorityKey 权限Key
     */
    @Transactional
    @Override
    public void deleteAuthorityAllRoles(String authorityKey) {
        roleAuthrityDao.deleteRolesByKey(authorityKey);
    }

    /**
     * 获取使用权限的角色id
     * @param authorityKey 权限Key
     * @return 使用该权限的所有角色id
     */
    public List<Long> getAuthorityRoles(String authorityKey) {
        return roleAuthrityDao.getRoleIds(authorityKey);
    }

}