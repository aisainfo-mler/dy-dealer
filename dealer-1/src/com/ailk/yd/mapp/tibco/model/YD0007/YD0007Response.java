package com.ailk.yd.mapp.tibco.model.YD0007;

import java.util.List;

import com.ailk.yd.mapp.client.model.TibcoCustomer;
import com.ailk.yd.mapp.model.YDBody;

/**
 * @author mler
 * @version 创建时间：2014-4-28 下午06:04:49 类说明:
 */

public class YD0007Response extends YDBody {

	private List<TibcoCustomer> customers;

	private Integer totalRecords;



	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<TibcoCustomer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<TibcoCustomer> customers) {
		this.customers = customers;
	}

}
