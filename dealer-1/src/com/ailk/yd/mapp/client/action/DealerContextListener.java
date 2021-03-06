package com.ailk.yd.mapp.client.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
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
	@Autowired
	private DealerDataService dealerDataService;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void loadCert() {
		String path = this.getClass().getResource("/").getPath();
		try {
			System.out.println(new URLDecoder().decode(path, "utf-8"));
			path = new URLDecoder().decode(path, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f = new File(path + "/tibco.cer");
		System.setProperty("javax.net.ssl.trustStore", f.getPath());
		System.setProperty("javax.net.ssl.trustStorePassword", "qiansh");

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		loadCert();

		WebApplicationContext wac = WebApplicationContextUtils
				.getWebApplicationContext(arg0.getServletContext());

		DataImpService dis = wac.getBean(DataImpService.class);
		dis.cacheDataStartUp();
		cacheDictData(wac);

	}

	/**
	 * 应用启动的时候加载一次静态数据
	 * 
	 * @param wac
	 */
	private void cacheDictData(WebApplicationContext wac) {
		SysPropService sps = wac.getBean(SysPropService.class);
		SysProp param = new SysProp();
		// param.setParentKey("TIBCO");
		Collection mmm = sps.listProp(param);
		TibcoCache.dicts = new HashMap();
		for (Iterator it = mmm.iterator(); it.hasNext();) {
			SysProp prop = (SysProp) it.next();
			prop.setValid("1");
			if (StringUtils.isBlank(prop.getParentKey())) {
				continue;
			}
			String mapKey = prop.getParentKey();
			// mapKey = mapKey.replaceAll("TIBCOPARAM_", "");
			if (!TibcoCache.dicts.containsKey(mapKey)) {
				Map m = new HashMap();
				m.put(prop.getKey(), prop.getName());
				TibcoCache.dicts.put(mapKey, m);
			} else {
				((Map) TibcoCache.dicts.get(mapKey)).put(prop.getKey(),
						prop.getName());
			}
		}
	}

}
