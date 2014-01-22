package com.sensus.lc.controller.process.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.process.ProcessAPIController;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

public class ProcessAPIControllerTest extends AbstractTestBase
{

	private static final String FETCH_ALL = "/api/process/fetchAll";
	private static final String MAP_CHECK_RNI_STATUS = "/api/process/checkRNIStatus";
	private static final String MAP_FETCH_COUNT = "/api/process/fetch/count";
	private static final String MAP_FETCH_ID = "/api/process/fetch/id";
	private static final String MAP_FETCH_SUMMARY = "/api/process/fetch/summary";
	private static final String MAP_FETCH_MONITORED = "/api/process/fetch/monitored";
	private static final String MAP_ABORT = "/api/process/abort";
	private static final String MAP_UPDATE = "/api/process/update";
	private static final String MAP_RETRY = "/api/process/retry/9";

	private ProcessBCFMockImpl getProcessMock()
	{
		return (ProcessBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ProcessAPIController.class).getProcessBCF();
	}

	/**
	 * FetchAll.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchAll() throws Exception
	{
		// set request object
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":25,\"sortExpressions\":[{\"field\":\"start_datetime\",\"direction\":\"Descending\"}],\"action\":\"table\"}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALL).andExpect(jsonPath("$.processes", hasSize(25)));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_ALL).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_ALL).andExpect(jsonPath("$.processes", nullValue()));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_ALL).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void fetchRniLinkStatus() throws Exception
	{
		// set request object
		setData("{}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_CHECK_RNI_STATUS).andExpect(jsonPath("$.rniOnline", equalTo(true)));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_CHECK_RNI_STATUS).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_CHECK_RNI_STATUS).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void abort() throws Exception
	{
		// set request object
		setData("{\"processList\":[{\"id\":152}]}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_ABORT).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_ABORT).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_ABORT).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void fetch() throws Exception
	{
		/**
		 * fetchCountMonitoredProcesses
		 */
		// set request object
		setData("{}");

		// Success situation - fetchCountMonitoredProcesses
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_FETCH_COUNT).andExpect(jsonPath("$.countMonitoredProcess", notNullValue()));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_FETCH_COUNT).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(MAP_FETCH_COUNT).andExpect(jsonPath("$.processes", hasSize(0)));

		/**
		 * fetchMonitoredProcesses
		 */
		setData("{\"sortExpressions\":[{\"field\":\"start_datetime\",\"direction\":\"Descending\"}],\"startRow\":0,\"pageSize\":25,\"endRow\":0}");

		// Success situation - fetchMonitoredProcesses
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_FETCH_MONITORED).andExpect(jsonPath("$.processes", notNullValue()));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_FETCH_MONITORED).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(MAP_FETCH_MONITORED).andExpect(jsonPath("$.processes", hasSize(0)));

		/**
		 * fetchProcessById
		 */
		setData("{\"processList\":[{\"id\":55}]}");

		// Success situation - fetchMonitoredProcesses
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_FETCH_ID).andExpect(jsonPath("$.processes", notNullValue()));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_FETCH_ID).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(MAP_FETCH_ID).andExpect(jsonPath("$.processes", hasSize(0)));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_FETCH_ID).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		/**
		 * Fetch Summary
		 */
		setData("{\"processList\":[{\"id\":55}]}");

		// Success situation - fetchMonitoredProcesses
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_FETCH_SUMMARY).andExpect(jsonPath("$.processes", notNullValue()));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_FETCH_SUMMARY).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(MAP_FETCH_SUMMARY).andExpect(jsonPath("$.processes", hasSize(0)));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_FETCH_SUMMARY).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void update() throws Exception
	{
		// set request object
		setData("{\"processList\":[{\"id\":7322}],\"action\":\"updateMonitoProcess\"}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_UPDATE).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_UPDATE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_UPDATE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// set request object
		setData("{\"processList\":[{\"id\":7322}],\"action\":\"updateMonitorProcess\"}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_UPDATE).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_UPDATE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_UPDATE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void retry() throws Exception
	{
		// set request object
		setData("{\"processList\":[{\"id\":152}]}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_RETRY).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_RETRY).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_RETRY).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

}
