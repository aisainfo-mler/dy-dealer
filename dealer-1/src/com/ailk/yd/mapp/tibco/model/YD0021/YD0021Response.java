package com.ailk.yd.mapp.tibco.model.YD0021;

import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

public class YD0021Response extends YDBody {

	private String customerId;
	private List<Account> accounts;

	public static class Service {
		private String serviceId;
		private String sericeName;
		private String serviceType;
		
		private String productCode;
		private String productName;

		public String getServiceId() {
			return serviceId;
		}

		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
		}

		public String getSericeName() {
			return sericeName;
		}

		public void setSericeName(String sericeName) {
			this.sericeName = sericeName;
		}

		public String getServiceType() {
			return serviceType;
		}

		public void setServiceType(String serviceType) {
			this.serviceType = serviceType;
		}

		public String getProductCode() {
			return productCode;
		}

		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}
	}

	public static class Account {

		private String prepaidAccountId;
		private String companyCode;
		
		/**
		 * servicePackage[].services[].identifier.value|type|name
		 */
		private List<Service> services;

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

		public List<Service> getServices() {
			return services;
		}

		public void setServices(List<Service> services) {
			this.services = services;
		}

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
