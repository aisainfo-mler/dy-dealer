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
import com.ailk.yd.mapp.client.model.HW0007Request;
import com.ailk.yd.mapp.client.model.HW0007Response;
import com.ailk.yd.mapp.tibco.action.YD0007Action;
import com.ailk.yd.mapp.tibco.model.YD0007.YD0007Request;
import com.ailk.yd.mapp.tibco.model.YD0007.YD0007Response;

@Action(bizcode = "hw0007", userCheck = true)
@Service("hw0007")
public class HW0007Action extends
		AbstractYDBaseActionHandler<HW0007Request, HW0007Response> {

	@Autowired
	private YD0007Action yd0007;

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
		for (Iterator it = g2t.getCustomers().iterator(); it.hasNext();) {
			YD0007Response.Customer f = (YD0007Response.Customer) it.next();
			HW0007Response.Customer t = new HW0007Response.Customer();
			new SetUtil(f, t).copyAllSameNameProp();
			cc.add(t);
		}
	}

}
