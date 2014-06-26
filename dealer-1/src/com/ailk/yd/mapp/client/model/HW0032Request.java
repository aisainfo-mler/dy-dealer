package com.ailk.yd.mapp.client.model;

import java.math.BigDecimal;

import com.ailk.yd.mapp.model.YDBody;

public class HW0032Request extends YDBody {

	private String seriveId;
	private String amount;
	private String rrfId;
	private Boolean accountLevel; 
	public String getSeriveId() {
		return seriveId;
	}
	public void setSeriveId(String seriveId) {
		this.seriveId = seriveId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRrfId() {
		return rrfId;
	}
	public void setRrfId(String rrfId) {
		this.rrfId = rrfId;
	}
	public Boolean getAccountLevel() {
		return accountLevel;
	}
	public void setAccountLevel(Boolean accountLevel) {
		this.accountLevel = accountLevel;
	}
	
}
