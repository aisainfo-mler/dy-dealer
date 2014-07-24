package com.ailk.yd.mapp.tibco.model.YD0010;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ai.mapp.sys.service.DealerDataService;
import com.ailk.yd.mapp.model.YDBody;
import com.ailk.yd.mapp.tibco.model.TibcoRequest;

/**
 * @author mler
 * @version 创建时间：2014-4-28 下午06:27:23 类说明:
 */

public class YD0010Request implements TibcoRequest {

	// "cafDetails": {},
	// "orderDetails": [],
	// "customerId": "",
	// "installationAddress": [],
	// "orderType": "CREATE",
	// "referenceNumber": "OT000000LTK9",
	// "customerDetails": {},
	// "deliveryMode": "",
	// "appointmentDateTimeTo": "",
	// "appointmentDateTimeFrom": "",
	// "paymentDetails": {},
	// "channel": "22",
	// "circleId": "TC"

	private CafInfo cafDetails;
	private List<Order> orderDetails;
	private String customerId;
	private List<Address> installationAddress;// SHIP_TO
	private String orderType;// *CREATE,MODIFY,DRAFT*,INTERIM*/
	private String referenceNumber;
	private Customer customerDetails;
	private String deliveryMode;
	private String appointmentDateTimeTo;
	private String appointmentDateTimeFrom;// 格式："2014-04-23T12:10:47"
	private PayInfo paymentDetails;
	private String channel;
	private String circleId;

	public static class Order implements TibcoRequest {

		// "planOffering": {},
		// "billingAddress": {},
		// "accountId": "",
		// "businessInteraction": {},
		// "eWalletReservationReferenceId": "1014689",
		// "products": [],
		// "offerId": "OC402"

		private IdObject planOffering;// Startup Plan Offering Prepaid -
										// Optional PostPaid - Mandatory **/
		private Address billingAddress;// BILL_TO
		private String accountId;
		private NameObject businessInteraction;
		private String eWalletReservationReferenceId;
		private List<Product> products;
		private String offerId;

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

	public static class Product implements TibcoRequest {

		// "dependancyInfo": {},
		// "proofs": [],
		// "cafDetails": {},
		// "starterKitCode": "N",
		// "characteristics": [],
		// "componentPrice": {},
		// "mnpPortDetails": {},
		// "businessInteraction": {},
		// "customerFacingServices": [],
		// "devices": [ ],
		// "productId": "P10009"

		private Dependancy dependancyInfo;
		private List<ProductProof> proofs;
		private ProductCafInfo cafDetails;
		private String starterKitCode;
		/**
		 * name = DND Preference Applicable only for Voice Services Service"
		 * value =
		 * "Specify the value 0 - For fully blocked category 1#2#3#4#5#6#7 (Separated by a separator #)"
		 */
		private List<NameAndValueObject> characteristics;
		private IdObject componentPrice;
		private MnpPort mnpPortDetails;
		private NameObject businessInteraction;
		private List<FacingService> customerFacingServices;
		private List<Device> devices;
		private List<ProductIdentifier> identifier;
		private String productId;

		public List<ProductIdentifier> getIdentifier() {
			return identifier;
		}

		public void setIdentifier(List<ProductIdentifier> identifier) {
			this.identifier = identifier;
		}

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

		public Dependancy getDependancyInfo() {
			return dependancyInfo;
		}

		public void setDependancyInfo(Dependancy dependancyInfo) {
			this.dependancyInfo = dependancyInfo;
		}

		public ProductCafInfo getCafDetails() {
			return cafDetails;
		}

		public void setCafDetails(ProductCafInfo cafDetails) {
			this.cafDetails = cafDetails;
		}

		public List<ProductProof> getProofs() {
			return proofs;
		}

		public void setProofs(List<ProductProof> proofs) {
			this.proofs = proofs;
		}

		public MnpPort getMnpPortDetails() {
			return mnpPortDetails;
		}

		public void setMnpPortDetails(MnpPort mnpPortDetails) {
			this.mnpPortDetails = mnpPortDetails;
		}

		public IdObject getComponentPrice() {
			return componentPrice;
		}

		public void setComponentPrice(IdObject componentPrice) {
			this.componentPrice = componentPrice;
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

		public void setCustomerFacingServices(
				List<FacingService> customerFacingServices) {
			this.customerFacingServices = customerFacingServices;
		}

	}

