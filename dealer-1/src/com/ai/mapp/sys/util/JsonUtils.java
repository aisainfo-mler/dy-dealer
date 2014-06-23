package com.ai.mapp.sys.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-5 下午05:18:28
 * 类说明:json 转换
 */

public class JsonUtils {
	/**
	 * <p>描述:将java对象转换成json字符串  不含空串  如："itemOutSerialNo":""</p> 
	 * @param bean
	 * @return  
	 * @author        Zhengwj
	 * @Date          2013-11-19 下午08:02:00
	 */
	 public static String beanToJsonExceptNull(Object bean) {

	     JSONObject jsonO = JSONObject.fromObject(bean);
	     Iterator it = jsonO.keys();  
	     List<String> discardKeys = new ArrayList<String>(); 
	     if(it != null){
	    	 while (it.hasNext()) {  
	    		  
	             String key = it.next().toString();  
	   
	             // 将所有的空串去掉  
	             if (StringUtils.isEmpty(jsonO.getString(key))) {  
	            	 discardKeys.add(key);
	             }  
	    	 }
	     }

	     if(discardKeys.size() != 0){
	    	 for(String key:discardKeys){
	    		 jsonO.discard(key);
	    	 }
	     }
	     return jsonO.toString();

	}
	 
	 /**
	  * <p>描述: 将java对象List集合转换成json字符串 </p> 
	  * @param beans
	  * @return  
	  * @author        Zhengwj
	  * @Date          2013-11-19 下午08:05:15
	  */
	 public static String beanListToJson(List beans) {

	     StringBuffer rest = new StringBuffer();

	     rest.append("[");

	     int size = beans.size();

	     for (int i = 0; i < size; i++) {

	         rest.append(beanToJsonExceptNull(beans.get(i))+((i<size-1)?",":""));

	     }
	     rest.append("]");
	     return rest.toString();

	    }
	 
	 /**
		 * <p>描述:从json对象集合表达式中得到一个java对象列表 </p> 
		 * @param <T>
		 * @param jsonString
		 * @param beanClass
		 * @return  
		 * @author        Zhengwj
		 * @Date          2013-11-19 下午07:43:26
		 */
		public static <T> List<T> jsonToBeanList(String jsonString, Class<T> beanClass) {

		     JSONArray jsonArray = JSONArray.fromObject(jsonString);

		     JSONObject jsonObject;

		     T bean;

		     int size = jsonArray.size();

		     List<T> list = new ArrayList<T>(size);

		     for (int i = 0; i < size; i++) {

		         jsonObject = jsonArray.getJSONObject(i);

		         bean = (T) JSONObject.toBean(jsonObject, beanClass);

		         list.add(bean);

		     }
		     return list;

		 }
		
		/**
		 * <p>描述: 从一个JSON 对象字符格式中得到一个java对象</p> 
		 * @param <T>
		 * @param jsonString
		 * @param beanCalss
		 * @return  
		 * @author        Zhengwj
		 * @Date          2014-5-13 上午09:03:41
		 */
		@SuppressWarnings("unchecked")
		public static <T> T jsonToBean(String jsonString, Class<T> beanCalss) {

		    JSONObject jsonObject = JSONObject.fromObject(jsonString);

		    T bean = (T) JSONObject.toBean(jsonObject, beanCalss);

		    return bean;

		 }
		
		public static void main(String[] args) {
			Map<String, String> test = new HashMap<String, String>();
			test.put("kjl", "jljljlkjl");
			test.put("kkk", "oioioiooo");
			System.out.println(beanToJsonExceptNull(test));
		}
}
