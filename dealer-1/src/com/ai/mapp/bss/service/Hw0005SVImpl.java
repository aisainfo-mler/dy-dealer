package com.ai.mapp.bss.service;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.SYSConstant;

@Service("hw0005Service")
@Scope("singleton")
public class Hw0005SVImpl extends ISVTemplate {

	@Autowired
	private UserService userService;

	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0005.Response rsp = new com.ai.mapp.model.HW0005.Response();
		com.ai.mapp.model.HW0005.Request req = com.ai.mapp.model.HW0005.Request
				.unmarshal(new StringReader((String)param.getParameter(BSSConstantParam.REQUESTCONTENT)));
		
		User user = (User)param.getResult();
		
		if(user == null)
		{
			rsp.setMSG(LanguageInfo.USERNAME_PASSWORD_ERROR);
			rsp.setRspCode(BSSConstantError.CODE_DATA_ERROR);
			return rsp.toXMLString();
		}
		
		/**
		 * 先存入终端相关信息
		 */
		param.setSessionAttr(BSSConstantParam.OS, req.getOS());
		param.setSessionAttr(BSSConstantParam.CLIENTVERSION, req.getClientVersion());
		param.setSessionAttr(BSSConstantParam.FROM, req.getFrom());
		param.setSessionAttr(BSSConstantParam.HARDWAREBRAND, req.getHardwareBrand());
		param.setSessionAttr(BSSConstantParam.HARDWAREMODEL, req.getHardwareModel());
		param.setSessionAttr(BSSConstantParam.IMEI, req.getIMEI());
		param.setSessionAttr(BSSConstantParam.IMSI, req.getIMSI());
		param.setSessionAttr(BSSConstantParam.USERCODE, user.getUserCode());
		param.setSessionAttr(BSSConstantParam.USERID, user.getUserId());
		param.setSessionAttr(BSSConstantParam.LANGUAGE, SYSConstant.LANGUAGE_CHINA);//req.getLanguage()
		
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);
		rsp.setSessionId((String)param.getParameter(BSSConstantParam.SESSION_ID));
		param.setIfUpdate(true);
				
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);

		return rsp.toXMLString();
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		String requestContent = (String) param.getParameter(BSSConstantParam.REQUESTCONTENT);
		com.ai.mapp.model.HW0005.Request request = com.ai.mapp.model.HW0005.Request.unmarshal(new StringReader(requestContent));
		
		User user = userService.loadValidUser(request.getUserName(), request.getPassWd());
		
		param.setIfSuccess(true);
		param.setResult(user);
		return param;
	}

	@Override
	/**
	 * 错误处理
	 */
	public Object error(ParamObject param) throws Exception {
		return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR,
				param.getErrorInfo());
	}

}
