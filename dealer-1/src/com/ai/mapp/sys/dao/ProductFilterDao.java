package com.ai.mapp.sys.dao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.ProductFilter;

@Repository("productFilterDao")
public class ProductFilterDao extends HibernateDao<ProductFilter, Long> {

	@Override
	public Criteria createCriteria(Criteria c, ProductFilter t) {
		
		if( t == null) return c;
	
		
//		
		
		return c;
	}
	
}
