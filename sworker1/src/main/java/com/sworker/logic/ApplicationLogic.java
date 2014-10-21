package com.sworker.logic;

import com.sworker.dao.*;
import com.sworker.entity.SwApplicationEntity;
import com.sworker.entity.SwEnterpriseAppEntity;
import com.sworker.entity.SwUserAppEntity;
import com.sworker.model.TempEnterpriseAppEntity;
import com.sworker.model.TempUserAppEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

/**
 * Created by wangying on 2014/9/19.
 */
@Repository
@Component("applicationLogic")
public class ApplicationLogic implements IApplicationLogic {
    @Resource
    private IApplicationDao ApplicationDao;

    @Resource
    private IEnterpriseAppDao EnterpriseAppDao;

    @Resource
    private IEnterpriseDao EnterpriseDao;

    @Resource
    private IUserAppDao UserAppDao;

    @Resource
    private ITempEnterpriseDao TempEnterpriseDao;

    @Resource
    private ITempUserAppDao TempUserAppDao;
    /**
     * 获取平台上的所有应用
     * @param pageIndex 页码
     * @param pageSize  每页的条数
     * @return 应用实体列表
     */
    @Override
    @Transactional
    public List<SwApplicationEntity> getAllApps(Integer pageIndex, Integer pageSize) {
        ApplicationDao.searchAllApps();
        return ApplicationDao.findByPage((pageIndex - 1) * pageSize, pageSize);
    }

    /**
     * 获取某种应用的信息
     * @param appKey 应用Key
     * @return 应用实体组成的列表
     */
    @Override
    @Transactional
    public List<SwApplicationEntity> getAppInfoByKey(String appKey) {
        return ApplicationDao.searchAppByKey(appKey);
    }

    /**
     * 获取某具体版本应用信息
     * @param appId 应用Id
     * @return 应用实体组成的列表
     */
    @Override
    @Transactional
    public List<SwApplicationEntity> getAppInfoByID(Long appId) {
        return ApplicationDao.searchAppById(appId);
    }

    /**
     * 获取某应用被安装的企业数（不包括被试用的场景）
     * @param appKey 应用Key
     * @return 此应用被安装的企业数
     */
    @Override
    @Transactional
    public Integer getInstallationsByEnterprise(String appKey) {
        return EnterpriseAppDao.getInstallationsNumByKey(appKey, 1, 0);
    }

    /**
     * 获取某应用的某版本被安装的企业数（不包括被试用的场景）
     * @param appId 应用Id
     * @return 此应用被安装的企业数
     */
    @Override
    @Transactional
    public Integer getInstallationsByEnterprise(Long appId) {
        return EnterpriseAppDao.getInstallationsNumById(appId, 1, 0);
    }

    /**
     * 获取某个人应用被安装的用户数（不包括被试用的场景）
     * @param appKey 应用Key
     * @return 此种应用被安装的用户数
     */
    @Override
    @Transactional
    public Integer getInstallationsByUser(String appKey) {
        List<SwApplicationEntity> appInfo = getAppInfoByKey(appKey);
        if (appInfo != null) {
            if (appInfo.get(0).getAppscope() == 2) {
                return UserAppDao.getAppInstallNum(appKey);
            }
        }
        return null;
    }

    /**
     * 获取某个人应用的某版本被安装的用户数
     * @param appId 应用Id
     * @return 此种应用被安装的用户数
     */
    @Override
    @Transactional
    public Integer getInstallationsByUser(Long appId) {
        List<SwApplicationEntity> appInfo = getAppInfoByID(appId);
        if (appInfo != null) {
            if (appInfo.get(0).getAppscope() == 2) {
                return UserAppDao.getAppInstallNum(appId);
            }
        }
        return null;
    }

    /**
     * 平台发布应用
     * @param appEntity 应用实体
     */
    @Override
    @Transactional
    public void releaseApp(SwApplicationEntity appEntity) {
        ApplicationDao.create(appEntity);
    }

