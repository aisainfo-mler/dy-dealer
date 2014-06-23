package com.ailk.yd.mapp.client.action;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.sys.entity.HwCity;
import com.ai.mapp.sys.entity.SmallLocalFile;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.SmallLocalFileService;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0006Request;
import com.ailk.yd.mapp.client.model.HW0006Response;
import com.ailk.yd.mapp.model.UserInfo;

@Service("hw0006")
@Action(bizcode="hw0006",userCheck=true)
@Scope("prototype")
public class HW0006Action extends AbstractYDBaseActionHandler<HW0006Request, HW0006Response> {

	@Autowired
	private UserService mappUserService;
	
	@Autowired
	private SmallLocalFileService slfs;

	
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		User user = new User();
		user.setAddress(this.request.getAddress());
		if (request.getBirthDay() != null)
			user.setBirthDay(StringUtil.isEmpty(request.getBirthDay()) ? null
					: DateUtils.getDate(request.getBirthDay(), "yyyy-MM-dd"));
		user.setCertificateNo(request.getIdCardNo());
		user.setCertificateType(request.getIdCardType());
		user.setContractPhone(request.getContractPhone());
		user.setEmail(request.getEmail());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setGender(request.getGender());
		user.setMobileNo(request.getMobileNo());
		user.setPassword(request.getPwd());
		user.setPostCode(request.getPostCode());
		if (StringUtil.isEmpty(request.getCity()) == false) {
			HwCity city = new HwCity();
			city.setCityCode(request.getCity());
			user.setCity(city);
		}
		if(StringUtils.isNotBlank(request.getPayPwd())){
			user.setPayPwd(request.getPayPwd());
		}

		// if(StringUtils.isEmpty(request.getState()) == false)
		// {
		// user.setStatus(request.getState());
		// }
		if (StringUtil.isEmpty(request.getStreet()) == false)
			user.setStreet(request.getStreet());
		if (StringUtil.isEmpty(request.getEmail()) == false)
			user.setUserCode(request.getEmail());
		else
			user.setUserCode(request.getMobileNo());
		user.setUserTitle(request.getUserTitle());
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());

		// User updator =
		// userService.loadUserByUserCode((String)param.getParameter(BSSConstantParam.USERCODE));//老的代码
		UserInfo u = (UserInfo) MappContext.getAttribute(MappContext.MAPPCONTEXT_USER);
		User updator = mappUserService.loadUserByUserCode(u.getUsercode());

		user.setUpdater(updator);
		user.setCreator(updator);
		user.setUserType(SYSConstant.USER_TYPE_AGENT);
		user.setStatus(SYSConstant.STATE_WAITING_4_AUDIT);

		if (StringUtil.isEmpty(user.getUserCode()) == false) {
			User checkUser = new User();
			checkUser.setEmail(user.getUserCode());
			// checkUser.setUserType(SYSConstant.USER_TYPE_AGENT);
			int checkFlag = mappUserService.checkRegUser(checkUser, 101);
			if (checkFlag > 0) {
				if (checkFlag == 101) {
					throw new Exception(LanguageInfo.EMAIL_HAD_EXIST);
				}
			}
		}

		
		Long slfId = this.savePic();
		if(slfId>0){
			user.setSlfid(slfId+"");
		}
		mappUserService.saveUserWithCheck(user);
		

	}
	
	private Long savePic() throws Exception{
//		File f = new File("/Users/qianshihua/Pictures/qq.jpg");
//		FileInputStream fis = new FileInputStream(f);
//		int available = fis.available();
//		final byte[] b = new byte[available];
//		fis.read(b);
//		this.request.setImage(new ArrayList(){{add(b);}});
		
		SmallLocalFile slf = new SmallLocalFile();
		if(this.request.getImage()!=null && this.request.getImage().size()>0){
			byte[] img = this.request.getImage().get(0);
			slf.setContentBlob(img);
			slf.setCreateTime(new Date());
			UserInfo u = (UserInfo) MappContext.getAttribute(MappContext.MAPPCONTEXT_USER);
			slf.setOptId(u.getUserId().longValue());
			this.slfs.save(slf);
			
			
			UserService.savePic(slf);
			
			
			return slf.getSlfid();
		}else{
			return -1L;
		}
		
	}


}
