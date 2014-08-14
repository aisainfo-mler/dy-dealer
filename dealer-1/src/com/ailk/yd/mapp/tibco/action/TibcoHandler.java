package com.ailk.yd.mapp.tibco.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.ailk.yd.mapp.client.action.ExternalRequest;

@Service
public class TibcoHandler implements ApplicationContextAware,ExternalRequest<Object,String>{

	public static ObjectMapper mapper = new ObjectMapper();
	
	protected Logger logger = LoggerFactory.getLogger(TibcoHandler.class);
	
	public ApplicationContext context;
	
	@Value("${server_timeout}")
	protected Integer timeout;
	@Value("${server_key}")
	protected String serverKey;
	@Value("${sign_encoding}")
	protected String encoding="utf-8";
	@Value("${sign_flag}")
	protected String mode;
	@Value("${x-api-key}")
	protected String apiKey;
	
	private String getTibcoUrl(String bizcode) throws Exception
	{
		return (String)context.getBean(bizcode+"_url");
	}

	public String sendMsg(String url, Object json , Map<String,?> parameters,String token,boolean isPost) throws Exception
	{
		System.out.println("支撑平台Json请求url："+url);
//		url = "https://api-st.ril.com:8443/v4/customers/orders/?referenceNumber=NO000000LO0N&returnCompleteOrder=1";
		System.out.println("支撑平台Json请求json："+json);
		System.out.println("支撑平台Json请求parameters："+parameters);
//		parameters = null;
		
//		String url = getTibcoUrl(bizcode);
		
		String result = "";
		
		HttpClient httpClient = new HttpClient();
		HttpMethodBase method ;
		
		if(isPost==true)
		{
			method = new PostMethod(url);
			StringRequestEntity jsonEntity = new StringRequestEntity(json.toString(), "application/json", "utf-8");
			((PostMethod)method).setRequestEntity(jsonEntity);
			logger.debug("请求串:"+json);
		}
		else
		{
			method = new GetMethod(url);
		}
		try {

			if(StringUtils.isBlank(url))
				throw new Exception("请求地址为空");
			
		    httpClient.getParams().setConnectionManagerTimeout(timeout);
			method.getParams().setSoTimeout(timeout);
			
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
			
			if (StringUtils.isBlank(token) == false) {
				Map<String,String> headers = new HashMap<String, String>();
				headers.put("Cookie", "JSESSIONID="+ token+"; mappkey="+System.currentTimeMillis());
//				headers.put(key, value);
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
			/** 设置API-KEY **/
			method.addRequestHeader("X-API-Key", apiKey);
			
			if(parameters != null && parameters.isEmpty() == false)
			{
			
				NameValuePair[] data = new NameValuePair[parameters.size()];
				int i=0;
				for(String key : parameters.keySet())
				{
					data[i] = new NameValuePair(key, parameters.get(key).toString());
					i++;
				}
				method.setQueryString(data);
			}
			
			/**
			 * 设置要传输的json数据
			 */
			logger.debug("请求url:"+url);
//			if(isPost==true){
//
//			}

			
			int responseCode = httpClient.executeMethod(method);
			logger.debug("响应编码 responseCode:"+responseCode);
			
			if (responseCode >= 100 && responseCode < 300) {
				result = method.getResponseBodyAsString();
			} else {
//				if (responseCode < 400 && responseCode >= 300) {
					logger.debug(""+method.getStatusLine());
					
					result = method.getResponseBodyAsString();
//					System.out.println(errorMsg);
//					if(StringUtils.isEmpty(result) == false)
//						throw new Exception(errorMsg);
					
					String redirectLocation;
					Header locationHeader = method.getResponseHeader("location");
					if (locationHeader != null) {
						redirectLocation = locationHeader.getValue();
						throw new Exception("响应编码："+responseCode+","+method.getStatusLine()+","+redirectLocation);
					}
//				} else {
//					throw new Exception("响应编码："+responseCode+","+method.getStatusLine());
//				}
			}
//			result = method.getResponseBodyAsString();
//			if (TibcoUtil.isJsonFormat(result)) {
//				//符合json格式的话就可以直接返回了
//				result = method.getResponseBodyAsString();
//			} else {
//				if (responseCode < 400 && responseCode >= 300) {
//					logger.debug(""+method.getStatusLine());
//					String redirectLocation;
//					Header locationHeader = method.getResponseHeader("location");
//					if (locationHeader != null) {
//						redirectLocation = locationHeader.getValue();
//						throw new Exception("响应编码："+responseCode+","+method.getStatusLine()+","+redirectLocation);
//					}
//				} else {
//					throw new Exception("响应编码："+responseCode+","+method.getStatusLine());
//				}
//			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			method.releaseConnection();
		}
		
		System.out.println("支撑平台返回结果："+result);
		
		return result;
	}

	public String getError(String errorMsg)
	{
		try{
			Map errMap = mapper.readValue(errorMsg, Map.class);
			return errMap.get("errors") == null?"tibco interface is error,can't fetch the error info":mapper.writeValueAsString(errMap.get("errors"));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}
}
