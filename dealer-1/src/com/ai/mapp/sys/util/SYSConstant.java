package com.ai.mapp.sys.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.FileManageCondition;


public class SYSConstant {
	/**
	 * tibco 超级管理员
	 */
	public static final Long USER_ID_TIBCO = 0L;
	
	/** 默认的MAPP系统应用key **/
	public static final String MAPP_APPLICATION_PRODUCTKEY = "MAPP";
	/** 超级管理员角色 **/
	public static final String MAPP_SUPER_ADMIN = "MAPP_SUPER_ADMIN";
	
	public static final String MAPP_SYS_DEFAULT = "mapp_sys";
	
	/**MappInterface 的inputType入参类型**/
	public static final String INPUT_TYPE_OBJECT = "object";
	public static final String INPUT_TYPE_XML = "xml";
	public static final String INPUT_TYPE_CUSTOM = "custom";

	/**MappService的初始化形式*/
	public static final String SERVICE_INIT_TYPE_SPRING = "spring";
	public static final String SERVICE_INIT_TYPE_NEW = "new";
	
	/**MappInterface的outputType出参类型**/
	public static final String OUTPUT_TYPE_CUSTOM = "custom";
	public static final String OUTPUT_TYPE_OBJECT = "object";
	public static final String OUTPUT_TYPE_DEFAULT = "default";
	
	/**获取数据的源 MappParamMapping的fetchSource**/
	public static final String FETCH_SOURCE_SESSION = "session";
	public static final String FETCH_SOURCE_OBJECT = "object";
	public static final String FETCH_SOURCE_STATIC = "static";
	public static final String FETCH_SOURCE_CONSTANT = "constant";
	public static final String FETCH_SOURCE_SYSDATE = "sysdate";
	
	/** MappXmlProtocol的constraint **/
	public static final String ELEMENT_TYPE_XML_ELEMENT = "element";
	public static final String ELEMENT_TYPE_XML_ATTR = "attr";
	/** MappXmlProtocol的elementType **/
	public static final String ELEMENT_CONSTRAINT_0_N = "*";
	public static final String ELEMENT_CONSTRAINT_1 = "1";
	public static final String ELEMENT_CONSTRAINT_0_1 = "?";
	public static final String ELEMENT_CONSTRAINT_1_N = "+";
	
	
	public static final String CONVERT_TYPE_OBJECT = "object";
	public static final String CONVERT_TYPE_CUSTOM = "custom";
	
	/**通过PropertyUtils根据fieldName设置和获取的方式**/
	public static final String SETGET_TYPE_NEST = "nest";//a.b
	public static final String SETGET_TYPE_SIMPLE = "simple";//a
	public static final String SETGET_TYPE_INDEX = "index";//a.b[0]
	public static final String SETGET_TYPE_MAP = "map";
	public static final String SETGET_TYPE_OWN = "own";
	public static final String SETGET_TYPE_OWN_ARRAY = "own_array";//0,1,3
	public static final String SETGET_TYPE_OWN_LIST = "own_list";
	public static final String SETGET_TYPE_OWN_MAP = "own_map";
	
	public static final String LANGUAGE_CHINA = "zh_CN";
	public static final String LANGUAGE_ENGLISH = "en_US";
	
	/**
	 * 文件归属性质 0:证件照  1：广告照  2：其它
	 */
	public static final String SMALL_LOCAL_FILE_DOMAIN_CERTIFICATION = "0";
	public static final String SMALL_LOCAL_FILE_DOMAIN_PROMOTION = "1";
	public static final String SMALL_LOCAL_FILE_DOMAIN_OTHER = "2";
	/**
	 * mapp_sys_prop
	 */
	public static final String MAPP_SYS_PROP_ROOT = "ROOT";//根节点
	public static final String SYSTEM_PARAM = "SYSTEM_PARAM";//系统参数类型
	public static final String STATIC_PARAM = "STATIC_PARAM";//静态参数类型
	public static final String MENU_PARAM = "MENU_TYPE";//菜单参数类型
	
	//上传图片的目录
	public static final String FILE_IMG_LOCAL_PATH = "mappImgFile";
	//上传应用的目录
	public static final String FILE_LOCAL_PATH = "mappFile";
	//上传文件的路径
	public static final String MAPP_UPLOAD_URL_HTTP = "MAPP_UPLOAD_URL_HTTP";//对应mapp_sys_prop
	//console的路径
	public static final String MAPP_CONSOLE_URL_HTTP = "MAPP_CONSOLE_URL_HTTP";//对应mapp_sys_prop
	//上传的文件类型
	public static final String ANDROID_SYSTEM = "android";
	//上传的文件类型
	public static final String IPHONE_SYSTEM = "iphone";
	
	public static final String SYSPROP_PROPERTIES_PARENTKEY = "MAPP_PROPERTIES";
	
	/**
	 * IPHONE管理参数系统参数关键字
	 */
	public static final String IPHONE_APP_CUR_VERSION = "IPHONE_APP_CUR_VERSION";
	public static final String IPHONE_APP_REQ_VERSION = "IPHONE_APP_REQ_VERSION";
	public static final String IPHONE_PLIST_ADDRESS = "IPHONE_PLIST_ADDRESS";
	
	/**
	 * IPAD管理参数系统参数关键字
	 */
	public static final String IPAD_APP_CUR_VERSION = "IPAD_APP_CUR_VERSION";
	public static final String IPAD_APP_REQ_VERSION = "IPAD_APP_REQ_VERSION";
	public static final String IPAD_PLIST_ADDRESS = "IPAD_PLIST_ADDRESS";
	public static final String IOS_DOWNLOAD_FRONT = "itms-services://?action=download-manifest&url=";

	/**
	 * ANDROID手机管理参数系统参数关键字
	 */
	public static final String APHONE_APP_CUR_VERSION = "APHONE_APP_CUR_VERSION";
	public static final String APHONE_APP_REQ_VERSION = "APHONE_APP_REQ_VERSION";
	public static final String APHONE_APK_ADDRESS="APHONE_APK_ADDRESS";// apk所在文件的地址
	/**
	 * ANDROID PAD管理参数系统参数关键字
	 */
	public static final String APAD_APP_CUR_VERSION = "APAD_APP_CUR_VERSION";
	public static final String APAD_APP_REQ_VERSION = "APAD_APP_REQ_VERSION";
	public static final String APAD_APK_ADDRESS="APAD_APK_ADDRESS";// apk所在文件的地址
	
	//web工程的目录 
	public static String REAL_PATH = "";
	
	/**临时状态*/
	public static Integer STATUS_TEMPORARY = new Integer(0);
	
	/**正式状态*/
	public static Integer STATUS_OFFICIAL = new Integer(1);
	
	/**草稿状态*/
	public static Integer STATUS_UNOFFICIAL = new Integer(2);
	
