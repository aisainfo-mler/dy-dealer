package com.ai.mapp.base.service;

import groovy.lang.Singleton;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Service("imageService")
@Singleton
public class ImageService {

	private Log log = LogFactory.getLog(ImageService.class);
	
	@Value("${IMAGE_DEFAUT_BASE}")
	private int defaultBase = 700;
	
	/**
	 * 根据固定width来压缩图片
	 * @param srcURL
	 * @param destURL
	 * @param extractBase
	 * @throws Exception
	 */
	public void extractPhoto(String srcURL, String destURL, double extractBase) throws Exception{
		
		
		File srcFile = new File(srcURL);
		Image src = ImageIO.read(srcFile);
		int srcHeight = src.getHeight(null);
		int srcWidth = src.getWidth(null);
		
		int deskHeight = 0;
		int deskWidth = 0;
		
		if(extractBase <= 0)
		{
			extractBase = defaultBase;
		}
		
		if(srcWidth <= 300 || srcHeight <= 300)
		{
			/**
			 * 如果图片比extractBase压缩的要求还低，就按原图片大小上传
			 */
			deskHeight = srcHeight;
			deskWidth = srcWidth;
				
		}
		else if(srcWidth<=srcHeight)
		{
			deskWidth = (int) extractBase;
			deskHeight = srcHeight * deskWidth / srcWidth;
		}
		else
		{
			deskHeight = (int) extractBase;
			deskWidth = srcWidth * deskHeight / srcHeight;
		}
		
		log.info("原始尺寸："+srcWidth+"*"+srcHeight+"最终尺寸："+deskWidth+"*"+deskHeight+",extractBase："+extractBase);
		
		extract(src, destURL, deskWidth, deskHeight);
		
	}
	
	/**
	 * @param srcURL
	 *            原图地址
	 * @param destURL
	 *            缩略图地址
	 * @param extractBase
	 *            压缩基数
	 * @param scale
	 *            压缩限制(宽/高)比例
	 * @throws Exception
	 */
	public void extractPhoto(String srcURL, String destURL, double extractBase,
			double scale) throws Exception {

		File srcFile = new File(srcURL);
		Image src = ImageIO.read(srcFile);
		int srcHeight = src.getHeight(null);
		int srcWidth = src.getWidth(null);
		
		log.info("原图像素为："+srcWidth+"*"+srcHeight);
		
		int deskHeight = 0;
		int deskWidth = 0;
		double srcScale = (double) srcHeight / srcWidth;
		log.info("原图scale为："+srcScale);
		if ((double) srcHeight > extractBase || (double) srcWidth > extractBase) {
			if (srcScale >= scale || 1 / srcScale > scale) {
				if (srcScale >= scale) {
					deskHeight = (int) extractBase;
					deskWidth = srcWidth * deskHeight / srcHeight;
				} else {
					deskWidth = (int) extractBase;
					deskHeight = srcHeight * deskWidth / srcWidth;
				}
			} else {
				if ((double) srcHeight > extractBase) {
					deskHeight = (int) extractBase;
					deskWidth = srcWidth * deskHeight / srcHeight;
				} else {
					deskWidth = (int) extractBase;
					deskHeight = srcHeight * deskWidth / srcWidth;
				}
			}
		} else {
			deskHeight = srcHeight;
			deskWidth = srcWidth;
		}
		
		extract(src, destURL, deskWidth, deskHeight);
		
	}
	
	public int[] getWidthAndHeight(String filePath) throws Exception
	{
		File srcFile = new File(filePath);
		
		if(isImage(srcFile) == false)
			return null;
		
		Image src = ImageIO.read(srcFile);
		int srcHeight = src.getHeight(null);
		int srcWidth = src.getWidth(null);
		
		log.info("原图像素为："+srcWidth+"*"+srcHeight);
		
		int[] result = new int[2];
		result[0] = srcWidth;
		result[1] = srcHeight;
		
		return result;
	}
	
