package com.ai.mapp.sys.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-19 下午04:56:38
 * 类说明:
 */
@Entity
@Table(name="HW_ORDER_INFO")
public class OrderInfo implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="HW_ORDER_INFO_GEN")
    @SequenceGenerator(name="HW_ORDER_INFO_GEN",sequenceName="HW_ORDER_INFO_SEQ")
	@Column(name="ORDER_ID")
	private Long id;
	
	@Column(name="COMMENTS")
	private String comments;
	
	@Column(name="STATUS")
	private String status;//1:待确认;2:已确认;3:已出库;4:交易成功;5:已作废;6:退货中;7:已退货
	
	@Column(name="ORDER_TYPE")
	private String type;//01：手机  ;02：卡
	
	@Column(name="FEE")
	private Long fee;
	
	@Column(name="NETID")
	private Long netId;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="OPERATOR_ID",referencedColumnName="OPERATOR_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private User operator;
	
	@Column(name="REGION_ID")
	private Long regionId;
	
	@Column(name="COUNTY_ID")
	private Long countyId;
	
	@Column(name="CREATETIME")
	private Date createTime;
	
	@Column(name="SENDTIME")
	private Date sendTime;
	
	@Column(name="CONFIRMTIME")
	private Date confirmTime;
	
	@Column(name="STATUSTIME")
	private Date statusTime;
	
	@Column(name="SERIAL_NUMBER")
	private String serialNumber;
	
	@Column(name="BLANK_SERIAL_NUMBER")
	private String blankserialNumber;
	
	@OneToMany(mappedBy="order",fetch=FetchType.LAZY,targetEntity=OrderDetail.class)
    @JoinColumn(name="ORDER_ID")
	private List<OrderDetail> details;
	
	@Column(name="EXPRESS_CHARGE")
	private Long expressCharge;
	
	@Column(name="EXPRESS_NUMBER")
	private String expressNumber;
	
	@Column(name="PAYMODE")
	private String payMode;
	
	@Column(name="PAYTIME")
	private Date payTime;
	
	@Column(name="EXPRESS_COMPANY_NO")
	private String expressCompanyNO;
	
	@Column(name="REALFEE")
	private Long realFee;
	
	@Column(name="PLACE_TIBCO")
	private String placeTibco;
	
	public OrderInfo() {
		super();
	}
	public OrderInfo(String serialNumber) {
		super();
		this.serialNumber = serialNumber;
	}
	public String getPlaceTibco() {
		return placeTibco;
	}
	public void setPlaceTibco(String placeTibco) {
		this.placeTibco = placeTibco;
	}
	public List<OrderDetail> getDetails() {
		return details;
	}
	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	public Long getNetId() {
		return netId;
	}
	public void setNetId(Long netId) {
		this.netId = netId;
	}
	public User getOperator() {
		return operator;
	}
	public void setOperator(User operator) {
		this.operator = operator;
	}
	public Long getCountyId() {
		return countyId;
	}
	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Date getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
	public Date getStatusTime() {
		return statusTime;
	}
	public void setStatusTime(Date statusTime) {
		this.statusTime = statusTime;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getBlankserialNumber() {
		return blankserialNumber;
	}
	public void setBlankserialNumber(String blankserialNumber) {
		this.blankserialNumber = blankserialNumber;
	}
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	public Long getExpressCharge() {
		return expressCharge;
	}
	public void setExpressCharge(Long expressCharge) {
		this.expressCharge = expressCharge;
	}
	public String getExpressNumber() {
		return expressNumber;
	}
	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getExpressCompanyNO() {
		return expressCompanyNO;
	}
	public void setExpressCompanyNO(String expressCompanyNO) {
		this.expressCompanyNO = expressCompanyNO;
	}
	public Long getRealFee() {
		return realFee;
	}
	public void setRealFee(Long realFee) {
		this.realFee = realFee;
	}
	
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	@Transient
	private String isAuthor;//是否关联本处理人
	
	@Transient
	private String expressCompanyName;
	
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


	public String getIsAuthor() {
		return isAuthor;
	}
	public void setIsAuthor(String isAuthor) {
		this.isAuthor = isAuthor;
	}
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="CREATOR",referencedColumnName="OPERATOR_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private User creator;

	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public String getExpressCompanyName() {
		return expressCompanyName;
	}
	public void setExpressCompanyName(String expressCompanyName) {
		this.expressCompanyName = expressCompanyName;
	}
	
	@Column(name="BSS_ORDER_SN")
	private String bssOrderCode;

	public String getBssOrderCode() {
		return bssOrderCode;
	}
	public void setBssOrderCode(String bssOrderCode) {
		this.bssOrderCode = bssOrderCode;
	}
	
}
