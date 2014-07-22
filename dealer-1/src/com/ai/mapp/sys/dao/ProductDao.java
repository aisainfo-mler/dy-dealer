package com.ai.mapp.sys.dao;

import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.entity.ProductFilter;

@Repository("productDao")
public class ProductDao extends HibernateDao<Product, Long> {

	@Override
	public Criteria createCriteria(Criteria c, Product t) {
		
		if( t == null) return c;
	
		if(t.getRangeId() != null)
		{
			c.add(Restrictions.eq("rangeId", t.getRangeId()));
		}
		
		if(StringUtils.isNotEmpty(t.getBssRangeId()))
		{
			c.add(Restrictions.eq("bssRangeId", t.getBssRangeId()));
		}
		
		if(StringUtils.isNotBlank(t.getPackedName()))
		{
			c.add(Restrictions.like("packedName", t.getPackedName(),MatchMode.ANYWHERE));
		}
		
		if(StringUtil.isEmpty(t.getTerms()) == false)
		{
			c.add(Restrictions.eq("terms", t.getTerms()));
		}
		if(StringUtil.isEmpty(t.getPayType()) == false)
		{
			c.add(Restrictions.eq("payType", t.getPayType()));
		}
		
		if(StringUtil.isEmpty(t.getServicetype()) == false)
		{
			c.add(Restrictions.eq("servicetype", t.getServicetype()));
			
			if(StringUtils.isBlank(t.getType()) == false)
			{
				c.add(Restrictions.eq("type", t.getType()));
			}
		}
		
		if(t.getIds() != null && t.getIds().isEmpty() == false)
		{
			c.add(Restrictions.in("rangeId",t.getIds()));
		}
		
		if(t.getBssReangeIds() != null && t.getBssReangeIds().isEmpty() == false)
		{
			c.add(Restrictions.in("bssRangeId", t.getBssReangeIds()));
		}
		
		if(StringUtils.isBlank(t.getName()) == false)
		{
			c.add(Restrictions.or(Restrictions.like("packedName", t.getName(),MatchMode.ANYWHERE),Restrictions.eq("bssRangeId", t.getName())));
		}
		
		if(t.getFilterCondition() != null)
		{
			int i=0;
			
			for(String filterType : t.getFilterCondition().keySet())
			{
				Set<Object> filterValues = t.getFilterCondition().get(filterType);
				if(filterValues == null || filterValues.isEmpty())
					continue;
				
				for(Object filterValue : filterValues)
				{
					DetachedCriteria pf_dc = DetachedCriteria.forClass(ProductFilter.class,"pf"+i); 
					pf_dc.add(Restrictions.eq("filterType", filterType));
					pf_dc.add(Restrictions.eq("filterValue", filterValue));
					pf_dc.add(Property.forName(c.getAlias()+".rangeId").eqProperty("pf"+i+".product.rangeId")); 
					c.add(Subqueries.exists(pf_dc.setProjection(Projections.property("pf"+i+".product.rangeId")))); 
					i++;
				}
			}
		}
		
		
		return c;
	}
	
}
