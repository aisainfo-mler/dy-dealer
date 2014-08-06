package com.ai.mapp.sys.dao;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.HwCity;

@Repository("hwCityDao")
public class HwCityDao extends HibernateDao<HwCity, Long> {
	
	@Override
	public Criteria createCriteria(Criteria c, HwCity t) {
		c.addOrder(Order.asc("cityName"));
		c.addOrder(Order.asc("cityCode"));
		if( t == null) return c;
	
//		if(StringUtils.isNotEmpty(t.getStateCode())){
//			c.add(Restrictions.eq("stateCode", t.getStateCode()));
//		}
		if(t.getState() != null)
		{
			c.createAlias("state", "state");
			if(!StringUtils.isEmpty(t.getState().getStateCode())){
				c.add(Restrictions.eq("state.stateCode", t.getState().getStateCode()));
			}
		}
		if(StringUtils.isNotEmpty(t.getFlag())){
			c.add(Restrictions.eq("flag", t.getFlag()));
		}
		if(!StringUtils.isEmpty(t.getCityCode())){
			c.add(Restrictions.eq("cityCode", t.getCityCode()));
		}
		if(!StringUtils.isEmpty(t.getCityName())){
			c.add(Restrictions.eq("cityName", t.getCityName()));
		}
		if(!StringUtils.isEmpty(t.getDistrictCode())){
			c.add(Restrictions.eq("districtCode", t.getDistrictCode()));
		}
		
		
		return c;
	}
}
