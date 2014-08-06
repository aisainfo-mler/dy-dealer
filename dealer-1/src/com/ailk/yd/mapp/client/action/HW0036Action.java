package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.HwCircle;
import com.ai.mapp.sys.entity.HwCity;
import com.ai.mapp.sys.entity.HwCountry;
import com.ai.mapp.sys.entity.HwDistrict;
import com.ai.mapp.sys.entity.HwState;
import com.ai.mapp.sys.service.HwCircleService;
import com.ai.mapp.sys.service.HwCityService;
import com.ai.mapp.sys.service.HwCountryService;
import com.ai.mapp.sys.service.HwDistrictService;
import com.ai.mapp.sys.service.HwStateService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0036Request;
import com.ailk.yd.mapp.client.model.HW0036Response;
import com.ailk.yd.mapp.client.model.HW0036Response.Country;
import com.ailk.yd.mapp.client.model.HW0036Response.State;
import com.ailk.yd.mapp.client.model.HW0043Response.Area;
import com.ailk.yd.mapp.tibco.TibcoCache;
import com.ailk.yd.mapp.tibco.TibcoConstant;

/**
 * 查询静态信息。
 * 2014-06-24移除district的信息，转到38接口中
 * @author qianshihua
 *
 */
@Service("hw0036")
@Action(bizcode="hw0036",userCheck=true)
@Scope("prototype")
public class HW0036Action extends AbstractYDBaseActionHandler<HW0036Request , HW0036Response>{
	
	@Autowired
	private HwStateService hwStateService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		Map m = TibcoCache.dicts;

		this.response = new HW0036Response();
		response.setDicts(m);
		
		Map<String,String> states = TibcoCache.states;
		Set<String> key = states.keySet();
		State state = null;
//		response.setStates(new ArrayList<State>());
		response.setStates(new HashMap<String, String>());
        for (Iterator<String> it = key.iterator(); it.hasNext();) {
        	String s = (String) it.next();
        	response.getStates().put(states.get(s),s);
//        	response.getStates().add(state);
        }
		
//		Collection<HwState> sl = hwStateService.listAllHwState(null);
//		for (Iterator it = sl.iterator(); it.hasNext();) {
//			HwState hs = (HwState) it.next();
//			state = new State();
//        	state.setStateCode(hs.getStateCode());
//        	state.setStateName(hs.getStateName());
//        	response.getStates().add(state);
//		}
		
		response.setCountrys(new ArrayList<Country>());
		
		
		Map<String,Country> countries = TibcoCache.countrys;
		key = countries.keySet();
        for (Iterator<String> it = key.iterator(); it.hasNext();) {
        	String s = (String) it.next();
        	response.getCountrys().add(countries.get(s));
        }
		
	}

	public HwStateService getHwStateService() {
		return hwStateService;
	}

	public void setHwStateService(HwStateService hwStateService) {
		this.hwStateService = hwStateService;
	}

}
