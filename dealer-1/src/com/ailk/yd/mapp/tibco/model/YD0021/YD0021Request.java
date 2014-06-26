package com.ailk.yd.mapp.tibco.model.YD0021;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ailk.yd.mapp.model.YDBody;

public class YD0021Request extends YDBody {
	private String customerId;
	private String serviceId;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	@SuppressWarnings("rawtypes")
	public Map returnGetParam(){
		Map m = new HashMap();
		if(StringUtils.isNotBlank(customerId))
			m.put("customerId", customerId);
		else if(StringUtils.isNotBlank(serviceId))
			m.put("serviceId", serviceId);
		return m;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
}
