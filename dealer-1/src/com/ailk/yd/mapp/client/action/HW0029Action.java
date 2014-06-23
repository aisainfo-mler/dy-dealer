package com.ailk.yd.mapp.client.action;

import org.apache.commons.lang3.StringUtils;

import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.model.IHeader;
import com.ailk.util.SetUtil;
import com.ailk.yd.mapp.client.model.HW0029Request;
import com.ailk.yd.mapp.client.model.HW0029Response;
import com.ailk.yd.mapp.model.YDDatapackage;
import com.ailk.yd.mapp.model.YDHeader;
import com.ailk.yd.mapp.tibco.model.YD0005.YD0005Request;
import com.ailk.yd.mapp.tibco.model.YD0005.YD0005Response;

/**
 * 算费接口。透传yd0005
 * @author qianshihua
 *
 */
public class HW0029Action extends
		AbstractYDBaseActionHandler<HW0029Request, HW0029Response> {

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		YD0005Request r = new YD0005Request();
		
		new SetUtil(request,r).copyAllSameNameProp();
		Object sid = MappContext.getAttribute(MappContext.MAPPCONTEXT_SESSIONID);
		
		YDDatapackage resp ;
		if(StringUtils.equals("1", test)){
			resp = new YDDatapackage();
			IHeader h = new YDHeader();
			h.setRespCode("0000");
			YD0005Response yd0005 = new YD0005Response();
			yd0005.fillTestData();
			resp.setBody(yd0005);
			resp.setHeader(h);
		}else{
//			resp = this.sendMsg("yd0005", r, sid!=null?sid.toString():null);
			resp = null;
		}
		new SetUtil(resp.getBody(), response).copyAllSameNameProp();

	}

}
