package com.ailk.yd.mapp.client.action;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.SysProp;
import com.ai.mapp.sys.service.SysPropService;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0033Request;
import com.ailk.yd.mapp.client.model.HW0033Response;

/**
 * @author mler 
 * @version 创建时间：2014-5-13 下午05:52:36
 * 类说明:版本升级
 */

@Service("hw0033")
@Action(bizcode = "hw0033", userCheck = false)
@Scope("prototype")
public class HW0033Action extends AbstractYDBaseActionHandler<HW0033Request, HW0033Response> {
	
	/**
	 * 系统类型
	 */
	private final String CLIENT_SYSTEM_IPHONE = "1";
	private final String CLIENT_SYSTEM_APHONE = "2";
	private final String CLIENT_SYSTEM_IPAD = "3";
	private final String CLIENT_SYSTEM_APAD = "4";
	
	@Autowired
	private SysPropService sysPropService;

	@Override
	protected void doAction() throws Exception 
	{
		if(CLIENT_SYSTEM_IPHONE.equals(request.getClientSystem()))
		{
			Set<String> key_set = new HashSet<String>(0);
			key_set.add(SYSConstant.IPHONE_APP_CUR_VERSION);
			key_set.add(SYSConstant.IPHONE_APP_REQ_VERSION);
			key_set.add(SYSConstant.IPHONE_PLIST_ADDRESS);
			
			Map<String,SysProp> props = sysPropService.loadSystemPropertiesByKeys(key_set);
			
			if(props != null && props.isEmpty() == false)
			{
				response.setClientVersion(props.get(SYSConstant.IPHONE_APP_CUR_VERSION) == null?null:props.get(SYSConstant.IPHONE_APP_CUR_VERSION).getName());
				response.setRequireVersion(props.get(SYSConstant.IPHONE_APP_REQ_VERSION) == null?null:props.get(SYSConstant.IPHONE_APP_REQ_VERSION).getName());
				response.setUpdateUrl(props.get(SYSConstant.IPHONE_PLIST_ADDRESS) == null?null:SYSConstant.IOS_DOWNLOAD_FRONT+props.get(SYSConstant.IPHONE_PLIST_ADDRESS).getName());
			}
		}
		else if(CLIENT_SYSTEM_IPAD.equals(request.getClientSystem()))
		{
			Set<String> key_set = new HashSet<String>(0);
			key_set.add(SYSConstant.IPAD_APP_CUR_VERSION);
			key_set.add(SYSConstant.IPAD_APP_REQ_VERSION);
			key_set.add(SYSConstant.IPAD_PLIST_ADDRESS);
			
			Map<String,SysProp> props = sysPropService.loadSystemPropertiesByKeys(key_set);
			
			if(props != null && props.isEmpty() == false)
			{
				response.setClientVersion(props.get(SYSConstant.IPAD_APP_CUR_VERSION) == null?null:props.get(SYSConstant.IPAD_APP_CUR_VERSION).getName());
				response.setRequireVersion(props.get(SYSConstant.IPAD_APP_REQ_VERSION) == null?null:props.get(SYSConstant.IPAD_APP_REQ_VERSION).getName());
				response.setUpdateUrl(props.get(SYSConstant.IPAD_PLIST_ADDRESS) == null?null:SYSConstant.IOS_DOWNLOAD_FRONT+props.get(SYSConstant.IPAD_PLIST_ADDRESS).getName());
			}
		}
		else if(CLIENT_SYSTEM_APHONE.equals(request.getClientSystem()))
		{
			Set<String> key_set = new HashSet<String>(0);
			key_set.add(SYSConstant.APHONE_APP_CUR_VERSION);
			key_set.add(SYSConstant.APHONE_APP_REQ_VERSION);
			key_set.add(SYSConstant.APHONE_APK_ADDRESS);
			
			Map<String,SysProp> props = sysPropService.loadSystemPropertiesByKeys(key_set);
			
			if(props != null && props.isEmpty() == false)
			{
				response.setClientVersion(props.get(SYSConstant.APHONE_APP_CUR_VERSION) == null?null:props.get(SYSConstant.APHONE_APP_CUR_VERSION).getName());
				response.setRequireVersion(props.get(SYSConstant.APHONE_APP_REQ_VERSION) == null?null:props.get(SYSConstant.APHONE_APP_REQ_VERSION).getName());
				response.setUpdateUrl(props.get(SYSConstant.APHONE_APK_ADDRESS) == null?null:props.get(SYSConstant.APHONE_APK_ADDRESS).getName());
			}
		}
		else if(CLIENT_SYSTEM_APAD.equals(request.getClientSystem()))
		{
			Set<String> key_set = new HashSet<String>(0);
			key_set.add(SYSConstant.APAD_APP_CUR_VERSION);
			key_set.add(SYSConstant.APAD_APP_REQ_VERSION);
			key_set.add(SYSConstant.APAD_APK_ADDRESS);
			
			Map<String,SysProp> props = sysPropService.loadSystemPropertiesByKeys(key_set);
			
			if(props != null && props.isEmpty() == false)
			{
				response.setClientVersion(props.get(SYSConstant.APAD_APP_CUR_VERSION) == null?null:props.get(SYSConstant.APAD_APP_CUR_VERSION).getName());
				response.setRequireVersion(props.get(SYSConstant.APAD_APP_REQ_VERSION) == null?null:props.get(SYSConstant.APAD_APP_REQ_VERSION).getName());
				response.setUpdateUrl(props.get(SYSConstant.APAD_APK_ADDRESS) == null?null:props.get(SYSConstant.APAD_APK_ADDRESS).getName());
			}
		}
		else
		{
			throw new Exception("unknow client system");
		}
	}

}
