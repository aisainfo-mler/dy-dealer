package com.ai.mapp.sys.service;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ai.mapp.sys.dao.HwStateDao;
import com.ai.mapp.sys.entity.HwState;

@Service
@Transactional
public class HwStateService {
	
	private final Log log = LogFactory.getLog(HwStateService.class);
	
	@Autowired
	private HwStateDao hwStateDao;
	
	public Collection<HwState> listAllHwState(HwState hwState){
		try{
			log.debug(hwState==null?"hwState is null":hwState.toString());
			Collection<HwState> c = hwStateDao.listAll(hwState);
			return c;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public HwState getStateByCode(String stateCode)throws Exception{
		if(StringUtils.isNotEmpty(stateCode)){
			HwState cond = new HwState();
			cond.setStateCode(stateCode);
			Collection<HwState> c = hwStateDao.listAll(cond);
			if(c.isEmpty() == false){
				return c.iterator().next();
			}
		}
		return null;
	}
	
	public void saveState(HwState state){
		try{
			hwStateDao.save(state);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


}
