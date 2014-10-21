package com.sworker.dao;

import com.sworker.entity.SwAuthorityEntity;

import java.util.List;

/**
 * Created by cuijh on 2014/8/25.
 */
public interface IAuthority {

    /**
     * 检索所有权限
     * @return 权限实体组成的列表
     */
    public List<SwAuthorityEntity> allAuthorityEntities();

    /**
     * 检索符合条件的所有权限
     * @param authorityKey  权限Key
     * @param authotityName 权限名称
     * @return 符合条件的权限组成的列表
     */
    public SwAuthorityEntity getAuthorityEntity(String authorityKey, String authotityName);

    /**
     * 添加权限
     * @param authorityEntity 新增加的权限实体
     */
    public void addAuthority(SwAuthorityEntity authorityEntity);

    /**
     * 更新权限
     * @param authorityEntity 更新成的权限实体
     * @return 更新后的权限实体
     */
    public SwAuthorityEntity updateAuthority(SwAuthorityEntity authorityEntity);

    /**
     * 删除权限
     * @param authorityKey 需要被删除的权限Key
     */
    public void deleteByKey(String authorityKey);

    /**
     * 查询某账号对某资源有权限的操作类型
     * @param userId    账号id
     * @param sourceKey 资源Key
     * @return 有权操作的类型列表
     */
    public List<Long> operateTypes(int userId, String sourceKey, String operateType);

}
