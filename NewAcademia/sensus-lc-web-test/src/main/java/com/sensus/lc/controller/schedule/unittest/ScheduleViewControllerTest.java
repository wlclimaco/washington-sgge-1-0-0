package com.sensus.lc.controller.schedule.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.schedule.ScheduleViewController;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.SessionAuthenticationTestUtil;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

/**
 * The Class ScheduleViewControllerTest.
 */
public class ScheduleViewControllerTest extends AbstractTestBase
{

	/** The Constant FETCH. */
	private static final String FETCH_ALL = "/schedule";

	/** The Constant FETCH. */
	private static final String SCHEDULE_MAIN = "/schedule/schedule_main";

	/** The Constant SCHEDULE_OFFSET_CREATE. */
	private static final String SCHEDULE_OFFSET_CREATE = "/schedule/schedule_create_offset";

	/** The Constant SCHEDULE_EVENT_CREATE. */
	private static final String SCHEDULE_EVENT_CREATE = "/schedule/schedule_create_event";

	/** The Constant RESPONSE. */
	private static final String RESPONSE = "response";

	/** The Constant REFRESH. */
	private static final String REFRESH = "refresh";

	/** The Constant FETCH_SCHEDULE_EVENT. */
	private static final String FETCH_SCHEDULE_EVENT = "/systemintelligence/schedule/insertOffset?id=1";

	/** The Constant FETCH_SCHEDULE_OFFSET. */
	private static final String FETCH_SCHEDULE_OFFSET = "/systemintelligence/schedule/insertOffset?id=1";

	/** The Constant INSERT_EVENT. */
	private static final String INSERT_EVENT = "/systemintelligence/schedule/insertEvent?id=5";

	/**
	 * Gets the schedule mock.
	 * 
	 * @return the schedule mock
	 */
	private ScheduleBCFMockImpl getScheduleMock()
	{
		return (ScheduleBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ScheduleViewController.class).getScheduleBCF();
	}

	/**
	 * Fetch.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchAll() throws Exception
	{

		// Success situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_ALL)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(SCHEDULE_MAIN, RESPONSE, new String()));

		// Failure situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_ALL)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(SCHEDULE_MAIN));

		// Success refresh situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_ALL).param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(SCHEDULE_MAIN, RESPONSE, new String()));

	}

	/**
	 * Fetch update.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchUpdateOffset() throws Exception
	{

		// Success situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_SCHEDULE_OFFSET)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(SCHEDULE_OFFSET_CREATE, RESPONSE, new String()));

		// Failure situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_SCHEDULE_OFFSET)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(SCHEDULE_OFFSET_CREATE, RESPONSE, new String()));

	}

	/**
	 * Fetch update.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchUpdateEvent() throws Exception
	{

		// Success situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_SCHEDULE_EVENT)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(SCHEDULE_EVENT_CREATE, RESPONSE, new String()));

		// Failure situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_SCHEDULE_EVENT)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(SCHEDULE_EVENT_CREATE));

	}

	@Test
	public void loadListEvent() throws Exception
	{
		// Success situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(INSERT_EVENT)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(SCHEDULE_EVENT_CREATE, RESPONSE, new String()));

		// Failure situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(INSERT_EVENT)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(SCHEDULE_EVENT_CREATE));

		// Success refresh situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(INSERT_EVENT).param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(SCHEDULE_MAIN, RESPONSE, new String()));

	}

}