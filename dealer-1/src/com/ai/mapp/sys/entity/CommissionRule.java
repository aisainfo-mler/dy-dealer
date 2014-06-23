package com.ai.mapp.sys.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

/**
 * 佣金规则实体
 * @author Luyang 
 * @version 创建时间：2012-12-11
 * 类说明:
 */

@Entity
@Table(name="HW_COMMISSION_RULE")
public class CommissionRule implements java.io.Serializable {
	
	@Id
	@Column(name="RULEID")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="HW_COMMISSION_RULE_GEN")
	@SequenceGenerator(name="HW_COMMISSION_RULE_GEN",sequenceName="HW_COMMISSION_RULE_SEQ")
	private Long ruleId;
	
	@Column(name="BACK_EXPRESS")
	private String backExpress;
	
	@Column(name="RULE_DESC")
	private String description;
	
	@Column(name="RULE_COND")
	private String condition;//x:0-100-0.5;100-200-5;末尾那个代表佣金率 缺省%
	
	@Column(name="VALID")
	private String valid;
	
	@Column(name="STARTTIME")
	private Date startTime;
	
	@Column(name="ENDTIME")
	private Date endTime;
	
	@Column(name="CREATETIME")
	private Date createTime;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="CREATOR",referencedColumnName="OPERATOR_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private User creator;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="UPDATER",referencedColumnName="OPERATOR_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private User updater;
	
	@Column(name="UPDATETIME")
	private Date updateTime;
	
	@Column(name="BACK_TYPE")
	private String backType;
	
	@Column(name="MOD_ID")
	private Long modId;
	
	@Transient
	private Long queryType;  //101:except joined rule
	
	@Transient
	private Long agentId;

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public String getBackExpress() {
		return backExpress;
	}

	public void setBackExpress(String backExpress) {
		this.backExpress = backExpress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getUpdater() {
		return updater;
	}

	public void setUpdater(User updater) {
		this.updater = updater;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getBackType() {
		return backType;
	}

	public void setBackType(String backType) {
		this.backType = backType;
	}
	
	@Column(name="RULE_NAME")
	private String ruleName;

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
	@Column(name="PAY_TYPE")
	private String payType;//支付方式
	
	@Transient
	private Map<String,List<String[]>> conditionMap;//条件
	
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Map<String, List<String[]>> getConditionMap() {
		return conditionMap;
	}

	public void setConditionMap(Map<String, List<String[]>> conditionMap) {
		this.conditionMap = conditionMap;
	}

	public Long getQueryType() {
		return queryType;
	}

	public void setQueryType(Long queryType) {
		this.queryType = queryType;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public Long getModId() {
		return modId;
	}

	public void setModId(Long modId) {
		this.modId = modId;
	}
	
}
