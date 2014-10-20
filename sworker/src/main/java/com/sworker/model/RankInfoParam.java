package com.sworker.model;
/**
 * 
 * @author wanggang 2014/09/11
 *用于EnterpriseRankLogic类中createRank方法，封装返回信息，返回主键
 *
 */

public class RankInfoParam {
	
	//保存结果信息
	private String msg;
	//企业等级
	private Integer rank;
	
	public RankInfoParam(){
		
		
	}
	
	public RankInfoParam(String msg, Integer rank) {
		super();
		this.msg = msg;
		this.rank = rank;
	}
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
}
