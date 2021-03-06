package com.ai.mapp.sys.dao;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.SysProp;
import com.ai.mapp.sys.util.SYSConstant;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-13 下午06:19:43
 * 类说明:
 */

@Repository("sysPropDao")
public class SysPropDao extends HibernateDao<SysProp, Long> {

	@Override
	public Criteria createCriteria(Criteria c, SysProp t) throws Exception {
		if(t == null){
			return c;
		}
		
		if(StringUtils.isNotEmpty(t.getKey()))
		{
			c.add(Restrictions.eq("key", t.getKey()));
		}
		
		if(StringUtils.isNotEmpty(t.getParentKey()))
		{
			c.add(Restrictions.eq("parentKey", t.getParentKey()));
		}
		
		if(StringUtils.isNotEmpty(t.getValid()))
		{
			c.add(Restrictions.eq("valid", t.getValid()));
		}
		if(StringUtils.isNotBlank(t.getPropNameLike())){
			c.add(Restrictions.like("name", t.getPropNameLike() , MatchMode.ANYWHERE));
		}
		
		if(t.getPropertiesKeys() != null && t.getPropertiesKeys().isEmpty() == false)
		{
			c.add(Restrictions.eq("parentKey", SYSConstant.SYSPROP_PROPERTIES_PARENTKEY));
			c.add(Restrictions.in("key", t.getPropertiesKeys()));
		}
		
		return c;
	}

}
