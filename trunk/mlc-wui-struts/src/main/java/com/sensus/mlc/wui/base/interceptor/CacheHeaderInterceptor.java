package com.sensus.mlc.wui.base.interceptor;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Adds Cache headers to Struts Action Responses to guarantee accurate data.
 * 
 * @author Anke Doerfel-Parker
 */
@SuppressWarnings("serial")
public class CacheHeaderInterceptor extends AbstractInterceptor
{

	/**
	 * Intercepts Struts Action Invocation to add Cache headers to Struts Action Responses to guarantee accurate data.
	 * 
	 * @param invocation the action invocation object
	 * @return the string
	 * @throws Exception the exception
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception
	{
		HttpServletResponse response =
				(HttpServletResponse)invocation.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		return invocation.invoke();
	}

}
