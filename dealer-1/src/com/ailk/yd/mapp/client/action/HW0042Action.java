package com.ailk.yd.mapp.client.action;

import java.net.URLDecoder;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0042Request;
import com.ailk.yd.mapp.client.model.HW0042Response;
import com.ailk.yd.mapp.tibco.action.YD0023Action;
import com.ailk.yd.mapp.tibco.model.YD0023.YD0023Request;
import com.ailk.yd.mapp.tibco.model.YD0023.YD0023Response;

@Service("hw0042")
@Action(bizcode = "hw0042", userCheck = true)
@Scope("prototype")
public class HW0042Action extends
		AbstractYDBaseActionHandler<HW0042Request, HW0042Response> {

	@Autowired
	private YD0023Action yd0023;

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {

		String fc = request.getFileContents();
		if (StringUtils.isBlank(fc)) {
			throw new Exception("File content is null!");
		}
		String decode = URLDecoder.decode(fc, "UTF-8");
		YD0023Request yd23 = new YD0023Request(decode);
		YD0023Response g2t = yd0023.post2Tibco(yd23, null,false);
		response = new HW0042Response();
		String url = g2t.getUrl();
		if(StringUtils.isBlank(url)){
			throw new Exception("url from tibco is null");
		}
		response.setUrl(url);

	}

}
