package com.ailk.yd.mapp.client.action;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0037Request;
import com.ailk.yd.mapp.client.model.HW0037Request.TracType;
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

	@SuppressWarnings("unchecked")
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		// TODO Auto-generated method stub
		YD0019Request yd19r = new YD0019Request();
		List<TracType> tts = request.getTracTypes();
		response = new HW0037Response();
		for (Iterator it = tts.iterator(); it.hasNext();) {
			TracType tt = (TracType) it.next();
			HW0037Response.TracRefNum trn = new HW0037Response.TracRefNum(tt.getTransactionType());
			
			if(tt.getSize()==null||tt.getSize()==0){
				tt.setSize(1);
			}
			for(int i=0;i<tt.getSize();i++){
				yd19r.setTransactionType(tt.getTransactionType());
				YD0019Response g2t = this.yd0019.get2Tibco(yd19r.returnGetParam());
				trn.addTracNum(g2t.getTransactionRefNumber());
			}
			response.addTracRef(trn);
		}

	}

}
