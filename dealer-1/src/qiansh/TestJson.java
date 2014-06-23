package qiansh;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ai.mapp.sys.entity.ProductFilter;
import com.ailk.yd.mapp.client.model.HW0001Request;
import com.ailk.yd.mapp.model.YDDatapackage;
import com.ailk.yd.mapp.model.YDHeader;

public class TestJson {
	
	
	   /** 
     * MD5 加密 - 
     */  
    public static String getMD5Str(String str) {  
        MessageDigest messageDigest = null;  
  
        try {  
            messageDigest = MessageDigest.getInstance("MD5");
            
            messageDigest.reset();  
  
            messageDigest.update(str.getBytes("UTF-8"));  
        } catch (NoSuchAlgorithmException e) {  
            System.out.println("NoSuchAlgorithmException caught!");  
            System.exit(-1);  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
  
        byte[] byteArray = messageDigest.digest();  
  
        StringBuffer md5StrBuff = new StringBuffer();  
  
        for (int i = 0; i < byteArray.length; i++) {              
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
  
        return md5StrBuff.toString();  
    }  
    
	public static String doSend(String url,String xmlStr,String sessionId) throws Exception{
		
		// TODO Auto-generated method stub
		HttpClient httpClient = new HttpClient();
		PostMethod method = new PostMethod(url);
		try {
			
			String result = "";
			
			if(null==url || "".equals(url)){
				throw new Exception("���ܲ���Ŧ�����URL��ַ����url="+url);
			}
		    httpClient.getParams().setConnectionManagerTimeout(300000);
			method.getParams().setSoTimeout(300000);
			
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
			
			
			if (StringUtils.isBlank(sessionId) == false) {
				Map<String,String> headers = new HashMap<String, String>();
				headers.put("Cookie", "JSESSIONID="+ sessionId+"; mappkey="+System.currentTimeMillis());
				
				 /**
				   * 增加http header
				   */
				  if(headers != null && headers.isEmpty() == false)
				  {
					  for(String key:headers.keySet())
					  {
						  System.out.println(key+":"+headers.get(key));
						  method.addRequestHeader(key, headers.get(key));
					  }
				  }
			}
			
			
			method.addRequestHeader("Accept", "text/html");
			method.addRequestHeader("Content-Type", "application/json");
			method.addRequestHeader("X-API-Key", "l7xx53d264422ca14191b09179494dee0330");
			method.setRequestEntity(new StringRequestEntity(xmlStr,"application/json","utf-8"));
			
//			method.setRequestBody(new ByteArrayInputStream(xmlStr.getBytes()));
//			hc.setCookieStore(cookieStore);
			NameValuePair[] data = { new NameValuePair("msg", xmlStr) };
			method.setQueryString(data);
			System.out.println("请求url:"+url);
			System.out.println("请求串:"+xmlStr);
			int responseCode = httpClient.executeMethod(method);
			System.out.println("响应编码 responseCode:"+responseCode);
			if (responseCode >= 100 && responseCode < 300) {
				result = method.getResponseBodyAsString();
			} else {
				Exception exception = null;
				if (responseCode < 400 && responseCode >= 300) {
					System.out.println("错误："+method.getStatusLine());
					String redirectLocation;
					Header locationHeader = method.getResponseHeader("location");
					if (locationHeader != null) {
						redirectLocation = locationHeader.getValue();
						exception = new Exception("错误："+responseCode+","+method.getStatusLine()+","+redirectLocation);
					}
				} else {
					exception = new Exception("错误："+responseCode+","+method.getStatusLine());
				}
				throw exception;
			}
			
			return result;
			
		} catch (Exception e) {
			throw e;
		} finally {
			method.releaseConnection();
		}
		
	}
	
	public static void hw0006(YDDatapackage pkg){}

	public static void main(String[] args) throws Exception
	{
		Map<String,Set<Object>> condition = new HashMap<String, Set<Object>>(0);
		condition.put(ProductFilter.FILTER_TYPE_GEOGRAPHICLOCATION, new HashSet<Object>(0));
//		condition.get(ProductFilter.FILTER_TYPE_GEOGRAPHICLOCATION).add("G40003");
//		condition.get(ProductFilter.FILTER_TYPE_GEOGRAPHICLOCATION).add("G40002");
		
		Set<String> codes = new HashSet<String>(0);
		codes.add("OC402");
		codes.add("3000");
		
		YDDatapackage pkg = new YDDatapackage();
		YDHeader header = new YDHeader();
		pkg.setHeader(header);
		header.setBizCode("hw0001");
		header.setIdentityId(System.currentTimeMillis()+"");
		HW0001Request req = new HW0001Request();
//		req.setFilterMap(condition);
//		req.setCodes(codes);
		req.setPage(0);
		req.setSize(2);
		pkg.setBody(req);
		
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String rsp = TestJson.doSend("http://100.112.4.75:8080/dealer/mapp/json.json",mapper.writeValueAsString(pkg),null);
			System.out.println(rsp);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		TestJson test = new TestJson();
//		
////		System.setProperty("javax.net.ssl.trustStore", "/Library/Java/JavaVirtualMachines/jdk1.7.0_40.jdk/Contents/Home/jre/lib/security/api-st.ril.com.cer");
//		System.setProperty("javax.net.ssl.trustStore", "/Users/luyang/.keystore");
//		System.setProperty("javax.net.ssl.trustStorePassword","password");
//		
//		
//		String a = "{\"customerId\": \"1100009298\",\"dateFrom\":\"\",\"dateTo\":\"\",\"status\":\"\",\"serviceId\":\"\",\"orderType\":\"\", \"referenceNumber\":\"\",\"registeredMobileNumber\":\"\",\"pageSize\":\"1\",\"offset\":\"1\"}";
//		
//		String url = "https://api-st.ril.com:8443/v4/customers/orders/find";
//		
//		
//		
//		String result = test.doSend(url,a, null);
//		
//		System.out.println("返回结果："+result);
	}

}
