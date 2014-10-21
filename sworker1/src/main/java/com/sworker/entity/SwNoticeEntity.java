package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by wangying on 2014/9/9.
 */
@Entity
@Table(name = "sw_notice")
public class SwNoticeEntity implements Serializable {
    private long id;
    private Long enterpriseid;
    private String title;
    private Integer status;
    private String content;
    private Timestamp expirationdate;
    private Timestamp updatetime;
    private Timestamp createdate;
    private Long createpersonid;
    private Integer isreceipt;
    private Integer iscomment;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "EXPIRATIONDATE")
    public Timestamp getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Timestamp expirationdate) {
        this.expirationdate = expirationdate;
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
    @Column(name = "CREATEDATE")
    public Timestamp getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }

    @Basic
    @Column(name = "CREATEPERSONID")
    public Long getCreatepersonid() {
        return createpersonid;
    }

    public void setCreatepersonid(Long createpersonid) {
        this.createpersonid = createpersonid;
    }

    @Basic
    @Column(name = "ISRECEIPT")
    public Integer getIsreceipt() {
        return isreceipt;
    }

    public void setIsreceipt(Integer isreceipt) {
        this.isreceipt = isreceipt;
    }

    @Basic
    @Column(name = "ISCOMMENT")
    public Integer getIscomment() {
        return iscomment;
    }

    public void setIscomment(Integer iscomment) {
        this.iscomment = iscomment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwNoticeEntity that = (SwNoticeEntity) o;

        if (id != that.id) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (createdate != null ? !createdate.equals(that.createdate) : that.createdate != null) return false;
        if (createpersonid != null ? !createpersonid.equals(that.createpersonid) : that.createpersonid != null)
            return false;
        if (enterpriseid != null ? !enterpriseid.equals(that.enterpriseid) : that.enterpriseid != null) return false;
        if (expirationdate != null ? !expirationdate.equals(that.expirationdate) : that.expirationdate != null)
            return false;
        if (iscomment != null ? !iscomment.equals(that.iscomment) : that.iscomment != null) return false;
        if (isreceipt != null ? !isreceipt.equals(that.isreceipt) : that.isreceipt != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (updatetime != null ? !updatetime.equals(that.updatetime) : that.updatetime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (expirationdate != null ? expirationdate.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        result = 31 * result + (createdate != null ? createdate.hashCode() : 0);
        result = 31 * result + (createpersonid != null ? createpersonid.hashCode() : 0);
        result = 31 * result + (isreceipt != null ? isreceipt.hashCode() : 0);
        result = 31 * result + (iscomment != null ? iscomment.hashCode() : 0);
        return result;
    }
}
