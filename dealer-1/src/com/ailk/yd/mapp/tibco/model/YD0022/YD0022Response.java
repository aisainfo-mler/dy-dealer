package com.ailk.yd.mapp.tibco.model.YD0022;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ailk.yd.mapp.tibco.TibcoCache;
import com.ailk.yd.mapp.tibco.model.TibcoRequest;
import com.ailk.yd.mapp.tibco.util.TibcoUtil;

public class YD0022Response implements TibcoRequest {

	private String customerId;
	private String customerCategory;
	private String customerStatus;
	private PersonalDetails personalDetails;

	public static class PersonalDetails {
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
		private List<String> roles;
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
		public List<String> getRoles() {
			return roles;
		}
		public void setRoles(List<String> roles) {
			this.roles = roles;
		}
		public FamilyContactDetails getFamilyContactDetails() {
			return familyContactDetails;
		}
		public void setFamilyContactDetails(FamilyContactDetails familyContactDetails) {
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
		private String villageORCityName;
		private String district;
		private String districtName;
		private String state;
		private String stateName;
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

		public String getVillageORCityName() {
			return villageORCityName;
		}

		public void setVillageORCityName(String villageORCityName) {
			this.villageORCityName = villageORCityName;
		}

		public String getDistrictName() {
			return districtName;
		}

		public void setDistrictName(String districtName) {
			this.districtName = districtName;
		}

		public String getStateName() {
			return stateName;
		}

		public void setStateName(String stateName) {
			this.stateName = stateName;
		}
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

	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}
	
	
/*
 * 串的例子：
{
    "customerId": "1100009566", 
    "customerCategory": "0001", 
    "customerStatus": "", 
    "personalDetails": {
        "salutation": "", 
        "firstName": "VLR", 
        "middleName": "", 
        "lastName": "SRI", 
        "dateOfBirth": "1996-06-03", 
        "gender": "2", 
        "nationality": "IN", 
        "passportNo": "", 
        "visaNo": "", 
        "visaValidityDate": "", 
        "maritalStatus": "1", 
        "anniversaryDate": "2014-06-18", 
        "occupation": "", 
        "occupationDescription": "test others", 
        "preferredLanguage": "6E", 
        "preferredCommunicationChannel": "INT", 
        "customerPictureURL": "", 
        "typeOfHouse": "RENTED", 
        "panNumber": "", 
        "ltrDetails": "", 
        "aadhaarNumber": "AADHAAR", 
        "isBlacklisted": "1", 
        "customerSegment": "YN01", 
        "isVip": "false", 
        "blacklistingReason": "", 
        "roles": [
            "CRM000"
        ], 
        "familyContactDetails": {
            "firstName": "GHH", 
            "middleName": "MN", 
            "lastName": "BN", 
            "relationship": "0001"
        }, 
        "contactDetails": {
            "mobileNumber": "9999988888", 
            "alternateContactNumberHome": "100", 
            "alternateContactNumberWork": "11111111111", 
            "emailId": "VLR@ril.com"
        }, 
        "permanentAddress": {
            "addressId": "0000075725", 
            "buildingId": "", 
            "addressType": "PER_ADD", 
            "careOf": "", 
            "houseNameORNumber": "yuu", 
            "buildingNameORNumber": "TC23- C Block", 
            "societyName": "", 
            "streetNameORNumber": "yui", 
            "landmark": "", 
            "subLocality": "", 
            "areaORTehsil": "Ghansoli", 
            "pincode": "400096", 
            "villageORCity": "Mumbai", 
            "district": "Mumbai-City", 
            "state": "MH", 
            "country": "IN", 
            "totalFloors": "", 
            "jioCentreId": "JC23"
        }
    }
}
*/
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, IllegalAccessException, InstantiationException {
		String test = "{ \"customerId\": \"1100009566\", \"customerCategory\": \"0001\", \"customerStatus\":\"\", \"personalDetails\": { \"salutation\": \"\", \"firstName\": \"VLR\", \"middleName\": \"\", \"lastName\": \"SRI\", \"dateOfBirth\": \"1996-06-03\", \"gender\": \"2\", \"nationality\": \"IN\", \"passportNo\": \"\", \"visaNo\": \"\", \"visaValidityDate\": \"\", \"maritalStatus\": \"1\", \"anniversaryDate\": \"2014-06-18\", \"occupation\": \"\", \"occupationDescription\": \"test others\", \"preferredLanguage\": \"6E\", \"preferredCommunicationChannel\": \"INT\", \"customerPictureURL\": \"\", \"typeOfHouse\": \"RENTED\", \"panNumber\": \"\", \"ltrDetails\":\"\", \"aadhaarNumber\": \"AADHAAR\", \"isBlacklisted\": \"1\", \"customerSegment\": \"YN01\", \"isVip\":\"false\", \"blacklistingReason\": \"\", \"roles\":[ \"CRM000\" ], \"familyContactDetails\": { \"firstName\": \"GHH\", \"middleName\": \"MN\", \"lastName\": \"BN\", \"relationship\": \"0001\" }, \"contactDetails\": { \"mobileNumber\":\"9999988888\", \"alternateContactNumberHome\":\"100\", \"alternateContactNumberWork\":\"11111111111\", \"emailId\":\"VLR@ril.com\" }, \"permanentAddress\": { \"addressId\": \"0000075725\", \"buildingId\": \"\", \"addressType\": \"PER_ADD\", \"careOf\": \"\", \"houseNameORNumber\": \"yuu\", \"buildingNameORNumber\": \"TC23- C Block\", \"societyName\": \"\", \"streetNameORNumber\": \"yui\", \"landmark\": \"\", \"subLocality\": \"\", \"areaORTehsil\": \"Ghansoli\", \"pincode\": \"400096\", \"villageORCity\": \"Mumbai\", \"district\": \"Mumbai-City\", \"state\": \"MH\", \"country\": \"IN\", \"totalFloors\": \"\", \"jioCentreId\": \"JC23\" } } }";
		System.err.println(test.replaceAll(" ", ""));
		YD0022Response rm = fillVal(test);
		System.err.println(new ObjectMapper().writeValueAsString(rm));
		
	}

