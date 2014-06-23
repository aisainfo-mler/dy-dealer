package com.ai.mapp.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hw_verify_sms")
public class VerifySms  implements Serializable{
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	@Id
	private Integer id;
	
	@Column(name="dealerid")
	private Integer dealerid;
	
	@Column(name="svcnum")
	private String svcnum;

	@Column(name="code")
	private String code;
	
	@Column(name="validtime")
	private Date validtime;
	
	@Column(name="invalidtime")
	private Date invalidtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDealerid() {
		return dealerid;
	}

	public void setDealerid(Integer dealerid) {
		this.dealerid = dealerid;
	}

	public String getSvcnum() {
		return svcnum;
	}

	public void setSvcnum(String svcnum) {
		this.svcnum = svcnum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getValidtime() {
		return validtime;
	}

	public void setValidtime(Date validtime) {
		this.validtime = validtime;
	}

	public Date getInvalidtime() {
		return invalidtime;
	}

	public void setInvalidtime(Date invalidtime) {
		this.invalidtime = invalidtime;
	}
	
}
