package com.ailk.yd.mapp.client.model;

import java.math.BigDecimal;

import com.ailk.yd.mapp.model.YDBody;

public class HW0032Request extends YDBody {

	
	/**
	 * 代理商ID号
	 */
	private Long dealerId;
	
	/**
	 * 手机号
	 */
	private String mdn;
	
	/**
	 * 用户付款金额
	 */
	private BigDecimal amount;
	
	/**
	 * 充值方式：0 直冲，1 pin码充值
	 */
	private String mode;


	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}
