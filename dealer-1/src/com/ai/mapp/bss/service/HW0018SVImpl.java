package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.HwOrderShipment;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.HwOrderShipmentService;
import com.ai.mapp.sys.service.OrderInfoService;
import com.ai.mapp.sys.util.SYSConstant;

@Service(value="hw0018Service")
@Scope(value="singleton")
public class HW0018SVImpl extends ISVTemplate {
	
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private HwOrderShipmentService hwOrderShipmentService;
	
	@Override
	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0018.Response rsp = new com.ai.mapp.model.HW0018.Response();
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);
		return rsp.toXMLString();
	}

	@Override
	public Object error(ParamObject param) throws Exception {
		return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR,param.getErrorInfo());
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		com.ai.mapp.model.HW0018.Request req = com.ai.mapp.model.HW0018.Request
			.unmarshal(new StringReader((String)param.getParameter(BSSConstantParam.REQUESTCONTENT)));
		
		String userCode = (String)param.getParameter(BSSConstantParam.USERCODE);
		Long userId = Long.parseLong((String)param.getParameter(BSSConstantParam.USERID));
		orderInfoService.changeOrderStatus(req.getOrderCode(), SYSConstant.ORDER_STATUS_SUCCESS,userId);
		
		//shipment information
		HwOrderShipment firstHwOrderShipment=hwOrderShipmentService.getFirstHwOrderShipmentByOrderCode(req.getOrderCode());
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
		
		/**
		 * 改变order_item_info表里的status
		 */
		param.setIfSuccess(true);
		param.setResult(req.getOrderCode());
		return param;
	}
	
}
