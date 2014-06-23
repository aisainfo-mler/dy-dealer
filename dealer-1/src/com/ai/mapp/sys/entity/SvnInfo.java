package com.ai.mapp.sys.entity;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name="HW_SVN")
public class SvnInfo implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="hw_svn_gen")
    @SequenceGenerator(name="hw_svn_gen",sequenceName="HW_SVN$SEQ")
	@Column(name="svnId")
	private Long svnId;
	
	@Column(name="svn")
	private String svn;
	
	@Column(name="status")
	private String status;
	
	@Column(name="amount")
	private Long amount;
	
	@Column(name="startTime")
	private Date startTime;
	
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=User.class)
	@JoinColumn(name="startUser",referencedColumnName="OPERATOR_USERCODE")
	@NotFound(action=NotFoundAction.IGNORE)
	private User startUser;
	
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=User.class)
	@JoinColumn(name="customer",referencedColumnName="OPERATOR_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private User customer;
	
	@Column(name="SVCLEVEL")
	private String svcLevel;

	public Long getSvnId() {
		return svnId;
	}

	public void setSvnId(Long svnId) {
		this.svnId = svnId;
	}

	public String getSvn() {
		return svn;
	}

	public void setSvn(String svn) {
		this.svn = svn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public User getStartUser() {
		return startUser;
	}

	public void setStartUser(User startUser) {
		this.startUser = startUser;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}
	
	public String getSvcLevel() {
		return svcLevel;
	}

	public void setSvcLevel(String svcLevel) {
		this.svcLevel = svcLevel;
	}

	@Transient
	private String pattern;

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	@Transient
	public Collection<SvnProduct> svnProducts;

	public Collection<SvnProduct> getSvnProducts() {
		return svnProducts;
	}

	public void setSvnProducts(Collection<SvnProduct> svnProducts) {
		this.svnProducts = svnProducts;
	}

	
	
}
