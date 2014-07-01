package com.ailk.yd.mapp.client.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.UserService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0041Request;
import com.ailk.yd.mapp.client.model.HW0041Response;
import com.ailk.yd.mapp.tibco.action.YD0009Action;
import com.ailk.yd.mapp.tibco.model.YD0009.YD0009Request;
import com.ailk.yd.mapp.tibco.model.YD0009.YD0009Response;
import com.ailk.yd.mapp.tibco.util.TibcoUtil;

/**
 * 用户充值接口
 * 
 */
@Service("hw0041")
@Action(bizcode = "hw0041", userCheck = true)
@Scope("prototype")
public class HW0041Action extends
		AbstractYDBaseActionHandler<HW0041Request, HW0041Response> {

	@Autowired
	private UserService userService;

	@Autowired
	private YD0009Action yd0009;

	@SuppressWarnings("unused")
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		IUserinfo ui = this.getUserinfo();
		User creator = userService.loadUser(ui.getUserId().longValue());

		final HW0041Request req = request;
		
		String circleId = creator.getCircleId();
		if(StringUtils.isBlank(circleId)){
			circleId = "TC";
		}
		TibcoUtil.checkNotNull(request.getAmount(), "admout");
		TibcoUtil.checkNotNull(request.getRrfId(), "refNum");
		TibcoUtil.checkNotNull(request.getSeriveId(), "serviceId");
		YD0009Request yd9 = new YD0009Request(request.getSeriveId(),
				request.getAmount(), request.getRrfId(), circleId,
				false, false, request.getProductId());

		YD0009Response post2Tibco = this.yd0009.post2Tibco(yd9, null);
		
		String rfid = post2Tibco.getRefillId();
		if(StringUtils.isBlank(rfid)){
			throw new Exception("refNum from TIBCO is null");
		}
//		order.setPin(request.getRrfId());
//		order.setSn(request.getRrfId());
//		agentOrderService.saveAgentOrder(order);

	}
	

}
