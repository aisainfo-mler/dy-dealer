package com.ailk.yd.mapp.client.action;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.base.BaseActionHandler;
import com.ailk.butterfly.mapp.core.model.IBody;
import com.ailk.yd.mapp.tibco.action.TibcoHandler;

public abstract class AbstractYDBaseActionHandler<Request extends IBody, Response extends IBody> extends BaseActionHandler<Request, Response> {

	protected Logger logger = LoggerFactory.getLogger(AbstractYDBaseActionHandler.class);
	
	protected ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	protected TibcoHandler tibcoHandler;
	
	@Value("${test}")
	protected String test;
	
	protected IUserinfo getUserinfo() throws Exception{
		Object ui = MappContext.getAttribute(MappContext.MAPPCONTEXT_USER);
		if(ui==null){
			throw new Exception("User in session is null");
		}else{
			return (IUserinfo) ui;
		}
	}
	
}
