package com.ai.mapp.sys.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.sys.dao.AccountInfoDao;
import com.ai.mapp.sys.dao.CommissionDao;
import com.ai.mapp.sys.entity.AccountInfo;
import com.ai.mapp.sys.entity.AccountLog;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.Commission;
import com.ai.mapp.sys.entity.CommonBean;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.SYSConstant;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-28 下午04:28:16
 * 类说明:
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
public class CommissionService {
public final Log log = LogFactory.getLog(CommissionService.class);
	
	@Autowired
	private CommissionDao commissionDao;
	
	@Autowired
	private AgentOrderService agentOrderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountInfoService accountInfoService;
	
	@Autowired
	private AccountLogService accountLogService;
	
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public Collection<Commission> listCommissions(Commission commission,int start,int limit)throws Exception{
		try{
			log.debug(commission==null?"commission is null":commission.toString());
			
			Collection<Commission> c = null;
			if(start < 0){
				c = commissionDao.listAll(commission);
			}else{
				c = commissionDao.list(commission, start, limit);
			}
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveCommission(Commission commission)throws Exception{
		log.debug(commission==null?"commission is null":commission.toString());
		commissionDao.save(commission);
	}
	
	public void deleteCommission(Commission commission)throws Exception{
		log.debug(commission==null?"commission is null":commission.toString());
		commissionDao.delete(commission);
	}
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public int countCommission(Commission commission) throws Exception{
		return commissionDao.count(commission);
	}
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public Commission loadCommission(Long id)throws Exception{
		return commissionDao.get(id);
	}
	
	public List<CommonBean> countIncome(Long userId,String commissionType,String payStatus,String orderType,Date startTime,Date endTime) throws Exception
	{
		if(userId == null || StringUtil.isEmpty(userId.toString()))
			throw new Exception(LanguageInfo.USER_ID_EMPTY);
		
		Commission condition = new Commission();
		condition.setPayStatus(payStatus);
		condition.setAgent(new User(userId));
		if(StringUtil.isEmpty(commissionType) == false){
			condition.setType(commissionType);
		}
		
		if(StringUtil.isEmpty(orderType) == false)
			condition.setChargeType(orderType);
		if(startTime != null)
			condition.setStartTime(startTime);
		if(endTime != null)
			condition.setEndTime(endTime);
		if(SYSConstant.COMMISSION_TYPE_COMMISSION.equals(commissionType)){
			return commissionDao.countIncome(condition,false);//true
		}else{
			return commissionDao.countIncome(condition,false);
		}
		
	}
	//	EEEE dd-MMM-yyyy 'at' hh:mm a
	
	/**
	 * 根据订单获取订单支付方式
	 * @param orderCode
	 * @return 1 立即支付，0 非立即支付
	 * @throws Exception
	 */
	public boolean pay_immediately(String orderCode) throws Exception
	{
		AgentOrder order = agentOrderService.loadAgentOrderByOrderCode(orderCode);
		
		/**
		 * 1.充值和补换卡为立即付款做扣
		 * 2.预付开户为立即付款做扣
		 * 3.其他为未付款做扣（后付）
		 */
		if(SYSConstant.AGENT_ORDER_TYPE_RECHARGE.equals(order.getOrderType()) || SYSConstant.AGENT_ORDER_TYPE_SIMCARD.equals(order.getOrderType()))
		{
			return true;
		}
		else if(SYSConstant.AGENT_ORDER_TYPE_NEW.equals(order.getOrderType()) 
				&& (SYSConstant.PRODUCT_PAY_TYPE_PREPAID.equals(order.getProduct().getPayType())))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public void addCommissionByAgentOrder(String orderCode) throws Exception
	{
		if(StringUtil.isEmpty(orderCode))
			throw new Exception(LanguageInfo.ORDERCODE_IS_EMPTY);
		
		AgentOrder order = agentOrderService.loadAgentOrderByOrderCode(orderCode);
		
		if(order == null)
			throw new Exception(orderCode+" " + LanguageInfo.ORDER_UNEXIST);
		
		Commission commission = new Commission();
		commission.setAgent(order.getCreator());
		commission.setAgentOrder(order);
		commission.setChargeType(getChargeTypeByOrderType(order.getOrderType()));
		commission.setCreateTime(new Date());
		commission.setPay(order.getDiscountFee());
		commission.setPayType("");
		commission.setSvn(order.getSvn());
		
		
		/**
		 * 1.充值和补换卡为立即付款做扣
		 * 2.预付开户为立即付款做扣
		 * 3.其他为未付款做扣（后付）
		 */
//		if(SYSConstant.AGENT_ORDER_TYPE_RECHARGE.equals(order.getOrderType()) || SYSConstant.AGENT_ORDER_TYPE_SIMCARD.equals(order.getOrderType()))
//		{
//			commission.setPayStatus(SYSConstant.PAY_STATUS_PAID);
//			commission.setPayTime(new Date());
//			commission.setPayType("");
//			commission.setType(SYSConstant.COMMISSION_TYPE_MARKUP);
//		}
//		else if((SYSConstant.AGENT_ORDER_TYPE_NEW.equals(order.getOrderType()) 
//				&& (SYSConstant.PRODUCT_PAY_TYPE_PREPAID.equals(order.getProduct().getPayType()) || SYSConstant.PRODUCT_PAY_TYPE_DATASIM.equals(order.getProduct().getPayType()))))
//		{
//			commission.setPayStatus(SYSConstant.PAY_STATUS_PAID);
//			commission.setPayTime(new Date());
//			commission.setPayType("");
//			commission.setType(SYSConstant.COMMISSION_TYPE_MARKUP);
//		}
//		else
//		{
//			commission.setPayStatus(SYSConstant.PAY_STATUS_NOT_PAID);
//			commission.setType(SYSConstant.COMMISSION_TYPE_MARKUP);//做扣；
//		}
		
		if(pay_immediately(orderCode))
		{
			commission.setPayStatus(SYSConstant.PAY_STATUS_PAID);
			commission.setPayTime(new Date());
			commission.setPayType("");
			commission.setType(SYSConstant.COMMISSION_TYPE_MARKUP);
			
			/**TODO 如果是是立即支付的佣金，往代理商预存池充值，生成充值记录**/
			if(order.getDiscountFee() != null && order.getDiscountFee() > 0)
			{
				User user = userService.loadUserByUserCode(order.getCreator().getUserCode());
				if(user == null)
					throw new Exception(LanguageInfo.USER_NOT_EXIST);
				
				Collection<AccountInfo> accounts = user.getAccounts();
				if(accounts == null || accounts.isEmpty())
					throw new Exception(LanguageInfo.HAVE_NO_ACCOUNT);
				
				/**
				 * 将佣金冲入账户
				 */
				AccountInfo account = accounts.iterator().next();
				long commissionFee = commission.getPay() == null ? 0 : commission.getPay().longValue();
				long amount = account.getAmount() == null ? 0 : account.getAmount().longValue();
				account.setAmount(amount+commissionFee);
				accountInfoService.saveAccountInfo(account);
				
				AccountLog log_recharge = new AccountLog();
				log_recharge.setAccount(account);
				log_recharge.setCreateTime(new Date());
				log_recharge.setPay(order.getDiscountFee());
				log_recharge.setLogType(SYSConstant.ACCOUNT_LOG_TYPE_CHARGE);
				log_recharge.setAgentOrder(order);
				accountLogService.saveAccountLog(log_recharge);
			}
		}
		else
		{
			commission.setPayStatus(SYSConstant.PAY_STATUS_NOT_PAID);
			commission.setType(SYSConstant.COMMISSION_TYPE_MARKUP);//做扣；
		}
		
		saveCommission(commission);
	}
	
	private String getChargeTypeByOrderType(String orderType) throws Exception
	{
		if(SYSConstant.AGENT_ORDER_TYPE_NEW.equals(orderType))
		{
			return SYSConstant.COMMISSION_CHARGE_TYPE_NEW;
		}
		else if(SYSConstant.AGENT_ORDER_TYPE_RECHARGE.equals(orderType))
		{
			return SYSConstant.COMMISSION_CHARGE_TYPE_TOP_UP;
		}
		else if(SYSConstant.AGENT_ORDER_TYPE_SIMCARD.equals(orderType))
		{
			return SYSConstant.COMMISSION_CHARGE_TYPE_SIM;
		}
		else if(SYSConstant.AGENT_ORDER_TYPE_BOLT_ON.equals(orderType))
		{
			return SYSConstant.COMMISSION_CHARGE_TYPE_BOLT_ON;
		}
		
		return "";
	}
	
	
	public String getOrderTypeByChargeType(String chargeType,String language) throws Exception
	{
		
		if(SYSConstant.COMMISSION_CHARGE_TYPE_NEW.equals(chargeType))
		{
			return SYSConstant.agentOrderTypes.get(SYSConstant.AGENT_ORDER_TYPE_NEW + language);
		}
		else if(SYSConstant.COMMISSION_CHARGE_TYPE_TOP_UP.equals(chargeType))
		{
			return SYSConstant.agentOrderTypes.get(SYSConstant.AGENT_ORDER_TYPE_RECHARGE + language);
		}
		else if(SYSConstant.COMMISSION_CHARGE_TYPE_SIM.equals(chargeType))
		{
			return SYSConstant.agentOrderTypes.get(SYSConstant.AGENT_ORDER_TYPE_SIMCARD + language);
		}
		else if(SYSConstant.COMMISSION_CHARGE_TYPE_BOLT_ON.equals(chargeType))
		{
			return SYSConstant.agentOrderTypes.get(SYSConstant.AGENT_ORDER_TYPE_BOLT_ON + language);
		}
		else if(SYSConstant.COMMISSION_CHARGE_TYPE_COMMON.equals(chargeType))
		{
			return LanguageInfo.COMMON;
		}
		else if(SYSConstant.COMMISSION_CHARGE_TYPE_REWARD.equals(chargeType))
		{
			return LanguageInfo.REWARD;
		}
		
		return "";
	}
	
	public List<Commission> listIncomeDetailByOrderType(Long userId,String commissionType,String payStatus,String orderType,Date startTime,Date endTime,int start,int pageSize)throws Exception{
//		if(userId == null || StringUtil.isEmpty(userId.toString()))
//			throw new Exception(LanguageInfo.USER_ID_EMPTY);
		
		Commission condition = new Commission();
		condition.setPayStatus(payStatus);
		if(userId != null){
			condition.setAgent(new User(userId));
		}
		if(StringUtil.isEmpty(commissionType) == false){
			condition.setType(commissionType);
		}
		
		if(StringUtil.isEmpty(orderType) == false)
			condition.setChargeType(orderType);
		if(startTime != null)
			condition.setStartTime(startTime);
		if(endTime != null)
			condition.setEndTime(endTime);
		
		return (List<Commission>)listCommissions(condition, start, pageSize);
	}
	
	/**
	 * <p>描述: 生成佣金文件</p> 
	 * @param userId
	 * @throws Exception  
	 * @author        Zhengwj
	 * @Date          2014-5-6 上午10:06:46
	 */
	public void createCommission2007Excel(String filePath,Date startTime,Date endTime)throws Exception{
		
		/**
		 * 取出相应的所有未支付佣金的佣金明细
		 */
		List<Commission> commissions = listIncomeDetailByOrderType(null, null, null, null, startTime, endTime, -1, -1);//SYSConstant.PAY_STATUS_NOT_PAID
		
		if(commissions == null || commissions.isEmpty()){
			return;
		}
		
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet();// 创建一个工作薄对象
		sheet.setColumnWidth(0, 5000);//设置第一列的宽度
		sheet.setColumnWidth(1, 5000);//设置第二列的宽度
		sheet.setColumnWidth(2, 6000);//设置第三列的宽度
		sheet.setColumnWidth(3, 7000);//设置第四列的宽度
		sheet.setColumnWidth(4, 6000);//设置第五列的宽度
		sheet.setColumnWidth(5, 8000);//设置第六列的宽度
		sheet.setColumnWidth(6, 7000);//设置第七列的宽度
		
		XSSFRow row = sheet.createRow(0);//创建第一行对象
		row.setHeightInPoints(23);//设置行高23像素
		XSSFCellStyle styleHeader = workBook.createCellStyle();//创建样式对象
		//设置字体
		XSSFFont font = workBook.createFont();//创建字体对象
		font.setFontHeightInPoints((short)15);//设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//将头行设为粗体
		font.setFontName("Calibri");//如黑体,不知道印度是啥样的
		styleHeader.setFont(font);
		//设置对齐方式
		styleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);//水平居中
		styleHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//设置边框
		styleHeader.setBorderTop(HSSFCellStyle.BORDER_THICK);
		styleHeader.setTopBorderColor(HSSFColor.RED.index);//设置为红色
		styleHeader.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);//底部边框双线
		styleHeader.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);//左边边框
		styleHeader.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);//右边边框
		styleHeader.setBottomBorderColor(HSSFColor.RED.index);
		
