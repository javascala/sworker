package com.sworker.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by zhangjin on 2014/9/4.
 */
@Entity
@Table(name = "sw_freeze")
public class SwFreezeEntity {
    private BigInteger id;
    private BigInteger enterpriseid;
    private BigInteger objectid;
    private BigInteger ownerid;
    private BigInteger userid;
    private BigInteger auditorid;
    private BigInteger appid;
    private String grouptypeid;
    private String freezereason;
    private Timestamp freezedate;
    private String auditstatus;
    private Long test;

    @Id
    @Column(name = "ID")
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ENTERPRISEID")
    public BigInteger getEnterpriseid() {
        return enterpriseid;
    }

    public void setEnterpriseid(BigInteger enterpriseid) {
        this.enterpriseid = enterpriseid;
    }

    @Basic
    @Column(name = "OBJECTID")
    public BigInteger getObjectid() {
        return objectid;
    }

    public void setObjectid(BigInteger objectid) {
        this.objectid = objectid;
    }

    @Basic
    @Column(name = "OWNERID")
    public BigInteger getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(BigInteger ownerid) {
        this.ownerid = ownerid;
    }

    @Basic
    @Column(name = "USERID")
    public BigInteger getUserid() {
        return userid;
    }

    public void setUserid(BigInteger userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "AUDITORID")
    public BigInteger getAuditorid() {
        return auditorid;
    }

    public void setAuditorid(BigInteger auditorid) {
        this.auditorid = auditorid;
    }

    @Basic
    @Column(name = "APPID")
    public BigInteger getAppid() {
        return appid;
    }

    public void setAppid(BigInteger appid) {
        this.appid = appid;
    }

    @Basic
    @Column(name = "GROUPTYPEID")
    public String getGrouptypeid() {
        return grouptypeid;
    }

    public void setGrouptypeid(String grouptypeid) {
        this.grouptypeid = grouptypeid;
    }

    @Basic
    @Column(name = "FREEZEREASON")
    public String getFreezereason() {
        return freezereason;
    }

    public void setFreezereason(String freezereason) {
        this.freezereason = freezereason;
    }

    @Basic
    @Column(name = "FREEZEDATE")
    public Timestamp getFreezedate() {
        return freezedate;
    }

    public void setFreezedate(Timestamp freezedate) {
        this.freezedate = freezedate;
    }

    @Basic
    @Column(name = "AUDITSTATUS")
    public String getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(String auditstatus) {
        this.auditstatus = auditstatus;
    }

    @Basic
    @Column(name = "test")
    public Long getTest() {
        return test;
    }

    public void setTest(Long test) {
        this.test = test;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwFreezeEntity that = (SwFreezeEntity) o;

        if (appid != null ? !appid.equals(that.appid) : that.appid != null) return false;
        if (auditorid != null ? !auditorid.equals(that.auditorid) : that.auditorid != null) return false;
        if (auditstatus != null ? !auditstatus.equals(that.auditstatus) : that.auditstatus != null) return false;
        if (enterpriseid != null ? !enterpriseid.equals(that.enterpriseid) : that.enterpriseid != null) return false;
        if (freezedate != null ? !freezedate.equals(that.freezedate) : that.freezedate != null) return false;
        if (freezereason != null ? !freezereason.equals(that.freezereason) : that.freezereason != null) return false;
        if (grouptypeid != null ? !grouptypeid.equals(that.grouptypeid) : that.grouptypeid != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (objectid != null ? !objectid.equals(that.objectid) : that.objectid != null) return false;
        if (ownerid != null ? !ownerid.equals(that.ownerid) : that.ownerid != null) return false;
        if (test != null ? !test.equals(that.test) : that.test != null) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result = 31 * result + (objectid != null ? objectid.hashCode() : 0);
        result = 31 * result + (ownerid != null ? ownerid.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (auditorid != null ? auditorid.hashCode() : 0);
        result = 31 * result + (appid != null ? appid.hashCode() : 0);
        result = 31 * result + (grouptypeid != null ? grouptypeid.hashCode() : 0);
        result = 31 * result + (freezereason != null ? freezereason.hashCode() : 0);
        result = 31 * result + (freezedate != null ? freezedate.hashCode() : 0);
        result = 31 * result + (auditstatus != null ? auditstatus.hashCode() : 0);
        result = 31 * result + (test != null ? test.hashCode() : 0);
        return result;
    }
}
