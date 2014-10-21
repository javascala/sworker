package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by zhangjin on 2014/9/5.
 */
@Entity
@Table(name = "sw_file_version")
public class SwFileVersionEntity implements Serializable{
    private Long versionid;
    private String versionname;
    private Long enterpriseid;
    private Long groupid;
    private Long updator;
    private Timestamp updatetime;
    private String updatedesc;
    private Long size;
    private String bucket;
    private String ossobjectid;
    private Integer status;

    @Id
    @Column(name = "VERSIONID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getVersionid() {
        return versionid;
    }

    public void setVersionid(Long versionid) {
        this.versionid = versionid;
    }

    @Basic
    @Column(name = "VERSIONNAME")
    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
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

    @Basic
    @Column(name = "UPDATEDESC")
    public String getUpdatedesc() {
        return updatedesc;
    }

    public void setUpdatedesc(String updatedesc) {
        this.updatedesc = updatedesc;
    }

    @Basic
    @Column(name = "SIZE")
    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Basic
    @Column(name = "BUCKET")
    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    @Basic
    @Column(name = "OSSOBJECTID")
    public String getOssobjectid() {
        return ossobjectid;
    }

    public void setOssobjectid(String ossobjectid) {
        this.ossobjectid = ossobjectid;
    }

    @Basic
    @Column(name = "STATUS")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwFileVersionEntity that = (SwFileVersionEntity) o;

        if (bucket != null ? !bucket.equals(that.bucket) : that.bucket != null) return false;
        if (enterpriseid != null ? !enterpriseid.equals(that.enterpriseid) : that.enterpriseid != null) return false;
        if (groupid != null ? !groupid.equals(that.groupid) : that.groupid != null) return false;
        if (ossobjectid != null ? !ossobjectid.equals(that.ossobjectid) : that.ossobjectid != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (updatedesc != null ? !updatedesc.equals(that.updatedesc) : that.updatedesc != null) return false;
        if (updatetime != null ? !updatetime.equals(that.updatetime) : that.updatetime != null) return false;
        if (updator != null ? !updator.equals(that.updator) : that.updator != null) return false;
        if (versionid != null ? !versionid.equals(that.versionid) : that.versionid != null) return false;
        if (versionname != null ? !versionname.equals(that.versionname) : that.versionname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = versionid != null ? versionid.hashCode() : 0;
        result = 31 * result + (versionname != null ? versionname.hashCode() : 0);
        result = 31 * result + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result = 31 * result + (groupid != null ? groupid.hashCode() : 0);
        result = 31 * result + (updator != null ? updator.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        result = 31 * result + (updatedesc != null ? updatedesc.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (bucket != null ? bucket.hashCode() : 0);
        result = 31 * result + (ossobjectid != null ? ossobjectid.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
