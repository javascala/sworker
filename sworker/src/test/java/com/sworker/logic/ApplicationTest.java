package com.sworker.logic;

import com.sworker.entity.SwApplicationEntity;
import com.sworker.entity.SwEnterpriseAppEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by wangying on 2014/9/25.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/applicationContext.xml")
public class ApplicationTest {
    @Resource
    private IApplicationLogic applicationLogic;

    /**
     * 获取平台上的所有应用
     */
    @Test
    public void getAllAppsTest() {
        Integer pageIndex = 2;
        Integer pageSize = 4;
        applicationLogic.getAllApps(pageIndex, pageSize);
    }

    /**
     * 获取某种应用的信息
     */
    @Test
    public void getAppInfoByKeyTest() {
        String appkey = "11";
        applicationLogic.getAppInfoByKey(appkey);
    }

    /**
     * 获取某具体版本应用信息
     */
    @Test
    public void getAppInfoByIDTest() {
        Long a = 2L;
        applicationLogic.getAppInfoByID(a);
    }

    /**
     * 获取某应用被安装的企业数（不包括被试用的场景）
     */
    @Test
    public void getInstallationsByEnterprise() {
        String a = "11";
        applicationLogic.getInstallationsByEnterprise(a);
    }

    /**
     * 获取某应用的某版本被安装的企业数（不包括被试用的场景）
     */
    @Test
    public void getInstallationsByEnterpriseTest() {
        Long a = 11L;
        applicationLogic.getInstallationsByEnterprise(a);
    }

    /**
     * 获取某个人应用被安装的用户数（不包括被试用的场景）
     */
    @Test
    public void getInstallationsByUserTest() {
        applicationLogic.getInstallationsByUser("11");
    }

    /**
     * 获取某个人应用的某版本被安装的用户数
     */
    @Test
    public void getInstallationsByUserTest2() {
        applicationLogic.getInstallationsByUser(11L);
    }

    /**
     * 平台发布应用
     */
    @Test
    public void releaseAppTest() {
        SwApplicationEntity appEntity = new SwApplicationEntity();
        appEntity.setAppkey("");
        appEntity.setCategory(2);
        appEntity.setStatus(1);
        appEntity.setVersion("1");
        appEntity.setAppdesc("");
        appEntity.setAppiconid(1L);
        appEntity.setAppname("");
        appEntity.setApppath("");
        appEntity.setAppscope(1);
        appEntity.setApptype(0);
        appEntity.setChargetype(1);
        appEntity.setFee(BigDecimal.valueOf(33));
        appEntity.setIntegralvalue(2);
        appEntity.setTrialperiod(1);
        appEntity.setUninstallable(1);
        applicationLogic.releaseApp(appEntity);
    }

    /**
     * 更新应用信息
     */
    @Test
    public void updateAppTest() {
        SwApplicationEntity appEntity = new SwApplicationEntity();
        appEntity.setAppkey("sw");
        appEntity.setCategory(2);
        appEntity.setStatus(1);
        appEntity.setVersion("1");
        appEntity.setAppdesc("");
        appEntity.setAppiconid(1L);
        appEntity.setAppname("");
        appEntity.setApppath("");
        appEntity.setAppscope(1);
        appEntity.setApptype(0);
        appEntity.setChargetype(1);
        appEntity.setFee(BigDecimal.valueOf(33));
        appEntity.setIntegralvalue(2);
        appEntity.setTrialperiod(1);
        appEntity.setUninstallable(1);
        applicationLogic.updateApp(appEntity);
    }

    /**
     * 向VIP分配某应用
     */
    @Test
    public void allocateAppForVIPTest() {
        SwApplicationEntity appEntity = new SwApplicationEntity();
        appEntity.setAppkey("sw");
        appEntity.setCategory(2);
        appEntity.setStatus(1);
        appEntity.setVersion("1");
        appEntity.setAppdesc("");
        appEntity.setAppiconid(1L);
        appEntity.setAppname("");
        appEntity.setApppath("");
        appEntity.setAppscope(1);
        appEntity.setApptype(0);
        appEntity.setChargetype(1);
        appEntity.setFee(BigDecimal.valueOf(33));
        appEntity.setIntegralvalue(2);
        appEntity.setTrialperiod(1);
        appEntity.setUninstallable(1);
        applicationLogic.allocateAppForVIP(appEntity);
    }

