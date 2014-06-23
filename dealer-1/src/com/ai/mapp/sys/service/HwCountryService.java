package com.ai.mapp.sys.service;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.sys.dao.HwCountryDao;
import com.ai.mapp.sys.entity.HwCity;
import com.ai.mapp.sys.entity.HwCountry;

@Service
@Transactional
public class HwCountryService {
	
	private final Log log = LogFactory.getLog(HwCountryService.class);
	
	@Autowired
	private HwCountryDao hwCountryDao;
	
	public Collection<HwCountry> listAllHwCountry(HwCountry hc){
		try{
			log.debug(hc==null?"HwCountry is null":hc.toString());
			Collection<HwCountry> c = hwCountryDao.listAll(hc);
			return c;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveCountry(HwCountry country){
		try{
			hwCountryDao.save(country);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
