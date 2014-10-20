package com.sworker.model;
/**
 * 
 * @author wanggang 2014/09/15
 *用于企业等级服务查询企业积分
 */
public class EnterpriseRankParam {
	//企业id
	private Long enterpriseid;
	//企业积分等级
	private Long point;

	public EnterpriseRankParam(){
		
		
		
	}
	
	public EnterpriseRankParam(Long enterpriseid, Long point) {
		super();
		this.enterpriseid = enterpriseid;
		this.point = point;
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
	
	
	
	
	
	
}
