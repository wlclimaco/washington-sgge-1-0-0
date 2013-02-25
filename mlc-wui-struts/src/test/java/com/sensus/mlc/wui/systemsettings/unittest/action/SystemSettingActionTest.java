package com.sensus.mlc.wui.systemsettings.unittest.action;

import org.apache.struts2.StrutsSpringTestCase;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.systemsettings.action.SystemSettingAction;

public class SystemSettingActionTest extends StrutsSpringTestCase
{
	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

	/** The Constant PROPERTY_NOT_NULL. */
	private static final String PROPERTY_NOT_NULL = "The property is null";

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
	private SystemSettingAction getAction(String methodAction) throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SystemSettingAction action =
				(SystemSettingAction)getActionProxy("/systemsettings/" + methodAction + ".action")
						.getAction();

		action.setServletRequest(request);

		return action;
	}

	/**
	 * Test settings.
	 * 
	 * @throws Exception the exception
	 */
	public void testSettings() throws Exception
	{
		/**
		 * Name of the Action of the Struts
		 */
		SystemSettingAction systemSettingsAction = getAction("settings");

		SessionAuthenticationTestUtil.setMockSession();

		String response = systemSettingsAction.settings();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);

		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getTagPageAction());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getProfilePageAction());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getAbortInclude());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getSummaryInclude());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getDeleteTagInclude());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getAutoExistingGroupInclude());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getAutoNoGroupInclude());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getSuspendAutoGroupInclude());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getSearchTagUrl());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getSmartpointUrl());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getUpdateAutoGroup());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getExistGroupWithTagName());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getCreateGroup());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getDeleteTagUrl());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getAddTag());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getProcessPageAction());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getSearchProcessUrl());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getAbortProcessUrl());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getRetryProcessUrl());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getSearchUrlLRP());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getLongRunningProcessDialog());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getLongRunningProcessDialogRemove());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getLongRunningProcessDialogAbort());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getLongRunningProcessRemove());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getLongRunningProcessAbort());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getLongRunningProcessRecent());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getCheckLongRunningProcess());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getLongRunningProcessRetryUnreachable());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getLongRunningProcessRemoveAll());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getCheckRni());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getDeleteFile());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getSaveProfileSettings());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getLoadWattageList());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getGenerateSummaryFileCSV());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getUnitkWh());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getUnitMWh());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getUnitGWh());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getUnitTWh());
		assertNotNull(PROPERTY_NOT_NULL, systemSettingsAction.getUnitPWh());
	}

}
