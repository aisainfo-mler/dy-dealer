package com.ai.mapp.sys.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.entity.User;

public class AgentOrderTaskThread extends Thread {

	private List<User> creators;
	
	private List<Product> products;
	
	private AgentOrderService agentOrderService;
	
	private int start;
	
	private int size;
	
	private Date createTime;
	
	public AgentOrderTaskThread(AgentOrderService agentOrderService,int start,List<Product> products,List<User> creators,Date createTime,int size) {
		this.agentOrderService = agentOrderService;
		this.products = products;
		this.creators = creators;
		this.start = start;
		this.size = size;
		this.createTime = createTime;
	}
	
	@Override
	public void run() {
		try {
			
			agentOrderService.createNewAgentOrderTest(start, products, creators, createTime, size);
			
			System.out.println("completed thread : "+ getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