    /**
     * 更新应用信息
     * @param appEntity 应用实体
     */
    @Override
    @Transactional
    public void updateApp(SwApplicationEntity appEntity) {
        ApplicationDao.update(appEntity);
        if (appEntity.getStatus() == 0) {
            EnterpriseAppDao.deleteEnterprise(appEntity.getId());
        }
        if (appEntity.getAppscope() == 2) {
            UserAppDao.deleteEnterpriseAppRelative(appEntity.getId());
        }
    }

    /**
     * 向VIP分配某应用
     * @param appEntity 应用实体
     */
    @Override
    @Transactional
    public void allocateAppForVIP(SwApplicationEntity appEntity) {
        if (appEntity.getChargetype() == 2) {
            //todo 检索等级基本信息表
            Integer level = 1;
            List<Long> enterpriseList = EnterpriseDao.searchEnterpriseLevel(level);
            Long enterpriseNum = null;
            List<SwEnterpriseAppEntity> eEngtity = null;
            for(int i = 0;i<enterpriseList.size();i++){
                enterpriseNum = enterpriseList.get(i);
                eEngtity.add( EnterpriseAppDao.searchEnterpriseAppStatus(enterpriseNum, appEntity.getId()).get(0));
            }
            if(eEngtity == null){
                SwEnterpriseAppEntity enterpriseEntity = new SwEnterpriseAppEntity();
                enterpriseEntity.setEnterpriseid(enterpriseList.get(0));
                enterpriseEntity.setAppkey(appEntity.getAppkey());
                enterpriseEntity.setAppid(appEntity.getId());
                enterpriseEntity.setIstrial(0);
                if(appEntity.getUninstallable() == 1){
                    enterpriseEntity.setInstallstatus(0);
                    enterpriseEntity.setInstalldate(null);
                }else{
                    enterpriseEntity.setInstallstatus(1);
                    java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                    enterpriseEntity.setInstalldate(currentDate);
                }
                EnterpriseAppDao.insertEnterprise(enterpriseEntity);
            }
            // todo 不再继续下面处理，并返回此应用已存在于该企业中的信息
        }
        //todo 返回此应用不属于VIP专享类应用的提示信息。
    }

    /**
     * 获取某企业的所有应用
     * @param enterpriseId 企业Id
     * @param pageIndex 页码
     * @param pageSize 每页的条数
     * @return 企业应用实体列表
     */
    @Override
    @Transactional
    public List<TempEnterpriseAppEntity> getAppsByEnterprise(Long enterpriseId, Integer pageIndex, Integer pageSize) {
        List<SwEnterpriseAppEntity> enterpriseList = EnterpriseAppDao.searchEnterpriseApp(enterpriseId);
        List<SwApplicationEntity>applicationList = null;
        for(int i = 0;i<enterpriseList.size();i++) {
            applicationList.add(ApplicationDao.searchAppById(enterpriseList.get(i).getAppid()).get(0));
        }
        List<TempEnterpriseAppEntity> tempEnterprise = null;
        tempEnterprise.get(0).setId(enterpriseList.get(0).getId());
        tempEnterprise.get(0).setEnterpriseid(enterpriseList.get(0).getEnterpriseid());
        tempEnterprise.get(0).setAppkey(applicationList.get(0).getAppkey());
        tempEnterprise.get(0).setAppid(enterpriseList.get(0).getAppid());
        tempEnterprise.get(0).setAppname(applicationList.get(0).getAppname());
        tempEnterprise.get(0).setAppiconid(applicationList.get(0).getAppiconid());
        tempEnterprise.get(0).setApppath(applicationList.get(0).getApppath());
        tempEnterprise.get(0).setAppdesc(applicationList.get(0).getAppdesc());
        tempEnterprise.get(0).setAppscope(applicationList.get(0).getAppscope());
        tempEnterprise.get(0).setApptype(applicationList.get(0).getApptype());
        tempEnterprise.get(0).setChargetype(applicationList.get(0).getChargetype());
        tempEnterprise.get(0).setFee(applicationList.get(0).getFee());
        tempEnterprise.get(0).setIntegralvalue(applicationList.get(0).getIntegralvalue());
        tempEnterprise.get(0).setTrialperiod(applicationList.get(0).getTrialperiod());
        tempEnterprise.get(0).setUninstallable(applicationList.get(0).getUninstallable());
        tempEnterprise.get(0).setVersion(applicationList.get(0).getVersion());
        tempEnterprise.get(0).setInstallstatus(enterpriseList.get(0).getInstallstatus());
        tempEnterprise.get(0).setIstrial(enterpriseList.get(0).getIstrial());
        tempEnterprise.get(0).setInstalldate(enterpriseList.get(0).getInstalldate());
        tempEnterprise.get(0).setCategory(applicationList.get(0).getCategory());
        ApplicationDao.getOrder(enterpriseId);
        return TempEnterpriseDao.orderPage(enterpriseId,pageIndex,pageSize);
    }

