package com.sworker.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by zhangjin on 2014/9/5.
 */
@Entity
@Table(name = "sw_group_apply")
public class SwGroupApplyEntity implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private Long groupid;
    private Long memberid;
    private String applyforreason;
    private Integer applyforstatus;
    private Date applyfordate;

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
    @Column(name = "APPLYFORREASON")
    public String getApplyforreason() {
        return applyforreason;
    }

    public void setApplyforreason(String applyforreason) {
        this.applyforreason = applyforreason;
    }

    @Basic
    @Column(name = "APPLYFORSTATUS")
    public Integer getApplyforstatus() {
        return applyforstatus;
    }

    public void setApplyforstatus(Integer applyforstatus) {
        this.applyforstatus = applyforstatus;
    }

    @Basic
    @Column(name = "APPLYFORDATE")
    public Date getApplyfordate() {
        return applyfordate;
    }

    public void setApplyfordate(Date applyfordate) {
        this.applyfordate = applyfordate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwGroupApplyEntity that = (SwGroupApplyEntity) o;

        if (applyfordate != null ? !applyfordate.equals(that.applyfordate) : that.applyfordate != null) return false;
        if (applyforreason != null ? !applyforreason.equals(that.applyforreason) : that.applyforreason != null)
            return false;
        if (applyforstatus != null ? !applyforstatus.equals(that.applyforstatus) : that.applyforstatus != null)
            return false;
        if (groupid != null ? !groupid.equals(that.groupid) : that.groupid != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (memberid != null ? !memberid.equals(that.memberid) : that.memberid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (groupid != null ? groupid.hashCode() : 0);
        result = 31 * result + (memberid != null ? memberid.hashCode() : 0);
        result = 31 * result + (applyforreason != null ? applyforreason.hashCode() : 0);
        result = 31 * result + (applyforstatus != null ? applyforstatus.hashCode() : 0);
        result = 31 * result + (applyfordate != null ? applyfordate.hashCode() : 0);
        return result;
    }
}
