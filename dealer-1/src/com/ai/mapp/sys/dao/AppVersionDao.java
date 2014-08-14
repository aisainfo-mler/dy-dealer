package com.ai.mapp.sys.dao;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.AppVersion;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-8-14 上午12:21:28
 * 类说明:
 */

@Repository("appVersionDao")
public class AppVersionDao extends HibernateDao<AppVersion, Long> {

	@Override
	public Criteria createCriteria(Criteria c, AppVersion t) throws Exception {
		if( t == null) return c;
		
		if(StringUtils.isEmpty(t.getItemKey()) ==false)
		{
			c.add(Restrictions.eq("itemKey", t.getItemKey()));
		}
		
		if(StringUtils.isEmpty(t.getSystem()) ==false)
		{
			c.add(Restrictions.eq("system", t.getSystem()));
		}
	
		return c;
	}

}
