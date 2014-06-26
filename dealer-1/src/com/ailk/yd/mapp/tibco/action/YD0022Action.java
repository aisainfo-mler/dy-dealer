package com.ailk.yd.mapp.tibco.action;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ailk.yd.mapp.tibco.model.YD0022.YD0022Request;
import com.ailk.yd.mapp.tibco.model.YD0022.YD0022Response;

@Service("yd0022")
public class YD0022Action extends AbstractTibcoService<YD0022Request, YD0022Response> {

	@Value("${yd0022_url}")
	private String url;
	@Override
	public String getTibcoUrl() {
		return url;
	}

	@Override
	protected YD0022Response convertResponse(String json) throws Exception {
		return YD0022Response.fillVal(json);
	}

}
