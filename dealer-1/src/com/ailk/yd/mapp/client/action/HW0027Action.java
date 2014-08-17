package com.ailk.yd.mapp.client.action;

import java.math.BigDecimal;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.util.ConvertUtils;
import com.ai.mapp.sys.entity.AccountInfo;
import com.ai.mapp.sys.entity.AccountLog;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AccountInfoService;
import com.ai.mapp.sys.service.AccountLogService;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.mapp.core.ErrorCodeDefine;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0027Request;
import com.ailk.yd.mapp.client.model.HW0027Response;
import com.ailk.yd.mapp.model.YDDatapackage;
import com.ailk.yd.mapp.tibco.model.YD0013.YD0013Request;
import com.ailk.yd.mapp.tibco.model.YD0013.YD0013Response;

/**
 * @author Zhengwj
 * @version 创建时间：2014-5-7 上午11:13:08 类说明:代理商充值接口
 */

@Service("hw0027")
@Action(bizcode = "hw0027", userCheck = true)
@Scope("prototype")
public class HW0027Action extends
		AbstractYDBaseActionHandler<HW0027Request, HW0027Response> {

	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private AccountLogService accountLogService;
	@Autowired
	private UserService userService;
	
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		IUserinfo user = (IUserinfo) MappContext.getAttribute(MappContext.MAPPCONTEXT_USER);

		String sessionId = (String) MappContext.getAttribute(MappContext.MAPPCONTEXT_SESSIONID);

		AccountLog accountLog = new AccountLog();

		if (request.getPwd() == null)
			throw new Exception("" + " " + LanguageInfo.PAY_PWD_EMPTY);
		if (request.getAccount() == null)
			throw new Exception("" + " " + LanguageInfo.USERCODE_IS_NULL);
		User users = userService.loadUserByUserCode(user.getUserName());
		if (users == null)
			throw new Exception(LanguageInfo.USER_NOT_EXIST);
		
		Collection<AccountInfo> accountInfos = accountInfoService.loadAccountInfoByOperatorId(users.getUserId().longValue());
		 // Collection<AccountInfo> accountInfos =accountInfoService.loadAccountInfoByOperatorId(user.getUserId().longValue());
	
		if(!StringUtils.equals(request.getPwd(), users.getPayPwd()))
			throw new Exception(""+" " + LanguageInfo.PAY_PWD_WRONG);
		AccountInfo account = null;
		Long logId = null;
		if (accountInfos != null && accountInfos.size() != 0) {
			account = accountInfos.iterator().next();
			accountLog.setAccount(account);
			accountLog.setIncome(this.request.getAmount() == null ? 0: ConvertUtils.getMoneyLong(request.getAmount().toString()));
			accountLog.setLogType(SYSConstant.ACCOUNT_LOG_TYPE_CHARGE);
			accountLog.setLogStatus(SYSConstant.ACCOUNT_LOG_STATUS_WAITING);
			logId = accountLogService.saveAccountLog(accountLog);

			if ("1".equals(test)) {
				AccountLog log = accountLogService.loadAccountLog(logId);
				log.setLogStatus(SYSConstant.ACCOUNT_LOG_STATUS_FAIL);
				log.setTibcoSn("test_tibco_1111");
				this.response.setTibcoSn("test_tibco_1111");
				BigDecimal crush = (new BigDecimal(account.getAmount() == null ? 0 : account.getAmount())).add(this.request.getAmount());
				account.setAmount(ConvertUtils.getMoneyLong(crush.toString()));
				accountLogService.saveAccountLog(accountLog);
				accountInfoService.saveAccountInfo(account);
			} else {
				YD0013Request ydReq = new YD0013Request();
				ydReq.setAccount(this.request.getAccount());
				ydReq.setDealerId(user.getUserId().longValue());
				// ydReq.setDealerId(1L);
				ydReq.setPwd(this.request.getPwd());
				ydReq.setAmount(this.request.getAmount());
				// YDDatapackage pkg = sendMsg("yd0013", ydReq, sessionId);
				YDDatapackage pkg = null;
				YD0013Response resp = (YD0013Response) pkg.getBody();

				if (!ErrorCodeDefine.SUCCESS.equals(pkg.getHeader().getRespCode())) {
					AccountLog log = accountLogService.loadAccountLog(logId);
					log.setLogStatus(SYSConstant.ACCOUNT_LOG_STATUS_FAIL);
					if (resp != null && StringUtils.isNotEmpty(resp.getSn())) {
						log.setTibcoSn(resp.getSn());
						this.response.setTibcoSn(resp.getSn());
					}
					accountLogService.saveAccountLog(accountLog);

					throw new BusinessException(ErrorCodeDefine.SERVER_ERROR,pkg.getHeader().getRespMsg());
				} else {
					AccountLog log = accountLogService.loadAccountLog(logId);
					log.setLogStatus(SYSConstant.ACCOUNT_LOG_STATUS_FAIL);
					log.setTibcoSn(resp.getSn());
					BigDecimal crush = (new BigDecimal(account.getAmount() == null ? 0: account.getAmount())).add(this.request.getAmount());
					account.setAmount(crush.longValue());
					accountInfoService.saveAccountInfo(account);
					this.response.setTibcoSn(resp.getSn());
					accountLogService.saveAccountLog(accountLog);
				}
			}

		} else {
			throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,"The agent is not to create local accounts!");
		}

	}

	public AccountInfoService getAccountInfoService() {
		return accountInfoService;
	}

	public void setAccountInfoService(AccountInfoService accountInfoService) {
		this.accountInfoService = accountInfoService;
	}

	public AccountLogService getAccountLogService() {
		return accountLogService;
	}

	public void setAccountLogService(AccountLogService accountLogService) {
		this.accountLogService = accountLogService;
	}

}