	/**删除状态*/
	public static Integer STATUS_DELETE = new Integer(9);
	
	public static String FILE_EXT_IMAGE = "01";//图片格式 
	
	public static String FILE_EXT_NOTIMAGE = "02";//附件格式
	
	/**列表图*/
	public static String FILE_TYPE_LIST = "0";
	
	public static String FILE_TYPE_SHOW = "1";//展示图
	
	public static String FILE_TYPE_BIG = "2";//大图
	
	/**原图*/
	public static String FILE_TYPE_ORIGINAL = "3";//原图
	
	public static String FILE_TYPE_ATTACHMENT = "4";//文件附件
	
	public static String File_TYPE_MOBILE_DETAIL = "5";//手机详细信息图
	
	public static String File_TYPE_MOBILE_CONTRACT = "6";//手机合约介绍图

	public static final String USER_TYPE_ADMIN = "0";
	
	public static final String USER_TYPE_AGENT = "1";
	
	public static final String USER_TYPE_CUSTOMER = "2";
	
	//佣金变量指标
//	public static final String VARIANT_COMMISSION_RULE_ORDERITEM_COUNT = "c";//订单数量
	
	/**
	 * 订单总金额
	 */
	public static final String VARIANT_COMMISSION_RULE_ORDER_AMOUNT = "x";//订单总金额
	
	/**
	 * 佣金率
	 */
	public static final String VARIANT_COMMISSION_RULE_REBATE = "y";//佣金率
	
//	public static final String VARIANT_COMMISSION_RULE_UNITCOST = "n";//单成本费
//	
//	public static final String VARIANT_COMMISSION_RULE_COST = "m";//总成本费
//	
//	public static final String VARIANT_COMMISSION_RULE_UNITREWARD = "s";//单奖励费
	
	/**
	 * 订单类型
	 */
	public static final String VARIANT_COMMISSION_RULE_ORDERTYPE = "t";//订单类型
	
	
	
	public static final String COMMISSION_BACK_TYPE_MAKE_UP = "1";//"立返";
	
	public static final String COMMISSION_BACK_TYPE_DAILY = "2";//日结
	
	public static final String COMMISSION_BACK_TYPE_MONTHLY = "3";//月结
	
	public static final String COMMISSION_PAY_TYPE_IMMEDIATELY = "0";//立结
	
	public static final String COMMISSION_PAY_TYPE_DAILY = "1";//日结
	
	public static final String COMMISSION_PAY_TYPE_ENDMONTH = "2";//月底结
	
	public static final String COMMISSION_PAY_TYPE_NEXTMONTH = "3";//下月初结
	
	public static final String COMMISSION_PAY_TYPE_MONTHLY = "4";//分月结
	
	public static final String COMMISSION_PAY_TYPE_ENDYEAR = "5";//年底结
	
	/**
	 * 操作类型:新增
	 */
	public static final String OPTTYPE_INSERT = "0";
	/**
	 * 操作类型:修改
	 */
	public static final String OPTTYPE_UPDATE = "1";
	/**
	 * 操作类型:删除
	 */
	public static final String OPTTYPE_DELETE = "2";
	
	
	/**
	 * 商品操作类型： 01. 进货入库;
	 */
	public static final String SELL_DETAIL_OPTTYPE_2_REP = "01";
	/**
	 * 商品操作类型： 03. 销售给普通用户;
	 */
	public static final String SELL_DETAIL_OPTTYPE_TIBCO_2_USER = "03";
	/**
	 * 商品操作类型：04. 销售给代理商;
	 */
	public static final String SELL_DETAIL_OPTTYPE_TIBCO_2_CHANNEL = "04";
	/**
	 * 商品操作类型：05.普通用户退货入平台库;
	 */
	public static final String SELL_DETAIL_OPTTYPE_USER_2_OSOONS = "05";
	/**
	 * 商品操作类型：06 代理商退货入平台库 ;
	 */
	public static final String SELL_DETAIL_OPTTYPE_CHANNEL_2_TIBCO = "06";
	/**
	 * 商品操作类型：07 代理商销售给普通用户;
	 */
	public static final String SELL_DETAIL_OPTTYPE_CHANNEL_2_USER = "07";
	/**
	 * 商品操作类型：08 普通用户退货入代理商库
	 */
	public static final String SELL_DETAIL_OPTTYPE_USER_2_CHANNEL = "08";
	
	
	public static Map<String,String> variantMap = new LinkedHashMap<String, String>(0);
	public static List<String> variantMapL = new ArrayList<String>();
	
	public static Map<String,String> commissionBackType = new LinkedHashMap<String,String>(0);
	public static List<String> commissionBackTypeL = new ArrayList<String>();
	public static Map<String,String> commissionPayType = new LinkedHashMap<String,String>(0);
	public static Map<String,String> isValidMap = new LinkedHashMap<String, String>(0);
	public static List<String> isValidMapL = new ArrayList<String>();
	
	public static Map<String,String> inputTypes = new LinkedHashMap<String, String>();
	public static Map<String,String> outputTypes = new LinkedHashMap<String, String>();
	public static Map<String,String> initTypes = new LinkedHashMap<String, String>();
	public static Map<String,String> fetchSources = new LinkedHashMap<String, String>();
	public static Map<String,String> elementTypes = new LinkedHashMap<String, String>();
	public static Map<String,String> constraintTypes = new LinkedHashMap<String, String>();
	public static Map<String,String> convertTypes = new LinkedHashMap<String, String>();
	public static Map<String,String> setgetTypes = new LinkedHashMap<String, String>();
	public static Map<String,String> mobilePriceTypes = new LinkedHashMap<String, String>();
	public static Map<String,String> mobileExteriorTypes = new LinkedHashMap<String, String>();
	public static Map<String,String> osTypes = new LinkedHashMap<String, String>();
	public static Map<String,String> networkTypes = new LinkedHashMap<String, String>();
	public static Map<String,String> screenTypes = new LinkedHashMap<String, String>();
	public static Map<String,String> ifTypes = new LinkedHashMap<String, String>();
	public static Map<String,String> fileTypes = new LinkedHashMap<String, String>();
	public static Map<String,String> useDomainTypes = new LinkedHashMap<String, String>();
	public static Map<String,String> stateTypes = new LinkedHashMap<String, String>();
	public static Map<String,String> systemTypes = new LinkedHashMap<String, String>();
	public static Map<String,String> certificateTypes = new LinkedHashMap<String, String>(0);
	public static Map<String,Map> dict = new HashMap<String, Map>();
	public static Map<String,String>  orderTypes = new LinkedHashMap<String, String>(0);
	public static List<String>  orderTypesL = new  ArrayList<String>();
	public static Map<String,String>  agentOrderTypes = new LinkedHashMap<String, String>(0);
	public static List<String> agentOrderTypesL = new ArrayList<String>();
	public static Map<String,Float>  discountTypes = new LinkedHashMap<String, Float>(0);
	public static Map<String,String>  payStatusTypes = new LinkedHashMap<String, String>(0);
	public static Map<String,String>  userTypes = new LinkedHashMap<String, String>(0);
	public static Map<String,String>  svcLevels = new LinkedHashMap<String, String>(0);
	public static Map<String,String>  agentOrderStatus = new LinkedHashMap<String, String>(0);
	public static List<String> agentOrderStatusL = new ArrayList<String>();
	public static Map<String,String>  genderType = new LinkedHashMap<String, String>(0);
	public static Map<String,String>  goodType = new LinkedHashMap<String, String>(0);
	public static Map<String,String> payTypes = new LinkedHashMap<String,String>(0);
	public static Map<String,String> userTitleType = new LinkedHashMap<String,String>(0);
	public static Map<String,String> shipmentStatus = new LinkedHashMap<String,String>(0);
	
	 
	
