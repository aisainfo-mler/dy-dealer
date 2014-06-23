package com.ailk.yd.mapp.tibco.model.YD0016;

import java.math.BigDecimal;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author luyang
 * @version 创建时间：2014-4-28 下午12:34:22
 * 类说明:叠加包请求接口
 */

public class YD0016Request extends YDBody{

//	Dealer平台的订单流水号
	private String orderId;
//	代理商ID
	private Long dealerId;
//	手机号
	private String mdn;
//	TIBCO合约ID(即叠加包产品ID)
	private Long productId;
//	总费用
	private BigDecimal totalFee;
//	支付方式ID : 0  账户扣款，1 银行
	private String payMethodId;
//	POS票号
	private String voucherNo;
	
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
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	public String getPayMethodId() {
		return payMethodId;
	}
	public void setPayMethodId(String payMethodId) {
		this.payMethodId = payMethodId;
	}
	public String getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}
	
	
}
