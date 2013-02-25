package com.sensus.mlc.wui.groups.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.mlc.wui.groups.GroupAPIController;
import com.sensus.mlc.wui.util.AbstractTestBase;
import com.sensus.mlc.wui.util.ModeEnum;
import com.sensus.mlc.wui.util.TestMessageEnum;

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
		performTest(FETCH_ALL).andExpect(jsonPath("$.groups", hasSize(25)));

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
				jsonPath("$.groups[0].id", equalTo(39)));

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
		setData("{\"groupName\":\"Group 1\",\"groupDescription\":\"a\"}");

		// Success situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(INSERT).andExpect(jsonPath("$.groups", hasSize(1))).andExpect(
				jsonPath("$.groups[0].name", equalTo("Group 10")));

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
		setData("{\"groupName\":\"Group 1\",\"groupNameOld\":\"000a1\",\"groupDescription\":\"teste\",\"groupId\":\"39\"}");

		// Success situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(UPDATE).andExpect(jsonPath("$.groups", hasSize(1))).andExpect(
				jsonPath("$.groups[0].id", equalTo(39)));

		// Failure situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(UPDATE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
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
		// set request object
		setData("{\"isAllRows\":\"true\",\"selectionPaginationIds\":[39]}");

		// Success situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(DELETE).andExpect(jsonPath("$.groups", hasSize(1))).andExpect(
				jsonPath("$.groups[0].id", equalTo(39)));

		// Failure situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(DELETE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(DELETE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

}