	/**web版  默认版**/
	public static Map<String,FileManageCondition> fileManageConditions = new HashMap<String,FileManageCondition>();
	public static Map<Integer,String> titleStatus= new HashMap<Integer,String>();
	
	public static final String TABLE_HW_MOBILE_INFO = "hw_mobile";
	
	public static final String APP_SYSTEM_IPHONE = "iphone";
	public static final String APP_SYSTEM_IPAD = "ipad";
	public static final String APP_SYSTEM_ANDROID = "android";
	public static final String APP_SYSTEM_ANDROID_PAD = "android_pad";
	
	/**上传文件平台**/
	public static final String FILE_SYSTEM_DEFAULT = "web";
	public static final String FILE_SYSTEM_IPAD2 = "ipad2";
	public static final String FILE_SYSTEM_IPAD3 = "ipad3";
	
	public static Map<String,FileManageCondition> fileManageConditions_ipad2 = new HashMap<String,FileManageCondition>();
	public static Map<String,FileManageCondition> fileManageConditions_ipad3 = new HashMap<String,FileManageCondition>();
	
	/**状态：有效*/
	public static final String STATE_VALID = "1";
	
	/**状态：有效*/
	public static final String STATE_ISVALID_YES = "1";
	/**状态：无效*/
	public static final String STATE_INVALID = "0";
	/**状态：无效*/
	public static final String STATE_ISVALID_NO = "0";
	/**状态：待审核*/
	public static final String STATE_WAITING_4_AUDIT = "2";
	
	public static Map<String,String[]> articleType = new HashMap<String,String[]>();
	
	public static final String CATEGORY_NEWS = "1";
	public static final String CATEGORY_SOLUTION = "3";
	
	
	
	/**权限类型**/
	public static final String RIGHT_TYPE_CATEGORY = "0";//权限总目录
	public static final String RIGHT_TYPE_URL = "1";//权限
	public static final String RIGHT_TYPE_CATEGORY_APP = "2";//应用权限目录  归属于权限总目录 
	public static final String RIGHT_TYPE_URL_APP = "3";//应用权限
	
	/**文件扩展名*/
	public static final Set<String> fileExtSet = new HashSet<String>();
	
	/**
	* Dealer 订货订单 状态 Inventory Order Status
	* 0 待支付
	Waiting for payment
	* 1:待确认
	Waiting for confirmation
	* 2:已确认 Confirmed
	* 3:已出库 Shipped
	* 4:交易成功 Closed
	* 5:已作废 Canceled
	* 6:退货中 Request for return
	* 7:已退货 Returned
	*
	* Dealer 销售订单 状态 Sales Order Status
	* 待处理 Processing
	* 支付成功 Paid
	* 开通中 Provisioning
	* 完成 Closed
	* 取消 Canceled
	*
	*/
	public static final String ORDER_STATUS_WAIT_PAY = "0";
	public static final String ORDER_STATUS_WAIT_AUDIT = "1";
	public static final String ORDER_STATUS_PROCESSING = "2";
	public static final String ORDER_STATUS_SEND = "3";
	public static final String ORDER_STATUS_SUCCESS = "4";
	public static final String ORDER_STATUS_CANCEL = "5";
//	public static final String ORDER_STATUS_RETURNING = "6";
//	public static final String ORDER_STATUS_RETURNED = "7";
	
	/**
	 * 向TIBCO发起订单
	 */
	public static final String ORDER_PLACE_TIBCO_YES = "1";
	/**
	 * 未向TIBCO发起订单
	 */
	public static final String ORDER_PLACE_TIBCO_NO = "0";
	
	public static final String ITEM_STATUS_UNUSE = "0";//未使用
	public static final String ITEM_STATUS_USED = "1";//已使用
	public static final String ITEM_STATUS_CANCEL = "2";//作废
	public static final String ITEM_STATUS_SENDING = "3";//发货状态
	public static final String ITEM_STATUS_TEMP = "4";//生成订单中的临时状态
	public static final String ITEM_STATUS_DELETE = "9";//取消删除状态
	
	public static final String ITEM_FROM_SCAN = "0";//扫描
	public static final String ITEM_FROM_MANUAL = "1";//手动
	public static final String ITEM_FROM_BATCH = "2";//批量输入
	
	public static final String GENDER_MALE = "0";
	public static final String GENDER_FEMALE = "1";
	public static final String GENDER_UNKNOW = "2";
	
	public static final String GOOD_TYPE_MOBILE = "01";//手机
	public static final String GOOD_TYPE_SIM = "02";//卡 
	
	public static final String USER_TITLE_TYPE_MR = "0";
	public static final String USER_TITLE_TYPE_MRS = "1";
	public static final String USER_TITLE_TYPE_MISS = "2";
	public static final String USER_TITLE_TYPE_MS = "3";
	public static final String USER_TITLE_TYPE_DOCTOR = "4";
	public static final String USER_TITLE_TYPE_OTHER = "5";
	/**
	 * 支付
	 */
	public static final String ACCOUNT_LOG_TYPE_PAY = "0";
	/**
	 * 充值
	 */
	public static final String ACCOUNT_LOG_TYPE_CHARGE = "1";	
	/**
	 * 0：待处理 1：成功 2：失败
	 */
	public static final String ACCOUNT_LOG_STATUS_WAITING = "0";	
	/**
	 * 0：待处理 1：成功 2：失败
	 */
	public static final String ACCOUNT_LOG_STATUS_SUCCESS = "1";	
	/**
	 * 0：待处理 1：成功 2：失败
	 */
	public static final String ACCOUNT_LOG_STATUS_FAIL = "2";	
	
	
	
	/**
	 * 待处理
	 */
	public static final String AGENT_ORDER_STATUS_WAITTING="0";//待处理
	/**
	 * 支付成功
	 */
	public static final String AGENT_ORDER_STATUS_HAS_PAID="1";//支付成功
	/**
	 * 开通中
	 */
	public static final String AGENT_ORDER_STATUS_OPENING="2";//开通中
	/**
	 * 完成
	 */
	public static final String AGENT_ORDER_STATUS_COMPLETE="3";//完成
	/**
	 * 取消
	 */
	public static final String AGENT_ORDER_STATUS_CANCEL="9";//取消
	
