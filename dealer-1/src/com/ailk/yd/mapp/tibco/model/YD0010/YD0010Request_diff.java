package com.ailk.yd.mapp.tibco.model.YD0010;

import java.util.List;

import com.ailk.yd.mapp.tibco.model.TibcoRequest;

/**
 * @author mler  
 * @version 创建时间：2014-4-28 下午06:27:23
 * 类说明:
 */

public class YD0010Request_diff implements TibcoRequest  {
	
	private String referenceNumber;
	private String customerId;
	private String orderType;
	private String circleId;
	private String appointmentDateTimeFrom;//格式："2014-04-23T12:10:47"
	private String appointmentDateTimeTo;
	private String channel;
	private String deliveryMode;
	private Customer customerDetails;
	private PayInfo paymentDetails;
	private List<Address> installationAddress;
	private CafInfo cafDetails;
	private List<Order> orderDetails;
	
	public static class Order implements TibcoRequest
	{
		private NameObject businessInteraction;
		private String accountId;
		private String offerId;
		private String eWalletReservationReferenceId;
		private IdObject planOffering;
		private Address billingAddress;
		private List<Product> products;
		public NameObject getBusinessInteraction() {
			return businessInteraction;
		}
		public void setBusinessInteraction(NameObject businessInteraction) {
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
		public IdObject getPlanOffering() {
			return planOffering;
		}
		public void setPlanOffering(IdObject planOffering) {
			this.planOffering = planOffering;
		}
		public Address getBillingAddress() {
			return billingAddress;
		}
		public void setBillingAddress(Address billingAddress) {
			this.billingAddress = billingAddress;
		}
		public List<Product> getProducts() {
			return products;
		}
		public void setProducts(List<Product> products) {
			this.products = products;
		}
		
		
	}
	
	public static class Product implements TibcoRequest
	{
		private NameObject businessInteraction;
		private String productId;
		private String starterKitCode;
		private List<NameAndValueObject> characteristics;
		private String dependancyInfo;
		private CafInfo cafDetails;
		private List<Proof> proofs;
		private MnpPort mnpPortDetails;
		private List<Identifier> identifier;
		private List<Device> devices;
		private List<FacingService> customerFacingServices;
		public NameObject getBusinessInteraction() {
			return businessInteraction;
		}
		public void setBusinessInteraction(NameObject businessInteraction) {
			this.businessInteraction = businessInteraction;
		}
		public String getProductId() {
			return productId;
		}
		public void setProductId(String productId) {
			this.productId = productId;
		}
		public String getStarterKitCode() {
			return starterKitCode;
		}
		public void setStarterKitCode(String starterKitCode) {
			this.starterKitCode = starterKitCode;
		}
		public List<NameAndValueObject> getCharacteristics() {
			return characteristics;
		}
		public void setCharacteristics(List<NameAndValueObject> characteristics) {
			this.characteristics = characteristics;
		}
		public String getDependancyInfo() {
			return dependancyInfo;
		}
		public void setDependancyInfo(String dependancyInfo) {
			this.dependancyInfo = dependancyInfo;
		}
		public CafInfo getCafDetails() {
			return cafDetails;
		}
		public void setCafDetails(CafInfo cafDetails) {
			this.cafDetails = cafDetails;
		}
		public List<Proof> getProofs() {
			return proofs;
		}
		public void setProofs(List<Proof> proofs) {
			this.proofs = proofs;
		}
		public MnpPort getMnpPortDetails() {
			return mnpPortDetails;
		}
		public void setMnpPortDetails(MnpPort mnpPortDetails) {
			this.mnpPortDetails = mnpPortDetails;
		}
		public List<Identifier> getIdentifier() {
			return identifier;
		}
		public void setIdentifier(List<Identifier> identifier) {
			this.identifier = identifier;
		}
		public List<Device> getDevices() {
			return devices;
		}
		public void setDevices(List<Device> devices) {
			this.devices = devices;
		}
		public List<FacingService> getCustomerFacingServices() {
			return customerFacingServices;
		}
		public void setCustomerFacingServices(List<FacingService> customerFacingServices) {
			this.customerFacingServices = customerFacingServices;
		}
		
		
	}
	
