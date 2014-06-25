package com.ailk.yd.mapp.client.model;

import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

public class HW0007Response extends YDBody {

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
