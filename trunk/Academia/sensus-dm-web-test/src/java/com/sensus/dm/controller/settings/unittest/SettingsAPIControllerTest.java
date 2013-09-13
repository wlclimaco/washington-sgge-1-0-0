package com.sensus.dm.controller.settings.unittest;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.systemsettings.SettingsAPIController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class SettingsAPIControllerTest.
 */
public class SettingsAPIControllerTest extends AbstractTestBase
{

	/** The Constant FETCH. */
	private static final String FETCH = "/api/settings/fetch";

	/** The Constant UPSERT. */
	private static final String UPSERT = "/api/settings/upsert";

	/**
	 * Gets the settings mock.
	 * 
	 * @return the settings mock
	 */
	private SettingsBCFMockImpl getSettingsMock()
	{
		return (SettingsBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(SettingsAPIController.class).getSettingsBCF();
	}

	/**
	 * Fetch.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetch() throws Exception
	{
		// Success situation with empty request
		setData("{}");
		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.properties", nullValue()));

		// Success situation: System
		setData("{\"typeSettings\":\"system\"}");

		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.properties", hasSize(4)));

		// Success situation: User
		setData("{\"typeSettings\":\"user\"}");

		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.properties", hasSize(5)));

		// Failure situation
		getSettingsMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getSettingsMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.properties", nullValue()));

		// Exception situation
		getSettingsMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Upsert.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void upsert() throws Exception
	{
		// Success situation: User
		setData("{\"properties\":[{\"propertyName\":\"LANGUAGE\",\"propertyValue\":\"en_US\"}," +
				"{\"propertyName\":\"TIME_ZONE\",\"propertyValue\":\"US/Eastern\"}," +
				"{\"propertyName\":\"DATE_FORMAT\",\"propertyValue\":\"mm/dd/yyyy\"}," +
				"{\"propertyName\":\"MONITOR_REQUEST\",\"propertyValue\":\"1\"}," +
				"{\"propertyName\":\"PAGE_SIZE\",\"propertyValue\":\"25\"}," +
				"{\"propertyName\":\"TEMPERATURE\",\"propertyValue\":\"FAHRENHEIT\"}]}");

		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(UPSERT)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)))
				.andExpect(jsonPath("$.messageInfoList", hasSize(0)));

		// Success situation: System
		setData("{\"properties\":[{\"propertyName\":\"LANGUAGE\",\"propertyValue\":\"en_US\"}," +
				"{\"propertyName\":\"TIME_ZONE\",\"propertyValue\":\"US/Eastern\"}," +
				"{\"propertyName\":\"DATE_FORMAT\",\"propertyValue\":\"mm/dd/yyyy\"}," +
				"{\"propertyName\":\"PAGE_SIZE\",\"propertyValue\":\"25\"}," +
				"{\"propertyName\":\"TEMPERATURE\",\"propertyValue\":\"FAHRENHEIT\"}]}");

		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(UPSERT)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)))
				.andExpect(jsonPath("$.messageInfoList", hasSize(0)));

		// Failure situation
		getSettingsMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(UPSERT).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getSettingsMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(UPSERT).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Exception situation
		getSettingsMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(UPSERT).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}
}
