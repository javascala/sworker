package com.sworker.dao;

import com.sworker.entity.SwEnterpriseAppEntity;

import java.sql.Date;
import java.util.List;

/**
 * Created by wangying on 2014/9/17.
 */
public interface IEnterpriseAppDao {

    /**
     * 获取应用被安装数量
     * @param appKey 应用Key
     * @param installStatus 安装状态
     * @param isTrial 是否试用
     * @return 数量
     */
    public Integer getInstallationsNumByKey(String appKey,Integer installStatus,Integer isTrial);

    /**
     * 获取应用被安装数量
     * @param appId 应用Id
     * @param installStatus 安装状态
     * @param isTrial 是否试用
     * @return 数量
     */
    public Integer getInstallationsNumById(Long appId,Integer installStatus,Integer isTrial);

    /**
     * 检索某企业内的应用
     * @param enterpriseId 企业Id
     * @return 企业应用列表
     */
    public List<SwEnterpriseAppEntity> searchEnterpriseApp(Long enterpriseId);

    /**
     * 检索企业内某种应用
     * @param enterpriseId 企业Id
     * @param appKey 应用Key
     * @param installStatus 安装状态
     * @return appId列表
     */
    public List<Long> searchEnterpriseAppById(Long enterpriseId,String appKey,Integer installStatus);

    /**
     * 检索企业内某应用的状况
     * @param enterpriseId 企业Id
     * @param appId 企业Id
     * @return 临时企业应用实体列表
     */
    public List<SwEnterpriseAppEntity> searchEnterpriseAppStatus(Long enterpriseId,Long appId);

    /**
     * 删除所有企业与某应用的关系
     * @param appId 企业Id
     */
    public void deleteEnterprise(Long appId);

    /**
     * 企业新增可使用的应用
     * @param enterpriseAppEntity 企业应用实体
     */
    public void insertEnterprise(SwEnterpriseAppEntity enterpriseAppEntity);

    /**
     * 更新企业应用表
     * @param enterpriseId 企业Id
     * @param appId 企业Id
     * @param installStatus 安装状态
     */
    public void updateEnterpriseApp(Long enterpriseId,Long appId,Integer installStatus);

    /**
     * 更新企业应用表
     * @param enterpriseId 企业Id
     * @param appId 企业Id
     * @param installStatus 安装状态
     * @param installDate 安装日期
     */
    public void updateEnterpriseAppSec(Long enterpriseId,Long appId,Integer installStatus,Date installDate);
}
