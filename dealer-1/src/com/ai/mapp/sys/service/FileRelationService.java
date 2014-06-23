package com.ai.mapp.sys.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.sys.dao.FileRelationDao;
import com.ai.mapp.sys.entity.FileRelation;
import com.ai.mapp.sys.entity.FileUpload;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.SYSConstant;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-8-17 下午01:33:44
 * 类说明:
 */

@Service
@Scope("singleton")
@Transactional
public class FileRelationService {
	private final Logger log =  Logger.getLogger(FileRelationService.class);
	
	@Autowired
	private FileRelationDao fileRelationDao;
	
//	@Autowired
//	private FileUploadService fileUploadService;
	
	
	/**
	  * <p>取出全部对应文件</p> 
	  * @param mappFileUpload
	  * @return 
	  * @return Collection<FileUpload> 
	 */
	public Collection<FileRelation> listFileRelation(FileRelation fileRelation){
		try{
			log.info(fileRelation == null?"fileRelation is null":fileRelation.toString());
			
			Collection<FileRelation> c = fileRelationDao.listAll(fileRelation);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public FileRelation saveFileRelation(FileRelation fileRelation)throws Exception{
		if(fileRelation == null){
			return null;
		}else{
			//先维护数据  file_id,object_id,table_domain三者一样则只是做修改，如改file_index,file_status
			if (fileRelation.getObjectId() != null
					&& fileRelation.getFileUpload() != null
					&& fileRelation.getFileUpload().getFileId() != null
					&& fileRelation.getTableDomain() != null) 
			{
				
				List<FileRelation> list = (List<FileRelation>)fileRelationDao.listAll(fileRelation);
				
				if(list == null || list.size() == 0)
				{
					fileRelation.setCreatetime(new Date());
					fileRelationDao.save(fileRelation);
					
					return fileRelation;
				}
				else if(list != null && list.size() == 1)
				{
					FileRelation rela = list.get(0);
					rela.setCreatetime(new Date());
					rela.setCreator(fileRelation.getCreator());
					fileRelationDao.save(rela);
					
					return rela;
				}
				else
				{
					throw new Exception(LanguageInfo.RELATION_DUPLICATE);
				}
			}else{
				throw new Exception(LanguageInfo.RELATION_ERROR);
			}
		}
	}
	
	/**
	 * 获取所有title下有效的附件(包括图片)
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public Collection<FileRelation> listFileRelationByMobile(Long mobileId,String fileType) throws Exception
	{
		FileRelation mfr = new FileRelation();
		mfr.setTableDomain(SYSConstant.TABLE_HW_MOBILE_INFO);
		mfr.setObjectId(mobileId);
		mfr.setStatus(SYSConstant.STATE_VALID);
		
		if(StringUtil.isEmpty(fileType) == false)
		{
			FileUpload file = new FileUpload();
			file.setFileType(fileType);
			
			mfr.setFileUpload(file);
		}
		
		return fileRelationDao.listAll(mfr);
	}
	

	
	
	/**
	 * <p>fileRelation 只是条件</p> 
	 * @param:        @param fileRelation
	 * @param:        @throws Exception    
	 * @return:       void    
	 * @author        Zhengwj
	 * @Date          2012-8-23 下午08:26:37
	 */
	public void deleteFileRelation(FileRelation fileRelation)throws Exception{
		if(fileRelation == null){
			return;
		}else{
			//先维护数据  file_id,object_id,table_domain三者一样则只是做修改，如改file_index,file_status
			if ((fileRelation.getObjectId() != null && fileRelation.getTableDomain() != null)
					|| fileRelation.getTableDomain() != null
					|| (fileRelation.getFileUpload() != null && fileRelation.getFileUpload().getFileId() != null)) 
			{
				List<FileRelation> list = (List<FileRelation>)fileRelationDao.listAll(fileRelation);
				
				if(list != null && list.isEmpty()==false)
				{
					for(FileRelation tmp:list){
						fileRelationDao.delete(tmp);
					}
					
				}
			}
		}
	}
	
	private void deleteRelationWithFile(FileRelation relation) throws Exception
	{
		if(relation == null || relation.getId() == null)
			return;
		
		if(relation.getFileUpload() != null && relation.getFileUpload().getFileId() != null)
		{
//			FileUpload upload = fileUploadService.loadFileUploadById(relation.getFileUpload().getFileId());
			
//			if(upload != null && StringUtil.isEmpty(upload.getFilePath()) == false)
			{
//				fileUploadService.deleteAttache(relation.getFileUpload().getFileId());
			}
			
		}
		
		fileRelationDao.delete(relation);
		
	}
	
	/**
	 * 前2个条件必须有
	 * @param useDomain
	 * @param key
	 * @param fileExt
	 * @return
	 * @throws Exception
	 */
	public Collection<FileRelation> listFileRelation(String tableDomain,Long objectId,String fileExt,String version) throws Exception
	{
		
		if(StringUtil.isEmpty(tableDomain) || objectId == null || StringUtil.isEmpty(version))
			return null;
			
		FileRelation condition = new FileRelation();
		condition.setTableDomain(tableDomain);
		condition.setStatus(SYSConstant.STATE_VALID);
		condition.setObjectId(objectId);
		if(StringUtil.isEmpty(fileExt) == false || StringUtil.isEmpty(version) == false)
		{
			FileUpload file = new FileUpload();
			file.setFileExt(fileExt);
			file.setAppVersion(version);
			condition.setFileUpload(file);
		}
		
		return fileRelationDao.listAll(condition);
		
	}
	
	public FileRelation loadFileRelationById(Long relationId) throws Exception
	{
		return fileRelationDao.get(relationId);
	}
	
}
