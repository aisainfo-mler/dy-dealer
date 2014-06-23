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
@Table(name="HW_ORDER_SHIPMENT")
public class HwOrderShipment implements java.io.Serializable {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO,generator="GEN_HW_ORDER_SHIPMENT")
    @SequenceGenerator(name="GEN_HW_ORDER_SHIPMENT",sequenceName="SEQ_HW_ORDER_SHIPMENT")
	@Column(name="order_ship_id")
	private Long orderShipId;
	
	@Column(name="order_id")
	private Long orderId;
	
	@Column(name="order_code")
	private String orderCode;
	
	@Column(name="express_number")
	private String expressNumber;
	
	@Column(name="express_company_code")
	private String expressCompanyCode;
	
	@Column(name="express_company_name")
	private String expressCompanyName;
	
	@Column(name="ship_type")
	private Long shipType;
	
	@Column(name="sender")
	private String sender;
	
	@Column(name="sender_company_name")
	private String senderCompanyName;
	
	@Column(name="sender_address")
	private String senderAddress;
	
	@Column(name="sender_tel")
	private String senderTel;
	
	@Column(name="sender_comment")
	private String senderComment;
	
	@Column(name="recipient")
	private String recipient;
	
	@Column(name="recipient_company_name")
	private String recipientCompanyName;
	
	@Column(name="recipient_address")
	private String recipientAddress;
	
	@Column(name="recipient_tel")
	private String recipientTel;
	
	@Column(name="rejector")
	private String rejector;
	
	@Column(name="reject_comment")
	private String rejectComment;
	
	@Column(name="ship_status")
	private Long shipStatus;
	
	@Column(name="creator")
	private String creator;
	
	@Column(name="create_time")
	private Date createTime;
	
	
	
	@Transient
	private int pkIdOrderType=2; // 1-升序  2-降序(默认) 

	public Long getOrderShipId() {
		return orderShipId;
	}

	public void setOrderShipId(Long orderShipId) {
		this.orderShipId = orderShipId;
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

	public String getExpressNumber() {
		return expressNumber;
	}

	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}

	public String getExpressCompanyCode() {
		return expressCompanyCode;
	}

	public void setExpressCompanyCode(String expressCompanyCode) {
		this.expressCompanyCode = expressCompanyCode;
	}

	public String getExpressCompanyName() {
		return expressCompanyName;
	}

	public void setExpressCompanyName(String expressCompanyName) {
		this.expressCompanyName = expressCompanyName;
	}

	public Long getShipType() {
		return shipType;
	}

	public void setShipType(Long shipType) {
		this.shipType = shipType;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSenderCompanyName() {
		return senderCompanyName;
	}

	public void setSenderCompanyName(String senderCompanyName) {
		this.senderCompanyName = senderCompanyName;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getSenderTel() {
		return senderTel;
	}

	public void setSenderTel(String senderTel) {
		this.senderTel = senderTel;
	}

	public String getSenderComment() {
		return senderComment;
	}

	public void setSenderComment(String senderComment) {
		this.senderComment = senderComment;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getRecipientCompanyName() {
		return recipientCompanyName;
	}

	public void setRecipientCompanyName(String recipientCompanyName) {
		this.recipientCompanyName = recipientCompanyName;
	}

	public String getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public String getRecipientTel() {
		return recipientTel;
	}

	public void setRecipientTel(String recipientTel) {
		this.recipientTel = recipientTel;
	}

	public String getRejector() {
		return rejector;
	}

	public void setRejector(String rejector) {
		this.rejector = rejector;
	}

	public String getRejectComment() {
		return rejectComment;
	}

	public void setRejectComment(String rejectComment) {
		this.rejectComment = rejectComment;
	}

	public Long getShipStatus() {
		return shipStatus;
	}

	public void setShipStatus(Long shipStatus) {
		this.shipStatus = shipStatus;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getPkIdOrderType() {
		return pkIdOrderType;
	}

	public void setPkIdOrderType(int pkIdOrderType) {
		this.pkIdOrderType = pkIdOrderType;
	}

}
