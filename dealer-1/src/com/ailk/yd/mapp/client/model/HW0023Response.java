package com.ailk.yd.mapp.client.model;

import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

/**
 * 
 * @author xuzhou
 * 
 */
public class HW0023Response extends YDBody {

	private List<Order> list;

	public List<Order> getList() {
		return list;
	}

	public void setList(List<Order> list) {
		this.list = list;
	}

	public static class Order extends YDBody {
		private java.lang.String orderCode;

		private java.lang.String orderType;

		private java.lang.String saleFee;

		private java.lang.String realFee;

		private java.lang.String income;

		private java.lang.String comment;

		private java.lang.String commissionCode;

		private java.lang.String commissionId;

		private java.lang.String payStatus;

		private java.lang.String payTime;

		private java.lang.String createTime;

		private java.lang.String chargeType;

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

		public java.lang.String getSaleFee() {
			return saleFee;
		}

		public void setSaleFee(java.lang.String saleFee) {
			this.saleFee = saleFee;
		}

		public java.lang.String getRealFee() {
			return realFee;
		}

		public void setRealFee(java.lang.String realFee) {
			this.realFee = realFee;
		}

		public java.lang.String getIncome() {
			return income;
		}

		public void setIncome(java.lang.String income) {
			this.income = income;
		}

		public java.lang.String getComment() {
			return comment;
		}

		public void setComment(java.lang.String comment) {
			this.comment = comment;
		}

		public java.lang.String getCommissionCode() {
			return commissionCode;
		}

		public void setCommissionCode(java.lang.String commissionCode) {
			this.commissionCode = commissionCode;
		}

		public java.lang.String getCommissionId() {
			return commissionId;
		}

		public void setCommissionId(java.lang.String commissionId) {
			this.commissionId = commissionId;
		}

		public java.lang.String getPayStatus() {
			return payStatus;
		}

		public void setPayStatus(java.lang.String payStatus) {
			this.payStatus = payStatus;
		}

		public java.lang.String getPayTime() {
			return payTime;
		}

		public void setPayTime(java.lang.String payTime) {
			this.payTime = payTime;
		}

		public java.lang.String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(java.lang.String createTime) {
			this.createTime = createTime;
		}

		public java.lang.String getChargeType() {
			return chargeType;
		}

		public void setChargeType(java.lang.String chargeType) {
			this.chargeType = chargeType;
		}

	}

}
