/**
 * 
 */
package com.ailk.web;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import com.ailk.butterfly.sys.RequestContext;

/**
 * @author yangqx
 *
 */
public class View {

	private ModelAndView mv;
	
	
	public View(String viewName) {
		this(new ModelAndView(viewName,null));
	}
	public View(String viewName,Map<String,?> model) {
		this(new ModelAndView(viewName,model));
	}
	public View(ModelAndView mv) {
		this.mv=mv;
	}
	
	
	public View() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public View addParam(String key,Object value) {
		this.mv.addObject(key, value);
		return this;
	}
	public StringWriter render() {
		StringWriter write=new StringWriter();
		WebApplicationContext context=(WebApplicationContext)RequestContext.getRequest().getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		
		VelocityConfigurer vc=context.getBean(VelocityConfigurer.class);
	
		Context velocityContext=new VelocityContext();
		Map<String, Object> datas=mv.getModel();
		for(Iterator<String> it=datas.keySet().iterator();it.hasNext();) {
			String key=it.next();
			velocityContext.put(key, datas.get(key));
		}
		
		vc.getVelocityEngine().mergeTemplate(mv.getViewName()+".vm", "utf-8", velocityContext, write);
	
		return write;
	}
	@Override
	public String toString() {
		return this.render().toString();
	}

	
	
}
