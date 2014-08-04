package com.ailk.yd.mapp.client.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.butterfly.mapp.core.model.IBody;
import com.ailk.yd.mapp.client.model.HW0016Request;

@Service("hw0016")
@Action(bizcode = "hw0016", userCheck = true)
@Scope("prototype")
public class HW0016Action extends AbstractYDBaseActionHandler<HW0016Request, IBody> {

	@Autowired
	private AgentOrderService agentOrderService;

	@Override
	protected void doAction() throws BusinessException, SystemException,Exception 
	{
		if(this.request.getPayMode() == null){
			throw new Exception("pay mode is null");
		}
		agentOrderService.payOrder(request.getOrderCode(), request.getPayMode(),request.getVoucherNo(),request.getPayPwd());
	}

}
