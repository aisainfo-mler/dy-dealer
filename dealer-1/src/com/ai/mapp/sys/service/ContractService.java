package com.ai.mapp.sys.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.error.RollbackException;
import com.ai.mapp.sys.dao.ContractDao;
import com.ai.mapp.sys.entity.Contract;
import com.ai.mapp.sys.entity.Mobile;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.mapp.core.ErrorCodeDefine;

@Service
@Transactional(rollbackFor=Exception.class)
public class ContractService {
	
	public final Log log = LogFactory.getLog(ContractService.class);
	
	@Autowired
	private ContractDao contractDao;
	
	public Collection<Contract> listContracts(Contract contract,int start,int limit){
		try{
			log.debug(contract==null?"contract is null":contract.toString());
			
			Collection<Contract> c = contractDao.list(contract, start, limit);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveContract(Contract contract){
		try{
			log.debug(contract==null?"contract is null":contract.toString());
			contractDao.save(contract);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>描述: 批量保存套餐</p> 
	 * @param contract  
	 * @author        Zhengwj
	 * @Date          2014-5-5 上午09:31:47
	 */
	public void saveContractBatch(List<Contract> contractList)throws Exception{
		log.debug(contractList==null?"contractList is null":contractList.toString());
		if(contractList == null || contractList.size() == 0){
			throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,"套餐列表为空");
		}
		Contract tmp = null;
		Contract contract = null;
		for(int i = 0; i < contractList.size(); i++){
			contract = contractList.get(i);
			tmp = loadContractByContractId(contract.getContractId());
			if(StringUtils.isNotEmpty(contract.getOptType())){//如果操作类型不为空的话
				if(StringUtils.equals(SYSConstant.OPTTYPE_INSERT, contract.getOptType())){
					if(tmp != null){
						throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,"第" + (i + 1) + "条的套餐ID已存在！");
					}
					contractDao.save(contract);
				}else{
					if(tmp != null){
						throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,"第" + (i + 1) + "条的套餐ID不存在,不能做更新或删除操作！");
					}
					if(StringUtils.equals(SYSConstant.OPTTYPE_UPDATE, contract.getOptType())){
						tmp.setAnswerFree(contract.getAnswerFree());
						tmp.setCallRate(contract.getCallRate());
						tmp.setContractFee(contract.getContractFee());
						tmp.setContractType(contract.getContractType());
						tmp.setDomesticCall(contract.getDomesticCall());
						tmp.setFlow(contract.getFlow());
						tmp.setFlowRate(contract.getFlowRate());
						tmp.setLowcost(contract.getLowcost());
						tmp.setMms(contract.getMms());
						tmp.setName(contract.getName());
						tmp.setOtherFee(contract.getOtherFee());
						tmp.setUpdateTime(new Date());
						tmp.setVideoFee(contract.getVideoFee());
						tmp.setVideoMin(contract.getVideoMin());
					}else if(StringUtils.equals(SYSConstant.OPTTYPE_DELETE, contract.getOptType())){
						tmp.setStatus(SYSConstant.STATE_INVALID);
					}
					contractDao.save(tmp);
				} 
			}else{
				
			}
		}
	}
	
	public void deleteContract(Contract contract){
		try{
			log.debug(contract==null?"contract is null":contract.toString());
			contractDao.delete(contract);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int countContract(Contract contract) throws RollbackException {
		try{
			return contractDao.count(contract);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RollbackException(e);
		}
	}
	
	public Contract loadContract(Long id)
	{
		return contractDao.get(id);
	}
	
	public Contract loadContractByContractId(String contractId)
	{
		//return mobileDao.get(id);
		
		Contract condition = new Contract(contractId);
		
		Collection<Contract> contracts = listContracts(condition, 0, 1);
		
		if(contracts == null || contracts.isEmpty())
			return null;
		
		return contracts.iterator().next();
	}
	
}
