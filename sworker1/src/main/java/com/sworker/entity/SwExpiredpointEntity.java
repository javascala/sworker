package com.sworker.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhangjin on 2014/9/16.
 */
@Entity
@Table(name = "sw_expiredpoint")
public class SwExpiredpointEntity implements Serializable{
    private Long id;
    private Long enterpriseid;
    private Long expiredpoint;
    private Date enddate;

    private SwEnterpriseInfoEntity enterprise;
    
    
	public SwExpiredpointEntity() {

	
	}

	public SwExpiredpointEntity(Long enterpriseid, Long expiredpoint,
			Date enddate) {
		super();
		this.enterpriseid = enterpriseid;
		this.expiredpoint = expiredpoint;
		this.enddate = enddate;
	}

	
	
	@ManyToOne(optional=true)
    @JoinColumn(name = "enterpriseid",insertable=false,updatable=false)
    public SwEnterpriseInfoEntity getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(SwEnterpriseInfoEntity enterprise) {
		this.enterprise = enterprise;
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
    @Column(name="POINT")
    public Long getExpiredpoint() {
		return expiredpoint;
	}

	public void setExpiredpoint(Long expiredpoint) {
		this.expiredpoint = expiredpoint;
	}

	
	@Basic
    @Column(name = "ENDDATE")
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwExpiredpointEntity that = (SwExpiredpointEntity) o;

        if (enddate != null ? !enddate.equals(that.enddate) : that.enddate != null) return false;
        if (enterpriseid != null ? !enterpriseid.equals(that.enterpriseid) : that.enterpriseid != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (expiredpoint != null ? !expiredpoint.equals(that.expiredpoint) : that.expiredpoint != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result = 31 * result + (expiredpoint != null ? expiredpoint.hashCode() : 0);
        result = 31 * result + (enddate != null ? enddate.hashCode() : 0);
        return result;
    }
}
