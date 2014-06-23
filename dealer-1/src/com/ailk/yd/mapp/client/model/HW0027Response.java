package com.ailk.yd.mapp.client.model;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-4 下午05:49:29
 * 类说明:代理商充值接口
 */

public class HW0027Response extends YDBody{
	/**
	 * Tibco平台流水号 
	 */
	private String tibcoSn;

	public String getTibcoSn() {
		return tibcoSn;
	}

	public void setTibcoSn(String tibcoSn) {
		this.tibcoSn = tibcoSn;
	}
	
	
}
