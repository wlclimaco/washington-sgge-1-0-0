package com.sensus.mlc.wui.process.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.mlc.wui.process.ProcessAPIController;
import com.sensus.mlc.wui.util.AbstractTestBase;
import com.sensus.mlc.wui.util.ModeEnum;
import com.sensus.mlc.wui.util.TestMessageEnum;

public class ProcessAPIControllerTest extends AbstractTestBase
{

	private static final String FETCH_ALL = "/api/process/fetchAll";
	private static final String MAP_CHECK_RNI_STATUS = "/api/process/checkRNIStatus";
	private static final String MAP_FETCH = "/api/process/fetch";
	private static final String MAP_ABORT = "/api/process/abort";

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

		// Empty situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(MAP_CHECK_RNI_STATUS).andExpect(jsonPath("$.rniOnline", nullValue()));

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
		setData("{\"action\":\"count\"}");

		// Success situation - fetchCountMonitoredProcesses
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_FETCH).andExpect(jsonPath("$.countMonitoredProcess", notNullValue()));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(MAP_FETCH).andExpect(jsonPath("$.processes", hasSize(0)));

		/**
		 * fetchMonitoredProcesses
		 */
		setData("{\"sortExpressions\":[{\"field\":\"start_datetime\",\"direction\":\"Descending\"}],\"action\":\"table\",\"startRow\":0,\"pageSize\":25,\"endRow\":0}");

		// Success situation - fetchMonitoredProcesses
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_FETCH).andExpect(jsonPath("$.processes", notNullValue()));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(MAP_FETCH).andExpect(jsonPath("$.processes", hasSize(0)));

		/**
		 * fetchProcessById
		 */
		setData("{\"action\":\"id\",\"id\":\"55\"}");

		// Success situation - fetchMonitoredProcesses
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_FETCH).andExpect(jsonPath("$.processes", notNullValue()));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(MAP_FETCH).andExpect(jsonPath("$.processes", hasSize(0)));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}
}
