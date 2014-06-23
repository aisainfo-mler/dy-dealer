package com.ai.mapp.sys.util;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ClassUtils {

	public final static Log log = LogFactory.getLog(ClassUtils.class);
	
	public final static HashMap<String, Class<?>> classMap = new HashMap<String,Class<?>>(0);
	
	static
	{
		classMap.put("int", int.class);
		classMap.put("boolean", boolean.class);
		classMap.put("char", char.class);
		classMap.put("byte", byte.class);
		classMap.put("short", short.class);
		classMap.put("int", int.class);
		classMap.put("long", long.class);
		classMap.put("float", float.class);
		classMap.put("double", double.class);
		
		classMap.put("int[]", int[].class);
		classMap.put("boolean[]", boolean[].class);
		classMap.put("char[]", char[].class);
		classMap.put("byte[]", byte[].class);
		classMap.put("short[]", short[].class);
		classMap.put("int[]", int[].class);
		classMap.put("long[]", long[].class);
		classMap.put("float[]", float[].class);
		classMap.put("double[]", double[].class);
	}
	
	/**
	 * 根据名称获取Class对象
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static Class<?> ClassforName(String name) throws Exception
	{
		if(classMap.containsKey(name))
		{
			return classMap.get(name);
		}
		
		return Class.forName(name);
	}
	
	/**
	 * 获取fieldName代表的属性的类型
	 * @param clazz
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public static Class<?> getClass(Class<?> clazz,String fieldName) throws SecurityException, NoSuchFieldException
	{
		log.info("获取属性类型："+clazz.getName()+",子属性"+fieldName);
		
		if(fieldName.indexOf(".") == -1)
		{
			return clazz.getDeclaredField(fieldName).getType();
		}
		
		String name = fieldName.substring(0,fieldName.indexOf("."));
		
		String new_fieldName = fieldName.substring(fieldName.indexOf(".")+1, fieldName.length());
		
		log.debug("获取"+clazz.getName()+"."+name+": ");
		Class<?> c = clazz.getDeclaredField(name).getType();
		log.debug(c.getName()+",新属性: "+new_fieldName);
		
		return getClass(c,new_fieldName);
	}
	

	/**
	 * 判断是否基础类
	 * @param clz
	 * @return
	 */
    public static boolean isWrapClass(Class<?> clz) { 
        try { 
           return ((Class<?>) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) { 
            return false; 
        } 
    } 
    
    /**
	 * 判断是否需要初始化，基础类型不进行初始化
	 * @param clazz
	 * @return
	 */
	public static boolean ifBaseClass(Class<?> clazz){
		
		return ClassUtils.classMap.containsValue(clazz) || ClassUtils.isWrapClass(clazz) || clazz == java.lang.String.class ;
		
	}
    
	public static final void main(String[] args)
	{
		
	}
	
}
