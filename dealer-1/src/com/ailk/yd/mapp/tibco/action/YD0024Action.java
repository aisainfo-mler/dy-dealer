package com.ailk.yd.mapp.tibco.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ailk.yd.mapp.tibco.model.YD0022.YD0022Response.ContactDetails;
import com.ailk.yd.mapp.tibco.model.YD0022.YD0022Response.FamilyContactDetails;
import com.ailk.yd.mapp.tibco.model.YD0022.YD0022Response.PermanentAddress;
import com.ailk.yd.mapp.tibco.model.YD0024.YD0024Request;
import com.ailk.yd.mapp.tibco.model.YD0024.YD0024Response;
import com.ailk.yd.mapp.tibco.model.YD0024.YD0024Response.Address;
import com.ailk.yd.mapp.tibco.model.YD0024.YD0024Response.Caf;
import com.ailk.yd.mapp.tibco.model.YD0024.YD0024Response.Contact;
import com.ailk.yd.mapp.tibco.model.YD0024.YD0024Response.Customer;
import com.ailk.yd.mapp.tibco.model.YD0024.YD0024Response.FamilyContact;
import com.ailk.yd.mapp.tibco.model.YD0024.YD0024Response.OrderDetail;
import com.ailk.yd.mapp.tibco.model.YD0024.YD0024Response.Product;
import com.ailk.yd.mapp.tibco.model.YD0024.YD0024Response.Proof;
import com.ailk.yd.mapp.tibco.util.TibcoUtil;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-8-12 下午07:43:15
 * 类说明:采用get方法
 */
@Service("yd0024")
public class YD0024Action extends AbstractTibcoService<YD0024Request, YD0024Response> {
	@Value("${yd0010_url}")
	private String url;
	@Override
	protected YD0024Response convertResponse(String json) throws Exception {
		if(StringUtils.isBlank(json))
			throw new Exception("tibco response json is null");
		
		Map<String,?> rsp_map = mapper.readValue(json, Map.class);
		YD0024Response rsp = new YD0024Response();
		rsp.setOrderNumber((String)rsp_map.get("orderNumber"));
		rsp.setCustomerId((String)rsp_map.get("customerId"));
		rsp.setReferenceNumber((String)rsp_map.get("referenceNumber"));
		rsp.setOrderType((String)rsp_map.get("orderType"));
		rsp.setOrderStatus((String)rsp_map.get("orderStatus"));
		
		Map cafDetailsMap = (Map)rsp_map.get("cafDetails");
		List<Map> proofsMap  = (List<Map>)cafDetailsMap.get("proofs");
		List<Proof> proofs = new ArrayList<Proof>();
		Proof proof = null;
		if(proofsMap != null && proofsMap.isEmpty() == false){
			 for(Map proofMap:proofsMap){
				 proof = (Proof)TibcoUtil.extractStrValClass(proofMap, Proof.class);
				 proof.setIdentifierURL(changeUrl(proof.getIdentifierURL()));
				 proofs.add(proof);
			 }
		}
		Caf caf = (Caf) TibcoUtil.extractStrValClass(cafDetailsMap, Caf.class);
		caf.setProofs(proofs);
		rsp.setCafDetails(caf);
		
		List<Map> orderDetailsMap = (List<Map>)rsp_map.get("orderDetails");
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		OrderDetail tmpD = null;
		if(orderDetailsMap != null && orderDetailsMap.isEmpty() == false){
			 for(Map orderDetailMap:orderDetailsMap){
				 tmpD = (OrderDetail)TibcoUtil.extractStrValClass(orderDetailMap, OrderDetail.class);
				 List<Map> productsMap = (List<Map>)orderDetailMap.get("products");
				 List<Product> products = new ArrayList<Product>();
				 if(productsMap != null && productsMap.isEmpty() == false){
					 for(Map productMap:productsMap){
						 Product tmpP = (Product)TibcoUtil.extractStrValClass(productMap, Product.class);
						 if(productMap.containsKey("cafDetails") && productMap.get("cafDetails") != null){
							 tmpP.setCafDetails((Caf)TibcoUtil.extractStrValClass(productMap.get("cafDetails"), Caf.class));
							 if(StringUtils.isNotEmpty(tmpP.getCafDetails().getCafURI())){
								 tmpP.getCafDetails().setCafURI(changeUrl(tmpP.getCafDetails().getCafURI()));
							 }
						 }
						 products.add(tmpP);
					 }
					 tmpD.setProducts(products);
				 }
				 orderDetails.add(tmpD);
			 }
		}
//		List<Map> orderDetailsMap = (List<Map>)rsp_map.get("orderDetails");
		rsp.setOrderDetails(orderDetails);
		rsp.setOrderEntryDateAndTimeStamp((String)rsp_map.get("orderEntryDateAndTimeStamp"));
		
		Map customerDetailsMap = (Map)rsp_map.get("customerDetails");
		if(customerDetailsMap != null && customerDetailsMap.isEmpty() == false){
			rsp.setCustomerDetails((Customer)TibcoUtil.extractStrValClass(customerDetailsMap, Customer.class));
			rsp.getCustomerDetails().setFamilyContactDetails((FamilyContact)TibcoUtil.extractStrValClass((Map)customerDetailsMap.get("familyContactDetails"), FamilyContact.class));
			rsp.getCustomerDetails().setContactDetails((Contact)TibcoUtil.extractStrValClass((Map)customerDetailsMap.get("contactDetails"), Contact.class));
			rsp.getCustomerDetails().setPermanentAddress((Address)TibcoUtil.extractStrValClass((Map)customerDetailsMap.get("permanentAddress"), Address.class));
		}
		return rsp;
	}

	@Override
	public String getTibcoUrl() {
		return url;
	}

	
	public String changeUrl(String orginal){
		String realUrl = orginal.replaceAll("&#38;", "&").replaceAll("&amp;", "&");
		return realUrl;
	}
}
