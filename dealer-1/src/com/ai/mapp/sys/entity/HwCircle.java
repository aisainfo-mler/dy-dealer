package com.ai.mapp.sys.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="HW_CIRCLE")
public class HwCircle implements Serializable {

	@Id
	@Column(name = "CIRCLE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long ciecleId;

	@Column(name = "CIRCLE_CODE")
	private String circle_code;
	
	@Column(name = "CIRCLE_NAME")
	private String circle_name;
	
	@Column(name = "cir_desc")
	private String cirDesc;

	public Long getCiecleId() {
		return ciecleId;
	}

	public void setCiecleId(Long ciecleId) {
		this.ciecleId = ciecleId;
	}

	public String getCircle_code() {
		return circle_code;
	}

	public void setCircle_code(String circle_code) {
		this.circle_code = circle_code;
	}

	public String getCircle_name() {
		return circle_name;
	}

	public void setCircle_name(String circle_name) {
		this.circle_name = circle_name;
	}

	public String getCirDesc() {
		return cirDesc;
	}

	public void setCirDesc(String cirDesc) {
		this.cirDesc = cirDesc;
	}

	
}
