package com.ailk.yd.mapp.client.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ailk.yd.mapp.model.YDBody;

public class HW0037Request extends YDBody {

	private Map<String, Integer> tracTypes;

	public Map<String, Integer> getTracTypes() {
		return tracTypes;
	}

	public void setTracTypes(Map<String, Integer> tracTypes) {
		this.tracTypes = tracTypes;
	}

}
