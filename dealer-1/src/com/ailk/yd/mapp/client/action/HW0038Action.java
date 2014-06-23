package com.ailk.yd.mapp.client.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0038Request;
import com.ailk.yd.mapp.client.model.HW0038Response;
import com.ailk.yd.mapp.client.model.HW0038Response.City;
import com.ailk.yd.mapp.tibco.TibcoCache;

@Service("hw0038")
@Action(bizcode="hw0038",userCheck=true)
public class HW0038Action extends AbstractYDBaseActionHandler<HW0038Request, HW0038Response> {

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		// TODO Auto-generated method stub
//		Collection<HwCity> citys = TibcoCache.citys;
//		Map cityInStateMap = new HashMap();
//		String sc = this.request.getStateCode();
//		for (Iterator iterator = citys.iterator(); iterator.hasNext();) {
//			HwCity hc = (HwCity) iterator.next();
//			HwState st = hc.getState();
//			if(st!=null && StringUtils.equals(sc, st.getStateCode())){
//				String stateCode = st.getStateCode();
//				if(!cityInStateMap.containsKey(stateCode)){
//					List cism = new ArrayList();
//					cism.add(new HW0038Response.City(hc.getCityCode(),hc.getCityName(),hc.getCircleCode()));
//					cityInStateMap.put(stateCode,cism);
//				}else{
//					HW0038Response.City c = new HW0038Response.City(hc.getCityCode(),hc.getCityName(),hc.getCircleCode());
//					((List)cityInStateMap.get(stateCode)).add(c);
//				}
//			}
//		}
		List<City> rm = TibcoCache.cityInState.get(request.getStateCode());
		Map<String,List<City>> p = new HashMap();
		p.put(request.getStateCode(),rm);
		response.setCityInState(p);
		
	}

}
