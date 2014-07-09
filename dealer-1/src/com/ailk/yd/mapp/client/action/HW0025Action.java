package com.ailk.yd.mapp.client.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.ProductService;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.service.VerifySmsService;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0025Request;
import com.ailk.yd.mapp.client.model.HW0025Response;
import com.ailk.yd.mapp.tibco.util.TibcoUtil;

/**
 * 叠加包下单接口 同yd0016
 * 
 * @author qianshihua
 * 
 */
@Service("hw0025")
@Action(bizcode = "hw0025", userCheck = true)
@Scope("prototype")
public class HW0025Action extends
		AbstractYDBaseActionHandler<HW0025Request, HW0025Response> {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AgentOrderService agentOrderService;
	
	@Autowired
	private VerifySmsService verifySmsService;
	
	@Autowired
	private ProductService productService;
	

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		// 算费
		
//			YD0005Request yd5r = new YD0005Request();
//			yd5r.setMdn(request.getMdn());
//			List<Long> l = new ArrayList() {
//				{
//					add(request.getProductId());
//				}
//			};
//			yd5r.setProducts(l);
//			yd5r.setProductType("");
//			Object ssid = MappContext
//					.getAttribute(MappContext.MAPPCONTEXT_SESSIONID);
////			YDDatapackage yddp = this.sendMsg("yd0005", yd5r,
////					ssid != null ? ssid.toString() : null);
//			
//			YDDatapackage yddp = null;
//			YD0005Response yd5resp = (YD0005Response) yddp.getBody();
//			tf = yd5resp.getTotalFee();
//			if(yd5resp.getFees()!=null && yd5resp.getFees().size()>0){
//				feeDtl = JsonUtil.ListToJsonString(yd5resp.getFees());
//			}
//		
//
//		//保存订单
		HW0025Request req = request;
		
		TibcoUtil.checkNotNull(req.getProductId(), "productId ");
		TibcoUtil.checkNotNull(req.getMdn(), "serviceId ");
		TibcoUtil.checkNotNull(req.getRefNo(), "regNo ");
		
		IUserinfo ui = this.getUserinfo();
		User creator = userService.loadUserByUserCode(ui.getUserName());
//
		AgentOrder order = new AgentOrder();
		order.setTibcoOrderNumber(request.getRefNo());
		order.setCreator(creator);
		order.setPayMode(req.getPayMethodId());
		Long packageFee = 0l;
		try {
			packageFee = req.getTotalFee() != null ? Long.parseLong(req.getTotalFee()) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		order.setPackageFee(packageFee);
		order.setSvn(req.getMdn());
		Product p = new Product();
		
		p.setRangeId(Long.parseLong(req.getProductId()));
		Product pro = productService.loadProductByBSSId(req.getProductId());
		if(pro!=null){
			order.setProduct(pro);
		}
//		order.setProduct(req.getProductId()==null ? null
//				: new Product(Long.valueOf(req.getProductId())));
		order.setProduct(p);
		order.setSaleFee(packageFee);
		order.setOptType(SYSConstant.AGENT_ORDER_TYPE_RECHARGE);
//		order.setFeeDetail(feeDtl);
		this.agentOrderService.createRechargeOrderByAgent(order);
		
//		
//		if(StringUtils.isNotBlank(order.getFeeDetail())){
//			response.setFeeInfos(JsonUtil.fromJsonStringReturnList(order.getFeeDetail(), HW0025Response.FeeInfo.class));
//		}
//		response.setOrderCode(order.getOrderCode());
//		
//		//验证码校验
//		if(request.getVerfyId()==null){
//			throw new Exception("短信校验编码为空");
//		}
//		if(request.getVerfyCode()==null){
//			throw new Exception("校验码为空");
//		}
//		VerifySms verifySmsById = this.verifySmsService.getVerifySmsById(Integer.parseInt(request.getVerfyId()));
//		if(!StringUtil.equals(verifySmsById.getCode(), request.getVerfyCode())){
//			throw new Exception("校验码错误");
//		}
//		Date now = new Date();
//		if(now.getTime()>verifySmsById.getInvalidtime().getTime()){
//			throw new Exception("校验码已过期");
//		}
//		//tibco调用
//		YD0016Request yd16r = new YD0016Request();
//		yd16r.setDealerId(creator.getUserId());
//		yd16r.setMdn(req.getMdn());
//		yd16r.setProductId(req.getProductId());
//		yd16r.setVoucherNo(req.getVoucherNo());
//		yd16r.setTotalFee(tf);
//		yd16r.setOrderId(order.getOrderId().toString());
//		Object ssid = MappContext.getAttribute(MappContext.MAPPCONTEXT_SESSIONID);
//		String sn = "9981737181";
//		if(org.apache.commons.lang.StringUtils.equals("1", test)){
//			
//		}else{
////			YDDatapackage sm = this.sendMsg("yd0016", yd16r, ssid==null?null:ssid.toString());
//			YDDatapackage sm = null;
//			YD0016Response r = (YD0016Response) sm.getBody();
//			sn = r.getSn();
//		}
//		order.setSn(sn);
//		this.agentOrderService.saveAgentOrder(order);
		
		//更新dealer订单状态
		
		response.setOrderCode(order.getOrderCode());

	}
}
