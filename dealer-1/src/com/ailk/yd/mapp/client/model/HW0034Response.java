package com.ailk.yd.mapp.client.model;

import java.util.List;
import java.util.Map;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-13 下午07:03:08
 * 类说明:代理商收货校验
 */

public class HW0034Response extends YDBody{
	/**
	 * 代理商收货校验
	 */
	private Map<Long,List<Good>> itemValues;
	
	public static class Good{
		/**
		 * 商品ID
		 */
		private Long goodId;
		/**
		 * 商品名称
		 */
		private String goodName;
		/**
		 * 商品类型
		 */
		private String goodType;
		/**
		 * 商品起始号
		 */
		private String startNum;
		/**
		 * 商品终止号
		 */
		private String endNum;
		/**
		 * 商品串号
		 */
		private String imei;
		
		
		public Long getGoodId() {
			return goodId;
		}
		public void setGoodId(Long goodId) {
			this.goodId = goodId;
		}
		public String getGoodName() {
			return goodName;
		}
		public void setGoodName(String goodName) {
			this.goodName = goodName;
		}
		public String getGoodType() {
			return goodType;
		}
		public void setGoodType(String goodType) {
			this.goodType = goodType;
		}
		public String getStartNum() {
			return startNum;
		}
		public void setStartNum(String startNum) {
			this.startNum = startNum;
		}
		public String getEndNum() {
			return endNum;
		}
		public void setEndNum(String endNum) {
			this.endNum = endNum;
		}
		public String getImei() {
			return imei;
		}
		public void setImei(String imei) {
			this.imei = imei;
		}
	}

	public Map<Long, List<Good>> getItemValues() {
		return itemValues;
	}

	public void setItemValues(Map<Long, List<Good>> itemValues) {
		this.itemValues = itemValues;
	}

}
