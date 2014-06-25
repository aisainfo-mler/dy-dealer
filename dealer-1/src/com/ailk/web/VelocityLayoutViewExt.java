package com.ailk.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.view.context.ChainedContext;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;

import com.ailk.butterfly.sys.RequestContext;


public class VelocityLayoutViewExt extends VelocityLayoutView {

	@Override
	protected void doRender(Context context, HttpServletResponse response)
			throws Exception {
		HttpServletRequest request=((ChainedContext)context).getRequest();
		
		context.put("title", "后台管理平台");
		context.put("date", new DateTool());
		context.put("CONTEXT_PATH", request.getContextPath());
		context.put("USER", RequestContext.getUser());
		context.put("THEME_PATH", "/theme/default");
		context.put("APP_STATIC_PATH", "/ultranote");
		context.put("APP_JS_PATH", "/ultranote/js");
		context.put("APP_THEME_PATH", "/ultranote/theme/default");
		
		
		
		context.put("velocityTemplate", new VelocityTemplate());
		boolean isLayout=true;
		//TODO 不需要layout
		String uri=request.getRequestURI();
		if(uri.startsWith("/user/login")) {
			//isLayout=false;
		}
		if(isLayout) {
			
			super.doRender(context, response);
		}else {
			mergeTemplate(getTemplate(), context, response);
		}
	}
	

}
