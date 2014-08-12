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
import com.ailk.yd.mapp.tibco.model.YD0024.YD0024Response.Caf;
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
		if(proofsMap != null && proofsMap.isEmpty() == false){
			 for(Map proofMap:proofsMap){
				 proofs.add((Proof)TibcoUtil.extractStrValClass(proofMap, Proof.class));
			 }
		}
		Caf caf = (Caf) TibcoUtil.extractStrValClass(cafDetailsMap, Caf.class);
		caf.setProofs(proofs);
		rsp.setCafDetails(caf);
		
		List<Map> orderDetailsMap = (List<Map>)rsp_map.get("orderDetails");
//		List<Map> orderDetailsMap = (List<Map>)rsp_map.get("orderDetails");
		
		rsp.setOrderEntryDateAndTimeStamp((String)rsp_map.get("orderEntryDateAndTimeStamp"));
		
		return rsp;
	}

	@Override
	public String getTibcoUrl() {
		return url;
	}

}
