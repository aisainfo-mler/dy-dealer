package com.ai.mapp.sys.dao;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.HwState;

@Repository("hwStateDao")
public class HwStateDao extends HibernateDao<HwState, Long> {
	
	@Override
	public Criteria createCriteria(Criteria c, HwState t) {
		
		if( t == null) return c;
	
		if(StringUtils.isNotEmpty(t.getStateCode())){
			c.add(Restrictions.eq("stateCode", t.getStateCode()));
		}
		if(StringUtils.isNotEmpty(t.getFlag())){
			c.add(Restrictions.eq("flag", t.getFlag()));
		}
		
		c.addOrder(Order.asc("stateCode"));
		return c;
	}

}
