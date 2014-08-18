package com.ai.mapp.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-8-13 下午10:19:46
 * 类说明:
 */
@Entity
@Table(name="HW_APP_VERSION")
public class AppVersion implements java.io.Serializable {
	@Id
	@Column(name="APP_ID")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="HW_APP_VERSION_GEN")
	@SequenceGenerator(name="HW_APP_VERSION_GEN",sequenceName="HW_APP_VERSION_SEQ")
	private Long appId;

	@Column(name="NAME")
	private String name;
	
	@Column(name="ITEMKEY")
	private String itemKey;
	
	@Column(name="LASTVERSION")
	private String lastVersion;
	
	@Column(name="COMPATIBLEVERSION")
	private String compatibleVersion;
	
	@Column(name="INTRODUCTION")
	private String introduction;
	
	@Column(name="UPDATEURL")
	private String updateUrl;
	
	@Column(name="ICON")
	private String icon;
	
	@Column(name="SYSTEM")
	private String system;
	
	@Column(name="APPSIZE")
	private Long appSize;
	
	@Column(name="APPTYPE")
	private String appType;
	
	@Column(name="OPERATOR")
	private String operator;
	
	@Column(name="CREATETIME")
	private Date createTime;
	
	@Column(name="PRODUCT_KEY")
	private String productKey;

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public String getLastVersion() {
		return lastVersion;
	}

	public void setLastVersion(String lastVersion) {
		this.lastVersion = lastVersion;
	}

	public String getCompatibleVersion() {
		return compatibleVersion;
	}

	public void setCompatibleVersion(String compatibleVersion) {
		this.compatibleVersion = compatibleVersion;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getUpdateUrl() {
		return updateUrl;
	}

	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public Long getAppSize() {
		return appSize;
	}

	public void setAppSize(Long appSize) {
		this.appSize = appSize;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}
	
}
