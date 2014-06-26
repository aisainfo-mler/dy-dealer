package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
 * 查询账户信息. 2014-06-26更新：根据多个customer信息，返回service列表
 * 
 * @author qianshihua
 * 
 */
@Service("hw0035")
@Action(bizcode = "hw0035", userCheck = true)
public class HW0035Action extends
		AbstractYDBaseActionHandler<HW0035Request, HW0035Response> {

	@Autowired
	private YD0021Action yd0021;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		YD0021Request yd0021Request = new YD0021Request();
		response = new HW0035Response();
		Map m = new HashMap();
		response.setServices(m);
		for (Iterator it = request.getCustomerIds().iterator(); it.hasNext();) {
			String customerId = (String) it.next();
			yd0021Request.setCustomerId(customerId);
			YD0021Response get2Tibco = yd0021.get2Tibco(yd0021Request
					.returnGetParam());
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
										if (m.containsKey(customerId)) {
											((List) m.get(customerId)).add(s);
										} else {
											List l = new ArrayList();
											l.add(s);
											m.put(customerId, l);
										}

									}
								}
							}
						}
					}

				}
			} else {
				m.put(customerId, new ArrayList());
			}

		}

	}

}
