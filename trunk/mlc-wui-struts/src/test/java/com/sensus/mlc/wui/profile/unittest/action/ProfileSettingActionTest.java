package com.sensus.mlc.wui.profile.unittest.action;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.profile.action.ProfileSettingAction;

public class ProfileSettingActionTest extends StrutsSpringTestCase
{
	/** The Constant STRUTS_OUTCOME. */
	private static final String STRUTS_OUTCOME = "Struts outcome";

	/** The Constant NAME. */
	private static final String NAME = "/profile/";

	/** The Constant ACTION. */
	private static final String ACTION = ".action";

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
	 */
	private ProfileSettingAction getAction(String methodAction)
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		ProfileSettingAction action =
				(ProfileSettingAction)getActionProxy(NAME + methodAction + ACTION).getAction();
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
		ProfileSettingAction settingsAction = getAction("settings");

		SessionAuthenticationTestUtil.setMockSession();

		String response = settingsAction.settings();

		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, response);

		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getSaveProfileSettings());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getUnitkWh());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getUnitMWh());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getUnitGWh());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getUnitTWh());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getUnitPWh());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getSearchUrlLRP());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getLongRunningProcessDialog());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getLongRunningProcessDialogRemove());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getLongRunningProcessDialogAbort());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getLongRunningProcessRemove());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getLongRunningProcessAbort());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getLongRunningProcessRecent());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getCheckLongRunningProcess());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getLongRunningProcessRetryUnreachable());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getLongRunningProcessRemoveAll());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getCheckRni());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getSaveProfileSettings());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getDeleteFile());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getGenerateSummaryFileCSV());
	}
}
