package com.ailk.yd.mapp.tibco.model.YD0007;

import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author mler
 * @version 创建时间：2014-4-28 下午06:04:49 类说明:
 */

public class YD0007Response extends YDBody {

	private List<Customer> customers;

	private Integer totalRecords;



	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public static class Customer {
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

	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
}