	public static class FacingService implements TibcoRequest
	{
		private NameObject businessInteraction;
		private String serviceId;
		private String featureId;
		private String productId;
		private List<FacingService> features;
		public NameObject getBusinessInteraction() {
			return businessInteraction;
		}
		public void setBusinessInteraction(NameObject businessInteraction) {
			this.businessInteraction = businessInteraction;
		}
		public String getServiceId() {
			return serviceId;
		}
		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
		}
		public String getFeatureId() {
			return featureId;
		}
		public void setFeatureId(String featureId) {
			this.featureId = featureId;
		}
		public String getProductId() {
			return productId;
		}
		public void setProductId(String productId) {
			this.productId = productId;
		}
		public List<FacingService> getFeatures() {
			return features;
		}
		public void setFeatures(List<FacingService> features) {
			this.features = features;
		}
		
		
	}
	
	public static class Device implements TibcoRequest
	{
		private NameObject businessInteraction;
		private String productId;
		private String boqType;
		private List<Identifier> identifier;
		public NameObject getBusinessInteraction() {
			return businessInteraction;
		}
		public void setBusinessInteraction(NameObject businessInteraction) {
			this.businessInteraction = businessInteraction;
		}
		public String getProductId() {
			return productId;
		}
		public void setProductId(String productId) {
			this.productId = productId;
		}
		public String getBoqType() {
			return boqType;
		}
		public void setBoqType(String boqType) {
			this.boqType = boqType;
		}
		public List<Identifier> getIdentifier() {
			return identifier;
		}
		public void setIdentifier(List<Identifier> identifier) {
			this.identifier = identifier;
		}
		
		
	}
	
	public static class MnpPort implements TibcoRequest
	{
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
	
	public static class Dependancy implements TibcoRequest
	{
		private List<Identifier> identifier;

		public List<Identifier> getIdentifier() {
			return identifier;
		}

		public void setIdentifier(List<Identifier> identifier) {
			this.identifier = identifier;
		}
		
	}
	
	public static class Identifier implements TibcoRequest
	{
		private String name;
		private String type;
		private String value;
		private IdObject componentPrice;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public IdObject getComponentPrice() {
			return componentPrice;
		}
		public void setComponentPrice(IdObject componentPrice) {
			this.componentPrice = componentPrice;
		}
		
		
	}
	
	public static class NameAndValueObject implements TibcoRequest
	{
		private String name;
		private String value;
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
		
	}
	
	public static class IdObject implements TibcoRequest
	{
		private String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
		
	}
	
