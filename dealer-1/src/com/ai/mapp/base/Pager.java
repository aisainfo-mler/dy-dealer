package com.ai.mapp.base;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;


public class Pager {
	 public Pager()
	    {
	    }
	    /**
	     * 生成分页脚本
	     * @param offset	第几行起
	     * @param length	总记数
	     * @param size		每页行数
	     * @param url
	     * @param pageContext
	     * @return
	     */
	    public static String generate(int offset, int length, int size, String url, String pageContext)
	    {
	        return generate(offset, length, size, url, null,pageContext);
	    }

	    
	    public static String generateAjax(int offset, int length, int size )
	    {
	    	 String header = "";
	         int pagecount = 0;
	         if(length>size)
	         {
	         if(length % size == 0)
	             pagecount = length / size;
	         else
	             pagecount = length / size + 1;
	         header = header + "\u5171 " + length + " \u884C\uFF0C" + pagecount + " \u9875  ";
	       
	         if(offset > 0)
	             header = header + "&nbsp;<a href='javascript:goPage(1)'>" + "<img src=\"../images/manage/backpage.gif\" width=\"16\" height=\"13\" border=\"0\" align=\"center\">" + "</a>\n";
	         int radius = (MAX_PAGE_INDEX / 2) * size;
	         int start;
	         if(offset < radius)
	             start = 0;
	         else
	         if(offset < length - radius)
	             start = offset - radius;
	         else
	         if(length / size - MAX_PAGE_INDEX < 0)
	             start = 0;
	         else
	         if(length % size == 0)
	             start = (length / size - MAX_PAGE_INDEX) * size;
	         else
	             start = ((length / size + 1) - MAX_PAGE_INDEX) * size;
	         for(int i = start; i < length && i < start + MAX_PAGE_INDEX * size; i += size)
	             if(i == offset)
	                 header = header + "<b>" + (i / size + 1) + "</b>\n";
	             else
	                 header = header + "&nbsp;<a href='javascript:goPage(" + (i / size + 1) + ")'>" + (i / size + 1) + "</a>\n";
	         if(offset < length - size)
	             header = header + "&nbsp;<a href='javascript:goPage(" + pagecount + ")'>" + "<img src=\"../images/manage/frontpage.gif\" width=\"16\" height=\"13\" border=\"0\" align=\"center\">" + "</a>\n";
	         header = header + "</a>&nbsp;&nbsp;\u8F6C\u7B2C&nbsp;<input type=\"text\" name=\"goPage\" id=\"goPage\" size=\"3\" \">";
	         header = header + "<input type=\"text\" name=\"temp\" style=\"display:none\">";
	         header = header + " \u9875<a href='javascript:goPage()'>"+"<img src=\"../images/manage/go.gif\" width=\"16\" height=\"13\" border=\"0\" align=\"center\">"+"</a>&nbsp;\n";
	         return header;
	         }
	         return "";
	    }
	    /**
	     * 生成分页脚本
	     * @param offset	第几行起
	     * @param length	总记数
	     * @param size		每页行数
	     * @param url
	     * @param param
	     * @param pageContext
	     * @return
	     */
	    @SuppressWarnings("unchecked")
		public static String generate(int offset, int length, int size,String target )
	    {    
	     	HttpServletRequest request = ServletActionContext.getRequest();
//	     	String lang =  (String)(request.getSession().getAttribute("WW_TRANS_I18N_LOCALE"));
	        Locale locale = ActionContext.getContext().getLocale();
	     	ResourceBundle prop = null;
	     	if(locale == null || StringUtil.isEmpty(locale.getCountry()) ){
	     		String lang = (String)(request.getSession().getAttribute("lang"));
		     	
		     	if(StringUtil.isEmpty(lang)){
		     			lang = "en_US";
		     	}
		     	
		     	String[] language_country = lang.split("_");
			    String language1 = language_country[0];
			    String country = language_country[1];
			    locale = new Locale(language1, country);
			    prop = ResourceBundle.getBundle("mappHW",locale);
	     		
	     	}else{
	     		prop = ResourceBundle.getBundle("mappHW",locale);
	     	}
	     	
	     	
	    	String url = request.getRequestURL().toString();
	    	String pageContext = request.getContextPath();
	    	List<String>list = new ArrayList<String>();
	    	list.add("fenye");
	    	list.add("pagelength");
	    	list.add("length");
	    	list.add("offset");
	    	
	    	StringBuffer sb = new StringBuffer(200); 
	    	Enumeration<String> e = request.getParameterNames();
	    	while (e.hasMoreElements()) {
	    		String s = e.nextElement();
	    		if(!list.contains(s)){
				String [] ss = request.getParameterValues(s);
				for (int i = 0; i < ss.length; i++) 
				{
					sb.append("&").append(s).append("=").append(ss[i]);
				}
	    		}
			}
	    	
	       sb.append("&fenye=1");
	        if(length > size)
	        {
	            String pref;
	            if(url.indexOf("?") > -1)
	                pref = "&";
	            else
	                pref = "?";
//	            String header = "";
	            String dest="#sub_Content";
	            if(StringUtil.isNotEmpty(target))
	            	dest = target;
	            String header = "<div class=\"pagination\" dest=\""+dest+"\"  resource=\"\">";
	            int pagecount = 0;
	            if(length % size == 0)
	                pagecount = length / size;
	            else
	                pagecount = length / size + 1;
	            if("zh_CN".equals(locale.toString())){
	            	
	            	header = header + new MessageFormat(prop.getString("pager.gong")).format(new String[]{pageContext}) + " " + length + new MessageFormat(prop.getString("pager.hang")).format(new String[]{pageContext}) + " \uFF0C" + pagecount + " " + new MessageFormat(prop.getString("pager.ye")).format(new String[]{pageContext}) + "  ";
	            }else{
	            	header = header + length + " &nbsp;" + new MessageFormat(prop.getString("pager.hang")).format(new String[]{pageContext}) + ",&nbsp;" + pagecount + " " + new MessageFormat(prop.getString("pager.yes")).format(new String[]{pageContext}) + " " + new MessageFormat(prop.getString("pager.gong")).format(new String[]{pageContext}) + " &nbsp;";
//	            	header = header + "\u5171 " + length + " \u884C\uFF0C" + pagecount + " \u9875  ";
	            }
	            String disParm = '"'+ url + pref + "offset=**" + "&length=" + size + "&pagelength=" + size + sb.toString() + '"';
	            if(offset > 0)
	                header = header + "&nbsp;<a href='javascript:void(0)' onclick='javascript:goPage(this,1)'>" + new MessageFormat(prop.getString("pager.header.prev")).format(new String[]{pageContext}) + "</a>\n";
	            int radius = (MAX_PAGE_INDEX / 2) * size;
	            int start;
	            if(offset < radius)
	                start = 0;
	            else
	            if(offset < length - radius)
	                start = offset - radius;
	            else
	            if(length / size - MAX_PAGE_INDEX < 0)
	                start = 0;
	            else
	            if(length % size == 0)
	                start = (length / size - MAX_PAGE_INDEX) * size;
	            else
	                start = ((length / size + 1) - MAX_PAGE_INDEX) * size;
	            for(int i = start; i < length && i < start + MAX_PAGE_INDEX * size; i += size)
	                if(i == offset)
	                    header = header + "<b>" + (i / size + 1) + "</b>\n";
	                else
	                    header = header + "&nbsp;<a href='javascript:void(0)' onclick='javascript:goPage(this," + (i / size + 1) + ")'>" + (i / size + 1) + "</a>\n";
	            if(offset < length - size){
	            	//String test = new MessageFormat("http://localhost:7001").format(new String[]{pageContext});
	            	String s = new MessageFormat(prop.getString("pager.header.next")).format(new String[]{pageContext}) + "</a>\n";
	            	header = header + "&nbsp;<a href='javascript:void(0)' onclick='javascript:goPage(this," + pagecount + ")'>" + s;
	            }
//	                header = header + "&nbsp;<a href='javascript:goPage(" + pagecount + ")'>" + new MessageFormat(prop.getString("pager.header.next")).format(new String[]{pageContext}) + "</a>\n";
	            if("zh_CN".equals(locale.toString())){
	            	header = header + "</a>&nbsp;&nbsp;" + new MessageFormat(prop.getString("pager.zhuan")).format(new String[]{pageContext}) + "&nbsp;<input type=\"text\" name=\"goPage\" id=\"goPage"+dest+"\" size=\"3\" onkeydown=\"KeyDown(event,this,this.value);\">";
	            }else{
	            	header = header + "</a>&nbsp;&nbsp;" + new MessageFormat(prop.getString("pager.ye")).format(new String[]{pageContext}) + "&nbsp;<input type=\"text\" name=\"goPage\" id=\"goPage"+dest+"\" size=\"3\" onkeydown=\"KeyDown(event,this,this.value);\">";
	            }
	            header = header + "<input type=\"text\" name=\"temp\" style=\"display:none\">";
	            if("zh_CN".equals(locale.toString())){
	            	header = header + " " + new MessageFormat(prop.getString("pager.ye")).format(new String[]{pageContext}) + "<button class=\"d_buttonThin3\" onclick=\"javascript:goPage(this,document.getElementById('goPage"+dest+"').value);\">"+new MessageFormat(prop.getString("pager.header.go")).format(new String[]{pageContext})+"</button>&nbsp;\n";
	            }else{
	            	header = header + " &nbsp;<button class=\"d_buttonThin3\" onclick=\"javascript:goPage(this,document.getElementById('goPage"+dest+"').value);\">"+new MessageFormat(prop.getString("pager.header.go")).format(new String[]{pageContext})+"</button>&nbsp;\n";
	            }
//	            header = header + " " + new MessageFormat(prop.getString("pager.ye")).format(new String[]{pageContext}) + "<button class=\"d_buttonThin3\" onclick=\"javascript:goPage(this,document.getElementById('goPage"+dest+"').value);\">"+new MessageFormat(prop.getString("pager.header.go")).format(new String[]{pageContext})+"</button>&nbsp;\n";
	            header = header + " <input type=hidden name=\"sppageparm\"  id=\"sppageparm"+dest+"\" value=" + disParm + ">\n";
	            header = header + " <input type=hidden name=\"sppagetotal\" id=\"sppagetotal"+dest+"\" value=" + pagecount + ">\n";
	            header = header + " <input type=hidden name=\"sppagelength\" id=\"sppagelength"+dest+"\" value=" + size + ">\n";
	            header += "</div>";
	            return header;
	        } else
	        {
	            return "<div class=\"pagination\" ></div>";
	        }
	    }
	    
