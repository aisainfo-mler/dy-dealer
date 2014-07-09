package com.ailk.yd.mapp.client.action;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AccountInfoService;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.ProductService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0009Request;
import com.ailk.yd.mapp.client.model.HW0009Response;

@Service("hw0009")
@Action(bizcode="hw0009",userCheck=true)
@Scope("prototype")
public class HW0009Action extends AbstractYDBaseActionHandler<HW0009Request, HW0009Response> {

	@Autowired
	private AgentOrderService agentOrderService;

	@Autowired
	private AccountInfoService accountInfoService;
	
	@Autowired
	private ProductService productService;
	
	/**
	 * topup的默认值88888
	 */
	@Value("${topup_pid}")
	private String topupProductId;

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		// TODO Auto-generated method stub


		String userCode = this.getUserinfo().getUserName();
		
		HW0009Request req = request;
		/** 预存池充值 **/
		if("1".equals(req.getRechargeTypeId()))
		{
			accountInfoService.chargeAccount(
					userCode,
					(req.getPayedAmount() == null ? 0 : Long.valueOf(req.getPayedAmount())), 
					req.getPayMethodId(), 
					req.getOrnNum());
		}
		else
		{
			/** 普通号码充值 **/
			
			if(StringUtils.isEmpty(req.getPayedAmount()) || Float.valueOf(req.getPayedAmount()) < 0)
			{
				throw new Exception("amount is error");
			}
			
			
			if("2".equals(req.getRechargeTypeId()) && StringUtils.isEmpty(req.getMdn()))
			{
				throw new Exception("phone number must not empty");
			}
			
			
			AgentOrder order = new AgentOrder();
			
			order.setCreator(new User(userCode));
			order.setPayMode(req.getPayMethodId());
			order.setPackageFee(Long.valueOf(req.getPayedAmount()));
			order.setSvn(req.getMdn());
			order.setOptType(req.getRechargeTypeId());
			order.setTibcoOrderNumber(req.getOrnNum());
			order.setSaleFee(Long.parseLong(req.getPayedAmount()));
			if(StringUtils.isNotBlank(this.topupProductId)){
				Product pro = productService.loadProductByBSSId(topupProductId);
				if(pro!=null){
					order.setProduct(pro);
				}
			}
			
			order = agentOrderService.createTopUpOrder(order);
			response = new HW0009Response();
			response.setOrderId(order.getOrderCode());
			
		}
		
	}

	

}
