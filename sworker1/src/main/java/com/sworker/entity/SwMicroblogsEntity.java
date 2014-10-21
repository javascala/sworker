package com.sworker.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by zhangjin on 2014/9/5.
 */
@Entity
@Table(name = "sw_microblogs")
public class SwMicroblogsEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long microblogid;
    private Long userid;
    private String author;
    private Long enterpriseid;
    private Long originalmicroblogid;
    private Long forwardedmicroblogid;
    private String body;
    private Long replycount;
    private Long forwardedcount;
    private String photoid;
    private String videourlid;
    private String musicurlid;
    private Timestamp datecreated;

    @Id
    @Column(name = "MICROBLOGID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getMicroblogid() {
        return microblogid;
    }

    public void setMicroblogid(Long microblogid) {
        this.microblogid = microblogid;
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
    @Column(name = "AUTHOR")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
    @Column(name = "ORIGINALMICROBLOGID")
    public Long getOriginalmicroblogid() {
        return originalmicroblogid;
    }

    public void setOriginalmicroblogid(Long originalmicroblogid) {
        this.originalmicroblogid = originalmicroblogid;
    }

    @Basic
    @Column(name = "FORWARDEDMICROBLOGID")
    public Long getForwardedmicroblogid() {
        return forwardedmicroblogid;
    }

    public void setForwardedmicroblogid(Long forwardedmicroblogid) {
        this.forwardedmicroblogid = forwardedmicroblogid;
    }

    @Basic
    @Column(name = "BODY")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "REPLYCOUNT")
    public Long getReplycount() {
        return replycount;
    }

    public void setReplycount(Long replycount) {
        this.replycount = replycount;
    }

    @Basic
    @Column(name = "FORWARDEDCOUNT")
    public Long getForwardedcount() {
        return forwardedcount;
    }

    public void setForwardedcount(Long forwardedcount) {
        this.forwardedcount = forwardedcount;
    }

    @Basic
    @Column(name = "PHOTOID")
    public String getPhotoid() {
        return photoid;
    }

    public void setPhotoid(String photoid) {
        this.photoid = photoid;
    }

    @Basic
    @Column(name = "VIDEOURLID")
    public String getVideourlid() {
        return videourlid;
    }

    public void setVideourlid(String videourlid) {
        this.videourlid = videourlid;
    }

    @Basic
    @Column(name = "MUSICURLID")
    public String getMusicurlid() {
        return musicurlid;
    }

    public void setMusicurlid(String musicurlid) {
        this.musicurlid = musicurlid;
    }

    @Basic
    @Column(name = "DATECREATED")
    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwMicroblogsEntity that = (SwMicroblogsEntity) o;

        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        if (datecreated != null ? !datecreated.equals(that.datecreated) : that.datecreated != null) return false;
        if (enterpriseid != null ? !enterpriseid.equals(that.enterpriseid) : that.enterpriseid != null) return false;
        if (forwardedcount != null ? !forwardedcount.equals(that.forwardedcount) : that.forwardedcount != null)
            return false;
        if (forwardedmicroblogid != null ? !forwardedmicroblogid.equals(that.forwardedmicroblogid) : that.forwardedmicroblogid != null)
            return false;
        if (microblogid != null ? !microblogid.equals(that.microblogid) : that.microblogid != null) return false;
        if (musicurlid != null ? !musicurlid.equals(that.musicurlid) : that.musicurlid != null) return false;
        if (originalmicroblogid != null ? !originalmicroblogid.equals(that.originalmicroblogid) : that.originalmicroblogid != null)
            return false;
        if (photoid != null ? !photoid.equals(that.photoid) : that.photoid != null) return false;
        if (replycount != null ? !replycount.equals(that.replycount) : that.replycount != null) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (videourlid != null ? !videourlid.equals(that.videourlid) : that.videourlid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = microblogid != null ? microblogid.hashCode() : 0;
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result = 31 * result + (originalmicroblogid != null ? originalmicroblogid.hashCode() : 0);
        result = 31 * result + (forwardedmicroblogid != null ? forwardedmicroblogid.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (replycount != null ? replycount.hashCode() : 0);
        result = 31 * result + (forwardedcount != null ? forwardedcount.hashCode() : 0);
        result = 31 * result + (photoid != null ? photoid.hashCode() : 0);
        result = 31 * result + (videourlid != null ? videourlid.hashCode() : 0);
        result = 31 * result + (musicurlid != null ? musicurlid.hashCode() : 0);
        result = 31 * result + (datecreated != null ? datecreated.hashCode() : 0);
        return result;
    }
}
