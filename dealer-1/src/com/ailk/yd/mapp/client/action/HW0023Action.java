package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.Commission;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.CommissionService;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0023Request;
import com.ailk.yd.mapp.client.model.HW0023Response;
import com.ailk.yd.mapp.client.model.HW0023Response.Order;

@Service("hw0023")
@Action(bizcode = "hw0023", userCheck = false)
@Scope("prototype")
public class HW0023Action extends
		AbstractYDBaseActionHandler<HW0023Request, HW0023Response> {

	@Autowired
	private UserService userService;
	@Autowired
	private CommissionService commissionService;

	@SuppressWarnings("unused")
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		Date startTime = null, endTime = null;
		final HW0023Request req = request;

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

		int start = StringUtil.isEmpty(req.getStart()) ? 0 : Integer
				.valueOf(req.getStart());
		int pageSize = StringUtil.isEmpty(req.getSize()) ? 16 : Integer
				.valueOf(req.getSize());

		List<Commission> result = commissionService
				.listIncomeDetailByOrderType(userId, req.getCommissionType(),
						req.getPayStatus(), req.getOrderType(), startTime,
						endTime, start, pageSize);
		List<HW0023Response.Order> orderList = new ArrayList<HW0023Response.Order>();
		if (result != null && result.size() != 0) {

			for (Commission tmp : result) {
				HW0023Response.Order order = new HW0023Response.Order();
				order.setIncome(tmp.getPay() == null ? "0" : tmp.getPay()
						.toString());
				order.setSaleFee("");
				order.setOrderCode(tmp.getAgentOrder().getOrderCode());
				order.setOrderType(tmp.getAgentOrder().getOrderType());
				order.setRealFee("");
				order.setCommissionCode(tmp.getCommissionCode() == null ? ""
						: tmp.getCommissionCode().toString());
				order.setCommissionId(tmp.getId() == null ? "" : tmp.getId()
						.toString());
				order.setComment(tmp.getComment() == null ? "" : tmp
						.getComment());
				order.setPayStatus(tmp.getPayStatus() == null ? "0" : tmp
						.getPayStatus());
				order.setPayTime(tmp.getPayTime() == null ? "" : DateUtils
						.getDateString(tmp.getPayTime(), "dd/MM/yyyy HH:mm:ss"));
				order.setChargeType(commissionService.getOrderTypeByChargeType(
						tmp.getChargeType(), SYSConstant.LANGUAGE_ENGLISH));
				order.setCreateTime(tmp.getCreateTime() == null ? ""
						: DateUtils.getDateString(tmp.getCreateTime(),
								"dd/MM/yyyy HH:mm:ss"));
				orderList.add(order);
			}
		}
		response.setList(orderList);
	}
}
