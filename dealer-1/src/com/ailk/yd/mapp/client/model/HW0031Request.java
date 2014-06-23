package com.ailk.yd.mapp.client.model;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-12 上午10:59:32
 * 类说明:广告图片
 */

public class HW0031Request extends YDBody{
	/**
	 * 广告类型 1：广告 2：终端首页 3开户
	 */
	private String promotionType;
	/**
	 * 起始数
	 */
	private Integer start;
	/**
	 * 一页显示的数量
	 */
	private Integer size;

	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}
