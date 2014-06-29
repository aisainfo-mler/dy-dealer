package com.ailk.yd.mapp.client.action;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.entity.ProductSpecMapping;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.DealerDataService;
import com.ai.mapp.sys.service.ProductService;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0010Request;
import com.ailk.yd.mapp.client.model.HW0010Response;
import com.ailk.yd.mapp.tibco.TibcoConstant;
import com.ailk.yd.mapp.tibco.action.YD0002Action;
import com.ailk.yd.mapp.tibco.model.YD0002.YD0002Request;
import com.ailk.yd.mapp.tibco.model.YD0002.YD0002Response;

@Service("hw0010")
@Action(bizcode="hw0010",userCheck=true)
@Scope("prototype")
public class HW0010Action extends AbstractYDBaseActionHandler<HW0010Request, HW0010Response> {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private AgentOrderService agentOrderService;
	
	private YD0002Action yd0002;
	
	@Override
	protected void doAction() throws BusinessException, SystemException,Exception {
		
		IUserinfo ui = (IUserinfo)MappContext.getAttribute(MappContext.MAPPCONTEXT_USER);
		User creator = userService.loadUserByUserCode(ui.getUserName());
		
		if(request.getCafInfos() == null || request.getCafInfos().isEmpty())
			throw new Exception("no caf information");
		
		HW0010Request.CafInfo caf = request.getCafInfos().get(0);
		
		if(caf.getOrder() == null)
			throw new Exception("no order information");
		
		if(StringUtils.isBlank(caf.getOrder().getOrn()) && StringUtils.isBlank(caf.getOrder().getMdn()))
		{
			YD0002Request yd0002_req = new YD0002Request();
			yd0002_req.setBusinessChannelInteraction(new YD0002Request.Channel());
			yd0002_req.setChannel(caf.getOrder().getChannel());
			yd0002_req.setOrderNumber(caf.getOrder().getOrn());
			yd0002_req.setSvcNum(caf.getOrder().getMdn());
			
			YD0002Response yd0002_rsp = yd0002.post2Tibco(yd0002_req, null);
			
			String number_orn = yd0002_rsp.getServiceProviderEmployee();
			String msg = yd0002_rsp.getMessage();
			String state = yd0002_rsp.getResponse()==null?null:yd0002_rsp.getResponse().getInteractionStatus();
			
			if(TibcoConstant.SELECT_SPEC_NUM_STATUS_ERR.equals(state) == false || caf.getOrder().getOrn().equals(number_orn) == false)
				throw new Exception("Number has blocked by order: " + number_orn+","+msg);
		}
		
		
		AgentOrder order = new AgentOrder();
		order.setCreator(creator);
		order.setPayMode(SYSConstant.PAY_MODE_ACCOUNT);
		order.setPackageFee(0L);//
		order.setSim(caf.getOrder().getSim());
		order.setSvn(caf.getOrder().getMdn());
		order.setNumberFee(caf.getOrder().getMdnFee()==null?0L:caf.getOrder().getMdnFee().longValue());
		if(caf.getOrder().getImei()  != null && caf.getOrder().getImei().isEmpty() == false)
			order.setSim(mapper.writeValueAsString(caf.getOrder().getImei()));
		//		order.setImsi(req.getImsi());
		order.setProduct(caf.getOrder().getPid() == null ? null : new Product(caf.getOrder().getPid()));
		order.setPayMode(caf.getPayInfo() ==null?null:caf.getPayInfo().getModeOfPayment());
		order.setBankSerial(caf.getPayInfo() ==null?null:caf.getPayInfo().getReceiptNumber());
		order.setBankCode(caf.getPayInfo() ==null?null:caf.getPayInfo().getBankName());
		order.setCafInfo(mapper.writeValueAsString(request));
		order.setTibcoOrderNumber(caf.getOrder().getOrn());
		
		//算费接口
		BigDecimal totalFee = BigDecimal.ZERO;
		BigDecimal numberFee = caf.getOrder().getMdnFee()==null?BigDecimal.ZERO:caf.getOrder().getMdnFee();
		
		Set<String> pcodes = new HashSet<String>(0);
		if(StringUtils.isBlank(caf.getOrder().getOfferId()) == false);
			pcodes.add(caf.getOrder().getOfferId());
		if(StringUtils.isBlank(caf.getOrder().getPlanOffering()) == false)
			pcodes.add(caf.getOrder().getPlanOffering());
		
		BigDecimal productFee = BigDecimal.ZERO;
		
		if(pcodes != null && pcodes.isEmpty()==false)
		{
			Product condition = new Product();
			condition.setBssReangeIds(pcodes);
			Collection<Product> products = productService.listAllProducts(condition);
			if(products != null && products.isEmpty() == false)
			{
				for(Product p : products)
					productFee = productFee.add(productService.calProductFee(p));
			}
		}
		
		Map<String,BigDecimal> feeMap = new HashMap<String, BigDecimal>(0);
		feeMap.put("mdn", numberFee);
		feeMap.put("product", productFee);
		feeMap.put("resource", getResourceFee(caf.getOrder().getOfferId()));
		feeMap.put("plan", BigDecimal.ZERO);
		if(StringUtils.isBlank(caf.getOrder().getPlanOffering()) == false)
		{
			Product plan = productService.getProductByCode(caf.getOrder().getPlanOffering());
			if(plan != null)
			feeMap.put("plan", plan.getPrice()==null?BigDecimal.ZERO:BigDecimal.valueOf(plan.getPrice()));	
		}
		
		order.setNumberFee(numberFee.longValue());
		order.setPackageFee(productFee.longValue());
		order.setSaleFee(totalFee.add(productFee).add(numberFee).longValue());
		order.setFeeDetail(mapper.writeValueAsString(feeMap));
		order = agentOrderService.createNewOrderByAgent(order,creator);
		this.response.setOrderCode(order.getOrderCode());
	}

	private BigDecimal getResourceFee(String productCode) throws Exception
	{
		BigDecimal resourceFee = BigDecimal.ZERO;
		
		if(StringUtils.isBlank(productCode))
			return resourceFee;
		
		Product p = productService.getProductByCode(productCode);
		
		if(p == null)
			return resourceFee;
		
		/** 设置resourceSpec **/
		if(StringUtils.isEmpty(p.getProductSpecList()) == false)
		{
			ProductSpecMapping productSpecMapping = DealerDataService.mapper.readValue(p.getProductSpecList(),ProductSpecMapping.class);
			if(productSpecMapping != null && productSpecMapping.getProductSpecs() != null && productSpecMapping.getProductSpecs().isEmpty() == false)
			{
				for(ProductSpecMapping.ProductSpec productSpec : productSpecMapping.getProductSpecs())
				{
					if(productSpec.getResourceSpecList() == null || productSpec.getResourceSpecList().isEmpty())
						continue;
					
					for(ProductSpecMapping.ResourceSpec resourceSpec : productSpec.getResourceSpecList())
					{
						if(StringUtils.isBlank(resourceSpec.getComponentPrice()) == false)
						{
							resourceFee.add(new BigDecimal(resourceSpec.getComponentPrice()));
						}
					}
				}
			}
		}
			
		return resourceFee;
	}
	

}
