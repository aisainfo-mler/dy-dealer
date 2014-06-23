package com.ailk.yd.mapp.tibco.model.YD0009;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-4-28 下午06:20:25
 * 类说明:充值
 */

public class YD0009Response extends YDBody {
	/**
	 * TIBCO生成的流水号
	 */
	private String sn;
	
	/**
	 * pin码
	 */
	private String pin;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
	
	
}
