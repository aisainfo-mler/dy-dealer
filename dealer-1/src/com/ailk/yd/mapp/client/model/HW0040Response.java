package com.ailk.yd.mapp.client.model;

import java.util.Map;

import com.ailk.yd.mapp.model.YDBody;

public class HW0040Response extends YDBody {

	private Map<String, Customer> customers;

	public static class Customer {

		private String customerId;
		private String customerCategory;
		private String customerStatus;

		private String salutation;
		private String firstName;
		private String middleName;
		private String lastName;
		private String dateOfBirth;
		private String gender;
		private String nationality;
		private String passportNo;
		private String visaNo;
		private String visaValidityDate;
		private String maritalStatus;
		private String anniversaryDate;
		private String occupation;
		private String occupationDescription;
		private String preferredLanguage;
		private String preferredCommunicationChannel;
		private String customerPictureURL;
		private String typeOfHouse;
		private String panNumber;
		private String ltrDetails;
		private String aadhaarNumber;
		private String isBlacklisted;
		private String customerSegment;
		private String isVip;
		private String blacklistingReason;
		// private List<String> roles;
		private FamilyContactDetails familyContactDetails;
		private ContactDetails contactDetails;
		private PermanentAddress permanentAddress;

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

		public String getDateOfBirth() {
			return dateOfBirth;
		}

		public void setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getNationality() {
			return nationality;
		}

		public void setNationality(String nationality) {
			this.nationality = nationality;
		}

		public String getPassportNo() {
			return passportNo;
		}

		public void setPassportNo(String passportNo) {
			this.passportNo = passportNo;
		}

		public String getVisaNo() {
			return visaNo;
		}

		public void setVisaNo(String visaNo) {
			this.visaNo = visaNo;
		}

		public String getVisaValidityDate() {
			return visaValidityDate;
		}

		public void setVisaValidityDate(String visaValidityDate) {
			this.visaValidityDate = visaValidityDate;
		}

		public String getMaritalStatus() {
			return maritalStatus;
		}

		public void setMaritalStatus(String maritalStatus) {
			this.maritalStatus = maritalStatus;
		}

		public String getAnniversaryDate() {
			return anniversaryDate;
		}

		public void setAnniversaryDate(String anniversaryDate) {
			this.anniversaryDate = anniversaryDate;
		}

		public String getOccupation() {
			return occupation;
		}

		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}

		public String getOccupationDescription() {
			return occupationDescription;
		}

		public void setOccupationDescription(String occupationDescription) {
			this.occupationDescription = occupationDescription;
		}

		public String getPreferredLanguage() {
			return preferredLanguage;
		}

		public void setPreferredLanguage(String preferredLanguage) {
			this.preferredLanguage = preferredLanguage;
		}

		public String getPreferredCommunicationChannel() {
			return preferredCommunicationChannel;
		}

		public void setPreferredCommunicationChannel(
				String preferredCommunicationChannel) {
			this.preferredCommunicationChannel = preferredCommunicationChannel;
		}

		public String getCustomerPictureURL() {
			return customerPictureURL;
		}

		public void setCustomerPictureURL(String customerPictureURL) {
			this.customerPictureURL = customerPictureURL;
		}

		public String getTypeOfHouse() {
			return typeOfHouse;
		}

		public void setTypeOfHouse(String typeOfHouse) {
			this.typeOfHouse = typeOfHouse;
		}

		public String getPanNumber() {
			return panNumber;
		}

		public void setPanNumber(String panNumber) {
			this.panNumber = panNumber;
		}

		public String getLtrDetails() {
			return ltrDetails;
		}

		public void setLtrDetails(String ltrDetails) {
			this.ltrDetails = ltrDetails;
		}

		public String getAadhaarNumber() {
			return aadhaarNumber;
		}

		public void setAadhaarNumber(String aadhaarNumber) {
			this.aadhaarNumber = aadhaarNumber;
		}

		public String getIsBlacklisted() {
			return isBlacklisted;
		}

		public void setIsBlacklisted(String isBlacklisted) {
			this.isBlacklisted = isBlacklisted;
		}

		public String getCustomerSegment() {
			return customerSegment;
		}

		public void setCustomerSegment(String customerSegment) {
			this.customerSegment = customerSegment;
		}

		public String getIsVip() {
			return isVip;
		}

		public void setIsVip(String isVip) {
			this.isVip = isVip;
		}

		public String getBlacklistingReason() {
			return blacklistingReason;
		}

		public void setBlacklistingReason(String blacklistingReason) {
			this.blacklistingReason = blacklistingReason;
		}

		public FamilyContactDetails getFamilyContactDetails() {
			return familyContactDetails;
		}

