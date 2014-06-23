package com.ailk.yd.mapp.tibco.model.YD0019;

import com.ailk.yd.mapp.tibco.model.TibcoRequest;

/**
 * @author luyang
 * @version 创建时间：2014-4-28 下午12:34:22
 * 类说明:从tibco的orn 接口
 */

public class YD0019Response implements TibcoRequest{

	private String transactionRefNumber;

	public String getTransactionRefNumber() {
		return transactionRefNumber;
	}

	public void setTransactionRefNumber(String transactionRefNumber) {
		this.transactionRefNumber = transactionRefNumber;
	}
	
}
