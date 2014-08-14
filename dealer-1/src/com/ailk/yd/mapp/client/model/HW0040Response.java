package com.ailk.yd.mapp.client.model;

import java.util.List;
import java.util.Map;

import com.ailk.yd.mapp.model.YDBody;
public class HW0040Response extends YDBody {

	private Map<String, Customer> customers;

	public static class Customer extends YDBody {

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

		private List<OrderDetail> orderDetails;
		
		/**
		 * poa,poi 的存放地址
		 */
		private List<Proof> proofs;
		
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

		public List<OrderDetail> getOrderDetails() {
			return orderDetails;
		}

		public void setOrderDetails(List<OrderDetail> orderDetails) {
			this.orderDetails = orderDetails;
		}

		public List<Proof> getProofs() {
			return proofs;
		}

		public void setProofs(List<Proof> proofs) {
			this.proofs = proofs;
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

	public static class FamilyContactDetails extends YDBody {
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

	public static class ContactDetails extends YDBody {
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

	public static class PermanentAddress extends YDBody {
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

	public static class OrderDetail extends YDBody {
		private BusinessInteraction businessInteraction;
		
		private String offerId;
		private String offerName;
		private String orderLineItemStatus;
		
		private PlanOffering planOffering;
		
		private Account account;
		
		private List<Product> products;

		public List<Product> getProducts() {
			return products;
		}

		public void setProducts(List<Product> products) {
			this.products = products;
		}

		public BusinessInteraction getBusinessInteraction() {
			return businessInteraction;
		}

		public void setBusinessInteraction(BusinessInteraction businessInteraction) {
			this.businessInteraction = businessInteraction;
		}

		public String getOfferId() {
			return offerId;
		}

		public void setOfferId(String offerId) {
			this.offerId = offerId;
		}

		public String getOfferName() {
			return offerName;
		}

		public void setOfferName(String offerName) {
			this.offerName = offerName;
		}

		public String getOrderLineItemStatus() {
			return orderLineItemStatus;
		}

		public void setOrderLineItemStatus(String orderLineItemStatus) {
			this.orderLineItemStatus = orderLineItemStatus;
		}

		public PlanOffering getPlanOffering() {
			return planOffering;
		}

		public void setPlanOffering(PlanOffering planOffering) {
			this.planOffering = planOffering;
		}

		public Account getAccount() {
			return account;
		}

		public void setAccount(Account account) {
			this.account = account;
		}
	}
	
	public static class BusinessInteraction extends YDBody{
		private String name;
		
		private String reasonId;
		
		private String reasonDesc;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getReasonId() {
			return reasonId;
		}

		public void setReasonId(String reasonId) {
			this.reasonId = reasonId;
		}

		public String getReasonDesc() {
			return reasonDesc;
		}

		public void setReasonDesc(String reasonDesc) {
			this.reasonDesc = reasonDesc;
		}
	}
	
	public static class PlanOffering extends YDBody {
		private String id;
		private String name;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	public static class Account extends YDBody {
		private String subscriptionType;

		private PermanentAddress billingAddress;
		
		public String getSubscriptionType() {
			return subscriptionType;
		}

		public void setSubscriptionType(String subscriptionType) {
			this.subscriptionType = subscriptionType;
		}

		public PermanentAddress getBillingAddress() {
			return billingAddress;
		}

		public void setBillingAddress(PermanentAddress billingAddress) {
			this.billingAddress = billingAddress;
		}
		
	}
	
	public static class Product extends YDBody {
		private BusinessInteraction businessInteraction; 
		private String productId;
		private String productName;
		private String orderLineId;
		private String starterKitCode;
		private String estimatedCompletionDateTime;
		private String installationDateTime;
		private String isTeleverificationRequired;
		private String callingPartyNumber;
		private String teleVerificationStatus;
		
		
		private Caf cafDetails;
		

		public BusinessInteraction getBusinessInteraction() {
			return businessInteraction;
		}

		public void setBusinessInteraction(BusinessInteraction businessInteraction) {
			this.businessInteraction = businessInteraction;
		}

		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getOrderLineId() {
			return orderLineId;
		}

		public void setOrderLineId(String orderLineId) {
			this.orderLineId = orderLineId;
		}

		public String getStarterKitCode() {
			return starterKitCode;
		}

		public void setStarterKitCode(String starterKitCode) {
			this.starterKitCode = starterKitCode;
		}

		public String getEstimatedCompletionDateTime() {
			return estimatedCompletionDateTime;
		}

		public void setEstimatedCompletionDateTime(String estimatedCompletionDateTime) {
			this.estimatedCompletionDateTime = estimatedCompletionDateTime;
		}

		public String getInstallationDateTime() {
			return installationDateTime;
		}

		public void setInstallationDateTime(String installationDateTime) {
			this.installationDateTime = installationDateTime;
		}

		public String getIsTeleverificationRequired() {
			return isTeleverificationRequired;
		}

		public void setIsTeleverificationRequired(String isTeleverificationRequired) {
			this.isTeleverificationRequired = isTeleverificationRequired;
		}

		public String getCallingPartyNumber() {
			return callingPartyNumber;
		}

		public void setCallingPartyNumber(String callingPartyNumber) {
			this.callingPartyNumber = callingPartyNumber;
		}

		public String getTeleVerificationStatus() {
			return teleVerificationStatus;
		}

		public void setTeleVerificationStatus(String teleVerificationStatus) {
			this.teleVerificationStatus = teleVerificationStatus;
		}

		public Caf getCafDetails() {
			return cafDetails;
		}

		public void setCafDetails(Caf cafDetails) {
			this.cafDetails = cafDetails;
		}

	}
	
	public static class Caf extends YDBody {
		private String log;
		private String merchantCode;
		private String posAgentCode;
		private String posAgentSignatureDate;
		private String customerDeclarationPlace;
		private String customerDeclarationDate;
		private List<Proof> proofs;
		private List<Object> currentMobileConnections;
		private String cafNumber;
		private String cafURI;
		
		public String getLog() {
			return log;
		}
		public void setLog(String log) {
			this.log = log;
		}
		public String getMerchantCode() {
			return merchantCode;
		}
		public void setMerchantCode(String merchantCode) {
			this.merchantCode = merchantCode;
		}
		public String getPosAgentCode() {
			return posAgentCode;
		}
		public void setPosAgentCode(String posAgentCode) {
			this.posAgentCode = posAgentCode;
		}
		public String getPosAgentSignatureDate() {
			return posAgentSignatureDate;
		}
		public void setPosAgentSignatureDate(String posAgentSignatureDate) {
			this.posAgentSignatureDate = posAgentSignatureDate;
		}
		public String getCustomerDeclarationPlace() {
			return customerDeclarationPlace;
		}
		public void setCustomerDeclarationPlace(String customerDeclarationPlace) {
			this.customerDeclarationPlace = customerDeclarationPlace;
		}
		public String getCustomerDeclarationDate() {
			return customerDeclarationDate;
		}
		public void setCustomerDeclarationDate(String customerDeclarationDate) {
			this.customerDeclarationDate = customerDeclarationDate;
		}
		public List<Proof> getProofs() {
			return proofs;
		}
		public void setProofs(List<Proof> proofs) {
			this.proofs = proofs;
		}
		public List<Object> getCurrentMobileConnections() {
			return currentMobileConnections;
		}
		public void setCurrentMobileConnections(List<Object> currentMobileConnections) {
			this.currentMobileConnections = currentMobileConnections;
		}
		public String getCafNumber() {
			return cafNumber;
		}
		public void setCafNumber(String cafNumber) {
			this.cafNumber = cafNumber;
		}
		public String getCafURI() {
			return cafURI;
		}
		public void setCafURI(String cafURI) {
			this.cafURI = cafURI;
		}
	}
	
	/**
	 * "proofIdentifier": "POA",
        "idProofType": "Z00046",
        "documentNumber": "89897978798798798798",
        "dateOfIssue": "2014-07-01",
        "placeOfIssue": "chennai",
        "issuingAuthority": "GOV",
        "identifierURL": "",
        "aadhaarTransactionRefNo": ""
	 */
	public static class Proof extends YDBody {
		private String proofIdentifier;
		private String idProofType;
		private String documentNumber;
		private String dateOfIssue;
		private String placeOfIssue;
		private String issuingAuthority;
		private String identifierURL;
		private String aadhaarTransactionRefNo;
		
		public String getProofIdentifier() {
			return proofIdentifier;
		}
		public void setProofIdentifier(String proofIdentifier) {
			this.proofIdentifier = proofIdentifier;
		}
		public String getIdProofType() {
			return idProofType;
		}
		public void setIdProofType(String idProofType) {
			this.idProofType = idProofType;
		}
		public String getDocumentNumber() {
			return documentNumber;
		}
		public void setDocumentNumber(String documentNumber) {
			this.documentNumber = documentNumber;
		}
		public String getDateOfIssue() {
			return dateOfIssue;
		}
		public void setDateOfIssue(String dateOfIssue) {
			this.dateOfIssue = dateOfIssue;
		}
		public String getPlaceOfIssue() {
			return placeOfIssue;
		}
		public void setPlaceOfIssue(String placeOfIssue) {
			this.placeOfIssue = placeOfIssue;
		}
		public String getIssuingAuthority() {
			return issuingAuthority;
		}
		public void setIssuingAuthority(String issuingAuthority) {
			this.issuingAuthority = issuingAuthority;
		}
		public String getIdentifierURL() {
			return identifierURL;
		}
		public void setIdentifierURL(String identifierURL) {
			this.identifierURL = identifierURL;
		}
		public String getAadhaarTransactionRefNo() {
			return aadhaarTransactionRefNo;
		}
		public void setAadhaarTransactionRefNo(String aadhaarTransactionRefNo) {
			this.aadhaarTransactionRefNo = aadhaarTransactionRefNo;
		}
		
	}
	
}
