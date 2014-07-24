package com.ailk.yd.mapp.client.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ailk.yd.mapp.model.YDBody;

public class HW0010Request extends YDBody {

	private List<CafInfo> cafInfos;

	public List<CafInfo> getCafInfos() {
		return cafInfos;
	}

	public void setCafInfos(List<CafInfo> cafInfos) {
		this.cafInfos = cafInfos;
	}

	public static class CafInfo extends YDBody {
		private String cafNo;
		private String merchantCode;
		private String posAgentCode;
		private String posAgentSignatureDate;
		private String customerDeclarationPlace;
		private String customerDeclarationDate;
		// Connection

		// Map<String,String> operators;
		private String operatorName;
		private String noOfConnections;
		// poi & poa
		private Proof poi;
		private Proof poa;
		// Form61
		private String lastTaxReturnFiled;
		private String reasonForNoPAN;
		private String referringCustomerId;
		private String referenceMobileNumber;
		private Customer customer;
		private LocalRef localReferenceDetails;
		private Form61 form61;
		private PayInfo payInfo;
		private MnpPort mnpPort;
		private Order order;

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}

		public String getCafNo() {
			return cafNo;
		}

		public void setCafNo(String cafNo) {
			this.cafNo = cafNo;
		}

		// public Map<String, String> getOperators() {
		// return operators;
		// }
		// public void setOperators(Map<String, String> operators) {
		// this.operators = operators;
		// }
		public String getOperatorName() {
			return operatorName;
		}

		public void setOperatorName(String operatorName) {
			this.operatorName = operatorName;
		}

		public String getNoOfConnections() {
			return noOfConnections;
		}

		public void setNoOfConnections(String noOfConnections) {
			this.noOfConnections = noOfConnections;
		}

		public Proof getPoi() {
			return poi;
		}

		public void setPoi(Proof poi) {
			this.poi = poi;
		}

		public Proof getPoa() {
			return poa;
		}

		public void setPoa(Proof poa) {
			this.poa = poa;
		}

		public String getLastTaxReturnFiled() {
			return lastTaxReturnFiled;
		}

		public void setLastTaxReturnFiled(String lastTaxReturnFiled) {
			this.lastTaxReturnFiled = lastTaxReturnFiled;
		}

		public String getReasonForNoPAN() {
			return reasonForNoPAN;
		}

		public void setReasonForNoPAN(String reasonForNoPAN) {
			this.reasonForNoPAN = reasonForNoPAN;
		}

		public String getReferringCustomerId() {
			return referringCustomerId;
		}

		public void setReferringCustomerId(String referringCustomerId) {
			this.referringCustomerId = referringCustomerId;
		}

		public String getReferenceMobileNumber() {
			return referenceMobileNumber;
		}

