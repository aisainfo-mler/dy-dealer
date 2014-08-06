package com.ailk.yd.mapp.client.model;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-8-4 下午03:49:35
 * 类说明:
 */

public class HW0043Request extends YDBody {
	
	/**
	 * 1:state,2:district,3:city
	 */
	private String queryType;
	
	/**
	 * queryType=1:null
	 * queryType-2:stateCode
	 * queryType=3:district
	 */
	private String upKeyCode;
	
	/**
	 * selfKeyCode--to get name
	 */
	private String selfKeyCode;
	
	/**
	 * 查询地址  0:表示数据库   1：表示缓存   默认1
	 */
	private String queryPlace;
	
	/**
	 * 默认取上一级第一个参数的子节点
	 * 	0:否  1：是   默认0 
	 */
	private String defaultChildren;

	
	
	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getUpKeyCode() {
		return upKeyCode;
	}

	public void setUpKeyCode(String upKeyCode) {
		this.upKeyCode = upKeyCode;
	}

	public String getSelfKeyCode() {
		return selfKeyCode;
	}

	public void setSelfKeyCode(String selfKeyCode) {
		this.selfKeyCode = selfKeyCode;
	}

	public String getQueryPlace() {
		return queryPlace;
	}

	public void setQueryPlace(String queryPlace) {
		this.queryPlace = queryPlace;
	}

	public String getDefaultChildren() {
		return defaultChildren;
	}

	public void setDefaultChildren(String defaultChildren) {
		this.defaultChildren = defaultChildren;
	}
	
}
