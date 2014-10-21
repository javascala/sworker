package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by zhangjin on 2014/9/5.
 */
@Entity
@Table(name = "sw_file_count")
public class SwFileCountEntity implements Serializable{
    private Long countid;
    private Long enterpriseid;
    private Long groupid;
    private Long userid;
    private Long operateid;
    private Timestamp operatetime;
    private Long operateinfo;

    @Id
    @Column(name = "COUNTID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getCountid() {
        return countid;
    }

    public void setCountid(Long countid) {
        this.countid = countid;
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
    @Column(name = "GROUPID")
    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
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
    @Column(name = "OPERATEID")
    public Long getOperateid() {
        return operateid;
    }

    public void setOperateid(Long operateid) {
        this.operateid = operateid;
    }

    @Basic
    @Column(name = "OPERATETIME")
    public Timestamp getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Timestamp operatetime) {
        this.operatetime = operatetime;
    }

    @Basic
    @Column(name = "OPERATEINFO")
    public Long getOperateinfo() {
        return operateinfo;
    }

    public void setOperateinfo(Long operateinfo) {
        this.operateinfo = operateinfo;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwFileCountEntity that = (SwFileCountEntity) o;

        if (countid != null ? !countid.equals(that.countid) : that.countid != null) return false;
        if (enterpriseid != null ? !enterpriseid.equals(that.enterpriseid) : that.enterpriseid != null) return false;
        if (groupid != null ? !groupid.equals(that.groupid) : that.groupid != null) return false;
        if (operateid != null ? !operateid.equals(that.operateid) : that.operateid != null) return false;
        if (operateinfo != null ? !operateinfo.equals(that.operateinfo) : that.operateinfo != null) return false;
        if (operatetime != null ? !operatetime.equals(that.operatetime) : that.operatetime != null) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = countid != null ? countid.hashCode() : 0;
        result = 31 * result + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result = 31 * result + (groupid != null ? groupid.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (operateid != null ? operateid.hashCode() : 0);
        result = 31 * result + (operatetime != null ? operatetime.hashCode() : 0);
        result = 31 * result + (operateinfo != null ? operateinfo.hashCode() : 0);
        return result;
    }
}
