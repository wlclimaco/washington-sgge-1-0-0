package com.sensus.mlc.wui.process.unittest.action;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.process.action.ProcessSettingAction;

/**
 * The Class ScheduleSettingsActionTest.
 */
public class ProcessSettingActionTest extends StrutsSpringTestCase
{
	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

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
	private ProcessSettingAction getAction(String methodAction) throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		ProcessSettingAction action =
				(ProcessSettingAction)getActionProxy("/process/" + methodAction + ".action")
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
		ProcessSettingAction settingsAction = getAction("settings");

		SessionAuthenticationTestUtil.setMockSession();

		String response = settingsAction.settings();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);

		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getProcessPageAction());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getAbortInclude());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getSummaryInclude());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getAbortProcessUrl());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getRetryProcessUrl());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getSearchUrlLRP());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getLongRunningProcessDialog());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getLongRunningProcessDialogRemove());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getLongRunningProcessDialogAbort());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getLongRunningProcessRemove());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getLongRunningProcessAbort());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getLongRunningProcessRecent());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getLongRunningProcessRemoveAll());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getLongRunningProcessRetryUnreachable());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getCheckLongRunningProcess());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getCheckRni());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getDeleteFile());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getGenerateSummaryFileCSV());
		assertNotNull(PROPERTY_NOT_NULL, settingsAction.getSearchProcessUrl());
	}
}