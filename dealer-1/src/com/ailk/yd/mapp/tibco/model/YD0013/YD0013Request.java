package com.ailk.yd.mapp.tibco.model.YD0013;

import java.math.BigDecimal;

import com.ailk.yd.mapp.model.YDBody;

public class YD0013Request extends YDBody {

	/**
	 * Dealer的id
	 */
	private Long dealerId;
	/**
	 * 钱包账户名
	 */
	private String accountId;
	/**
	 * 钱包密码（MD5加密）
	 */
	private String pwd;
	/**
	 * 充值金额
	 */
	private BigDecimal amount;
	public Long getDealerId() {
		return dealerId;
	}
	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}
	public String getAccount() {
		return accountId;
	}
	public void setAccount(String account) {
		this.accountId = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
