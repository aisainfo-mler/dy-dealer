package com.ailk.yd.mapp.tibco.model.YD0007;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ailk.yd.mapp.tibco.model.TibcoRequest;

/**
 * @author mler
 * @version 创建时间：2014-4-28 下午06:04:49 类说明:
 */

public class YD0007Response implements TibcoRequest {

	private String totalRecords;

	private List<Customer> customers;

	public static class Customer {
		private String customerId;
		private String customerStatus;
		private PersonalDetails personalDetails;
		private ContactDetails contactDetails;
		private String customerPictureURL;
		private String isBlacklisted;
		private List<String> roles;
		private String userId;
		private String displayName;

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

		public PersonalDetails getPersonalDetails() {
			return personalDetails;
		}

		public void setPersonalDetails(PersonalDetails personalDetails) {
			this.personalDetails = personalDetails;
		}

		public ContactDetails getContactDetails() {
			return contactDetails;
		}

		public void setContactDetails(ContactDetails contactDetails) {
			this.contactDetails = contactDetails;
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

		public List<String> getRoles() {
			return roles;
		}

		public void setRoles(List<String> roles) {
			this.roles = roles;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

	}

	public static class ContactDetails {
		private String mobileNumber;
		private String emailId;

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

	}

	public static class PersonalDetails {
		private String salutation;
		private String firstName;
		private String middleName;
		private String lastName;

		public String getSalutation() {
			return salutation;
		}

		public void setSalutation(String salutation) {
			this.salutation = salutation;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getMiddleName() {
			return middleName;
		}

		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

	}

	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException, IllegalArgumentException,
			IllegalAccessException, InstantiationException {
		String in = "{ \"totalRecords\": \"24\", \"customers\": [ { \"customerId\": \"1100009626\", \"customerStatus\": \"E0011\", \"personalDetails\": { \"salutation\": \"\", \"firstName\": \"Jayshil\", \"middleName\": \"\", \"lastName\": \"Dave\" }, \"contactDetails\": { \"mobileNumber\": \"9819253878\", \"emailId\": \"jayshildave@gmail.com\" }, \"customerPictureURL\": \"\", \"isBlacklisted\": \"1\", \"roles\":[], \"userId\":\"\", \"displayName\":\"\" }, { \"customerId\": \"1100009627\", \"customerStatus\": \"E0009\", \"personalDetails\": { \"salutation\": \"\", \"firstName\": \"Jayshil\", \"middleName\": \"\", \"lastName\": \"Dave\" }, \"contactDetails\": { \"mobileNumber\": \"9819253878\", \"emailId\": \"jayshildave@gmail.com\" }, \"customerPictureURL\": \"\", \"isBlacklisted\": \"1\", \"roles\":[ \"BUP002\" ], \"userId\":\"\", \"displayName\":\"\" }, { \"customerId\": \"1100009629\", \"customerStatus\": \"E0011\", \"personalDetails\": { \"salutation\": \"\", \"firstName\": \"Jayshil\", \"middleName\": \"\", \"lastName\": \"Dave\" }, \"contactDetails\": { \"mobileNumber\": \"9819253878\", \"emailId\": \"jayshildave@gmail.com\" }, \"customerPictureURL\": \"\", \"isBlacklisted\": \"1\", \"roles\":[ \"BUP002\" ], \"userId\":\"\", \"displayName\":\"\" }, { \"customerId\": \"1100009630\", \"customerStatus\": \"E0011\", \"personalDetails\": { \"salutation\": \"\", \"firstName\": \"Jayshil\", \"middleName\": \"\", \"lastName\": \"Dave\" }, \"contactDetails\": { \"mobileNumber\": \"9819253878\", \"emailId\": \"jayshildave@gmail.com\" }, \"customerPictureURL\": \"\", \"isBlacklisted\": \"1\", \"roles\":[ \"BUP002\" ], \"userId\":\"\", \"displayName\":\"\" } ] }";
		System.err.println(in.replaceAll(" ", ""));
		YD0007Response rm = fillVal(in);

		System.err.println(new ObjectMapper().writeValueAsString(rm));
	}

	public static YD0007Response fillVal(String in) throws IOException,
			JsonParseException, JsonMappingException, IllegalAccessException {
		Map m = new ObjectMapper().readValue(in, Map.class);
		YD0007Response rm = new YD0007Response();
		extractStrVal(m, rm);
		Object cl = m.get("customers");
		if (cl != null) {
			List customerList = (List) cl;
			List responseCustomerList = new ArrayList();
			rm.setCustomers(responseCustomerList);
			for (Iterator it = customerList.iterator(); it.hasNext();) {
				Map oneCustomerMap = (Map) it.next();
				Customer cu = new Customer();
				extractStrVal(oneCustomerMap, cu);
				PersonalDetails pd = new PersonalDetails();
				cu.setPersonalDetails(pd);
				extractStrVal(oneCustomerMap.get("personalDetails"), pd);

				ContactDetails cd = new ContactDetails();
				cu.setContactDetails(cd);
				extractStrVal(oneCustomerMap.get("contactDetails"), cd);

				Object obj = oneCustomerMap.get("roles");
				if (obj != null) {
					cu.setRoles((List<String>) obj);
				}
				responseCustomerList.add(cu);
			}
		}
		return rm;
	}

	private static void extractStrVal(Object mapObj, Object rm)
			throws IllegalAccessException {
		if (!(mapObj instanceof Map))
			return;
		Map m = (Map) mapObj;
		Field[] declaredFields = rm.getClass().getDeclaredFields();
		for (int i = 0; i < declaredFields.length; i++) {
			Field f = declaredFields[i];
			String fieldname = f.getName();
			Object fieldVal = m.get(fieldname);
			if (f.getType().equals(String.class) && fieldVal != null) {
				f.setAccessible(true);
				f.set(rm, fieldVal.toString());
			}
		}
	}

	private static void extractNonStrVal(Map m, Object rm)
			throws IllegalAccessException, InstantiationException {
		Field[] declaredFields = rm.getClass().getDeclaredFields();
		for (int i = 0; i < declaredFields.length; i++) {
			Field f = declaredFields[i];
			String fieldname = f.getName();
			Object fieldVal = m.get(fieldname);
			if (m.get(fieldname) != null
					&& m.get(fieldname).getClass().equals(LinkedHashMap.class)) {
				Object obj = f.getType().newInstance();
				f.setAccessible(true);
				f.set(rm, obj);
				extractStrVal(m, obj);
			}
		}
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
}
