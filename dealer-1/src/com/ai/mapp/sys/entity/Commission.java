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
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-19 下午06:40:45
 * 类说明:
 */

@Entity
@Table(name="HW_COMMISSION")
public class Commission implements java.io.Serializable {
	
	@Id
	@Column(name="COMMISSIONID")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="HW_COMMISSION_GEN")
	@SequenceGenerator(name="HW_COMMISSION_GEN",sequenceName="HW_COMMISSION_SEQ")
	private Long id;
	
	@Column(name="PAY")
	private Long pay;
	
	@Column(name="PAYTIME")
	private Date payTime;
	
	@Column(name="PAYSTATUS")
	private String payStatus;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=AgentOrder.class)
	@JoinColumn(name="AGENT_ORDER_ID",referencedColumnName="ORDERID")
	private AgentOrder agentOrder;
	
	@Column(name="SVN")
	private String svn;
	
	@Column(name="CREATETIME")
	private Date createTime;
	
	@Column(name="COMMISSIONTYPE")
	private String type;//做扣，佣金
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="AGENTID",referencedColumnName="OPERATOR_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private User agent;
	
	@Column(name="PAYTYPE")
	private String payType;//支付类型，做扣，返佣
	
	@Column(name="CHARGETYPE")
	private String chargeType;//做扣的为：订单的类型，佣金：以c开头
	
	@Column(name="COMMENT")
	private String comment;
	
	@Column(name="COMMISSIONCODE")
	private String commissionCode;
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommissionCode() {
		return commissionCode;
	}

	public void setCommissionCode(String commissionCode) {
		this.commissionCode = commissionCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPay() {
		return pay;
	}

	public void setPay(Long pay) {
		this.pay = pay;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public AgentOrder getAgentOrder() {
		return agentOrder;
	}

	public void setAgentOrder(AgentOrder agentOrder) {
		this.agentOrder = agentOrder;
	}

	public String getSvn() {
		return svn;
	}

	public void setSvn(String svn) {
		this.svn = svn;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getAgent() {
		return agent;
	}

	public void setAgent(User agent) {
		this.agent = agent;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
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

	
}
