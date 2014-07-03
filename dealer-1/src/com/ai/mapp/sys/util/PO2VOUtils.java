package com.ai.mapp.sys.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

import com.ailk.yd.mapp.tibco.model.YD0010.YD0010Request;

public class PO2VOUtils {
	
	public final static Log log = LogFactory.getLog(PO2VOUtils.class);
	
	/**
	 * PO转vo的过程，VO中的简单属性都赋值，Collection和数组不进行转化
	 * @param <T>
	 * @param dest
	 * @param orig
	 * @return
	 * @throws Exception
	 */
	public static <T> T po2voNoCollection(Class<T> clazz,Object orig, boolean onlyBase) throws Exception
	{
		if(orig == null) return null;
		
		T dest = clazz.newInstance();
		
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(clazz);
		
		for(PropertyDescriptor pd : pds)
		{
//			log.debug(pd.getName()+"--"+pd.getPropertyType().getName());
			
			if(pd.getPropertyType() == Class.class)
			{
				continue;
			}
			else if(Collection.class.isAssignableFrom(pd.getPropertyType()) || pd.getPropertyType().isArray())
			{
				continue;
			}
			else if(ClassUtils.ifBaseClass(pd.getPropertyType()) == false && onlyBase == false)
			{	
				PropertyUtils.setProperty(dest, pd.getName(), po2voNoCollection(pd.getPropertyType(),PropertyUtils.getProperty(orig, pd.getName()),onlyBase));
			}
			else if(ClassUtils.ifBaseClass(pd.getPropertyType()) && PropertyUtils.isWriteable(dest, pd.getName()))
			{
				PropertyUtils.setSimpleProperty(dest, pd.getName(), PropertyUtils.getProperty(orig, pd.getName()));
			}
			else
				continue;
			
		}
		
		return dest;
	}
	
	/**
	 * po转vo collection对象
	 * @param <T>
	 * @param origs
	 * @return
	 * @throws Exception
	 */
	public static <T> Collection<T> po2voCollection(Class<T> componentClass,Collection<T> origs,boolean onlyBase) throws Exception
	{
		if(origs == null || origs.isEmpty())
			return null;
		
		Collection<T> dest = null;
		
//		log.debug("origs.getClass():"+origs.getClass().getName());
//		log.debug("Set.class.isAssignableFrom(origs.getClass()):"+Set.class.isAssignableFrom(origs.getClass()));
//		log.debug("List.class.isAssignableFrom(origs.getClass()):"+List.class.isAssignableFrom(origs.getClass()));
		
		if(Set.class.isAssignableFrom(origs.getClass()) || origs instanceof org.hibernate.collection.PersistentSet)
		{
			dest = new HashSet<T>(0);
		}
		else if(List.class.isAssignableFrom(origs.getClass()) || origs instanceof org.hibernate.collection.PersistentList)
		{
			dest = new ArrayList<T>(0);
		}
		
		for(T o : origs)
		{
			if(o == null) continue;
			
			dest.add(po2voNoCollection(componentClass, o,onlyBase));
		}
		
		return dest;
	}
	
	/**
	 * po转vo 数组
	 * @param <T>
	 * @param origs
	 * @return
	 * @throws Exception
	 */
	public static <T> T[] po2voArray(Class<T> componentClass,T[] origs,boolean onlyBase) throws Exception
	{
		if(origs == null || origs.length == 0)
			return null;
		
		T[] dest = (T[])Array.newInstance(componentClass, origs.length);
		
		for (int i = 0; i < origs.length; i++) {
			dest[i] = po2voNoCollection(componentClass, origs[i],onlyBase);
		}
		
		return dest;
	}
	
