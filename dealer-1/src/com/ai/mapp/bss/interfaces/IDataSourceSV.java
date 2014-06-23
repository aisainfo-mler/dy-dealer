package com.ai.mapp.bss.interfaces;

import com.ai.mapp.bss.entity.ParamObject;

public interface IDataSourceSV {

	public ParamObject send(ParamObject param) throws Exception;
	
	public ParamObject getParamObjectResult(ParamObject param) throws Exception;
	
	public void initParam(ParamObject param) throws Exception;
	
}
