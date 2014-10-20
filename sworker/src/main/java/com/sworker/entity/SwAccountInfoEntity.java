package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by zhangjin on 2014/9/22.
 */
@Entity
@javax.persistence.Table(name = "sw_account_info")
public class SwAccountInfoEntity implements Serializable{

    private SwUserInfoEntity user;

    @OneToOne(mappedBy="accountInfoEntity")
    public SwUserInfoEntity getUser() {
        return user;
    }

    public void setUser(SwUserInfoEntity user) {
        this.user = user;
    }

    private Long id;

    @Id
    @javax.persistence.Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long enterpriseid;

    @Basic
    @javax.persistence.Column(name = "ENTERPRISEID")
    public Long getEnterpriseid() {
        return enterpriseid;
    }

    public void setEnterpriseid(Long enterpriseid) {
        this.enterpriseid = enterpriseid;
    }

    private String accountname;

    @Basic
    @javax.persistence.Column(name = "ACCOUNTNAME")
    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    private String password;

    @Basic
    @javax.persistence.Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String passwordquestion;

    @Basic
    @javax.persistence.Column(name = "PASSWORDQUESTION")
    public String getPasswordquestion() {
        return passwordquestion;
    }

    public void setPasswordquestion(String passwordquestion) {
        this.passwordquestion = passwordquestion;
    }

    private String passwordanswer;

    @Basic
    @javax.persistence.Column(name = "PASSWORDANSWER")
    public String getPasswordanswer() {
        return passwordanswer;
    }

    public void setPasswordanswer(String passwordanswer) {
        this.passwordanswer = passwordanswer;
    }

    private String account;

    @Basic
    @javax.persistence.Column(name = "ACCOUNT")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    private String companyname;

    @Basic
    @javax.persistence.Column(name = "COMPANYNAME")
    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    private String nickname;

    @Basic
    @javax.persistence.Column(name = "NICKNAME")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private Timestamp creatdate;