		public void setReferenceMobileNumber(String referenceMobileNumber) {
			this.referenceMobileNumber = referenceMobileNumber;
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

		public LocalRef getLocalReferenceDetails() {
			return localReferenceDetails;
		}

		public void setLocalReferenceDetails(LocalRef localReferenceDetails) {
			this.localReferenceDetails = localReferenceDetails;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public Form61 getForm61() {
			return form61;
		}

		public void setForm61(Form61 form61) {
			this.form61 = form61;
		}

		public PayInfo getPayInfo() {
			return payInfo;
		}

		public void setPayInfo(PayInfo payInfo) {
			this.payInfo = payInfo;
		}

		public MnpPort getMnpPort() {
			return mnpPort;
		}

		public void setMnpPort(MnpPort mnpPort) {
			this.mnpPort = mnpPort;
		}
	}

	public static class Customer extends YDBody {
		private String customerId;
		private String prospectId;
		private String customerCategory;
		private String salutation;
		private String firstName;
		private String middleName;
		private String lastName;
		// FamilyContact
		private String familyFirstName;
		private String familyMiddleName;
		private String familyLastName;
		private String familyRelationship;
		// ContactDetails
		private String mobileNumber;
		private String alternateContactNumberHome;
		private String alternateContactNumberWork;
		private String emailId;

		private String dateOfBirth;
		private String gender;
		private String nationality;

		private Address presentAddress;
		private Address permanentAddress;// addressType=PER_ADD
		private String panNumber;
		private String preferredLanguage;
		private String preferredCommunicationChannel;
		private String aadhaarNumber;
		private String maritalStatus;
		private String anniversaryDate;
		private String occupation;
		private String occupationDescription;
		private String typeOfHouse;
		private String customerPictureURL;
		private ForeignNational foreignNational;

		public String getCustomerId() {
			return customerId;
		}

		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}

		public ForeignNational getForeignNational() {
			return foreignNational;
		}

		public void setForeignNational(ForeignNational foreignNational) {
			this.foreignNational = foreignNational;
		}

		public String getProspectId() {
			return prospectId;
		}

		public void setProspectId(String prospectId) {
			this.prospectId = prospectId;
		}

		public String getCustomerCategory() {
			return customerCategory;
		}

		public void setCustomerCategory(String customerCategory) {
			this.customerCategory = customerCategory;
		}

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

		public String getFamilyFirstName() {
			return familyFirstName;
		}

		public void setFamilyFirstName(String familyFirstName) {
			this.familyFirstName = familyFirstName;
		}

		public String getFamilyMiddleName() {
			return familyMiddleName;
		}

		public void setFamilyMiddleName(String familyMiddleName) {
			this.familyMiddleName = familyMiddleName;
		}

		public String getFamilyLastName() {
			return familyLastName;
		}

		public void setFamilyLastName(String familyLastName) {
			this.familyLastName = familyLastName;
		}

		public String getFamilyRelationship() {
			return familyRelationship;
		}

		public void setFamilyRelationship(String familyRelationship) {
			this.familyRelationship = familyRelationship;
		}

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

		public Address getPresentAddress() {
			return presentAddress;
		}

		public void setPresentAddress(Address presentAddress) {
			this.presentAddress = presentAddress;
		}

		public Address getPermanentAddress() {
			return permanentAddress;
		}

		public void setPermanentAddress(Address permanentAddress) {
			this.permanentAddress = permanentAddress;
		}

		public String getPanNumber() {
			return panNumber;
		}

		public void setPanNumber(String panNumber) {
			this.panNumber = panNumber;
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

		public String getAadhaarNumber() {
			return aadhaarNumber;
		}

		public void setAadhaarNumber(String aadhaarNumber) {
			this.aadhaarNumber = aadhaarNumber;
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

		public String getTypeOfHouse() {
			return typeOfHouse;
		}

		public void setTypeOfHouse(String typeOfHouse) {
			this.typeOfHouse = typeOfHouse;
		}

		public String getCustomerPictureURL() {
			return customerPictureURL;
		}

		public void setCustomerPictureURL(String customerPictureURL) {
			this.customerPictureURL = customerPictureURL;
		}

	}

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

	public static class Address extends YDBody {
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

	// PaymentDetails
	public static class PayInfo extends YDBody {
		private String modeOfPayment;
		private String totalAmount;
		private String paymentInstrumentNumber;
		private String paymentInstrumentDate;
		private String bankName;
		private String branchNameAndAddress;
		private String receiptNumber;
		private String cpTransactionId;

		public String getModeOfPayment() {
			return modeOfPayment;
		}

		public void setModeOfPayment(String modeOfPayment) {
			this.modeOfPayment = modeOfPayment;
		}

		public String getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(String totalAmount) {
			this.totalAmount = totalAmount;
		}

		public String getPaymentInstrumentNumber() {
			return paymentInstrumentNumber;
		}

		public void setPaymentInstrumentNumber(String paymentInstrumentNumber) {
			this.paymentInstrumentNumber = paymentInstrumentNumber;
		}

		public String getPaymentInstrumentDate() {
			return paymentInstrumentDate;
		}

		public void setPaymentInstrumentDate(String paymentInstrumentDate) {
			this.paymentInstrumentDate = paymentInstrumentDate;
		}

		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getBranchNameAndAddress() {
			return branchNameAndAddress;
		}

		public void setBranchNameAndAddress(String branchNameAndAddress) {
			this.branchNameAndAddress = branchNameAndAddress;
		}

		public String getReceiptNumber() {
			return receiptNumber;
		}

		public void setReceiptNumber(String receiptNumber) {
			this.receiptNumber = receiptNumber;
		}

		public String getCpTransactionId() {
			return cpTransactionId;
		}

		public void setCpTransactionId(String cpTransactionId) {
			this.cpTransactionId = cpTransactionId;
		}
	}

	/**
	 * Outstation Customer
	 * 
	 * @author luyang
	 */
	// Outstation Customer
	public static class LocalRef extends YDBody {
		private String firstName;
		private String middleName;
		private String lastName;
		private Address address;
		private String contactNumber;
		private String eMailId;
		private String callingPartyNumber;

		public String getCallingPartyNumber() {
			return callingPartyNumber;
		}

		public void setCallingPartyNumber(String callingPartyNumber) {
			this.callingPartyNumber = callingPartyNumber;
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

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public String getContactNumber() {
			return contactNumber;
		}

		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}

		public String geteMailId() {
			return eMailId;
		}

		public void seteMailId(String eMailId) {
			this.eMailId = eMailId;
		}
	}

	// Foreign National
	public static class ForeignNational extends YDBody {
		private String passportNo;
		private String visaNo;
		private String visaValidityDate;

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
	}

	// MNP Port-in Details
	public static class MnpPort extends YDBody {
		private String uniquePortingCode;
		private String upcGenerationDate;
		private String existingOperatorCode;
		private String existingSubscriberType;
		private String lastPaidBillReceiptURI;

		public String getUniquePortingCode() {
			return uniquePortingCode;
		}

		public void setUniquePortingCode(String uniquePortingCode) {
			this.uniquePortingCode = uniquePortingCode;
		}

		public String getUpcGenerationDate() {
			return upcGenerationDate;
		}

		public void setUpcGenerationDate(String upcGenerationDate) {
			this.upcGenerationDate = upcGenerationDate;
		}

		public String getExistingOperatorCode() {
			return existingOperatorCode;
		}

		public void setExistingOperatorCode(String existingOperatorCode) {
			this.existingOperatorCode = existingOperatorCode;
		}

		public String getExistingSubscriberType() {
			return existingSubscriberType;
		}

		public void setExistingSubscriberType(String existingSubscriberType) {
			this.existingSubscriberType = existingSubscriberType;
		}

		public String getLastPaidBillReceiptURI() {
			return lastPaidBillReceiptURI;
		}

		public void setLastPaidBillReceiptURI(String lastPaidBillReceiptURI) {
			this.lastPaidBillReceiptURI = lastPaidBillReceiptURI;
		}
	}

	// Form61
	public static class Form61 extends YDBody {
		private String name;
		private String address;
		private String amountOfTransacation;
		private String particularsOfTransacation;
		private String lastTaxReturnFiled;
		private String reasonForNoPAN;

		public String getLastTaxReturnFiled() {
			return lastTaxReturnFiled;
		}

		public void setLastTaxReturnFiled(String lastTaxReturnFiled) {
			this.lastTaxReturnFiled = lastTaxReturnFiled;
		}

		public String getReasonForNoPAN() {
			return reasonForNoPAN;
		}

		public void setReasonForNoPAN(String reasonForNoPAN) {
			this.reasonForNoPAN = reasonForNoPAN;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getAmountOfTransacation() {
			return amountOfTransacation;
		}

		public void setAmountOfTransacation(String amountOfTransacation) {
			this.amountOfTransacation = amountOfTransacation;
		}

		public String getParticularsOfTransacation() {
			return particularsOfTransacation;
		}

		public void setParticularsOfTransacation(
				String particularsOfTransacation) {
			this.particularsOfTransacation = particularsOfTransacation;
		}
	}

	// OrderDetials
	public static class Order extends YDBody {
		private String orn;
		private String orderType;
		private String circleId;
		private String appointmentDateTimeFrom;// 格式："2014-04-23T12:10:47"
		private String appointmentDateTimeTo;
		private String channel;
		private String deliveryMode;
		private String businessInteraction;
		private String accountId;
		private Long pid;
		private String offerId;
		private String eWalletReservationReferenceId;
		private String planOffering;
		private Address billingAddress;// addressType = billing
		private String mdn;
		private BigDecimal mdnFee;
		private String sim;
		private Set<String> imei;
		// Map<productId,Map<imei,deviceType>>
		private Map<String, Map<String, String>> devices;
		private Map<String, Map<String, List<Identifier>>> identifiers;// Map<类型，Map<code,identifier集合>>

		public Map<String, Map<String, List<Identifier>>> getIdentifiers() {
			return identifiers;
		}

		public void setIdentifiers(
				Map<String, Map<String, List<Identifier>>> identifiers) {
			this.identifiers = identifiers;
		}

		public Long getPid() {
			return pid;
		}

		public void setPid(Long pid) {
			this.pid = pid;
		}

		public String getOrderType() {
			return orderType;
		}

		public void setOrderType(String orderType) {
			this.orderType = orderType;
		}

		public String getCircleId() {
			return circleId;
		}

		public void setCircleId(String circleId) {
			this.circleId = circleId;
		}

		public String getAppointmentDateTimeFrom() {
			return appointmentDateTimeFrom;
		}

		public void setAppointmentDateTimeFrom(String appointmentDateTimeFrom) {
			this.appointmentDateTimeFrom = appointmentDateTimeFrom;
		}

		public String getAppointmentDateTimeTo() {
			return appointmentDateTimeTo;
		}

		public void setAppointmentDateTimeTo(String appointmentDateTimeTo) {
			this.appointmentDateTimeTo = appointmentDateTimeTo;
		}

		public String getChannel() {
			return channel;
		}

		public void setChannel(String channel) {
			this.channel = channel;
		}

		public String getDeliveryMode() {
			return deliveryMode;
		}

		public void setDeliveryMode(String deliveryMode) {
			this.deliveryMode = deliveryMode;
		}

		public Map<String, Map<String, String>> getDevices() {
			return devices;
		}

		public void setDevices(Map<String, Map<String, String>> devices) {
			this.devices = devices;
		}

		public String getBusinessInteraction() {
			return businessInteraction;
		}

		public void setBusinessInteraction(String businessInteraction) {
			this.businessInteraction = businessInteraction;
		}

		public String getAccountId() {
			return accountId;
		}

		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}

		public String getOfferId() {
			return offerId;
		}

		public void setOfferId(String offerId) {
			this.offerId = offerId;
		}

		public String geteWalletReservationReferenceId() {
			return eWalletReservationReferenceId;
		}

		public void seteWalletReservationReferenceId(
				String eWalletReservationReferenceId) {
			this.eWalletReservationReferenceId = eWalletReservationReferenceId;
		}

		public String getPlanOffering() {
			return planOffering;
		}

		public void setPlanOffering(String planOffering) {
			this.planOffering = planOffering;
		}

		public Address getBillingAddress() {
			return billingAddress;
		}

		public void setBillingAddress(Address billingAddress) {
			this.billingAddress = billingAddress;
		}

		public String getMdn() {
			return mdn;
		}

		public void setMdn(String mdn) {
			this.mdn = mdn;
		}

		public String getSim() {
			return sim;
		}

		public void setSim(String sim) {
			this.sim = sim;
		}

		public Set<String> getImei() {
			return imei;
		}

		public void setImei(Set<String> imei) {
			this.imei = imei;
		}

		public BigDecimal getMdnFee() {
			return mdnFee;
		}

		public void setMdnFee(BigDecimal mdnFee) {
			this.mdnFee = mdnFee;
		}

		public String getOrn() {
			return orn;
		}

		public void setOrn(String orn) {
			this.orn = orn;
		}

	}

	// {
	// "businessInteraction": {
	// "name": "OFFER_CHANGE"
	// },
	// "productId": "DEV100002",
	// "boqType": "ODU",
	// "identifier": [
	// {
	// "name": "",
	// "value": ""
	// }
	// ]
	// }
	public static class Device extends YDBody {
		private String businessInteraction;
		private String resourceSpecId;
		private String boqType;
		private Map<String, String> resources;

		public String getBusinessInteraction() {
			return businessInteraction;
		}

		public void setBusinessInteraction(String businessInteraction) {
			this.businessInteraction = businessInteraction;
		}

		public String getResourceSpecId() {
			return resourceSpecId;
		}

		public void setResourceSpecId(String resourceSpecId) {
			this.resourceSpecId = resourceSpecId;
		}

		public String getBoqType() {
			return boqType;
		}

		public void setBoqType(String boqType) {
			this.boqType = boqType;
		}

		public Map<String, String> getResources() {
			return resources;
		}

		public void setResources(Map<String, String> resources) {
			this.resources = resources;
		}

	}

	public static class Identifier extends YDBody {
		private String code;
		private String name;
		private String value;
		private String componentPriceId;
		private BigDecimal price;

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getComponentPriceId() {
			return componentPriceId;
		}

		public void setComponentPriceId(String componentPriceId) {
			this.componentPriceId = componentPriceId;
		}
	}

}
