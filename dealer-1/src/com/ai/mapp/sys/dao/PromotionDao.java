package com.ai.mapp.sys.dao;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.sys.entity.Promotion;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-8 下午02:25:18
 * 类说明:促销 广告
 */

@Repository("promotionDao")
public class PromotionDao extends HibernateDao<Promotion, Long> {

	@Override
	public Criteria createCriteria(Criteria c, Promotion t) throws Exception {
		if( t == null) return c;
		
		if(StringUtils.isNotEmpty(t.getStatus()))
		{
			c.add(Restrictions.eq("status", t.getStatus()));
		}
		
		if(t.getEffDate() != null)
		{
			c.add(Restrictions.ge("effDate", DateUtils.getDate(DateUtils.getDateString(t.getEffDate(), "yyyy-MM-dd")+" 00:00:00","yyyy-MM-dd HH:mm:ss")));
		}
		
		if(t.getExpDate() != null)
		{
			c.add(Restrictions.le("expDate", DateUtils.getDate(DateUtils.getDateString(t.getExpDate(), "yyyy-MM-dd")+" 23:59:59","yyyy-MM-dd HH:mm:ss")));
		}
		
		c.addOrder(Order.asc("fileIndex"));
		
		return c;
	}

}
