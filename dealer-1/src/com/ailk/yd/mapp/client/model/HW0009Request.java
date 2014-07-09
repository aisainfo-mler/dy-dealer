package com.ailk.yd.mapp.client.model;

import com.ailk.yd.mapp.model.YDBody;

public class HW0009Request extends YDBody {
	private java.lang.String mdn;

	private java.lang.String rechargeTypeId;

	private java.lang.String payedAmount;

	private java.lang.String payMethodId;

	/**
	 * tibco的订单编号。
	 */
	private String  ornNum;
	
	public java.lang.String getMdn() {
		return mdn;
	}

	public void setMdn(java.lang.String mdn) {
		this.mdn = mdn;
	}

	public java.lang.String getRechargeTypeId() {
		return rechargeTypeId;
	}

	public void setRechargeTypeId(java.lang.String rechargeTypeId) {
		this.rechargeTypeId = rechargeTypeId;
	}

	public java.lang.String getPayedAmount() {
		return payedAmount;
	}

	public void setPayedAmount(java.lang.String payedAmount) {
		this.payedAmount = payedAmount;
	}

	public java.lang.String getPayMethodId() {
		return payMethodId;
	}

	public void setPayMethodId(java.lang.String payMethodId) {
		this.payMethodId = payMethodId;
	}

	public String getOrnNum() {
		return ornNum;
	}

	public void setOrnNum(String ornNum) {
		this.ornNum = ornNum;
	}

}
