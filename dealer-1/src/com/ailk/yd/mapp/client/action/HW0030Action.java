package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.VerifySms;
import com.ai.mapp.sys.service.VerifySmsService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0030Request;
import com.ailk.yd.mapp.client.model.HW0030Response;
import com.ailk.yd.mapp.tibco.model.YD0018.YD0018Request;

/**
 * 生成短信验证码接口
 * @author qianshihua
 * 
 */
@Service("hw0030")
@Action(bizcode = "hw0030", userCheck = true)
@Scope("prototype")
public class HW0030Action extends
		AbstractYDBaseActionHandler<HW0030Request, HW0030Response> {

	@Autowired
	private VerifySmsService verifySmsService;

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		//发送短信
		String code = geneCode(6);
		YD0018Request yd18r = new YD0018Request();
		final YD0018Request.Sms sms = new YD0018Request.Sms();
		sms.setMdn(request.getMdn());
		sms.setMsg(code);
		yd18r.setSmsList(new ArrayList() {
			{
				add(sms);
			}
		});
		//沉淀到验证表
		IUserinfo ui = this.getUserinfo();
		VerifySms vs = new VerifySms();
		vs.setCode(code);
		vs.setInvalidtime(DateUtils.addMinutes(new Date(), 30));
		vs.setValidtime(new Date());
		vs.setSvcnum(request.getMdn());
		vs.setDealerid(ui.getUserId());
		this.verifySmsService.save(vs);
		response.setVerifyCode(code);
		response.setVerifyId(vs.getId()+"");
	}

	/**
	 * 生成若干位的验证码
	 * 
	 * @param digit
	 *            位数
	 * @return
	 */
	private static String geneCode(int digit) {
		String rm = "";
		Random random = new Random();
		for (int i = 0; i < digit; i++) {
			rm += random.nextInt(10);
		}
		return rm;
	}

	public static void main(String[] args) {
		System.err.println(geneCode(6));
		System.err.println(geneCode(16));
	}
}
