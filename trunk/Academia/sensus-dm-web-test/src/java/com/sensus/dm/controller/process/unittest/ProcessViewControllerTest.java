package com.sensus.dm.controller.process.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.device.unittest.DeviceBCFMockImpl;
import com.sensus.dm.controller.process.ProcessViewController;
import com.sensus.dm.controller.settings.unittest.SettingsBCFMockImpl;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.SessionAuthenticationTestUtil;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;
import com.sensus.dm.controller.watermeter.unittest.WaterMeterBCFMockImpl;

/**
 * The Class ProcessViewControllerTest.
 */
public class ProcessViewControllerTest extends AbstractTestBase
{

	/** The Constant RESPONSE. */
	private static final String RESPONSE = "response";

	/** The Constant REFRESH. */
	private static final String REFRESH = "refresh";

	/** The Constant VIEW_GROUP_MAIN. */
	private static final String VIEW_PROCESS_TODAY_MAIN = "/process/process_today";

	/** The Constant VIEW_PROCESS_HISTORY_MAIN. */
	private static final String VIEW_PROCESS_LEAKREPORT_MAIN = "/process/process_leak_report";

	/** The Constant FETCH_LIST_TODAY. */
	private static final String FETCH_LIST_TODAY = "/process/tab/today";

	/** The Constant FETCH_LIST_HISTORY. */
	private static final String FETCH_LIST_HISTORY = "/process/tab/history";

	/** The Constant FETCH_LIST_LEAKREPORT. */
	private static final String FETCH_LIST_LEAKREPORT = "/process/tab/report";

	/**
	 * Gets the process mock.
	 * 
	 * @return the process mock
	 */
	private ProcessBCFMockImpl getProcessMock()
	{
		return (ProcessBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ProcessViewController.class).getProcessBCF();
	}

	/**
	 * Gets the device mock.
	 * 
	 * @return the device mock
	 */
	private DeviceBCFMockImpl getDeviceMock()
	{
		return (DeviceBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ProcessViewController.class).getDeviceBCF();
	}

	/**
	 * Gets the process type mock.
	 * 
	 * @return the process type mock
	 */
	private ProcessTypeBCFMockImpl getProcessTypeMock()
	{
		return (ProcessTypeBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ProcessViewController.class).getProcessTypeBCF();
	}

	/**
	 * Gets the settings mock.
	 * 
	 * @return the settings mock
	 */
	private SettingsBCFMockImpl getSettingsMock()
	{
		return (SettingsBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ProcessViewController.class).getSettingsBCF();
	}

	/**
	 * Gets the water meter mock.
	 * 
	 * @return the water meter mock
	 */
	private WaterMeterBCFMockImpl getWaterMeterMock()
	{
		return (WaterMeterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ProcessViewController.class).getWaterMeterBCF();
	}

	/**
	 * Fetch list today.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchListToday() throws Exception
	{

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);

		getMockMvc().perform(get(FETCH_LIST_TODAY)
				.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_PROCESS_TODAY_MAIN, RESPONSE, new String()));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST_TODAY)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_PROCESS_TODAY_MAIN, RESPONSE, new String()));
	}

	/**
	 * Fetch list history.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchListHistory() throws Exception
	{

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		getProcessTypeMock().setMode(ModeEnum.MODE_SUCCESS);
		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);

		getMockMvc().perform(get(FETCH_LIST_HISTORY)
				.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(FETCH_LIST_HISTORY, RESPONSE, new String()));

		// Success refresh situation
		getMockMvc().perform(
				get(FETCH_LIST_HISTORY).param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(FETCH_LIST_HISTORY, RESPONSE, new String()));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		getDeviceMock().setMode(ModeEnum.MODE_FAILURE);
		getProcessTypeMock().setMode(ModeEnum.MODE_FAILURE);
		getSettingsMock().setMode(ModeEnum.MODE_FAILURE);

		getMockMvc().perform(
				get(FETCH_LIST_HISTORY)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(FETCH_LIST_HISTORY, RESPONSE, new String()));
	}

	/**
	 * Fetch list repost.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchListRepost() throws Exception
	{

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		getWaterMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(get(FETCH_LIST_LEAKREPORT)
				.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_PROCESS_LEAKREPORT_MAIN, RESPONSE, new String()));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		getWaterMeterMock().setMode(ModeEnum.MODE_FAILURE);
		getSettingsMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST_LEAKREPORT)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_PROCESS_LEAKREPORT_MAIN, RESPONSE, new String()));
	}

}