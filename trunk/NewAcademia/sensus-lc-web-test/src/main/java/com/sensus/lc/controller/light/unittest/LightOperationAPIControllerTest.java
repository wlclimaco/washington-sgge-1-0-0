package com.sensus.lc.controller.light.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.light.LightOperationAPIController;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

public class LightOperationAPIControllerTest extends AbstractTestBase
{

	/* Spring Actions URLs */
	/** The Constant CLEAR_STATUS. */
	public static final String CLEAR_STATUS = "/api/lighttop/clearstatus";

	/** The Constant UPSERT_PROPERTY. */
	public static final String UPSERT_PROPERTY = "/api/lighttop/upsertproperty/poleid";

	/** The Constant FETCH_STATUS. */
	public static final String FETCH_STATUS = "/api/lighttop/fetchstatus";

	/** The Constant UPDATE_PROTECTED. */
	public static final String UPDATE_PROTECTED = "/api/lighttop/updateprotected";

	/** The Constant UPDATE_STATUS. */
	public static final String UPDATE_STATUS = "/api/lighttop/updatestatus";

	/** The Constant UPDATE_RESET. */
	public static final String UPDATE_RESET = "/api/lighttop/updatereset";

	/** The Constant CONTROL_LIGHTS. */
	public static final String CONTROL_LIGHTS = "/api/lighttop/controlLights";

	private LightProcessorBCFMockImpl getLightProcessorMock()
	{
		return (LightProcessorBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(LightOperationAPIController.class).getLightProcessorBCF();
	}

	private LightBCFMockImpl getLightMock()
	{
		return (LightBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(LightOperationAPIController.class).getLightBCF();
	}

	@Test
	public void clearStatus() throws Exception
	{

		// set request object
		setData("{\"lightCriteria\":{\"lightIdList\":[1009]},\"actionCriteria\":null,\"groupCriteria\":null,\"alertCriteria\":{\"alertTypeList\":null,\"alertSubtypeList\":[\"COMMUNICATION_FAIL\"]},\"addressCriteria\":null,\"processCriteria\":null,\"scheduleCriteria\":null,\"configurationCriteria\":null,\"operationalDataCriteria\":null,\"tagCriteria\":null,\"notificationHistoryCriteria\":{\"notificationHistoryId\":11807},\"startRow\":0,\"endRow\":0,\"pageSize\":\"25\",\"sortExpressions\":[]}");

		// Success situation
		getLightProcessorMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(CLEAR_STATUS).andExpect(jsonPath("$.processes", hasSize(1)));

		// Failure situation
		getLightProcessorMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(CLEAR_STATUS).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getLightProcessorMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(CLEAR_STATUS).andExpect(jsonPath("$.processes", hasSize(0)));

		// Exception situation
		getLightProcessorMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(CLEAR_STATUS).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void updateLightProperty() throws Exception
	{

		// set request object
		setData("{\"light\":{\"id\":\"1020\",\"poleId\":\"POLED_2\"},\"processCriteria\":{\"monitored\":true}}");

		// Success situation
		getLightProcessorMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(UPSERT_PROPERTY).andExpect(jsonPath("$.processes", hasSize(1)));

		// Failure situation
		getLightProcessorMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(UPSERT_PROPERTY).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getLightProcessorMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(UPSERT_PROPERTY).andExpect(jsonPath("$.processes", hasSize(0)));

		// Exception situation
		getLightProcessorMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(UPSERT_PROPERTY).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	@Test
	public void fetchLightStatus() throws Exception
	{

		// set request object
		setData("{\"lightCriteria\":{\"lightIdList\":[\"1020\"],\"lifeCycleStateList\":null,\"geoCodeCriteria\":null,\"protect\":null,\"lightBlink\":\"NONE\"},\"actionCriteria\":{\"percentage\":null,\"blinkStatus\":null,\"override\":\"NONE\",\"overridePerDate\":null,\"lifeCycleState\":null,\"isClearOverride\":null,\"getDataFromLightEnum\":\"ALL\"},\"processCriteria\":{\"monitored\":true},\"startRow\":0,\"endRow\":0,\"pageSize\":\"25\",\"sortExpressions\":[]}");

		// Success situation
		getLightProcessorMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_STATUS).andExpect(jsonPath("$.processes", hasSize(1)));

		// Failure situation
		getLightProcessorMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_STATUS).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getLightProcessorMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_STATUS).andExpect(jsonPath("$.processes", hasSize(0)));

		// Exception situation
		getLightProcessorMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_STATUS).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void updateLightProtected() throws Exception
	{

		// set request object
		setData("{\"light\":{\"protect\":true},\"lightCriteria\":{\"lightIdList\":[1020]}}");

		// Success situation
		getLightMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(UPDATE_PROTECTED).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getLightMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(UPDATE_PROTECTED).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getLightMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(UPDATE_PROTECTED).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	@Test
	public void updateLightStatus() throws Exception
	{

		// set request object
		setData("{\"light\":{\"id\":1020,\"lifeCycleState\":\"DEACTIVATED\"},\"processCriteria\":{\"monitored\":true}}");

		// Success situation
		getLightProcessorMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(UPDATE_STATUS).andExpect(jsonPath("$.processes", hasSize(1)));

		// Failure situation
		getLightProcessorMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(UPDATE_STATUS).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getLightProcessorMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(UPDATE_STATUS).andExpect(jsonPath("$.processes", hasSize(0)));

		// Exception situation
		getLightProcessorMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(UPDATE_STATUS).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	@Test
	public void resetValues() throws Exception
	{

		// set request object
		setData("{\"lightCriteria\":{\"lightIdList\":[1020]},\"startRow\":0,\"endRow\":0,\"pageSize\":\"25\",\"sortExpressions\":[]}");
		// Success situation
		getLightMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(UPDATE_RESET).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getLightMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(UPDATE_RESET).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getLightMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(UPDATE_RESET).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void initiateUpdateLightIntensity() throws Exception
	{

		// set request object
		setData("{\"lightCriteria\":{\"lightIdList\":[\"1020\"]},\"actionCriteria\":{\"percentage\":100,\"blinkStatus\":\"NONE\",\"override\":\"SCHEDULED\",\"overridePerDate\":null},\"processCriteria\":{\"monitored\":true},\"startRow\":0,\"endRow\":0,\"pageSize\":\"25\",\"sortExpressions\":[]}");

		// Success situation
		getLightProcessorMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(CONTROL_LIGHTS).andExpect(jsonPath("$.processes", hasSize(1)));

		// Failure situation
		getLightProcessorMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(CONTROL_LIGHTS).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getLightProcessorMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(CONTROL_LIGHTS).andExpect(jsonPath("$.processes", hasSize(0)));

		// Exception situation
		getLightProcessorMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(CONTROL_LIGHTS).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

}