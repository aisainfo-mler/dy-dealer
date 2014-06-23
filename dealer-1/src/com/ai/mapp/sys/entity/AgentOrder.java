package com.ai.mapp.sys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="hw_agent_order")
public class AgentOrder implements Serializable {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO,generator="hw_agent_order_gen")
    @SequenceGenerator(name="hw_agent_order_gen",sequenceName="HW_AGENT_ORDER$SEQ")
	@Column(name="orderId")
	private Long orderId;
	
	@Column(name="ORDERCODE")
	private String orderCode;
	
	@Column(name="ORDERSTATUS")
	private String status;
	
	@Column(name="ORDERDESC")
	private String desc;
	
	@Column(name="SVN")
	private String svn;
	
	@Column(name="ORDERTYPE")
	private String orderType;
	
	@Column(name="PAYTYPE")
	private String payType;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Product.class)
	@JoinColumn(name="PRODUCTID",referencedColumnName="RANGEID")
	@NotFound(action=NotFoundAction.IGNORE)
	private Product product;
	
	@Column(name="PRESTORE")
	private Long preStore;
	
	@Column(name="SALEFEE")
	private Long saleFee;
	
	@Column(name="DISCOUNTFEE")
	private Long discountFee;
	
	@Column(name="REALFEE")
	private Long realFee;
	
	@Column(name="DISCOUNTRATE")
	private Float discountRate;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="CREATOR",referencedColumnName="OPERATOR_USERCODE")
	@NotFound(action=NotFoundAction.IGNORE)
	private User creator;
	
	@Column(name="PAYMODE")
	private String payMode;
	
	@Column(name="PAYSTATUS")
	private String payStatus;
	
	@Column(name="CREATETIME")
	private Date createTime;
	
	@Column(name="PAYTIME")
	private Date payTime;
	
	@Column(name="COMPLETETIME")
	private Date completeTime;
	
	@Column(name="BANKSERIAL")
	private String bankSerial;
	
	@Column(name="ZWSERIAL")
	private String zwSerial;
	
	@Column(name="SIM")
	private String sim;
	
	@Column(name="IMSI")
	private String imsi;
	
	@Column(name="IMEI")
	private String imei;
	
	@Column(name="BANKCODE")
	private String bankCode;
	
	@Column(name="BLANCE")
	private Long blance;
	
	@Column(name="UPDATETIME")
	private Date updateTime;
	
	@Column(name="PIN")
	private String pin;
	
	@Column(name="PACKAGEFEE")
	private Long packageFee;
	
	@Column(name="SIMFEE")
	private Long simFee;
	
	@Column(name="NUMBERFEE")
	private Long numberFee;
	
	@Column(name="FEE_DETAIL")
	private String feeDetail;
	
	@Column(name="SN")
	private String sn;
	
	@Column(name="CAF_INFO")
	private String cafInfo;
	
	@Column(name="TIBCO_ORN")
	private String tibcoOrderNumber;
	
	@Column(name="TIBCO_SEND_FLAG")
	private String tibcoSendFlag;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="UPDATER",referencedColumnName="OPERATOR_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private User updater;
	
	public AgentOrder() {
		super();
	}

	public AgentOrder(Long orderId) {
		super();
		this.orderId = orderId;
	}

	public AgentOrder(String orderCode) {
		super();
		this.orderCode = orderCode;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSvn() {
		return svn;
	}

	public void setSvn(String svn) {
		this.svn = svn;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getPreStore() {
		return preStore;
	}

	public void setPreStore(Long preStore) {
		this.preStore = preStore;
	}

	public Long getSaleFee() {
		return saleFee;
	}

	public void setSaleFee(Long saleFee) {
		this.saleFee = saleFee;
	}

	public Long getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(Long discountFee) {
		this.discountFee = discountFee;
	}

	public Long getRealFee() {
		return realFee;
	}

	public void setRealFee(Long realFee) {
		this.realFee = realFee;
	}

	public Float getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Float discountRate) {
		this.discountRate = discountRate;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public String getBankSerial() {
		return bankSerial;
	}

	public void setBankSerial(String bankSerial) {
		this.bankSerial = bankSerial;
	}

	public String getZwSerial() {
		return zwSerial;
	}

	public void setZwSerial(String zwSerial) {
		this.zwSerial = zwSerial;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
	
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	public void setBlance(Long blance) {
		this.blance = blance;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public User getUpdater() {
		return updater;
	}

	public void setUpdater(User updater) {
		this.updater = updater;
	}
	
	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Long getPackageFee() {
		return packageFee;
	}

	public void setPackageFee(Long packageFee) {
		this.packageFee = packageFee;
	}

	public Long getSimFee() {
		return simFee;
	}

	public void setSimFee(Long simFee) {
		this.simFee = simFee;
	}

	public Long getNumberFee() {
		return numberFee;
	}

	public void setNumberFee(Long numberFee) {
		this.numberFee = numberFee;
	}


	@Transient
	private Date startTime;
	
	@Transient
	private Date endTime;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getBlance() {
		return blance;
	}
	
	@Transient
	private String orderBy;//for example:    desc:userId,updateTime;asc:createTime,lastName

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	@Transient
	private String optType;

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public String getFeeDetail() {
		return feeDetail;
	}

	public void setFeeDetail(String feeDetail) {
		this.feeDetail = feeDetail;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getCafInfo() {
		return cafInfo;
	}

	public void setCafInfo(String cafInfo) {
		this.cafInfo = cafInfo;
	}

	public String getTibcoOrderNumber() {
		return tibcoOrderNumber;
	}

	public void setTibcoOrderNumber(String tibcoOrderNumber) {
		this.tibcoOrderNumber = tibcoOrderNumber;
	}

	public String getTibcoSendFlag() {
		return tibcoSendFlag;
	}

	public void setTibcoSendFlag(String tibcoSendFlag) {
		this.tibcoSendFlag = tibcoSendFlag;
	}


	
}
