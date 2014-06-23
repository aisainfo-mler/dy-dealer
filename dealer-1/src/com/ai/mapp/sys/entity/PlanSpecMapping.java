package com.ai.mapp.sys.entity;

import java.util.Date;
import java.util.List;

import com.ailk.butterfly.mapp.core.model.IBody;

public class PlanSpecMapping extends IBody {
	
	private List<PlanSpec> planSpecs;
	
	public List<PlanSpec> getPlanSpecs() {
		return planSpecs;
	}

	public void setPlanSpecs(List<PlanSpec> planSpecs) {
		this.planSpecs = planSpecs;
	}

	public static class PlanSpec extends IBody {
	
		private String id;
		private String name;
		private String description;
		private Date startDate;
		private Date endDate;
		private String status;
		private String type;
		private String serviceType;
		private String subType;
		private String billingType;
		private String companyCode;
		private String priority;
		private String stackedQueued;
		private String isCumulative;
		private String isProratedOnActivation;
		private String isProratedOnDeactivation;
		private String timeId;
		private String serviceSpecificationId;
		private List<PlanSpecCharacteristic> planSpecCharList;
		private GeographicLocationMapping geographicLocationMapping;
		private String componentPrice;
		private String isPrimary;
		private String planSpecificationid;
		private String mappingType;
		private String associationType;
		

		public String getIsPrimary() {
			return isPrimary;
		}

		public void setIsPrimary(String isPrimary) {
			this.isPrimary = isPrimary;
		}

		public String getPlanSpecificationid() {
			return planSpecificationid;
		}

		public void setPlanSpecificationid(String planSpecificationid) {
			this.planSpecificationid = planSpecificationid;
		}

		public String getMappingType() {
			return mappingType;
		}

		public void setMappingType(String mappingType) {
			this.mappingType = mappingType;
		}

		public String getAssociationType() {
			return associationType;
		}

		public void setAssociationType(String associationType) {
			this.associationType = associationType;
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

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getServiceType() {
			return serviceType;
		}

		public void setServiceType(String serviceType) {
			this.serviceType = serviceType;
		}

		public String getSubType() {
			return subType;
		}

		public void setSubType(String subType) {
			this.subType = subType;
		}

		public String getBillingType() {
			return billingType;
		}

		public void setBillingType(String billingType) {
			this.billingType = billingType;
		}

		public String getCompanyCode() {
			return companyCode;
		}

		public void setCompanyCode(String companyCode) {
			this.companyCode = companyCode;
		}

		public String getPriority() {
			return priority;
		}

		public void setPriority(String priority) {
			this.priority = priority;
		}

		public String getStackedQueued() {
			return stackedQueued;
		}

		public void setStackedQueued(String stackedQueued) {
			this.stackedQueued = stackedQueued;
		}

		public String getIsCumulative() {
			return isCumulative;
		}

		public void setIsCumulative(String isCumulative) {
			this.isCumulative = isCumulative;
		}

		public String getIsProratedOnActivation() {
			return isProratedOnActivation;
		}

		public void setIsProratedOnActivation(String isProratedOnActivation) {
			this.isProratedOnActivation = isProratedOnActivation;
		}

		public String getIsProratedOnDeactivation() {
			return isProratedOnDeactivation;
		}

		public void setIsProratedOnDeactivation(String isProratedOnDeactivation) {
			this.isProratedOnDeactivation = isProratedOnDeactivation;
		}

		public String getTimeId() {
			return timeId;
		}

		public void setTimeId(String timeId) {
			this.timeId = timeId;
		}

		public String getServiceSpecificationId() {
			return serviceSpecificationId;
		}

		public void setServiceSpecificationId(String serviceSpecificationId) {
			this.serviceSpecificationId = serviceSpecificationId;
		}

		public List<PlanSpecCharacteristic> getPlanSpecCharList() {
			return planSpecCharList;
		}

		public void setPlanSpecCharList(List<PlanSpecCharacteristic> planSpecCharList) {
			this.planSpecCharList = planSpecCharList;
		}

		public String getComponentPrice() {
			return componentPrice;
		}

		public void setComponentPrice(String componentPrice) {
			this.componentPrice = componentPrice;
		}

		public GeographicLocationMapping getGeographicLocationMapping() {
			return geographicLocationMapping;
		}

		public void setGeographicLocationMapping(
				GeographicLocationMapping geographicLocationMapping) {
			this.geographicLocationMapping = geographicLocationMapping;
		}
		
	}
	
	public static class PlanSpecCharacteristic extends IBody {
		private String id;
		private String planSpecCharacteristicId;
		private String routingPatternId;
		private String isAutoRewenable;
		private String value;
		private String unitsOfMeasure;
		private String minValue;
		private String maxValue;
		private Date startDate;
		private Date endDate;
		private String mappingType;
		private String status;
		private String validityCycle;
		private String timeId;
		private String rechargeBehaviour;
		private String associationType;
		
		private String name;
		private String description;
		private String valueType;
		private String defaultValue;
		private String priority;
		private String type;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPlanSpecCharacteristicId() {
			return planSpecCharacteristicId;
		}
		public void setPlanSpecCharacteristicId(String planSpecCharacteristicId) {
			this.planSpecCharacteristicId = planSpecCharacteristicId;
		}
		public String getRoutingPatternId() {
			return routingPatternId;
		}
		public void setRoutingPatternId(String routingPatternId) {
			this.routingPatternId = routingPatternId;
		}
		public String getIsAutoRewenable() {
			return isAutoRewenable;
		}
		public void setIsAutoRewenable(String isAutoRewenable) {
			this.isAutoRewenable = isAutoRewenable;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getUnitsOfMeasure() {
			return unitsOfMeasure;
		}
		public void setUnitsOfMeasure(String unitsOfMeasure) {
			this.unitsOfMeasure = unitsOfMeasure;
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
		public String getValidityCycle() {
			return validityCycle;
		}
		public void setValidityCycle(String validityCycle) {
			this.validityCycle = validityCycle;
		}
		public String getTimeId() {
			return timeId;
		}
		public void setTimeId(String timeId) {
			this.timeId = timeId;
		}
		public String getRechargeBehaviour() {
			return rechargeBehaviour;
		}
		public void setRechargeBehaviour(String rechargeBehaviour) {
			this.rechargeBehaviour = rechargeBehaviour;
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
		public String getValueType() {
			return valueType;
		}
		public void setValueType(String valueType) {
			this.valueType = valueType;
		}
		public String getDefaultValue() {
			return defaultValue;
		}
		public void setDefaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
		}
		public String getPriority() {
			return priority;
		}
		public void setPriority(String priority) {
			this.priority = priority;
		}
	}
	
	
	
}
