package com.ai.mapp.sys.service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.MoneyUtils;
import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.entity.ResultJson;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.sys.dao.AgentOrderDao;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.CommonBean;
import com.ai.mapp.sys.entity.OrderItem;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.entity.SvnInfo;
import com.ai.mapp.sys.entity.SvnProduct;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.SYSConstant;

@Service
@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
public class AgentOrderService {
	
	public final Log log = LogFactory.getLog(AgentOrderService.class);
	
	@Autowired
	private SvnInfoService svnInfoService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private  OrderItemService orderItemService;
	
	@Autowired
	private  SvnProductService svnProductService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountInfoService accountInfoService;
	
	@Autowired
	private CommissionService commissionService;
	
	@Autowired
	private CommissionRuleService commissionRuleService;
	
	@Autowired
	private HwStateService hwStatService;
	
	@Autowired
	private AgentOrderDao agentOrderDao;
	
	@Autowired
	private SmallLocalFileService slfs;

	private Class<CommonBean> engine;
	
	public Collection<AgentOrder> listAgentOrders(AgentOrder agentOrder,int start,int limit){
		try{
			log.debug(agentOrder==null?"agentOrder is null":agentOrder.toString());
			
			Collection<AgentOrder> c = null;
			if(start < 0){
				c = agentOrderDao.listAll(agentOrder);
			}else{
				c = agentOrderDao.list(agentOrder, start, limit);
			}
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection<AgentOrder> listAllAgentOrders(AgentOrder agentOrder){
		try{
			log.debug(agentOrder==null?"agentOrder is null":agentOrder.toString());
			
			Collection<AgentOrder> c = agentOrderDao.listAll(agentOrder);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveAgentOrder(AgentOrder agentOrder)throws Exception{
		log.debug(agentOrder==null?"agentOrder is null":agentOrder.toString());
		agentOrderDao.save(agentOrder);
	}
	
	public void deleteAgentOrder(AgentOrder agentOrder)throws Exception{
		log.debug(agentOrder==null?"agentOrder is null":agentOrder.toString());
		agentOrderDao.delete(agentOrder);
	}
	
	public int countAgentOrder(AgentOrder agentOrder) throws Exception {
		return agentOrderDao.count(agentOrder);
	}
	
	public AgentOrder loadAgentOrder(Long id)throws Exception{
		return agentOrderDao.get(id);
	}
	
	public AgentOrder loadAgentOrderByOrderCode(String orderCode)throws Exception{
		AgentOrder condition = new AgentOrder(orderCode);
		Collection<AgentOrder> orders =  agentOrderDao.list(condition, 0, 1);
		
		if(orders == null || orders.isEmpty())
			return null;
		
		return orders.iterator().next();
		
	}
	
	/**
	 * 充值并且生成订单
	 * @param svn
	 * @param amount
	 * @param payMode
	 * @param voucherNo
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public AgentOrder createRechargeOrderByAgent(AgentOrder order) throws Exception
	{

		SvnInfo svnInfo = null;
		
		/**
		 * order.getOptType() == 2时为带手机号的直接充值，order.getOptType()==3时为通过PIN号充值
		 */
		if("3".equals(order.getOptType()) && StringUtil.isEmpty(order.getSvn()))
		{
			order.setPin(""+System.currentTimeMillis()+Math.round(5));
		}
//		else if("2".equals(order.getOptType()) && StringUtil.isEmpty(order.getSvn()) == false)
//		{
//			svnInfo = svnInfoService.loadSvnInfoBySvn(order.getSvn(),SYSConstant.ITEM_STATUS_USED);
//			
//			if(svnInfo == null)
//				throw new Exception(LanguageInfo.PHONENUM_UNEXIST);
//			
//			//设置该号码的余额
//			order.setBlance(svnInfo.getAmount());
//		}
		
		/** 设置价格 **/
		order.setNumberFee(Long.valueOf(0));//号码费用
		order.setSimFee(Long.valueOf(0));//SIM卡费
		Long saleFee = order.getSimFee()+order.getNumberFee()+order.getPackageFee();
		order.setSaleFee(saleFee);
		
		/** 充值动作等支付完成后操作 **/
//		svnInfo.setAmount(( svnInfo.getAmount() == null ? 0 :svnInfo.getAmount() )+order.getSaleFee());
		
//		svnInfoService.saveSvnInfo(svnInfo);
		
//		float rate = SYSConstant.discountTypes.get(SYSConstant.AGENT_ORDER_TYPE_RECHARGE);
//		float discountFee = order.getSaleFee()*rate/100;
		Map<String,String> variantMap = new HashMap<String,String>();
		BigDecimal fee = (new BigDecimal(order.getSaleFee()));
		variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDER_AMOUNT, fee.toString());
		variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDERTYPE, SYSConstant.AGENT_ORDER_TYPE_RECHARGE);
		
		//agent rule
		Long agentId=null;
		if(order.getCreator()!=null){
			User agentUser = userService.loadUserByUserCode(order.getCreator().getUserCode());
			agentId=agentUser.getUserId();
		}
		
		BigDecimal discountFeeB = commissionRuleService.getImmediateCommissionValue(variantMap,agentId);
		String orderCode = DateUtils.getDateString("yyyyMMddhhmmss")+Math.round(5) + SYSConstant.AGENT_ORDER_TYPE_TOPUP;
		Date now = new Date();
		
		order.setCompleteTime(now);
		order.setCreateTime(now);
		order.setDesc((order.getDesc() == null?"":order.getDesc())+ " Recharge "+((float)order.getSaleFee())+ "元");
		Long df = (long) discountFeeB.intValue();
		order.setDiscountFee(df);
		if(order.getSaleFee().longValue() == 0){
			order.setDiscountRate((float)0);
		}else{
			order.setDiscountRate(discountFeeB.divide(new BigDecimal(order.getSaleFee()),3,BigDecimal.ROUND_HALF_UP).floatValue());
		}
		order.setOrderCode(orderCode);
		order.setOrderType(SYSConstant.AGENT_ORDER_TYPE_RECHARGE);
		order.setPayStatus(SYSConstant.PAY_STATUS_NOT_PAID);
		order.setPayTime(now);
		order.setPreStore((long)0);
		Long sf = (long) (order.getSaleFee().intValue()-discountFeeB.intValue());
		order.setRealFee(sf);
		order.setSaleFee(order.getSaleFee());
		order.setStatus(SYSConstant.AGENT_ORDER_STATUS_WAITTING);
			
		saveAgentOrder(order);
		
		return order;
	}
	
	/**
	 * 开户并且生成订单
	 * @param svn
	 * @param amount
	 * @param payMode
	 * @param voucherNo
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public AgentOrder createNewOrderByAgent(AgentOrder order,User user) throws Exception
	{
		if(StringUtil.isEmpty(order.getSvn()))
			throw new Exception(LanguageInfo.PHONENUM_UNEXIST);
		
		if(user == null)
			throw new Exception(LanguageInfo.USER_NOT_EXIST);
		
		Date now = new Date();
		if(StringUtil.isEmpty(order.getSim()) == false)
		{
			OrderItem item = orderItemService.loadOrderItemByValue(order.getSim());
			if(item != null)
			{
				item.setStatus(SYSConstant.ITEM_STATUS_TEMP);
				orderItemService.saveOrderItem(item);
			}
		}
		
		Product product = productService.loadProduct(order.getProduct().getRangeId());
		
		if(product == null)
			throw new Exception(LanguageInfo.PRODUCT_UNEXIST);
		
		/** 设置价格 **/
		Long number_fee = 0L;
		
		order.setNumberFee(number_fee);//号码费用
		order.setSimFee(0L);//SIM卡费
//		Long saleFee = order.getSimFee()+order.getNumberFee()+order.getPackageFee();
//		order.setSaleFee(saleFee);
		
//		float rate = SYSConstant.discountTypes.get(SYSConstant.AGENT_ORDER_TYPE_NEW);
//		float discountFee = order.getSaleFee()*rate/100;
		Map<String,String> variantMap = new HashMap<String,String>();
//		BigDecimal fee = (new BigDecimal(order.getSaleFee()).divide(new BigDecimal(1000)));
		BigDecimal fee = (new BigDecimal(order.getSaleFee()));
		variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDER_AMOUNT, fee.toString());
		variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDERTYPE, SYSConstant.AGENT_ORDER_TYPE_NEW);
		
		//agent rule
		Long agentId=null;
		if(order.getCreator()!=null){
			User agentUser = userService.loadUserByUserCode(order.getCreator().getUserCode());
			agentId=agentUser.getUserId();
		}
		
