package com.ai.mapp.menu.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.mapp.base.action.BaseAction;
import com.ai.mapp.sys.entity.OrderInfo;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.OrderInfoService;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.util.SYSConstant;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-24 下午04:40:48
 * 类说明:
 */

public class MenuAction extends BaseAction{
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private UserService userService;
	
	private Integer orderWaitCounts;
	
	private Integer userWaitCounts;
	
	/**
	 * <p>待办</p> 
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 */
	public String waitDo()throws Exception{
		OrderInfo info = new OrderInfo();
		info.setStatus(SYSConstant.ORDER_STATUS_WAIT_AUDIT);
		orderWaitCounts = orderInfoService.countOrderInfo(info);
		
		User user = new User();
		user.setStatus(SYSConstant.STATE_WAITING_4_AUDIT);
		userWaitCounts = userService.countUser(user);
		
		return SUCCESS;
	}

	public OrderInfoService getOrderInfoService() {
		return orderInfoService;
	}

	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Integer getOrderWaitCounts() {
		return orderWaitCounts;
	}

	public void setOrderWaitCounts(Integer orderWaitCounts) {
		this.orderWaitCounts = orderWaitCounts;
	}

	public Integer getUserWaitCounts() {
		return userWaitCounts;
	}

	public void setUserWaitCounts(Integer userWaitCounts) {
		this.userWaitCounts = userWaitCounts;
	}
	
}
