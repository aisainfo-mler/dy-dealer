package com.ailk.yd.mapp.client.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.UserService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.util.SetUtil;
import com.ailk.yd.mapp.client.model.HW0032Request;
import com.ailk.yd.mapp.client.model.HW0032Response;
import com.ailk.yd.mapp.model.YDDatapackage;
import com.ailk.yd.mapp.tibco.action.YD0009Action;
import com.ailk.yd.mapp.tibco.model.YD0009.YD0009Request;
import com.ailk.yd.mapp.tibco.model.YD0009.YD0009Response;

/**
 * 用户充值接口
 * 
 */
@Service("hw0032")
@Action(bizcode = "hw0032", userCheck = true)
@Scope("prototype")
public class HW0032Action extends
		AbstractYDBaseActionHandler<HW0032Request, HW0032Response> {

	@Autowired
	private UserService userService;

	@Autowired
	private AgentOrderService agentOrderService;

	@Autowired
	private YD0009Action yd0009;

	@SuppressWarnings("unused")
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		IUserinfo ui = this.getUserinfo();
		User creator = userService.loadUser(ui.getUserId().longValue());

		final HW0032Request req = request;
		AgentOrder order = new AgentOrder();
		order.setCreator(creator);
		order.setSvn(req.getSeriveId());
		order.setSaleFee(Long.parseLong(req.getAmount()));

		order = agentOrderService.createTopUpOrder(order);
		String circleId = creator.getCircleId();
		if(StringUtils.isBlank(circleId)){
			circleId = "TC";
		}

		YD0009Request yd9 = new YD0009Request(request.getSeriveId(),
				request.getAmount(), request.getRrfId(), circleId,
				request.getAccountLevel());

		YD0009Response post2Tibco = this.yd0009.post2Tibco(yd9, null);
		order.setPin(request.getRrfId());
		order.setSn(request.getRrfId());
		agentOrderService.saveAgentOrder(order);

	}

}
