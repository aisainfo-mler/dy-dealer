package com.ai.mapp.sys.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.Contract;

@Repository("contractDao")
public class ContractDao extends HibernateDao<Contract, Long> {

	@Override
	public Criteria createCriteria(Criteria c, Contract t) {
		
		if( t == null) return c;
	
		if(t.getContractId() != null)
		{
			c.add(Restrictions.eq("contractId", t.getContractId()));
		}
	
		c.addOrder(Order.desc("contractId"));
		
		return c;
	}
	
}
