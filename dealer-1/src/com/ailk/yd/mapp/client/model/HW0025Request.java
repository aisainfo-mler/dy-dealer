package com.ailk.yd.mapp.client.model;

import java.math.BigDecimal;

import com.ailk.yd.mapp.model.YDBody;

public class HW0025Request extends YDBody {

	/**
	 * 手机号
	 * 
	 */
	private String mdn;
	/**
	 * TIBCO合约ID(即叠加包产品ID)
	 * 
	 */
	private String productId;
	/**
	 * 总费用
	 * 
	 */
	private String totalFee;
	/**
	 * 支付方式ID : 0 账户扣款，1 银行
	 * 
	 */
	private String payMethodId;
	
	/**
	 * 印度的tibco流水号
	 */
	private String refNo;

	public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}


	public String getPayMethodId() {
		return payMethodId;
	}

	public void setPayMethodId(String payMethodId) {
		this.payMethodId = payMethodId;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}


}
