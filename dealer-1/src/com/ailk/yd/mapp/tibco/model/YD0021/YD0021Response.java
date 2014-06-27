package com.ailk.yd.mapp.tibco.model.YD0021;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ailk.yd.mapp.model.YDBody;
import com.ailk.yd.mapp.tibco.model.TibcoRequest;

public class YD0021Response implements TibcoRequest {

	private String customerId;
	private List<Account> accounts;

	public static class Account{
		private String prepaidAccountId;
		private String companyCode;
		private String circleId;
		private String accountType;
		private List<ServicePackage> servicePackage;
		public String getPrepaidAccountId() {
			return prepaidAccountId;
		}
		public void setPrepaidAccountId(String prepaidAccountId) {
			this.prepaidAccountId = prepaidAccountId;
		}
		public String getCompanyCode() {
			return companyCode;
		}
		public void setCompanyCode(String companyCode) {
			this.companyCode = companyCode;
		}
		public String getCircleId() {
			return circleId;
		}
		public void setCircleId(String circleId) {
			this.circleId = circleId;
		}
		public String getAccountType() {
			return accountType;
		}
		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}
		public List<ServicePackage> getServicePackage() {
			return servicePackage;
		}
		public void setServicePackage(List<ServicePackage> servicePackage) {
			this.servicePackage = servicePackage;
		}
	}
	public static class ServicePackage{
		private String packageCode;
		private String packageName;
		private List<Services> services;
		public String getPackageCode() {
			return packageCode;
		}
		public void setPackageCode(String packageCode) {
			this.packageCode = packageCode;
		}
		public String getPackageName() {
			return packageName;
		}
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}
		public List<Services> getServices() {
			return services;
		}
		public void setServices(List<Services> services) {
			this.services = services;
		}
	}
	public static class Services{
		private String productCode;
		private String productName;
		private Identifier identifier;
		private String serviceContractReferenceNo;
		private String serviceStatus;
		private String statusDescription;
		private String serviceAlias;
		private String activationDate;
		private DependancyInfo dependancyInfo;
		private List action;
		private List<Characteristics> characteristics;
		private List associatedUsers;
		private List<CustomerFacingServices> customerFacingServices;
		public String getProductCode() {
			return productCode;
		}
		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public Identifier getIdentifier() {
			return identifier;
		}
		public void setIdentifier(Identifier identifier) {
			this.identifier = identifier;
		}
		public String getServiceContractReferenceNo() {
			return serviceContractReferenceNo;
		}
		public void setServiceContractReferenceNo(String serviceContractReferenceNo) {
			this.serviceContractReferenceNo = serviceContractReferenceNo;
		}
		public String getServiceStatus() {
			return serviceStatus;
		}
		public void setServiceStatus(String serviceStatus) {
			this.serviceStatus = serviceStatus;
		}
		public String getStatusDescription() {
			return statusDescription;
		}
		public void setStatusDescription(String statusDescription) {
			this.statusDescription = statusDescription;
		}
		public String getServiceAlias() {
			return serviceAlias;
		}
		public void setServiceAlias(String serviceAlias) {
			this.serviceAlias = serviceAlias;
		}
		public String getActivationDate() {
			return activationDate;
		}
		public void setActivationDate(String activationDate) {
			this.activationDate = activationDate;
		}
		public DependancyInfo getDependancyInfo() {
			return dependancyInfo;
		}
		public void setDependancyInfo(DependancyInfo dependancyInfo) {
			this.dependancyInfo = dependancyInfo;
		}
		public List getAction() {
			return action;
		}
		public void setAction(List action) {
			this.action = action;
		}
		public List<Characteristics> getCharacteristics() {
			return characteristics;
		}
		public void setCharacteristics(List<Characteristics> characteristics) {
			this.characteristics = characteristics;
		}
		public List getAssociatedUsers() {
			return associatedUsers;
		}
		public void setAssociatedUsers(List associatedUsers) {
			this.associatedUsers = associatedUsers;
		}
		public List<CustomerFacingServices> getCustomerFacingServices() {
			return customerFacingServices;
		}
		public void setCustomerFacingServices(
				List<CustomerFacingServices> customerFacingServices) {
			this.customerFacingServices = customerFacingServices;
		}
	}
	
	public static class Identifier{
		private String name;
		private String type;
		private String value;
		private String category;
		private String subCategory;
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
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getSubCategory() {
			return subCategory;
		}
		public void setSubCategory(String subCategory) {
			this.subCategory = subCategory;
		}
	}
	
	public static class CustomerFacingServices{
		private String serviceId;
		private String serviceName;
		private String serviceType;
		private Identifier identifier;
		public String getServiceId() {
			return serviceId;
		}
		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
		}
		public String getServiceName() {
			return serviceName;
		}
		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}
		public String getServiceType() {
			return serviceType;
		}
		public void setServiceType(String serviceType) {
			this.serviceType = serviceType;
		}
		public Identifier getIdentifier() {
			return identifier;
		}
		public void setIdentifier(Identifier identifier) {
			this.identifier = identifier;
		}
	}
	
	public static class Characteristics{
		private String  name;
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
	public static class DependancyInfo{
		private String parentProductId;
		private List<Identifier> identifier;
		public String getParentProductId() {
			return parentProductId;
		}
		public void setParentProductId(String parentProductId) {
			this.parentProductId = parentProductId;
		}
		public List<Identifier> getIdentifier() {
			return identifier;
		}
		public void setIdentifier(List<Identifier> identifier) {
			this.identifier = identifier;
		}
	}


	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, IllegalAccessException, InstantiationException {
		String test = "{ \"customerId\": \"1100009566\", \"accounts\": [ { \"prepaidAccountId\": \"001000009282\", \"companyCode\": \"TIBX\", \"circleId\": \"TC\", \"accountType\": \"\", \"servicePackage\": [ { \"packageCode\": \"OC401\", \"packageName\": \"VOLTE BASIC OFFER\", \"services\": [ { \"productCode\": \"P10016\", \"productName\": \"VVM VOLTE\", \"identifier\": { \"name\": \"SERVICE_NAME\", \"type\": \"GPRS\", \"value\": \"3334002189\", \"category\": \"4\", \"subCategory\": \"2\" }, \"serviceContractReferenceNo\": \"\", \"serviceStatus\": \"CMPD\", \"statusDescription\": \"SERVICE_STATUS_DESC\", \"serviceAlias\": \"SERVICE_\", \"activationDate\": \"2014-07-11\", \"dependancyInfo\": { \"parentProductId\": \"ParentID\", \"identifier\": [ { \"name\": \"SERVICE_NAME\", \"value\": \"1234\", \"category\": \"4\", \"subcategory\": \"\", \"type\": \"GPRS\" } ] }, \"action\":[ ], \"characteristics\": [ { \"name\": \"Reason\", \"value\": \"DND\" } , { \"name\": \"Reason Description\", \"value\": \"REASON_DESC\" } ], \"associatedUsers\": [ ], \"customerFacingServices\": [ { \"serviceId\": \"S40002\", \"serviceName\": \"VOLTE-DATA\", \"serviceType\": \"\", \"identifier\": { \"name\": \"IMSI\", \"value\": \"600000000006535\", \"category\": \"2\", \"subcategory\": \"\", \"type\": \"\" } } , { \"serviceId\": \"S40001\", \"serviceName\": \"VOICE VIDEO MESSAGING\", \"serviceType\": \"\", \"identifier\": { \"name\": \"R4GID\", \"value\": \"001900009190\", \"category\": \"2\", \"subcategory\": \"\", \"type\": \"\" } } ] } ] } ] } , { \"prepaidAccountId\": \"001900009190\", \"companyCode\": \"\", \"circleId\": \"TC\", \"accountType\": \"\", \"servicePackage\": [ { \"packageCode\": \"OC401\", \"packageName\": \"VOLTE BASIC OFFER\", \"services\": [ { \"productCode\": \"P10016\", \"productName\": \"VVM VOLTE\", \"identifier\": { \"name\": \"\", \"type\": \"\", \"value\": \"9334002189\", \"category\": \"4\", \"subCategory\": \"2\" }, \"serviceContractReferenceNo\": \"\", \"serviceStatus\": \"Z020\", \"statusDescription\": \"\", \"serviceAlias\": \"\", \"activationDate\": \"\", \"dependancyInfo\": { \"parentProductId\": \"\", \"identifier\": [ ] }, \"action\":[ ], \"characteristics\": [ ], \"associatedUsers\": [ ], \"customerFacingServices\": [ { \"serviceId\": \"S40002\", \"serviceName\": \"VOLTE-DATA\", \"serviceType\": \"\", \"identifier\": { \"name\": \"IMSI\", \"value\": \"600000000006535\", \"category\": \"2\", \"subcategory\": \"\", \"type\": \"\" } } , { \"serviceId\": \"S40001\", \"serviceName\": \"VOICE VIDEO MESSAGING\", \"serviceType\": \"\", \"identifier\": { \"name\": \"R4GID\", \"value\": \"001900009190\", \"category\": \"2\", \"subcategory\": \"\", \"type\": \"\" } } ] } ] } ] } ] }";
		System.err.println(test.replaceAll(" ", ""));
		YD0021Response rm = fillVal(test);
		System.err.println(new ObjectMapper().writeValueAsString(rm).replaceAll(" ", ""));
		
	}

	public static YD0021Response fillVal(String test) throws IOException,
			JsonParseException, JsonMappingException, IllegalAccessException,
			InstantiationException, JsonGenerationException {
		Map rv = new ObjectMapper().readValue(test, Map.class);
		YD0021Response rm = new YD0021Response();
		rm.setCustomerId(rv.get("customerId").toString());
		List accts = (List)rv.get("accounts");
		List acctsResponse = new ArrayList();
		rm.setAccounts(acctsResponse);
		for (Iterator it = accts.iterator(); it.hasNext();) {
			Map oneAccountMap = (Map) it.next();
			Account acc = (Account) extractStrValClass(oneAccountMap, Account.class);
			acctsResponse.add(acc);
			List servicePackageListInAcc = new ArrayList();
			acc.setServicePackage(servicePackageListInAcc);
			List servicePackageList = (List) oneAccountMap.get("servicePackage");
			for (Iterator spit = servicePackageList.iterator(); spit
					.hasNext();) {
				Object next = spit.next();
				Map oneServicePackageMap = (Map) next;
				List servicesInServicePackage = new ArrayList();
				ServicePackage sp = (ServicePackage) extractStrValClass(oneServicePackageMap, ServicePackage.class);
				sp.setServices(servicesInServicePackage);
				servicePackageListInAcc.add(sp);
				List servicesList = (List) oneServicePackageMap.get("services");
				for (Iterator sList = servicesList.iterator(); sList
						.hasNext();) {
					Map oneServiceMap = (Map) sList.next();
					Services oneService = (Services) extractStrValClass(oneServiceMap, Services.class);
					servicesInServicePackage.add(oneService);
					Map identifierMap = (Map) oneServiceMap.get("identifier");
					Identifier identifier = (Identifier) extractStrValClass(identifierMap, Identifier.class);
					oneService.setIdentifier(identifier);
					
					//下面是待处理的2014-06-26
					Map dependancyInfoMap = (Map) oneServiceMap.get("dependancyInfo");
					List characteristicsList = (List) oneServiceMap.get("characteristics");
					List customerFacingServicesList = (List) oneServiceMap.get("customerFacingServices");
					
				}
				
				
			}
		}
		return rm;
	}
	
	public static void extractStrValObj(Object mapObj, Object rm)
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
	public static Object extractStrValClass(Object mapObj, Class clazz)
			throws IllegalAccessException, InstantiationException {
		if (!(mapObj instanceof Map))
			return null;
		Object rm = clazz.newInstance();
		extractStrValObj(mapObj, rm);
		return rm;
	}


}
