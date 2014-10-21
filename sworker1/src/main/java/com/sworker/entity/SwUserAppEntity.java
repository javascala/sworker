package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wangying on 2014/9/17.
 */
@Entity
@Table(name = "sw_user_app")
public class SwUserAppEntity implements Serializable {
    private long id;
    private Long enterpriseid;
    private Long userid;
    private String appkey;
    private Long appid;

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
    @Column(name = "USERID")
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwUserAppEntity that = (SwUserAppEntity) o;

        if (id != that.id) return false;
        if (appid != null ? !appid.equals(that.appid) : that.appid != null) return false;
        if (appkey != null ? !appkey.equals(that.appkey) : that.appkey != null) return false;
        if (enterpriseid != null ? !enterpriseid.equals(that.enterpriseid) : that.enterpriseid != null) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (appkey != null ? appkey.hashCode() : 0);
        result = 31 * result + (appid != null ? appid.hashCode() : 0);
        return result;
    }
}
