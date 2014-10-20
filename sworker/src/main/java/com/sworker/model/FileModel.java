package com.sworker.model;

import java.util.Date;

/**
 * Created by zhangjin on 2014/9/17.
 */
public class FileModel {
    private Long enterpriseid;
    private Long fileid;
    private String filename;
    private String description;
    private Long parentid;
    private Long creator;
    private Date createtime;
    private Long editcount;
    private Long viewcount;
    private Long downloadcount;
    private Long sourceSize;
    private Long updator;
    private Date updatetime;

    private String bucket;
    private String fileKey;

    public FileModel() {
    }

    public FileModel(Long enterpriseid, Long fileid, String filename, String description, Long parentid, Long creator, Date createtime, Long editcount, Long viewcount, Long downloadcount, Long sourceSize, Long updator, Date updatetime, String bucket, String fileKey) {
        this.enterpriseid = enterpriseid;
        this.fileid = fileid;
        this.filename = filename;
        this.description = description;
        this.parentid = parentid;
        this.creator = creator;
        this.createtime = createtime;
        this.editcount = editcount;
        this.viewcount = viewcount;
        this.downloadcount = downloadcount;
        this.sourceSize = sourceSize;
        this.updator = updator;
        this.updatetime = updatetime;
        this.bucket = bucket;
        this.fileKey = fileKey;
    }

    public Long getEnterpriseid() {
        return enterpriseid;
    }

    public void setEnterpriseid(Long enterpriseid) {
        this.enterpriseid = enterpriseid;
    }

    public Long getFileid() {
        return fileid;
    }

    public void setFileid(Long fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getEditcount() {
        return editcount;
    }

    public void setEditcount(Long editcount) {
        this.editcount = editcount;
    }

    public Long getViewcount() {
        return viewcount;
    }

    public void setViewcount(Long viewcount) {
        this.viewcount = viewcount;
    }

    public Long getDownloadcount() {
        return downloadcount;
    }

    public void setDownloadcount(Long downloadcount) {
        this.downloadcount = downloadcount;
    }

    public Long getUpdator() {
        return updator;
    }

    public void setUpdator(Long updator) {
        this.updator = updator;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public Long getSourceSize() {
        return sourceSize;
    }

    public void setSourceSize(Long sourceSize) {
        this.sourceSize = sourceSize;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