	/**
	 * 新开户
	 */
	public static final String AGENT_ORDER_TYPE_NEW="1";
	/**
	 * 充值
	 */
	public static final String AGENT_ORDER_TYPE_TOPUP="2";
	/**
	 * topup
	 */
	public static final String AGENT_ORDER_TYPE_ADDON="4";
	/**
	 * 补卡
	 */
	public static final String AGENT_ORDER_TYPE_SIMCARD="3";
	/**
	 * 买手机或卡
	 */
	public static final String ORDER_TYPE_MOBILE_SIM ="4";//买手机或卡
	/**
	 * 叠加包
	 */
	public static final String AGENT_ORDER_TYPE_RECHARGE= "5";
	
	
	/**
	 *	未支付
	 */
	public static final String PAY_STATUS_NOT_PAID = "0";
	/**
	 * 已经支付
	 */
	public static final String PAY_STATUS_PAID = "1";//已经支付
	
	/**
	 * 信用卡
	 */
	public static final String PAY_MODE_CREDIT = "1";//信用卡
	/**
	 * 预存池账户
	 */
	public static final String PAY_MODE_ACCOUNT = "2";//预存池账户
	
	public static final String SVC_LEVEL_GOLD = "1";//金牌
	public static final String SVC_LEVEL_SILVER = "2";//银牌
	public static final String SVC_LEVEL_COPPER = "3";//号码铜牌
	
	public static final String PRODUCT_PAY_TYPE_PREPAID = "1";//预付
	public static final String PRODUCT_PAY_TYPE_POSTPAID = "2";//后付
	public static final String PRODUCT_PAY_TYPE_HYBRID = "3";//混合
//	public static final String PRODUCT_PAY_TYPE_MONTHLY_OFFERS = "1";//带终端合约
//	public static final String PRODUCT_PAY_TYPE_MONTHLY_SIM = "2";//带终端合约
	
	public static final String COMMISSION_TYPE_MARKUP = "0";
	public static final String COMMISSION_TYPE_COMMISSION = "1";
	
	
//	0:New  1:top-up 2:sim 4：奖励佣金
	
	/**
	 * 新开户佣金
	 */
	public static final String COMMISSION_CHARGE_TYPE_NEW = "0";
	/**
	 * 充值佣金
	 */
	public static final String COMMISSION_CHARGE_TYPE_TOP_UP = "1";
	/**
	 * sim卡佣金
	 */
	public static final String COMMISSION_CHARGE_TYPE_SIM = "2";
	/**
	 * 奖励佣金
	 */
	public static final String COMMISSION_CHARGE_TYPE_REWARD = "4";
	/**
	 * 叠加包佣金
	 */
	public static final String COMMISSION_CHARGE_TYPE_BOLT_ON = "5";
	public static final String COMMISSION_CHARGE_TYPE_COMMON = "99";
	
	
	public static final String SHIPMENT_STATUS_SENT = "1"; //已发货
	public static final String SHIPMENT_STATUS_SIGNED = "2"; //已签收
	public static final String SHIPMENT_STATUS_REJECTED = "3"; //已拒收
	public static final String SHIPMENT_STATUS_CANCEL = "5"; //已拒收
	public static final String SHIPMENT_STATUS_RETURNING = "6"; //退回中
	public static final String SHIPMENT_STATUS_RETURNED = "7"; //已退回
	
	public static final String SHIPMENT_TYPE_EXPRESS = "1"; //快递
	public static final String SHIPMENT_TYPE_OTHER = "9"; //其它
	
	public static final Map<String,String> skuStatusMap = new LinkedHashMap<String,String>(0);
	
	/**
	 * 关于SKU实体状态 SKU_ENTITY
	 *  01:在平台库
	 */
	public static final String SKU_STATUS_TIBCO = "01";
	/**
	 * 关于SKU实体状态 SKU_ENTITY
	 *  02:在代理商库
	 */
	public static final String SKU_STATUS_CHANNEL = "02";
	/**
	 * 关于SKU实体状态 SKU_ENTITY
	 * 03:已销售给普通用户
	 */
	public static final String SKU_STATUS_USER = "03";
	/**
	 * 关于SKU实体状态 SKU_ENTITY
	 * 04:已损坏
	 */
	public static final String SKU_STATUS_DESTROY = "04";
	
	/**
	 * 预占，暂时不可用状态。撤单之后恢复正常
	 */
	public static final String SKU_STATUS_TMP = "05";
	
	/**
	 * tibco仓库ID
	 */
	public static final Long REP_CODE_TIBCO = 0L;
	
