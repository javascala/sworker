package com.sworker.model;
/**
 * 
 * @author wanggang 2014/9/03.
 *用于RoleDao 中findParam方法将数据库中查询的字段包装成一个类
 *
 */
public class RoleParam {
		//角色类型
		private Integer roletype;
		//角色状态
		private Integer status;
		
		
		public RoleParam(){
		
		}

		public RoleParam(Integer roletype, Integer status) {
			super();
			this.roletype = roletype;
			this.status = status;
		}

		public Integer getRoletype() {
			return roletype;
		}


		public void setRoletype(Integer roletype) {
			this.roletype = roletype;
		}


		public Integer getStatus() {
			return status;
		}


		public void setStatus(Integer status) {
			this.status = status;
		}
		
}
