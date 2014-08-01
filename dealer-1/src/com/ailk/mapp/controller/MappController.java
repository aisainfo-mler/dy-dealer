package com.ailk.mapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.service.DealerDataService;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.mapp.core.MappConstant;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.client.JsonClientHandler;
import com.ailk.butterfly.sys.dal.ibatis.model.IUserInfo;
import com.ailk.web.BaseController;
import com.ailk.yd.mapp.model.UserInfo;
import com.ailk.yd.mapp.model.YDDatapackage;

@Controller
@RequestMapping("/mapp")
public class MappController extends BaseController {
	
//	private final String COOKIE_MAPP_SESSION_ID = "MAPP_SESSION_ID";
//	
//	private final int COOKIE_MAX_AGE = 60 * 60 * 24 * 180 * 2;
	
	public final Log log = LogFactory.getLog(MappController.class);
	
	@Autowired
	private JsonClientHandler<YDDatapackage> jsonHandler;
	
	@Autowired
	private DealerDataService dealerDataService;
	
	@RequestMapping(value = {"/json"})
	@ResponseBody
	public String getJsonRsp(String msg, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{		
//		dealerDataService.updateProductInfoByFile("/Users/luyang/Desktop/Sales.xml");
		
//		String sessionid = getSessionId(request, response);
		
		MappContext.clearContext();
		Map<String,Object> attrMap = new HashMap<String, Object>(0);
		attrMap.put(MappContext.MAPPCONTEXT_REQUEST_IP,request.getRemoteHost());
		attrMap.put(MappContext.MAPPCONTEXT_SESSIONID, request.getSession().getId());
		attrMap.put(MappContext.MAPPCONTEXT_USER, request.getSession().getAttribute(MappConstant.MAPP_SESSION_USER));
		
		
		
		IUserinfo user = new UserInfo();
		user.setUserId(1L);
		user.setUserName("m01");
		attrMap.put(MappContext.MAPPCONTEXT_USER, user);
//		
//		IUserinfo u = new UserInfo();
//		u.setUserId(1l);
//		u.setUserName("m01");
//		
//		attrMap.put(MappContext.MAPPCONTEXT_USER, u);
		
		System.out.println("message is :"+msg);
		
		try
		{
			String ret = jsonHandler.doHandle(msg,attrMap);
			
			updateSession(request);
			
//			System.out.println(ret);
			
			return ret;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void updateSession(HttpServletRequest request) {
		
//		System.out.println("==================MappContext.context:"+MappContext.getContext().hashCode()+"======================");
		
		for(String key : MappConstant.sessionKeys)
		{
			if(MappContext.getAttribute(key) != null)
			{
				if(MappContext.getAttribute(key) instanceof IUserInfo)
				{
					System.out.println(((IUserInfo)MappContext.getAttribute(key)).getUserName()+"======"+((IUserInfo)MappContext.getAttribute(key)).getUser().getUserLoginName());
				}
				request.getSession().setAttribute(key, MappContext.getAttribute(key));
			}
		}
		
		for(String key : BSSConstantParam.PARAMKEYS)
		{
			if(MappContext.getAttribute(key) != null)
			{
				if(MappContext.getAttribute(key) instanceof IUserInfo)
				{
					System.out.println(((IUserInfo)MappContext.getAttribute(key)).getUserName()+"======"+((IUserInfo)MappContext.getAttribute(key)).getUser().getUserLoginName());
				}
				request.getSession().setAttribute(key, MappContext.getAttribute(key));
			}
		}
	}
	
//	private String getSessionId(HttpServletRequest request,HttpServletResponse response) throws Exception
//	{
//		String session_id = null;
//		Cookie[] cookies = request.getCookies();
//		if(cookies != null && cookies.length >0)
//		{
//			for(Cookie cookie : cookies)
//			{
//				if("mapp_session_id".equals(cookie.getName()))
//					session_id = cookie.getValue();
//			}
//		}
//		
//		System.out.println("client cookie = "+session_id);
//		
//		if(StringUtils.isBlank(session_id))
//		{
//			session_id = System.currentTimeMillis()+""+Math.random();
//			Cookie _cookie = new Cookie(COOKIE_MAPP_SESSION_ID, session_id);
//			_cookie.setMaxAge(COOKIE_MAX_AGE);
//			_cookie.setDomain(request.getServerName());// localhost
//			_cookie.setPath(request.getContextPath());// /dealer
//			response.addCookie(_cookie);
//			System.out.println("set new session_id = "+session_id);
//		}
//		
//		return session_id;
//	}
}
