package com.sensus.dm.controller.process.unittest;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.process.ProcessAPIController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class ProcessAPIControllerTest.
 */
public class ProcessAPIControllerTest extends AbstractTestBase
{

	/** The Constant API_PROCESS_FETCH_BY_ID. */
	private static final String FETCH_BY_ID = "/api/process/fetchById";

	/** The Constant FETCH. */
	private static final String FETCH = "/api/process/fetch";

	/** The Constant FETCH_SCHEDULE. */
	private static final String FETCH_SCHEDULE = "/api/process/fetchScheduled";

	/** The Constant FETCH_HAN_TEXT_MESSAGE_INFO. */
	private static final String FETCH_HAN_TEXT_MESSAGE_INFO = "/api/process/fetchHanTextMessageInfo";

	/** The Constant FETCH_COUNT_MONITORED. */
	private static final String FETCH_COUNT_MONITORED = "/api/process/fetchCountMonitored";

	/** The Constant CHECK_RNI_STATUS. */
	private static final String CHECK_RNI_STATUS = "/api/process/checkRNIStatus";

	/** The Constant UPDATE. */
	private static final String UPDATE = "/api/process/update";

	private static final String EXPIRE = "/api/process/expire";

	/**
	 * Gets the process mock.
	 * 
	 * @return the process mock
	 */
	private ProcessBCFMockImpl getProcessMock()
	{
		return (ProcessBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ProcessAPIController.class).getProcessBCF();
	}

	/**
	 * Gets the process summary mock.
	 * 
	 * @return the process summary mock
	 */
	private ProcessSummaryBCFMockImpl getProcessSummaryMock()
	{
		return (ProcessSummaryBCFMockImpl)SensusAppContext.getApplicationContext().getBean(ProcessAPIController.class)
				.getProcessSummaryBCF();
	}

	/**
	 * Fetch.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetch() throws Exception
	{

		// History Page
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":25,\"processSearch\":{\"startDate\":\"2013-03-06T05:00:00.170Z\","
				+ "\"endDate\":\"2013-03-07T04:59:59.000Z\", \"searchType\":\"NETWORK_ADDRESS\", \"searchText\":\"00:00:00:00:00:b3:6d:cd\"}}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.processes", hasSize(25)));

		// Today Page
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":25,\"isToday\":true}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.processes", hasSize(25)));

		// Dashboard Page
		setData("{\"startRow\":0,\"endRow\":4,\"pageSize\":5, \"processSearch\":{\"startDate\":\"2013-03-06T05:00:00.170Z\",\"endDate\":\"2013-03-07T04:59:59.000Z\"}}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.processes", hasSize(5)));

		// Recent Request/Monitor Dialog
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":null," +
				"\"processes\":[{\"monitoredInstance\":true,\"dashboardMonitored\":false}]}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.processes", hasSize(25)));

		// Default request for other situations
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":25, \"processSearch\":{\"startDate\":\"2013-03-06T05:00:00.170Z\",\"endDate\":\"2013-03-07T04:59:59.000Z\"}}");

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.processes", nullValue()));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Fetch by id.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchById() throws Exception
	{
		// No processID parameter
		setData("{}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_BY_ID).andExpect(jsonPath("$.processes", nullValue()));

		// Test with Process ID
		setData("{\"processID\":1}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_BY_ID)
				.andExpect(jsonPath("$.processes", hasSize(1)))
				.andExpect(jsonPath("$.processes[0].processItems", hasSize(4)))
				.andExpect(jsonPath("$.processes[0].processItems[0].device.radio.flexNetId", comparesEqualTo(1001)));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_BY_ID).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_BY_ID).andExpect(jsonPath("$.processes", nullValue()));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_BY_ID).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Fetch schedule.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchSchedule() throws Exception
	{
		// No processID parameter
		setData("{}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_SCHEDULE).andExpect(jsonPath("$.processes", nullValue()));

		// Test with Process ID
		setData("{\"processID\":1}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_SCHEDULE)
				.andExpect(jsonPath("$.processes", hasSize(1)))
				.andExpect(jsonPath("$.processes[0].processItems", hasSize(4)))
				.andExpect(jsonPath("$.processes[0].processItems[0].device.radio.flexNetId", comparesEqualTo(1001)));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_SCHEDULE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_SCHEDULE).andExpect(jsonPath("$.processes", nullValue()));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_SCHEDULE).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Fetch han text message info.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchHanTextMessageInfo() throws Exception
	{
		// No processID parameter
		setData("{}");

		// Success situation
		getProcessSummaryMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_HAN_TEXT_MESSAGE_INFO).andExpect(jsonPath("$.processes", nullValue()));

		// Test with Process ID
		setData("{\"processID\":1}");

		// Success situation
		getProcessSummaryMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_HAN_TEXT_MESSAGE_INFO)
				.andExpect(jsonPath("$.processes", hasSize(1)))
				.andExpect(jsonPath("$.processes[0].processItems", hasSize(4)))
				.andExpect(jsonPath("$.processes[0].processItems[0].device.radio.flexNetId", comparesEqualTo(1001)));

		// Failure situation
		getProcessSummaryMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_HAN_TEXT_MESSAGE_INFO).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getProcessSummaryMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_HAN_TEXT_MESSAGE_INFO).andExpect(jsonPath("$.processes", nullValue()));

		// Exception situation
		getProcessSummaryMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_HAN_TEXT_MESSAGE_INFO).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Fetch count monitored.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchCountMonitored() throws Exception
	{
		setData("{}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_COUNT_MONITORED)
				.andExpect(jsonPath("$.countMonitoredProcess.count_monitored", comparesEqualTo(30)))
				.andExpect(jsonPath("$.countMonitoredProcess.count_processing", comparesEqualTo(2)));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_COUNT_MONITORED).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_COUNT_MONITORED).andExpect(jsonPath("$.countMonitoredProcess", nullValue()));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_COUNT_MONITORED).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Check RNI.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void checkRni() throws Exception
	{
		setData("{}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(CHECK_RNI_STATUS)
				.andExpect(jsonPath("$.linkStatus", comparesEqualTo(true)));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(CHECK_RNI_STATUS).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(CHECK_RNI_STATUS).andExpect(jsonPath("$.linkStatus", nullValue()));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(CHECK_RNI_STATUS).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Update.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void update() throws Exception
	{
		setData("{\"processList\":[{\"id\":\"1\",\"monitoredInstance\":false}," +
				"{\"id\":\"2\",\"monitoredInstance\":false}]}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(UPDATE)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)))
				.andExpect(jsonPath("$.messageInfoList", hasSize(0)));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(UPDATE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(UPDATE).andExpect(jsonPath("$.messageInfoList", hasSize(0)));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(UPDATE).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void expireProcess() throws Exception
	{
		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);

		setData("{\"processList\":[{\"id\":2449}]}");

		performTest(EXPIRE)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)))
				.andExpect(jsonPath("$.messageInfoList", hasSize(0)));

		// By device id
		setData("{\"processList\":[{\"id\":2449,\"processItems\":[{\"device\":{\"radio\":{\"flexNetId\":1001}}}]}]}");

		performTest(EXPIRE)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)))
				.andExpect(jsonPath("$.messageInfoList", hasSize(0)));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(EXPIRE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(EXPIRE).andExpect(jsonPath("$.messageInfoList", hasSize(0)));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(EXPIRE).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}
}
