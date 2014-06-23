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
	private Long productId;
	/**
	 * 总费用
	 * 
	 */
	private BigDecimal totalFee;
	/**
	 * 支付方式ID : 0 账户扣款，1 银行
	 * 
	 */
	private String payMethodId;
	/**
	 * POS票号
	 * 
	 */
	private String voucherNo;
	
	/**
	 * 校验表的id。用来查找是哪个校验码.终端调用校验接口时回传的返回值
	 */
	private String verfyId;
	
	/**
	 * 校验码
	 */
	private String verfyCode;

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

	public String getVerfyId() {
		return verfyId;
	}

	public void setVerfyId(String verfyId) {
		this.verfyId = verfyId;
	}

	public String getVerfyCode() {
		return verfyCode;
	}

	public void setVerfyCode(String verfyCode) {
		this.verfyCode = verfyCode;
	}

}
