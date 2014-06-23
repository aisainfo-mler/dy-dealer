package com.ailk.yd.mapp.client.model;

import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-7 下午02:06:55
 * 类说明:库存下订单
 */

public class HW0020Request extends YDBody{
	/**
	 * 商品列表
	 */
	private List<Good> goodList;
	
	
	public List<Good> getGoodList() {
		return goodList;
	}

	public void setGoodList(List<Good> goodList) {
		this.goodList = goodList;
	}

	public static class Good extends YDBody{
		/**
		 * 商品ID
		 */
		private String goodId;
		/**
		 * 商品数量
		 */
		private String count;
		

		public String getGoodId() {
			return goodId;
		}

		public void setGoodId(String goodId) {
			this.goodId = goodId;
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

	}
}
