package com.ai.mapp.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ai.mapp.sys.util.SYSConstant;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;


/**
 * @author Zhengwj 
 * @version 创建时间：2012-12-12 下午04:23:39
 * 类说明:
 */

public class EvaluatorUtil {
	public static final Log log = LogFactory.getLog(EvaluatorUtil.class);
	/**
	 * <p>描述:翻译表达式 </p> 
	 * @param:        @param express
	 * @param:        @return    
	 * @return:       String    
	 * @author        Zhengwj
	 * @Date          2012-12-12 下午09:01:15
	 */
	public static String translateExpress(String express){
		if(StringUtil.isEmpty(express)){
			return "";
		}
		Evaluator eva = new Evaluator();
		if(express.matches(".*[a-zA-Z]+.*") && (express.indexOf("#{") == -1)){
			express = backExpress(express);
		}
		String tran = express;
		eva.setVariables(SYSConstant.variantMap);
		try {
			tran = eva.replaceVariables(express);
			
		} catch (EvaluationException e) {
			e.printStackTrace();
		}
		return tran;
	}
	
	public static String orginalExpress(String backExpress){
		if(StringUtil.isEmpty(backExpress)){
			return "";
		}
		Evaluator eva = new Evaluator();
		String orginal = backExpress;
		Map<String, String> oMap = new HashMap<String,String>();
		Set<String> key = SYSConstant.variantMap.keySet();
        String s = null;
        for (Iterator<String> it = key.iterator(); it.hasNext();) {
            s = it.next();
            oMap.put(s,s);
        }
		eva.setVariables(oMap);
		try {
			orginal = eva.replaceVariables(backExpress);
			
		} catch (EvaluationException e) {
			e.printStackTrace();
		}
		return orginal;
	}
	
	public static String backExpress(String oExpress){
		String backExpress = "";
		boolean flag = true;
		for(int i = 0; i < oExpress.length(); i++){
			if((oExpress.charAt(i) >= 'a' && oExpress.charAt(i) >= 'z') || (oExpress.charAt(i) >= 'A' && oExpress.charAt(i) >= 'Z' )){
				if( i != 0){
					if((oExpress.charAt(i - 1) >= 'a' && oExpress.charAt(i - 1) >= 'z') || (oExpress.charAt(i - 1) >= 'A' && oExpress.charAt(i - 1) >= 'Z' )){
						flag = false;
					}
				}else if(i != oExpress.length() - 1){
					if((oExpress.charAt(i + 1) >= 'a' && oExpress.charAt(i + 1) >= 'z') || (oExpress.charAt(i + 1) >= 'A' && oExpress.charAt(i + 1) >= 'Z' )){
						flag = false;
					}
				}
				
			}else{
				flag = false;
			}
			if(flag){
				backExpress += "#{" + oExpress.charAt(i) + "}";
			}else{
				backExpress += oExpress.charAt(i);
			}
			flag = true;
		}
		return backExpress;
	}
	
	public static double getResult(String express,Map<String, String> variantMap)throws Exception{
		Evaluator eva = new Evaluator();
		eva.setVariables(variantMap);
		double result = eva.getNumberResult(express);
		return result;
	}
	
	
	public static boolean isValidExpress(String express){
		if(StringUtil.isEmpty(express)){
			return false;
		}
		boolean flag = true;
		Evaluator eva = new Evaluator();
		eva.clearVariables();
		Map<String, String> orginalMap = new HashMap<String,String>();
		//随便设值
//		orginalMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDERITEM_COUNT, "1");
//		orginalMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDER_AMOUNT, "2");
//		orginalMap.put(SYSConstant.VARIANT_COMMISSION_RULE_REBATE, "3");
//		orginalMap.put(SYSConstant.VARIANT_COMMISSION_RULE_UNITCOST, "4");
//		orginalMap.put(SYSConstant.VARIANT_COMMISSION_RULE_COST, "5");
//		orginalMap.put(SYSConstant.VARIANT_COMMISSION_RULE_UNITREWARD, "6");
//		orginalMap.put(SYSConstant.VARIANT_COMMISSION_RULE_ORDERTYPE, "7");
//        Set<String> key = SYSConstant.variantMap.keySet();
        List<String> key = SYSConstant.variantMapL;
        int i = 0;
//        String s = null;
//        for (Iterator<String> it = key.iterator(); it.hasNext();) {
//            s = it.next();
//            orginalMap.put(s,(++i) + "");
//        }
        for (String s:key) {
            orginalMap.put(s,(++i) + "");
        }
		eva.setVariables(orginalMap);
		try {
			eva.evaluate(express);
			
		} catch (EvaluationException e) {
			log.debug(e.getMessage());
			flag = false;
		}
		return flag;
	}
	public static void main(String args[]) {

		/*
		 * This sample shows the basic usage of the JEval Evaluator class.
		 * Calling the default contructor will set he quoteCharater to single
		 * quote. This constructor will also load all math variables, math
		 * functions and string variables.
		 */
		Evaluator evaluator = new Evaluator();

		try {

			/**
			 * This sample shows nested string functions.
			 */
			System.out.println(evaluator
					.evaluate("toUpperCase(trim( trim(' a b c ') ))"));

			/**
			 * This sample shows nested math functions.
			 */
			System.out.println(evaluator.evaluate("atan2(atan2(1, 1), 1)"));

			/**
			 * Create a new evaluator and turn off nested function support.
			 */
//			evaluator = new Evaluator(EvaluationConstants.SINGLE_QUOTE, true,
//					true, true, false);

			/**
			 * This expression is now invalid, because nested function support
			 * has been turned off.
			 */
//			System.out.println("An exception is expected in the "
//					+ "next evaluation.");
//			System.out.println(evaluator
//					.evaluate("toUpperCase(trim( trim(' a b c ') ))"));
			
			Evaluator eva = new Evaluator();
			eva.setVariables(SYSConstant.variantMap);
			System.out.println(eva.replaceVariables("#{x} * #{y}"));
			
			System.out.println(evaluator.getNumberResult("((1+2000)+(900-30)-(90*3)*(-900*-1))*(0.33)"));
//			System.out.println(evaluator.evaluate("if(1<2) 1+1"));
			System.out.println(isValidExpress("#{x} * #{y}"));
			System.out.println(isValidExpress("((1+2000)+(900-30)-(90*3)*(-900*-1))*(0.33)"));
			 System.out.println(evaluator.evaluate("#{PI}"));
//			 System.out.println(evaluator.evaluate("#{a} + ' ' + #{b} + '!'"));
			 System.out.println(translateExpress("0.5*x"));
//			 System.out.println( "0.5".matches(".*[a-zA-Z]+.*"));
		} catch (Exception ee) {
			System.out.println(ee);
		}
	}
}
