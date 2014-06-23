package com.ailk.yd.mapp.client.model;


import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

public class HW0006Request extends YDBody {

	/**
	 * 用户名
	 */
	private java.lang.String userCode;

	/**
	 * 密码
	 */
	private java.lang.String pwd;

	/**
	 * 头衔
	 */
	private java.lang.String userTitle;

	private java.lang.String firstName;

	private java.lang.String lastName;

	/**
	 * 性别
	 */
	private java.lang.String gender;

	/**
	 * 生日 yyyy-MM-dd
	 */
	private java.lang.String birthDay;

	private java.lang.String email;

	/**
	 * 联系电话
	 */
	private java.lang.String contractPhone;

	/**
	 * 邮编
	 */
	private java.lang.String postCode;

	/**
	 * 手机号码
	 */
	private java.lang.String mobileNo;

	/**
	 * 地址
	 */
	private java.lang.String address;

	/**
	 * 洲
	 */
	private java.lang.String state;

	/**
	 * 城市
	 */
	private java.lang.String city;

	/**
	 * 街道
	 */
	private java.lang.String street;

	/**
	 * 证件类型
	 */
	private java.lang.String idCardType;

	/**
	 * 证件号
	 */
	private java.lang.String idCardNo;
	
	/**
	 * 支付密码
	 */
	private String payPwd;
	
	private Long dealerId;


	/**
	 * 图片
	 */
	private List<byte[]> image;

	public java.lang.String getUserCode() {
		return userCode;
	}

	public void setUserCode(java.lang.String userCode) {
		this.userCode = userCode;
	}

	public java.lang.String getPwd() {
		return pwd;
	}

	public void setPwd(java.lang.String pwd) {
		this.pwd = pwd;
	}

	public java.lang.String getUserTitle() {
		return userTitle;
	}

	public void setUserTitle(java.lang.String userTitle) {
		this.userTitle = userTitle;
	}

	public java.lang.String getFirstName() {
		return firstName;
	}

	public void setFirstName(java.lang.String firstName) {
		this.firstName = firstName;
	}

	public java.lang.String getLastName() {
		return lastName;
	}

	public void setLastName(java.lang.String lastName) {
		this.lastName = lastName;
	}

	public java.lang.String getGender() {
		return gender;
	}

	public void setGender(java.lang.String gender) {
		this.gender = gender;
	}

	public java.lang.String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(java.lang.String birthDay) {
		this.birthDay = birthDay;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getContractPhone() {
		return contractPhone;
	}

	public void setContractPhone(java.lang.String contractPhone) {
		this.contractPhone = contractPhone;
	}

	public java.lang.String getPostCode() {
		return postCode;
	}

	public void setPostCode(java.lang.String postCode) {
		this.postCode = postCode;
	}

	public java.lang.String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(java.lang.String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public java.lang.String getAddress() {
		return address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getState() {
		return state;
	}

	public void setState(java.lang.String state) {
		this.state = state;
	}

	public java.lang.String getCity() {
		return city;
	}

	public void setCity(java.lang.String city) {
		this.city = city;
	}

	public java.lang.String getStreet() {
		return street;
	}

	public void setStreet(java.lang.String street) {
		this.street = street;
	}

	public java.lang.String getIdCardType() {
		return idCardType;
	}

	public void setIdCardType(java.lang.String idCardType) {
		this.idCardType = idCardType;
	}

	public java.lang.String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(java.lang.String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public List<byte[]> getImage() {
		return image;
	}

	public void setImage(List<byte[]> image) {
		this.image = image;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}
}
