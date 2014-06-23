package com.ai.mapp.sys.service;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.sys.dao.HwOrderShipmentDao;
import com.ai.mapp.sys.entity.HwOrderShipment;


/**
 * @author 
 * @version 
 * 类说明:
 */

@Service
@Transactional
public class HwOrderShipmentService {

	public final Log log = LogFactory.getLog(HwOrderShipmentService.class);
	
	@Autowired
	private HwOrderShipmentDao hwOrderShipmentDao;
	
	public Collection<HwOrderShipment> listHwOrderShipments(HwOrderShipment hwOrderShipment,int start,int limit)throws Exception{
		try{
			log.debug(hwOrderShipment==null?"hwOrderShipment is null":hwOrderShipment.toString());
			
			Collection<HwOrderShipment> c = null;
			if(start < 0){
				c = hwOrderShipmentDao.listAll(hwOrderShipment);
			}else{
				c = hwOrderShipmentDao.list(hwOrderShipment, start, limit);
			}
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveHwOrderShipment(HwOrderShipment hwOrderShipment)throws Exception{
		log.debug(hwOrderShipment==null?"hwOrderShipment is null":hwOrderShipment.toString());
		hwOrderShipmentDao.save(hwOrderShipment);
	}
	
	public void deleteHwOrderShipment(HwOrderShipment hwOrderShipment)throws Exception{
		log.debug(hwOrderShipment==null?"hwOrderShipment is null":hwOrderShipment.toString());
		hwOrderShipmentDao.delete(hwOrderShipment);
	}
	
	public int countHwOrderShipment(HwOrderShipment hwOrderShipment) throws Exception{
		return hwOrderShipmentDao.count(hwOrderShipment);
	}
	
	public HwOrderShipment loadHwOrderShipment(Long id)throws Exception{
		return hwOrderShipmentDao.get(id);
	}
	
	public HwOrderShipment getFirstHwOrderShipmentByOrderCode(String orderCode) throws Exception{
		HwOrderShipment hwOrderShipment=new HwOrderShipment();
		hwOrderShipment.setOrderCode(orderCode);
		hwOrderShipment.setPkIdOrderType(1);
		Collection<HwOrderShipment> hwOrderShipmentList=hwOrderShipmentDao.listAll(hwOrderShipment);
		if(hwOrderShipmentList==null || hwOrderShipmentList.isEmpty()) return null;
		return hwOrderShipmentList.iterator().next();
	}
		
}