    /**
     * 向企业分配应用（应用于新建企业账号时，即初次给企业分配应用的场景，即分配免费应用）
     * @param enterpriseId 企业Id
     */
    @Override
    @Transactional
    public void allocateAppsForEnterprise(Long enterpriseId) {
        List<SwApplicationEntity> appEntity = ApplicationDao.searchAppByChargeType(1,1);
        SwEnterpriseAppEntity enterpriseEntity = new SwEnterpriseAppEntity();
        enterpriseEntity.setEnterpriseid(enterpriseId);
        enterpriseEntity.setAppkey(appEntity.get(0).getAppkey());
        enterpriseEntity.setAppid(appEntity.get(0).getId());
        enterpriseEntity.setIstrial(0);
        if(appEntity.get(0).getUninstallable() == 1){
            enterpriseEntity.setInstallstatus(0);
            enterpriseEntity.setInstalldate(null);
        }else{
            enterpriseEntity.setInstallstatus(1);
            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            enterpriseEntity.setInstalldate(currentDate);
        }
        EnterpriseAppDao.insertEnterprise(enterpriseEntity);
    }

    /**
     * 向企业分配应用（应用于企业升级后的增加应用的场景）
     * @param enterpriseId 企业Id
     */
    @Override
    @Transactional
    public void allocateAppForVIPEnterprise(Long enterpriseId) {
        Integer level = EnterpriseDao.searchEnterpriseLevel(enterpriseId);
        //todo SQL25 检索等级基本信息表,返回该等级可以使用的应用Key组成的列表,排序按照等级Id升序。

    }

    /**
     * 向企业分配应用（应用于企业购买应用后的应用增加场景）
     * @param enterpriseId 企业Id
     * @param appEntity 应用实体
     */
    @Override
    @Transactional
    public void allocateAppForDealEnterprise(Long enterpriseId, SwApplicationEntity appEntity) {
        List<SwEnterpriseAppEntity> enterpriseList = EnterpriseAppDao.searchEnterpriseAppStatus(enterpriseId,appEntity.getId());
        if (enterpriseList == null) {
            SwEnterpriseAppEntity enterpriseEntity = new SwEnterpriseAppEntity();
            enterpriseEntity.setEnterpriseid(enterpriseId);
            enterpriseEntity.setAppkey(appEntity.getAppkey());
            enterpriseEntity.setAppid(appEntity.getId());
            enterpriseEntity.setIstrial(0);
            if(appEntity.getUninstallable() == 1){
                enterpriseEntity.setInstallstatus(0);
                enterpriseEntity.setInstalldate(null);
            }else{
                enterpriseEntity.setInstallstatus(1);
                java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                enterpriseEntity.setInstalldate(currentDate);
            }
            EnterpriseAppDao.insertEnterprise(enterpriseEntity);
        }else{
            //todo 返回此应用已存在于该企业中的信息。
        }
    }

