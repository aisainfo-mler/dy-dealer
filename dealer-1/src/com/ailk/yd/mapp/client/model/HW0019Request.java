package com.ailk.yd.mapp.client.model;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-7 下午05:30:01
 * 类说明:商品列表
 */

public class HW0019Request extends YDBody{
	/**
	 * 商品Id
	 */
	private String goodId;
	/**
	 * 商品类型
	 */
	private String goodType;
	/**
	 * 状态
	 */
	private String status;
	
	public String getGoodId() {
		return goodId;
	}
	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}
	public String getGoodType() {
		return goodType;
	}
	public void setGoodType(String goodType) {
		this.goodType = goodType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
