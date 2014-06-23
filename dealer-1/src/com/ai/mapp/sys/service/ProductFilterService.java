package com.ai.mapp.sys.service;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.error.RollbackException;
import com.ai.mapp.sys.dao.ProductFilterDao;
import com.ai.mapp.sys.entity.ProductFilter;

@Service
@Transactional(rollbackFor=Exception.class)
public class ProductFilterService {
	
	public final Log log = LogFactory.getLog(ProductFilterService.class);
	
	@Autowired
	private ProductFilterDao productFilterDao;
	
	public Collection<ProductFilter> listProductFilters(ProductFilter productFilter,int start,int limit){
		try{
			log.debug(productFilter==null?"productFilter is null":productFilter.toString());
			
			Collection<ProductFilter> c = productFilterDao.list(productFilter, start, limit);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection<ProductFilter> listAllProductFilters(ProductFilter productFilter){
		try{
			log.debug(productFilter==null?"productFilter is null":productFilter.toString());
			
			Collection<ProductFilter> c = productFilterDao.listAll(productFilter);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ProductFilter loadProductFilterById(Long productFilterId)throws Exception{
		return productFilterDao.get(productFilterId);
	}
	
	public void saveProductFilter(ProductFilter productFilter){
		try{
			log.debug(productFilter==null?"productFilter is null":productFilter.toString());
			productFilterDao.save(productFilter);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteProductFilter(ProductFilter productFilter){
		try{
			log.debug(productFilter==null?"productFilter is null":productFilter.toString());
			productFilterDao.delete(productFilter);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int countProductFilter(ProductFilter productFilter) throws RollbackException {
		try{
			return productFilterDao.count(productFilter);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RollbackException(e);
		}
	}
	
	public ProductFilter loadProductFilter(Long id)
	{
		return productFilterDao.get(id);
	}
	
}
