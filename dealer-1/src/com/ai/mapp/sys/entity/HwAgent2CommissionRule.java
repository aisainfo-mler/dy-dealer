package com.ai.mapp.sys.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name="HW_AGENT2COMMISSION_RULE")
public class HwAgent2CommissionRule implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="hw_agent2commission_rule_gen")
    @SequenceGenerator(name="hw_agent2commission_rule_gen",sequenceName="SEQ_HW_AGENT2COMMISSION_RULE")
	@Column(name="RULE_RELA_ID")
	private Long ruleRelaId;

	@Column(name="AGENT_ID")
	private Long agentId;

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=CommissionRule.class)
	@JoinColumn(name="RULE_ID",referencedColumnName="RULEID")
	@NotFound(action=NotFoundAction.IGNORE)
	private CommissionRule commissionRule;
	
	@Column(name="CREATOR")
	private String creator;
	
	@Column(name="CREATOR_TIME")
	private Date creatorTime;

	public Long getRuleRelaId() {
		return ruleRelaId;
	}

	public void setRuleRelaId(Long ruleRelaId) {
		this.ruleRelaId = ruleRelaId;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public CommissionRule getCommissionRule() {
		return commissionRule;
	}

	public void setCommissionRule(CommissionRule commissionRule) {
		this.commissionRule = commissionRule;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatorTime() {
		return creatorTime;
	}

	public void setCreatorTime(Date creatorTime) {
		this.creatorTime = creatorTime;
	}
	
}

