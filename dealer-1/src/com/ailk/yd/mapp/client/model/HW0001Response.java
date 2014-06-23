package com.ailk.yd.mapp.client.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ailk.yd.mapp.model.YDBody;

public class HW0001Response extends YDBody {
	
	private List<Product> products;
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public static class Product extends YDBody {
		
		private Long id;
		private String code;
		private String name;
		private String type;
		private BigDecimal price;
		private List<Product> planList;
		private List<ProductSpec> productSpecList;
		private List<PlanSpec> planSpecList;
		private Map<String,ResourceSpec> resourceMap;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
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
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public List<Product> getPlanList() {
			return planList;
		}
		public void setPlanList(List<Product> planList) {
			this.planList = planList;
		}
		public List<ProductSpec> getProductSpecList() {
			return productSpecList;
		}
		public void setProductSpecList(List<ProductSpec> productSpecList) {
			this.productSpecList = productSpecList;
		}
		public Map<String, ResourceSpec> getResourceMap() {
			return resourceMap;
		}
		public void setResourceMap(Map<String, ResourceSpec> resourceMap) {
			this.resourceMap = resourceMap;
		}
		public List<PlanSpec> getPlanSpecList() {
			return planSpecList;
		}
		public void setPlanSpecList(List<PlanSpec> planSpecList) {
			this.planSpecList = planSpecList;
		}
	}
	
	/**
	public static class Plan extends YDBody {
		
		private Long id;
		private String code;
		private String name;
		private String type;
		private BigDecimal price;
		private List<PlanSpec> planSpecList;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
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
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public List<PlanSpec> getPlanSpecList() {
			return planSpecList;
		}
		public void setPlanSpecList(List<PlanSpec> planSpecList) {
			this.planSpecList = planSpecList;
		}
		
	}
	**/
	public static class PlanSpec extends YDBody {
		
		private String code;
		private String name;
		private String type;
		private BigDecimal price;
		private List<PlanSpecChar> planSpecCharList;
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
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
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		
		public List<PlanSpecChar> getPlanSpecCharList() {
			return planSpecCharList;
		}
		public void setPlanSpecCharList(List<PlanSpecChar> planSpecCharList) {
			this.planSpecCharList = planSpecCharList;
		}
		
	}
	
	public static class ProductSpec extends YDBody {
		private String code;
		private String name;
		private String type;
		private BigDecimal price;
		private String checkServiceAvailability;
		private String isCAFRequired;
		private List<ServiceSpec> serviceSpecList;
		private List<ResourceSpec> resourceSpecList;
	
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
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
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public List<ServiceSpec> getServiceSpecList() {
			return serviceSpecList;
		}
		public void setServiceSpecList(List<ServiceSpec> serviceSpecList) {
			this.serviceSpecList = serviceSpecList;
		}
		public List<ResourceSpec> getResourceSpecList() {
			return resourceSpecList;
		}
		public void setResourceSpecList(List<ResourceSpec> resourceSpecList) {
			this.resourceSpecList = resourceSpecList;
		}
		public String getCheckServiceAvailability() {
			return checkServiceAvailability;
		}
		public void setCheckServiceAvailability(String checkServiceAvailability) {
			this.checkServiceAvailability = checkServiceAvailability;
		}
		public String getIsCAFRequired() {
			return isCAFRequired;
		}
		public void setIsCAFRequired(String isCAFRequired) {
			this.isCAFRequired = isCAFRequired;
		}
	}
	
	public static class ResourceSpec extends YDBody {
		private String code;
		private String name;
		private String type;
		private BigDecimal price;
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
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
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		
	}
	
	public static class ServiceSpec extends YDBody {
		private String code;
		private String name;
		private String type;
		private BigDecimal price;
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
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
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		
		
	}
	
	public static class PlanSpecChar extends YDBody {
		private String code;
		private String name;
		private String type;
		private BigDecimal price;
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
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
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		
	}
	
}
