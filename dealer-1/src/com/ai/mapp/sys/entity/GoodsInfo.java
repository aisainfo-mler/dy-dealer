package com.ai.mapp.sys.entity;

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

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-19 下午04:58:30
 * 类说明:
 */
@Entity
@Table(name="HW_GOODS_INFO")
public class GoodsInfo implements java.io.Serializable {
	private Long id;
	private String name;
	private String comments;
	private FileRelation listpic;
	private String type;//商品类型：01：16K白卡;02：32K白卡;03：手机
	private Long price;
	private Date createTime;
	private Date updateTime;
	private User creator;
	private User updateor;
	private Long salePrice;
	
	
	public GoodsInfo(Long id) {
		super();
		this.id = id;
	}
	
	
	public GoodsInfo() {
		super();
	}


	@Id
	@Column(name="GOOD_ID")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="HW_ORDER_INFO_GEN")
	@SequenceGenerator(name="HW_ORDER_INFO_GEN",sequenceName="HW_ORDER_INFO_SEQ")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="GOOD_NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="COMMENTS")
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=FileRelation.class)
	@JoinColumn(name="LISTPIC",referencedColumnName="ID")
	@NotFound(action=NotFoundAction.IGNORE)
	public FileRelation getListpic() {
		return listpic;
	}
	public void setListpic(FileRelation listpic) {
		this.listpic = listpic;
	}
	
	@Column(name="GOOD_TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="PRICE")
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
	@Column(name="CREATETIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="UPDATETIME")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="CREATOR",referencedColumnName="OPERATOR_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="UPDATOR",referencedColumnName="OPERATOR_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	public User getUpdateor() {
		return updateor;
	}
	public void setUpdateor(User updateor) {
		this.updateor = updateor;
	}
	@Column(name="SALEPRICE")
	public Long getSalePrice() {
		return salePrice;
	}


	public void setSalePrice(Long salePrice) {
		this.salePrice = salePrice;
	}

	private Collection<Long> goodIds;
	
	@Transient
	public Collection<Long> getGoodIds() {
		return goodIds;
	}

	public void setGoodIds(Collection<Long> goodIds) {
		this.goodIds = goodIds;
	}
	
	public static final String SPECIALSEARCH_OPERATOR_HAS = "OPERATOR_HAS";
	
	private String specialSearch;

	@Transient
	public String getSpecialSearch() {
		return specialSearch;
	}

	public void setSpecialSearch(String specialSearch) {
		this.specialSearch = specialSearch;
	}
	
	private String userId;
	
	@Transient
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private Long[] goodNum = new Long[3];

	@Transient
	public Long[] getGoodNum() {
		return goodNum;
	}


	public void setGoodNum(Long[] goodNum) {
		this.goodNum = goodNum;
	}
	
	
	
	private String bssId;
	
	private String bssUrl;
	
	private String status;

	@Column(name="BSS_GOOD_ID")
	public String getBssId() {
		return bssId;
	}


	public void setBssId(String bssId) {
		this.bssId = bssId;
	}

	@Column(name="BSS_PIC_URL")
	public String getBssUrl() {
		return bssUrl;
	}


	public void setBssUrl(String bssUrl) {
		this.bssUrl = bssUrl;
	}

	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 操作类型，0 新增，1修改，2删除
	 */
	private String optType;

	@Transient
	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}
	
	
	private Long agentPrice;
	
	private Integer serviceMonth;
	
	private String ifRangePhone;
	
	private Long rangePrice;


	@Column(name="AGENT_PRICE")
	public Long getAgentPrice() {
		return agentPrice;
	}


	public void setAgentPrice(Long agentPrice) {
		this.agentPrice = agentPrice;
	}

	@Column(name="SERVICE_MONTH")
	public Integer getServiceMonth() {
		return serviceMonth;
	}


	public void setServiceMonth(Integer serviceMonth) {
		this.serviceMonth = serviceMonth;
	}

	@Column(name="IS_RANGEPHONE")
	public String getIfRangePhone() {
		return ifRangePhone;
	}


	public void setIfRangePhone(String ifRangePhone) {
		this.ifRangePhone = ifRangePhone;
	}

	@Column(name="RANGE_PRICE")
	public Long getRangePrice() {
		return rangePrice;
	}


	public void setRangePrice(Long rangePrice) {
		this.rangePrice = rangePrice;
	}
	
	
}
