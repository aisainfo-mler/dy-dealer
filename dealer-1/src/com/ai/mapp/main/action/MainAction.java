package com.ai.mapp.main.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.mapp.base.Pager;
import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.action.BaseAction;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.CommissionRule;
import com.ai.mapp.sys.entity.CommonBean;
import com.ai.mapp.sys.entity.HwState;
import com.ai.mapp.sys.entity.OrderInfo;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.ChartViewService;
import com.ai.mapp.sys.service.HwStateService;
import com.ai.mapp.sys.service.OrderInfoService;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.util.SYSConstant;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-10-5 下午06:39:57
 * 类说明:
 */

public class MainAction extends BaseAction{
	@Autowired
	private AgentOrderService agentOrderService;
	@Autowired
	private UserService mappUserService;
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private ChartViewService chartViewService;
	@Autowired
	private HwStateService hwStateService;
	
	private List<CommonBean> agentTopList;
	
	private List<OrderInfo> orderList;
	
	private List<User> dealerList;
	
	private List<CommonBean> topSaleMobileList;
	
	private List<CommonBean> areaProfileList;
	
	private List<OrderInfo> orderMains;
	
	private String ifThisMonth;//1:是  0:否
	
	private Long creatorId;
	
	private List<User> customerList;

	private String stateCode;
	
	private String cityCode;
	
	private List<HwState> states;
	
	public List<HwState> getStates() {
		return states;
	}

	public void setStates(List<HwState> states) {
		this.states = states;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public OrderInfoService getOrderInfoService() {
		return orderInfoService;
	}

	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	public ChartViewService getChartViewService() {
		return chartViewService;
	}

	public void setChartViewService(ChartViewService chartViewService) {
		this.chartViewService = chartViewService;
	}

	public List<CommonBean> getAgentTopList() {
		return agentTopList;
	}

	public void setAgentTopList(List<CommonBean> agentTopList) {
		this.agentTopList = agentTopList;
	}

	public List<OrderInfo> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderInfo> orderList) {
		this.orderList = orderList;
	}
	
	public List<CommonBean> getTopSaleMobileList() {
		return topSaleMobileList;
	}

	public void setTopSaleMobileList(List<CommonBean> topSaleMobileList) {
		this.topSaleMobileList = topSaleMobileList;
	}

	public AgentOrderService getAgentOrderService() {
		return agentOrderService;
	}

	public void setAgentOrderService(AgentOrderService agentOrderService) {
		this.agentOrderService = agentOrderService;
	}

	public List<User> getDealerList() {
		return dealerList;
	}

	public void setDealerList(List<User> dealerList) {
		this.dealerList = dealerList;
	}
	
	public List<CommonBean> getAreaProfileList() {
		return areaProfileList;
	}

	public void setAreaProfileList(List<CommonBean> areaProfileList) {
		this.areaProfileList = areaProfileList;
	}

	public List<OrderInfo> getOrderMains() {
		return orderMains;
	}

	public void setOrderMains(List<OrderInfo> orderMains) {
		this.orderMains = orderMains;
	}

	public String main()throws Exception{
		//获得最多发展人
//		getTopAgent(0,5);
		
		//代理商信息表
//		User userCondition = new User();
//		userCondition.setStatus(SYSConstant.STATE_WAITING_4_AUDIT);
//		userCondition.setOrderBy("desc:createTime;");
//		getDealerReview(userCondition,1,5);
		
//		//order approve
//		OrderInfo orderInfo = new OrderInfo();
//		getOrderApprove(orderInfo,1,5);
		
		//top sale
//		getTopSale(null,0,5);
		
		//2013-01-19  获得所有省
		HwState stateCondtion = new HwState();
		stateCondtion.setFlag(SYSConstant.STATE_VALID);
		states = (List<HwState>) hwStateService.listAllHwState(stateCondtion);
		return SUCCESS;
	}
	
	public String listInventoryOrder() throws Exception {
		OrderInfo orderInfo = new OrderInfo();
		getOrderApprove(orderInfo,1,5);
		return "INVENTORY_ORDER_LIST";
	}
	
