package com.ai.mapp.sys.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.AccountInfo;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-19 下午07:03:35
 * 类说明:
 */

@Repository("accountInfoDao")
public class AccountInfoDao extends HibernateDao<AccountInfo, Long> {

	@Override
	public Criteria createCriteria(Criteria c, AccountInfo t) throws Exception {
		if(t != null){
			if(t.getOperator() != null && t.getOperator().getUserId() != null){
				c.createAlias("operator", "operator");
				c.add(Restrictions.eq("operator.userId", t.getOperator().getUserId()));
			}
		}
		return c;
	}

}
