package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.ProductService;

@Service(value="hw0013Service")
@Scope(value="singleton")
public class HW0013SVImpl extends ISVTemplate {
	
	@Autowired
	private AgentOrderService agentOrderService; 
	
	@Autowired
	private ProductService productService;
	
	
	@Override
	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0013.Response rsp = new com.ai.mapp.model.HW0013.Response();
		
		Collection<AgentOrder> orders = (Collection<AgentOrder>)param.getResult();
		
		if(orders != null && orders.isEmpty() == false)
		{
			com.ai.mapp.model.HW0013.OrderList dl = new com.ai.mapp.model.HW0013.OrderList();
			
			for(AgentOrder order : orders)
			{
				com.ai.mapp.model.HW0013.Order d = new com.ai.mapp.model.HW0013.Order();
				
				d.setBalance(order.getBlance()== null?"":order.getBlance().toString());
				d.setCreateTime(order.getCreateTime() == null ? "":DateUtils.getDateString(order.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
				d.setOrderCode(order.getOrderCode()== null?"":order.getOrderCode().toString());
				d.setOrderType(order.getOrderType()== null?"":order.getOrderType().toString());
				d.setPreStore(order.getPreStore()== null?"":order.getPreStore().toString());
				d.setProductId(order.getProduct()== null?"":order.getProduct().getRangeId().toString());
				d.setSim(order.getSim()== null?"":order.getSim());
				d.setSvn(order.getSvn());
				d.setDiscountFee(order.getDiscountFee()== null?"":order.getDiscountFee().toString());
				d.setPayMode(order.getPayMode()== null?"":order.getPayMode().toString());
				d.setRealFee(order.getRealFee()== null?"":order.getRealFee().toString());
				d.setSaleFee(order.getSaleFee()== null?"":order.getSaleFee().toString());
				d.setTotalFee(order.getSaleFee()== null?"":order.getSaleFee().toString());
				String productType=productService.getProductTypeName(order.getProduct(),param.getParameter(BSSConstantParam.LANGUAGE).toString());
				if(productType==null) productType="";
				d.setProductType(productType);
				d.setOrderStatus(order.getStatus()== null?"":order.getStatus());
				d.setPayStatus(order.getPayStatus()== null?"":order.getPayStatus());
				d.setPin(order.getPin()== null?"":order.getPin());
				d.setPackageFee(order.getPackageFee() == null?"0":order.getPackageFee().toString());
				if(order.getProduct()!=null && order.getProduct().getContract()!=null 
						&& StringUtils.isNotEmpty(order.getProduct().getContract().getName())){
					d.setPackageName(order.getProduct().getContract().getName());
				}else{
					d.setPackageName("");
				}
				d.setSimFee(order.getSimFee() == null?"0":order.getSimFee().toString());
				d.setSIMFee(order.getSimFee() == null?"0":order.getSimFee().toString());
				d.setNumberFee(order.getNumberFee() == null?"0":order.getNumberFee().toString());
				
				dl.addOrder(d);
			}
			
			rsp.setOrderList(dl);
		}
		
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);
		return rsp.toXMLString();
	}

	@Override
	public Object error(ParamObject param) throws Exception {
		return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR,param.getErrorInfo());
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		com.ai.mapp.model.HW0013.Request req = com.ai.mapp.model.HW0013.Request
			.unmarshal(new StringReader((String)param.getParameter(BSSConstantParam.REQUESTCONTENT)));
		
		String userCode = (String)param.getParameter(BSSConstantParam.USERCODE);
		
		AgentOrder condition = new AgentOrder();
		
		if(StringUtil.isEmpty(req.getEndTime()) == false)
			condition.setEndTime(DateUtils.getDate(req.getEndTime()+" 23:59:59","yyyy-MM-dd HH:mm:ss"));
		if(StringUtil.isEmpty(req.getStartTime()) == false)
			condition.setStartTime(DateUtils.getDate(req.getStartTime()+" 00:00:00","yyyy-MM-dd HH:mm:ss"));
		if(StringUtil.isEmpty(req.getOrderCode()) == false)
			condition.setOrderCode(req.getOrderCode());
		if(StringUtil.isEmpty(req.getPayStatus()) == false)
			condition.setPayStatus(req.getPayStatus());
		if(StringUtil.isEmpty(req.getStatus()) == false)
			condition.setStatus(req.getStatus());
		if(StringUtil.isEmpty(req.getPayType()) == false)
			condition.setPayType(req.getPayType());
		if(StringUtil.isEmpty(req.getOrderType()) == false)
			condition.setOrderType(req.getOrderType());
		if(StringUtil.isEmpty(req.getSvn()) == false)
			condition.setSvn(req.getSvn());
		if(StringUtil.isEmpty(userCode) == false)
			condition.setCreator(new User(userCode));
		
		
		int start = StringUtil.isEmpty(req.getStart())  ? 0 : Integer.valueOf(req.getStart());
		int pageSize = StringUtil.isEmpty(req.getSize()) ? 16 : Integer.valueOf(req.getSize());
		
		Collection<AgentOrder> orders = null;
		
		if(StringUtil.isEmpty(req.getStart()) == false && Integer.valueOf(req.getStart()) < 0 )
			orders = agentOrderService.listAllAgentOrders(condition);
		else
			orders = agentOrderService.listAgentOrders(condition, start, pageSize);
		
		
		param.setIfSuccess(true);
		param.setResult(orders);
		return param;
	}
	
	
	
}
