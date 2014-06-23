package com.ai.mapp.sys.entity;

import java.util.Collection;
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
 * @version 创建时间：2012-9-19 下午09:17:46
 * 类说明:
 */

@Entity
@Table(name="HW_ORDER_ITEM_INFO")
public class OrderItem implements java.io.Serializable {

	@Id
	@Column(name="ORDER_ITEM_ID")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="HW_ORDER_ITEM_INFO_GEN")
	@SequenceGenerator(name="HW_ORDER_ITEM_INFO_GEN",sequenceName="HW_ORDER_ITEM_INFO_SEQ")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=OrderDetail.class)
	@JoinColumn(name="DETAIL_ID",referencedColumnName="DETAIL_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private OrderDetail detail;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=GoodsInfo.class)
	@JoinColumn(name="GOOD_ID",referencedColumnName="GOOD_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private GoodsInfo good;
	
	@Column(name="ITEM_VALUE")
	private String itemValue;
	
	@Column(name="CREATETIME")
	private Date createTime;
	
	@Column(name="ITEM_STATUS")
	private String status;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="OPERATOR_ID",referencedColumnName="OPERATOR_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private User operator;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="AGENT_ID",referencedColumnName="OPERATOR_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private User agent;
	
	
	@Column(name="TEMP_EFF_TIME")
	private Date tempEffTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderDetail getDetail() {
		return detail;
	}

	public void setDetail(OrderDetail detail) {
		this.detail = detail;
	}

	public GoodsInfo getGood() {
		return good;
	}

	public void setGood(GoodsInfo good) {
		this.good = good;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public User getAgent() {
		return agent;
	}

	public void setAgent(User agent) {
		this.agent = agent;
	}
	
	@Column(name="ITEM_FROM")
	private String from;//0:scan  1:manual  2:batch
	
	@Column(name="BATCH_GROUPINFO")
	private String batchGroup;
	
	@Transient
	private String groupBy;

	@Transient
	private String orderBy;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}


	public String getBatchGroup() {
		return batchGroup;
	}

	public void setBatchGroup(String batchGroup) {
		this.batchGroup = batchGroup;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Date getTempEffTime() {
		return tempEffTime;
	}

	public void setTempEffTime(Date tempEffTime) {
		this.tempEffTime = tempEffTime;
	}
	
}