	public static class NameObject implements TibcoRequest
	{
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	public static class Customer implements TibcoRequest
	{
		private String prospectId;
		private String customerCategory;
		private String salutation;
		private String firstName;
		private String middleName;
		private String lastName;
		private FamilyContact familyContactDetails;
		private String dateOfBirth;
		private String gender;
		private String nationality;
		private String passportNo;
		private String visaNo;
		private String visaValidityDate;
		private Contact contactDetails;
		private Address permanentAddress;
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
		public FamilyContact getFamilyContactDetails() {
			return familyContactDetails;
		}
		public void setFamilyContactDetails(FamilyContact familyContactDetails) {
			this.familyContactDetails = familyContactDetails;
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
		public Contact getContactDetails() {
			return contactDetails;
		}
		public void setContactDetails(Contact contactDetails) {
			this.contactDetails = contactDetails;
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
	
	public static class CafInfo implements TibcoRequest
	{
		private String cafNumber;
		private String merchantCode;
		private String posAgentCode;
		private String posAgentSignatureDate;
		private String customerDeclarationPlace;
		private String customerDeclarationDate;
		private List<Proof> proofs;
		private List<Connection> currentMobileConnections;
		private Form61 form61Details;
		private LocalRef localReferenceDetails;
		private ReferringCustomer referringCustomerDetails;
		private LocalRefVerify localReferenceVerification;
		private String cafURI;
		public String getCafNumber() {
			return cafNumber;
		}
		public void setCafNumber(String cafNumber) {
			this.cafNumber = cafNumber;
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
		public List<Connection> getCurrentMobileConnections() {
			return currentMobileConnections;
		}
		public void setCurrentMobileConnections(
				List<Connection> currentMobileConnections) {
			this.currentMobileConnections = currentMobileConnections;
		}
		public Form61 getForm61Details() {
			return form61Details;
		}
		public void setForm61Details(Form61 form61Details) {
			this.form61Details = form61Details;
		}
		public LocalRef getLocalReferenceDetails() {
			return localReferenceDetails;
		}
		public void setLocalReferenceDetails(LocalRef localReferenceDetails) {
			this.localReferenceDetails = localReferenceDetails;
		}
		public ReferringCustomer getReferringCustomerDetails() {
			return referringCustomerDetails;
		}
		public void setReferringCustomerDetails(
				ReferringCustomer referringCustomerDetails) {
			this.referringCustomerDetails = referringCustomerDetails;
		}
		public LocalRefVerify getLocalReferenceVerification() {
			return localReferenceVerification;
		}
		public void setLocalReferenceVerification(
				LocalRefVerify localReferenceVerification) {
			this.localReferenceVerification = localReferenceVerification;
		}
		public String getCafURI() {
			return cafURI;
		}
		public void setCafURI(String cafURI) {
			this.cafURI = cafURI;
		}
		
		
		
	}
	
	public static class LocalRefVerify implements TibcoRequest
	{
		private String callingPartyNumber;

		public String getCallingPartyNumber() {
			return callingPartyNumber;
		}

		public void setCallingPartyNumber(String callingPartyNumber) {
			this.callingPartyNumber = callingPartyNumber;
		}
		
		
	}
	
	public static class ReferringCustomer implements TibcoRequest
	{
		private String id;
		private String referenceMobileNumber;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getReferenceMobileNumber() {
			return referenceMobileNumber;
		}
		public void setReferenceMobileNumber(String referenceMobileNumber) {
			this.referenceMobileNumber = referenceMobileNumber;
		}
		
		
	}
	
	public static class LocalRef implements TibcoRequest
	{
		private String firstName;
		private String middleName;
		private String lastName;
		private Address address;
		private String contactNumber;
		private String eMailId;
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
	
	public static class Form61 implements TibcoRequest
	{
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
		
		
	}
	
	public static class Connection implements TibcoRequest
	{
		private String operatorName;
		private String noOfConnections;
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
		
	}
	
	public static class Proof implements TibcoRequest
	{
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
	
	public static class PayInfo implements TibcoRequest
	{
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
	
	public static class Address implements TibcoRequest
	{
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
	
	public static class Contact implements TibcoRequest
	{
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
		public void setAlternateContactNumberHome(String alternateContactNumberHome) {
			this.alternateContactNumberHome = alternateContactNumberHome;
		}
		public String getAlternateContactNumberWork() {
			return alternateContactNumberWork;
		}
		public void setAlternateContactNumberWork(String alternateContactNumberWork) {
			this.alternateContactNumberWork = alternateContactNumberWork;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
	
	}
	
	public static class FamilyContact implements TibcoRequest
	{
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

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public Customer getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(Customer customerDetails) {
		this.customerDetails = customerDetails;
	}

	public PayInfo getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PayInfo paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public List<Address> getInstallationAddress() {
		return installationAddress;
	}

	public void setInstallationAddress(List<Address> installationAddress) {
		this.installationAddress = installationAddress;
	}

	public CafInfo getCafDetails() {
		return cafDetails;
	}

	public void setCafDetails(CafInfo cafDetails) {
		this.cafDetails = cafDetails;
	}

	public List<Order> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<Order> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
	
}
