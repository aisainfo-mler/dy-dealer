package com.ailk.yd.mapp.client.model;

import com.ailk.yd.mapp.model.YDBody;

public class HW0007Request extends YDBody{

	/**
	 * 客户手机号
	 */
	private String mdn;
	
	private Integer page;
	private Integer size;
	public String getMdn() {
		return mdn;
	}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer offset) {
		this.page = offset;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer pageSize) {
		this.size = pageSize;
	}
}
