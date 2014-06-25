/**
 * 
 */
package com.ailk.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.ailk.butterfly.sys.RequestContext;

/**
 * @author yangqx
 *
 */
public class VelocityTemplate {

	public View merger(String url) {
		WebApplicationContext context=null;
		try {
			 context=(WebApplicationContext)RequestContext.getRequest().getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Map<String,RequestMappingHandlerMapping> hms=context.getBeansOfType(RequestMappingHandlerMapping.class, true, true);
		String d="";
		RequestMappingHandlerMapping hm=hms.get(hms.keySet().iterator().next());
		
		Map<RequestMappingInfo, HandlerMethod> handlerMethods=hm.getHandlerMethods();
		
		Map<RequestMappingInfo, HandlerMethod> matchs=new HashMap<RequestMappingInfo, HandlerMethod>();
		for(RequestMappingInfo mapping:handlerMethods.keySet()) {
			Set<String> urls=mapping.getPatternsCondition().getPatterns();
			boolean requestMappingUrlMatch=false;
			boolean requestMappingMethodMatch=false;
			for(String u:urls) {
				if(url.matches(u)) {
					requestMappingUrlMatch=true;
					break;
				}
			}
			if(!requestMappingUrlMatch) continue;
			
			if(mapping.getMethodsCondition().getMethods().contains(RequestMethod.GET)) {
				requestMappingMethodMatch=true;
			}else {
				continue;
			}
			HandlerMethod handlerMethod=handlerMethods.get(mapping);
			Component component=handlerMethod.getMethodAnnotation(Component.class);
			if(component != null) {
				matchs.put(mapping, handlerMethods.get(mapping));
			}
			
			
			
		} 
		
		if(matchs.size() > 1) {
			throw new RuntimeException("发现多个符合条件的controller ");
		}
		
		HandlerMethod method=matchs.values().iterator().next();
		
		Map<String,RequestMappingHandlerAdapter> adapters=context.getBeansOfType(RequestMappingHandlerAdapter.class, true, true);
		
		RequestMappingHandlerAdapter adpater=adapters.values().iterator().next();
		
		HandlerMethod realMethod=method.createWithResolvedBean();
		HttpServletRequest request=RequestContext.getRequest();
		request.setAttribute("name", "yangqx");
		try {
			ModelAndView mav=adpater.handle(RequestContext.getRequest(), RequestContext.getResponse(), realMethod);
			return new View(mav);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return new View(new ModelAndView(url));
		
	}
	
}
