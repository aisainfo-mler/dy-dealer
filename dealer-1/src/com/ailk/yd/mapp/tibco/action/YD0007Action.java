package com.ailk.yd.mapp.tibco.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ailk.yd.mapp.tibco.model.YD0007.YD0007Request;
import com.ailk.yd.mapp.tibco.model.YD0007.YD0007Response;
import com.ailk.yd.mapp.tibco.util.TibcoUtil;

@Service("yd0007")
public class YD0007Action extends
		AbstractTibcoService<YD0007Request, YD0007Response> {

	@Value("${yd0007_url}")
	private String url;
	@Override
	protected YD0007Response convertResponse(String json) throws Exception {
		YD0007Response rm = new YD0007Response();
		JSONObject jo = JSONObject.fromObject(json);
//		if (jo.get("success") != null) {
//			// 必然是失败
//			String errMsg = TibcoUtil.findErrMsg(jo);
//			throw new Exception(errMsg);
//		}
		String tmp = (String) jo.get("totalRecords");
		Integer totalRecords = Integer.parseInt(tmp);
		rm.setTotalRecords(totalRecords);

		JSONArray customers = jo.getJSONArray("customers");
		List<YD0007Response.Customer> j = new ArrayList();
		rm.setCustomers(j);
		for (int i = 0; i < customers.size(); i++) {
			YD0007Response.Customer c = new YD0007Response.Customer();
			JSONObject obj = (JSONObject) customers.get(i);
			JSONObject cd = obj.getJSONObject("contactDetails");
			JSONObject pd = obj.getJSONObject("personalDetails");
			c.setCustomerId(obj.getString("customerId"));
			c.setCustomerPictureURL(obj.getString("customerPictureURL"));
			c.setCustomerStatus(obj.getString("customerStatus"));
			c.setDisplayName(obj.getString("displayName"));
			c.setMobileNumber(cd.getString("mobileNumber"));
			c.setEmailId(cd.getString("emailId"));
			c.setIsBlacklisted(obj.getString("isBlacklisted"));
			c.setPersonalDetails(obj.getString("displayName"));
			String middleName = StringUtils.isBlank(pd.getString("middleName")) ? ""
					: (" " + pd.getString("middleName"));
			c.setPersonalDetails(pd.getString("salutation") + " "
					+ pd.getString("firstName") + middleName + " "
					+ pd.getString("lastName"));
			j.add(c);
		}
		return rm;
	}

	

	@Override
	protected String convertRequest(YD0007Request request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) throws Exception {
		YD0007Action yd7 = new YD0007Action();
		YD0007Request r = new YD0007Request();
		r.setEmailid("jjj@gmail.com");
		r.setMdn("9819253878");
		r.setPage(0);
		r.setSize(10);
		System.err.println(r.returnGetParam());

		// 越界了
		String yueJie = "{ \"success\": false, \"general_message\": \"This is an operation implementation generated fault\", \"errors\": { \"findCustomerProfileException\": \"RIL4G-B-MDM_FindCustomer-NO-DATANo Data Found in MDM For the given Request\" } }";

		String success = "{ \"totalRecords\": \"20\", \"customers\": [ { \"customerId\": \"\", \"customerStatus\": \"ACTIVE\", \"personalDetails\": { \"salutation\": \"\", \"firstName\": \"\", \"middleName\": \"\", \"lastName\": \"\" }, \"contactDetails\": { \"mobileNumber\": \"+919819253878\", \"emailId\": \"\" }, \"customerPictureURL\": \"\", \"isBlacklisted\": \"\", \"roles\":[ ], \"userId\":\"\", \"displayName\":\"c22c26fb-293c-4a5a-a23c-0a064d99174e\" }, { \"customerId\": \"1100009647\", \"customerStatus\": \"E0011\", \"personalDetails\": { \"salutation\": \"\", \"firstName\": \"Jayshil\", \"middleName\": \"\", \"lastName\": \"Dave\" }, \"contactDetails\": { \"mobileNumber\": \"9819253878\", \"emailId\": \"jayshildave@gmail.com\" }, \"customerPictureURL\": \"\", \"isBlacklisted\": \"1\", \"roles\":[ \"BUP002\" ], \"userId\":\"\", \"displayName\":\"\" } ] }";
		yd7.convertResponse(success);
		yd7.convertResponse(yueJie);

	}



	@Override
	public String getTibcoUrl() {
		// TODO Auto-generated method stub
		return url;
	}

}
