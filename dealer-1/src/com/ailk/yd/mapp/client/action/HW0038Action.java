package com.ailk.yd.mapp.client.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.service.DataImpService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0038Request;
import com.ailk.yd.mapp.client.model.HW0038Response;
import com.ailk.yd.mapp.client.model.HW0038Response.City;
import com.ailk.yd.mapp.tibco.TibcoCache;

/**
 * 根据state返回city列表
 * 2014-06-24新增district列表.这个原来在36接口中
 * @author qianshihua
 *
 */
@Service("hw0038")
@Action(bizcode="hw0038",userCheck=true)
@Scope("prototype")
public class HW0038Action extends AbstractYDBaseActionHandler<HW0038Request, HW0038Response> {

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		// TODO Auto-generated method stub
//		List<City> rm = TibcoCache.cityInState.get(request.getStateCode());
//		if(rm!=null && rm.size()>0){
//			Map<String,List<City>> p = new LinkedHashMap();
//			p.put(request.getStateCode(),rm);
//			response.setCityInState(p);
//		}
		
		List<City> rm = TibcoCache.cityInDistrict.get(request.getDistrictCode());
		if(rm!=null && rm.size()>0){
			Map<String,List<City>> p = new LinkedHashMap();
			p.put(request.getDistrictCode(),rm);
//			response.setCityInState(p);
			response.setCityInDistrict(p);
		}
		
		Map<String, String> ds = TibcoCache.districtInState.get(request.getStateCode());
		if(ds!=null && ds.keySet().size()>0){
			Map<String,Map<String,String>> dsm = new LinkedHashMap();
			dsm.put(request.getStateCode(), ds);
			response.setDistrictInState(dsm);
		}
	}

}
