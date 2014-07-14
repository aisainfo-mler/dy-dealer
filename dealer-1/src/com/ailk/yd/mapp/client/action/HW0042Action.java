package com.ailk.yd.mapp.client.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;

import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0042Request;
import com.ailk.yd.mapp.client.model.HW0042Response;
import com.ailk.yd.mapp.tibco.action.YD0023Action;
import com.ailk.yd.mapp.tibco.model.YD0023.YD0023Request;
import com.ailk.yd.mapp.tibco.model.YD0023.YD0023Response;
import com.ailk.yd.mapp.tibco.util.TibcoUtil;

@Service("hw0042")
@Action(bizcode = "hw0042", userCheck = true)
@Scope("prototype")
public class HW0042Action extends
		AbstractYDBaseActionHandler<HW0042Request, HW0042Response> {

	@Autowired
	private YD0023Action yd0023;
	
	private static String filePath = "/PoaPoi/";

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {

		String fc = request.getFileContents();
		if (StringUtils.isBlank(fc)) {
			throw new Exception("File content is null!");
		}
		final String ornNum = request.getOrnNum();
		TibcoUtil.checkNotNull(ornNum, "ornNumber");
		TibcoUtil.checkNotNull(request.getFileType(), "picType");
		
		final String webinf = this.getClass().getResource("/").getPath();
		final String dir = webinf+filePath;
		final File dirFile = new File(dir);
		if(dirFile.exists()==false)
			dirFile.mkdir();
		
		if(StringUtils.equalsIgnoreCase(request.getIfReturnUrl(), "true")){
			uploadFile(fc, ornNum, dir);
			File poaFile = new File(dir+"/"+ornNum+"_poa");
			File poiFile = new File(dir+"/"+ornNum+"_poa");
			File cusFile = new File(dir+"/"+ornNum+"_cus");
			if(poaFile.exists()==false) throw new Exception(ornNum+" no POA img");
			if(poiFile.exists()==false) throw new Exception(ornNum+" no POI img");
			if(cusFile.exists()==false) throw new Exception(ornNum+" no CUSTOMER img");
			List<String> l = new ArrayList<String>();
			l.add(poaFile.getAbsolutePath());
			l.add(poiFile.getAbsolutePath());
			l.add(cusFile.getAbsolutePath());
			String pdfFile = dir+"/"+request.getOrnNum()+"_pdf";
			TibcoUtil.converImgToPdf(l, pdfFile);
			final String pdfFileContent = TibcoUtil.convertFileToBase64Str(pdfFile);
			
			//上传了3个全的图片，转成pdf文件
			YD0023Request yd23 = new YD0023Request(pdfFileContent);
			YD0023Response g2t = yd0023.post2Tibco(yd23, null,false);
			response = new HW0042Response();
			String url = g2t.getUrl();
			if(StringUtils.isBlank(url)){
				throw new Exception("url from tibco is null");
			}
			response.setUrl(url);
		}else{
			uploadFile(fc, ornNum, dir);
		}
		
		

	}

	private void uploadFile(String fc, final String ornNum, final String dir)
			throws UnsupportedEncodingException, IOException,
			FileNotFoundException {
		//未上传完3个文件
		String decode = URLDecoder.decode(fc, "UTF-8");
		final byte[] decodeBuffer = new BASE64Decoder().decodeBuffer(decode);
		String fileName = ornNum+"_"+request.getFileType().toLowerCase();
		File f = new File(dir + fileName);
		System.err.println(f.getAbsolutePath());
		if(f.exists()==false){
			f.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(decodeBuffer);
		fos.flush();
		fos.close();
	}

}
