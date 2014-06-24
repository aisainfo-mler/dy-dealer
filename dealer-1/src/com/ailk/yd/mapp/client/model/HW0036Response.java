package com.ailk.yd.mapp.client.model;

import java.util.Map;

import com.ailk.butterfly.mapp.core.model.IBody;

public class HW0036Response  extends IBody{

	
	private Map<String,String> states;
	
	private Map<String,String> countrys;
	
	private Map dicts;

	public Map getDicts() {
		return dicts;
	}

	public void setDicts(Map dicts) {
		this.dicts = dicts;
	}

	public Map<String, String> getStates() {
		return states;
	}

	public void setStates(Map<String, String> states) {
		this.states = states;
	}


	
	public Map<String, String> getCountrys() {
		return countrys;
	}

	public void setCountrys(Map<String, String> countrys) {
		this.countrys = countrys;
	}

	
	
}
