package com.ai.mapp.order.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.mapp.base.Pager;
import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.action.BaseAction;
import com.ai.mapp.sys.entity.HwOrderShipment;
import com.ai.mapp.sys.entity.OrderDetail;
import com.ai.mapp.sys.entity.OrderInfo;
import com.ai.mapp.sys.entity.OrderItem;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.HwOrderShipmentService;
import com.ai.mapp.sys.service.OrderDetailService;
import com.ai.mapp.sys.service.OrderInfoService;
import com.ai.mapp.sys.service.OrderItemService;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.yd.mapp.model.YDDatapackage;
import com.ailk.yd.mapp.tibco.action.TibcoHandler;
import com.ailk.yd.mapp.tibco.model.YD0012.YD0012Request;
import com.ailk.yd.mapp.tibco.model.YD0012.YD0012Response;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-19 下午07:34:24
 * 类说明:
 */

public class OrderAction extends BaseAction{
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private HwOrderShipmentService hwOrderShipmentService;
	
	@Autowired
	protected TibcoHandler tibcoHandler;
	
	public OrderItemService getOrderItemService() {
		return orderItemService;
	}

	public void setOrderItemService(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	private List<OrderInfo> orderMains;
	private List<HwOrderShipment> hwOrderShipmentList;
	
	private OrderInfo orderMain;
	
	private List<OrderDetail> orderDetails;
	
	private OrderDetail orderDetail;
	
	private List<String> orderItems;
	
	private String orderItemBatchValues;//批量输入的字符串  如：00001--00010--10;00020--00080--61 from--end--total
	
	/**
	 * 是否可写   只读或可写  如：关于货物清单的修改
	 */
	private String isWrite;
	
	/**
	 * 录入item信息时用来保存和判断重复之用 
	 */
	private String orderItemValues;
	
	private String isHome;
	
	private Integer orderItemValueNum;//orderItem.jsp  已输入的value数
	
	/**
	 * <p>列出粗订单</p> 
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 */
	public String listOrderMain()throws Exception{
		if(length == 0 || length == 16){
			setLength(50);
		}
		if(orderMain == null){
			orderMain = new OrderInfo();
		}
		
		if(page == 0){
			page = 1;
		}
		page = (offset + 1 )/length + 1;
		orderMains = (List<OrderInfo>) orderInfoService.listOrderInfos(orderMain,  page, length);
//		if(orderMains.get(0).getDetails() != null){
//			
//		System.out.println(orderMains.get(0).getDetails().size());
//		}
		int count = orderInfoService.countOrderInfo(orderMain);
		setTotal(count);
		setPagerHeader(Pager.generate(getOffset(), (int) getTotal(),getLength(),"#order_main_div"));
		return SUCCESS;
	}
	
	/**
	 * <p>进入订单明细 </p> 
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 */
	public String orderDetail()throws Exception{
		if(orderMain == null || orderMain.getId() == null){
			if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
				throw new Exception("丢失订单信息");
			 }else{
				 throw new Exception("Lost order ID");
			 }
		}
		
		if(orderMain.getId()!=null){
			orderInfoService.processOrder(orderMain.getId());
		}
		
		OrderDetail detail = new OrderDetail();
		detail.setOrder(orderMain);
		orderMain = orderInfoService.loadOrderInfo(orderMain.getId());
		orderDetails = (List<OrderDetail>) orderDetailService.listOrderDetails(detail, -1, 0);
		
		HwOrderShipment hwOrderShipment=new HwOrderShipment();
		hwOrderShipment.setOrderId(orderMain.getId());
		hwOrderShipment.setPkIdOrderType(1);
		hwOrderShipmentList=(List<HwOrderShipment>)hwOrderShipmentService.listHwOrderShipments(hwOrderShipment, -1, 0);
		return SUCCESS;
	}
	
