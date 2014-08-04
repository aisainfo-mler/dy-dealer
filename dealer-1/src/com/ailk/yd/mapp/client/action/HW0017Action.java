package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.OrderDetail;
import com.ai.mapp.sys.entity.OrderInfo;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.OrderInfoService;
import com.ai.mapp.sys.service.UserService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0017Request;
import com.ailk.yd.mapp.client.model.HW0017Response;

@Service("hw0017")
@Scope("prototype")
@Action(bizcode="hw0017",userCheck=true)
public class HW0017Action extends
		AbstractYDBaseActionHandler<HW0017Request, HW0017Response> {

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		
		HW0017Request req = request;
		
		OrderInfo condition = new OrderInfo();
		condition.setStatus(req.getStatus());
		condition.setSerialNumber(req.getOrderCode());
		
		User agent = userService.loadUserByUserCode(this.getUserinfo().getUserName());
//		String userCode = (String)MappContext.getAttribute(BSSConstantParam.USERCODE);
//		User agent = userService.loadUserByUserCode(userCode);
		condition.setCreator(agent);
		if(StringUtil.isEmpty(req.getGoodType()) == false)
		{
			condition.setType(req.getGoodType());
		}
		
		int start = StringUtil.isEmpty(req.getStart())  ? 0 : Integer.valueOf(req.getStart());
		int pageSize = StringUtil.isEmpty(req.getSize()) ? 16 : Integer.valueOf(req.getSize());
		
		Collection<OrderInfo> orders = orderInfoService.listOrderInfos(condition, start, pageSize);
		
		if(orders != null && orders.isEmpty() == false)
		{
			List os = new ArrayList();
			for(OrderInfo order : orders)
			{
				HW0017Response.Order  o = new HW0017Response.Order();
				
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
				
				os.add(o);
				
				if(order.getDetails() == null || order.getDetails().isEmpty())
					continue;
				
				List gl = new ArrayList();
				for(OrderDetail detail : order.getDetails())
				{
					HW0017Response.Order.Good d = new HW0017Response.Order.Good();
					
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
					
					gl.add(d);
				}
				
				o.setGoodList(gl);
			}
			
			response.setOrderList(os);
		}
	}

}
