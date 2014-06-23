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
import com.ai.mapp.sys.entity.GoodsInfo;
import com.ai.mapp.sys.entity.OrderDetail;
import com.ai.mapp.sys.entity.OrderInfo;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.OrderInfoService;
import com.ai.mapp.sys.service.UserService;

@Service(value="hw0017Service")
@Scope(value="singleton")
public class HW0017SVImpl extends ISVTemplate {
	
	@Autowired
	private OrderInfoService orderInfoService; 
	@Autowired
	private UserService userService;
	
	@Override
	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0017.Response rsp = new com.ai.mapp.model.HW0017.Response();
		
		Collection<OrderInfo> orders = (Collection<OrderInfo>)param.getResult();
		
		if(orders != null && orders.isEmpty() == false)
		{
			com.ai.mapp.model.HW0017.OrderList dl = new com.ai.mapp.model.HW0017.OrderList();
			
			for(OrderInfo order : orders)
			{
				com.ai.mapp.model.HW0017.Order o = new com.ai.mapp.model.HW0017.Order();
				
				long fee = order.getFee() == null ? 0 : order.getFee().longValue();
				long realFee = order.getRealFee() == null ? 0 : order.getRealFee().longValue();
				long discount = fee-realFee;
				
				o.setCreateTime(order.getCreateTime() == null ? "":DateUtils.getDateString(order.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
				o.setDiscount(discount+"");
				o.setOrderCode(order.getSerialNumber());
				o.setOrderStatus(order.getStatus());
				o.setOrderType(order.getType());
				o.setPayTime(order.getPayTime() == null ? "":DateUtils.getDateString(order.getPayTime(), "yyyy-MM-dd HH:mm:ss"));
				o.setRealFee(realFee+"");
				o.setSaleFee(fee+"");
				
				dl.addOrder(o);
				
				if(order.getDetails() == null || order.getDetails().isEmpty())
					continue;
				
				com.ai.mapp.model.HW0017.GoodList gl = new com.ai.mapp.model.HW0017.GoodList();
				
				for(OrderDetail detail : order.getDetails())
				{
					com.ai.mapp.model.HW0017.Good d = new com.ai.mapp.model.HW0017.Good();
					
					long count = detail.getCounts() == null ? 0 :detail.getCounts();
					long d_fee = detail.getDiscount() == null ? 0 :detail.getDiscount();
					long s_fee = detail.getDiscount() == null ? 0 :detail.getDiscount();
					long r_fee = detail.getRealFee() == null ? 0 :detail.getRealFee();
					
					d.setCount(detail.getCounts() == null ? "0":detail.getCounts().toString());
					d.setDiscount(d_fee+"");
					d.setGoodId(detail.getGood() == null?"":detail.getGood().getId().toString());
					d.setGoodName(detail.getGood() == null?"":detail.getGood().getName().toString());
					d.setGoodPrice((float)r_fee/(float)count+"");
					d.setGoodSalePrice((float)s_fee/(float)count+"");
					d.setRealFee(detail.getRealFee() == null ? "0":detail.getRealFee().toString());
					d.setSaleFee(detail.getSaleFee() == null ? "0":detail.getSaleFee().toString());
					
					gl.addGood(d);
				}
				
				o.setGoodList(gl);
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
		
		com.ai.mapp.model.HW0017.Request req = com.ai.mapp.model.HW0017.Request
			.unmarshal(new StringReader((String)param.getParameter(BSSConstantParam.REQUESTCONTENT)));

		OrderInfo condition = new OrderInfo();
		condition.setStatus(req.getStatus());
		condition.setSerialNumber(req.getOrderCode());
		User agent = userService.loadUserByUserCode((String)param.getParameter(BSSConstantParam.USERCODE));
		condition.setCreator(agent);
		if(StringUtil.isEmpty(req.getGoodType()) == false)
		{
			condition.setType(req.getGoodType());
		}
//		if(StringUtils.isEmpty(req.getGoodId()) == false)
//		{
//			GoodsInfo good = new GoodsInfo();
//			if(StringUtils.isEmpty(req.getGoodId()) == false)
//				good.setId(Long.valueOf(req.getGoodId()));
//			
//			condition.setGood(good);
//		}
		
		int start = StringUtil.isEmpty(req.getStart())  ? 0 : Integer.valueOf(req.getStart());
		int pageSize = StringUtil.isEmpty(req.getSize()) ? 16 : Integer.valueOf(req.getSize());
		
		Collection<OrderInfo> orders = orderInfoService.listOrderInfos(condition, start, pageSize);
		
		param.setIfSuccess(true);
		param.setResult(orders);
		return param;
	}
	
}
