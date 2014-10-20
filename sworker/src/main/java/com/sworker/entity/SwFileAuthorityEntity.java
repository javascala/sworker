package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhangjin on 2014/9/5.
 */
@Entity
@Table(name = "sw_file_authority")
public class SwFileAuthorityEntity implements Serializable{
    private Long id;
    private Long enterpriseid;
    private Integer authrole;
    private Integer authtype;
    private Long userroleid;
    private Long grouproleid;
    private Long resourceid;
    private Long fileResourceid;

    //用户实体
    private SwUserInfoEntity userEntity;


    @ManyToOne(optional = false)
    @JoinColumn(name = "userroleid" ,referencedColumnName = "id", insertable=false, updatable=false)
    public SwUserInfoEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(SwUserInfoEntity userEntity) {
        this.userEntity = userEntity;
    }

    //群组实体
    private SwGroupInfoEntity groupEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "grouproleid", referencedColumnName = "id", insertable=false, updatable=false)
    public SwGroupInfoEntity getGroupEntity() {
        return groupEntity;
    }

    public void setGroupEntity(SwGroupInfoEntity groupEntity) {
        this.groupEntity = groupEntity;
    }


    //路径实体
    private SwFileDirectoryEntity drEntity;


    @ManyToOne(optional = false)
    @JoinColumn(name="resourceid", referencedColumnName = "directoryid", insertable=false, updatable=false)
    public SwFileDirectoryEntity getDrEntity() {
        return drEntity;
    }

    public void setDrEntity(SwFileDirectoryEntity drEntity) {
        this.drEntity = drEntity;
    }

    private SwFileinfoEntity fileinfoEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name="fileResourceid", referencedColumnName = "fileid", insertable=false, updatable=false)
    public SwFileinfoEntity getFileinfoEntity() {
        return fileinfoEntity;
    }

    public void setFileinfoEntity(SwFileinfoEntity fileinfoEntity) {
        this.fileinfoEntity = fileinfoEntity;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    @Column(name = "AUTHROLE")
    public Integer getAuthrole() {
        return authrole;
    }

    public void setAuthrole(Integer authrole) {
        this.authrole = authrole;
    }

    @Basic
    @Column(name = "AUTHTYPE")
    public Integer getAuthtype() {
        return authtype;
    }

    public void setAuthtype(Integer authtype) {
        this.authtype = authtype;
    }

    public Long getFileResourceid() {
        return fileResourceid;
    }

    public void setFileResourceid(Long fileResourceid) {
        this.fileResourceid = fileResourceid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwFileAuthorityEntity that = (SwFileAuthorityEntity) o;

        if (authrole != null ? !authrole.equals(that.authrole) : that.authrole != null) return false;
        if (authtype != null ? !authtype.equals(that.authtype) : that.authtype != null) return false;
        if (enterpriseid != null ? !enterpriseid.equals(that.enterpriseid) : that.enterpriseid != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result = 31 * result + (authrole != null ? authrole.hashCode() : 0);
        result = 31 * result + (authtype != null ? authtype.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "USERROLEID")
    public Long getUserroleid() {
        return userroleid;
    }

    public void setUserroleid(Long userroleid) {
        this.userroleid = userroleid;
    }

    @Basic
    @Column(name = "GROUPROLEID")
    public Long getGrouproleid() {
        return grouproleid;
    }

    public void setGrouproleid(Long grouproleid) {
        this.grouproleid = grouproleid;
    }

    @Basic
    @Column(name = "RESOURCEID")
    public Long getResourceid() {
        return resourceid;
    }

    public void setResourceid(Long resourceid) {
        this.resourceid = resourceid;
    }
}
