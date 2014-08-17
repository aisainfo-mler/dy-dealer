package com.ai.mapp.base.util;

import java.io.UnsupportedEncodingException;

import com.ai.mapp.base.StringUtil;

/**
 * 
 * @author fangll
 * 
 */
public class ConvertUtils {

	/**
	 * 将String数组转换为Long类型数组
	 * 
	 * @param strs
	 * @return
	 */
	public static Long[] StringToLong(String[] strs) {
		Long[] longArr = new Long[strs.length];
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i];
			Long thelong = Long.valueOf(str);
			longArr[i] = thelong;
		}
		return longArr;
	}

	/**
	 * 将Long数组转换为String类型数组
	 * 
	 * @param longs
	 * @return
	 */
	public static String[] LongToString(Long[] longs) {
		String[] strArr = new String[longs.length];
		for (int i = 0; i < longs.length; i++) {
			String str = longs[i] + "";
			strArr[i] = str;
		}
		return strArr;
	}

	/**
	 * 将null转为""
	 * 
	 * @param str
	 * @return
	 */
	public static String NVL(String str) {
		return str == null ? "" : str;
	}

	/**
	 * 将null转为""
	 * 
	 * @param obj
	 * @return
	 */
	public static String NVL(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	/**
	 * obj为null时返回value,否则不变
	 * 
	 * @param obj
	 *            ,value
	 * @return object
	 */
	public static Object NVL(Object obj, Object value) {
		return obj == null ? value : obj;
	}

	/**
	 * 解析字符串为Long类型，若字符串是无效的Long型数据，则返回null，否则返回Long型数据
	 * 
	 * @param sLongId
	 * @return
	 */
	public static Long parseLong(String sLong) {
		Long l = null;
		try {
			l = (sLong == null) ? null : Long.parseLong(sLong);
		} catch (Exception e) {
			l = null;
		}
		return l;
	}

	/**
	 * 右补填字符串，为长度length的字符串，例如将字符串右补空格：'123'->'123 '
	 * 
	 * @param str
	 * @param length
	 *            长度
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String fillSpace(String str, int length)
			throws UnsupportedEncodingException {
		if (str == null || "null".equals(str.trim()))
			str = "";
		int fill_length = length - str.getBytes().length;
		// System.out.println(str+" 不齐空格长度："+fill_length);
		for (int i = 0; i < fill_length; i++) {
			str += " ";
		}

		return str;
	}

	/**
	 * 右补填字符串，为长度length的字符串，例如将字符串右补空格：'123'->'123 '
	 * 
	 * @param str
	 * @param length
	 *            长度
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String fillLeftSpace(String str, int length)
			throws UnsupportedEncodingException {
		if (str == null)
			str = "";

		int fill_length = length - str.getBytes().length;

		for (int i = 0; i < fill_length; i++) {
			str = " " + str;
		}

		return str;
	}

	/**
	 * 
	 * converReturnJson 方法
	 * <p>
	 * 常用于dwr返回值
	 * </p>
	 * 
	 * @param flag
	 * @param msg
	 * @return
	 * @return String
	 * @author zwj
	 * @date 2012-3-26
	 */
	public static String converReturnJson(boolean flag, String msg) {
		String str = "{'flag':" + flag;
		if (msg == null || StringUtil.isEmpty(msg.trim())) {
			str += "}";
		} else {
			str += ",'msg':'" + msg + "'}";
		}
		return str;
	}

	public static String convertSplitStrToOracleCondition(String splitStr,
			String split) {
		if (StringUtil.isEmpty(splitStr)) {
			return "";
		}
		String[] arr = splitStr.split(split);
		String result = "";
		for (String str : arr) {
			if (!StringUtil.isEmpty(str)) {
				result += "'" + str + "',";
			}
		}
		if (result.endsWith(",")) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	/**
	 * xuzhou
	 * 
	 * @param str
	 * @return
	 */
	public static String getMoneyString(Long l) {
		Double re = (double)l / 100;
		return re.toString();
	}

	public static Long getMoneyLong(String str) {
		
		return Long.valueOf((long) (Double.valueOf(str)*100));
	}

	public static void main(String[] args) {
		System.out.println(ConvertUtils.getMoneyLong("100.6789"));
		System.out.println(ConvertUtils.getMoneyString(100679L));
	}
}
