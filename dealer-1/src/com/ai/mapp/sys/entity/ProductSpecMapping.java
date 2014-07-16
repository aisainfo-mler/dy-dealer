package com.ai.mapp.sys.entity;

import java.util.Date;
import java.util.List;

import com.ai.mapp.sys.entity.GeographicLocationMapping.GeographicLocation;
import com.ailk.butterfly.mapp.core.model.IBody;

public class ProductSpecMapping  extends IBody {

	private List<ProductSpec> productSpecs;
	
	public List<ProductSpec> getProductSpecs() {
		return productSpecs;
	}

	public void setProductSpecs(List<ProductSpec> productSpecs) {
		this.productSpecs = productSpecs;
	}

	public static class Identifier  extends IBody  {
		
		private String id;
		private String name;
		private String value;
		private String type;
		private String category;
		private String subCategory;
		private String isCustomerSelectable;
		private String status;
		private String componentPriceId;
		private String componentPrice;
//		private String productSpecificationId;
//		private String resourceSpecificationId;
//		private String serviceSpecificationId;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
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
		public String getIsCustomerSelectable() {
			return isCustomerSelectable;
		}
		public void setIsCustomerSelectable(String isCustomerSelectable) {
			this.isCustomerSelectable = isCustomerSelectable;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getComponentPrice() {
			return componentPrice;
		}
		public void setComponentPrice(String componentPrice) {
			this.componentPrice = componentPrice;
		}
		public String getComponentPriceId() {
			return componentPriceId;
		}
		public void setComponentPriceId(String componentPriceId) {
			this.componentPriceId = componentPriceId;
		}
	}
	
	public static class ProductSpec  extends IBody {
	
		private String id;
		private String name;
		private String description;
		private String status;
		private Date startDate;
		private Date endDate;
		private String companyCode;
		private String type;
		private String accessTechnology;
		private String isCafRequired;
		private String productFamily;
		private String checkServiceAvailability;
		private String isTeleverificationRequired;
		private String isInstallationRequired;
		private String billingType;
		private String isProvisioningRequired;
		private String isActivationRequired;
		private String isOCSRequired;
		private String serviceType;
		private List<ProductSpecCharacteristic> productSpecCharList;
		private List<ResourceSpec> resourceSpecList;
		private GeographicLocationMapping geographicLocationMapping;
		private List<ServiceSpec> serviceSpecList;
		private String compositePrice;
		
		private String mappingTtype;
		private String productSpecificationId;
		private String minValue;
		private String maxValue;
		private String associationType;
		private String componentPrice;
		
		private List<Identifier> identifiers;

		public List<Identifier> getIdentifiers() {
			return identifiers;
		}