//		BigDecimal discountFeeB = commissionRuleService.getImmediateCommissionValue(variantMap,agentId).multiply(new BigDecimal(1000));
		BigDecimal discountFeeB = BigDecimal.valueOf(commissionRuleService.getImmediateCommissionValue(variantMap,agentId).longValue());
		
		String orderCode = DateUtils.getDateString("yyyyMMddhhmmss")+Math.round(5) + SYSConstant.AGENT_ORDER_TYPE_NEW;
		
		order.setCompleteTime(now);
		order.setCreateTime(now);
		order.setDesc("New "+order.getSvn());
		order.setDiscountFee(discountFeeB.longValue());
		if(order.getSaleFee().longValue() == 0){
			order.setDiscountRate((float)0);
		}else{
			order.setDiscountRate(discountFeeB.divide(new BigDecimal(order.getSaleFee()),3,BigDecimal.ROUND_HALF_UP).floatValue());
		}
		order.setOrderCode(orderCode);
		order.setOrderType(SYSConstant.AGENT_ORDER_TYPE_NEW);
		order.setPayStatus(SYSConstant.PAY_STATUS_NOT_PAID);
		order.setPayTime(now);
		order.setPreStore(product.getPrestore() == null?0:product.getPrestore());
		order.setRealFee((new BigDecimal(order.getSaleFee())).subtract(discountFeeB).longValue());
		order.setStatus(SYSConstant.AGENT_ORDER_STATUS_WAITTING);
//		order.setBlance(svnInfo.getAmount());
		order.setUpdateTime(now);
		order.setCreator(user);
		
		saveAgentOrder(order);
		
//		if(StringUtil.isEmpty(order.getImei()) == false)
//			orderItemService.useItemTemp(order.getCreator().getUserId(),SYSConstant.GOOD_TYPE_MOBILE,order.getImei());
//		
//		if(StringUtil.isEmpty(order.getSim()) == false)
//			orderItemService.useItemTemp(order.getCreator().getUserId(),SYSConstant.GOOD_TYPE_SIM,order.getSim());
		
		return order;
	}
	
	/**
	 * 补卡并且生成订单
	 * @param svn
	 * @param amount
	 * @param payMode
	 * @param voucherNo
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public AgentOrder createSimCardOrderByAgent(AgentOrder order) throws Exception
	{
		if(StringUtil.isEmpty(order.getSvn()))
			throw new Exception(LanguageInfo.PHONENUM_MUST_TYPE);

		SvnInfo svnInfo = svnInfoService.loadSvnInfoBySvn(order.getSvn(),SYSConstant.ITEM_STATUS_USED);
		
		if(svnInfo == null)
			throw new Exception(LanguageInfo.PHONENUM_UNEXIST);
		
		/** 设置价格 **/
		order.setNumberFee(Long.valueOf(0));//号码费用
		order.setSimFee(Long.valueOf(5000));//SIM卡费
		order.setPackageFee(Long.valueOf(0));
		Long saleFee = order.getSimFee()+order.getNumberFee();
		order.setSaleFee(saleFee);
		
//		float rate = SYSConstant.discountTypes.get(SYSConstant.AGENT_ORDER_TYPE_SIMCARD);
//		float discountFee = order.getSaleFee()*rate/100;
		Map<String,String> variantMap = new HashMap<String,String>();
		BigDecimal fee = (new BigDecimal(order.getSaleFee()).divide(new BigDecimal(1000)));
		variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDER_AMOUNT, fee.toString());
		variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDERTYPE, SYSConstant.AGENT_ORDER_TYPE_SIMCARD);
		
		//agent rule
		Long agentId=null;
		if(order.getCreator()!=null){
			if(order.getCreator().getUserId()!=null){
				agentId=order.getCreator().getUserId();
			}else{
				User agentUser = userService.loadUserByUserCode(order.getCreator().getUserCode());
				agentId=agentUser.getUserId();
			}
		}
		
		BigDecimal discountFeeB = commissionRuleService.getImmediateCommissionValue(variantMap,agentId).multiply(new BigDecimal(1000));
		String orderCode = DateUtils.getDateString("yyyyMMddhhmmss")+Math.round(5) + SYSConstant.AGENT_ORDER_TYPE_SIMCARD;
		Date now = new Date();
		
		order.setCompleteTime(now);
		order.setCreateTime(now);
		order.setDesc("SimCard "+order.getSvn());
		order.setDiscountFee(discountFeeB.longValue());
		if(order.getSaleFee().longValue() == 0){
			order.setDiscountRate((float)0);
		}else{
			order.setDiscountRate(discountFeeB.divide(new BigDecimal(order.getSaleFee()),3,BigDecimal.ROUND_HALF_UP).floatValue());
		}
		order.setOrderCode(orderCode);
		order.setOrderType(SYSConstant.AGENT_ORDER_TYPE_SIMCARD);
		order.setPayStatus(SYSConstant.PAY_STATUS_NOT_PAID);
		order.setPayTime(now);
		order.setPreStore((long)0);
		order.setRealFee((new BigDecimal(order.getSaleFee())).subtract(discountFeeB).longValue());
		order.setStatus(SYSConstant.AGENT_ORDER_STATUS_WAITTING);
		order.setBlance(svnInfo.getAmount());
		order.setUpdateTime(now);
		
//		if(StringUtil.isEmpty(order.getSim()) == false)
//		{
//			OrderItem item = orderItemService.loadOrderItemByValue(order.getSim());
//			if(item != null)
//			{
//				item.setStatus(SYSConstant.ITEM_STATUS_USED);
//				orderItemService.saveOrderItem(item);
//			}
//		}

		saveAgentOrder(order);
		
		if(StringUtil.isEmpty(order.getSim()) == false)
			orderItemService.useItemTemp(order.getCreator().getUserId(),SYSConstant.GOOD_TYPE_SIM,order.getSim());
		
		return order;
	}
	
	/**
	 * 叠加包生成订单
	 * @param order
	 * @throws Exception
	 */
	public void createAddOnOrder(AgentOrder order)  throws Exception{
		
		
		Map<String,String> variantMap = new HashMap<String,String>();
		BigDecimal fee = (new BigDecimal(order.getSaleFee()));
		variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDER_AMOUNT, fee.toString());
		variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDERTYPE, SYSConstant.AGENT_ORDER_TYPE_RECHARGE);
		
		//agent rule
		Long agentId=null;
		if(order.getCreator()!=null){
			if(order.getCreator().getUserId()!=null){
				agentId=order.getCreator().getUserId();
			}else{
				User agentUser = userService.loadUserByUserCode(order.getCreator().getUserCode());
				agentId=agentUser.getUserId();
			}
		}
		
		BigDecimal discountFeeB = commissionRuleService.getImmediateCommissionValue(variantMap,agentId);
		
		
		String orderCode = DateUtils.getDateString("yyyyMMddhhmmss")+Math.round(5) + SYSConstant.AGENT_ORDER_TYPE_RECHARGE;
		Date now = new Date();
		
		order.setCompleteTime(now);
		order.setCreateTime(now);
		order.setDesc("addOn "+order.getSvn());
		order.setDiscountFee(discountFeeB.longValue());
		if(order.getSaleFee().longValue() == 0){
			order.setDiscountRate((float)0);
		}else{
			order.setDiscountRate(discountFeeB.divide(new BigDecimal(order.getSaleFee()),3,BigDecimal.ROUND_HALF_UP).floatValue());
		}
		order.setOrderCode(orderCode);
		order.setOrderType(SYSConstant.AGENT_ORDER_TYPE_RECHARGE);
		order.setPayStatus(SYSConstant.PAY_STATUS_NOT_PAID);
		order.setPayTime(now);
//		order.setPreStore((long)0);
		order.setRealFee((new BigDecimal(order.getSaleFee())).subtract(discountFeeB).longValue());
		order.setStatus(SYSConstant.AGENT_ORDER_STATUS_WAITTING);
//		order.setBlance(svnInfo.getAmount());
		order.setUpdateTime(now);

		saveAgentOrder(order);
	}
	
	
	public AgentOrder createTopUpOrder(AgentOrder order)  throws Exception{
		
		
		Map<String,String> variantMap = new HashMap<String,String>();
		BigDecimal fee = (new BigDecimal(order.getSaleFee()));
		variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDER_AMOUNT, fee.toString());
		variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDERTYPE, SYSConstant.AGENT_ORDER_TYPE_TOPUP);
		
		//agent rule
		Long agentId=null;
		if(order.getCreator()!=null){
			if(order.getCreator().getUserId()!=null){
				agentId=order.getCreator().getUserId();
			}else{
				User agentUser = userService.loadUserByUserCode(order.getCreator().getUserCode());
				agentId=agentUser.getUserId();
			}
		}
		
		BigDecimal discountFeeB = commissionRuleService.getImmediateCommissionValue(variantMap,agentId);
		
		
		String orderCode = DateUtils.getDateString("yyyyMMddhhmmss")+Math.round(5) + SYSConstant.AGENT_ORDER_TYPE_SIMCARD;
		Date now = new Date();
		
		order.setCompleteTime(now);
		order.setCreateTime(now);
		order.setDesc("topUp "+order.getSvn()+" "+order.getSaleFee());
		Long df = (long) discountFeeB.intValue();
		order.setDiscountFee(df);
		if(order.getSaleFee().longValue() == 0){
			order.setDiscountRate((float)0);
		}else{
			order.setDiscountRate(discountFeeB.divide(new BigDecimal(order.getSaleFee()),3,BigDecimal.ROUND_HALF_UP).floatValue());
		}
		order.setOrderCode(orderCode);
		order.setOrderType(SYSConstant.AGENT_ORDER_TYPE_TOPUP);
		order.setPayStatus(SYSConstant.PAY_STATUS_PAID);
		order.setPayTime(now);
