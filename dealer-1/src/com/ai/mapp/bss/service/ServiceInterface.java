package com.ai.mapp.bss.service;

import com.ai.mapp.bss.entity.ParamObject;

public interface ServiceInterface {
	
	/**
	 * 用于给前端的接口，返回协议的XML
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Object getResponseXML(ParamObject param) throws Exception;
	
}
