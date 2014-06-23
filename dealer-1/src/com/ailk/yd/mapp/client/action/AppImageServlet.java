package com.ailk.yd.mapp.client.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ai.mapp.sys.entity.SmallLocalFile;
import com.ai.mapp.sys.service.SmallLocalFileService;


/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-9 下午02:37:13
 * 类说明:读取图片
 */

public class AppImageServlet extends HttpServlet {
	private final static Logger logger = Logger.getLogger(AppImageServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	@Autowired//因为没用
	private SmallLocalFileService  smallLocalFileService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//Http 1.0 header
		resp.setDateHeader("Expires", 1L);
		resp.addHeader("Pragma", "no-cache");
		//Http 1.1 header
		resp.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
		resp.setContentType("image/gif");
		
		ServletOutputStream out = resp.getOutputStream();
		ByteArrayInputStream in = null;
		try {
			String slfid = req.getParameter("slfid");
			
			smallLocalFileService = (SmallLocalFileService)(WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext())).getBean("smallLocalFileService");
			in = new ByteArrayInputStream(smallLocalFileService.getImageBySlfId(Long.valueOf(slfid)));
			BufferedImage challenge = ImageIO.read(in);
			ImageIO.write(challenge, "jpg", out);
			out.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			in.close();
			out.close();
		}
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}


	public SmallLocalFileService getSmallLocalFileService() {
		return smallLocalFileService;
	}


	public void setSmallLocalFileService(SmallLocalFileService smallLocalFileService) {
		this.smallLocalFileService = smallLocalFileService;
	}

}
