package com.sensus.lc.controller.process.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.process.ProcessAPIController;
import com.sensus.lc.controller.user.UserAPIController;
import com.sensus.lc.controller.user.unittest.UserBCFMockImpl;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.SessionAuthenticationTestUtil;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

/**
 * The Class GroupViewControllerTest.
 */
public class ProcessViewControllerTest extends AbstractTestBase
{

	/** The Constant VIEW_LIGHT_MAIN. */
	private static final String VIEW_PROCESS_MAIN = "/process/process_main";

	/** The Constant GROUPS. */
	private static final String RESPONSE = "response";

	/** The Constant REFRESH. */
	private static final String REFRESH = "refresh";

	/** The fetch all. */
	private final String FETCH_LIST = "/process";

	private ProcessBCFMockImpl getProcessMock()
	{
		return (ProcessBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ProcessAPIController.class).getProcessBCF();
	}

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
	 * Fetch list.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void loadList() throws Exception
	{

		// Success situation
		getUserMock().setMode(ModeEnum.MODE_SUCCESS);
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(VIEW_PROCESS_MAIN, RESPONSE, new String()));

		// Failure situation
		getUserMock().setMode(ModeEnum.MODE_FAILURE);
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_PROCESS_MAIN, RESPONSE, new String()));

		// Success refresh situation
		getUserMock().setMode(ModeEnum.MODE_SUCCESS);
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST).param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(VIEW_PROCESS_MAIN, RESPONSE, new String()));

	}

}