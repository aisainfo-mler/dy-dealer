package com.ai.mapp.sys.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.sys.entity.GeographicLocationMapping;
import com.ai.mapp.sys.entity.Plan2ProductMapping;
import com.ai.mapp.sys.entity.PlanSpecMapping;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.entity.ProductFilter;
import com.ai.mapp.sys.entity.ProductSpecMapping;
import com.ailk.butterfly.core.util.DateUtils;

@Service
@Scope("prototype")
@Transactional
public class DealerDataService {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductFilterService productFilterService;
	
	public static final ObjectMapper mapper = new ObjectMapper();
	private Map<String, Map<String,Object>> productMap 		= new HashMap<String, Map<String,Object>>();// key为proSpec编码，value为Map
	private Map<String, Map<String,Object>> productSpecMap 	= new HashMap<String, Map<String,Object>>();// key为proSpec编码，value为Map
	private Map<String, Map<String,Object>> planMap 		= new HashMap<String, Map<String,Object>>();// key为planOff编码，value为map
	private Map<String, Map<String,Object>> planSepcMap 	= new HashMap<String, Map<String,Object>>();// key为planSPec的编码，value为map
	private Map<String, Map<String,Object>> planSpecCharMap = new HashMap<String, Map<String,Object>>();// key为planOff编码，value为planSpec编码s
	private Map<String, Map<String,Object>> serviceSpecMap 	= new HashMap<String, Map<String,Object>>();
	private Map<String, Map<String,Object>> resourceSpecMap		  = new HashMap<String, Map<String,Object>>();
	private Map<String, Map<String,Object>> componentPriceMap = new HashMap<String,Map<String,Object>>(0);
	private Map<String, Map<String,Object>> compositePriceMap = new HashMap<String,Map<String,Object>>(0);
	private Map<String, Map<String,Object>> geographicLocationMap = new HashMap<String,Map<String,Object>>(0);
	private Map<String, Map<String,Map<String,Object>>> propMap = new HashMap<String,Map<String,Map<String,Object>>>(0);
	
	public void updateProductInfoByFile(String url) throws Exception {
		File inputXml = new File(url);
		updateProductInfoByFile(inputXml);
	}

	public void updateProductInfoByFile(File file) throws Exception 
	{
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(file);
		
		init(document);
		
		List<Product> products = getProductList();
		for(Product p : products)
		{
			if(p.getProductSpecList() != null)
			{
				ProductSpecMapping productSpecList = mapper.readValue(p.getProductSpecList(), ProductSpecMapping.class);
//				System.out.println(mapper.writeValueAsString(productSpecList.getProductSpecs().get(0).getServiceSpecList().get(0)));
				System.err.println("***********************测试 ProductId："+p.getBssRangeId()+"**********************************************");
			}
		}
		
		List<Product> plans = getPlanList();
		for(Product p : plans)
		{
			if(p.getPlanSpecList() != null)
			{
				PlanSpecMapping planSpecList = mapper.readValue(p.getPlanSpecList(), PlanSpecMapping.class);
//				System.out.println(mapper.writeValueAsString(planSpecList.getPlanSpecs().get(0).getPlanSpecCharList().get(0)));
				System.err.println("***********************测试 planId："+p.getBssRangeId()+"**********************************************");
			}
		}
		
		products.addAll(plans);
		
		saveProductAndFilter(products);

	}
	
	private void saveProductAndFilter(List<Product> products) throws Exception
	{
		if(products == null || products.isEmpty())
			return;
		
		/**
		 * 数据结构 Map<productid，Map<filterType,Set<filterValue>>
		 */
		Map<Long,Map<String,Set<String>>> product_filter_map = new HashMap<Long,Map<String,Set<String>>>(0);
		
		for(Product p : products)
		{
			/** 先保存product，生成productid **/
			productService.saveProduct(p);
			
			Long productId = p.getRangeId();
			/**
			 * serviceType的filter设置
			 */
			if(StringUtils.isBlank(p.getProductSpecList()) == false)
			{
				if(product_filter_map.get(productId) == null)
					product_filter_map.put(productId, new HashMap<String,Set<String>>(0));
				
				ProductSpecMapping productSpecMapping = mapper.readValue(p.getProductSpecList(),ProductSpecMapping.class);
				if(productSpecMapping == null || productSpecMapping.getProductSpecs()==null || productSpecMapping.getProductSpecs().isEmpty())
					continue;
				
				List<ProductSpecMapping.ProductSpec> productSpecList = productSpecMapping.getProductSpecs();
				
				for(ProductSpecMapping.ProductSpec ps : productSpecList)
				{
					if(ps.getServiceSpecList() != null && ps.getServiceSpecList().isEmpty() == false)
					{
						product_filter_map.get(productId).put(ProductFilter.FILTER_TYPE_SERVICE_TYPE, new HashSet<String>(0));
						for(ProductSpecMapping.ServiceSpec ss : ps.getServiceSpecList())
						{
							product_filter_map.get(productId).get(ProductFilter.FILTER_TYPE_SERVICE_TYPE).add(ss.getServiceType());
							
							/**
							 * TODO 如果serviceType为lte-data,则可以有speed类型的filter
							 */
						}
					}
				}
			}
			
			if(StringUtils.isBlank(p.getGeographicLocationList()) == false)
			{
				if(product_filter_map.get(productId) == null)
					product_filter_map.put(productId, new HashMap<String,Set<String>>(0));
				
				GeographicLocationMapping geographicLocationMapping = mapper.readValue(p.getGeographicLocationList(), GeographicLocationMapping.class);
				if(geographicLocationMapping.getGeographicLocations() == null || geographicLocationMapping.getGeographicLocations().isEmpty())
					continue;
				
				List<GeographicLocationMapping.GeographicLocation> geographicLocationList = geographicLocationMapping.getGeographicLocations();
				
				product_filter_map.get(productId).put(ProductFilter.FILTER_TYPE_GEOGRAPHICLOCATION, new HashSet<String>(0));
				for(GeographicLocationMapping.GeographicLocation ggh : geographicLocationList)
				{
					product_filter_map.get(productId).get(ProductFilter.FILTER_TYPE_GEOGRAPHICLOCATION).add(ggh.getGeographicLocationMasterId());
				}
			}
			
//			/**
//			 * 如果没有地理信息的筛选条件，即在product级别对地址信息做筛选，则对其下级进行筛选
//			 */
//			if(product_filter_map.get(productId) == null 
//					|| product_filter_map.get(productId).get(ProductFilter.FILTER_TYPE_GEOGRAPHICLOCATION) == null
//					|| product_filter_map.get(productId).get(ProductFilter.FILTER_TYPE_GEOGRAPHICLOCATION).isEmpty())
//			{
//				
//			}
			
		}
		
		/**
		 * 保存filter数据
		 */
		if(product_filter_map == null || product_filter_map.isEmpty())
			return;
		for(Long productId : product_filter_map.keySet())
		{
			if(product_filter_map.get(productId) == null || product_filter_map.get(productId).isEmpty())
				continue;
			
			for(String filterType : product_filter_map.get(productId).keySet())
			{
				if(product_filter_map.get(productId).get(filterType) == null || product_filter_map.get(productId).get(filterType).isEmpty())
					continue;
				
				for(String filterValue : product_filter_map.get(productId).get(filterType))
				{
					ProductFilter pf = new ProductFilter();
					pf.setFilterType(filterType);
					pf.setProduct(new Product(productId));
					pf.setFilterValue(filterValue);
					
					productFilterService.saveProductFilter(pf);
				}
			}
		}
		
		
	}
	
