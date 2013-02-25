package com.sensus.mlc.wui.schedule.unittest.action;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.schedule.action.ScheduleSettingsAction;

/**
 * The Class ScheduleSettingsActionTest.
 */
public class ScheduleSettingsActionTest extends StrutsSpringTestCase
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
	private ScheduleSettingsAction getAction(String methodAction) throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		ScheduleSettingsAction action =
				(ScheduleSettingsAction)getActionProxy("/schedule/" + methodAction + ".action")
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
		ScheduleSettingsAction systemSettingsAction =
				getAction("settings");

		SessionAuthenticationTestUtil.setMockSession();

		String response = systemSettingsAction.settings();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);

		assertNotNull("The property ScheduleUrl is not null",
				systemSettingsAction.getScheduleUrl());

		assertNotNull("The property SearchUrl is not null",
				systemSettingsAction.getSearchUrl());

		assertNotNull("The property CheckLongRunningProcess is not null",
				systemSettingsAction.getCheckLongRunningProcess());

		assertNotNull("The property CheckRni is not null",
				systemSettingsAction.getCheckRni());

		assertNotNull("The property CreateUrl is not null",
				systemSettingsAction.getCreateUrl());

		assertNotNull("The property DeleteFile is not null",
				systemSettingsAction.getDeleteFile());

		assertNotNull("The property DeleteScheduleInclude is not null",
				systemSettingsAction.getDeleteScheduleInclude());

		assertNotNull("The property DeleteScheduleUrl is not null",
				systemSettingsAction.getDeleteScheduleUrl());

		assertNotNull("The property GenerateSummaryFileCSV is not null",
				systemSettingsAction.getGenerateSummaryFileCSV());

		assertNotNull("The property InitiateDeleteSchedule is not null",
				systemSettingsAction.getInitiateDeleteSchedule());

		assertNotNull("The property LongRunningProcessAbort is not null",
				systemSettingsAction.getLongRunningProcessAbort());

		assertNotNull("The property LongRunningProcessDialog is not null",
				systemSettingsAction.getLongRunningProcessDialog());

		assertNotNull("The property LongRunningProcessDialogAbort is not null",
				systemSettingsAction.getLongRunningProcessDialogAbort());

		assertNotNull("The property LongRunningProcessDialogRemove is not null",
				systemSettingsAction.getLongRunningProcessDialogRemove());

		assertNotNull("The property LongRunningProcessRecent is not null",
				systemSettingsAction.getLongRunningProcessRecent());

		assertNotNull("The property LongRunningProcessRemove is not null",
				systemSettingsAction.getLongRunningProcessRemove());

		assertNotNull("The property LongRunningProcessRemoveAll is not null",
				systemSettingsAction.getLongRunningProcessRemoveAll());

		assertNotNull("The property LongRunningProcessRetryUnreachable is not null",
				systemSettingsAction.getLongRunningProcessRetryUnreachable());

		assertNotNull("The property SaveProfileSettings is not null",
				systemSettingsAction.getSaveProfileSettings());

		assertNotNull("The property SearchUrlLRP is not null",
				systemSettingsAction.getSearchUrlLRP());

		assertNotNull("The property SmartpointUrl is not null",
				systemSettingsAction.getSmartpointUrl());

		assertNotNull("The property SunriseOffsetMax is not null",
				systemSettingsAction.getSunriseOffsetMax());

		assertNotNull("The property SunriseOffsetMin is not null",
				systemSettingsAction.getSunriseOffsetMin());

		assertNotNull("The property UpdateScheduleUrl is not null",
				systemSettingsAction.getUpdateScheduleUrl());

	}

}
