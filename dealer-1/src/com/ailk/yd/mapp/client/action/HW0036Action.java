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
import com.ailk.yd.mapp.tibco.AreaCache;
import com.ailk.yd.mapp.tibco.TibcoConstant;

/**
 * 查询静态信息。
 * @author qianshihua
 *
 */
@Service("hw0036")
@Action(bizcode="hw0036",userCheck=true)
public class HW0036Action extends AbstractYDBaseActionHandler<HW0036Request , HW0036Response>{

	@Autowired
	private HwCountryService hwCountryService;
	@Autowired
	private HwStateService hwStateService;
	@Autowired
	private HwCityService hwCitySerice;
	@Autowired
	private HwCircleService hwCircleService;
	@Autowired
	private HwDistrictService hwDirstrictService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		Map m = TibcoConstant.returnDicMapForTerm();

		this.response = new HW0036Response();
		response.setDicts(m);
		Collection<HwCountry> countrys = AreaCache.countrys;
		Collection<HwState> state = AreaCache.state;
		Collection<HwCircle> circle =AreaCache.circle;
//		Collection<HwCity> citys = AreaCache.citys;
		Collection<HwDistrict> districts = AreaCache.districts;
		
		
		Map stateMap = new HashMap();
		for (Iterator it = state.iterator(); it.hasNext();) {
			HwState hs = (HwState) it.next();
			stateMap.put(hs.getStateCode(), hs.getStateName());
		}
		response.setStates(stateMap);
//		Map cityInStateMap = new HashMap();
//		for (Iterator iterator = citys.iterator(); iterator.hasNext();) {
//			HwCity hc = (HwCity) iterator.next();
//			HwState st = hc.getState();
//			if(st!=null){
//				String stateCode = st.getStateCode();
//				if(!cityInStateMap.containsKey(stateCode)){
//					List cism = new ArrayList();
//					cism.add(new HW0036Response.City(hc.getCityCode(),hc.getCityName(),hc.getCircleCode()));
//					cityInStateMap.put(stateCode,cism);
//				}else{
//					HW0036Response.City c = new HW0036Response.City(hc.getCityCode(),hc.getCityName(),hc.getCircleCode());
//					((List)cityInStateMap.get(stateCode)).add(c);
//				}
//			}
//		}
//		response.setCityInState(cityInStateMap);
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

	public HwCountryService getHwCountryService() {
		return hwCountryService;
	}

	public void setHwCountryService(HwCountryService hwCountryService) {
		this.hwCountryService = hwCountryService;
	}

	public HwStateService getHwStateService() {
		return hwStateService;
	}

	public void setHwStateService(HwStateService hwStateService) {
		this.hwStateService = hwStateService;
	}

	public HwCityService getHwCitySerice() {
		return hwCitySerice;
	}

	public void setHwCitySerice(HwCityService hwCitySerice) {
		this.hwCitySerice = hwCitySerice;
	}

	public HwCircleService getHwCircleService() {
		return hwCircleService;
	}

	public void setHwCircleService(HwCircleService hwCircleService) {
		this.hwCircleService = hwCircleService;
	}

}
