package com.ailk.util;

import java.lang.reflect.Field;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.ailk.yd.mapp.client.model.HW0006Request;
import com.ailk.yd.mapp.tibco.model.YD0006.YD0006Request;

/**
 * 
 * @author qiansh
 * 
 *         2013-6-25 TODO
 */
public class SetUtil {

	private Object from;
	private Object to;
	static Callback defaultCb = new Callback() {
		@Override
		public Object callback(Object obj) {
			return obj;
		}
	};

	public SetUtil(Object from, Object to) {
		this.from = from;
		this.to = to;
//		this.copyAllSameNameProp();
	}
	
	public void copyAllSameNameProp(){
		Field[] fromFields = from.getClass().getDeclaredFields();
		Field[] toFields = to.getClass().getDeclaredFields();
		for (int i = 0; i < fromFields.length; i++) {
			Field fromField = fromFields[i];
			for (int j = 0; j < toFields.length; j++) {
				Field toField = toFields[j];
				if(StringUtils.equals(fromField.getName(), toField.getName())){
					this.copyProp(fromField.getName());
				}
			}
		}
	}

	public static void copyProp(Object from, Object to, String fromFieldName,
			String toFieldName, Object defaultVal) {
		SetUtil su = new SetUtil(from, to);
		su.copyProp(fromFieldName, toFieldName, defaultVal, defaultCb);
	}

	public static void copyProp(Object from, Object to, String fromFieldName,
			String toFieldName) {
		copyProp(from, to, fromFieldName, toFieldName, null);
	}

	public void copyProp(String fromFieldName, String toFieldName,
			Object defaultVal, Callback cb) {
		if (from == null || to == null) {
			throw new RuntimeException("对象为null。。");
		}
		try {
			Field fromField = from.getClass().getDeclaredField(fromFieldName);
			fromField.setAccessible(true);
			Field toField = to.getClass().getDeclaredField(toFieldName);
			toField.setAccessible(true);
			Object fromVal = fromField.get(this.from);
			fromVal = fromVal == null ? defaultVal : fromVal;
			if (cb != null) {
				fromVal = cb.callback(fromVal);
			}
			toField.set(this.to, fromVal);
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new RuntimeException("属性设置出错！！" + e.getMessage());
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			throw new RuntimeException("属性设置出错！！" + e.getMessage());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException("属性设置出错！！" + e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("属性设置出错！！" + e.getMessage());
		}
	}

	public void copyProp(String fromFieldName, String toFieldName) {
		this.copyProp(fromFieldName, toFieldName, null, defaultCb);
	}

	public void copyProp(String fromFieldName, String toFieldName, Callback cb) {
		this.copyProp(fromFieldName, toFieldName, null, cb);
	}

	public void copyProp(String sameFieldName) {
		this.copyProp(sameFieldName, sameFieldName, null, defaultCb);
	}

	public void copyProp(String sameFieldName, Callback cb) {
		this.copyProp(sameFieldName, sameFieldName, null, cb);
	}

	public void copyProp(String sameFieldName, Object defaultVal) {
		this.copyProp(sameFieldName, sameFieldName, defaultVal, defaultCb);
	}

	public static void main(String[] args) {
		HW0006Request r = new HW0006Request();
		r.setAddress("address");
		r.setBirthDay("yyyymmdd");
		r.setCity("hangzhou");
		r.setDealerId(998l);
		r.setIdCardType("idy");
		YD0006Request yd6 = new YD0006Request();
		new SetUtil(r,yd6).copyAllSameNameProp();
		String s = ObjectUtils.toString(yd6);
		System.err.println(s);
	}
}
