package com.ai.mapp.sys.service;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.error.RollbackException;
import com.ai.mapp.sys.dao.AppUpdateLogDao;
import com.ai.mapp.sys.dao.AppVersionDao;
import com.ai.mapp.sys.entity.AppUpdateLog;
import com.ai.mapp.sys.entity.AppVersion;

@Service
@Transactional
public class AppVersionService {
public final Log log = LogFactory.getLog(AppVersionService.class);
	
	@Autowired
	private AppVersionDao appVersionDao;
	
	@Autowired
	private AppUpdateLogDao appUpdateLogDao;
	
	public Collection<AppVersion> listAppVersion(AppVersion appVersion,int start,int limit)throws Exception{
		log.debug(appVersion==null?"appVersion is null":appVersion.toString());
		
		Collection<AppVersion> c = appVersionDao.list(appVersion, start, limit,true);
		
		return c;
	}
	
	public void saveAppVersion(AppVersion appVersion)throws Exception{
		log.debug(appVersion==null?"appVersion is null":appVersion.toString());
		appVersionDao.save(appVersion);
	}
	
	public void deleteAppVersion(AppVersion appVersion)throws Exception{
		log.debug(appVersion==null?"appVersion is null":appVersion.toString());
			appVersionDao.delete(appVersion);
	}
	
	public int countAppVersion(AppVersion appVersion) throws RollbackException {
		return appVersionDao.count(appVersion);
	}
	
	public AppVersion loadByItemKey(String itemKey) throws Exception{
		AppVersion condition = new AppVersion();
		condition.setItemKey(itemKey);
		
		Collection<AppVersion> list = appVersionDao.list(condition, 1, 1);
		
		if(list == null || list.isEmpty())
			return null;
		
		return (AppVersion)list.iterator().next();
		
	}

	public AppUpdateLog getLastAppUpdateLog(String itemKey,String version) throws Exception{
		AppUpdateLog condition = new AppUpdateLog();
		condition.setItemKey(itemKey);
		condition.setVersion(version);
		Collection<AppUpdateLog> list = appUpdateLogDao.list(condition, 1, 1);
		
		if(list == null || list.isEmpty())
			return null;
		
		return (AppUpdateLog)list.iterator().next();
		
	}
	
	public AppVersionDao getAppVersionDao() {
		return appVersionDao;
	}

	public void setAppVersionDao(AppVersionDao appVersionDao) {
		this.appVersionDao = appVersionDao;
	}

	public AppUpdateLogDao getAppUpdateLogDao() {
		return appUpdateLogDao;
	}

	public void setAppUpdateLogDao(AppUpdateLogDao appUpdateLogDao) {
		this.appUpdateLogDao = appUpdateLogDao;
	}
	
}
