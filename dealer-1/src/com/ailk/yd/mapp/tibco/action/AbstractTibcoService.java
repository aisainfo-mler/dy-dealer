package com.ailk.yd.mapp.tibco.action;

import java.util.Iterator;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractTibcoService<Req,Rsp> {
	
	protected ObjectMapper mapper = new ObjectMapper();

	protected Logger logger = LoggerFactory.getLogger(AbstractTibcoService.class);
	
	@Autowired
	protected TibcoHandler tibcoHandler;
	
	public Rsp post2Tibco(Req request,Map<String,?> paramters) throws Exception {
		return post2Tibco(request, paramters, true);
	}
	public Rsp post2Tibco(Req request,Map<String,?> paramters,boolean autoCheck) throws Exception {
		try{
			String json = convertRequest(request);
			System.out.println("tibco request:"+json);
			String rsp_string = tibcoHandler.sendMsg(getTibcoUrl(), json, paramters,null,true);
			System.out.println(rsp_string);
			if(autoCheck==true)
				checkSucc(rsp_string);
			return convertResponse(rsp_string);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		
	}
	
	
	
	public Rsp get2Tibco(Map<String,?> paramters) throws Exception {
		return get2Tibco(paramters, true);
	}
	
	public Rsp get2Tibco(Map<String,?> paramters,boolean autoCheck) throws Exception {
		try
		{
			String rsp_string = tibcoHandler.sendMsg(getTibcoUrl(), null , paramters,null,false);
			if(autoCheck==true){
				checkSucc(rsp_string);
			}
			return convertResponse(rsp_string);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
//			throw new Exception("Sorry. This service is temporarily unavailable. Please try again later.");
			throw e;
		}
	}

	/**
	 * 判断返回的串是否失败。是的话就直接抛出异常
	 * @param rsp_string
	 * @throws Exception
	 */
	private void checkSucc(String rsp_string) throws Exception {
		
	try {
			Map jo = mapper.readValue(rsp_string,Map.class);
			if (jo !=null && jo.get("success") != null) 
			{
				Map errMsg = (Map)jo.get("errors");
				if(errMsg.isEmpty() == false){
					Iterator<String> it=errMsg.keySet().iterator();    
//					while(it.hasNext()){    
//					     String key = ;    
//					     value=map.get(key);    
//					}
					throw new Exception((String)(errMsg.get(it.next())));//默认取第一个错误
				}
				
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public abstract String getTibcoUrl();
	
	

	abstract protected Rsp convertResponse(String json) throws Exception ;
	
	protected String convertRequest(Req request) throws Exception
	{
		return mapper.writeValueAsString(request);
	}
	
//	/**
//	 * PO转vo的过程，VO中的简单属性都赋值
//	 * @param <T>
//	 * @param dest
//	 * @param orig
//	 * @return
//	 * @throws Exception
//	 */
//	public static <T> T po2voNoCollection(T orig) throws Exception
//	{
//		Class<?> clazz = orig.getClass();
//		
//		if(orig == null) return null;
//		
//		T dest = (T)clazz.newInstance();
//		
//		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(clazz);
//		
//		for(PropertyDescriptor pd : pds)
//		{
//			if(pd.getPropertyType() == Class.class)
//			{
//				continue;
//			}
//			else if(Collection.class.isAssignableFrom(pd.getPropertyType()) || pd.getPropertyType().isArray())
//			{
//				continue;
//			}
//			else if(ClassUtils.ifBaseClass(pd.getPropertyType()) == false && onlyBase == false)
//			{	
//				PropertyUtils.setProperty(dest, pd.getName(), po2voNoCollection(pd.getPropertyType(),PropertyUtils.getProperty(orig, pd.getName()),onlyBase));
//			}
//			else if(ClassUtils.ifBaseClass(pd.getPropertyType()) && PropertyUtils.isWriteable(dest, pd.getName()))
//			{
//				PropertyUtils.setSimpleProperty(dest, pd.getName(), PropertyUtils.getProperty(orig, pd.getName()));
//			}
//			else
//				continue;
//			
//		}
//		
//		return dest;
//	}
	
	
}
