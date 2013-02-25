package com.sensus.mlc.wui.light.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.mlc.wui.light.LightAPIController;
import com.sensus.mlc.wui.util.AbstractTestBase;
import com.sensus.mlc.wui.util.ModeEnum;
import com.sensus.mlc.wui.util.TestMessageEnum;


public class LightAPIControllerTest extends AbstractTestBase
{

	/* Spring Actions URLs */
	/** The Constant FETCH. */
	public static final String FETCH = "/fetch";

	/** The Constant FETCH_ALL. */
	public static final String FETCH_ALL = "/fetchall";

	/** The Constant FETCH_MESSAGES. */
	public static final String FETCH_MESSAGES = "/fetchmessages";

	/** The Constant FETCH_ALARMS. */
	public static final String FETCH_ALARMS = "/fetchalarms";


	private SmartPointAccessorBCFMockImpl getSmartPointAccessorMock()
	{
		return (SmartPointAccessorBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(LightAPIController.class).getSmartPointAccessorBCF();
	}

	/**
	 * Fetch all.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void fetchAll() throws Exception
	{
		// set request object
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":25,\"sortExpressions\":[{\"field\":\"name\",\"direction\":\"Ascending\"}],\"action\":\"table\"}");

		// Success situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALL).andExpect(jsonPath("$.lights", hasSize(25)));

		// Failure situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_ALL).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_ALL).andExpect(jsonPath("$.lights", nullValue()));

		// Exception situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_ALL).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Fetch.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void fetch() throws Exception
	{
		// set request object
		//setData("{\"action\":\"fetchById\",\"groupId\":\"39\"}");

		// Success situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.lights", hasSize(1))).andExpect(
				jsonPath("$.lights[0].id", equalTo(39)));

		// Failure situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.lights", nullValue()));

		// Exception situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void fetchStatusMessage() throws Exception
	{
		// set request object
		setData("{\"lightId\":\"1\"}");

		// Success situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_MESSAGES).andExpect(jsonPath("$.lights", hasSize(1))).andExpect(
				jsonPath("$.lights[0].id", equalTo(39)));

		// Failure situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_MESSAGES).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_MESSAGES).andExpect(jsonPath("$.lights", nullValue()));

		// Exception situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_MESSAGES).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void currentAlarmStatus() throws Exception
	{
		// set request object
		setData("{\"lightId\":\"1\"}");

		// Success situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALARMS).andExpect(jsonPath("$.lights", hasSize(1))).andExpect(
				jsonPath("$.lights[0].id", equalTo(39)));

		// Failure situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_ALARMS).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_ALARMS).andExpect(jsonPath("$.lights", nullValue()));

		// Exception situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_ALARMS).andExpect(jsonPath("$.messageInfoList[0].code",
			containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

}