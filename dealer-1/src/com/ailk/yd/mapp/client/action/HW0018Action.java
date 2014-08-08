package com.ailk.yd.mapp.client.action;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.HwOrderShipment;
import com.ai.mapp.sys.service.HwOrderShipmentService;
import com.ai.mapp.sys.service.OrderInfoService;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0018Request;
import com.ailk.yd.mapp.client.model.HW0018Response;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-8-7 下午10:59:16
 * 类说明:库存订单确认收货
 */

@Service("hw0018")
@Action(bizcode = "hw0018", userCheck = false)
@Scope("prototype")
public class HW0018Action extends AbstractYDBaseActionHandler<HW0018Request, HW0018Response> {

	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private HwOrderShipmentService hwOrderShipmentService;
	
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		// TODO Auto-generated method stub
		String userCode = this.getUserinfo().getUserName();
		Integer uid = this.getUserinfo().getUserId();
		Long userId = Long.valueOf(uid.toString());
		System.out.println(userId);
		orderInfoService.changeOrderStatus(this.request.getOrderCode(), SYSConstant.ORDER_STATUS_SUCCESS,userId);
		
		//shipment information
		HwOrderShipment firstHwOrderShipment=hwOrderShipmentService.getFirstHwOrderShipmentByOrderCode(this.request.getOrderCode());
		if(firstHwOrderShipment!=null){
			HwOrderShipment hwOrderShipment=new HwOrderShipment();
			hwOrderShipment.setOrderId(firstHwOrderShipment.getOrderId());
			hwOrderShipment.setOrderCode(firstHwOrderShipment.getOrderCode());
			hwOrderShipment.setExpressNumber(firstHwOrderShipment.getExpressNumber());
			hwOrderShipment.setExpressCompanyCode(firstHwOrderShipment.getExpressCompanyCode());
			hwOrderShipment.setExpressCompanyName(firstHwOrderShipment.getExpressCompanyName());
			hwOrderShipment.setShipType(new Long(SYSConstant.SHIPMENT_TYPE_EXPRESS));
			hwOrderShipment.setShipStatus(new Long(SYSConstant.SHIPMENT_STATUS_SIGNED));
			hwOrderShipment.setCreateTime(new Date());
			if(StringUtils.isNotEmpty(userCode)){
				hwOrderShipment.setCreator(userCode);
			}
			hwOrderShipmentService.saveHwOrderShipment(hwOrderShipment);
		}
	}

	public OrderInfoService getOrderInfoService() {
		return orderInfoService;
	}

	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	public HwOrderShipmentService getHwOrderShipmentService() {
		return hwOrderShipmentService;
	}

	public void setHwOrderShipmentService(
			HwOrderShipmentService hwOrderShipmentService) {
		this.hwOrderShipmentService = hwOrderShipmentService;
	}
	
}