    @Basic
    @javax.persistence.Column(name = "CREATDATE")
    public Timestamp getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Timestamp creatdate) {
        this.creatdate = creatdate;
    }

    private String creatip;

    @Basic
    @javax.persistence.Column(name = "CREATIP")
    public String getCreatip() {
        return creatip;
    }

    public void setCreatip(String creatip) {
        this.creatip = creatip;
    }

    private Integer usertype;

    @Basic
    @javax.persistence.Column(name = "USERTYPE")
    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    private Timestamp lastdate;

    @Basic
    @javax.persistence.Column(name = "LASTDATE")
    public Timestamp getLastdate() {
        return lastdate;
    }

    public void setLastdate(Timestamp lastdate) {
        this.lastdate = lastdate;
    }

    private String lastoperation;

    @Basic
    @javax.persistence.Column(name = "LASTOPERATION")
    public String getLastoperation() {
        return lastoperation;
    }

    public void setLastoperation(String lastoperation) {
        this.lastoperation = lastoperation;
    }

    private String lastip;

    @Basic
    @javax.persistence.Column(name = "LASTIP")
    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip;
    }

    private Integer status;

    @Basic
    @javax.persistence.Column(name = "STATUS")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Timestamp changetime;

    @Basic
    @javax.persistence.Column(name = "CHANGETIME")
    public Timestamp getChangetime() {
        return changetime;
    }

    public void setChangetime(Timestamp changetime) {
        this.changetime = changetime;
    }

    private Integer diskspace;

    @Basic
    @javax.persistence.Column(name = "DISKSPACE")
    public Integer getDiskspace() {
        return diskspace;
    }

    public void setDiskspace(Integer diskspace) {
        this.diskspace = diskspace;
    }

    private Integer userddiskspace;

    @Basic
    @javax.persistence.Column(name = "USERDDISKSPACE")
    public Integer getUserddiskspace() {
        return userddiskspace;
    }

    public void setUserddiskspace(Integer userddiskspace) {
        this.userddiskspace = userddiskspace;
    }

    private Long skinid;

    @Basic
    @javax.persistence.Column(name = "SKINID")
    public Long getSkinid() {
        return skinid;
    }

    public void setSkinid(Long skinid) {
        this.skinid = skinid;
    }

    private Long avaterid;

    @Basic
    @javax.persistence.Column(name = "AVATERID")
    public Long getAvaterid() {
        return avaterid;
    }

    public void setAvaterid(Long avaterid) {
        this.avaterid = avaterid;
    }

    private Integer follownum;

    @Basic
    @javax.persistence.Column(name = "FOLLOWNUM")
    public Integer getFollownum() {
        return follownum;
    }

    public void setFollownum(Integer follownum) {
        this.follownum = follownum;
    }

    private Integer fansnum;

    @Basic
    @javax.persistence.Column(name = "FANSNUM")
    public Integer getFansnum() {
        return fansnum;
    }

    public void setFansnum(Integer fansnum) {
        this.fansnum = fansnum;
    }

    private Integer userlevel;

    @Basic
    @javax.persistence.Column(name = "USERLEVEL")
    public Integer getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(Integer userlevel) {
        this.userlevel = userlevel;
    }

    private Integer userexp;

    @Basic
    @javax.persistence.Column(name = "USEREXP")
    public Integer getUserexp() {
        return userexp;
    }

    public void setUserexp(Integer userexp) {
        this.userexp = userexp;
    }

    private Integer empiricalvalue;

    @Basic
    @javax.persistence.Column(name = "EMPIRICALVALUE")
    public Integer getEmpiricalvalue() {
        return empiricalvalue;
    }

    public void setEmpiricalvalue(Integer empiricalvalue) {
        this.empiricalvalue = empiricalvalue;
    }

    private String language;

    @Basic
    @javax.persistence.Column(name = "LANGUAGE")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwAccountInfoEntity that = (SwAccountInfoEntity) o;

        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (accountname != null ? !accountname.equals(that.accountname) : that.accountname != null) return false;
        if (avaterid != null ? !avaterid.equals(that.avaterid) : that.avaterid != null) return false;
        if (changetime != null ? !changetime.equals(that.changetime) : that.changetime != null) return false;
        if (companyname != null ? !companyname.equals(that.companyname) : that.companyname != null) return false;
        if (creatdate != null ? !creatdate.equals(that.creatdate) : that.creatdate != null) return false;
        if (creatip != null ? !creatip.equals(that.creatip) : that.creatip != null) return false;
        if (diskspace != null ? !diskspace.equals(that.diskspace) : that.diskspace != null) return false;
        if (empiricalvalue != null ? !empiricalvalue.equals(that.empiricalvalue) : that.empiricalvalue != null)
            return false;
        if (enterpriseid != null ? !enterpriseid.equals(that.enterpriseid) : that.enterpriseid != null) return false;
        if (fansnum != null ? !fansnum.equals(that.fansnum) : that.fansnum != null) return false;
        if (follownum != null ? !follownum.equals(that.follownum) : that.follownum != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;
        if (lastdate != null ? !lastdate.equals(that.lastdate) : that.lastdate != null) return false;
        if (lastip != null ? !lastip.equals(that.lastip) : that.lastip != null) return false;
        if (lastoperation != null ? !lastoperation.equals(that.lastoperation) : that.lastoperation != null)
            return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (passwordanswer != null ? !passwordanswer.equals(that.passwordanswer) : that.passwordanswer != null)
            return false;
        if (passwordquestion != null ? !passwordquestion.equals(that.passwordquestion) : that.passwordquestion != null)
            return false;
        if (skinid != null ? !skinid.equals(that.skinid) : that.skinid != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (userddiskspace != null ? !userddiskspace.equals(that.userddiskspace) : that.userddiskspace != null)
            return false;
        if (userexp != null ? !userexp.equals(that.userexp) : that.userexp != null) return false;
        if (userlevel != null ? !userlevel.equals(that.userlevel) : that.userlevel != null) return false;
        if (usertype != null ? !usertype.equals(that.usertype) : that.usertype != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result = 31 * result + (accountname != null ? accountname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (passwordquestion != null ? passwordquestion.hashCode() : 0);
        result = 31 * result + (passwordanswer != null ? passwordanswer.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (companyname != null ? companyname.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (creatdate != null ? creatdate.hashCode() : 0);
        result = 31 * result + (creatip != null ? creatip.hashCode() : 0);
        result = 31 * result + (usertype != null ? usertype.hashCode() : 0);
        result = 31 * result + (lastdate != null ? lastdate.hashCode() : 0);
        result = 31 * result + (lastoperation != null ? lastoperation.hashCode() : 0);
        result = 31 * result + (lastip != null ? lastip.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (changetime != null ? changetime.hashCode() : 0);
        result = 31 * result + (diskspace != null ? diskspace.hashCode() : 0);
        result = 31 * result + (userddiskspace != null ? userddiskspace.hashCode() : 0);
        result = 31 * result + (skinid != null ? skinid.hashCode() : 0);
        result = 31 * result + (avaterid != null ? avaterid.hashCode() : 0);
        result = 31 * result + (follownum != null ? follownum.hashCode() : 0);
        result = 31 * result + (fansnum != null ? fansnum.hashCode() : 0);
        result = 31 * result + (userlevel != null ? userlevel.hashCode() : 0);
        result = 31 * result + (userexp != null ? userexp.hashCode() : 0);
        result = 31 * result + (empiricalvalue != null ? empiricalvalue.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }
}
