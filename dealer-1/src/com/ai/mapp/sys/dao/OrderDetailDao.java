package com.ai.mapp.sys.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.OrderDetail;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-19 下午06:54:47
 * 类说明:
 */

@Repository("orderDetailDao")
public class OrderDetailDao  extends HibernateDao<OrderDetail, Long> {

	@Override
	public Criteria createCriteria(Criteria c, OrderDetail t) throws Exception {
		if( t == null) return c;
		
		if(t.getOrder() != null)
		{
			if(t.getOrder().getId() != null){
				c.createAlias("order", "order");
				c.add(Restrictions.eq("order.id", t.getOrder().getId()));
			}
			
		}
	
//		c.addOrder(Order.desc("userId"));
		
		return c;
	}

}
