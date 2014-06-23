package com.ailk.yd.mapp.tibco.action;

import java.util.Map;

import net.sf.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ailk.yd.mapp.tibco.model.TibcoRequest;
import com.ailk.yd.mapp.tibco.model.YD0019.YD0019Response;


/**
 * @author mler
 * @version 创建时间：2014-6-11 上午03:05:11
 * 类说明:从tibco获取orn号
 */
@Service("yd0019")
public class YD0019Action extends AbstractTibcoService<TibcoRequest,YD0019Response> {

	public static final String PARAM_KEY_TRANSACTIONTYPE = "transactionType";
	
	@Value("${yd0019_url}")
	private String url;
	
	@Override
	protected YD0019Response convertResponse(String json) throws Exception {
		
		YD0019Response rsp = new YD0019Response();
		JSONObject jo = JSONObject.fromObject(json);
		rsp.setTransactionRefNumber(jo.getString("transactionRefNumber"));
		return rsp;
	}

	@Override
	protected String convertRequest(TibcoRequest request) throws Exception {
		return null;
	}

	@Override
	public String getTibcoUrl() {
		// TODO Auto-generated method stub
		return url;
	}

	


}
