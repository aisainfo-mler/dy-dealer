package com.ai.mapp.sys.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.Menu;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-21 下午08:25:09
 * 类说明:
 */

@Repository("menuDao")
public class MenuDao  extends HibernateDao<Menu, Long> {

	@Override
	public Criteria createCriteria(Criteria c, Menu t) throws Exception {
		if(t == null){
			return c;
		}
		if(t.getParent() != null && t.getParent().getId() != null){
			c.add(Restrictions.eq("parent.id", t.getParent().getId()));
		}
		
		if(!StringUtil.isEmpty(t.getStatus())){
			c.add(Restrictions.eq("status", t.getStatus()));
		}
		
		c.addOrder(Order.asc("index"));
		return c;
	}

}
