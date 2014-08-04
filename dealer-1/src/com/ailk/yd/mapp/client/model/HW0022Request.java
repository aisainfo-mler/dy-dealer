package com.ailk.yd.mapp.client.model;

import com.ailk.yd.mapp.model.YDBody;

/**
 * 
 * @author xuzhou
 * 
 */

public class HW0022Request extends YDBody {

	private java.lang.String startTime;

	private java.lang.String endTime;

	private java.lang.String userCode;

	private java.lang.String commissionType;

	private java.lang.String orderType;

	private java.lang.String payStatus;

	public java.lang.String getStartTime() {
		return startTime;
	}

	public void setStartTime(java.lang.String startTime) {
		this.startTime = startTime;
	}

	public java.lang.String getEndTime() {
		return endTime;
	}

	public void setEndTime(java.lang.String endTime) {
		this.endTime = endTime;
	}

	public java.lang.String getUserCode() {
		return userCode;
	}

	public void setUserCode(java.lang.String userCode) {
		this.userCode = userCode;
	}

	public java.lang.String getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(java.lang.String commissionType) {
		this.commissionType = commissionType;
	}

	public java.lang.String getOrderType() {
		return orderType;
	}

	public void setOrderType(java.lang.String orderType) {
		this.orderType = orderType;
	}

	public java.lang.String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(java.lang.String payStatus) {
		this.payStatus = payStatus;
	}

}
