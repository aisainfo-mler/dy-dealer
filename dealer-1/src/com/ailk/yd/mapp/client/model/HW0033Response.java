package com.ailk.yd.mapp.client.model;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-13 下午06:06:15
 * 类说明:版本升级
 */

public class HW0033Response extends YDBody{
	/**
	 * 版本号
	 */
	private String clientVersion;

	/**
	 * 最低版本号
	 */
	private String requireVersion;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 升级地址
	 */
	private String updateUrl;
	
	public String getClientVersion() {
		return clientVersion;
	}
	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}
	public String getRequireVersion() {
		return requireVersion;
	}
	public void setRequireVersion(String requireVersion) {
		this.requireVersion = requireVersion;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUpdateUrl() {
		return updateUrl;
	}
	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}
}
