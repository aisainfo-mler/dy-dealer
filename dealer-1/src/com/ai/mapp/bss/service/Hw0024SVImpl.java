package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.HwCity;
import com.ai.mapp.sys.entity.HwState;
import com.ai.mapp.sys.service.HwStateService;
import com.ai.mapp.sys.service.HwCityService;

@Service("hw0024Service")
@Scope("singleton")
public class Hw0024SVImpl extends ISVTemplate {
	
	@Autowired
	private HwStateService hwStateService;
	@Autowired
	private HwCityService hwCityService;
	
	protected Object convertResponse(ParamObject param) throws Exception 
	{
		com.ai.mapp.model.HW0024.Response rsp = new com.ai.mapp.model.HW0024.Response();
		Map resultMap = (Map)param.getResult();
		Collection<HwState> hwStateList = (Collection<HwState>)resultMap.get("stateList");
		Collection<HwCity> hwCityList = (Collection<HwCity>)resultMap.get("cityList");
		
		Map<String,com.ai.mapp.model.HW0024.CityList> cityMap= new LinkedHashMap<String,com.ai.mapp.model.HW0024.CityList>(0);
		
		for(HwCity t : hwCityList){
			com.ai.mapp.model.HW0024.City city=new com.ai.mapp.model.HW0024.City();
			city.setCityCode(t.getCityCode());
			city.setCityName(t.getCityName());
			city.setStateCode(t.getState().getStateCode());
			
			com.ai.mapp.model.HW0024.CityList cityList=cityMap.get(city.getStateCode());
			if(cityList==null){
				cityList=new com.ai.mapp.model.HW0024.CityList();
				cityMap.put(city.getStateCode(), cityList);
			}
			cityList.addCity(city);
		}
		
		if(hwStateList!=null && hwStateList.size()>0){
			com.ai.mapp.model.HW0024.StateList stateList=new com.ai.mapp.model.HW0024.StateList();
			for(HwState t: hwStateList){
				com.ai.mapp.model.HW0024.State state=new com.ai.mapp.model.HW0024.State();
				state.setStateCode(t.getStateCode());
				state.setStateName(t.getStateName());
				state.setCityList(cityMap.get(t.getStateCode()));
				
				stateList.addState(state);
			}
			rsp.setStateList(stateList);
		}
		
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);
		return rsp.toXMLString();
	}
	
	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		String requestContent = (String) param.getParameter(BSSConstantParam.REQUESTCONTENT);
		com.ai.mapp.model.HW0024.Request request=com.ai.mapp.model.HW0024.Request.unmarshal(new StringReader(requestContent));
		HwState hwState=new HwState();
		hwState.setFlag("1");
		HwCity hwCity=new HwCity();
		hwCity.setFlag("1");
		
		if(StringUtils.isNotEmpty(request.getStateCode())){
			hwState.setStateCode(request.getStateCode());
			HwState state = new HwState();
			state.setStateCode(request.getStateCode());
			hwCity.setState(state);
		}
		Collection<HwState> hwStateList = hwStateService.listAllHwState(hwState);
		Collection<HwCity> hwCityList = hwCityService.listAllHwCity(hwCity);
		Map resultMap=new HashMap();
		resultMap.put("stateList", hwStateList);
		resultMap.put("cityList", hwCityList);
		
		param.setIfSuccess(true);
		param.setResult(resultMap);
		return param;
	}
	
	@Override
	/**
	 * 错误处理
	 */
	public Object error(ParamObject param) throws Exception {
		return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR,param.getErrorInfo());
	}

}
