package com.ailk.yd.mapp.client.model;

import java.util.List;
import java.util.Map;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-12 上午10:59:50
 * 类说明:广告图片
 */

public class HW0031Response extends YDBody{
	
	private List<Promotion> promotionImages;
	
	public static class Promotion extends YDBody{
		/**
		 * 广告类型 1：广告 2：终端首页 3开户
		 */
		private String promotionType;
		/**
		 * 图片内容
		 
		private byte[] imageData;*/
		/**
		 * 后缀名 格式
		 
		private String ext;*/
		/**
		 * 备注
		 */
		private String remark;
		/**
		 * 图片序号
		 */
		private String imageIndex;
		
		/**
		 * 缩略图
		 * 0:缩略图ID
		 * 4: Iphone4图ID
		 * 5: Iphone5图ID
		 */
		private Map<String,Long> imageMap;
		
		
		public Map<String, Long> getImageMap() {
			return imageMap;
		}
		public void setImageMap(Map<String, Long> imageMap) {
			this.imageMap = imageMap;
		}
		public String getPromotionType() {
			return promotionType;
		}
		public void setPromotionType(String promotionType) {
			this.promotionType = promotionType;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getImageIndex() {
			return imageIndex;
		}
		public void setImageIndex(String imageIndex) {
			this.imageIndex = imageIndex;
		}
	}

	public List<Promotion> getPromotionImages() {
		return promotionImages;
	}

	public void setPromotionImages(List<Promotion> promotionImages) {
		this.promotionImages = promotionImages;
	}
	
}
