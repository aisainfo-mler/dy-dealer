package com.ailk.yd.mapp.model;

import java.beans.PropertyDescriptor;

import org.apache.commons.beanutils.PropertyUtils;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import com.ailk.butterfly.mapp.core.model.IBody;

/**
 * 分装IBody接口，重写toString方法
 * @author mler
 *
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public abstract class YDBody extends IBody {

	public String toString() 
	{
		String ret = "";
		
		try{
			
			StringBuffer string = new StringBuffer("");
			
			PropertyDescriptor[] props = PropertyUtils.getPropertyDescriptors(this.getClass());
			
			if(props == null || props.length==0)
			{
				return this.getClass().getName() + "[ ]";
			}
			
			for(PropertyDescriptor prop : props)
			{
				if(prop.getPropertyType() == java.lang.Class.class)
					continue;

				string.append(prop.getName() + "=" + PropertyUtils.getProperty(this, prop.getName()) + ", ");
			}
			
			ret = string.toString().substring(0, string.toString().lastIndexOf(","));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.getClass().getName() + "[ "+ret+" ] ";
	}
	
}
