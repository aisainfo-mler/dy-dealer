package com.ailk.yd.mapp.client.model;

import com.ailk.butterfly.mapp.core.model.IBody;

public class HW0042Request extends IBody {

	private String fileContents;
	/**
	 * poa,poi,cus
	 */
	private String fileType;
	/**
	 * true：将上传的多个图片转成一个pdf，上传到tibco false：仅仅是上传一个图片到服务器，并不调tibco接口
	 */
	private String ifReturnUrl;
	/**
	 * 用这个编码区分照片属于哪个订单
	 */
	private String ornNum;

	public String getFileContents() {
		return fileContents;
	}

	public void setFileContents(String fileContents) {
		this.fileContents = fileContents;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getOrnNum() {
		return ornNum;
	}

	public void setOrnNum(String ornNum) {
		this.ornNum = ornNum;
	}

	public String getIfReturnUrl() {
		return ifReturnUrl;
	}

	public void setIfReturnUrl(String ifReturnUrl) {
		this.ifReturnUrl = ifReturnUrl;
	}

}
