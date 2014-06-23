package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.HwCity;
import com.ai.mapp.sys.entity.HwState;
import com.ai.mapp.sys.service.AgentOrderService;

@Service("hw0025Service")
@Scope("singleton")
public class Hw0025SVImpl extends ISVTemplate {
	
	@Autowired
	private AgentOrderService agentOrderService;
	
	protected Object convertResponse(ParamObject param) throws Exception 
	{
		com.ai.mapp.model.HW0025.Response rsp = new com.ai.mapp.model.HW0025.Response();
		
		AgentOrder order = (AgentOrder)param.getResult();
		rsp.setOrderCode(order.getOrderCode());
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);
		
		return rsp.toXMLString();
	}
	
	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		String requestContent = (String) param.getParameter(BSSConstantParam.REQUESTCONTENT);
		com.ai.mapp.model.HW0025.Request req=com.ai.mapp.model.HW0025.Request.unmarshal(new StringReader(requestContent));
		
		System.out.println("proudctId:"+req.getProductId()+",userId:"+param.getParameter(BSSConstantParam.USERID));
		
		Long fee = StringUtils.isEmpty(req.getFee()) ? 0 : Long.valueOf(req.getFee());
		Long proudctId = StringUtils.isEmpty(req.getProductId()) ? 0 : Long.valueOf(req.getProductId());
		Long userId = null;
		if(param.getParameter(BSSConstantParam.USERID) instanceof Long)
			userId = (Long)param.getParameter(BSSConstantParam.USERID);
		else
			userId = Long.valueOf((String)param.getParameter(BSSConstantParam.USERID));
		
		AgentOrder order = agentOrderService.createBoltOnOrderByAgent(req.getSvn(), fee, proudctId, userId);
		
		param.setIfSuccess(true);
		param.setResult(order);
		return param;
	}
	
	@Override
	/**
	 * 错误处理
	 */
	public Object error(ParamObject param) throws Exception {
		return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR,param.getErrorInfo());
	}

}
