package com.ai.mapp.bss.service;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.service.AgentOrderService;

@Service(value="hw0016Service")
@Scope(value="singleton")
public class HW0016SVImpl extends ISVTemplate {
	
	@Autowired
	private AgentOrderService agentOrderService; 
	
	@Override
	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0016.Response rsp = new com.ai.mapp.model.HW0016.Response();
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);
		return rsp.toXMLString();
	}

	@Override
	public Object error(ParamObject param) throws Exception {
		return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR,param.getErrorInfo());
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		com.ai.mapp.model.HW0016.Request req = com.ai.mapp.model.HW0016.Request
			.unmarshal(new StringReader((String)param.getParameter(BSSConstantParam.REQUESTCONTENT)));

		agentOrderService.payOrder(req.getOrderCode(), req.getPayMode(),req.getVoucherNo());
		
		param.setIfSuccess(true);
		return param;
	}
	
}