	private void init(Document document) throws Exception
	{
		Element root = document.getRootElement();
		
		componentPriceMap = handleItemList(root, "ComponentPrice", "id");
		System.err.println("***********ComponentPrice********************************************************************************************");
		compositePriceMap = handleItemList(root, "CompositePrice", "id");
		System.err.println("***********CompositePrice********************************************************************************************");
		productMap = handleItemList(root, "ProductOffering", "id");
		System.err.println("***********PlanOffering**********************************************************************************************");
		productSpecMap = handleItemList(root, "ProductSpecification", "id");
		System.err.println("***********ProductSpecification**************************************************************************************");
		planMap = handleItemList(root, "PlanOffering", "id");
		System.err.println("***********PlanOffering**********************************************************************************************");
		planSepcMap = handleItemList(root, "PlanSpecification", "id");
		System.err.println("***********PlanSpecification*****************************************************************************************");
		planSpecCharMap = handleItemList(root, "CompositePrice", "id");
		System.err.println("***********CompositePrice********************************************************************************************");
		serviceSpecMap = handleItemList(root, "ServiceSpecification", "id");
		System.err.println("***********ServiceSpecification**************************************************************************************");
		resourceSpecMap = handleItemList(root, "ResourceSpecification", "id");
		System.err.println("***********ResourceSpecification*************************************************************************************");
		geographicLocationMap = handleItemList(root, "GeographicLocationMaster", "id");
		System.err.println("***********ResourceSpecification*************************************************************************************");
		propMap = handlePropMap(root);
		System.err.println("***********ReferenceValueMaster**************************************************************************************");
	}

	public static Map<String, Map<String,Map<String,Object>>> handlePropMap(Element root) 
	{
		if (root == null || root.element("ReferenceValueMaster") == null
				|| root.element("ReferenceValueMaster").elements("item").isEmpty())
			return null;
		
		Map<String, Map<String,Map<String,Object>>> itemMap = new HashMap<String, Map<String,Map<String,Object>>>(0);
		
		List<Element> itemListElement = root.element("ReferenceValueMaster").elements("item");
		for (Iterator<Element> it=itemListElement.iterator(); it.hasNext();) 
		{
			Element itemElement = (Element) it.next();
			List<Element> subElements = itemElement.element("itemAttributes").elements("itemAttribute");
			if(subElements == null || subElements.isEmpty())
				continue;
			
			/**
			 * 获取//itemAttributes/itemAttribute 节点
			 */
			Map<String,Map<String,Object>> entity = new HashMap<String,Map<String, Object>>(0);
			
			for(Iterator<Element> sub_it = subElements.iterator(); sub_it.hasNext();)
			{
				Element subElement = (Element) sub_it.next();
				List<Element> propElements = subElement.elements("refValueDescriptionItems");
				if(propElements == null || propElements.isEmpty())
					continue;
				
				/**
				 * refValueDescriptionItems节点
				 */
				Map<String,Object> prop_map = new HashMap<String, Object>(0);
				
				for(Iterator<Element> prop_it = propElements.iterator(); prop_it.hasNext();)
				{
					Element propElement = (Element) prop_it.next();
					prop_map.put(propElement.elementText("value"), StringUtils.isBlank(propElement.elementText("description"))?null:propElement.elementTextTrim("description"));
//					System.out.println(propElement.elementText("value")+":"+propElement.elementText("description"));
				}
				entity.put(subElement.elementText("attributeName"), prop_map);
				
			}
			
			itemMap.put(itemElement.elementText("entityName"), entity);
		}
		
		return itemMap;
	
	}
	
