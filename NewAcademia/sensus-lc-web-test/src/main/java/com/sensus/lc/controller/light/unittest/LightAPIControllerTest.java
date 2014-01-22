package com.sensus.lc.controller.light.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.light.LightAPIController;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

/**
 * The Class LightAPIControllerTest.
 */
public class LightAPIControllerTest extends AbstractTestBase
{

	/* Spring Actions URLs */
	/** The Constant FETCH. */
	public static final String FETCH = "/api/light/fetch";

	/** The Constant FETCH_HISTORY. */
	public static final String FETCH_HISTORY = "/api/light/fetchhistory";

	/** The Constant FETCH_HISTORY_BY_ID. */
	public static final String FETCH_HISTORY_BY_ID = "/api/light/fetchhistorybyid";

	/** The Constant FETCH_BY_ID. */
	public static final String FETCH_BY_ID = "/api/light/fetchById/16";

	/** The Constant FETCH_ALL. */
	public static final String FETCH_ALL = "/api/light/fetchall";

	/** The Constant FETCH_LIGHTS_BY_PROCESS. */
	public static final String FETCH_LIGHTS_BY_PROCESS = "/api/light/fetchByProcess";

	/** The Constant FETCH_MESSAGES. */
	public static final String FETCH_MESSAGES = "/api/light/fetchmessages";

	/** The Constant FETCH_ALARMS. */
	public static final String FETCH_ALARMS = "/api/light/fetchalarms";

	@Test
	public void fetchLightById() throws Exception
	{
		// set request object
		setData("{\"id\":\"6\",\"action\":\"id\"}");

		// Success situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_BY_ID).andExpect(jsonPath("$.light", notNullValue()));

		// Failure situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_BY_ID).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_BY_ID).andExpect(jsonPath("$.light", nullValue()));

		// Exception situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_BY_ID).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	/**
	 * Fetch light history.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchLightHistory() throws Exception
	{
		// set request object
		setData("{\"notificationHistoryCriteria\":{\"notificationHistoryId\":null,\"notificationType\":null,\"lightId\":\"1008\",\"processFilter\":{\"startDate\":null,\"endDate\":null,\"lightTextSearch\":{},\"userIds\":null}},\"sortExpressions\":[{\"field\":\"LC_ACTION_DESCRIPTION\",\"direction\":\"Ascending\"}],\"startRow\":0,\"pageSize\":25,\"endRow\":0}");

		// Success situation
		getLightNotificationHistoryBCF().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_HISTORY).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getLightNotificationHistoryBCF().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_HISTORY).andExpect(jsonPath("$.operationSuccess", equalTo(false)));

		// Empty situation
		getLightNotificationHistoryBCF().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_HISTORY).andExpect(jsonPath("$.lightHistory", hasSize(0)));

		// Exception situation
		getLightNotificationHistoryBCF().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_HISTORY).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * FetchAll lights.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchAll() throws Exception
	{
		// set request object
		setData("{\"lightCriteria\":{\"lifeCycleStateList\":[\"ACTIVE\"],\"ecomodeFilter\":[]},\"groupCriteria\":{},\"alertCriteria\":{\"alertTypeList\":[],\"alertSubtypeList\":[]},\"addressCriteria\":{},\"processCriteria\":{},\"scheduleCriteria\":{\"lightIdList\":null,\"lightSchedule\":[]},\"configurationCriteria\":{},\"tagCriteria\":{},\"startRow\":0,\"endRow\":0,\"pageSize\":15,\"sortExpressions\":[{\"field\":\"label_key\",\"direction\":\"Descending\"}]}");
	
		// Success situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALL).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_ALL).andExpect(jsonPath("$.operationSuccess", equalTo(false)));

		// Empty situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_ALL).andExpect(jsonPath("$.lightList", hasSize(0)));

		// Exception situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_ALL).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * fetchByProcess .
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchByProcess() throws Exception
	{
		// set request object

		setData("{\"lightCriteria\":{\"lifeCycleStateList\":[\"ACTIVE\"],\"ecomodeFilter\":[]},\"groupCriteria\":{},\"alertCriteria\":{\"alertTypeList\":[],\"alertSubtypeList\":[]},\"addressCriteria\":{},\"processCriteria\":{},\"scheduleCriteria\":{\"lightIdList\":null,\"lightSchedule\":[]},\"configurationCriteria\":{},\"tagCriteria\":{},\"startRow\":0,\"endRow\":0,\"pageSize\":15,\"sortExpressions\":[{\"field\":\"label_key\",\"direction\":\"Descending\"}]}");
		// setData("{\"notificationHistoryCriteria\":{\"notificationHistoryId\":null,\"notificationType\":null,\"lightId\":\"1008\",\"processFilter\":{\"startDate\":null,\"endDate\":null,\"lightTextSearch\":{},\"userIds\":null}},\"sortExpressions\":[{\"field\":\"LC_ACTION_DESCRIPTION\",\"direction\":\"Ascending\"}],\"startRow\":0,\"pageSize\":25,\"endRow\":0}");

		// Success situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_LIGHTS_BY_PROCESS).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_LIGHTS_BY_PROCESS).andExpect(jsonPath("$.operationSuccess", equalTo(false)));

		// Empty situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_LIGHTS_BY_PROCESS).andExpect(jsonPath("$.lightList", hasSize(0)));

		// Exception situation
		getSmartPointAccessorMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_LIGHTS_BY_PROCESS).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Fetch light history.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchLightHistoryById() throws Exception
	{
		// set request object
		setData("{\"notificationHistoryCriteria\":{\"notificationHistoryId\":null,\"notificationType\":null,\"lightId\":\"1008\",\"processFilter\":{\"startDate\":null,\"endDate\":null,\"lightTextSearch\":{},\"userIds\":null}},\"sortExpressions\":[{\"field\":\"LC_ACTION_DESCRIPTION\",\"direction\":\"Ascending\"}],\"startRow\":0,\"pageSize\":25,\"endRow\":0}");

		// Success situation
		getLightNotificationHistoryBCF().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_HISTORY_BY_ID).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getLightNotificationHistoryBCF().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_HISTORY_BY_ID).andExpect(jsonPath("$.operationSuccess", equalTo(false)));

		// Exception situation
		getLightNotificationHistoryBCF().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_HISTORY_BY_ID).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	private LightBCFMockImpl getSmartPointAccessorMock()
	{
		return (LightBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(LightAPIController.class).getLightBCF();
	}

	private LightNotificationHistoryBCFMockImpl getLightNotificationHistoryBCF()
	{
		return (LightNotificationHistoryBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(LightAPIController.class).getLightNotificationHistoryBCF();

	}

}