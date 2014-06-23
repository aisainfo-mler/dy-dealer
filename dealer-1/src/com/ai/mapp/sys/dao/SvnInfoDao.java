package com.ai.mapp.sys.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.SvnInfo;

@Repository("svnInfoDao")
public class SvnInfoDao extends HibernateDao<SvnInfo, Long> {

	@Override
	public Criteria createCriteria(Criteria c, SvnInfo t) {
		
		if( t == null) return c;
	
		if(t.getSvnId() != null)
		{
			c.add(Restrictions.eq("svnId", t.getSvnId()));
		}
		
		if(StringUtil.isEmpty(t.getSvn()) == false)
		{
			c.add(Restrictions.eq("svn", t.getSvn()));
		}
		
		if(StringUtil.isEmpty(t.getStatus()) == false)
		{
			c.add(Restrictions.eq("status", t.getStatus()));
		}
		
		if(StringUtil.isEmpty(t.getPattern()) == false)
		{
			c.add(Restrictions.like("svn", t.getPattern(),MatchMode.ANYWHERE));
		}
		
		if(StringUtil.isEmpty(t.getSvcLevel()) == false)
		{
			c.add(Restrictions.eq("svcLevel",t.getSvcLevel()));
		}
	
		c.addOrder(Order.desc("svnId"));
		
		return c;
	}
	
}
