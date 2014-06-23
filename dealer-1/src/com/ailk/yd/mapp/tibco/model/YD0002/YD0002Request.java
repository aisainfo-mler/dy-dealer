package com.ailk.yd.mapp.tibco.model.YD0002;

import java.util.ArrayList;
import java.util.List;

import com.ailk.yd.mapp.tibco.model.TibcoRequest;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-4-28 下午03:20:38
 * 类说明:号码预占
 */

public class YD0002Request implements TibcoRequest{
	
	/**
	 * 订单号
	 */
	private Order serviceProviderEmployee;
	
	private Channel businessChannelInteraction;
	
	private List<SvcNumber> numberList;
	
	
	public void setOrderNumber(String orn){
		serviceProviderEmployee = new Order();
		serviceProviderEmployee.setEmployeeId(orn);
	}
	
	public void setChannel(String chnl){
		businessChannelInteraction=new Channel();
		businessChannelInteraction.setName(chnl);
	}
	
	public void setSvcNum(String num){
		numberList = new ArrayList();
		SvcNumber ele = new SvcNumber();
		ele.setValue(num);
		ele.setType("MSISDN");
		numberList.add(ele);
	}
	
	public static class Order{
		
		private String employeeId;

		public String getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}
		
	}
	
	public static class Channel{
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	public static class SvcNumber{
		private String name;
		private String value;
		private String type;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	}

	public Order getServiceProviderEmployee() {
		return serviceProviderEmployee;
	}

	public Channel getBusinessChannelInteraction() {
		return businessChannelInteraction;
	}

	public List<SvcNumber> getNumberList() {
		return numberList;
	}

	public void setServiceProviderEmployee(Order serviceProviderEmployee) {
		this.serviceProviderEmployee = serviceProviderEmployee;
	}

	public void setBusinessChannelInteraction(Channel businessChannelInteraction) {
		this.businessChannelInteraction = businessChannelInteraction;
	}

	public void setNumberList(List<SvcNumber> numberList) {
		this.numberList = numberList;
	}
	
	
	
	
}
