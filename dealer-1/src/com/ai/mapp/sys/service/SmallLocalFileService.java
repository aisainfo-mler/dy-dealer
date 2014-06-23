package com.ai.mapp.sys.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.sys.dao.SmallLocalFileDao;
import com.ai.mapp.sys.dao.UserDao;
import com.ai.mapp.sys.entity.SmallLocalFile;

@Service
@Transactional
public class SmallLocalFileService {

	@Autowired
	private SmallLocalFileDao smallLocalFileDao;
	
	public Collection<SmallLocalFile> list(SmallLocalFile slf){
		return this.smallLocalFileDao.listAll(slf);
	}
	
	public SmallLocalFile getById(Long id){
		return this.smallLocalFileDao.get(id);
	}
	
	public Long save(SmallLocalFile slf){
		slf.setCreateTime(new Date());
		this.smallLocalFileDao.save(slf);
		return slf.getSlfid();
	}
	
	public void delete(Long id){
		this.smallLocalFileDao.delete(id);
	}
	
	public byte[] getImageBySlfId(Long id){
		SmallLocalFile file = getById(id);
		file.getSlfid();
		return file.getContentBlob();
	}
}
