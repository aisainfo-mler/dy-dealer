package com.ailk.yd.mapp.tibco.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ailk.yd.mapp.client.model.TibcoAccount;
import com.ailk.yd.mapp.client.model.TibcoService;
import com.ailk.yd.mapp.tibco.model.YD0021.YD0021Request;
import com.ailk.yd.mapp.tibco.model.YD0021.YD0021Response;

@Service("yd0021")
public class YD0021Action extends AbstractTibcoService<YD0021Request, YD0021Response> {

	@Value("${yd0021_url}")
	private String url;
	@Override
	protected YD0021Response convertResponse(String json) throws Exception {
		YD0021Response res = new YD0021Response();
		JSONObject jo =  JSONObject.fromObject(json);
//		if(jo.get("success")!=null){
//			//必然是出错了
//			String errMsg = TibcoUtil.findErrMsg(jo);
//			throw new Exception(errMsg);
//		}
		String customerId = jo.getString("customerId");
		res.setCustomerId(customerId);
		List accounts = new ArrayList();
		res.setAccounts(accounts);
		
		JSONArray acts = jo.getJSONArray("accounts");
		for(int i=0;i<acts.size();i++){
			JSONObject accountNode = (JSONObject) acts.get(i);
			TibcoAccount acc = new TibcoAccount();
			accounts.add(acc);
			acc.setCompanyCode(accountNode.getString("companyCode"));
			acc.setPrepaidAccountId(accountNode.getString("prepaidAccountId"));
			List services = new ArrayList();
			acc.setServices(services);
			JSONArray servicePackageNode = accountNode.getJSONArray("servicePackage");
			for(int j=0;j<servicePackageNode.size();j++){
				JSONArray servicesNode = ((JSONObject) servicePackageNode.get(j)).getJSONArray("services");
				for(int k=0;k<servicesNode.size();k++){
					//获取service的记录。这里可能需要改
					JSONObject service = (JSONObject) servicesNode.get(k);
					JSONObject idObj = service.getJSONObject("identifier");
					TibcoService s = new TibcoService();
					s.setProductCode(service.getString("productCode"));
					s.setProductName(service.getString("productName"));
					s.setSericeName(idObj.getString("name"));
					s.setServiceId(idObj.getString("value"));
					s.setServiceType(idObj.getString("type"));
					services.add(s);
				}
			}
		}
		return res;
	}

	@Override
	protected String convertRequest(YD0021Request request) throws Exception {
		// 
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		YD0021Action yd21 = new YD0021Action();
		
		YD0021Request r = new YD0021Request();
		r.setCustomerId("1100009566");
		System.err.println(r.returnGetParam());
		
		
		
		String success="{ \"customerId\": \"1100009566\", \"accounts\": [ { \"prepaidAccountId\": \"001000009282\", \"companyCode\": \"TIBX\", \"circleId\": \"TC\", \"accountType\": \"\", \"servicePackage\": [ { \"packageCode\": \"OC401\", \"packageName\": \"VOLTE BASIC OFFER\", \"services\": [ { \"productCode\": \"P10016\", \"productName\": \"VVM VOLTE\", \"identifier\": { \"name\": \"SERVICE_NAME\", \"type\": \"GPRS\", \"value\": \"3334002189\", \"category\": \"4\", \"subCategory\": \"2\" }, \"serviceContractReferenceNo\": \"\", \"serviceStatus\": \"CMPD\", \"statusDescription\": \"SERVICE_STATUS_DESC\", \"serviceAlias\": \"SERVICE_\", \"activationDate\": \"2014-07-11\", \"dependancyInfo\": { \"parentProductId\": \"ParentID\", \"identifier\": [ { \"name\": \"SERVICE_NAME\", \"value\": \"1234\", \"category\": \"4\", \"subcategory\": \"\", \"type\": \"GPRS\" } ] }, \"action\":[ ], \"characteristics\": [ { \"name\": \"Reason\", \"value\": \"DND\" } , { \"name\": \"Reason Description\", \"value\": \"REASON_DESC\" } ], \"associatedUsers\": [ ], \"customerFacingServices\": [ { \"serviceId\": \"S40002\", \"serviceName\": \"VOLTE-DATA\", \"serviceType\": \"\", \"identifier\": { \"name\": \"IMSI\", \"value\": \"600000000006535\", \"category\": \"2\", \"subcategory\": \"\", \"type\": \"\" } } , { \"serviceId\": \"S40001\", \"serviceName\": \"VOICE VIDEO MESSAGING\", \"serviceType\": \"\", \"identifier\": { \"name\": \"R4GID\", \"value\": \"001900009190\", \"category\": \"2\", \"subcategory\": \"\", \"type\": \"\" } } ] } ] } ] } , { \"prepaidAccountId\": \"001900009190\", \"companyCode\": \"\", \"circleId\": \"TC\", \"accountType\": \"\", \"servicePackage\": [ { \"packageCode\": \"OC401\", \"packageName\": \"VOLTE BASIC OFFER\", \"services\": [ { \"productCode\": \"P10016\", \"productName\": \"VVM VOLTE\", \"identifier\": { \"name\": \"\", \"type\": \"\", \"value\": \"9334002189\", \"category\": \"4\", \"subCategory\": \"2\" }, \"serviceContractReferenceNo\": \"\", \"serviceStatus\": \"Z020\", \"statusDescription\": \"\", \"serviceAlias\": \"\", \"activationDate\": \"\", \"dependancyInfo\": { \"parentProductId\": \"\", \"identifier\": [ ] }, \"action\":[ ], \"characteristics\": [ ], \"associatedUsers\": [ ], \"customerFacingServices\": [ { \"serviceId\": \"S40002\", \"serviceName\": \"VOLTE-DATA\", \"serviceType\": \"\", \"identifier\": { \"name\": \"IMSI\", \"value\": \"600000000006535\", \"category\": \"2\", \"subcategory\": \"\", \"type\": \"\" } } , { \"serviceId\": \"S40001\", \"serviceName\": \"VOICE VIDEO MESSAGING\", \"serviceType\": \"\", \"identifier\": { \"name\": \"R4GID\", \"value\": \"001900009190\", \"category\": \"2\", \"subcategory\": \"\", \"type\": \"\" } } ] } ] } ] } ] }";
		String err = "{ \"success\": false, \"general_message\": \"This is an operation implementation generated fault\", \"errors\": { \"retrieveCustomerServiceConfigurationException\": \"RIL4G-CustomerProfileManagement-9999\" } }";
		yd21.convertResponse(success);
		yd21.convertResponse(err);
		
		
	}

	@Override
	public String getTibcoUrl() {
		// TODO Auto-generated method stub
		return url;
	}
	

}
