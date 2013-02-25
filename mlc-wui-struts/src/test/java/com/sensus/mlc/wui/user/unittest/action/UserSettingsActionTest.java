package com.sensus.mlc.wui.user.unittest.action;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.user.action.UserSettingsAction;

public class UserSettingsActionTest extends StrutsSpringTestCase
{
	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

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
	 * @throws Exception the exception
	 */
	private UserSettingsAction getAction(String methodAction) throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		UserSettingsAction action =
				(UserSettingsAction)getActionProxy("/user/" + methodAction + ".action")
						.getAction();

		action.setServletRequest(request);

		return action;
	}

	/**
	 * Test settings.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testSettings() throws Exception
	{
		/**
		 * Name of the Action of the Struts
		 */
		UserSettingsAction systemSettingsAction =
				getAction("settings");

		SessionAuthenticationTestUtil.setMockSession();

		String response = systemSettingsAction.settings();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);

	}

}
