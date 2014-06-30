package com.ailk.yd.mapp.tibco.model.YD0002;

import com.ailk.yd.mapp.model.YDBody;
import com.ailk.yd.mapp.tibco.model.TibcoRequest;

public class YD0002Response extends YDBody {

	private String serviceProviderEmployee;
	private CustomerAccountIdentifier customerAccountIdentifier;
	private Status response;
	private String message;
	
	public String getServiceProviderEmployee() {
		return serviceProviderEmployee;
	}

	public void setServiceProviderEmployee(String serviceProviderEmployee) {
		this.serviceProviderEmployee = serviceProviderEmployee;
	}

	public CustomerAccountIdentifier getCustomerAccountIdentifier() {
		return customerAccountIdentifier;
	}

	public void setCustomerAccountIdentifier(
			CustomerAccountIdentifier customerAccountIdentifier) {
		this.customerAccountIdentifier = customerAccountIdentifier;
	}

	public Status getResponse() {
		return response;
	}

	public void setResponse(Status response) {
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static class CustomerAccountIdentifier  implements TibcoRequest 
	{
		private String value;
		private String name;
		private String type;
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	}
	
	public static class Status  implements TibcoRequest 
	{
		private String interactionStatus;

		public Status() {
			super();
		}
		
		public Status(String interactionStatus) {
			super();
			this.interactionStatus = interactionStatus;
		}

		public String getInteractionStatus() {
			return interactionStatus;
		}

		public void setInteractionStatus(String interactionStatus) {
			this.interactionStatus = interactionStatus;
		}
	}
	
}
