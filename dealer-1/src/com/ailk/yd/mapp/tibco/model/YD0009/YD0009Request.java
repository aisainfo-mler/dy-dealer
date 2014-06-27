package com.ailk.yd.mapp.tibco.model.YD0009;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ailk.yd.mapp.model.YDBody;
import com.ailk.yd.mapp.tibco.model.TibcoRequest;

/**
 * @author Zhengwj
 * @version 创建时间：2014-4-28 下午06:20:15 *
 * @version 2014-06-26 更新 类说明:充值
 */

public class YD0009Request implements TibcoRequest {


	
	
	private List<OrderDetail> orderDetails;
	private String refillId;
	private PaymentDetails paymentDetails;
	private String transactionDateTime;

	public static class OrderDetail {
		private String amount;
		private List<Characteristic> characteristics;
		private List<Product> products;
		private String circleId;
		private String productId;
		private String accountId;

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public List<Characteristic> getCharacteristics() {
			return characteristics;
		}

		public void setCharacteristics(List<Characteristic> characteristics) {
			this.characteristics = characteristics;
		}

		public List<Product> getProducts() {
			return products;
		}

		public void setProducts(List<Product> products) {
			this.products = products;
		}

		public String getCircleId() {
			return circleId;
		}

		public void setCircleId(String circleId) {
			this.circleId = circleId;
		}

		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public String getAccountId() {
			return accountId;
		}

		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}

	}

	public static class Characteristic {
		private String name;
		private String value;

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

		public Characteristic() {
			super();
		}

		public Characteristic(String name, String value) {
			super();
			this.name = name;
			this.value = value;
		}
	}

	public static class Product {
		private String serviceId;
		/**
		 * Type of Service "LTE-Fixed, LTE Mobile, FTTx, WiFi, MOBITV, ECS
		 * Channels need not provide this value"
		 */
		private List<Service> services;

		public String getServiceId() {
			return serviceId;
		}

		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
		}

		public List<Service> getServices() {
			return services;
		}

		public void setServices(List<Service> services) {
			this.services = services;
		}
	}

	public static class Service {
		private String serviceType;

		public String getServiceType() {
			return serviceType;
		}

		public void setServiceType(String serviceType) {
			this.serviceType = serviceType;
		}

		public Service(String serviceType) {
			super();
			this.serviceType = serviceType;
		}
	}

	public static class PaymentDetails {
		private String cpTransactionId;
		private String totalAmount;
		private String modeOfPayment;
		private String gatewayTransId;

		public String getCpTransactionId() {
			return cpTransactionId;
		}

		public void setCpTransactionId(String cpTransactionId) {
			this.cpTransactionId = cpTransactionId;
		}

		public String getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(String totalAmount) {
			this.totalAmount = totalAmount;
		}

		public String getModeOfPayment() {
			return modeOfPayment;
		}

		public void setModeOfPayment(String modeOfPayment) {
			this.modeOfPayment = modeOfPayment;
		}

		public String getGatewayTransId() {
			return gatewayTransId;
		}

		public void setGatewayTransId(String gatewayTransId) {
			this.gatewayTransId = gatewayTransId;
		}

	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String getRefillId() {
		return refillId;
	}

	public void setRefillId(String refillId) {
		this.refillId = refillId;
	}

	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public String getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(String transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public static void main(String[] args) throws JsonGenerationException,
			JsonMappingException, IOException {
		YD0009Request yd9 = new YD0009Request();
		yd9.setRefillId("BR000000LFR1");
		yd9.setTransactionDateTime(new Date().toString());
		List ods = new ArrayList();
		yd9.setOrderDetails(ods);
		OrderDetail od = new OrderDetail();
		ods.add(od);
		od.setAmount("200");
		od.setCircleId("tc");
		od.setProductId("11");
		od.setCharacteristics(new ArrayList() {
			{
				new Characteristic("TOPUPSHARED_IND", "ALL");
			}
		});
		List ps = new ArrayList();
		Product p = new Product();
		p.setServiceId("18957116664");
		p.setServices(new ArrayList() {
			{
				add(new Service("toppUp"));
			}
		});
		ps.add(p);
		od.setProducts(ps);
		System.err.println(new ObjectMapper().writeValueAsString(yd9));
		;
	}

	public YD0009Request() {
		super();
	}

	/**
	 * @param serviceId 
	 * @param amount
	 * @param refId 订单编号
	 * @param circleId
	 * @param accountLevel 是否是accountLevel
	 */
	public YD0009Request(String serviceId,String amount,String refId,String circleId,boolean accountLevel) {
		this.setRefillId(refId);
		List<OrderDetail>  ods = new ArrayList();
		this.setOrderDetails(ods);
		OrderDetail od = new OrderDetail();
		ods.add(od);
		od.setAmount(amount);
		od.setCircleId(circleId);
		List<Product> ps = new ArrayList();
		Product p = new Product();
		p.setServiceId(serviceId);
		Service s = new Service("");
		List li = new ArrayList();
		li.add(s);
		p.setServices(li);
		ps.add(p);
		od.setProducts(ps);
		if(accountLevel==true){
			Characteristic c = new Characteristic();
			c.setName("TOPUPSHARED_IND");
			c.setValue("ALL");
			List l = new ArrayList();
			l.add(c);
			od.setCharacteristics(l);
		}
		
	}

}
