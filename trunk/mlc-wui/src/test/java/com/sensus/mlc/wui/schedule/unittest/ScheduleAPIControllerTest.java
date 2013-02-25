package com.sensus.mlc.wui.schedule.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.mlc.wui.schedule.ScheduleAPIController;
import com.sensus.mlc.wui.util.AbstractTestBase;
import com.sensus.mlc.wui.util.ModeEnum;
import com.sensus.mlc.wui.util.TestMessageEnum;

/**
 * The Class ScheduleAPIControllerTest.
 */
public class ScheduleAPIControllerTest extends AbstractTestBase
{

	/** The Constant FETCH. */
	private static final String FETCH_ALL = "/api/schedule/fetchAll";

	/** The Constant FETCH. */
	private static final String FETCH = "/api/schedule/fetch";

	/** The Constant DELETE. */
	private static final String DELETE = "/api/schedule/delete";

	/** The Constant INSERT. */
	private static final String INSERT = "/api/schedule/insert";

	/** The Constant INSERT_LIGHTS. */
	private static final String INSERT_LIGHTS = "/api/schedule/insertlights";

	/** The Constant UPDATE. */
	private static final String UPDATE = "/api/schedule/update";

	/** The Constant DELETE_LIGHTS. */
	private static final String DELETE_LIGHTS = "/api/schedule/deletelights";

	/**
	 * Gets the schedule mock.
	 * 
	 * @return the schedule mock
	 */
	private ScheduleBCFMockImpl getScheduleMock()
	{
		return (ScheduleBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ScheduleAPIController.class).getScheduleBCF();
	}

	/**
	 * Fetch.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchAll() throws Exception
	{
		// set request object
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":25,\"sortExpressions\":[{\"field\":\"name\",\"direction\":\"Ascending\"}],\"action\":\"table\"}");

		// Success situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALL).andExpect(jsonPath("$.schedules", hasSize(25)));

		// Failure situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_ALL).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_ALL).andExpect(jsonPath("$.schedules", nullValue()));

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
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
		setData("{\"scheduleTypeEnum\":\"EVENT\",\"schedule\":{\"id\":7,\"scheduleTypeEnum\":\"EVENT\"},\"selectionPaginationIds\":[]}");

		// Success situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.schedules", hasSize(1))).andExpect(
				jsonPath("$.schedules[0].id", equalTo(7)));

		// Failure situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.schedules", nullValue()));

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Insert.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void insert() throws Exception
	{
		// set request object
		setData("{\"scheduleTypeEnum\":\"OFFSET\",\"offsetSchedule\":{\"id\":null,\"description\":\"teste1\",\"name\":\"Schedule 1\",\"scheduleTypeEnum\":\"OFFSET\",\"sunriseOffsetMinutes\":\"5\",\"sunsetOffsetMinutes\":\"10\",\"sunriseBefore\":\"false\",\"sunsetBefore\":\"true\",\"intensity\":50},\"bottomLeftLat\":null,\"bottomLeftLon\":null,\"topRightLat\":null,\"topRightLon\":null,\"maxSmartpointCount\":null,\"selectionPaginationIds\":[]}");

		// Success situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(INSERT).andExpect(jsonPath("$.schedules", hasSize(1))).andExpect(
				jsonPath("$.schedules[0].name", equalTo("Schedule 10")));

		// Failure situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(INSERT).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(INSERT).andExpect(jsonPath("$.messageInfoList[0].code",
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
		// set request object
		setData("{\"scheduleTypeEnum\":\"OFFSET\",\"offsetSchedule\":{\"id\":\"8\",\"description\":\"teste2\",\"name\":\"teste2\",\"scheduleTypeEnum\":\"OFFSET\",\"sunriseOffsetMinutes\":\"5\",\"sunsetOffsetMinutes\":\"10\",\"sunriseBefore\":\"false\",\"sunsetBefore\":\"true\",\"intensity\":50},\"bottomLeftLat\":null,\"bottomLeftLon\":null,\"topRightLat\":null,\"topRightLon\":null,\"maxSmartpointCount\":null,\"selectionPaginationIds\":[]}");

		// Success situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(UPDATE).andExpect(jsonPath("$.schedules", hasSize(1))).andExpect(
				jsonPath("$.schedules[0].id", equalTo(8)));

		// Failure situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(UPDATE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(UPDATE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Delete.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void delete() throws Exception
	{
		// set request object to event schedule
		setData("{\"scheduleTypeEnum\":\"EVENT\",\"selectionPaginationIds\":[8],\"paginationAllSelected\":false}");

		// Success situation - event schedule
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(DELETE).andExpect(jsonPath("$.schedules", hasSize(1))).andExpect(
				jsonPath("$.schedules[0].id", equalTo(8)));

		// set request object to offset schedule
		setData("{\"scheduleTypeEnum\":\"OFFSET\",\"selectionPaginationIds\":[8],\"paginationAllSelected\":false}");

		// Success situation - offset schedule
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(DELETE).andExpect(jsonPath("$.schedules", hasSize(1))).andExpect(
				jsonPath("$.schedules[0].id", equalTo(8)));

		// Failure situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(DELETE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(DELETE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Insert lights.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void insertLights() throws Exception
	{
		// set request object
		setData("{\"scheduleTypeEnum\":\"EVENT\",\"schedule\":{\"id\":\"7\",\"name\":\"qqqq\",\"scheduleTypeEnum\":\"EVENT\"},\"bottomLeftLat\":null,\"bottomLeftLon\":null,\"topRightLat\":null,\"topRightLon\":null,\"maxSmartpointCount\":null,\"searchLight\":{\"searchParameters\":[{\"propertyEnum\":\"SORT\",\"value\":\"ss.label_key desc\"}]},\"paginationAllSelected\":false,\"selectionPaginationIds\":[\"47\"],\"isMonitored\":true}");

		// Success situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(INSERT_LIGHTS).andExpect(jsonPath("$.schedules", hasSize(1))).andExpect(
				jsonPath("$.schedules[0].id", equalTo(7))).andExpect(
				jsonPath("$.schedules[0].lights[0].rniId", equalTo(47)));

		// Failure situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(INSERT_LIGHTS).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(INSERT_LIGHTS).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Delete lights.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void deleteLights() throws Exception
	{
		// set request object
		setData("{\"scheduleTypeEnum\":\"EVENT\",\"selectionPaginationIds\":[17],\"paginationAllSelected\":false}");

		// Success situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(DELETE_LIGHTS).andExpect(jsonPath("$.schedules[0].lights[0].rniId", equalTo(17)));

		// Failure situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(DELETE_LIGHTS).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(DELETE_LIGHTS).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}
}