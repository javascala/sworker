package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by wangying on 2014/9/17.
 */
@Entity
@Table(name = "sw_enterprise_app")
public class SwEnterpriseAppEntity implements Serializable {
    private long id;
    private Long enterpriseid;
    private String appkey;
    private Long appid;
    private Integer installstatus;
    private Integer istrial;
    private Date installdate;

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
    @Column(name = "ENTERPRISEID")
    public Long getEnterpriseid() {
        return enterpriseid;
    }

    public void setEnterpriseid(Long enterpriseid) {
        this.enterpriseid = enterpriseid;
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
    @Column(name = "APPID")
    public Long getAppid() {
        return appid;
    }

    public void setAppid(Long appid) {
        this.appid = appid;
    }

    @Basic
    @Column(name = "INSTALLSTATUS")
    public Integer getInstallstatus() {
        return installstatus;
    }

    public void setInstallstatus(Integer installstatus) {
        this.installstatus = installstatus;
    }

    @Basic
    @Column(name = "ISTRIAL")
    public Integer getIstrial() {
        return istrial;
    }

    public void setIstrial(Integer istrial) {
        this.istrial = istrial;
    }

    @Basic
    @Column(name = "INSTALLDATE")
    public Date getInstalldate() {
        return installdate;
    }

    public void setInstalldate(Date installdate) {
        this.installdate = installdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwEnterpriseAppEntity that = (SwEnterpriseAppEntity) o;

        if (id != that.id) return false;
        if (appid != null ? !appid.equals(that.appid) : that.appid != null) return false;
        if (appkey != null ? !appkey.equals(that.appkey) : that.appkey != null) return false;
        if (enterpriseid != null ? !enterpriseid.equals(that.enterpriseid) : that.enterpriseid != null) return false;
        if (installdate != null ? !installdate.equals(that.installdate) : that.installdate != null) return false;
        if (installstatus != null ? !installstatus.equals(that.installstatus) : that.installstatus != null)
            return false;
        if (istrial != null ? !istrial.equals(that.istrial) : that.istrial != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result = 31 * result + (appkey != null ? appkey.hashCode() : 0);
        result = 31 * result + (appid != null ? appid.hashCode() : 0);
        result = 31 * result + (installstatus != null ? installstatus.hashCode() : 0);
        result = 31 * result + (istrial != null ? istrial.hashCode() : 0);
        result = 31 * result + (installdate != null ? installdate.hashCode() : 0);
        return result;
    }
}
