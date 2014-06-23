package com.ai.mapp.bss.service;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.UserService;

@Service("hw0010Service")
@Scope("singleton")
public class HW0010SVImpl extends ISVTemplate {
	
	@Autowired
	private AgentOrderService agentOrderService;
	
	@Autowired
	private UserService userService;
	
	protected Object convertResponse(ParamObject param) throws Exception 
	{
		com.ai.mapp.model.HW0010.Response rsp = new com.ai.mapp.model.HW0010.Response();
		
		AgentOrder order = (AgentOrder)param.getResult();
		rsp.setOrderId(order.getOrderCode());
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);
		return rsp.toXMLString();
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {

		com.ai.mapp.model.HW0010.Request req = com.ai.mapp.model.HW0010.Request
				.unmarshal(new StringReader((String)param.getParameter(BSSConstantParam.REQUESTCONTENT)));
		
		String userCode = (String)param.getParameter(BSSConstantParam.USERCODE);
		
		User creator = userService.loadUserByUserCode(userCode);
		
		AgentOrder order = new AgentOrder();
		order.setBankSerial(req.getVoucherNo());
		order.setCreator(creator);
		order.setPayMode(req.getPayMethodId());
		order.setPackageFee(StringUtil.isEmpty(req.getFee())? 0:Long.valueOf(req.getFee()));
		order.setSim(req.getSIM());
		order.setImsi(req.getIMSI());
		order.setImei(req.getIMEI());
		order.setSvn(req.getMdn());
		order.setProduct(StringUtil.isEmpty(req.getProductId())? null : new Product(Long.valueOf(req.getProductId())));
		
		User user = new User();
		user.setFirstName(req.getName());
		user.setCertificateType(req.getIDCardType());
		user.setCertificateNo(req.getIDCardNo());
		user.setEmail(req.getEmail());
		user.setContractPhone(req.getContactPhone());
		user.setCreator(creator);

		
//		agentOrderService.statBestProductByMonth(null, null, null, null);
		
		order = agentOrderService.createNewOrderByAgent(order,user);
	
		param.setIfSuccess(true);
		param.setResult(order);
		return param;
		
	}
	
	/**
	 * 错误处理
	 */
	public Object error(ParamObject param) throws Exception {
		return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR,param.getErrorInfo());
	}
}
