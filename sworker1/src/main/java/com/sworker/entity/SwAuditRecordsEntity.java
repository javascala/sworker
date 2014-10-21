package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by cuijh on 2014/9/9.
 */
@Entity
@Table(name = "sw_audit_records")
public class SwAuditRecordsEntity implements Serializable {
    private long id;
    private Long enterpriseid;
    private Long applicationid;
    private Long auditorid;
    private String auditdetails;
    private Integer auditopinion;
    private Timestamp auditdate;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ID")
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
    @Column(name = "APPLICATIONID")
    public Long getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(Long applicationid) {
        this.applicationid = applicationid;
    }

    @Basic
    @Column(name = "AUDITORID")
    public Long getAuditorid() {
        return auditorid;
    }

    public void setAuditorid(Long auditorid) {
        this.auditorid = auditorid;
    }

    @Basic
    @Column(name = "AUDITDETAILS")
    public String getAuditdetails() {
        return auditdetails;
    }

    public void setAuditdetails(String auditdetails) {
        this.auditdetails = auditdetails;
    }

    @Basic
    @Column(name = "AUDITOPINION")
    public Integer getAuditopinion() {
        return auditopinion;
    }

    public void setAuditopinion(Integer auditopinion) {
        this.auditopinion = auditopinion;
    }

    @Basic
    @Column(name = "AUDITDATE")
    public Timestamp getAuditdate() {
        return auditdate;
    }

    public void setAuditdate(Timestamp auditdate) {
        this.auditdate = auditdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwAuditRecordsEntity that = (SwAuditRecordsEntity) o;

        if (id != that.id) return false;
        if (applicationid != null ? !applicationid.equals(that.applicationid) : that.applicationid != null)
            return false;
        if (auditdate != null ? !auditdate.equals(that.auditdate) : that.auditdate != null) return false;
        if (auditdetails != null ? !auditdetails.equals(that.auditdetails) : that.auditdetails != null) return false;
        if (auditopinion != null ? !auditopinion.equals(that.auditopinion) : that.auditopinion != null) return false;
        if (auditorid != null ? !auditorid.equals(that.auditorid) : that.auditorid != null) return false;
        if (enterpriseid != null ? !enterpriseid.equals(that.enterpriseid) : that.enterpriseid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result = 31 * result + (applicationid != null ? applicationid.hashCode() : 0);
        result = 31 * result + (auditorid != null ? auditorid.hashCode() : 0);
        result = 31 * result + (auditdetails != null ? auditdetails.hashCode() : 0);
        result = 31 * result + (auditopinion != null ? auditopinion.hashCode() : 0);
        result = 31 * result + (auditdate != null ? auditdate.hashCode() : 0);
        return result;
    }
}

