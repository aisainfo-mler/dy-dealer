package com.ailk.yd.mapp.client.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.util.SetUtil;
import com.ailk.yd.mapp.client.model.HW0040Request;
import com.ailk.yd.mapp.client.model.HW0040Response;
import com.ailk.yd.mapp.tibco.action.YD0022Action;
import com.ailk.yd.mapp.tibco.model.YD0022.YD0022Request;
import com.ailk.yd.mapp.tibco.model.YD0022.YD0022Response;
import com.ailk.yd.mapp.tibco.model.YD0022.YD0022Response.PersonalDetails;

/**
 * queryCustomer
 * @version 2014-06-27 根据customer查询客户的详细信息
 *
 */
@Service("hw0040")
@Action(bizcode="hw0040",userCheck=true)
@Scope("prototype")
public class HW0040Action extends
		AbstractYDBaseActionHandler<HW0040Request, HW0040Response> {

	@Autowired
	private YD0022Action yd0022Action;
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		if(this.request.getCustomerIds()==null && this.request.getCustomerIds().size()==0){
			response = new HW0040Response();
			return;
		}
		
		HW0040Response hw0040rsp = new HW0040Response();
		response = hw0040rsp;
		Map customers = new HashMap();
		hw0040rsp.setCustomers(customers);
		for (Iterator it = request.getCustomerIds().iterator(); it.hasNext();) {
			String customerId = (String) it.next();
			YD0022Request yd0022Req = new YD0022Request();
			yd0022Req.setCustomerId(customerId);
			YD0022Response get2Tibco = this.yd0022Action.get2Tibco(yd0022Req.returnGetParam());
			PersonalDetails personalDetails = get2Tibco.getPersonalDetails();
			HW0040Response.Customer c = new HW0040Response.Customer();
			new SetUtil(get2Tibco, c).copyAllSameNameProp();
			new SetUtil(get2Tibco.getPersonalDetails(), c).copyAllSameNameProp();
			customers.put(customerId, c);
			if(personalDetails!=null){
				HW0040Response.ContactDetails hc = new HW0040Response.ContactDetails();
				HW0040Response.FamilyContactDetails hf = new HW0040Response.FamilyContactDetails();
				HW0040Response.PermanentAddress hp = new HW0040Response.PermanentAddress();
				new SetUtil(personalDetails.getContactDetails(),hc).copyAllSameNameProp();
				new SetUtil(personalDetails.getFamilyContactDetails(),hf).copyAllSameNameProp();
				new SetUtil(personalDetails.getPermanentAddress(),hp).copyAllSameNameProp();
				c.setContactDetails(hc);
				c.setFamilyContactDetails(hf);
				c.setPermanentAddress(hp);
			}
			
		}
		
		
		
		
	}

	
}
