package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.entity.ProductSpecMapping;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.DealerDataService;
import com.ai.mapp.sys.service.ProductService;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.util.PO2VOUtils;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.util.DateUtils;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.butterfly.mapp.core.model.IBody;
import com.ailk.yd.mapp.client.model.HW0010Request;
import com.ailk.yd.mapp.client.model.HW0012Request;
import com.ailk.yd.mapp.tibco.action.YD0010Action;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.Address;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.CafInfo;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.Connection;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.Contact;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.Customer;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.FamilyContact;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.Form61;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.IdObject;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.LocalRef;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.LocalRefVerify;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.NameAndValueObject;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.NameObject;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.Order;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.PayInfo;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.Proof;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.ReferringCustomer;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Response;


@Service("hw0012")
@Action(bizcode="hw0012",userCheck=true)
@Scope("prototype")
public class HW0012Action extends AbstractYDBaseActionHandler<HW0012Request, IBody> {

	@Autowired
	private AgentOrderService agentOrderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private YD0010Action yd0010;

	@Override
	protected void doAction() throws Exception 
	{
//		IUserinfo ui = (IUserinfo)MappContext.getAttribute(MappContext.MAPPCONTEXT_USER);
//		User creator = userService.loadUserByUserCode(ui.getUserName());
		AgentOrder ao = agentOrderService.loadAgentOrderByOrderCode(request.getOrderCode());
		if(ao == null)
			throw new Exception(request.getOrderCode()+": not found");
		
		Map<String,String> resourceMap =  new HashMap<String, String>(0);
		
		if(StringUtils.equals(SYSConstant.AGENT_ORDER_TYPE_NEW, ao.getOrderType())){
			//心开户订单
			if(StringUtils.isBlank(ao.getCafInfo()))
				throw new Exception("caf information not found");
			HW0010Request hw0010Request = mapper.readValue(ao.getCafInfo(), HW0010Request.class);
			YD0010Request yd0010Request = convertByHW0010(hw0010Request);
			System.out.println(mapper.writeValueAsString(yd0010Request));
			YD0010Response yd0010Response = yd0010.post2Tibco(PO2VOUtils.replaceNull(yd0010Request), null);
			/**
			 * 如果成功，设置订单上tibco发送成功的标记位
			 */
			ao.setTibcoSendFlag("1");
			agentOrderService.saveAgentOrder(ao);
			resourceMap = getResource(hw0010Request);
		}
		agentOrderService.completedOrder(ao.getOrderCode(), resourceMap);
	}
	
	protected Map<String,String> getResource(HW0010Request hw0010_req) throws Exception
	{
		if(hw0010_req.getCafInfos() == null || hw0010_req.getCafInfos().isEmpty())
			throw new Exception("no caf information");
		
		HW0010Request.CafInfo caf = hw0010_req.getCafInfos().get(0);
		HW0010Request.Order hw_caf_order = caf.getOrder();
		
		Map<String,Map<String,String>> deviceMap = hw_caf_order.getDevices();
		
		if(deviceMap == null || deviceMap.isEmpty())
			return null;
		
		Map<String,String> resourceMap = new HashMap<String, String>(0);
		
		for(String key : deviceMap.keySet())
		{
			if(deviceMap.get(key) == null || deviceMap.get(key).isEmpty())
				continue;
			
			for(Entry<String,String> entry : deviceMap.get(key).entrySet())
			{
				resourceMap.put(entry.getKey(), entry.getValue());
			}
		}
		
		return resourceMap;
	}
	
