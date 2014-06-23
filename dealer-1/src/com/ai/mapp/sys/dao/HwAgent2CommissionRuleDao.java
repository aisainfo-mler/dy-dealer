package com.ai.mapp.sys.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.HwAgent2CommissionRule;

@Repository("hwAgent2CommissionRuleDao")
public class HwAgent2CommissionRuleDao extends HibernateDao<HwAgent2CommissionRule, Long> {
	
	@Override
	public Criteria createCriteria(Criteria c, HwAgent2CommissionRule t) {
		
		if( t == null) return c;
		
		if(t.getAgentId()!=null){
			c.add(Restrictions.eq("agentId", t.getAgentId()));
		}
		
		c.addOrder(Order.asc("ruleRelaId"));
		return c;
	}

}
