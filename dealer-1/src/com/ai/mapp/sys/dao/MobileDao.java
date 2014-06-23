package com.ai.mapp.sys.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.Mobile;
import com.ai.mapp.sys.entity.Product;

@Repository("mappMobileDao")
public class MobileDao extends HibernateDao<Mobile, Long> {

	@Override
	public Criteria createCriteria(Criteria c, Mobile t) {
		
		//TODO 完成手机搜索条件拼装
		if( t == null) return c;
		
		if(t.getMobileId() != null)
		{
			c.add(Restrictions.eq("mobileId", t.getMobileId()));
		}
		if(StringUtil.isEmpty(t.getBrand()) == false)
		{
			c.add(Restrictions.eq("brand", t.getBrand()));
		}
		if(StringUtil.isEmpty(t.getBssMobileId()) == false)
		{
			c.add(Restrictions.eq("bssMobileId", t.getBssMobileId()));
		}
//		if(t.getMobileModel() != null && t.getMobileModel() != null)
//		{
//			c.add(Restrictions.eq("mobileModel", t.getMobileModel()));
//		}
		if(StringUtil.isEmpty(t.getMobileModel()) == false)
		{
			c.add(Restrictions.like("mobileModel", t.getMobileModel(),MatchMode.ANYWHERE).ignoreCase());
		}
		// 价格范围
		if ( StringUtil.isEmpty( t.getPriceRange() ) == false ) {
			String[] price = t.getPriceRange().split("-");
			if ( price != null && price.length == 2 ) {
				Long priceLow = new Long(price[0]);  // 价格：低值
				Long priceHigh = new Long(price[1]); // 价格：高值
				c.add( Restrictions.ge( "price", priceLow ) ); // 大于等于 价格低值
				if ( priceHigh != 0 ) { // 价格高值为0，表示无上限
					c.add( Restrictions.le( "price", priceHigh ) ); // 小于等于 价格高值
				}
			}
		}
		
		// 操作系统
		if ( StringUtil.isEmpty( t.getOs() ) == false ) {
			c.add( Restrictions.eq( "os", t.getOs() ) );
		}
		
		// 优惠活动
		if ( StringUtil.isEmpty( t.getPromotion() ) == false) {
			c.add( Restrictions.eq( "promotion", t.getPromotion() ) );
		}
		
		// 终端特点
		if ( StringUtil.isEmpty( t.getFeature() ) == false) {
			c.add( Restrictions.eq( "feature", t.getFeature() ) );
		}
		
		if (t.getIds() != null && t.getIds().isEmpty() == false) {
			c.add( Restrictions.in("mobileId", t.getIds()) );
		}
		
		if(Mobile.SPECIALSEARCH_POSTPAY_HAS_PRODUCT.equals(t.getSpecialSearch()))
		{
			c.add(Restrictions.sqlRestriction(" EXISTS ( SELECT * FROM HW_PRODUCT k WHERE k.MOBILEID = {alias}.MOBILEID ) "));
		}
		
		c.addOrder(Order.asc("mobileId"));
	
		return c;
	}

	
	
}
