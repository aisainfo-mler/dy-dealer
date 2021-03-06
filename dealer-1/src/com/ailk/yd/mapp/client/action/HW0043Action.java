package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.sys.entity.HwCity;
import com.ai.mapp.sys.entity.HwDistrict;
import com.ai.mapp.sys.entity.HwState;
import com.ai.mapp.sys.service.HwCityService;
import com.ai.mapp.sys.service.HwDistrictService;
import com.ai.mapp.sys.service.HwStateService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0043Request;
import com.ailk.yd.mapp.client.model.HW0043Response;
import com.ailk.yd.mapp.client.model.HW0038Response.City;
import com.ailk.yd.mapp.client.model.HW0043Response.Area;
import com.ailk.yd.mapp.tibco.TibcoCache;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-8-4 下午03:48:50
 * 类说明:级 联  state_district_city
 */

@Service("hw0043")
@Action(bizcode="hw0043",userCheck=true)
@Scope("prototype")
public class HW0043Action extends AbstractYDBaseActionHandler<HW0043Request, HW0043Response> {

	@Autowired
	private HwCityService hwCityService;
	
	@Autowired
	private HwDistrictService hwDistrictService;
	
	@Autowired
	private HwStateService hwStateService;
	
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		if(StringUtil.isNotEmpty(this.request.getQueryType())){
			List<Area> resultList = new ArrayList<Area>(); 
				if(StringUtils.equals("1", this.request.getQueryType())){
					getStateInfo(resultList);
					if(StringUtils.equals("1", this.request.getDefaultChildren())){
						List<Area> childrenList = new ArrayList<Area>(); 
						getDistrictInfo(childrenList,resultList.get(0).getSelfCode(),null);
						this.response.setDefaultChildren(childrenList);
					}
				}
				
				if(StringUtils.equals("2", this.request.getQueryType()) && StringUtils.isNotEmpty(this.request.getUpKeyCode())){
					//district查询
					getDistrictInfo(resultList,this.request.getUpKeyCode(),this.request.getSelfKeyCode());

					
					
					if(StringUtils.equals("1", this.request.getDefaultChildren())){
						List<Area> childrenList = new ArrayList<Area>(); 
						getCityInfo(childrenList,resultList.get(0).getSelfCode(),null);
						this.response.setDefaultChildren(childrenList);
					}
				}
				
				if(StringUtils.equals("3", this.request.getQueryType()) && StringUtils.isNotEmpty(this.request.getUpKeyCode())){
					getCityInfo(resultList,this.request.getUpKeyCode(),this.request.getSelfKeyCode());
				}
			this.response.setAreaInfo(resultList);
			
		}else{
			throw new Exception("please input the queryType");
		}
		
	}

	public HwCityService getHwCityService() {
		return hwCityService;
	}

	public void setHwCityService(HwCityService hwCityService) {
		this.hwCityService = hwCityService;
	}

	public HwDistrictService getHwDistrictService() {
		return hwDistrictService;
	}

	public void setHwDistrictService(HwDistrictService hwDistrictService) {
		this.hwDistrictService = hwDistrictService;
	}

	public HwStateService getHwStateService() {
		return hwStateService;
	}

	public void setHwStateService(HwStateService hwStateService) {
		this.hwStateService = hwStateService;
	}
	
	
	public void getStateInfo(List<Area>  resultList)throws Exception{
		if(StringUtils.equals(this.request.getQueryPlace(), "0")){
				//根部查询 
			if(StringUtils.isNotEmpty(this.request.getSelfKeyCode())){
				HwState state = hwStateService.getStateByCode(this.request.getSelfKeyCode());
				if(state != null){
					Area area = new Area();
					area.setSelfCode(this.request.getSelfKeyCode());
					area.setSelfName(state.getStateName());
					resultList.add(area);
				}
		 }else{
				Collection<HwState> allState = hwStateService.listAllHwState(null);
				Area area = null;
		        for (HwState state:allState) {
		        	area = new Area();
		        	area.setSelfCode(state.getStateCode());
		        	area.setSelfName(state.getStateName());
		        	resultList.add(area);
		        	}
				}
	}else{
				if(StringUtils.isNotEmpty(this.request.getSelfKeyCode()) && TibcoCache.states.containsKey(this.request.getSelfKeyCode())){
					Area area = new Area();
					area.setSelfCode(this.request.getSelfKeyCode());
					area.setSelfName(TibcoCache.states.get(this.request.getSelfKeyCode()));
					resultList.add(area);
				}else{
					Map<String,String> states = TibcoCache.states;
					Set<String> key = states.keySet();
					Area area = null;
			        for (Iterator<String> it = key.iterator(); it.hasNext();) {
			            String s = (String) it.next();
			            area = new Area();
			        	area.setSelfCode(s);
			        	area.setSelfName(states.get(s));
			        	resultList.add(area);
			        }
				}
			}
	}
	
	
	public void getDistrictInfo(List<Area>  resultList,String upKeyCode,String selfKeyCode)throws Exception{
		if(StringUtils.equals(this.request.getQueryPlace(), "0")){
			if(StringUtils.equals("1", this.request.getQueryType())){
				//district查询
				if(StringUtils.isNotEmpty(selfKeyCode)){
					HwDistrict district = hwDistrictService.getDistrictByCode(selfKeyCode);
					if(district != null){
						Area area = new Area();
						area.setSelfCode(selfKeyCode);
						area.setSelfName(district.getDistrictName());
						resultList.add(area);
					}
				}
		}else{
				HwDistrict cond = new HwDistrict();
				cond.setStateCode(this.request.getUpKeyCode());
				Collection<HwDistrict> allDistrict = hwDistrictService.listAllHwDistrict(cond);
				Area area = null;
		        for (HwDistrict district:allDistrict) {
		        	area = new Area();
		        	area.setSelfCode(district.getDistrictGisCode());
		        	area.setSelfName(district.getDistrictName());
		        	area.setUpCode(upKeyCode);
		        	resultList.add(area);
		        }
			}
		}else{
			//district查询
			if(StringUtils.isNotEmpty(selfKeyCode)){
				HwDistrict district = hwDistrictService.getDistrictByCode(selfKeyCode);
				if(district != null){
					Area area = new Area();
					area.setSelfCode(selfKeyCode);
					area.setSelfName(district.getDistrictName());
					resultList.add(area);
				}
			}else if(TibcoCache.districtInState.containsKey(upKeyCode)){
		        Map<String,String> districts = TibcoCache.districtInState.get(upKeyCode);
				Set<String> key = districts.keySet();
				Area area = null;
		        for (Iterator<String> it = key.iterator(); it.hasNext();) {
		            String s = (String) it.next();
		            area = new Area();
		        	area.setSelfCode(s);
		        	area.setSelfName(districts.get(s));
		        	area.setUpCode(upKeyCode);
		        	resultList.add(area);
		        }
			}
		}
	}
	
	public void getCityInfo(List<Area>  resultList,String upKeyCode,String selfKeyCode)throws Exception{
		if(StringUtils.equals(this.request.getQueryPlace(), "0")){
			//city查询
			if(StringUtils.isNotEmpty(selfKeyCode)){
				HwState state = hwStateService.getStateByCode(selfKeyCode);
				if(state != null){
					Area area = new Area();
					area.setSelfCode(selfKeyCode);
//					City city = TibcoCache.getCityNameByCityCode(this.request.getUpKeyCode(), this.request.getSelfKeyCode());
//					area.setSelfName(city.getCityName());
//					area.setCircleId(city.getCircleId());
					HwCity city = hwCityService.getCityByCode( selfKeyCode);
					area.setSelfName(city.getCityName());
					area.setCircleId(city.getCircleCode());
					resultList.add(area);
				}
		}else{
			HwCity cond = new HwCity();
			Area area = null;
			cond.setDistrictCode(this.request.getUpKeyCode());
			Collection<HwCity> allCity = hwCityService.listAllHwCity(cond);
			for(HwCity city:allCity){
	        	area = new Area();
	        	area.setSelfCode(city.getCityCode());
	        	area.setSelfName(city.getCityName());
	        	area.setUpCode(upKeyCode);
	        	resultList.add(area);
			}
		}
	  }else{
		//city查询
			if(StringUtils.isNotEmpty(selfKeyCode)){
				HwState state = hwStateService.getStateByCode(selfKeyCode);
				if(state != null){
					Area area = new Area();
					area.setSelfCode(selfKeyCode);
					HwCity city = hwCityService.getCityByCode(selfKeyCode);
					area.setSelfName(city.getCityName());
					area.setUpCode(upKeyCode);
					area.setCircleId(city.getCircleCode());
					resultList.add(area);
				}
			}else if(TibcoCache.cityInDistrict.containsKey(upKeyCode)){
				List<City> cities = TibcoCache.cityInDistrict.get(upKeyCode);
				Area area = null;
		        for (City city:cities) {
		            area = new Area();
		        	area.setSelfCode(city.getCityCode());
		        	area.setSelfName(city.getCityName());
		        	area.setUpCode(upKeyCode);
		        	area.setCircleId(city.getCircleId());
		        	resultList.add(area);
		        }
			}
	  }
	}
}
