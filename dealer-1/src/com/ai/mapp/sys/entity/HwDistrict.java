package com.ai.mapp.sys.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="HW_DISTRICT")
public class HwDistrict implements Serializable {

	@Id
	@Column(name = "DISTRICT_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long districtId;
	
	@Column(name = "DISTRICT_GIS_CODE")
	private String districtGisCode;

	@Column(name = "DISTRICT_NAME")
	private String districtName;
	
	@Column(name = "STATE_CODE")
	private String stateCode;

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}


	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getDistrictGisCode() {
		return districtGisCode;
	}

	public void setDistrictGisCode(String districtGisCode) {
		this.districtGisCode = districtGisCode;
	}
}