    /**
     * 向企业分配应用（应用于企业用积分兑换应用后的应用增加场景）
     * @param enterpriseId 企业Id
     * @param appEntity 应用实体
     */
    @Override
    @Transactional
    public void allocateAppForExchangeEnterprise(Long enterpriseId, SwApplicationEntity appEntity) {
        List<SwEnterpriseAppEntity> enList = EnterpriseAppDao.searchEnterpriseAppStatus(enterpriseId,appEntity.getId());
        if (enList == null) {
            SwEnterpriseAppEntity enterpriseEntity = new SwEnterpriseAppEntity();
            enterpriseEntity.setEnterpriseid(enterpriseId);
            enterpriseEntity.setAppkey(appEntity.getAppkey());
            enterpriseEntity.setAppid(appEntity.getId());
            enterpriseEntity.setInstallstatus(0);
            enterpriseEntity.setIstrial(1);
            enterpriseEntity.setInstalldate(null);
            EnterpriseAppDao.insertEnterprise(enterpriseEntity);
        }else{
            //todo 返回此应用已存在于该企业中的信息。
        }
    }

    /**
     * 企业安装应用
     * @param enterpriseId 企业Id
     * @param appKey 应用Key
     * @param appId 应用Id
     */
    @Override
    @Transactional
    public void installAppForEnterprise(Long enterpriseId, String appKey, Long appId) {
        List<Long> appIdList = EnterpriseAppDao.searchEnterpriseAppById(enterpriseId,appKey,1);
        if (appIdList != null) {
            if (appIdList.get(0) == appId) {
                //todo 返回提示已安装此版本应用信息，不再继续下面处理。
            }else{
                EnterpriseAppDao.updateEnterpriseApp(enterpriseId,appIdList.get(0),0);
                Date nowDate = new Date(System.currentTimeMillis());
                EnterpriseAppDao.updateEnterpriseAppSec(enterpriseId,appId,1,nowDate);
                UserAppDao.updateEnterpriseAppVersion(enterpriseId, appIdList.get(0), appId);
            }
        }else{
            Date nowDate = new Date(System.currentTimeMillis());
            EnterpriseAppDao.updateEnterpriseAppSec(enterpriseId,appId,1,nowDate);
        }
    }

    /**
     * 企业卸载应用（并不删除与此应用有关的数据)
     * @param enterpriseId 企业Id
     * @param appId 应用Id
     */
    @Override
    @Transactional
    public void uninstallAppForEnterprise(Long enterpriseId, Long appId) {
        List<SwEnterpriseAppEntity> enterpriseAppEntityList = EnterpriseAppDao.searchEnterpriseAppStatus(enterpriseId,appId);
        if(enterpriseAppEntityList.get(0).getInstallstatus() == 0){
            //todo 返回提示未安装此应用信息；
        }
        else if(enterpriseAppEntityList.get(0).getIstrial() == 1){
            UserAppDao.deleteEnterpriseAppRelative(enterpriseId,appId);
        }
        else{
            List<SwApplicationEntity> appList = ApplicationDao.searchAppById(appId);
            if (appList.get(0).getUninstallable() == 1) {
                UserAppDao.deleteEnterpriseAppRelative(enterpriseId,appId);
                EnterpriseAppDao.updateEnterpriseApp(enterpriseId,appId,0);
            }else{
                //todo 返回不可以卸载的提示信息。
            }
        }
    }

