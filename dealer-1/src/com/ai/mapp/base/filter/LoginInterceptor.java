package com.ai.mapp.base.filter;

import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.action.BaseAction;
import com.ai.mapp.sys.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-25 下午12:33:15
 * 类说明:
 */

public class LoginInterceptor extends AbstractInterceptor {
private final Log log = LogFactory.getLog(LoginInterceptor.class);
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		log.debug("验证登陆信息........");
        Map<String,Object> session = invocation.getInvocationContext().getSession();
        Object user = session.get(BaseAction.HTTP_SESSION_USER);
        
        Object lang =  session.get("WW_TRANS_I18N_LOCALE");
        Locale locale = ActionContext.getContext().getLocale();
        if(lang == null || locale == null || StringUtil.isEmpty(locale.getCountry()) ){
			String requestLang = "en_US";
			String[] language_country = requestLang.split("_");
		    String language1 = language_country[0];
		    String country = language_country[1];
		    locale = new Locale(language1, country);
		    ActionContext.getContext().setLocale(locale);
		   
		    session.put("lang", requestLang);
		    session.put("WW_TRANS_I18N_LOCALE", requestLang);
		}
		
		
	    
        
        if(user == null || user instanceof User == false)
        {
        	log.error("无登陆信息..");
        	return "index"; 
        }
        
        log.debug("验证成功。");
        
        return invocation.invoke();    
	}
}
