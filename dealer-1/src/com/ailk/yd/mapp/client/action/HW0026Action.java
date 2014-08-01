package com.ailk.yd.mapp.client.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.entity.ResultJson;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.OrderInfoService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0026Request;
import com.ailk.yd.mapp.client.model.HW0026Response;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-8-1 下午03:28:12
 * 类说明:
 */
@Service("hw0026")
@Action(bizcode = "hw0026", userCheck = false)
@Scope("prototype")
public class HW0026Action extends AbstractYDBaseActionHandler<HW0026Request, HW0026Response> {
	@Autowired
	private AgentOrderService agentOrderService; 
	@Autowired
	private OrderInfoService orderInfoService;

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		if(StringUtil.isEmpty(request.getOrderCode())){
			throw new Exception("lost the orderCode");
		}else{
			if(!StringUtil.isEmpty(request.getOrderCode())){
				ResultJson result = agentOrderService.cancelOrder(request.getOrderCode());
				if(!result.getFlag()){
					throw new Exception(result.getMsg());
				}
			}
			
		}
		
	}

	public AgentOrderService getAgentOrderService() {
		return agentOrderService;
	}

	public void setAgentOrderService(AgentOrderService agentOrderService) {
		this.agentOrderService = agentOrderService;
	}

	public OrderInfoService getOrderInfoService() {
		return orderInfoService;
	}

	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

}
