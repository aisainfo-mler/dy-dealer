package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.GoodsInfo;
import com.ai.mapp.sys.entity.OrderDetail;
import com.ai.mapp.sys.entity.OrderInfo;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.OrderInfoService;
import com.ai.mapp.sys.service.UserService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0020Request;
import com.ailk.yd.mapp.client.model.HW0020Request.Good;
import com.ailk.yd.mapp.client.model.HW0020Response;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-7 下午02:04:39
 * 类说明:代理商下订单  如：批卡----处未支付状态  不知道这时要不要订单下到TIBCO处  不
 */

@Service("hw0020")
@Action(bizcode="hw0020",userCheck=true)
@Scope("prototype")
public class HW0020Action extends AbstractYDBaseActionHandler<HW0020Request, HW0020Response>{
	@Autowired
	private OrderInfoService orderInfoService;

	@Autowired
	private UserService userService;
	
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		
//		String userCode = (String)MappContext.getAttribute(BSSConstantParam.USERCODE);
		User user = userService.loadUserByUserCode(this.getUserinfo().getUserName());
		
//		User user = userService.loadUserByUserCode(userCode);
		
		OrderDetail orderDetail = null;
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		List<Good> goodList = this.request.getGoodList();
		if(goodList != null && goodList.isEmpty() == false){
			for(Good g:goodList){
				orderDetail = new OrderDetail();
				orderDetail.setCounts(g.getCount());
				orderDetail.setGood(new GoodsInfo(g.getGoodId()));
				orderDetail.setCreateTime(new Date());
				list.add(orderDetail);
			}
			
			OrderInfo orderInfo = orderInfoService.generateOrder(list, user);
			this.response.setOrderCode(orderInfo.getSerialNumber());
		}
	}

	public OrderInfoService getOrderInfoService() {
		return orderInfoService;
	}

	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}
	
}
