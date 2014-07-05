package com.ai.mapp.sys.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.entity.ResultJson;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.sys.dao.OrderInfoDao;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.CommonBean;
import com.ai.mapp.sys.entity.GoodsInfo;
import com.ai.mapp.sys.entity.HwOrderShipment;
import com.ai.mapp.sys.entity.OrderDetail;
import com.ai.mapp.sys.entity.OrderInfo;
import com.ai.mapp.sys.entity.SvnInfo;
import com.ai.mapp.sys.entity.User;

import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.SYSConstant;
import com.ai.mapp.sys.entity.OrderItem;
import com.ailk.ts.dal.ibatis.model.Repository;
import com.ailk.ts.dal.ibatis.model.SkuEntity;
import com.ailk.ts.ibatis.service.RepOptRecordService;
import com.ailk.ts.ibatis.service.RepositoryService;
import com.ailk.ts.ibatis.service.SkuEntityService;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-19 下午07:24:02
 * 类说明:
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class OrderInfoService {
	public final Log log = LogFactory.getLog(OrderInfoService.class);
	
	@Autowired
	private OrderInfoDao orderInfoDao;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private GoodsInfoService goodsInfoService;
	
	@Autowired
	private AccountInfoService accountInfoService;
	
	@Autowired
	private HwOrderShipmentService hwOrderShipmentService;
	
	@Autowired
	private SkuEntityService skuEntityService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	public Collection<OrderInfo> listOrderInfos(OrderInfo orderInfo,int start,int limit)throws Exception{
		try{
			log.debug(orderInfo==null?"orderInfo is null":orderInfo.toString());
			
			Collection<OrderInfo> c = null;
			if(start < 0){
				c = orderInfoDao.listAll(orderInfo);
			}else{
				c = orderInfoDao.list(orderInfo, start, limit);
			}
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveOrderInfo(OrderInfo orderInfo)throws Exception{
		log.debug(orderInfo==null?"orderInfo is null":orderInfo.toString());
		orderInfoDao.save(orderInfo);
	}
	
	public void deleteOrderInfo(OrderInfo orderInfo)throws Exception{
		log.debug(orderInfo==null?"orderInfo is null":orderInfo.toString());
		orderInfoDao.delete(orderInfo);
	}
	
	public int countOrderInfo(OrderInfo orderInfo) throws Exception{
		return orderInfoDao.count(orderInfo);
	}
	
	public OrderInfo loadOrderInfo(Long id)throws Exception{
		return orderInfoDao.get(id);
	}
	
	public OrderInfo loadOrderInfoByOrderCode(String orderCode)throws Exception{
		OrderInfo condition = new OrderInfo(orderCode);
		Collection<OrderInfo> orders =  orderInfoDao.list(condition, 0, 1);
		
		if(orders == null || orders.isEmpty())
			return null;
		
		return orders.iterator().next();
		
	}
	
	public void changeOrderStatus(String orderCode,String status,Long optId) throws Exception
	{
		if(StringUtil.isEmpty(orderCode))
			throw new Exception(LanguageInfo.ORDERCODE_IS_EMPTY);
		
		OrderInfo order = loadOrderInfoByOrderCode(orderCode);
		
		if(order == null)
			throw new Exception(orderCode+" " + LanguageInfo.ORDER_UNEXIST);
		
		order.setStatus(status);
		
		saveOrderInfo(order);
		
		//设置item为可用状态
		orderItemService.setOrderItemHasReceived(order.getSerialNumber());
		
		List<OrderDetail> orderDetails = order.getDetails();
		List<String> imeis = new ArrayList<String>();
		if(orderDetails != null && orderDetails.size() != 0){
			List<OrderItem> items = null;
			for(OrderDetail detail:orderDetails){
				items = detail.getItems();
				if(items != null && items.size() != 0){
					continue;
				}
				for(OrderItem item:items){
					imeis.add(item.getItemValue());
				}
			}
		}
		if(imeis.size() != 0){//代理商确认收货
			//获得代理商仓库
			List<Repository> reps = repositoryService.getRepsByUserId(optId);
			if(reps == null || reps.size() == 0){
				throw new Exception("该代理商无仓库，请建仓库");
			}
			skuEntityService.updateSkuEntityStatusByImeis(SYSConstant.SKU_STATUS_CHANNEL, imeis, order.getId(), optId, SYSConstant.SELL_DETAIL_OPTTYPE_TIBCO_2_CHANNEL, reps.get(0).getRepCode());
		}
		
	}
	
	public void sendOrder(OrderInfo order)throws Exception{
		//先更改item的临时置为发货状态
		List<OrderDetail> details = order.getDetails();
		if(details != null){
			for(OrderDetail detail:details){
				orderItemService.updateStatusByDetailId(detail.getId(), SYSConstant.ITEM_STATUS_SENDING);
			}
		}
		//再改定单
		saveOrderInfo(order);
		//入库SKU_ENTITY
		saveSkuEntityByOrderId(order, order.getOperator().getUserId());
	}
	
	
	
	public OrderInfo generateOrder(List<OrderDetail> details,User creator)throws Exception{
		OrderInfo order = null;
		if(details != null && details.size() != 0){
			OrderDetail detail = null;
			BigDecimal saleFee = new BigDecimal(0);
			BigDecimal realFee = new BigDecimal(0);
			OrderInfo info = new OrderInfo();
			for(OrderDetail detailTmp:details){
				if(detailTmp.getGood() != null && detailTmp.getGood().getId() != null){
					GoodsInfo good = goodsInfoService.loadGoodsInfo(detailTmp.getGood().getId());
					
					if(good == null){
						throw new Exception(LanguageInfo.LOST_GOOD_IN_DATA + "!");
					}else{
						info.setType(good.getType());
						BigDecimal count = new BigDecimal(detailTmp.getCounts() == null ?0:detailTmp.getCounts());
						BigDecimal unitPrice = new BigDecimal(good.getSalePrice() == null?0:good.getSalePrice());
						BigDecimal discount = new BigDecimal(0);
						
						BigDecimal c = unitPrice.multiply(count);
						BigDecimal unitRealPrice = unitPrice.subtract(discount);
						BigDecimal e = unitRealPrice.multiply(count);
						
						saleFee = saleFee.add(c);
						realFee = realFee.add(e);
						detailTmp.setSaleFee(c.longValue());
						detailTmp.setRealFee(e.longValue());
						
						realFee.add(new BigDecimal(detailTmp.getRealFee()== null ? 0:detailTmp.getRealFee()));
					}
				}
			}
			info.setPlaceTibco(SYSConstant.ORDER_PLACE_TIBCO_NO);
			info.setStatus(SYSConstant.ORDER_STATUS_WAIT_PAY);//待支付状态
			info.setCreator(creator);
			info.setCreateTime(new Date());
			info.setSerialNumber(DateUtils.getDateString("yyyyMMddhhmmss")+Math.round(5) + SYSConstant.ORDER_TYPE_MOBILE_SIM);
			info.setFee(saleFee.longValue());
			info.setRealFee(realFee.longValue());
			
			saveOrderInfo(info);
			order = info;
			for(OrderDetail detailTmp:details){
				detailTmp.setOrder(info);
				orderDetailService.saveOrderDetail(detailTmp);
			}
				
				
		}
		return order;
		
	}
	
	public void payOrderInfo(String orderCode,String payMode,String voucherNo) throws Exception
	{
		if(StringUtil.isEmpty(orderCode))
			throw new Exception(LanguageInfo.ORDERCODE_IS_EMPTY);
		
		OrderInfo order = loadOrderInfoByOrderCode(orderCode);
		
		if(order == null)
			throw new Exception(orderCode+" " + LanguageInfo.ORDER_UNEXIST);
		
		if(SYSConstant.ORDER_STATUS_WAIT_PAY.equals(order.getStatus()) == false)
			throw new Exception(orderCode+" " + LanguageInfo.ORDER_NOT_WAIT_PAY);
		/**
		 * 填写支付方式，流水号，支付时间，状态
		 */
		order.setPayMode(payMode);
		order.setPayTime(new Date());
		order.setStatus(SYSConstant.ORDER_STATUS_WAIT_AUDIT);
		saveOrderInfo(order);
		
		//TODO 预存池扣款
		if(SYSConstant.PAY_MODE_ACCOUNT.equals(order.getPayMode()))
			accountInfoService.payOrderInfoFromAccount(order.getSerialNumber());
		
		
	}

	/**
	 * <p>取消定单 </p> 
	 * @param:        @param orderCode
	 * @param:        @return    
	 * @return:       ResultJson    
	 * @author        Zhengwj
	 * @Date          2012-12-11 下午05:28:21
	 */
	public ResultJson cancelOrder(String orderCode,String userCode)throws Exception {
		ResultJson result = new ResultJson();
		result.setFlag(true);
		
		if(StringUtil.isEmpty(orderCode)){
			result.setFlag(false);
			result.setMsg(LanguageInfo.ORDERCODE_IS_EMPTY);
			return result;
		}
			
		
		OrderInfo order = loadOrderInfoByOrderCode(orderCode);
		
		if(order == null){
			result.setFlag(false);
			result.setMsg(orderCode+" " + LanguageInfo.ORDER_UNEXIST);
			return result;
		}
		
		/**
		 * Dealer 订货订单 状态 Inventory Order Status
		 * 0:待支付 Waiting for payment
		 * 1:待确认 Waiting for confirmation
		 * 2:已确认 Confirmed
		 * 3:已出库 Shipped
		 * 4:交易成功 Closed
		 * 5:已作废 Canceled
	 	 * 6:退货中 Request for return
	  	 * 7:已退货 Returned
		 */
		Map<String, String> cannotCancelStatus = new HashMap<String, String>(); 
		cannotCancelStatus.put(SYSConstant.ORDER_STATUS_SUCCESS, SYSConstant.orderTypes.get(SYSConstant.ORDER_STATUS_SUCCESS));
		cannotCancelStatus.put(SYSConstant.ORDER_STATUS_CANCEL, SYSConstant.orderTypes.get(SYSConstant.ORDER_STATUS_CANCEL));
		cannotCancelStatus.put(SYSConstant.ORDER_STATUS_SEND, SYSConstant.orderTypes.get(SYSConstant.ORDER_STATUS_SEND));
		
		if(cannotCancelStatus.get(order.getStatus()) != null){
			result.setFlag(false);
			result.setMsg(orderCode+" " + LanguageInfo.STATUS_OF_ORDER_IS + " " + cannotCancelStatus.get(order.getStatus()));
			return result;
		}
		
		/**
		 * 如果已经向TIBCO发送了
		 */
		if(SYSConstant.ORDER_PLACE_TIBCO_YES.equals(order.getPlaceTibco()))
		{
			result.setFlag(false);
//			result.setMsg(orderCode+" " + LanguageInfo.ORDER_IS_PLACE_TIBCO_YES);
			return result;
		}
		
		
		
		/**
		 * 在发货之前OrderItem表中无库存信息，库存由发货时，tibco回调接口进行入库
		 */
		List<OrderDetail> details = order.getDetails();
		if(details != null){
			for(OrderDetail detail:details){
				orderItemService.updateStatusByDetailId(detail.getId(), SYSConstant.ITEM_STATUS_DELETE);
			}
		}
		
		order.setStatus(SYSConstant.ORDER_STATUS_CANCEL);
		saveOrderInfo(order);
		
		
		
		/**
		 * 发货之前无物流信息，发货后不能进行退货
		 */
//		HwOrderShipment hwOrderShipment=new HwOrderShipment();
//		hwOrderShipment.setOrderId(order.getId());
//		hwOrderShipment.setOrderCode(order.getSerialNumber());
//		hwOrderShipment.setShipType(new Long(SYSConstant.SHIPMENT_TYPE_EXPRESS));
//		hwOrderShipment.setShipStatus(new Long(SYSConstant.SHIPMENT_STATUS_CANCEL));
//		hwOrderShipment.setCreateTime(new Date());
//		if(StringUtil.isNotEmpty(userCode)){
//			hwOrderShipment.setCreator(userCode);
//		}
//		HwOrderShipment firstHwOrderShipment=hwOrderShipmentService.getFirstHwOrderShipmentByOrderCode(orderCode);
//		if(firstHwOrderShipment!=null){
//			hwOrderShipment.setExpressNumber(firstHwOrderShipment.getExpressNumber());
//			hwOrderShipment.setExpressCompanyCode(firstHwOrderShipment.getExpressCompanyCode());
//			hwOrderShipment.setExpressCompanyName(firstHwOrderShipment.getExpressCompanyName());
//		}
//		hwOrderShipmentService.saveHwOrderShipment(hwOrderShipment);
		
		return result;
	}
	
	public void processOrder(Long orderId) throws Exception {
		String hql="update OrderInfo obj set obj.status='2' where obj.status='1' and obj.id = "+orderId;
		orderInfoDao.excute(hql, null);
	}
	
	/**
	 * <p>描述:向TIBCO发起订单 将其回调的定单号沉淀到数据库 </p> 
	 * @param orderId
	 * @throws Exception  
	 * @author        Zhengwj
	 * @Date          2014-5-7 下午08:35:36
	 */
	public void tibcoReplace(Long orderId,String sn)throws Exception{
		OrderInfo order = orderInfoDao.get(orderId);
		/**
		 * 大状态值 不变，仍为 processing
		 */
		order.setPlaceTibco(SYSConstant.ORDER_PLACE_TIBCO_YES);
		order.setBssOrderCode(sn);
		orderInfoDao.save(order);
	}
	
	
	/**
	 * <p>描述:  TIBCO确认处理定单后发起订单回调操作</p> 
	 * @param order 定单
	 * @param sendTime 发货
	 * @param expressNo 快递号
	 * @param expressCompany 快递公司
	 * @param expressFee 快递费
	 * @throws Exception  
	 * @author        Zhengwj
	 * @Date          2014-5-7 下午08:52:42
	 */
	public void tibcoSendOrder(OrderInfo order,List<OrderItem> items,Date sendTime,String expressNo,String expressCompany,BigDecimal expressFee)throws Exception{
		//先更改item的临时置为发货状态
		List<OrderDetail> details = order.getDetails();
		Map<Long,Long> goodId_detailId_map = new HashMap<Long,Long>();
		if(details != null && details.isEmpty() == false){
			for(OrderDetail detail:details){
				if(detail.getGood() != null && goodId_detailId_map.get(detail.getGood().getId()) == null){
					goodId_detailId_map.put(detail.getGood().getId(), detail.getId());
				}
//				orderItemService.updateStatusByDetailId(detail.getId(), SYSConstant.ITEM_STATUS_SENDING);
			}
		}
		if(items != null && items.isEmpty() == false){
			for(OrderItem item:items){
//				System.out.println(item.getGood().getId() + ":" + goodId_detailId_map.get(item.getGood().getId()));
				item.setDetail(new OrderDetail(goodId_detailId_map.get(item.getGood().getId())));
				item.setStatus(SYSConstant.ITEM_STATUS_SENDING);
				item.setAgent(order.getCreator());
				item.setCreateTime(new Date());
				orderItemService.saveOrderItem(item);
			}
		}
		//生成物流信息
		HwOrderShipment hwOrderShipment=new HwOrderShipment();
		hwOrderShipment.setOrderId(order.getId());
		hwOrderShipment.setOrderCode(order.getSerialNumber());
		hwOrderShipment.setExpressNumber(expressNo);
//		hwOrderShipment.setExpressCompanyCode(expressCompany);
		hwOrderShipment.setExpressCompanyName(expressCompany);
		hwOrderShipment.setShipType(new Long(SYSConstant.SHIPMENT_TYPE_EXPRESS));
		hwOrderShipment.setShipStatus(new Long(SYSConstant.SHIPMENT_STATUS_SENT));
//		hwOrderShipment.setCreator(getLogincode());
		hwOrderShipment.setCreateTime(new Date());
		if(order.getCreator()!=null){
			hwOrderShipment.setRecipient(order.getCreator().getFirstName()+" "+order.getCreator().getLastName());
			hwOrderShipment.setRecipientTel(order.getCreator().getMobileNo());
			if(order.getCreator() != null)
			hwOrderShipment.setRecipientAddress(order.getCreator().getStreet()+", "+(order.getCreator().getCity() ==null ? "" : order.getCreator().getCity().getCityName()));
		}
		hwOrderShipmentService.saveHwOrderShipment(hwOrderShipment);
		//再改定单
		order.setStatus(SYSConstant.ORDER_STATUS_SEND);
		order.setExpressCharge(expressFee == null?null:expressFee.longValue());
		order.setExpressCompanyName(expressCompany);
		order.setExpressNumber(expressNo);
		order.setSendTime(sendTime);
		saveOrderInfo(order);
//		System.out.println(000000000);
	}
	
	/**
	 * <p>获得本月order订单的饼图 </p> 
	 * @param:        @param cityCode
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       List<CommonBean>    
	 * @author        Zhengwj
	 * @Date          2013-1-17 下午07:46:26
	 */
	public List<CommonBean> getOrderPieByTypeThisMonth(String stateCode,String cityCode)throws Exception{
		OrderInfo condition = new OrderInfo();
		
		String thisMonth = DateUtils.getDateString("yyyyMM");
		condition.setStartTime(DateUtils.getDate(thisMonth+"01 00:00:00", "yyyyMMdd HH:mm:ss"));
		condition.setEndTime(DateUtils.getDate(thisMonth + DateUtils.getDays(thisMonth) + " 23:59:59", "yyyyMMdd HH:mm:ss"));
		/**
		 * 统计本月订单数
		 */
		Map<String,CommonBean> result_total = orderInfoDao.getOrderNumByTypeAndCity(condition,stateCode,cityCode);
		
		/**
		 * 统计本月未完成订单数
		 */
		condition.setStatus( "!" + SYSConstant.ORDER_STATUS_SUCCESS + "," + SYSConstant.ORDER_STATUS_CANCEL);
		Map<String,CommonBean> result_pending = orderInfoDao.getOrderNumByTypeAndCity(condition,stateCode,cityCode);
		return combineOrderPie(result_total, result_pending);
	}
	
	private List<CommonBean> combineOrderPie(Map<String,CommonBean> result_total,Map<String,CommonBean> result_pending)throws Exception{
		if(result_total != null && !result_total.isEmpty()){
			Set<String> keySet = result_total.keySet();
			List<CommonBean> result = new ArrayList<CommonBean>();
			CommonBean tmpCom = null;
			String key = null;
	        for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
	            key = (String) it.next();
	            tmpCom = result_total.get(key);
	            if(result_pending != null && !result_pending.isEmpty() && result_pending.get(key) != null){
	            	 tmpCom.setStr3(result_pending.get(key).getStr2());//未完成订单
	            	 tmpCom.setStr4(((new BigDecimal(result_total.get(key).getStr2())).subtract(new BigDecimal(result_pending.get(key).getStr2()))).toString());//已完成的订单
	            }else{
	            	 tmpCom.setStr3("0");
	            	 tmpCom.setStr4(tmpCom.getStr2());
	            }
	            result.add(tmpCom);
	        }
	        return result;
		}else{
			return new ArrayList<CommonBean>();
		}
	}
	
	public Map<String,Object> getItemsInfoByOrderId(String orderCode)throws Exception{
		OrderInfo orderInfo = loadOrderInfoByOrderCode(orderCode);
		List<OrderDetail> details = orderInfo.getDetails();
		String detailIds_sim = "";
		List<CommonBean> mobiles = null;
		Map<Long,String> goodTranslate = new HashMap<Long, String>();
		Map<String,Object> result = new HashMap<String, Object>();
		CommonBean mobile = null;
		List<OrderItem> items = null;
		for(OrderDetail detail:details){
			goodTranslate.put(detail.getGood().getId(),detail.getGood().getName());
			if(SYSConstant.GOOD_TYPE_MOBILE.equals(detail.getGood().getType())){
				if(mobiles == null){
					mobiles = new ArrayList<CommonBean>();
				}
				items = detail.getItems();
				if(items != null && items.isEmpty() == false){
					for(OrderItem item:items){
						mobile = new CommonBean();
						mobile.setStr1(detail.getGood().getId() + "");
						mobile.setStr5(item.getItemValue());
						mobile.setStr6(SYSConstant.GOOD_TYPE_MOBILE);
						mobile.setStr7(detail.getGood().getName());
						mobiles.add(mobile);
					}
				}
			}else if(SYSConstant.GOOD_TYPE_SIM.equals(detail.getGood().getType())){
				detailIds_sim += "," + detail.getId();
			}
		}
		if(detailIds_sim.startsWith(",")){
			detailIds_sim = detailIds_sim.substring(1);
		}
		List<CommonBean> cards = null;
		if(StringUtils.isNotEmpty(detailIds_sim)){
			cards = orderInfoDao.getCardSequence(detailIds_sim);
		}
		result.put("goodMap", goodTranslate);
		
		if(cards != null && cards.isEmpty() == false){
			if(mobiles != null && mobiles.isEmpty() == false){
				cards.addAll(mobiles);
			}
			result.put("list", cards);
		}else if(mobiles != null && mobiles.isEmpty() == false){
			result.put("list", mobiles);
		}
		return result;
	}

	public OrderInfoDao getOrderInfoDao() {
		return orderInfoDao;
	}

	public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}

	public OrderItemService getOrderItemService() {
		return orderItemService;
	}

	public void setOrderItemService(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	public OrderDetailService getOrderDetailService() {
		return orderDetailService;
	}

	public void setOrderDetailService(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}

	public GoodsInfoService getGoodsInfoService() {
		return goodsInfoService;
	}

	public void setGoodsInfoService(GoodsInfoService goodsInfoService) {
		this.goodsInfoService = goodsInfoService;
	}

	public AccountInfoService getAccountInfoService() {
		return accountInfoService;
	}

	public void setAccountInfoService(AccountInfoService accountInfoService) {
		this.accountInfoService = accountInfoService;
	}

	public HwOrderShipmentService getHwOrderShipmentService() {
		return hwOrderShipmentService;
	}

	public void setHwOrderShipmentService(
			HwOrderShipmentService hwOrderShipmentService) {
		this.hwOrderShipmentService = hwOrderShipmentService;
	}

	public SkuEntityService getSkuEntityService() {
		return skuEntityService;
	}

	public void setSkuEntityService(SkuEntityService skuEntityService) {
		this.skuEntityService = skuEntityService;
	}
	
	public void saveSkuEntityByOrderId(OrderInfo order,Long optId) throws Exception{
		if(order != null && StringUtils.equals(order.getPlaceTibco(), "1") ){
			List<OrderDetail> details = order.getDetails();
			if(details != null && details.isEmpty() == false){
				SkuEntity entity = null;
				List<SkuEntity> entities = new ArrayList<SkuEntity>();
				List<OrderItem> items = null;
				for(OrderDetail detail:details){
					items = detail.getItems();
					if(items != null && items.isEmpty() == false){
						for(OrderItem item:items){
							entity = new SkuEntity();
							entity.setImei(item.getItemValue());
							entity.setModifyTime(com.ailk.butterfly.core.util.DateUtils.getCurrent());
							entity.setRepositoryCode(SYSConstant.REP_CODE_TIBCO);//目前存在于TIBCO仓库,始于TIBCO仓库
							entity.setTargetRepcode(SYSConstant.REP_CODE_TIBCO);//目前存在于TIBCO仓库,始于TIBCO仓库
							entity.setSkuid(detail.getGood().getId());
							entity.setStatus(SYSConstant.SKU_STATUS_TIBCO);
//							entity.setOper
							entities.add(entity);
						}
					}
				}
				if(entities != null && entities.isEmpty() == false){
					skuEntityService.insertSkuEntites(entities, optId);
				}
			}
		}else{
			throw new Exception("订单不存在，或订单未向tibco确认");
		}
		
		
		
	}
	
}
