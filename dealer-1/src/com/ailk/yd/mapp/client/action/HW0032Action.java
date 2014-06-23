package com.ailk.yd.mapp.client.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ailk.yd.mapp.tibco.model.YD0009.YD0009Request;
import com.ailk.yd.mapp.tibco.model.YD0009.YD0009Response;

@Service("hw0032")
@Action(bizcode = "hw0032", userCheck = true)
public class HW0032Action extends
		AbstractYDBaseActionHandler<HW0032Request, HW0032Response> {

	@Autowired
	private UserService userService;

	@Autowired
	private AgentOrderService agentOrderService;

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		IUserinfo ui = this.getUserinfo();
		User creator = userService.loadUser(ui.getUserId().longValue());

		final HW0032Request req = request;
		AgentOrder order = new AgentOrder();
		order.setCreator(creator);
		order.setSvn(req.getMdn());
		order.setSaleFee(req.getAmount().longValue());

		order = agentOrderService.createTopUpOrder(order);
		
		YD0009Request yd9 = new YD0009Request();
		new SetUtil(req, yd9).copyAllSameNameProp();
		yd9.setOrderId(order.getOrderId() + "");
		
		
		String sn ="";
		String pin = "";
		if(StringUtils.equals("1", test)){
			sn = "20140511143000";
			pin = "8819182921918281";
		}else{
//			YDDatapackage r = this.sendMsg("yd0009", yd9, (String) MappContext
//					.getAttribute(MappContext.MAPPCONTEXT_SESSIONID));
			YDDatapackage r = null;
			YD0009Response res = (YD0009Response) r.getBody();
			sn = res.getSn();
			pin = res.getPin();
		}
		
		order.setPin(pin);
		order.setSn(sn);
		agentOrderService.saveAgentOrder(order);
		
		
		
		
	}

}
