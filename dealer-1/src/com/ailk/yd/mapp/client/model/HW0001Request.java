package com.ailk.yd.mapp.client.model;


import java.util.Map;
import java.util.Set;

import com.ailk.yd.mapp.model.YDBody;

public class HW0001Request extends YDBody {
	
	private Map<String,Set<Object>> filterMap;
	
	private Set<String> codes;
	
	private String serviceType;//PRODUCT：PRODUCT，PLAN：PLAN
	
	/**
	 * 必须在选择了serviceType时才会起效
	 * 如果serviceType=PRODUCT，则查询属性表ProductOffering_type
	 * 如果serviceType=PLAN，则查询属性表PlanOffering_type
	 */
	private String type;
	
	private Integer page;
	
	private Integer size;

	public Map<String, Set<Object>> getFilterMap() {
		return filterMap;
	}

	public void setFilterMap(Map<String, Set<Object>> filterMap) {
		this.filterMap = filterMap;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Set<String> getCodes() {
		return codes;
	}

	public void setCodes(Set<String> codes) {
		this.codes = codes;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
