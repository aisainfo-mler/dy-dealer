package com.ai.mapp.sys.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.sys.entity.HwCircle;
import com.ai.mapp.sys.entity.HwCity;
import com.ai.mapp.sys.entity.HwCountry;
import com.ai.mapp.sys.entity.HwDistrict;
import com.ai.mapp.sys.entity.HwState;
import com.ailk.yd.mapp.client.model.HW0038Response;
import com.ailk.yd.mapp.client.model.HW0038Response.City;
import com.ailk.yd.mapp.tibco.TibcoCache;

@Service
@Scope("prototype")
@Transactional
public class DataImpService {

	@Autowired
	private HwCountryService hwCountryService;
	@Autowired
	private HwStateService hwStateService;
	@Autowired
	private HwCircleService hwCircleService;
	@Autowired
	private HwCityService hwCityService;
	@Autowired
	private HwDistrictService hwDirstrictService;
	

	public static boolean ifBack = false;
	
	
	public static void sort(final Map<String,String> m){
		if(m==null || m.size()==0){
			return;
		}
		Map<String,String> rm = new LinkedHashMap<String, String>();
		Set<String> ks = m.keySet();
		List<String> list = new ArrayList<String>();
		list.addAll(ks);
		Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String key1, String key2) {
//				if(StringUtils.equalsIgnoreCase(key1, "in")&&StringUtils.equalsIgnoreCase(m.get(key1).toString(), "india")){
//					//印度国家优先排第一个
//					return -1;
//				}
				return key1.compareTo(key2);
			}
		});
		for(int i=0;i<list.size();i++){
			String key = (String) list.get(i);
			rm.put(key,m.get(key));
		}
		m.clear();
		m.putAll(rm);
	}
	
	/**
	 * 缓存地域相关信息。circle,state,country,district,cityInState
	 */
	public void cacheDataStartUp(){

		TibcoCache.states = new LinkedHashMap<String,String>();
		for (Iterator it = hwStateService.listAllHwState(null).iterator(); it.hasNext();) {
			HwState hs = (HwState) it.next();
			TibcoCache.states.put(hs.getStateName(), hs.getStateCode());
		}

		
		TibcoCache.districtInState = new LinkedHashMap<String,Map<String,String>>();
		for (Iterator it = hwDirstrictService.lsitAllHwDistrict(null).iterator(); it.hasNext();) {
			HwDistrict hd = (HwDistrict) it.next();
			if(StringUtils.isNotBlank(hd.getStateCode())){
				if(TibcoCache.districtInState.containsKey(hd.getStateCode())){
					((Map)TibcoCache.districtInState.get(hd.getStateCode())).put(hd.getDistrictName(), hd.getDistrictGisCode());
				}else{
					TibcoCache.districtInState.put(hd.getStateCode(), new LinkedHashMap());
				}
			}
		}
		
		TibcoCache.countrys = new LinkedHashMap<String,String>();
		TibcoCache.countrys.put("India", "IN");//印度排第一个
		for (Iterator it = hwCountryService.listAllHwCountry(null).iterator(); it.hasNext();) {
			HwCountry hw = (HwCountry) it.next();
			if(StringUtils.equals(hw.getCountryCode(), "IN"))
				continue;
			TibcoCache.countrys.put(hw.getCountryName(), hw.getCountryCode());
		}
		
		TibcoCache.cityInState = new LinkedHashMap();
		for (Iterator iterator = hwCityService.listAllHwCity(null).iterator(); iterator.hasNext();) {
			HwCity hc = (HwCity) iterator.next();
			HwState st = hc.getState();
			if(st!=null){
				String stateCode = st.getStateCode();
				if(!TibcoCache.cityInState.containsKey(stateCode)){
					List cism = new ArrayList();
					cism.add(new HW0038Response.City(hc.getCityCode(),hc.getCityName(),hc.getCircleCode()));
					TibcoCache.cityInState.put(stateCode,cism);
				}else{
					HW0038Response.City c = new HW0038Response.City(hc.getCityCode(),hc.getCityName(),hc.getCircleCode());
					((List)TibcoCache.cityInState.get(stateCode)).add(c);
				}
			}
		}
		
		TibcoCache.circles = new LinkedHashMap<String,String>();
		for (Iterator it = hwCircleService.lsitAllHwCircle(null).iterator(); it.hasNext();) {
			HwCircle hc = (HwCircle) it.next();
			TibcoCache.circles.put(hc.getCircle_name(), hc.getCircle_code());
		}

		//排序
//		sort(TibcoCache.circles);
//		sort(TibcoCache.countrys);
//		sort(TibcoCache.states);
//		Set<String> ks = TibcoCache.cityInState.keySet();
//		for (Iterator it = ks.iterator(); it.hasNext();) {
//			String key = (String) it.next();
//			List<City> list = TibcoCache.cityInState.get(key);
//			if(list!=null && list.size()>0){
//				Collections.sort(list, new Comparator<City>() {
//					@Override
//					public int compare(City c1, City c2) {
//						return c1.getCityName().compareTo(c2.getCityName());
//					}
//				});
//			}
//		}
//		
//		Set<String> kss = TibcoCache.districtInState.keySet();
//		for (Iterator it = kss.iterator(); it.hasNext();) {
//			String key = (String) it.next();
//			Map<String, String> districts = TibcoCache.districtInState.get(key);
//			sort(districts);
//		}
		
		
	}
	
	
	public static void main(String[] args) {
		TibcoCache.circles = new LinkedHashMap<String,String>();
		TibcoCache.circles.put("yy", "aa");
		TibcoCache.circles.put("dd", "dd");
		TibcoCache.circles.put("ll", "hh");
		TibcoCache.circles.put("xx", "bb");
		TibcoCache.circles.put("qq", "cc");
		TibcoCache.circles.put("uu", "zz");
		TibcoCache.circles.put("aa", "yy");
		System.err.println(TibcoCache.circles);
		sort(TibcoCache.circles);
		System.err.println(TibcoCache.circles);
		System.err.println(TibcoCache.circles);
	}

	

	public  void imp() throws InvalidFormatException,
			IOException, SQLException, ClassNotFoundException {
		URL resource = this.getClass().getResource("/");
		String filePath = resource.getPath()+"qiansh/ReferenceDataInquiry.xlsx";


		FileInputStream fis = new FileInputStream(new File(filePath));

		Map districtNameCodeMap = new HashMap();// key为name，value为code
		Map stateNameCodeMap = new HashMap();//key为statename，value为statecode
		Map cityNameDisCodeMap = new HashMap();// key为cityname，value为distract的code。来自pincode
		Map cityNameStateCodeMap = new HashMap();//key为cityname，value为state的code。来自pincode
		Map cityNamePincodesMap = new HashMap();// key为cityname，value为pincode的集合，逗号分割
		Map countryMap = new HashMap();// key为countryCode,value为country对象

		Workbook wb = WorkbookFactory.create(fis);

		parseCircle(wb);
		parseState(null, wb,stateNameCodeMap);
		parseDistract(districtNameCodeMap, null, wb);
		parsePinCode(cityNameDisCodeMap, cityNamePincodesMap, wb,cityNameStateCodeMap,stateNameCodeMap,districtNameCodeMap);
		parseCity(districtNameCodeMap, cityNameDisCodeMap, cityNamePincodesMap, null,
				wb,cityNameStateCodeMap);

		parseCountry(wb);
		fis.close();

	}

	private void parseCountry( Workbook wb) {
		Sheet districtSheet = wb.getSheet("COUNTRY&NATIONALITY");
		int rowNumd = districtSheet.getLastRowNum();
		for (int i = 1; i < rowNumd; i++) {
			String countryCode = districtSheet.getRow(i).getCell(0)
					.getStringCellValue();
			String countryName = districtSheet.getRow(i).getCell(1)
					.getStringCellValue();
			String nationalName = districtSheet.getRow(i).getCell(2)
					.getStringCellValue();

			HwCountry cou = new HwCountry();
			cou.setCountryCode(countryCode);
			cou.setCountryName(countryName);
			cou.setNationalltyName(nationalName);
			this.hwCountryService.saveCountry(cou);
			
		}
	}


	private  void parseCity(Map districtMap,
			Map cityDisNameMap, Map cityPincodesMap, BufferedWriter bw,
			Workbook wb,Map cityStateNameMap) throws IOException, SQLException, ClassNotFoundException {
		Sheet citySheet = wb.getSheet("CITY_VILLAGE");
		int rowNum = citySheet.getLastRowNum();
		
		
		for (int i = 1; i < rowNum; i++) {
			String cityCode = citySheet.getRow(i).getCell(0)
					.getStringCellValue();
			String cityName = citySheet.getRow(i).getCell(1)
					.getStringCellValue();
			String districtCode = citySheet.getRow(i).getCell(2)
					.getStringCellValue();
			Cell stateCell = citySheet.getRow(i).getCell(3);
			String stateCode = stateCell.getStringCellValue();
			String circleCode = citySheet.getRow(i).getCell(4)
					.getStringCellValue();
			
			
//			String circleCode = citySheet.getRow(i).getCell(2)
//					.getStringCellValue();
//			String districtCode = (String) cityDisNameMap
//					.get(cityName);
//			Object tmp = cityStateNameMap.get(cityName);
//			String stateCode = tmp == null ? "" : tmp.toString();

			Object pincodes = cityPincodesMap.get(cityName);
			HwCity c = new HwCity();
			c.setCityCode(cityCode);
			c.setCityName(cityName);
			HwState s = new HwState();
			s.setStateCode(stateCode);
			s.setStateId(0l);
			c.setState(s);
			
			c.setDistrictCode(districtCode);
			c.setCircleCode(circleCode);
			c.setPincodes(pincodes==null?"":pincodes.toString());
			this.hwCityService.saveCity(c);
		}
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

	private void parseDistract(Map districtMap, BufferedWriter bw,
			Workbook wb) throws IOException, SQLException,
			ClassNotFoundException {
		Sheet districtSheet = wb.getSheet("DISTRICT");
		int rowNumd = districtSheet.getLastRowNum();
		for (int i = 1; i < rowNumd; i++) {
			String districtCode = districtSheet.getRow(i).getCell(0)
					.getStringCellValue();
			String districtName = districtSheet.getRow(i).getCell(1)
					.getStringCellValue();
			String parentCode_stateCode = districtSheet.getRow(i).getCell(2)
					.getStringCellValue();

			HwDistrict dis = new HwDistrict();
			dis.setDistrictGisCode(districtCode);
			dis.setDistrictName(districtName);
			dis.setStateCode(parentCode_stateCode);
			this.hwDirstrictService.saveDistrict(dis);
			
			districtMap.put(districtName, districtCode);
		}
	}

	private void parseState(BufferedWriter bw, Workbook wb,Map stateMap)
			throws IOException, SQLException, ClassNotFoundException {
		Sheet stateSheet = wb.getSheet("STATE");
		int rowNum = stateSheet.getLastRowNum();
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
			stateMap.put(stateName, stateCode);
			HwState s= new HwState();
			s.setStateCode(stateCode);
			s.setStateName(stateName);
			s.setStateDesc(stateSapCode);
			this.hwStateService.saveState(s);
		}
	}

	private void parseCircle(Workbook wb) throws IOException,
			SQLException, ClassNotFoundException {
		Sheet circleSheet = wb.getSheet("CIRCLECODE");
		int rowNum = circleSheet.getLastRowNum();
		for (int i = 1; i < rowNum; i++) {
			String circleCode = circleSheet.getRow(i).getCell(0)
					.getStringCellValue();
			String circleName = circleSheet.getRow(i).getCell(1)
					.getStringCellValue();
			String sql = "insert into hw_circle (circle_code,circle_name) values('"
					+ circleCode + "','" + circleName + "')";
			
			HwCircle c = new HwCircle();
			c.setCircle_code(circleCode);
			c.setCircle_name(circleName);
			this.hwCircleService.saveCircle(c);
		}

	}

}
