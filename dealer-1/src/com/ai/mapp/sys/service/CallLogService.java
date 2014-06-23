package com.ai.mapp.sys.service;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.error.RollbackException;
import com.ai.mapp.sys.dao.CallLogDao;
import com.ai.mapp.sys.entity.CallLog;

/**
 * @author mler 
 * @version 创建时间：2014-5-14 下午07:10:36
 * 类说明:
 */

@Service
public class CallLogService {
	
	public final Log log = LogFactory.getLog(CallLogService.class);
	
	@Autowired
	private CallLogDao callLogDao;
	
	public Collection<CallLog> listCallLogs(CallLog callLog,int start,int limit){
		try{
			log.debug(callLog==null?"callLog is null":callLog.toString());
			
			Collection<CallLog> c = callLogDao.list(callLog, start, limit);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection<CallLog> listAllCallLogs(CallLog callLog){
		try{
			log.debug(callLog==null?"callLog is null":callLog.toString());
			
			Collection<CallLog> c = callLogDao.listAll(callLog);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=false,rollbackFor={Exception.class})
	public void saveCallLog(CallLog callLog){
		try{
			log.debug(callLog==null?"callLog is null":callLog.toString());
			callLogDao.save(callLog);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCallLog(CallLog callLog){
		try{
			log.debug(callLog==null?"callLog is null":callLog.toString());
			callLogDao.delete(callLog);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int countCallLog(CallLog callLog) throws RollbackException {
		try{
			return callLogDao.count(callLog);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RollbackException(e);
		}
	}
	
	public CallLog loadCallLog(Long id)
	{
		return callLogDao.get(id);
	}
	
}
