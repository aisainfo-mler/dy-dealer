package com.ai.mapp.sys.service;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.error.RollbackException;
import com.ai.mapp.sys.dao.AccountInfoDao;
import com.ai.mapp.sys.entity.AccountInfo;
import com.ai.mapp.sys.entity.AccountLog;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.OrderInfo;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.SYSConstant;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-19 下午07:10:36
 * 类说明:
 */

@Service
@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
public class AccountInfoService {
	
	public final Log log = LogFactory.getLog(AccountInfoService.class);
	
	@Autowired
	private AccountInfoDao accountInfoDao;
	
	@Autowired
	private AccountLogService accountLogService;
	
	@Autowired
	private AgentOrderService agentOrderService;
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommissionService commissionService;
	
	public Collection<AccountInfo> listAccountInfos(AccountInfo accountInfo,int start,int limit){
		try{
			log.debug(accountInfo==null?"accountInfo is null":accountInfo.toString());
			
			Collection<AccountInfo> c = accountInfoDao.list(accountInfo, start, limit);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection<AccountInfo> listAllAccountInfos(AccountInfo accountInfo){
		try{
			log.debug(accountInfo==null?"accountInfo is null":accountInfo.toString());
			
			Collection<AccountInfo> c = accountInfoDao.listAll(accountInfo);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveAccountInfo(AccountInfo accountInfo){
		try{
			log.debug(accountInfo==null?"accountInfo is null":accountInfo.toString());
			accountInfoDao.save(accountInfo);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAccountInfo(AccountInfo accountInfo){
		try{
			log.debug(accountInfo==null?"accountInfo is null":accountInfo.toString());
			accountInfoDao.delete(accountInfo);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int countAccountInfo(AccountInfo accountInfo) throws RollbackException {
		try{
			return accountInfoDao.count(accountInfo);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RollbackException(e);
		}
	}
	
	public AccountInfo loadAccountInfo(Long id)
	{
		return accountInfoDao.get(id);
	}
	
	public Collection<AccountInfo> loadAccountInfoByOperatorId(Long operatorId)
	{
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setOperator(new User(operatorId));
		return  listAllAccountInfos(accountInfo);
	}
	
	public void payAgentOrderFromAccount(String agentOrderCode) throws Exception
	{
		AgentOrder order = agentOrderService.loadAgentOrderByOrderCode(agentOrderCode);
		
		//TODO 预存池扣款
		User user = userService.loadUserByUserCode(order.getCreator().getUserCode());
		if(user == null)
			throw new Exception(LanguageInfo.USER_NOT_EXIST);
		Collection<AccountInfo> accounts = user.getAccounts();
		if(accounts == null || accounts.isEmpty())
			throw new Exception(LanguageInfo.HAVE_NO_ACCOUNT);
		
		AccountInfo account = accounts.iterator().next();
		/** 
		 * 订单支付全额款，佣金订单完成后返充代理商账户
		 *  2014-05-05 mler
		 **/
		long needfee =  order.getSaleFee() == null ? 0 : order.getSaleFee().longValue();

		long amount = account.getAmount() == null ? 0 : account.getAmount().longValue();
		if(amount < needfee)
			throw new Exception(" " + LanguageInfo.ACCOUNT + " : "+amount+" " + LanguageInfo.BE_NO_ENOUGH + " ");
		
		account.setAmount(amount-needfee);
		saveAccountInfo(account);
		
		//TODO 预存池流水扣款记录
		AccountLog log_pay = new AccountLog();
		log_pay.setAccount(account);
		log_pay.setCreateTime(new Date());
		log_pay.setPay(order.getSaleFee());
		log_pay.setLogType(SYSConstant.ACCOUNT_LOG_TYPE_PAY);
		log_pay.setAgentOrder(order);
		accountLogService.saveAccountLog(log_pay);
		
	}
	
	public void payOrderInfoFromAccount(String orderCode) throws Exception
	{
		OrderInfo order = orderInfoService.loadOrderInfoByOrderCode(orderCode);
		
		//TODO 预存池扣款
		User user = userService.loadUserByUserCode(order.getCreator().getUserCode());
		if(user == null)
			throw new Exception(LanguageInfo.USER_NOT_EXIST);
		
		Collection<AccountInfo> accounts = user.getAccounts();
		if(accounts == null || accounts.isEmpty())
			throw new Exception(LanguageInfo.HAVE_NO_ACCOUNT);
		
		AccountInfo account = accounts.iterator().next();
		
		long needfee = order.getRealFee() == null ? 0 : order.getRealFee().longValue();
		long amount = account.getAmount() == null ? 0 : account.getAmount().longValue();
		if(amount < needfee)
			throw new Exception(" " + LanguageInfo.ACCOUNT + " : "+amount+" " + LanguageInfo.BE_NO_ENOUGH + " ");
		
		account.setAmount(amount-needfee);
		saveAccountInfo(account);
		
		//TODO 预存池流水记录
		AccountLog log = new AccountLog();
		log.setAccount(account);
		log.setCreateTime(new Date());
		log.setPay(order.getRealFee());
		log.setLogType(SYSConstant.ACCOUNT_LOG_TYPE_PAY);
		log.setOrder(order);
		
		accountLogService.saveAccountLog(log);
	}
	
	public void chargeAccount(String userCode,Long fee,String payMode,String bankSerial) throws Exception
	{
		if(StringUtil.isEmpty(userCode))
			throw new Exception(LanguageInfo.USERCODE_IS_NULL);
		
		User user = userService.loadUserByUserCode(userCode);
		
		if(user == null || SYSConstant.STATE_VALID.equals(user.getStatus()) == false)
			throw new Exception(userCode + " " + LanguageInfo.UNEXIST_OR_INACTIVE);
		
		Collection<AccountInfo> accounts = user.getAccounts();
		if(accounts == null || accounts.isEmpty())
			throw new Exception(LanguageInfo.HAVE_NO_ACCOUNT);
		
		AccountInfo account = accounts.iterator().next();
		
		long addfee = fee == null ? 0 : fee.longValue();
		long amount = account.getAmount() == null ? 0 : account.getAmount().longValue();
		
		account.setAmount(account.getAmount()-fee);
		account.setAmount(amount+addfee);
		saveAccountInfo(account);
		
		AccountLog log = new AccountLog();
		log.setAccount(account);
		log.setCreateTime(new Date());
		log.setPay(addfee);
		log.setLogType(SYSConstant.ACCOUNT_LOG_TYPE_CHARGE);//1
		log.setBlankSerialNumber(bankSerial);
		
		accountLogService.saveAccountLog(log);
	}
	
}
