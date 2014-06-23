package com.ai.mapp.sys.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.VerifySms;

@Repository("verifySmsDao")
public class VerifySmsDao extends HibernateDao<VerifySms, Integer> {

	@Override
	public Criteria createCriteria(Criteria c, VerifySms t) throws Exception {
		if(t.getId()!=null && t.getId()>0){
			c.add(Restrictions.eq("id", t.getId()));
		}
		c.add(Restrictions.le("validtime", new Date()));
		c.add(Restrictions.ge("invalidtime", new Date()));
		return c;
	}

}