    /**
     * 获取某企业的所有应用
     */
    @Test
    public void getAppsByEnterpriseTest(){
        applicationLogic.getAppsByEnterprise(12L,2,4);
    }

    /**
     * 向企业分配应用（应用于新建企业账号时，即初次给企业分配应用的场景，即分配免费应用）
     */
    @Test
    public void allocateAppsForEnterpriseTest(){
        applicationLogic.allocateAppsForEnterprise(2L);
    }

    /**
     * 向企业分配应用（应用于企业升级后的增加应用的场景）
     */
    @Test
    public void allocateAppForVIPEnterpriseTest(){
        applicationLogic.allocateAppForVIPEnterprise(3L);
    }

    /**
     * 向企业分配应用（应用于企业购买应用后的应用增加场景）
     */
    @Test
    public void allocateAppForDealEnterpriseTest(){
        SwApplicationEntity appEntity = new SwApplicationEntity();
        appEntity.setAppkey("sw");
        appEntity.setCategory(2);
        appEntity.setStatus(1);
        appEntity.setVersion("1");
        appEntity.setAppdesc("");
        appEntity.setAppiconid(1L);
        appEntity.setAppname("");
        appEntity.setApppath("");
        appEntity.setAppscope(1);
        appEntity.setApptype(0);
        appEntity.setChargetype(1);
        appEntity.setFee(BigDecimal.valueOf(33));
        appEntity.setIntegralvalue(2);
        appEntity.setTrialperiod(1);
        appEntity.setUninstallable(1);
        applicationLogic.allocateAppForDealEnterprise(2L,appEntity);
    }

    /**
     * 向企业分配应用（应用于企业用积分兑换应用后的应用增加场景）
     */
    @Test
    public void allocateAppForExchangeEnterpriseTest(){
        SwApplicationEntity appEntity = new SwApplicationEntity();
        appEntity.setAppkey("sw");
        appEntity.setCategory(2);
        appEntity.setStatus(1);
        appEntity.setVersion("1");
        appEntity.setAppdesc("");
        appEntity.setAppiconid(1L);
        appEntity.setAppname("");
        appEntity.setApppath("");
        appEntity.setAppscope(1);
        appEntity.setApptype(0);
        appEntity.setChargetype(1);
        appEntity.setFee(BigDecimal.valueOf(33));
        appEntity.setIntegralvalue(2);
        appEntity.setTrialperiod(1);
        appEntity.setUninstallable(1);
        applicationLogic.allocateAppForExchangeEnterprise(4L,appEntity);
    }

    /**
     * 企业安装应用
     */
    @Test
    public void installAppForEnterprise(){
        applicationLogic.installAppForEnterprise(3L,"sw",4L);
    }

    /**
     * 企业卸载应用（并不删除与此应用有关的数据)
     */
    @Test
    public void uninstallAppForEnterpriseTest(){
        applicationLogic.uninstallAppForEnterprise(2L,2L);
    }

    /**
     * 获取某用户的所有个人应用信息
     */
    @Test
    public void getAppsByUserTest(){
        applicationLogic.getAppsByUser(2L,3L,2,4);
    }

    /**
     *用户安装应用
     */
    @Test
    public void installAppByUserTest(){
        applicationLogic.installAppByUser(2L,3L,"",2L);
    }

    /**
     * 用户卸载应用（并不删除与此应用有关的数据）
     */
    @Test
    public void uninstallAppByUserTest(){
        applicationLogic.uninstallAppByUser(1L,2L,3L);
    }

    /**
     * 根据应用名称获取某种应用的信息
     */
    @Test
    public void getAppInfoByNameTest(){
        applicationLogic.getAppInfoByName("sw");
    }
}