	/**
	 * 讲SECO(Tibco)返回的串解析成对象，并且设置到对象中
	 * @param test
	 * @return
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static YD0022Response fillVal(String test) throws IOException,
			JsonParseException, JsonMappingException, IllegalAccessException,
			InstantiationException {
		//讲json字符串转成对象
		Map rv = new ObjectMapper().readValue(test, Map.class);
		YD0022Response rm = new YD0022Response();
		//解析第一层对象的值
		TibcoUtil.extractStrValObj(rv, rm);
		Map personalDetailsMap = new HashMap();
		Object obj = rv.get("personalDetails");
		if(obj!=null){
			personalDetailsMap = (Map) obj;
		}
		if(personalDetailsMap.isEmpty()){
			return rm;
		}
		//解析personalDetails节点
		PersonalDetails pd = (PersonalDetails) TibcoUtil.extractStrValClass(personalDetailsMap, PersonalDetails.class);
		rm.setPersonalDetails(pd);
		
		List roles = (List) personalDetailsMap.get("roles");
		pd.setRoles(roles);
		Map familyContactDetailsMap = (Map) personalDetailsMap.get("familyContactDetails");
		Map contactDetailsMap = (Map) personalDetailsMap.get("contactDetails");
		Map permanentAddressMap = (Map) personalDetailsMap.get("permanentAddress");
		FamilyContactDetails familyContactDetails = (FamilyContactDetails) TibcoUtil.extractStrValClass(familyContactDetailsMap, FamilyContactDetails.class);
		ContactDetails contactDetails = (ContactDetails) TibcoUtil.extractStrValClass(contactDetailsMap, ContactDetails.class);
		PermanentAddress permanentAddress = (PermanentAddress) TibcoUtil.extractStrValClass(permanentAddressMap, PermanentAddress.class);
		
		pd.setFamilyContactDetails(familyContactDetails);
		pd.setPermanentAddress(permanentAddress);
		pd.setContactDetails(contactDetails);
		return rm;
	}
}
