package qiansh;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import com.ai.mapp.sys.util.PO2VOUtils;
import com.ailk.yd.mapp.client.model.HW0001Request;
import com.ailk.yd.mapp.client.model.HW0001Response;
import com.ailk.yd.mapp.model.YDDatapackage;

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
		
		DateTime dd = new DateTime();
		
//		1996-06-25T00:00:00
		System.out.println(dd.toString(ISODateTimeFormat.basicDateTimeNoMillis()));
//		System.out.println(dd.toString(Jod.basicOrdinalDateTime()));
		
		List<HW0001Response.Identifier> list = new ArrayList<HW0001Response.Identifier>(0);
		list.add(new HW0001Response.Identifier());	
		
		HW0001Response.Product p = new HW0001Response.Product();
		List<HW0001Response.Product> list1 = new ArrayList<HW0001Response.Product>(0);
		list1.add(p);	
		
		List<HW0001Response.PlanSpec> list2 = new ArrayList<HW0001Response.PlanSpec>(0);
		list2.add(new HW0001Response.PlanSpec());
		p.setPlanSpecList(list2);
		
		HW0001Response.ProductSpec ps = new HW0001Response.ProductSpec();
		ps.setIdentifierList(list);
		List<HW0001Response.ProductSpec> list4 = new ArrayList<HW0001Response.ProductSpec>(0);
		list4.add(ps);
		p.setProductSpecList(list4);
		
		HW0001Response.ServiceSpec ss = new HW0001Response.ServiceSpec();
		ss.setIdentifierList(list);
		List<HW0001Response.ServiceSpec> list5 = new ArrayList<HW0001Response.ServiceSpec>(0);
		list5.add(ss);
		ps.setServiceSpecList(list5);
		
		HW0001Response.ResourceSpec rs = new HW0001Response.ResourceSpec();
		rs.setIdentifierList(list);
		List<HW0001Response.ResourceSpec> list6 = new ArrayList<HW0001Response.ResourceSpec>(0);
		list6.add(rs);
		ps.setResourceSpecList(list6);
		
		
		
		YDDatapackage pkg = new YDDatapackage();
		HW0001Response r = new HW0001Response();
		pkg.setBody(r);
		r.setProducts(list1);
	
		
		
//		BigDecimal a = new BigDecimal("900000000000000000000");
//		System.out.println(a);
//		
//		
//		YDDatapackage pkg = new YDDatapackage();
//		HW0021Request r = new HW0021Request();
//		pkg.setBody(r);	
		System.out.println(new ObjectMapper().writeValueAsString(pkg));
//		
////		DateTime dd = new DateTime();
////		System.out.println(dd.toString(ISODateTimeFormat.basicDateTimeNoMillis()));
//		
//		
//		
//		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><MAPP><BizCode>HW0022</BizCode><SvcContent><![CDATA[<Request><CommissionType>0</CommissionType><UserCode>m01</UserCode><OrderType></OrderType><PayStatus></PayStatus><StartTime></StartTime><EndTime>2014-07-04</EndTime></Request>]]></SvcContent></MAPP>";
//		String stt = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><MAPP><BizCode>HW0022</BizCode><SvcContent><![CDATA[<Request><CommissionType>0</CommissionType><UserCode>m01</UserCode><OrderType></OrderType><PayStatus></PayStatus><StartTime></StartTime><EndTime>2014-07-04</EndTime></Request>]]></SvcContent></MAPP>        ";
//		
//		System.out.println(str.equals(stt));
//		StringReader reader = new StringReader(str);
//		MAPP send = MAPP.unmarshal(reader);	
//		System.out.println(send.getBizCode());
//		StringReader reader1 = new StringReader(stt);
//		MAPP send1 = MAPP.unmarshal(reader1);	
//		
//		System.out.println(send1.getBizCode());
//		
//		
//		MAPPHTTPService m = new MAPPHTTPService();
//		
//		String en = m.decrypt(str);
//		System.out.println(en);
//		System.out.println(m.decrypt(en));
		
//		System.out.println(StringUtils.leftPad("1", 4, "0");
		
//		Map<String,Set<Object>> condition = new HashMap<String, Set<Object>>(0);
//		condition.put(ProductFilter.FILTER_TYPE_GEOGRAPHICLOCATION, new HashSet<Object>(0));
////		condition.get(ProductFilter.FILTER_TYPE_GEOGRAPHICLOCATION).add("G40003");
////		condition.get(ProductFilter.FILTER_TYPE_GEOGRAPHICLOCATION).add("G40002");
//		
//		Set<String> codes = new HashSet<String>(0);
//		codes.add("OC402");
//		codes.add("3000");
//		
//		YDDatapackage pkg = new YDDatapackage();
//		YDHeader header = new YDHeader();
//		pkg.setHeader(header);
//		header.setBizCode("hw0012");
//		header.setIdentityId(System.currentTimeMillis()+"");
//		HW0012Request req = new HW0012Request();
//		pkg.setBody(req);
//		req.setOrderCode("2014062403044051");
//		
//		
//		ObjectMapper mapper = new ObjectMapper();
//		System.out.println(mapper.writeValueAsString(pkg));
//		
//		
//		try {
//			String rsp = TestJson.doSend("http://100.112.12.99:8080/dealer/mapp/json.json",mapper.writeValueAsString(pkg),null);
//			System.out.println(rsp);
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
////		TestJson test = new TestJson();
////		
//////		System.setProperty("javax.net.ssl.trustStore", "/Library/Java/JavaVirtualMachines/jdk1.7.0_40.jdk/Contents/Home/jre/lib/security/api-st.ril.com.cer");
////		System.setProperty("javax.net.ssl.trustStore", "/Users/luyang/.keystore");
////		System.setProperty("javax.net.ssl.trustStorePassword","password");
////		
////		
////		String a = "{\"customerId\": \"1100009298\",\"dateFrom\":\"\",\"dateTo\":\"\",\"status\":\"\",\"serviceId\":\"\",\"orderType\":\"\", \"referenceNumber\":\"\",\"registeredMobileNumber\":\"\",\"pageSize\":\"1\",\"offset\":\"1\"}";
////		
////		String url = "https://api-st.ril.com:8443/v4/customers/orders/find";
////		
////		
////		
////		String result = test.doSend(url,a, null);
////		
////		System.out.println("返回结果："+result);
	}

}
