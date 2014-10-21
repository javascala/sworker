package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by cuijh on 2014/9/5.
 */
@Entity
@Table(name = "sw_role_authority")
public class SwRoleAuthorityEntity implements Serializable {
    private long id;
    private Long roleid;
    private String authoritykey;

    //权限实体
    private SwAuthorityEntity authorityEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "authoritykey",referencedColumnName = "authoritykey",insertable = false,updatable = false)
    public SwAuthorityEntity getAuthorityEntity(){return authorityEntity;}
    public void setAuthorityEntity(SwAuthorityEntity authorityEntity){this.authorityEntity = authorityEntity;}


    //角色用户实体
    private SwRoleAccountEntity roleAccountEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "roleid",referencedColumnName = "roleid",insertable = false,updatable = false)
    public SwRoleAccountEntity getRoleAccountEntity(){return roleAccountEntity;}
    public void setRoleAccountEntity(SwRoleAccountEntity roleAccountEntity){this.roleAccountEntity = roleAccountEntity;}


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
    @Column(name = "ROLEID")
    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    @Basic
    @Column(name = "AUTHORITYKEY")
    public String getAuthoritykey() {
        return authoritykey;
    }

    public void setAuthoritykey(String authoritykey) {
        this.authoritykey = authoritykey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwRoleAuthorityEntity that = (SwRoleAuthorityEntity) o;

        if (id != that.id) return false;
        if (authoritykey != null ? !authoritykey.equals(that.authoritykey) : that.authoritykey != null) return false;
        if (roleid != null ? !roleid.equals(that.roleid) : that.roleid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (roleid != null ? roleid.hashCode() : 0);
        result = 31 * result + (authoritykey != null ? authoritykey.hashCode() : 0);
        return result;
    }
}
