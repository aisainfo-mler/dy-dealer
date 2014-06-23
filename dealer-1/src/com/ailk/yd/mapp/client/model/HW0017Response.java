package com.ailk.yd.mapp.client.model;

import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

public class HW0017Response extends YDBody {

	private List<Order> orderList;

	public static class Order {

		private java.lang.String orderCode;

		private java.lang.String orderType;

		private java.lang.String saleFee;

		private java.lang.String realFee;

		private java.lang.String discount;

		private java.lang.String createTime;

		private java.lang.String payTime;

		private java.lang.String orderStatus;

		private List<Good> goodList;

		public static class Good {
			private java.lang.String goodId;

			private java.lang.String goodName;

			private java.lang.String goodPrice;

			private java.lang.String goodSalePrice;

			private java.lang.String count;

			private java.lang.String discount;

			private java.lang.String saleFee;

			private java.lang.String realFee;

			public java.lang.String getGoodId() {
				return goodId;
			}

			public void setGoodId(java.lang.String goodId) {
				this.goodId = goodId;
			}

			public java.lang.String getGoodName() {
				return goodName;
			}

			public void setGoodName(java.lang.String goodName) {
				this.goodName = goodName;
			}

			public java.lang.String getGoodPrice() {
				return goodPrice;
			}

			public void setGoodPrice(java.lang.String goodPrice) {
				this.goodPrice = goodPrice;
			}

			public java.lang.String getGoodSalePrice() {
				return goodSalePrice;
			}

			public void setGoodSalePrice(java.lang.String goodSalePrice) {
				this.goodSalePrice = goodSalePrice;
			}

			public java.lang.String getCount() {
				return count;
			}

			public void setCount(java.lang.String count) {
				this.count = count;
			}

			public java.lang.String getDiscount() {
				return discount;
			}

			public void setDiscount(java.lang.String discount) {
				this.discount = discount;
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

		public java.lang.String getDiscount() {
			return discount;
		}

		public void setDiscount(java.lang.String discount) {
			this.discount = discount;
		}

		public java.lang.String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(java.lang.String createTime) {
			this.createTime = createTime;
		}

		public java.lang.String getPayTime() {
			return payTime;
		}

		public void setPayTime(java.lang.String payTime) {
			this.payTime = payTime;
		}

		public java.lang.String getOrderStatus() {
			return orderStatus;
		}

		public void setOrderStatus(java.lang.String orderStatus) {
			this.orderStatus = orderStatus;
		}

		public List<Good> getGoodList() {
			return goodList;
		}

		public void setGoodList(List<Good> goodList) {
			this.goodList = goodList;
		}
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

}
