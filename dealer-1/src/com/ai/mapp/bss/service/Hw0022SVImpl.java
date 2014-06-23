package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.CommonBean;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.CommissionService;
import com.ai.mapp.sys.service.OrderInfoService;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.util.SYSConstant;

@Service(value="hw0022Service")
@Scope(value="singleton")
public class Hw0022SVImpl extends ISVTemplate {
	
//	@Autowired
//	private AgentOrderService agentOrderService;
	@Autowired
	private CommissionService commissionService;
	@Autowired
	private UserService userService;
	
	@Override
	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0022.Response rsp = new com.ai.mapp.model.HW0022.Response();
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);
		List<CommonBean> result = (List<CommonBean>)param.getResult();
		
		if(result != null && result.size() != 0){
			com.ai.mapp.model.HW0022.OrderTypeList orderTypeList = new com.ai.mapp.model.HW0022.OrderTypeList();
			
	        for (CommonBean bean:result) {
	        	com.ai.mapp.model.HW0022.OrderType orderType = new com.ai.mapp.model.HW0022.OrderType();
	            orderType.setValue(bean.getStr1());
	            orderType.setText(commissionService.getOrderTypeByChargeType(bean.getStr1(),(String)(param.getParameter(BSSConstantParam.LANGUAGE))));
	            orderType.setIncome(bean.getStr2());
	            orderType.setProductType(bean.getStr3() == null?"":SYSConstant.payTypes.get(bean.getStr3()) + param.getParameter(BSSConstantParam.LANGUAGE));
	            orderTypeList.addOrderType(orderType);
	        }
			
			rsp.setOrderTypeList(orderTypeList);
		}
		return rsp.toXMLString();
	}

	@Override
	public Object error(ParamObject param) throws Exception {
		return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR,param.getErrorInfo());
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		com.ai.mapp.model.HW0022.Request req = com.ai.mapp.model.HW0022.Request
			.unmarshal(new StringReader((String)param.getParameter(BSSConstantParam.REQUESTCONTENT)));
		
		Date startTime = null,endTime = null;
		
		if(StringUtil.isEmpty(req.getStartTime()) == false)
		{
			startTime = DateUtils.getDate(req.getStartTime()+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
		}
		
		if(StringUtil.isEmpty(req.getEndTime()) == false)
		{
			endTime = DateUtils.getDate(req.getEndTime()+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		}
		
		
		Long userId = Long.parseLong(param.getParameter(BSSConstantParam.USERID).toString());
		
//		Long userId = 1L;
		
		String userCode = req.getUserCode();
		if(StringUtil.isEmpty(userCode) == false){
			User user = userService.loadUserByUserCode(req.getUserCode());
			if(user != null){
				userId = user.getUserId();
			}
		}
//		Map<String,Long> result = agentOrderService.countIncomeByOrderType(userCode, req.getOrderType(),"",SYSConstant.PAY_STATUS_PAID, startTime, endTime);
		List<CommonBean> result = commissionService.countIncome(userId, req.getCommissionType(),req.getPayStatus(),req.getOrderType(), startTime, endTime);
		
		param.setResult(result);
		param.setIfSuccess(true);
		return param;
	}
	
}