		/**
		//格式化日期
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy?h:mm"));
		XSSFCell cell = row.createCell(1);//创建单元格
		cell.setCellValue(new Date());// 写入当前日期
		cell.setCellStyle(style);// 应用样式对象
		//文件输出流
		FileOutputStream os = new FileOutputStream(filePath);
		workBook.write(os);//将文档对象写入文件输出流
		os.close();//关闭文件输出流
		System.out.println("创建成功 office 2007 excel"); 
		**/
		
		XSSFCell cell = row.createCell(0);//创建单元格
		cell.setCellValue("Commission ID");// 本地的佣金id
		cell.setCellStyle(styleHeader);// 应用样式对象
		
		cell = row.createCell(1);
		cell.setCellValue("Dealer ID");// Dealer的id
		cell.setCellStyle(styleHeader);// 应用样式对象
		
		cell = row.createCell(2);
		cell.setCellValue("Creation No");// 产生佣金的号码
		cell.setCellStyle(styleHeader);// 应用样式对象
		
		cell = row.createCell(3);
		cell.setCellValue("Commission Type");// 佣金类型 0立返 1 月结
		cell.setCellStyle(styleHeader);// 应用样式对象
		
		cell = row.createCell(4);
		cell.setCellValue("Commission Payment Status");// 佣金支付状态
		cell.setCellStyle(styleHeader);// 应用样式对象
		
