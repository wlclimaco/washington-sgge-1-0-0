package com.sensus.dm.controller.schedule.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.schedule.ScheduleAPIController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class ScheduleAPIControllerTest.
 */
public class ScheduleAPIControllerTest extends AbstractTestBase
{

	/** The Constant DELETE. */
	private static final String DELETE = "/api/schedule/delete";

	/** The Constant FETCH. */
	private static final String FETCH = "/api/schedule/fetch";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/api/schedule/fetchAll";

	/** The Constant UPDATE. */
	private static final String UPDATE = "/api/schedule/update";

	/**
	 * Gets the schedule mock.
	 * 
	 * @return the schedule mock
	 */
	private ScheduleBCFMockImpl getScheduleMock()
	{
		return (ScheduleBCFMockImpl)SensusAppContext.getApplicationContext().getBean(ScheduleAPIController.class)
				.getScheduleBCF();
	}

	/**
	 * Fetch all.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchAll() throws Exception
	{
		// set...
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":25,\"sortExpressions\":[{\"field\":\"action_date\",\"direction\":\"Ascending\"}]}");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALL).andExpect(jsonPath("$.schedules", hasSize(25)));

		// set...
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":25,\"sortExpressions\":[{\"field\":\"name\",\"direction\":\"Ascending\"}]}");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALL).andExpect(jsonPath("$.schedules", hasSize(25)));

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_ALL).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
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
		/*
		 * Fetch Schedule By Id
		 * Set Data
		 */
		setData("{\"id\" : \"1\", \"type\" : \"openUpdateById\"}");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.schedules", hasSize(1)));

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.schedules", nullValue()));

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		/*
		 * Fetch Schedule By Device
		 * Set Data
		 */
		setData("{\"id\" : \"1\", \"type\" : \"scheduleByDevice\"}");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.schedules", hasSize(4)));

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.schedules", nullValue()));

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		/*
		 * Fetch Schedule By Group
		 * Set Data
		 */
		setData("{\"id\" : [\"1\"], \"type\" : \"checksSchedule\"}");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.schedules", hasSize(4)));

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.schedules", nullValue()));

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		/*
		 * Fetch Schedule By Name
		 * Set Data
		 */
		setData("{\"scheduleName\" : \"Schedule Name 01\", \"type\" : \"checkEvent\"}");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.schedules", hasSize(1)));

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.schedules", nullValue()));

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		/*
		 * Fetch Schedule By Action
		 * Set Data
		 */
		setData("{\"id\" : \"1\", \"type\" : \"openUpdateByAction\"}");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.schedules", hasSize(1)));

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.schedules", nullValue()));

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
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
		/*
		 * Delete Schedule
		 * Set Data
		 */
		setData("{\"id\" : 1}");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(DELETE);

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(DELETE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(DELETE).andExpect(jsonPath("$.schedules", nullValue()));

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(DELETE).andExpect(jsonPath("$.messageInfoList[0].code",
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
		/*
		 * Update Schedule Status for schedule pause option
		 * Set Data
		 */
		setData("{\"id\" : 1, \"type\" : \"statusPaused\"}");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(UPDATE);

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(UPDATE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(UPDATE).andExpect(jsonPath("$.schedules", nullValue()));

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(UPDATE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		/*
		 * Update schedule status for schedule resume option
		 * Set Data
		 */
		setData("{\"id\" : 2, \"type\" : \"statusResume\"}");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(UPDATE);

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(UPDATE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(UPDATE).andExpect(jsonPath("$.schedules", nullValue()));

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(UPDATE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}
}