	    public static String generate1(int offset, int length, int size,String target ,String formId)
	    {    
	     	HttpServletRequest request = ServletActionContext.getRequest();
	    	String url = request.getRequestURL().toString();
	    	String pageContext = request.getContextPath();
	    	List<String>list = new ArrayList<String>();
	    	list.add("fenye");
	    	list.add("pagelength");
	    	list.add("length");
	    	list.add("offset");
	    	
	    	StringBuffer sb = new StringBuffer(200); 
	    	Enumeration<String> e = request.getParameterNames();
	    	while (e.hasMoreElements()) {
	    		String s = e.nextElement();
	    		if(!list.contains(s)){
				String [] ss = request.getParameterValues(s);
				for (int i = 0; i < ss.length; i++) 
				{
					sb.append("&").append(s).append("=").append(ss[i]);
				}
	    		}
			}
	    	
	       sb.append("&fenye=1");
	        if(length > size)
	        {
	            String pref;
	            if(url.indexOf("?") > -1)
	                pref = "&";
	            else
	                pref = "?";
//	            String header = "";
	            String dest="#sub_Content";
	            if(StringUtil.isNotEmpty(target))
	            	dest = target;
	            String header = "<div class=\"pagination\" dest=\""+dest+"\"  resource=\"\" formId=\"" + formId + "\">";
	            int pagecount = 0;
	            if(length % size == 0)
	                pagecount = length / size;
	            else
	                pagecount = length / size + 1;
	            header = header + "\u5171 " + length + " \u884C\uFF0C" + pagecount + " \u9875  ";
	            String disParm = '"'+ url + pref + "offset=**" + "&length=" + size + "&pagelength=" + size + sb.toString() + '"';
	            if(offset > 0)
	                header = header + "&nbsp;<a href='javascript:void(0)' onclick='javascript:goPage(this,1)'>" + new MessageFormat(prop.getString("pager.header.prev")).format(new String[]{pageContext}) + "</a>\n";
	            int radius = (MAX_PAGE_INDEX / 2) * size;
	            int start;
	            if(offset < radius)
	                start = 0;
	            else
	            if(offset < length - radius)
	                start = offset - radius;
	            else
	            if(length / size - MAX_PAGE_INDEX < 0)
	                start = 0;
	            else
	            if(length % size == 0)
	                start = (length / size - MAX_PAGE_INDEX) * size;
	            else
	                start = ((length / size + 1) - MAX_PAGE_INDEX) * size;
	            for(int i = start; i < length && i < start + MAX_PAGE_INDEX * size; i += size)
	                if(i == offset)
	                    header = header + "<b>" + (i / size + 1) + "</b>\n";
	                else
	                    header = header + "&nbsp;<a href='javascript:void(0)' onclick='javascript:goPage(this," + (i / size + 1) + ")'>" + (i / size + 1) + "</a>\n";
	            if(offset < length - size){
	            	//String test = new MessageFormat("http://localhost:7001").format(new String[]{pageContext});
	            	String s = new MessageFormat(prop.getString("pager.header.next")).format(new String[]{pageContext}) + "</a>\n";
	            	header = header + "&nbsp;<a href='javascript:void(0)' onclick='javascript:goPage(this," + pagecount + ")'>" + s;
	            }
//	                header = header + "&nbsp;<a href='javascript:goPage(" + pagecount + ")'>" + new MessageFormat(prop.getString("pager.header.next")).format(new String[]{pageContext}) + "</a>\n";
	            header = header + "</a>&nbsp;&nbsp;\u8F6C\u7B2C&nbsp;<input type=\"text\" name=\"goPage\" id=\"goPage"+dest+"\" size=\"3\" onkeydown=\"KeyDown(event,this,this.value);\">";
	            header = header + "<input type=\"text\" name=\"temp\" style=\"display:none\">";
	            header = header + " \u9875<button class=\"d_buttonThin3\" onclick=\"javascript:goPage(this,document.getElementById('goPage"+dest+"').value);\">"+new MessageFormat(prop.getString("pager.header.go")).format(new String[]{pageContext})+"</button>&nbsp;\n";
	            header = header + " <input type=hidden name=\"sppageparm\"  id=\"sppageparm"+dest+"\" value=" + disParm + ">\n";
	            header = header + " <input type=hidden name=\"sppagetotal\" id=\"sppagetotal"+dest+"\" value=" + pagecount + ">\n";
	            header = header + " <input type=hidden name=\"sppagelength\" id=\"sppagelength"+dest+"\" value=" + size + ">\n";
	            header += "</div>";
	            return header;
	        } else
	        {
	            return "";
	        }
	    }
	    
