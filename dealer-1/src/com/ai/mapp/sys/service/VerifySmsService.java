package com.ai.mapp.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.sys.dao.VerifySmsDao;
import com.ai.mapp.sys.entity.VerifySms;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
public class VerifySmsService {

	@Autowired
	private VerifySmsDao vsDao;

	public VerifySms getVerifySmsById(Integer id) {
		return this.vsDao.get(id);
	}
	
	
	public void save(VerifySms vs){
		this.vsDao.save(vs);
	}

}