		public void setIdentifiers(List<Identifier> identifiers) {
			this.identifiers = identifiers;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		public String getCompanyCode() {
			return companyCode;
		}

		public void setCompanyCode(String companyCode) {
			this.companyCode = companyCode;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getAccessTechnology() {
			return accessTechnology;
		}

		public void setAccessTechnology(String accessTechnology) {
			this.accessTechnology = accessTechnology;
		}

		public String getIsCafRequired() {
			return isCafRequired;
		}

		public void setIsCafRequired(String isCafRequired) {
			this.isCafRequired = isCafRequired;
		}

		public String getProductFamily() {
			return productFamily;
		}

		public void setProductFamily(String productFamily) {
			this.productFamily = productFamily;
		}

		public String getCheckServiceAvailability() {
			return checkServiceAvailability;
		}

		public void setCheckServiceAvailability(String checkServiceAvailability) {
			this.checkServiceAvailability = checkServiceAvailability;
		}

		public String getIsTeleverificationRequired() {
			return isTeleverificationRequired;
		}

		public void setIsTeleverificationRequired(String isTeleverificationRequired) {
			this.isTeleverificationRequired = isTeleverificationRequired;
		}

		public String getIsInstallationRequired() {
			return isInstallationRequired;
		}

		public void setIsInstallationRequired(String isInstallationRequired) {
			this.isInstallationRequired = isInstallationRequired;
		}

		public String getBillingType() {
			return billingType;
		}

		public void setBillingType(String billingType) {
			this.billingType = billingType;
		}

		public String getIsProvisioningRequired() {
			return isProvisioningRequired;
		}

		public void setIsProvisioningRequired(String isProvisioningRequired) {
			this.isProvisioningRequired = isProvisioningRequired;
		}

		public String getIsActivationRequired() {
			return isActivationRequired;
		}

		public void setIsActivationRequired(String isActivationRequired) {
			this.isActivationRequired = isActivationRequired;
		}

		public String getIsOCSRequired() {
			return isOCSRequired;
		}

		public void setIsOCSRequired(String isOCSRequired) {
			this.isOCSRequired = isOCSRequired;
		}

		public String getServiceType() {
			return serviceType;
		}

		public void setServiceType(String serviceType) {
			this.serviceType = serviceType;
		}

		public List<ProductSpecCharacteristic> getProductSpecCharList() {
			return productSpecCharList;
		}

		public void setProductSpecCharList(
				List<ProductSpecCharacteristic> productSpecCharList) {
			this.productSpecCharList = productSpecCharList;
		}

		public List<ResourceSpec> getResourceSpecList() {
			return resourceSpecList;
		}

		public void setResourceSpecList(List<ResourceSpec> resourceSpecList) {
			this.resourceSpecList = resourceSpecList;
		}

		public GeographicLocationMapping getGeographicLocationMapping() {
			return geographicLocationMapping;
		}

		public void setGeographicLocationMapping(
				GeographicLocationMapping geographicLocationMapping) {
			this.geographicLocationMapping = geographicLocationMapping;
		}

		public String getComponentPrice() {
			return componentPrice;
		}

		public void setComponentPrice(String componentPrice) {
			this.componentPrice = componentPrice;
		}

		public List<ServiceSpec> getServiceSpecList() {
			return serviceSpecList;
		}

		public void setServiceSpecList(List<ServiceSpec> serviceSpecList) {
			this.serviceSpecList = serviceSpecList;
		}

		public String getCompositePrice() {
			return compositePrice;
		}

		public void setCompositePrice(String compositePrice) {
			this.compositePrice = compositePrice;
		}

		public String getMappingTtype() {
			return mappingTtype;
		}

		public void setMappingTtype(String mappingTtype) {
			this.mappingTtype = mappingTtype;
		}

		public String getProductSpecificationId() {
			return productSpecificationId;
		}

		public void setProductSpecificationId(String productSpecificationId) {
			this.productSpecificationId = productSpecificationId;
		}

		public String getMinValue() {
			return minValue;
		}

		public void setMinValue(String minValue) {
			this.minValue = minValue;
		}

		public String getMaxValue() {
			return maxValue;
		}

		public void setMaxValue(String maxValue) {
			this.maxValue = maxValue;
		}

		public String getAssociationType() {
			return associationType;
		}

		public void setAssociationType(String associationType) {
			this.associationType = associationType;
		}
	}
	
	public static class ServiceSpec extends IBody {
		private String id;
		private String serviceSpecificationId;
		private Date startDate;
		private Date endDate;
		private String mappingType;
		private String type;
		private String status;
		private String associationType;
		private String name;
		private String description;
		private String subType;
		private String isProvisioningRequired;
		private String isAutoRenewable;
		private String isActivationRequired;
		private String serviceType;
		private List<GeographicLocation> geographicLocations;
		private List<Identifier> identifiers;
		
		public List<Identifier> getIdentifiers() {
			return identifiers;
		}
		public void setIdentifiers(List<Identifier> identifiers) {
			this.identifiers = identifiers;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getServiceSpecificationId() {
			return serviceSpecificationId;
		}
		public void setServiceSpecificationId(String serviceSpecificationId) {
			this.serviceSpecificationId = serviceSpecificationId;
		}
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getAssociationType() {
			return associationType;
		}
		public void setAssociationType(String associationType) {
			this.associationType = associationType;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getSubType() {
			return subType;
		}
		public void setSubType(String subType) {
			this.subType = subType;
		}
		public String getIsProvisioningRequired() {
			return isProvisioningRequired;
		}
		public void setIsProvisioningRequired(String isProvisioningRequired) {
			this.isProvisioningRequired = isProvisioningRequired;
		}
		public String getIsAutoRenewable() {
			return isAutoRenewable;
		}
		public void setIsAutoRenewable(String isAutoRenewable) {
			this.isAutoRenewable = isAutoRenewable;
		}
		public String getIsActivationRequired() {
			return isActivationRequired;
		}
		public void setIsActivationRequired(String isActivationRequired) {
			this.isActivationRequired = isActivationRequired;
		}
		public String getServiceType() {
			return serviceType;
		}
		public void setServiceType(String serviceType) {
			this.serviceType = serviceType;
		}
		public List<GeographicLocation> getGeographicLocations() {
			return geographicLocations;
		}
		public void setGeographicLocations(List<GeographicLocation> geographicLocations) {
			this.geographicLocations = geographicLocations;
		}
		public String getMappingType() {
			return mappingType;
		}
		public void setMappingType(String mappingType) {
			this.mappingType = mappingType;
		}
	}
	
	public static class ResourceSpec  extends IBody  {
		private String maxValue;
		private String id;
		private String resourceSpecificationId;
		private String minValue;
		private String defaultValue;
		private Date startDate;
		private Date endDate;
		private String mappingType;
		private String status;
		private String associationType;
		private String name;
		private String description;
		private String type;
		private String isInstallationRequired;
		private String companyCode;
		private String isManageable;
		private String fixedMobile;
		private GeographicLocationMapping geographicLocationMapping;
		private String componentPrice;
		private List<Identifier> identifiers;
		
		public List<Identifier> getIdentifiers() {
			return identifiers;
		}
		public void setIdentifiers(List<Identifier> identifiers) {
			this.identifiers = identifiers;
		}
		public String getMaxValue() {
			return maxValue;
		}
		public void setMaxValue(String maxValue) {
			this.maxValue = maxValue;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getResourceSpecificationId() {
			return resourceSpecificationId;
		}
		public void setResourceSpecificationId(String resourceSpecificationId) {
			this.resourceSpecificationId = resourceSpecificationId;
		}
		public String getMinValue() {
			return minValue;
		}
		public void setMinValue(String minValue) {
			this.minValue = minValue;
		}
		public String getDefaultValue() {
			return defaultValue;
		}
		public void setDefaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
		}
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getAssociationType() {
			return associationType;
		}
		public void setAssociationType(String associationType) {
			this.associationType = associationType;
		}
		public String getMappingType() {
			return mappingType;
		}
		public void setMappingType(String mappingType) {
			this.mappingType = mappingType;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getIsInstallationRequired() {
			return isInstallationRequired;
		}
		public void setIsInstallationRequired(String isInstallationRequired) {
			this.isInstallationRequired = isInstallationRequired;
		}
		public String getCompanyCode() {
			return companyCode;
		}
		public void setCompanyCode(String companyCode) {
			this.companyCode = companyCode;
		}
		public String getIsManageable() {
			return isManageable;
		}
		public void setIsManageable(String isManageable) {
			this.isManageable = isManageable;
		}
		public String getFixedMobile() {
			return fixedMobile;
		}
		public void setFixedMobile(String fixedMobile) {
			this.fixedMobile = fixedMobile;
		}
		public GeographicLocationMapping getGeographicLocationMapping() {
			return geographicLocationMapping;
		}
		public void setGeographicLocationMapping(
				GeographicLocationMapping geographicLocationMapping) {
			this.geographicLocationMapping = geographicLocationMapping;
		}
		public String getComponentPrice() {
			return componentPrice;
		}
		public void setComponentPrice(String componentPrice) {
			this.componentPrice = componentPrice;
		}
		
		
	}
	
	public static class ProductSpecCharacteristic extends IBody {
		private String id;
		private String serviceSpecificationId;
		private Date startDate;
		private Date endDate;
		private String type;
		private String status;
		private String associationType;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getServiceSpecificationId() {
			return serviceSpecificationId;
		}
		public void setServiceSpecificationId(String serviceSpecificationId) {
			this.serviceSpecificationId = serviceSpecificationId;
		}
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getAssociationType() {
			return associationType;
		}
		public void setAssociationType(String associationType) {
			this.associationType = associationType;
		}
		
	}
	
}
