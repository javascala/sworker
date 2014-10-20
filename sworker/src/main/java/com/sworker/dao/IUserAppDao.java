package com.sworker.dao;

import com.sworker.entity.SwUserAppEntity;

import java.util.List;

/**
 * Created by wangying on 2014/9/18.
 */
public interface IUserAppDao {
    /**
     * 获取应用被安装数量
     * @param appKey 应用Key
     * @return 安装数量
     */
    public Integer getAppInstallNum(String appKey);

    /**
     * 获取应用被安装数量
     * @param appId 应用Id
     * @return 安装数量
     */
    public Integer getAppInstallNum(Long appId);

    /**
     * 检索某用户的应用
     * @param enterpriseId 企业id
     * @param userId 用户Id
     * @return 个人应用列表
     */
    public List<SwUserAppEntity> searchUseApp(Long enterpriseId,Long userId);

    /**
     * 插入用户实体
     * @param appEntity 用户实体
     * @return 用户实体
     */
    public void insertEntity(SwUserAppEntity appEntity);

    /**
     * 更新某企业的某应用版本
     * @param enterpriseId 企业id
     * @param appIdOld 旧的应用Id
     * @param appIdNew 新的应用Id
     */
    public void updateEnterpriseAppVersion(Long enterpriseId,Long appIdOld,Long appIdNew);

    /**
     * 删除所有用户与某应用的关系
     * @param appId 用户Id
     */
    public void deleteEnterpriseAppRelative(Long appId);

    /**
     * 删除所有用户与某应用的关系
     * @param enterpriseId 企业id
     * @param appId 应用Id
     */
    public void deleteEnterpriseAppRelative(Long enterpriseId,Long appId);

    /**
     * 删除所有用户与某应用的关系
     * @param enterpriseId 企业id
     * @param userId 用户Id
     * @param appId 应用Id
     */
    public void deleteEnterpriseAppRelative(Long enterpriseId,Long userId,Long appId);
}
