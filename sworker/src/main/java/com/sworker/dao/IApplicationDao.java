package com.sworker.dao;

import com.sworker.dao.common.IOperations;
import com.sworker.entity.SwApplicationEntity;
import com.sworker.model.TempEnterpriseAppEntity;

import java.util.List;

/**
 * Created by wangying on 2014/9/17.
 */
public interface IApplicationDao extends IOperations<SwApplicationEntity> {
    /**
     * 对应用表进行全检索
     * @return 应用实体列表
     */
    public List<SwApplicationEntity> searchAllApps();

    /**
     * 根据应用Key获取某种应用基本信息
     * @param appKey 应用Key
     * @return 应用实体列表
     */
    public List<SwApplicationEntity> searchAppByKey(String appKey);

    /**
     * 根据收费类型获取应用信息
     * @param appId id
     * @return 应用实体列表
     */
    public List<SwApplicationEntity> searchAppById(long appId);

    /**
     * 根据收费类型获取应用信息
     * @param chargeType 收费类型
     * @param status 状态
     * @return 应用实体列表
     */
    public List<SwApplicationEntity> searchAppByChargeType(Integer chargeType,Integer status);

    /**
     * 根据应用名称获取某种应用基本信息
     * @param appName 应用Name
     * @return 应用实体列表
     */
    public List<SwApplicationEntity> getAppInfoByName(String appName);

    public List<TempEnterpriseAppEntity> getOrder(Long enterpriseId);

}
