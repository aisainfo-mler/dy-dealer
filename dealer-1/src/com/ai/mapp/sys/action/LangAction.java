package com.ai.mapp.sys.action;

import java.util.Locale;


import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-7-23 下午11:56:57
 * 类说明:
 */

public class LangAction extends BaseAction {
	
	private String requestLang;

	public String getRequestLang() {
		return requestLang;
	}

	public void setRequestLang(String requestLang) {
		this.requestLang = requestLang;
	}

	public String lang()throws Exception{
		if(StringUtil.isEmpty(requestLang)){
			requestLang = "en_US";
		}
		
		
	    String[] language_country = requestLang.split("_");
	    String language1 = language_country[0];
	    String country = language_country[1];
	    Locale locale = new Locale(language1, country);
	    ActionContext.getContext().setLocale(locale);
	   
	    putSessionValue("lang", requestLang);
		putSessionValue("WW_TRANS_I18N_LOCALE", requestLang);
	    
//	    sendUrl = getRequest().getContextPath();
//	    String s = getRequest().getRequestURL().toString();
	   
//	    request = getRequest();
//	    sendUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		
//		getRequest().getSession().setAttribute("lang",requestLang);
		return SUCCESS;
	}
	
	public String en()throws Exception{
		if(StringUtil.isEmpty(requestLang)){
			requestLang = "en_US";
		}
		setLanguage();
		return SUCCESS;
	}
	
	public String cn()throws Exception{
		if(StringUtil.isEmpty(requestLang)){
			requestLang = "zh_CN";
		}
		setLanguage();
		return SUCCESS;
	}
	
	private void setLanguage()throws Exception{
		String[] language_country = requestLang.split("_");
	    String language1 = language_country[0];
	    String country = language_country[1];
	    Locale locale = new Locale(language1, country);
	    ActionContext.getContext().setLocale(locale);
	   
	    putSessionValue("lang", requestLang);
		putSessionValue("WW_TRANS_I18N_LOCALE", requestLang);
	}
	
}
