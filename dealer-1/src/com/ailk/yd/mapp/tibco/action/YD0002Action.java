package com.ailk.yd.mapp.tibco.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ailk.yd.mapp.tibco.model.YD0002.YD0002Request;
import com.ailk.yd.mapp.tibco.model.YD0002.YD0002Response;

@Service("yd0002")
public class YD0002Action extends AbstractTibcoService<YD0002Request, YD0002Response> {

	
	@Value("${yd0002_url}")
	private String url;
	@Override
	protected YD0002Response convertResponse(String json) throws Exception {
	
		if(StringUtils.isBlank(json))
			throw new Exception("tibco return json is blank");
		
		Map resMap = mapper.readValue(json, Map.class);
		YD0002Response response = new YD0002Response();
		response.setServiceProviderEmployee((String)resMap.get("serviceProviderEmployee"));
		response.setResponse(new YD0002Response.Status((String)((Map)resMap.get("response")).get("interactionStatus")));
		response.setMessage((String)resMap.get("message"));
		
		Map caiMap = (Map)resMap.get("customerAccountIdentifier");
		response.setCustomerAccountIdentifier(new YD0002Response.CustomerAccountIdentifier());
		response.getCustomerAccountIdentifier().setName((String)caiMap.get("name"));
		response.getCustomerAccountIdentifier().setType((String)caiMap.get("type"));
		response.getCustomerAccountIdentifier().setValue((String)caiMap.get("value"));
		return response;
	}

	@Override
	public String getTibcoUrl() {
		return url;
	}

}
