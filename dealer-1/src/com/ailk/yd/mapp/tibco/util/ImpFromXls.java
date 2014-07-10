package com.ailk.yd.mapp.tibco.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.mapp.sys.entity.SysProp;
import com.ai.mapp.sys.service.DataImpService;
import com.ai.mapp.sys.service.DealerDataService;
import com.ai.mapp.sys.service.SysPropService;
import com.ai.mapp.sys.util.SYSConstant;

public class ImpFromXls {
	

	/**
	 * 根据xlxs文件生成字段数据
	 * 
	 * @param filePath
	 * @param sheetName
	 * @param startCol
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public void parseLOV(String filePath, String sheetName, int startCol,SysPropService sps)
			throws IOException, InvalidFormatException {
		Workbook ws = WorkbookFactory.create(new File(filePath));
		Sheet sheet = ws.getSheet(sheetName);
		int lrn = sheet.getLastRowNum();
		String preType = "";

		String preKey = "";
		for (int i = 1; i <= lrn; i++) {
			Row row = sheet.getRow(i);
			String paramType = row.getCell(startCol).getStringCellValue();
			Cell paramValCell = row.getCell(startCol + 2);
			paramValCell.setCellType(Cell.CELL_TYPE_STRING);
			String paramName = row.getCell(startCol + 1).getStringCellValue();
			paramName = paramName.split("\\(")[0];
			
			if(!StringUtils.equals(preKey, paramType)){
				//不相等，需要插入一个parent的阶段
				SysProp sp = new SysProp();
				sp.setKey(paramType);
				sp.setName(paramType);
				sp.setRemark(paramType);
				sp.setValid(SYSConstant.STATE_VALID);
				sps.saveSysProp(sp);
			}
			preKey = paramType;
			String paramValue = paramValCell.getStringCellValue();
			SysProp sp = new SysProp(paramName, paramValue, paramType, paramName, SYSConstant.STATE_VALID);
			sps.saveSysProp(sp);
			// continue;

			//
			// if(!StringUtils.equals(preType, type)){
			// preType = type;
			// System.err.println("mp = new HashMap();");
			// System.err.println("m = new HashMap();");
			// System.err.println("mp.put(\""+preType+"\",m);");
			// System.err.println("list.add(mp);");
			// System.err.println("m.put(\""+key+"\",\""+value+"\");");
			// }else{
			// System.err.println("m.put(\""+key+"\",\""+value+"\");");
			// }
		}
	}
	
	
	

	public static void main(String[] args) throws InvalidFormatException,
			IOException, SQLException, ClassNotFoundException {
		String path = ImpFromXls.class.getResource("/").getPath()
				+ "/qiansh/CAF Fields.xlsx";
		// System.err.println(new File(path).exists());
		// CodeGene.parseLOV(path, "LOV", 1);
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
				"classpath:mapp-base.xml", "classpath:applicationContext*.xml",
				"classpath*:com/ailk/butterfly/**/*applicationContext-butterfly.xml");
		SysPropService sps = ac.getBean(SysPropService.class);
		//从CAF里面导入LOV常量
		new ImpFromXls().parseLOV(path, "LOV", 1,sps);
		//教主写的导入产品信息
		impProductJiaoZhu(ac);
		//导入地域信息
		DataImpService dis = ac.getBean(DataImpService.class);
		dis.imp();
		
	}




	private static void impProductJiaoZhu(ClassPathXmlApplicationContext ac) {
		try
		{
			String pathh = ImpFromXls.class.getResource("/").getPath();
			File file = new File(pathh+"/tibco_product.xml");
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(file);
			DealerDataService dealerDataService =ac.getBean(DealerDataService.class);
			dealerDataService.updateProps(document.getRootElement());
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
