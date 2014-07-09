package com.ailk.yd.mapp.client.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.OrderDetail;
import com.ai.mapp.sys.entity.OrderInfo;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.OrderInfoService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.ErrorCodeDefine;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.butterfly.mapp.core.model.IBody;
import com.ailk.yd.mapp.client.model.HW0021Request;
import com.ailk.yd.mapp.tibco.model.YD0012.YD0012Request;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-7 下午02:40:50
 * 类说明:库存支付订单---延用原21接口
 */

@Service("hw0021")
@Action(bizcode="hw0021",userCheck=true)
@Scope("prototype")
public class HW0021Action extends AbstractYDBaseActionHandler<HW0021Request, IBody>{
	@Autowired
	private OrderInfoService orderInfoService; 

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		if(StringUtils.isEmpty(this.request.getOrderCode())){
			throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,"订单流水号为空");
		}
		orderInfoService.payOrderInfo(this.request.getOrderCode(), this.request.getPayMode(), this.request.getVoucherNo());
		User user = (User)MappContext.getAttribute(MappContext.MAPPCONTEXT_USER);
		OrderInfo order = orderInfoService.loadOrderInfoByOrderCode(this.request.getOrderCode());
		YD0012Request ydReq = new YD0012Request();
		ydReq.setDealerId(user.getUserId());
		Map<Long,Long> goodMap = new HashMap<Long, Long>();
		if(order.getDetails() != null && order.getDetails().isEmpty() == false){
			for(OrderDetail detail:order.getDetails()){
//				goodMap.put(detail.getGood().getBssId(), value)
			}
		}
//		goodMap.put(this.request.get, value)
		
	}

	public OrderInfoService getOrderInfoService() {
		return orderInfoService;
	}

	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

}
