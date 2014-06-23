package com.ai.mapp.sys.service;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.error.RollbackException;
import com.ai.mapp.sys.dao.AccountLogDao;
import com.ai.mapp.sys.entity.AccountLog;
import com.ai.mapp.sys.entity.User;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-19 下午07:10:36
 * 类说明:
 */

@Service
@Transactional
public class AccountLogService {
	
	public final Log log = LogFactory.getLog(AccountLogService.class);
	
	@Autowired
	private AccountLogDao accountLogDao;
	
	@Autowired
	private UserService userService;
	
	public Collection<AccountLog> listAccountLogs(AccountLog accountLog,int start,int limit){
		try{
			log.debug(accountLog==null?"accountLog is null":accountLog.toString());
			
			Collection<AccountLog> c = accountLogDao.list(accountLog, start, limit);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection<AccountLog> listAllAccountLogs(AccountLog accountLog){
		try{
			log.debug(accountLog==null?"accountLog is null":accountLog.toString());
			
			Collection<AccountLog> c = accountLogDao.listAll(accountLog);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Long saveAccountLog(AccountLog accountLog){
			log.debug(accountLog==null?"accountLog is null":accountLog.toString());
			accountLog.setCreateTime(new Date());
			accountLogDao.save(accountLog);
			return accountLog.getId();
	}
	
	public void deleteAccountLog(AccountLog accountLog){
		try{
			log.debug(accountLog==null?"accountLog is null":accountLog.toString());
			accountLogDao.delete(accountLog);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int countAccountLog(AccountLog accountLog) throws RollbackException {
		try{
			return accountLogDao.count(accountLog);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RollbackException(e);
		}
	}
	
	public AccountLog loadAccountLog(Long id)
	{
		return accountLogDao.get(id);
	}
	
}
