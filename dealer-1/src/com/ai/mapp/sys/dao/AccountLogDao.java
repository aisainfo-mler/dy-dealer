package com.ai.mapp.sys.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.AccountLog;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-19 下午07:05:41
 * 类说明:
 */

@Repository("accountLogDao")
public class AccountLogDao extends HibernateDao<AccountLog, Long> {

	@Override
	public Criteria createCriteria(Criteria c, AccountLog t) throws Exception {
		if(t.getAccount() != null)
		{
			if(t.getAccount().getId() != null){
				c.createAlias("account", "account");
				c.add(Restrictions.eq("account.id", t.getAccount().getId()));
			}
			
		}
		return c;
	}

}
