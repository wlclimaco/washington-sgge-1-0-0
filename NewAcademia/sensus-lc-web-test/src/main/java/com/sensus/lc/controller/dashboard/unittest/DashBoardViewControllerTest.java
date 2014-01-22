package com.sensus.lc.controller.dashboard.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.analytics.unittest.AnalyticsBCFMockImpl;
import com.sensus.lc.controller.dashboard.DashBoardViewController;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.SessionAuthenticationTestUtil;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

/**
 * The Class GroupViewControllerTest.
 */
public class DashBoardViewControllerTest extends AbstractTestBase
{

	/** The Constant GROUP_GROUP_MAIN. */
	private static final String DASHBOARD_MAIN = "/dashboard/dashboard_main";

	/** The Constant POWER_FAILURE. */
	private static final String POWER_FAILURE = "powerFailureResponse";

	/** The Constant LAMP_FAILURE. */
	private static final String LAMP_FAILURE = "lampFailureResponse";

	/** The Constant HEADER_RESPONSE. */
	private static final String HEADER_RESPONSE = "dashBoardHeaderResponse";

	/** The Constant HEADER_RESPONSE. */
	private static final String ALERTS_RESPONSE = "alertsResponse";

	/** The Constant RESUME_RESPONSE. */
	private static final String RESUME_RESPONSE = "resumeResponse";

	/** The fetch all. */
	private final String FETCH_LIST = "/dashboard";

	/**
	 * Gets the group mock.
	 * 
	 * @return the group mock
	 */
	private AnalyticsBCFMockImpl getAnalyticsMock()
	{
		return (AnalyticsBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DashBoardViewController.class).getAnalyticsBCF();
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
		getAnalyticsMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(POWER_FAILURE, containsString("\"operationSuccess\":true")))
				.andExpect(model().attribute(LAMP_FAILURE, containsString("\"operationSuccess\":true")))
				.andExpect(model().attribute(HEADER_RESPONSE, containsString("\"operationSuccess\":true")))
				.andExpect(model().attribute(ALERTS_RESPONSE, containsString("\"operationSuccess\":true")))
				.andExpect(model().attribute(RESUME_RESPONSE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(DASHBOARD_MAIN, POWER_FAILURE, new String()));

		// Failure situation
		getAnalyticsMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc()
				.perform(
						get(FETCH_LIST)
								.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(POWER_FAILURE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.andExpect(model().attribute(LAMP_FAILURE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.andExpect(model().attribute(HEADER_RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.andExpect(model().attribute(ALERTS_RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.andExpect(model().attribute(RESUME_RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(DASHBOARD_MAIN, POWER_FAILURE, new String()));

	}
}