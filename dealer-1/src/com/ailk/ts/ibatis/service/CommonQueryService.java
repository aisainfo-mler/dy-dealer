package com.ailk.ts.ibatis.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ailk.ts.dal.ibatis.CommonQueryDAO;
import com.ailk.ts.dal.ibatis.model.SelfDefineRep;


@Service
@Transactional(rollbackFor=Exception.class)
public class CommonQueryService {	
	private static final Logger log = LoggerFactory.getLogger(CommonQueryService.class);
	public CommonQueryDAO getCommonQueryDAO() {
		return commonQueryDAO;
	}
	public void setCommonQueryDAO(CommonQueryDAO commonQueryDAO) {
		this.commonQueryDAO = commonQueryDAO;
	}
	@Autowired
	private CommonQueryDAO commonQueryDAO;
	
	//querySysDate 查询系统时间
	public List querySysDate() {
		// TODO Auto-generated method stub
		Map paraMap=new java.util.HashMap();
		List result=commonQueryDAO.getModels(paraMap, "addUtilsSQL.querySysDate");
		return result;
	}
	//querybrand 查询品牌
	public List querybrand(String BRAND_ID,String PARA_TYPE_ID,Integer limitClauseStart,Integer limitClauseCount) {
		// TODO Auto-generated method stub
		Map paraMap=new java.util.HashMap();
		paraMap.put("BRAND_ID", BRAND_ID);
		paraMap.put("PARA_TYPE_ID", PARA_TYPE_ID);
		paraMap.put("limitClauseStart", limitClauseStart);
		paraMap.put("limitClauseCount", limitClauseCount);
		List result=commonQueryDAO.getModels(paraMap, "addUtilsSQL.querybrand");
		return result;
	}
	//queryProductAD 独立广告位商品
	public List queryProductAD(String POSITION_TYPE) {
		// TODO Auto-generated method stub
		Map paraMap=new java.util.HashMap();
		paraMap.put("POSITION_TYPE", POSITION_TYPE);
		List result=commonQueryDAO.getModels(paraMap, "addUtilsSQL.queryProductAD");
		return result;
	}
	
	///querySpecial 查询特殊商品信息
	public List querySpecial(String SPE_TYPE,Integer SKUID,String CATEGORY,Integer limitClauseStart,Integer limitClauseCount) {
		// TODO Auto-generated method stub
		Map paraMap=new java.util.HashMap();
		paraMap.put("SPE_TYPE", SPE_TYPE);
		paraMap.put("SKUID", SKUID);
		paraMap.put("CATEGORY", CATEGORY);
		paraMap.put("limitClauseStart", limitClauseStart);
		paraMap.put("limitClauseCount", limitClauseCount);
		List result=commonQueryDAO.getModels(paraMap, "addUtilsSQL.querySpecial");
		return result;
	}

	//queryPorp 查询属性
	public List queryPorp(String PROP_ID,String PRODUCT_TYPE_ID) {
		// TODO Auto-generated method stub
		Map paraMap=new java.util.HashMap();
		paraMap.put("PROP_ID", PROP_ID);
		paraMap.put("PRODUCT_TYPE_ID", PRODUCT_TYPE_ID);
		List result=commonQueryDAO.getModels(paraMap, "addUtilsSQL.queryPorp");
		return result;
	}

	//queryPorpValue 查询属性可选值
	public List queryPorpValue(String VALUE_ID,String PROP_ID,String VALUE,Integer limitClauseStart,Integer limitClauseCount) {
		// TODO Auto-generated method stub
		Map paraMap=new java.util.HashMap();
		paraMap.put("VALUE_ID", VALUE_ID);
		paraMap.put("PROP_ID", PROP_ID);
		paraMap.put("VALUE", VALUE);
		paraMap.put("limitClauseStart", limitClauseStart);
		paraMap.put("limitClauseCount", limitClauseCount);
		List result=commonQueryDAO.getModels(paraMap, "addUtilsSQL.queryPorpValue");
		return result;
	}

	//querySKU 查询商品SKU信息
	public List querySKU(String SKUID,String PRODUCT_ID,String SKU_NAME,Integer limitClauseStart,Integer limitClauseCount) {
		// TODO Auto-generated method stub
		Map paraMap=new java.util.HashMap();
		paraMap.put("SKUID", SKUID);
		paraMap.put("PRODUCT_ID", PRODUCT_ID);
		paraMap.put("SKU_NAME", SKU_NAME);
		paraMap.put("limitClauseStart", limitClauseStart);
		paraMap.put("limitClauseCount", limitClauseCount);
		List result=commonQueryDAO.getModels(paraMap, "addUtilsSQL.querySKU");
		return result;
	}
	//querySKUPropValue 查询商品SKU属性值信息
	public List querySKUPropValue(String SKUID,String PROP_ID,String PROP_VALUE,Integer limitClauseStart,Integer limitClauseCount) {
		// TODO Auto-generated method stub
		Map paraMap=new java.util.HashMap();
		paraMap.put("SKUID", SKUID);
		paraMap.put("PROP_ID", PROP_ID);
		paraMap.put("PROP_VALUE", PROP_VALUE);
		paraMap.put("limitClauseStart", limitClauseStart);
		paraMap.put("limitClauseCount", limitClauseCount);
		List result=commonQueryDAO.getModels(paraMap, "addUtilsSQL.querySKUPropValue");
		return result;
	}
	
