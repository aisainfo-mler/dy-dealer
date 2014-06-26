package com.ailk.yd.mapp.tibco.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ailk.yd.mapp.tibco.model.YdPage;
import com.ailk.yd.mapp.tibco.model.YD0008.YD0008Request;
import com.ailk.yd.mapp.tibco.model.YD0008.YD0008Response;
@Service("yd0008")
public class YD0008Action extends AbstractTibcoService<YD0008Request, YD0008Response> {

	
	@Value("${yd0008_url}")
	private String url;
	
	@Override
	protected YD0008Response convertResponse(String json) throws Exception {
		YD0008Response rm = new YD0008Response();
		JSONObject jo = JSONObject.fromObject(json);
		List numList = new ArrayList();
		JSONArray nums = (JSONArray) jo.get("number");//号码列表
		String vn = (String) jo.get("vanityName");//号码级别
		for (Iterator it = nums.iterator(); it.hasNext();) {
			JSONObject numObj = (JSONObject) it.next();
			numList.add(numObj.get("id"));
		}
		rm.setSvnList(numList);
		rm.setVanityName(vn);
		System.err.println(rm);
		return rm;
	}


	@Override
	protected String convertRequest(YD0008Request request) throws Exception {
		// TODO Auto-generated method stub
		if(request==null){
			return "";
		}
		
		String writeValueAsString = new ObjectMapper().writeValueAsString(request);
		return writeValueAsString;
//		JSONObject m = new JSONObject();
//		JSONObject page = new JSONObject();
//		m.put("circleID", request.getCiecleId());
//		m.put("type", "MSISDN");
//		if(StringUtils.isNotBlank(request.getPattern())){
//			m.put("searchPattern", "*"+request.getPattern()+"*");
//		}
//		if(StringUtils.isNotBlank(request.getVanityName())){
//			m.put("includeVanityNumber", "true");
//			m.put("vanityName", request.getVanityName());
//		}else{
//			m.put("includeVanityNumber", "false");
//			m.put("vanityName", "");
//		}
//		page.put("offset", request.getPage());
//		page.put("pageSize", request.getSize());
//		m.put("paging", page);
//		JSONObject.fromObject(m).toString();
//		return JSONObject.fromObject(m).toString();
	}


	@Override
	public String getTibcoUrl() {
		// TODO Auto-generated method stub
		return url;
	}
	
	public static void main(String[] args) throws Exception {
		YD0008Action yd8 = new YD0008Action();
		YD0008Request req =  new YD0008Request();
		YdPage pg = new YdPage();
		req.setPaging(pg);
		req.setSearchPattern("1234");
		pg.setPageSize("10");
		pg.setOffset("5");
		req.setCiecleId("TC");
		System.err.println(yd8.convertRequest(req));
		System.err.println(new ObjectMapper().writeValueAsString(req));
		
		YD0008Response res = yd8.convertResponse("{\"number\":[{\"id\":\"3334021234\"},{\"id\":\"3334041234\"},{\"id\":\"3334061234\"}],\"vanityName\":\"\"}");
		res = yd8.convertResponse("{ \"number\":[ ] , \"vanityName\":\"\" }");
		
	}



}
