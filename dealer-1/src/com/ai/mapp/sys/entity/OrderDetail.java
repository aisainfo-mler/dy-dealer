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
 * @version 创建时间：2012-9-19 下午06:40:45
 * 类说明:
 */

@Entity
@Table(name="HW_ORDER_DETAIL")
public class OrderDetail implements java.io.Serializable {
	
	@Id
	@Column(name="DETAIL_ID")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="HW_ORDER_DETAIL_GEN")
	@SequenceGenerator(name="HW_ORDER_DETAIL_GEN",sequenceName="HW_ORDER_DETAIL_SEQ")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=OrderInfo.class)
	@JoinColumn(name="ORDER_ID",referencedColumnName="ORDER_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private OrderInfo order;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=GoodsInfo.class)
	@JoinColumn(name="GOOD_ID",referencedColumnName="GOOD_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private GoodsInfo good;
	
	@Column(name="SALEFEE")
	private Long saleFee;
	
	@Column(name="REALFEE")
	private Long realFee;
	
	@Column(name="ORDERTYPE")
	private String orderType;//01：16K白卡 ;02：32K白卡;03：手机	
	
	@Column(name="CREATETIME")
	private Date createTime;
	
	@Column(name="COUNTS")
	private Long counts;
	
	@Column(name="DISCOUNT")
	private Long discount;
	
	@OneToMany(mappedBy="detail",fetch=FetchType.LAZY,targetEntity=OrderItem.class)
    @JoinColumn(name="DETAIL_ID")
	private List<OrderItem> items;

	public OrderDetail() {
		super();
	}
	
	public OrderDetail(Long id) {
		super();
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public OrderInfo getOrder() {
		return order;
	}
	public void setOrder(OrderInfo order) {
		this.order = order;
	}
	public GoodsInfo getGood() {
		return good;
	}
	public void setGood(GoodsInfo good) {
		this.good = good;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getCounts() {
		return counts;
	}
	public void setCounts(Long counts) {
		this.counts = counts;
	}
	public Long getDiscount() {
		return discount;
	}
	public void setDiscount(Long discount) {
		this.discount = discount;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public Long getSaleFee() {
		return saleFee;
	}
	public void setSaleFee(Long saleFee) {
		this.saleFee = saleFee;
	}
	public Long getRealFee() {
		return realFee;
	}
	public void setRealFee(Long realFee) {
		this.realFee = realFee;
	}
	
	@Transient
	private Long agentId;

	public Long getAgentId() {
		return agentId;
	}
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	
	
}
