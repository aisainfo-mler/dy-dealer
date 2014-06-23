package com.ai.mapp.model.HW0021;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-7 下午02:41:37
 * 类说明:库存订单支付  沉淀到TIBCO
 */

public class HW0021Request extends YDBody{
	/**
	 * 订单号
	 */
	private String orderCode;
	/**
	 * 支付方式
	 */
	private String payMode;
	/**
	 * POS票号
	 */
	private String voucherNo;
	
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public String getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}
}
