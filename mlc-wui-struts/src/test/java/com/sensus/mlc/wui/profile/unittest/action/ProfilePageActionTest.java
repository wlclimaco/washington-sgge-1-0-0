package com.sensus.mlc.wui.profile.unittest.action;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.profile.action.ProfilePageAction;

public class ProfilePageActionTest extends StrutsSpringTestCase
{
	/** The Constant STRUTS_OUTCOME. */
	private static final String STRUTS_OUTCOME = "Struts outcome";

	/** The Constant RESULT_COUNT. */
	private static final String RESULT_COUNT = "Result Count";

	/** The Constant NAME. */
	private static final String NAME = "/profile/";

	/** The Constant ACTION. */
	private static final String ACTION = ".action";

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
	 * @param methodAction the method action
	 * @return the action
	 */
	private ProfilePageAction getAction(String methodAction)
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		ProfilePageAction action =
				(ProfilePageAction)getActionProxy(NAME + methodAction + ACTION).getAction();
		action.setServletRequest(request);

		return action;
	}

	/**
	 * Test open profile.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testOpenProfile() throws Exception
	{
		ProfilePageAction action = getAction("ajax.list");
		String result = action.openProfile();
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, result);
	}
}
