package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by zhangjin on 2014/9/22.
 */
@Entity
@javax.persistence.Table(name = "sw_user_info")
public class SwUserInfoEntity implements Serializable{

    private SwAccountInfoEntity accountInfoEntity;

    @OneToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST}, targetEntity=SwAccountInfoEntity.class)
    @JoinColumn(name="accountid")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public SwAccountInfoEntity getAccountInfoEntity() {
        return accountInfoEntity;
    }

    public void setAccountInfoEntity(SwAccountInfoEntity accountInfoEntity) {
        this.accountInfoEntity = accountInfoEntity;
    }

    private Long id;

    @Id
    @javax.persistence.Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long accountid;

    @Basic
    @javax.persistence.Column(name = "ACCOUNTID")
    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
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

    private Integer sex;

    @Basic
    @javax.persistence.Column(name = "SEX")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    private String brithdate;

    @Basic
    @javax.persistence.Column(name = "BRITHDATE")
    public String getBrithdate() {
        return brithdate;
    }

    public void setBrithdate(String brithdate) {
        this.brithdate = brithdate;
    }

    private Integer idtype;

    @Basic
    @javax.persistence.Column(name = "IDTYPE")
    public Integer getIdtype() {
        return idtype;
    }

    public void setIdtype(Integer idtype) {
        this.idtype = idtype;
    }

    private String idnumber;

    @Basic
    @javax.persistence.Column(name = "IDNUMBER")
    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    private String nationality;

    @Basic
    @javax.persistence.Column(name = "NATIONALITY")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    private String birthplace;

    @Basic
    @javax.persistence.Column(name = "BIRTHPLACE")
    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    private String nation;

    @Basic
    @javax.persistence.Column(name = "NATION")
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    private String address;

    @Basic
    @javax.persistence.Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String graduateschool;

    @Basic
    @javax.persistence.Column(name = "GRADUATESCHOOL")
    public String getGraduateschool() {
        return graduateschool;
    }

    public void setGraduateschool(String graduateschool) {
        this.graduateschool = graduateschool;
    }

    private String major;

    @Basic
    @javax.persistence.Column(name = "MAJOR")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    private String email;

    @Basic
    @javax.persistence.Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private Integer isemailbound;

    @Basic
    @javax.persistence.Column(name = "ISEMAILBOUND")
    public Integer getIsemailbound() {
        return isemailbound;
    }

    public void setIsemailbound(Integer isemailbound) {
        this.isemailbound = isemailbound;
    }

    private Long phone;

    @Basic
    @javax.persistence.Column(name = "PHONE")
    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    private Integer isphonebound;

    @Basic
    @javax.persistence.Column(name = "ISPHONEBOUND")
    public Integer getIsphonebound() {
        return isphonebound;
    }

    public void setIsphonebound(Integer isphonebound) {
        this.isphonebound = isphonebound;
    }

    private Long qq;

    @Basic
    @javax.persistence.Column(name = "QQ")
    public Long getQq() {
        return qq;
    }

    public void setQq(Long qq) {
        this.qq = qq;
    }

    private String msn;

    @Basic
    @javax.persistence.Column(name = "MSN")
    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    private String selfinfo;

    @Basic
    @javax.persistence.Column(name = "SELFINFO")
    public String getSelfinfo() {
        return selfinfo;
    }

    public void setSelfinfo(String selfinfo) {
        this.selfinfo = selfinfo;
    }

    private Integer persondataintegrity;

    @Basic
    @javax.persistence.Column(name = "PERSONDATAINTEGRITY")
    public Integer getPersondataintegrity() {
        return persondataintegrity;
    }

    public void setPersondataintegrity(Integer persondataintegrity) {
        this.persondataintegrity = persondataintegrity;
    }

    private Long departmentid;

    @Basic
    @javax.persistence.Column(name = "DEPARTMENTID")
    public Long getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Long departmentid) {
        this.departmentid = departmentid;
    }

    private Long dutyid;

    @Basic
    @javax.persistence.Column(name = "DUTYID")
    public Long getDutyid() {
        return dutyid;
    }

    public void setDutyid(Long dutyid) {
        this.dutyid = dutyid;
    }

    private String fax;

    @Basic
    @javax.persistence.Column(name = "FAX")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    private String workphone;

    @Basic
    @javax.persistence.Column(name = "WORKPHONE")
    public String getWorkphone() {
        return workphone;
    }

    public void setWorkphone(String workphone) {
        this.workphone = workphone;
    }

    private Date entrydate;

    @Basic
    @javax.persistence.Column(name = "ENTRYDATE")
    public Date getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(Date entrydate) {
        this.entrydate = entrydate;
    }

    private Integer entrymode;

    @Basic
    @javax.persistence.Column(name = "ENTRYMODE")
    public Integer getEntrymode() {
        return entrymode;
    }

    public void setEntrymode(Integer entrymode) {
        this.entrymode = entrymode;
    }

    private Integer dutydataintegrity;

    @Basic
    @javax.persistence.Column(name = "DUTYDATAINTEGRITY")
    public Integer getDutydataintegrity() {
        return dutydataintegrity;
    }

    public void setDutydataintegrity(Integer dutydataintegrity) {
        this.dutydataintegrity = dutydataintegrity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwUserInfoEntity that = (SwUserInfoEntity) o;

        if (accountid != null ? !accountid.equals(that.accountid) : that.accountid != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (birthplace != null ? !birthplace.equals(that.birthplace) : that.birthplace != null) return false;
        if (brithdate != null ? !brithdate.equals(that.brithdate) : that.brithdate != null) return false;
        if (departmentid != null ? !departmentid.equals(that.departmentid) : that.departmentid != null) return false;
        if (dutydataintegrity != null ? !dutydataintegrity.equals(that.dutydataintegrity) : that.dutydataintegrity != null)
            return false;
        if (dutyid != null ? !dutyid.equals(that.dutyid) : that.dutyid != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (entrydate != null ? !entrydate.equals(that.entrydate) : that.entrydate != null) return false;
        if (entrymode != null ? !entrymode.equals(that.entrymode) : that.entrymode != null) return false;
        if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
        if (graduateschool != null ? !graduateschool.equals(that.graduateschool) : that.graduateschool != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idnumber != null ? !idnumber.equals(that.idnumber) : that.idnumber != null) return false;
        if (idtype != null ? !idtype.equals(that.idtype) : that.idtype != null) return false;
        if (isemailbound != null ? !isemailbound.equals(that.isemailbound) : that.isemailbound != null) return false;
        if (isphonebound != null ? !isphonebound.equals(that.isphonebound) : that.isphonebound != null) return false;
        if (major != null ? !major.equals(that.major) : that.major != null) return false;
        if (msn != null ? !msn.equals(that.msn) : that.msn != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (nation != null ? !nation.equals(that.nation) : that.nation != null) return false;
        if (nationality != null ? !nationality.equals(that.nationality) : that.nationality != null) return false;
        if (persondataintegrity != null ? !persondataintegrity.equals(that.persondataintegrity) : that.persondataintegrity != null)
            return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        if (selfinfo != null ? !selfinfo.equals(that.selfinfo) : that.selfinfo != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (workphone != null ? !workphone.equals(that.workphone) : that.workphone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (accountid != null ? accountid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (brithdate != null ? brithdate.hashCode() : 0);
        result = 31 * result + (idtype != null ? idtype.hashCode() : 0);
        result = 31 * result + (idnumber != null ? idnumber.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (birthplace != null ? birthplace.hashCode() : 0);
        result = 31 * result + (nation != null ? nation.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (graduateschool != null ? graduateschool.hashCode() : 0);
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (isemailbound != null ? isemailbound.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (isphonebound != null ? isphonebound.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (msn != null ? msn.hashCode() : 0);
        result = 31 * result + (selfinfo != null ? selfinfo.hashCode() : 0);
        result = 31 * result + (persondataintegrity != null ? persondataintegrity.hashCode() : 0);
        result = 31 * result + (departmentid != null ? departmentid.hashCode() : 0);
        result = 31 * result + (dutyid != null ? dutyid.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (workphone != null ? workphone.hashCode() : 0);
        result = 31 * result + (entrydate != null ? entrydate.hashCode() : 0);
        result = 31 * result + (entrymode != null ? entrymode.hashCode() : 0);
        result = 31 * result + (dutydataintegrity != null ? dutydataintegrity.hashCode() : 0);
        return result;
    }
}
