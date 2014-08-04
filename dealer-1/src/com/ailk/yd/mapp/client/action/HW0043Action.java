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
import com.ai.mapp.sys.service.HwCityService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0043Request;
import com.ailk.yd.mapp.client.model.HW0043Response;
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
	
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		if(StringUtil.isNotEmpty(this.request.getQueryType())){
			List<Area> resultList = new ArrayList<Area>(); 
			if(StringUtils.equals("1", this.request.getQueryType())){
				//根部查询 
				if(StringUtils.isNotEmpty(this.request.getSelfKeyCode()) && TibcoCache.states.containsKey(this.request.getSelfKeyCode())){
					Area area = new Area();
					area.setSelfCode(this.request.getSelfKeyCode());
					area.setSelfName(TibcoCache.states.get(this.request.getSelfKeyCode()));
					resultList.add(area);
				}else{
					Map<String,String> allState = TibcoCache.states;
					Set<String> key = allState.keySet();
					Area area = null;
			        for (Iterator<String> it = key.iterator(); it.hasNext();) {
			        	String s = (String) it.next();
			        	area = new Area();
			        	area.setSelfCode(s);
			        	area.setSelfName(allState.get(s));
			        	resultList.add(area);
			        }
				}
			}
			
			if(StringUtils.equals("2", this.request.getQueryType()) && StringUtils.isNotEmpty(this.request.getUpKeyCode())){
				//district查询
				if(StringUtils.isNotEmpty(this.request.getSelfKeyCode()) && TibcoCache.states.containsKey(this.request.getSelfKeyCode())){
					Area area = new Area();
					area.setSelfCode(this.request.getSelfKeyCode());
					area.setSelfName(TibcoCache.districtInState.get(this.request.getUpKeyCode()).get(this.request.getSelfKeyCode()));
					resultList.add(area);
				}else{
					Map<String,String> allDistrict = TibcoCache.districtInState.get(this.request.getUpKeyCode());
					Set<String> key = allDistrict.keySet();
					Area area = null;
			        for (Iterator<String> it = key.iterator(); it.hasNext();) {
			        	String s = (String) it.next();
			        	area = new Area();
			        	area.setSelfCode(s);
			        	area.setSelfName(allDistrict.get(s));
			        	area.setUpCode(this.request.getUpKeyCode());
			        	resultList.add(area);
			        }
				}
			}
			
			if(StringUtils.equals("3", this.request.getQueryType()) && StringUtils.isNotEmpty(this.request.getUpKeyCode())){
				//city查询
				if(StringUtils.isNotEmpty(this.request.getSelfKeyCode()) && TibcoCache.states.containsKey(this.request.getSelfKeyCode())){
					Area area = new Area();
					area.setSelfCode(this.request.getSelfKeyCode());
//					City city = TibcoCache.getCityNameByCityCode(this.request.getUpKeyCode(), this.request.getSelfKeyCode());
//					area.setSelfName(city.getCityName());
//					area.setCircleId(city.getCircleId());
					HwCity city = hwCityService.getCityByCode( this.request.getSelfKeyCode());
					area.setSelfName(city.getCityName());
					area.setCircleId(city.getCircleCode());
					resultList.add(area);
				}else{
					HwCity cond = new HwCity();
					Area area = null;
					cond.setDistrictCode(this.request.getUpKeyCode());
					Collection<HwCity> allCity = hwCityService.listAllHwCity(cond);
					for(HwCity city:allCity){
			        	area = new Area();
			        	area.setSelfCode(city.getCityCode());
			        	area.setSelfName(city.getCityName());
			        	area.setUpCode(this.request.getUpKeyCode());
			        	resultList.add(area);
					}

				}
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
	
}
