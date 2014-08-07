package com.ailk.yd.mapp.client.model;

import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-8-6 下午10:42:22
 * 类说明:
 */

public class HW0015Response extends YDBody {
	private List<GoodType>  goodTypes;
	
	
	public List<GoodType> getGoodTypes() {
		return goodTypes;
	}

	public void setGoodTypes(List<GoodType> goodTypes) {
		this.goodTypes = goodTypes;
	}

	public static class GoodType extends YDBody {
		private String TypeValue;
		
		private List<Good> goodList;

		public String getTypeValue() {
			return TypeValue;
		}

		public void setTypeValue(String typeValue) {
			TypeValue = typeValue;
		}

		public List<Good> getGoodList() {
			return goodList;
		}

		public void setGoodList(List<Good> goodList) {
			this.goodList = goodList;
		}
	}
	
	public static class Good extends YDBody {
		private String goodName;
		private String sale;
		private String unSale;
		private String all;
		public String getGoodName() {
			return goodName;
		}
		public void setGoodName(String goodName) {
			this.goodName = goodName;
		}
		public String getSale() {
			return sale;
		}
		public void setSale(String sale) {
			this.sale = sale;
		}
		public String getUnSale() {
			return unSale;
		}
		public void setUnSale(String unSale) {
			this.unSale = unSale;
		}
		public String getAll() {
			return all;
		}
		public void setAll(String all) {
			this.all = all;
		}
		
	}

}
