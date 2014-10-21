package com.sworker.logic;

import com.sworker.entity.SwApplicationEntity;
import com.sworker.model.TempEnterpriseAppEntity;
import com.sworker.model.TempUserAppEntity;

import java.util.List;

/**
 * Created by wangying on 2014/9/19.
 */
public interface IApplicationLogic {
    /**
     * 获取平台上的所有应用
     * @param pageIndex 页码
     * @param pageSize 每页的条数
     * @return 应用实体列表
     */
    public List<SwApplicationEntity> getAllApps(Integer pageIndex,Integer pageSize);

    /**
     * 获取某种应用的信息
     * @param appKey 应用Key
     * @return 应用实体组成的列表
     */
    public List<SwApplicationEntity> getAppInfoByKey(String appKey);

    /**
     * 获取某具体版本应用信息
     * @param appId 应用Id
     * @return 应用实体组成的列表
     */
    public List<SwApplicationEntity> getAppInfoByID(Long appId);

    /**
     * 获取某应用被安装的企业数（不包括被试用的场景）
     * @param appKey 应用Key
     * @return 此应用被安装的企业数
     */
    public Integer getInstallationsByEnterprise(String appKey);

    /**
     * 获取某应用的某版本被安装的企业数（不包括被试用的场景）
     * @param appId 应用Id
     * @return 此应用被安装的企业数
     */
    public Integer getInstallationsByEnterprise(Long appId);

    /**
     * 获取某个人应用被安装的用户数（不包括被试用的场景）
     * @param appKey 应用Key
     * @return 此种应用被安装的用户数
     */
    public Integer getInstallationsByUser(String appKey);

    /**
     * 获取某个人应用的某版本被安装的用户数
     * @param appId 应用Id
     * @return 此种应用被安装的用户数
     */
    public Integer getInstallationsByUser(Long appId);

    /**
     * 平台发布应用
     * @param appEntity 应用实体
     */
    public void releaseApp(SwApplicationEntity appEntity);

    /**
     * 更新应用信息
     * @param appEntity 应用实体
     */
    public void updateApp (SwApplicationEntity appEntity);

    /**
     * 向VIP分配某应用
     * @param appEntity 应用实体
     */
    public void allocateAppForVIP(SwApplicationEntity appEntity);

    /**
     * 获取某企业的所有应用
     * @param enterpriseId 企业Id
     * @param pageIndex 页码
     * @param pageSize 每页的条数
     * @return 企业应用实体列表
     */
    public List<TempEnterpriseAppEntity> getAppsByEnterprise(Long enterpriseId,Integer pageIndex,Integer pageSize);

    /**
     * 向企业分配应用（应用于新建企业账号时，即初次给企业分配应用的场景，即分配免费应用）
     * @param enterpriseId 企业Id
     */
    public void allocateAppsForEnterprise(Long enterpriseId);

    /**
     * 向企业分配应用（应用于企业升级后的增加应用的场景）
     * @param enterpriseId 企业Id
     */
    public void allocateAppForVIPEnterprise(Long enterpriseId);

    /**
     * 向企业分配应用（应用于企业购买应用后的应用增加场景）
     * @param enterpriseId 企业Id
     * @param appEntity 应用实体
     */
    public void allocateAppForDealEnterprise(Long enterpriseId,SwApplicationEntity appEntity);

    /**
     * 向企业分配应用（应用于企业用积分兑换应用后的应用增加场景）
     * @param enterpriseId 企业Id
     * @param appEntity 应用实体
     */
    public void allocateAppForExchangeEnterprise(Long enterpriseId,SwApplicationEntity appEntity);

    /**
     * 企业安装应用
     * @param enterpriseId 企业Id
     * @param appKey 应用Key
     * @param appId 应用Id
     */
    public void installAppForEnterprise(Long enterpriseId,String appKey,Long appId);

    /**
     * 企业卸载应用（并不删除与此应用有关的数据)
     * @param enterpriseId 企业Id
     * @param appId 应用Id
     */
    public void uninstallAppForEnterprise(Long enterpriseId,Long appId);

    /**
     * 获取某用户的所有个人应用信息
     * @param enterpriseId 企业Id
     * @param userId 用户Id
     * @param pageIndex 页码
     * @param pageSize 每页的条数
     * @return 个人应用实体列表
     */
    public List<TempUserAppEntity> getAppsByUser(Long enterpriseId,Long userId,Integer pageIndex,Integer pageSize);

    /**
     * 用户安装应用
     * @param enterpriseId 企业Id
     * @param userId 用户Id
     * @param appKey 应用Key
     * @param appId 应用Id
     */
    public void installAppByUser(Long enterpriseId,Long userId,String appKey,Long appId);

    /**
     * 用户卸载应用（并不删除与此应用有关的数据）
     * @param enterpriseId 企业Id
     * @param userId 用户Id
     * @param appId 应用Id
     */
    public void uninstallAppByUser(Long enterpriseId,Long userId,Long appId);

    /**
     * 根据应用名称获取某种应用的信息
     * @param appName 应用名称
     * @return 应用实体列表
     */
    public List<SwApplicationEntity> getAppInfoByName(String appName);
}
