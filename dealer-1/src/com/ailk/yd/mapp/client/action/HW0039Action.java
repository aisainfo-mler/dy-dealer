package com.ailk.yd.mapp.client.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.util.ConvertUtils;
import com.ai.mapp.sys.entity.AccountInfo;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.UserService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0039Request;
import com.ailk.yd.mapp.client.model.HW0039Response;

/**
 * 取登录账户的余额
 * @author qianshihua
 *
 */
@Service("hw0039")
@Action(bizcode="hw0039",userCheck=true)
@Scope("prototype")
public class HW0039Action extends
		AbstractYDBaseActionHandler<HW0039Request, HW0039Response> {

	@Autowired
	private UserService userService;
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		String userCode = this.getUserinfo().getUserName();
		User user = userService.loadUserByUserCode(userCode);
		List<AccountInfo> accounts = user.getAccounts();
		if(accounts==null || accounts.size()<1){
			throw new Exception("Dealer: "+userCode+" has no account!");
		}
		Long amount = accounts.get(0).getAmount();
		this.response=new HW0039Response();
		this.response.setAmount(ConvertUtils.getMoneyString(amount));
	}

}
