package com.ailk.ts.ibatis.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.ts.dal.ibatis.RepOptRecordDAO;
import com.ailk.ts.dal.ibatis.model.RepOptRecord;
import com.ailk.ts.dal.ibatis.model.RepOptRecordExample;
import com.ailk.ts.dal.ibatis.model.RepOptRecordExample.Criteria;

@Service
@Transactional(rollbackFor=Exception.class)
public class RepOptRecordService{

	@Autowired
	private RepOptRecordDAO repOptRecordDao;

	public int countRepOptRecord(RepOptRecord ror) throws BusinessException,
			SystemException {
		return this.repOptRecordDao.countByExample(this.convert2Example(ror));
	}

	public List<RepOptRecord> getRep(RepOptRecord ror, int start, int limit)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		RepOptRecordExample e = this.convert2Example(ror);
		e.setLimitClauseStart(start);
		e.setLimitClauseCount(limit);
		return this.repOptRecordDao.selectByExample(e);
	}

	private RepOptRecordExample convert2Example(RepOptRecord ror) {
		RepOptRecordExample e = new RepOptRecordExample();
		Criteria cc = e.createCriteria();
		if (ror.getSkuid() != null) {
			cc.andSkuidEqualTo(ror.getSkuid());
		}
		if (StringUtils.isNotBlank(ror.getInputRepCode())) {
			cc.andInputRepCodeEqualTo(ror.getInputRepCode());
		}
		if (StringUtils.isNotBlank(ror.getOutputRepCode())) {
			Criteria cc2 = e.or();
			if (ror.getSkuid() != null) {
				cc2.andSkuidEqualTo(ror.getSkuid());
			}
			if (StringUtils.isNotBlank(ror.getOutputRepCode())) {
				cc2.andOutputRepCodeEqualTo(ror.getOutputRepCode());
			}
		}
		return e;
	}

	public RepOptRecordDAO getRepOptRecordDao() {
		return repOptRecordDao;
	}

	public void setRepOptRecordDao(RepOptRecordDAO repOptRecordDao) {
		this.repOptRecordDao = repOptRecordDao;
	}

}
