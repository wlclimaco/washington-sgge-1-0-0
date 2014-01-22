package com.sensus.lc.controller.groups.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.groups.GroupAPIController;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

/**
 * The Class GroupAPIControllerTest.
 */
public class GroupAPIControllerTest extends AbstractTestBase
{

	/** The fetch all. */
	private final String FETCH_ALL = "/api/group/fetchall";

	/** The fetch. */
	private final String FETCH = "/api/group/fetch";

	/** The insert. */
	private final String INSERT = "/api/group/insert";

	/** The update. */
	private final String UPDATE = "/api/group/update";

	/** The delete. */
	private final String DELETE = "/api/group/delete";

	/** The Constant FETCH_COUNT. */
	private static final String FETCH_COUNT = "/api/group/fetchcount";

	/** The Constant INSERT_LIGHTS. */
	private static final String INSERT_LIGHTS = "/api/group/insertlights";

	/** The Constant INSERT_LIGHTS. */
	private static final String DELETE_LIGHTS = "/api/group/deletelights";

	/**
	 * Gets the group mock.
	 * 
	 * @return the group mock
	 */
	private GroupBCFMockImpl getGroupMock()
	{
		return (GroupBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(GroupAPIController.class).getGroupBCF();
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
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALL).andExpect(jsonPath("$.groups", hasSize(10)));

		// Failure situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_ALL).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
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
		// set request object
		setData("{\"action\":\"fetchById\",\"groupId\":\"39\"}");

		// Success situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.groups", hasSize(1))).andExpect(
				jsonPath("$.groups[0].id", equalTo(10)));

		// Failure situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getGroupMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.groups", nullValue()));

		// Exception situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
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
		setData("{\"group\":{\"name\":\"Group Test\",\"description\":\"\"},\"selectionPaginationIds\":[]}");

		// Success situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(INSERT).andExpect(jsonPath("$.groups", hasSize(1))).andExpect(
				jsonPath("$.groups[0].name", equalTo("Group Test")));

		// Failure situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(INSERT).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
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
		setData("{\"group\":{\"id\":\"1738\",\"name\":\"Group\",\"description\":\"\"},\"oldName\":\"Group\",\"selectionPaginationIds\":[]}");

		// Success situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(UPDATE).andExpect(jsonPath("$.groups", hasSize(1))).andExpect(
				jsonPath("$.groups[0].id", equalTo(10)));

		// Failure situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(UPDATE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(UPDATE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void delete() throws Exception
	{
		// set request object
		setData("{\"group\":{\"id\":\"1738\",\"name\":\"Group\",\"description\":\"\"},\"oldName\":\"Group\",\"selectionPaginationIds\":[]}");

		// Success situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(DELETE).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(DELETE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(DELETE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void fetchCountLightsFromGroups() throws Exception
	{
		// set request object
		setData("{\"group\":{\"id\":\"1738\",\"name\":\"Group\",\"description\":\"\"},\"oldName\":\"Group\",\"selectionPaginationIds\":[]}");

		// Success situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_COUNT).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_COUNT).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_COUNT).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Delete.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void insertLights() throws Exception
	{
		// set request object
		setData("{\"group\":{\"id\":\"619\",\"name\":\"Group Test 2\",\"description\":null,\"modelAction\":\"UPDATE\"},\"paginationAllSelected\":false,\"selectionPaginationIds\":[\"1046\"]}");

		// Success situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(INSERT_LIGHTS).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(INSERT_LIGHTS).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(INSERT_LIGHTS).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void deleteLights() throws Exception
	{
		// set request object
		setData("{\"group\":{\"id\":\"619\",\"name\":\"Group Test 2\",\"description\":null,\"modelAction\":\"UPDATE\"},\"paginationAllSelected\":false,\"selectionPaginationIds\":[\"1046\"]}");

		// Success situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(DELETE_LIGHTS).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(DELETE_LIGHTS).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(DELETE_LIGHTS).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

}
