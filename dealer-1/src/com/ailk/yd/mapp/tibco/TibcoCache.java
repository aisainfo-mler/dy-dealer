package com.ailk.yd.mapp.tibco;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.mapp.sys.entity.HwCircle;
import com.ai.mapp.sys.entity.HwCity;
import com.ai.mapp.sys.entity.HwCountry;
import com.ai.mapp.sys.entity.HwDistrict;
import com.ai.mapp.sys.entity.HwState;
import com.ailk.yd.mapp.client.model.HW0038Response.City;

public class TibcoCache {
	
	
//	public static Collection<HwCountry> countrys ;
//	public static Collection<HwState> state  ;
//	public static Collection<HwCircle> circle;
//	public static Collection<HwCity> citys ;
//	public static Collection<HwDistrict> districts;
	
	
	/**
	 * code:name
	 */
	public static Map<String,String> states;
	
	/**
	 * stateCode:[districtCode:districtName]
	 */
	public static Map<String,Map<String,String>> districtInState;
	
	/**
	 * code:name
	 */
	public static Map<String,String> countrys;
	
	public static Map<String,List<City>> cityInState;
	
	/**
	 * code:name
	 */
	public static Map<String,String> circles;
	
	
	/**
	 * 静态常量。提供给终端使用的。取自hw_sys_prop表。应用启动时加载一次
	 */
	public static Map dicts;
	

}
