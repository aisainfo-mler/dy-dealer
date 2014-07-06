package com.ai.mapp.base.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ai.mapp.base.security.DESUtils;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.service.ISVTemplate;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.model.MAPP.MAPP;
import com.ailk.butterfly.core.util.GZipUtils;

/**
 * 手机终端统一接口
 * @author fangll
 *
 */
public class MAPPHTTPService extends HttpServlet {	
	
	private static final String DES_PWD = "MAPP_DESKEY";
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(MAPPHTTPService.class); 
	/**
	 * Constructor of the object.
	 */
	public MAPPHTTPService() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
//		doPost(request, response);
		PrintWriter out = response.getWriter();
		logger.error("不支持get方法");
		out.print("不支持get方法");
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		String str = request.getParameter("xmlmsg").trim();
		
		response.addHeader("cryptType", "DES");
		System.out.println("start 解密");
		System.out.println(str);
		
		try
		{		
			str = decrypt(str);
			System.out.println(str);
			
			if(StringUtils.isBlank(str)){
				out.print("xmlmsg 为空");
				return;
			}	
			
			String rspXML = "";
			//解析包头
			StringReader reader = new StringReader(str.trim());
			MAPP send = MAPP.unmarshal(reader);	
			String svcContent = send.getSvcContent();	
//			svcContent = updateSvcContent(svcContent);
			String bizCode = send.getBizCode();		
			logger.debug("请求参数:"+str);
			//System.out.println("请求参数:"+str);
		
			
			/**
			 * 浙江移动终端项目 只做协议转发 以"HW"开头
			 */
			HttpSession session = request.getSession();
			
			logger.debug(bizCode+" 包头 session:++++++++++++++++"+(request.getSession()==null?null:request.getSession().getId()));
			
			Set<String> uncheck = new HashSet<String>(0);
			uncheck.add("HW0005");
			uncheck.add("HW0006");
			uncheck.add("HW0024");
			
			
			String userCode = session == null?null:(String) session.getAttribute(BSSConstantParam.USERCODE);			
			if(StringUtils.isEmpty(userCode) && uncheck.contains(bizCode) == false  )
			{
				rspXML = "<Response><RspCode>"+BSSConstantError.CODE_TIME_OUT_ERROR+"</RspCode><MSG>登录超时</MSG></Response>";
			}
			else
			{
				/** TODO 菜单复验 **/
				ISVTemplate socketService = (ISVTemplate)getApplicationContext().getBean(bizCode.toLowerCase()+"Service");
				
				if(socketService == null)
					rspXML = "<Response><RspCode>"+BSSConstantError.CODE_TIME_OUT_ERROR+"</RspCode><MSG>无此接口信息</MSG></Response>";
				else{
					ParamObject po = new ParamObject();
					po.setParameter(BSSConstantParam.SESSION_ID, session.getId());
					po.setParameter(BSSConstantParam.BIZCODE, bizCode);
					po.init(session);
					
//					po.setParameter(BSSConstantParam.USERCODE, "ysy");
//					po.setParameter(BSSConstantParam.USERID, "1255");
					
					po.setParameter(BSSConstantParam.REQUESTCONTENT, svcContent);
					try{
						rspXML = (String)socketService.getResponse(po);
					}catch (Exception e) {
						e.printStackTrace();
						rspXML = "<Response><RspCode>"+BSSConstantError.CODE_UNKNOW_ERROR+"</RspCode><MSG>"+e.getMessage()+"</MSG></Response>";
					}
					if(po.isIfUpdate()){
						po.updateSession(request.getSession());
					}
				}
			}
			
			logger.info("==========================返回=========================");
			logger.info(rspXML);
			logger.info("=======================================================");
			
			if(logger.isDebugEnabled())
			{
				Enumeration<String> a = session.getAttributeNames();
				while(a.hasMoreElements())
				{	
					String t = a.nextElement();
					logger.debug("当前session  "+t+":"+session.getAttribute(t));
				}
			}
			
			
			/**
			 * 判断包头是否要加密
			 */
			rspXML = encrypt(rspXML);
			
            //注意以下这一行,wls环境有影响
            response.setContentLength(rspXML.getBytes("utf-8").length);     
            PrintWriter out2 = response.getWriter();
            out2.print(rspXML);             
            response.flushBuffer();
            out2.close();
            
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		out.flush();
		out.close();
	}

