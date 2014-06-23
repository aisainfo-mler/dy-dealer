package com.ailk.yd.mapp.client.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.SmallLocalFile;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.SmallLocalFileService;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.butterfly.mapp.core.model.IBody;
import com.ailk.util.JsonUtil;
import com.ailk.util.SetUtil;
import com.ailk.yd.mapp.client.model.HW0010Request;
import com.ailk.yd.mapp.client.model.HW0012Request;
import com.ailk.yd.mapp.model.YDDatapackage;
import com.ailk.yd.mapp.tibco.action.YD0010Action;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request_diff;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Response;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.Address;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.CafInfo;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.Connection;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.Contact;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.Customer;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.Dependancy;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.Device;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.FacingService;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.FamilyContact;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.Form61;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.IdObject;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.LocalRef;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.LocalRefVerify;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.MnpPort;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.NameAndValueObject;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.NameObject;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.Order;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.PayInfo;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.Product;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.ProductCafInfo;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.ProductIdentifier;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.ProductProof;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.Proof;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.ReferringCustomer;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request.ValueObject;


@Service("hw0012")
@Action(bizcode="hw0012",userCheck=true)
public class HW0012Action extends AbstractYDBaseActionHandler<HW0012Request, IBody> {

	@Autowired
	private AgentOrderService agentOrderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private YD0010Action yd0010;

	@Override
	protected void doAction() throws Exception 
	{
		IUserinfo ui = this.getUserinfo();
		User u = userService.loadUserByUserCode(ui.getUserName());
		AgentOrder ao = agentOrderService.loadAgentOrderByOrderCode(request.getOrderCode());
		if(ao == null)
			throw new Exception(request.getOrderCode()+": not found");
		
		if(StringUtils.isBlank(ao.getCafInfo()))
			throw new Exception("caf information not found");
		
		HW0010Request hw0010Request = mapper.readValue(ao.getCafInfo(), HW0010Request.class);
		YD0010Request yd0010Request = convertByHW0010(hw0010Request);
		YD0010Response yd0010Response = yd0010.post2Tibco(yd0010Request, null);
		ao.setTibcoSendFlag("1");
		agentOrderService.saveAgentOrder(ao);
		agentOrderService.completedOrder(ao.getOrderCode(), null);
	}
	
