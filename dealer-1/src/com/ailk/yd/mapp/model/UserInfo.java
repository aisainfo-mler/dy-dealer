package com.ailk.yd.mapp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.ailk.butterfly.core.security.IUserinfo;

public class UserInfo implements IUserinfo {

	private Integer userId;
	
	private String usercode;
	
	private String userName;
	
	private String nickName;
	
	private String mailBox;

	private Integer logoId;
	
	private Set<String> right;
	
	private String status;
	
	private String areaid;
	
	private Long cityid;
	
	private String level;
	
	private String provinceName;
	
	private String cityName;
	
	private String areaName;
	
	private Map<String,Map<String,String>> userSetting;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		if(userId != null)
			this.userId = userId.intValue();
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMailBox() {
		return mailBox;
	}

	public void setMailBox(String mailBox) {
		this.mailBox = mailBox;
	}

	public Integer getLogoId() {
		return logoId;
	}

	public void setLogoId(Integer logoId) {
		this.logoId = logoId;
	}

	public Map<String, Map<String, String>> getUserSetting() {
		return userSetting;
	}

	public void setUserSetting(Map<String, Map<String, String>> userSetting) {
		this.userSetting = userSetting;
	}

	@Override
	public String getUserSetting(String groupName, String key) {
		if(userSetting == null || userSetting.get(groupName) == null)
			return null;
		
		return userSetting.get(groupName).get(key);
	}

	@Override
	public void updateUserSetting(String groupname, String key, String value) {
		if(userSetting == null)
			userSetting = new HashMap<String,Map<String,String>>(0);
		
		if(userSetting.get(groupname) == null)
			userSetting.put(groupname, new HashMap<String,String>(0));
		
		userSetting.get(groupname).put(key,value);
	}

	public Set<String> getRight() {
		return right;
	}

	public void setRight(Set<String> right) {
		this.right = right;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public Long getCityid() {
		return cityid;
	}

	public void setCityid(Long cityid) {
		this.cityid = cityid;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	
	
}
