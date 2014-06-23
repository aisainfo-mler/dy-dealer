package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.util.ConvertUtils;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.bss.entity.EH2Socket;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AccountInfoService;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.util.SYSConstant;

@Service("hw0009Service")
@Scope("singleton")
public class HW0009SVImpl extends ISVTemplate {

	@Autowired
	private AgentOrderService agentOrderService;

	@Autowired
	private AccountInfoService accountInfoService;

	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0009.Response rsp = new com.ai.mapp.model.HW0009.Response();

		AgentOrder order = (AgentOrder) param.getResult();
		rsp.setOrderId(order == null ? "" : order.getOrderCode());
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);

		return rsp.toXMLString();
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {

		com.ai.mapp.model.HW0009.Request req = com.ai.mapp.model.HW0009.Request
				.unmarshal(new StringReader((String)param.getParameter(BSSConstantParam.REQUESTCONTENT)));
		
		String userCode = (String)param.getParameter(BSSConstantParam.USERCODE);
		
		/** 预存池充值 **/
		if("1".equals(req.getRechargeTypeId()))
		{
			accountInfoService.chargeAccount(
					userCode,
					(req.getPayedAmount() == null ? 0 : Long.valueOf(req.getPayedAmount())), 
					req.getPayMethodId(), 
					req.getVoucherNo());
		}
		else
		{
			/** 普通号码充值 **/
			
			if(StringUtils.isEmpty(req.getPayedAmount()) || Float.valueOf(req.getPayedAmount()) < 0)
			{
				param.setIfSuccess(false);
				param.addError(BSSConstantError.CODE_DATA_ERROR);
				return param;
			}
			
			
			if("2".equals(req.getRechargeTypeId()) && StringUtils.isEmpty(req.getMdn()))
			{
				param.setIfSuccess(false);
				param.addError("phone number must not empty");
				return param;
			}
			
			
			AgentOrder order = new AgentOrder();
			
			order.setBankSerial(req.getVoucherNo());
			order.setCreator(new User(userCode));
			order.setPayMode(req.getPayMethodId());
			order.setPackageFee(Long.valueOf(req.getPayedAmount()));
			order.setSvn(req.getMdn());
			order.setOptType(req.getRechargeTypeId());
			
			order = agentOrderService.createRechargeOrderByAgent(order);
			
			param.setResult(order);
		}
		
		param.setIfSuccess(true);
		return param;
	}

	/**
	 * 错误处理
	 */
	public Object error(ParamObject param) throws Exception {
		return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR,
				param.getErrorInfo());
	}

}
