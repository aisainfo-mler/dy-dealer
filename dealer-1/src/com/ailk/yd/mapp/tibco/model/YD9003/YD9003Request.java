package com.ailk.yd.mapp.tibco.model.YD9003;

import java.util.List;
import java.util.Map;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-4-28 下午03:22:58
 * 类说明:终端信息和预览图片更新
 */

public class YD9003Request extends YDBody{
	/**
	 * 终端列表
	 */
	private List<Phone> phoneList;
	
	public List<Phone> getPhoneList() {
		return phoneList;
	}


	public void setPhoneList(List<Phone> phoneList) {
		this.phoneList = phoneList;
	}


	public static class Phone extends YDBody {
		/**
		 * 终端ID
		 */
		private String phoneId;
		/**
		 * 型号
		 */
		private String model;
		/**
		 * 是否智能
		 */
		private String ifInt;
		/**
		 * 外观
		 */
		private String exterior;
		/**
		 * 操作系统
		 */
		private String os;
		/**
		 * 网络频段
		 */
		private String network;
		/**
		 * 数据传输
		 */
		private String trans;
		/**
		 * 浏览器
		 */
		private String browser;
		/**
		 * 内存
		 */
		private String memory;
		/**
		 * 扩展内存
		 */
		private String extraMemory;
		/**
		 * 屏幕尺寸
		 */
		private String screen;
		/**
		 * 是否宽屏
		 */
		private String isWideScreen;
		/**
		 * 屏幕类型
		 */
		private String screenType;
		/**
		 * 分辨率
		 */
		private String resolution;
		/**
		 * 音频格式
		 */
		private String musicType;
		/**
		 * 视频格式
		 */
		private String videoFormat;
		/**
		 * 电池容量
		 */
		private String batterySpace;
		/**
		 * 是否Gps
		 */
		private String gps;
		/**
		 * 是否蓝牙
		 */
		private String blue;
		/**
		 * 品牌
		 */
		private String brand;
		/**
		 * 相关配件
		 */
		private String instructions;
		/**
		 * 商品信息图
		 * url(比如缩略图)作为KEY
		 * orgiUrl 作为VALUE
		 */
		private List<Map<String,String>> imgInfoList;
		
		/**
		 * 操作类型，0 新增，1修改，2删除
		 */
		private String optType;
		/**
		 * 电池类型
		 */
		private String batteryType;
		
		/**
		 * 颜色
		 */
		private String color;
		
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public String getBatteryType() {
			return batteryType;
		}
		public void setBatteryType(String batteryType) {
			this.batteryType = batteryType;
		}
		public String getPhoneId() {
			return phoneId;
		}
		public void setPhoneId(String phoneId) {
			this.phoneId = phoneId;
		}
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		public String getIfInt() {
			return ifInt;
		}
		public void setIfInt(String ifInt) {
			this.ifInt = ifInt;
		}
		public String getExterior() {
			return exterior;
		}
		public void setExterior(String exterior) {
			this.exterior = exterior;
		}
		public String getOs() {
			return os;
		}
		public void setOs(String os) {
			this.os = os;
		}
		public String getNetwork() {
			return network;
		}
		public void setNetwork(String network) {
			this.network = network;
		}
		public String getTrans() {
			return trans;
		}
		public void setTrans(String trans) {
			this.trans = trans;
		}
		public String getBrowser() {
			return browser;
		}
		public void setBrowser(String browser) {
			this.browser = browser;
		}
		public String getMemory() {
			return memory;
		}
		public void setMemory(String memory) {
			this.memory = memory;
		}
		public String getExtraMemory() {
			return extraMemory;
		}
		public void setExtraMemory(String extraMemory) {
			this.extraMemory = extraMemory;
		}
		public String getScreen() {
			return screen;
		}
		public void setScreen(String screen) {
			this.screen = screen;
		}
		public String getIsWideScreen() {
			return isWideScreen;
		}
		public void setIsWideScreen(String isWideScreen) {
			this.isWideScreen = isWideScreen;
		}
		public String getScreenType() {
			return screenType;
		}
		public void setScreenType(String screenType) {
			this.screenType = screenType;
		}
		public String getResolution() {
			return resolution;
		}
		public void setResolution(String resolution) {
			this.resolution = resolution;
		}
		public String getMusicType() {
			return musicType;
		}
		public void setMusicType(String musicType) {
			this.musicType = musicType;
		}
		public String getVideoFormat() {
			return videoFormat;
		}
		public void setVideoFormat(String videoFormat) {
			this.videoFormat = videoFormat;
		}
		public String getBatterySpace() {
			return batterySpace;
		}
		public void setBatterySpace(String batterySpace) {
			this.batterySpace = batterySpace;
		}
		public String getGps() {
			return gps;
		}
		public void setGps(String gps) {
			this.gps = gps;
		}
		public String getBlue() {
			return blue;
		}
		public void setBlue(String blue) {
			this.blue = blue;
		}
		public String getBrand() {
			return brand;
		}
		public void setBrand(String brand) {
			this.brand = brand;
		}
		public String getInstructions() {
			return instructions;
		}
		public void setInstructions(String instructions) {
			this.instructions = instructions;
		}
		public List<Map<String, String>> getImgInfoList() {
			return imgInfoList;
		}
		public void setImgInfoList(List<Map<String, String>> imgInfoList) {
			this.imgInfoList = imgInfoList;
		}
		public String getOptType() {
			return optType;
		}
		public void setOptType(String optType) {
			this.optType = optType;
		}
		public Phone() {
			super();
		}
		public Phone(String phoneId, String model, String ifInt,
				String exterior, String os, String network, String trans,
				String browser, String memory, String extraMemory,
				String screen, String isWideScreen, String screenType,
				String resolution, String musicType, String videoFormat,
				String batterySpace, String gps, String blue, String brand,
				String instructions, List<Map<String, String>> imgInfoList) {
			super();
			this.phoneId = phoneId;
			this.model = model;
			this.ifInt = ifInt;
			this.exterior = exterior;
			this.os = os;
			this.network = network;
			this.trans = trans;
			this.browser = browser;
			this.memory = memory;
			this.extraMemory = extraMemory;
			this.screen = screen;
			this.isWideScreen = isWideScreen;
			this.screenType = screenType;
			this.resolution = resolution;
			this.musicType = musicType;
			this.videoFormat = videoFormat;
			this.batterySpace = batterySpace;
			this.gps = gps;
			this.blue = blue;
			this.brand = brand;
			this.instructions = instructions;
			this.imgInfoList = imgInfoList;
		}
		
		
	}

}
