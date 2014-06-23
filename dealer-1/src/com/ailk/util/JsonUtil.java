package com.ailk.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Transient;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	/**
	 * json串的分隔符
	 */
	@Transient
	public static String SPLIT = "@-@";

	/**
	 * 对象转成json串
	 * @param obj
	 * @return
	 */
	public static String toJsonString(Object obj) {
		ObjectMapper om = new ObjectMapper();
		try {
			return om.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	/**
	 * list对象转成json串
	 * @param list
	 * @return
	 */
	public static String ListToJsonString(List list) {
		String rm = "";
		ObjectMapper om = new ObjectMapper();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Object obj = (Object) it.next();
			try {
				String writeValueAsString = om.writeValueAsString(obj);
				rm += writeValueAsString;
				if(it.hasNext()){
					rm += SPLIT;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}
		return rm;
	}

	/**
	 * json串转成单个对象
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static Object fromJsonString(String jsonStr, Class clazz) {
		if (jsonStr == null) {
			return null;
		}
		ObjectMapper om = new ObjectMapper();
		try {
			return om.readValue(jsonStr, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * json穿转成list对象。
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static List fromJsonStringReturnList(String jsonStr, Class clazz) {
		ArrayList rm = new ArrayList();
		if(jsonStr==null){
			return rm;
		}
		
		String[] strs = jsonStr.split(SPLIT);
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i];
			rm.add(fromJsonString(str, clazz));
		}
		return rm;
	}
}
