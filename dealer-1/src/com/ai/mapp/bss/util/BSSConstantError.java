package com.ai.mapp.bss.util;

import java.util.HashMap;
import java.util.Map;


public class BSSConstantError {
	
	public static final String CODE_LOGIN_ERROR = "0001";//帐号或密码错误
	public static final String CODE_PWD_ERROR = "0002";//密码错误
	public static final String CODE_RIGHT_ERROR = "0003";//权限不存在
	public static final String CODE_ORDER_SUBMIT_ERROR = "0004";//订单提交失败
	public static final String CODE_IDCARD_ERROR = "0005";//身份证
	public static final String CODE_MDN_ERROR = "0007";//号码错误
		
	public static final String CODE_TIME_OUT_ERROR = "5000";//超时
	public static final String CODE_DATA_ERROR = "5001";//数据异常
	public static final String CODE_NORESPONSE = "5002"; //无响应数据包
	public static final String CODE_UNKNOW_ERROR = "5003";//未知异常
	public static final String CODE_SIM_MATCH_ERROR = "5004";//MDN和SIM卡不匹配

	

	private static final Map<String, String> errorMap = new HashMap<String, String>(0);
	
	static {
		errorMap.put("01000", "号码查询失败");
		errorMap.put("01001", "用户号码/合同号不存在");
		errorMap.put("01002", "号码预占失败");
		errorMap.put("01003", "号码未预占");
		errorMap.put("01004", "原交易不存在/或不能被冲正");
		errorMap.put("01005", "号码预约失败");
		errorMap.put("01006", "业务类型不匹配");
		errorMap.put("01007", "用户名或密码错误");
		errorMap.put("01008", "该账号权限为空");
		errorMap.put("01009", "密码变更失败");
		errorMap.put("01010", "密码已经过期");
		errorMap.put("01011", "号码不可用");
		errorMap.put("01012", "号码回收占用");
		errorMap.put("01013", "号码冻结状态");
		errorMap.put("01014", "号码预开占用");
		errorMap.put("01016", "号码已经被预占");
		errorMap.put("01017", "号码已经被预定");
		errorMap.put("01019", "号码已经开户");
		errorMap.put("01020", "无工号对应的销售包配置");
		errorMap.put("01021", "根据卡号查询卡类型失败");
		errorMap.put("01022", "号码在号源表中不存在");
		errorMap.put("01023", "没有配置的销售包");
		errorMap.put("01024", "查询不到该资源的信息");
		errorMap.put("01025", "没有得到手机销售价格信息");
		errorMap.put("01026", "操作员不存在");
		errorMap.put("01027", "没有发展人信息");
		errorMap.put("01028", "查询卡费失败");
		errorMap.put("01029", "查询用户主产品ID失败");
		errorMap.put("01030", "异常预开户已绑定客户");
		errorMap.put("01031", "存在多个手机营业或代理营业工号");
		errorMap.put("01032", "用户已停机");
		errorMap.put("01033", "查询用户主产品失败");
		errorMap.put("01042", "只能通过身份证来新建客户");

		errorMap.put(CODE_DATA_ERROR, "返回数据异常");
		errorMap.put(CODE_TIME_OUT_ERROR, "连接超时");
		errorMap.put(CODE_NORESPONSE, "无响应数据包");
		errorMap.put(CODE_UNKNOW_ERROR, "未知异常");
		errorMap.put(CODE_SIM_MATCH_ERROR, "MDN和SIM卡不匹配");
	
	}
	
	public static String getErrorInfo(String errorCode)
	{
		if(errorMap.containsKey(errorCode))
		{
			return errorMap.get(errorCode);
		}
		
		return null;
	}
	
	public static String error(String code)
	{
		return error(code,getErrorInfo(code));
	}
	
	public static String error(String code,String errorInfo)
	{
		StringBuffer rspXML = new StringBuffer("<Response>")
				.append("<RspCode>").append(code).append("</RspCode>")
				.append("<MSG>").append(errorInfo).append("</MSG>")
			.append("</Response>");
		return rspXML.toString();
	}

	public static void main (String args[]){
		String a = "asdasd:{{00002} {sdfsdfsdf}}";
		a = a.replaceAll("}", "").replaceAll("{", "");
		String[] info = a.split(" ");
		System.out.println(info[1]);
	}
}
