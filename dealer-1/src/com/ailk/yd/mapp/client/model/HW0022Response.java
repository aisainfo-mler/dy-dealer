package com.ailk.yd.mapp.client.model;

import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

/**
 * 
 * @author xuzhou
 * 
 */
public class HW0022Response extends YDBody {

	private List<OrderType> list;

	public List<OrderType> getList() {
		return list;
	}

	public void setList(List<OrderType> list) {
		this.list = list;
	}

	public static class OrderType extends YDBody {

		private String text;

		private String value;

		private String income;

		private String productType;

		public OrderType() {
			super();
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getIncome() {
			return income;
		}

		public void setIncome(String income) {
			this.income = income;
		}

		public String getProductType() {
			return productType;
		}

		public void setProductType(String productType) {
			this.productType = productType;
		}

	}
}
