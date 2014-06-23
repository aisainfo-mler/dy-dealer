package com.ailk.yd.mapp.client.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.entity.VerifySms;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.service.VerifySmsService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.util.JsonUtil;
import com.ailk.yd.mapp.client.model.FeeInfo;
import com.ailk.yd.mapp.client.model.HW0025Request;
import com.ailk.yd.mapp.client.model.HW0025Response;
import com.ailk.yd.mapp.model.YDDatapackage;
import com.ailk.yd.mapp.tibco.model.YD0005.YD0005Request;
import com.ailk.yd.mapp.tibco.model.YD0005.YD0005Response;
import com.ailk.yd.mapp.tibco.model.YD0016.YD0016Request;
import com.ailk.yd.mapp.tibco.model.YD0016.YD0016Response;

/**
 * 叠加包下单接口 同yd0016
 * 
 * @author qianshihua
 * 
 */
@Service("hw0025")
@Action(bizcode = "hw0025", userCheck = true)
public class HW0025Action extends
		AbstractYDBaseActionHandler<HW0025Request, HW0025Response> {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AgentOrderService agentOrderService;
	
	@Autowired
	private VerifySmsService verifySmsService;

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		// 算费
		BigDecimal tf;
		String feeDtl = "";
		if (StringUtil.equals("1", test)) {
			tf = new BigDecimal(8821);
			List l = new ArrayList();
			FeeInfo fi = new FeeInfo();
			fi.setFee(new BigDecimal(8800));
			fi.setFeeType(1l);
			fi.setName("测试1");
			FeeInfo fi1 = new FeeInfo();
			fi1.setFee(new BigDecimal(21));
			fi1.setFeeType(3l);
			fi1.setName("222");
			l.add(fi1);
			l.add(fi);
			feeDtl = JsonUtil.ListToJsonString(l);
			
		} else {
			YD0005Request yd5r = new YD0005Request();
			yd5r.setMdn(request.getMdn());
			List<Long> l = new ArrayList() {
				{
					add(request.getProductId());
				}
			};
			yd5r.setProducts(l);
			yd5r.setProductType("");
			Object ssid = MappContext
					.getAttribute(MappContext.MAPPCONTEXT_SESSIONID);
//			YDDatapackage yddp = this.sendMsg("yd0005", yd5r,
//					ssid != null ? ssid.toString() : null);
			
			YDDatapackage yddp = null;
			YD0005Response yd5resp = (YD0005Response) yddp.getBody();
			tf = yd5resp.getTotalFee();
			if(yd5resp.getFees()!=null && yd5resp.getFees().size()>0){
				feeDtl = JsonUtil.ListToJsonString(yd5resp.getFees());
			}
		}

		//保存订单
		HW0025Request req = request;
		IUserinfo ui = this.getUserinfo();
		User creator = userService.loadUserByUserCode(ui.getUserName());

		AgentOrder order = new AgentOrder();
		order.setBankSerial(req.getVoucherNo());
		order.setCreator(creator);
		order.setPayMode(req.getPayMethodId());
		order.setPackageFee(req.getTotalFee() != null ? req.getTotalFee()
				.longValue() : null);
		order.setSvn(req.getMdn());
		order.setProduct(req.getProductId()==null ? null
				: new Product(Long.valueOf(req.getProductId())));
		order.setSaleFee(tf.longValue());
		order.setFeeDetail(feeDtl);
		
		this.agentOrderService.createAddOnOrder(order);
		
		if(StringUtils.isNotBlank(order.getFeeDetail())){
			response.setFeeInfos(JsonUtil.fromJsonStringReturnList(order.getFeeDetail(), HW0025Response.FeeInfo.class));
		}
		response.setOrderCode(order.getOrderCode());
		
		//验证码校验
		if(request.getVerfyId()==null){
			throw new Exception("短信校验编码为空");
		}
		if(request.getVerfyCode()==null){
			throw new Exception("校验码为空");
		}
		VerifySms verifySmsById = this.verifySmsService.getVerifySmsById(Integer.parseInt(request.getVerfyId()));
		if(!StringUtil.equals(verifySmsById.getCode(), request.getVerfyCode())){
			throw new Exception("校验码错误");
		}
		Date now = new Date();
		if(now.getTime()>verifySmsById.getInvalidtime().getTime()){
			throw new Exception("校验码已过期");
		}
		//tibco调用
		YD0016Request yd16r = new YD0016Request();
		yd16r.setDealerId(creator.getUserId());
		yd16r.setMdn(req.getMdn());
		yd16r.setProductId(req.getProductId());
		yd16r.setVoucherNo(req.getVoucherNo());
		yd16r.setTotalFee(tf);
		yd16r.setOrderId(order.getOrderId().toString());
		Object ssid = MappContext.getAttribute(MappContext.MAPPCONTEXT_SESSIONID);
		String sn = "9981737181";
		if(org.apache.commons.lang.StringUtils.equals("1", test)){
			
		}else{
//			YDDatapackage sm = this.sendMsg("yd0016", yd16r, ssid==null?null:ssid.toString());
			YDDatapackage sm = null;
			YD0016Response r = (YD0016Response) sm.getBody();
			sn = r.getSn();
		}
		order.setSn(sn);
		this.agentOrderService.saveAgentOrder(order);
		
		//更新dealer订单状态
		

	}
}
