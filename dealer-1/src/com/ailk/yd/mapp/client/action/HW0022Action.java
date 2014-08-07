package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.sys.entity.CommonBean;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.CommissionService;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0022Request;
import com.ailk.yd.mapp.client.model.HW0022Response;

@Service("hw0022")
@Action(bizcode = "hw0022", userCheck = false)
@Scope("prototype")
public class HW0022Action extends
		AbstractYDBaseActionHandler<HW0022Request, HW0022Response> {

	@Autowired
	private UserService userService;
	@Autowired
	private CommissionService commissionService;

	@SuppressWarnings("unused")
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		final HW0022Request req = request;
		Date startTime = null, endTime = null;

		if (StringUtil.isEmpty(req.getStartTime()) == false) {
			startTime = DateUtils.getDate(req.getStartTime() + " 00:00:00",
					"yyyy-MM-dd HH:mm:ss");
		}

		if (StringUtil.isEmpty(req.getEndTime()) == false) {
			endTime = DateUtils.getDate(req.getEndTime() + " 23:59:59",
					"yyyy-MM-dd HH:mm:ss");
		}
		IUserinfo ui = this.getUserinfo();
		Long userId = Long.valueOf(ui.getUserId());
		String userCode = req.getUserCode();
		if (StringUtil.isEmpty(userCode) == false) {
			User user = userService.loadUserByUserCode(req.getUserCode());
			if (user != null) {
				userId = user.getUserId();
			}
		}
		List<CommonBean> result = commissionService.countIncome(userId,
				req.getCommissionType(), req.getPayStatus(),
				req.getOrderType(), startTime, endTime);
 
		if (result != null && result.size() != 0) {
			List<HW0022Response.OrderType> orderTypeList = new ArrayList<HW0022Response.OrderType>();
			for (CommonBean bean : result) {
			 
				HW0022Response.OrderType orderType = new HW0022Response.OrderType();
				orderType.setValue(bean.getStr1());
				//orderType.setText(commissionService.getOrderTypeByChargeType(bean.getStr1(), (String)(bean.getParameter(BSSConstantParam.LANGUAGE))));
				orderType.setText(commissionService.getOrderTypeByChargeType(bean.getStr1(), SYSConstant.LANGUAGE_ENGLISH));
				orderType.setProductType(bean.getStr3() == null ? "": SYSConstant.payTypes.get(bean.getStr3())+SYSConstant.LANGUAGE_ENGLISH);
				
				orderType.setIncome(bean.getStr2());
			//	orderType.setProductType(bean.getStr3() == null ? "": SYSConstant.payTypes.get(bean.getStr3())+ param.getParameter(BSSConstantParam.LANGUAGE));
				orderTypeList.add(orderType);
			}
			response.setList(orderTypeList);
		}

	}
}
