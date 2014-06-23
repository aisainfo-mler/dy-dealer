package com.ai.mapp.sys.service;

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.sys.dao.HwCircleDao;
import com.ai.mapp.sys.entity.HwCircle;
import com.ai.mapp.sys.entity.HwCity;
import com.ai.mapp.sys.entity.HwDistrict;

@Service
@Transactional
public class HwCircleService {
	private final Log log = LogFactory.getLog(HwCircleService.class);
	@Autowired
	private HwCircleDao hwCircleDao;
	
	public Collection<HwCircle> lsitAllHwCircle(HwCircle hc){
		try{
			log.debug(hc==null?"hwState is null":hc.toString());
			Collection<HwCircle> c = hwCircleDao.listAll(hc);
			return c;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveCircle(HwCircle circle){
		try{
			hwCircleDao.save(circle);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


}
