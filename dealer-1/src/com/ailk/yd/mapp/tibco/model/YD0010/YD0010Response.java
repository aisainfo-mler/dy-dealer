package com.ailk.yd.mapp.tibco.model.YD0010;

import com.ailk.yd.mapp.model.YDBody;
import com.ailk.yd.mapp.tibco.model.TibcoRequest;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-4-28 下午06:27:42
 * 类说明:
 */

//{
//    "orderNumber": "",
//    "orderReferenceNumber": "OT00000000HL",
//    "orderEntryDateAndTimeStamp": "",
//    "orderDetails": []
//}

//{
//    "success": false,
//    "general_message": "This is an operation implementation generated fault",
//    "errors": {
//        "submitCustomerOrderExc"
//    }
//}

public class YD0010Response implements TibcoRequest {

	private String orderNumber;
	private String orderReferenceNumber;
	private String  orderEntryDateAndTimeStamp;
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderReferenceNumber() {
		return orderReferenceNumber;
	}
	public void setOrderReferenceNumber(String orderReferenceNumber) {
		this.orderReferenceNumber = orderReferenceNumber;
	}
	public String getOrderEntryDateAndTimeStamp() {
		return orderEntryDateAndTimeStamp;
	}
	public void setOrderEntryDateAndTimeStamp(String orderEntryDateAndTimeStamp) {
		this.orderEntryDateAndTimeStamp = orderEntryDateAndTimeStamp;
	}
	
	
	 
}
