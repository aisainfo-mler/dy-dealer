package com.ailk.yd.mapp.client.model;

import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-8-4 下午03:50:01
 * 类说明:
 */

public class HW0043Response extends YDBody {
	
	/**
	 * 地区信息
	 */
	private List<Area> areaInfo;
	
	/**
	 * 地区信息
	 */
	private List<Area> defaultChildren;
	
	
	public List<Area> getDefaultChildren() {
		return defaultChildren;
	}

	public void setDefaultChildren(List<Area> defaultChildren) {
		this.defaultChildren = defaultChildren;
	}

	public List<Area> getAreaInfo() {
		return areaInfo;
	}


	public void setAreaInfo(List<Area> areaInfo) {
		this.areaInfo = areaInfo;
	}

	public static class Area extends YDBody {
		private String selfCode;
		
		private String selfName;
		
		private String upCode;
		
		private String circleId;

		public String getSelfCode() {
			return selfCode;
		}

		public void setSelfCode(String selfCode) {
			this.selfCode = selfCode;
		}

		public String getSelfName() {
			return selfName;
		}

		public void setSelfName(String selfName) {
			this.selfName = selfName;
		}

		public String getUpCode() {
			return upCode;
		}

		public void setUpCode(String upCode) {
			this.upCode = upCode;
		}

		public String getCircleId() {
			return circleId;
		}

		public void setCircleId(String circleId) {
			this.circleId = circleId;
		}
		
	}
	
}
