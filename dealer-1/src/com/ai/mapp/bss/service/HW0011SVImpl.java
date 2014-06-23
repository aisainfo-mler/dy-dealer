package com.ai.mapp.bss.service;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.UserService;

@Service(value="hw0011Service")
@Scope(value="singleton")
public class HW0011SVImpl extends ISVTemplate {
	
	@Autowired
	private AgentOrderService agentOrderService;
	
	@Autowired
	private UserService userService;
	
	@Override
	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0011.Response rsp = new com.ai.mapp.model.HW0011.Response();
		
		AgentOrder order = (AgentOrder)param.getResult();
		rsp.setOrderId(order.getOrderCode());
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);
		
		return rsp.toXMLString();
	}

	@Override
	public Object error(ParamObject param) throws Exception {
		return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR,param.getErrorInfo());
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		com.ai.mapp.model.HW0011.Request req = com.ai.mapp.model.HW0011.Request
			.unmarshal(new StringReader((String)param.getParameter(BSSConstantParam.REQUESTCONTENT)));
		
		String userCode = (String)param.getParameter(BSSConstantParam.USERCODE);
		
		User creator = userService.loadUserByUserCode(userCode);
		
		AgentOrder order = new AgentOrder();
		order.setBankSerial(req.getVoucherNo());
		order.setCreator(creator);
		order.setPayMode(req.getPayMethodId());
		order.setSimFee(Long.valueOf(req.getFee()));
		order.setSim(req.getSIM());
		order.setImsi(req.getIMSI());
		order.setSvn(req.getMdn());
		
		order = agentOrderService.createSimCardOrderByAgent(order);
		
		param.setIfSuccess(true);
		param.setResult(order);
		return param;
	}
	
}
