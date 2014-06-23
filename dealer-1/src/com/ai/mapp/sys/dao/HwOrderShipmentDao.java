package com.ai.mapp.sys.dao;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.HwOrderShipment;


@Repository("hwOrderShipmentDao")
public class HwOrderShipmentDao extends HibernateDao<HwOrderShipment, Long> {

	@Override
	public Criteria createCriteria(Criteria c, HwOrderShipment t) throws Exception {
		if( t == null) return c;		
		
		if(t.getOrderShipId()!=null){
			c.add(Restrictions.eq("orderShipId", t.getOrderShipId()));
		}
		if(t.getOrderId()!=null){
			c.add(Restrictions.eq("orderId", t.getOrderId()));
		}
		if(StringUtils.isNotEmpty(t.getOrderCode())){
			c.add(Restrictions.eq("orderCode", t.getOrderCode()));
		}
		
		if(t.getPkIdOrderType()==1) c.addOrder(Order.asc("orderShipId"));
		else c.addOrder(Order.desc("orderShipId"));
		
		return c;
	}

}
