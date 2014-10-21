package com.sworker.model;

import java.util.Date;


public class EnterprisePointParam {

	private Long enterpriseid;
	private Long point;
	//等级
	private Integer level;
	private Long growthvalue;
	private Long totalpoint;
	private Long expiredpoint;
	private Date enddate;
	
	public EnterprisePointParam(){
		
	}
	
	
	public EnterprisePointParam(Long enterpriseid, Long point, Integer level,
			Long growthvalue, Long totalpoint, Long expiredpoint, Date enddate) {
		super();
		this.enterpriseid = enterpriseid;
		this.point = point;
		this.level = level;
		this.growthvalue = growthvalue;
		this.totalpoint = totalpoint;
		this.expiredpoint = expiredpoint;
		this.enddate = enddate;
	}
	public Long getEnterpriseid() {
		return enterpriseid;
	}
	public void setEnterpriseid(Long enterpriseid) {
		this.enterpriseid = enterpriseid;
	}
	public Long getPoint() {
		return point;
	}
	public void setPoint(Long point) {
		this.point = point;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Long getGrowthvalue() {
		return growthvalue;
	}
	public void setGrowthvalue(Long growthvalue) {
		this.growthvalue = growthvalue;
	}
	public Long getTotalpoint() {
		return totalpoint;
	}
	public void setTotalpoint(Long totalpoint) {
		this.totalpoint = totalpoint;
	}
	public Long getExpiredpoint() {
		return expiredpoint;
	}
	public void setExpiredpoint(Long expiredpoint) {
		this.expiredpoint = expiredpoint;
	}

	public Date getEnddate() {
		return enddate;
	}


	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	
	
}
