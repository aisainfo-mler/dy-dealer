package com.ailk.yd.mapp.client.model;

import com.ailk.yd.mapp.model.YDBody;

public class HW0016Request extends YDBody {

    /**
     * 订单编码
     */
    private java.lang.String orderCode;

    /**
     * 支付模式
     */
    private java.lang.String payMode;

    /**
     * 金额
     */
    private java.lang.String saleFee;

    /**
     * 凭证号
     */
    private java.lang.String voucherNo;
    
    /**
     * 支付密码
     */
    private String payPwd;

	public java.lang.String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(java.lang.String orderCode) {
		this.orderCode = orderCode;
	}

	public java.lang.String getPayMode() {
		return payMode;
	}

	public void setPayMode(java.lang.String payMode) {
		this.payMode = payMode;
	}

	public java.lang.String getSaleFee() {
		return saleFee;
	}

	public void setSaleFee(java.lang.String saleFee) {
		this.saleFee = saleFee;
	}

	public java.lang.String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(java.lang.String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}
}
