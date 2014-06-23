package com.ailk.yd.mapp.client.model;

import java.util.HashMap;
import java.util.Map;

import com.ailk.yd.mapp.model.YDBody;

public class HW0035Request extends YDBody {
	private String customerId;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@SuppressWarnings("rawtypes")
	public Map returnGetParam() {
		Map m = new HashMap();
		m.put("customerId", customerId);
		return m;
	}

}
