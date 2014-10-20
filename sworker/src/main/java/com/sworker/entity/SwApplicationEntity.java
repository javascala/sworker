package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by wangying on 2014/9/17.
 */
@Entity
@Table(name = "sw_application")
public class SwApplicationEntity implements Serializable {
    private long id;
    private String appkey;
    private String appname;
    private Long appiconid;
    private String apppath;
    private String appdesc;
    private Integer appscope;
    private Integer apptype;
    private Integer chargetype;
    private BigDecimal fee;
    private Integer integralvalue;
    private Integer trialperiod;
    private Integer uninstallable;
    private Integer status;
    private String version;
    private Integer category;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "APPKEY")
    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    @Basic
    @Column(name = "APPNAME")
    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    @Basic
    @Column(name = "APPICONID")
    public Long getAppiconid() {
        return appiconid;
    }

    public void setAppiconid(Long appiconid) {
        this.appiconid = appiconid;
    }

    @Basic
    @Column(name = "APPPATH")
    public String getApppath() {
        return apppath;
    }

    public void setApppath(String apppath) {
        this.apppath = apppath;
    }

    @Basic
    @Column(name = "APPDESC")
    public String getAppdesc() {
        return appdesc;
    }

    public void setAppdesc(String appdesc) {
        this.appdesc = appdesc;
    }

    @Basic
    @Column(name = "APPSCOPE")
    public Integer getAppscope() {
        return appscope;
    }

    public void setAppscope(Integer appscope) {
        this.appscope = appscope;
    }

    @Basic
    @Column(name = "APPTYPE")
    public Integer getApptype() {
        return apptype;
    }

    public void setApptype(Integer apptype) {
        this.apptype = apptype;
    }

    @Basic
    @Column(name = "CHARGETYPE")
    public Integer getChargetype() {
        return chargetype;
    }

    public void setChargetype(Integer chargetype) {
        this.chargetype = chargetype;
    }

    @Basic
    @Column(name = "FEE")
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Basic
    @Column(name = "INTEGRALVALUE")
    public Integer getIntegralvalue() {
        return integralvalue;
    }

    public void setIntegralvalue(Integer integralvalue) {
        this.integralvalue = integralvalue;
    }

    @Basic
    @Column(name = "TRIALPERIOD")
    public Integer getTrialperiod() {
        return trialperiod;
    }

    public void setTrialperiod(Integer trialperiod) {
        this.trialperiod = trialperiod;
    }

    @Basic
    @Column(name = "UNINSTALLABLE")
    public Integer getUninstallable() {
        return uninstallable;
    }

    public void setUninstallable(Integer uninstallable) {
        this.uninstallable = uninstallable;
    }

    @Basic
    @Column(name = "STATUS")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "VERSION")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Basic
    @Column(name = "CATEGORY")
    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwApplicationEntity that = (SwApplicationEntity) o;

        if (id != that.id) return false;
        if (appdesc != null ? !appdesc.equals(that.appdesc) : that.appdesc != null) return false;
        if (appiconid != null ? !appiconid.equals(that.appiconid) : that.appiconid != null) return false;
        if (appkey != null ? !appkey.equals(that.appkey) : that.appkey != null) return false;
        if (appname != null ? !appname.equals(that.appname) : that.appname != null) return false;
        if (apppath != null ? !apppath.equals(that.apppath) : that.apppath != null) return false;
        if (appscope != null ? !appscope.equals(that.appscope) : that.appscope != null) return false;
        if (apptype != null ? !apptype.equals(that.apptype) : that.apptype != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (chargetype != null ? !chargetype.equals(that.chargetype) : that.chargetype != null) return false;
        if (fee != null ? !fee.equals(that.fee) : that.fee != null) return false;
        if (integralvalue != null ? !integralvalue.equals(that.integralvalue) : that.integralvalue != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (trialperiod != null ? !trialperiod.equals(that.trialperiod) : that.trialperiod != null) return false;
        if (uninstallable != null ? !uninstallable.equals(that.uninstallable) : that.uninstallable != null)
            return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (appkey != null ? appkey.hashCode() : 0);
        result = 31 * result + (appname != null ? appname.hashCode() : 0);
        result = 31 * result + (appiconid != null ? appiconid.hashCode() : 0);
        result = 31 * result + (apppath != null ? apppath.hashCode() : 0);
        result = 31 * result + (appdesc != null ? appdesc.hashCode() : 0);
        result = 31 * result + (appscope != null ? appscope.hashCode() : 0);
        result = 31 * result + (apptype != null ? apptype.hashCode() : 0);
        result = 31 * result + (chargetype != null ? chargetype.hashCode() : 0);
        result = 31 * result + (fee != null ? fee.hashCode() : 0);
        result = 31 * result + (integralvalue != null ? integralvalue.hashCode() : 0);
        result = 31 * result + (trialperiod != null ? trialperiod.hashCode() : 0);
        result = 31 * result + (uninstallable != null ? uninstallable.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
