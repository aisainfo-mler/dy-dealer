package com.ai.mapp.bss.util;

import java.util.Collection;
import java.util.HashSet;

public class BSSConstantParam {
	
	public static final String SERVICE_TYPE_MOBILE = "";//业务类型
	
	/**
	 * 响应异常编码
	 */
	public static final String RESPONSECODE_SUCCESS = "0000";//成功
	
	public static final String IP = "IP";
	public static final String PORT = "PORT";
	/**
	 * EH2协议报文相关参数
	 */
	public static String EH2_IP_VALUE = "130.36.21.179";
	public static Integer EH2_PORT_VALUE = 7766;//6622
	public static String EH2_ENCODING = "";
	
//	ZQBXZD/ZQBXZD123
	public static final String EH2_LOGINCODE_VALUE = "ZQBXZD";
	public static final String EH2_LOGINPWD_VALUE = "ZQBXZD123";
	
	
	public static final String EH2_CHECK = "EH2_CHECK";
	public static final String EH2_CHECKCODE = "EH2_CHECKCODE";
	public static final String EH2_COMPRESS = "EH2_COMPRESS";
	public static final String EH2_ENCRYPT = "EH2_ENCRYPT";
	public static final String EH2_OPERATOR = "EH2_OPERATOR";//操作员工号
	public static final String EH2_SELLCODE = "EH2_SELLCODE";//营业厅
	public static final String EH2_SERIALNUMBER = "EH2_SERIALNUMBER";
	public static final String EH2_SOCKETCODE = "EH2_SOCKETCODE";//
	public static final String EH2_SVCNO = "EH2_SVCNO";//业务号码
	public static final String EH2_SVCTYPE = "EH2_SVCTYPE";//业务号码类型
	public static final String EH2_VERSION = "EH2_VERSION";//EH2版本
	public static final String EH2_SERVICECODE = "EH2_SERVICECODE";
	public static final String EH2_SVCLEVEL = "EH2_SVCLEVEL";//号码等级
	public static final String EH2_BODY = "EH2_BODY";
	public static final String EH2_MESSAGE = "EH2_MESSAGE";
	public static final String EH2_TYPE = "EH2_TYPE";
	public static final String EH2_IFLOCAL = "EH2_IFLOCAL";
	public static final String EH2_LOGINCODE = "EH2_LOGINCODE";
	public static final String EH2_LOGINPWD = "EH2_LOGINPWD";
	
	public final static char EH2_SOCKET_COMPLETE_CHAR = (char)0x1a;
	public static char[] EH2_ROW_SPLIT_CHAR = {(char) 0x0d,(char)0x0a};
	public static char[] EH2_COL_SPLIT_CHAR = {(char) 0x09};
	public final static String EH2_COL_SPLIT = new String(EH2_COL_SPLIT_CHAR);
	public final static String EH2_ROW_SPLIT = new String(EH2_ROW_SPLIT_CHAR);
	
	/**
	 * DC协议报文相关参数
	 */
	public static String DC_IP_VALUE = "130.36.21.79";
	public static Integer DC_PORT_VALUE = 6699;
	
	public static final String DC_OPERATOR = "DC_OPERATOR";//操作员工号
	public static final String DC_SELLCODE = "DC_SELLCODE";//营业厅
	public static final String DC_SERIALNUMBER = "DC_SERIALNUMBER";
	public static final String DC_SOCKETCODE = "DC_SOCKETCODE";//
	public static final String DC_SVCNO = "DC_SVCNO";//业务号码
	public static final String DC_SVCTYPE = "DC_SVCTYPE";//业务号码类型
	public static final String DC_VERSION = "DC_VERSION";//DC版本
	public static final String DC_SERVICECODE = "DC_SERVICECODE";
	public static final String DC_BODY = "DC_BODY";
	public static final String DC_MESSAGE = "DC_MESSAGE";

	public final static char DC_SOCKET_COMPLETE= 0x1a;

	
	/**
	 * 总部报文
	 */
	public static final String ZB_BIPCODE = "ZB_BIPCODE";
	public static final String ZB_ACTIVITYCODE = "ZB_ACTIVITYCODE";
	
	public static final String ZB_MESSAGE = "ZB_MESSAGE";
	public static final String ZB_BODY = "ZB_BODY";
	public static final String ZB_HEADER = "ZB_HEADER";
	
	/**
	 * 普通service调用
	 */
//	public static final String OBJECT_CLASS = "OBJECT_CLASS";
//	public static final String OBJECT_METHOD = "OBJECT_METHOD";
//	public static final String OBJECT_PARAMCLASSES = "OBJECT_PARAMCLASSES";
//	public static final String OBJECT_PARAMLIST = "OBJECT_PARAMLIST";
	
	/**
	 * session 相关
	 */
	public static final String SESSION_ID = "SESSION_ID";
	public static final String LOGINLOG_ID = "LOGINLOG_ID";
	public static final String IMSI = "IMSI";
	public static final String IMEI = "IMEI";
	public static final String CLIENTVERSION = "CLIENTVERSION";
	public static final String HARDWAREBRAND = "HARDWAREBRAND";
	public static final String HARDWAREMODEL = "HARDWAREMODEL";
	public static final String OS = "OS";
	public static final String FROM = "FROM";
	public static final String USERCODE = "USERCODE";
	public static final String USERID = "USERID";
	public static final String AREAID = "AREAID";
	public static final String COUNTYID = "COUNTYID";
	public static final String DEPT_ID = "DEPT_ID";
	public static final String DEPT_PTYPE = "DEPT_PTYPE";
	public static final String DEPT_TYPE = "DEPT_TYPE";
	public static final String USER_LEVEL = "USER_LEVEL";
	public static final String USE_DOMAIN = "USE_DOMAIN";
	public static final String RIGHT = "RIGHT";
	public static final String LANGUAGE = "LANGUAGE";
	
	/**
	 * MAPP-SERVER客户端请求参数协议
	 */
	public static final String REQUESTCONTENT = "REQUESTCONTENT";
	public static final String BIZCODE = "BIZCODE";
	
	/**
	 * 
	 */
	public static final String BUSIID = "BUSIID";
	public static final String ORDERLOGID = "ORDERLOGID";
	
	public static final Collection<String> PARAMKEYS = new HashSet<String>(0);
	static {
		PARAMKEYS.add(IMSI);
		PARAMKEYS.add(IMEI);
		PARAMKEYS.add(CLIENTVERSION);
		PARAMKEYS.add(HARDWAREBRAND);
		PARAMKEYS.add(HARDWAREMODEL);
		PARAMKEYS.add(OS);
		PARAMKEYS.add(FROM);
		PARAMKEYS.add(USERCODE);
		PARAMKEYS.add(USERID);
		PARAMKEYS.add(AREAID);
		PARAMKEYS.add(COUNTYID);
		PARAMKEYS.add(DEPT_ID);
		PARAMKEYS.add(DEPT_PTYPE);
		PARAMKEYS.add(DEPT_TYPE);
		PARAMKEYS.add(USER_LEVEL);
		PARAMKEYS.add(USE_DOMAIN);
		PARAMKEYS.add(RIGHT);
		PARAMKEYS.add(LANGUAGE);
	}

	
	
	
}
