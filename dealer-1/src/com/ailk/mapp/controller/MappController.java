package com.ailk.mapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.mapp.sys.service.DealerDataService;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.mapp.core.MappConstant;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.client.JsonClientHandler;
import com.ailk.butterfly.sys.dal.ibatis.model.IUserInfo;
import com.ailk.web.BaseController;
import com.ailk.yd.mapp.model.UserInfo;
import com.ailk.yd.mapp.model.YDDatapackage;

@Controller
@RequestMapping("/mapp")
public class MappController extends BaseController {
	public final Log log = LogFactory.getLog(MappController.class);
	
	@Autowired
	private JsonClientHandler<YDDatapackage> jsonHandler;
	
	@Autowired
	private DealerDataService dealerDataService;
	
	@RequestMapping(value = {"/json"})
	@ResponseBody
	public String getJsonRsp(String msg, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{		
		
//		try
//		{
//			String path = TibcoUtil.class.getResource("/").getPath();
//			File file = new File(path+"/tibco_product.xml");
//			SAXReader saxReader = new SAXReader();
//			Document document = saxReader.read(file);
//			dealerDataService.updateProps(document.getRootElement());
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
		
//		System.out.println(msg);
		MappContext.clearContext();
		
		Map<String,Object> attrMap = new HashMap<String, Object>(0);
		attrMap.put(MappContext.MAPPCONTEXT_REQUEST_IP,request.getRemoteHost());
		attrMap.put(MappContext.MAPPCONTEXT_SESSIONID, request.getSession().getId());
		IUserinfo user = new UserInfo();
		user.setUserId(1L);
		user.setUserName("m01");
		attrMap.put(MappContext.MAPPCONTEXT_USER, user);
		
		IUserinfo u = new UserInfo();
		u.setUserId(1l);
		u.setUserName("m01");
		
		attrMap.put(MappContext.MAPPCONTEXT_USER, u);
		
		try
		{
			
			log.debug(request.getContentType());
			
			String ret = jsonHandler.doHandle(msg,attrMap);
			
			updateSession(request);
			
			log.debug(ret);
			
			log.debug(response.getContentType());
			
			return ret;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private void updateSession(HttpServletRequest request) {
		
		System.out.println("==================MappContext.context:"+MappContext.getContext().hashCode()+"======================");
		
		for(String key : MappConstant.sessionKeys)
		{
			if(MappContext.getAttribute(key) != null)
			{
				if(MappContext.getAttribute(key) instanceof IUserInfo)
				{
					System.out.println(((IUserInfo)MappContext.getAttribute(key)).getUserName()+"======"+((IUserInfo)MappContext.getAttribute(key)).getUser().getUserLoginName());
				}
				request.getSession().setAttribute(key, MappContext.getAttribute(key));
			}
		}
	}
	
//	@RequestMapping(value = {"/payCallBack"},produces="text/html;charset=UTF-8")
//	public String payCallBack(@RequestParam(value="ORDER_ID",required=false) String orderId,
//			@RequestParam(value="TRANS_IDO",required=false) String transIdo,
//			@RequestParam(value="TOTAL_FEE",required=false) String totalFee,
//			@RequestParam(value="RSP_CODE") String rspCode,
//			@RequestParam(value="RSP_DESC",required=false) String rspMsg,
//			@RequestParam(value="SIGN") String sign, ModelMap model) throws Exception 
//	{
//		System.out.println("ORDER_ID:"+orderId+",TRANS_IDO:"+transIdo+",TOTAL_FEE:"+totalFee+",RSP_CODE:"+rspCode+",RSP_DESC:"+rspMsg);
//		
//		if(sign.endsWith(UmsPayInfo.getSignWithKey(orderId+transIdo+totalFee+rspCode+rspMsg)) == false)
//			new SystemException(ErrorCodeDefine.UNKNOW_ERROR,"签名验证失败");
//		
//		System.out.println("returnUrl payCallBack is ok");
//		model.put("orderid", orderId);
//		model.put("rspCode", rspCode);
//		return "payCallBack";  
//	}
	
}
