package com.ailk.yd.mapp.client.model;

import java.util.List;
import java.util.Map;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-7 下午05:30:29
 * 类说明:商品列表
 */

public class HW0019Response extends YDBody{
	private Map<String, List<Good>> goodTypeList;//key:GoodType
	
	public Map<String, List<Good>> getGoodTypeList() {
		return goodTypeList;
	}

	public void setGoodTypeList(Map<String, List<Good>> goodTypeList) {
		this.goodTypeList = goodTypeList;
	}

	public static class Good extends YDBody{
		/**
		 * 商品ID
		 */
		private String goodId;
		/**
		 * 名称
		 */
		private String goodName;
		/**
		 * 商品价格
		 */
		private String goodPrice;
		/**
		 * 商品销售价格
		 */
		private String goodSalePrice;
		/**
		 * 状态
		 */
		private String goodStatus;
		/**
		 * 图片信息
		 */
		private String image;
		/**
		 * Tibco商品ID
		 */
		private String bssGoodId;
		public String getGoodId() {
			return goodId;
		}
		public void setGoodId(String goodId) {
			this.goodId = goodId;
		}
		public String getGoodName() {
			return goodName;
		}
		public void setGoodName(String goodName) {
			this.goodName = goodName;
		}
		public String getGoodPrice() {
			return goodPrice;
		}
		public void setGoodPrice(String goodPrice) {
			this.goodPrice = goodPrice;
		}
		public String getGoodSalePrice() {
			return goodSalePrice;
		}
		public void setGoodSalePrice(String goodSalePrice) {
			this.goodSalePrice = goodSalePrice;
		}
		public String getGoodStatus() {
			return goodStatus;
		}
		public void setGoodStatus(String goodStatus) {
			this.goodStatus = goodStatus;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getBssGoodId() {
			return bssGoodId;
		}
		public void setBssGoodId(String bssGoodId) {
			this.bssGoodId = bssGoodId;
		}
		
	}
}
