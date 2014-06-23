package com.ai.mapp.sys.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.FileUpload;

/**
 * @author zwj
 * @version ??��?��?�?012-4-5 �??07:08:36
 * 类�???
 */

@Repository("mappFileUploadDao")
public class FileUploadDao extends HibernateDao<FileUpload, Serializable>{

	@Override
	public Criteria createCriteria(Criteria c, FileUpload mappFileUpload) {
		if(mappFileUpload == null){
			return c;
		}

		if(mappFileUpload.getFileMappingId() != null){
			c.add(Restrictions.eq("fileMappingId",mappFileUpload.getFileMappingId() ));
		}
		if(!StringUtil.isEmpty(mappFileUpload.getAppVersion())){
			c.add(Restrictions.eq("appVersion", mappFileUpload.getAppVersion()));
		}
		if(!StringUtil.isEmpty(mappFileUpload.getFileType())){
			c.add(Restrictions.eq("fileType", mappFileUpload.getFileType()));
		}
		if(!StringUtil.isEmpty(mappFileUpload.getUseDomain())){
			c.add(Restrictions.eq("useDomain", mappFileUpload.getUseDomain()));
		}
		if(!StringUtil.isEmpty(mappFileUpload.getFileStat())){
			c.add(Restrictions.eq("fileStat", mappFileUpload.getFileStat()));
		}
		if(!StringUtil.isEmpty(mappFileUpload.getFileExt())){
			c.add(Restrictions.eq("fileExt", mappFileUpload.getFileExt()));
		}
		if(mappFileUpload.getParent() != null && mappFileUpload.getParent().getFileId() != null){
			c.add(Restrictions.eq("parent.fileId", mappFileUpload.getParent().getFileId()));
		}
		if(!StringUtil.isEmpty(mappFileUpload.getFileSystem())){
			c.add(Restrictions.eq("fileSystem", mappFileUpload.getFileSystem()));
		}
		
		c.addOrder(Order.asc("fileIndex"));
		return c;
	}
	
	/**
	 * �?????件�?�?????
	 * @param ids
	 * @param useDomain
	 * @throws Exception
	 */
	public void updateFileUploadValid(Collection<Long> ids,String useDomain) throws Exception
	{
		if(ids == null || ids.isEmpty())
			return;
		
		String fileIds = "";
		
		for(Long id : ids)
		{
			fileIds = fileIds.isEmpty()?id.toString():fileIds+","+id;
		}
		
		String hql = "update MappFileUpload obj set status = 1 where obj.fileId in :fileIds";
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("fileIds", fileIds);
		
		excute(hql, params);
		
	}
	
	/**
	 * <p>?��?mapp_file_relation???mapp_file_upload </p> 
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       List<MappFileUpload>    
	 * @author        Zhengwj
	 * @Date          2012-8-17 �??10:41:11
	 */
	public List<FileUpload> listFileByRelation(FileUpload mappFileUpload,String table_domain,String object_id)throws Exception{
		if(StringUtil.isEmpty(table_domain)){
			return new ArrayList<FileUpload>();
		}
		Criteria criteria = getSession().createCriteria(entityClass);
		if(StringUtil.isEmpty(object_id)){
			criteria.add(Restrictions.sqlRestriction(" exists ( select 1 from mapp_file_relation r where {alias}.file_id=r.file_id and r.table_domain = ? ) ", table_domain, Hibernate.STRING));
		}else{
			criteria.add(Restrictions.sqlRestriction(" exists ( select 1 from mapp_file_relation r where {alias}.file_id=r.file_id and r.table_domain = '" + table_domain + "' and r.object_id = ? ) ", object_id, Hibernate.STRING));
		}
		
		if(mappFileUpload != null)
		{
			if(!StringUtil.isEmpty(mappFileUpload.getFileType())){
				criteria.add(Restrictions.eq("fileType", mappFileUpload.getFileType()));
			}
			if(!StringUtil.isEmpty(mappFileUpload.getFileStat())){
				criteria.add(Restrictions.eq("fileStat", mappFileUpload.getFileStat()));
			}
			if(!StringUtil.isEmpty(mappFileUpload.getFileExt())){
				criteria.add(Restrictions.eq("fileExt", mappFileUpload.getFileExt()));
			}
			if(!StringUtil.isEmpty(mappFileUpload.getFileSystem())){
				criteria.add(Restrictions.eq("fileSystem", mappFileUpload.getFileSystem()));
			}
		}
		List<FileUpload> list = criteria.list();
		return list;
	}
	
	
	public Long saveMappFileUpload(FileUpload mappFileUpload)throws Exception{
		save(mappFileUpload);
		return mappFileUpload.getFileId();
	}

}
