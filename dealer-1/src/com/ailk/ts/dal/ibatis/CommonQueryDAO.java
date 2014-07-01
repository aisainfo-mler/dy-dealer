package com.ailk.ts.dal.ibatis;

import java.util.List;


public interface CommonQueryDAO {

	public List getModels(Object obj,String sqlKey);
	public List getNewUserReportList(Object obj, String sqlKey);
	
	public Object findByPkey(java.lang.String serialno, String sqlKey);
	
	public void addModel(Object object, String sqlKey);
	public void modModel(Object object, String sqlKey);
	public void delModel(java.lang.String serialno, String sqlKey);

  
	public Object findModel(Object object, String sqlKey);
	public Object getRealName(Object obj, String sqlKey);
	
	public void update(Object obj, String sqlKey);
}
