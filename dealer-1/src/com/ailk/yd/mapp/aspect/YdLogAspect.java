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

import com.ai.mapp.sys.entity.CallLog;
import com.ai.mapp.sys.service.CallLogService;
import com.ailk.butterfly.core.security.IUserinfo;
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
	@Around("execution(* com.ailk.yd.mapp.client.action.ExternalRequest.sendMsg_String(..))")
	public Object insertCallLog(ProceedingJoinPoint point) throws Throwable
	{
		CallLog log = new CallLog();
		Object[] args = point.getArgs();
		Object result = null;
		
		try{
			result = point.proceed(args);
			return result;
			
		}catch (Exception e) {
			/** 设置异常信息 **/
			log.setMsg(e.getMessage());
			throw e;
		}
		finally
		{
			IUserinfo user = (IUserinfo)MappContext.getAttribute(MappContext.MAPPCONTEXT_USER);
			if(user != null && user.getUserId() != null)
			log.setUserId(user.getUserId().longValue());
			log.setCreateTime(new Date(System.currentTimeMillis()));
			log.setUrl(serverKey);
			log.setBizCode((String)args[0]);
			log.setReq((String)args[1]);
			log.setRsp((String)result);
			callLogService.saveCallLog(log);
			logger.debug("mapp向外部发送请求日志记录完毕");
		}
	}
	
	/**
	 * 外部请求mapp时，记录日志
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.ailk.butterfly.mapp.core.client.StringClientHandler.doHandle(..))")
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
			throw e;
		}
		finally
		{
			try
			{
				IMappDatapackage pkg = mapper.readValue((String)args[0], IMappDatapackage.class);
				log.setBizCode(pkg.getHeader().getBizCode());
			}
			catch (Exception e) {
				// TODO: 如果json转化出错则无法获取值bizcode
			}
			
			IUserinfo user = (IUserinfo)MappContext.getAttribute(MappContext.MAPPCONTEXT_USER);
			if(user != null && user.getUserId() != null)
			log.setUserId(user.getUserId().longValue());
			log.setCreateTime(new Date(System.currentTimeMillis()));
			log.setUrl("localhost");

			log.setReq((String)args[0]);
			log.setRsp((String)result);
			callLogService.saveCallLog(log);
			logger.debug("mapp向外部发送请求日志记录完毕");
		}
		return result;
	}
	
	
}