package com.ailk.yd.mapp.client.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.mapp.core.MappConstant;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0005Request;
import com.ailk.yd.mapp.client.model.HW0005Response;
import com.ailk.yd.mapp.model.UserInfo;

@Service("hw0005")
@Action(bizcode="hw0005",userCheck=false)
@Scope("prototype")
public class HW0005Action extends AbstractYDBaseActionHandler<HW0005Request, HW0005Response> {

	@Autowired
	private UserService userService;
	
	@Override
	protected void doAction() throws BusinessException, SystemException,Exception 
	{
		User user = userService.loadValidUser(request.getUserName(), request.getPassWd());
		
		if(user == null)
			throw new Exception(LanguageInfo.USERNAME_PASSWORD_ERROR);
		
		/**
		 * 先存入终端相关信息
		 */
		MappContext.setAttribute(BSSConstantParam.OS, request.getOs());
		MappContext.setAttribute(BSSConstantParam.CLIENTVERSION, request.getClientVersion());
		MappContext.setAttribute(BSSConstantParam.FROM, request.getFrom());
		MappContext.setAttribute(BSSConstantParam.HARDWAREBRAND, request.getHardwareBrand());
		MappContext.setAttribute(BSSConstantParam.HARDWAREMODEL, request.getHardwareModel());
		MappContext.setAttribute(BSSConstantParam.IMEI, request.getImei());
		MappContext.setAttribute(BSSConstantParam.IMSI, request.getImsi());
		MappContext.setAttribute(BSSConstantParam.USERCODE, user.getUserCode());
		MappContext.setAttribute(BSSConstantParam.USERID, user.getUserId());
		MappContext.setAttribute(BSSConstantParam.LANGUAGE, SYSConstant.LANGUAGE_ENGLISH);//req.getLanguage()
		
		IUserinfo u = new UserInfo();
		u.setUserId(user.getUserId());
		u.setUserName(user.getUserCode());
		MappContext.setAttribute(MappConstant.MAPP_SESSION_USER,u);
		String sessionId = (String)MappContext.getAttribute(MappContext.MAPPCONTEXT_SESSIONID);
		this.response.setSessionId(sessionId);
	}

}
