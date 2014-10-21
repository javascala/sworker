package com.sworker.entity;

import javax.persistence.*;

import java.io.Serializable;

/**
 * Created by zhangjin on 2014/9/5.
 */
@Entity
@Table(name = "sw_group_statistics")
public class SwGroupStatisticsEntity implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private Long enterpriseid;
    private Long grouptype;
    private Long groupid;
    private Long memberid;
    private String appkey;
    private Integer counter;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    @Column(name = "GROUPTYPE")
    public Long getGrouptype() {
        return grouptype;
    }

    public void setGrouptype(Long grouptype) {
        this.grouptype = grouptype;
    }

    @Basic
    @Column(name = "GROUPID")
    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    @Basic
    @Column(name = "MEMBERID")
    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
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
    @Column(name = "COUNTER")
    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwGroupStatisticsEntity that = (SwGroupStatisticsEntity) o;

        if (appkey != null ? !appkey.equals(that.appkey) : that.appkey != null) return false;
        if (counter != null ? !counter.equals(that.counter) : that.counter != null) return false;
        if (enterpriseid != null ? !enterpriseid.equals(that.enterpriseid) : that.enterpriseid != null) return false;
        if (groupid != null ? !groupid.equals(that.groupid) : that.groupid != null) return false;
        if (grouptype != null ? !grouptype.equals(that.grouptype) : that.grouptype != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (memberid != null ? !memberid.equals(that.memberid) : that.memberid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result = 31 * result + (grouptype != null ? grouptype.hashCode() : 0);
        result = 31 * result + (groupid != null ? groupid.hashCode() : 0);
        result = 31 * result + (memberid != null ? memberid.hashCode() : 0);
        result = 31 * result + (appkey != null ? appkey.hashCode() : 0);
        result = 31 * result + (counter != null ? counter.hashCode() : 0);
        return result;
    }
}
