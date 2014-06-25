package com.ailk.yd.mapp.client.model;

import java.util.List;

public class TibcoAccount {

	private String prepaidAccountId;
	private String companyCode;
	
	/**
	 * servicePackage[].services[].identifier.value|type|name
	 */
	private List<TibcoService> services;

	public String getPrepaidAccountId() {
		return prepaidAccountId;
	}

	public void setPrepaidAccountId(String prepaidAccountId) {
		this.prepaidAccountId = prepaidAccountId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public List<TibcoService> getServices() {
		return services;
	}

	public void setServices(List<TibcoService> services) {
		this.services = services;
	}
	

}