	public YD0010Request convertByHW0010(HW0010Request hw0010_req) throws Exception
	{
		if(hw0010_req.getCafInfos() == null || hw0010_req.getCafInfos().isEmpty())
			throw new Exception("no caf information");
		
		HW0010Request.CafInfo caf = hw0010_req.getCafInfos().get(0);
		
		if(caf.getOrder() == null)
			throw new Exception("no order information");
		
		YD0010Request req = new YD0010Request();
		
		req.setReferenceNumber(caf.getOrder().getOrn());
		req.setCustomerId(caf.getCustomer().getCustomerId());
		req.setOrderType(caf.getOrder().getOrderType());
		req.setCircleId(caf.getOrder().getCircleId());
		req.setAppointmentDateTimeFrom(caf.getOrder().getAppointmentDateTimeFrom());
		req.setAppointmentDateTimeTo(caf.getOrder().getAppointmentDateTimeTo());
		req.setChannel(caf.getOrder().getChannel());
		req.setDeliveryMode(caf.getOrder().getDeliveryMode());
		
		/********customerDetail 设置***********/
		req.setCustomerDetails(new Customer());
		req.getCustomerDetails().setProspectId(caf.getCustomer().getProspectId());
		req.getCustomerDetails().setCustomerCategory(caf.getCustomer().getCustomerCategory());
		req.getCustomerDetails().setSalutation(caf.getCustomer().getSalutation());
		req.getCustomerDetails().setFirstName(caf.getCustomer().getFirstName());
		req.getCustomerDetails().setMiddleName(caf.getCustomer().getMiddleName());
		req.getCustomerDetails().setLastName(caf.getCustomer().getLastName());
		
		/********FamilyContact 设置***********/
		req.getCustomerDetails().setFamilyContactDetails(new FamilyContact());
		req.getCustomerDetails().getFamilyContactDetails().setFirstName(caf.getCustomer().getFamilyFirstName());
		req.getCustomerDetails().getFamilyContactDetails().setMiddleName(caf.getCustomer().getFamilyMiddleName());
		req.getCustomerDetails().getFamilyContactDetails().setLastName(caf.getCustomer().getFamilyLastName());
		req.getCustomerDetails().getFamilyContactDetails().setRelationship(caf.getCustomer().getFamilyRelationship());
		
		req.getCustomerDetails().setDateOfBirth(caf.getCustomer().getDateOfBirth());
		req.getCustomerDetails().setGender(caf.getCustomer().getGender());
		req.getCustomerDetails().setNationality(caf.getCustomer().getNationality());
		if(caf.getCustomer().getForeignNational() != null)
		{
			req.getCustomerDetails().setPassportNo(caf.getCustomer().getForeignNational().getPassportNo());
			req.getCustomerDetails().setVisaNo(caf.getCustomer().getForeignNational().getVisaNo());
			req.getCustomerDetails().setVisaValidityDate(caf.getCustomer().getForeignNational().getVisaValidityDate());
		}
		
		/*********Contact*********/
		req.getCustomerDetails().setContactDetails(new Contact());
		req.getCustomerDetails().getContactDetails().setMobileNumber(caf.getCustomer().getMobileNumber());
		req.getCustomerDetails().getContactDetails().setAlternateContactNumberHome(caf.getCustomer().getAlternateContactNumberHome());
		req.getCustomerDetails().getContactDetails().setAlternateContactNumberWork(caf.getCustomer().getAlternateContactNumberWork());
		req.getCustomerDetails().getContactDetails().setEmailId(caf.getCustomer().getEmailId());
		
		/*********PermanentAddress*********/
		if(caf.getCustomer().getPermanentAddress() != null)
		{
			HW0010Request.Address address = caf.getCustomer().getPermanentAddress();
			req.getCustomerDetails().setPermanentAddress(new Address());
			req.getCustomerDetails().getPermanentAddress().setAddressId(address.getAddressId());
			req.getCustomerDetails().getPermanentAddress().setBuildingId(address.getBuildingId());
			req.getCustomerDetails().getPermanentAddress().setAddressType(address.getAddressType());
			req.getCustomerDetails().getPermanentAddress().setCareOf(address.getCareOf());
			req.getCustomerDetails().getPermanentAddress().setHouseNameORNumber(address.getHouseNameORNumber());
			req.getCustomerDetails().getPermanentAddress().setBuildingNameORNumber(address.getBuildingNameORNumber());
			req.getCustomerDetails().getPermanentAddress().setSocietyName(address.getSocietyName());
			req.getCustomerDetails().getPermanentAddress().setStreetNameORNumber(address.getStreetNameORNumber());
			req.getCustomerDetails().getPermanentAddress().setLandmark(address.getLandmark());
			req.getCustomerDetails().getPermanentAddress().setSubLocality(address.getSubLocality());
			req.getCustomerDetails().getPermanentAddress().setAreaORTehsil(address.getAreaORTehsil());
			req.getCustomerDetails().getPermanentAddress().setPincode(address.getPincode());
			req.getCustomerDetails().getPermanentAddress().setVillageORCity(address.getVillageORCity());
			req.getCustomerDetails().getPermanentAddress().setDistrict(address.getDistrict());
			req.getCustomerDetails().getPermanentAddress().setState(address.getState());
			req.getCustomerDetails().getPermanentAddress().setCountry(address.getCountry());
			req.getCustomerDetails().getPermanentAddress().setTotalFloors(address.getTotalFloors());
			req.getCustomerDetails().getPermanentAddress().setJioCentreId(address.getJioCentreId());
		}
		
		req.getCustomerDetails().setPanNumber(caf.getCustomer().getPanNumber());
		req.getCustomerDetails().setPreferredLanguage(caf.getCustomer().getPreferredLanguage());
		req.getCustomerDetails().setPreferredCommunicationChannel(caf.getCustomer().getPreferredCommunicationChannel());
		req.getCustomerDetails().setAadhaarNumber(caf.getCustomer().getAadhaarNumber());
		req.getCustomerDetails().setMaritalStatus(caf.getCustomer().getMaritalStatus());
		req.getCustomerDetails().setAnniversaryDate(caf.getCustomer().getAnniversaryDate());
		req.getCustomerDetails().setOccupation(caf.getCustomer().getOccupation());
		req.getCustomerDetails().setOccupationDescription(caf.getCustomer().getOccupationDescription());
		req.getCustomerDetails().setTypeOfHouse(caf.getCustomer().getTypeOfHouse());
		req.getCustomerDetails().setCustomerPictureURL(caf.getCustomer().getCustomerPictureURL());
		
		/*******************PaymentDetails****************************/
		
		if(caf.getPayInfo() != null)
		{
			HW0010Request.PayInfo payInfo = caf.getPayInfo();
			req.setPaymentDetails(new PayInfo());
			req.getPaymentDetails().setModeOfPayment(payInfo.getModeOfPayment());
			req.getPaymentDetails().setPaymentInstrumentNumber(payInfo.getPaymentInstrumentNumber());
			req.getPaymentDetails().setPaymentInstrumentDate(payInfo.getPaymentInstrumentDate());
			req.getPaymentDetails().setBankName(payInfo.getBankName());
			req.getPaymentDetails().setBranchNameAndAddress(payInfo.getBranchNameAndAddress());
			req.getPaymentDetails().setReceiptNumber(payInfo.getReceiptNumber());
			req.getPaymentDetails().setCpTransactionId(payInfo.getCpTransactionId());
		}
		
		/********************InstallationAddress************************/
		if(caf.getCustomer().getPresentAddress() != null)
		{
			HW0010Request.Address address = caf.getCustomer().getPresentAddress();
			req.setInstallationAddress(new ArrayList<YD0010Request.Address>(0));
			Address install_address = new Address();
			install_address.setAddressId(address.getAddressId());
			install_address.setBuildingId(address.getBuildingId());
			install_address.setAddressType(address.getAddressType());
			install_address.setCareOf(address.getCareOf());
			install_address.setHouseNameORNumber(address.getHouseNameORNumber());
			install_address.setBuildingNameORNumber(address.getBuildingNameORNumber());
			install_address.setSocietyName(address.getSocietyName());
			install_address.setStreetNameORNumber(address.getStreetNameORNumber());
			install_address.setLandmark(address.getLandmark());
			install_address.setSubLocality(address.getSubLocality());
			install_address.setAreaORTehsil(address.getAreaORTehsil());
			install_address.setPincode(address.getPincode());
			install_address.setVillageORCity(address.getVillageORCity());
			install_address.setDistrict(address.getDistrict());
			install_address.setState(address.getState());
			install_address.setCountry(address.getCountry());
			install_address.setTotalFloors(address.getTotalFloors());
			install_address.setJioCentreId(address.getJioCentreId());
			req.getInstallationAddress().add(install_address);
		}
		
		
		/********************cafDetails************************/
		req.setCafDetails(new CafInfo());
		req.getCafDetails().setMerchantCode(caf.getMerchantCode());
		req.getCafDetails().setPosAgentCode(caf.getPosAgentCode());
		req.getCafDetails().setPosAgentSignatureDate(caf.getPosAgentSignatureDate());
		req.getCafDetails().setCustomerDeclarationPlace(caf.getCustomerDeclarationPlace());
		req.getCafDetails().setCustomerDeclarationDate(caf.getCustomerDeclarationDate());
		
		req.getCafDetails().setProofs(new ArrayList<YD0010Request.Proof>(0));
		if(caf.getPoi() != null)
		{
			Proof poi = new Proof();
			poi.setProofIdentifier(caf.getPoi().getProofIdentifier());
			poi.setIdProofType(caf.getPoi().getIdProofType());
			poi.setDocumentNumber(caf.getPoi().getDocumentNumber());
			poi.setDateOfIssue(caf.getPoi().getDateOfIssue());
			poi.setPlaceOfIssue(caf.getPoi().getPlaceOfIssue());
			poi.setIssuingAuthority(caf.getPoi().getIssuingAuthority());
			poi.setIdentifierURL(caf.getPoi().getIdentifierURL());
			poi.setAadhaarTransactionRefNo(caf.getPoi().getAadhaarTransactionRefNo());
			req.getCafDetails().getProofs().add(poi);
		}
		
		if(caf.getPoa() != null)
		{
			Proof poa = new Proof();
			poa.setProofIdentifier(caf.getPoa().getProofIdentifier());
			poa.setIdProofType(caf.getPoa().getIdProofType());
			poa.setDocumentNumber(caf.getPoa().getDocumentNumber());
			poa.setDateOfIssue(caf.getPoa().getDateOfIssue());
			poa.setPlaceOfIssue(caf.getPoa().getPlaceOfIssue());
			poa.setIssuingAuthority(caf.getPoa().getIssuingAuthority());
			poa.setIdentifierURL(caf.getPoa().getIdentifierURL());
			poa.setAadhaarTransactionRefNo(caf.getPoa().getAadhaarTransactionRefNo());
			req.getCafDetails().getProofs().add(poa);
		}
		
		req.getCafDetails().setCurrentMobileConnections(new ArrayList<YD0010Request.Connection>(0));
		Connection conn = new Connection();
		conn.setOperatorName(caf.getOperatorName());
		conn.setNoOfConnections(caf.getNoOfConnections());
		req.getCafDetails().getCurrentMobileConnections().add(conn);
		
		/***Form61***/
		if(caf.getForm61() != null)
		{
			req.getCafDetails().setForm61Details(new Form61());
			req.getCafDetails().getForm61Details().setLastTaxReturnFiled(caf.getForm61().getLastTaxReturnFiled());
			req.getCafDetails().getForm61Details().setReasonForNoPAN(caf.getForm61().getReasonForNoPAN());
		}
		
		if(caf.getLocalReferenceDetails() != null)
		{
			HW0010Request.LocalRef lr = caf.getLocalReferenceDetails();
			req.getCafDetails().setLocalReferenceDetails(new LocalRef());
			req.getCafDetails().getLocalReferenceDetails().setFirstName(lr.getFirstName());
			req.getCafDetails().getLocalReferenceDetails().setMiddleName(lr.getMiddleName());
			req.getCafDetails().getLocalReferenceDetails().setLastName(lr.getLastName());
			req.getCafDetails().getLocalReferenceDetails().setContactNumber(lr.getContactNumber());
			req.getCafDetails().getLocalReferenceDetails().seteMailId(lr.geteMailId());
			if(lr.getAddress() != null)
			{
				HW0010Request.Address hw_lr_address = lr.getAddress();
				YD0010Request.Address yd_lr_address = new Address();
				req.getCafDetails().getLocalReferenceDetails().setAddress(yd_lr_address);
				yd_lr_address.setAddressId(hw_lr_address.getAddressId());
				yd_lr_address.setBuildingId(hw_lr_address.getBuildingId());
				yd_lr_address.setAddressType(hw_lr_address.getAddressType());
				yd_lr_address.setCareOf(hw_lr_address.getCareOf());
				yd_lr_address.setHouseNameORNumber(hw_lr_address.getHouseNameORNumber());
				yd_lr_address.setBuildingNameORNumber(hw_lr_address.getBuildingNameORNumber());
				yd_lr_address.setSocietyName(hw_lr_address.getSocietyName());
				yd_lr_address.setStreetNameORNumber(hw_lr_address.getStreetNameORNumber());
				yd_lr_address.setLandmark(hw_lr_address.getLandmark());
				yd_lr_address.setSubLocality(hw_lr_address.getSubLocality());
				yd_lr_address.setAreaORTehsil(hw_lr_address.getAreaORTehsil());
				yd_lr_address.setPincode(hw_lr_address.getPincode());
				yd_lr_address.setVillageORCity(hw_lr_address.getVillageORCity());
				yd_lr_address.setDistrict(hw_lr_address.getDistrict());
				yd_lr_address.setState(hw_lr_address.getState());
				yd_lr_address.setCountry(hw_lr_address.getCountry());
				yd_lr_address.setTotalFloors(hw_lr_address.getTotalFloors());
				yd_lr_address.setJioCentreId(hw_lr_address.getJioCentreId());
			}
		}

		/****ReferringCustomer****/
		req.getCafDetails().setReferringCustomerDetails(new ReferringCustomer());
		req.getCafDetails().getReferringCustomerDetails().setId(caf.getReferringCustomerId());
		req.getCafDetails().getReferringCustomerDetails().setReferenceMobileNumber(caf.getReferenceMobileNumber());
		
		if(caf.getLocalReferenceDetails() != null)
		{
			req.getCafDetails().setLocalReferenceVerification(new LocalRefVerify());
			req.getCafDetails().getLocalReferenceVerification().setCallingPartyNumber(caf.getLocalReferenceDetails().getCallingPartyNumber());
		}
		/*************************orderDetails*******************************/
		if(caf.getOrder() != null)
		{
			HW0010Request.Order hw_order = caf.getOrder();
			req.setOrderDetails(new ArrayList<YD0010Request.Order>(0));
			Order order = new Order();
			req.getOrderDetails().add(order);
			order.setBusinessInteraction(new NameObject(hw_order.getBusinessInteraction()));
			order.setAccountId(hw_order.getAccountId());
			order.setOfferId(hw_order.getOfferId());
			order.seteWalletReservationReferenceId(hw_order.geteWalletReservationReferenceId());
			order.setPlanOffering(new IdObject(hw_order.getPlanOffering()));
			
			if(hw_order.getBillingAddress() != null)
			{
				order.setBillingAddress(new Address());
				order.getBillingAddress().setAddressId(hw_order.getBillingAddress().getAddressId());
				order.getBillingAddress().setBuildingId(hw_order.getBillingAddress().getBuildingId());
				order.getBillingAddress().setAddressType(hw_order.getBillingAddress().getAddressType());
				order.getBillingAddress().setCareOf(hw_order.getBillingAddress().getCareOf());
				order.getBillingAddress().setHouseNameORNumber(hw_order.getBillingAddress().getHouseNameORNumber());
				order.getBillingAddress().setBuildingNameORNumber(hw_order.getBillingAddress().getBuildingNameORNumber());
				order.getBillingAddress().setSocietyName(hw_order.getBillingAddress().getSocietyName());
				order.getBillingAddress().setStreetNameORNumber(hw_order.getBillingAddress().getStreetNameORNumber());
				order.getBillingAddress().setLandmark(hw_order.getBillingAddress().getLandmark());
				order.getBillingAddress().setSubLocality(hw_order.getBillingAddress().getSubLocality());
				order.getBillingAddress().setAreaORTehsil(hw_order.getBillingAddress().getAreaORTehsil());
				order.getBillingAddress().setPincode(hw_order.getBillingAddress().getPincode());
				order.getBillingAddress().setVillageORCity(hw_order.getBillingAddress().getVillageORCity());
				order.getBillingAddress().setDistrict(hw_order.getBillingAddress().getDistrict());
				order.getBillingAddress().setState(hw_order.getBillingAddress().getState());
				order.getBillingAddress().setCountry(hw_order.getBillingAddress().getCountry());
				order.getBillingAddress().setTotalFloors(hw_order.getBillingAddress().getTotalFloors());
				order.getBillingAddress().setJioCentreId(hw_order.getBillingAddress().getJioCentreId());
			}
			
			if(order.getProducts() != null && order.getProducts().isEmpty() == false)
			{
				
//				for(HW0010Request.order.getProducts())
				order.setProducts(new ArrayList<YD0010Request.Product>(0));
				Product order_p1 = new Product();
				order.getProducts().add(order_p1);
				order_p1.setBusinessInteraction(new NameObject("OFFER_CHANGE"));
				order_p1.setProductId("P30001");
				order_p1.setStarterKitCode("");
				
				List<NameAndValueObject> chs = new ArrayList<YD0010Request.NameAndValueObject>(0);
				order_p1.setCharacteristics(chs);
				NameAndValueObject nv1 = new NameAndValueObject("DND_PREFERENCE","");
				chs.add(nv1);
				
				Dependancy dependancy = new Dependancy();
				order_p1.setDependancyInfo(dependancy);
				List<ValueObject> dependancy_identifier= new ArrayList<YD0010Request.ValueObject>(0);
				dependancy.setIdentifier(dependancy_identifier);
				ValueObject di1 = new ValueObject("DND_PREFERENCE");
				dependancy_identifier.add(di1);
				
				order_p1.setCafDetails(new ProductCafInfo("", ""));
				
				List<ProductProof> p_proofs = new ArrayList<YD0010Request.ProductProof>(0);
				order_p1.setProofs(p_proofs);
				ProductProof p_p1 = new ProductProof("POA", "http://sidcdevotas.in.ril.com:8080/archive?get&pVersion=0045&contRep=CAF&docId=OT00000001UW_118864_1_181824&ixUser=SIDCDEVOTAS&ixAppl=libdsh");
				p_proofs.add(p_p1);
				ProductProof p_p2 = new ProductProof("POI", "http://sidcdevotas.in.ril.com:8080/archive?get&pVersion=0045&contRep=CAF&docId=OT00000001UW_118864_1_181824&ixUser=SIDCDEVOTAS&ixAppl=libdsh");
				p_proofs.add(p_p2);
				
				MnpPort mp = new MnpPort();
				order_p1.setMnpPortDetails(mp);
				mp.setUniquePortingCode("");
				mp.setUpcGenerationDate("");
				mp.setExistingOperatorCode("");
				mp.setExistingSubscriberType("");
				mp.setLastPaidBillReceiptURI("");
				
				List<ProductIdentifier> pi_list = new ArrayList<YD0010Request.ProductIdentifier>(0);
				order_p1.setIdentifier(pi_list);
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
				List<NameAndValueObject> id_list1 = new ArrayList<YD0010Request.NameAndValueObject>(0);
				d1.setIdentifier(id_list1);
				id_list1.add(new NameAndValueObject("",""));
				
				Device d2 = new Device();
				devices.add(d2);
				d2.setBusinessInteraction(new NameObject("OFFER_CHANGE"));
				d2.setProductId("DEV100002");
				d2.setBoqType("ODU");
				List<NameAndValueObject> id_list2 = new ArrayList<YD0010Request.NameAndValueObject>(0);
				d2.setIdentifier(id_list2);
				id_list2.add(new NameAndValueObject("",""));
				
				List<FacingService> customerFacingServices = new ArrayList<YD0010Request.FacingService>(0);
				order_p1.setCustomerFacingServices(customerFacingServices);
				/// 1 freature
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
				
				/// 2 freature
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
				
				/// 3 freature
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
			}
		
		}
		
		
		return null;
	}
	
	
	
}
