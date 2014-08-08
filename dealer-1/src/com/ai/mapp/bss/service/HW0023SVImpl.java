package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.Commission;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.CommissionService;
import com.ai.mapp.sys.service.UserService;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-26 下午07:13:30
 * 类说明:
 */

@Service(value="hw0023Service")
@Scope(value="singleton")
public class HW0023SVImpl extends ISVTemplate {

//	@Autowired
//	private AgentOrderService agentOrderService;
	@Autowired
	private CommissionService commissionService;
	
	@Autowired
	private UserService userService;
	
	@Override
	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0023.Response rsp = new com.ai.mapp.model.HW0023.Response();
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);
		List<Commission> result = (List<Commission>)param.getResult();
		
		if(result != null && result.size() != 0){
			com.ai.mapp.model.HW0023.OrderList orderList = new com.ai.mapp.model.HW0023.OrderList();
			com.ai.mapp.model.HW0023.Order order = null;
			for(Commission tmp:result){
				order = new com.ai.mapp.model.HW0023.Order();
				order.setIncome(tmp.getPay() == null?"0":tmp.getPay().toString());
				order.setSaleFee("");
				order.setOrderCode(tmp.getAgentOrder().getOrderCode());
				order.setOrderType(tmp.getAgentOrder().getOrderType());
				order.setRealFee("");
				order.setCommissionCode(tmp.getCommissionCode() == null?"":tmp.getCommissionCode().toString());
				order.setCommissionId(tmp.getId() == null?"":tmp.getId().toString());
				order.setComment(tmp.getComment() == null?"":tmp.getComment());
				order.setPayStatus(tmp.getPayStatus() == null?"0":tmp.getPayStatus());
				order.setPayTime(tmp.getPayTime() == null?"":DateUtils.getDateString(tmp.getPayTime(), "dd/MM/yyyy HH:mm:ss"));
				order.setChargeType(commissionService.getOrderTypeByChargeType(tmp.getChargeType(),(String)param.getParameter(BSSConstantParam.LANGUAGE)));
				order.setCreateTime(tmp.getCreateTime() == null?"":DateUtils.getDateString(tmp.getCreateTime(), "dd/MM/yyyy HH:mm:ss"));
				
				orderList.addOrder(order);
			}
			
			rsp.setOrderList(orderList);
		}else{
			com.ai.mapp.model.HW0023.OrderList orderList = new com.ai.mapp.model.HW0023.OrderList();
			rsp.setOrderList(orderList);
		}
		return rsp.toXMLString();
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0023.Request req = com.ai.mapp.model.HW0023.Request
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
		
		String userCode = req.getUserCode();
		if(StringUtil.isEmpty(userCode) == false){
			User user = userService.loadUserByUserCode(req.getUserCode());
			if(user != null){
				userId = user.getUserId();
			}
		}
		
		int start = StringUtil.isEmpty(req.getStart())  ? 0 : Integer.valueOf(req.getStart());
		int pageSize = StringUtil.isEmpty(req.getSize()) ? 16 : Integer.valueOf(req.getSize());
		
	//	List<AgentOrder> result = agentOrderService.listIncomeDetailByOrderType(userCode, req.getOrderType(), "", startTime, endTime, start, pageSize);
		List<Commission> result = commissionService.listIncomeDetailByOrderType(userId, req.getCommissionType(),req.getPayStatus(),req.getOrderType(), startTime, endTime, start, pageSize);
		
		param.setResult(result);
		param.setIfSuccess(true);
		return param;
	}

}
