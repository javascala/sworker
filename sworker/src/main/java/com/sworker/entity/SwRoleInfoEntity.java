package com.sworker.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Created by cuijh on 2014/9/9.
 */
@Entity
@Table(name = "sw_role_info")
public class SwRoleInfoEntity implements Serializable{
    private long id;
    private Long enterpriseid;
    private String role;
    private String description;
    private Integer level;
    private Integer sublevel;
    private Integer roletype;
    private Integer status;

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
    @Column(name = "ROLE")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
    @Column(name = "LEVEL")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "SUBLEVEL")
    public Integer getSublevel() {
        return sublevel;
    }

    public void setSublevel(Integer sublevel) {
        this.sublevel = sublevel;
    }

    @Basic
    @Column(name = "ROLETYPE")
    public Integer getRoletype() {
        return roletype;
    }

    public void setRoletype(Integer roletype) {
        this.roletype = roletype;
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

        SwRoleInfoEntity that = (SwRoleInfoEntity) o;

        if (id != that.id) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (enterpriseid != null ? !enterpriseid.equals(that.enterpriseid) : that.enterpriseid != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (roletype != null ? !roletype.equals(that.roletype) : that.roletype != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (sublevel != null ? !sublevel.equals(that.sublevel) : that.sublevel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (sublevel != null ? sublevel.hashCode() : 0);
        result = 31 * result + (roletype != null ? roletype.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
