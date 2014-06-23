
package com.ai.mapp.base;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import com.ai.mapp.sys.util.SYSConstant;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class StringUtil extends org.apache.commons.lang.StringUtils{
	public static boolean isEmpty(String string){
		return (string == null || "".equalsIgnoreCase(string.trim()));
	}
	
	public static String cutFileSuffix(String fileName){
		if(fileName==null || "".equals(fileName.trim()))
			return "";
		int index = fileName.lastIndexOf('.');
		if(index<0)
			return "";
		else{
			return fileName.substring(index+1);
		}
	}
	
	public static String buildAutoFileName(String fileName){
		String suffix = cutFileSuffix(fileName);
		Date date = new Date();
		String newFileName = date.getTime()+"."+suffix;
		return newFileName;
	}
	
	public static String getTodayString(){
		return DateToStr(new Date());
	}
	
	public static String DateToStr(Date date, String format){
		return new java.text.SimpleDateFormat(format).format(date);
	}
	
	public static String DateToStr(Date date){
		return DateToStr(date,"yyyy-MM-dd");
	}
	
	public static String ParameterUnit(String url){
		if(isEmpty(url))
			return "?";
		else
			return url+"&";
	}
	public static String DateToStrOracle(Date date, String format){
		String oracleFormat = format;
		if("yyyy-MM-dd HH:mm:ss".equals(format))
			oracleFormat= "yyyy-mm-dd HH24:MI:SS";
		else if("yyyy-MM-dd HH:mm".equals(format))
			oracleFormat= "yyyy-mm-dd HH24:MI";
		else if("yyyy-MM-dd HH".equals(format))
			oracleFormat= "yyyy-mm-dd HH24";
		return "to_date('"+DateToStr(date,format)+"','"+oracleFormat+"')";
	}

	/**
	 * 当天结算开始时间,即当天的00:00
	 * @param date
	 * @return
	 */
	public static String BalanceDateToStrOracle(Date date){
		String balanceStr = DateToStr(date) + " 00:00";
		return "to_date('"+balanceStr+"','yyyy-mm-dd HH24:MI')";
	}
//	/**
//	 * 结算结束时间,明天的凌晨 00:00
//	 * @param date
//	 * @return
//	 */
//	public static String NextBalanceDateToStrOracle(Date date){
//		Date nextDate = DateUtils.getNextDayCurrDay(date, 1);
//		String balanceStr = DateToStr(nextDate) + " 00:00";
//		return "to_date('"+balanceStr+"','yyyy-mm-dd HH24:MI')";
//	}
//	/**
//	 * 结算时间，一般取上一天的 00:00
//	 * @param date
//	 * @return
//	 */
//	public static String LastBalanceDateToStrOracle(Date date){
//		Date lastDate = DateUtils.getNextDayCurrDay(date, -1);
//		String balanceStr = DateToStr(lastDate) + " 00:00";
//		return "to_date('"+balanceStr+"','yyyy-mm-dd HH24:MI')";
//	}
	/**
	 * 结算时间，一般取当天的 16:00
	 */
	public static String DateToStrOracle(Date date){
		return DateToStrOracle(date,"yyyy-MM-dd");
	}
	public static String DateToStrOracleSecond(Date date){
		return DateToStrOracle(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	public static String StrDateToStrOracle(String date,String format) throws ParseException{
			Date d = new java.text.SimpleDateFormat(format).parse(date);
			return DateToStrOracle(d,"yyyy-MM-dd");		
	}
	public static String MoneyToStr(BigDecimal bigDecimal){
		return new java.text.DecimalFormat("###.00").format(bigDecimal);
	}
	
//	public static String NextDateToStrOracle(Date date,String format,int amount){
//		Date nextDate = DateUtils.getNextDayCurrDay(date, amount);
//		String oracleFormat = format;
//		if("yyyy-MM-dd HH:mm:ss".equals(format))
//			oracleFormat= "yyyy-mm-dd HH24:MI:SS";
//		else if("yyyy-MM-dd HH:mm".equals(format))
//			oracleFormat= "yyyy-mm-dd HH24:MI";
//		else if("yyyy-MM-dd HH".equals(format))
//			oracleFormat= "yyyy-mm-dd HH24";
//		return "to_date('"+DateToStr(nextDate,format)+"','"+oracleFormat+"')";
//	}
	
//	public static String NextDateToStrOracle(Date date,String format){
//		return NextDateToStrOracle(date,format,1);
//	}
	
//	public static String NextDateToStrOracle(Date date){
//		return NextDateToStrOracle(date,"yyyy-MM-dd");
//	}
//	
//	public static String NextDateToStrOracle(Date date,int amount){
//		return NextDateToStrOracle(date,"yyyy-MM-dd",amount);
//	}
	public static String checkLinker(String condition) {
		return isEmpty(condition)?"":"&";
	}
	public static String checkSQLLinker(String condition) {
		return isEmpty(condition)?"":"and";
	}
	public static String convertSQLBlank(String sql,boolean bIn){
        if(bIn)
            return sql.replace(' ','?');
        else
            return sql.replace('?',' ');
    }
    
    /**
     * 去除空格和逗号分号转换
     * @param cont
     * @return
     */
    public static String convertBalnkAndSpliter(String cont){       
        return cont.replaceAll(" ","").replaceAll(",","，").replaceAll(";","；");     
    }
        
    public static String Encript(String param){
        
        if(param==null) return null;
        
        byte[] bytes = param.getBytes();
        
        for(int i=0;i<bytes.length;i++){
            bytes[i] ^= 127 ;
            bytes[i] ^= 90 ;
        }       
        
        BASE64Encoder encoder = new BASE64Encoder();        
        return new String(encoder.encode(bytes));
    }
    
    public static String Decript(String param) throws IOException {
        
        if(param==null) return null;
        
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(param);
                
        for(int i=0;i<bytes.length;i++){
            bytes[i] ^= 37 ;
            bytes[i] ^= 0 ;
        }
        
        return new String(bytes);
        
    }   
    
    /*
	 * 字符长度处理,过长切除,不足补空格
	 * 传入长度为0什么都不操作
	 */
	public static String StringBatch(String input,int length){
		String ret = input.trim();
		//int len = input.getBytes().length;
		if(null!=ret){
			
			if(0==length){//0直接返回原来字符串
				return ret;
			}
			
			int len = ret.getBytes().length;
			if(len<=length){
				
				for(int j = 0;j<(length-len);j++){
					ret = ret + " ";
				}
			}else{
				
				if(len==ret.length()){
					ret = ret.substring(0,length);
				}else{
					ret = ret.substring(0,length/2);//.toString();//ret.split("",length);
				}
			}
		}else{//空字符串
			ret = "";
			for(int j = 0;j<(length);j++){
				ret = ret + " ";
			}
		}
		return ret;
	}
	/**********组装字符串***********/
	public static String unitString(String[] a,String b)
	{
		String result="";
		if(a!=null && a.length>0)
		{
			for(int i=0;i<a.length;i++)
			{
				result+=a[i]+b;
			}
		}
		return result;
	}
	
	/**
	 * 过滤空字符串或者以及去掉二头多有空格
	 * @param string
	 * @return
	 */
	public static String filterNullString(String string){
		if(isEmpty(string))
			return "";
		return string.trim();
	}
	public static String toString(Object[] array) {
		int len = array.length;
		if ( len == 0 ) return "";
		StringBuffer buf = new StringBuffer( len * 12 );
		for ( int i = 0; i < len - 1; i++ ) {
			buf.append( array[i] ).append(", ");
		}
		return buf.append( array[len - 1] ).toString();
	}
	public static boolean isNotEmpty(String string) {
		return string != null && string.trim().length() > 0;
	}	
	/**
	 * 获取银行卡号的掩码`
	 * @param cardNo
	 * 		真实银行卡号
	 * @return
	 */
	public static String getBankShade(String cardNo){
		if(StringUtil.isEmpty(cardNo))
			return "";
		if(cardNo.length()<8)
			return cardNo;
		if(cardNo.length()<8)
			return cardNo;
		String pre = cardNo.substring(0,4);
		String pix = cardNo.substring(cardNo.length()-4);
		String middle = cardNo.substring(4,cardNo.length()-4);
		String shard = "";
		for(int i=0; i<middle.length(); i++){
			shard += "*";
		}
		return pre + shard + pix;
	}
	/**
	 * 连接两个字符串，去掉连接部分重复的地方。
	 * 例如把字符串：上海虹桥、虹桥国际机场链接成一个字符串，中间的虹桥不能重复，即上海虹桥国际机场
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String trimjoinString(String str1,String str2){
		if(str1==null&&str2==null){
			return "";
		}else if(str1!=null&&str2==null){
			return str1;
		}else if(str1==null&&str2!=null){
			return str2;
		}else{
			int repeatTemp = 0,maxRepeat = 0;
			int str1Len = str1.length();
			int str2Len = str2.length();
			int minLen = 0;
			if(str1Len>str2Len){
				minLen = str2Len;
			}else{
				minLen = str1Len;
			}
			for(int i=0;i<minLen;i++){
				repeatTemp = 0;
				for(int k=str1Len-i-1,j=0;k<str1Len&&j<str2Len&&j<=i;k++,j++){
					if(str1.charAt(k)==str2.charAt(j)){
						repeatTemp++;
					}else{
						break;
					}
				}
				if(maxRepeat<repeatTemp){
					maxRepeat = repeatTemp;
				}
			}
			return str1 + str2.substring(maxRepeat);
		}
	}
	
	/**
	 * 字符左填充
	 * @param src
	 * @param length
	 * @param padChar
	 * @return
	 */
	public static String lpad(String src,int length,char padChar) {
		return pad(src, length, padChar, true);
	}
	/**
	 * 字符右填充
	 * @param src
	 * @param length
	 * @param padChar
	 * @return
	 */
	public static String rpad(String src,int length,char padChar) {
		return pad(src, length, padChar, false);
	}
	
	/**
	 * 字符串填充
	 * @param src
	 * @param length
	 * @param padChar
	 * @param isLpad
	 * @return
	 */
	public static String pad(String src,int length,char padChar,boolean isLpad) {
		if(src ==  null)  src="";
		int padNum=length-src.length();
		if(padNum <= 0) return src;
		StringBuffer sb= null;
		if(!isLpad) {
			sb =new StringBuffer(src);
		}else {
			sb =new StringBuffer();
		}
		for(int i=0;i<padNum;i++) {
			sb.append(padChar);
		}
		if(isLpad) {
			sb.append(src);
		}
		return sb.toString();
	}
	
	
	
	/**
	 * 
	  * converReturnJson 方法 
	  * <p>常用于dwr返回值 </p> 
	  * @param flag
	  * @param msg
	  * @return 
	  * @return String 
	  * @author zwj 
	  * @date 2012-3-26
	 */
	public static String converReturnJson(boolean flag,String msg){
		String str = "{'flag':" + flag;
		if(isEmpty(msg)){
			str += "}";
		}else{
			str += ",'msg':'" + msg + "'}";
		}
		return str;
	}
	
	/**
	 * <p>描述: </p> 
	 * @param:        @param 0001
	 * @param:        @param 0003
	 * @param:        @param 是否等长  考虑到要否填充
	 * @param:        @return    
	 * @return:       String[]    
	 * @author        Zhengwj
	 * @Date          2012-12-5 下午08:57:14
	 */
	public static String[] getUnitNumString(String from,String end,boolean sameLength){
		from = filterNullString(from);
		end = filterNullString(end);
		if(isEmpty(from) || isEmpty(end)){
			return null;
		}else if(!(from.matches("\\d+") && end.matches("\\d+"))){
			return null;
		}else if(sameLength && from.length() != end.length()){
			return null;
		}else{
			Long fromNum = Long.parseLong(from);
			Long endNum = Long.parseLong(end);
			int strLength = from.length();
			long length = endNum - fromNum;
			String[] result = null;
			String resultUnit = null;
			int resultIndex = 0;
			
			if(length == 0){
				result = new String[]{fromNum.toString()};
			}else{
				if(length < 0){
					length = -length;
					Long tmp = fromNum;
					fromNum = endNum;
					endNum = tmp;
				}
				length += 1;
				result = new String[(int)length];
				for(long i = fromNum; i <= endNum; i++){
					if(sameLength){//如果等长
						resultUnit = lpad(i + "",strLength,'0');
					}else{
						resultUnit = i + "";
					}
					result[resultIndex++] = resultUnit;
				}
				
			}
			return result;
		}
		
	}
	
	public static String appSystemType(String itemKey){
		if (isEmpty(itemKey)) return "";
		String str = "";
		if(itemKey.toLowerCase().indexOf(SYSConstant.ANDROID_SYSTEM.toLowerCase()) != -1){
			str = SYSConstant.ANDROID_SYSTEM;
		}else if(itemKey.toLowerCase().indexOf(SYSConstant.IPHONE_SYSTEM.toLowerCase()) != -1){
			str = SYSConstant.IPHONE_SYSTEM;
		}
		return str;	
	}
	
	public static void main(String[] args){
		String from = "0001";
		String end = "10";
		
		String[] arr = getUnitNumString(from,end,false);
		for(String tmp:arr){
			System.out.println(tmp + ",");
		}
	}
}
