package com.ai.mapp.sys.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.entity.SmallLocalFile;

@Repository
public class SmallLocalFileDao extends HibernateDao<SmallLocalFile, Long> {

	@Override
	public Criteria createCriteria(Criteria c, SmallLocalFile f) throws Exception {
		if(f.getSlfid()!=null && f.getSlfid()>-1){
			c.add(Restrictions.eq("slfid", f.getSlfid()));
		}
		return c;
	}

}
