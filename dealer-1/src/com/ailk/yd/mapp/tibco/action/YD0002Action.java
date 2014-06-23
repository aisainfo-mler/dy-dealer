package com.ailk.yd.mapp.tibco.action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ailk.yd.mapp.tibco.TibcoConstant;
import com.ailk.yd.mapp.tibco.model.YD0002.YD0002Request;
import com.ailk.yd.mapp.tibco.model.YD0002.YD0002Response;

@Service("yd0002")
public class YD0002Action extends
		AbstractTibcoService<YD0002Request, YD0002Response> {

	
	@Value("${yd0002_url}")
	private String url;
	@Override
	protected YD0002Response convertResponse(String json) throws Exception {
		// TODO Auto-generated method stub
		JSONObject jo = JSONObject.fromObject(json);
		String orn = (String) jo.get("serviceProviderEmployee");
		String resStatus = (String) jo.getJSONObject("response").get(
				"interactionStatus");
		String msg = (String) jo.get("message");
		if (StringUtils.isBlank(orn)
				&& StringUtils.equals(
						TibcoConstant.SELECT_SPEC_NUM_STATUS_SUCC, resStatus)) {
			// 调用成功
		} else if (StringUtils.isNotBlank(orn)
				&& StringUtils.equals(
						TibcoConstant.SELECT_SPEC_NUM_STATUS_SUCC, resStatus)) {
			// 已经被预占了
			throw new Exception("Number has blocked by order: " + orn);
		} else if (StringUtils.isNotBlank(msg)) {
			throw new Exception(msg);
		}

		return null;
	}

	@Override
	protected String convertRequest(YD0002Request request) throws Exception {
		
		return new ObjectMapper().writeValueAsString(request);
//		JSONObject jo = new JSONObject();
//		JSONObject orn = new JSONObject();
//		orn.put("employeeId", request.getOrn());
//		jo.put("serviceProviderEmployee", orn);
//
//		// JSONObject business = new JSONObject();
//		// business.put("name", "SelfCare");//待定是否需要变
//		// jo.put("businessChannelInteraction", business);
//
//		JSONArray numberList = new JSONArray();
//		JSONObject num = new JSONObject();
//		num.put("value", request.getMdn());
//		// num.put("name", "TN");//待定。什么含义
//		num.put("type", "MSISDN");
//		numberList.add(num);
//		jo.put("numberList", numberList);
//		return jo.toString();
	}

	public static void main(String[] args) throws Exception {
		YD0002Action y2a = new YD0002Action();
		YD0002Request r = new YD0002Request();
		r.setSvcNum("1111111111");
		r.setOrderNumber("OT00000002MA");
		String cr = y2a.convertRequest(r);
		System.err.println(cr);

		String fail = "{ \"serviceProviderEmployee\": \"\", \"commandArgument\":{ \"name\":\"\", \"value\":\"\"}, \"customerAccountIdentifier\": { \"value\": \"\", \"name\": \"\", \"type\": \"\", \"status\": [ ] }, \"response\": { \"interactionStatus\": \"2\" }, \"message\": \"The Number 334000258 is invalid according to the Service ID Parsing Rules for Type TN and Subtype MSISDN.\" }";
		String hasBlocked = "{ \"serviceProviderEmployee\": \"2\", \"commandArgument\":{ \"name\":\"\", \"value\":\"\"}, \"customerAccountIdentifier\": { \"value\": \"1111111112\", \"name\": \"TN\", \"type\": \"MSISDN\", \"status\": [ ] }, \"response\": { \"interactionStatus\": \"0\" }, \"message\": \"\" }";
		String succ = "{ \"serviceProviderEmployee\": \"\", \"commandArgument\":{ \"name\":\"\", \"value\":\"\"}, \"customerAccountIdentifier\": { \"value\": \"3334000259\", \"name\": \"TN\", \"type\": \"MSISDN\", \"status\": [ ] }, \"response\": { \"interactionStatus\": \"0\" }, \"message\": \"\" }";
		System.err.println(y2a.convertResponse(succ));
		// System.err.println(y2a.convertResponse(fail));
		// System.err.println(y2a.convertResponse(hasBlocked));

	}

	@Override
	public String getTibcoUrl() {
		// TODO Auto-generated method stub
		return url;
	}

}
