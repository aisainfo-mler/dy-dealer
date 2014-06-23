package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.AccountInfo;
import com.ai.mapp.sys.entity.SvnInfo;
import com.ai.mapp.sys.entity.SvnProduct;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.SvnInfoService;
import com.ai.mapp.sys.service.SvnProductService;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.SYSConstant;

@Service(value="hw0007Service")
@Scope(value="singleton")
public class Hw0007SVImpl extends ISVTemplate {
	
	@Autowired
	private SvnInfoService svnInfoService;
	
	@Autowired
	private SvnProductService svnProductService;
	
	@Autowired
	private UserService userService;

	protected Object convertResponse(ParamObject param) throws Exception 
	{
		
		com.ai.mapp.model.HW0007.Response rsp = new com.ai.mapp.model.HW0007.Response();
		
		if(param.isIfSuccess() == false)
		{
			return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR);
		}
		
		SvnInfo svnInfo = (SvnInfo)param.getResult();
		
		if(svnInfo != null)
		{
			rsp.setBalance(svnInfo.getAmount() == null ?"0":String.valueOf(svnInfo.getAmount()));
			if(svnInfo.getCustomer() != null)
			{
				rsp.setName((svnInfo.getCustomer().getFirstName() == null ? "" : svnInfo.getCustomer().getFirstName())
						+ " " + (svnInfo.getCustomer().getLastName() == null ? "" : svnInfo.getCustomer().getLastName()));
				rsp.setAddress(svnInfo.getCustomer().getAddress());
				rsp.setBirthDay(svnInfo.getCustomer().getBirthDay() == null ? "" : DateUtils.getDateString(svnInfo.getCustomer().getBirthDay(), "yyyy-MM-dd"));
				rsp.setContactPhone(svnInfo.getCustomer().getContractPhone());
				rsp.setCreatTime(svnInfo.getCustomer().getCreateTime() == null ? "" : DateUtils.getDateString(svnInfo.getCustomer().getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
				rsp.setEmail(svnInfo.getCustomer().getEmail());
				rsp.setFirstName(svnInfo.getCustomer().getFirstName());
				rsp.setLastName(svnInfo.getCustomer().getLastName());
				rsp.setGender(svnInfo.getCustomer().getGender());
				rsp.setIdCardNo(svnInfo.getCustomer().getCertificateNo());
				rsp.setIdCardType(SYSConstant.certificateTypes.get(svnInfo.getCustomer().getCertificateType() + param.getParameter(BSSConstantParam.LANGUAGE)));
				rsp.setPhoneNumber(svnInfo.getSvn());//mobileNo
				rsp.setPostCode(svnInfo.getCustomer().getPostCode());
				rsp.setStatus(svnInfo.getCustomer().getStatus());
				
				if(svnInfo.getCustomer().getAccounts() != null && svnInfo.getCustomer().getAccounts().isEmpty() == false)
				{
					AccountInfo account = svnInfo.getCustomer().getAccounts().iterator().next();
					rsp.setAmount(account.getAmount() == null ? "0" : account.getAmount().toString());
				}
				
				if(svnInfo.getSvnProducts() != null && svnInfo.getSvnProducts().isEmpty() == false)
				{
					com.ai.mapp.model.HW0007.ProductList pl = new com.ai.mapp.model.HW0007.ProductList();
					
					for(SvnProduct sp : svnInfo.getSvnProducts())
					{
						com.ai.mapp.model.HW0007.Product p = new com.ai.mapp.model.HW0007.Product();
						p.setProductId(sp.getProduct().getRangeId()+"");
						p.setProductName(sp.getProduct().getContract().getName());
						
						pl.addProduct(p);
					}
					
					rsp.setProductList(pl);
					
				}
				
			}
			
			rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);
		}
		else
		{
			return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR);
		}
		
		return rsp.toXMLString();
	}
	
	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		com.ai.mapp.model.HW0007.Request req = com.ai.mapp.model.HW0007.Request
				.unmarshal(new StringReader((String)param.getParameter(BSSConstantParam.REQUESTCONTENT)));
		
//		userService.statDealer(null,null,null, null);
		
		SvnInfo svnInfo = null;
		
		if(StringUtil.isEmpty(req.getUserCode()) == false)
		{
			User user = userService.loadDealer(req.getUserCode());
			
			svnInfo  = new SvnInfo();
			svnInfo.setCustomer(user);
		}
		else
		{
			 svnInfo = svnInfoService.loadSvnInfoBySvn(req.getMdn(),null);
			 
			 if(svnInfo != null && SYSConstant.ITEM_STATUS_UNUSE.equals(svnInfo.getStatus()))
			 {
				 svnInfo = null;
			 }
			 else if(svnInfo != null && svnInfo.getCustomer() != null )
			 {
				 Collection<SvnProduct> svnProducts = svnProductService.getValidSvnProductByUserId(svnInfo.getCustomer().getUserId());
				 svnInfo.setSvnProducts(svnProducts);
			 }
		}
		
		if(svnInfo == null)
			throw new Exception(req.getMdn()+" " + LanguageInfo.UNEXIST);
		
		param.setIfSuccess(true);
		param.setResult(svnInfo);
		return param;
	
	}
	
	@Override
	public Object error(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0007.Response rsp = new com.ai.mapp.model.HW0007.Response();
		rsp.setRspCode(BSSConstantError.CODE_DATA_ERROR);
		rsp.setMSG(param.getErrorInfo());
		
		return rsp.toXMLString();
	}
	
}
