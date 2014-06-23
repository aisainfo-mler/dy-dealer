package com.ailk.yd.mapp.tibco.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request;
import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Response;
@Service("yd0010")
public class YD0010Action extends AbstractTibcoService<YD0010Request, YD0010Response> {

	
	@Value("${yd0010_url}")
	private String url;
	
	@Override
	public String getTibcoUrl() {
		return url;
	}

	@Override
	protected YD0010Response convertResponse(String json) throws Exception {

		if(StringUtils.isBlank(json))
			throw new Exception("tibco response json is null");
		
		Map<String,?> rsp_map = mapper.readValue(json, Map.class);
		YD0010Response rsp = new YD0010Response();
		rsp.setOrderNumber((String)rsp_map.get("orderNumber"));
		rsp.setOrderReferenceNumber((String)rsp_map.get("orderReferenceNumber"));
		rsp.setOrderEntryDateAndTimeStamp((String)rsp_map.get("OrderEntryDateAndTimeStamp"));
		
		return rsp;
	}

}
