package com.ai.mapp.sys.service;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.sys.dao.HwDistrictDao;
import com.ai.mapp.sys.entity.HwCircle;
import com.ai.mapp.sys.entity.HwCountry;
import com.ai.mapp.sys.entity.HwDistrict;

@Service
@Transactional
public class HwDistrictService {
	private final Log log = LogFactory.getLog(HwDistrictService.class);
	@Autowired
	private HwDistrictDao hwDistrictDao;
	
	public Collection<HwDistrict> lsitAllHwDistrict(HwDistrict hd){
		try{
			log.debug(hd==null?"HwDistrict is null":hd.toString());
			Collection<HwDistrict> c = hwDistrictDao.listAll(hd);
			return c;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveDistrict(HwDistrict district){
		try{
			hwDistrictDao.save(district);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
