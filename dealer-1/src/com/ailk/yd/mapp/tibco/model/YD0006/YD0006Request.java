package com.ailk.yd.mapp.tibco.model.YD0006;

import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-4-28 下午05:57:50
 * 类说明:注册接口
 */

public class YD0006Request extends YDBody{
	/**
	 * 代理商id
	 */
	private Long dealerId;
	/**
	 * 头衔/称谓
	 */
	private String userTitle;
	/**
	 * FirstName
	 */
	private String firstName;
	/**
	 * LastName
	 */
	private String lastName;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 生日 yyyy-MM-dd
	 */
	private String birthDay;
	/**
	 * 邮件
	 */
	private String email;
	/**
	 * 联系电话
	 */
	private String contractPhone;
	/**
	 * 邮编
	 */
	private String postCode;
	/**
	 * 手机号码（短信使用）
	 */
	private String mobileNo;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 州
	 */
	private String state;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 街道
	 */
	private String street;
	/**
	 * 证件类型
	 */
	private String idCardType;
	/**
	 * 证件号
	 */
	private String idCardNo;
	/**
	 * 图片
	 */
	private List<byte[]> image;
	
	public Long getDealerId() {
		return dealerId;
	}
	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
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
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContractPhone() {
		return contractPhone;
	}
	public void setContractPhone(String contractPhone) {
		this.contractPhone = contractPhone;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getIdCardType() {
		return idCardType;
	}
	public void setIdCardType(String idCardType) {
		this.idCardType = idCardType;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public YD0006Request() {
		super();
	}
	public YD0006Request(Long dealerId, String userTitle, String firstName,
			String lastName, String gender, String birthDay, String email,
			String contractPhone, String postCode, String mobileNo,
			String address, String state, String city, String street,
			String idCardType, String idCardNo, List<byte[]> image) {
		super();
		this.dealerId = dealerId;
		this.userTitle = userTitle;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDay = birthDay;
		this.email = email;
		this.contractPhone = contractPhone;
		this.postCode = postCode;
		this.mobileNo = mobileNo;
		this.address = address;
		this.state = state;
		this.city = city;
		this.street = street;
		this.idCardType = idCardType;
		this.idCardNo = idCardNo;
		this.image = image;
	}
	public List<byte[]> getImage() {
		return image;
	}
	public void setImage(List<byte[]> image) {
		this.image = image;
	}
	
	
}