//		order.setPreStore((long)0);
		Long rf = (long) (order.getSaleFee().intValue()-discountFeeB.intValue());
		order.setRealFee(rf);
		order.setStatus(SYSConstant.AGENT_ORDER_STATUS_WAITTING);
//		order.setBlance(svnInfo.getAmount());
		order.setUpdateTime(now);

		saveAgentOrder(order);
		return order;
	}
	
	public void changeOrderStatus(String orderCode,String status) throws Exception
	{
		if(StringUtil.isEmpty(orderCode))
			throw new Exception(LanguageInfo.ORDERCODE_IS_EMPTY);
		
		AgentOrder order = loadAgentOrderByOrderCode(orderCode);
		
		if(order == null)
			throw new Exception(orderCode+" " + LanguageInfo.ORDER_UNEXIST);
		
		order.setStatus(status);
		
		saveAgentOrder(order);
	}
	
	/**
	 * 订单完成
	 * @param orderCode
	 * @param goods
	 * @throws Exception
	 */
	public void completedOrder(String orderCode,Map<String,String> goods) throws Exception
	{
		if(StringUtil.isEmpty(orderCode))
			throw new Exception(LanguageInfo.ORDERCODE_IS_EMPTY);
		
		AgentOrder order = loadAgentOrderByOrderCode(orderCode);
		
		if(order == null)
			throw new Exception(orderCode+" " + LanguageInfo.ORDER_UNEXIST);
		
		order.setStatus(SYSConstant.AGENT_ORDER_STATUS_COMPLETE);
		
		/** svnInfo相关数据不进行保存 **/
//		SvnInfo svnInfo = svnInfoService.loadSvnInfoBySvn(order.getSvn(), null);
//		
//		/**
//		 * 新开的时候会在svn_product表中增加记录
//		 */
//		if(SYSConstant.AGENT_ORDER_TYPE_NEW.equals(order.getOrderType()))
//		{
//			auditOrder(order);
//			svnInfo.setStatus(SYSConstant.STATE_VALID);
//			svnInfoService.saveSvnInfo(svnInfo);
//			
//		}
//		
//		if (svnInfo != null && order.getProduct() != null
//				&& order.getProduct().getPrestore() != null
//				&& order.getProduct().getPrestore() >= 0) 
//		{
//			Long amount = svnInfo.getAmount() == null ? 0 : svnInfo.getAmount().longValue();
//			Long preStore = order.getProduct().getPrestore() == null ? 0 : order.getProduct().getPrestore().longValue()*1000;
//			svnInfo.setAmount(amount+preStore);
//			svnInfoService.saveSvnInfo(svnInfo);
//		}
		
		/** 由于卡号是后录入的，因此在完成之前将sim号存入订单 **/
		if(goods != null && goods.isEmpty() == false)
		{
			if(StringUtil.isEmpty(goods.get(SYSConstant.GOOD_TYPE_SIM)) == false)
			{
				order.setSim(goods.get(SYSConstant.GOOD_TYPE_SIM));
				saveAgentOrder(order);
			}
			else if(StringUtil.isEmpty(goods.get("2")) == false)
			{
				order.setSim(goods.get("2"));
				saveAgentOrder(order);
			}
		}
		
		if(StringUtil.isEmpty(order.getImei()) == false)
			orderItemService.useItem(order.getCreator().getUserId(),SYSConstant.GOOD_TYPE_MOBILE,order.getImei());
		
		if(StringUtil.isEmpty(order.getSim()) == false)
			orderItemService.useItem(order.getCreator().getUserId(),SYSConstant.GOOD_TYPE_SIM,order.getSim());
		
		/** 保存佣金记录 **/
		commissionService.addCommissionByAgentOrder(order.getOrderCode());
	}
	/**
	 * <p>通过订单</p> 
	 * @param:        @param order
	 * @param:        @throws Exception    
	 * @return:       void    
	 * @author        Zhengwj
	 * @Date          2012-9-23 上午11:50:59
	 */
	public void auditOrder(AgentOrder order)throws Exception{

		/**
		对接生产环境，用户订购的套餐，用户数据，号码数据都在CRM侧，本地db不需要进行保存
		SvnInfo svnInfo = svnInfoService.loadSvnInfoBySvn(order.getSvn(),SYSConstant.ITEM_STATUS_TEMP);
		
		SvnProduct svnProduct = new SvnProduct();
		svnProduct.setCreateTime(new Date());
		svnProduct.setUpdateTime(new Date());
		svnProduct.setCreator(order.getCreator());
		svnProduct.setStatus(SYSConstant.STATE_VALID);
		svnProduct.setSvn(order.getSvn());
		svnProduct.setProduct(order.getProduct());
		svnProduct.setUser(svnInfo.getCustomer());
		svnProductService.saveSvnProduct(svnProduct);
		
		
		**/
		
		saveAgentOrder(order);
		
	}
	
	/**
	 * 
	 * @param orderCode
	 * @param payMode
	 * @param voucherNo
	 * @param payPwd 支付密码
	 * @param userId 用户id
	 * @throws Exception
	 */
	public void payOrder(String orderCode,String payMode,String voucherNo,String payPwd) throws Exception{
		
		if(StringUtil.isEmpty(orderCode))
			throw new Exception(LanguageInfo.ORDERCODE_IS_EMPTY);
		
		AgentOrder order = loadAgentOrderByOrderCode(orderCode);
		
		if(order == null)
			throw new Exception(orderCode+" " + LanguageInfo.ORDER_UNEXIST);
		
		User user = order == null?null:order.getCreator();
		if(user==null)
			throw new Exception(orderCode+" " + LanguageInfo.USER_NOT_EXIST);
		
		if(order.getCreator()==null || order.getCreator().getUserId() == null)
			throw new Exception(orderCode+" " + LanguageInfo.USER_ID_EMPTY);
		
		if(payPwd==null)
			throw new Exception(orderCode+" " + LanguageInfo.PAY_PWD_EMPTY);
		
		if(!StringUtils.equals(payPwd, user.getPayPwd()))
			throw new Exception(orderCode+" " + LanguageInfo.PAY_PWD_WRONG);
		
		if(SYSConstant.AGENT_ORDER_STATUS_WAITTING.equals(order.getStatus()) == false)
			throw new Exception(orderCode+" " + LanguageInfo.ORDER_HAD_PAID);
		/**
		 * 填写，实际支付费用，支付方式，流水号，更改支付状态，支付时间
		 */
		order.setPayMode(payMode);
		order.setBankSerial(voucherNo);
		order.setPayStatus(SYSConstant.PAY_STATUS_PAID);
		order.setPayTime(new Date());
		
		/**
		 * 修改，将充值订单和其余类型的订单一致，支付完以后还未完成，等tibco调用top-up成功以后再完成
		 */
//		if(SYSConstant.AGENT_ORDER_TYPE_RECHARGE.equals(order.getOrderType()))
//		{
//			/** 充值订单在付款完成后，直接完成订单 **/
//			changeOrderStatus(order.getOrderCode(),SYSConstant.AGENT_ORDER_STATUS_COMPLETE);
//			
//			/** 当充值订单手机号不存在时，默认为打印PIN码的订单 **/
//			if(StringUtil.isEmpty(order.getSvn()) == false)
//			{
////				SvnInfo svnInfo = svnInfoService.loadSvnInfoBySvn(order.getSvn(),SYSConstant.ITEM_STATUS_USED);
////			
////				if(svnInfo != null)
////				{
////	//				throw new Exception(LanguageInfo.PHONENUM_UNEXIST);
////					/** 充值动作等支付完成后操作 **/
////					svnInfo.setAmount(( svnInfo.getAmount() == null ? 0 :svnInfo.getAmount() )+order.getSaleFee());
////				
////					svnInfoService.saveSvnInfo(svnInfo);
////				}
//			}
//			else
//			{
//				if(StringUtil.isEmpty(order.getPin()))
//					throw new Exception(LanguageInfo.MUST_HAVE_PIN);
//			}
//			
//		}
//		else
		{
			/** 将订单状态改为已经支付 **/
			changeOrderStatus(order.getOrderCode(),SYSConstant.AGENT_ORDER_STATUS_HAS_PAID);
		}
		
		/** 将佣金生成放到订单完成后 **/
//		commissionService.addCommissionByAgentOrder(order.getOrderCode());
		
		if(SYSConstant.PAY_MODE_ACCOUNT.equals(order.getPayMode()))
		{
			/** 预存池全额扣款 **/
			accountInfoService.payAgentOrderFromAccount(order.getOrderCode());
		}
		
	}
	
	/**
	 * 订单支付接口
	 * @param orderCode
	 * @param payMode
	 * @param voucherNo
	 * @throws Exception
	 */
	public void payOrder(String orderCode,String payMode,String voucherNo) throws Exception
	{
		this.payOrder(orderCode, payMode, voucherNo, null);
	}
	
	public void createCommission(String orderCode) throws Exception
	{
		AgentOrder order = loadAgentOrderByOrderCode(orderCode);
		
		/** 保存佣金记录 **/
		commissionService.addCommissionByAgentOrder(order.getOrderCode());
		
		if(SYSConstant.PAY_MODE_ACCOUNT.equals(order.getPayMode()))
		{
			//TODO 预存池扣款
			accountInfoService.payAgentOrderFromAccount(order.getOrderCode());
		}
		
	}
	
	public Map<String,Long> countIncomeByOrderType(String userCode,String orderType,String orderStatus,String payStatus,Date startTime,Date endTime) throws Exception
	{
		if(StringUtil.isEmpty(userCode))
			throw new Exception(LanguageInfo.USERCODE_IS_NULL);
		
		AgentOrder condition = new AgentOrder();
		condition.setPayStatus(payStatus);
		if(StringUtil.isEmpty(orderStatus) == false){
			condition.setStatus(orderStatus);
		}
		condition.setCreator(new User(userCode));
		if(StringUtil.isEmpty(orderType) == false)
			condition.setOrderType(orderType);
		if(startTime != null)
			condition.setStartTime(startTime);
		if(endTime != null)
			condition.setEndTime(endTime);
		
		
		return agentOrderDao.countIncomeByOrderType(condition);
	}
	
	public List<AgentOrder> listIncomeDetailByOrderType(String userCode,String orderType,String orderStatus,Date startTime,Date endTime,int start,int pageSize)throws Exception{
		if(StringUtil.isEmpty(userCode))
			throw new Exception(LanguageInfo.USERCODE_IS_NULL);
		
		AgentOrder condition = new AgentOrder();
		condition.setPayStatus(SYSConstant.PAY_STATUS_PAID);
		if(StringUtil.isEmpty(orderStatus) != false){
			condition.setStatus(orderStatus);
		}
		condition.setCreator(new User(userCode));
		if(StringUtil.isEmpty(orderType) == false)
			condition.setOrderType(orderType);
		if(startTime != null)
			condition.setStartTime(startTime);
		if(endTime != null)
			condition.setEndTime(endTime);
		
		return (List<AgentOrder>)listAgentOrders(condition, start, pageSize);
	}
	
	
	public Map<Long,Long> countFeeByUser(AgentOrder condition,Date startTime,Date endTime) throws Exception
	{
		if(condition == null)
			condition = new AgentOrder();
		
		condition.setPayStatus(SYSConstant.PAY_STATUS_PAID);
		if(startTime != null)
			condition.setStartTime(startTime);
		if(endTime != null)
			condition.setEndTime(endTime);
		
		
		return agentOrderDao.countAmountByUser(condition);
	}
	
	public AgentOrder createBoltOnOrderByAgent(String svn,Long fee,Long proudctId,Long userId) throws Exception
	{
		if(StringUtil.isEmpty(svn))
			throw new Exception(LanguageInfo.PHONENUM_MUST_TYPE);
		
		SvnInfo svnInfo = svnInfoService.loadSvnInfoBySvn(svn,SYSConstant.STATE_VALID);
		
		if(svnInfo == null)
			throw new Exception(LanguageInfo.PHONENUM_UNEXIST_INACTIVE);
		
		Date now = new Date();
		
		Product product = productService.loadProduct(proudctId);
		
		if(product == null)
			throw new Exception(LanguageInfo.PRODUCT_UNEXIST);
		
		fee = product.getPrice() == null?Long.valueOf(0):product.getPrice();
		
		User creator = userService.loadUser(userId);
		
		AgentOrder order = new AgentOrder();
		
		/** 设置价格 **/
		order.setNumberFee(Long.valueOf(0));//号码费用
		order.setSimFee(Long.valueOf(0));//SIM卡费
		order.setPackageFee(Long.valueOf(fee));
		Long saleFee = order.getSimFee()+order.getNumberFee()+order.getPackageFee();
		order.setSaleFee(saleFee);
		
//		float rate = SYSConstant.discountTypes.get(SYSConstant.AGENT_ORDER_TYPE_BOLT_ON);
		//float discountFee = fee*rate/100;
		Map<String,String> variantMap = new HashMap<String,String>();
		BigDecimal bigF = (new BigDecimal(fee).divide(new BigDecimal(1000)));
		variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDER_AMOUNT, bigF.toString());
		variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDERTYPE, SYSConstant.AGENT_ORDER_TYPE_SIMCARD);
		
		
		//agent rule
		Long agentId=null;
		if(userId!=null){
			agentId=userId;
		}
		
		BigDecimal discountFeeB = commissionRuleService.getImmediateCommissionValue(variantMap,agentId).multiply(new BigDecimal(1000));
