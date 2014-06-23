package com.ai.mapp.sys.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import antlr.StringUtils;

import com.ai.mapp.base.MoneyUtils;
import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.error.RollbackException;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.base.util.MD5;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.dao.UserDao;
import com.ai.mapp.sys.entity.AccountInfo;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.CommonBean;
import com.ai.mapp.sys.entity.HwCity;
import com.ai.mapp.sys.entity.HwState;
import com.ai.mapp.sys.entity.SmallLocalFile;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.mapp.controller.MappController;
import com.ailk.yd.mapp.client.action.HW0006Action;

@Service("mappUserService")
@Transactional
public class UserService {
	
	public final Log log = LogFactory.getLog(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AccountInfoService accountInfoService;
	
	@Autowired
	private HwCityService hwCityService;
	
	@Autowired
	private SmallLocalFileService slfs;
	
//	@Autowired
//	private HW0006Action hw0006Action;
	
	
	public Collection<User> listUsers(User user,int start,int limit){
		try{
			log.debug(user==null?"user is null":user.toString());
			
			Collection<User> c = userDao.list(user, start, limit);
			for (Iterator it = c.iterator(); it.hasNext();) {
				User u = (User) it.next();
//				String pic = u.getPic();
//				System.err.println(pic);
				String slfid = u.getSlfid();
				if(slfid!=null && checkPicExists(slfid)==false){
					try {
						long slfidInt = Long.parseLong(slfid);
						SmallLocalFile slf = slfs.getById(slfidInt);
						savePic(slf);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection<User> listAllUsers(User user){
		try{
			log.debug(user==null?"user is null":user.toString());
			
			Collection<User> c = userDao.listAll(user);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveUser(User user)throws Exception{
			log.debug(user==null?"user is null":user.toString());
			userDao.save(user);
	}
	
	public void deleteUser(User user){
		try{
			log.debug(user==null?"user is null":user.toString());
			userDao.delete(user);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int countUser(User user) throws RollbackException {
		try{
			return userDao.count(user);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RollbackException(e);
		}
	}
	
	public User loadUser(Long id)
	{
		return userDao.get(id);
	}
	
	public User loadValidUser(String userCode, String pwd) {
		
		User condition = new User();
		condition.setUserCode(userCode);
		condition.setPassword(pwd);
		condition.setStatus(SYSConstant.STATE_VALID);
		
		Collection<User> users  = listUsers(condition, 0, 1);
		
		if(users == null || users.isEmpty())
			return null;
		
		return users.iterator().next();
		
	}
	
	public User loadUserByUserCode(String userCode) {
		
		User condition = new User();
		condition.setUserCode(userCode);
		
		Collection<User> users  = listUsers(condition, 0, 1);
		
		if(users == null || users.isEmpty())
			return null;
		
		return users.iterator().next();
		
	}
	
	public User loadDealer(String userCode) throws Exception
	{
		User condition = new User();
		condition.setUserCode(userCode);
		condition.setUserType(SYSConstant.USER_TYPE_AGENT);
		
		Collection<User> users  = listUsers(condition, 0, 1);
		
		if(users == null || users.isEmpty())
			return null;
		
		return users.iterator().next();
		
	}
	
	
	
	private boolean checkUser(User user)
	{
		User condition = new User();
		
		condition.setUserCode(user.getUserCode());
		
		int count = countUser(user);
		
		if(user != null && user.getUserId() == null && count > 0 )
			return false;
		
		if(user != null && count >1 && user.getUserId() != null)
			return false;
		
		return true;
					
	}
	
	public int checkRegUser(User checkUser,int checkType) throws Exception {
		
		if(checkUser!=null){
			int count = countUser(checkUser);
			if(checkType==101 && count>0){//check user by email
				return 101;
			}
		}
		
		return 0;
	}
	
	public User saveUserWithCheck(User user) throws Exception
	{
		User tmp = user;
		if(checkUser(tmp) == false)
			throw new Exception(LanguageInfo.USER_HAD_EXIST);
		user.setUpdateTime(new Date());
		saveUser(user);
		tmp.setUserId(user.getUserId());
		return tmp;
	}
	
	/**
	 * <p>审批通过一个用户 </p> 
	 * @param user: 要求为持久对象
	 * @param:        @param creator
	 * @param:        @throws Exception    
	 * @return:       void    
	 * @author        Zhengwj
	 * @Date          2012-9-28 下午03:34:41
	 */
	public void auditPassUser(User user,User creator)throws Exception {
		saveUser(user);
		if(user.getAccounts() == null || user.getAccounts().size() == 0){
			AccountInfo account = new AccountInfo();
			String name = user.getFirstName() == null ?"":user.getFirstName();
			name += user.getLastName() == null ?"":" "+user.getLastName();
			account.setName(name);
			account.setOperator(user);
			account.setCreator(creator);
			account.setAmount(Long.parseLong("0"));
			account.setUpdateTime(new Date());
			account.setCreateTime(new Date());
			account.setStatus(SYSConstant.STATE_VALID);//也肯定是有效的
			accountInfoService.saveAccountInfo(account);
		}
		//审核通过之后调用tibco接口
//		hw0006Action.doHandle();
		
	}
	
	/**
	 * <p>描述: </p> 
	 * @param:        @param condition
	 * @param:        @param start
	 * @param:        @param limit
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       List<User>    
	 * @author        Zhengwj
	 * @Date          2012-10-5 下午10:16:54
	 */
	public List<CommonBean> getTopUserAgent(User condition,int start,int limit)throws Exception{
		List<User> list = null;
		if(condition == null){
			condition = new User();
		}
		if(condition.getCreator() == null){
			User user = new User();
			user.setUserType(SYSConstant.USER_TYPE_AGENT);
			user.setStatus(SYSConstant.STATE_VALID);
			condition.setCreator(user);
		}
		
		condition.setStartTime(null);
		Map<String, Object> usersTotal = userDao.getTopUserIds(condition, start, limit);
		List<CommonBean> comTotal = (List<CommonBean>)usersTotal.get("objs");
		List<Long> userIds = (List<Long>)usersTotal.get("userIds");
		
		condition.setUserIds(userIds);
		AgentOrder order = new AgentOrder();
		User agent = new User();
		agent.setUserIds(userIds);
		order.setCreator(agent);
//		Map<Long, Long> incomeTotal = agentOrderService.countFeeByUser(order, condition.getStartTime(), condition.getEndTime());//计算收入
		/****/
		String thisMonth = DateUtils.getDateString("yyyyMM");
		condition.setStartTime(DateUtils.getDate(thisMonth+"01 00:00:00", "yyyyMMdd HH:mm:ss"));
		condition.setEndTime(DateUtils.getDate(thisMonth + DateUtils.getDays(thisMonth) + " 23:59:59", "yyyyMMdd HH:mm:ss"));
		Map<String, Object> usersThisMonth = userDao.getTopUserIds(condition, start, limit);
		
		
		List<CommonBean> comThisMonth = (List<CommonBean>)usersThisMonth.get("objs");
//		Map<Long, Long> incomeThisMonth = agentOrderService.countFeeByUser(order, condition.getStartTime(), condition.getEndTime());
		
		
		
		String lastMonth = DateUtils.getChdate(-1);
		condition.setStartTime(DateUtils.getDate(lastMonth + "01 00:00:00", "yyyyMMdd HH:mm:ss"));
		condition.setEndTime(DateUtils.getDate(lastMonth + DateUtils.getDays(lastMonth) + " 23:59:59", "yyyyMMdd HH:mm:ss"));
		Map<String, Object> usersLastMonth = userDao.getTopUserIds(condition, start, limit);
		List<CommonBean> comLastMonth = (List<CommonBean>)usersLastMonth.get("objs");
//		Map<Long, Long> incomeLastMonth = agentOrderService.countFeeByUser(order, condition.getStartTime(), condition.getEndTime());
		
//		if(ids != null && ids.size() != 0){
//			list = userDao.findByIds(ids);
//		}
		
		
		
//		completeTop(comThisMonth, comTotal, comLastMonth, incomeThisMonth, incomeTotal, incomeLastMonth);
		completeTop(comTotal,comThisMonth,comLastMonth, null, null, null);
		
//		return comThisMonth;
		return comTotal;
	}
	
	/**
	 * <p>描述: 完善同比环比的分析</p> 
	 * @param:        @return    
	 * @return:       List<CommonBean>    
	 * @author        Zhengwj
	 * @Date          2012-10-6 下午05:05:24
	 */
	private List<CommonBean> completeTop(List<CommonBean> comTotal,List<CommonBean> comThisMonth,List<CommonBean> comLastMonth,
//	private List<CommonBean> completeTop(List<CommonBean> comThisMonth,List<CommonBean> comTotal,List<CommonBean> comLastMonth,
			Map<Long, Long> incomeThisMonth,Map<Long, Long> incomeTotal,Map<Long, Long> incomeLastMonth){
		long incomeThis = 0;
		long incomeLast = 0;
		if(comTotal != null && comTotal.size() != 0){
//		if(comThisMonth != null && comThisMonth.size() != 0){
//			for(CommonBean tmpThisMonth:comThisMonth){
			for(CommonBean tmpTotal: comTotal){
//				for(int i = 0; i < comTotal.size(); i++){
//					if(tmpTotal.getStr1().equals(comTotal.get(i).getStr1())){
//						tmpThisMonth.setStr5(comTotal.get(i).getStr4());//总发展用户数
//						break;
//					}else{
//						if( i == comTotal.size()){
//							tmpThisMonth.setStr5("0");
//						}
//					}
//					
//				}
				boolean lastFlag = true;
				if(comLastMonth == null || comLastMonth.size() == 0){
					lastFlag = false;
				}
				if(comThisMonth != null && comThisMonth.size() != 0){
					for(int i = 0; i <  comThisMonth.size(); i++){
						if(tmpTotal.getStr1().equals(comThisMonth.get(i).getStr1())){
							if(lastFlag){
								for(int j = 0; j <  comLastMonth.size(); j++){
//								for(CommonBean tmpLastMonth: comLastMonth){
									if(comThisMonth.get(i).getStr1().equals(comLastMonth.get(j).getStr1())){
										tmpTotal.setStr5( comThisMonth.get(i).getStr4());
										tmpTotal.setStr7( comLastMonth.get(j).getStr4());
										if(Long.parseLong(comThisMonth.get(i).getStr4()) > Long.parseLong(comLastMonth.get(j).getStr4())){
											tmpTotal.setStr6("↑");//本月用户数
										}else if(Long.parseLong(comThisMonth.get(i).getStr4()) == Long.parseLong(comLastMonth.get(j).getStr4())){
											tmpTotal.setStr6("-");
										}else{
											tmpTotal.setStr6("↓");
										}
										
										break;
									}else{
										if( j == comLastMonth.size()){//当没有相应的上个月时
											tmpTotal.setStr7("0");
											if("0".equals(comThisMonth.get(i).getStr4())){
												tmpTotal.setStr5("0");
												tmpTotal.setStr6("-");
											}else{
												tmpTotal.setStr5(comThisMonth.get(i).getStr4());
												tmpTotal.setStr6("↑");
											}
										}
									}
								}
							}else{
								tmpTotal.setStr7("0");
								if("0".equals(comThisMonth.get(i).getStr4())){
									tmpTotal.setStr5("0");
									tmpTotal.setStr6("-");
								}else{
									tmpTotal.setStr5(comThisMonth.get(i).getStr4());
									tmpTotal.setStr6("↑");
								}
							}
						}else{
							if( i == comThisMonth.size()){//当没有相应的当月时
								tmpTotal.setStr5("0");
								if(lastFlag){//有总的上月
									for(int j = 0; j <  comLastMonth.size(); j++){
										if(tmpTotal.getStr1().equals(comLastMonth.get(j).getStr1())){//但有相应的上月
											tmpTotal.setStr7( comLastMonth.get(j).getStr4());
											if("0".equals(comLastMonth.get(j).getStr4())){
												tmpTotal.setStr6("-");
											}else{
												tmpTotal.setStr6("↓");
											}
											
											break;
										}else{
											if( j == comLastMonth.size()){//同样没有相应的上月时
												tmpTotal.setStr7("0");
												tmpTotal.setStr6("-");
											}
										}
									}
								}else{//没有总的上月 既一个上月都没有  没有上月的集合
									tmpTotal.setStr7("0");
									tmpTotal.setStr6("-");
								}
							}
						}
						
					}
				}else{
					tmpTotal.setStr5("0");
					if(lastFlag){//有总的上月
						for(int j = 0; j <  comLastMonth.size(); j++){
							if(tmpTotal.getStr1().equals(comLastMonth.get(j).getStr1())){//但有相应的上月
								tmpTotal.setStr7( comLastMonth.get(j).getStr4());
								if("0".equals(comLastMonth.get(j).getStr4())){
									tmpTotal.setStr6("-");
								}else{
									tmpTotal.setStr6("↓");
								}
								
								break;
							}else{
								if( j == comLastMonth.size()){//同样没有相应的上月时
									tmpTotal.setStr7("0");
									tmpTotal.setStr6("-");
								}
							}
						}
					}else{//没有总的上月 既一个上月都没有  没有上月的集合
						tmpTotal.setStr7("0");
						tmpTotal.setStr6("-");
					}
				}
//				for(CommonBean tmpLastMonth: comLastMonth){
//					if(tmpThisMonth.getStr1().equals(tmpLastMonth.getStr1())){
//						if(Long.parseLong(tmpThisMonth.getStr4()) > Long.parseLong(tmpLastMonth.getStr4())){
//							tmpThisMonth.setStr6( tmpThisMonth.getStr4() + " <strong style='color:red;'>↑</strong>");//本月用户数
//						}else if(Long.parseLong(tmpThisMonth.getStr4()) == Long.parseLong(tmpLastMonth.getStr4())){
//							tmpThisMonth.setStr6( tmpThisMonth.getStr4() + " <strong style='color:yellow;'>-</strong>");
//						}else{
//							tmpThisMonth.setStr6( tmpThisMonth.getStr4() + " <strong style='color:green;'>↓</strong>");
//						}
//						
//						break;
//					}
//				}
//				if(incomeThisMonth != null){
//					incomeThis = incomeThisMonth.get(tmpThisMonth.getStr1()) == null ? 0:Long.parseLong(incomeThisMonth.get(tmpThisMonth.getStr1()).toString());
//				}
//				if(incomeLastMonth != null){
//					incomeLast = incomeLastMonth.get(tmpThisMonth.getStr1()) == null ? 0:Long.parseLong(incomeLastMonth.get(tmpThisMonth.getStr1()).toString());
//				}
//				if(incomeThis > incomeLast){
//					tmpThisMonth.setStr7(MoneyUtils.formatToMoney(incomeThis) + "<span class=\"rise\"> </span>");//本月收入
//				}else if(incomeThis == incomeLast){
//					tmpThisMonth.setStr7(MoneyUtils.formatToMoney(incomeThis) + "<span class=\"flat\"> </span>");//本月收入
//				}else{
//					tmpThisMonth.setStr7(MoneyUtils.formatToMoney(incomeThis) + "<span class=\"descend\"> </span>");//本月收入
//				}
//				if(incomeTotal != null){
//					tmpThisMonth.setStr8(MoneyUtils.formatToMoney(incomeTotal.get(tmpThisMonth.getStr1()) == null ? "0":incomeTotal.get(tmpThisMonth.getStr1()).toString()));//总收入
//				}else{
//					tmpThisMonth.setStr8("0.00");
//				}
				
			}
		}
//		return comThisMonth;
		return comTotal;
	}
	
	public Map<String,Long> getUsersByCity(User condition,int start,int limit)throws Exception{
		List<User> list = null;
		if(condition == null){
			condition = new User();
		}
		if(condition.getCreator() == null){
			User user = new User();
			user.setUserType(SYSConstant.USER_TYPE_AGENT);
			user.setStatus(SYSConstant.STATE_VALID);
			condition.setCreator(user);
		}else{
			if(StringUtil.isEmpty(condition.getCreator().getUserType())){
				condition.getCreator().setUserType(SYSConstant.USER_TYPE_AGENT);
			}
			if(StringUtil.isEmpty(condition.getCreator().getStatus())){
				condition.getCreator().setStatus(SYSConstant.STATE_VALID);
			}
		}
		
		return userDao.getUsersByCity(condition,start,limit);
		
	}
	
	
	public JSONObject getTopJson(List<CommonBean> comThisMonth,List<CommonBean> comTotal,List<CommonBean> comLastMonth,
			Map<Long, Long> incomeThisMonth,Map<Long, Long> incomeTotal,Map<Long, Long> incomeLastMonth)throws Exception{
		JSONObject json = new JSONObject();//出账图表
		
		long incomeThis = 0;
		long incomeT = 0;
		
		if(comThisMonth != null && comThisMonth.size() != 0){
			//获得X轴坐标-人物名
			List<String> xCategories = new ArrayList<String>();
			//y轴--记录
			JSONObject json_temp = new JSONObject();
			List<JSONObject> series = new ArrayList<JSONObject>();
			List<String> acct_data = new ArrayList<String>();
			
			//本月收入
			json_temp.put("name", "Sales Revenue(Last Month)");//当月销售额
			json_temp.put("type", "column");
			for(CommonBean tmpThisMonth:comThisMonth){
				if(xCategories.size() != comThisMonth.size()){
					xCategories.add(tmpThisMonth.getStr3() + " " + tmpThisMonth.getStr2() );//名字
				}
				
				if(incomeThisMonth != null){
					incomeThis = incomeThisMonth.get(tmpThisMonth.getStr1()) == null ? 0:Long.parseLong(incomeThisMonth.get(tmpThisMonth.getStr1()).toString());
				}
				acct_data.add(incomeThis + "");
			}
			json_temp.put("data", acct_data.toArray());
			series.add(json_temp);
			
			//总发展用户数
			json_temp = new JSONObject();
			acct_data = new ArrayList<String>();
			json_temp.put("name", "Num of New Customers");//本月发展用户数
			json_temp.put("type", "column");
			for(CommonBean tmpThisMonth:comThisMonth){
				acct_data.add(tmpThisMonth.getStr4());//本月发展用户数
			}
			json_temp.put("data", acct_data.toArray());
			series.add(json_temp);
				
			//总收入
			json_temp = new JSONObject();
			acct_data = new ArrayList<String>();
			json_temp.put("name", "Sales Revenue (YTD)");//总收入
			json_temp.put("type", "column");
			for(CommonBean tmpComTotal:comTotal){
				if(incomeTotal != null){
					incomeT = incomeTotal.get(tmpComTotal.getStr1()) == null ? 0:Long.parseLong(incomeTotal.get(tmpComTotal.getStr1()).toString());
				}
				acct_data.add(incomeT + "");//总收入
			}
			json_temp.put("data", acct_data.toArray());	
			series.add(json_temp);

			
			//本月发展用户数
			json_temp = new JSONObject();
			acct_data = new ArrayList<String>();
			json_temp.put("name", "Num of New Customers (YTD)");//本月发展用户数
			json_temp.put("type", "column");
			for(CommonBean tmpComTotal:comTotal){
				
				
				for(int i = 0; i < comThisMonth.size(); i++){
					if(tmpComTotal.getStr1().equals(comThisMonth.get(i).getStr1())){
						acct_data.add(comThisMonth.get(i).getStr4());//本月发展用户数
						break;
					}else{
						if( i == comTotal.size()){
							acct_data.add("0");
						}
					}
					
				}
			}
			json_temp.put("data", acct_data.toArray());	
			series.add(json_temp);
			
				
				
//				//出账趋势图
//				json_temp.put("name", "出账费用");
//				json_temp.put("type", "spline");
				json_temp.put("data", acct_data.toArray());
//				series.add(json_temp);
				
		}else{
			json.put("result", "false");
		}
		return json;
	}
	
	public Map<String,Object> getUsersByCreator(Long creatorId,Date startTime,Date endTime,int start,int limit)throws Exception{
		List<User> customerList = null;  
		User condition = new User();
		User creator = new User();
		List<Long> creatorIds = new ArrayList<Long>();
		creatorIds.add(creatorId);
		creator.setUserIds(creatorIds);
		condition.setCreator(creator);
		condition.setStartTime(startTime);
		condition.setEndTime(endTime);
		condition.setStatus(SYSConstant.STATE_VALID);
		int c = countUser(condition);
		if(start < 0){
			customerList = (List<User>) listAllUsers(condition);
		}else{
			customerList = (List<User>) listUsers(condition, start, limit);
		}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("list", customerList);
		result.put("count", c);
		
		return result;
	}
	
//	public List<CommonBean> statDealer(String stateCode,String cityCode,Date startTime,Date endTime) throws Exception
//	{
//		User condition = new User();
//		if(StringUtil.isEmpty(stateCode) == false && StringUtil.isEmpty(cityCode) == false)
//		{
//			HwCity city = new HwCity(cityCode);
//			city.setState(new HwState(stateCode));
//			condition.setCity(city);
//		}
//		else if(StringUtil.isEmpty(cityCode) == false)
//		{
//			HwCity city = new HwCity(cityCode);
//			condition.setCity(city);
//		}
//		else if(StringUtil.isEmpty(stateCode) == false)
//		{
//			HwCity city = new HwCity();
//			city.setState(new HwState(stateCode));
//			condition.setCity(city);
//		}
//		
//		condition.setStatus(SYSConstant.STATE_VALID);
//		condition.setUserType(SYSConstant.USER_TYPE_AGENT);
//		
//		int count = countUser(condition);
//		
//		List<CommonBean> ret1 = userDao.statAwaitingDealerByMonth(stateCode, cityCode, startTime, endTime);
//		
//		List<CommonBean> ret2 = userDao.statActiveDealerByMonth(stateCode, cityCode, startTime, endTime);
//		
//		List<CommonBean> ret3 = userDao.statInActiveDealerByMonth(stateCode, cityCode, startTime, endTime);
//		
//		log.info("一共在线："+count);
//		log.info("===============================等待处理的Dealer==================================");
//		for(CommonBean bean : ret1)
//		{
//			log.info(bean.getStr1()+":"+bean.getStr2());
//		}
//		log.info("===============================审批通过的Dealer==================================");
//		for(CommonBean bean : ret2)
//		{
//			log.info(bean.getStr1()+":"+bean.getStr2());
//		}
//		log.info("===============================离开的Dealer==================================");
//		for(CommonBean bean : ret3)
//		{
//			log.info(bean.getStr1()+":"+bean.getStr2());
//		}
//		log.info("========================================================================");
//		return ret1;
//	}
	
	/**
	 * <p>描述: </p> 
	 * @param:        @param stateCode
	 * @param:        @param cityCode
	 * @param:        @param months 需从小到大排列 yyyymm
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       Map<String,List<CommonBean>>    
	 * @author        Zhengwj
	 * @Date          2013-1-18 下午05:30:11
	 */
	public Map<String,List<Long>> statisticsDealerByMonths(String stateCode,String cityCode,List<String> months) throws Exception{
		Map<String,List<Long>> result = new HashMap<String,List<Long>>();
		if(months != null && months.size() != 0){
			Date startTime = DateUtils.getDate(months.get(0) + "01 00:00:00", "yyyyMMdd HH:mm:ss");
			Date endTime = DateUtils.getDate(months.get(months.size() - 1) + DateUtils.getDays(months.get(months.size() - 1)) + " 23:59:59", "yyyyMMdd HH:mm:ss");
			
			Map<String,String> awaiting_map = userDao.statAwaitingDealerByMonth(stateCode, cityCode, startTime, endTime);
			List<Long> awaiting_list = new ArrayList<Long>();
			
			Map<String,String> active_map = userDao.statActiveDealerByMonth(stateCode, cityCode, startTime, endTime);
			List<Long> active_list = new ArrayList<Long>();
			
			Map<String,String> inActive_map = userDao.statInActiveDealerByMonth(stateCode, cityCode, startTime, endTime);
			List<Long> inActive_list = new ArrayList<Long>();
			
			for(int i = 0; i < months.size(); i++){
				if(awaiting_map != null && awaiting_map.get(months.get(i)) != null ){
					awaiting_list.add(Long.parseLong(awaiting_map.get(months.get(i))));
				}else{
					awaiting_list.add(new Long(0));
				}
				if(active_map != null && active_map.get(months.get(i)) != null ){
					active_list.add(Long.parseLong(active_map.get(months.get(i))));
				}else{
					active_list.add(new Long(0));
				}
				if(inActive_map != null && inActive_map.get(months.get(i)) != null ){
					inActive_list.add(Long.parseLong(inActive_map.get(months.get(i))));
				}else{
					inActive_list.add(new Long(0));
				}
			}
			result.put("awaiting", awaiting_list);
			result.put("active", active_list);
			result.put("inActive", inActive_list);
			
		}
		
		return result;
	}
	
	
//	public void intUser(Date)
	
	public int initUser(Date createTime, Date approvedTime,Date denyTime,String status,List<HwCity> citys,int start,int num) throws Exception
	{

		if(num <=0)
			return start;
		
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, -60000);
		for(int i=start;i< start+num;i++)
		{
			today.add(Calendar.DATE, -1);
			User user = new User();
			user.setAddress("Quay Street, Galway, Co. Galway");
			user.setBirthDay(today.getTime());
			user.setCertificateNo((1234567+i)+"");
			user.setCertificateType("1");
			user.setContractPhone("");
			user.setEmail("mler"+i+"@163.com");
			user.setFirstName("Best");
			user.setLastName("Paul"+i);
			user.setGender("1");
			user.setMobileNo("0872626126");
			user.setPassword(MD5.toMD5(i+""));
			user.setPostCode("123456");
//			HwCity city = citys.get(i%citys.size());
			user.setCity(citys.get(i%citys.size()));
			user.setStreet("Quay Street, Galway, Co. Galway");
			user.setUserCode("mler"+i+"@163.com");
			user.setUserTitle("1");
			user.setCreateTime(createTime);
			user.setUpdateTime(createTime);
			user.setApproveTime(approvedTime);
			user.setDenyTime(denyTime);
			
			User updator = new User(1028L);
			user.setUpdater(updator);
			user.setCreator(updator);
			user.setUserType(SYSConstant.USER_TYPE_AGENT);
			
			if(StringUtil.isEmpty(status))
				user.setStatus(SYSConstant.STATE_WAITING_4_AUDIT);
			else
				user.setStatus(status);
			
			saveUser(user);
			
			if(SYSConstant.STATE_VALID.equals(status) && (user.getAccounts() == null || user.getAccounts().size() == 0)){
				AccountInfo account = new AccountInfo();
				String name = user.getFirstName() == null ?"":user.getFirstName();
				name += user.getLastName() == null ?"":" "+user.getLastName();
				account.setName(name);
				account.setOperator(user);
				account.setCreator(updator);
				account.setAmount(Long.parseLong("0"));
				account.setUpdateTime(approvedTime);
				account.setCreateTime(approvedTime);
				account.setStatus(SYSConstant.STATE_VALID);//也肯定是有效的
				accountInfoService.saveAccountInfo(account);
			}
			
			
		}
			
		
		return start+num;
	}
	
	public void initUserData() throws ParseException, Exception
	{
		
		Collection<HwCity> citys = hwCityService.listAllHwCity(null);
		List<HwCity> citylist = new ArrayList<HwCity>(citys);
		
		Map<String,String> map = new HashMap<String, String>(0);
//		map.put("2012-07-03", "1-0-0");
		map.put("2012-07-03", "10-20-100");
		map.put("2012-08-03", "5-23-130");
		map.put("2012-09-03", "3-37-143");
		map.put("2012-10-15", "8-12-154");
		map.put("2012-11-03", "4-30-167");
		map.put("2012-12-08", "5-10-123");
		map.put("2013-01-11", "9-20-147");
		map.put("2013-02-07", "10-10-187");
		
		/** 2012-07**/
		
		int num = 0;
		
		for(String date : map.keySet())
		{
			String[] nums = map.get(date).split("-");
			
			
			
			num = initUser(DateUtils.getDate(date+" 12:00:00", "yyyy-MM-dd HH:mm:ss"), 
					DateUtils.getDate(date+" 12:00:00", "yyyy-MM-dd HH:mm:ss"), 
					DateUtils.getDate(date+" 12:00:00", "yyyy-MM-dd HH:mm:ss"), 
					SYSConstant.STATE_INVALID, citylist,num,Integer.valueOf(nums[0]));
			
			num = initUser(DateUtils.getDate(date+" 12:00:00", "yyyy-MM-dd HH:mm:ss"), 
					null, 
					null, 
					SYSConstant.STATE_WAITING_4_AUDIT, citylist, num,Integer.valueOf(nums[1]));
			
			num = initUser(DateUtils.getDate(date+" 12:00:00", "yyyy-MM-dd HH:mm:ss"), 
					DateUtils.getDate(date+" 12:00:00", "yyyy-MM-dd HH:mm:ss"), 
					null, 
					SYSConstant.STATE_VALID, citylist, num,Integer.valueOf(nums[2]));
		
		}
		
	}
	

	public static void savePic(SmallLocalFile slf) throws IOException {
		if(checkPicExists(slf.getSlfid()+"")){
			return;
		}
		if(slf==null || slf.getContentBlob()==null){
			return;
		}
		
		URL res = MappController.class.getResource("/../../images");
		String path = res.getPath()+"user_pic";
		File p = new File(path);
		if(p.exists()==false){
			p.mkdir();
		}
		String filePath = path+File.separator+slf.getSlfid();
		FileOutputStream fos=null;
		try {
			File picFile = new File(filePath);
			System.err.println(picFile.getAbsolutePath());
			fos = new FileOutputStream(picFile);
			fos.write(slf.getContentBlob());
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fos!=null)
				fos.close();
		}
	}
	
	public static boolean checkPicExists(String slfid){
		URL res = MappController.class.getResource("/../../images");
		String path = res.getPath()+"user_pic"+File.separator+slfid;
		File f = new File(path);
		return f.exists();
	}
	
}
