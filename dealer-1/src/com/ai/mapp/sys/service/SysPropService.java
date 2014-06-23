package com.ai.mapp.sys.service;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.sys.dao.SysPropDao;
import com.ai.mapp.sys.entity.SysProp;
import com.ai.mapp.sys.util.SYSConstant;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-13 下午06:33:59
 * 类说明:
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class SysPropService {
	public final Log log = LogFactory.getLog(SysPropService.class);
	
	@Autowired
	private SysPropDao sysPropDao;

	public SysPropDao getSysPropDao() {
		return sysPropDao;
	}

	public void setSysPropDao(SysPropDao sysPropDao) {
		this.sysPropDao = sysPropDao;
	}
	
	public SysProp loadPropByKey(String key){
		SysProp cond = new SysProp();
		cond.setKey(key);
		cond.setValid(SYSConstant.STATE_VALID);
		Collection<SysProp> props = sysPropDao.listAll(cond);
		if(props != null && props.isEmpty() == false){
			return props.iterator().next();
		}
		return null;
	}
	
	public Collection<SysProp> listProp(SysProp sp){
		return sysPropDao.listAll(sp);
	}
	
	
	public Collection<SysProp> listPropByNameLike(String nameLike){
		SysProp cond = new SysProp();
		cond.setName(nameLike);
		Collection<SysProp> props = sysPropDao.listAll(cond);
		return props;
	}
	
	public void saveSysProp(SysProp prop)
	{
		sysPropDao.save(prop);
	}
}
