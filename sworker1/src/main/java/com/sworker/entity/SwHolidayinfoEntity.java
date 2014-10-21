package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by wangying on 2014/9/17.
 */
@Entity
@Table(name = "sw_holidayinfo")
public class SwHolidayinfoEntity implements Serializable {
    private long id;
    private String holidayname;
    private Integer country;
    private Date dateinfo;
    private Integer holidaytype;
    private String operateid;
    private Timestamp operatetime;

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
    @Column(name = "HOLIDAYNAME")
    public String getHolidayname() {
        return holidayname;
    }

    public void setHolidayname(String holidayname) {
        this.holidayname = holidayname;
    }

    @Basic
    @Column(name = "COUNTRY")
    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    @Basic
    @Column(name = "DATEINFO")
    public Date getDateinfo() {
        return dateinfo;
    }

    public void setDateinfo(Date dateinfo) {
        this.dateinfo = dateinfo;
    }

    @Basic
    @Column(name = "HOLIDAYTYPE")
    public Integer getHolidaytype() {
        return holidaytype;
    }

    public void setHolidaytype(Integer holidaytype) {
        this.holidaytype = holidaytype;
    }

    @Basic
    @Column(name = "OPERATEID")
    public String getOperateid() {
        return operateid;
    }

    public void setOperateid(String operateid) {
        this.operateid = operateid;
    }

    @Basic
    @Column(name = "OPERATETIME")
    public Timestamp getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Timestamp operatetime) {
        this.operatetime = operatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwHolidayinfoEntity that = (SwHolidayinfoEntity) o;

        if (id != that.id) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (dateinfo != null ? !dateinfo.equals(that.dateinfo) : that.dateinfo != null) return false;
        if (holidayname != null ? !holidayname.equals(that.holidayname) : that.holidayname != null) return false;
        if (holidaytype != null ? !holidaytype.equals(that.holidaytype) : that.holidaytype != null) return false;
        if (operateid != null ? !operateid.equals(that.operateid) : that.operateid != null) return false;
        if (operatetime != null ? !operatetime.equals(that.operatetime) : that.operatetime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (holidayname != null ? holidayname.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (dateinfo != null ? dateinfo.hashCode() : 0);
        result = 31 * result + (holidaytype != null ? holidaytype.hashCode() : 0);
        result = 31 * result + (operateid != null ? operateid.hashCode() : 0);
        result = 31 * result + (operatetime != null ? operatetime.hashCode() : 0);
        return result;
    }
}
