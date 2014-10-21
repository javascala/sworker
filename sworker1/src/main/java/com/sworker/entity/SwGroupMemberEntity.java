package com.sworker.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by zhangjin on 2014/9/5.
 */
@Entity
@Table(name = "sw_group_member")
public class SwGroupMemberEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
    private Long groupid;
    private Long memberid;
    private Long roleid;
    private Timestamp enterdate;
    private Integer status;
    private Integer isforbidden;

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
    @Column(name = "ROLEID")
    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    @Basic
    @Column(name = "ENTERDATE")
    public Timestamp getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(Timestamp enterdate) {
        this.enterdate = enterdate;
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
    @Column(name = "ISFORBIDDEN")
    public Integer getIsforbidden() {
        return isforbidden;
    }

    public void setIsforbidden(Integer isforbidden) {
        this.isforbidden = isforbidden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwGroupMemberEntity that = (SwGroupMemberEntity) o;

        if (enterdate != null ? !enterdate.equals(that.enterdate) : that.enterdate != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (isforbidden != null ? !isforbidden.equals(that.isforbidden) : that.isforbidden != null) return false;
        if (roleid != null ? !roleid.equals(that.roleid) : that.roleid != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (memberid != null ? !memberid.equals(that.memberid) : that.memberid != null) return false;
        if (groupid != null ? !groupid.equals(that.groupid) : that.groupid != null) return false;
        
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (groupid != null ? groupid.hashCode() : 0);
        result = 31 * result + (memberid != null ? memberid.hashCode() : 0);
        result = 31 * result + (roleid != null ? roleid.hashCode() : 0);
        result = 31 * result + (enterdate != null ? enterdate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (isforbidden != null ? isforbidden.hashCode() : 0);
        return result;
    }
}
