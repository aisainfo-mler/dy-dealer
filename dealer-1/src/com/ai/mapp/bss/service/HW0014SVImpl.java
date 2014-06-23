package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.GoodsInfo;
import com.ai.mapp.sys.entity.OrderItem;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.OrderItemService;

@Service(value="hw0014Service")
@Scope(value="singleton")
public class HW0014SVImpl extends ISVTemplate {
	
	@Autowired
	private OrderItemService orderItemService; 
	
	@Override
	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0014.Response rsp = new com.ai.mapp.model.HW0014.Response();
		
		Collection<OrderItem> items = (Collection<OrderItem>)param.getResult();
		
		if(items != null && items.isEmpty() == false)
		{
			com.ai.mapp.model.HW0014.ItemList itemlist = new com.ai.mapp.model.HW0014.ItemList();
			
			for(OrderItem item : items)
			{
				com.ai.mapp.model.HW0014.Item i = new com.ai.mapp.model.HW0014.Item();
				
				i.setGoodId(item.getGood() == null?"":item.getGood().getId().toString());
				i.setItemValue(item.getItemValue() == null?"":item.getItemValue());
				if(item.getOperator() != null)
				{
					i.setStatus(item.getStatus() == null ? "":item.getStatus());
					i.setUserCode(item.getOperator() == null?"":item.getOperator().getUserCode());
					i.setUserName(item.getOperator() == null?"":item.getOperator().getFirstName()+" "+item.getOperator().getLastName());
				}
				else
				{
					i.setStatus("");
					i.setUserCode("");
					i.setUserName("");
				}
				
				itemlist.addItem(i);
			}
			
			rsp.setItemList(itemlist);
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
		
		com.ai.mapp.model.HW0014.Request req = com.ai.mapp.model.HW0014.Request
			.unmarshal(new StringReader((String)param.getParameter(BSSConstantParam.REQUESTCONTENT)));
		
		OrderItem condition = new OrderItem();
		
		if(StringUtil.isEmpty(req.getGoodId()) == false)
			condition.setGood(new GoodsInfo(Long.valueOf(req.getGoodId())));
		
		
		if(StringUtil.isEmpty(req.getGoodType()) == false)
		{
			if(condition.getGood() == null)
				condition.setGood(new GoodsInfo());
			condition.getGood().setType(req.getGoodType());
		}
		
		if(StringUtil.isEmpty(req.getStatus()) == false)
			condition.setStatus(req.getStatus());
		
		if(StringUtil.isEmpty(req.getUserCode()) == false)
			condition.setOperator(new User(req.getUserCode()));

		int start = StringUtil.isEmpty(req.getStart())  ? 0 : Integer.valueOf(req.getStart());
		int pageSize = StringUtil.isEmpty(req.getSize()) ? 16 : Integer.valueOf(req.getSize());
		
		Collection<OrderItem> items = orderItemService.listOrderItems(condition, start, pageSize);
		
		param.setIfSuccess(true);
		param.setResult(items);
		return param;
	}
	
}