	/**
	 * 获取每个Reliance:item的信息，把Reliance:item转化为Map<对象主键,Map<属性名称，值>>
	 * @param element
	 * @param nodeName
	 * @param keyName
	 * @return
	 */
	private Map<String, Map<String,Object>> handleItemList(Element element,String nodeName,String keyName) 
	{
		if (element == null || element.element(nodeName) == null
				|| element.element(nodeName).elements("item").isEmpty())
			return null;
		
		Map<String, Map<String,Object>> itemMap = new HashMap<String, Map<String,Object>>(0);
		
		List<Element> itemListElement = element.element(nodeName).elements("item");
		for (Iterator<Element> it=itemListElement.iterator(); it.hasNext();) 
		{
			Element itemElement = (Element) it.next();
			List<Element> subElements = itemElement.elements();
			
			if(subElements == null || subElements.isEmpty())
				continue;
			
			Map<String,Object> entity = new HashMap<String, Object>(0);
			
			for(Iterator<Element> sub_it = subElements.iterator(); sub_it.hasNext();)
			{
				Element subElement = (Element) sub_it.next();
				
				if(subElement.elements("item") != null && subElement.elements("item").isEmpty()==false)
				{
					Object value = handleItemList(itemElement, subElement.getName(), "id");
					entity.put(subElement.getName(), value);
				}
				else
				{
					entity.put(subElement.getName(), StringUtils.isBlank(subElement.getText())?null:subElement.getTextTrim());
//					System.out.println(subElement.getName()+":"+ subElement.getText());
				}
			}
			
			itemMap.put(itemElement.elementText(keyName), entity);
		}
		
		return itemMap;
	}

	public List<Product> getProductList() throws Exception
	{
		if(productMap == null || productMap.isEmpty())
			return null;
		
		List<Product> productList = new ArrayList<Product>(0);
		for(String productid : productMap.keySet())
		{
			Map<String,Object> product_vo = productMap.get(productid);
			Product product = new Product();
			product.setServicetype(Product.SERVICE_TYPE_PRODUCT);
			/**
			 * 基本属性
			 */
			product.setDesc((String)product_vo.get("description"));
			product.setPackedName((String)product_vo.get("name"));
			product.setPrestore(null);
			product.setBssRangeId((String)product_vo.get("id"));
			product.setContract(null);
			product.setPayType((String)product_vo.get("billingType"));
			product.setStatus((String)product_vo.get("status"));//offer 状态
			product.setType((String)product_vo.get("type"));
			product.setSubType((String)product_vo.get("subType"));
			if(product_vo.get("startDate") != null && StringUtils.isEmpty((String)product_vo.get("startDate"))==false)
			product.setStartDate(DateUtils.formatDate((String)product_vo.get("startDate"),"yyyy-MM-dd"));
			if(product_vo.get("endDate") != null && StringUtils.isEmpty((String)product_vo.get("endDate"))==false)
			product.setEndDate(DateUtils.formatDate((String)product_vo.get("endDate"),"yyyy-MM-dd"));
			product.setIsBundle((String)product_vo.get("isBundle"));
			product.setIsDynamicPriceApplicable((String)product_vo.get("isDynamicEligibilityApplicable"));
			product.setIsDynamicEligibilityApplicable((String)product_vo.get("isDynamicEligibilityApplicable"));
	
			/**
			 * 需要通过别的map节点处理出来的数据
			 */
			if(compositePriceMap != null 
					&& product_vo.get("compositePriceId") != null 
					&& compositePriceMap.get((String)product_vo.get("compositePriceId")) != null)
			{
				Map<String,Object> cpMap = compositePriceMap.get((String)product_vo.get("compositePriceId"));
				product.setPrice(Long.valueOf((String)cpMap.get("price")));
			}
			
			List<ProductSpecMapping.ProductSpec> productSpecList = getProductSpecList(product_vo);
			if(productSpecList != null && productSpecList.isEmpty() == false)
			{
				ProductSpecMapping productSpecMapping = new ProductSpecMapping();
				productSpecMapping.setProductSpecs(productSpecList);
				product.setProductSpecList(mapper.writeValueAsString(productSpecMapping));//productOffer 对应的productSpec的json
				System.out.println(product.getProductSpecList());
			}
			
			List<Plan2ProductMapping> planCodes = getPlanCodes(product_vo);
			if(planCodes != null && planCodes.isEmpty() == false)
			product.setPlancodes(mapper.writeValueAsString(planCodes));//productOffer对应的planOffer
			
			productList.add(product);
			
		}
		
		return productList;
	}
	