	public YD0010Request convertByHW0010(HW0010Request hw0010_req) throws Exception
	{
		if(hw0010_req.getCafInfos() == null || hw0010_req.getCafInfos().isEmpty())
			throw new Exception("no caf information");
		
		HW0010Request.CafInfo caf = hw0010_req.getCafInfos().get(0);
		HW0010Request.Order hw_caf_order = caf.getOrder();
		HW0010Request.Customer hw_customer = caf.getCustomer();
		
		if(hw_caf_order == null)
			throw new Exception("no order information");
		
		YD0010Request req = new YD0010Request();
		
		req.setReferenceNumber(hw_caf_order.getOrn()==null?"":hw_caf_order.getOrn());
		req.setCustomerId(hw_customer.getCustomerId()==null?"":hw_customer.getCustomerId());
		req.setOrderType("CREATE");
		req.setCircleId("TC");
		req.setAppointmentDateTimeFrom(hw_caf_order.getAppointmentDateTimeFrom()==null?"":hw_caf_order.getAppointmentDateTimeFrom());
		req.setAppointmentDateTimeTo(hw_caf_order.getAppointmentDateTimeTo()==null?"":hw_caf_order.getAppointmentDateTimeTo());
		//测试数据
		hw_caf_order.setChannel("10");
		req.setChannel(hw_caf_order.getChannel()==null?"":hw_caf_order.getChannel());
		req.setDeliveryMode(hw_caf_order.getDeliveryMode()==null?"":hw_caf_order.getDeliveryMode());
		
		/********customerDetail 设置***********/
		req.setCustomerDetails(new Customer());
		req.getCustomerDetails().setProspectId(hw_customer.getProspectId()==null?"":hw_customer.getProspectId());
		req.getCustomerDetails().setCustomerCategory(hw_customer.getCustomerCategory()==null?"":StringUtils.leftPad(hw_customer.getCustomerCategory(),4,"0"));
		req.getCustomerDetails().setSalutation(hw_customer.getSalutation()==null?"":StringUtils.leftPad(hw_customer.getSalutation(),4,"0"));
		req.getCustomerDetails().setFirstName(hw_customer.getFirstName()==null?"":hw_customer.getFirstName());
		req.getCustomerDetails().setMiddleName(hw_customer.getMiddleName()==null?"":hw_customer.getMiddleName());
		req.getCustomerDetails().setLastName(hw_customer.getLastName()==null?"":hw_customer.getLastName());
		
		/********FamilyContact 设置***********/
		req.getCustomerDetails().setFamilyContactDetails(new FamilyContact());
		req.getCustomerDetails().getFamilyContactDetails().setFirstName(hw_customer.getFamilyFirstName()==null?"":hw_customer.getFamilyFirstName());
		req.getCustomerDetails().getFamilyContactDetails().setMiddleName(hw_customer.getFamilyMiddleName()==null?"":hw_customer.getFamilyMiddleName());
		req.getCustomerDetails().getFamilyContactDetails().setLastName(hw_customer.getFamilyLastName()==null?"":hw_customer.getFamilyLastName());
		//测试数据
		hw_customer.setFamilyRelationship("0001");
		req.getCustomerDetails().getFamilyContactDetails().setRelationship(hw_customer.getFamilyRelationship()==null?"":StringUtils.leftPad(hw_customer.getFamilyRelationship(),4,"0"));
		req.getCustomerDetails().setDateOfBirth("");
		if(hw_customer.getDateOfBirth() != null)
		{
			Date d = DateUtils.formatDate(hw_customer.getDateOfBirth(), "dd/MM/yyyy");
			req.getCustomerDetails().setDateOfBirth(DateUtils.parse(d.getTime(), "yyyy-MM-dd"));
		}
		req.getCustomerDetails().setGender(hw_customer.getGender()==null?"":hw_customer.getGender());
		req.getCustomerDetails().setNationality(hw_customer.getNationality()==null?"":hw_customer.getNationality());
		if(hw_customer.getForeignNational() != null)
		{
			req.getCustomerDetails().setPassportNo(hw_customer.getForeignNational().getPassportNo()==null?"":hw_customer.getForeignNational().getPassportNo());
			req.getCustomerDetails().setVisaNo(hw_customer.getForeignNational().getVisaNo()==null?"":hw_customer.getForeignNational().getVisaNo());
			req.getCustomerDetails().setVisaValidityDate(hw_customer.getForeignNational().getVisaValidityDate()==null?"":hw_customer.getForeignNational().getVisaValidityDate());
		}
		
		/*********Contact*********/
		req.getCustomerDetails().setContactDetails(new Contact());
		
		//测试数据
//		req.getCustomerDetails().getContactDetails().setMobileNumber("5885555555");
//		req.getCustomerDetails().getContactDetails().setEmailId("ggyy@bh.com");
//		req.getCustomerDetails().getContactDetails().setAlternateContactNumberHome("5666777766");
//		req.getCustomerDetails().getContactDetails().setAlternateContactNumberWork("");
		
		req.getCustomerDetails().getContactDetails().setMobileNumber(hw_customer.getMobileNumber()==null?"":hw_customer.getMobileNumber());
		req.getCustomerDetails().getContactDetails().setAlternateContactNumberHome(hw_customer.getAlternateContactNumberHome()==null?"":hw_customer.getAlternateContactNumberHome());
		req.getCustomerDetails().getContactDetails().setAlternateContactNumberWork(hw_customer.getAlternateContactNumberWork()==null?"":hw_customer.getAlternateContactNumberWork());
		req.getCustomerDetails().getContactDetails().setEmailId(hw_customer.getEmailId()==null?"":hw_customer.getEmailId());
		
		/*********PermanentAddress*********/
		if(hw_customer.getPermanentAddress() != null)
		{
			HW0010Request.Address address = hw_customer.getPermanentAddress();
			
//			address = new HW0010Request.Address();
//			address.setAddressId("");
//			address.setBuildingId("");
//			address.setAddressType("PER_ADD");
//			address.setCareOf("");
//			address.setHouseNameORNumber("ghh");
//			address.setBuildingNameORNumber("TC23- C Block");
//			address.setSocietyName("");
//			address.setStreetNameORNumber("hjj");
//			address.setLandmark("");
//			address.setSubLocality("");
//			address.setAreaORTehsil("ghh");
//			address.setPincode("666888");
//			address.setVillageORCity("Mumbai");
//			address.setDistrict("mum");
//			address.setState("MH");
//			address.setCountry("IN");
//			address.setTotalFloors("");
//			address.setJioCentreId("JC23");
			
			req.getCustomerDetails().setPermanentAddress(new Address());
			req.getCustomerDetails().getPermanentAddress().setAddressId(address.getAddressId()==null?"":address.getAddressId());
			req.getCustomerDetails().getPermanentAddress().setBuildingId(address.getBuildingId()==null?"":address.getBuildingId());
			req.getCustomerDetails().getPermanentAddress().setAddressType("PER_ADD");
			req.getCustomerDetails().getPermanentAddress().setCareOf(address.getCareOf()==null?"":address.getCareOf());
			req.getCustomerDetails().getPermanentAddress().setHouseNameORNumber(address.getHouseNameORNumber()==null?"":address.getHouseNameORNumber());
			req.getCustomerDetails().getPermanentAddress().setBuildingNameORNumber(address.getBuildingNameORNumber()==null?"":address.getBuildingNameORNumber());
			req.getCustomerDetails().getPermanentAddress().setSocietyName(address.getSocietyName()==null?"":address.getSocietyName());
			req.getCustomerDetails().getPermanentAddress().setStreetNameORNumber(address.getStreetNameORNumber()==null?"":address.getStreetNameORNumber());
			req.getCustomerDetails().getPermanentAddress().setLandmark(address.getLandmark()==null?"":address.getLandmark());
			req.getCustomerDetails().getPermanentAddress().setSubLocality(address.getSubLocality()==null?"":address.getSubLocality());
			req.getCustomerDetails().getPermanentAddress().setAreaORTehsil(address.getAreaORTehsil()==null?"":address.getAreaORTehsil());
			req.getCustomerDetails().getPermanentAddress().setPincode(address.getPincode()==null?"":address.getPincode());
			req.getCustomerDetails().getPermanentAddress().setVillageORCity(address.getVillageORCity()==null?"":address.getVillageORCity());
			req.getCustomerDetails().getPermanentAddress().setDistrict(address.getDistrict()==null?"":address.getDistrict());
			req.getCustomerDetails().getPermanentAddress().setState(address.getState()==null?"":address.getState());
			req.getCustomerDetails().getPermanentAddress().setCountry(address.getCountry()==null?"":address.getCountry());
			req.getCustomerDetails().getPermanentAddress().setTotalFloors(address.getTotalFloors()==null?"":address.getTotalFloors());
			req.getCustomerDetails().getPermanentAddress().setJioCentreId(address.getJioCentreId()==null?"":address.getJioCentreId());
		}
		
		req.getCustomerDetails().setPanNumber(hw_customer.getPanNumber()==null?"":hw_customer.getPanNumber());
		req.getCustomerDetails().setPreferredLanguage(hw_customer.getPreferredLanguage()==null?"":hw_customer.getPreferredLanguage());
		req.getCustomerDetails().setPreferredCommunicationChannel(hw_customer.getPreferredCommunicationChannel()==null?"":hw_customer.getPreferredCommunicationChannel());
		req.getCustomerDetails().setAadhaarNumber(hw_customer.getAadhaarNumber()==null?"":hw_customer.getAadhaarNumber());
		req.getCustomerDetails().setMaritalStatus(hw_customer.getMaritalStatus()==null?"":hw_customer.getMaritalStatus());
		req.getCustomerDetails().setAnniversaryDate("");
		if(hw_customer.getAnniversaryDate() != null)
		{
			Date d = DateUtils.formatDate(hw_customer.getAnniversaryDate(), "dd/MM/yyyy");
			req.getCustomerDetails().setAnniversaryDate(DateUtils.parse(d.getTime(), "yyyy-MM-dd"));
		}
		req.getCustomerDetails().setOccupation(hw_customer.getOccupation()==null?"":StringUtils.leftPad(hw_customer.getOccupation(), 4, "0"));
		req.getCustomerDetails().setOccupationDescription(hw_customer.getOccupationDescription()==null?"":hw_customer.getOccupationDescription());
		req.getCustomerDetails().setTypeOfHouse(hw_customer.getTypeOfHouse()==null?"":hw_customer.getTypeOfHouse());
		req.getCustomerDetails().setCustomerPictureURL(hw_customer.getCustomerPictureURL()==null?"":hw_customer.getCustomerPictureURL());
		
		/*******************PaymentDetails****************************/
		
		if(caf.getPayInfo() != null)
		{
			HW0010Request.PayInfo payInfo = caf.getPayInfo();
			req.setPaymentDetails(new PayInfo());
			req.getPaymentDetails().setModeOfPayment(payInfo.getModeOfPayment()==null?"":payInfo.getModeOfPayment());
			req.getPaymentDetails().setPaymentInstrumentNumber(payInfo.getPaymentInstrumentNumber()==null?"":payInfo.getPaymentInstrumentNumber());
			req.getPaymentDetails().setPaymentInstrumentDate("");
			if(payInfo.getPaymentInstrumentDate() != null)
			{
				Date d = DateUtils.formatDate(payInfo.getPaymentInstrumentDate(), "dd/MM/yyyy");
				req.getPaymentDetails().setPaymentInstrumentDate(DateUtils.parse(d.getTime(), "yyyy-MM-dd"));
			}
//			req.getPaymentDetails().setPaymentInstrumentDate(payInfo.getPaymentInstrumentDate()==null?"":DateUtils.parse(payInfo.getPaymentInstrumentDate().getTime(),"yyyy-MM-dd"));
			req.getPaymentDetails().setBankName(payInfo.getBankName()==null?"":payInfo.getBankName());
			req.getPaymentDetails().setBranchNameAndAddress(payInfo.getBranchNameAndAddress()==null?"":payInfo.getBranchNameAndAddress());
			req.getPaymentDetails().setReceiptNumber(payInfo.getReceiptNumber()==null?"":payInfo.getReceiptNumber());
			req.getPaymentDetails().setCpTransactionId(payInfo.getCpTransactionId()==null?"":payInfo.getCpTransactionId());
		}
//		else
//		{
//			req.setPaymentDetails(new PayInfo());
//			req.getPaymentDetails().setModeOfPayment("");
//			req.getPaymentDetails().setPaymentInstrumentNumber("");
//			req.getPaymentDetails().setPaymentInstrumentDate("");
//			req.getPaymentDetails().setPaymentInstrumentDate("");
//			req.getPaymentDetails().setBankName("");
//			req.getPaymentDetails().setBranchNameAndAddress("");
//			req.getPaymentDetails().setReceiptNumber("");
//			req.getPaymentDetails().setCpTransactionId("");
//		}
		
		
		/********************InstallationAddress************************/
		if(hw_customer.getPresentAddress() != null)
		{
			HW0010Request.Address address = hw_customer.getPresentAddress();
			
			//测试数据
//			address = new HW0010Request.Address();
//			address.setAddressId("");
//			address.setBuildingId("");
//			address.setAddressType("PER_ADD");
//			address.setCareOf("");
//			address.setHouseNameORNumber("ghh");
//			address.setBuildingNameORNumber("TC23- C Block");
//			address.setSocietyName("");
//			address.setStreetNameORNumber("hjj");
//			address.setLandmark("");
//			address.setSubLocality("");
//			address.setAreaORTehsil("ghh");
//			address.setPincode("666888");
//			address.setVillageORCity("Mumbai");
//			address.setDistrict("mum");
//			address.setState("MH");
//			address.setCountry("IN");
//			address.setTotalFloors("");
//			address.setJioCentreId("JC23");
			
			
			req.setInstallationAddress(new ArrayList<YD0010Request.Address>(0));
			Address install_address = new Address();
			install_address.setAddressId(address.getAddressId()==null?"":address.getAddressId());
			install_address.setBuildingId(address.getBuildingId()==null?"":address.getBuildingId());
			install_address.setAddressType("SHIP_TO");
			install_address.setCareOf(address.getCareOf()==null?"":address.getCareOf());
			install_address.setHouseNameORNumber(address.getHouseNameORNumber()==null?"":address.getHouseNameORNumber());
			install_address.setBuildingNameORNumber(address.getBuildingNameORNumber()==null?"":address.getBuildingNameORNumber());
			install_address.setSocietyName(address.getSocietyName()==null?"":address.getSocietyName());
			install_address.setStreetNameORNumber(address.getStreetNameORNumber()==null?"":address.getStreetNameORNumber());
			install_address.setLandmark(address.getLandmark()==null?"":address.getLandmark());
			install_address.setSubLocality(address.getSubLocality()==null?"":address.getSubLocality());
			install_address.setAreaORTehsil(address.getAreaORTehsil()==null?"":address.getAreaORTehsil());
			install_address.setPincode(address.getPincode()==null?"":address.getPincode());
			install_address.setVillageORCity(address.getVillageORCity()==null?"":address.getVillageORCity());
			install_address.setDistrict(address.getDistrict()==null?"":address.getDistrict());
			install_address.setState(address.getState()==null?"":address.getState());
			install_address.setCountry(address.getCountry()==null?"":address.getCountry());
			install_address.setTotalFloors(address.getTotalFloors()==null?"":address.getTotalFloors());
			install_address.setJioCentreId(address.getJioCentreId()==null?"":address.getJioCentreId());
			req.getInstallationAddress().add(install_address);
		}
		
		
		/********************cafDetails************************/
		req.setCafDetails(new CafInfo());
		
		//测试数据
		caf.setPosAgentCode("POSAgent01");
		caf.setMerchantCode("POSAgent01");
		req.getCafDetails().setMerchantCode(caf.getMerchantCode()==null?"":caf.getMerchantCode());
		req.getCafDetails().setPosAgentCode(caf.getPosAgentCode()==null?"":caf.getPosAgentCode());
		req.getCafDetails().setPosAgentSignatureDate("");
		if(caf.getPosAgentSignatureDate() != null)
		{
			Date d = DateUtils.formatDate(caf.getPosAgentSignatureDate(), "dd/MM/yyyy");
			req.getCafDetails().setPosAgentSignatureDate(DateUtils.parse(d.getTime(), "yyyy-MM-dd"));
		}
//		req.getCafDetails().setPosAgentSignatureDate(caf.getPosAgentSignatureDate()==null?"":DateUtils.parse(caf.getPosAgentSignatureDate().getTime(),"yyyy-MM-dd"));
		req.getCafDetails().setCustomerDeclarationPlace(caf.getCustomerDeclarationPlace()==null?"":caf.getCustomerDeclarationPlace());
		req.getCafDetails().setCustomerDeclarationDate("");
		if(caf.getCustomerDeclarationDate() != null)
		{
			Date d = DateUtils.formatDate(caf.getCustomerDeclarationDate(), "dd/MM/yyyy");
			req.getCafDetails().setPosAgentSignatureDate(DateUtils.parse(d.getTime(), "yyyy-MM-dd"));
		}
//		req.getCafDetails().setCustomerDeclarationDate(caf.getCustomerDeclarationDate()==null?"":DateUtils.parse(caf.getCustomerDeclarationDate().getTime(),"yyyy-MM-dd"));
		
		req.getCafDetails().setProofs(new ArrayList<YD0010Request.Proof>(0));
		
		if(caf.getPoa() != null)
		{
			Proof poa = new Proof();
			poa.setProofIdentifier("POA");
			poa.setIdProofType(caf.getPoa().getIdProofType()==null?"":caf.getPoa().getIdProofType());
			poa.setDocumentNumber(caf.getPoa().getDocumentNumber()==null?"":caf.getPoa().getDocumentNumber());
			poa.setDateOfIssue("");
			if(caf.getPoi().getDateOfIssue() != null)
			{
				Date d = DateUtils.formatDate(caf.getPoi().getDateOfIssue(), "dd/MM/yyyy");
				poa.setDateOfIssue(DateUtils.parse(d.getTime(), "yyyy-MM-dd"));
			}
//			poa.setDateOfIssue(caf.getPoa().getDateOfIssue()==null?"":caf.getPoa().getDateOfIssue());
			poa.setPlaceOfIssue(caf.getPoa().getPlaceOfIssue()==null?"":caf.getPoa().getPlaceOfIssue());
			poa.setIssuingAuthority(caf.getPoa().getIssuingAuthority()==null?"":caf.getPoa().getIssuingAuthority());
			poa.setIdentifierURL(caf.getPoa().getIdentifierURL()==null?"":caf.getPoa().getIdentifierURL());
			poa.setAadhaarTransactionRefNo(caf.getPoa().getAadhaarTransactionRefNo()==null?"":caf.getPoa().getAadhaarTransactionRefNo());
			req.getCafDetails().getProofs().add(poa);
		}
		
		
		if(caf.getPoi() != null)
		{
			Proof poi = new Proof();
			poi.setProofIdentifier("POI");
			poi.setIdProofType(caf.getPoi().getIdProofType()==null?"":caf.getPoi().getIdProofType());
			poi.setDocumentNumber(caf.getPoi().getDocumentNumber()==null?"":caf.getPoi().getDocumentNumber());
			poi.setDateOfIssue("");
			if(caf.getPoi().getDateOfIssue() != null)
			{
				Date d = DateUtils.formatDate(caf.getPoi().getDateOfIssue(), "dd/MM/yyyy");
				poi.setDateOfIssue(DateUtils.parse(d.getTime(), "yyyy-MM-dd"));
			}
//			poi.setDateOfIssue(caf.getPoi().getDateOfIssue()==null?"":caf.getPoi().getDateOfIssue());
			poi.setPlaceOfIssue(caf.getPoi().getPlaceOfIssue()==null?"":caf.getPoi().getPlaceOfIssue());
			poi.setIssuingAuthority(caf.getPoi().getIssuingAuthority()==null?"":caf.getPoi().getIssuingAuthority());
			poi.setIdentifierURL(caf.getPoi().getIdentifierURL()==null?"":caf.getPoi().getIdentifierURL());
			poi.setAadhaarTransactionRefNo(caf.getPoi().getAadhaarTransactionRefNo()==null?"":caf.getPoi().getAadhaarTransactionRefNo());
			req.getCafDetails().getProofs().add(poi);
		}
		
		req.getCafDetails().setCurrentMobileConnections(new ArrayList<YD0010Request.Connection>(0));
		Connection conn = new Connection();
//		conn.setOperatorName(caf.getOperatorName()==null?"":caf.getOperatorName());
//		conn.setNoOfConnections(caf.getNoOfConnections()==null?"":caf.getNoOfConnections());
		req.getCafDetails().getCurrentMobileConnections().add(conn);
		req.getCafDetails().setForm61Details(new Form61());
		/***Form61***/
		if(caf.getForm61() != null)
		{
//			req.getCafDetails().getForm61Details().setLastTaxReturnFiled(caf.getForm61().getLastTaxReturnFiled()==null?"":caf.getForm61().getLastTaxReturnFiled());
//			req.getCafDetails().getForm61Details().setReasonForNoPAN(caf.getForm61().getReasonForNoPAN()==null?"":caf.getForm61().getReasonForNoPAN());
		}
		
		req.getCafDetails().setLocalReferenceDetails(new LocalRef());
		if(caf.getLocalReferenceDetails() != null)
		{
			HW0010Request.LocalRef lr = caf.getLocalReferenceDetails();
			req.getCafDetails().getLocalReferenceDetails().setFirstName(lr.getFirstName()==null?"":lr.getFirstName());
			req.getCafDetails().getLocalReferenceDetails().setMiddleName(lr.getMiddleName()==null?"":lr.getMiddleName());
			req.getCafDetails().getLocalReferenceDetails().setLastName(lr.getLastName()==null?"":lr.getLastName());
			req.getCafDetails().getLocalReferenceDetails().setContactNumber(lr.getContactNumber()==null?"":lr.getContactNumber());
			req.getCafDetails().getLocalReferenceDetails().seteMailId(lr.geteMailId()==null?"":lr.geteMailId());
			
			if(lr.getAddress() != null)
			{
				HW0010Request.Address hw_lr_address = lr.getAddress();
				YD0010Request.Address yd_lr_address = new Address();
				
//				//测试数据
//				hw_lr_address = new HW0010Request.Address();
//				hw_lr_address.setAddressId("");
//				hw_lr_address.setBuildingId("");
//				hw_lr_address.setAddressType("PER_ADD");
//				hw_lr_address.setCareOf("");
//				hw_lr_address.setHouseNameORNumber("ghh");
//				hw_lr_address.setBuildingNameORNumber("TC23- C Block");
//				hw_lr_address.setSocietyName("");
//				hw_lr_address.setStreetNameORNumber("hjj");
//				hw_lr_address.setLandmark("");
//				hw_lr_address.setSubLocality("");
//				hw_lr_address.setAreaORTehsil("ghh");
//				hw_lr_address.setPincode("666888");
//				hw_lr_address.setVillageORCity("Mumbai");
//				hw_lr_address.setDistrict("mum");
//				hw_lr_address.setState("MH");
//				hw_lr_address.setCountry("IN");
//				hw_lr_address.setTotalFloors("");
//				hw_lr_address.setJioCentreId("JC23");
				
				
				req.getCafDetails().getLocalReferenceDetails().setAddress(yd_lr_address);
				yd_lr_address.setAddressId(hw_lr_address.getAddressId()==null?"":hw_lr_address.getAddressId());
				yd_lr_address.setBuildingId(hw_lr_address.getBuildingId()==null?"":hw_lr_address.getBuildingId());
				yd_lr_address.setAddressType("REF_ADD");
				yd_lr_address.setCareOf(hw_lr_address.getCareOf()==null?"":hw_lr_address.getCareOf());
				yd_lr_address.setHouseNameORNumber(hw_lr_address.getHouseNameORNumber()==null?"":hw_lr_address.getHouseNameORNumber());
				yd_lr_address.setBuildingNameORNumber(hw_lr_address.getBuildingNameORNumber()==null?"":hw_lr_address.getBuildingNameORNumber());
				yd_lr_address.setSocietyName(hw_lr_address.getSocietyName()==null?"":hw_lr_address.getSocietyName());
				yd_lr_address.setStreetNameORNumber(hw_lr_address.getStreetNameORNumber()==null?"":hw_lr_address.getStreetNameORNumber());
				yd_lr_address.setLandmark(hw_lr_address.getLandmark()==null?"":hw_lr_address.getLandmark());
				yd_lr_address.setSubLocality(hw_lr_address.getSubLocality()==null?"":hw_lr_address.getSubLocality());
				yd_lr_address.setAreaORTehsil(hw_lr_address.getAreaORTehsil()==null?"":hw_lr_address.getAreaORTehsil());
				yd_lr_address.setPincode(hw_lr_address.getPincode()==null?"":hw_lr_address.getPincode());
				yd_lr_address.setVillageORCity(hw_lr_address.getVillageORCity()==null?"":hw_lr_address.getVillageORCity());
				yd_lr_address.setDistrict(hw_lr_address.getDistrict()==null?"":hw_lr_address.getDistrict());
				yd_lr_address.setState(hw_lr_address.getState()==null?"":hw_lr_address.getState());
				yd_lr_address.setCountry(hw_lr_address.getCountry()==null?"":hw_lr_address.getCountry());
				yd_lr_address.setTotalFloors(hw_lr_address.getTotalFloors()==null?"":hw_lr_address.getTotalFloors());
				yd_lr_address.setJioCentreId(hw_lr_address.getJioCentreId()==null?"":hw_lr_address.getJioCentreId());
			}
		}

		/****ReferringCustomer****/
		req.getCafDetails().setReferringCustomerDetails(new ReferringCustomer());
		req.getCafDetails().getReferringCustomerDetails().setId(caf.getReferringCustomerId()==null?"":caf.getReferringCustomerId());
		req.getCafDetails().getReferringCustomerDetails().setReferenceMobileNumber(caf.getReferenceMobileNumber()==null?"":caf.getReferenceMobileNumber());
		
		/*************************LocalReference*******************************/
		if(caf.getLocalReferenceDetails() != null)
		{
			req.getCafDetails().setLocalReferenceVerification(new LocalRefVerify());
			req.getCafDetails().getLocalReferenceVerification().setCallingPartyNumber(caf.getLocalReferenceDetails().getCallingPartyNumber()==null?"":caf.getLocalReferenceDetails().getCallingPartyNumber());
		}
		
		/*************************orderDetails*******************************/
		if(hw_caf_order != null)
		{
			HW0010Request.Order hw_order = hw_caf_order;
			req.setOrderDetails(new ArrayList<YD0010Request.Order>(0));
			Order order = new Order();
			req.getOrderDetails().add(order);
			order.setBusinessInteraction(new NameObject(""));
			order.setAccountId(hw_order.getAccountId()==null?"":hw_order.getAccountId());
			order.setOfferId(hw_order.getOfferId()==null?"":hw_order.getOfferId());
			order.seteWalletReservationReferenceId(hw_order.geteWalletReservationReferenceId()==null?"":hw_order.geteWalletReservationReferenceId());
			order.setPlanOffering(new IdObject(hw_order.getPlanOffering()));
			
			if(hw_order.getBillingAddress() != null)
			{
				HW0010Request.Address address = hw_order.getBillingAddress();
				
//				//测试数据
//				address = new HW0010Request.Address();
//				address.setAddressId("");
//				address.setBuildingId("");
//				address.setAddressType("PER_ADD");
//				address.setCareOf("");
//				address.setHouseNameORNumber("ghh");
//				address.setBuildingNameORNumber("TC23- C Block");
//				address.setSocietyName("");
//				address.setStreetNameORNumber("hjj");
//				address.setLandmark("");
//				address.setSubLocality("");
//				address.setAreaORTehsil("ghh");
//				address.setPincode("666888");
//				address.setVillageORCity("Mumbai");
//				address.setDistrict("mum");
//				address.setState("MH");
//				address.setCountry("IN");
//				address.setTotalFloors("");
//				address.setJioCentreId("JC23");
				
				order.setBillingAddress(new Address());
				order.getBillingAddress().setAddressId(address.getAddressId()==null?"":address.getAddressId());
				order.getBillingAddress().setBuildingId(address.getBuildingId()==null?"":address.getBuildingId());
				order.getBillingAddress().setAddressType("BILL_TO");
				order.getBillingAddress().setCareOf(address.getCareOf()==null?"":address.getCareOf());
				order.getBillingAddress().setHouseNameORNumber(address.getHouseNameORNumber()==null?"":address.getHouseNameORNumber());
				order.getBillingAddress().setBuildingNameORNumber(address.getBuildingNameORNumber()==null?"":address.getBuildingNameORNumber());
				order.getBillingAddress().setSocietyName(address.getSocietyName()==null?"":address.getSocietyName());
				order.getBillingAddress().setStreetNameORNumber(address.getStreetNameORNumber()==null?"":address.getStreetNameORNumber());
				order.getBillingAddress().setLandmark(address.getLandmark()==null?"":address.getLandmark());
				order.getBillingAddress().setSubLocality(address.getSubLocality()==null?"":address.getSubLocality());
				order.getBillingAddress().setAreaORTehsil(address.getAreaORTehsil()==null?"":address.getAreaORTehsil());
				order.getBillingAddress().setPincode(address.getPincode()==null?"":address.getPincode());
				order.getBillingAddress().setVillageORCity(address.getVillageORCity()==null?"":address.getVillageORCity());
				order.getBillingAddress().setDistrict(address.getDistrict()==null?"":address.getDistrict());
				order.getBillingAddress().setState(address.getState()==null?"":address.getState());
				order.getBillingAddress().setCountry(address.getCountry()==null?"":address.getCountry());
				order.getBillingAddress().setTotalFloors(address.getTotalFloors()==null?"":address.getTotalFloors());
				order.getBillingAddress().setJioCentreId(address.getJioCentreId()==null?"":address.getJioCentreId());
			}
			
			/**********如果有Product则设置下面相关的信息**********/
			if(StringUtils.isBlank(hw_caf_order.getOfferId()) == false)
			{
				Product p = productService.getProductByCode(hw_caf_order.getOfferId()==null?"":hw_caf_order.getOfferId());
				
				/** 设置resourceSpec **/
				ProductSpecMapping productSpecMapping = DealerDataService.mapper.readValue(p.getProductSpecList(),ProductSpecMapping.class);
				order.setProducts(new ArrayList<YD0010Request.Product>(0));
				
				if(productSpecMapping != null && productSpecMapping.getProductSpecs() != null && productSpecMapping.getProductSpecs().isEmpty() == false)
				{
					for(ProductSpecMapping.ProductSpec productSpec : productSpecMapping.getProductSpecs())
					{
						YD0010Request.Product ps = new YD0010Request.Product();
						ps.setBusinessInteraction(new NameObject("ADD"));
						ps.setProductId(productSpec.getProductSpecificationId()==null?"":productSpec.getProductSpecificationId());
						ps.setStarterKitCode("Y");
						order.getProducts().add(ps);
						if(req.getCafDetails() != null && req.getCafDetails().getProofs().isEmpty() == false)
						{
							ps.setProofs(new ArrayList<YD0010Request.ProductProof>(0));
							
							for(YD0010Request.Proof pf : req.getCafDetails().getProofs())
							{
								YD0010Request.ProductProof tmp_pf = new YD0010Request.ProductProof();
								tmp_pf.setIdentifierURL(pf.getIdentifierURL());
								tmp_pf.setProofIdentifier(pf.getProofIdentifier());
								ps.getProofs().add(tmp_pf);
							}
						}
						
						ps.setDevices(new ArrayList<YD0010Request.Device>(0));
						if(productSpec.getResourceSpecList() == null || productSpec.getResourceSpecList().isEmpty())
							continue;
						
						Map<String,Map<String,String>> deviceMap = hw_caf_order.getDevices();
						for(ProductSpecMapping.ResourceSpec resourceSpec : productSpec.getResourceSpecList())
						{
							YD0010Request.Device device = new YD0010Request.Device();
							device.setBoqType(resourceSpec.getType()==null?"":resourceSpec.getType());
							device.setBusinessInteraction(new NameObject("ADD"));
							device.setProductId(resourceSpec.getResourceSpecificationId()==null?"":resourceSpec.getResourceSpecificationId());
							device.setIdentifier(new ArrayList<YD0010Request.NameAndValueObject>(0));
							
							if(deviceMap.get(resourceSpec.getResourceSpecificationId()) == null || deviceMap.get(resourceSpec.getResourceSpecificationId()).isEmpty())
								continue;
							
							for(String key : deviceMap.get(resourceSpec.getResourceSpecificationId()).keySet())
							{
								device.getIdentifier().add(new NameAndValueObject("SERIAL_NUMBER", deviceMap.get(resourceSpec.getResourceSpecificationId()).get(key)));
							}
						}
					}
				}
				
			}
			
			else
			{}
		
		}
		
		return req;
	}
	
	private void validate(YD0010Request request) throws Exception
	{
		if(request == null || request.getCustomerDetails() == null || request.getCafDetails() == null)
			throw new Exception("request Incomplete");
		
		YD0010Request.Customer customer =  request.getCustomerDetails();
		YD0010Request.CafInfo caf =  request.getCafDetails();
		
		if(StringUtils.isEmpty(customer.getPanNumber()) && caf.getForm61Details() == null)
			throw new Exception("PanNumber is null,form61 to be filled");
		//Outstation 类型为 5
		if("5".equals(customer.getCustomerCategory()) && caf.getLocalReferenceDetails() == null)
		{
			throw new Exception("Outstation Customer to be filled Local Reference info");
		}
		
		//visa号码不为空，而visa有效期为空
		if(StringUtils.isBlank(customer.getVisaNo()) == false && StringUtils.isBlank(customer.getVisaValidityDate()))
		{
			throw new Exception("Visa Validity Date is null ");
		}
		
	}
	
}