		public void setFamilyContactDetails(
				FamilyContactDetails familyContactDetails) {
			this.familyContactDetails = familyContactDetails;
		}

		public ContactDetails getContactDetails() {
			return contactDetails;
		}

		public void setContactDetails(ContactDetails contactDetails) {
			this.contactDetails = contactDetails;
		}

		public PermanentAddress getPermanentAddress() {
			return permanentAddress;
		}

		public void setPermanentAddress(PermanentAddress permanentAddress) {
			this.permanentAddress = permanentAddress;
		}

		public String getCustomerId() {
			return customerId;
		}

		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}

		public String getCustomerCategory() {
			return customerCategory;
		}

		public void setCustomerCategory(String customerCategory) {
			this.customerCategory = customerCategory;
		}

		public String getCustomerStatus() {
			return customerStatus;
		}

		public void setCustomerStatus(String customerStatus) {
			this.customerStatus = customerStatus;
		}
	}

	public static class FamilyContactDetails {
		private String firstName;
		private String middleName;
		private String lastName;
		private String relationship;

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

		public String getRelationship() {
			return relationship;
		}

		public void setRelationship(String relationship) {
			this.relationship = relationship;
		}
	}

	public static class ContactDetails {
		private String mobileNumber;
		private String alternateContactNumberHome;
		private String alternateContactNumberWork;
		private String emailId;

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public String getAlternateContactNumberHome() {
			return alternateContactNumberHome;
		}

		public void setAlternateContactNumberHome(
				String alternateContactNumberHome) {
			this.alternateContactNumberHome = alternateContactNumberHome;
		}

		public String getAlternateContactNumberWork() {
			return alternateContactNumberWork;
		}

		public void setAlternateContactNumberWork(
				String alternateContactNumberWork) {
			this.alternateContactNumberWork = alternateContactNumberWork;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
	}

	public static class PermanentAddress {
		private String addressId;
		private String buildingId;
		private String addressType;
		private String careOf;
		private String houseNameORNumber;
		private String buildingNameORNumber;
		private String societyName;
		private String streetNameORNumber;
		private String landmark;
		private String subLocality;
		private String areaORTehsil;
		private String pincode;
		private String villageORCity;
		private String district;
		private String state;
		private String country;
		private String totalFloors;
		private String jioCentreId;

		public String getAddressId() {
			return addressId;
		}

		public void setAddressId(String addressId) {
			this.addressId = addressId;
		}

		public String getBuildingId() {
			return buildingId;
		}

		public void setBuildingId(String buildingId) {
			this.buildingId = buildingId;
		}

		public String getAddressType() {
			return addressType;
		}

		public void setAddressType(String addressType) {
			this.addressType = addressType;
		}

		public String getCareOf() {
			return careOf;
		}

		public void setCareOf(String careOf) {
			this.careOf = careOf;
		}

		public String getHouseNameORNumber() {
			return houseNameORNumber;
		}

		public void setHouseNameORNumber(String houseNameORNumber) {
			this.houseNameORNumber = houseNameORNumber;
		}

		public String getBuildingNameORNumber() {
			return buildingNameORNumber;
		}

		public void setBuildingNameORNumber(String buildingNameORNumber) {
			this.buildingNameORNumber = buildingNameORNumber;
		}

		public String getSocietyName() {
			return societyName;
		}

		public void setSocietyName(String societyName) {
			this.societyName = societyName;
		}

		public String getStreetNameORNumber() {
			return streetNameORNumber;
		}

		public void setStreetNameORNumber(String streetNameORNumber) {
			this.streetNameORNumber = streetNameORNumber;
		}

		public String getLandmark() {
			return landmark;
		}

		public void setLandmark(String landmark) {
			this.landmark = landmark;
		}

		public String getSubLocality() {
			return subLocality;
		}

		public void setSubLocality(String subLocality) {
			this.subLocality = subLocality;
		}

		public String getAreaORTehsil() {
			return areaORTehsil;
		}

		public void setAreaORTehsil(String areaORTehsil) {
			this.areaORTehsil = areaORTehsil;
		}

		public String getPincode() {
			return pincode;
		}

		public void setPincode(String pincode) {
			this.pincode = pincode;
		}

		public String getVillageORCity() {
			return villageORCity;
		}

		public void setVillageORCity(String villageORCity) {
			this.villageORCity = villageORCity;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getTotalFloors() {
			return totalFloors;
		}

		public void setTotalFloors(String totalFloors) {
			this.totalFloors = totalFloors;
		}

		public String getJioCentreId() {
			return jioCentreId;
		}

		public void setJioCentreId(String jioCentreId) {
			this.jioCentreId = jioCentreId;
		}
	}

	public Map<String, Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Map<String, Customer> customers) {
		this.customers = customers;
	}

}
