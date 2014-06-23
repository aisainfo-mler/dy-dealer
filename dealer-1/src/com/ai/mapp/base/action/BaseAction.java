package com.ai.mapp.base.action;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ai.mapp.sys.entity.FileUpload;
import com.ai.mapp.sys.util.SYSConstant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware, SessionAware,ServletResponseAware {
	
	private static final long serialVersionUID = -1960743243378821141L;
	
	private final Log log = LogFactory.getLog(BaseAction.class);
	
	public static final String HTTP_SESSION_LOGINCODE = "loginUsercode";
	
	public static final String HTTP_SESSION_USER = "loginUser";
	
	public static final String HTTP_SESSION_RIGHT = "right";
	
	public static final String HTTP_SESSION_APP_RIGHT = "appRight";
	
	public static final String HTTP_SESSION_MAINMENU = "mainMenu";
	
	public static final String HTTP_SESSION_SUBMENU = "subMenu";
	
	public static final String HTTP_SESSION_CURRUSERINFO = "CurrUserInfo";
	
	
	
	private Map<String,Object> session;  
	
    protected HttpServletRequest request;  
    
    private HttpServletResponse response;  
    
    protected  Map<String,Cookie> cookieMap;
    
    
	
	//-- 分页参数 --//
//	protected int pageNo = 1;
//	protected int pageSize = 10;
//	protected long totalCount = -1;
	protected long total;//当前总记录数
	protected String pagerHeader;//当前分页代码
	protected int length = 16;//设置页最大记录数
	protected int offset;//设置起始记录数
	protected int page;//当前页码
	protected String pageName;
	
	protected String sendUrl;//远程请求url
	
	protected String callbackUrl;//回调url
	
	protected Collection<FileUpload> files;
	
	protected FileUpload fileUpload;
	
	protected Map<String,String> fileTypeMap;
	
	protected String jsonResult;
	
	protected Long menuId;//点到的主菜单ID,方便找到子菜单
	
	protected String menuUrl;//点到的主菜单ID,方便展现
	
	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getPagerHeader() {
		return pagerHeader;
	}

	public void setPagerHeader(String pagerHeader) {
		this.pagerHeader = pagerHeader;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public BaseAction(){
		super();
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public Map<String, Cookie> getCookieMap() {
		if(cookieMap == null){
    		cookieMap = new HashMap<String,Cookie>();
    	}
		return cookieMap;
	}

	public void setCookieMap(Map<String, Cookie> cookieMap) {
		this.cookieMap = cookieMap;
	}
	
	public void addCookie(String name,String value,int maxAge){
		Cookie cookie = new Cookie(name, value); 
	    cookie.setMaxAge(60*60*24*maxAge); 
	    cookie.setPath(request.getContextPath());
	    response.addCookie(cookie); 

	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为-1.
	 */
	public long getTotalPage() {

		long totalPage = getTotal() / getLength();
		
		if (getTotal() % getLength() > 0) {
			totalPage++;
		}
		return totalPage;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (page + 1 <= getTotalPage());
	}

	/**
	 * 取得下页的页号, 序号从1开始.
	 * 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return page + 1;
		} else {
			return page;
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (page > 1);
	}
//
	/**
	 * 取得上页的页号, 序号从1开始.
	 * 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return page - 1;
		} else {
			return page;
		}
	}
	
	public Object getSessionValue(String key)
	{
		if(ActionContext.getContext().getSession() == null)
			return null;
		
		return ActionContext.getContext().getSession().get(key);
	}
	
	public Object putSessionValue(String key,Object value)
	{
		if(ActionContext.getContext().getSession() == null)
			return null;
		
		return ActionContext.getContext().getSession().put(key,value);
	}
	
	/**
	 * 获取当前HttpSession
	 * @return HttpSession
	 */
	protected HttpSession getHttpSession(){
		return ServletActionContext.getRequest().getSession(true);
	}
	
	/**
	 * 获得当前应用目录
	  * getContextPath 方法 
	  * <p>方法说明:</p> 
	  * @return 
	  * @return String 
	  * @author zwj 
	  * @date 2012-3-19
	 */
	protected String getContextPath(){		
		return ServletActionContext.getRequest().getContextPath();
	}
	
	
	protected String getRelativeFilePath(String path,String fileId,String fileExt){
		return ServletActionContext.getServletContext().getRealPath(path) + System.getProperty("file.separator") +fileId+"."+fileExt;
	}
	
	public String toPage()
	{
//		System.out.println("================serverIp="+getServerIp()+",uploadIp="+getUploadIp());
		return SUCCESS;
	}
	
	private String getWebClassesPath() {
		String path = getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		return path;

	}

	private String getWebInfPath() throws IllegalAccessException {
		String path = getWebClassesPath();
		if (path.indexOf("WEB-INF") > 0) {
			path = path.substring(0, path.indexOf("WEB-INF") + 8);
		} else {
			throw new IllegalAccessException("路径获取错误");
		}
		return path;
	}

	private String getWebRoot() throws IllegalAccessException {
		String path = getWebClassesPath();
		if (path.indexOf("WEB-INF") > 0) {
			path = path.substring(0, path.indexOf("WEB-INF/classes"));
		} else {
			throw new IllegalAccessException("路径获取错误");
		}
		return path;
	}
	
	public String fileUpload() throws Exception
	{
		if(fileUpload == null || fileUpload.getFileMappingId() == null)
		{
			log.error("文件上传映射id不明确，无法上传");
			throw new Exception("文件上传映射id不明确，无法上传");
		}
		
		fileTypeMap = SYSConstant.fileTypes;
		
		return SUCCESS;
	}
	
	/**上传图片*/
	public String uploadImage() throws Exception
	{
		if(fileUpload == null || fileUpload.getFileMappingId() == null)
		{
			log.error("文件上传映射id不明确，无法上传");
			throw new Exception("文件上传映射id不明确，无法上传");
		}
		
//		fileTypeMap = new HashMap<String, String>();
//		fileTypeMap.put(SYSConstant.FILE_TYPE_LIST, SYSConstant.);
//		fileTypeMap.put(SYSConstant.FILE_TYPE_SHOW, SYSConstant.FILE_TYPE_SHOW_NAME);
//		fileTypeMap.put(SYSConstant.FILE_TYPE_ORIGINAL, SYSConstant.FILE_TYPE_ORIGINAL_NAME);
		
		return SUCCESS;
	}
	
	
	public String uploadFile() throws Exception
	{
		if(fileUpload == null || fileUpload.getFileMappingId() == null)
		{
			log.error("文件上传映射id不明确，无法上传");
			throw new Exception("文件上传映射id不明确，无法上传");
		}
		
		fileTypeMap = new HashMap<String, String>();
//		fileTypeMap.put(SYSConstant.FILE_TYPE_ATTACHMENT, SYSConstant.FILE_TYPE_ATTACHMENT_NAME);
		
		return SUCCESS;
	}
	
	public String returnAjaxError(String msg,boolean ifJson)
	{
		if(ifJson){
			jsonResult = msg;
		}else{
			jsonResult="{\"flag\":false,\"msg\":\"" + (msg==null?"":msg) + "\"}";
		}
		return "jsonResult";
	}
	
	public String returnAjaxSuccess(String msg,boolean ifJson)
	{
		if(ifJson){
			jsonResult = msg;
		}else{
			jsonResult="{\"flag\":true,\"msg\":\"" + (msg==null?"":msg) + "\"}";
		}
		return "jsonResult";
	}
	
	public String returnAjaxSuccess(String msg,Map<String,String> values)
	{
		if(values == null || values.isEmpty())
			jsonResult="{\"flag\":true,\"msg\":\""+(msg==null?"":msg)+"\"}";
		else
		{
			jsonResult = "{\"flag\":true,\"msg\":\""+(msg==null?"":msg)+"\"";
				for(String key:values.keySet())
				{
					jsonResult = jsonResult+",\""+key+"\":\""+values.get(key)+"\"";
				}
				jsonResult = jsonResult+"}";
		}
		
		System.out.println(jsonResult);
		
		return "jsonResult";
	}
	
	public String getLogincode()
	{
		return (String)getSessionValue("loginUsercode");
	}

	public String getSendUrl() {
		return sendUrl;
	}

	public void setSendUrl(String sendUrl) {
		this.sendUrl = sendUrl;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public Collection<FileUpload> getFiles() {
		return files;
	}

	public void setFiles(Collection<FileUpload> files) {
		this.files = files;
	}

	public FileUpload getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}

	public void setSession(Map<String,Object> session) {  
        this.session = session;  
    }  
  
    public void setServletRequest(HttpServletRequest request) {  
        this.request = request;  
    }  
  
    public void setServletResponse(HttpServletResponse response) {  
        this.response = response;  
    }

	public Map<String, Object> getSession() {
		return session;
	}

	public HttpServletRequest getRequest() {
		return request;
	} 

	public HttpServletResponse getResponse()
	{
		return this.response;
	}

	public Map<String, String> getFileTypeMap() {
		return fileTypeMap;
	}

	public void setFileTypeMap(Map<String, String> fileTypeMap) {
		this.fileTypeMap = fileTypeMap;
	}

	protected  ApplicationContext getApplicationContext(){
        return WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());  
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
	
}
