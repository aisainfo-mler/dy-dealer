package com.ailk.yd.mapp.tibco.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class CodeGene {

	/**
	 * 根据xlxs文件生成字段数据
	 * 
	 * @param filePath
	 * @param sheetName
	 * @param startCol
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static void parseLOV(String filePath, String sheetName, int startCol)
			throws IOException, InvalidFormatException {
		Workbook ws = WorkbookFactory.create(new File(filePath));
		Sheet sheet = ws.getSheet(sheetName);
		int lrn = sheet.getLastRowNum();
		String preType = "";

		for (int i = 1; i < lrn; i++) {
			Row row = sheet.getRow(i);
			String type = row.getCell(startCol).getStringCellValue();
			Cell valCell = row.getCell(startCol + 2);
			valCell.setCellType(Cell.CELL_TYPE_STRING);
			String key = row.getCell(startCol + 1).getStringCellValue();

			String value = valCell.getStringCellValue();

			System.err.println("public static String TIBCO_"
					+ type.replaceAll("-", "").replaceAll("_", "")
					+ "_"
					+ key.replaceAll("-", "").replaceAll(" ", "")
							.replaceAll("/", "").replaceAll("\\(", "")
							.replaceAll("\\.", "").replaceAll("\\)", "")
					+ "=\"" + value + "\";");
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
			IOException {
		CodeGene.parseLOV(
				"/Users/qianshihua/Documents/亚信工作文件/印度相关/otherDoc/CAF Fields.xlsx",
				"LOV", 1);
	}

}
