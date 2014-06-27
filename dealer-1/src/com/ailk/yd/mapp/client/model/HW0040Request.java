package com.ailk.yd.mapp.client.model;

import java.util.ArrayList;
import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

public class HW0040Request extends YDBody {
	private List<String> customerIds;

	public List<String> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(List<String> customerIds) {
		this.customerIds = customerIds;
	}
	
	public void addCustomerId(String cId){
		if(this.customerIds==null){
			this.customerIds = new ArrayList();
		}
		this.customerIds.add(cId);
	}
	

}
