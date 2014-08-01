package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0035Request;
import com.ailk.yd.mapp.client.model.HW0035Response;
import com.ailk.yd.mapp.tibco.action.YD0021Action;
import com.ailk.yd.mapp.tibco.model.YD0021.YD0021Request;
import com.ailk.yd.mapp.tibco.model.YD0021.YD0021Response;
import com.ailk.yd.mapp.tibco.model.YD0021.YD0021Response.Account;
import com.ailk.yd.mapp.tibco.model.YD0021.YD0021Response.Identifier;
import com.ailk.yd.mapp.tibco.model.YD0021.YD0021Response.ServicePackage;
import com.ailk.yd.mapp.tibco.model.YD0021.YD0021Response.Services;

/**
 * 查询账户信息. 2014-06-26更新：根据多个customer信息或者serviceId，返回service列表
 *  就是印度的CCI接口
 * @author qianshihua
 * @version 2014-06-27 更新：根据customerId或者serviceId来查询
 * 
 */
@Service("hw0035")
@Action(bizcode = "hw0035", userCheck = true)
@Scope("prototype")
public class HW0035Action extends
		AbstractYDBaseActionHandler<HW0035Request, HW0035Response> {

	@Autowired
	private YD0021Action yd0021;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void doAction() throws BusinessException, SystemException,Exception {
		
		YD0021Request yd0021Request = new YD0021Request();
		response = new HW0035Response();
		List list = new ArrayList();
		response.setServices(list); 
		if(request.getCustomerIds()!=null && request.getCustomerIds().size()>0){
			request.getCustomerIds().add("1100010300");
			for (Iterator it = request.getCustomerIds().iterator(); it.hasNext();) {
				String customerId = (String) it.next();
				yd0021Request.setCustomerId(customerId);
				fetchService(yd0021Request, list);
			}
		}else if(request.getServiceIds()!=null && request.getServiceIds().size()>0){
			for (Iterator it = request.getServiceIds().iterator(); it.hasNext();) {
				String serviceId = (String) it.next();
				yd0021Request.setServiceId(serviceId);
				fetchService(yd0021Request, list);
			}
		}

	}

	private void fetchService(YD0021Request yd0021Request, List m) throws Exception 
	{
		YD0021Response get2Tibco = yd0021.get2Tibco(yd0021Request.returnGetParam());
		String customerId = get2Tibco.getCustomerId();
		if (get2Tibco.getAccounts() != null
				&& get2Tibco.getAccounts().size() > 0) {
			for (Iterator itACC = get2Tibco.getAccounts().iterator(); itACC
					.hasNext();) {
				Account acc = (Account) itACC.next();
				if (acc.getServicePackage() != null
						&& acc.getServicePackage().size() > 0) {
					for (Iterator itSP = acc.getServicePackage().iterator(); itSP
							.hasNext();) {
						ServicePackage sp = (ServicePackage) itSP.next();
						if (sp.getServices() != null
								&& sp.getServices().size() > 0) {
							for (Iterator itSer = sp.getServices()
									.iterator(); itSer.hasNext();) {
								Services ser = (Services) itSer.next();
								Identifier idf = ser.getIdentifier();
								if (idf != null) {
									com.ailk.yd.mapp.client.model.HW0035Response.Service s = new com.ailk.yd.mapp.client.model.HW0035Response.Service();
									s.setCategory(idf.getCategory());
									s.setName(idf.getName());
									s.setSubCategory(idf.getCategory());
									s.setType(idf.getType());
									s.setValue(idf.getValue());
									// 下面三个字段从上一层取
									s.setProductCode(ser.getProductCode());
									s.setProductName(ser.getProductName());
									s.setServiceStatus(ser
											.getServiceStatus());
									s.setCustomerId(customerId);
									m.add(s);
								}
							}
						}
					}
				}

			}
		}
	}

}
