package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by cuijh on 2014/9/5.
 */
@Entity
@Table(name = "sw_authority")
public class SwAuthorityEntity implements Serializable {
    private long id;
    private String authoritykey;
    private String authorityname;
    private String description;
    private String resourcekey;
    private String operatetype;
    private Timestamp creatdate;

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
    @Column(name = "AUTHORITYKEY")
    public String getAuthoritykey() {
        return authoritykey;
    }

    public void setAuthoritykey(String authoritykey) {
        this.authoritykey = authoritykey;
    }

    @Basic
    @Column(name = "AUTHORITYNAME")
    public String getAuthorityname() {
        return authorityname;
    }

    public void setAuthorityname(String authorityname) {
        this.authorityname = authorityname;
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
    @Column(name = "RESOURCEKEY")
    public String getResourcekey() {
        return resourcekey;
    }

    public void setResourcekey(String resourcekey) {
        this.resourcekey = resourcekey;
    }

    @Basic
    @Column(name = "OPERATETYPE")
    public String getOperatetype() {
        return operatetype;
    }

    public void setOperatetype(String operatetype) {
        this.operatetype = operatetype;
    }

    @Basic
    @Column(name = "CREATDATE")
    public Timestamp getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Timestamp creatdate) {
        this.creatdate = creatdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwAuthorityEntity that = (SwAuthorityEntity) o;

        if (id != that.id) return false;
        if (authoritykey != null ? !authoritykey.equals(that.authoritykey) : that.authoritykey != null) return false;
        if (authorityname != null ? !authorityname.equals(that.authorityname) : that.authorityname != null)
            return false;
        if (creatdate != null ? !creatdate.equals(that.creatdate) : that.creatdate != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (operatetype != null ? !operatetype.equals(that.operatetype) : that.operatetype != null) return false;
        if (resourcekey != null ? !resourcekey.equals(that.resourcekey) : that.resourcekey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (authoritykey != null ? authoritykey.hashCode() : 0);
        result = 31 * result + (authorityname != null ? authorityname.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (resourcekey != null ? resourcekey.hashCode() : 0);
        result = 31 * result + (operatetype != null ? operatetype.hashCode() : 0);
        result = 31 * result + (creatdate != null ? creatdate.hashCode() : 0);
        return result;
    }
}

