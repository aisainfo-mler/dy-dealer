package com.ai.mapp.sys.dao;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.AppUpdateLog;

@Repository("appUpdateLogDao")
public class AppUpdateLogDao extends HibernateDao<AppUpdateLog, Long> {

	@Override
	public Criteria createCriteria(Criteria c, AppUpdateLog t) {
		
		if( t == null) return c;
	
		if(StringUtils.isEmpty(t.getItemKey()) ==false)
		{
			c.add(Restrictions.eq("itemKey", t.getItemKey()));
		}
	
		c.addOrder(Order.desc("createDate"));
		
		return c;
	}

	
	
}
