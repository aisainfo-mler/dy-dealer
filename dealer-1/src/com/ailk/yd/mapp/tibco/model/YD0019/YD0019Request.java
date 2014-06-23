package com.ailk.yd.mapp.tibco.model.YD0019;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ailk.yd.mapp.tibco.model.TibcoRequest;

/**
 * @author luyang
 * @version 创建时间：2014-4-28 下午12:34:22 类说明:从tibco的orn 接口
 */

public class YD0019Request implements TibcoRequest {

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
