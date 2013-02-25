package com.sensus.mlc.wui.analytics.unittest.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.analytics.action.AnalyticsPageAction;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;

public class AnalyticsPageActionTest extends StrutsSpringTestCase
{
	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	/** The Constant STRUTS_OUTCOME. */
	private static final String STRUTS_OUTCOME = "Struts outcome";

	/** The Constant RESULT_COUNT. */
	private static final String RESULT_COUNT = "Result Count";
	/** The Constant DEFAULT_MESSAGE. */
	private static final String DEFAULT_MESSAGE = "Default Message";

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
	 * Gets the action.
	 * 
	 * @return the action
	 */
	private AnalyticsPageAction getAction()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		AnalyticsPageAction action = (AnalyticsPageAction)getActionProxy("/analytics/ajax.list.action").getAction();
		action.setServletRequest(request);

		return action;
	}

	/**
	 * Test execute.
	 */
	public void testExecute()
	{
		AnalyticsPageAction action = getAction();
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, action.execute());
	}

}
