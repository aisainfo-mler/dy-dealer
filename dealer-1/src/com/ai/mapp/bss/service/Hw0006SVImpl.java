package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.HwCity;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.PO2VOUtils;
import com.ai.mapp.sys.util.SYSConstant;

@Service("hw0006Service")
@Scope("singleton")
public class Hw0006SVImpl extends ISVTemplate {

	@Autowired
	private UserService userService;

	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0006.Response rsp = new com.ai.mapp.model.HW0006.Response();
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);

		return rsp.toXMLString();
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		String requestContent = (String) param.getParameter(BSSConstantParam.REQUESTCONTENT);
		com.ai.mapp.model.HW0006.Request request = com.ai.mapp.model.HW0006.Request.unmarshal(new StringReader(requestContent));
		
		User user = new User();
		user.setAddress(request.getAddress());
		if(request.getBirthDay() != null)
		user.setBirthDay(StringUtil.isEmpty(request.getBirthDay()) ? null : DateUtils.getDate(request.getBirthDay(), "yyyy-MM-dd"));
		user.setCertificateNo(request.getIdCardNo());
		user.setCertificateType(request.getIdCardType());
		user.setContractPhone(request.getContractPhone());
		user.setEmail(request.getEmail());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setGender(request.getGender());
		user.setMobileNo(request.getMobileNo());
		user.setPassword(request.getPwd());
		user.setPostCode(request.getPostCode());
		if(StringUtil.isEmpty(request.getCity()) ==  false){
			HwCity city = new HwCity();
			city.setCityCode(request.getCity());
			user.setCity(city);
		}
			
//		if(StringUtils.isEmpty(request.getState()) == false)
//		{
//			user.setStatus(request.getState());
//		}
		if(StringUtil.isEmpty(request.getStreet()) == false)
			user.setStreet(request.getStreet());
		if(StringUtil.isEmpty(request.getEmail()) == false)
			user.setUserCode(request.getEmail());
		else
			user.setUserCode(request.getMobileNo());
		user.setUserTitle(request.getUserTitle());
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		
		User updator = userService.loadUserByUserCode((String)param.getParameter(BSSConstantParam.USERCODE));
		user.setUpdater(updator);
		user.setCreator(updator);
		user.setUserType(SYSConstant.USER_TYPE_AGENT);
		user.setStatus(SYSConstant.STATE_WAITING_4_AUDIT);
		
		if(StringUtil.isEmpty(user.getUserCode()) ==  false){
			User checkUser=new User();
			checkUser.setEmail(user.getUserCode());
//			checkUser.setUserType(SYSConstant.USER_TYPE_AGENT);
			int checkFlag=userService.checkRegUser(checkUser,101);
			if(checkFlag>0){
				if(checkFlag==101){
					throw new Exception(LanguageInfo.EMAIL_HAD_EXIST);
				}
			}
		}
		
		userService.saveUserWithCheck(user);
		
//		user.setUpdateTime(new Date());
//		userService.saveUser(user);
		
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