	/**
	 * 设置product下的planoffer信息对应Plan2ProductMapping
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public List<Plan2ProductMapping> getPlanCodes(Map<String,Object> product) throws Exception
	{
		if(product == null || product.get("productOfferingToPlanOfferingMapping") == null)
			return null;
		List<Plan2ProductMapping> planCodes = new ArrayList<Plan2ProductMapping>(0);
		Map<String,Map<String,Object>> p2pMap = (Map<String,Map<String,Object>>)product.get("productOfferingToPlanOfferingMapping");
		for(String productSpecId : p2pMap.keySet())
		{
			Map<String,Object> p2p_vo = p2pMap.get(productSpecId);
			Plan2ProductMapping plan2ProductMapping = new Plan2ProductMapping();
			plan2ProductMapping.setAssociationType((String)p2p_vo.get("associationType"));
			plan2ProductMapping.setPlanOfferingID((String)p2p_vo.get("planOfferingID"));
			if(p2p_vo.get("endDate") != null && StringUtils.isEmpty((String)p2p_vo.get("endDate"))==false)
			plan2ProductMapping.setEndDate(DateUtils.formatDate((String)p2p_vo.get("endDate"), "yyyy-MM-dd"));
			if(p2p_vo.get("startDate") != null && StringUtils.isEmpty((String)p2p_vo.get("startDate"))==false)
			plan2ProductMapping.setStartDate(DateUtils.formatDate((String)p2p_vo.get("startDate"), "yyyy-MM-dd"));
			plan2ProductMapping.setId((String)p2p_vo.get("id"));
			plan2ProductMapping.setMappingType((String)p2p_vo.get("mappingType"));
			plan2ProductMapping.setStatus((String)p2p_vo.get("status"));
			
			planCodes.add(plan2ProductMapping);
		}
			
		
		return planCodes;
	}
	
	/**
	 * 设置product的productSpec
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public List<ProductSpecMapping.ProductSpec> getProductSpecList(Map<String,Object> product) throws Exception
	{
		if(product == null || product.get("productSpecMadeAvailableAsOffering") == null)
			return null;
		List<ProductSpecMapping.ProductSpec> productSpecList = new ArrayList<ProductSpecMapping.ProductSpec>(0);
		
		Map<String,Map<String,Object>> psMap = (Map<String,Map<String,Object>>)product.get("productSpecMadeAvailableAsOffering");
		for(String productSpecMappingId : psMap.keySet())
		{
			Map<String,Object> productSpec_vo = psMap.get(productSpecMappingId);
			String productSpecId= ((String)productSpec_vo.get("productSpecificationId")).trim();
			
			ProductSpecMapping.ProductSpec productSpec = new ProductSpecMapping.ProductSpec();
			productSpec.setId((String)productSpec_vo.get("id"));
			productSpec.setStatus((String)productSpec_vo.get("status"));
			productSpec.setProductSpecificationId(productSpecId);
			productSpec.setMinValue((String)productSpec_vo.get("minValue"));
			productSpec.setMaxValue((String)productSpec_vo.get("maxValue"));
			productSpec.setMappingTtype((String)productSpec_vo.get("type"));
			productSpec.setAssociationType((String)productSpec_vo.get("associationType"));
			productSpec.setAssociationType((String)productSpec_vo.get("associationType"));
			if(componentPriceMap != null 
					&& productSpec_vo.get("componentPriceId") != null 
					&& componentPriceMap.get((String)productSpec_vo.get("componentPriceId")) != null)
			{
				Map<String,Object> cpMap = componentPriceMap.get((String)productSpec_vo.get("componentPriceId"));
				productSpec.setComponentPrice((String)cpMap.get("price"));
			}
			
			if(productSpecMap.get(productSpecId) != null)
			{
				Map<String,Object> productSpec_vo2 = productSpecMap.get(productSpecId);
			
				productSpec.setAccessTechnology((String)productSpec_vo2.get("accessTechnology"));
				productSpec.setBillingType((String)productSpec_vo2.get("billingType"));
				productSpec.setCheckServiceAvailability((String)productSpec_vo2.get("checkServiceAvailability"));
				productSpec.setCompanyCode((String)productSpec_vo2.get("companyCode"));
				productSpec.setDescription((String)productSpec_vo2.get("description"));
				productSpec.setIsActivationRequired((String)productSpec_vo2.get("isActivationRequired"));
				productSpec.setIsCafRequired((String)productSpec_vo2.get("isCafRequired"));
				productSpec.setIsInstallationRequired((String)productSpec_vo2.get("isInstallationRequired"));
				productSpec.setIsOCSRequired((String)productSpec_vo2.get("isOCSRequired"));
				productSpec.setIsProvisioningRequired((String)productSpec_vo2.get("isProvisioningRequired"));
				productSpec.setIsTeleverificationRequired((String)productSpec_vo2.get("isTeleverificationRequired"));
				productSpec.setName((String)productSpec_vo2.get("name"));
				productSpec.setProductFamily((String)productSpec_vo2.get("productFamily"));
				productSpec.setServiceType((String)productSpec_vo2.get("serviceType"));
				if(productSpec_vo2.get("startDate") != null && StringUtils.isEmpty((String)productSpec_vo2.get("startDate"))==false)
				productSpec.setStartDate(DateUtils.formatDate((String)productSpec_vo2.get("startDate"),"yyyy-MM-dd"));
				if(productSpec_vo2.get("endDate") != null && StringUtils.isEmpty((String)productSpec_vo2.get("endDate"))==false)
				productSpec.setEndDate(DateUtils.formatDate((String)productSpec_vo2.get("endDate"),"yyyy-MM-dd"));
				
				if(compositePriceMap != null 
						&& productSpec_vo2.get("compositePriceId") != null 
						&& compositePriceMap.get((String)productSpec_vo2.get("compositePriceId")) != null)
				{
					Map<String,Object> cpMap = compositePriceMap.get((String)productSpec_vo2.get("compositePriceId"));
					productSpec.setComponentPrice((String)cpMap.get("price"));
				}
				
				/**设置地区**/
				List<GeographicLocationMapping.GeographicLocation> geographicLocationList = getGeographicLocation((Map<String,Map<String,Object>>)productSpec_vo2.get("geographicLocationToProductSpecificationMapping"));
				if(geographicLocationList != null && geographicLocationList.isEmpty() == false)
					productSpec.setGeographicLocationMapping(new GeographicLocationMapping(geographicLocationList));
				
				/**  **/
				if(StringUtils.isBlank((String)productSpec_vo2.get("productSpecToCharacteristicMapping")) == false)
				{
					List<ProductSpecMapping.ProductSpecCharacteristic> productSpecCharList = getProductSpecCharList((Map<String,Map<String,Object>>)productSpec_vo2.get("productSpecToCharacteristicMapping"));
					productSpec.setProductSpecCharList(productSpecCharList);
				}
				
