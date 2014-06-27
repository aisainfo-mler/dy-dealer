package com.ailk.yd.mapp.tibco.model.YD0022;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ailk.yd.mapp.tibco.model.TibcoRequest;

public class YD0022Request implements TibcoRequest {
	/*
	 * customerId=1100009566&serviceId=&technicalId=
	 */
	private String customerId;
	private String serviceId;
	private String technicalId;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getTechnicalId() {
		return technicalId;
	}

	public void setTechnicalId(String technicalId) {
		this.technicalId = technicalId;
	}

	public Map returnGetParam() {
		Map rm = new HashMap();
		if (StringUtils.isNotBlank(customerId)) {
			rm.put("customerId", customerId);
		}
		if (StringUtils.isNotBlank(serviceId)) {
			rm.put("serviceId", serviceId);
		}
		if (StringUtils.isNotBlank(technicalId)) {
			rm.put("technicalId", technicalId);
		}
		return rm;
	}

}