	static
	{
		/**业务类型**/
		payTypes.put(PRODUCT_PAY_TYPE_PREPAID + LANGUAGE_ENGLISH, "Prepaid");
		payTypes.put(PRODUCT_PAY_TYPE_POSTPAID + LANGUAGE_ENGLISH, "Postpaid");
		payTypes.put(PRODUCT_PAY_TYPE_HYBRID + LANGUAGE_ENGLISH, "Hybrid");
//		payTypes.put(PRODUCT_PAY_TYPE_MONTHLY_SIM + LANGUAGE_ENGLISH, "Post Paid SIM");
		
		payTypes.put(PRODUCT_PAY_TYPE_PREPAID + LANGUAGE_CHINA, "预付费");
		payTypes.put(PRODUCT_PAY_TYPE_POSTPAID + LANGUAGE_CHINA, "后付费");
		payTypes.put(PRODUCT_PAY_TYPE_HYBRID + LANGUAGE_CHINA, "混合");
//		payTypes.put(PRODUCT_PAY_TYPE_MONTHLY_SIM + LANGUAGE_CHINA, "后付费SIM");
		
		/**状态**/
		stateTypes.put(STATE_VALID + LANGUAGE_ENGLISH, "Active");
		stateTypes.put(STATE_INVALID + LANGUAGE_ENGLISH, "Inactive");
		stateTypes.put(STATE_WAITING_4_AUDIT + LANGUAGE_ENGLISH, "Processing");
		
		stateTypes.put(STATE_VALID + LANGUAGE_CHINA, "有效");
		stateTypes.put(STATE_INVALID + LANGUAGE_CHINA, "无效");
		stateTypes.put(STATE_WAITING_4_AUDIT + LANGUAGE_CHINA, "处理中");
		
		/**是否**/
		isValidMapL.add(STATE_ISVALID_YES);
		isValidMapL.add(STATE_ISVALID_NO);
		isValidMap.put(STATE_ISVALID_YES + LANGUAGE_ENGLISH, "Yes");
		isValidMap.put(STATE_ISVALID_NO + LANGUAGE_ENGLISH, "No");
		
		isValidMap.put(STATE_ISVALID_YES + LANGUAGE_CHINA, "是");
		isValidMap.put(STATE_ISVALID_NO + LANGUAGE_CHINA, "否");
		
		
		
		inputTypes.put(INPUT_TYPE_OBJECT, "对象");
		inputTypes.put(INPUT_TYPE_XML, "XML");
		inputTypes.put(INPUT_TYPE_CUSTOM, "自定义");
		
		/**订货订单 状态**/
//		* Dealer 订货订单 状态 Inventory Order Status
//		* 0 待支付 Waiting for payment
//		* 1:待确认 Waiting for confirmation
//		* 2:已确认 Confirmed
//		* 3:已出库 Shipped
//		* 4:交易成功 Closed
//		* 5:已作废 Canceled
//		* 6:退货中 Request for return
		orderTypesL.add(ORDER_STATUS_WAIT_PAY);
		orderTypesL.add(ORDER_STATUS_WAIT_AUDIT);
		orderTypesL.add(ORDER_STATUS_PROCESSING);
		orderTypesL.add(ORDER_STATUS_SEND);
		orderTypesL.add(ORDER_STATUS_SUCCESS);
		orderTypesL.add(ORDER_STATUS_CANCEL);
		orderTypes.put(ORDER_STATUS_WAIT_PAY + LANGUAGE_ENGLISH, "Waiting for payment");
		orderTypes.put(ORDER_STATUS_WAIT_AUDIT + LANGUAGE_ENGLISH, "Waiting for confirmation");
		orderTypes.put(ORDER_STATUS_PROCESSING + LANGUAGE_ENGLISH, "Processing");
		orderTypes.put(ORDER_STATUS_SEND + LANGUAGE_ENGLISH, "Shipped");
		orderTypes.put(ORDER_STATUS_SUCCESS + LANGUAGE_ENGLISH, "Completed");//Succeeded
		orderTypes.put(ORDER_STATUS_CANCEL + LANGUAGE_ENGLISH, "Canceled");
//		orderTypes.put(ORDER_STATUS_RETURNING, "Request for return");
//		orderTypes.put(ORDER_STATUS_RETURNED, "Returned");
//		orderTypes.put(ORDER_STATUS_RETURNED, "Canceled");
		
		orderTypes.put(ORDER_STATUS_WAIT_PAY + LANGUAGE_CHINA, "等待支付");
		orderTypes.put(ORDER_STATUS_WAIT_AUDIT + LANGUAGE_CHINA, "等待确认");
		orderTypes.put(ORDER_STATUS_PROCESSING + LANGUAGE_CHINA, "处理中");
		orderTypes.put(ORDER_STATUS_SEND + LANGUAGE_CHINA, "已发货");
		orderTypes.put(ORDER_STATUS_SUCCESS + LANGUAGE_CHINA, "交易完成");//Succeeded
		orderTypes.put(ORDER_STATUS_CANCEL + LANGUAGE_CHINA, "交易取消");
		
		/**产品类型**/
		goodType.put(GOOD_TYPE_MOBILE + LANGUAGE_ENGLISH,"Mobile");
		goodType.put(GOOD_TYPE_SIM + LANGUAGE_ENGLISH,"Sim");
		
		goodType.put(GOOD_TYPE_MOBILE + LANGUAGE_CHINA,"终端");
		goodType.put(GOOD_TYPE_SIM + LANGUAGE_CHINA,"卡");
		
		/**用户类型**/
		userTypes.put(USER_TYPE_ADMIN + LANGUAGE_ENGLISH, "Administrator");
		userTypes.put(USER_TYPE_AGENT + LANGUAGE_ENGLISH, "Dealer");
		userTypes.put(USER_TYPE_CUSTOMER + LANGUAGE_ENGLISH, "Customer");
		
		userTypes.put(USER_TYPE_ADMIN + LANGUAGE_CHINA, "管理员");
		userTypes.put(USER_TYPE_AGENT + LANGUAGE_CHINA, "代理商");
		userTypes.put(USER_TYPE_CUSTOMER + LANGUAGE_CHINA, "客户");
		
		/**性别 **/
		genderType.put(GENDER_MALE + LANGUAGE_ENGLISH, "Male");
		genderType.put(GENDER_FEMALE + LANGUAGE_ENGLISH, "Female");
		genderType.put(GENDER_UNKNOW + LANGUAGE_ENGLISH, "Unknown");
		
		genderType.put(GENDER_MALE + LANGUAGE_CHINA, "男");
		genderType.put(GENDER_FEMALE + LANGUAGE_CHINA, "女");
		genderType.put(GENDER_UNKNOW + LANGUAGE_CHINA, "未知");
		
		
		/**称呼**/
		userTitleType.put(USER_TITLE_TYPE_MR + LANGUAGE_ENGLISH, "Mr.");
		userTitleType.put(USER_TITLE_TYPE_MRS + LANGUAGE_ENGLISH, "Mrs.");
		userTitleType.put(USER_TITLE_TYPE_MISS + LANGUAGE_ENGLISH, "Miss");
		userTitleType.put(USER_TITLE_TYPE_MS + LANGUAGE_ENGLISH, "Ms.");
		userTitleType.put(USER_TITLE_TYPE_DOCTOR + LANGUAGE_ENGLISH, "Doctor");
		userTitleType.put(USER_TITLE_TYPE_OTHER + LANGUAGE_ENGLISH, "Other");
		
		userTitleType.put(USER_TITLE_TYPE_MR + LANGUAGE_CHINA, "先生");
		userTitleType.put(USER_TITLE_TYPE_MRS + LANGUAGE_CHINA, "夫人");
		userTitleType.put(USER_TITLE_TYPE_MISS + LANGUAGE_CHINA, "小姐");
		userTitleType.put(USER_TITLE_TYPE_MS + LANGUAGE_CHINA, "女士");
		userTitleType.put(USER_TITLE_TYPE_DOCTOR + LANGUAGE_CHINA, "研究生");
		userTitleType.put(USER_TITLE_TYPE_OTHER + LANGUAGE_CHINA, "其它");
		
		/**发货状态**/
		shipmentStatus.put(SHIPMENT_STATUS_SENT + LANGUAGE_ENGLISH,"Sent");
		shipmentStatus.put(SHIPMENT_STATUS_SIGNED + LANGUAGE_ENGLISH,"Signed");
		shipmentStatus.put(SHIPMENT_STATUS_REJECTED + LANGUAGE_ENGLISH,"Rejected");
		shipmentStatus.put(SHIPMENT_STATUS_CANCEL + LANGUAGE_ENGLISH, "Canceled");
		shipmentStatus.put(SHIPMENT_STATUS_RETURNING + LANGUAGE_ENGLISH,"Returning");
		shipmentStatus.put(SHIPMENT_STATUS_RETURNED + LANGUAGE_ENGLISH,"Returned");
		
		shipmentStatus.put(SHIPMENT_STATUS_SENT + LANGUAGE_CHINA,"发货中");
		shipmentStatus.put(SHIPMENT_STATUS_SIGNED + LANGUAGE_CHINA,"已签收");
		shipmentStatus.put(SHIPMENT_STATUS_REJECTED + LANGUAGE_CHINA,"拒绝");
		shipmentStatus.put(SHIPMENT_STATUS_CANCEL + LANGUAGE_CHINA, "取消");
		shipmentStatus.put(SHIPMENT_STATUS_RETURNING + LANGUAGE_CHINA,"退货中");
		shipmentStatus.put(SHIPMENT_STATUS_RETURNED + LANGUAGE_CHINA,"已退货");
		
		/** 代理商订单类型 **/
		agentOrderTypesL.add(AGENT_ORDER_TYPE_NEW);
		agentOrderTypesL.add(AGENT_ORDER_TYPE_RECHARGE);
		agentOrderTypesL.add(AGENT_ORDER_TYPE_SIMCARD);
		agentOrderTypesL.add(AGENT_ORDER_TYPE_ADDON);
		
		agentOrderTypes.put(SYSConstant.AGENT_ORDER_TYPE_NEW + LANGUAGE_ENGLISH, "New User");
		agentOrderTypes.put(SYSConstant.AGENT_ORDER_TYPE_RECHARGE + LANGUAGE_ENGLISH, "Top Up");//充值
		agentOrderTypes.put(SYSConstant.AGENT_ORDER_TYPE_SIMCARD + LANGUAGE_ENGLISH, "SIM Care");
		agentOrderTypes.put(SYSConstant.AGENT_ORDER_TYPE_ADDON + LANGUAGE_ENGLISH, "Add-On");//叠加包
		
		agentOrderTypes.put(SYSConstant.AGENT_ORDER_TYPE_NEW + LANGUAGE_CHINA, "开户");
		agentOrderTypes.put(SYSConstant.AGENT_ORDER_TYPE_RECHARGE + LANGUAGE_CHINA, "充值");//充值
		agentOrderTypes.put(SYSConstant.AGENT_ORDER_TYPE_SIMCARD + LANGUAGE_CHINA, "补换卡");
		agentOrderTypes.put(SYSConstant.AGENT_ORDER_TYPE_ADDON + LANGUAGE_CHINA, "叠加包");//叠加包
		
		/** 代理商订单状态 **/
//		* Dealer 销售订单 状态 Sales Order Status
//		* 0 待处理 Processing
//		* 1 支付成功 Paid
//		* 2 开通中 Provisioning
//		* 3 完成 Closed
//		* 9 取消 Canceled
		//用于select 的key值
		agentOrderStatusL.add(AGENT_ORDER_STATUS_WAITTING);
		agentOrderStatusL.add(AGENT_ORDER_STATUS_HAS_PAID);
		agentOrderStatusL.add(AGENT_ORDER_STATUS_OPENING);
		agentOrderStatusL.add(AGENT_ORDER_STATUS_COMPLETE);
		agentOrderStatusL.add(AGENT_ORDER_STATUS_CANCEL);
		
		agentOrderStatus.put(SYSConstant.AGENT_ORDER_STATUS_WAITTING + LANGUAGE_ENGLISH, "Processing");
		agentOrderStatus.put(SYSConstant.AGENT_ORDER_STATUS_HAS_PAID + LANGUAGE_ENGLISH, "Paid");
		agentOrderStatus.put(SYSConstant.AGENT_ORDER_STATUS_OPENING + LANGUAGE_ENGLISH, "Provisioning");
		agentOrderStatus.put(SYSConstant.AGENT_ORDER_STATUS_COMPLETE + LANGUAGE_ENGLISH, "Completed");
		agentOrderStatus.put(SYSConstant.AGENT_ORDER_STATUS_CANCEL + LANGUAGE_ENGLISH, "Canceled");
		
		agentOrderStatus.put(SYSConstant.AGENT_ORDER_STATUS_WAITTING + LANGUAGE_CHINA, "处理中");
		agentOrderStatus.put(SYSConstant.AGENT_ORDER_STATUS_HAS_PAID + LANGUAGE_CHINA, "已支付");
		agentOrderStatus.put(SYSConstant.AGENT_ORDER_STATUS_OPENING + LANGUAGE_CHINA, "开通中");
		agentOrderStatus.put(SYSConstant.AGENT_ORDER_STATUS_COMPLETE + LANGUAGE_CHINA, "完成");
		agentOrderStatus.put(SYSConstant.AGENT_ORDER_STATUS_CANCEL + LANGUAGE_CHINA, "取消");
		
		/** 订单折扣定义 **/
		discountTypes.put(SYSConstant.AGENT_ORDER_TYPE_SIMCARD, Float.valueOf("5"));
		discountTypes.put(SYSConstant.AGENT_ORDER_TYPE_RECHARGE, Float.valueOf("2"));
		discountTypes.put(SYSConstant.AGENT_ORDER_TYPE_NEW, Float.valueOf("10"));
		discountTypes.put(SYSConstant.AGENT_ORDER_TYPE_ADDON, Float.valueOf("10"));
		
		
		/**支付状态**/
		payStatusTypes.put(SYSConstant.PAY_STATUS_NOT_PAID + LANGUAGE_ENGLISH, "Unpaid");
		payStatusTypes.put(SYSConstant.PAY_STATUS_PAID + LANGUAGE_ENGLISH, "Paid");
		
		payStatusTypes.put(SYSConstant.PAY_STATUS_NOT_PAID + LANGUAGE_CHINA, "未支付");
		payStatusTypes.put(SYSConstant.PAY_STATUS_PAID + LANGUAGE_CHINA, "已支付");
		
		/**号码等级**/
		svcLevels.put(SYSConstant.SVC_LEVEL_GOLD + LANGUAGE_ENGLISH, "Gold");
		svcLevels.put(SYSConstant.SVC_LEVEL_SILVER + LANGUAGE_ENGLISH, "Silver");
		svcLevels.put(SYSConstant.SVC_LEVEL_COPPER + LANGUAGE_ENGLISH, "Copper");
		
		svcLevels.put(SYSConstant.SVC_LEVEL_GOLD + LANGUAGE_CHINA, "金牌");
		svcLevels.put(SYSConstant.SVC_LEVEL_SILVER + LANGUAGE_CHINA, "银牌");
		svcLevels.put(SYSConstant.SVC_LEVEL_COPPER + LANGUAGE_CHINA, "铜牌");
		
		outputTypes.put(OUTPUT_TYPE_CUSTOM, "自定义");
		outputTypes.put(OUTPUT_TYPE_OBJECT, "结果直接返回");
		outputTypes.put(OUTPUT_TYPE_DEFAULT, "默认");
		
		initTypes.put(SERVICE_INIT_TYPE_SPRING, "通过spring初始化");
		initTypes.put(SERVICE_INIT_TYPE_NEW, "构造函数初始化");
		
		fetchSources.put(FETCH_SOURCE_SESSION, "session");
		fetchSources.put(FETCH_SOURCE_OBJECT, "入参对象");
		fetchSources.put(FETCH_SOURCE_STATIC, "staticValue字段");
		fetchSources.put(FETCH_SOURCE_CONSTANT, "系统参数");
		fetchSources.put(FETCH_SOURCE_SYSDATE, "sysdate");
		
		elementTypes.put(ELEMENT_TYPE_XML_ELEMENT, "节点");
		elementTypes.put(ELEMENT_TYPE_XML_ATTR, "属性");
		
		constraintTypes.put(ELEMENT_CONSTRAINT_1, "1");
		constraintTypes.put(ELEMENT_CONSTRAINT_0_N, "*");
		constraintTypes.put(ELEMENT_CONSTRAINT_0_1, "?");
		constraintTypes.put(ELEMENT_CONSTRAINT_1_N, "+");
		
		convertTypes.put(CONVERT_TYPE_OBJECT, "");
		
		setgetTypes.put(SETGET_TYPE_NEST, "普通对象嵌套属性");
		setgetTypes.put(SETGET_TYPE_SIMPLE, "普通对象简单属性");
		setgetTypes.put(SETGET_TYPE_INDEX, "普通对象数组属性");
		setgetTypes.put(SETGET_TYPE_MAP, "普通对象MAP属性");
		setgetTypes.put(SETGET_TYPE_OWN, "对象本身");
		setgetTypes.put(SETGET_TYPE_OWN_ARRAY, "数组数组");
		setgetTypes.put(SETGET_TYPE_OWN_LIST, "集合对象");
		setgetTypes.put(SETGET_TYPE_OWN_MAP, "MAP对象");
		
		//手机价格： 1-499    500-999   1000-1999   2000-2999   3000-4999   5000
		mobilePriceTypes.put("", "所有");
		mobilePriceTypes.put("1-499", "1-499");
		mobilePriceTypes.put("500-999", "500-999");
		mobilePriceTypes.put("1000-1999", "1000-1999");
		mobilePriceTypes.put("2000-2999", "2000-2999");
		mobilePriceTypes.put("3000-4999", "3000-4999");
		mobilePriceTypes.put("5000以上", "5000以上");
		
		//手机外观设计： 直板  翻盖   滑盖    旋盖   其它
		mobileExteriorTypes.put("1", "直板");
		mobileExteriorTypes.put("2", "翻盖");
		mobileExteriorTypes.put("3", "滑盖");
		mobileExteriorTypes.put("4", "旋盖");
		mobileExteriorTypes.put("5", "其它");
		
		//手机操作系统 IOS    Android    Symbian   WindowsPhone   Palm
		
		osTypes.put("iOS", "iOS");
		osTypes.put("Android", "Android");
		osTypes.put("Symbian", "Symbian");
		osTypes.put("百度易平台", "百度易平台");
		
		//网络：WCDMA  GSM
		networkTypes.put("1", "WCDMA");
		networkTypes.put("2", "GSM");
		networkTypes.put("3", "WCDMA/GSM");
		
		//屏幕：3.7寸   4.0寸  4.3寸  其它
		screenTypes.put("3.7", "3.7寸");
		screenTypes.put("4.0", "4.0寸");
		screenTypes.put("4.3", "4.3寸");
		screenTypes.put("-1", "其它");
		
		ifTypes.put("0", "No");
		ifTypes.put("1", "Yes");
		
		fileTypes.put(FILE_TYPE_LIST,"列表图");
		fileTypes.put(FILE_TYPE_SHOW,"展示图");
		fileTypes.put(FILE_TYPE_BIG,"大图");
		fileTypes.put(FILE_TYPE_ORIGINAL,"原图");
		fileTypes.put(FILE_TYPE_ATTACHMENT, "文件附件");
		
		useDomainTypes.put("mapp_app_version","应用附件");
		useDomainTypes.put("mapp_mobile_info","手机附件");
		useDomainTypes.put("mapp_article_title","文章附件");
		
		//文章状态 0-已审核 1-需要审核 2-已忽略
		titleStatus.put(0,"已审核");
		titleStatus.put(1,"需要审核");
		titleStatus.put(2,"已忽略");
		
		/**证件类型**/
		certificateTypes.put("0" + LANGUAGE_ENGLISH, "ID");//
		certificateTypes.put("1" + LANGUAGE_ENGLISH, "Passport");
		certificateTypes.put("3" + LANGUAGE_ENGLISH, "Driver License");
		certificateTypes.put("2" + LANGUAGE_ENGLISH, "Other");
		
		certificateTypes.put("0" + LANGUAGE_CHINA, "身份证");
		certificateTypes.put("1" + LANGUAGE_CHINA, "护照");
		certificateTypes.put("3" + LANGUAGE_CHINA, "驾照");
		certificateTypes.put("2" + LANGUAGE_CHINA, "其它");
		
		
		/**变量名称**/
		variantMapL.add(VARIANT_COMMISSION_RULE_ORDER_AMOUNT);
		variantMapL.add(VARIANT_COMMISSION_RULE_REBATE);
		variantMapL.add(VARIANT_COMMISSION_RULE_ORDERTYPE);
//		variantMap.put(VARIANT_COMMISSION_RULE_ORDERITEM_COUNT, "订单数量");
		variantMap.put(VARIANT_COMMISSION_RULE_ORDER_AMOUNT + LANGUAGE_ENGLISH, "Amount(€)");
		variantMap.put(VARIANT_COMMISSION_RULE_REBATE + LANGUAGE_ENGLISH, "Rebate");
//		variantMap.put(VARIANT_COMMISSION_RULE_UNITCOST, "单成本费");
//		variantMap.put(VARIANT_COMMISSION_RULE_COST, "总成本费");
//		variantMap.put(VARIANT_COMMISSION_RULE_UNITREWARD, "单奖励费");
		variantMap.put(VARIANT_COMMISSION_RULE_ORDERTYPE + LANGUAGE_ENGLISH, "Order Type");
		
		variantMap.put(VARIANT_COMMISSION_RULE_ORDER_AMOUNT + LANGUAGE_CHINA, "支付总额");
		variantMap.put(VARIANT_COMMISSION_RULE_REBATE + LANGUAGE_CHINA, "佣金率");
		variantMap.put(VARIANT_COMMISSION_RULE_ORDERTYPE + LANGUAGE_CHINA, "订单类型");
		
		
		/**佣金偿还方式**/
		commissionBackTypeL.add(COMMISSION_BACK_TYPE_MAKE_UP);
		commissionBackTypeL.add(COMMISSION_BACK_TYPE_MONTHLY);
		commissionBackType.put(COMMISSION_BACK_TYPE_MAKE_UP, "Immediate");
//		commissionBackType.put(COMMISSION_BACK_TYPE_DAILY, "日结");
		commissionBackType.put(COMMISSION_BACK_TYPE_MONTHLY, "Monthly");
		
		commissionBackType.put(COMMISSION_BACK_TYPE_MAKE_UP + LANGUAGE_CHINA, "立结");
		commissionBackType.put(COMMISSION_BACK_TYPE_MONTHLY + LANGUAGE_CHINA, "月结");
		
		commissionPayType.put(COMMISSION_PAY_TYPE_IMMEDIATELY, "立结");
		commissionPayType.put(COMMISSION_PAY_TYPE_DAILY, "日结");
		commissionPayType.put(COMMISSION_PAY_TYPE_ENDMONTH, "月底结");
		commissionPayType.put(COMMISSION_PAY_TYPE_NEXTMONTH, "下月初结");
		commissionPayType.put(COMMISSION_PAY_TYPE_MONTHLY, "分月结");
		commissionPayType.put(COMMISSION_PAY_TYPE_ENDYEAR, "年底结");
		
		dict.put("inputTypes", inputTypes);
		dict.put("outputTypes", outputTypes);
		dict.put("initTypes", initTypes);
		dict.put("fetchSources", fetchSources);
		dict.put("elementTypes", elementTypes);
		dict.put("constraintTypes", constraintTypes);
		dict.put("convertTypes", convertTypes);
		dict.put("setgetTypes", setgetTypes);
		dict.put("mobilePriceTypes", mobilePriceTypes);
		dict.put("mobileExteriorTypes", mobileExteriorTypes);
		dict.put("osTypes", osTypes);
		dict.put("networkTypes", networkTypes);
		dict.put("screenTypes", screenTypes);
		dict.put("ifTypes", ifTypes);
		dict.put("fileTypes", fileTypes);
		dict.put("useDomainTypes", useDomainTypes);
		dict.put("titleStatus", titleStatus);
		dict.put("stateTypes", stateTypes);
		dict.put("certificateTypes", certificateTypes);
		dict.put("orderTypes", orderTypes);
		dict.put("userTypes", userTypes);
		dict.put("agentOrderTypes", agentOrderTypes);
		dict.put("agentOrderStatus", agentOrderStatus);
		dict.put("genderType", genderType);
		dict.put("userTitleType", userTitleType);
		dict.put("shipmentStatus", shipmentStatus);
		dict.put("commissionBackType", commissionBackType);
		dict.put("isValidMap", isValidMap);
		dict.put("variantMap", variantMap);
		dict.put("commissionPayType", commissionPayType);
		
		
		fileManageConditions.put(FILE_TYPE_LIST,new FileManageCondition(null, null, null, "false",true,70));
		fileManageConditions.put(FILE_TYPE_SHOW,new FileManageCondition(null, null, null, "false",true,800));
		fileManageConditions.put(FILE_TYPE_BIG,new FileManageCondition(null, null, null, "false",true,1600));
		fileManageConditions.put(FILE_TYPE_ORIGINAL,new FileManageCondition(null, "1000", null, "false",false,0));
		fileManageConditions.put(FILE_TYPE_ATTACHMENT,new FileManageCondition(null, "2000", null, "false",false,0));
		
		fileManageConditions_ipad2.put(FILE_TYPE_LIST,new FileManageCondition(null, null, null, "false",true,70));
		fileManageConditions_ipad2.put(FILE_TYPE_SHOW,new FileManageCondition(null, null, null, "false",true,800));
		fileManageConditions_ipad2.put(FILE_TYPE_BIG,new FileManageCondition(null, null, null, "false",true,1600));
		
		fileManageConditions_ipad3.put(FILE_TYPE_LIST,new FileManageCondition(null, null, null, "false",true,70));
		fileManageConditions_ipad3.put(FILE_TYPE_SHOW,new FileManageCondition(null, null, null, "false",true,800));
		fileManageConditions_ipad3.put(FILE_TYPE_BIG,new FileManageCondition(null, null, null, "false",true,1600));
		
		articleType.put(CATEGORY_SOLUTION, new String[] { "解决方案", "特色功能", "技术架构", "成功案例", "系统配置" });//行业解决方案
		articleType.put("1", new String[]{""});//新闻
		articleType.put("2", new String[]{"解决方案","特色功能","技术架构"});//产品
		
		fileExtSet.add("xls");
		fileExtSet.add("xlsx");
		fileExtSet.add("ppt");
		fileExtSet.add("pptx");
		fileExtSet.add("doc");
		fileExtSet.add("docx");
		fileExtSet.add("rar");
		fileExtSet.add("zip");
		fileExtSet.add("txt");
		fileExtSet.add("pdf");
		fileExtSet.add("vsd");
		fileExtSet.add("rtf");
		fileExtSet.add("pot");
		fileExtSet.add("pps");
		fileExtSet.add("wps");
		fileExtSet.add("dps");
		
//		bankTypes.put("CBC", "中国建设银行");//中国建设银行——CBC
//		bankTypes.put("BC", "中国银行");//中国银行——BC
//		bankTypes.put("ABC", "中国农业银行");//中国农业银行——ABC
//		bankTypes.put("ICBC", "中国工商银行");//中国工商银行——ICBC
//		bankTypes.put("CMSB", "民生银行");//民生银行——CMSB
//		bankTypes.put("CMBC", "招商银行");//招商银行——CMBC
//		bankTypes.put("CIB", "兴业银行");//兴业银行——CIB
//		bankTypes.put("HSBC", "汇丰银行");//汇丰银行——HSBC
//		bankTypes.put("CGB", "广发银行");//广发银行---CGB
//		bankTypes.put("SPD", "浦发银行");//浦发银行--SPD
//		bankTypes.put("HZBANK", "杭州银行");//杭州银行---HZBANK
		
		skuStatusMap.put(SKU_STATUS_TIBCO, "在平台库");
		skuStatusMap.put(SKU_STATUS_CHANNEL, "在库");
		skuStatusMap.put(SKU_STATUS_USER, "已销售");
		skuStatusMap.put(SKU_STATUS_DESTROY, "已损坏");
		skuStatusMap.put(SKU_STATUS_TMP, "预占");
	}

	public static Object getDictName(String type,Object key)
	{
		if(dict.get(type) == null)
			return null;
		
		return dict.get(type).get(key);
	}
	
	public static Map<String,AgentOrder> orders = new ConcurrentHashMap<String,AgentOrder>(0);
	
	/**
	 * 文件存储关联表  01:表示 orderid取自order_recode
	 */
	public static final String ORDER_DOMAIN_ORDER = "01";
}
