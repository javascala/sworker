package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by wangying on 2014/9/5.
 */
@Entity
@Table(name = "sw_visible_range")
public class SwVisibleRangeEntity implements Serializable {
    private long id;
    private Long objectid;
    private Long groupid;
    private BigDecimal groupeviewrangeid;
    private BigDecimal userviewrangeid;
    private String viewrangetype;

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
    @Column(name = "OBJECTID")
    public Long getObjectid() {
        return objectid;
    }

    public void setObjectid(Long objectid) {
        this.objectid = objectid;
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
    @Column(name = "GROUPEVIEWRANGEID")
    public BigDecimal getGroupeviewrangeid() {
        return groupeviewrangeid;
    }

    public void setGroupeviewrangeid(BigDecimal groupeviewrangeid) {
        this.groupeviewrangeid = groupeviewrangeid;
    }

    @Basic
    @Column(name = "USERVIEWRANGEID")
    public BigDecimal getUserviewrangeid() {
        return userviewrangeid;
    }

    public void setUserviewrangeid(BigDecimal userviewrangeid) {
        this.userviewrangeid = userviewrangeid;
    }

    @Basic
    @Column(name = "VIEWRANGETYPE")
    public String getViewrangetype() {
        return viewrangetype;
    }

    public void setViewrangetype(String viewrangetype) {
        this.viewrangetype = viewrangetype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwVisibleRangeEntity that = (SwVisibleRangeEntity) o;

        if (id != that.id) return false;
        if (groupeviewrangeid != null ? !groupeviewrangeid.equals(that.groupeviewrangeid) : that.groupeviewrangeid != null)
            return false;
        if (groupid != null ? !groupid.equals(that.groupid) : that.groupid != null) return false;
        if (objectid != null ? !objectid.equals(that.objectid) : that.objectid != null) return false;
        if (userviewrangeid != null ? !userviewrangeid.equals(that.userviewrangeid) : that.userviewrangeid != null)
            return false;
        if (viewrangetype != null ? !viewrangetype.equals(that.viewrangetype) : that.viewrangetype != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (objectid != null ? objectid.hashCode() : 0);
        result = 31 * result + (groupid != null ? groupid.hashCode() : 0);
        result = 31 * result + (groupeviewrangeid != null ? groupeviewrangeid.hashCode() : 0);
        result = 31 * result + (userviewrangeid != null ? userviewrangeid.hashCode() : 0);
        result = 31 * result + (viewrangetype != null ? viewrangetype.hashCode() : 0);
        return result;
    }
}
