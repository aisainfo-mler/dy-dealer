package com.ai.mapp.bss.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BSSConstant {
	
	/** 订单日志OrderLogBean (表 mapp_bus_order_log) 中协议类型 **/
	public static final String PROTOCOL_TYPE_EH2 = "EH2";
	public static final String PROTOCOL_TYPE_DC = "DC";
	public static final String PROTOCOL_TYPE_ZB = "ZB";
	
	/** 订单日志OrderType (表 mapp_bus_order_log) 中订单类型 **/
	public static final String ORDER_TYPE_JIAOFEI = "JIAOFEI";
	public static final String ORDER_TYPE_KAIHU = "KAIHU";
	public static final String ORDER_TYPE_BUKA = "BUKA";
	public static final String ORDER_TYPE_YUYUE = "YUYUE";
	
	/**号码样式*/
	public final static Map<String,String>  mdnPatternList = new LinkedHashMap<String,String>(0);
	/**缴费类型**/
	public final static Map<Integer,String>  rechargeTypeList = new LinkedHashMap<Integer,String>(0);
	/**支付模式**/
	public final static Map<String,String>  payMethodList = new LinkedHashMap<String,String>(0);
	/**证件类型*/
	public final static Map<String,String>  certificateList  = new LinkedHashMap<String,String>(0);
	/**首月模式*/
	public final static Map<String,String>  firstMonthTypeList  = new LinkedHashMap<String, String>(0);
	/**订单类型*/
	public static final Map<String,String>orderTypes = new LinkedHashMap<String, String>(0);
	/**银行类型*/
	public static final Map<String,String> bankTypes = new LinkedHashMap<String, String>(0);
//	public static final p<String,String> bankTypes = new LinkedHashMap<String, String>(0);
	/**
	 * 0:行业解决方案；1:新闻；2:产品
	 */
	public static Map<String,String[]> articleType = new HashMap<String,String[]>();
	
	
	
	static {
		
		mdnPatternList.put("0","无号码样式");
		mdnPatternList.put("1","号码样式AA");
		mdnPatternList.put("2","号码样式ABC");
		mdnPatternList.put("3","号码样式ABAB");
		mdnPatternList.put("4","号码样式AABB");
		mdnPatternList.put("5","号码样式AAA");
		mdnPatternList.put("6","号码样式ABCD");
		mdnPatternList.put("7","号码样式ABCDE");
		mdnPatternList.put("8","号码样式AAAA");
		mdnPatternList.put("9","号码样式AAAAA");
		mdnPatternList.put("11","号码中带6");
		mdnPatternList.put("12","号码中带8");
		mdnPatternList.put("13","号码中不带4");
		mdnPatternList.put("14","号码中带4");
		
		rechargeTypeList.put(20, "20 元");
		rechargeTypeList.put(30, "30 元");
		rechargeTypeList.put(50, "50 元");
		rechargeTypeList.put(100, "100 元");
		rechargeTypeList.put(200, "200 元");
		rechargeTypeList.put(300, "300 元");
		rechargeTypeList.put(500, "500 元");
		
		payMethodList.put("0", "现金");
		payMethodList.put("1", "POS机");
		payMethodList.put("2", "分期");
		
//		payMethodList.put("2003", "预存池支付");
		
		certificateList.put("11", "18位身份证");
		certificateList.put("10", "15位身份证");
//		certificateList.put("12", "护照");
//		certificateList.put("13", "军人证");
//		certificateList.put("14", "驾驶证");
//		certificateList.put("15", "工作证");
//		certificateList.put("16", "学生证");
//		certificateList.put("17", "户口本");
//		certificateList.put("18", "暂住证");
//		certificateList.put("19", "警官证");
//		certificateList.put("20", "单位介绍信");
		certificateList.put("21", "营业执照");
//		certificateList.put("22", "其它");
//		certificateList.put("23", "数固客户编码");
//		certificateList.put("24", "集团编号");
//		certificateList.put("25", "工商注册登记证");
//		certificateList.put("26", "企业代码证");
//		certificateList.put("30", "预开户编号");
//		certificateList.put("31", "港澳居民来往内地通行证");
//		certificateList.put("31", "台湾居民来往大陆通行证");
//		certificateList.put("31", "武警身份证");
		
		firstMonthTypeList.put("01","标准资费");
		firstMonthTypeList.put("02","全月模式");
		firstMonthTypeList.put("03","半月模式");
		
		orderTypes.put("", "全部");
		orderTypes.put(ORDER_TYPE_JIAOFEI,"缴费");//补卡提交
		orderTypes.put(ORDER_TYPE_KAIHU,"开户");//充值
		orderTypes.put(ORDER_TYPE_BUKA,"补卡");//售卡订单提交
		orderTypes.put(ORDER_TYPE_YUYUE,"预约");//号码预约提交
		
		
		bankTypes.put("CBC", "中国建设银行");//中国建设银行——CBC
//		bankTypes.put("BC", "中国银行");//中国银行——BC
		bankTypes.put("ABC", "中国农业银行");//中国农业银行——ABC
		bankTypes.put("ICBC", "中国工商银行");//中国工商银行——ICBC
//		bankTypes.put("CMSB", "民生银行");//民生银行——CMSB
		bankTypes.put("CMBC", "招商银行");//招商银行——CMBC
//		bankTypes.put("CIB", "兴业银行");//兴业银行——CIB
//		bankTypes.put("HSBC", "汇丰银行");//汇丰银行——HSBC
//		bankTypes.put("CGB", "广发银行");//广发银行---CGB
//		bankTypes.put("SPD", "浦发银行");//浦发银行--SPD
//		bankTypes.put("HZBANK", "杭州银行");//杭州银行---HZBANK
		articleType.put("0", new String[]{"解决方案","特色功能","技术架构"});//行业解决方案
		articleType.put("1", new String[]{""});//新闻
		articleType.put("2", new String[]{"解决方案","特色功能","技术架构"});//产品
		
	}
}
