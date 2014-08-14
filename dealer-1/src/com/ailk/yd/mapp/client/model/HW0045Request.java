package com.ailk.yd.mapp.client.model;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-8-14 上午01:57:06
 * 类说明:
 */

public class HW0045Request extends YDBody {
	/**
	 * 客户端版本
	 */
	private String clientVersion;
	
	/**
	 * 手机品牌
	 */
	private String hardwareBrand;
	
	/**
	 * 手机型号
	 */
	private String hardwareModel;
	
	/**
	 * 手机操作系统  ios,android,wp
	 */
	private String os;
	
	/**
	 * 系统版本号
	 */
	private String osVersion;
	
	/**
	 * 关键字
	 */
	private String itemKey;

	
	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getHardwareBrand() {
		return hardwareBrand;
	}

	public void setHardwareBrand(String hardwareBrand) {
		this.hardwareBrand = hardwareBrand;
	}

	public String getHardwareModel() {
		return hardwareModel;
	}

	public void setHardwareModel(String hardwareModel) {
		this.hardwareModel = hardwareModel;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}
	
}
