package com.ai.mapp.sys.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.error.RollbackException;
import com.ai.mapp.sys.dao.ProductDao;
import com.ai.mapp.sys.entity.Contract;
import com.ai.mapp.sys.entity.Mobile;
import com.ai.mapp.sys.entity.PlanSpecMapping;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.entity.ProductFilter;
import com.ai.mapp.sys.entity.ProductSpecMapping;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.mapp.core.ErrorCodeDefine;

@Service
@Transactional(rollbackFor=Exception.class)
public class ProductService {
	
	public final Log log = LogFactory.getLog(ProductService.class);
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductFilterService productFilterService;
	
	public Collection<Product> listProducts(Product product,int start,int limit){
		try{
			log.debug(product==null?"product is null":product.toString());
			
			Collection<Product> c = productDao.list(product, start, limit);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection<Product> listAllProducts(Product product){
		try{
			log.debug(product==null?"product is null":product.toString());
			
			Collection<Product> c = productDao.listAll(product);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Product loadProductById(Long productId)throws Exception{
		return productDao.get(productId);
	}
	
	public Product loadProductByBSSId(String bssProductId)throws Exception{
		Product condition = new Product();
		condition.setBssRangeId(bssProductId);
		Collection<Product> pl = listAllProducts(condition);
		if(pl != null && pl.isEmpty() == false){
			return pl.iterator().next();
		}else{
			return null;
		}
		
	}
	
	public void saveProduct(Product product){
		try{
			log.debug(product==null?"product is null":product.toString());
			productDao.save(product);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 算费接口，通过一个product对象计算PlanSpec和ResourceSpec
	 * @param p
	 * @return
	 * @throws Exception
	 */
	public BigDecimal calProductFee(Product p) throws Exception
	{
		BigDecimal pFee = BigDecimal.ZERO;

		/** 设置planSpec **/
		PlanSpecMapping planSpecMapping = DealerDataService.mapper.readValue(p.getPlanSpecList(),PlanSpecMapping.class);
		if(planSpecMapping != null && planSpecMapping.getPlanSpecs() != null && planSpecMapping.getPlanSpecs().isEmpty())
		{
			for(PlanSpecMapping.PlanSpec planSpec : planSpecMapping.getPlanSpecs())
			{
				if(StringUtils.isBlank(planSpec.getComponentPrice()) == false)
					pFee = pFee.add(new BigDecimal(planSpec.getComponentPrice()));
			}
		}
		
		/** 设置resourceSpec **/
		ProductSpecMapping productSpecMapping = DealerDataService.mapper.readValue(p.getProductSpecList(),ProductSpecMapping.class);
		if(productSpecMapping != null && productSpecMapping.getProductSpecs() != null && productSpecMapping.getProductSpecs().isEmpty())
		{
			for(ProductSpecMapping.ProductSpec productSpec : productSpecMapping.getProductSpecs())
			{
				if(productSpec.getResourceSpecList() == null || productSpec.getResourceSpecList().isEmpty() == false)
					continue;
				
				for(ProductSpecMapping.ResourceSpec resourceSpec : productSpec.getResourceSpecList())
				{
					if(StringUtils.isBlank(resourceSpec.getComponentPrice()) == false)
						pFee = pFee.add(new BigDecimal(resourceSpec.getComponentPrice()));
				}
			}
		}
	
		return pFee;
	
	}
	
	public void deleteProduct(Product product){
		try{
			log.debug(product==null?"product is null":product.toString());
			productDao.delete(product);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int countProduct(Product product) throws RollbackException {
		try{
			return productDao.count(product);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RollbackException(e);
		}
	}
	
	public Product loadProduct(Long id)
	{
		return productDao.get(id);
	}
	
	public Product getRecommendProductByMobile(Long mobileId)
	{
		if(mobileId == null)
			return null;
		
		Product condition = new Product();
		condition.setMobile(new Mobile(mobileId));
		
		Collection<Product> plist = listProducts(condition, 0, 1);
		
		if(plist == null || plist.isEmpty())
			return null;
		
		return plist.iterator().next();
	}
	
	public String getProductTypeName(Product product,String language)
	{
		if(product == null || StringUtil.isEmpty(product.getPayType()))
			return "";
		
//		if(SYSConstant.PRODUCT_PAY_TYPE_POSTPAID.equals(product.getPayType()))
//		{
//			if(product.getMobile() == null || product.getMobile().getMobileId() == null)
//				return SYSConstant.PRODUCT_PAY_TYPE_MONTHLY_SIM;
//			else
//				return SYSConstant.PRODUCT_PAY_TYPE_MONTHLY_OFFERS;
//		}
		
		return SYSConstant.payTypes.get(product.getPayType() + language);
		
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
}
