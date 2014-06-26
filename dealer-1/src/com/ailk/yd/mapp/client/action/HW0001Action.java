package com.ailk.yd.mapp.client.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.Plan2ProductMapping;
import com.ai.mapp.sys.entity.PlanSpecMapping;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.entity.ProductSpecMapping;
import com.ai.mapp.sys.service.DealerDataService;
import com.ai.mapp.sys.service.ProductService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0001Request;
import com.ailk.yd.mapp.client.model.HW0001Response;

@Service("hw0001")
@Action(bizcode="hw0001",userCheck=false)
@Scope("prototype")
public class HW0001Action extends AbstractYDBaseActionHandler<HW0001Request, HW0001Response> {

	@Autowired
	private ProductService productService;

	
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		
		Product condition = new Product();
		condition.setFilterCondition(request.getFilterMap());
		condition.setServicetype(request.getServiceType());
		condition.setType(request.getType());
		
		Collection<Product> products = null;
		if(request.getCodes() != null && request.getCodes().isEmpty() == false)
		{
			condition.setBssReangeIds(request.getCodes());
			products = productService.listAllProducts(condition);
		}
		else
		{
			products = productService.listProducts(condition, request.getPage(), request.getSize());
		}
		
		/** 设置子plan **/
		Map<String,Product> planMap = gePlanMap(products);
		List<HW0001Response.Product> hw_products = handlerProduct(products,planMap);
		response.setProducts(hw_products);
	}
	
	private Map<String,Product> gePlanMap(Collection<Product> products) throws Exception
	{
		if(products == null || products.isEmpty())
			return null;
		
		Set<String> plancodes = new HashSet<String>(0);
		Map<String,Product> planMap = new HashMap<String,Product>(0);
		
		for(Product p : products)
		{
			if(p.getPlancodes() == null)
				continue;
			
			List<Plan2ProductMapping> ppm_list = mapper.readValue(p.getPlancodes(),new TypeReference<List<Plan2ProductMapping>>(){});
			if(ppm_list == null || ppm_list.isEmpty())
				continue;
			
			for(Plan2ProductMapping ppm : ppm_list)
			{
				plancodes.add(ppm.getPlanOfferingID());
			}
		}
		
		/** 一次性获取或有的子plan **/
		Product condition = new Product();
		condition.setBssReangeIds(plancodes);
		Collection<Product> plan_list = productService.listAllProducts(condition);
		
		if(plan_list == null || plan_list.isEmpty())
			return null;
		
		for(Product plan : plan_list)
		{
			planMap.put(plan.getBssRangeId(), plan);
		}
		
		return planMap;
	}
	
	private List<HW0001Response.Product> handlerProduct(Collection<Product> products, Map<String,Product> planMap) throws Exception
	{
		if(products == null || products.isEmpty())
			return null;
					
		List<HW0001Response.Product> hw_products = new ArrayList<HW0001Response.Product>(0);
		response.setProducts(hw_products);
		
		for(Product p : products)
		{
			HW0001Response.Product hw_p = new HW0001Response.Product();
			hw_products.add(hw_p);
			
			hw_p.setCode(p.getBssRangeId());
			hw_p.setId(p.getRangeId());
			hw_p.setName(p.getPackedName());
			hw_p.setPrice(p.getPrice() == null?BigDecimal.ZERO:BigDecimal.valueOf(p.getPrice()));
			hw_p.setType(p.getType());
			List<HW0001Response.ProductSpec> productSpecList = handlerProductSpecList(p);
			hw_p.setProductSpecList(productSpecList);
			hw_p.setPlanSpecList(handlerPlanSpecList(p));

			/**
			 * 设置planList
			 */
			if(StringUtils.isBlank(p.getPlancodes()))
				continue;
			
			List<Plan2ProductMapping> ppm_list = mapper.readValue(p.getPlancodes(),new TypeReference<List<Plan2ProductMapping>>(){});
			if(ppm_list == null || ppm_list.isEmpty())
				continue;
			
			List<Product> plan_list = new ArrayList<Product>(0);
			
			for(Plan2ProductMapping ppm : ppm_list)
			{
				if(planMap.containsKey(ppm.getPlanOfferingID()))
					plan_list.add(planMap.get(ppm.getPlanOfferingID()));
			}
			hw_p.setPlanList(handlerProduct(plan_list, planMap));
		}
		
		return hw_products;
	}
	
	/**
	 * 组装ProductSpecList对象
	 * @param p
	 * @return
	 * @throws Exception
	 */
	private List<HW0001Response.ProductSpec> handlerProductSpecList(Product p) throws Exception
	{
		if(StringUtils.isBlank(p.getProductSpecList() ))
			return null;
		
		ProductSpecMapping productSpecMapping = DealerDataService.mapper.readValue(p.getProductSpecList(),ProductSpecMapping.class);
		/** 设置productSpec **/
		if(productSpecMapping == null || productSpecMapping.getProductSpecs() == null || productSpecMapping.getProductSpecs().isEmpty())
			return null;
		
		List<ProductSpecMapping.ProductSpec> productSpecList = productSpecMapping.getProductSpecs();
		List<HW0001Response.ProductSpec> hw_productSpecList =  new ArrayList<HW0001Response.ProductSpec>(0);
		
		for(ProductSpecMapping.ProductSpec ps : productSpecList)
		{
			HW0001Response.ProductSpec hw_ps = new HW0001Response.ProductSpec();
			hw_productSpecList.add(hw_ps);
			
			hw_ps.setCode(ps.getProductSpecificationId());
			hw_ps.setPrice(StringUtils.isBlank(ps.getCompositePrice())?BigDecimal.ZERO:new BigDecimal(ps.getCompositePrice()));
			hw_ps.setName(ps.getName());
			hw_ps.setType(ps.getType());
			hw_ps.setCheckServiceAvailability(ps.getCheckServiceAvailability());
			hw_ps.setIsCAFRequired(ps.getIsCafRequired());
			
			/** 设置ProductSpec下面的ServiceSpec **/
			if(ps.getServiceSpecList() != null && ps.getServiceSpecList().isEmpty() == false)
			{
				List<HW0001Response.ServiceSpec> hw_serviceSpecList =  new ArrayList<HW0001Response.ServiceSpec>(0);
				hw_ps.setServiceSpecList(hw_serviceSpecList);
				
				for(ProductSpecMapping.ServiceSpec ss : ps.getServiceSpecList())
				{
					HW0001Response.ServiceSpec hw_ss = new HW0001Response.ServiceSpec();
					hw_serviceSpecList.add(hw_ss);
					
					hw_ss.setCode(ss.getServiceSpecificationId());
					hw_ss.setName(ss.getName());
//					hw_ss.setPrice(StringUtils.isBlank(ss.get));
					hw_ss.setType(ss.getType());
				}
			}
			
			/** 设置ProductSpec下面的ResourceSpec **/
			if(ps.getResourceSpecList() != null && ps.getResourceSpecList().isEmpty() == false)
			{
				List<HW0001Response.ResourceSpec> hw_resourceSpecList =  new ArrayList<HW0001Response.ResourceSpec>(0);
				hw_ps.setResourceSpecList(hw_resourceSpecList);
				
				for(ProductSpecMapping.ResourceSpec rs : ps.getResourceSpecList())
				{
					HW0001Response.ResourceSpec hw_rs = new HW0001Response.ResourceSpec();
					hw_resourceSpecList.add(hw_rs);
					
					hw_rs.setCode(rs.getResourceSpecificationId());
					hw_rs.setName(rs.getName());
					hw_rs.setType(rs.getType());
					hw_rs.setPrice(StringUtils.isBlank(rs.getComponentPrice())?BigDecimal.ZERO:new BigDecimal(rs.getComponentPrice()));
				}
			}
		}
			
		return hw_productSpecList;
	}
	
	/**
	 * 组装PlanSpecList对象
	 * @param p
	 * @return
	 * @throws Exception
	 */
	private List<HW0001Response.PlanSpec> handlerPlanSpecList(Product p) throws Exception
	{
		if(StringUtils.isBlank(p.getPlanSpecList() ))
			return null;
		
		PlanSpecMapping planSpecMapping = DealerDataService.mapper.readValue(p.getPlanSpecList(),PlanSpecMapping.class);
		/** 设置planSpec **/
		if(planSpecMapping == null || planSpecMapping.getPlanSpecs() == null || planSpecMapping.getPlanSpecs().isEmpty())
			return null;
		
		List<PlanSpecMapping.PlanSpec> planSpecList = planSpecMapping.getPlanSpecs();
		List<HW0001Response.PlanSpec> hw_planSpecList =  new ArrayList<HW0001Response.PlanSpec>(0);
		
		for(PlanSpecMapping.PlanSpec ps : planSpecList)
		{
			HW0001Response.PlanSpec hw_ps = new HW0001Response.PlanSpec();
			hw_planSpecList.add(hw_ps);
			
			hw_ps.setCode(ps.getPlanSpecificationid());
			hw_ps.setPrice(StringUtils.isBlank(ps.getComponentPrice())?BigDecimal.ZERO:new BigDecimal(ps.getComponentPrice()));
			hw_ps.setName(ps.getName());
			hw_ps.setType(ps.getType());
			
			/** 设置PlanSpec下面的ServiceSpec **/
			if(ps.getPlanSpecCharList() != null && ps.getPlanSpecCharList().isEmpty() == false)
			{
				List<HW0001Response.PlanSpecChar> hw_planSpecCharList =  new ArrayList<HW0001Response.PlanSpecChar>(0);
				hw_ps.setPlanSpecCharList(hw_planSpecCharList);
				
				for(PlanSpecMapping.PlanSpecCharacteristic psc : ps.getPlanSpecCharList())
				{
					HW0001Response.PlanSpecChar hw_psc = new HW0001Response.PlanSpecChar();
					hw_planSpecCharList.add(hw_psc);
					
					hw_psc.setCode(psc.getPlanSpecCharacteristicId());
					hw_psc.setName(psc.getName());
					hw_psc.setType(psc.getType());
//					hw_psc.setPrice(price);
				}
			}
		}
			
		return hw_planSpecList;
	}

}
