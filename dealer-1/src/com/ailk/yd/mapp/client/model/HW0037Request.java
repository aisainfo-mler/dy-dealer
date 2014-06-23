package com.ailk.yd.mapp.client.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ailk.yd.mapp.model.YDBody;

public class HW0037Request extends YDBody {

	private String transactionType;

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Map returnGetParam() {

		Map rm = new HashMap();
		if (StringUtils.isNotBlank(transactionType)) {
			rm.put("transactionType", transactionType);
		}
		return rm;

	}

}
