package com.ai.mapp.sys.dao;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.HwCity;
import com.ai.mapp.sys.entity.HwCountry;


@Repository("hwCountryDao")
public class HwCountryDao extends HibernateDao<HwCountry, Long> {
	
	@Override
	public Criteria createCriteria(Criteria c, HwCountry t) {
		
		if( t == null) return c;
	
		if(StringUtils.isNotEmpty(t.getCountryCode())){
			c.add(Restrictions.eq("country_code", t.getCountryCode()));
		}
		if(!StringUtils.isEmpty(t.getCountryName())){
			c.add(Restrictions.eq("county_name", t.getCountryName()));
		}
		if(!StringUtils.isEmpty(t.getNationalltyName())){
			c.add(Restrictions.eq("nationalltyName", t.getNationalltyName()));
		}
		
		return c;
	}
}
