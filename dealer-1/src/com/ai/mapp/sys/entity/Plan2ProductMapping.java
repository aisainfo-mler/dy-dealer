package com.ai.mapp.sys.entity;

import java.util.Date;

public class Plan2ProductMapping {

	private String id;
	private String planOfferingID;
	private Date startDate;
	private Date endDate;
	private String mappingType;
	private String status;
	/**
	 * 4:Inclusive:必选
	 * 3:Multiple:可选
	 */
	private String associationType;
	
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
	public String getPlanOfferingID() {
		return planOfferingID;
	}
	public void setPlanOfferingID(String planOfferingID) {
		this.planOfferingID = planOfferingID;
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
	public String getMappingType() {
		return mappingType;
	}
	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
