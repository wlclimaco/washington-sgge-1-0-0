package com.sensus.mlc.wui.user.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.mlc.wui.user.UserAPIController;
import com.sensus.mlc.wui.util.AbstractTestBase;
import com.sensus.mlc.wui.util.ModeEnum;
import com.sensus.mlc.wui.util.TestMessageEnum;

public class UserAPIControllerTest extends AbstractTestBase
{

	/** The Constant FETCH. */
	private static final String FETCH = "/api/user/fetch";

	private static final String FETCH_ALL = "/api/user/fetchAll";

	private static final String DELETE = "/api/user/delete";

	private static final String INSERT = "/api/user/insert";

	private static final String UPDATE = "/api/user/update";

	/**
	 * Gets the analytics mock.
	 * 
	 * @return the analytics mock
	 */
	private UserBCFMockImpl getUserMock()
	{
		return (UserBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(UserAPIController.class).getUserBCF();
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
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":25,\"sortExpressions\":[{\"field\":\"user_name\",\"direction\":\"Ascending\"}],\"action\":\"table\"}");

		// Success situation
		getUserMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALL).andExpect(jsonPath("$.users", hasSize(25)));

		// Failure situation
		getUserMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_ALL).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getUserMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_ALL).andExpect(jsonPath("$.users", nullValue()));

		// Exception situation
		getUserMock().setMode(ModeEnum.MODE_EXCEPTION);
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
		setData("{\"id\":\"25\"}");

		// Success situation
		getUserMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.user.id", equalTo(25)));

		// Failure situation
		getUserMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getUserMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.user", nullValue()));

		// Exception situation
		getUserMock().setMode(ModeEnum.MODE_EXCEPTION);
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
		// set request object
		setData("{\"selectionPaginationIds\":[89]}");

		// Success situation
		getUserMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(DELETE).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getUserMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(DELETE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getUserMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(DELETE).andExpect(jsonPath("$.messageInfoList[0].code",
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
		setData("{\"user\":{\"userName\":\"sensususer\",\"firstName\":\"Sensus\",\"role\":\"ROLE_Role.Admin\",\"email\":\"sensususer@sensus.com\",\"groups\":[],\"lastName\":\"User\",\"password\":\"Sensus123$\",\"allLightsAuth\":\"true\"},\"paginationAllSelected\":null,\"selectionPaginationIds\":null}");

		// Success situation
		getUserMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(INSERT).andExpect(jsonPath("$.operationSuccess", equalTo(true))).andExpect(
				jsonPath("$.responseTime", notNullValue()));

		// Failure situation
		getUserMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(INSERT).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getUserMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(INSERT).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Insert.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void update() throws Exception
	{
		// set request object
		setData("{\"user\":{\"id\":\"25\",\"userName\":\"qatuser\",\"firstName\":\"Qat\",\"role\":\"ROLE_Role.Admin\",\"email\":\"qatuser@sensus.com\",\"groups\":[],\"lastName\":\"Sensus\",\"password\":null,\"allLightsAuth\":\"true\"},\"paginationAllSelected\":null,\"selectionPaginationIds\":null}");

		// Success situation
		getUserMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(UPDATE).andExpect(jsonPath("$.operationSuccess", equalTo(true))).andExpect(
				jsonPath("$.responseTime", notNullValue()));

		// Failure situation
		getUserMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(UPDATE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getUserMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(UPDATE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

}
