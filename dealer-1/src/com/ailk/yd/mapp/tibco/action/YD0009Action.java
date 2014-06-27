package com.ailk.yd.mapp.tibco.action;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ailk.yd.mapp.tibco.model.YD0009.YD0009Request;
import com.ailk.yd.mapp.tibco.model.YD0009.YD0009Response;

@Service("yd0009")
public class YD0009Action extends AbstractTibcoService<YD0009Request, YD0009Response> {

	@Value("${yd0009_url}")
	private String url;
	@Override
	public String getTibcoUrl() {
		// TODO Auto-generated method stub
		return url;
	}

	@Override
	protected YD0009Response convertResponse(String json) throws Exception {
		Map map = new ObjectMapper().readValue(json, Map.class);
		String ref = (String) map.get("refillId");
		YD0009Response rm = new YD0009Response();
		rm.setRefillId(ref);
		return rm;
	}

}
