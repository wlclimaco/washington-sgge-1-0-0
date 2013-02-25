package com.sensus.mlc.wui.systemsettings.unittest.action;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.settings.unittest.action.SettingsBCFMockImpl;
import com.sensus.mlc.wui.systemsettings.action.SystemSettingsPageAction;

/**
 * The Class SystemSettingsPageActionTest.
 */
public class SystemSettingsPageActionTest extends StrutsSpringTestCase
{
	/** The Constant STRUTS_OUTCOME. */
	private static final String STRUTS_OUTCOME = "Struts outcome";

	/** The Constant SYSTEM_SETTINGS. */
	private static final String SYSTEM_SETTINGS = "/systemsettings/";

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
	private SystemSettingsPageAction getAction(String methodAction)
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SystemSettingsPageAction action =
				(SystemSettingsPageAction)getActionProxy(SYSTEM_SETTINGS + methodAction + ACTION).getAction();
		action.setServletRequest(request);

		return action;
	}

	/**
	 * Test fetch system settings.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testFetchSystemSettings() throws Exception
	{
		SystemSettingsPageAction action = getAction("ajax.list");
		SettingsBCFMockImpl settingsBCFMock = (SettingsBCFMockImpl)action.getSettingsBCF();

		settingsBCFMock.setMode(BaseMockImpl.MODE_SUCCESS);
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, action.fetchSystemSettings());

		/**
		 * Exception case
		 */
		action = getAction("ajax.list");
		settingsBCFMock = (SettingsBCFMockImpl)action.getSettingsBCF();

		settingsBCFMock.setMode(BaseMockImpl.MODE_EXCEPTION);
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, action.fetchSystemSettings());
	}

}