	public String dealerReview()throws Exception{
		User userCondition = new User();
		userCondition.setStatus(SYSConstant.STATE_WAITING_4_AUDIT);
		userCondition.setOrderBy("desc:createTime;");
		getDealerReview(userCondition,1,5);
		
		return SUCCESS;
	}
	
	public String topDealerChart()throws Exception{
		return returnAjaxSuccess(chartViewService.getTopDealers(0, 5), true);
	}
	
	
	public String typeDealersChart()throws Exception{
		return returnAjaxSuccess(chartViewService.statisticsDealerByMonths(stateCode,""), true);
	}
	
	public String bestPackageChart()throws Exception{
		return returnAjaxSuccess(chartViewService.getBestPackageByMonth(stateCode,""), true);
	}
	
	public String topDealerDetail()throws Exception{
		Date startTime = null;
		Date endTime = null;
		if("1".equals(ifThisMonth)){
			String thisMonth = DateUtils.getDateString("yyyyMM");
			startTime = DateUtils.getDate(thisMonth+"01 00:00:00", "yyyyMMdd HH:mm:ss");
			endTime = DateUtils.getDate(thisMonth + DateUtils.getDays(thisMonth) + " 23:59:59", "yyyyMMdd HH:mm:ss");
		}
		
//		if(length == 0 || length == 16){
//			setLength(5);
//		}
//		
//		if(page == 0){
//			page = 1;
//		}
//		page = (offset + 1 )/length + 1;
//		Map<String,Object> result = userService.getUsersByCreator(creatorId, startTime, endTime, page, length);
		Map<String,Object> result = mappUserService.getUsersByCreator(creatorId, startTime, endTime, -1, 0);
		customerList =  (List<User>) result.get("list");
//		int count = (Integer) result.get("count");
//		setTotal(count);
//		setPagerHeader(Pager.generate(getOffset(), (int) getTotal(),getLength(),"#common_backDiv"));
		
		return SUCCESS;
	}
	
	private List<CommonBean> getTopAgent(int start,int limit)throws Exception{
		agentTopList = null;
		User user = new User();
		user.setStatus(SYSConstant.STATE_VALID);
		agentTopList = mappUserService.getTopUserAgent(user, start, limit);
		return agentTopList;
	}
	
	private List<User> getDealerReview(User userCondition,int start,int limit)throws Exception{
		if(userCondition == null){
			userCondition = new User();
		}
		
		if(StringUtil.isEmpty(userCondition.getStatus())){
			userCondition.setStatus("!" + SYSConstant.STATE_WAITING_4_AUDIT);
		}
		dealerList = (List<User>) mappUserService.listUsers(userCondition, start, limit);
		return dealerList;
	}
	
	private List<CommonBean> getTopSale(AgentOrder order,int start,int limit)throws Exception{
		if(order == null){
			order = new AgentOrder();
		}
		topSaleMobileList = agentOrderService.getTopSaleByProduct(order, start, limit);
		return topSaleMobileList;
	}
	
	private List<CommonBean> getAreaProfiles(AgentOrder order,int start,int limit)throws Exception{
		if(order == null){
			order = new AgentOrder();
		}
		areaProfileList = agentOrderService.getIncomeByCity(order, start, limit);
		return areaProfileList;
	}
	
	public String areaProfilesChart()throws Exception{
		return returnAjaxSuccess(chartViewService.getAreaProfiles(0, 5), true);
	}
	
	public String orderPieChart()throws Exception{
		return returnAjaxSuccess(chartViewService.getOrderTypePie(stateCode,""), true);
	}
	
	private List<OrderInfo>  getOrderApprove(OrderInfo orderInfo,int start,int limit)throws Exception{
		if(orderInfo == null){
			orderInfo = new OrderInfo();
		}
		orderMains = (List<OrderInfo>) orderInfoService.listOrderInfos(orderInfo,  start, limit);
		return orderMains;
	}

	public String getIfThisMonth() {
		return ifThisMonth;
	}

	public void setIfThisMonth(String ifThisMonth) {
		this.ifThisMonth = ifThisMonth;
	}

	public List<User> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<User> customerList) {
		this.customerList = customerList;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	
}
