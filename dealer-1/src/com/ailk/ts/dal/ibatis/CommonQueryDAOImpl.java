package com.ailk.ts.dal.ibatis;

import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class CommonQueryDAOImpl extends SqlMapClientDaoSupport implements CommonQueryDAO {

	public List getModels(Object obj, String sqlKey) {
		return getSqlMapClientTemplate().queryForList(sqlKey, obj);
	}
	

	public void addModel(Object object, String sqlKey) {
		getSqlMapClientTemplate().insert(sqlKey, object);
		
	}



	public void delModel(String serialno, String sqlKey) {
		getSqlMapClientTemplate().delete(sqlKey, serialno);
		
	}

	public Object findByPkey(String serialno, String sqlKey) {
		return getSqlMapClientTemplate().queryForObject(sqlKey, serialno);
	}

	public void modModel(Object object, String sqlKey) {
		getSqlMapClientTemplate().update(sqlKey, object);
		
	}

	public Object findModel(Object object, String sqlKey) {
		return getSqlMapClientTemplate().queryForObject(sqlKey, object);
	}

	public Object getRealName(Object obj, String sqlKey) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForObject(sqlKey, obj);
	}


	@Override
	public List getNewUserReportList(Object obj, String sqlKey) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void update(Object obj, String sqlKey) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update(sqlKey, obj);
	}

	



}
