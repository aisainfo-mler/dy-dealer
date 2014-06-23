package com.ailk.yd.mapp.client.model;

import java.math.BigDecimal;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-4 下午05:48:47
 * 类说明:新增代理商充值接口
 */

public class HW0027Request extends YDBody{
	/**
	 * 钱包账户名
	 */
	private String account;
	
	/**
	 * 钱包密码（MD5加密）
	 */
	private String pwd;
	
	/**
	 * 充值金额
	 */
	private BigDecimal amount;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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
