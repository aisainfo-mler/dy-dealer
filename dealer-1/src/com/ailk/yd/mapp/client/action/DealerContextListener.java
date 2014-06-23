package com.ailk.yd.mapp.client.action;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ai.mapp.sys.entity.SysProp;
import com.ai.mapp.sys.service.DataImpService;
import com.ai.mapp.sys.service.DealerDataService;
import com.ai.mapp.sys.service.HwCircleService;
import com.ai.mapp.sys.service.HwCityService;
import com.ai.mapp.sys.service.HwCountryService;
import com.ai.mapp.sys.service.HwDistrictService;
import com.ai.mapp.sys.service.HwStateService;
import com.ai.mapp.sys.service.SysPropService;
import com.ailk.yd.mapp.tibco.TibcoCache;
import com.ailk.yd.mapp.tibco.TibcoConstant;
import com.ailk.yd.mapp.tibco.util.TibcoUtil;

public class DealerContextListener implements ServletContextListener {

	@Autowired
	private HwCountryService hwCountryService;
	@Autowired
	private HwStateService hwStateService;
	@Autowired
	private HwCityService hwCitySerice;
	@Autowired
	private HwCircleService hwCircleService;
	@Autowired
	private HwDistrictService hwDirstrictService;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	private void loadCert(){
		String path = TibcoUtil.class.getResource("/").getPath();
		File f = new File(path+"/tibco.cer");
		System.setProperty("javax.net.ssl.trustStore", f.getPath());
		System.setProperty("javax.net.ssl.trustStorePassword", "qiansh");
	
	}
	
	private void initProductProp()
	{
		try
		{
			String path = TibcoUtil.class.getResource("/").getPath();
			File file = new File(path+"/tibco_product.xml");
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(file);
			TibcoConstant.productPropMap = DealerDataService.handlePropMap(document.getRootElement());
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		loadCert();
		initProductProp();
		
		WebApplicationContext wac=WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		
		DataImpService dis = wac.getBean(DataImpService.class);
		dis.cacheDataStartUp();
		cacheDictData(wac);
		
	}

	/**
	 * 应用启动的时候加载一次静态数据
	 * @param wac
	 */
	private void cacheDictData(WebApplicationContext wac) {
		SysPropService sps = wac.getBean(SysPropService.class);
		SysProp param = new SysProp();
		param.setParentKey("TIBCO");
		Collection mmm = sps.listProp(param);
		TibcoCache.dicts = new HashMap();
		for (Iterator it = mmm.iterator(); it.hasNext();) {
			SysProp prop = (SysProp) it.next();
			String mapKey = prop.getName();
			mapKey = mapKey.replaceAll("TIBCOPARAM_", "");
			if(!TibcoCache.dicts.containsKey(mapKey)){
				Map m = new HashMap();
				m.put(prop.getKey(), prop.getRemark());
				TibcoCache.dicts.put(mapKey , m);
			}else{
				((Map)TibcoCache.dicts.get(mapKey)).put(prop.getKey(), prop.getRemark());
			}
		}
	}

	
}
