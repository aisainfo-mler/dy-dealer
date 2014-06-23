package com.ai.mapp.base.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ai.mapp.sys.util.LanguageInfo;


/**
 * @author zwj
 * @version 创建时间：2012-4-5 下午03:50:34
 * 类说明
 */

public class FileUtil {
	private static final int BUFFER_SIZE = 16 * 1024;
	
	
	public static String getFileExt(String fileFullName) {
		return fileFullName.substring(fileFullName.lastIndexOf(".") + 1);
	}
	
	public static String getFileName(String fileFullName)
	{
		return fileFullName.substring(fileFullName.lastIndexOf(System.getProperty("file.separator")) + 1,fileFullName.lastIndexOf("."));
	}
	
	public static void uploadFile(File file, File newFile) throws Exception {
		InputStream in = null;
	    OutputStream out = null;
		try
		{
	        in = new BufferedInputStream(new FileInputStream(file), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(newFile),BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) 
			{
				out.write(buffer, 0, len);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(LanguageInfo.UPLOAD_ERROR);
		}
		finally 
		{
            if (null != in) 
            {
                try 
                {
                    in.close();
                } catch (IOException e) 
                {
                	e.printStackTrace();
                	throw new Exception(LanguageInfo.UPLOAD_ERROR + "," + LanguageInfo.CANNOT_CLOSE_FILE + "!");
                }
            }
            if (null != out) 
            {
                try 
                {
                    out.close();
                } catch (IOException e) 
                {
                	e.printStackTrace();
                	throw new Exception(LanguageInfo.UPLOAD_ERROR + "," + LanguageInfo.CANNOT_CLOSE_FILE + "!");
                }
            }
        }

	}
	
	public static final void main(String[] arg) throws Exception
	{
		String name = "/Users/luyiq/Pictures/test/123123.ext";
		System.out.println(FileUtil.getFileName(name));
		
		
	}
}
