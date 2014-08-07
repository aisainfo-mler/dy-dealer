package com.ailk.yd.mapp.client.model;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-8-6 下午10:41:53
 * 类说明:
 */

public class HW0015Request extends YDBody {

    private java.lang.String userCode;

    private java.lang.String goodId;

    private java.lang.String goodType;

	public java.lang.String getUserCode() {
		return userCode;
	}

	public void setUserCode(java.lang.String userCode) {
		this.userCode = userCode;
	}

	public java.lang.String getGoodId() {
		return goodId;
	}

	public void setGoodId(java.lang.String goodId) {
		this.goodId = goodId;
	}

	public java.lang.String getGoodType() {
		return goodType;
	}

	public void setGoodType(java.lang.String goodType) {
		this.goodType = goodType;
	}

    
}
