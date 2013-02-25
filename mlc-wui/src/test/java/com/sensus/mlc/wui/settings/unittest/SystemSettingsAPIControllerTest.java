package com.sensus.mlc.wui.settings.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.mlc.wui.settings.SystemSettingsAPIController;
import com.sensus.mlc.wui.util.AbstractTestBase;
import com.sensus.mlc.wui.util.ModeEnum;
import com.sensus.mlc.wui.util.TestMessageEnum;

// TODO: Auto-generated Javadoc
/**
 * The Class AnalyticsAPIControllerTest.
 */
public class SystemSettingsAPIControllerTest extends AbstractTestBase

{

	/** The Constant FETCH_PROPERTIES. */
	private static final String FETCH_PROPERTIES = "/api/settings/fetchproperties";

	/** The Constant SAVE_PROFILE_SETTINGS. */
	private static final String SAVE_PROFILE_SETTINGS = "/api/settings/upsert";

	/** The Constant MAP_FETCH. */
	private static final String MAP_FETCH = "/api/settings/fetch";

	/** The Constant MAP_FETCH_MESSAGES. */
	private static final String MAP_FETCH_MESSAGES =
			"/api/settings/fetchmessages/?localeLanguage=en-US&pathFile=/src/main/resources//locale/messages_";

	/**
	 * Gets the settings bcf.
	 * 
	 * @return the settings bcf
	 */
	private SettingsBCFMockImpl getSettingsBCF()
	{
		return (SettingsBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(SystemSettingsAPIController.class).getSettingsBCF();
	}

	/**
	 * Fetch.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchProperties() throws Exception
	{
		setData("{}");

		// Success situation
		getSettingsBCF().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_PROPERTIES).andExpect(jsonPath("$.setting", hasSize(9)));

		// Failure situation
		getSettingsBCF().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_PROPERTIES).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getSettingsBCF().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_PROPERTIES).andExpect(jsonPath("$.setting", nullValue()));

		// Exception situation
		getSettingsBCF().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_PROPERTIES).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Save profile settings.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void saveProfileSettings() throws Exception
	{

		// set request object
		setData("{\"settings\":[{\"propertyEnum\":\"LANGUAGE\",\"propertyValue\":\"en_US\"},{\"propertyEnum\":\"DATE_FORMAT\",\"propertyValue\":\"dd/mm/yyyy\"},{\"propertyEnum\":\"PAGE_SIZE\",\"propertyValue\":\"25\"},{\"propertyEnum\":\"TIME_ZONE\",\"propertyValue\":\"US/Eastern\"},{\"propertyEnum\":\"CONVERT_ENERGY_UNIT\",\"propertyValue\":\"false\"},{\"propertyEnum\":\"PAGE_SIZE_SHOW_DIALOG\",\"propertyValue\":\"3\"},{\"propertyEnum\":\"SHOW_DIALOG_POLYGON\",\"propertyValue\":\"true\"}]}");

		// Success situation
		getSettingsBCF().setMode(ModeEnum.MODE_SUCCESS);
		performTest(SAVE_PROFILE_SETTINGS).andExpect(jsonPath("$.operationSuccess", equalTo(true))).andExpect(
				jsonPath("$.responseTime", notNullValue()));

		// Failure situation
		getSettingsBCF().setMode(ModeEnum.MODE_FAILURE);
		performTest(SAVE_PROFILE_SETTINGS).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getSettingsBCF().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(SAVE_PROFILE_SETTINGS).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	/**
	 * Open settings.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void openSettings() throws Exception
	{
		setData("{}");

		// Success situation
		getSettingsBCF().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_FETCH).andExpect(jsonPath("$.settings", hasSize(9)));

		// Failure situation
		getSettingsBCF().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getSettingsBCF().setMode(ModeEnum.MODE_EMPTY);
		performTest(MAP_FETCH).andExpect(jsonPath("$.settings", nullValue()));

		// Exception situation
		getSettingsBCF().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Fetch messages.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchMessages() throws Exception
	{

		setData("{}");

		// Success situation
		performTestGet(MAP_FETCH_MESSAGES).andExpect(content().string(notNullValue()));

		// Exception situation
		performTestGet("/api/settings/fetchmessages").andExpect(content().string(containsString("")));
	}
}
