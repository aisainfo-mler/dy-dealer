package com.ai.mapp.sys.service;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.error.RollbackException;
import com.ai.mapp.sys.dao.SvnInfoDao;
import com.ai.mapp.sys.entity.SvnInfo;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.util.SYSConstant;

@Service
@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
public class SvnInfoService {
	
	public final Log log = LogFactory.getLog(SvnInfoService.class);
	
	@Autowired
	private SvnInfoDao svnInfoDao;
	
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public Collection<SvnInfo> listSvnInfos(SvnInfo svnInfo,int start,int limit){
		try{
			log.debug(svnInfo==null?"svnInfo is null":svnInfo.toString());
			
			Collection<SvnInfo> c = svnInfoDao.list(svnInfo, start, limit);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public Collection<SvnInfo> listAllSvnInfos(SvnInfo svnInfo){
		try{
			log.debug(svnInfo==null?"svnInfo is null":svnInfo.toString());
			
			Collection<SvnInfo> c = svnInfoDao.listAll(svnInfo);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveSvnInfo(SvnInfo svnInfo){
		try{
			log.debug(svnInfo==null?"svnInfo is null":svnInfo.toString());
			svnInfoDao.save(svnInfo);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteSvnInfo(SvnInfo svnInfo){
		try{
			log.debug(svnInfo==null?"svnInfo is null":svnInfo.toString());
			svnInfoDao.delete(svnInfo);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public int countSvnInfo(SvnInfo svnInfo) throws RollbackException {
		try{
			return svnInfoDao.count(svnInfo);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RollbackException(e);
		}
	}
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public SvnInfo loadSvnInfo(Long id)
	{
		return svnInfoDao.get(id);
	}
	
	
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public SvnInfo loadSvnInfoBySvn(String svn,String status) {
		
		SvnInfo condition = new SvnInfo();
		condition.setSvn(svn);
		if(StringUtil.isEmpty(status) == false)
		{
			condition.setStatus(status);
		}
//		condition.setStatus(SYSConstant.ITEM_STATUS_UNUSE);
		
		Collection<SvnInfo> svnList  = listSvnInfos(condition, 0, 1);
		
		if(svnList == null || svnList.isEmpty())
			return null;
		
		return svnList.iterator().next();
	}
	
}
