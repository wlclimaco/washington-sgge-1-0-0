package com.sensus.dm.controller.group.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.group.GroupAPIController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class GroupAPIControllerTest.
 */
public class GroupAPIControllerTest extends AbstractTestBase
{
	/** The Constant DELETE. */
	private static final String DELETE = "/api/group/delete";

	/** The Constant DELETE_DEVICE. */
	private static final String DELETE_DEVICE = "/api/group/deleteDevice";

	/** The Constant FETCH. */
	private static final String FETCH = "/api/group/fetch";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/api/group/fetchAll";

	/** The Constant INSERT_DEVICE. */
	private static final String INSERT_DEVICE = "/api/group/insertDevice";

	/**
	 * Gets the group mock.
	 * 
	 * @return the group mock
	 */
	private GroupBCFMockImpl getGroupMock()
	{
		return (GroupBCFMockImpl)SensusAppContext.getApplicationContext().getBean(GroupAPIController.class)
				.getGroupBCF();
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
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":25,\"sortExpressions\":[{\"field\":\"name\",\"direction\":\"Ascending\"}]}");

		// Success Situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALL).andExpect(jsonPath("$.groups", hasSize(25)));

		// Failure Situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_ALL).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getGroupMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_ALL).andExpect(jsonPath("$.groups", nullValue()));

		// Exception situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
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
		 * Fetch to open Edit Group Page
		 * Success Situation
		 */
		setData("{\"id\" : \"1\", \"type\" : \"editGroup\"}");
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath(".$groups", hasSize(1)));

		/*
		 * Fetch to open Edit Group Page
		 * Success Situation with device type
		 */
		setData("{\"id\" : \"1\", \"type\" : \"editGroup\", \"deviceType\" : \"ELECTRIC_METER\"}");
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath(".$groups", hasSize(1)));

		/*
		 * Fetch to open Edit Group Page
		 * Success Situation with device type
		 */
		setData("{\"id\" : \"1\", \"type\" : \"checkGroupName\", \"deviceType\" : \"ELECTRIC_METER\"}");
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath(".$groups", hasSize(1)));

		/*
		 * Fetch to open Edit Group Page
		 * Success Situation with device type
		 */
		setData("{\"id\" : \"1\", \"type\" : \"fetchGroupsByDevice\", \"deviceType\" : \"ELECTRIC_METER\"}");
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath(".$groups", hasSize(10)));

		// Failure Situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getGroupMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.groups", nullValue()));

		// Exception Situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		/*
		 * Fetch to open Check Group Name method
		 * Success Situation
		 */
		setData("{\"groupName\" : \"Group Name 01\", \"type\" : \"checkGroupName\"}");

		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath(".$groups", hasSize(1)));

		// Failure Situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getGroupMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.groups", nullValue()));

		// Exception Situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		/*
		 * Fetch Groups By Device
		 * Success Situation
		 */
		setData("{\"id\" : \"1\", \"type\" : \"fetchGroupsByDevice\"}");

		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath(".$groups", hasSize(10)));

		// Failure Situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getGroupMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.groups", nullValue()));

		// Exception Situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
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
		setData("{\"groups\" : [\"1\",\"2\"]}");

		// Success Situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(DELETE);

		// Failure Situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(DELETE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getGroupMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(DELETE).andExpect(jsonPath("$.groups", nullValue()));

		// Exception Situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(DELETE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Insert device.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void insertDevice() throws Exception
	{
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":25,\"sortExpressions\":[{\"field\":\"name\",\"direction\":\"Ascending\"}],\"groups\":[{\"name\":\"GROUP_NAME\",\"id\":1}],\"devices\":[{}]}");

		// Success Situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(INSERT_DEVICE);

		// Failure Situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(INSERT_DEVICE);

		// Empty Situation
		getGroupMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(INSERT_DEVICE);

		// Exception Situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(INSERT_DEVICE);
	}

	/**
	 * Delete device.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void deleteDevice() throws Exception
	{
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":25,\"sortExpressions\":[{\"field\":\"name\",\"direction\":\"Ascending\"}],\"groups\":[{\"name\":\"GROUP_NAME\",\"id\":1}],\"devices\":[{}]}");

		// Success Situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(DELETE_DEVICE);

		// Failure Situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(DELETE_DEVICE);

		// Empty Situation
		getGroupMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(DELETE_DEVICE);

		// Exception Situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(DELETE_DEVICE);
	}

}