	    public static String generate(int offset, int length, int size, String url, String param,String pageContext)
	    {      
	        if(param != null && param.length() > 0)
	            param = "&" + param;
	        else
	            param = "";
	        param = param + "&fenye=1";
	        if(length > size)
	        {
	            String pref;
	            if(url.indexOf("?") > -1)
	                pref = "&";
	            else
	                pref = "?";
	            String header = "";
	            int pagecount = 0;
	            if(length % size == 0)
	                pagecount = length / size;
	            else
	                pagecount = length / size + 1;
	            header = header + "\u5171 " + length + " \u884C\uFF0C" + pagecount + " \u9875  ";
	            String disParm = '"'+ url + pref + "offset=**" + "&length=" + size + "&pagelength=" + size + param + '"';
	            if(offset > 0)
	                header = header + "&nbsp;<a href='javascript:goPage(1)'>" + new MessageFormat(prop.getString("pager.header.prev")).format(new String[]{pageContext}) + "</a>\n";
	            int radius = (MAX_PAGE_INDEX / 2) * size;
	            int start;
	            if(offset < radius)
	                start = 0;
	            else
	            if(offset < length - radius)
	                start = offset - radius;
	            else
	            if(length / size - MAX_PAGE_INDEX < 0)
	                start = 0;
	            else
	            if(length % size == 0)
	                start = (length / size - MAX_PAGE_INDEX) * size;
	            else
	                start = ((length / size + 1) - MAX_PAGE_INDEX) * size;
	            for(int i = start; i < length && i < start + MAX_PAGE_INDEX * size; i += size)
	                if(i == offset)
	                    header = header + "<b>" + (i / size + 1) + "</b>\n";
	                else
	                    header = header + "&nbsp;<a href='javascript:goPage(" + (i / size + 1) + ")'>" + (i / size + 1) + "</a>\n";
	            if(offset < length - size)
	                header = header + "&nbsp;<a href='javascript:goPage(" + pagecount + ")'>" + new MessageFormat(prop.getString("pager.header.next")).format(new String[]{pageContext}) + "</a>\n";
	            header = header + "</a>&nbsp;&nbsp;\u8F6C\u7B2C&nbsp;<input type=\"text\" id=\"goPage\" name=\"goPage\" size=\"3\" onkeydown=\"KeyDown(event);\">";
	            header = header + "<input type=\"text\" name=\"temp\" style=\"display:none\">";
	            header = header + " \u9875<a href='javascript:goPage()'><img src=\""+pageContext+"/images/go.gif\" width=\"15\" height=\"15\" border=\"0\" align=\"center\"></a>&nbsp;\n";
	            header = header + " <input type=hidden name=sppageparm id=\"sppageparm\" value=" + disParm + ">\n";
	            header = header + " <input type=hidden name=sppagetotal id=\"sppagetotal\" value=" + pagecount + ">\n";
	            header = header + " <input type=hidden name=sppagelength id=\"sppagelength\" value=" + size + ">\n";
	            return header;
	        } else
	        {
	            return "";
	        }
	    }
	    /**
	     * jasperReopert专用报表分页脚本
	     * @param current	当前页
	     * @param end		最后一页
	     * @param url
	     * @param param
	     * @param pageContext
	     * @return
	     */
	    public static String generateReport(int current, int end, String url, String param,String pageContext){
	        
	        if(param != null && param.length() > 0)
	            param = "&" + param;
	        else
	            param = "";
	        param = param + "&fenye=1";
	        if(end > current)
	        {
	            String pref;
	            if(url.indexOf("?") > -1)
	                pref = "&";
	            else
	                pref = "?";
	            String header = "";
	            header = header + "第 "+(current+1)+" 页 / 共 " + end + " 页 \u9875  ";
	            String disParm = '"'+ url + pref + "offset=**"+ param + '"';
	            if(current > 0)
	                header = header + "&nbsp;<a href='javascript:goReportPage(0)'>" + new MessageFormat(prop.getString("pager.header.prev")).format(new String[]{pageContext}) + "</a>\n";
	            int start=0;
	            if(current<=0)
	            	start= 0;
	            else
	            	start = current-1;
	            start= start-2;
	            if(start<0)
	            	start=0;
	            for(int i = start; i < end && i<= (current+2);i++){
	                if(i == current)
	                    header = header + "<b>" + (i+1) + "</b>\n";
	                else
	                    header = header + "&nbsp;<a href='javascript:goReportPage(" + i + ")'>" +(i+1) + "</a>\n";
	            }
	            if(end-1 > current)
	                header = header + "&nbsp;<a href='javascript:goReportPage(" + (end-1) + ")'>" + new MessageFormat(prop.getString("pager.header.next")).format(new String[]{pageContext}) + "</a>\n";
	            header = header + "</a>";
	            header = header + " <input type=\"hidden\" name=\"goPage\" id=\"goPage\"><input type=hidden name=\"sppageparm\"  id=\"sppageparm\" value=" + disParm + ">\n";
	            header = header + " <input type=hidden name=\"sppagetotal\" id=\"sppagetotal\" value=" + (end-1) + ">\n";
	            header = header + " <input type=hidden name=\"sppagelength\" id=\"sppagelength\" value=1>\n";
	            return header;
	        } else
	        {
	            return "";
	        }
	    }
	    public static String generateOrder(int offset, int length, int size, String url, String condition, String order)
	    {
	        //condition = CommFunc.parseCondition(condition);
	        order = order + "&fenye=1";
	        if(length > size)
	        {
	            String pref;
	            if(url.indexOf("?") > -1)
	                pref = "&";
	            else
	                pref = "?";
	            String header = "";
	            int pagecount = 0;
	            if(length % size == 0)
	                pagecount = length / size;
	            else
	                pagecount = length / size + 1;
	            header = header + "\u5171 " + length + " \u884C\uFF0C" + pagecount + " \u9875  ";
	            if(offset > 0)
	                header = header + "&nbsp;<a href='javascript:goPage(1)'>" + prop.getString("pager.header.prev") + "</a>\n";
	            int radius = (MAX_PAGE_INDEX / 2) * size;
	            String disParm = '"' + url + pref + "pager.offset=**" + "&length=" + size + "&length=" + size + "&condition=" + condition + "&order=" + order + '"';
	            int start;
	            if(offset < radius)
	                start = 0;
	            else
	            if(offset < length - radius)
	                start = offset - radius;
	            else
	            if(length / size - MAX_PAGE_INDEX < 0)
	                start = 0;
	            else
	            if(length % size == 0)
	                start = (length / size - MAX_PAGE_INDEX) * size;
	            else
	                start = ((length / size + 1) - MAX_PAGE_INDEX) * size;
	            for(int i = start; i < length && i < start + MAX_PAGE_INDEX * size; i += size)
	                if(i == offset)
	                    header = header + "<b>" + (i / size + 1) + "</b>\n";
	                else
	                    header = header + "&nbsp;<a href='javascript:goPage(" + (i / size + 1) + ")'>" + (i / size + 1) + "</a>\n";

	            if(offset < length - size)
	                header = header + "&nbsp;<a href='javascript:goPage(" + pagecount + ")'>" + prop.getString("pager.header.next") + "</a>\n";
	            header = header + "</a>&nbsp;&nbsp;\u8F6C\u7B2C&nbsp;<input type=\"text\" name=\"goPage\" id=\"goPage\" size=\"3\" onkeydown=\"KeyDown(event);\">";
	            header = header + "<input type=\"text\" name=\"temp\" style=\"display:none\">";
	            header = header + " \u9875&nbsp;<a href='javascript:goPage()'><img src=\"/images/go.gif\" width=\"16\" height=\"16\" border=\"0\" align=\"middle\"></a>&nbsp;\n";
	            header = header + " <input type=hidden name=\"sppageparm\"  id=\"sppageparm\" value=" + disParm + ">\n";
	            header = header + " <input type=hidden name=\"sppagetotal\" id=\"sppagetotal\" value=" + pagecount + ">\n";
	            header = header + " <input type=hidden name=\"sppagelength\" id=\"sppagelength\" value=" + size + ">\n";
	            return header;
	        } else
	        {
	            return "";
	        }
	    }

	    private static ResourceBundle prop;
	    private static int MAX_PAGE_INDEX = 15;
	    //private static String HEADER = "Result page";

	    static
	    {
	        prop = ResourceBundle.getBundle("mappHW");
	        try
	        {
	            //HEADER = prop.getString("pager.header.title");
	        }
	        catch(Exception e) { }
	        try
	        {
	            MAX_PAGE_INDEX = Integer.parseInt(prop.getString("pager.max.page.index"));
	        }
	        catch(Exception e) { }
	    }
}
