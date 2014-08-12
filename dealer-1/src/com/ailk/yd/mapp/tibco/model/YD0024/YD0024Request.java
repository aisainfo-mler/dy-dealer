package com.ailk.yd.mapp.tibco.model.YD0024;

import com.ailk.yd.mapp.tibco.model.TibcoRequest;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-8-12 下午03:50:03
 * 类说明:
 */

public class YD0024Request implements TibcoRequest {
	/**
	 * just orn
	 */
	private String orderReferenceNumber;
	
	/**
	 * Generated Order Number
	 * Or Generated Opportunity Number when Order Type = DRAFT is specified
	 */
	private String orderNumber;
	
	/**
	 * DRAFT INTERIM
	 * To be specified only when details of DRAFT Orders need to be returned otherwise not applicable
	 */
	private String orderType;
	
	/**
	 * Customer Identifier (Business Partner ID)
	 * Mandatory to fetch the Interim Order
	 */
	private String customerUniqueReferenceID;
	
	/**
	 * Calling Party Number from which Televerification is initiated.
	 * This can be:
	 * 1. Service ID (MSISDN Number) for the Voice Services for which televerification is pending
	 * 2. Registered Mobile Number for non-Voice services for which televertication is pending
	 */
	private String callingPartyNumber;
	
	/**
	 * 0 - Return Order details
	 * 1 - Return Order details with CAF
	 * 2 - Return Order Details with CAF and Customer details
	 * default - 0
	 * default - 1 for Draft Orders
	 * 0 - Not applicable for Draft Orders
	 */
	private String returnCompleteOrder;

	public String getOrderReferenceNumber() {
		return orderReferenceNumber;
	}

	public void setOrderReferenceNumber(String orderReferenceNumber) {
		this.orderReferenceNumber = orderReferenceNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getCustomerUniqueReferenceID() {
		return customerUniqueReferenceID;
	}

	public void setCustomerUniqueReferenceID(String customerUniqueReferenceID) {
		this.customerUniqueReferenceID = customerUniqueReferenceID;
	}

	public String getCallingPartyNumber() {
		return callingPartyNumber;
	}

	public void setCallingPartyNumber(String callingPartyNumber) {
		this.callingPartyNumber = callingPartyNumber;
	}

	public String getReturnCompleteOrder() {
		return returnCompleteOrder;
	}

	public void setReturnCompleteOrder(String returnCompleteOrder) {
		this.returnCompleteOrder = returnCompleteOrder;
	}
	
}