//		BigDecimal discountFeeB = commissionRuleService.getImmediateCommissionValueByFee(fee);
		String orderCode = DateUtils.getDateString("yyyyMMddhhmmss")+Math.round(5) + SYSConstant.AGENT_ORDER_TYPE_RECHARGE;
		
		order.setCompleteTime(now);
		order.setCreateTime(now);
		order.setDesc("Bolt on "+svn);
		order.setDiscountFee(discountFeeB.longValue());
		if(fee.longValue() == 0){
			order.setDiscountRate((float)0);
		}else{
			order.setDiscountRate(discountFeeB.divide(new BigDecimal(fee),3,BigDecimal.ROUND_HALF_UP).floatValue());
		}
		order.setOrderCode(orderCode);
		order.setOrderType(SYSConstant.AGENT_ORDER_TYPE_RECHARGE);
		order.setPayStatus(SYSConstant.PAY_STATUS_NOT_PAID);
		order.setPayTime(now);
		order.setSvn(svn);
		order.setPreStore(product.getPrestore() == null?0:product.getPrestore()*1000);
		order.setRealFee((new BigDecimal(fee)).subtract(discountFeeB).longValue());
		order.setStatus(SYSConstant.AGENT_ORDER_STATUS_WAITTING);
		order.setBlance(svnInfo.getAmount());
		order.setUpdateTime(now);
		order.setCreator(new User(creator.getUserCode()));
		order.setProduct(new Product(proudctId));

		saveAgentOrder(order);
		
		SvnProduct sp = new SvnProduct();
		sp.setCreateTime(new Date());
		sp.setCreator(creator);
		sp.setProduct(new Product(proudctId));
		sp.setSvn(svn);
		sp.setUser(svnInfo.getCustomer());
		sp.setStatus(SYSConstant.STATE_VALID);
		sp.setUpdateTime(new Date());
		
		svnProductService.saveSvnProduct(sp);
	
		
		return order;
	}
	
	/**
	 * <p>描述:根据城市获得收入情况 </p> 
	 * @param:        @return    
	 * @return:       List<CommonBean>    
	 * @author        Zhengwj
	 * @Date          2012-10-7 下午04:44:46
	 */
	public List<CommonBean> getIncomeByCity(AgentOrder condition,int start,int limit)throws Exception{
		List<CommonBean> incomeThisMonth = null;
		
		String thisMonth = DateUtils.getDateString("yyyyMM");
		condition.setPayStatus(SYSConstant.PAY_STATUS_PAID);
		condition.setStatus(SYSConstant.ORDER_STATUS_SUCCESS);
		
		//this month
		condition.setStartTime(DateUtils.getDate(thisMonth+"01 00:00:00", "yyyyMMdd HH:mm:ss"));
		condition.setEndTime(DateUtils.getDate(thisMonth + DateUtils.getDays(thisMonth) + " 23:59:59", "yyyyMMdd HH:mm:ss"));
		User userCondition = new User();
		userCondition.setStartTime(condition.getStartTime());
		userCondition.setEndTime(condition.getEndTime());
		
		Map<String,Object> resultThis = agentOrderDao.countAmountByCity(condition,start,limit);
		List<String> cityCodes = null;
		if(resultThis != null){
			cityCodes = (List<String>)resultThis.get("cityCodes");
			incomeThisMonth = (List<CommonBean>)resultThis.get("objs");
		}
		
		
		if(incomeThisMonth != null && incomeThisMonth.size() != 0){
			User creator = new User();
			creator.setStateCodes(cityCodes);
			condition.setCreator(creator);
			userCondition.setCreator(creator);
			
			//last month
			String lastMonth = DateUtils.getChdate(-1);
			condition.setStartTime(DateUtils.getDate(lastMonth + "01 00:00:00", "yyyyMMdd HH:mm:ss"));
			condition.setEndTime(DateUtils.getDate(lastMonth + DateUtils.getDays(lastMonth) + " 23:59:59", "yyyyMMdd HH:mm:ss"));
			Map<String,Object> income_map = agentOrderDao.countAmountByCity(condition,start,limit);
			List<CommonBean> incomeLastMonth = null;
			if(income_map != null){
				 incomeLastMonth = (List<CommonBean>)(income_map.get("objs"));
			 }else{
				 incomeLastMonth = new ArrayList<CommonBean>(0);
			 }
			
			//last year month
			String lastYearMonth = DateUtils.getChdate(-12);
			condition.setStartTime(DateUtils.getDate(lastYearMonth + "01 00:00:00", "yyyyMMdd HH:mm:ss"));
			condition.setEndTime(DateUtils.getDate(lastYearMonth + DateUtils.getDays(lastMonth) + " 23:59:59", "yyyyMMdd HH:mm:ss"));
			income_map = agentOrderDao.countAmountByCity(condition,start,limit);
			List<CommonBean> incomeLastYearMonth = null;
			if(income_map != null){
				incomeLastYearMonth = (List<CommonBean>)(income_map.get("objs"));
			 }else{
				 incomeLastYearMonth = new ArrayList<CommonBean>(0);
			 }
			
			//develop users count
			Map<String,Long> developUsers = userService.getUsersByCity(userCondition, start, -1);
			
			
			incomeThisMonth = getTongbiHuanbi(incomeThisMonth, incomeLastMonth, incomeLastYearMonth,developUsers);
			
			
		}
		
		return incomeThisMonth;
	}
	
	private List<CommonBean> getTongbiHuanbi(List<CommonBean> incomeThisMonth,List<CommonBean> incomeLastMonth,
			List<CommonBean> incomeLastYearMonth,Map<String,Long> developUsers)throws Exception{
		//环比--同比
		double this_income = 0.0;
		double pre_month_income = 0.0;
		double pre_year_income = 0.0;
		double percent = 0.0;
		
		for(CommonBean thisMonth:incomeThisMonth){
			this_income = Double.parseDouble(StringUtil.isEmpty(thisMonth.getStr3())?"0":thisMonth.getStr3());
			thisMonth.setStr3(MoneyUtils.formatToMoney(this_income));
			for(CommonBean lastMonth:incomeLastMonth){
				if(thisMonth.getStr1() != null && thisMonth.getStr1().equals(lastMonth.getStr1())){
						pre_month_income = Double.parseDouble(StringUtil.isEmpty(lastMonth.getStr3())?"0":lastMonth.getStr3());
						break;
					}
				}
			for(CommonBean lastYearMonth:incomeLastYearMonth){
				if(thisMonth.getStr1() != null && thisMonth.getStr1().equals(lastYearMonth.getStr1())){
					pre_year_income = Double.parseDouble(StringUtil.isEmpty(lastYearMonth.getStr3())?"0":lastYearMonth.getStr3());
					break;
				}
			}
			//huanbi
			if(pre_month_income == 0.0){
				thisMonth.setStr4("--");
			}else{
				percent = percentRound((this_income - pre_month_income) / pre_month_income);
				if(percent > 0){
					thisMonth.setStr4("<span class='red'>" + MoneyUtils.formatToMoney(percent) + "%</span>");
				}else if(percent < 0){
					thisMonth.setStr4("<span class='green'>-" + MoneyUtils.formatToMoney(Math.abs(percent)) + "%</span>");
				}else{
					thisMonth.setStr4("<span class='orange'>0</span>");
				}
			}
			
			//tongbi
			if(pre_year_income == 0.0){
				thisMonth.setStr5("--");
			}else{
				percent = percentRound((this_income - pre_year_income) / pre_year_income);
				if(percent > 0){
					thisMonth.setStr5("<span class='rise'>" + MoneyUtils.formatToMoney(percent) + "%</span>");
				}else if(percent < 0){
					thisMonth.setStr5("<span class='descend\'>-" + MoneyUtils.formatToMoney(Math.abs(percent)) + "%</span>");
				}else{
					thisMonth.setStr5("0");
				}
			}
			
			//develop users
			if(developUsers != null){
				if(developUsers.get(thisMonth.getStr1()) != null){
					thisMonth.setStr6(developUsers.get(thisMonth.getStr1()).toString());
				}else{
					thisMonth.setStr6("0");
				}
			}else{
				thisMonth.setStr6("0");
			}
		}
			
		
		return incomeThisMonth;
	}
	
	private double percentRound(double d){
		return Math.round(d * 10000)/100.0;
	}
	
	public List<CommonBean> getTopSaleByProduct(AgentOrder condition ,int start,int limit)throws Exception{
		if(condition == null){
			condition = new AgentOrder();
		}
		if(StringUtil.isEmpty(condition.getPayStatus())){
			condition.setPayStatus(SYSConstant.PAY_STATUS_PAID);
		}
		if(StringUtil.isEmpty(condition.getStatus())){
			condition.setStatus(SYSConstant.AGENT_ORDER_STATUS_COMPLETE);
		}
		String thisMonth = DateUtils.getDateString("yyyyMM");
		
		//this month
		condition.setStartTime(DateUtils.getDate(thisMonth+"01 00:00:00", "yyyyMMdd HH:mm:ss"));
		condition.setEndTime(DateUtils.getDate(thisMonth + DateUtils.getDays(thisMonth) + " 23:59:59", "yyyyMMdd HH:mm:ss"));
		
		List<CommonBean> result = agentOrderDao.getTopSaleByProduct(condition, start, limit);
		return result;
	}
	
	public List<CommonBean> getIncomeByCityJustByMonth(AgentOrder condition,int start,int limit)throws Exception{
		List<CommonBean> incomeLastMonth = null;
		
//		String thisMonth = DateUtils.getDateString("yyyyMM");
		String lastMonth = DateUtils.getChdate(-1);
		condition.setPayStatus(SYSConstant.PAY_STATUS_PAID);
		condition.setStatus(SYSConstant.AGENT_ORDER_STATUS_COMPLETE);
		
		//last month
		condition.setStartTime(DateUtils.getDate(lastMonth+"01 00:00:00", "yyyyMMdd HH:mm:ss"));
		condition.setEndTime(DateUtils.getDate(lastMonth + DateUtils.getDays(lastMonth) + " 23:59:59", "yyyyMMdd HH:mm:ss"));
		User userCondition = new User();
		userCondition.setStartTime(condition.getStartTime());
		userCondition.setEndTime(condition.getEndTime());
		
		Map<String,Object> resultLast = agentOrderDao.countAmountByCity(condition,start,limit);
		List<String> cityCodes = null;
		if(resultLast != null)
		{
			cityCodes = (List<String>)resultLast.get("cityCodes");
			incomeLastMonth = (List<CommonBean>)resultLast.get("objs");
		}
		
		
		if(incomeLastMonth != null && incomeLastMonth.size() != 0){
			User creator = new User();
			creator.setStateCodes(cityCodes);
			condition.setCreator(creator);
			userCondition.setCreator(creator);
			
			//last month 2
			String lastMonth2 = DateUtils.getChdate(-2);
			condition.setStartTime(DateUtils.getDate(lastMonth2 + "01 00:00:00", "yyyyMMdd HH:mm:ss"));
			condition.setEndTime(DateUtils.getDate(lastMonth2 + DateUtils.getDays(lastMonth2) + " 23:59:59", "yyyyMMdd HH:mm:ss"));
			Map<String,Object> resultLast2 = agentOrderDao.countAmountByCity(condition,start,limit);
			
			//last month 3
			String lastMonth3 = DateUtils.getChdate(-3);
			condition.setStartTime(DateUtils.getDate(lastMonth3 + "01 00:00:00", "yyyyMMdd HH:mm:ss"));
			condition.setEndTime(DateUtils.getDate(lastMonth3 + DateUtils.getDays(lastMonth3) + " 23:59:59", "yyyyMMdd HH:mm:ss"));
			Map<String,Object> resultLast3 = agentOrderDao.countAmountByCity(condition,start,limit);
			
			//last month 4
			String lastMonth4 = DateUtils.getChdate(-4);
			condition.setStartTime(DateUtils.getDate(lastMonth4 + "01 00:00:00", "yyyyMMdd HH:mm:ss"));
			condition.setEndTime(DateUtils.getDate(lastMonth4 + DateUtils.getDays(lastMonth4) + " 23:59:59", "yyyyMMdd HH:mm:ss"));
			Map<String,Object> resultLast4 = agentOrderDao.countAmountByCity(condition,start,limit);
			
			//last month 5
			String lastMonth5 = DateUtils.getChdate(-5);
			condition.setStartTime(DateUtils.getDate(lastMonth5 + "01 00:00:00", "yyyyMMdd HH:mm:ss"));
			condition.setEndTime(DateUtils.getDate(lastMonth5 + DateUtils.getDays(lastMonth5) + " 23:59:59", "yyyyMMdd HH:mm:ss"));
			Map<String,Object> resultLast5 = agentOrderDao.countAmountByCity(condition,start,limit);
			
			//last month 6
			String lastMonth6 = DateUtils.getChdate(-6);
			condition.setStartTime(DateUtils.getDate(lastMonth6 + "01 00:00:00", "yyyyMMdd HH:mm:ss"));
			condition.setEndTime(DateUtils.getDate(lastMonth6 + DateUtils.getDays(lastMonth6) + " 23:59:59", "yyyyMMdd HH:mm:ss"));
			Map<String,Object> resultLast6 = agentOrderDao.countAmountByCity(condition,start,limit);
			
			incomeLastMonth = combineMonthIncome(incomeLastMonth, resultLast2, resultLast3,resultLast4,resultLast5,resultLast6);
			
			
		}
		
//		List<CommonBean>  first = new ArrayList<CommonBean>();
//		CommonBean obj = new CommonBean();
//		obj.setStr1("aa");
//		obj.setStr2("London");
//		obj.setStr3("10");
//		first.add(obj);
//		obj = new CommonBean();
//		obj.setStr1("bb");
//		obj.setStr2("Manchester");
//		obj.setStr3("20");
//		first.add(obj);
//		obj = new CommonBean();
//		obj.setStr1("cc");
//		obj.setStr2("Liverpool");
//		obj.setStr3("30");
//		first.add(obj);
//		
//		
//		Map<String,Object> map2 = new HashMap<String, Object>();
//		map2.put("_aa", "11");
//		map2.put("_bb", "21");
//		map2.put("_cc", "31");
//		
//		Map<String,Object> map3 = new HashMap<String, Object>();
//		map3.put("_aa", "12");
//		map3.put("_bb", "22");
//		map3.put("_cc", "32");
//		
//		Map<String,Object> map4 = new HashMap<String, Object>();
//		map4.put("_aa", "13");
//		map4.put("_bb", "23");
//		map4.put("_cc", "33");
//		
//		Map<String,Object> map5 = new HashMap<String, Object>();
//		map5.put("_aa", "14");
//		map5.put("_bb", "24");
//		map5.put("_cc", "34");
//		
//		Map<String,Object> map6 = new HashMap<String, Object>();
//		
//		combineMonthIncome(first, map2, map3,map4,map5,map6);
		
		return incomeLastMonth;
//		return first;
		
	}
	
	private List<CommonBean>  combineMonthIncome(List<CommonBean> firstMonth,Map<String,Object>...otherMonths)throws Exception{
		String income = null;
		engine = (Class<CommonBean>) Class.forName("com.ai.mapp.sys.entity.CommonBean");
		Method method = null;
		for(CommonBean tmpFirst:firstMonth){
			for(int i = 0; i < otherMonths.length; i++){
				method = engine.getMethod("setStr" + (4 + i), String.class);
				if(otherMonths[i] != null && !otherMonths[i].isEmpty()){
					income = (String)otherMonths[i].get("_" + tmpFirst.getStr1());
					if(StringUtil.isEmpty(income)){
						method.invoke(tmpFirst, "0");
					}else{
						method.invoke(tmpFirst, income);
					}
				}else{
					method.invoke(tmpFirst, "0");
				}
				
				
			}
		}
		return firstMonth;
	}
	
	public static void main(String[] args) {
		List<CommonBean>  first = new ArrayList<CommonBean>();
		CommonBean obj = new CommonBean();
		obj.setStr1("aa");
		obj.setStr2("aa");
		obj.setStr3("0");
		first.add(obj);
		obj = new CommonBean();
		obj.setStr1("bb");
		obj.setStr2("aa");
		obj.setStr3("1");
		first.add(obj);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("_aa", "1");
		map.put("_bb", "2");
		try {
			new AgentOrderService().combineMonthIncome(first,map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(first.get(0).getStr4());
	}
	
	/**
	 * <p>描述: 取消定单</p> 
	 * @param:        @param orderCode
	 * @param:        @throws Exception    
	 * @return:       String    
	 * @author        Zhengwj
	 * @Date          2012-12-7 下午05:25:05
	 */
	public ResultJson cancelOrder(String orderCode) throws Exception
	{
		ResultJson result = new ResultJson();
		result.setFlag(true);
		
		if(StringUtil.isEmpty(orderCode)){
			result.setFlag(false);
			result.setMsg(LanguageInfo.ORDERCODE_IS_EMPTY);
			return result;
		}
			
		
		AgentOrder order = loadAgentOrderByOrderCode(orderCode);
		
		if(order == null){
			result.setFlag(false);
			result.setMsg(orderCode+" " + LanguageInfo.ORDER_UNEXIST);
			return result;
		}
		
		if(SYSConstant.AGENT_ORDER_STATUS_COMPLETE.equals(order.getStatus())){
			result.setFlag(false);
			result.setMsg(orderCode+" " + LanguageInfo.ORDER_COMPLETED);
			return result;
		}
		
		order.setStatus(SYSConstant.AGENT_ORDER_STATUS_CANCEL);
		agentOrderDao.save(order);
		SvnInfo svnInfo = svnInfoService.loadSvnInfoBySvn(order.getSvn(), null);
		
		/**
		 * 新开的时候会在svn_product表中增加记录
		 */
		if(SYSConstant.AGENT_ORDER_TYPE_NEW.equals(order.getOrderType()) && svnInfo != null)
		{
			svnInfo.setStatus(SYSConstant.STATE_INVALID);
			svnInfoService.saveSvnInfo(svnInfo);
			
		}
		
//		if (svnInfo != null && order.getProduct() != null
//				&& order.getProduct().getPrestore() != null
//				&& order.getProduct().getPrestore() >= 0) 
//		{
//			Long amount = svnInfo.getAmount() == null ? 0 : svnInfo.getAmount().longValue();
//			Long preStore = order.getProduct().getPrestore() == null ? 0 : order.getProduct().getPrestore().longValue()*1000;
//			svnInfo.setAmount(amount+preStore);
//			svnInfoService.saveSvnInfo(svnInfo);
//		}
		
//		/** 由于卡号是后录入的，因此在完成之前将sim号存入订单 **/
//		if(goods != null && goods.isEmpty() == false)
//		{
//			if(StringUtil.isEmpty(goods.get(SYSConstant.GOOD_TYPE_SIM)) == false)
//			{
//				order.setSim(goods.get(SYSConstant.GOOD_TYPE_SIM));
//				saveAgentOrder(order);
//			}
//			else if(StringUtil.isEmpty(goods.get("2")) == false)
//			{
//				order.setSim(goods.get("2"));
//				saveAgentOrder(order);
//			}
//		}
		
		if(StringUtil.isEmpty(order.getImei()) == false)
			orderItemService.resetItem(order.getCreator().getUserId(),SYSConstant.GOOD_TYPE_MOBILE,order.getImei());
		
		if(StringUtil.isEmpty(order.getSim()) == false)
			orderItemService.resetItem(order.getCreator().getUserId(),SYSConstant.GOOD_TYPE_SIM,order.getSim());
		return result;
	}
	
	/**
	 * 按月统计最好卖的套餐
	 * @param stateCode
	 * @param cityCode
	 * @param startTime
	 * @param endTime
	 * @param:需从小到大排列 yyyymm
	 * @return
	 * @throws Exception
	 */
	public Map<Long,List<Object>> statBestProductByMonth(String stateCode,String cityCode,List<String> months) throws Exception
	{
		try{
		Map<Long,List<Object>> result = new LinkedHashMap<Long, List<Object>>(0);
		if(months != null && months.size() != 0){
			Date startTime = DateUtils.getDate(months.get(0) + "01 00:00:00", "yyyyMMdd HH:mm:ss");
			Date endTime = DateUtils.getDate(months.get(months.size() - 1) + DateUtils.getDays(months.get(months.size() - 1)) + " 23:59:59", "yyyyMMdd HH:mm:ss");
			
			Map<Long,String> result_map = agentOrderDao.getBestProudct(stateCode, cityCode, startTime, endTime);
			
			if(result_map == null || result_map.keySet() == null || result_map.keySet().isEmpty())
				return null;
			
			Map<String,Long> tmpSquery = null;
			
			for(Long id : result_map.keySet())
			{
				tmpSquery = agentOrderDao.statSingleProductByMonth(stateCode, cityCode, startTime, endTime, id);
				List<Object> tmp = new ArrayList<Object>();
				tmp.add(result_map.get(id));
				
				for(int i = 0; i < months.size(); i++){
					if(tmpSquery != null && tmpSquery.get(months.get(i)) != null){
						tmp.add(tmpSquery.get(months.get(i)));
					}else{
						tmp.add(new Long(0));
					}
				}
				
				result.put(id, tmp);
			}
		}
		
		for(Long id : result.keySet())
		{
			System.out.println(id+""+(result.get(id)).get(0)+"    "+(result.get(id)).toArray());
		}
		
		return result;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		
	}
	
	public int createNewAgentOrderTest(int start,List<Product> products,List<User> creators,Date createTime,int size) throws Exception
	{
		
		if(size <= 0)
			return start;
		
		for(int i=0;i<size;i++)
		{
			User creator = creators.get(start%creators.size());
			Product product = products.get(start%products.size());
			
			
			String svn = "0"+(start+i);
			SvnInfo svnInfo = new SvnInfo();
			svnInfo.setSvcLevel("3");
			svnInfo.setSvn(svn);
			svnInfo.setStatus(SYSConstant.ITEM_STATUS_UNUSE);
			svnInfoService.saveSvnInfo(svnInfo);
			
			AgentOrder order = new AgentOrder();
			order.setBankSerial("3939939399"+i);
			order.setCreator(creator);
			order.setPayMode(SYSConstant.PAY_MODE_ACCOUNT);
			order.setPackageFee(500000L);
			order.setSim("123456789000000"+i);
			order.setImsi("10000000020202"+i);
			order.setImei("2220000000020202"+i);
			order.setSvn(svn);
			order.setProduct(product);
			
			User user = new User();
			user.setFirstName("David");
			user.setCertificateType("1");
			user.setCertificateNo("222201010"+i);
			user.setEmail("David"+i+"@hotmail.com");
			user.setContractPhone("0"+(829292299+i));
			user.setCreator(creator);
			
			user.setContractPhone(order.getSvn());
			user.setMobileNo(order.getSvn());
			user.setStatus(SYSConstant.STATE_VALID);
			user.setUserType(SYSConstant.USER_TYPE_CUSTOMER);
			user.setCreateTime(createTime);
			userService.saveUser(user);
			
			svnInfo.setStatus(SYSConstant.ITEM_STATUS_USED);
			svnInfo.setCustomer(user);
			svnInfo.setStartTime(createTime);
			svnInfoService.saveSvnInfo(svnInfo);
			
			if(product == null)
				throw new Exception(LanguageInfo.PRODUCT_UNEXIST);
			
			/** 设置价格 **/
			Long number_fee = 0L;
			if("1".equals(svnInfo.getSvcLevel()))
				number_fee = 15000L;
			else if("2".equals(svnInfo.getSvcLevel()))
				number_fee = 10000L;
			else
				number_fee = 0L;
			
			order.setNumberFee(number_fee);//号码费用
			order.setSimFee(0L);//SIM卡费
			Long saleFee = order.getSimFee()+order.getNumberFee()+order.getPackageFee();
			order.setSaleFee(saleFee);
			
			Map<String,String> variantMap = new HashMap<String,String>();
			BigDecimal fee = (new BigDecimal(order.getSaleFee()).divide(new BigDecimal(1000)));
			variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDER_AMOUNT, fee.toString());
			variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDERTYPE, SYSConstant.AGENT_ORDER_TYPE_NEW);
			
			//agent rule
			Long agentId=null;
			if(order.getCreator()!=null){
				agentId=order.getCreator().getUserId();
			}
			
			BigDecimal discountFeeB = commissionRuleService.getImmediateCommissionValue(variantMap,agentId).multiply(new BigDecimal(1000));
			String orderCode = DateUtils.getDateString("yyyyMMddhhmmss")+(Math.round(5)+i) + SYSConstant.AGENT_ORDER_TYPE_NEW;
			
			order.setCompleteTime(createTime);
			order.setCreateTime(createTime);
			order.setDesc("New "+order.getSvn());
			order.setDiscountFee(discountFeeB.longValue());
			if(order.getSaleFee().longValue() == 0){
				order.setDiscountRate((float)0);
			}else{
				order.setDiscountRate(discountFeeB.divide(new BigDecimal(order.getSaleFee()),3,BigDecimal.ROUND_HALF_UP).floatValue());
			}
			order.setOrderCode(orderCode);
			order.setOrderType(SYSConstant.AGENT_ORDER_TYPE_NEW);
			order.setPayStatus(SYSConstant.PAY_STATUS_PAID);
			order.setPayTime(createTime);
			order.setPreStore(product.getPrestore() == null?0:product.getPrestore()*1000);
			order.setRealFee((new BigDecimal(order.getSaleFee())).subtract(discountFeeB).longValue());
			order.setStatus(SYSConstant.AGENT_ORDER_STATUS_COMPLETE);
			order.setBlance(svnInfo.getAmount());
			order.setUpdateTime(createTime);
	
//			saveAgentOrder(order);
			
//			if(StringUtil.isEmpty(order.getImei()) == false)
//				orderItemService.useItemTemp(order.getCreator().getUserId(),SYSConstant.GOOD_TYPE_MOBILE,order.getImei());
//			
//			if(StringUtil.isEmpty(order.getSim()) == false)
//				orderItemService.useItemTemp(order.getCreator().getUserId(),SYSConstant.GOOD_TYPE_SIM,order.getSim());
			
			SYSConstant.orders.put(order.getSvn(),order);
			
			System.out.println(svn+" is ok");
		}
		
		return start+size;
	}
	
	public void createRechargeAgentOrderTest(String svn,Long chargefee,String creator,Date createTime,int i) throws Exception
	{

		AgentOrder order = new AgentOrder();
		
		order.setBankSerial("99292929112"+i);
		order.setCreator(new User(creator));
		order.setPayMode(SYSConstant.PAY_MODE_ACCOUNT);
		order.setPackageFee(chargefee);
		order.setSvn(svn);
		order.setOptType("1");
		
		
		SvnInfo svnInfo = null;
		
		/**
		 * order.getOptType() == 2时为带手机号的直接充值，order.getOptType()==3时为通过PIN号充值
		 */
		if("3".equals(order.getOptType()) && StringUtil.isEmpty(order.getSvn()))
		{
			order.setPin(""+System.currentTimeMillis()+Math.round(5));
		}
		else if("2".equals(order.getOptType()) && StringUtil.isEmpty(order.getSvn()) == false)
		{
			svnInfo = svnInfoService.loadSvnInfoBySvn(order.getSvn(),SYSConstant.ITEM_STATUS_USED);
			
			if(svnInfo == null)
				throw new Exception(LanguageInfo.PHONENUM_UNEXIST);
			
			//设置该号码的余额
			order.setBlance(svnInfo.getAmount());
		}
		
		/** 设置价格 **/
		order.setNumberFee(Long.valueOf(0));//号码费用
		order.setSimFee(Long.valueOf(0));//SIM卡费
		Long saleFee = order.getSimFee()+order.getNumberFee()+order.getPackageFee();
		order.setSaleFee(saleFee);
		
		/** 充值动作等支付完成后操作 **/
//		svnInfo.setAmount(( svnInfo.getAmount() == null ? 0 :svnInfo.getAmount() )+order.getSaleFee());
		
//		svnInfoService.saveSvnInfo(svnInfo);
		
//		float rate = SYSConstant.discountTypes.get(SYSConstant.AGENT_ORDER_TYPE_RECHARGE);
//		float discountFee = order.getSaleFee()*rate/100;
		Map<String,String> variantMap = new HashMap<String,String>();
		BigDecimal fee = (new BigDecimal(order.getSaleFee()).divide(new BigDecimal(1000)));
		variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDER_AMOUNT, fee.toString());
		variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDERTYPE, SYSConstant.AGENT_ORDER_TYPE_TOPUP);
		
		//agent rule
		Long agentId=null;
		if(order.getCreator()!=null){
			User agentUser = userService.loadUserByUserCode(order.getCreator().getUserCode());
			agentId=agentUser.getUserId();
		}
		
		BigDecimal discountFeeB = commissionRuleService.getImmediateCommissionValue(variantMap,agentId).multiply(new BigDecimal(1000));
		String orderCode = DateUtils.getDateString("yyyyMMddhhmmss")+Math.round(5) + SYSConstant.AGENT_ORDER_TYPE_TOPUP;
		Date now = new Date();
		
		order.setCompleteTime(createTime);
		order.setCreateTime(createTime);
		order.setDesc((order.getDesc() == null?"":order.getDesc())+ " Recharge "+((float)order.getSaleFee()/(float)1000)+ "元");
		order.setDiscountFee(discountFeeB.longValue());
		if(order.getSaleFee().longValue() == 0){
			order.setDiscountRate((float)0);
		}else{
			order.setDiscountRate(discountFeeB.divide(new BigDecimal(order.getSaleFee()),3,BigDecimal.ROUND_HALF_UP).floatValue());
		}
		order.setOrderCode(orderCode);
		order.setOrderType(SYSConstant.AGENT_ORDER_TYPE_TOPUP);
		order.setPayStatus(SYSConstant.PAY_STATUS_PAID);
		order.setPayTime(createTime);
		order.setPreStore((long)0);
		order.setRealFee((new BigDecimal(order.getSaleFee())).subtract(discountFeeB).longValue());
		order.setSaleFee(order.getSaleFee());
		order.setStatus(SYSConstant.AGENT_ORDER_STATUS_COMPLETE);
			
		saveAgentOrder(order);
		
	}
	
	
	public void createSimCardAgentOrderTest(String svn,Long orderfee,String creator,Date createTime,int i) throws Exception
	{
		
		AgentOrder order = new AgentOrder();
		order.setBankSerial("9929292929"+i);
		order.setCreator(new User(creator));
		order.setPayMode(SYSConstant.PAY_MODE_ACCOUNT);
		order.setSimFee(orderfee);
		order.setSim("99887484848"+i);
		order.setImsi("2228182882"+i);
		order.setSvn(svn);
		
		if(StringUtil.isEmpty(order.getSvn()))
			throw new Exception(LanguageInfo.PHONENUM_MUST_TYPE);

		SvnInfo svnInfo = svnInfoService.loadSvnInfoBySvn(order.getSvn(),SYSConstant.ITEM_STATUS_USED);
		
		if(svnInfo == null)
			throw new Exception(LanguageInfo.PHONENUM_UNEXIST);
		
		/** 设置价格 **/
		order.setNumberFee(Long.valueOf(0));//号码费用
		order.setSimFee(Long.valueOf(5000));//SIM卡费
		order.setPackageFee(Long.valueOf(0));
		Long saleFee = order.getSimFee()+order.getNumberFee();
		order.setSaleFee(saleFee);
		
//		float rate = SYSConstant.discountTypes.get(SYSConstant.AGENT_ORDER_TYPE_SIMCARD);
//		float discountFee = order.getSaleFee()*rate/100;
		Map<String,String> variantMap = new HashMap<String,String>();
		BigDecimal fee = (new BigDecimal(order.getSaleFee()).divide(new BigDecimal(1000)));
		variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDER_AMOUNT, fee.toString());
		variantMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDERTYPE, SYSConstant.AGENT_ORDER_TYPE_SIMCARD);
		
		//agent rule
		Long agentId=null;
		if(order.getCreator()!=null){
			if(order.getCreator().getUserId()!=null){
				agentId=order.getCreator().getUserId();
			}else{
				User agentUser = userService.loadUserByUserCode(order.getCreator().getUserCode());
				agentId=agentUser.getUserId();
			}
		}
		
		BigDecimal discountFeeB = commissionRuleService.getImmediateCommissionValue(variantMap,agentId).multiply(new BigDecimal(1000));
		String orderCode = DateUtils.getDateString("yyyyMMddhhmmss")+Math.round(5) + SYSConstant.AGENT_ORDER_TYPE_SIMCARD;
		
		order.setCompleteTime(createTime);
		order.setCreateTime(createTime);
		order.setDesc("SimCard "+order.getSvn());
		order.setDiscountFee(discountFeeB.longValue());
		if(order.getSaleFee().longValue() == 0){
			order.setDiscountRate((float)0);
		}else{
			order.setDiscountRate(discountFeeB.divide(new BigDecimal(order.getSaleFee()),3,BigDecimal.ROUND_HALF_UP).floatValue());
		}
		order.setOrderCode(orderCode);
		order.setOrderType(SYSConstant.AGENT_ORDER_TYPE_SIMCARD);
		order.setPayStatus(SYSConstant.PAY_STATUS_PAID);
		order.setPayTime(createTime);
		order.setPreStore((long)0);
		order.setRealFee((new BigDecimal(order.getSaleFee())).subtract(discountFeeB).longValue());
		order.setStatus(SYSConstant.AGENT_ORDER_STATUS_COMPLETE);
		order.setBlance(svnInfo.getAmount());
		order.setUpdateTime(createTime);
		
