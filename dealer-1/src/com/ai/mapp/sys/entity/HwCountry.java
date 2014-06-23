package com.ai.mapp.sys.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HW_COUNTRY")
public class HwCountry implements Serializable {

	
	@Id
	@Column(name = "COUNTRY_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long countryId;
	
	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@Column(name = "COUNTRY_NAME")
	private String countryName;
	
	@Column(name = "NATIONALLTY_NAME")
	private String nationalltyName;

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getNationalltyName() {
		return nationalltyName;
	}

	public void setNationalltyName(String nationalltyName) {
		this.nationalltyName = nationalltyName;
	}
}
