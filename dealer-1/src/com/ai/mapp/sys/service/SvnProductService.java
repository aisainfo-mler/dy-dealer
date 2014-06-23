package com.ai.mapp.sys.service;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.sys.dao.SvnProductDao;
import com.ai.mapp.sys.entity.SvnProduct;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.SYSConstant;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-23 上午11:44:57
 * 类说明:
 */

@Service
@Transactional
public class SvnProductService {
	public final Log log = LogFactory.getLog(SvnProductService.class);
	
	@Autowired
	private SvnProductDao svnProductDao;
	
	public Collection<SvnProduct> listSvnProducts(SvnProduct svnProduct,int start,int limit)throws Exception{
		try{
			log.debug(svnProduct==null?"svnProduct is null":svnProduct.toString());
			
			Collection<SvnProduct> c = null;
			if(start < 0){
				c = svnProductDao.listAll(svnProduct);
			}else{
				c = svnProductDao.list(svnProduct, start, limit);
			}
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveSvnProduct(SvnProduct svnProduct)throws Exception{
		log.debug(svnProduct==null?"svnProduct is null":svnProduct.toString());
		svnProductDao.save(svnProduct);
	}
	
	public void deleteSvnProduct(SvnProduct svnProduct)throws Exception{
		log.debug(svnProduct==null?"svnProduct is null":svnProduct.toString());
		svnProductDao.delete(svnProduct);
	}
	
	public int countSvnProduct(SvnProduct svnProduct) throws Exception{
		return svnProductDao.count(svnProduct);
	}
	
	public SvnProduct loadSvnProduct(Long id)throws Exception{
		return svnProductDao.get(id);
	}
		
	public Collection<SvnProduct> getValidSvnProductByUserId(Long userId) throws Exception
	{
		if(userId == null)
			throw new Exception(LanguageInfo.USER_ID_EMPTY);
		
		SvnProduct condition = new SvnProduct();
		condition.setUser(new User(userId));
		condition.setStatus(SYSConstant.STATE_VALID);
		
		return listSvnProducts(condition, -1, 0);
		
	}
	
}