	public static class FacingService implements TibcoRequest {
		// "features": [ ],
		// "serviceId": "S10009",
		// "businessInteraction": {
		// "name": "ADD"
		// }
		private List<FacingService> features;
		private String serviceId;
		private NameObject businessInteraction;
		private String featureId;
		private String productId;

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

	public static class Device implements TibcoRequest {
		private NameObject businessInteraction;
		private String productId;
		private String boqType;
		private List<NameAndValueObject> identifier;

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

		public List<NameAndValueObject> getIdentifier() {
			return identifier;
		}

		public void setIdentifier(List<NameAndValueObject> identifier) {
			this.identifier = identifier;
		}

	}

	public static class ProductIdentifier implements TibcoRequest {
		// "value": "4456667778",
		// "type": "",
		// "componentPrice": {
		// "id": ""
		// },
		// "name": "MSISDN"

		private String value;
		private String type;
		private IdObject componentPrice;
		private String name;

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

	public static class MnpPort implements TibcoRequest {
		// "uniquePortingCode": "",
		// "upcGenerationDate": "",
		// "existingSubscriberType": "",
		// "lastPaidBillReceiptURI": "",
		// "existingOperatorCode": ""

		private String uniquePortingCode;
		private String upcGenerationDate;
		private String existingSubscriberType;
		private String lastPaidBillReceiptURI;
		private String existingOperatorCode;

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

	public static class ProductProof implements TibcoRequest {
		private String identifierURL;
		private String proofIdentifier;

		public ProductProof() {
			super();
		}

		public ProductProof(String proofIdentifier, String identifierURL) {
			super();
			this.proofIdentifier = proofIdentifier;
			this.identifierURL = identifierURL;
		}

		public String getProofIdentifier() {
			return proofIdentifier;
		}

		public void setProofIdentifier(String proofIdentifier) {
			this.proofIdentifier = proofIdentifier;
		}

		public String getIdentifierURL() {
			return identifierURL;
		}

		public void setIdentifierURL(String identifierURL) {
			this.identifierURL = identifierURL;
		}

	}

	public static class ProductCafInfo implements TibcoRequest {
		private String cafNumber;
		private String cafURI;

		public ProductCafInfo() {
			super();
		}

