package com.ailk.yd.mapp.tibco.model.YD0009;

import java.math.BigDecimal;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-4-28 下午06:20:15
 * 类说明:充值
 */

public class YD0009Request extends YDBody {
	/**
	 * 本地订单流水号
	 */
	private String orderId;
	
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

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
