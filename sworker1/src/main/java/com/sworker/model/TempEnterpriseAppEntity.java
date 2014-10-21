package com.sworker.model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by wangying on 2014/9/24.
 */
public class TempEnterpriseAppEntity {
    //    id
    private long id;
    //    企业Id
    private Long enterpriseid;
    //    应用Key
    private String appkey;
    //    应用Id
    private Long appid;
    //    应用名称
    private String appname;
    //    应用图标Id
    private Long appiconid;
    //    应用路径
    private String apppath;
    //    应用描述
    private String appdesc;
    //    应用范畴
    private Integer appscope;
    //    应用类型
    private Integer apptype;
    //    收费类型
    private Integer chargetype;
    //    金额
    private BigDecimal fee;
    //    积分值
    private Integer integralvalue;
    //    试用期限
    private Integer trialperiod;
    //    可否卸载
    private Integer uninstallable;
    //    版本
    private String version;
    //    安装状态
    private Integer installstatus;
    //    是否试用
    private Integer istrial;
    //    安装日期
    private Date installdate;
    //    分类
    private Integer category;


    public TempEnterpriseAppEntity() {

    }

    public TempEnterpriseAppEntity(long id, Long enterpriseid, String appkey, Long appid, String appname,
                                   Long appiconid, String apppath, String appdesc, Integer appscope, Integer apptype,
                                   Integer chargetype, BigDecimal fee, Integer integralvalue, Integer trialperiod,
                                   Integer uninstallable, String version, Integer installstatus, Integer istrial,
                                   Date installdate, Integer category) {
        this.id = id;
        this.enterpriseid = enterpriseid;
        this.appkey = appkey;
        this.appid = appid;
        this.appname = appname;
        this.appiconid = appiconid;
        this.apppath = apppath;
        this.appdesc = appdesc;
        this.appscope = appscope;
        this.apptype = apptype;
        this.chargetype = chargetype;
        this.fee = fee;
        this.integralvalue = integralvalue;
        this.trialperiod = trialperiod;
        this.uninstallable = uninstallable;
        this.version = version;
        this.installstatus = installstatus;
        this.istrial = istrial;
        this.installdate = installdate;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getEnterpriseid() {
        return enterpriseid;
    }

    public void setEnterpriseid(Long enterpriseid) {
        this.enterpriseid = enterpriseid;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public Long getAppid() {
        return appid;
    }

    public void setAppid(Long appid) {
        this.appid = appid;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public Long getAppiconid() {
        return appiconid;
    }

    public void setAppiconid(Long appiconid) {
        this.appiconid = appiconid;
    }

    public String getApppath() {
        return apppath;
    }

    public void setApppath(String apppath) {
        this.apppath = apppath;
    }

    public String getAppdesc() {
        return appdesc;
    }

    public void setAppdesc(String appdesc) {
        this.appdesc = appdesc;
    }

    public Integer getAppscope() {
        return appscope;
    }

    public void setAppscope(Integer appscope) {
        this.appscope = appscope;
    }

    public Integer getApptype() {
        return apptype;
    }

    public void setApptype(Integer apptype) {
        this.apptype = apptype;
    }

    public Integer getChargetype() {
        return chargetype;
    }

    public void setChargetype(Integer chargetype) {
        this.chargetype = chargetype;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Integer getIntegralvalue() {
        return integralvalue;
    }

    public void setIntegralvalue(Integer integralvalue) {
        this.integralvalue = integralvalue;
    }

    public Integer getTrialperiod() {
        return trialperiod;
    }

    public void setTrialperiod(Integer trialperiod) {
        this.trialperiod = trialperiod;
    }

    public Integer getUninstallable() {
        return uninstallable;
    }

    public void setUninstallable(Integer uninstallable) {
        this.uninstallable = uninstallable;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getInstallstatus() {
        return installstatus;
    }

    public void setInstallstatus(Integer installstatus) {
        this.installstatus = installstatus;
    }

    public Integer getIstrial() {
        return istrial;
    }

    public void setIstrial(Integer istrial) {
        this.istrial = istrial;
    }

    public Date getInstalldate() {
        return installdate;
    }

    public void setInstalldate(Date installdate) {
        this.installdate = installdate;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}