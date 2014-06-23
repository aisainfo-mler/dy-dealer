package com.ai.mapp.base.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InterPropertiesUtils {
	public static final String DEFAULT_CONFIG = "/inter.properties";
	public static final String VAC_VACWEBSERVICE_URL = "vac.vacWebServiceUrl";
	public static final String VAC_CRMWEBSERVICE_URL_4_TEST = "var.crmWebServiceUrl4Test";
	private static Properties config;
	private static final Log logger = LogFactory.getLog(InterPropertiesUtils.class);
			
	static {
		config = new Properties();
		try{
			InputStream is = InterPropertiesUtils.class.getResourceAsStream(DEFAULT_CONFIG);
            if (is != null) {
                config.load(is);
                logger.debug("获取接口配置文件"+DEFAULT_CONFIG+"信息:\n"+config);
            }else{
            	logger.error("未配置接口配置文件"+DEFAULT_CONFIG+" !webservice接口可能调用不正确");
            }
		}catch(Exception e){
			logger.error("接口配置文件"+DEFAULT_CONFIG+" 读取异常!webservice接口可能调用不正确",e);
		}
	}
	
	public static String getProperty(String key) {
		String value = config.getProperty(key);
        return value == null ? value : value.trim();
	}
}
