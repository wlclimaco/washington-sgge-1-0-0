package com.sensus.lc.controller.user.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.groups.unittest.GroupBCFMockImpl;
import com.sensus.lc.controller.user.UserViewController;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.SessionAuthenticationTestUtil;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

/**
 * The Class UserViewControllerTest.
 */
public class UserViewControllerTest extends AbstractTestBase
{

	/** The Constant USER_MAIN. */
	private static final String USER_MAIN = "/user/user_main";

	/** The Constant RESPONSE. */
	private static final String RESPONSE = "response";

	/** The fetch all. */
	private final String FETCH_LIST = "/user";

	private final String REFRESH = "refresh";

	/** The fetch. */
	private final String FETCH_UPDATE = "/user/update?userId=97";

	/** The Constant USER_USER_CREATE. */
	private static final String USER_USER_CREATE = "/user/user_create";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "UserViewController";

	/**
	 * Gets the user mock.
	 * 
	 * @return the user mock
	 */
	private UserBCFMockImpl getUserMock()
	{
		return (UserBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(UserViewController.class).getUserBCF();
	}

	private GroupBCFMockImpl getGroupMock()
	{
		return (GroupBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(UserViewController.class).getGroupBCF();
	}

	/**
	 * Fetch list.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchList() throws Exception
	{

		// Success situation
		getUserMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(USER_MAIN, RESPONSE, new String()));

		// Failure situation
		getUserMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(USER_MAIN, RESPONSE, new String()));

		// Success refresh situation
		getUserMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST).param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(USER_MAIN, RESPONSE, new String()));

	}

	/**
	 * Fetch update.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void update() throws Exception
	{
		// set request object
		setData("{\"user\":{\"id\":\"25\",\"userName\":\"anna\",\"firstName\":\"Anna\",\"role\":\"ROLE_Role.Admin\",\"email\":\"anna@anna.com\",\"groups\":[],\"lastName\":\"huynh\",\"password\":null,\"allLightsAuth\":\"true\"},\"paginationAllSelected\":null,\"selectionPaginationIds\":null}");

		// Success situation
		getUserMock().setMode(ModeEnum.MODE_SUCCESS);
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_UPDATE)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(USER_USER_CREATE, RESPONSE, new String()));

		// Failure situation
		getUserMock().setMode(ModeEnum.MODE_FAILURE);
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_UPDATE)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(USER_USER_CREATE, RESPONSE, new String()));

	}

}