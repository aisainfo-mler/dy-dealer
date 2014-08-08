package com.ailk.yd.mapp.model;

import com.ailk.butterfly.mapp.core.model.IHeader;

public class YDHeader implements IHeader {

	private String bizCode;
	
	private String identityId;
	
	private String respCode;
	
	private String respMsg;
	
	private String mode;
	
	private String sign;
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String getBizCode() {
		return this.bizCode;
	}

	@Override
	public String getIdentityId() {
		return this.identityId;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

//	@Override
//	public String getToken() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void setToken(String arg0) {
//		// TODO Auto-generated method stub
//		
//	}

}
