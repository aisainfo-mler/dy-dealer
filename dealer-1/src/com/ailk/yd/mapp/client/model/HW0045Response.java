package com.ailk.yd.mapp.client.model;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-8-14 上午01:57:31
 * 类说明:
 */

public class HW0045Response extends YDBody {
	
	/**
	 * 服务器上最新版本
	 */
	private String lastVersion;
	
	/**
	 * 服务器上最低版本，若客户端低于这个版本，就强制升级
	 */
	private String compatibleVersion;
	
	/**
	 * 更新地址
	 */
	private String updateURL;
	
	/**
	 * APP简要介绍
	 */
	private String introduction;
	
	/**
	 * 版本更新的问题
	 */
	private String updateIssue;
	
	
	public String getLastVersion() {
		return lastVersion;
	}
	public void setLastVersion(String lastVersion) {
		this.lastVersion = lastVersion;
	}
	public String getCompatibleVersion() {
		return compatibleVersion;
	}
	public void setCompatibleVersion(String compatibleVersion) {
		this.compatibleVersion = compatibleVersion;
	}
	public String getUpdateURL() {
		return updateURL;
	}
	public void setUpdateURL(String updateURL) {
		this.updateURL = updateURL;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getUpdateIssue() {
		return updateIssue;
	}
	public void setUpdateIssue(String updateIssue) {
		this.updateIssue = updateIssue;
	}
}
