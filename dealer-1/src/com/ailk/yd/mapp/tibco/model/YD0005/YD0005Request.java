package com.ailk.yd.mapp.tibco.model.YD0005;

import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-4-28 下午05:29:16
 * 类说明:算费
 */

public class YD0005Request extends YDBody{
	/**
	 * sim卡号
	 */
	private String sim;
	/**
	 * 终端串号
	 */
	private String imei;
	/**
	 * 号码
	 */
	private String mdn;
	/**
	 * 类型：0 预付费，1,带终端后付费 ，2，后付费 sim，3 data-sim 都是不带终端的 9是bolt-on套餐
	 */
	private String productType;
	/**
	 * 订购的产品
	 */
	private List<Long> products;
	
	public String getSim() {
		return sim;
	}
	public void setSim(String sim) {
		this.sim = sim;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getMdn() {
		return mdn;
	}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public List<Long> getProducts() {
		return products;
	}
	public void setProducts(List<Long> products) {
		this.products = products;
	}
	public YD0005Request(String sim, String imei, String mdn,
			String productType, List<Long> products) {
		super();
		this.sim = sim;
		this.imei = imei;
		this.mdn = mdn;
		this.productType = productType;
		this.products = products;
	}
	public YD0005Request() {
		super();
	}
}
