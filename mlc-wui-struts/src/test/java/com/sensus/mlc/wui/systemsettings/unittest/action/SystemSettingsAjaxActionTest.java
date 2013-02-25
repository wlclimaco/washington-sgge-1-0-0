package com.sensus.mlc.wui.systemsettings.unittest.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.settings.unittest.action.SettingsBCFMockImpl;
import com.sensus.mlc.wui.systemsettings.action.SystemSettingsAjaxAction;

/**
 * The Class SystemSettingsAjaxActionTest.
 * 
 * @author Alexandre Tiveron
 */
public class SystemSettingsAjaxActionTest extends StrutsSpringTestCase
{
	/** The logger. */
	private final Logger logger = Logger.getLogger(this.getClass());

	/** The Constant STRUTS_OUTCOME. */
	private static final String STRUTS_OUTCOME = "Struts outcome";

	/** The Constant MESSAGE_MESSAGES_SET. */
	private static final String MESSAGE_MESSAGES_SET = "Messages Set";

	/** The Constant MESSAGE_RESPONSE_OBJECT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

	/** The Constant MESSAGE_RESPONSE_OBJECT_NOT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_NOT_PRESENT = "Response object not present";

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
	private SystemSettingsAjaxAction getAction()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SystemSettingsAjaxAction action =
				(SystemSettingsAjaxAction)getActionProxy("/systemsettings/saveProfileSettings.action").getAction();
		action.setServletRequest(request);

		return action;
	}

	/**
	 * Test execute.
	 * 
	 * @throws Exception
	 */
	public void testExecute() throws Exception
	{
		SystemSettingsAjaxAction action = getAction();
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, action.execute());
	}

	@Test
	public void testSaveSystemSettings() throws Exception
	{
		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		ActionProxy proxy = getActionProxy("/systemsettings/saveProfileSettings.action");
		SystemSettingsAjaxAction action = (SystemSettingsAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		SettingsRequest settingsRequest = new SettingsRequest();

		List<Setting> settingsList = new ArrayList<Setting>();

		Setting setting = new Setting();

		setting.setPropertyEnum(PropertyEnum.LANGUAGE);
		setting.setPropertyValue("en_US");

		settingsList.add(setting);

		setting = new Setting();

		setting.setPropertyEnum(PropertyEnum.DATE_FORMAT);
		setting.setPropertyValue("mm/dd/yyyy");

		settingsList.add(setting);

		setting = new Setting();

		setting.setPropertyEnum(PropertyEnum.PAGE_SIZE);
		setting.setPropertyValue("15");

		settingsList.add(setting);

		setting = new Setting();

		setting.setPropertyEnum(PropertyEnum.TIME_ZONE);
		setting.setPropertyValue("US/Arizona");

		setting = new Setting();

		setting.setPropertyEnum(PropertyEnum.CONVERT_ENERGY_UNIT);
		setting.setPropertyValue("false");

		settingsList.add(setting);

		setting = new Setting();

		setting.setPropertyEnum(PropertyEnum.MONITOR_REQUEST);
		setting.setPropertyValue("3");

		settingsList.add(setting);

		settingsRequest.setSettings(settingsList);

		action.setSettingsRequest(settingsRequest);

		SettingsBCFMockImpl bcf = (SettingsBCFMockImpl)action.getSettingsBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		String response = proxy.execute();

		/** Asserts **/
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

	}
}
