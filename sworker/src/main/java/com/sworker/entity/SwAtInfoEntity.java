package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhangjin on 2014/8/21.
 */
@Entity
@Table(name = "sw_at_info")
public class SwAtInfoEntity implements Serializable{

    private static final long serialVersionUID = 42L;

    private Long id;
    private String apptypeid;
    private Long atobjid;
    private String atobjtype;
    private Long associateid;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "APPTYPEID")
    public String getApptypeid() {
        return apptypeid;
    }

    public void setApptypeid(String apptypeid) {
        this.apptypeid = apptypeid;
    }

    @Basic
    @Column(name = "ATOBJID")
    public Long getAtobjid() {
        return atobjid;
    }

    public void setAtobjid(Long atobjid) {
        this.atobjid = atobjid;
    }

    @Basic
    @Column(name = "ATOBJTYPE")
    public String getAtobjtype() {
        return atobjtype;
    }

    public void setAtobjtype(String atobjtype) {
        this.atobjtype = atobjtype;
    }

    @Basic
    @Column(name = "ASSOCIATEID")
    public Long getAssociateid() {
        return associateid;
    }

    public void setAssociateid(Long associateid) {
        this.associateid = associateid;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        SwAtInfoEntity that = (SwAtInfoEntity) o;
//
//        if (id != that.id) return false;
//        if (apptypeid != null ? !apptypeid.equals(that.apptypeid) : that.apptypeid != null) return false;
//        if (associateid != null ? !associateid.equals(that.associateid) : that.associateid != null) return false;
//        if (atobjid != null ? !atobjid.equals(that.atobjid) : that.atobjid != null) return false;
//        if (atobjtype != null ? !atobjtype.equals(that.atobjtype) : that.atobjtype != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id;
//        result = 31 * result + (apptypeid != null ? apptypeid.hashCode() : 0);
//        result = 31 * result + (atobjid != null ? atobjid.hashCode() : 0);
//        result = 31 * result + (atobjtype != null ? atobjtype.hashCode() : 0);
//        result = 31 * result + (associateid != null ? associateid.hashCode() : 0);
//        return result;
//    }
}
