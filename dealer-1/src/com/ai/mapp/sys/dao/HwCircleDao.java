package com.ai.mapp.sys.dao;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.HwCircle;


@Repository("hwCircleDao")
public class HwCircleDao extends HibernateDao<HwCircle, Long> {

	@Override
	public Criteria createCriteria(Criteria c, HwCircle t) throws Exception {
		
		if( t == null) return c;
	
		if(StringUtils.isNotEmpty(t.getCircle_code())){
			c.add(Restrictions.eq("circle_code", t.getCircle_code()));
		}
		if(!StringUtils.isEmpty(t.getCircle_name())){
			c.add(Restrictions.eq("circle_name", t.getCircle_name()));
		}
		
		return c;
	}

}
