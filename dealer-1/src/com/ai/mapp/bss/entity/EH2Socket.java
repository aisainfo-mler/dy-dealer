package com.ai.mapp.bss.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import com.ai.mapp.base.util.ConvertUtils;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.util.LanguageInfo;

/**
 * 外部报文抱头模型
 * @author luyang
 *
 */
public class EH2Socket implements IMessage {
	
	private final int HEADER_LENGTH = 128;
	 
	private final String SUCCESS_FLAG = "1";
	
	private String check;//A0 数据报文校验算法
	private String encrypt;//A1 密码加密算法
	private String compress;//A2 数据包体压缩算法
	private String checkcode;//A4 校验码字段
	private String logincode;//A5 登陆用户名
	private String password;//A6 登陆用户密码
	private String version;//A7 版本号信息
	private String length;//A8 数据包大小
	private String serialNumber;//A9 流水号
	private String ifSuccess;//A10 标志(1 表示成功  0 表示失败 仅适用于响应包)
	private String serviceCode;//A11 服务类型
	private String svcNo;//A12 业务号码
	private String svcType;//A13 业务号码类型
	private String sellCode;//A14 营业点
	private String operator;//A15 营业员
	private String socketCode;//A16 包编号
	private String ifLast;//A17 最后一包标志
	private String errorCode;//A18 错误码
	private String message;
	
	public EH2Socket toEntity(String socket_message) throws Exception {
	
		if(StringUtils.isEmpty(socket_message) || socket_message.length() < HEADER_LENGTH)
			throw new Exception(LanguageInfo.HTTP_HEAD_ERROR);
		
		this.check = socket_message.substring(0,1);
		this.encrypt = socket_message.substring(1,2);
		this.compress = socket_message.substring(2,3);
//		String baoliu = socket_message.substring(3,4);
		this.checkcode = socket_message.substring(4, 20).trim();
		this.logincode = socket_message.substring(20, 26).trim();
		this.password = socket_message.substring(26, 42).trim();
		this.version = socket_message.substring(42, 44).trim();
		this.length = socket_message.substring(44, 49).trim();
		this.serialNumber = socket_message.substring(49, 69).trim();
		this.ifSuccess = socket_message.substring(69, 70).trim();
		this.serviceCode = socket_message.substring(70, 82).trim();
		this.svcNo = socket_message.substring(82, 102).trim();
		this.svcType = socket_message.substring(102, 103);
		this.sellCode = socket_message.substring(103, 109).trim();
		this.operator = socket_message.substring(109, 117).trim();
		this.socketCode = socket_message.substring(117, 122).trim();
		this.ifLast = socket_message.substring(122, 123);
		this.errorCode = socket_message.substring(123, 128).trim();
		this.message = socket_message.substring(128, socket_message.length());
		
		return this;
	}
	
	public EH2Socket() {
		this.check = "0";
		this.checkcode = "0000000000000000";
		this.compress = "0";
		this.encrypt = "0";
		this.version = "11";
		this.logincode = "ZQBXZD";
		this.password = "ZQBXZD123";
		this.ifLast = "1";
		this.ifSuccess = "1";
	}
	
	public String toStr() throws Exception {
		
		StringBuffer socket_message = new StringBuffer("");

		socket_message.append(this.check)
		.append(this.encrypt)
		.append(this.compress)
		.append("0")
		.append(ConvertUtils.fillSpace(this.checkcode,16))
		.append(ConvertUtils.fillSpace("ZQBXZD", 6))
		.append(ConvertUtils.fillSpace("ZQBXZD123", 16))
		.append(ConvertUtils.fillSpace(this.version, 2))
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
		.append(ConvertUtils.fillSpace(this.errorCode, 5))
		.append(this.message);
		return socket_message.toString();
	}
	
	public boolean ifLast() throws Exception {
		if("1".equals(getIfLast()))
			return true;
		return false;
	}
	
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public String getEncrypt() {
		return encrypt;
	}
	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}
	public String getCompress() {
		return compress;
	}
	public void setCompress(String compress) {
		this.compress = compress;
	}
	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public String getLogincode() {
		return logincode;
	}
	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
		
		String msg = BSSConstantError.getErrorInfo(getErrorCode());
		if(StringUtils.isEmpty(msg) == false)
			return msg;
		
		msg = getMessage();
		
		if(msg.indexOf("}") >=0 && msg.indexOf("{") >= 0)
		{	
			String info[] = msg.split("\\} \\{");
			
			if(info.length>1)
			{
				info[0] = info[0].replaceAll("\\}", "");
				info[0] = info[0].replaceAll("\\{", "");
				
				info[1] = info[1].replaceAll("\\}", "");
				info[1] = info[1].replaceAll("\\{", "");
//				return new String((getErrorCode()+":"+info[1].trim()).getBytes("ISO-8859-1"),"GBK");
				return info[0]+":"+info[1].trim();
			}
		}
		
		
		
		return getErrorCode()+":系统出错";
	}

	public boolean ifSuccess() throws Exception 
	{
		if(SUCCESS_FLAG.equals(getIfSuccess()) && "00000".equals(getErrorCode()))
		{
			return true;
		}
		
		return false;
	}
	
	public Collection<String[]> getData() throws Exception 
	{
		try{
			System.out.println("==============================message==================================");
			System.out.println(message);
			System.out.println("=======================================================================");
			Collection<String[]> result = new ArrayList<String[]>(0);
			String[] rows = message.split(BSSConstantParam.EH2_ROW_SPLIT);
			System.out.println("rows:"+rows.length);
			for(String row : rows)
			{
				if(StringUtils.isEmpty(row))
				{
					System.out.println("+++++++++号码为空+++++++++");
					continue;
				}
				
				String[] cols = row.split(BSSConstantParam.EH2_COL_SPLIT);
				result.add(cols);
			}
			
			return result;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}
	
	public String getBusiId(){
		
		return getServiceCode();
		
	}
	
	public final static void main(String[] args)
	{
		String msg = "{{00002} {error: BMS-1 BMS异常 第二发展信息AQFYah未找到.}}";
		System.out.println(msg);
//		String msg = BSSConstantError.getErrorInfo(getErrorCode());
//		if(StringUtils.isEmpty(msg) == false)
//			return msg;
//		
//		msg = getMessage();
		
		if(msg.indexOf("}") >=0 && msg.indexOf("{") >= 0)
		{	
			String info[] = msg.split("\\} \\{");
			
			if(info.length>1)
			{
				info[0] = info[0].replaceAll("\\}", "");
				info[0] = info[0].replaceAll("\\{", "");
				
				info[1] = info[1].replaceAll("\\}", "");
				info[1] = info[1].replaceAll("\\{", "");
//				return new String((getErrorCode()+":"+info[1].trim()).getBytes("ISO-8859-1"),"GBK");
				System.out.println(info[0]+":"+info[1].trim());
			}
		}
		
	}
	
}
