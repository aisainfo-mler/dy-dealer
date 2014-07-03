package com.ai.mapp.sys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-13 下午06:11:19
 * 类说明:系统参数
 */

@Entity
@Table(name="HW_SYS_PROP")
public class SysProp implements Serializable{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO,generator="hw_sys_prop_gen")
    @SequenceGenerator(name="hw_sys_prop_gen",sequenceName="HW_SYS_PROP$SEQ")
	@Column(name="PROP_ID")
	private Long id;
	 
	@Column(name="PROP_NAME")
	private String name;
	
	@Column(name="PROP_KEY")
	private String key;
	
	@Column(name="PARENT_KEY")
	private String parentKey;
	
	@Column(name="PROP_DESCRIPTION")
	private String remark;
	
	@Column(name="UPDATETIME")
	private Date updateTime;
	
	@Column(name="PROP_VALID")
	private String valid;
	
	@Column(name="PROP_ORDER")
	private String order;
	
	@Column(name="PROP_TYPE")
	private String ptype;
	
	@Transient
	private String propNameLike;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getPropNameLike() {
		return propNameLike;
	}

	public void setPropNameLike(String propNameLike) {
		this.propNameLike = propNameLike;
	}

	public SysProp() {
		super();
	}

	public SysProp(String name, String key, String parentKey, String remark,
			String valid) {
		super();
		this.name = name;
		this.key = key;
		this.parentKey = parentKey;
		this.remark = remark;
		this.valid = valid;
	}

	@Transient
	private Set<String> propertiesKeys;

	public Set<String> getPropertiesKeys() {
		return propertiesKeys;
	}

	public void setPropertiesKeys(Set<String> propertiesKeys) {
		this.propertiesKeys = propertiesKeys;
	}
}
