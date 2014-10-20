package com.sworker.dao;

import com.sworker.entity.SwRoleAuthorityEntity;

import java.util.List;

/**
 * Created by cuijh on 2014/8/26.
 */
public interface IRoleAuthrity {

    /**
     * 检索拥有权限的角色id
     * @param authotityKey 权限Key
     * @return 返回拥有权限的所有角色id组成的列表
     */
    public List<Long> getRoleIds(String authotityKey);

    /**
     * 检索某权限与某角色的对应关系id
     * @param authorityKey 权限Key
     * @param roleId 角色Id
     * @return 角色权限关系表id
     */
    public Long getRoleAuthrityId(String authorityKey,Long roleId);

    /**
     * 为角色添加权限
     * @param roleAuthorityEntity 新增加的权限实体
     */
    public void addRoleAuthority(SwRoleAuthorityEntity roleAuthorityEntity);

    /**
     * 删除使用权限的所有角色
     * @param authorityKey 权限Key
     */
    public void deleteRolesByKey(String authorityKey);

    /**
     * 删除使用权限的某角色
     * @param authorityKey 权限Key
     * @param roleId 需要删除的角色Id
     */
    public void deleteRoleByKey(String authorityKey,Integer roleId);


}
