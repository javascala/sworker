package com.sworker.model;

/**
 * 
 * @author wanggang 2014/09/17
 *用于EnterprisePointLogic savePointForCompany方法
 */
public class PointParamInfo {
	
	//是否保存成功
	private Boolean flag;
	//返回保存信息
	private String msg;
	
	
	public PointParamInfo(){
		
		
	}
	
	public PointParamInfo(Boolean flag, String msg) {
		super();
		this.flag = flag;
		this.msg = msg;
	}
	
	
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	
	
}
