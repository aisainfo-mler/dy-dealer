package com.ailk.ts.ibatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ailk.butterfly.common.dal.ibatis.SysCommonCoreDAO;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.core.util.DateUtils;
import com.ailk.ts.dal.ibatis.RepositoryDAO;
import com.ailk.ts.dal.ibatis.model.Repository;
import com.ailk.ts.dal.ibatis.model.RepositoryExample;

@Service
@Transactional(rollbackFor=Exception.class)
public class RepositoryService{

	@Autowired
	private RepositoryDAO repDao;
	
	@Autowired
	private SysCommonCoreDAO sysCommonCoreDAO;

	public List<Repository> getAllRep() {
		return this.repDao.selectByExample(new RepositoryExample());
	}

	public List<Repository> getRepsByDeptId(String deptId){
		RepositoryExample re = new RepositoryExample();
		re.createCriteria().andDeptIdEqualTo(deptId);
		return this.repDao.selectByExample(re);
	}
	
	public List<Repository> getRepsByUserId(Long userId){
		RepositoryExample re = new RepositoryExample();
		re.createCriteria().andUserIdEqualTo(userId);
		re.setOrderByClause("CREATE_TIME");
		return this.repDao.selectByExample(re);
	}
	
	public void updateRepBySaleSkuEntity(){
		
	}
	
	public RepositoryDAO getRepDao() {
		return repDao;
	}

	public void setRepDao(RepositoryDAO repDao) {
		this.repDao = repDao;
	}

	public Long createRepository(String deptId, String province,
			String areaId, String countyId, String streetId, String repName,
			String remark,Long userId) throws BusinessException, SystemException {
		Repository repository = new Repository();
		repository.setDeptId(deptId);
		repository.setProvince(province);
		repository.setCounty(countyId);
		repository.setArea(areaId);
		repository.setCreateTime(DateUtils.getCurrent());
		repository.setRepName(repName);
		repository.setStreet(streetId);
		repository.setRemark(remark);
		repository.setUserId(userId);
//		String repCode = sysCommonCoreDAO.getNextSequence("SEQ_REPOSITORY");
//		repository.setRepCode(repCode);
		return repDao.insertSelective(repository);
	}

	public SysCommonCoreDAO getSysCommonCoreDAO() {
		return sysCommonCoreDAO;
	}

	public void setSysCommonCoreDAO(SysCommonCoreDAO sysCommonCoreDAO) {
		this.sysCommonCoreDAO = sysCommonCoreDAO;
	}

	public Long createSignalRepository(String deptId, String province,
			String areaId, String countyId, String streetId, String repName,
			String remark,Long userId) throws BusinessException, SystemException {
		List<Repository> reps = getRepsByDeptId(deptId);
		if(reps == null || reps.size() == 0){
			return createRepository(deptId, province, areaId, countyId, streetId, repName, remark,userId);
		}else{
			return null;
		}
		
	}
	
	

}
