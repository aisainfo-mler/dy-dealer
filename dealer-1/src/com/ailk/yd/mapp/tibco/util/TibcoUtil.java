package com.ailk.yd.mapp.tibco.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class TibcoUtil {

	/**
	 * @param errResponseJson
	 *            { \"success\": false, \"general_message\": \"This is an
	 *            operation implementation generated fault\", \"errors\": {
	 *            \"findCustomerProfileException\":
	 *            \"RIL4G-B-MDM_FindCustomer-NO-DATANo Data Found in MDM For the
	 *            given Request\" } }
	 * @return
	 */
	public static String findErrMsg(JSONObject errResponseJson) {
		String errMsg = "";
		JSONObject err = errResponseJson.getJSONObject("errors");
		Set ks = err.keySet();
		for (Iterator it = ks.iterator(); it.hasNext();) {
			String key = (String) it.next();
			String errValue = (String) err.get(key);
			errMsg = key + ":" + errValue;
		}
		return errMsg;
	}
	
	/**
	 * 判断是否json格式
	 * @param in
	 * @return
	 */
	public static boolean isJsonFormat(String in){
		try {
			JSONObject.fromObject(in);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static String getCurTime(){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		return sdf.format(d);
	}
	
	public static void checkNotNull(String obj,String name) throws Exception{
		if(StringUtils.isBlank(obj)){
			throw new Exception(name + " can't be null!");
		}
	}
	
	

	/**
	 * 一页pdf放一张照片
	 * 
	 * @param imgs
	 *            图片的地址
	 * @param pdfFilePath
	 *            pdf的地址
	 * @throws DocumentException
	 * @throws FileNotFoundException
	 * @throws BadElementException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static void converImgToPdf(List<String> imgs, String pdfFilePath)
			throws DocumentException, FileNotFoundException,
			BadElementException, MalformedURLException, IOException {
		/*
		 * A4尺寸：595,842
		 */
		final Rectangle pageSize = PageSize.A4;
		Document document = new Document(pageSize);
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream(pdfFilePath));
		document.open();
		for (Iterator<String> it = imgs.iterator(); it.hasNext();) {
			String imgFilePath = (String) it.next();
			document.newPage();
			addImg(pageSize, writer, imgFilePath);
		}
		document.close();
	}

	/**
	 * 添加照片到pdf文件
	 * 
	 * @param pageSize
	 * @param writer
	 * @param picFilePath
	 * @throws BadElementException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws DocumentException
	 */
	private static void addImg(final Rectangle pageSize, PdfWriter writer,
			final String picFilePath) throws BadElementException,
			MalformedURLException, IOException, DocumentException {
		Image img = Image.getInstance(picFilePath);
		/*
		 * pdf的左下角的坐标为：0，0 img.setAbsolutePosition(0, absoluteY);
		 * 这个方法的x，y的坐标是指图片左下角再pdf文件中的位置。所以：0，0表示图片的左下角和pdf的左下角一致
		 * width：可以理解成图片的左上角在pdf上的坐标。pdf左边界为0 0：顶到边 正数：图片左上角再pdf边界的右边
		 * 负数：图片左上角再pdf左边界的左边
		 */
		// 图片占据pdf的比例，横向全屏显示
		// 计算出图片和pdf的比例
		int percent = (int) (pageSize.getWidth() / img.getScaledWidth() * 100);
		if(percent<100)
			img.scalePercent(percent);
		// 计算图片纵向的坐标
		final int absoluteY = (int) (pageSize.getHeight() - img
				.getScaledHeight()) / 2;
		img.setAbsolutePosition(0, absoluteY);
		writer.getDirectContent().addImage(img);
	}
	
	
	/**
	 * 将文件转成base64的字符串
	 * @param filePath
	 * @return
	 */
	public static  String convertFileToBase64Str(String filePath) throws Exception{
		File f = new File(filePath);
		FileInputStream fis = new FileInputStream(f);
		int available = fis.available();
		byte[] r = new byte[available];
		fis.read(r);
		String encode = BASE64EncoderWithoutWrap.encode(r);
		return encode;
		
	}
	
	
	/**
	 * 从mapObj中获取rm对象中key值为乘以变量名字的对象，通过反射设置到rm对象中
	 * @param mapObj
	 * @param rm
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("rawtypes")
	public static void extractStrValObj(Object mapObj, Object rm)
			throws IllegalAccessException {
		if (!(mapObj instanceof Map))
			return;
		Map m = (Map) mapObj;
		//获取rm对象的成员变量的名称
		Field[] declaredFields = rm.getClass().getDeclaredFields();
		for (int i = 0; i < declaredFields.length; i++) {
			Field f = declaredFields[i];
			String fieldname = f.getName();
			//判断成员遍历的名称再map中是否存在
			Object fieldVal = m.get(fieldname);
			if (f.getType().equals(String.class) && fieldVal != null) {
				//成员变量名称和Map中key值相同，进行设置操作
				f.setAccessible(true);
				f.set(rm, fieldVal.toString());
			}
		}
	}
	
	/**
	 * 生成一个clazz类型的对象，并且从mapObj中获取成员遍历的值
	 * @param mapObj
	 * @param clazz
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static Object extractStrValClass(Object mapObj, Class clazz)
			throws IllegalAccessException, InstantiationException {
		if (!(mapObj instanceof Map))
			return null;
		Object rm = clazz.newInstance();
		extractStrValObj(mapObj, rm);
		return rm;
	}

	
	public static void main(String[] args) throws FileNotFoundException, BadElementException, MalformedURLException, DocumentException, IOException {
		System.err.println(getCurTime());

		List<String> imgs = new ArrayList<String>();
		String pdfFile = "/Users/qianshihua/Downloads/forTest.pdf";
		imgs.add("/Users/qianshihua/Pictures/com.tencent.ScreenCapture/Snip20140422_1.png");
		imgs.add("/Users/qianshihua/Pictures/com.tencent.ScreenCapture/QQ20140710-1.png");
		imgs.add("/Users/qianshihua/Pictures/com.tencent.ScreenCapture/QQ20140630-1.png");
		converImgToPdf(imgs, pdfFile);
	
	}
}