//		if(StringUtil.isEmpty(order.getSim()) == false)
//		{
//			OrderItem item = orderItemService.loadOrderItemByValue(order.getSim());
//			if(item != null)
//			{
//				item.setStatus(SYSConstant.ITEM_STATUS_USED);
//				orderItemService.saveOrderItem(item);
//			}
//		}

		saveAgentOrder(order);
		
		if(StringUtil.isEmpty(order.getSim()) == false)
			orderItemService.useItemTemp(order.getCreator().getUserId(),SYSConstant.GOOD_TYPE_SIM,order.getSim());
		
	}
	
	public void initAgentOrder() throws Exception
	{
		User condtion = new User();
		condtion.setUserType(SYSConstant.USER_TYPE_AGENT);
		condtion.setStatus(SYSConstant.STATE_VALID);
		condtion.setUserIds(new ArrayList<Long>(0));
		condtion.getUserIds().add(1006L);
		condtion.getUserIds().add(1005L);
		condtion.getUserIds().add(1004L);
		condtion.getUserIds().add(1009L);
		condtion.getUserIds().add(1003L);
		
		Collection<User> agents = userService.listAllUsers(condtion);
		
		
//		ysy        1006
//		Kennan@hotmail.com  1005
//		Lancelot@hotmail.com  1004
//		Law@hotmail.com    1009
//		Micah@hotmail.com  1003
		
//		Ralph@hotmail.com 1022
//		Adolph@hotmail.com  1014
//		Albert@hotmail.com  1012
//		agents.add(userService.loadUser(1006L));
//		agents.add(userService.loadUser(1005L));
//		agents.add(userService.loadUser(1004L));
//		agents.add(userService.loadUser(1009L));
//		agents.add(userService.loadUser(1003L));
//		
//		for(User u : agents)
//		{
//			Hibernate.isInitialized(u);
//		}
		
		List<User> userlist = new ArrayList<User>(agents);
		
//		Collection<HwState> states = hwStatService.listAllHwState(null);
//		List<HwState> statelist = new ArrayList<HwState>(states);
		
		Product condtion1 = new Product();
		condtion1.setIds(new ArrayList<Long>(0));
		condtion1.getIds().add(9849L);
		condtion1.getIds().add(8888L);
		condtion1.getIds().add(10057L);
		condtion1.getIds().add(8856L);
		condtion1.getIds().add(9193L);
//		9849,8888,10057,8856,9193
		
		Collection<Product> products = productService.listAllProducts(condtion1);
//		99003572Package for 3G 10 B(iPhone)    [Ljava.lang.Object;@50649d13
//				99002147Package for 3G 10(C)    [Ljava.lang.Object;@7d8473a0
//				99002143Package for 3G 30(C)    [Ljava.lang.Object;@2c34dc1
//				99002144Package for 3G 40(A)    [Ljava.lang.Object;@6425bb51
//				99002140Package for 3G 20(A)    [Ljava.lang.Object;@692cf634
		
		
		
//		for(Product p : products)
//		{
//			Hibernate.isInitialized(p);
//		}
		
		List<Product> productlisit = new ArrayList<Product>(products);
		
		Map<String,String> map = new HashMap<String, String>(0);
		
		map.put("2012-07-03", "3999");
		map.put("2012-08-03", "789");
		map.put("2012-08-09", "564");
		map.put("2012-08-19", "988");
		map.put("2012-08-12", "789");
		map.put("2012-08-28", "567");
		
		
		map.put("2012-09-03", "766");
		map.put("2012-09-08", "677");
		map.put("2012-09-13", "876");
		map.put("2012-09-16", "888");
		map.put("2012-09-17", "677");
		map.put("2012-09-27", "566");
		map.put("2012-09-28", "988");
		
		map.put("2012-10-03", "766");
		map.put("2012-10-08", "677");
		map.put("2012-10-13", "876");
		map.put("2012-10-16", "888");
		map.put("2012-10-17", "677");
		map.put("2012-10-27", "566");
		map.put("2012-10-28", "988");
		
		map.put("2012-11-03", "766");
		map.put("2012-11-08", "677");
		map.put("2012-11-13", "876");
		map.put("2012-11-16", "888");
		map.put("2012-11-17", "677");
		map.put("2012-11-27", "566");
		map.put("2012-11-28", "988");
		
		map.put("2012-12-03", "766");
		map.put("2012-12-08", "677");
		map.put("2012-12-13", "876");
		map.put("2012-12-16", "888");
		map.put("2012-12-17", "677");
		map.put("2012-12-27", "566");
		map.put("2012-12-28", "988");
		
		map.put("2013-01-03", "766");
		map.put("2013-01-08", "677");
		map.put("2013-01-13", "876");
		map.put("2013-01-16", "888");
		map.put("2013-01-17", "677");
		map.put("2013-01-27", "566");
		map.put("2013-01-28", "988");
		
		map.put("2013-02-03", "766");
		map.put("2013-02-08", "677");
		map.put("2013-02-13", "876");
		map.put("2013-02-16", "888");
		map.put("2013-02-17", "677");
		map.put("2013-02-27", "566");
		map.put("2013-02-28", "988");
		
		
		
//		map.put("2012-10-13", "4798");
//		map.put("2012-11-23", "4698");
//		map.put("2012-12-10", "4998");
//		map.put("2013-01-06", "5398");
//		map.put("2012-02-26", "5998");
		
		int start = 834000000;
		
		SYSConstant.orders.clear();
		
		Collection<Thread> tasks = new HashSet<Thread>(0);
    	
//		for (String path : pathes) 
//		{
//			Thread task = new PortalTaskThread(path,isDownload,htmlParserTool);
//			task.setName("Thread-"+path.subSequence(path.length()-14, path.length()));
//			tasks.add(task);
//			task.start();
//		}
//		
//		waitTaskToCompleted(tasks);
		
		for(String date : map.keySet())
		{
			int size = Integer.valueOf(map.get(date));
			Thread task = new AgentOrderTaskThread(this,start,
					productlisit, 
					userlist,
					DateUtils.getDate(date+" 12:00:00", "yyyy-MM-dd HH:mm:ss") , size);
			task.setName("Thread-"+date);
			tasks.add(task);
			task.start();
			
			start = start+size;
			
//			int size = Integer.valueOf(map.get(date));
//			
//			start = createNewAgentOrderTest(start,
//						productlisit.get(start%productlisit.size()), 
//						userlist.get(start%userlist.size()),
//						DateUtils.getDate(date+" 12:00:00", "yyyy-MM-dd HH:mm:ss") , size);
			
		}
		
		if(tasks != null && tasks.isEmpty() == false )
		{
			for(Thread atask : tasks)
			{
				atask.join();
			}
		}
		
		
		if(SYSConstant.orders.isEmpty() == false)
		{
			
			for(AgentOrder order:SYSConstant.orders.values())
			{
				saveAgentOrder(order);
				System.out.println("save "+order.getOrderCode());
			}
			
			
		}
		
		System.out.println("all is ok");
		
		
		
		
		
	}
	
}
