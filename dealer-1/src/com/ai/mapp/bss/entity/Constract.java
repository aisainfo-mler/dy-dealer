package com.ai.mapp.bss.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="mapp_constract")
public class Constract {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO,generator="mapp_constract_gen")
    @SequenceGenerator(name="mapp_constract_gen",sequenceName="MAPP_CONSTRACT$SEQ")
	private Long contractId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="contractFee")
	private String contractFee;
	
	@Column(name="domesticCall")
	private String domesticCall;
	
	@Column(name="sms")
	private String sms;
	
	@Column(name="mms")
	private String mms;
	
	@Column(name="callRate")
	private String callRate;
	
	@Column(name="flowRate")
	private String flowRate;
	
	@Column(name="present")
	private String present;
	
	@Column(name="otherService")
	private String otherService;
	
	@Column(name="accept_range_id")
	private String accept_range_id;

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContractFee() {
		return contractFee;
	}

	public void setContractFee(String contractFee) {
		this.contractFee = contractFee;
	}

	public String getDomesticCall() {
		return domesticCall;
	}

	public void setDomesticCall(String domesticCall) {
		this.domesticCall = domesticCall;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public String getMms() {
		return mms;
	}

	public void setMms(String mms) {
		this.mms = mms;
	}

	public String getCallRate() {
		return callRate;
	}

	public void setCallRate(String callRate) {
		this.callRate = callRate;
	}

	public String getFlowRate() {
		return flowRate;
	}

	public void setFlowRate(String flowRate) {
		this.flowRate = flowRate;
	}

	public String getPresent() {
		return present;
	}

	public void setPresent(String present) {
		this.present = present;
	}

	public String getOtherService() {
		return otherService;
	}

	public void setOtherService(String otherService) {
		this.otherService = otherService;
	}

	public String getAccept_range_id() {
		return accept_range_id;
	}

	public void setAccept_range_id(String acceptRangeId) {
		accept_range_id = acceptRangeId;
	}
	
	
	
}
