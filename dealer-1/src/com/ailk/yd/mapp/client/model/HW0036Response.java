package com.ailk.yd.mapp.client.model;

import java.util.List;
import java.util.Map;

import com.ailk.butterfly.mapp.core.model.IBody;

public class HW0036Response extends IBody{

	
	private Map<String,String> states;
	
	private List<Country> countrys;
	
	private Map dicts;

	public static class State extends IBody{
		private String stateCode;
		
		private String stateName;

		public String getStateCode() {
			return stateCode;
		}

		public void setStateCode(String stateCode) {
			this.stateCode = stateCode;
		}

		public String getStateName() {
			return stateName;
		}

		public void setStateName(String stateName) {
			this.stateName = stateName;
		}
		
	}

	public Map<String, String> getStates() {
		return states;
	}

	public void setStates(Map<String, String> states) {
		this.states = states;
	}

	public static class Country extends IBody{
		private String countryCode;
		
		private String countryName;
		
		private String nationalltyName;
		
		private String countryPhone;

		public String getCountryCode() {
			return countryCode;
		}

		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}

		public String getCountryName() {
			return countryName;
		}

		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}

		public String getNationalltyName() {
			return nationalltyName;
		}

		public void setNationalltyName(String nationalltyName) {
			this.nationalltyName = nationalltyName;
		}

		public String getCountryPhone() {
			return countryPhone;
		}

		public void setCountryPhone(String countryPhone) {
			this.countryPhone = countryPhone;
		}
		
	}
	
	public Map getDicts() {
		return dicts;
	}

	public void setDicts(Map dicts) {
		this.dicts = dicts;
	}

	public List<Country> getCountrys() {
		return countrys;
	}

	public void setCountrys(List<Country> countrys) {
		this.countrys = countrys;
	}
	
}
