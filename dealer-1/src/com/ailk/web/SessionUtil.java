package com.ailk.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ailk.butterfly.core.security.session.ISession;
import com.ailk.butterfly.core.security.session.SessionManager;


public class SessionUtil {

	private static final String SESSION_COOKIE_NAME = "SID";
	private static final String SESSION_URL_PARAM   = "sid";
	private static final String SESSION_COOKIE_PATH = "/";
	
	public static final String SESSION_USER_INFO_KEY="__USERINFO__";
	
	private static final String COOKIE_USER_ID="u";
	private static final String COOKIE_ENCRYPT_PWD="p";
	
	private static final String COOKIE_K_REMEBER_LOGIN="s";
	
	private static final String COOKIE_V_REMEBER_LOGIN="1";
	
	public static ISession getSession(HttpServletRequest request,HttpServletResponse response) {
    	String requestedSessionId=null;
    	if (requestedSessionId == null) {
			requestedSessionId = parseSessionIdFromCookie( request);
		}
		if (requestedSessionId == null) {
			requestedSessionId = parseSessionIdFromUri( request);
		}
		if (requestedSessionId == null) {
			requestedSessionId =request.getParameter(SESSION_URL_PARAM);
		}
		ISession  session= null;
			
		if (requestedSessionId != null) {
			session=SessionManager.getSessionById(requestedSessionId);
			if(session == null) {
				session=SessionManager.createNewSession(); 
				setCookieValue(response,SESSION_COOKIE_NAME,session.getId());
			}
		}else {
			session=SessionManager.createNewSession(); 
			setCookieValue(response,SESSION_COOKIE_NAME,session.getId());
		}
		return session;
	}
	
	public static String parseSessionIdFromCookie(HttpServletRequest request) {
	        return getCookieValue(request,SESSION_COOKIE_NAME);
		}
	    
	    
	public static String parseSessionIdFromUri(HttpServletRequest request) {
			String id = null;
			String uri = request.getRequestURI();
	        
	        int semi = uri.lastIndexOf(';');
	        if (semi >= 0) {
	            String pathParams = uri.substring(semi + 1);
	            
	            // check if there is a url encoded session param.
	            String param = SESSION_URL_PARAM;
	            if (param != null && pathParams != null && pathParams.startsWith(param) && pathParams.length() > param.length() + 1) {
	                id = pathParams.substring(param.length() + 1);
	            }
	        }
	        return id;
		}
	    
	public static String getCookieValue(HttpServletRequest request,String name) {
	    	return getCookieValue(request, name, null);
	    }
	    
	public static String getCookieValue(HttpServletRequest request,String name,String path) {
	    	Cookie[] cookies = request.getCookies();
	    	
	    	if (cookies != null && cookies.length > 0) {
	    		for(Cookie c:cookies) {
	    			if(name.equals(c.getName())) {
	    				return c.getValue();
	    			}
	    		}
	    	}
	    	return null;
	    }
	    
	public static boolean setCookieValue(HttpServletResponse response,String cookieName,String value) {
	    	return setCookieValue(response, cookieName, value, SESSION_COOKIE_PATH);
	    }
	public static boolean setCookieValue(HttpServletResponse response,String cookieName,String value,String path) {
	    	Cookie cookie = new Cookie(cookieName,  value);
			cookie.setPath(path);
			response.addCookie(cookie);
			
	    	return true;
	    }
	    
	public static boolean remvoeCookie(HttpServletResponse response,String cookieName,String path) {
	    	Cookie cookie = new Cookie(cookieName, null); 
	    	cookie.setMaxAge(0);
	    	cookie.setPath(path);
	    	response.addCookie(cookie);
	    	return true;
	    }
}
