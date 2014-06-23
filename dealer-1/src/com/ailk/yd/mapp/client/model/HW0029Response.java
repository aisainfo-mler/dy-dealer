package com.ailk.yd.mapp.client.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ailk.yd.mapp.model.YDBody;
import com.ailk.yd.mapp.tibco.model.YD0005.YD0005Response;

public class HW0029Response extends YDBody {
	/**
	 * 总费用
	 */
	private BigDecimal totalFee;

	/**
	 * 费用列表
	 */
	private List<FeeInfo> fees;

	public List<FeeInfo> getFees() {
		return fees;
	}

	public void setFees(List<FeeInfo> fees) {
		this.fees = fees;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	/**
	 * 费用对象
	 */
	public static class FeeInfo extends YDBody {
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

	public void fillTestData() {
		this.setTotalFee(new BigDecimal(998));
		this.setFees(new ArrayList() {
			{
				add(new YD0005Response.FeeInfo(7l, "fee1", new BigDecimal(541)));
				add(new YD0005Response.FeeInfo(9l, "fee2", new BigDecimal(457)));
			}
		});
	}
}
