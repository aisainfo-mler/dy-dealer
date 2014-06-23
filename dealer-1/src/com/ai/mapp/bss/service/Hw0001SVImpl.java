package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.Contract;
import com.ai.mapp.sys.entity.Mobile;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.service.ProductService;

@Service("hw0001Service")
@Scope("singleton")
public class Hw0001SVImpl extends ISVTemplate {
	
	@Autowired
	private ProductService productService;
	
	protected Object convertResponse(ParamObject param) throws Exception 
	{
		com.ai.mapp.model.HW0001.Response rsp = new com.ai.mapp.model.HW0001.Response();
		Collection<Product> products = (Collection<Product>)param.getResult();
		
		if(products != null && products.isEmpty() == false)
		{
			/** 
			 * Map结构：Map<terms,Map<contractType,List<product>>> 
			 * terms:期数，contractType：套餐类型，product：合约对象
			 */
			Map<String,Map<String,List<Product>>> products_map = new LinkedHashMap<String, Map<String,List<Product>>>(0);
			
			for(Product p : products)
			{
				/**
				 * 判断是否有合约，无合约则跳过
				 */
				if(p.getContract() == null)
					continue;
				System.out.println(p.getRangeId()+":"+p.getContract().getContractId());
				
				if(StringUtil.isEmpty(p.getPeriod()))
				{
					p.setTerms("0");
					p.setPeriod("0");
				}
				
				if(products_map.get(p.getPeriod()) == null)
					products_map.put(p.getPeriod(),new LinkedHashMap<String, List<Product>>(0));
				
				if(products_map.get(p.getPeriod()).get(p.getContract().getContractType()) == null)
					products_map.get(p.getPeriod()).put(p.getContract().getContractType(), new ArrayList<Product>(0));
				
				products_map.get(p.getPeriod()).get(p.getContract().getContractType()).add(p);
				
			}
		
			com.ai.mapp.model.HW0001.Terms terms = new com.ai.mapp.model.HW0001.Terms();
			
			for(String termValue : products_map.keySet())
			{
				/** 获取某个term下对应的Map<contractType，List<Product>> **/
				Map<String,List<Product>> c_product_map = products_map.get(termValue);
				
				if(c_product_map == null || c_product_map.isEmpty())
					continue;
				
				com.ai.mapp.model.HW0001.Term term = new com.ai.mapp.model.HW0001.Term();
				term.setTermValue(termValue);
				terms.addTerm(term);
				
				
				for(String contractType : c_product_map.keySet())
				{
					Collection<Product> plist = c_product_map.get(contractType);
					
					if(plist == null || plist.isEmpty())
						continue;
					com.ai.mapp.model.HW0001.ProductList pl = new com.ai.mapp.model.HW0001.ProductList();
					pl.setContractType(contractType);
					
					for(Product p : plist)
					{
						com.ai.mapp.model.HW0001.Product product = new com.ai.mapp.model.HW0001.Product();
						
						Contract c = p.getContract();
						
						product.setActType(p.getActTypeDes() == null ? "":p.getActTypeDes()	);
						product.setAllFee(p.getAllFee() == null?"":String.valueOf(p.getAllFee()*1000));
						product.setBackMonth(p.getThawMon() == null?"":String.valueOf(p.getThawMon()*1000));
						product.setContractId(p.getContract() == null?"":p.getContract().getContractId().toString());
						product.setPhoneId(p.getMobile() == null?"":p.getMobile().getMobileId().toString());
						product.setPreStore(p.getPrestore() == null?"":String.valueOf(p.getPrestore()*1000));
						product.setPreStoreDesc(p.getStoredDesc() == null?"":p.getStoredDesc().toString());
						product.setPrice(c == null || c.getContractFee() == null?"":String.valueOf(c.getContractFee()*1000));
						product.setProductDesc(c == null || c.getName() == null?"":c.getName());
						product.setProductFee(p.getPrice() == null?"":String.valueOf(p.getPrice()*1000));
						product.setProductId(p.getRangeId() == null?"":p.getRangeId().toString());
						product.setRecommend(StringUtil.isEmpty(p.getRecommend())?"":p.getRecommend().toString());
						product.setResFee(p.getResourcesFee() == null?"":String.valueOf(p.getResourcesFee()*1000));
						
						pl.addProduct(product);
					}
					
					term.addProductList(pl);
					
				}
				
			}
			
			rsp.setTerms(terms);
			
		}
	
		
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);
		
		return rsp.toXMLString();
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		String requestContent = (String) param.getParameter(BSSConstantParam.REQUESTCONTENT);
		com.ai.mapp.model.HW0001.Request request = com.ai.mapp.model.HW0001.Request.unmarshal(new StringReader(requestContent));

		Product condition = new Product();
		
		if(StringUtil.isEmpty(request.getPhoneId()) ==  false)
			condition.setMobile(new Mobile(Long.valueOf(request.getPhoneId())));
		if(StringUtil.isEmpty(request.getProductType()) ==  false)
			condition.setActType(request.getProductType());
		if(StringUtil.isEmpty(request.getRecommend()) ==  false)
			condition.setRecommend(request.getRecommend());
		if(StringUtil.isEmpty(request.getProductId()) ==  false)
			condition.setRangeId(Long.valueOf(request.getProductId()));
		
		/** 与付费是不存在卡的,0 预付费，1,带终端后付费 ，2，后付费 sim，3 data-sim 都是不带终端的 9是bolt-on套餐**/
		if ("0".equals(request.getPayType()))
		{
			condition.setPayType("0");
			condition.setSpecialSearch(Product.SPECIALSEARCH_NOT_HAS_MOBILE);
		}
		else if("1".equals(request.getPayType()))
		{
			condition.setPayType("1");
			condition.setSpecialSearch(Product.SPECIALSEARCH_HAS_MOBILE);
		}
		else if("2".equals(request.getPayType()))
		{
			condition.setPayType("2");
			condition.setSpecialSearch(Product.SPECIALSEARCH_NOT_HAS_MOBILE);
		}
		else if("3".equals(request.getPayType()))
		{
			condition.setPayType("3");
			condition.setSpecialSearch(Product.SPECIALSEARCH_NOT_HAS_MOBILE);
		}
		else if("9".equals(request.getPayType()))
		{
			condition.setPayType("9");
		}
		
		Collection<Product> ranges = productService.listAllProducts(condition);
		
//		for(Product tmp : ranges)
//		{
//			/**
//			 * 判断是否有合约，无合约则跳过
//			 */
//			if(tmp == null || tmp.getContract() == null)
//				continue;
//			System.out.println(tmp.getRangeId()+":"+tmp.getContract().getContractType()+" - "+tmp.getContract().getContractId());
//			
//			
//		}
		
		param.setIfSuccess(true);
		param.setResult(ranges);
		return param;
	}
	
	@Override
	/**
	 * 错误处理
	 */
	public Object error(ParamObject param) throws Exception {
		return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR,param.getErrorInfo());
	}
	
}
