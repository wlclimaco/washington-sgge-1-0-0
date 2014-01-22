package com.sensus.lc.controller.analytics.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.analytics.AnalyticsViewController;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.SessionAuthenticationTestUtil;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

public class AnalyticsViewControllerTest extends AbstractTestBase
{

	private static final String ANALYTICS_MAIN = "/analytics/analytics_main";

	private static final String RESPONSE = "response";

	private static final String RESPONSE_TABLE = "response_table";

	private final String FETCH_LIST_ALARM = "/analytics/?tb=&ty=1&g=&di=&de=&dt=&dv=1";

	private final String FETCH_LIST_WARNING = "/analytics/?tb=&ty=2&g=&di=&de=&dt=&dv=1";

	private final String FETCH_LIST_INSTALLED = "/analytics/?tb=&ty=3&g=&di=&de=&dt=&dv=1";

	private final String FETCH_LIST_ECOMODE_CONSUMPTION = "/analytics/?tb=&ty=4&g=&di=&de=&dt=&dv=1";

	private final String FETCH_LIST_LIGHTING_ECOMODE = "/analytics/?tb=&ty=5&g=&di=&de=&dt=&dv=1";

	private final String FETCH_LIST_ECOMODE_CARBON_CREDITS = "/analytics/?tb=&ty=6&g=&di=&de=&dt=&dv=1";

	private final String FETCH_LIST_ONE_DAY = "/analytics/?tb=&ty=1&g=&di=&de=&dt=1d&dv=1";

	private final String FETCH_LIST_ONE_MONTH = "/analytics/?tb=&ty=1&g=&di=&de=&dt=1m&dv=1";

	private final String FETCH_LIST_ZERO = "/analytics/?tb=&ty=1&g=&di=&de=&dt=0&dv=1";

	private static final String REFRESH = "refresh";

	@Test
	public void loadList() throws Exception
	{
		/** ALARM SITUATION */
		// Success situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST_ALARM)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		// Failure situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST_ALARM)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		/** INSTALLED SITUATION */
		// Success situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST_INSTALLED)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		// Failure situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST_INSTALLED)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		/** WARNING SITUATION */
		// Success situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST_WARNING)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		// Failure situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST_WARNING)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		/** ECOMODE CONSUMPTION SITUATION */
		// Success situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST_ECOMODE_CONSUMPTION)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		// Failure situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST_ECOMODE_CONSUMPTION)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		/** LIGHTING ECOMODE SITUATION */
		// Success situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST_LIGHTING_ECOMODE)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		// Failure situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST_LIGHTING_ECOMODE)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		/** ECOMODE CARBON CREDITS SITUATION */
		// Success situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST_ECOMODE_CARBON_CREDITS)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		// Failure situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST_ECOMODE_CARBON_CREDITS)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		/** ONE DAY SITUATION */
		// Success situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST_ONE_DAY)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		// Failure situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST_ONE_DAY)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		/** ONE MONTH SITUATION */
		// Success situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST_ONE_MONTH)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		// Failure situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST_ONE_MONTH)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		/** OTHERS SITUATIONS */
		// Success situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST_ZERO)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		// Failure situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST_ZERO)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE_TABLE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

		// PRELOAD situation
		getAnalyticsBCFMockImpl().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST_ALARM).param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(ANALYTICS_MAIN, RESPONSE, new String()));

	}

	private AnalyticsBCFMockImpl getAnalyticsBCFMockImpl()
	{
		return (AnalyticsBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(AnalyticsViewController.class).getAnalyticsBCF();
	}
}