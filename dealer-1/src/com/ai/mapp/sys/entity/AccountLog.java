package com.ai.mapp.sys.entity;

import java.util.Date;

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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-19 下午04:31:30
 * 类说明:
 */
@Entity
@Table(name="HW_ACCOUNT_LOG")
public class AccountLog implements java.io.Serializable {
	
	@Id
	@Column(name="ACCOUNT_LOG_ID")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="HW_ACCOUNT_LOG_GEN")
	@SequenceGenerator(name="HW_ACCOUNT_LOG_GEN",sequenceName="HW_ACCOUNT_LOG_SEQ")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=AccountInfo.class)
	@JoinColumn(name="ACCOUNT_ID",referencedColumnName="ACCOUNT_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private AccountInfo account;
	
	@Column(name="REAL_PAY")
	private Long pay;
	
	@Column(name="INCOME")
	private Long income;
	
	@Column(name="LOG_TYPE")
	private String logType;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=OrderInfo.class)
	@JoinColumn(name="ORDER_ID",referencedColumnName="ORDER_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private OrderInfo order;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=AgentOrder.class)
	@JoinColumn(name="AGENT_ORDER_ID",referencedColumnName="ORDERID")
	@NotFound(action=NotFoundAction.IGNORE)
	private AgentOrder agentOrder;
	
	@Column(name="BLANK_SERIAL_NUMBER")
	private String blankSerialNumber;
	
	@Column(name="CREATETIME")
	private Date createTime;
	
	@Column(name="LOG_STATUS")
	private String logStatus;
	
	@Column(name="TIBCO_SN")
	private String tibcoSn;
	
	@Column(name="CREATOR")
	private String creator;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public AccountInfo getAccount() {
		return account;
	}
	public void setAccount(AccountInfo account) {
		this.account = account;
	}
	public Long getPay() {
		return pay;
	}
	public void setPay(Long pay) {
		this.pay = pay;
	}
	public Long getIncome() {
		return income;
	}
	public void setIncome(Long income) {
		this.income = income;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public OrderInfo getOrder() {
		return order;
	}
	public void setOrder(OrderInfo order) {
		this.order = order;
	}
	public AgentOrder getAgentOrder() {
		return agentOrder;
	}
	public void setAgentOrder(AgentOrder agentOrder) {
		this.agentOrder = agentOrder;
	}
	public String getBlankSerialNumber() {
		return blankSerialNumber;
	}
	public void setBlankSerialNumber(String blankSerialNumber) {
		this.blankSerialNumber = blankSerialNumber;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getLogStatus() {
		return logStatus;
	}
	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}
	public String getTibcoSn() {
		return tibcoSn;
	}
	public void setTibcoSn(String tibcoSn) {
		this.tibcoSn = tibcoSn;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}
