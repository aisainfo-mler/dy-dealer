package com.ailk.yd.mapp.aspect;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ai.mapp.base.security.DESUtils;
import com.ai.mapp.sys.entity.CallLog;
import com.ai.mapp.sys.service.CallLogService;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.core.util.GZipUtils;
import com.ailk.butterfly.mapp.core.MappConstant;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.model.IMappDatapackage;


/**
 * 请求报文日志
 * @author mler
 *
 */
@Component
@Aspect
public class YdLogAspect {
	
	Logger logger = LoggerFactory.getLogger(YdLogAspect.class);
	
//	@Value("${CRYPT_PERMISSION}")
//	private boolean cryptPermission;
//	
//	private String desKey = "MAPP_DESKEY";
	
	@Autowired
	private CallLogService callLogService;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Value("${server_key}")
	protected String serverKey;//向外部发送请求的地址
	
	/**
	 * mapp请求外部系统做日志
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.ailk.yd.mapp.client.action.ExternalRequest.sendMsg(..))")
	public Object insertCallLog(ProceedingJoinPoint point) throws Throwable
	{
		CallLog log = new CallLog();
		Object[] args = point.getArgs();
		Object result = null;
		
		try{
			result = point.proceed(args);
		}catch (Exception e) {
			/** 设置异常信息 **/
			log.setMsg(e.getMessage());
			result = e.getMessage();
			throw e;
		}
		finally
		{
			try{
				IUserinfo user = (IUserinfo)MappContext.getAttribute(MappContext.MAPPCONTEXT_USER);
				if(user != null && user.getUserId() != null)
				log.setUserId(user.getUserId().longValue());
				log.setCreateTime(new Date(System.currentTimeMillis()));
				log.setUrl((String)MappContext.getAttribute(MappContext.MAPPCONTEXT_REQUEST_IP));
				log.setBizCode((String)args[0]);
				log.setReq((String)args[1]);
				log.setRsp((String)result);
				System.out.println("size:"+log.getBizCode()==null?null:log.getBizCode().length());
				callLogService.saveCallLog(log);
				logger.debug("mapp向外部发送请求日志记录完毕");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return result;
		
	}
	
	/**
	 * 外部请求mapp时，记录日志
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	
	
	@Around("execution(* com.ailk.butterfly.mapp.core.client.ClientHandler.doHandlePackage(..))")
//	@Around("execution(* com.ailk.butterfly.mapp.core.client.StringClientHandler.doHandle(..))")
	public Object responseLog(ProceedingJoinPoint point) throws Throwable
	{
		CallLog log = new CallLog();
		Object[] args = point.getArgs();
		Object result = null;
		
		try
		{
			result = point.proceed(args);
			logger.debug("外部向mapp发送请求日志记录完毕");
			
		}catch (Exception e) {
			log.setMsg(e.getMessage());
			result = e.getMessage();
			throw e;
		}
		finally
		{
			try
			{
//				IMappDatapackage pkg = mapper.readValue(decrypt((String)args[0]), IMappDatapackage.class);
				log.setBizCode(((IMappDatapackage)args[0]).getHeader().getBizCode());
				log.setReq(mapper.writeValueAsString(args[0]));
				log.setRsp(mapper.writeValueAsString(result));
				
				IUserinfo user = (IUserinfo)MappContext.getAttribute(MappContext.MAPPCONTEXT_USER);
				if(user != null && user.getUserId() != null)
				log.setUserId(user.getUserId().longValue());
				log.setCreateTime(new Date(System.currentTimeMillis()));
				log.setUrl((String)MappContext.getAttribute(MappContext.MAPPCONTEXT_REQUEST_IP));
				
				callLogService.saveCallLog(log);
				logger.debug("mapp向外部发送请求日志记录完毕");
			}
			catch (Exception e) {
				// TODO: 如果json转化出错则无法获取值bizcode
				e.printStackTrace();
			}
			
			
		}
		return result;
		
		
	}
	
//	
//	public String decrypt(String str) throws Exception
//	{
//		
//		/**
//		 * 当拦截的方法第一个参数对象为String类型时，将参数进行解密操作
//		 */
//		if(cryptPermission)
//		{
//			logger.debug("对参数进行解密操作。");
//			
//			byte[] arg0_byte = str.getBytes("utf-8");
//			arg0_byte = DESUtils.decryptBASE64(str);
//			arg0_byte = GZipUtils.decompress(arg0_byte);
//			
//			/** 将加密的包文体解密，并将解密后的包文体转化，设置到包文中 **/
//			str = DESUtils.decrypt(new String(arg0_byte), desKey);
//			
//			return str;
//		}
//		else
//			return str;
//			
//	}
//
//	public String encrypt(String str) throws Exception
//	{
//		if(cryptPermission)
//		{
//			String encrypt = DESUtils.encrypt(str, desKey);
//			byte[] ret_byte = encrypt.getBytes("utf-8");
//			ret_byte = GZipUtils.compress(ret_byte);
//			ret_byte = DESUtils.encryptBASE64(ret_byte).getBytes("utf-8");
//			/** 生成加密的包文体 **/
//			String cs = new String(ret_byte,"utf-8");
//			return cs;
//		}
//		
//		return str;
//	}
//	
}