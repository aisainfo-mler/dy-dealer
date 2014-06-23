package com.ai.mapp.sys.service;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.sys.dao.OrderDetailDao;
import com.ai.mapp.sys.entity.OrderDetail;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-20 上午11:15:09
 * 类说明:
 */
@Service
@Transactional
public class OrderDetailService {
	public final Log log = LogFactory.getLog(OrderDetailService.class);
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	public Collection<OrderDetail> listOrderDetails(OrderDetail orderDetail,int start,int limit)throws Exception{
		try{
			log.debug(orderDetail==null?"orderDetail is null":orderDetail.toString());
			
			Collection<OrderDetail> c = null;
			if(start < 0){
				c = orderDetailDao.listAll(orderDetail);
			}else{
				c = orderDetailDao.list(orderDetail, start, limit);
			}
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveOrderDetail(OrderDetail orderDetail)throws Exception{
		log.debug(orderDetail==null?"orderDetail is null":orderDetail.toString());
		orderDetailDao.save(orderDetail);
	}
	
	public void deleteOrderDetail(OrderDetail orderDetail)throws Exception{
		log.debug(orderDetail==null?"orderDetail is null":orderDetail.toString());
		orderDetailDao.delete(orderDetail);
	}
	
	public int countOrderDetail(OrderDetail orderDetail) throws Exception{
		return orderDetailDao.count(orderDetail);
	}
	
	public OrderDetail loadOrderDetail(Long id)throws Exception{
		return orderDetailDao.get(id);
	}
}
