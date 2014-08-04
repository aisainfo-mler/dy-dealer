package com.ai.mapp.sys.dao;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.HwDistrict;



@Repository("hwDistrictDao")
public class HwDistrictDao extends HibernateDao<HwDistrict, Long> {

	@Override
	public Criteria createCriteria(Criteria c, HwDistrict t) throws Exception {
		
		if( t == null) return c;
	
		if(StringUtils.isNotEmpty(t.getDistrictName())){
			c.add(Restrictions.eq("districtName", t.getDistrictName()));
		}
		if(!StringUtils.isEmpty(t.getStateCode())){
			c.add(Restrictions.eq("stateCode", t.getStateCode()));
		}
		if(!StringUtils.isEmpty(t.getDistrictGisCode())){
			c.add(Restrictions.eq("districtGisCode", t.getDistrictGisCode()));
		}
		
		c.addOrder(Order.asc("districtName"));
		c.addOrder(Order.asc("districtGisCode"));
		return c;
	}

}
