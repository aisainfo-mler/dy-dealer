package com.ailk.yd.mapp.client.action;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AgentOrderService;
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

@Service("hw0010")
@Action(bizcode="hw0010",userCheck=true)
public class HW0010Action extends AbstractYDBaseActionHandler<HW0010Request, HW0010Response> {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private AgentOrderService agentOrderService;
	
	@Override
	protected void doAction() throws BusinessException, SystemException,Exception {
		
		IUserinfo ui = (IUserinfo)MappContext.getAttribute(MappContext.MAPPCONTEXT_USER);
		User creator = userService.loadUserByUserCode(ui.getUserName());
		
		if(request.getCafInfos() == null || request.getCafInfos().isEmpty())
			throw new Exception("no caf information");
		
		HW0010Request.CafInfo caf = request.getCafInfos().get(0);
		
		if(caf.getOrder() == null)
			throw new Exception("no order information");
		
		AgentOrder order = new AgentOrder();
		order.setCreator(creator);
		order.setPayMode(SYSConstant.PAY_STATUS_ACCOUNT);
		order.setPackageFee(0L);//
		order.setSim(caf.getOrder().getSim());
		order.setSvn(caf.getOrder().getMdn());
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
		if(caf.getOrder().getMdnFee() != null)
			totalFee = totalFee.add(caf.getOrder().getMdnFee());
		
		Set<String> pcodes = new HashSet<String>(0);
		if(StringUtils.isBlank(caf.getOrder().getOfferId()) == false);
			pcodes.add(caf.getOrder().getOfferId());
		if(StringUtils.isBlank(caf.getOrder().getPlanOffering()) == false)
			pcodes.add(caf.getOrder().getPlanOffering());
		
		if(pcodes != null && pcodes.isEmpty()==false)
		{
			Product condition = new Product();
			condition.setBssReangeIds(pcodes);
			Collection<Product> products = productService.listAllProducts(condition);
			if(products != null && products.isEmpty() == false)
			{
				for(Product p : products)
					totalFee = totalFee.add(productService.calProductFee(p));
			}
		}
		order.setSaleFee(totalFee.longValue());
		order = agentOrderService.createNewOrderByAgent(order,creator);
		this.response.setOrderCode(order.getOrderCode());
	}

}