		cell = row.createCell(5);
		cell.setCellValue("Commission Creation Time");// 佣金生成时间
		cell.setCellStyle(styleHeader);// 应用样式对象
		
		cell = row.createCell(6);
		cell.setCellValue("Top-up Amount");// 佣金金额
		cell.setCellStyle(styleHeader);// 应用样式对象
		
		Commission commission = null;
		XSSFCellStyle styleContent = workBook.createCellStyle();//创建样式对象
		for(int i = 0; i < commissions.size(); i++){
			commission = commissions.get(i);
			row = sheet.createRow(i + 1);
			
			cell = row.createCell(0);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(commission.getId());//本地的佣金id
			
			cell = row.createCell(1);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			Long userId = commission.getAgent() != null?commission.getAgent().getUserId():-1;
			cell.setCellValue(userId);// Dealer的id
			
			cell = row.createCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING );
			cell.setCellValue(commission.getSvn());//产生佣金的号码
			
			cell = row.createCell(3);
			cell.setCellType(Cell.CELL_TYPE_STRING );
			cell.setCellValue(commission.getType());//佣金类型 0立返 1 月结-------------不知道写commissionType还是payType  还有COMMISSION_BACK_TYPE_MAKE_UP与word文档里不一致
			
			cell = row.createCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING );
			cell.setCellValue(commission.getPayStatus());//佣金支付状态
			
			cell = row.createCell(5);
			styleContent.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellStyle(styleContent);
			cell.setCellValue(commission.getCreateTime());// 佣金生成时间
			
			cell = row.createCell(6);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(commission.getPay());// 佣金金额
		}
		
		FileOutputStream os = new FileOutputStream(filePath);
		workBook.write(os);//将文档对象写入文件输出流
		os.close();//关闭文件输出流
		log.debug("创建成功 office 2007 excel"); 
		
	} 
}
