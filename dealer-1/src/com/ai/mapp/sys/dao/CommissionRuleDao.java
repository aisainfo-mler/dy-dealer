package com.ai.mapp.sys.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.CommissionRule;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-12-12 下午02:57:26
 * 类说明:
 */
@Repository("commissionRuleDao")
public class CommissionRuleDao extends HibernateDao<CommissionRule, Long> {

	@Override
	public Criteria createCriteria(Criteria c, CommissionRule t)
			throws Exception {
		if( t != null){
			if(t.getRuleId()!=null){
				c.add(Restrictions.eq("ruleId", t.getRuleId()));
			}
			if(!StringUtil.isEmpty(t.getRuleName())){
				c.add(Restrictions.like("ruleName", t.getRuleName(),MatchMode.ANYWHERE));
			}
			if(!StringUtil.isEmpty(t.getBackType())){
				c.add(Restrictions.eq("backType", t.getBackType()));
			}
			if(!StringUtil.isEmpty(t.getPayType())){
				c.add(Restrictions.eq("payType", t.getPayType()));
			}
			if(!StringUtil.isEmpty(t.getValid())){
				c.add(Restrictions.eq("valid", t.getValid()));
			}
			if(t.getQueryType()!=null){
				if(t.getQueryType()==101){//except agent joined rule
					c.add(Restrictions.sqlRestriction("{alias}.ruleid not in (select rule_id from hw_agent2commission_rule b where b.agent_id="+t.getAgentId()+")"));
				}
				if(t.getQueryType()==102){//query agent owen joined rule
					if(t.getAgentId()!=null){
						c.add(Restrictions.sqlRestriction("{alias}.ruleid in (select rule_id from hw_agent2commission_rule c where c.agent_id="+t.getAgentId()+")"));
					}
				}
			}
		}
		
		c.addOrder(Order.desc("ruleId"));
		
		return c;
	}

}
