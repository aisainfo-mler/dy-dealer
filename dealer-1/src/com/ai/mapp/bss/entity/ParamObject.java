package com.ai.mapp.bss.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.ai.mapp.bss.util.BSSConstantParam;

public class ParamObject implements IParam
{
	private Map<String,Object>	params;
	private Object result;
	private Map<String,Object> sessionMap;
	private Set<String> errors;
	private boolean ifSuccess = false;
	private boolean ifUpdate;
	
	public Map<String, Object> getParams() {
		return params;
	}
	
	public ParamObject() throws Exception
	{
		setParameter(BSSConstantParam.EH2_CHECK, "0");
		setParameter(BSSConstantParam.EH2_CHECKCODE, "0000000000000000");
		setParameter(BSSConstantParam.EH2_COMPRESS, "0");
		setParameter(BSSConstantParam.EH2_ENCRYPT, "0");
		setParameter(BSSConstantParam.EH2_VERSION, "11");
		setParameter(BSSConstantParam.EH2_SVCTYPE, "");
		
	}
	
	public Set<String> getErrors() {
		return errors;
	}

	public void setErrors(Set<String> errors) {
		this.errors = errors;
	}

	public boolean isIfSuccess() {
		return ifSuccess;
	}

	public void setIfSuccess(boolean ifSuccess) {
		this.ifSuccess = ifSuccess;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	public Object getResult() {
		return result;
	}
	
	public void setResult(Object result) {
		this.result = result;
	}
	
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}
	
	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	public boolean isIfUpdate() {
		return ifUpdate;
	}
	
	public void setIfUpdate(boolean ifUpdate) {
		this.ifUpdate = ifUpdate;
	}
	
	public Object getParameter(String key) throws Exception {
		if(getParams() == null || getParams().isEmpty())
			return null;
		
		return getParams().get(key);
	}
	
	public void setParameter(String key, Object value) throws Exception {
		if(getParams() == null)
			params = new HashMap<String, Object>(0);
		
		params.put(key, value);
	}
	
	/**
	 * 设置需要更新的session属性
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public void setSessionAttr(String key, Object value) throws Exception {
		if(getSessionMap() == null)
			sessionMap = new HashMap<String, Object>(0);
		
		sessionMap.put(key, value);
	}
	
	/**
	 * 通过reuest初始化对象参数
	 * @param request
	 * @throws Exception
	 */
	public void init(HttpSession session) throws Exception 
	{
		if(session == null){
			
			for(String key : BSSConstantParam.PARAMKEYS)
			{
				setParameter(key, null);
			}
			
			return;
		}
		
		for(String key : BSSConstantParam.PARAMKEYS)
		{
			setParameter(key, session.getAttribute(key));
		}
		
		for(String key : BSSConstantParam.PARAMKEYS)
		{
			setSessionAttr(key, session.getAttribute(key));
		}
		
	}

	/**
	 * 更新session
	 * @param session
	 * @throws Exception
	 */
	public void updateSession(HttpSession session) throws Exception
	{
		if(ifUpdate == false || sessionMap == null || sessionMap.isEmpty())
			return;
		
		for(String key : sessionMap.keySet())
		{
			session.setAttribute(key, sessionMap.get(key));
		}
	}
	
	public void addError(String errorInfo) {
		
		if(errors == null)
			errors = new HashSet<String>(0);
		
		errors.add(errorInfo);
	}
	
	public String getErrorInfo()
	{
		if(errors == null || errors.isEmpty())
			return "";
		
		return errors.iterator().next();
		
	}
	
	
	public void clear(){
		result = null;
	}
}
