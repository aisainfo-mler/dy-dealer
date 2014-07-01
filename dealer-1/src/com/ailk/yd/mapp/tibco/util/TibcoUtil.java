package com.ailk.yd.mapp.tibco.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;

public class TibcoUtil {

	/**
	 * @param errResponseJson
	 *            { \"success\": false, \"general_message\": \"This is an
	 *            operation implementation generated fault\", \"errors\": {
	 *            \"findCustomerProfileException\":
	 *            \"RIL4G-B-MDM_FindCustomer-NO-DATANo Data Found in MDM For the
	 *            given Request\" } }
	 * @return
	 */
	public static String findErrMsg(JSONObject errResponseJson) {
		String errMsg = "";
		JSONObject err = errResponseJson.getJSONObject("errors");
		Set ks = err.keySet();
		for (Iterator it = ks.iterator(); it.hasNext();) {
			String key = (String) it.next();
			String errValue = (String) err.get(key);
			errMsg = key + ":" + errValue;
		}
		return errMsg;
	}
	
	/**
	 * 判断是否json格式
	 * @param in
	 * @return
	 */
	public static boolean isJsonFormat(String in){
		try {
			JSONObject.fromObject(in);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static String getCurTime(){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		return sdf.format(d);
	}
	
	public static void checkNotNull(String obj,String name) throws Exception{
		if(StringUtils.isBlank(obj)){
			throw new Exception(name + " can't be null!");
		}
	}
	
	public static void main(String[] args) {
		System.err.println(getCurTime());
	}
}
