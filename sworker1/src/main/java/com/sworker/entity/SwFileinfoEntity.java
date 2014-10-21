package com.sworker.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by zhangjin on 2014/9/16.
 */
@Entity
@Table(name = "sw_fileinfo")
public class SwFileinfoEntity implements Serializable{
    private Long fileid;
    private String filename;
    private String description;
    private Long parentid;
    private Integer isinherit;
    private Integer filetype;
    private Long enterpriseid;
    private Long groupid;
    private Long creator;
    private Timestamp createtime;
    private Long currversionid;
    private Long editcount;
    private Long viewcount;
    private Long downloadcount;
    private Long updator;
    private Timestamp updatetime;

    private SwFileVersionEntity fileVersionEntity;

    @OneToOne(optional = false)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @JoinColumn(name="currversionid", insertable=false, updatable=false)
    public SwFileVersionEntity getFileVersionEntity() {
        return fileVersionEntity;
    }

    public void setFileVersionEntity(SwFileVersionEntity fileVersionEntity) {
        this.fileVersionEntity = fileVersionEntity;
    }


    @Id
    @Column(name = "FILEID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getFileid() {
        return fileid;
    }

    public void setFileid(Long fileid) {
        this.fileid = fileid;
    }

    @Basic
    @Column(name = "FILENAME")
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    @Column(name = "FILETYPE")
    public Integer getFiletype() {
        return filetype;
    }

    public void setFiletype(Integer filetype) {
        this.filetype = filetype;
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
    @Column(name = "CURRVERSIONID")
    public Long getCurrversionid() {
        return currversionid;
    }

    public void setCurrversionid(Long currversionid) {
        this.currversionid = currversionid;
    }

    @Basic
    @Column(name = "EDITCOUNT")
    public Long getEditcount() {
        return editcount;
    }

    public void setEditcount(Long editcount) {
        this.editcount = editcount;
    }

    @Basic
    @Column(name = "VIEWCOUNT")
    public Long getViewcount() {
        return viewcount;
    }

    public void setViewcount(Long viewcount) {
        this.viewcount = viewcount;
    }

    @Basic
    @Column(name = "DOWNLOADCOUNT")
    public Long getDownloadcount() {
        return downloadcount;
    }

    public void setDownloadcount(Long downloadcount) {
        this.downloadcount = downloadcount;
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

        SwFileinfoEntity that = (SwFileinfoEntity) o;

        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
        if (currversionid != null ? !currversionid.equals(that.currversionid) : that.currversionid != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (downloadcount != null ? !downloadcount.equals(that.downloadcount) : that.downloadcount != null)
            return false;
        if (editcount != null ? !editcount.equals(that.editcount) : that.editcount != null) return false;
        if (enterpriseid != null ? !enterpriseid.equals(that.enterpriseid) : that.enterpriseid != null) return false;
        if (fileid != null ? !fileid.equals(that.fileid) : that.fileid != null) return false;
        if (filename != null ? !filename.equals(that.filename) : that.filename != null) return false;
        if (filetype != null ? !filetype.equals(that.filetype) : that.filetype != null) return false;
        if (groupid != null ? !groupid.equals(that.groupid) : that.groupid != null) return false;
        if (isinherit != null ? !isinherit.equals(that.isinherit) : that.isinherit != null) return false;
        if (parentid != null ? !parentid.equals(that.parentid) : that.parentid != null) return false;
        if (updatetime != null ? !updatetime.equals(that.updatetime) : that.updatetime != null) return false;
        if (updator != null ? !updator.equals(that.updator) : that.updator != null) return false;
        if (viewcount != null ? !viewcount.equals(that.viewcount) : that.viewcount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fileid != null ? fileid.hashCode() : 0;
        result = 31 * result + (filename != null ? filename.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (parentid != null ? parentid.hashCode() : 0);
        result = 31 * result + (isinherit != null ? isinherit.hashCode() : 0);
        result = 31 * result + (filetype != null ? filetype.hashCode() : 0);
        result = 31 * result + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result = 31 * result + (groupid != null ? groupid.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (currversionid != null ? currversionid.hashCode() : 0);
        result = 31 * result + (editcount != null ? editcount.hashCode() : 0);
        result = 31 * result + (viewcount != null ? viewcount.hashCode() : 0);
        result = 31 * result + (downloadcount != null ? downloadcount.hashCode() : 0);
        result = 31 * result + (updator != null ? updator.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        return result;
    }
}
