package com.ailk.yd.mapp.client.model;

import java.util.Map;
import java.util.Set;

import com.ailk.yd.mapp.model.YDBody;

public class HW0037Response extends YDBody {

	private Map<String,Set> tracRefNums;

	public Map<String, Set> getTracRefNums() {
		return tracRefNums;
	}

	public void setTracRefNums(Map<String, Set> tracRefNums) {
		this.tracRefNums = tracRefNums;
	}
	
}
