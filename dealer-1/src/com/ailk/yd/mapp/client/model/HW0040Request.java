package com.ailk.yd.mapp.client.model;

import java.util.ArrayList;
import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

public class HW0040Request extends YDBody {
	private List<String> customerIds;
	
	/**
	 * 传ORN过来查订单  可以查到caf的PDF图片  一笔订单 如果有多个 product对应多个CAF图片
	 */
	private String orn;

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

	public String getOrn() {
		return orn;
	}

	public void setOrn(String orn) {
		this.orn = orn;
	}
}
