package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.HwCity;
import com.ai.mapp.sys.entity.HwState;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0036Response;
import com.ailk.yd.mapp.client.model.HW0038Request;
import com.ailk.yd.mapp.client.model.HW0038Response;
import com.ailk.yd.mapp.tibco.TibcoCache;

@Service("hw0038")
@Action(bizcode="hw0038",userCheck=true)
public class HW0038Action extends AbstractYDBaseActionHandler<HW0038Request, HW0038Response> {

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		// TODO Auto-generated method stub
		Collection<HwCity> citys = TibcoCache.citys;
		Map cityInStateMap = new HashMap();
		String sc = this.request.getStateCode();
		for (Iterator iterator = citys.iterator(); iterator.hasNext();) {
			HwCity hc = (HwCity) iterator.next();
			HwState st = hc.getState();
			if(st!=null && StringUtils.equals(sc, st.getStateCode())){
				String stateCode = st.getStateCode();
				if(!cityInStateMap.containsKey(stateCode)){
					List cism = new ArrayList();
					cism.add(new HW0038Response.City(hc.getCityCode(),hc.getCityName(),hc.getCircleCode()));
					cityInStateMap.put(stateCode,cism);
				}else{
					HW0038Response.City c = new HW0038Response.City(hc.getCityCode(),hc.getCityName(),hc.getCircleCode());
					((List)cityInStateMap.get(stateCode)).add(c);
				}
			}
		}
		response.setCityInState(cityInStateMap);
		
	}

}