	//querySKUPicture 查询商品SKU的图片信息
	public List querySKUPicture(String PIC_ID,String PRODUCT_ID,String SKUID,String PIC_TYPE,String STATUS,Integer limitClauseStart,Integer limitClauseCount) {
		// TODO Auto-generated method stub
		Map paraMap=new java.util.HashMap();
		paraMap.put("PIC_ID", PIC_ID);
		paraMap.put("PRODUCT_ID", PRODUCT_ID);
		paraMap.put("SKUID", SKUID);
		paraMap.put("PIC_TYPE", PIC_TYPE);
		paraMap.put("STATUS", STATUS);
		paraMap.put("limitClauseStart", limitClauseStart);
		paraMap.put("limitClauseCount", limitClauseCount);
		List result=commonQueryDAO.getModels(paraMap, "addUtilsSQL.querySKUPicture");
		return result;
	}
	//querySKUViewPicture 查询商品SKU的浏览图片信息
	public List querySKUViewPicture(String SKUID) {
		// TODO Auto-generated method stub
		Map paraMap=new java.util.HashMap();
		paraMap.put("SKUID", SKUID);
		List result=commonQueryDAO.getModels(paraMap, "addUtilsSQL.querySKUViewPicture");
		return result;
	}
	//querySKUMainPicture 查询商品SKU的优先级别为1的浏览图片信息
	public List querySKUMainPicture(String SKUID) {
		// TODO Auto-generated method stub
		Map paraMap=new java.util.HashMap();
		paraMap.put("SKUID", SKUID);
		List result=commonQueryDAO.getModels(paraMap, "addUtilsSQL.querySKUMainPicture");
		return result;
	}
	//productSearch 综合查询商品信息
	public List productSearch(String SKUID,String PRODUCT_ID,String BRAND_ID,String LANTYPE,String FEELENGTH,String TYPE_ID,String SEARCH_VALUE) {
		// TODO Auto-generated method stub
		Map paraMap=new java.util.HashMap();
		paraMap.put("SKUID", SKUID);
		paraMap.put("PRODUCT_ID", PRODUCT_ID);
		paraMap.put("BRAND_ID", BRAND_ID);
		paraMap.put("LANTYPE", LANTYPE);
		paraMap.put("FEELENGTH", FEELENGTH);
		paraMap.put("TYPE_ID", TYPE_ID);
		paraMap.put("SEARCH_VALUE", SEARCH_VALUE);
		List result=commonQueryDAO.getModels(paraMap, "addUtilsSQL.productSearch");
		return result;
	}	
	//queryProduct 查询商品信息
	public List queryProduct(String PRODUCT_ID,String NAME,String BRAND_ID,String PRODUCT_TYPE_ID) {
		// TODO Auto-generated method stub
		Map paraMap=new java.util.HashMap();
		paraMap.put("PRODUCT_ID", PRODUCT_ID);
		paraMap.put("NAME", NAME);
		paraMap.put("BRAND_ID", BRAND_ID);
		paraMap.put("FEELENGTH", PRODUCT_TYPE_ID);
		List result=commonQueryDAO.getModels(paraMap, "addUtilsSQL.queryProduct");
		return result;
	}	
	//queryParaType 查询商品类型信息
	public List queryParaType(String TYPE_ID,String TYPE_NAME,String PARENT_TYPE_ID,Integer limitClauseStart,Integer limitClauseCount) {
		// TODO Auto-generated method stub
		Map paraMap=new java.util.HashMap();
		paraMap.put("TYPE_ID", TYPE_ID);
		paraMap.put("TYPE_NAME", TYPE_NAME);
		paraMap.put("PARENT_TYPE_ID", PARENT_TYPE_ID);
		paraMap.put("limitClauseStart", limitClauseStart);
		paraMap.put("limitClauseCount", limitClauseCount);
		List result=commonQueryDAO.getModels(paraMap, "addUtilsSQL.queryParaType");
		return result;
	}	
	
	public List querySKUPara(String SKUID,String PRODUCT_ID,String SKU_NAME,Integer limitClauseStart,Integer limitClauseCount) {
		// TODO Auto-generated method stub
		Map paraMap=new java.util.HashMap();
		paraMap.put("PRODUCT_ID", PRODUCT_ID);
		paraMap.put("limitClauseStart", limitClauseStart);
		paraMap.put("limitClauseCount", limitClauseCount);
		List result=commonQueryDAO.getModels(paraMap, "addUtilsSQL.querySKUPara");
		return result;
	}

	public List<SelfDefineRep> selectRep(Map<String, Object> cond, int start,
			int num) {
		if(cond == null) cond = new HashMap<String,Object>();
		if(start>=0&&num>=1){
			cond.put("limitClauseStart", start);
			cond.put("limitClauseCount", num);
		}
		List<SelfDefineRep> reps = commonQueryDAO.getModels(cond,"selfDefine_rep.selectSelfRep");
		return reps;
	}
}