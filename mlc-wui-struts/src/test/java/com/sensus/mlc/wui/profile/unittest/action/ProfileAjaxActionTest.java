package com.sensus.mlc.wui.profile.unittest.action;

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
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.profile.action.ProfileAjaxAction;

/**
 * The Class ProfileAjaxActionTest.
 * 
 * @author Alexandre Tiveron
 */
public class ProfileAjaxActionTest extends StrutsSpringTestCase
{
	/** The logger. */
	private final Logger logger = Logger.getLogger(this.getClass());

	/** The Constant STRUTS_OUTCOME. */
	private static final String STRUTS_OUTCOME = "Struts outcome";

	/** The Constant MESSAGE_MESSAGES_SET. */
	private static final String MESSAGE_MESSAGES_SET = "Messages Set";

	/** The Constant MESSAGE_RESPONSE_OBJECT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

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
	private ProfileAjaxAction getAction()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		ProfileAjaxAction action = (ProfileAjaxAction)getActionProxy("/profile/saveProfileSettings.action").getAction();
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
		ProfileAjaxAction action = getAction();
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, action.execute());
	}

	@Test
	public void testSaveProfileSettings() throws Exception
	{

		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		// Success called from profile settings
		logger.debug("Testing success called from profile settings");
		ActionProxy proxy = getActionProxy("/profile/saveProfileSettings.action");
		ProfileAjaxAction action = (ProfileAjaxAction)proxy.getAction();
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

		String response = proxy.execute();

		/** Asserts **/
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());
		/*
		 * // Success called from remember decision
		 * logger.debug("Testing success");
		 * ActionProxy proxy3 = getActionProxy("/profile/saveProfileSettings.action");
		 * ProfileAjaxAction action3 =
		 * (ProfileAjaxAction)proxy3.getAction();
		 * action3.setServletRequest(request);
		 * SessionAuthenticationTestUtil.setMockSession();
		 * action3.setDateformat("MM/DD/YYYY");
		 * action3.setCallType("processdialog");
		 * action3.setLanguage("en_Us");
		 * action3.setMonitor(3);
		 * action3.setTimezone("-3.0");
		 * assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action3.getJsonResult());
		 * response = proxy3.execute();
		 * assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action3.getJsonResult());
		 * assertEquals("SUCCESS", Constants.JSON_OK, action3.getJsonResult().getResult());
		 * assertNotNull(MESSAGE_MESSAGES_SET, action3.getJsonResult().getMessages());
		 * // Success called from Page Size Dialog
		 * logger.debug("Testing success");
		 * ActionProxy proxy2 = getActionProxy("/profile/saveProfileSettings.action");
		 * ProfileAjaxAction action2 =
		 * (ProfileAjaxAction)proxy2.getAction();
		 * action2.setServletRequest(request);
		 * SessionAuthenticationTestUtil.setMockSession();
		 * action2.setCallType("dialogpagesize");
		 * action2.setLanguage("en_Us");
		 * action2.setTimezone("-3.0");
		 * action2.setDateformat("MM/DD/YYYY");
		 * action2.setPageSize("15");
		 * action2.setMonitor(3);
		 * action2.setConvertUnit("3");
		 * action2.setPageSizeShowDialog(1);
		 * assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action2.getJsonResult());
		 * response = proxy2.execute();
		 * assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action2.getJsonResult());
		 * assertEquals("SUCCESS", Constants.JSON_OK, action2.getJsonResult().getResult());
		 * assertNotNull(MESSAGE_MESSAGES_SET, action2.getJsonResult().getMessages());
		 */

	}
}
