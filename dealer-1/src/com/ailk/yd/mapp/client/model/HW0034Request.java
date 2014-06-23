package com.ailk.yd.mapp.client.model;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-14 上午10:17:26
 * 类说明:代理商收货校验
 */

public class HW0034Request extends YDBody{
	/**
	 * 订单ID
	 */
	private String orderCode;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

}
