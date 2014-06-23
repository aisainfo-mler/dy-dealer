package com.ailk.yd.mapp.client.action;

import java.io.File;
import java.util.Collection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ai.mapp.sys.entity.HwCircle;
import com.ai.mapp.sys.entity.HwCity;
import com.ai.mapp.sys.entity.HwCountry;
import com.ai.mapp.sys.entity.HwDistrict;
import com.ai.mapp.sys.entity.HwState;
import com.ai.mapp.sys.service.DataImpService;
import com.ai.mapp.sys.service.DealerDataService;
import com.ai.mapp.sys.service.HwCircleService;
import com.ai.mapp.sys.service.HwCityService;
import com.ai.mapp.sys.service.HwCountryService;
import com.ai.mapp.sys.service.HwDistrictService;
import com.ai.mapp.sys.service.HwStateService;
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
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		loadCert();
		initProductProp();
		
		WebApplicationContext wac=WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		
		DataImpService dis = wac.getBean(DataImpService.class);
		
		long start = System.currentTimeMillis();
		dis.cacheDataStartUp();
		System.err.println((System.currentTimeMillis()-start)+"country***************************************************************");
		
	}

	
}
