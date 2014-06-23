package com.ailk.yd.mapp.client.model;

import com.ailk.yd.mapp.model.YDBody;

public class HW0032Response extends YDBody {
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
