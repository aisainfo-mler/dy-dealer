package com.ai.mapp.sys.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.Commission;
import com.ai.mapp.sys.entity.CommonBean;
import com.ai.mapp.sys.entity.OrderDetail;
import com.ai.mapp.sys.entity.User;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-28 下午04:29:18
 * 类说明:
 */

@Repository("commissionDao")
public class CommissionDao  extends HibernateDao<Commission, Long> {

	@Override
	public Criteria createCriteria(Criteria c, Commission t) throws Exception {
		if( t == null) return c;
		
		if(StringUtil.isEmpty(t.getPayStatus()) == false)
		{
//			c.createAlias("detail", "detail");
			c.add(Restrictions.eq("payStatus", t.getPayStatus()));
		}
		
		
		if(t.getAgent() != null)
		{
			c.createAlias("agent", "agent");
			if(t.getAgent().getUserId() != null)
			{
				c.add(Restrictions.eq("agent.userId", t.getAgent().getUserId()));
			}
			
			if(StringUtil.isEmpty(t.getAgent().getUserCode()) == false)
			{
				c.add(Restrictions.eq("agent.userCode", t.getAgent().getUserCode()));
			}
			
		}
		
		if(StringUtil.isEmpty(t.getType()) == false)
		{
			c.add(Restrictions.eq("type", t.getType()));
		}
		
		if(StringUtil.isEmpty(t.getChargeType()) == false)
		{
			c.add(Restrictions.eq("chargeType", t.getChargeType()));
		}
		
		if(t.getStartTime() != null)
		{
			c.add(Restrictions.ge("createTime", t.getStartTime()));
		}
		
		if(t.getEndTime() != null)
		{
			c.add(Restrictions.le("createTime", t.getEndTime()));
		}
		
		c.addOrder(Order.desc("id"));
		
		return c;
	}
	
	public List<CommonBean> countIncome(Commission commission,boolean ifProductType) throws Exception
	{
		Collection<Object> param = new ArrayList<Object>(0);
		
		param.add(commission.getAgent().getUserId());
		
		String hql = null;
		if(ifProductType == false){
			hql = "select obj.chargeType,sum(obj.pay) from Commission obj where obj.agent.userId = ? ";
		}else{
			hql = "select obj.chargeType,sum(obj.pay),obj.agentOrder.product.payType from Commission as obj where obj.agent.userId = ? ";
		}
		
		if(!StringUtil.isEmpty(commission.getType()))
		{
			hql += " and obj.type = ? ";
			param.add(commission.getType());//佣金类型 ：作扣;返佣
		}
		if(!StringUtil.isEmpty(commission.getChargeType()))
		{
			hql += " and obj.chargeType = ? ";
			param.add(commission.getChargeType());//new\top-up\simCard
		}
		
		if(!StringUtil.isEmpty(commission.getPayStatus()))
		{
			hql += " and obj.payStatus = ? ";
			param.add(commission.getPayStatus());
		}
		
		if(commission.getStartTime() != null)
		{
			hql += " and obj.createTime >= ? ";
			param.add(commission.getStartTime());
		}
		
		if(commission.getEndTime() != null)
		{
			hql += " and obj.createTime <= ? ";
			param.add(commission.getEndTime());
		}
		
		hql += "group by obj.chargeType ";
		if(ifProductType){
			hql += ",obj.agentOrder.product.payType ";//预付费、后付费
		}
		
		List<?> result = find(hql, param.toArray());
		
		List<CommonBean> list = null;
		
		if(result == null || result.isEmpty()){
			return null;
		}else{
			list = new ArrayList<CommonBean>();
		}
		CommonBean temp = null;
		Map<String,Long> income_map = new HashMap<String, Long>(0);
		for(Object o : result)
		{
			Object[] items = (Object[])o;
			income_map.put((String)items[0], (Long)items[1]);
			if(items[0] != null){
				temp = new CommonBean();
				temp.setStr1(items[0].toString());
				temp.setStr2(items[1] == null ? "0":items[1].toString());
				if(items.length > 2){
					temp.setStr3(items[2] == null ? "":items[2].toString());
				}
				
				list.add(temp);
			}
			
		}
		
		return list;

	}
}
