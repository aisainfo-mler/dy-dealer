package com.ailk.yd.mapp.client.model;

import java.util.List;

public class TibcoCustomer {
	private String customerId;
	private String customerStatus;
	/**
	 * salutation,firstName,middleName,lastName
	 */
	private String personalDetails;
	private String customerPictureURL;
	private String isBlacklisted;
	private String displayName;
	private String mobileNumber;
	private String emailId;
	
	
	private List<TibcoAccount> accounts;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(String personalDetails) {
		this.personalDetails = personalDetails;
	}

	public String getCustomerPictureURL() {
		return customerPictureURL;
	}

	public void setCustomerPictureURL(String customerPictureURL) {
		this.customerPictureURL = customerPictureURL;
	}

	public String getIsBlacklisted() {
		return isBlacklisted;
	}

	public void setIsBlacklisted(String isBlacklisted) {
		this.isBlacklisted = isBlacklisted;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public List<TibcoAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<TibcoAccount> accounts) {
		this.accounts = accounts;
	}
}
