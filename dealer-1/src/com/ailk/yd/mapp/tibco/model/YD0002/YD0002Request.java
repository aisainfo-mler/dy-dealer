package com.ailk.yd.mapp.tibco.model.YD0002;

import java.util.ArrayList;
import java.util.List;

import com.ailk.yd.mapp.tibco.model.TibcoRequest;

/**
 * @author mler 
 * @version 创建时间：2014-6-28 下午03:20:38
 * 类说明:号码预占
 */

public class YD0002Request implements TibcoRequest{
	
	/**
	 * 订单号
	 */
	private Order serviceProviderEmployee;
	
	private Channel businessChannelInteraction;
	
	private List<SvcNumber> numberList;
	
	public static class Order  implements TibcoRequest {
		
		public Order() {
			super();
		}
		
		public Order(String employeeId) {
			super();
			this.employeeId = employeeId;
		}

		private String employeeId;

		public String getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}
		
	}
	
	public static class Channel implements TibcoRequest 
	{
		public Channel() {
			super();
		}
		
		public Channel(String name) {
			super();
			this.name = name;
		}

		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	public static class SvcNumber implements TibcoRequest{
		
		private String name;
		private String value;
		private String type;
		
		public SvcNumber() {
			super();
		}
		
		public SvcNumber(String name, String value, String type) {
			super();
			this.name = name;
			this.value = value;
			this.type = type;
		}
		
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
