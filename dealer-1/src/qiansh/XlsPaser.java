package qiansh;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class XlsPaser {

	public static Connection con = null;
	public static Statement stmt;

	public static boolean ifBack = false;

	public static Connection getCon() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		if (con == null)
			con = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/haiwai_0630?useUnicode=true&amp;characterEncoding=UTF-8",
							"root", "");
		return con;
	}

	public static Statement getStmt() throws SQLException,
			ClassNotFoundException {
		if (stmt == null) {
			stmt = getCon().createStatement();
		}
		return stmt;
	}

	public static void main(String[] args) throws InvalidFormatException,
			IOException, SQLException, ClassNotFoundException {
		String filePath = "/Users/qianshihua/Documents/亚信工作文件/印度相关/地域信息以及部分字典信息ReferenceDataInquiry.xlsx";
		String outPutFile = "/Users/qianshihua/Documents/亚信工作文件/印度相关/inport.sql";

		Connection con = getCon();

		FileInputStream fis = new FileInputStream(new File(filePath));
		File ofile = new File(outPutFile);
		if (ofile.exists() == false)
			ofile.createNewFile();
		FileOutputStream fos = new FileOutputStream(ofile);

		Map districtNameCodeMap = new HashMap();// key为name，value为code
		Map stateNameCodeMap = new HashMap();//key为statename，value为statecode
		Map cityNameDisCodeMap = new HashMap();// key为cityname，value为distract的code。来自pincode
		Map cityNameStateCodeMap = new HashMap();//key为cityname，value为state的code。来自pincode
		Map cityNamePincodesMap = new HashMap();// key为cityname，value为pincode的集合，逗号分割

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		Workbook wb = WorkbookFactory.create(fis);

		parseCircle(wb);
		parseState(bw, wb,stateNameCodeMap);
		parseDistract(districtNameCodeMap, bw, wb);
		parsePinCode(cityNameDisCodeMap, cityNamePincodesMap, wb,cityNameStateCodeMap,stateNameCodeMap,districtNameCodeMap);
		parseCity(districtNameCodeMap, cityNameDisCodeMap, cityNamePincodesMap, bw,
				wb,cityNameStateCodeMap);

		bw.flush();
		bw.close();
		fis.close();
		getCon().close();

	}

	private static void parseCity(Map districtMap,
			Map cityDisNameMap, Map cityPincodesMap, BufferedWriter bw,
			Workbook wb,Map cityStateNameMap) throws IOException, SQLException, ClassNotFoundException {
		Sheet citySheet = wb.getSheet("CITY_VILLAGE");
		int rowNum = citySheet.getLastRowNum();
		
		Statement st = getStmt();
		if (ifBack == true) {
			String f = new SimpleDateFormat("yyyyMMdd").format(new Date());
			st.execute("DROP TABLE IF EXISTS hw_city_" + f);
			st.execute("create TABLE hw_city_" + f + " SELECT * from hw_city");
		}
		st.execute("delete from hw_city");
		
		for (int i = 1; i < rowNum; i++) {
			String cityCode = citySheet.getRow(i).getCell(0)
					.getStringCellValue();
			String cityName = citySheet.getRow(i).getCell(1)
					.getStringCellValue();
			String circleCode = citySheet.getRow(i).getCell(2)
					.getStringCellValue();
			String districtCode = (String) cityDisNameMap
					.get(cityName);
			Object tmp = cityStateNameMap.get(cityName);
			String stateCode = tmp == null ? "" : tmp.toString();

			String sql = "insert into hw_city(city_code,city_name,state_code,district_code,circle_code,pincodes) VALUES('"
					+ cityCode
					+ "','"
					+ cityName
					+ "','"
					+ stateCode
					+ "','"
					+ districtCode
					+ "','"
					+ circleCode
					+ "','"
					+ cityPincodesMap.get(cityName) + "');";
			bw.write(sql + ";\r\n");
			if(cityPincodesMap.get(cityName)!=null && cityPincodesMap.get(cityName).toString().length()>200)
				System.err.println(cityPincodesMap.get(cityName).toString().length());
			st.addBatch(sql);
		}
		st.executeBatch();
	}

	private static void parsePinCode(Map cityDisNameMap, Map cityPincodesMap,
			Workbook wb,Map cityStateNameMap,Map stateMap,Map districtMap) {
		Sheet pinCodeSheet = wb.getSheet("PINCODE");
		int rowNump = pinCodeSheet.getLastRowNum();
		for (int i = 1; i < rowNump; i++) {
			Cell cell = pinCodeSheet.getRow(i).getCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String pinCode = cell.getStringCellValue();
			String cityName = pinCodeSheet.getRow(i).getCell(1)
					.getStringCellValue();
			String districtName = pinCodeSheet.getRow(i).getCell(2)
					.getStringCellValue();
			String stateName = pinCodeSheet.getRow(i).getCell(3)
					.getStringCellValue();
			if (!cityDisNameMap.containsKey(cityName)) {
				cityDisNameMap.put(cityName, districtMap.get(districtName));
			}
			if (!cityStateNameMap.containsKey(cityName)) {
				cityStateNameMap.put(cityName, stateMap.get(stateName));
			}
			if (!cityPincodesMap.containsKey(cityName)) {
				cityPincodesMap.put(cityName, pinCode);
			} else {
				cityPincodesMap.put(cityName, cityPincodesMap.get(cityName)
						+ "," + pinCode);
			}
		}
	}

	private static void parseDistract(Map districtMap, BufferedWriter bw,
			Workbook wb) throws IOException, SQLException,
			ClassNotFoundException {
		Sheet districtSheet = wb.getSheet("DISTRICT");
		int rowNumd = districtSheet.getLastRowNum();
		Statement st = getStmt();
		if (ifBack == true) {
			String f = new SimpleDateFormat("yyyyMMdd").format(new Date());
			st.execute("DROP TABLE IF EXISTS hw_district_" + f);
			st.execute("create TABLE hw_district_" + f
					+ " SELECT * from hw_district");
		}
		st.execute("delete from hw_district");
		for (int i = 1; i < rowNumd; i++) {
			String districtCode = districtSheet.getRow(i).getCell(0)
					.getStringCellValue();
			String districtName = districtSheet.getRow(i).getCell(1)
					.getStringCellValue();
			String parentCode_stateCode = districtSheet.getRow(i).getCell(2)
					.getStringCellValue();
			String sql = "insert into hw_district(district_gis_code,district_name,state_code) values('"
					+ districtCode
					+ "','"
					+ districtName
					+ "','"
					+ parentCode_stateCode + "')";

			bw.write(sql + ";\r\n");
			st.addBatch(sql);
			districtMap.put(districtName, districtCode);
		}
		st.executeBatch();
	}

	private static void parseState(BufferedWriter bw, Workbook wb,Map stateMap)
			throws IOException, SQLException, ClassNotFoundException {
		Sheet stateSheet = wb.getSheet("STATE");
		int rowNum = stateSheet.getLastRowNum();
		Statement st = getStmt();
		if (ifBack == true) {
			String f = new SimpleDateFormat("yyyyMMdd").format(new Date());
			st.execute("DROP TABLE IF EXISTS hw_state_" + f);
			st.execute("create TABLE hw_state_" + f + " SELECT * from hw_state");
		}
		st.execute("delete from hw_state");
		for (int i = 1; i < rowNum; i++) {
			String stateCode = stateSheet.getRow(i).getCell(0)
					.getStringCellValue();
			String stateName = stateSheet.getRow(i).getCell(2)
					.getStringCellValue();
			String stateSapCode = stateSheet.getRow(i).getCell(1)
					.getStringCellValue();
			String sql = "insert into hw_state(state_code,state_name,state_desc) values('"
					+ stateCode
					+ "','"
					+ stateName
					+ "','"
					+ stateSapCode
					+ "')";
			st.addBatch(sql);
			stateMap.put(stateName, stateCode);
			bw.write(sql + ";\r\n");
		}
		st.executeBatch();
	}

	private static void parseCircle(Workbook wb) throws IOException,
			SQLException, ClassNotFoundException {
		Sheet circleSheet = wb.getSheet("CIRCLECODE");
		int rowNum = circleSheet.getLastRowNum();
		Statement st = getStmt();
		if (ifBack == true) {
			String f = new SimpleDateFormat("yyyyMMdd").format(new Date());
			st.execute("DROP TABLE IF EXISTS hw_circle_" + f);
			st.execute("create TABLE hw_circle_" + f
					+ " SELECT * from hw_circle");
		}
		st.execute("delete from hw_circle");
		for (int i = 1; i < rowNum; i++) {
			String circleCode = circleSheet.getRow(i).getCell(0)
					.getStringCellValue();
			String circleName = circleSheet.getRow(i).getCell(1)
					.getStringCellValue();
			String sql = "insert into hw_circle (circle_code,circle_name) values('"
					+ circleCode + "','" + circleName + "')";
			st.addBatch(sql);
		}
		st.executeBatch();

	}

}
