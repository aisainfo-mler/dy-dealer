package com.ailk.yd.mapp.tibco.model.YD0012;

import java.util.Map;

import com.ailk.yd.mapp.model.YDBody;

public class YD0012Request extends YDBody {

	/**
	 * private Map 
	 */
	private String orderId;
	/**
	 * 
	 */
	private Long dealerId;
	/**
	 * 商品资料列表
	 */
	private Map<String,String> good;//(key,value)-->(goodId,goodNum)  goodId:TIBCO商品id
	
	/**
	 * 收件人姓名
	 */
	private String recipient;
	
	/**
	 * 联系电话
	 */
	private String recipientTel;
	
	/**
	 * 收件地址
	 */
	private String recipientAddress;
	
	
	
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getRecipientTel() {
		return recipientTel;
	}
	public void setRecipientTel(String recipientTel) {
		this.recipientTel = recipientTel;
	}
	public String getRecipientAddress() {
		return recipientAddress;
	}
	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}
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
	public Map<String, String> getGood() {
		return good;
	}
	public void setGood(Map<String, String> good) {
		this.good = good;
	}
}
