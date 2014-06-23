package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.SvnInfo;
import com.ai.mapp.sys.service.SvnInfoService;
import com.ai.mapp.sys.util.SYSConstant;

@Service("hw0008Service")
@Scope("singleton")
public class HW0008SVImpl extends ISVTemplate {

	@Autowired
	private SvnInfoService svnInfoService;
	
	protected Object convertResponse(ParamObject param) throws Exception 
	{
		com.ai.mapp.model.HW0008.Response rsp = new com.ai.mapp.model.HW0008.Response();
		
		Collection<SvnInfo> svnInfos = (Collection<SvnInfo>)param.getResult();
		if(svnInfos != null && svnInfos.isEmpty() == false)
		{
			com.ai.mapp.model.HW0008.MdnList mdns = new com.ai.mapp.model.HW0008.MdnList();
			for(SvnInfo svnInfo : svnInfos)
			{
				com.ai.mapp.model.HW0008.Item item = new com.ai.mapp.model.HW0008.Item();
				
				item.setSvcNo(svnInfo.getSvn());
				item.setSvcLevel(svnInfo.getSvcLevel());
				mdns.addItem(item);
			}
			rsp.setMdnList(mdns);
		}
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);
		
		return rsp.toXMLString();
	}
	
	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		com.ai.mapp.model.HW0008.Request req = com.ai.mapp.model.HW0008.Request
				.unmarshal(new StringReader((String)param.getParameter(BSSConstantParam.REQUESTCONTENT)));
		
		SvnInfo condition = new SvnInfo();
		condition.setStatus(SYSConstant.ITEM_STATUS_UNUSE);
		if(StringUtil.isEmpty(req.getSvcLevel()) == false)
			condition.setSvcLevel(req.getSvcLevel());
		if(StringUtil.isEmpty(req.getPattern()) == false)
			condition.setPattern(req.getPattern());
		
		int start = StringUtil.isEmpty(req.getStart())  ? 0 : Integer.valueOf(req.getStart());
		int pageSize = StringUtil.isEmpty(req.getSize()) ? 200 : Integer.valueOf(req.getSize());
		
		Collection<SvnInfo> svnInfos = svnInfoService.listSvnInfos(condition, start, pageSize);
		
		param.setIfSuccess(true);
		param.setResult(svnInfos);
		return param;
		
	}

	@Override
	public Object error(ParamObject param) throws Exception {
		return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR,param.getErrorInfo());
	}
	
}