	/**
	 * The doPut method of the servlet. <br>
	 *
	 * This method is called when a HTTP put request is received.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Put your code here
	}

	/**
	 * Returns information about the servlet, such as 
	 * author, version, and copyright. 
	 *
	 * @return String information about this servlet
	 */
	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
	private ApplicationContext getApplicationContext(){
        return WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());  
	}
	
	public String decrypt(String str) throws Exception
	{
		
		/**
		 * 当拦截的方法第一个参数对象为String类型时，将参数进行解密操作
		 */
		if(ifCryptPermission())
		{
			logger.debug("对参数进行解密操作。");
			
			byte[] arg0_byte = str.getBytes("utf-8");
			arg0_byte = DESUtils.decryptBASE64(str);
			arg0_byte = GZipUtils.decompress(arg0_byte);
			
			/** 将加密的包文体解密，并将解密后的包文体转化，设置到包文中 **/
			str = DESUtils.decrypt(new String(arg0_byte), DES_PWD);
			
			return str;
		}
		else
			return str;
			
	}

	public String encrypt(String str) throws Exception
	{
		if(ifCryptPermission())
		{
			String encrypt = DESUtils.encrypt(str, DES_PWD);
			byte[] ret_byte = encrypt.getBytes("utf-8");
			ret_byte = GZipUtils.compress(ret_byte);
			ret_byte = DESUtils.encryptBASE64(ret_byte).getBytes("utf-8");
			/** 生成加密的包文体 **/
			String cs = new String(ret_byte,"utf-8");
			return cs;
		}
		
		return str;
	}
	
	private boolean ifCryptPermission()
	{
		return true;
	}

	/**
	 * <p>描述: </p> 
	 * 最常用的字符实体(Character Entities) 
				显示结果 说明 Entity Name Entity Number 
				显示一个空格 &nbsp; &#160; 
				< 小于 &lt; &#60; 
				> 大于 &gt; &#62; 
				& &符号 &amp; &#38; 
				" 双引号 &quot; &#34; 
				
				其他常用的字符实体(Character Entities) 
				显示结果 说明 Entity Name Entity Number 
				© 版权 &copy; &#169; 
				® 注册商标 &reg; &#174; 
				× 乘号 &times; &#215; 
				÷ 除号 &divide; &#247; 
	 */
	private String updateSvcContent(String oldSvc){
		Pattern pattern = Pattern.compile("&.{3,4};?");
		StringBuffer sb = new StringBuffer();

		Matcher matcher = pattern.matcher(oldSvc);

		while (matcher.find()){
			String str = matcher.group();
			if(str.endsWith(";")){
			}else{
				matcher.appendReplacement(sb, "&amp;" + str.substring(1) );
			}
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	
	public static void main(String[] args) throws Exception
	{
		MAPPHTTPService m = new MAPPHTTPService();
		String a = "H4sIAAAAAAAAAxWTuRUEMQhDWzKYyyFn/yUNE2wy+wzoS8Iy1rxtGOF9h4AnJ8SpW+lx+STIAyWs0n5zD841liZArFP8QHqi6Zb1vSrOnaFKIAFjEHQe0JWcS3Sy3JCH0uncGIwuz9lhejSuvG7ioIIyFzrT4E8TLQezVVDp6Knakaquis9B+Q5MmiecV2mPlTgptdUs887gC78AI3E6gZ/LYyiAOmBmtyLn6K3x0hNY0ScRWWaFMpPvjkEYDrAWfjWPIpdPAlo89VXjtJ+a5fmEkthFttop4Af0ccqisqAoerqrsatLfpG30BdA+I4xMOd99IxLSI3govhSC6AkBFJJeth9kBR0RaBdcR3IfLnwegFHLHqENmMUbq6zI1m0AxOo73lDzWn8FjMk0nLrqOI+Uym7944oPF9f80X9ZyjmQjtPmA6zwop3jSBSlIn1Mu9Zd9vgHjLVY42c+6ZkL5ZaLmkNNC2xM1lMEjX06i/unLtBqMk3uzbuM7V1ai1ya35XCJcLbQp3LvHTLr7YCyTk0N45jChF1QKHcJMrfODMXZEZzxvHz7rTMstTg9dT3N31Ht8+ctjv/guMZ7/1qrXcX927ZOrRdU3YFHs/gjDqohBbL6874XSnNAT4JvqCghx8TaWMLbv+Cjzwdj9GZrWRqGvVJNsMrD3HOmZt3/d7J690RvYx3A60ZvzWrAhVqM0uyobjWFXJEvW6f9SjZYXNxhg3C7yqx4jPfVtOpaqH58Cqd9hgl2wVvMEC/qK/yxSOtazftmf7dM7mZZfijTdZ6QGdWzj1kcuYcS7bs8VsE/DOmXRz5aWUvRrL1qnsOXvWhmo5vl/FBvUa4uQHal62n3AEAAA=";
		System.out.println(m.decrypt(a));
		String str = "测试解密—123123——：{}AD";
		str = m.encrypt(str);
		System.out.println(m.decrypt(str));
		
	}
}
