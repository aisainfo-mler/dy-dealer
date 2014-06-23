package com.ai.mapp.sys.util;

/**
 * 
 * java类获取web应用的根目录
 * 
 */
public class WebPathUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		WebPathUtil p = new WebPathUtil();
		System.out.println("Web Class Path = " + p.getWebClassesPath());
		System.out.println("WEB-INF Path = " + p.getWebInfPath());
		System.out.println("WebRoot Path = " + p.getWebRoot());
	}

	public String getWebClassesPath() {
		String path = getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		return path;

	}

	public String getWebInfPath() throws IllegalAccessException {
		String path = getWebClassesPath();
		if (path.indexOf("WEB-INF") > 0) {
			path = path.substring(0, path.indexOf("WEB-INF") + 8);
		} else {
			throw new IllegalAccessException("路径获取错误");
		}
		return path;
	}

	public String getWebRoot() throws IllegalAccessException {
		String path = getWebClassesPath();
		if (path.indexOf("WEB-INF") > 0) {
			path = path.substring(0, path.indexOf("WEB-INF/classes"));
		} else {
			throw new IllegalAccessException("路径获取错误");
		}
		return path;
	}
}
