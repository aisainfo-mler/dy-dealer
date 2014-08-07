package com.ailk.yd.mapp.client.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.ProductService;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0013Request;
import com.ailk.yd.mapp.client.model.HW0013Response;
import com.ailk.yd.mapp.model.UserInfo;


@Service("hw0013")
@Action(bizcode="hw0013",userCheck=true)
@Scope("prototype")
public class HW0013Action extends
		AbstractYDBaseActionHandler<HW0013Request, HW0013Response> {

	@Autowired
	private AgentOrderService agentOrderService;
	
	@Autowired
	private ProductService productService;
	
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		
		
		HW0013Request req = request;
		UserInfo ui = (UserInfo) this.getUserinfo();
		
		AgentOrder condition = new AgentOrder();
		
		if(StringUtil.isEmpty(req.getEndTime()) == false)
			condition.setEndTime(DateUtils.getDate(req.getEndTime()+" 23:59:59","yyyy-MM-dd HH:mm:ss"));
		if(StringUtil.isEmpty(req.getStartTime()) == false)
			condition.setStartTime(DateUtils.getDate(req.getStartTime()+" 00:00:00","yyyy-MM-dd HH:mm:ss"));
		if(StringUtil.isEmpty(req.getOrderCode()) == false)
			condition.setOrderCode(req.getOrderCode());
		if(StringUtil.isEmpty(req.getPayStatus()) == false)
			condition.setPayStatus(req.getPayStatus());
		if(StringUtil.isEmpty(req.getStatus()) == false)
			condition.setStatus(req.getStatus());
		if(StringUtil.isEmpty(req.getPayType()) == false)
			condition.setPayType(req.getPayType());
		if(StringUtil.isEmpty(req.getOrderType()) == false)
			condition.setOrderType(req.getOrderType());
		if(StringUtil.isEmpty(req.getSvn()) == false)
			condition.setSvn(req.getSvn());
		if(StringUtil.isEmpty(ui.getUsercode()) == false)
			condition.setCreator(new User(ui.getUsercode()));
		if(StringUtil.isEmpty(req.getOrn()) == false)
			condition.setTibcoOrderNumber(req.getOrn());
		
		int start = StringUtil.isEmpty(req.getStart())  ? 0 : Integer.valueOf(req.getStart());
		int pageSize = StringUtil.isEmpty(req.getSize()) ? 16 : Integer.valueOf(req.getSize());
		
		Collection<AgentOrder> orders = null;
	
		if(StringUtil.isEmpty(req.getStart()) == false && Integer.valueOf(req.getStart()) < 0 )
			orders = agentOrderService.listAllAgentOrders(condition);
		else
			orders = agentOrderService.listAgentOrders(condition, start, pageSize);
		
		List<HW0013Response.Order> rm = new ArrayList<HW0013Response.Order>();
		for (Iterator<AgentOrder> it = orders.iterator(); it.hasNext();) {
			AgentOrder order = it.next();
			

			HW0013Response.Order d = new HW0013Response.Order();
			
			d.setBalance(order.getBlance()== null?"":order.getBlance().toString());
			d.setCreateTime(order.getCreateTime() == null ? "":DateUtils.getDateString(order.getCreateTime(), "dd/MM/yyyy HH:mm:ss"));
			d.setOrderCode(order.getOrderCode()== null?"":order.getOrderCode().toString());
			d.setOrderType(order.getOrderType()== null?"":order.getOrderType().toString());
			d.setPreStore(order.getPreStore()== null?"":order.getPreStore().toString());
			d.setProductId(order.getProduct()== null?"":order.getProduct().getRangeId().toString());
			d.setSim(order.getSim()== null?"":order.getSim());
			d.setSvn(order.getSvn());
			d.setDiscountFee(order.getDiscountFee()== null?"":order.getDiscountFee().toString());
			d.setPayMode(order.getPayMode()== null?"":order.getPayMode().toString());
			d.setRealFee(order.getRealFee()== null?"":order.getRealFee().toString());
			d.setSaleFee(order.getSaleFee()== null?"":order.getSaleFee().toString());
			d.setTotalFee(order.getSaleFee()== null?"":order.getSaleFee().toString());
			String productType=productService.getProductTypeName(order.getProduct(),LanguageInfo.CURR_LANGUAGE);
			d.setProductType(productType==null?"":productType);
			d.setOrderStatus(order.getStatus()== null?"":order.getStatus());
			d.setPayStatus(order.getPayStatus()== null?"":order.getPayStatus());
			d.setPin(order.getPin()== null?"":order.getPin());
			d.setPackageFee(order.getPackageFee() == null?"0":order.getPackageFee().toString());
			d.setPackageName(order.getProduct()==null?"":order.getProduct().getPackedName());
			d.setSimFee(order.getSimFee() == null?"0":order.getSimFee().toString());
			d.setSIMFee(order.getSimFee() == null?"0":order.getSimFee().toString());
			d.setNumberFee(order.getNumberFee() == null?"0":order.getNumberFee().toString());
			d.setOrn(order.getTibcoOrderNumber() == null?"":order.getTibcoOrderNumber());
			d.setAccountLevel(StringUtils.equals(order.getAccountLevel(), "1"));
			
			if(StringUtils.isNotBlank(order.getFeeDetail()))
			{
				Map<String,BigDecimal> feeDetail = (Map<String,BigDecimal>)mapper.readValue(order.getFeeDetail(), new TypeReference<Map<String,BigDecimal>>(){});
				d.setFeeDetail(feeDetail);
			}
			rm.add(d);
		}
		response.setOrders(rm);
	}

}
