package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.SysProp;
import com.ai.mapp.sys.service.SysPropService;
import com.ai.mapp.sys.service.UserService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.util.SetUtil;
import com.ailk.yd.mapp.client.model.HW0008Request;
import com.ailk.yd.mapp.client.model.HW0008Response;
import com.ailk.yd.mapp.tibco.action.YD0008Action;
import com.ailk.yd.mapp.tibco.action.YD0019Action;
import com.ailk.yd.mapp.tibco.model.YdPage;
import com.ailk.yd.mapp.tibco.model.YD0008.YD0008Request;
import com.ailk.yd.mapp.tibco.model.YD0008.YD0008Response;

/**
 * 选号接口
 * 
 */
@Service("hw0008")
@Action(bizcode = "hw0008", userCheck = true)
public class HW0008Action extends
		AbstractYDBaseActionHandler<HW0008Request, HW0008Response> {

	@Autowired
	private YD0019Action yd0019;

	@Autowired
	private YD0008Action yd0008;

	@Autowired
	private SysPropService sysPropService;
	
	@Autowired
	private UserService userService;

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		// TODO Auto-generated method stub
		YD0008Request r = new YD0008Request();

		if (StringUtils.isNotBlank(r.getVanityName())) {
			r.setIncludeVanityNumbers("true");
		} else {
			r.setIncludeVanityNumbers("false");
		}
		if (StringUtils.isNotBlank(request.getPattern())) {
			r.setPattern("*" + request.getPattern() + "*");
		}
		YdPage pg = new YdPage();
		r.setPaging(pg);
		pg.setOffset(request.getPage());
		pg.setPageSize(request.getSize());
		r.setType(request.getType());
		String vanityName = request.getVanityName();
		r.setVanityName(vanityName==null?"":vanityName);
		String numFee= "";
		if(StringUtils.isNotBlank(vanityName)){
			SysProp sp = new SysProp();
			sp.setParentKey("MSISDNTYYPE_PRICE");
			sp.setName(vanityName);
			Collection<SysProp> lp = sysPropService.listProp(sp);
			if(lp!=null&&lp.size()>0){
				SysProp next = lp.iterator().next();
				numFee = next.getKey();
			}
		}
		r.setCiecleId(userService.loadDealer(this.getUserinfo().getUserName()).getCircleId());// 现在是TC,以后要改成从登录的dealer信息中取
		Object sid = MappContext
				.getAttribute(MappContext.MAPPCONTEXT_SESSIONID);
		YD0008Response yd8resp = yd0008.post2Tibco(r, null);
		this.response = new HW0008Response();
		if (yd8resp.getSvnList() != null) {
			List svcNums = new ArrayList();
			response.setSvnList(svcNums);
			for (Iterator it = yd8resp.getSvnList().iterator(); it.hasNext();) {
				String svcNum = (String) it.next();
				svcNums.add(new HW0008Response.SvnNumber(yd8resp
						.getVanityName(), svcNum,numFee));
			}
		}

	}

	public static void main(String[] args) {
		YD0008Response yd08resp = new YD0008Response();
		HW0008Response to = new HW0008Response();
		new SetUtil(yd08resp, to).copyAllSameNameProp();
		System.err.println(to);
	}

}