	public void extract(Image src, String destURL, int deskWidth,int deskHeight) throws Exception
	{
		try{
			log.info("原图像素为："+src.getWidth(null)+"*"+src.getHeight(null)+",新图的像素："+deskWidth+"*"+deskHeight);
			BufferedImage buffer = new BufferedImage(deskWidth, deskHeight,BufferedImage.TYPE_3BYTE_BGR);
			log.info("BufferedImage=========="+buffer);
			buffer.getGraphics().drawImage(src.getScaledInstance(deskWidth, deskHeight,  Image.SCALE_SMOOTH), 0, 0,  null);     
			log.info("drawImage=========="+buffer.getGraphics());
			FileOutputStream deskImage = new FileOutputStream(destURL); // 输出到文件流
			log.info("deskImage=========="+deskImage);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(buffer); 
	        param.setQuality(Float.valueOf("1.0"), true); 
	        encoder.setJPEGEncodeParam(param);
			log.info("encoder=========="+encoder);
			encoder.encode(buffer,param);
			log.info("encode=========="+buffer);
			deskImage.close();
			log.info("================deskImage close================");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}
	

	/**
	 * 给图片添加水印
	 * 
	 * @param filePath
	 *            需要添加水印的图片的路径
	 * @param markContent
	 *            水印的文字
	 * @param markContentColor
	 *            水印文字的颜色
	 * @param qualNum
	 *            图片质量
	 * @return
	 */
	public boolean createMark(String filePath, String markContent,Color markContentColor, float qualNum) {
		ImageIcon imgIcon = new ImageIcon(filePath);
		Image theImg = imgIcon.getImage();
		int width = theImg.getWidth(null);
		int height = theImg.getHeight(null);
		BufferedImage bimage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bimage.createGraphics();
		g.setColor(markContentColor);
		g.setBackground(Color.white);
		g.drawImage(theImg, 0, 0, null);
		g.drawString(markContent, width / 5, height / 5); // 添加文字
		g.dispose();
		try {
			FileOutputStream out = new FileOutputStream(filePath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
			param.setQuality(qualNum, true);
			encoder.encode(bimage, param);
			out.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static void saveImageAsJpg(InputStream in, File saveFile, int width,int hight) throws Exception {
		BufferedImage srcImage;

		srcImage = ImageIO.read(in);

		if (width > 0 || hight > 0) {
			srcImage = resize(srcImage, width, hight);
		}

		ImageIO.write(srcImage, "JPEG", saveFile);
		in.close();
	}

	
	/**
	 * jpg图片使用的是24-bit的编码，png有png-24和png-8两种，gif是8-bit的编码。
	 * 如果强行将jpg图片信息流按字节拆开，转换成gif图片，即使使用标准256色，也会出现严重的失真. 转jpg代码比较简单
	 * 
	 * @param source
	 * @param targetW
	 * @param targetH
	 * @return
	 */
	public static BufferedImage resize(BufferedImage source, int targetW,
			int targetH) {
		// targetW，targetH分别表示目标长和宽
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();

		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		if (sx > sy) {
			sx = sy;
			targetW = (int) (sx * source.getWidth());
		} else {
			sy = sx;
			targetH = (int) (sy * source.getHeight());
		}

		if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW,targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else
			target = new BufferedImage(targetW, targetH, type);
		
		Graphics2D g = target.createGraphics();
		// smoother than exlax:
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}
	
	public final static void main(String[] args) throws Exception
	{
		ImageService imageService = new ImageService();
		String srcURL = "/Users/mler/Desktop/TEST.JPG";
		String destURL = "/Users/mler/Desktop/test1.JPG";
//		String srcURL1 = "/Users/luyiq/Pictures/照片/101NCD90/DSC_0002.JPG";
//		String destURL1 = "/Users/luyiq/Pictures/test/test2.jpg";
		
		imageService.extractPhoto(srcURL, destURL, 200);
//		imageService.extractPhoto(srcURL1, destURL1, 100);
		
	}
	
	 /**
     * 通过读取文件并获取其width及height的方式，来判断判断当前文件是否图片，这是一种非常简单的方式。
     * 
     * @param imageFile
     * @return
     */
    public boolean isImage(File imageFile) {
        if (!imageFile.exists()) {
            return false;
        }
        Image img = null;
        try {
            img = ImageIO.read(imageFile);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            img = null;
        }
    }
    
    public void extractPhotoWithFix(String srcURL, String destURL, double extractBase) throws Exception{
		File srcFile = new File(srcURL);
		Image src = ImageIO.read(srcFile);
		int srcHeight = src.getHeight(null);
		int srcWidth = src.getWidth(null);
		int deskHeight = 0;
		int deskWidth = 0;
		if(extractBase <= 0){
			extractBase = defaultBase;
		}
		if(srcWidth <= extractBase && srcHeight <= extractBase){
			deskHeight = srcHeight;
			deskWidth = srcWidth;
		}else if(srcWidth>=srcHeight){
			deskWidth = (int) extractBase;
			double r=deskWidth/Double.valueOf(String.valueOf(srcWidth));
			deskHeight = (int)(srcHeight * r);
		}else{
			deskHeight = (int) extractBase;
			double r=deskHeight/Double.valueOf(String.valueOf(srcHeight));
			deskWidth = (int)(srcWidth * r);
		}
		log.info("原始尺寸："+srcWidth+"*"+srcHeight+"最终尺寸："+deskWidth+"*"+deskHeight+",extractBase："+extractBase);
		extract(src, destURL, deskWidth, deskHeight);
	}

}
