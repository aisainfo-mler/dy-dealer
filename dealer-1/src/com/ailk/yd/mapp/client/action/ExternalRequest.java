package com.ailk.yd.mapp.client.action;

import java.util.Map;


public interface ExternalRequest<Req,Rsp> {

	public Rsp sendMsg(String url,Req req , Map<String,?>parameters,String token,boolean isPost) throws Exception;
	
	
}
