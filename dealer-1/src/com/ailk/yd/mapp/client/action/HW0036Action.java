package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ailk.yd.mapp.tibco.TibcoCache;
import com.ailk.yd.mapp.tibco.TibcoConstant;

/**
 * 查询静态信息。
 * @author qianshihua
 *
 */
@Service("hw0036")
@Action(bizcode="hw0036",userCheck=true)
public class HW0036Action extends AbstractYDBaseActionHandler<HW0036Request , HW0036Response>{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		Map m = TibcoCache.dicts;

		this.response = new HW0036Response();
		response.setDicts(m);
		Collection<HwCountry> countrys = TibcoCache.countrys;
		Collection<HwState> state = TibcoCache.state;
		Collection<HwCircle> circle =TibcoCache.circle;
//		Collection<HwCity> citys = AreaCache.citys;
		Collection<HwDistrict> districts = TibcoCache.districts;
		
		
		Map stateMap = new HashMap();
		for (Iterator it = state.iterator(); it.hasNext();) {
			HwState hs = (HwState) it.next();
			stateMap.put(hs.getStateCode(), hs.getStateName());
		}
		response.setStates(stateMap);

		
		Map districtInState = new HashMap();
		for (Iterator it = districts.iterator(); it.hasNext();) {
			HwDistrict hd = (HwDistrict) it.next();
			if(StringUtils.isNotBlank(hd.getStateCode())){
				if(districtInState.containsKey(hd.getStateCode())){
					((Map)districtInState.get(hd.getStateCode())).put(hd.getDistrictGisCode(), hd.getDistrictName());
				}else{
					districtInState.put(hd.getStateCode(), new HashMap());
				}
			}
		}
		response.setDistrictInState(districtInState);
		
		Map countrysMap = new HashMap();
		for (Iterator it = countrys.iterator(); it.hasNext();) {
			HwCountry hw = (HwCountry) it.next();
			countrysMap.put(hw.getCountryCode(), hw.getCountryName());
		}
		response.setCountrys(countrysMap);
		
		
		
		
	}

}
