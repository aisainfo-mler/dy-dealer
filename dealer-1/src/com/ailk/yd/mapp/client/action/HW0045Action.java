package com.ailk.yd.mapp.client.action;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.AppUpdateLog;
import com.ai.mapp.sys.entity.AppVersion;
import com.ai.mapp.sys.service.AppVersionService;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0045Request;
import com.ailk.yd.mapp.client.model.HW0045Response;
import com.ailk.yd.mapp.tibco.TibcoCache;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-8-14 上午01:56:19
 * 类说明:版本升级
 */

@Service("hw0045")
@Action(bizcode = "hw0045", userCheck = false)
@Scope("prototype")
public class HW0045Action extends AbstractYDBaseActionHandler<HW0045Request, HW0045Response> {

	@Autowired
	private AppVersionService appVersionService;
	
	
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		if(StringUtils.isEmpty(this.request.getItemKey()))
			throw new Exception("no send the itemKey,please send it");
	
		AppVersion app = appVersionService.loadByItemKey(this.request.getItemKey());
		if(app == null){
			throw new Exception("there's no app of " + this.request.getItemKey() + ",check your itemKey");
		}
		
		this.response.setCompatibleVersion(app.getCompatibleVersion());
		this.response.setIntroduction(app.getIntroduction());
		this.response.setLastVersion(app.getLastVersion());
		this.response.setUpdateURL(app.getUpdateUrl());
		
		AppUpdateLog updateLog = appVersionService.getLastAppUpdateLog(this.request.getItemKey(),app.getLastVersion());
		if(updateLog != null){
			this.response.setUpdateIssue(updateLog.getUpdateContent());
		}
		
		String updateUrl = app.getUpdateUrl();
		String front = updateUrl.substring(0,updateUrl.indexOf("."));
		String end = updateUrl.substring(updateUrl.indexOf("."));
		String hostUrl = null;
		if(end.indexOf("plist") != -1){
			hostUrl = (String)((Map)TibcoCache.dicts.get("ROOT")).get(SYSConstant.MAPP_IOS_PLIST_URL);
			
			this.response.setUpdateURL(SYSConstant.IOS_DOWNLOAD_FRONT + hostUrl);
		}else{
			updateUrl =  app.getLastVersion();
			updateUrl = front + "_" + updateUrl + end;
			hostUrl = (String)((Map)TibcoCache.dicts.get("ROOT")).get(SYSConstant.MAPP_UPLOAD_URL_HTTP);
			this.response.setUpdateURL(hostUrl + updateUrl);
		}
	}

}
