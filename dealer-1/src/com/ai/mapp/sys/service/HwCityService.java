package com.ai.mapp.sys.service;

import java.util.Collection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ai.mapp.sys.dao.HwCityDao;
import com.ai.mapp.sys.entity.HwCity;
import com.ai.mapp.sys.entity.Product;

@Service
@Transactional
public class HwCityService {
	
	private final Log log = LogFactory.getLog(HwCityService.class);
	
	@Autowired
	private HwCityDao hwCityDao;
	
	public Collection<HwCity> listAllHwCity(HwCity hwCity){
		try{
			log.debug(hwCity==null?"hwState is null":hwCity.toString());
			Collection<HwCity> c = hwCityDao.listAll(hwCity);
			return c;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void saveCity(HwCity city){
		try{
			hwCityDao.save(city);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
