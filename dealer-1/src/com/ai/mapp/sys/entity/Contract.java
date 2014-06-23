package com.ai.mapp.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="hw_plan")
public class Contract implements java.io.Serializable {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO,generator="hw_plan_gen")
    @SequenceGenerator(name="hw_plan_gen",sequenceName="HW_PLAN$SEQ")
	@Column(name="CID")
	private Long cId;
	
	@Column(name="CONTRACTID")
	private String contractId;
	
	@Column(name="NAME")
	private  String name;
	
	@Column(name="CONTRACT_FEE")
	private  Long contractFee;
	
	@Column(name="DOMESTIC_CALL")
	private  String domesticCall;
	
	@Column(name="SMS")
	private  String sms;
	
	@Column(name="MMS")
	private  String mms;
	
	@Column(name="CALLRATE")
	private  String callRate;
	
	@Column(name="FLOWRATE")
	private  String flowRate;
	
	@Column(name="PRESENT")
	private  String present;
	
	@Column(name="OTHERSERVICE")
	private  String otherService;
	
	@Column(name="CONTRACTTYPE")
	private String contractType;
	
	@Column(name="ANSWERFREE")
	private String answerFree;
	
	@Column(name="FLOW")
	private String flow;
	
	@Column(name="LOWCOST")
	private Long lowcost;
	
	@Column(name="VIDEOFEE")
	private String videoFee;
	
	@Column(name="VIDEOMIN")
	private String videoMin;
	
	@Column(name="OTHERFEE")
	private String otherFee;
	
	@Column(name="UPDATETIME")
	private Date updateTime;
	
	@Column(name="STATUS")
	private String status;

	public Contract() {
		super();
	}

	public Contract(String contractId) {
		super();
		this.contractId = contractId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getContractFee() {
		return contractFee;
	}

	public void setContractFee(Long contractFee) {
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

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getAnswerFree() {
		return answerFree;
	}

	public void setAnswerFree(String answerFree) {
		this.answerFree = answerFree;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public Long getLowcost() {
		return lowcost;
	}

	public void setLowcost(Long lowcost) {
		this.lowcost = lowcost;
	}

	public String getVideoFee() {
		return videoFee;
	}

	public void setVideoFee(String videoFee) {
		this.videoFee = videoFee;
	}

	public String getVideoMin() {
		return videoMin;
	}

	public void setVideoMin(String videoMin) {
		this.videoMin = videoMin;
	}

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	@Override
	public String toString() {
		return "Contract [contractId=" + contractId + ", name=" + name
				+ ", contractFee=" + contractFee + ", domesticCall="
				+ domesticCall + ", sms=" + sms + ", mms=" + mms
				+ ", callRate=" + callRate + ", flowRate=" + flowRate
				+ ", present=" + present + ", otherService=" + otherService
				+ ", contractType=" + contractType + ", answerFree="
				+ answerFree + ", flow=" + flow + ", lowcost=" + lowcost
				+ ", videoFee=" + videoFee + ", videoMin=" + videoMin + "]";
	}
	
	/**
	 * 操作类型，0 新增，1修改，2删除
	 */
	@Transient
	private String optType;

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}
	
}
