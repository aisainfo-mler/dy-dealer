package com.ailk.yd.mapp.tibco.action;

import java.util.Map;

import net.sf.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ailk.yd.mapp.tibco.util.TibcoUtil;


public abstract class AbstractTibcoService<Req,Rsp> {
	
	protected ObjectMapper mapper = new ObjectMapper();

	protected Logger logger = LoggerFactory.getLogger(AbstractTibcoService.class);
	
	@Autowired
	protected TibcoHandler tibcoHandler;
	
	public Rsp post2Tibco(Req request,Map<String,?> paramters) throws Exception {
		
		String rsp_string = tibcoHandler.sendMsg(getTibcoUrl(), convertRequest(request), paramters,null,true);
		checkSucc(rsp_string);
		return convertResponse(rsp_string);
		
	}
	
	public Rsp get2Tibco(Map<String,?> paramters) throws Exception {
		
		String rsp_string = tibcoHandler.sendMsg(getTibcoUrl(), null , paramters,null,false);
		checkSucc(rsp_string);
		return convertResponse(rsp_string);
		
	}

	/**
	 * 判断返回的串是否失败。是的话就直接抛出异常
	 * @param rsp_string
	 * @throws Exception
	 */
	private void checkSucc(String rsp_string) throws Exception {
		JSONObject jo = null;
		try {
			/**
			 * TODO JsonObject==>ObjectMapping
			 */
			jo = JSONObject.fromObject(rsp_string);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jo!=null && jo.get("success") != null) {
			// 必然是失败
			String errMsg = TibcoUtil.findErrMsg(jo);
			throw new Exception(errMsg);
		}
	}
	
	public abstract String getTibcoUrl();
	
	

	abstract protected Rsp convertResponse(String json) throws Exception ;
	
	protected String convertRequest(Req request) throws Exception
	{
		return mapper.writeValueAsString(request);
	}
	
	
	
}
