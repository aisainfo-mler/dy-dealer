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

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-23 上午11:17:01
 * 类说明:
 */

@Entity
@Table(name="HW_SVN_PRODUCT")
public class SvnProduct implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="HW_SVN_PRODUCT_GEN")
    @SequenceGenerator(name="HW_SVN_PRODUCT_GEN",sequenceName="HW_SVN_PRODUCT_GEN_SEQ")
	@Column(name="SVNPROID")
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=User.class)
	@JoinColumn(name="USERID",referencedColumnName="OPERATOR_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=Product.class)
	@JoinColumn(name="PRODUCTID",referencedColumnName="RANGEID")
	@NotFound(action=NotFoundAction.IGNORE)
	private Product product;
	
	@Column(name="UPDATETIME")
	private Date updateTime;
	
	@Column(name="CREATETIME")
	private Date createTime;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="SVN")
	private String svn;
	
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=User.class)
	@JoinColumn(name="CREATOR",referencedColumnName="OPERATOR_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private User creator;
	
	@Transient
	private String mainFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSvn() {
		return svn;
	}

	public void setSvn(String svn) {
		this.svn = svn;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getMainFlag() {
		return mainFlag;
	}

	public void setMainFlag(String mainFlag) {
		this.mainFlag = mainFlag;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
