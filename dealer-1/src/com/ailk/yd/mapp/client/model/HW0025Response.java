package com.ailk.yd.mapp.client.model;

import java.math.BigDecimal;
import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

public class HW0025Response extends YDBody {
	private String orderCode;
	private List<FeeInfo> feeInfos;

	public static class FeeInfo {
		/**
		 * 费用分类型
		 */
		private Long feeType;
		/**
		 * 费用名称
		 */
		private String name;
		/**
		 * 所需费用(卢比)
		 */
		private BigDecimal fee;
		
		public Long getFeeType() {
			return feeType;
		}

		public void setFeeType(Long feeType) {
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

		public FeeInfo() {
			super();
		}

		public FeeInfo(Long feeType, String name, BigDecimal fee) {
			super();
			this.feeType = feeType;
			this.name = name;
			this.fee = fee;
		}

	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public List<FeeInfo> getFeeInfos() {
		return feeInfos;
	}

	public void setFeeInfos(List<FeeInfo> feeInfos) {
		this.feeInfos = feeInfos;
	}
}
