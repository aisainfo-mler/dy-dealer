package com.ai.mapp.bss.entity;

import org.apache.commons.lang.StringUtils;

import com.ai.mapp.base.util.ConvertUtils;
import com.ai.mapp.sys.util.LanguageInfo;

/**
 * 内网SOCKET包头对象
 * @author mler
 * 2011-09-02
 */
public class LocalEH2Socket implements IMessage {
	
	private final int HEADER_LENGTH = 86;
	
	private String version;//A0版本号信息
	private String length;//A1数据包大小
	private String serialNumber;//A2 流水号
	private String ifSuccess;//A3 标志(1 表示成功  0 表示失败 仅适用于响应包)
	private String serviceCode;//A4 服务类型
	private String svcNo;//A5 业务号码
	private String svcType;//A6 业务号码类型
	private String sellCode;//A7 营业点
	private String operator;//A8 营业员
	private String socketCode;//A9 包编号
	private String ifLast;//A10 最后一包标志
	private String errorCode;//A11 错误码
	private String message;
	
	/**
	 * 响应串转换成ISocketMessage对象
	 */
	public IMessage toEntity(String socket_message) throws Exception {
		
		if(StringUtils.isEmpty(socket_message) || socket_message.length() <= HEADER_LENGTH)
			throw new Exception(LanguageInfo.EH2_HTTP_HEAD_ERROR);
		
		this.version = socket_message.substring(0,2).trim();
		this.length = socket_message.substring(2,7).trim();
		this.serialNumber = socket_message.substring(7,27).trim();
		this.ifSuccess = socket_message.substring(27, 28).trim();
		this.serviceCode = socket_message.substring(28, 40).trim();
		this.svcNo = socket_message.substring(40, 60).trim();
		this.svcType = socket_message.substring(60, 61).trim();
		this.sellCode = socket_message.substring(61, 67).trim();
		this.operator = socket_message.substring(67, 75).trim();
		this.socketCode = socket_message.substring(75, 80).trim();
		this.ifLast = socket_message.substring(80, 81).trim();
		this.errorCode = socket_message.substring(81, 86).trim();
		this.message = socket_message.substring(86, socket_message.length());
		
		return this;
		
	}
	
	/**
	 * 将对象转化为socket请求串
	 */
	public String toStr() throws Exception {
		
		StringBuffer socket_message = new StringBuffer("");
		
		socket_message.append(ConvertUtils.fillSpace(this.version, 2))
		.append(ConvertUtils.fillSpace(this.length, 5))
		.append(ConvertUtils.fillSpace(this.serialNumber, 20))
		.append(ConvertUtils.fillSpace(this.ifSuccess, 1))
		.append(ConvertUtils.fillSpace(this.serviceCode, 12))
		.append(ConvertUtils.fillSpace(this.svcNo, 20))
		.append(ConvertUtils.fillSpace(this.svcType, 1))
		.append(ConvertUtils.fillSpace(this.sellCode, 6))
		.append(ConvertUtils.fillSpace(this.operator, 8))
		.append(ConvertUtils.fillSpace(this.socketCode, 5))
		.append(ConvertUtils.fillSpace(this.ifLast, 1))
		.append(ConvertUtils.fillSpace(this.errorCode, 5));
		
		return socket_message.toString();
	}
	
	public boolean ifLast() throws Exception {
		if("1".equals(ifLast()))
			return true;
		return false;
	}
	
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getSvcNo() {
		return svcNo;
	}
	public void setSvcNo(String svcNo) {
		this.svcNo = svcNo;
	}
	public String getSvcType() {
		return svcType;
	}
	public void setSvcType(String svcType) {
		this.svcType = svcType;
	}
	public String getSellCode() {
		return sellCode;
	}
	public void setSellCode(String sellCode) {
		this.sellCode = sellCode;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getSocketCode() {
		return socketCode;
	}
	public void setSocketCode(String socketCode) {
		this.socketCode = socketCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getIfSuccess() {
		return ifSuccess;
	}

	public void setIfSuccess(String ifSuccess) {
		this.ifSuccess = ifSuccess;
	}

	public String getIfLast() {
		return ifLast;
	}

	public void setIfLast(String ifLast) {
		this.ifLast = ifLast;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorInfo() throws Exception {
		return getErrorCode();
	}

	public boolean ifSuccess() throws Exception {
		// TODO Auto-generated method stub
		if("1".equals(getIfSuccess()))
		{
			return true;
		}
		
		return false;
	}
	
	public String getBusiId(){
		
		return getServiceCode();
		
	}
}
