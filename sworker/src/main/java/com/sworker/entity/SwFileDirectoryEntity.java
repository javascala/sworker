package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by zhangjin on 2014/9/5.
 */
@Entity
@Table(name = "sw_file_directory")
public class SwFileDirectoryEntity implements Serializable{
    private Long directoryid;
    private String dirname;
    private Long parentid;
    private Integer isinherit;
    private Long enterpriseid;
    private Long groupid;
    private Long creator;
    private Timestamp createtime;
    private Long updator;
    private Timestamp updatetime;

    @Id
    @Column(name = "DIRECTORYID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getDirectoryid() {
        return directoryid;
    }

    public void setDirectoryid(Long directoryid) {
        this.directoryid = directoryid;
    }

    @Basic
    @Column(name = "DIRNAME")
    public String getDirname() {
        return dirname;
    }

    public void setDirname(String dirname) {
        this.dirname = dirname;
    }

    @Basic
    @Column(name = "PARENTID")
    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    @Basic
    @Column(name = "ISINHERIT")
    public Integer getIsinherit() {
        return isinherit;
    }

    public void setIsinherit(Integer isinherit) {
        this.isinherit = isinherit;
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
    @Column(name = "CREATOR")
    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "CREATETIME")
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "UPDATOR")
    public Long getUpdator() {
        return updator;
    }

    public void setUpdator(Long updator) {
        this.updator = updator;
    }

    @Basic
    @Column(name = "UPDATETIME")
    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwFileDirectoryEntity entity = (SwFileDirectoryEntity) o;

        if (createtime != null ? !createtime.equals(entity.createtime) : entity.createtime != null) return false;
        if (creator != null ? !creator.equals(entity.creator) : entity.creator != null) return false;
        if (directoryid != null ? !directoryid.equals(entity.directoryid) : entity.directoryid != null) return false;
        if (dirname != null ? !dirname.equals(entity.dirname) : entity.dirname != null) return false;
        if (enterpriseid != null ? !enterpriseid.equals(entity.enterpriseid) : entity.enterpriseid != null)
            return false;
        if (groupid != null ? !groupid.equals(entity.groupid) : entity.groupid != null) return false;
        if (isinherit != null ? !isinherit.equals(entity.isinherit) : entity.isinherit != null) return false;
        if (parentid != null ? !parentid.equals(entity.parentid) : entity.parentid != null) return false;
        if (updatetime != null ? !updatetime.equals(entity.updatetime) : entity.updatetime != null) return false;
        if (updator != null ? !updator.equals(entity.updator) : entity.updator != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = directoryid != null ? directoryid.hashCode() : 0;
        result = 31 * result + (dirname != null ? dirname.hashCode() : 0);
        result = 31 * result + (parentid != null ? parentid.hashCode() : 0);
        result = 31 * result + (isinherit != null ? isinherit.hashCode() : 0);
        result = 31 * result + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result = 31 * result + (groupid != null ? groupid.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (updator != null ? updator.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        return result;
    }
}
