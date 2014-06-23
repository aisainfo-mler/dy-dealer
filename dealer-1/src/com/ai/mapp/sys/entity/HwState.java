package com.ai.mapp.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="HW_STATE")
public class HwState implements java.io.Serializable {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO,generator="GEN_HW_STATE")
    @SequenceGenerator(name="GEN_HW_STATE",sequenceName="SEQ_HW_STATE")
	@Column(name="STATE_ID")
	private Long stateId;
	
	@Column(name="STATE_CODE")
	private String stateCode;
	
	@Column(name="STATE_NAME")
	private String stateName;
	
	@Column(name="STATE_DESC")
	private String stateDesc;
	
	@Column(name="FLAG")
	private String flag;
	
	public HwState() {
		super();
	}

	public HwState(String stateCode) {
		super();
		this.stateCode = stateCode;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

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

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
