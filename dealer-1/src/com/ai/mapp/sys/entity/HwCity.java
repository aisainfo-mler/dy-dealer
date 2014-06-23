package com.ai.mapp.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="HW_CITY")
public class HwCity implements java.io.Serializable {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO,generator="GEN_HW_CITY")
    @SequenceGenerator(name="GEN_HW_CITY",sequenceName="SEQ_HW_CITY")
	@Column(name="CITY_ID")
	private Long cityId;
	
	@Column(name="CITY_CODE")
	private String cityCode;
	
	@Column(name="CITY_NAME")
	private String cityName;
	
	@Column(name="CITY_DESC")
	private String cityDesc;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=HwState.class)
	@JoinColumn(name="STATE_CODE",referencedColumnName="STATE_CODE")
	@NotFound(action=NotFoundAction.IGNORE)
	private HwState state;
	
	@Column(name="FLAG")
	private String flag;
	
	@Column(name="CIRCLE_CODE")
	private String circleCode;
	@Column(name="DISTRICT_CODE")
	private String districtCode;
	@Column(name="PINCODES")
	private String pincodes;
	
	public HwCity() {
		super();
	}

	public HwCity(String cityCode) {
		super();
		this.cityCode = cityCode;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityDesc() {
		return cityDesc;
	}

	public void setCityDesc(String cityDesc) {
		this.cityDesc = cityDesc;
	}

	public HwState getState() {
		return state;
	}

	public void setState(HwState state) {
		this.state = state;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCircleCode() {
		return circleCode;
	}

	public void setCircleCode(String circleCode) {
		this.circleCode = circleCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getPincodes() {
		return pincodes;
	}

	public void setPincodes(String pincodes) {
		this.pincodes = pincodes;
	}

}
