package com.ailk.yd.mapp.tibco.model.YD9011;

import java.math.BigDecimal;
import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-7 下午04:57:08
 * 类说明:订购商品批量导入
 */

public class YD9011Request extends YDBody {

	private List<Good> goodList;
	
	public List<Good> getGoodList() {
		return goodList;
	}

	public void setGoodList(List<Good> goodList) {
		this.goodList = goodList;
	}

	public static class Good extends YDBody {
		/**
		 * 操作类型，0 新增，1修改，2删除
		 */
		private String optType;
		/**
		 * 商品类型
		 */
		private String goodType;
		/**
		 * 商品id  TIBCO
		 */
		private String goodId;
		/**
		 * 商品名称
		 */
		private String name;
		/**
		 * 商品单价(单位：卢比)
		 */
		private BigDecimal salePrice;
		/**
		 * 商品批发单价(单位：卢比)
		 */
		private BigDecimal price;
		/**
		 * 商品图片Url
		 */
		private String pic;
		/**
		 * 商品备注
		 */
		private String comment;
		public String getOptType() {
			return optType;
		}
		public void setOptType(String optType) {
			this.optType = optType;
		}
		public String getGoodType() {
			return goodType;
		}
		public void setGoodType(String goodType) {
			this.goodType = goodType;
		}
		public String getGoodId() {
			return goodId;
		}
		public void setGoodId(String goodId) {
			this.goodId = goodId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public BigDecimal getSalePrice() {
			return salePrice;
		}
		public void setSalePrice(BigDecimal salePrice) {
			this.salePrice = salePrice;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public String getPic() {
			return pic;
		}
		public void setPic(String pic) {
			this.pic = pic;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		
	}
}
