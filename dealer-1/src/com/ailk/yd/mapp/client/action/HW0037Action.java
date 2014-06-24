package com.ailk.yd.mapp.client.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0037Request;
import com.ailk.yd.mapp.client.model.HW0037Response;
import com.ailk.yd.mapp.tibco.action.YD0019Action;
import com.ailk.yd.mapp.tibco.model.YD0019.YD0019Request;
import com.ailk.yd.mapp.tibco.model.YD0019.YD0019Response;

/**
 * 获取tibco的订单编号
 * 
 * @author qianshihua
 * 
 */
@Action(bizcode = "hw0037", userCheck = true)
@Service("hw0037")
public class HW0037Action extends
		AbstractYDBaseActionHandler<HW0037Request, HW0037Response> {

	/**
	 * 
	 New Order (NO), Change Order (CO), Balance Replenishment (BR), Digital
	 * Service Order (DO), Balance Transfer (BT), Charge Redirection (CD)
	 * Service Request (SR), Miscellaneous Requests (MR) CAF Number (CF)
	 */
	@Autowired
	private YD0019Action yd0019;

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		// TODO Auto-generated method stub
		YD0019Request yd19r = new YD0019Request();
		yd19r.setTransactionType(request.getTransactionType());
		YD0019Response g2t = this.yd0019.get2Tibco(yd19r.returnGetParam());
		this.response = new HW0037Response();
		response.setTransactionRefNumber(g2t.getTransactionRefNumber());

	}

}
