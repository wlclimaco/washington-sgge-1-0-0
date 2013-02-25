package com.sensus.mlc.wui.analytics.unittest.action;

import org.apache.struts2.StrutsSpringTestCase;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.wui.analytics.action.AnalyticsSettingAction;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;

public class AnalyticsSettingActionTest extends StrutsSpringTestCase
{

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts2.StrutsSpringTestCase#getContextLocations()
	 */
	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	/**
	 * Test settings.
	 * 
	 * @throws Exception the exception
	 */
	public void testSettings() throws Exception
	{

		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		ActionProxy proxy = getActionProxy("/analytics/settings.action");
		AnalyticsSettingAction action = (AnalyticsSettingAction)proxy.getAction();

		action.setServletRequest(request);

		SessionAuthenticationTestUtil.setMockSession();

		action.setServletRequest(request);

		SessionAuthenticationTestUtil.setMockSession();

		String response = proxy.execute();
		assertEquals("Struts Outcome", Action.SUCCESS, response);
		assertNotNull("searchAnalytics", action.getSearchUrl());
	}
}