	/**
	 * PO转vo的过程，VO中的简单属性都赋值，Collection和数组不进行转化
	 * @param <T>
	 * @param dest
	 * @param orig
	 * @return
	 * @throws Exception
	 */
	public static <T> T po2voNoCollection(Class<T> clazz,Object orig, boolean onlyBase,Collection<String> except) throws Exception
	{
		if(orig == null) return null;
		
		T dest = clazz.newInstance();
		
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(clazz);
		
		for(PropertyDescriptor pd : pds)
		{
//			log.debug(pd.getName()+"--"+pd.getPropertyType().getName());
			
			if(Collection.class.isAssignableFrom(pd.getPropertyType()) 
					|| pd.getPropertyType().isArray() 
					|| pd.getPropertyType() == Class.class
					|| (except != null && except.contains(pd.getName())))
			{
				continue;
			}
			else if(ClassUtils.ifBaseClass(pd.getPropertyType()) == false && onlyBase == false)
			{	
				PropertyUtils.setProperty(dest, pd.getName(), po2voNoCollection(pd.getPropertyType(),PropertyUtils.getProperty(orig, pd.getName()),onlyBase));
			}
			else if(ClassUtils.ifBaseClass(pd.getPropertyType()) && PropertyUtils.isWriteable(dest, pd.getName()))
			{
				PropertyUtils.setSimpleProperty(dest, pd.getName(), PropertyUtils.getProperty(orig, pd.getName()));
			}
			else
				continue;
			
		}
		
		return dest;
	}
	
	
	//Tibco 初始化使用，由于其对象为null
	public static <T> T replaceNull(T dest) throws Exception
	{
		Class<?> clazz = dest.getClass();
		
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(clazz);
		
		for(PropertyDescriptor pd : pds)
		{
			if(pd.getPropertyType() == Class.class)
			{
				continue;
			}
			else if(List.class.isAssignableFrom(pd.getPropertyType()))
			{
				//List类型
				List listValue = (List)PropertyUtils.getProperty(dest, pd.getName());
				
				if(listValue  == null)
					listValue = new ArrayList(0);
				
				if(listValue != null && listValue.isEmpty() == false)	
				{
					for(Object o : (List)PropertyUtils.getProperty(dest, pd.getName()))
					{
						replaceNull(o);
					}
				}
				PropertyUtils.setProperty(dest, pd.getName(), listValue);
				
			}
			else if(Set.class.isAssignableFrom(pd.getPropertyType()))
			{
				//Set类型
				Set setValue = (Set)PropertyUtils.getProperty(dest, pd.getName());
				
				if(setValue  == null)
					setValue = new HashSet(0);
				
				if(setValue != null && setValue.isEmpty() == false)	
				{
					for(Object o : (Set)PropertyUtils.getProperty(dest, pd.getName()))
					{
						o = replaceNull(o);
					}
				}
				
				PropertyUtils.setProperty(dest, pd.getName(), setValue);
				
			}
			else if(pd.getPropertyType().isArray())
			{
				//数组类型
				if(PropertyUtils.getProperty(dest, pd.getName())  == null){
					PropertyUtils.setProperty(dest, pd.getName(), Array.newInstance(pd.getPropertyType().getComponentType(), 0));
					continue;
				}
				
				//TODO 数组的去null
//				else
//				{
//					for(Object o : PropertyUtils.getProperty(dest, pd.getName()))
//					{
//						o = replaceNull(o);
//					}
//				}
				
			}
			else if(Map.class.isAssignableFrom(pd.getPropertyType()))
			{
				//Map类型
				Map mapValue = (Map)PropertyUtils.getProperty(dest, pd.getName());
				if(mapValue == null)
					mapValue = new HashMap(0);
				
				if(mapValue != null && mapValue.isEmpty() == false){
					
					for(Object key : mapValue.keySet())
					{
						replaceNull(mapValue.get(key));
					}
				}
				PropertyUtils.setProperty(dest, pd.getName(), mapValue);
				
			}
			else if(pd.getPropertyType() == java.lang.String.class)
			{
				//字符串类型类型
				PropertyUtils.setProperty(dest, pd.getName(), PropertyUtils.getProperty(dest, pd.getName())==null?"":PropertyUtils.getProperty(dest, pd.getName()));
			}
			else if(ClassUtils.ifBaseClass(pd.getPropertyType()) == false)
			{	
				//复杂对象
				Object value = PropertyUtils.getProperty(dest, pd.getName());
				if(value == null)
					value = pd.getPropertyType().newInstance();
				
				PropertyUtils.setProperty(dest, pd.getName(), replaceNull(value));
			}
			else
				continue;
			
		}
		
		return dest;
	}
	
//	public static <T> T po2voSimpleProperty(Class<T> clazz,Object orig) throws Exception
//	{
//		if(orig == null) return null;
//		
//		T dest = clazz.newInstance();
//		
//		PropertyUtils.copyProperties(dest, orig);
//		
//		return dest;
//		
//	}
//	
//	public static <T> Collection<T> po2voSimpleCollection(Class<T> componentClass,Collection<T> origs) throws Exception
//	{
//		if(origs == null || origs.isEmpty())
//			return null;
//		
//		Collection<T> dest = null;
//		
//		if(Set.class.isAssignableFrom(origs.getClass()))
//		{
//			dest = new HashSet<T>(0);
//		}
//		else if(List.class.isAssignableFrom(origs.getClass()))
//		{
//			dest = new ArrayList<T>(0);
//		}
//		
//		for(T o : origs)
//		{
//			dest.add(po2voSimpleProperty(componentClass, o));
//		}
//		
//		return dest;
//	} 
	
	
	public static final void main(String[] args) throws Exception
	{
		YD0010Request req = PO2VOUtils.replaceNull(new YD0010Request());
		
		System.out.println(new ObjectMapper().writeValueAsString(req));
//		
//		
	}
	
}
