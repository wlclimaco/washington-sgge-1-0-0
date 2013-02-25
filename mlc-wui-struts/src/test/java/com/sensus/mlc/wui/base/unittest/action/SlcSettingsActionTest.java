package com.sensus.mlc.wui.base.unittest.action;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.base.action.SlcSettingsAction;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;

/**
 * Test the GroupSettingsAction.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class SlcSettingsActionTest extends StrutsSpringTestCase
{
	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

	/** The Constant PROPERTY_IS_NULL. */
	private static final String PROPERTY_IS_NULL = "The property is null";

	/** The Constant PROPERTY_NOT_NULL. */
	private static final String PROPERTY_NOT_NULL = "The property is not null";

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
	private SlcSettingsAction getAction(String methodAction) throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SlcSettingsAction action =
				(SlcSettingsAction)getActionProxy("/" + methodAction + ".action")
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
		SlcSettingsAction settingsAction = getAction("settings");

		SessionAuthenticationTestUtil.setMockSession();

		String response = settingsAction.settings();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getLongRunningProcessTime());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getCheckRniTime());
	}
}
