package com.ai.mapp.sys.entity;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

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

@Entity
@Table(name="HW_PRODUCT")
public class Product implements java.io.Serializable {
	
	public static final String SERVICE_TYPE_PRODUCT="PRODUCT";
	
	public static final String SERVICE_TYPE_PLAN="PLAN";
	
	/*** 印度新增 用到的 字段***/
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO,generator="hw_product_gen")
    @SequenceGenerator(name="hw_product_gen",sequenceName="HW_PRODUCT$SEQ")
	@Column(name="RANGEID")
	private  Long rangeId;
	
	@Column(name="PRODUCT_DESC")
	private String Desc;//offer描述
	
	@Column(name="PACKEDNAME")
	private  String packedName;//OFFER的名称
	
	@Column(name="PRESTORE")
	private  Long prestore;//预存款
	
	@Column(name="BSS_ACCEPT_RANGE_ID")
	private  String bssRangeId;//tibco系统中offering的offerid
	
	@Column(name="PRICE")
	private Long price; //offering价格
	
//	@ManyToOne(fetch=FetchType.EAGER,targetEntity=Contract.class)
//	@JoinColumn(name="CONTRACTID",referencedColumnName="CONTRACTID")
	@Transient
	private Contract contract;//套餐内容和自费信息
	
	@Column(name="PAYTYPE")
	private String payType;//billingType 计费方式
	
	@Column(name="PRODUCT_SPEC_LIST")
	private String productSpecList;//productOffer 对应的productSpec的json
	
	@Column(name="PLAN_SPEC_LIST")
	private String planSpecList;//planOffer对应的planSpec的json字段
	
	@Column(name="RESOURCE_LIST")
	private String resourceList;//Offer对应的资源json
	
	@Column(name="PLAN_CODES")
	private String plancodes;//productOffer对应的planOffer
	
	@Column(name="STATUS")
	private String status;//offer 状态
	
	@Column(name="P_TYPE")
	private String type;
	
	@Column(name="P_SUBTYPE")
	private String subType;
	
	@Column(name="STARTDATE")
	private Date startDate;
	
	@Column(name="ENDDATE")
	private Date endDate;
	
	@Column(name="ISBUNDLE")
	private String isBundle;
	
	@Column(name="IS_DYN_PRICE")
	private String isDynamicPriceApplicable;
	
	@Column(name="IS_DYN_ELI")
	private String isDynamicEligibilityApplicable;
	
	@Column(name="IS_AUTO_RENEWABLE")
	private String isAutoRenewable;
	
	@Column(name="GGH")
	private String geographicLocationList;
	
	@Column(name="SERVICETYPE")
	private  String servicetype;
	
	/********************************************************************/
	
	@Column(name="SVCLEVEL")
	private  String svclevel;
	
	@Column(name="CONSUMPTION")
	private  Long consumption;
	
	@Column(name="PACKEDPHONE")
	private  Long packedPhone;
	
	@Column(name="PREFERENCE")
	private  String preference;
	
	@Column(name="TERMS")
	private  String terms;	
	
	/**
	 * 总部活动ID
	 */
	@Column(name="ACTIVITYID")
	private String actId;
	
	@Column(name="ACTTYPE")
	private String actType;
	
	@Column(name="ACTTYPEDES")
	private String actTypeDes;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Mobile.class)
	@JoinColumn(name="MOBILEID",referencedColumnName="MOBILEID",nullable=true)
	private Mobile mobile; 
	
	@Column(name="PERIOD")
	private String period;
	
	@Column(name="MACHINETYPEID")
	private String machineTypeId;
	
	@Column(name="THAWMON")
	private Long thawMon;
	
	@Column(name="RESOURCESFEE")
	private Long resourcesFee;
	
	@Column(name="PRESENETEDFEE")
	private Long presenetedFee;
	
	@Column(name="ALLFEE")
	private Long allFee;
	
	@Column(name="STOREDFEE")
	private Long storedFee;
	
	@Column(name="STOREDDESC")
	private String storedDesc;
	
	
	@Column(name="RECOMMEND")
	private String recommend;

	@Column(name="BSS_CONTRACTID")
	private String bssContractid;
	
	@Column(name="BSS_MOBILEID")
	private String bssMobileid;

	@Column(name="PIC")
	private String pic;
	
	@Transient
	private Map<String,Set<Object>> filterCondition;
	
	@Transient
	private Set<String> bssReangeIds;

	public Product() {
		super();
	}

	public Product(Long rangeId) {
		super();
		this.rangeId = rangeId;
	}

	public Long getRangeId() {
		return rangeId;
	}

	public void setRangeId(Long rangeId) {
		this.rangeId = rangeId;
	}

	public String getBssRangeId() {
		return bssRangeId;
	}

	public void setBssRangeId(String bssRangeId) {
		this.bssRangeId = bssRangeId;
	}

	public String getSvclevel() {
		return svclevel;
	}

	public void setSvclevel(String svclevel) {
		this.svclevel = svclevel;
	}

	public Long getConsumption() {
		return consumption;
	}

	public void setConsumption(Long consumption) {
		this.consumption = consumption;
	}

	public Long getPrestore() {
		return prestore;
	}

	public void setPrestore(Long prestore) {
		this.prestore = prestore;
	}

	public Long getPackedPhone() {
		return packedPhone;
	}

	public void setPackedPhone(Long packedPhone) {
		this.packedPhone = packedPhone;
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getServicetype() {
		return servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	public String getPackedName() {
		return packedName;
	}

	public void setPackedName(String packedName) {
		this.packedName = packedName;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	public String getActType() {
		return actType;
	}

	public void setActType(String actType) {
		this.actType = actType;
	}

	public String getActTypeDes() {
		return actTypeDes;
	}

	public void setActTypeDes(String actTypeDes) {
		this.actTypeDes = actTypeDes;
	}

	public Mobile getMobile() {
		return mobile;
	}

	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getMachineTypeId() {
		return machineTypeId;
	}

	public void setMachineTypeId(String machineTypeId) {
		this.machineTypeId = machineTypeId;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getThawMon() {
		return thawMon;
	}

	public void setThawMon(Long thawMon) {
		this.thawMon = thawMon;
	}

	public Long getResourcesFee() {
		return resourcesFee;
	}

	public void setResourcesFee(Long resourcesFee) {
		this.resourcesFee = resourcesFee;
	}

	public Long getPresenetedFee() {
		return presenetedFee;
	}

	public void setPresenetedFee(Long presenetedFee) {
		this.presenetedFee = presenetedFee;
	}

	public Long getAllFee() {
		return allFee;
	}

	public void setAllFee(Long allFee) {
		this.allFee = allFee;
	}

	public Long getStoredFee() {
		return storedFee;
	}

	public void setStoredFee(Long storedFee) {
		this.storedFee = storedFee;
	}

	public String getStoredDesc() {
		return storedDesc;
	}

	public void setStoredDesc(String storedDesc) {
		this.storedDesc = storedDesc;
	}
	
	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getProductSpecList() {
		return productSpecList;
	}

	public void setProductSpecList(String productSpecList) {
		this.productSpecList = productSpecList;
	}

	public String getPlanSpecList() {
		return planSpecList;
	}

	public void setPlanSpecList(String planSpecList) {
		this.planSpecList = planSpecList;
	}


	@Override
	public String toString() {
		return "MappAcceptRange [rangeId=" + rangeId + ", actId=" + actId
				+ ", actType=" + actType + ", actTypeDes=" + actTypeDes
				+ ", mobile=" + mobile + ", period=" + period
				+ ", machineTypeId=" + machineTypeId + ", price=" + price
				+ ", thawMon=" + thawMon + ", resourcesFee=" + resourcesFee
				+ ", presenetedFee=" + presenetedFee + ", allFee=" + allFee
				+ ", storedFee=" + storedFee + ",contractId="+(contract==null?null:contract.getContractId())+"]";
	}
	
	/** 不对应数据库 **/ 
	@Transient
	public static final String SPECIALSEARCH_NOT_HAS_MOBILE = "NOT_HAS_MOBILE";
	
	public static final String SPECIALSEARCH_HAS_MOBILE = "HAS_MOBILE";
	
	@Transient
	public String specialSearch;

	public String getSpecialSearch() {
		return specialSearch;
	}

	public void setSpecialSearch(String specialSearch) {
		this.specialSearch = specialSearch;
	}
	
	@Column(name="VALIDTIME")
	private Date validTime;
	
	@Column(name="INVALIDTIME")
	private Date invalidTime;

	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}
	
	@Transient
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Transient
	private Collection<Long> ids;

	public Collection<Long> getIds() {
		return ids;
	}

	public void setIds(Collection<Long> ids) {
		this.ids = ids;
	}
	
	@Transient
	private String optType;
	
	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public String getBssContractid() {
		return bssContractid;
	}

	public void setBssContractid(String bssContractid) {
		this.bssContractid = bssContractid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBssMobileid() {
		return bssMobileid;
	}

	public void setBssMobileid(String bssMobileid) {
		this.bssMobileid = bssMobileid;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getResourceList() {
		return resourceList;
	}

	public void setResourceList(String resourceList) {
		this.resourceList = resourceList;
	}

	public String getPlancodes() {
		return plancodes;
	}

	public void setPlancodes(String plancodes) {
		this.plancodes = plancodes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIsBundle() {
		return isBundle;
	}

	public void setIsBundle(String isBundle) {
		this.isBundle = isBundle;
	}

	public String getIsDynamicPriceApplicable() {
		return isDynamicPriceApplicable;
	}

	public void setIsDynamicPriceApplicable(String isDynamicPriceApplicable) {
		this.isDynamicPriceApplicable = isDynamicPriceApplicable;
	}

	public String getIsDynamicEligibilityApplicable() {
		return isDynamicEligibilityApplicable;
	}

	public void setIsDynamicEligibilityApplicable(
			String isDynamicEligibilityApplicable) {
		this.isDynamicEligibilityApplicable = isDynamicEligibilityApplicable;
	}

	public String getIsAutoRenewable() {
		return isAutoRenewable;
	}

	public void setIsAutoRenewable(String isAutoRenewable) {
		this.isAutoRenewable = isAutoRenewable;
	}

	public String getGeographicLocationList() {
		return geographicLocationList;
	}

	public void setGeographicLocationList(
			String geographicLocationList) {
		this.geographicLocationList = geographicLocationList;
	}
	
	public Map<String, Set<Object>> getFilterCondition() {
		return filterCondition;
	}

	public void setFilterCondition(Map<String, Set<Object>> filterCondition) {
		this.filterCondition = filterCondition;
	}

	public Set<String> getBssReangeIds() {
		return bssReangeIds;
	}

	public void setBssReangeIds(Set<String> bssReangeIds) {
		this.bssReangeIds = bssReangeIds;
	}
	
}