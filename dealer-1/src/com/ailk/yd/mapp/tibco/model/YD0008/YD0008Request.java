package com.ailk.yd.mapp.tibco.model.YD0008;

import com.ailk.yd.mapp.tibco.model.TibcoRequest;
import com.ailk.yd.mapp.tibco.model.YdPage;

/**
 * @author Zhengwj
 * @version 创建时间：2014-4-28 下午06:14:57 类说明:号码搜索
 */

public class YD0008Request implements TibcoRequest {
	/**
	 * 用户输入的模式串号码
	 */
	private String searchPattern="";
	
	private YdPage paging;
	
	/**
	 * MSISDN
	 */
	private String type="";
	
	/**
	 * 靓号级别。 BRONZE, BRONZEPLUS SILVER SILVERPLUS GOLD GOLDPLUS PLATINUM
	 * PLATINUMPLUS DIAMOND
	 */
	private String vanityName="";
	
	/**
	 * 是否包含靓号。true，false
	 */
	private String includeVanityNumbers="";
	
	/**
	 * 区域
	 */
	private String ciecleId="";



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVanityName() {
		return vanityName;
	}

	public void setVanityName(String vanityName) {
		this.vanityName = vanityName;
	}

	public String getIncludeVanityNumbers() {
		return includeVanityNumbers;
	}

	public void setIncludeVanityNumbers(String includeVanityNumbers) {
		this.includeVanityNumbers = includeVanityNumbers;
	}

	public String getCiecleId() {
		return ciecleId;
	}

	public void setCiecleId(String ciecleId) {
		this.ciecleId = ciecleId;
	}

	public YdPage getPaging() {
		return paging;
	}

	public void setPaging(YdPage paging) {
		this.paging = paging;
	}

	public String getSearchPattern() {
		return searchPattern;
	}

	public void setSearchPattern(String searchPattern) {
		this.searchPattern = searchPattern;
	}

}
