package com.ailk.yd.mapp.client.model;

import java.util.List;
import java.util.Map;

import com.ailk.yd.mapp.model.YDBody;

public class HW0038Response extends YDBody {
	
	private Map<String,List<City>> cityInState;
	
	private Map<String,Map<String,String>> districtInState;

	
	public static class City{
		private String cityCode;
		private String cityName;
		private String circleId;
		
		public String getCityCode() {
			return cityCode;
		}
		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public String getCircleId() {
			return circleId;
		}
		public void setCircleId(String circleId) {
			this.circleId = circleId;
		}
		public City(String cityCode, String cityName, String circleId) {
			super();
			this.cityCode = cityCode;
			this.cityName = cityName;
			this.circleId = circleId;
		}
		public City() {
			super();
		}
		
	}

	public Map<String, List<City>> getCityInState() {
		return cityInState;
	}

	public void setCityInState(Map<String, List<City>> cityInState) {
		this.cityInState = cityInState;
	}

	public Map<String, Map<String, String>> getDistrictInState() {
		return districtInState;
	}

	public void setDistrictInState(Map<String, Map<String, String>> districtInState) {
		this.districtInState = districtInState;
	}

}
