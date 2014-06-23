package com.ai.mapp.base;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MoneyUtils {
	private final static DecimalFormat nf = new DecimalFormat("###,##0.00");
	private final static DecimalFormat nfNum = new DecimalFormat("###,##0");
	
	public static String formatTo2Precision(String s){
		try{
			if(s == null || "".equals(s)) {
				return "0.00";
			}
			BigDecimal bd   =  new BigDecimal((Double.parseDouble(s)));  
			bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);  
			
			return bd.toString();
			}catch (Exception e) {
				e.printStackTrace();
				return String.valueOf(s);
			}
	}
	
	public static String formatToMoney(String s) {
		try{
		if(s == null || "".equals(s) || !s.matches("^[0-9]*$")) {
			return "0.00";
		}
		return formatToMoney(Double.parseDouble(s));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return String.valueOf(s);
		}
	}
	
	public static String formatToMoney(double d) {
		try {
			return nf.format(d);
		} catch(Exception e) {
			return String.valueOf(d);
		}
	}
	
	public static String formatToNum(double d) {
		try {
			return nfNum.format(d);
		} catch(Exception e) {
			return String.valueOf(d);
		}
	}
	
	public static String formatToNum(String d) {
		try {
			if(d == null || "".equals(d)) {
				return "0";
			}
			return nfNum.format(Double.parseDouble(d));
		} catch(Exception e) {
			return String.valueOf(d);
		}
	}
	public static String formatToMoney(BigDecimal d) {
		try {
			return nf.format(d);
		} catch(Exception e) {
			return String.valueOf(d);
		}
	}
	
	/**
	 * <p>描述: </p> 
	 * @param:        @param d 以厘为单位
	 * @param:        @return    
	 * @return:       String    
	 * @author        Zhengwj
	 * @Date          2012-9-22 上午10:27:17
	 */
	public static String formatToMoney(Long d) {
		if(d == null || "".equals(d.toString()))
			return "0.00";
		BigDecimal mon = BigDecimal.ZERO;
		try {
			mon = new BigDecimal(d);
			mon = mon.divide(new BigDecimal(1000));
			return nf.format(mon);
		} catch(Exception e) {
			return String.valueOf(d);
		}
	}
	
	public static String formatToMoney(Object d) {
		BigDecimal mon = BigDecimal.ZERO;
		try {
			if(d != null)
				mon = new BigDecimal((String)d);
			return nf.format(mon);
		} catch(Exception e) {
			return String.valueOf(mon);
		}
	}
	
	
	public static String formatStringtoNum(String d){
		try{
			if(d==null) return "0";
			return nfNum.format(Double.parseDouble(d));
		}catch(Exception ex){
			return String.valueOf(d);
		}
	}
	
	public static String formatStringtoNum(double d){
		try {
			return nfNum.format(d);
		} catch(Exception e) {
			return String.valueOf(d);
		}
	}
	
	public static void main(String[] args) {
		String dd = formatToMoney("12345678990");
		String d = formatToNum(12345678990.0);
		System.out.println(d);
		System.out.println(dd);

	}
}
