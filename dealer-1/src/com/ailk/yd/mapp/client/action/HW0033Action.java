package com.ailk.yd.mapp.client.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.SysProp;
import com.ai.mapp.sys.service.SysPropService;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0033Request;
import com.ailk.yd.mapp.client.model.HW0033Response;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-13 下午05:52:36
 * 类说明:版本升级
 */

@Service("hw0033")
@Action(bizcode = "hw0033", userCheck = false)
public class HW0033Action extends AbstractYDBaseActionHandler<HW0033Request, HW0033Response> {
	
	@Autowired
	private SysPropService sysPropService;

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		SysProp appProp = sysPropService.loadPropByKey(SYSConstant.APP_ITEMKEY_SYSPROP);
		this.response.setClientVersion(appProp.getpValue());
		this.response.setRemark(appProp.getRemark());
		this.response.setRequireVersion(appProp.getString1());
		
		
		
		String updateUrl = "";
		if("IOS".equalsIgnoreCase(this.request.getClientSystem()) || this.request.getClientSystem().toUpperCase().indexOf("IOS") != -1){
			SysProp plistProp = sysPropService.loadPropByKey(SYSConstant.PLIST_ADDRESS);
			updateUrl = SYSConstant.IOS_DOWNLOAD_FRONT + plistProp.getpValue();
		}
		this.response.setUpdateUrl(updateUrl);
	}

	public SysPropService getSysPropService() {
		return sysPropService;
	}

	public void setSysPropService(SysPropService sysPropService) {
		this.sysPropService = sysPropService;
	}

}
