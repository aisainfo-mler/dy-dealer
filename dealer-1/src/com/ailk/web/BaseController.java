/**
 * 
 */
package com.ailk.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ailk.butterfly.sys.RequestContext;
import com.ailk.butterfly.sys.dal.ibatis.model.IUserInfo;

/**
 * @author yangqx
 *
 */
public abstract class BaseController {
	
	/**
	 * 获取当前用户的ID
	 * @return
	 */
	protected int currentUserId(){
		return  RequestContext.getUser().getUserId();
	}
	
	protected IUserInfo currentUser(){
		return RequestContext.getUser();
	}
	
	public String getParam(HttpServletRequest request,String name) {
		
		String method=request.getMethod();
		try {
			if("PUT".equals(method)) {
				return new String(request.getParameter(name).getBytes("iso8859-1"),"UTF-8");
			}else {
				return request.getParameter(name);
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	 

	public class JsonObject {
		private int total;
		private boolean success;
		private String message;
		
		private Object data;
		
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
		public JsonObject() {}
		public JsonObject(List data) {
			this(true,data);
		}
		public JsonObject(boolean success,List data) {
			if(data != null) {
				this.total=data.size();
				this.data=data;
				this.success=success;
			}
		}
		
		public JsonObject(boolean success,String msg) {
			this.success =success;
			this.message=msg;
		}
		public JsonObject(boolean success,String msg,Object data) {
			this.success =success;
			this.message=msg;
			this.data=data;
		}
		
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		public Object getData() {
			return data;
		}
		public void setData(Object data) {
			this.data = data;
		}

	}
	
	public class KeyValueSet extends HashMap {

		public KeyValueSet() {
			super();
			// TODO Auto-generated constructor stub
		}

		public KeyValueSet(String keyproperty,String key,String valueproperty,String value) {
			super();
			// TODO Auto-generated constructor stub
			this.put(keyproperty, key);
			this.put(valueproperty, value);
		}
		public KeyValueSet(String key,String value) {
			this("key",key,"value",value);
		}

		
	}
}
