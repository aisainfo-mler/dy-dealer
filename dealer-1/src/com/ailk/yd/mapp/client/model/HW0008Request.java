package com.ailk.yd.mapp.client.model;

import com.ailk.yd.mapp.model.YDBody;

public class HW0008Request extends YDBody {
	/**
	 * 用户输入的模式串号码
	 */
	private String pattern;

	/**
	 * 偏移量
	 */
	private String page;
	/**
	 * 获取推荐的数量
	 */
	private String size;
	
	/**
	 * MSISDN
	 */
	private String type;

	/**
	 * 靓号级别。 BRONZE, BRONZEPLUS SILVER SILVERPLUS GOLD GOLDPLUS PLATINUM
	 * PLATINUMPLUS DIAMOND
	 */
	private String vanityName;
	
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String start) {
		this.page = start;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getVanityName() {
		return vanityName;
	}

	public void setVanityName(String vanityName) {
		this.vanityName = vanityName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
