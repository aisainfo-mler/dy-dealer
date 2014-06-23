package com.ai.mapp.sys.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.GoodsInfo;
import com.ai.mapp.sys.entity.Mobile;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-19 下午07:09:07
 * 类说明:
 */

@Repository("goodsInfoDao")
public class GoodsInfoDao extends HibernateDao<GoodsInfo, Long> {

	@Override
	public Criteria createCriteria(Criteria c, GoodsInfo t) throws Exception {
		
		if(Mobile.SPECIALSEARCH_POSTPAY_HAS_PRODUCT.equals(t.getSpecialSearch()) && StringUtil.isEmpty(t.getUserId()) == false)
		{
			c.add(Restrictions.sqlRestriction(" EXISTS ( SELECT * FROM HW_ORDER_ITEM_INFO k WHERE k.GOOD_ID = {alias}.GOOD_ID and k.OPERATOR_ID = "+t.getUserId()+") "));
		}
		
		if(t.getGoodIds() != null && t.getGoodIds().isEmpty() == false)
		{
			c.add(Restrictions.in("id", t.getGoodIds()));
		}
		
		if(t.getId() != null)
		{
			c.add(Restrictions.eq("id", t.getId()));
		}
		
		if(StringUtil.isEmpty(t.getType()) == false)
		{
			c.add(Restrictions.eq("type", t.getType()));
		}
		
		if(StringUtil.isEmpty(t.getBssId()) == false)
		{
			c.add(Restrictions.eq("bssId", t.getBssId()));
		}
		c.addOrder(Order.asc("id"));
		return c;
	}

}
