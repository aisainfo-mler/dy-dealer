package com.ailk.yd.mapp.client.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ailk.yd.mapp.model.YDBody;

public class HW0013Response extends YDBody {

	private List<Order> orders;

	public static class Order extends YDBody
	{
		private java.lang.String orderCode;

		private java.lang.String orderType;

		private java.lang.String svn;

		private java.lang.String sim;

		private java.lang.String preStore;

		private java.lang.String totalFee;

		private java.lang.String createTime;

		private java.lang.String simFee;

		private java.lang.String balance;

		private java.lang.String payMode;

		private java.lang.String saleFee;

		private java.lang.String discountFee;

		private java.lang.String realFee;

		private java.lang.String payStatus;

		private java.lang.String orderStatus;

		private java.lang.String productId;

		private java.lang.String productType;

		private java.lang.String pin;

		private java.lang.String packageFee;

		private java.lang.String packageName;

		private java.lang.String numberFee;

		private java.lang.String SIMFee;
		
		private java.lang.String orn;
		
		private Boolean accountLevel;
		
		private Map<String,BigDecimal> feeDetail;
		
		
		public Boolean getAccountLevel() {
			return accountLevel;
		}

		public void setAccountLevel(Boolean accountLevel) {
			this.accountLevel = accountLevel;
		}

		public java.lang.String getOrn() {
			return orn;
		}

		public void setOrn(java.lang.String orn) {
			this.orn = orn;
		}

		public java.lang.String getOrderCode() {
			return orderCode;
		}

		public void setOrderCode(java.lang.String orderCode) {
			this.orderCode = orderCode;
		}

		public java.lang.String getOrderType() {
			return orderType;
		}

		public void setOrderType(java.lang.String orderType) {
			this.orderType = orderType;
		}

		public java.lang.String getSvn() {
			return svn;
		}

		public void setSvn(java.lang.String svn) {
			this.svn = svn;
		}

		public java.lang.String getSim() {
			return sim;
		}

		public void setSim(java.lang.String sim) {
			this.sim = sim;
		}

		public java.lang.String getPreStore() {
			return preStore;
		}

		public void setPreStore(java.lang.String preStore) {
			this.preStore = preStore;
		}

		public java.lang.String getTotalFee() {
			return totalFee;
		}

		public void setTotalFee(java.lang.String totalFee) {
			this.totalFee = totalFee;
		}

		public java.lang.String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(java.lang.String createTime) {
			this.createTime = createTime;
		}

		public java.lang.String getSimFee() {
			return simFee;
		}

		public void setSimFee(java.lang.String simFee) {
			this.simFee = simFee;
		}

		public java.lang.String getBalance() {
			return balance;
		}

		public void setBalance(java.lang.String balance) {
			this.balance = balance;
		}

		public java.lang.String getPayMode() {
			return payMode;
		}

		public void setPayMode(java.lang.String payMode) {
			this.payMode = payMode;
		}

		public java.lang.String getSaleFee() {
			return saleFee;
		}

		public void setSaleFee(java.lang.String saleFee) {
			this.saleFee = saleFee;
		}

		public java.lang.String getDiscountFee() {
			return discountFee;
		}

		public void setDiscountFee(java.lang.String discountFee) {
			this.discountFee = discountFee;
		}

		public java.lang.String getRealFee() {
			return realFee;
		}

		public void setRealFee(java.lang.String realFee) {
			this.realFee = realFee;
		}

		public java.lang.String getPayStatus() {
			return payStatus;
		}

		public void setPayStatus(java.lang.String payStatus) {
			this.payStatus = payStatus;
		}

		public java.lang.String getOrderStatus() {
			return orderStatus;
		}

		public void setOrderStatus(java.lang.String orderStatus) {
			this.orderStatus = orderStatus;
		}

		public java.lang.String getProductId() {
			return productId;
		}

		public void setProductId(java.lang.String productId) {
			this.productId = productId;
		}

		public java.lang.String getProductType() {
			return productType;
		}

		public void setProductType(java.lang.String productType) {
			this.productType = productType;
		}

		public java.lang.String getPin() {
			return pin;
		}

		public void setPin(java.lang.String pin) {
			this.pin = pin;
		}

		public java.lang.String getPackageFee() {
			return packageFee;
		}

		public void setPackageFee(java.lang.String packageFee) {
			this.packageFee = packageFee;
		}

		public java.lang.String getPackageName() {
			return packageName;
		}

		public void setPackageName(java.lang.String packageName) {
			this.packageName = packageName;
		}

		public java.lang.String getNumberFee() {
			return numberFee;
		}

		public void setNumberFee(java.lang.String numberFee) {
			this.numberFee = numberFee;
		}

		public java.lang.String getSIMFee() {
			return SIMFee;
		}

		public void setSIMFee(java.lang.String sIMFee) {
			SIMFee = sIMFee;
		}

		public Map<String,BigDecimal> getFeeDetail() {
			return feeDetail;
		}

		public void setFeeDetail(Map<String,BigDecimal> feeDetail) {
			this.feeDetail = feeDetail;
		}

	}
	
	public static class ResourceFee  extends YDBody {
		/**
		 * 费用分类型
		 */
		private String feeType;
		/**
		 * 费用名称
		 */
		private String name;
		/**
		 * 所需费用(卢比)
		 */
		private BigDecimal fee;
		
		public String getFeeType() {
			return feeType;
		}

		public void setFeeType(String feeType) {
			this.feeType = feeType;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public BigDecimal getFee() {
			return fee;
		}

		public void setFee(BigDecimal fee) {
			this.fee = fee;
		}

		public ResourceFee() {
			super();
		}

		public ResourceFee(String feeType, String name, BigDecimal fee) {
			super();
			this.feeType = feeType;
			this.name = name;
			this.fee = fee;
		}

	}
	
	public static class FeeDetail 
	{
		 private BigDecimal planFee;
		 
		 private BigDecimal numberFee;
		 
		 private BigDecimal simFee;
		 
		 private List<ResourceFee> resourceFeeInfo;

		public BigDecimal getPlanFee() {
			return planFee;
		}

		public void setPlanFee(BigDecimal planFee) {
			this.planFee = planFee;
		}

		public BigDecimal getNumberFee() {
			return numberFee;
		}

		public void setNumberFee(BigDecimal numberFee) {
			this.numberFee = numberFee;
		}

		public BigDecimal getSimFee() {
			return simFee;
		}

		public void setSimFee(BigDecimal simFee) {
			this.simFee = simFee;
		}

		public List<ResourceFee> getResourceFeeInfo() {
			return resourceFeeInfo;
		}

		public void setResourceFeeInfo(List<ResourceFee> resourceFeeInfo) {
			this.resourceFeeInfo = resourceFeeInfo;
		}

	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
