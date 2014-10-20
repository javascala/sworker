package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by zhangjin on 2014/9/16.
 */
@Entity
@javax.persistence.Table(name = "sw_activity")
public class SwActivityEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long activityid;

    @Id
    @javax.persistence.Column(name = "ACTIVITYID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getActivityid() {
        return activityid;
    }

    public void setActivityid(Long activityid) {
        this.activityid = activityid;
    }

    private String username;

    @Basic
    @javax.persistence.Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String activityitemkey;

    @Basic
    @javax.persistence.Column(name = "ACTIVITYITEMKEY")
    public String getActivityitemkey() {
        return activityitemkey;
    }

    public void setActivityitemkey(String activityitemkey) {
        this.activityitemkey = activityitemkey;
    }

    private String appkey;

    @Basic
    @javax.persistence.Column(name = "APPKEY")
    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    private Long userid;

    @Basic
    @javax.persistence.Column(name = "USERID")
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    private Long sourceid;

    @Basic
    @javax.persistence.Column(name = "SOURCEID")
    public Long getSourceid() {
        return sourceid;
    }

    public void setSourceid(Long sourceid) {
        this.sourceid = sourceid;
    }

    private Long referenceid;

    @Basic
    @javax.persistence.Column(name = "REFERENCEID")
    public Long getReferenceid() {
        return referenceid;
    }

    public void setReferenceid(Long referenceid) {
        this.referenceid = referenceid;
    }

    private Long referenceappkey;

    @Basic
    @javax.persistence.Column(name = "REFERENCEAPPKEY")
    public Long getReferenceappkey() {
        return referenceappkey;
    }

    public void setReferenceappkey(Long referenceappkey) {
        this.referenceappkey = referenceappkey;
    }

    private Integer isprivate;

    @Basic
    @javax.persistence.Column(name = "ISPRIVATE")
    public Integer getIsprivate() {
        return isprivate;
    }

    public void setIsprivate(Integer isprivate) {
        this.isprivate = isprivate;
    }

    private Integer isoriginalthread;

    @Basic
    @javax.persistence.Column(name = "ISORIGINALTHREAD")
    public Integer getIsoriginalthread() {
        return isoriginalthread;
    }

    public void setIsoriginalthread(Integer isoriginalthread) {
        this.isoriginalthread = isoriginalthread;
    }

    private Integer hasvideo;

    @Basic
    @javax.persistence.Column(name = "HASVIDEO")
    public Integer getHasvideo() {
        return hasvideo;
    }

    public void setHasvideo(Integer hasvideo) {
        this.hasvideo = hasvideo;
    }

    private Integer hasmusic;

    @Basic
    @javax.persistence.Column(name = "HASMUSIC")
    public Integer getHasmusic() {
        return hasmusic;
    }

    public void setHasmusic(Integer hasmusic) {
        this.hasmusic = hasmusic;
    }

    private Integer hasimage;

    @Basic
    @javax.persistence.Column(name = "HASIMAGE")
    public Integer getHasimage() {
        return hasimage;
    }

    public void setHasimage(Integer hasimage) {
        this.hasimage = hasimage;
    }

    private Timestamp datecreated;

    @Basic
    @javax.persistence.Column(name = "DATECREATED")
    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    private Timestamp lastmodified;

    @Basic
    @javax.persistence.Column(name = "LASTMODIFIED")
    public Timestamp getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Timestamp lastmodified) {
        this.lastmodified = lastmodified;
    }

    private Integer applicationid;

    @Basic
    @javax.persistence.Column(name = "APPLICATIONID")
    public Integer getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(Integer applicationid) {
        this.applicationid = applicationid;
    }

    private Integer referenceappid;

    @Basic
    @javax.persistence.Column(name = "REFERENCEAPPID")
    public Integer getReferenceappid() {
        return referenceappid;
    }

    public void setReferenceappid(Integer referenceappid) {
        this.referenceappid = referenceappid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwActivityEntity that = (SwActivityEntity) o;

        if (activityid != null ? !activityid.equals(that.activityid) : that.activityid != null) return false;
        if (activityitemkey != null ? !activityitemkey.equals(that.activityitemkey) : that.activityitemkey != null)
            return false;
        if (appkey != null ? !appkey.equals(that.appkey) : that.appkey != null) return false;
        if (applicationid != null ? !applicationid.equals(that.applicationid) : that.applicationid != null)
            return false;
        if (datecreated != null ? !datecreated.equals(that.datecreated) : that.datecreated != null) return false;
        if (hasimage != null ? !hasimage.equals(that.hasimage) : that.hasimage != null) return false;
        if (hasmusic != null ? !hasmusic.equals(that.hasmusic) : that.hasmusic != null) return false;
        if (hasvideo != null ? !hasvideo.equals(that.hasvideo) : that.hasvideo != null) return false;
        if (isoriginalthread != null ? !isoriginalthread.equals(that.isoriginalthread) : that.isoriginalthread != null)
            return false;
        if (isprivate != null ? !isprivate.equals(that.isprivate) : that.isprivate != null) return false;
        if (lastmodified != null ? !lastmodified.equals(that.lastmodified) : that.lastmodified != null) return false;
        if (referenceappid != null ? !referenceappid.equals(that.referenceappid) : that.referenceappid != null)
            return false;
        if (referenceappkey != null ? !referenceappkey.equals(that.referenceappkey) : that.referenceappkey != null)
            return false;
        if (referenceid != null ? !referenceid.equals(that.referenceid) : that.referenceid != null) return false;
        if (sourceid != null ? !sourceid.equals(that.sourceid) : that.sourceid != null) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = activityid != null ? activityid.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (activityitemkey != null ? activityitemkey.hashCode() : 0);
        result = 31 * result + (appkey != null ? appkey.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (sourceid != null ? sourceid.hashCode() : 0);
        result = 31 * result + (referenceid != null ? referenceid.hashCode() : 0);
        result = 31 * result + (referenceappkey != null ? referenceappkey.hashCode() : 0);
        result = 31 * result + (isprivate != null ? isprivate.hashCode() : 0);
        result = 31 * result + (isoriginalthread != null ? isoriginalthread.hashCode() : 0);
        result = 31 * result + (hasvideo != null ? hasvideo.hashCode() : 0);
        result = 31 * result + (hasmusic != null ? hasmusic.hashCode() : 0);
        result = 31 * result + (hasimage != null ? hasimage.hashCode() : 0);
        result = 31 * result + (datecreated != null ? datecreated.hashCode() : 0);
        result = 31 * result + (lastmodified != null ? lastmodified.hashCode() : 0);
        result = 31 * result + (applicationid != null ? applicationid.hashCode() : 0);
        result = 31 * result + (referenceappid != null ? referenceappid.hashCode() : 0);
        return result;
    }
}
