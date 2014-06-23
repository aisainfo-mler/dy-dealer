package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.util.SetUtil;
import com.ailk.yd.mapp.client.model.HW0035Request;
import com.ailk.yd.mapp.client.model.HW0035Response;
import com.ailk.yd.mapp.tibco.action.YD0021Action;
import com.ailk.yd.mapp.tibco.model.YD0021.YD0021Request;
import com.ailk.yd.mapp.tibco.model.YD0021.YD0021Response;

/**
 * 查询账户信息
 * 
 * @author qianshihua
 * 
 */
@Service("hw0035")
@Action(bizcode = "hw0035", userCheck = true)
public class HW0035Action extends
		AbstractYDBaseActionHandler<HW0035Request, HW0035Response> {

	@Autowired
	private YD0021Action yd0021;

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		YD0021Request yd0021Request = new YD0021Request();
		new SetUtil(this.request, yd0021Request).copyAllSameNameProp();
		YD0021Response get2Tibco = yd0021.get2Tibco(yd0021Request.returnGetParam());
		this.response = new HW0035Response();
		response.setCustomerId(get2Tibco.getCustomerId());
		List accs25 = new ArrayList();
		this.response.setAccounts(accs25);
		if(get2Tibco.getAccounts()!=null){
			for (Iterator it = get2Tibco.getAccounts().iterator(); it.hasNext();) {
				YD0021Response.Account acc = (YD0021Response.Account) it.next();
				HW0035Response.Account acc25 = new HW0035Response.Account();
				List ss = new ArrayList();
				accs25.add(acc25);
				acc25.setCompanyCode(acc.getCompanyCode());
				acc25.setPrepaidAccountId(acc.getPrepaidAccountId());
				acc25.setServices(ss);
				
				if(acc.getServices()!=null){
					for (Iterator itt = acc.getServices().iterator(); itt
							.hasNext();) {
						YD0021Response.Service s = (YD0021Response.Service) itt.next();
						HW0035Response.Service s25 = new HW0035Response.Service();
						s25.setProductCode(s.getProductCode());
						s25.setProductName(s.getProductName());
						s25.setSericeName(s.getSericeName());
						s25.setServiceId(s.getServiceId());
						s25.setServiceType(s.getServiceType());
						ss.add(s25);
					}
				}
				
				
			}
		}
	}

}