				/** 设置serviceSpec **/
				List<ProductSpecMapping.ServiceSpec> serviceSpecList = getServiceSpecList((Map<String,Map<String,Object>>)productSpec_vo2.get("productSpecHasServiceSpec"));
				productSpec.setServiceSpecList(serviceSpecList);
				
				/**设置硬件资源**/
				List<ProductSpecMapping.ResourceSpec> resourceSpecList = getResourceSpecList((Map<String,Map<String,Object>>)productSpec_vo2.get("productSpecHasResourceSpec"));
				productSpec.setResourceSpecList(resourceSpecList);
			}
			
			
			productSpecList.add(productSpec);
		}
		
		return productSpecList;
	}
	
	/**
	 * 设置productSpec的resourceSpecList属性
	 * @param ssMap
	 * @return
	 * @throws Exception
	 */
	public List<ProductSpecMapping.ResourceSpec> getResourceSpecList(Map<String,Map<String,Object>> rsMap) throws Exception
	{
		if(rsMap == null || rsMap.isEmpty())
			return null;

		List<ProductSpecMapping.ResourceSpec> resourceSpecList = new ArrayList<ProductSpecMapping.ResourceSpec>(0);
		
		for(String rsid : rsMap.keySet())
		{
			Map<String,Object> rs_vo = rsMap.get(rsid);
			ProductSpecMapping.ResourceSpec rs = new ProductSpecMapping.ResourceSpec();
			String resourceSpecificationId = (String)rs_vo.get("resourceSpecificationId");
			
			rs.setId((String)rs_vo.get("id"));
			rs.setResourceSpecificationId(resourceSpecificationId);
			if(rs_vo.get("startDate") != null && StringUtils.isEmpty((String)rs_vo.get("startDate"))==false)
			rs.setStartDate(DateUtils.formatDate((String)rs_vo.get("startDate"),"yyyy-MM-dd"));
			if(rs_vo.get("endDate") != null && StringUtils.isEmpty((String)rs_vo.get("endDate"))==false)
			rs.setEndDate(DateUtils.formatDate((String)rs_vo.get("endDate"),"yyyy-MM-dd"));
			rs.setMappingType((String)rs_vo.get("type"));
			rs.setStatus((String)rs_vo.get("status"));
			rs.setAssociationType((String)rs_vo.get("associationType"));
			rs.setDescription((String)rs_vo.get("description"));
			
			if(resourceSpecMap.get(resourceSpecificationId) != null)
			{
				Map<String,Object> rs_vo2 = resourceSpecMap.get(resourceSpecificationId);
				
				rs.setCompanyCode((String)rs_vo2.get("companyCode"));
				rs.setComponentPrice((String)rs_vo2.get("componentPrice"));
				rs.setDefaultValue((String)rs_vo2.get("defaultValue"));
				rs.setFixedMobile((String)rs_vo2.get("fixedMobile"));
				rs.setIsInstallationRequired((String)rs_vo2.get("isInstallationRequired"));
				rs.setIsManageable((String)rs_vo2.get("isManageable"));
				rs.setMaxValue((String)rs_vo2.get("maxValue"));
				rs.setMinValue((String)rs_vo2.get("minValue"));
				rs.setName((String)rs_vo2.get("name"));
				rs.setType((String)rs_vo2.get("type"));
				
				if(rs_vo2.get("geographicLocations") != null)
				{
					Map<String,Map<String,Object>> rs_glMap = (Map<String,Map<String,Object>>)rs_vo2.get("geographicLocations");
					List<GeographicLocationMapping.GeographicLocation> geographicLocationList = getGeographicLocation(rs_glMap);
					if(geographicLocationList != null && geographicLocationList.isEmpty() == false)
					rs.setGeographicLocationMapping(new GeographicLocationMapping(geographicLocationList));
				}
				
				if(componentPriceMap != null 
						&& rs_vo2.get("resourceSpecificationToComponentPriceMapping") != null 
						&& compositePriceMap.get((String)rs_vo2.get("resourceSpecificationToComponentPriceMapping")) != null)
				{
					Map<String,Object> cpMap = componentPriceMap.get((String)rs_vo2.get("resourceSpecificationToComponentPriceMapping"));
					rs.setComponentPrice((String)cpMap.get("price"));
				}
				
			}
			resourceSpecList.add(rs);
		}
		
		return resourceSpecList;
		
	}
	
	/**
	 * 
	 * @param pscMap
	 * @return
	 * @throws Exception
	 */
	public List<ProductSpecMapping.ProductSpecCharacteristic> getProductSpecCharList(Map<String,Map<String,Object>> pscMap) throws Exception
	{
		if(pscMap == null || pscMap.isEmpty())
			return null;

		List<ProductSpecMapping.ProductSpecCharacteristic> productSpecCharList = new ArrayList<ProductSpecMapping.ProductSpecCharacteristic>(0);
		
		for(String pscid : pscMap.keySet())
		{
			Map<String,Object> psc_vo = pscMap.get(pscid);
			ProductSpecMapping.ProductSpecCharacteristic psc = new ProductSpecMapping.ProductSpecCharacteristic();
			String serviceSpecificationId = (String)psc_vo.get("serviceSpecificationId");
			
			psc.setServiceSpecificationId(serviceSpecificationId);
			psc.setId((String)psc_vo.get("id"));
			if(psc_vo.get("startDate") != null && StringUtils.isEmpty((String)psc_vo.get("startDate"))==false)
			psc.setStartDate(DateUtils.formatDate((String)psc_vo.get("startDate"),"yyyy-MM-dd"));
			if(psc_vo.get("endDate") != null && StringUtils.isEmpty((String)psc_vo.get("endDate"))==false)
			psc.setEndDate(DateUtils.formatDate((String)psc_vo.get("endDate"),"yyyy-MM-dd"));
			psc.setStatus((String)psc_vo.get("status"));
			psc.setType((String)psc_vo.get("type"));
			psc.setAssociationType((String)psc_vo.get("associationType"));
			productSpecCharList.add(psc);
		}
		
		return productSpecCharList;
		
	}
	
	/**
	 * 设置productSpec的serviceSpecList属性
	 * @param ssMap
	 * @return
	 * @throws Exception
	 */
	public List<ProductSpecMapping.ServiceSpec> getServiceSpecList(Map<String,Map<String,Object>> ssMap) throws Exception
	{
		if(ssMap == null || ssMap.isEmpty())
			return null;

		List<ProductSpecMapping.ServiceSpec> serviceSpecList = new ArrayList<ProductSpecMapping.ServiceSpec>(0);
		
		for(String ssid : ssMap.keySet())
		{
			Map<String,Object> ss_vo = ssMap.get(ssid);
			ProductSpecMapping.ServiceSpec ss = new ProductSpecMapping.ServiceSpec();
			String serviceSpecificationId = (String)ss_vo.get("serviceSpecificationId");
			
			ss.setId((String)ss_vo.get("id"));
			ss.setServiceSpecificationId(serviceSpecificationId);
			if(ss_vo.get("startDate") != null && StringUtils.isEmpty((String)ss_vo.get("startDate"))==false)
			ss.setStartDate(DateUtils.formatDate((String)ss_vo.get("startDate"),"yyyy-MM-dd"));
			if(ss_vo.get("endDate") != null && StringUtils.isEmpty((String)ss_vo.get("endDate"))==false)
			ss.setEndDate(DateUtils.formatDate((String)ss_vo.get("endDate"),"yyyy-MM-dd"));
			ss.setMappingType((String)ss_vo.get("type"));
			ss.setStatus((String)ss_vo.get("status"));
			ss.setAssociationType((String)ss_vo.get("associationType"));
			ss.setDescription((String)ss_vo.get("description"));
			
			if(serviceSpecMap.get(serviceSpecificationId) != null)
			{
				Map<String,Object> ss_vo2 = serviceSpecMap.get(serviceSpecificationId);
				
				ss.setIsActivationRequired((String)ss_vo2.get("isActivationRequired"));
				ss.setIsAutoRenewable((String)ss_vo2.get("isAutoRenewable"));
				ss.setIsProvisioningRequired((String)ss_vo2.get("isProvisioningRequired"));
				ss.setType((String)ss_vo2.get("type"));
				ss.setName((String)ss_vo2.get("name"));
				ss.setServiceType((String)ss_vo2.get("serviceType"));
				ss.setSubType((String)ss_vo2.get("subType"));
				
				if(ss_vo2.get("geographicLocations") != null)
				{
					Map<String,Map<String,Object>> ss_glMap = (Map<String,Map<String,Object>>)ss_vo2.get("geographicLocations");
					ss.setGeographicLocations(getGeographicLocation(ss_glMap));
				}
			}
			
			serviceSpecList.add(ss);
		}
		
		return serviceSpecList;
		
	}
	
	/**
	 * 设置地理信息
	 * @param glMap
	 * @return
	 * @throws Exception
	 */
	public List<GeographicLocationMapping.GeographicLocation> getGeographicLocation(Map<String,Map<String,Object>> glMap) throws Exception
	{
		if(glMap == null || glMap.isEmpty())
			return null;
		
		List<GeographicLocationMapping.GeographicLocation> geographicLocationList = new ArrayList<GeographicLocationMapping.GeographicLocation>(0);
		
		for(String gid : glMap.keySet())
		{
			Map<String,Object> gl_vo = glMap.get(gid);
			GeographicLocationMapping.GeographicLocation gl = new GeographicLocationMapping.GeographicLocation();
			String geographicLocationMasterId = (String)gl_vo.get("geographicLocationMasterId");
			gl.setGeographicLocationMasterId(geographicLocationMasterId);
			gl.setId((String)gl_vo.get("id"));
			if(gl_vo.get("startDate") != null && StringUtils.isEmpty((String)gl_vo.get("startDate"))==false)
			gl.setStartDate(DateUtils.formatDate((String)gl_vo.get("startDate"),"yyyy-MM-dd"));
			if(gl_vo.get("endDate") != null && StringUtils.isEmpty((String)gl_vo.get("endDate"))==false)
			gl.setEndDate(DateUtils.formatDate((String)gl_vo.get("endDate"),"yyyy-MM-dd"));
			gl.setStatus((String)gl_vo.get("status"));
			gl.setType((String)gl_vo.get("type"));
			
			if(geographicLocationMap.get(geographicLocationMasterId) != null)
			{
				Map<String,Object> geographicLocation_vo = geographicLocationMap.get(geographicLocationMasterId);
				gl.setLocationDescription((String)geographicLocation_vo.get("locationDescription"));
				gl.setLocationName((String)geographicLocation_vo.get("locationName"));
				gl.setLocationType((String)geographicLocation_vo.get("locationType"));
			}
			
			geographicLocationList.add(gl);
			
		}
		
		return geographicLocationList;
	}
	
	
	public List<Product> getPlanList() throws Exception
	{
		if(planMap == null || planMap.isEmpty())
			return null;
		
		List<Product> planList = new ArrayList<Product>(0);
		for(String planId : planMap.keySet())
		{
			Map<String,Object> plan_vo = planMap.get(planId);
			Product product = new Product();
			product.setServicetype(Product.SERVICE_TYPE_PLAN);
			/**
			 * 基本属性
			 */
			
			product.setDesc((String)plan_vo.get("description"));
			product.setPackedName((String)plan_vo.get("name"));
			product.setPrestore(null);
			product.setBssRangeId((String)plan_vo.get("id"));
			product.setContract(null);
			product.setPayType((String)plan_vo.get("billingType"));
			product.setStatus((String)plan_vo.get("status"));//offer 状态
			product.setType((String)plan_vo.get("type"));
			product.setSubType((String)plan_vo.get("subType"));
			if(plan_vo.get("startDate") != null && StringUtils.isEmpty((String)plan_vo.get("startDate"))==false)
			product.setStartDate(DateUtils.formatDate((String)plan_vo.get("startDate"),"yyyy-MM-dd"));
			if(plan_vo.get("endDate") != null && StringUtils.isEmpty((String)plan_vo.get("endDate"))==false)
			product.setEndDate(DateUtils.formatDate((String)plan_vo.get("endDate"),"yyyy-MM-dd"));
			product.setIsBundle((String)plan_vo.get("isBundle"));
			product.setIsDynamicPriceApplicable((String)plan_vo.get("isDynamicEligibilityApplicable"));
			product.setIsDynamicEligibilityApplicable((String)plan_vo.get("isDynamicEligibilityApplicable"));
			product.setIsAutoRenewable((String)plan_vo.get("isAutoRenewable"));
	
			/**
			 * 需要通过别的map节点处理出来的数据
			 */
			if(compositePriceMap != null 
					&& plan_vo.get("compositePriceId") != null 
					&& compositePriceMap.get((String)plan_vo.get("compositePriceId")) != null)
			{
				Map<String,Object> cpMap = compositePriceMap.get((String)plan_vo.get("compositePriceId"));
				product.setPrice(Long.valueOf((String)cpMap.get("price")));
			}
			
			List<PlanSpecMapping.PlanSpec> planSpecList = getPlanSpecList(plan_vo);
			if(planSpecList != null && planSpecList.isEmpty() == false)
			{
				PlanSpecMapping planSpecMapping = new PlanSpecMapping();
				planSpecMapping.setPlanSpecs(planSpecList);
				product.setPlanSpecList(mapper.writeValueAsString(planSpecMapping));//productOffer 对应的productSpec的json
			}
			
			/**设置地区**/
			List<GeographicLocationMapping.GeographicLocation> geographicLocationList = getGeographicLocation((Map<String,Map<String,Object>>)plan_vo.get("geographicLocationToPlanOfferingMapping"));
			if(geographicLocationList != null && geographicLocationList.isEmpty() == false){
				product.setGeographicLocationList(mapper.writeValueAsString(new GeographicLocationMapping(geographicLocationList)));
			}
			
			planList.add(product);
		}
		
		return planList;
	}
	
	/**
	 * 设置planSpec
	 * @param plan
	 * @return
	 * @throws Exception
	 */
	public List<PlanSpecMapping.PlanSpec> getPlanSpecList(Map<String,Object> plan) throws Exception
	{
		if(plan == null || plan.get("planOfferingToPlanSpecMapping") == null)
			return null;
		List<PlanSpecMapping.PlanSpec> planSpecList = new ArrayList<PlanSpecMapping.PlanSpec>(0);
		
		Map<String,Map<String,Object>> plMap = (Map<String,Map<String,Object>>)plan.get("planOfferingToPlanSpecMapping");
		for(String planSpecId : plMap.keySet())
		{
			Map<String,Object> planSpec_vo = plMap.get(planSpecId);
			
			PlanSpecMapping.PlanSpec planSpec = new PlanSpecMapping.PlanSpec();
			String planSpecificationid = (String)planSpec_vo.get("planSpecificationid");
			planSpec.setId((String)planSpec_vo.get("id"));
			planSpec.setPlanSpecificationid(planSpecificationid);
			planSpec.setIsPrimary((String)planSpec_vo.get("isPrimary"));
			planSpec.setAssociationType((String)planSpec_vo.get("associationType"));
			planSpec.setMappingType((String)planSpec_vo.get("mappingType"));
			if(planSpec_vo.get("startDate") != null && StringUtils.isEmpty((String)planSpec_vo.get("startDate"))==false)
			planSpec.setStartDate(DateUtils.formatDate((String)planSpec_vo.get("startDate"),"yyyy-MM-dd"));
			if(planSpec_vo.get("endDate") != null && StringUtils.isEmpty((String)planSpec_vo.get("endDate"))==false)
			planSpec.setEndDate(DateUtils.formatDate((String)planSpec_vo.get("endDate"),"yyyy-MM-dd"));
			planSpec.setStatus((String)planSpec_vo.get("status"));
			/**
			 * 需要通过别的map节点处理出来的数据
			 */
			if(compositePriceMap != null 
					&& planSpec_vo.get("compositePriceId") != null 
					&& compositePriceMap.get((String)planSpec_vo.get("compositePriceId")) != null)
			{
				Map<String,Object> cpMap = compositePriceMap.get((String)planSpec_vo.get("compositePriceId"));
				planSpec.setComponentPrice((String)cpMap.get("price"));
			}
			
			if(planSepcMap != null && planSepcMap.get(planSpecificationid) != null)
			{
				Map<String,Object> planSpec_vo2 = planSepcMap.get(planSpecificationid);
				
				planSpec.setBillingType((String)planSpec_vo2.get("billingType"));
				planSpec.setCompanyCode((String)planSpec_vo2.get("companyCode"));
				planSpec.setDescription((String)planSpec_vo2.get("description"));
				planSpec.setName((String)planSpec_vo2.get("name"));
				planSpec.setServiceType((String)planSpec_vo2.get("serviceType"));
				planSpec.setType((String)planSpec_vo2.get("type"));
				planSpec.setSubType((String)planSpec_vo2.get("subType"));
				planSpec.setPriority((String)planSpec_vo2.get("priority"));
				planSpec.setStackedQueued((String)planSpec_vo2.get("stackedQueued"));
				planSpec.setIsCumulative((String)planSpec_vo2.get("isCumulative"));
				planSpec.setIsProratedOnActivation((String)planSpec_vo2.get("isProratedOnActivation"));
				planSpec.setIsProratedOnDeactivation((String)planSpec_vo2.get("isProratedOnDeactivation"));
				planSpec.setTimeId((String)planSpec_vo2.get("timeId"));
				
				/** 设置planSpecChar信息 **/
				List<PlanSpecMapping.PlanSpecCharacteristic> planSpecCharList = getPlanSpecCharList((Map<String,Map<String,Object>>)planSpec_vo2.get("planSpecToPlanCharacteristicMapping"));
				planSpec.setPlanSpecCharList(planSpecCharList);
				
				/**设置地区**/
				List<GeographicLocationMapping.GeographicLocation> geographicLocationList = getGeographicLocation((Map<String,Map<String,Object>>)planSpec_vo2.get("geographicLocationToPlanSpecificationMapping"));
				if(geographicLocationList != null && geographicLocationList.isEmpty() == false)
					planSpec.setGeographicLocationMapping(new GeographicLocationMapping(geographicLocationList));
		
			}
			
			planSpecList.add(planSpec);
		}
		
		return planSpecList;
	}
	
	/**
	 * 设置planSpec
	 * @param plan
	 * @return
	 * @throws Exception
	 */
	public List<PlanSpecMapping.PlanSpecCharacteristic> getPlanSpecCharList(Map<String,Map<String,Object>> psMap) throws Exception
	{
		if(psMap == null || psMap.isEmpty())
			return null;

		List<PlanSpecMapping.PlanSpecCharacteristic> planSpecCharList = new ArrayList<PlanSpecMapping.PlanSpecCharacteristic>(0);
		
		for(String psid : psMap.keySet())
		{
			Map<String,Object> ps_vo = psMap.get(psid);
			
			PlanSpecMapping.PlanSpecCharacteristic ps = new PlanSpecMapping.PlanSpecCharacteristic();
			ps.setId((String)ps_vo.get("id"));
			ps.setPlanSpecCharacteristicId((String)ps_vo.get("planSpecCharacteristicId"));
			if(ps_vo.get("startDate") != null && StringUtils.isEmpty((String)ps_vo.get("startDate"))==false)
			ps.setStartDate(DateUtils.formatDate((String)ps_vo.get("startDate"),"yyyy-MM-dd"));
			if(ps_vo.get("endDate") != null && StringUtils.isEmpty((String)ps_vo.get("endDate"))==false)
			ps.setEndDate(DateUtils.formatDate((String)ps_vo.get("endDate"),"yyyy-MM-dd"));
			ps.setMappingType((String)ps_vo.get("type"));
			ps.setStatus((String)ps_vo.get("status"));
			ps.setAssociationType((String)ps_vo.get("associationType"));
			ps.setRoutingPatternId((String)ps_vo.get("routingPatternId"));
			ps.setIsAutoRewenable((String)ps_vo.get("isAutoRewenable"));
			ps.setValue((String)ps_vo.get("value"));
			ps.setUnitsOfMeasure((String)ps_vo.get("unitsOfMeasure"));
			ps.setMinValue((String)ps_vo.get("minValue"));
			ps.setMaxValue((String)ps_vo.get("maxValue"));
			ps.setValidityCycle((String)ps_vo.get("validityCycle"));
			ps.setTimeId((String)ps_vo.get("timeId"));
			ps.setRechargeBehaviour((String)ps_vo.get("rechargeBehaviour"));
			
			
			
			if(planSpecCharMap.get((String)ps_vo.get("planSpecCharacteristicId")) != null)
			{
				Map<String,Object> ps_vo2 = planSpecCharMap.get((String)ps_vo.get("planSpecCharacteristicId"));
				
				ps.setDescription((String)ps_vo2.get("description"));
				ps.setDefaultValue((String)ps_vo2.get("defaultValue"));
				ps.setPriority((String)ps_vo2.get("priority"));
				ps.setType((String)ps_vo2.get("valueType"));
				ps.setName((String)ps_vo2.get("name"));
			}
			
			planSpecCharList.add(ps);
		}
		
		return planSpecCharList;
		
	}
	
	public static final void main(String[] args) throws Exception
	{
//		List<PlroductSpecMapping.ProductSpec> list = new ArrayList<PlanSpecMapping.ProductSpec>(0);
//		ProductSpec sp = new ProductSpec();
//		ProductSpec.ProductSpecCharacteristic psc = new ProductSpecCharacteristic();
//		psc.setType("234234234");
//		sp.setProductSpecCharList(new ArrayList<ProductSpec.ProductSpecCharacteristic>(0));
//		sp.getProductSpecCharList().add(psc);
//		sp.getProductSpecCharList().add(psc);
//		sp.getProductSpecCharList().add(psc);
//		
//		list.add(sp);
//		
//		String json = mapper.writeValueAsString(list);
//		
//		System.out.println(json);
//		
//		List<ProductSpec> dest = new ArrayList<ProductSpec>(0);
//		
//		List<ProductSpec> list2 = mapper.readValue(json, new TypeReference<List<ProductSpec>>(){});
//		
////		for(Object o : list2)
////		{
////			System.out.println(mapper.writeValueAsString(o));
////			dest.add(mapper.readValue(mapper.writeValueAsString(o), ProductSpec.class));
////		}
//
//		
//		
//		System.out.println(list2.get(0).getType());
		
	}

}