		public ProductCafInfo(String cafNumber, String cafURI) {
			super();
			this.cafNumber = cafNumber;
			this.cafURI = cafURI;
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

	public static class Dependancy implements TibcoRequest {
		private List<ValueObject> identifier;

		public List<ValueObject> getIdentifier() {
			return identifier;
		}

		public void setIdentifier(List<ValueObject> identifier) {
			this.identifier = identifier;
		}

	}

	public static class NameAndValueObject implements TibcoRequest {
		private String name;
		private String value;

		public NameAndValueObject() {
			super();
		}

		public NameAndValueObject(String name, String value) {
			super();
			this.name = name;
			this.value = value;
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

	}

	public static class IdObject implements TibcoRequest {
		public IdObject() {
			super();
		}

		public IdObject(String id) {
			super();
			this.id = id;
		}

		private String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

	}

	public static class NameObject implements TibcoRequest {

		public NameObject() {
			super();
		}

		public NameObject(String name) {
			super();
			this.name = name;
		}

		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public static class ValueObject implements TibcoRequest {
		private String value;

		public ValueObject() {
			super();
		}

		public ValueObject(String value) {
			super();
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

	public static class Customer implements TibcoRequest {

		// "dateOfBirth": "1996-06-25",
		// "middleName": "",
		// "prospectId": "",
		// "lastName": "GYY",
		// "occupation": "0005",
		// "passportNo": "",
		// "anniversaryDate": "2014-06-26",
		// "customerCategory": "0001",
		// "maritalStatus": "",
		// "panNumber": "AUIPR0027Q",
		// "visaNo": "",
		// "familyContactDetails": {},
		// "preferredLanguage": "EN",
		// "occupationDescription": "",
		// "customerPictureURL":
		// "http://sidcdevotas.in.ril.com:8080/archive?get&pVersion=0045&contRep=CAF&docId=167140_20140626_185115&ixUser=SIDCDEVOTAS&ixAppl=libdsh",
		// "preferredCommunicationChannel": "INT",
		// "nationality": "IN",
		// "aadhaarNumber": "",
		// "gender": "2",
		// "contactDetails": {},
		// "typeOfHouse": "",
		// "firstName": "HH",
		// "salutation": "0002",
		// "permanentAddress": {},
		// "visaValidityDate": "1996-06-25T00:00:00"

		private String dateOfBirth;
		private String middleName;
		private String prospectId;
		private String lastName;
		private String occupation;
		private String passportNo;
		private String anniversaryDate;
		private String customerCategory;
		private String maritalStatus;
		private String panNumber;
		private String visaNo;
		private FamilyContact familyContactDetails;
		private String preferredLanguage;
		private String occupationDescription;
		private String customerPictureURL;
		private String preferredCommunicationChannel;
		private String nationality;
		private String aadhaarNumber;
		private String gender;
		private Contact contactDetails;
		private String typeOfHouse;
		private String firstName;
		private String salutation;
		private Address permanentAddress;// PER_ADD
		private String visaValidityDate;

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

	public static class CafInfo implements TibcoRequest {

		// "posAgentCode": "POSAgent01",
		// "proofs": [],
		// "form61Details": {},
		// "customerDeclarationDate": "",
		// "currentMobileConnections": [],
		// "posAgentSignatureDate": "",
		// "localReferenceVerification": {},
		// "referringCustomerDetails": {},
		// "customerDeclarationPlace": "",
		// "merchantCode": "POSAgent01",
		// "localReferenceDetails": {}

		private String posAgentCode;
		private List<Proof> proofs;
		private Form61 form61Details;
		private String customerDeclarationDate;
		private List<Connection> currentMobileConnections;
		private String posAgentSignatureDate;
		private LocalRefVerify localReferenceVerification;
		private ReferringCustomer referringCustomerDetails;
		private String customerDeclarationPlace;
		private String merchantCode;
		private LocalRef localReferenceDetails;

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

	}

	public static class LocalRefVerify implements TibcoRequest {
		private String callingPartyNumber;

		public String getCallingPartyNumber() {
			return callingPartyNumber;
		}

		public void setCallingPartyNumber(String callingPartyNumber) {
			this.callingPartyNumber = callingPartyNumber;
		}

	}

	public static class ReferringCustomer implements TibcoRequest {
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

	public static class LocalRef implements TibcoRequest {

		// "middleName": "",
		// "lastName": "",
		// "eMailId": "",
		// "contactNumber": "",
		// "firstName": "",
		// "address": {}
		private String middleName;
		private String lastName;
		private String eMailId;
		private String contactNumber;
		private String firstName;
		private Address address;// PRE_ADD

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

	public static class Form61 implements TibcoRequest {
		private String reasonForNoPAN;
		private String lastTaxReturnFiled;

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

	public static class Connection implements TibcoRequest {
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

	public static class Proof implements TibcoRequest {

		// "proofIdentifier": "POA",
		// "aadhaarTransactionRefNo": "",
		// "idProofType": "Z00005",
		// "placeOfIssue": "jju",
		// "identifierURL":
		// "http://sidcdevotas.in.ril.com:8080/archive?get&pVersion=0045&contRep=CAF&docId=167133_20140626_185110&ixUser=SIDCDEVOTAS&ixAppl=libdsh",
		// "documentNumber": "hh",
		// "issuingAuthority": "hj",
		// "dateOfIssue": "2014-06-25"

		private String proofIdentifier;
		private String aadhaarTransactionRefNo;
		private String idProofType;
		private String placeOfIssue;
		private String identifierURL;
		private String documentNumber;
		private String issuingAuthority;
		private String dateOfIssue;

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

	public static class PayInfo implements TibcoRequest {

		// "cpTransactionId": "",
		// "branchNameAndAddress": "",
		// "modeOfPayment": "01",
		// "totalAmount": "5000",
		// "bankName": "",
		// "paymentInstrumentDate": "",
		// "receiptNumber": "",
		// "paymentInstrumentNumber": ""

		private String cpTransactionId;
		private String branchNameAndAddress;
		/***
		 * paymode :Cash,Cheque,Demand Draft,Credit Card,Debit
		 * Card,Netbanking,POD,RPayCard"
		 ***/
		private String modeOfPayment;
		private String totalAmount;
		private String bankName;
		private String paymentInstrumentDate;
		private String receiptNumber;
		private String paymentInstrumentNumber;

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

	public static class Address implements TibcoRequest {
		// "villageORCity": "Mumbai",
		// "careOf": "",
		// "jioCentreId": "JC23",
		// "state": "MH",
		// "houseNameORNumber": "ghh",
		// "buildingNameORNumber": "TC23- C Block",
		// "addressType": "SHIP_TO",
		// "addressId": "",
		// "country": "IN",
		// "pincode": "666888",
		// "areaORTehsil": "ghh",
		// "landmark": "",
		// "totalFloors": "",
		// "societyName": "",
		// "streetNameORNumber": "hjj",
		// "district": "mum",
		// "subLocality": "",
		// "buildingId": ""

		private String villageORCity;
		private String careOf;
		private String jioCentreId;
		private String state;
		private String houseNameORNumber;
		private String buildingNameORNumber;
		private String addressType;// - 类型：Installation- Billing- Shipping
		private String addressId;
		private String country;
		private String pincode;
		private String areaORTehsil;
		private String landmark;
		private String totalFloors;
		private String societyName;
		private String streetNameORNumber;
		private String district;
		private String subLocality;
		private String buildingId;

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

	public static class Contact implements TibcoRequest {
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

	public static class FamilyContact implements TibcoRequest {
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

	public static class Identifier extends YDBody {
		private String code;
		private String name;
		private String value;
		private IdObject componentPriceId;

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

		public IdObject getComponentPriceId() {
			return componentPriceId;
		}

		public void setComponentPriceId(IdObject componentPriceId) {
			this.componentPriceId = componentPriceId;
		}
	}

	public static final void main(String[] args) throws Exception {
		YD0010Request req = new YD0010Request();

		req.setReferenceNumber("OT00000000HL");
		req.setCustomerId("1100008129");
		req.setOrderType("MODIFY");
		req.setCircleId("TC");
		req.setAppointmentDateTimeFrom("2014-04-23T12:10:47");
		req.setAppointmentDateTimeTo("2014-04-23T12:13:47");
		req.setChannel("01");
		req.setDeliveryMode("");

		/******** customerDetail 设置 ***********/
		YD0010Request.Customer customer = new Customer();
		req.setCustomerDetails(customer);
		customer.setProspectId("");
		customer.setCustomerCategory("0001");
		customer.setSalutation("0001");
		customer.setFirstName("SBL155TC02");
		customer.setMiddleName("QE");
		customer.setLastName("TEST");

		FamilyContact fc = new FamilyContact();
		customer.setFamilyContactDetails(fc);
		fc.setFirstName("");
		fc.setMiddleName("");
		fc.setLastName("");
		fc.setRelationship("");

		customer.setDateOfBirth("1986-01-10");
		customer.setGender("1");
		customer.setNationality("IN");
		customer.setPassportNo("");
		customer.setVisaNo("");
		customer.setVisaValidityDate("");

		Contact contact = new Contact();
		customer.setContactDetails(contact);
		contact.setMobileNumber("1100008129");
		contact.setAlternateContactNumberHome("8939353200");
		contact.setAlternateContactNumberWork("8939353200");
		contact.setEmailId("SBL155TC02@RIL.COM");

		Address pa = new Address();
		customer.setPermanentAddress(pa);
		pa.setAddressId("");
		pa.setBuildingId("NVMBBD0063702");
		pa.setAddressType("PER_ADD");
		pa.setCareOf("");
		pa.setHouseNameORNumber("110");
		pa.setBuildingNameORNumber("TC23- C Block");
		pa.setSocietyName("");
		pa.setStreetNameORNumber("yui");
		pa.setLandmark("");
		pa.setSubLocality("");
		pa.setAreaORTehsil("Ghansoli");
		pa.setPincode("400096");
		pa.setVillageORCity("Mumbai");
		pa.setDistrict("Mumbai-City");
		pa.setState("MH");
		pa.setCountry("IN");
		pa.setTotalFloors("");
		pa.setJioCentreId("");

		customer.setPanNumber("PB908189009");
		customer.setPreferredLanguage("EN");
		customer.setPreferredCommunicationChannel("");
		customer.setAadhaarNumber("");
		customer.setMaritalStatus("0");
		customer.setAnniversaryDate("");
		customer.setOccupation("0001");
		customer.setOccupationDescription("I AM A STUDENT");
		customer.setTypeOfHouse("");
		customer.setCustomerPictureURL("");

		/******************* PaymentDetails ****************************/

		PayInfo payInfo = new PayInfo();
		req.setPaymentDetails(payInfo);
		payInfo.setModeOfPayment("01");
		payInfo.setPaymentInstrumentNumber("1000");
		payInfo.setPaymentInstrumentDate("");
		payInfo.setBankName("");
		payInfo.setBranchNameAndAddress("");
		payInfo.setReceiptNumber("");
		payInfo.setCpTransactionId("");

		/******************** InstallationAddress ************************/
		List<Address> install_addess_list = new ArrayList<YD0010Request.Address>(
				0);
		req.setInstallationAddress(install_addess_list);
		Address in_a = new Address();
		install_addess_list.add(in_a);
		in_a.setAddressId("");
		in_a.setBuildingId("NVMBBD0063702");
		in_a.setAddressType("SHIP_TO");
		in_a.setCareOf("");
		in_a.setHouseNameORNumber("110");
		in_a.setBuildingNameORNumber("TC23- C Block");
		in_a.setSocietyName("");
		in_a.setStreetNameORNumber("yui");
		in_a.setLandmark("");
		in_a.setSubLocality("");
		in_a.setAreaORTehsil("Ghansoli");
		in_a.setPincode("400096");
		in_a.setVillageORCity("Mumbai");
		in_a.setDistrict("Mumbai-City");
		in_a.setState("MH");
		in_a.setCountry("IN");
		in_a.setTotalFloors("");
		in_a.setJioCentreId("");

		/******************** cafDetails ************************/
		CafInfo caf = new CafInfo();
		req.setCafDetails(caf);
		caf.setMerchantCode("");
		caf.setPosAgentCode("");
		caf.setPosAgentSignatureDate("");
		caf.setCustomerDeclarationPlace("");
		caf.setCustomerDeclarationDate("");

		List<Proof> proofs = new ArrayList<YD0010Request.Proof>(0);
		caf.setProofs(proofs);
		Proof p1 = new Proof();
		Proof p2 = new Proof();
		proofs.add(p1);
		proofs.add(p2);

		p1.setProofIdentifier("POA");
		p1.setIdProofType("Z00005");
		p1.setDocumentNumber("343456567876");
		p1.setDateOfIssue("2011-02-06");
		p1.setPlaceOfIssue("mumbai");
		p1.setIssuingAuthority("gov");
		p1.setIdentifierURL("http://sidcdevotas.in.ril.com:8080/archive?get&pVersion=0045&contRep=CAF&docId=OT00000001UW_118864_1_181824&ixUser=SIDCDEVOTAS&ixAppl=libdsh");
		p1.setAadhaarTransactionRefNo("");

		p1.setProofIdentifier("POI");
		p1.setIdProofType("Z00005");
		p1.setDocumentNumber("343456567876");
		p1.setDateOfIssue("2011-02-06");
		p1.setPlaceOfIssue("mumbai");
		p1.setIssuingAuthority("gov");
		p1.setIdentifierURL("http://sidcdevotas.in.ril.com:8080/archive?get&pVersion=0045&contRep=CAF&docId=OT00000001UW_118864_1_181824&ixUser=SIDCDEVOTAS&ixAppl=libdsh");
		p1.setAadhaarTransactionRefNo("");

		List<Connection> conns = new ArrayList<YD0010Request.Connection>(0);
		caf.setCurrentMobileConnections(conns);
		Connection conn = new Connection();
		conns.add(conn);
		conn.setOperatorName("");
		conn.setNoOfConnections("");

		Form61 f61 = new Form61();
		caf.setForm61Details(f61);
		f61.setLastTaxReturnFiled("");
		f61.setReasonForNoPAN("");

		LocalRef lr = new LocalRef();
		caf.setLocalReferenceDetails(lr);
		lr.setFirstName("");
		lr.setMiddleName("");
		lr.setLastName("");

		Address lr_a = new Address();
		lr.setAddress(lr_a);
		lr_a.setAddressId("");
		lr_a.setBuildingId("NVMBBD0063702");
		lr_a.setAddressType("REF_ADD");
		lr_a.setCareOf("");
		lr_a.setHouseNameORNumber("110");
		lr_a.setBuildingNameORNumber("TC23- C Block");
		lr_a.setSocietyName("");
		lr_a.setStreetNameORNumber("yui");
		lr_a.setLandmark("");
		lr_a.setSubLocality("");
		lr_a.setAreaORTehsil("Ghansoli");
		lr_a.setPincode("400096");
		lr_a.setVillageORCity("Mumbai");
		lr_a.setDistrict("Mumbai-City");
		lr_a.setState("MH");
		lr_a.setCountry("IN");
		lr_a.setTotalFloors("");
		lr_a.setJioCentreId("");

		lr.setContactNumber("");
		lr.seteMailId("");

		ReferringCustomer cr = new ReferringCustomer();
		caf.setReferringCustomerDetails(cr);
		cr.setId("");
		cr.setReferenceMobileNumber("");

		LocalRefVerify lrv = new LocalRefVerify();
		caf.setLocalReferenceVerification(lrv);
		lrv.setCallingPartyNumber("");

		/************************* orderDetails *******************************/
		req.setOrderDetails(new ArrayList<YD0010Request.Order>(0));
		Order order = new Order();
		req.getOrderDetails().add(order);
		order.setBusinessInteraction(new NameObject("OFFER_CHANGE"));
		order.setAccountId("");
		order.setOfferId("OC300036");
		order.seteWalletReservationReferenceId("");
		order.setPlanOffering(new IdObject(""));

		Address o_ba = new Address();
		order.setBillingAddress(o_ba);
		o_ba.setAddressId("");
		o_ba.setBuildingId("NVMBBD0063702");
		o_ba.setAddressType("BILL_TO");
		o_ba.setCareOf("");
		o_ba.setHouseNameORNumber("110");
		o_ba.setBuildingNameORNumber("TC23- C Block");
		o_ba.setSocietyName("");
		o_ba.setStreetNameORNumber("yui");
		o_ba.setLandmark("");
		o_ba.setSubLocality("");
		o_ba.setAreaORTehsil("Ghansoli");
		o_ba.setPincode("400096");
		o_ba.setVillageORCity("Mumbai");
		o_ba.setDistrict("Mumbai-City");
		o_ba.setState("MH");
		o_ba.setCountry("IN");
		o_ba.setTotalFloors("");
		o_ba.setJioCentreId("");

		order.setProducts(new ArrayList<YD0010Request.Product>(0));
		Product order_p1 = new Product();
		order.getProducts().add(order_p1);
		order_p1.setBusinessInteraction(new NameObject("OFFER_CHANGE"));
		order_p1.setProductId("P30001");
		order_p1.setStarterKitCode("");

		List<NameAndValueObject> chs = new ArrayList<YD0010Request.NameAndValueObject>(
				0);
		order_p1.setCharacteristics(chs);
		NameAndValueObject nv1 = new NameAndValueObject("DND_PREFERENCE", "");
		chs.add(nv1);

		Dependancy dependancy = new Dependancy();
		order_p1.setDependancyInfo(dependancy);
		List<ValueObject> dependancy_identifier = new ArrayList<YD0010Request.ValueObject>(
				0);
		dependancy.setIdentifier(dependancy_identifier);
		ValueObject di1 = new ValueObject("DND_PREFERENCE");
		dependancy_identifier.add(di1);

		order_p1.setCafDetails(new ProductCafInfo("", ""));

		List<ProductProof> p_proofs = new ArrayList<YD0010Request.ProductProof>(
				0);
		order_p1.setProofs(p_proofs);
		ProductProof p_p1 = new ProductProof(
				"POA",
				"http://sidcdevotas.in.ril.com:8080/archive?get&pVersion=0045&contRep=CAF&docId=OT00000001UW_118864_1_181824&ixUser=SIDCDEVOTAS&ixAppl=libdsh");
		p_proofs.add(p_p1);
		ProductProof p_p2 = new ProductProof(
				"POI",
				"http://sidcdevotas.in.ril.com:8080/archive?get&pVersion=0045&contRep=CAF&docId=OT00000001UW_118864_1_181824&ixUser=SIDCDEVOTAS&ixAppl=libdsh");
		p_proofs.add(p_p2);

		MnpPort mp = new MnpPort();
		order_p1.setMnpPortDetails(mp);
		mp.setUniquePortingCode("");
		mp.setUpcGenerationDate("");
		mp.setExistingOperatorCode("");
		mp.setExistingSubscriberType("");
		mp.setLastPaidBillReceiptURI("");

		List<ProductIdentifier> pi_list = new ArrayList<YD0010Request.ProductIdentifier>(
				0);
		// order_p1.setIdentifier(pi_list);
		ProductIdentifier pi = new ProductIdentifier();
		pi_list.add(pi);
		pi.setName("");
		pi.setType("");
		pi.setValue("");
		pi.setComponentPrice(new IdObject(""));

		List<Device> devices = new ArrayList<YD0010Request.Device>(0);
		order_p1.setDevices(devices);

		Device d1 = new Device();
		devices.add(d1);
		d1.setBusinessInteraction(new NameObject("OFFER_CHANGE"));
		d1.setProductId("DEV100002");
		d1.setBoqType("ODU");
		List<NameAndValueObject> id_list1 = new ArrayList<YD0010Request.NameAndValueObject>(
				0);
		d1.setIdentifier(id_list1);
		id_list1.add(new NameAndValueObject("", ""));

		Device d2 = new Device();
		devices.add(d2);
		d2.setBusinessInteraction(new NameObject("OFFER_CHANGE"));
		d2.setProductId("DEV100002");
		d2.setBoqType("ODU");
		List<NameAndValueObject> id_list2 = new ArrayList<YD0010Request.NameAndValueObject>(
				0);
		d2.setIdentifier(id_list2);
		id_list2.add(new NameAndValueObject("", ""));

		List<FacingService> customerFacingServices = new ArrayList<YD0010Request.FacingService>(
				0);
		order_p1.setCustomerFacingServices(customerFacingServices);
		// / 1 freature
		FacingService fs1 = new FacingService();
		fs1.setBusinessInteraction(new NameObject("OFFER_CHANGE"));
		fs1.setServiceId("S30001");
		fs1.setFeatures(new ArrayList<YD0010Request.FacingService>(0));

		FacingService ft1_1 = new FacingService();
		ft1_1.setBusinessInteraction(new NameObject("ADD"));
		ft1_1.setFeatureId("F30001");
		ft1_1.setFeatures(new ArrayList<YD0010Request.FacingService>(0));
		fs1.getFeatures().add(ft1_1);

		FacingService ft1_1_1 = new FacingService();
		ft1_1_1.setBusinessInteraction(new NameObject("ADD"));
		ft1_1.setFeatureId("");
		ft1_1.getFeatures().add(ft1_1_1);

		// / 2 freature
		FacingService fs2 = new FacingService();
		fs2.setBusinessInteraction(new NameObject("OFFER_CHANGE"));
		fs2.setServiceId("S30002");
		fs2.setFeatures(new ArrayList<YD0010Request.FacingService>(0));

		FacingService ft2_1 = new FacingService();
		ft2_1.setBusinessInteraction(new NameObject("ADD"));
		ft2_1.setFeatureId("F30001");
		ft2_1.setFeatures(new ArrayList<YD0010Request.FacingService>(0));
		fs2.getFeatures().add(ft2_1);

		FacingService ft2_1_1 = new FacingService();
		ft2_1_1.setBusinessInteraction(new NameObject("ADD"));
		ft2_1_1.setProductId("");
		ft2_1.getFeatures().add(ft2_1_1);

		// / 3 freature
		FacingService fs3 = new FacingService();
		fs3.setBusinessInteraction(new NameObject("OFFER_CHANGE"));
		fs3.setServiceId("S30003");
		fs3.setFeatures(new ArrayList<YD0010Request.FacingService>(0));

		FacingService ft3_1 = new FacingService();
		ft3_1.setBusinessInteraction(new NameObject("ADD"));
		ft3_1.setFeatureId("F30001");
		ft3_1.setFeatures(new ArrayList<YD0010Request.FacingService>(0));
		fs3.getFeatures().add(ft3_1);

		FacingService ft3_1_1 = new FacingService();
		ft3_1.setBusinessInteraction(new NameObject("ADD"));
		ft3_1.setFeatureId("");
		ft3_1.getFeatures().add(ft3_1_1);

		System.out.println(DealerDataService.mapper.writeValueAsString(req));

	}

}
