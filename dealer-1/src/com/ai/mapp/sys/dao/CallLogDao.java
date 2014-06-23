package com.ai.mapp.sys.dao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.CallLog;

/**
 * @author mler
 * @version 创建时间：2014-5-14 下午07:03:35
 * 类说明:
 */

@Repository("callLogDao")
public class CallLogDao extends HibernateDao<CallLog, Long> {

	@Override
	public Criteria createCriteria(Criteria c, CallLog t) throws Exception {
		if(t != null){
			
		}
		return c;
	}

}
