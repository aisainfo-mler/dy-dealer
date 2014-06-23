package com.ailk.yd.mapp.tibco;

import java.util.Collection;
import java.util.Map;

import com.ai.mapp.sys.entity.HwCircle;
import com.ai.mapp.sys.entity.HwCity;
import com.ai.mapp.sys.entity.HwCountry;
import com.ai.mapp.sys.entity.HwDistrict;
import com.ai.mapp.sys.entity.HwState;

public class TibcoCache {
	
	
	public static Collection<HwCountry> countrys ;
	public static Collection<HwState> state  ;
	public static Collection<HwCircle> circle;
	public static Collection<HwCity> citys ;
	public static Collection<HwDistrict> districts;
	
	/**
	 * 静态常量。提供给终端使用的。取自hw_sys_prop表。应用启动时加载一次
	 */
	public static Map dicts;
}
