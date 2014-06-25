/**
 * 
 */
package com.ailk.web;

import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

/**
 * @author yangqx
 *
 */
public class VelocityLayoutViewResolverExt extends VelocityLayoutViewResolver {

	@Override
	protected Class requiredViewClass() {
		// TODO Auto-generated method stub
		return VelocityLayoutViewExt.class;
	}

}
