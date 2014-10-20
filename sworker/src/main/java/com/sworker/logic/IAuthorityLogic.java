package com.sworker.logic;

import com.sworker.entity.SwAuthorityEntity;
import com.sworker.entity.SwRoleAuthorityEntity;

import java.util.List;

/**
 * Created by cuijh on 2014/8/26.
 */
public interface IAuthorityLogic {

    /**
     * 创建权限
     * @param authorityEntity 新创建的权限实体
     */
    public void createAuthority(SwAuthorityEntity authorityEntity);

    /**
     * 删除权限
     * @param authorityKey 需要被删除的权限Key
     */
    public void deleteAuthority(String authorityKey);

    /**
     * 修改权限本身参数，不涉及与角色的关系
     * @param authorityEntity 被修改成的权限实体
     */
    public void modifyAuthority(SwAuthorityEntity authorityEntity);

    /**
     * 检索所有的权限
     * @return 所有的权限实体列表
     */
    public List<SwAuthorityEntity> getAllAuthorities();

    /**
     * 检索某权限的具体内容
     * @param authorityKey 被检索的权限key
     * @return 某权限的具体内容
     */
    public SwAuthorityEntity getAuthority(String authorityKey);

    /**
     * 验证某用户对某应用的操作权限
     * @param userId 用户Id
     * @param appKey 应用Key
     * @param operateType 操作类型
     * @return 可操作，返回True；否则返回False
     */
    public Boolean verifyAuthority(int userId,String appKey,String operateType);

    /**
     * 给角色分配权限
     * @param roleAuthorityEntity 角色权限关系实体
     */
    public void addAuthorityRole(SwRoleAuthorityEntity roleAuthorityEntity);

    /**
     * 删除使用某权限的某角色
     * @param authorityKey 权限Key
     * @param roleId 角色Id
     */
    public void deleteAuthorityRole(String authorityKey,Integer roleId);

    /**
     * 删除使用某权限的所有角色
     * @param authorityKey 权限Key
     */
    public void deleteAuthorityAllRoles(String authorityKey);

    /**
     * 获取使用权限的角色id
     * @param authorityKey 权限Key
     * @return 使用该权限的所有角色id
     */
    public List<Long> getAuthorityRoles(String authorityKey);
}
