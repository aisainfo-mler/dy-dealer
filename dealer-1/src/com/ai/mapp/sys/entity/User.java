package com.ai.mapp.sys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name="HW_OPERATOR")
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="hw_operator_gen")
    @SequenceGenerator(name="hw_operator_gen",sequenceName="HW_OPERATOR$SEQ")
	@Column(name="OPERATOR_ID")
	private Long userId;
	
	@Column(name="OPERATOR_USERCODE")
	private String userCode;
	
	@Column(name="OPERATOR_NET_ID")
	private Long netId;
	
	@Column(name="OPERATOR_TITLE")
	private String userTitle;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="OPERATOR_PWD")
	private String password;
	
	@Column(name="DATE_OF_BIRTH")
	private Date birthDay;
	
	@Column(name="CONTACT_NUMBER")
	private String contractPhone;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PHONE_NUMBER")
	private String mobileNo;
	
	@Column(name="HOUSE_NUMBER")
	private String houseNumber;
	
	@Column(name="POST_CODE")
	private String postCode;
	
	@Column(name="HOUSE_BUILDING_NAME")
	private String building;
	
	@Column(name="STREET")
	private String street;

	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=HwCity.class)
	@JoinColumn(name="CITY_TOWN",referencedColumnName="CITY_CODE")
	@NotFound(action=NotFoundAction.IGNORE)
	private HwCity city;
	
	@Column(name="OPERATOR_ID_TYPE")
	private String certificateType;
	
	@Column(name="OPERATOR_ID_NUMBER")
	private String certificateNo;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="PAY_PWD")
	private String payPwd;
	
	@Column(name="SLFID")
	private String slfid;
	/**
	 * {成员1json串}$^-^${成员2json串}
	 */
	@Column(name="MEMBERS")
	private String members;
	
	
//	@ManyToOne(fetch=FetchType.LAZY,targetEntity=SmallLocalFile.class)
//	@JoinColumn(name="slfid",referencedColumnName="slfid")
//	@NotFound(action=NotFoundAction.IGNORE)
//	private SmallLocalFile slf;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=FileRelation.class)
	@JoinColumn(name="SHOW_PIC",referencedColumnName="ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private FileRelation showPic;

	@Column(name="OPERATOR_TYPE")
	private String userType;
	
	@Column(name="OPERATOR_STATUS")
	private String status;

	@OneToMany(mappedBy="operator",fetch=FetchType.LAZY,targetEntity=AccountInfo.class)
    @JoinColumn(name="OPERATOR_ID")
    @NotFound(action=NotFoundAction.IGNORE)
	private List<AccountInfo> accounts;
	
	@Column(name="REASON")
	private String reason;
	
	@Column(name="APPROVE_TIME")
	private Date approveTime;
	
	@Column(name="DENY_TIME")
	private Date denyTime;
	
	/***印度***/
	@Column(name="CIRCLE_ID")
	private String circleId;//circleid
	@Transient
	private String accountId;//sap account
	
	/************************************************************************/
	
	public User() {
		super();
	}

	public User(Long userId) {
		super();
		this.userId = userId;
	}
	
	public User(String userCode) {
		super();
		this.userCode = userCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Long getNetId() {
		return netId;
	}

	public void setNetId(Long netId) {
		this.netId = netId;
	}

	public String getUserTitle() {
		return userTitle;
	}

	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getContractPhone() {
		return contractPhone;
	}

	public void setContractPhone(String contractPhone) {
		this.contractPhone = contractPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public HwCity getCity() {
		return city;
	}

	public void setCity(HwCity city) {
		this.city = city;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public FileRelation getShowPic() {
		return showPic;
	}

	public void setShowPic(FileRelation showPic) {
		this.showPic = showPic;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<AccountInfo> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountInfo> accounts) {
		this.accounts = accounts;
	}
	
	@Transient
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="CREATETIME")
	private Date createTime;
	
	@Column(name="UPDATETIIME")
	private Date updateTime;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="CREATEOR",referencedColumnName="OPERATOR_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private User creator;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="UPDATEER",referencedColumnName="OPERATOR_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private User updater;

//	public String getPic() throws IOException{
//		if(this.slf!=null && slf.getContentBlob()!=null){
//			String path = this.getClass().getResource(".").getPath()+File.pathSeparator+"user_pic";
//			File p = new File(path);
//			if(p.exists()==false){
//				p.createNewFile();
//			}
//			FileOutputStream fos=null;
//			try {
//				File picFile = new File(path+File.pathSeparator+slf.getSlfid());
//				fos = new FileOutputStream(picFile);
//				fos.write(slf.getContentBlob());
//				fos.flush();
////				this.setSlfUrl(path+File.pathSeparator+slf.getSlfid());
//				return path+File.pathSeparator+slf.getSlfid();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}finally{
//				if(fos!=null)
//					fos.close();
//			}
//		}
//		return "";
//	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public Date getDenyTime() {
		return denyTime;
	}

	public void setDenyTime(Date denyTime) {
		this.denyTime = denyTime;
	}

	@Transient
	private String orderBy;//for example:    desc:userId,updateTime;asc:createTime,lastName

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	@Transient
	private List<Long> userIds;

	public List<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
	@Transient
	private Date startTime;
	
	@Transient
	private Date endTime;

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

	@Transient
	private List<String> stateCodes;

	public List<String> getStateCodes() {
		return stateCodes;
	}

	public void setStateCodes(List<String> stateCodes) {
		this.stateCodes = stateCodes;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	public String getSlfid() {
		return slfid;
	}

	public void setSlfid(String slfid) {
		this.slfid = slfid;
	}

	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}
	

//	public SmallLocalFile getSlf() {
//		return slf;
//	}
//
//	public void setSlf(SmallLocalFile slf) {
//		this.slf = slf;
//	}

	public String getCircleId() {
		return circleId;
	}

	public void setCircleId(String circleId) {
		this.circleId = circleId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	
	
}

