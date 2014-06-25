package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.util.SetUtil;
import com.ailk.yd.mapp.client.model.HW0007Request;
import com.ailk.yd.mapp.client.model.HW0007Response;
import com.ailk.yd.mapp.client.model.TibcoAccount;
import com.ailk.yd.mapp.client.model.TibcoCustomer;
import com.ailk.yd.mapp.client.model.TibcoService;
import com.ailk.yd.mapp.tibco.action.YD0007Action;
import com.ailk.yd.mapp.tibco.action.YD0021Action;
import com.ailk.yd.mapp.tibco.model.YD0007.YD0007Request;
import com.ailk.yd.mapp.tibco.model.YD0007.YD0007Response;
import com.ailk.yd.mapp.tibco.model.YD0021.YD0021Request;
import com.ailk.yd.mapp.tibco.model.YD0021.YD0021Response;

/**
 * queryCustomer
 * 
 * @author qianshihua
 * 
 */
@Action(bizcode = "hw0007", userCheck = true)
@Service("hw0007")
@Scope("prototype")
public class HW0007Action extends
		AbstractYDBaseActionHandler<HW0007Request, HW0007Response> {

	@Autowired
	private YD0007Action yd0007;

	@Autowired
	private YD0021Action yd0021;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		// TODO Auto-generated method stub
		YD0007Request yd0007Req = new YD0007Request();
		new SetUtil(this.request, yd0007Req).copyAllSameNameProp();
		yd0007Req.setEmailid(request.getMdn());
		YD0007Response g2t = yd0007.get2Tibco(yd0007Req.returnGetParam());
		this.response = new HW0007Response();
		response.setTotalRecords(g2t.getTotalRecords());
		List cc = new ArrayList();
		response.setCustomers(cc);
//		int i=0;
		for (Iterator it = g2t.getCustomers().iterator(); it.hasNext();) {
			TibcoCustomer f = (TibcoCustomer) it.next();
//			if(i==0){
//				i++;
//				f.setCustomerId("1100009566");
//			}
			cc.add(f);
			queryCustomer(f);
		}

	}

	/**
	 * 查询
	 * 
	 * @param customer
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void queryCustomer(TibcoCustomer customer) throws Exception {
		YD0021Request yd0021Request = new YD0021Request();
		yd0021Request.setCustomerId(customer.getCustomerId());
		YD0021Response get2Tibco = yd0021.get2Tibco(yd0021Request
				.returnGetParam());
		List accs25 = new ArrayList();
		customer.setAccounts(accs25);
		if (get2Tibco.getAccounts() != null) {
			for (Iterator itAcc = get2Tibco.getAccounts().iterator(); itAcc
					.hasNext();) {
				TibcoAccount acc = (TibcoAccount) itAcc.next();
				TibcoAccount acc25 = new TibcoAccount();
				List ss = new ArrayList();
				accs25.add(acc25);
				acc25.setCompanyCode(acc.getCompanyCode());
				acc25.setPrepaidAccountId(acc.getPrepaidAccountId());
				acc25.setServices(ss);

				if (acc.getServices() != null) {
					for (Iterator itt = acc.getServices().iterator(); itt
							.hasNext();) {
						TibcoService s = (TibcoService) itt.next();
						TibcoService s25 = new TibcoService();
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
