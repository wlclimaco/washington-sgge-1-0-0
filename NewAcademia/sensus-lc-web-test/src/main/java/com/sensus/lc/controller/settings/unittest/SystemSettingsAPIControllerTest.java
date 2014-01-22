package com.sensus.lc.controller.settings.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.settings.SystemSettingsAPIController;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

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

	private static final String INSERT_USER_COLUMN_FILTER = "/api/settings/insertUserColumnFilter";

	private static final String MAP_UPSERT_SYSTEM_SETTINGS = "/api/settings/upsertSystemSettings";

	/** The Constant MAP_FETCH_MESSAGES. */
	private static final String MAP_FETCH_MESSAGES =
			"/api/settings/fetchmessages/?localeLanguage=en-US&pathFile=/src/main/resources/locale/messages_";

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
		performTest(FETCH_PROPERTIES).andExpect(jsonPath("$.settings", hasSize(7)));

		// Failure situation
		getSettingsBCF().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_PROPERTIES).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getSettingsBCF().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_PROPERTIES).andExpect(jsonPath("$.settings", nullValue()));

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
		performTest(SAVE_PROFILE_SETTINGS).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

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
		performTest(MAP_FETCH).andExpect(jsonPath("$.settings", hasSize(7)));

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

	@Test
	public void insertUserColumnFilter() throws Exception
	{

		// set request object
		setData("{\"listColumn\":[{\"columnEnum\":\"POLE_ID\",\"fieldName\":\"Pole ID\",\"ordered\":null},{\"columnEnum\":\"FLEXNET_ID\",\"fieldName\":\"FlexNet ID\",\"ordered\":null},{\"columnEnum\":\"LAMP_TYPE_WATTAGE_DIMMABLE\",\"fieldName\":\"Light Type\",\"ordered\":null},{\"columnEnum\":\"DATE_ADDED\",\"fieldName\":\"Date Added\",\"ordered\":null},{\"columnEnum\":\"MAP_IT\",\"fieldName\":\"Map It\",\"ordered\":null},{\"columnEnum\":\"PROTECTED\",\"fieldName\":\"Protected\",\"ordered\":null}],\"additionalColumns\":[{\"columnEnum\":\"CITY\",\"fieldName\":\"City\",\"ordered\":null},{\"columnEnum\":\"LIFECYCLE_STATE\",\"fieldName\":\"State\",\"ordered\":null},{\"columnEnum\":\"ECOMODE\",\"fieldName\":\"Eco-Mode\",\"ordered\":null}],\"filters\":[],\"additionalFilters\":[],\"listTypeEnum\":\"SMARTPOINTLIST\"}");

		// Success situation
		getSettingsBCF().setMode(ModeEnum.MODE_SUCCESS);
		performTest(INSERT_USER_COLUMN_FILTER).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getSettingsBCF().setMode(ModeEnum.MODE_FAILURE);
		performTest(INSERT_USER_COLUMN_FILTER).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getSettingsBCF().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(INSERT_USER_COLUMN_FILTER).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	@Test
	public void saveSystemSettings() throws Exception
	{

		// set request object
		setData("{\"settings\":[{\"propertyEnum\":\"LANGUAGE\",\"propertyValue\":\"en_US\"},{\"propertyEnum\":\"DATE_FORMAT\",\"propertyValue\":\"dd/mm/yyyy\"},{\"propertyEnum\":\"PAGE_SIZE\",\"propertyValue\":\"50\"},{\"propertyEnum\":\"TIME_ZONE\",\"propertyValue\":\"US/Arizona\"},{\"propertyEnum\":\"CONVERT_ENERGY_UNIT\",\"propertyValue\":\"false\"},{\"propertyEnum\":\"SUBSCRIBE_ALARM_BOARD_FAILURE\",\"propertyValue\":\"false\"},{\"propertyEnum\":\"SUBSCRIBE_ALARM_LAMP_FAILURE\",\"propertyValue\":\"false\"},{\"propertyEnum\":\"SUBSCRIBE_ALARM_POWER_FAILURE\",\"propertyValue\":\"false\"},{\"propertyEnum\":\"SUBSCRIBE_ALARM_METROLOGY_COM_FAILURE\",\"propertyValue\":\"false\"},{\"propertyEnum\":\"SUBSCRIBE_ALARM_METROLOGY_ERROR\",\"propertyValue\":\"false\"},{\"propertyEnum\":\"SUBSCRIBE_WARN_BROWNOUT_DETECTED\",\"propertyValue\":\"false\"},{\"propertyEnum\":\"SUBSCRIBE_WARN_COMMN_FAIL\",\"propertyValue\":\"false\"},{\"propertyEnum\":\"SUBSCRIBE_WARN_POWER_SURGE\",\"propertyValue\":\"false\"},{\"propertyEnum\":\"SUBSCRIBE_WARN_HIGH_CURRENT\",\"propertyValue\":\"false\"},{\"propertyEnum\":\"SUBSCRIBE_WARN_LOW_CURRENT\",\"propertyValue\":\"false\"},{\"propertyEnum\":\"SUBSCRIBE_WARN_METROLOGY_RESET\",\"propertyValue\":\"false\"},{\"propertyEnum\":\"SUBSCRIBE_WARN_REVERSE_ENERGY\",\"propertyValue\":\"false\"},{\"propertyEnum\":\"SUBSCRIBE_WARN_LIGHT_QUALITY\",\"propertyValue\":\"false\"},{\"propertyEnum\":\"PAGE_SIZE_SHOW_DIALOG\",\"propertyValue\":\"3\"},{\"propertyEnum\":\"SHOW_DIALOG_POLYGON\",\"propertyValue\":\"false\"},{\"propertyEnum\":\"MONITOR_REQUEST\",\"propertyValue\":\"3\"},{\"propertyEnum\":\"SHOW_SUBSCRIPTION_DIALOG\",\"propertyValue\":\"true\"}]}");

		// Success situation
		getSettingsBCF().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_UPSERT_SYSTEM_SETTINGS).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getSettingsBCF().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_UPSERT_SYSTEM_SETTINGS).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getSettingsBCF().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_UPSERT_SYSTEM_SETTINGS).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}
}
