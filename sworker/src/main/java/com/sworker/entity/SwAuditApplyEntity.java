package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by cuijh on 2014/9/9.
 */
@Entity
@Table(name = "sw_audit_apply")
public class SwAuditApplyEntity implements Serializable {
    private long id;
    private Long enterpriseid;
    private Long objectid;
    private String appkey;
    private Long ownerid;
    private Long proposerid;
    private Timestamp applyfordate;
    private String reason;
    private Long auditorid;
    private Integer status;
    private Integer result;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ID")
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
    @Column(name = "OBJECTID")
    public Long getObjectid() {
        return objectid;
    }

    public void setObjectid(Long objectid) {
        this.objectid = objectid;
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
    @Column(name = "OWNERID")
    public Long getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(Long ownerid) {
        this.ownerid = ownerid;
    }

    @Basic
    @Column(name = "PROPOSERID")
    public Long getProposerid() {
        return proposerid;
    }

    public void setProposerid(Long proposerid) {
        this.proposerid = proposerid;
    }

    @Basic
    @Column(name = "APPLYFORDATE")
    public Timestamp getApplyfordate() {
        return applyfordate;
    }

    public void setApplyfordate(Timestamp applyfordate) {
        this.applyfordate = applyfordate;
    }

    @Basic
    @Column(name = "REASON")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Basic
    @Column(name = "AUDITORID")
    public Long getAuditorid() {
        return auditorid;
    }

    public void setAuditorid(Long auditorid) {
        this.auditorid = auditorid;
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
    @Column(name = "RESULT")
    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwAuditApplyEntity that = (SwAuditApplyEntity) o;

        if (id != that.id) return false;
        if (appkey != null ? !appkey.equals(that.appkey) : that.appkey != null) return false;
        if (applyfordate != null ? !applyfordate.equals(that.applyfordate) : that.applyfordate != null) return false;
        if (auditorid != null ? !auditorid.equals(that.auditorid) : that.auditorid != null) return false;
        if (enterpriseid != null ? !enterpriseid.equals(that.enterpriseid) : that.enterpriseid != null) return false;
        if (objectid != null ? !objectid.equals(that.objectid) : that.objectid != null) return false;
        if (ownerid != null ? !ownerid.equals(that.ownerid) : that.ownerid != null) return false;
        if (proposerid != null ? !proposerid.equals(that.proposerid) : that.proposerid != null) return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;
        if (result != null ? !result.equals(that.result) : that.result != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = (int) (id ^ (id >>> 32));
        result1 = 31 * result1 + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result1 = 31 * result1 + (objectid != null ? objectid.hashCode() : 0);
        result1 = 31 * result1 + (appkey != null ? appkey.hashCode() : 0);
        result1 = 31 * result1 + (ownerid != null ? ownerid.hashCode() : 0);
        result1 = 31 * result1 + (proposerid != null ? proposerid.hashCode() : 0);
        result1 = 31 * result1 + (applyfordate != null ? applyfordate.hashCode() : 0);
        result1 = 31 * result1 + (reason != null ? reason.hashCode() : 0);
        result1 = 31 * result1 + (auditorid != null ? auditorid.hashCode() : 0);
        result1 = 31 * result1 + (status != null ? status.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }
}