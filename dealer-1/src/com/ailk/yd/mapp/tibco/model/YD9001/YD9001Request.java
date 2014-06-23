package com.ailk.yd.mapp.tibco.model.YD9001;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author mler
 *  
 * @version 创建时间：2014-4-28 下午12:34:22
 * 类说明:合约计划更新
 */

public class YD9001Request extends YDBody{
	
	private String productFileUrl;

	public String getProductFileUrl() {
		return productFileUrl;
	}

	public void setProductFileUrl(String productFileUrl) {
		this.productFileUrl = productFileUrl;
	}
	
}
