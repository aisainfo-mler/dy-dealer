package com.ailk.yd.mapp.client.model;

import java.util.List;
import java.util.Map;

import com.ailk.yd.mapp.model.YDBody;

public class HW0035Response extends YDBody {

	private Map<String, List<Service>> services;

	public static class Service {
		private String name;
		private String type;
		private String value;
		private String category;
		private String subCategory;
		
		private String productCode;
		private String productName;
		private String serviceStatus;
		

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getSubCategory() {
			return subCategory;
		}

		public void setSubCategory(String subCategory) {
			this.subCategory = subCategory;
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

		public String getServiceStatus() {
			return serviceStatus;
		}

		public void setServiceStatus(String serviceStatus) {
			this.serviceStatus = serviceStatus;
		}
	}

	public Map<String, List<Service>> getServices() {
		return services;
	}

	public void setServices(Map<String, List<Service>> services) {
		this.services = services;
	}
}