    /**
     * 获取某用户的所有个人应用信息
     * @param enterpriseId 企业Id
     * @param userId 用户Id
     * @param pageIndex 页码
     * @param pageSize 每页的条数
     * @return 个人应用实体列表
     */
    @Override
    @Transactional
    public List<TempUserAppEntity> getAppsByUser(Long enterpriseId, Long userId, Integer pageIndex, Integer pageSize) {
        List<SwUserAppEntity> userAppList = UserAppDao.searchUseApp(enterpriseId,userId);
        List<SwApplicationEntity> applicationList = ApplicationDao.searchAppById(userAppList.get(0).getAppid());
        List<TempUserAppEntity> userAppEntityList = null;
        userAppEntityList.get(0).setId(userAppList.get(0).getId());
        userAppEntityList.get(0).setEnterpriseid(userAppList.get(0).getEnterpriseid());
        userAppEntityList.get(0).setUserid(userAppList.get(0).getUserid());
        userAppEntityList.get(0).setAppkey(applicationList.get(0).getAppkey());
        userAppEntityList.get(0).setAppid(userAppList.get(0).getAppid());
        userAppEntityList.get(0).setAppname(applicationList.get(0).getAppname());
        userAppEntityList.get(0).setAppiconid(applicationList.get(0).getAppiconid());
        userAppEntityList.get(0).setApppath(applicationList.get(0).getApppath());
        userAppEntityList.get(0).setAppdesc(applicationList.get(0).getAppdesc());
        userAppEntityList.get(0).setApptype(applicationList.get(0).getApptype());
        userAppEntityList.get(0).setChargetype(applicationList.get(0).getChargetype());
        userAppEntityList.get(0).setFee(applicationList.get(0).getFee());
        userAppEntityList.get(0).setIntegralvalue(applicationList.get(0).getIntegralvalue());
        userAppEntityList.get(0).setTrialperiod(applicationList.get(0).getTrialperiod());
        userAppEntityList.get(0).setUninstallable(applicationList.get(0).getUninstallable());
        userAppEntityList.get(0).setVersion(applicationList.get(0).getVersion());
        userAppEntityList.get(0).setCategory(applicationList.get(0).getCategory());
        return  TempUserAppDao.orderByPage(pageIndex,pageSize);
    }

    /**
     * 用户安装应用
     * @param enterpriseId 企业Id
     * @param userId 用户Id
     * @param appKey 应用Key
     * @param appId 应用Id
     */
    @Override
    @Transactional
    public void installAppByUser(Long enterpriseId, Long userId, String appKey, Long appId) {
        List<SwEnterpriseAppEntity> enterpriseList = EnterpriseAppDao.searchEnterpriseAppStatus(enterpriseId,appId);
        if (enterpriseList.size() == 0) {
            //todo 返回此应用不属于本企业，不可以安装的提示信息；
        }
        else if(enterpriseList.get(0).getInstallstatus() == 0){
            //todo 返回此应用未被被企业安装的提示信息；
        }
        else if(enterpriseList.get(0).getInstallstatus() == 1){
            SwUserAppEntity userAppEntity = new SwUserAppEntity();
            userAppEntity.setEnterpriseid(enterpriseId);
            userAppEntity.setUserid(userId);
            userAppEntity.setAppid(appId);
            userAppEntity.setAppkey(appKey);
            UserAppDao.insertEntity(userAppEntity);
        }
    }

    /**
     * 用户卸载应用（并不删除与此应用有关的数据）
     * @param enterpriseId 企业Id
     * @param userId 用户Id
     * @param appId 应用Id
     */
    @Override
    @Transactional
    public void uninstallAppByUser(Long enterpriseId, Long userId, Long appId) {
        List<SwEnterpriseAppEntity> enterpriseStatus = EnterpriseAppDao.searchEnterpriseAppStatus(enterpriseId,appId);
        if (enterpriseStatus.get(0).getIstrial() == 1) {
            UserAppDao.deleteEnterpriseAppRelative(enterpriseId,userId,appId);
        }
        else{
            List<SwApplicationEntity> info = ApplicationDao.searchAppById(appId);
            if (info.get(0).getUninstallable() == 1) {
                UserAppDao.deleteEnterpriseAppRelative(enterpriseId,userId,appId);
            }else{
                //todo 不可卸载的提示信息
            }
        }
    }

    /**
     * 根据应用名称获取某种应用的信息
     * @param appName 应用名称
     * @return 应用实体列表
     */
    @Override
    @Transactional
    public List<SwApplicationEntity> getAppInfoByName(String appName) {
        return ApplicationDao.getAppInfoByName(appName);
    }
}
