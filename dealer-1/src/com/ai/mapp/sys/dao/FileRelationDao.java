package com.ai.mapp.sys.dao;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.FileRelation;

/**
 * @author Zhengwj 
 * @version ??��?��?�?012-8-16 �??03:06:36
 * 类�???
 */


@Repository("mappFileRelationDao")
public class FileRelationDao extends HibernateDao<FileRelation, Serializable>{

	@Override
	public Criteria createCriteria(Criteria c, FileRelation t)
			throws Exception {
		if(t == null){
			return c;
		}
		
		if(t.getFileUpload() != null)
		{
			c.createAlias("fileUpload", "fileUpload", CriteriaSpecification.LEFT_JOIN);
			
			if(t.getFileUpload().getFileId() != null){
				c.add(Restrictions.eq("fileUpload.fileId",t.getFileUpload().getFileId()));
			}
			
			if(StringUtil.isEmpty(t.getFileUpload().getFileType()) == false){
				c.add(Restrictions.eq("fileUpload.fileType",t.getFileUpload().getFileType()));
			}
			
			if(StringUtil.isEmpty(t.getFileUpload().getFileExt()) == false)
			{
				c.add(Restrictions.eq("fileUpload.fileExt",t.getFileUpload().getFileExt()));
			}
			
			if(StringUtil.isEmpty(t.getFileUpload().getAppVersion()) == false)
			{
				c.add(Restrictions.eq("fileUpload.appVersion",t.getFileUpload().getAppVersion()));
			}
			
			
		}
		
		if(!StringUtil.isEmpty(t.getTableDomain()))
		{
			c.add(Restrictions.eq("tableDomain", t.getTableDomain()));
		}
		
		if(t.getObjectId() != null)
		{
			c.add(Restrictions.eq("objectId", t.getObjectId()));
		}
		
		if(!StringUtil.isEmpty(t.getStatus()))
		{
			c.add(Restrictions.eq("status", t.getStatus()));
		}
		
		if(t.getId() != null)
		{
			c.add(Restrictions.eq("id", t.getId()));
		}
		
		if(t.getObjectIds() != null && t.getObjectIds().size() >0)
		{
			c.add(Restrictions.in("objectId", t.getObjectIds()));
		}
		
		c.addOrder(Order.asc("fileIndex"));
		return c;
	}
	
}
