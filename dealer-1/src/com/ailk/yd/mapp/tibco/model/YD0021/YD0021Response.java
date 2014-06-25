package com.ailk.yd.mapp.tibco.model.YD0021;

import java.util.List;

import org.activiti.engine.impl.identity.Account;

import com.ailk.yd.mapp.client.model.TibcoAccount;
import com.ailk.yd.mapp.model.YDBody;

public class YD0021Response extends YDBody {

	private String customerId;
	private List<TibcoAccount> accounts;



	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<TibcoAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<TibcoAccount> accounts) {
		this.accounts = accounts;
	}

}
