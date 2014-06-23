package com.ai.mapp.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-21 下午05:22:40
 * 类说明:
 */

@Entity
@Table(name="HW_MENU")
public class Menu implements java.io.Serializable {
	
	@Id
	@Column(name="MENU_ID")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="HW_MENU_GEN")
	@SequenceGenerator(name="HW_MENU_GEN",sequenceName="HW_MENU_SEQ")
	private Long id;
	
	@Column(name="MENU_NAME")
	private String name;
	
	@Column(name="MENU_URL")
	private String url;
	
	@Column(name="MENU_STATUS")
	private String status;
	
	@Column(name="MENU_INDEX")
	private Integer index;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Menu.class)
	@JoinColumn(name="PARENT_ID",referencedColumnName="MENU_ID",nullable=true)
	@NotFound(action=NotFoundAction.IGNORE)
	private Menu parent;
	
	@Column(name="IS_WHOLE")
	private String isWhole;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public String getIsWhole() {
		return isWhole;
	}

	public void setIsWhole(String isWhole) {
		this.isWhole = isWhole;
	}
	
}
