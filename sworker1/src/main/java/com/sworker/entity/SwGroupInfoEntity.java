package com.sworker.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by zhangjin on 2014/9/5.
 */
@Entity
@javax.persistence.Table(name = "sw_group_info")
public class SwGroupInfoEntity implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
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

    private Long groupnum;

    @Basic
    @javax.persistence.Column(name = "GROUPNUM")
    public Long getGroupnum() {
        return groupnum;
    }

    public void setGroupnum(Long groupnum) {
        this.groupnum = groupnum;
    }

    private String name;

    @Basic
    @javax.persistence.Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String abbr;

    @Basic
    @javax.persistence.Column(name = "ABBR")
    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
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

    private String intro;

    @Basic
    @javax.persistence.Column(name = "INTRO")
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    private String department;

    @Basic
    @javax.persistence.Column(name = "DEPARTMENT")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    private Long logoid;

    @Basic
    @javax.persistence.Column(name = "LOGOID")
    public Long getLogoid() {
        return logoid;
    }

    public void setLogoid(Long logoid) {
        this.logoid = logoid;
    }

    private Long typeid;

    @Basic
    @javax.persistence.Column(name = "TYPEID")
    public Long getTypeid() {
        return typeid;
    }

    public void setTypeid(Long typeid) {
        this.typeid = typeid;
    }

    private Integer ispublic;

    @Basic
    @javax.persistence.Column(name = "ISPUBLIC")
    public Integer getIspublic() {
        return ispublic;
    }

    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    private String joinmode;

    @Basic
    @javax.persistence.Column(name = "JOINMODE")
    public String getJoinmode() {
        return joinmode;
    }

    public void setJoinmode(String joinmode) {
        this.joinmode = joinmode;
    }

    private Integer checkstatus;

    @Basic
    @javax.persistence.Column(name = "CHECKSTATUS")
    public Integer getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(Integer checkstatus) {
        this.checkstatus = checkstatus;
    }

    private Integer memberqty;

    @Basic
    @javax.persistence.Column(name = "MEMBERQTY")
    public Integer getMemberqty() {
        return memberqty;
    }

    public void setMemberqty(Integer memberqty) {
        this.memberqty = memberqty;
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

    private Long fileid;

    @Basic
    @javax.persistence.Column(name = "FILEID")
    public Long getFileid() {
        return fileid;
    }

    public void setFileid(Long fileid) {
        this.fileid = fileid;
    }

    private Integer growthvalue;

    @Basic
    @javax.persistence.Column(name = "GROWTHVALUE")
    public Integer getGrowthvalue() {
        return growthvalue;
    }

    public void setGrowthvalue(Integer growthvalue) {
        this.growthvalue = growthvalue;
    }

    private Timestamp createdate;

    @Basic
    @javax.persistence.Column(name = "CREATEDATE")
    public Timestamp getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }

    private Timestamp startdate;

    @Basic
    @javax.persistence.Column(name = "STARTDATE")
    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    private Timestamp enddate;

    @Basic
    @javax.persistence.Column(name = "ENDDATE")
    public Timestamp getEnddate() {
        return enddate;
    }

    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
    }

    private Timestamp closedate;

    @Basic
    @javax.persistence.Column(name = "CLOSEDATE")
    public Timestamp getClosedate() {
        return closedate;
    }

    public void setClosedate(Timestamp closedate) {
        this.closedate = closedate;
    }

    private String createip;

    @Basic
    @javax.persistence.Column(name = "CREATEIP")
    public String getCreateip() {
        return createip;
    }

    public void setCreateip(String createip) {
        this.createip = createip;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwGroupInfoEntity that = (SwGroupInfoEntity) o;

        if (abbr != null ? !abbr.equals(that.abbr) : that.abbr != null) return false;
        if (checkstatus != null ? !checkstatus.equals(that.checkstatus) : that.checkstatus != null) return false;
        if (closedate != null ? !closedate.equals(that.closedate) : that.closedate != null) return false;
        if (createdate != null ? !createdate.equals(that.createdate) : that.createdate != null) return false;
        if (createip != null ? !createip.equals(that.createip) : that.createip != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (enddate != null ? !enddate.equals(that.enddate) : that.enddate != null) return false;
        if (enterpriseid != null ? !enterpriseid.equals(that.enterpriseid) : that.enterpriseid != null) return false;
        if (fileid != null ? !fileid.equals(that.fileid) : that.fileid != null) return false;
        if (groupnum != null ? !groupnum.equals(that.groupnum) : that.groupnum != null) return false;
        if (growthvalue != null ? !growthvalue.equals(that.growthvalue) : that.growthvalue != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (intro != null ? !intro.equals(that.intro) : that.intro != null) return false;
        if (ispublic != null ? !ispublic.equals(that.ispublic) : that.ispublic != null) return false;
        if (joinmode != null ? !joinmode.equals(that.joinmode) : that.joinmode != null) return false;
        if (logoid != null ? !logoid.equals(that.logoid) : that.logoid != null) return false;
        if (memberqty != null ? !memberqty.equals(that.memberqty) : that.memberqty != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (skinid != null ? !skinid.equals(that.skinid) : that.skinid != null) return false;
        if (startdate != null ? !startdate.equals(that.startdate) : that.startdate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (typeid != null ? !typeid.equals(that.typeid) : that.typeid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (groupnum != null ? groupnum.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (abbr != null ? abbr.hashCode() : 0);
        result = 31 * result + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result = 31 * result + (intro != null ? intro.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (logoid != null ? logoid.hashCode() : 0);
        result = 31 * result + (typeid != null ? typeid.hashCode() : 0);
        result = 31 * result + (ispublic != null ? ispublic.hashCode() : 0);
        result = 31 * result + (joinmode != null ? joinmode.hashCode() : 0);
        result = 31 * result + (checkstatus != null ? checkstatus.hashCode() : 0);
        result = 31 * result + (memberqty != null ? memberqty.hashCode() : 0);
        result = 31 * result + (skinid != null ? skinid.hashCode() : 0);
        result = 31 * result + (fileid != null ? fileid.hashCode() : 0);
        result = 31 * result + (growthvalue != null ? growthvalue.hashCode() : 0);
        result = 31 * result + (createdate != null ? createdate.hashCode() : 0);
        result = 31 * result + (startdate != null ? startdate.hashCode() : 0);
        result = 31 * result + (enddate != null ? enddate.hashCode() : 0);
        result = 31 * result + (closedate != null ? closedate.hashCode() : 0);
        result = 31 * result + (createip != null ? createip.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
