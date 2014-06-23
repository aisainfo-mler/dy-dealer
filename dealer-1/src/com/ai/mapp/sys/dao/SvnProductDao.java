package com.ai.mapp.sys.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.SvnProduct;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-23 上午11:43:09
 * 类说明:
 */

@Repository("svnProductDao")
public class SvnProductDao extends HibernateDao<SvnProduct, Long> {

	@Override
	public Criteria createCriteria(Criteria c, SvnProduct t) throws Exception {
		
		if( t == null) return c;
		
		if(StringUtil.isEmpty(t.getSvn()) == false)
		{
			c.add(Restrictions.eq("svn", t.getSvn()));
		}
		
		if(StringUtil.isEmpty(t.getStatus()) == false)
		{
			c.add(Restrictions.eq("status", t.getStatus()));
		}
		
		if(t.getId() != null)
		{
			c.add(Restrictions.eq("id", t.getId()));
		}
		
		if(t.getUser() != null)
		{
			c.createAlias("user", "user");
			
			if(t.getUser().getUserId() != null)
				c.add(Restrictions.eq("user.userId",t.getUser().getUserId()));
		}
	
		c.addOrder(Order.desc("createTime"));
		
		return c;
	}

}