	/**
	 * <p>列出货物明细 </p> 
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 */
	public String orderItem()throws Exception{
		if(orderDetail == null || orderDetail.getId() == null || orderDetail.getGood() == null){
			if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
				throw new Exception("丢失订单信息或商品信息");
			 }else{
				 throw new Exception("Lost order ID or good ID");
			 }
		}
		OrderItem item = new OrderItem();
		item.setDetail(orderDetail);
		Map<String,Object> result = orderItemService.getItemsInfo(item);
		orderItems = (List<String>) result.get("frontValues");
		orderItemValues = (String) result.get("orderItemValues");
		orderItemBatchValues = (String) result.get("orderItemBatchValues");
		orderItemValueNum = (Integer)result.get("hadNum");
		return SUCCESS;
	}
	
	public String saveItems()throws Exception{
		if(StringUtil.isEmpty(orderItemValues) && StringUtil.isEmpty(orderItemBatchValues)){
			if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
				 return returnAjaxError("丢失order_item_value", false);
			 }else{
				 return returnAjaxError("Lost order_item_value", false);
			 }
		}else{
			try{
				orderItemService.saveItemsByValueString(orderDetail,orderItemValues,orderItemBatchValues,(User)getSessionValue(HTTP_SESSION_USER));
			}catch(Exception e){
				e.printStackTrace();
				return returnAjaxError(e.getMessage(), false);
			}
			
		}
		return returnAjaxSuccess("", false);
	}
	
	/**
	 * <p>保存发货信息 </p> 
	 * @param:        @return    
	 * @return:       String    
	 */
	public String saveSend()throws Exception{
		if(orderMain == null || orderMain.getId() == null){
			return returnAjaxError("lost orderInfo_id", false);
		}else{
			try{
//				orderItemService.saveItemsByValueString(orderDetail,orderItemValues);
				OrderInfo orderInfo = orderInfoService.loadOrderInfo(orderMain.getId());
				if(orderInfo == null){
					if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
						 return returnAjaxError("数据库订单丢失", false);
					 }else{
						 return returnAjaxError("Lost order in dataBase", false);
					 }
				}
				orderInfo.setStatus(SYSConstant.ORDER_STATUS_SEND);
				orderInfo.setSendTime(new Date());
				orderInfo.setExpressNumber(orderMain.getExpressNumber());
				orderInfo.setExpressCompanyNO(orderMain.getExpressCompanyNO());
				orderInfo.setStatusTime(new Date());
				orderInfo.setOperator((User)getSessionValue(HTTP_SESSION_USER));
//				orderInfoService.saveOrderInfo(orderInfo);
				orderInfoService.sendOrder(orderInfo);
				
				HwOrderShipment hwOrderShipment=new HwOrderShipment();
				hwOrderShipment.setOrderId(orderInfo.getId());
				hwOrderShipment.setOrderCode(orderInfo.getSerialNumber());
				hwOrderShipment.setExpressNumber(orderMain.getExpressNumber());
				hwOrderShipment.setExpressCompanyCode(orderMain.getExpressCompanyNO());
				hwOrderShipment.setExpressCompanyName(orderMain.getExpressCompanyName());
				hwOrderShipment.setShipType(new Long(SYSConstant.SHIPMENT_TYPE_EXPRESS));
				hwOrderShipment.setShipStatus(new Long(SYSConstant.SHIPMENT_STATUS_SENT));
				hwOrderShipment.setCreator(getLogincode());
				hwOrderShipment.setCreateTime(new Date());
				if(orderInfo.getCreator()!=null){
					hwOrderShipment.setRecipient(orderInfo.getCreator().getFirstName()+" "+orderInfo.getCreator().getLastName());
					hwOrderShipment.setRecipientTel(orderInfo.getCreator().getMobileNo());
					if(orderInfo.getCreator() != null)
					hwOrderShipment.setRecipientAddress(orderInfo.getCreator().getStreet()+", "+(orderInfo.getCreator().getCity() ==null ? "" : orderInfo.getCreator().getCity().getCityName()));
				}
				hwOrderShipmentService.saveHwOrderShipment(hwOrderShipment);
				
			}catch(Exception e){
				e.printStackTrace();
				return returnAjaxError(e.getMessage(), false);
			}
			
		}
		return returnAjaxSuccess("", false);
	}
	
	/**
	 * <p>批量保存item </p> 
	 * @param:        @return    
	 * @return:       String    
	 * @author        Zhengwj
	 * @Date          2012-12-5 下午09:58:14
	 */
	public String batchSave(){
		if(orderMain == null || orderMain.getId() == null){
			return returnAjaxError("lost orderInfo_id", false);
		}else{
			try{
//				orderItemService.saveItemsByValueString(orderDetail,orderItemValues);
				OrderInfo orderInfo = orderInfoService.loadOrderInfo(orderMain.getId());
				if(orderInfo == null){
					if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
						 return returnAjaxError("数据库订单丢失", false);
					 }else{
						 return returnAjaxError("Lost order in dataBase", false);
					 }
				}
				orderInfo.setStatus(SYSConstant.ORDER_STATUS_SEND);
				orderInfo.setSendTime(new Date());
				orderInfo.setExpressNumber(orderMain.getExpressNumber());
				orderInfo.setExpressCompanyNO(orderMain.getExpressCompanyNO());
				orderInfo.setStatusTime(new Date());
				orderInfo.setOperator((User)getSessionValue(HTTP_SESSION_USER));
//				orderInfoService.saveOrderInfo(orderInfo);
				orderInfoService.sendOrder(orderInfo);
			}catch(Exception e){
				e.printStackTrace();
				return returnAjaxError(e.getMessage(), false);
			}
			
		}
		return returnAjaxSuccess("", false);
	}

	public OrderInfoService getOrderInfoService() {
		return orderInfoService;
	}

	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	public List<OrderInfo> getOrderMains() {
		return orderMains;
	}

	public void setOrderMains(List<OrderInfo> orderMains) {
		this.orderMains = orderMains;
	}

	public OrderInfo getOrderMain() {
		return orderMain;
	}

	public void setOrderMain(OrderInfo orderMain) {
		this.orderMain = orderMain;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetailService getOrderDetailService() {
		return orderDetailService;
	}

	public void setOrderDetailService(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public List<String> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<String> orderItems) {
		this.orderItems = orderItems;
	}

	public String getOrderItemValues() {
		return orderItemValues;
	}

	public void setOrderItemValues(String orderItemValues) {
		this.orderItemValues = orderItemValues;
	}

	public String getIsWrite() {
		return isWrite;
	}

	public void setIsWrite(String isWrite) {
		this.isWrite = isWrite;
	}

	public String getIsHome() {
		return isHome;
	}

	public void setIsHome(String isHome) {
		this.isHome = isHome;
	}

	public String getOrderItemBatchValues() {
		return orderItemBatchValues;
	}

	public void setOrderItemBatchValues(String orderItemBatchValues) {
		this.orderItemBatchValues = orderItemBatchValues;
	}

	public Integer getOrderItemValueNum() {
		return orderItemValueNum;
	}

	public void setOrderItemValueNum(Integer orderItemValueNum) {
		this.orderItemValueNum = orderItemValueNum;
	}

	public HwOrderShipmentService getHwOrderShipmentService() {
		return hwOrderShipmentService;
	}

	public void setHwOrderShipmentService(
			HwOrderShipmentService hwOrderShipmentService) {
		this.hwOrderShipmentService = hwOrderShipmentService;
	}

	public List<HwOrderShipment> getHwOrderShipmentList() {
		return hwOrderShipmentList;
	}

	public void setHwOrderShipmentList(List<HwOrderShipment> hwOrderShipmentList) {
		this.hwOrderShipmentList = hwOrderShipmentList;
	}
	
	
	/**
	 * <p>描述:向Tibco发起订单 </p> 
	 * @return
	 * @throws Exception  
	 * @author        Zhengwj
	 * @Date          2014-5-12 下午05:36:10
	 */
	public String placeTibco(){
		try{
//			IUserinfo user = (IUserinfo)MappContext.getAttribute(MappContext.MAPPCONTEXT_USER);
			String sessionId = this.request.getSession().getId();
			
			OrderInfo order = orderInfoService.loadOrderInfo(orderMain.getId());
			YD0012Request ydReq = new YD0012Request();
			ydReq.setDealerId(order.getCreator().getUserId());
			ydReq.setOrderId(order.getId().toString());
			ydReq.setRecipient(order.getCreator().getFirstName()+" "+order.getCreator().getLastName());
			ydReq.setRecipientAddress(order.getCreator().getStreet()+", "+(order.getCreator().getCity() ==null ? "" : order.getCreator().getCity().getCityName()));
			ydReq.setRecipientTel(order.getCreator().getMobileNo());
			
			if(order.getDetails() != null || order.getDetails().size() != 0){
				Map<String,String> goodM = new HashMap<String, String>();
				for(OrderDetail detail:order.getDetails()){
					detail.getGood().getId();
					goodM.put(detail.getGood().getBssId(), detail.getCounts() + "");
				}
				ydReq.setGood(goodM);
			}
			
			YDDatapackage pkg =null;
//			YDDatapackage pkg = tibcoHandler.sendMsg("yd0012", ydReq, sessionId);
			YD0012Response ydResp = (YD0012Response)pkg.getBody();
			String sn = ydResp.getSn();
//			String sn = "goTo_tibco_1";
			orderInfoService.tibcoReplace(orderMain.getId(), sn);
		}catch(Exception e){
			e.printStackTrace();
			return returnAjaxError(e.getMessage(), false);
		}
		return returnAjaxSuccess("", false);
	}
	
}
