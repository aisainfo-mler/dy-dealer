package com.ai.mapp.bss.service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;

import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;

/**
 * 接口模板
 * @author mler
 *
 */
public abstract class ISVTemplate implements ServiceInterface{
	
	protected Log logger = LogFactory.getLog(ISVTemplate.class);
	
	@Value("${server_url}")
	protected String url;
	@Value("${server_timeout}")
	protected Integer timeout;
	@Value("${server_key}")
	protected String serverKey;
	@Value("${sign_encoding}")
	protected String encoding="GBK";
	@Value("${sign_flag}")
	protected String mode;
	
	private String sendMsg(String bizcode,String msg , String sessionId) throws Exception
	{
		logger.info("支撑平台请求："+msg);
		
		String result = "";
		
		HttpClient httpClient = new HttpClient();
		
		HttpMethod method = new PostMethod(url);
		try {

			if(StringUtils.isBlank(url))
				throw new Exception("请求地址为空");
			
		    httpClient.getParams().setConnectionManagerTimeout(timeout);
			method.getParams().setSoTimeout(timeout);
			
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
			
			if (StringUtils.isBlank(sessionId) == false) {
				Map<String,String> headers = new HashMap<String, String>();
				headers.put("Cookie", "JSESSIONID="+ sessionId+"; mappkey="+System.currentTimeMillis());
				
				 /** 增加http header **/
				  if(headers != null && headers.isEmpty() == false)
				  {
					  for(String key:headers.keySet())
					  {
						  logger.debug(key+":"+headers.get(key));
						  method.addRequestHeader(key, headers.get(key));
					  }
				  }
			}

			NameValuePair[] data = { new NameValuePair("msg", msg) };
			method.setQueryString(data);
			logger.debug("请求url:"+url);
			logger.debug("请求串:"+msg);
			
			int responseCode = httpClient.executeMethod(method);
			logger.debug("响应编码 responseCode:"+responseCode);
			
			if (responseCode >= 100 && responseCode < 300) {
				result = method.getResponseBodyAsString();
			} else {
				if (responseCode < 400 && responseCode >= 300) {
					logger.debug(""+method.getStatusLine());
					String redirectLocation;
					Header locationHeader = method.getResponseHeader("location");
					if (locationHeader != null) {
						redirectLocation = locationHeader.getValue();
						throw new Exception("响应编码："+responseCode+","+method.getStatusLine()+","+redirectLocation);
					}
				} else {
					throw new Exception("响应编码："+responseCode+","+method.getStatusLine());
				}
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			method.releaseConnection();
		}
		
		System.out.println("支撑平台返回结果："+result);
		
//		result = DESUtils.decrypt(new String(GZipUtils.decompress(DESUtils.decryptBASE64(result))),serverKey);
//		
//		logger.debug("解密支撑平台密文："+result);
		
		return result;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	
	/**
	 * 拼装查询参数以及查询/调用接口操作，并且将设置ParamObject相应的信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	protected abstract ParamObject send(ParamObject param) throws Exception;
	
	/**
	 * 用于给前端的接口，返回协议的XML
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Object getResponse(ParamObject param) throws Exception 
	{
		try{
		
			param = send(param);
			
			if(param.isIfSuccess() == false){
				return error(param);
			}
			return convertResponse(param);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
			
	}
	
	/**
	 * 接口错误处理，并返回协议的XML
	 * 如果send方法返回失败，则将调用此方法进行结果处理
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Object error(ParamObject param) throws Exception {
		return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR,param.getErrorInfo());
	}

	
	/**
	 * 通过（查询/接口调用）后返回的结果处理
	 * 如果send方法返回成功，就将调用此方法进行结果处理
	 * @param param
	 * @return
	 * @throws Exception
	 */
	protected abstract Object convertResponse(ParamObject param) throws Exception;
	
	/**
	 * 返回XML结果
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String getResponseXML(ParamObject param) throws Exception{
		
		try{
			Object rsp = getResponse(param);
			Method method = rsp.getClass().getMethod("toXMLString", (Class[])null);
			String rspXml = (String)method.invoke(rsp, (Object[])null);
			return rspXml;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "<Response><RspCode>"+BSSConstantError.CODE_DATA_ERROR+"</RspCode><MSG>"+e.getMessage()+"</MSG></Response>";
		}
	}
	
}
