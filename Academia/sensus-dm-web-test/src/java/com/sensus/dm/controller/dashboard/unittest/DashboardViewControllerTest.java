package com.sensus.dm.controller.dashboard.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.dashboard.DashboardViewController;
import com.sensus.dm.controller.device.unittest.DeviceBCFMockImpl;
import com.sensus.dm.controller.process.unittest.ProcessBCFMockImpl;
import com.sensus.dm.controller.savedsearch.unittest.CustomSearchBCFMockImpl;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.SessionAuthenticationTestUtil;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;
import com.sensus.dm.controller.watermeter.unittest.WaterMeterBCFMockImpl;

/**
 * The Class DashboardViewControllerTest.
 */
public class DashboardViewControllerTest extends AbstractTestBase
{

	/** The Constant FETCH_LIST_DASHBOARD. */
	private static final String FETCH_LIST_DASHBOARD = "/dashboard";

	/** The Constant VIEW_DASHBOARD_MAIN. */
	private static final String VIEW_DASHBOARD_MAIN = "/dashboard/dashboard_main";

	/**
	 * Gets the process mock.
	 * 
	 * @return the process mock
	 */
	private ProcessBCFMockImpl getProcessMock()
	{
		return (ProcessBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DashboardViewController.class).getProcessBCF();
	}

	/**
	 * Gets the device mock.
	 * 
	 * @return the device mock
	 */
	private DeviceBCFMockImpl getDeviceMock()
	{
		return (DeviceBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DashboardViewController.class).getDeviceBCF();
	}

	/**
	 * Gets the water meter mock.
	 * 
	 * @return the water meter mock
	 */
	private WaterMeterBCFMockImpl getWaterMeterMock()
	{
		return (WaterMeterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DashboardViewController.class).getWaterMeterBCF();
	}

	/**
	 * Gets the custom search mock.
	 * 
	 * @return the custom search mock
	 */
	private CustomSearchBCFMockImpl getCustomSearchMock()
	{
		return (CustomSearchBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DashboardViewController.class).getCustomSearchBCF();
	}

	/**
	 * Load.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void load() throws Exception
	{

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		getCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		getWaterMeterMock().setMode(ModeEnum.MODE_SUCCESS);

		// Electric
		getMockMvc().perform(get(FETCH_LIST_DASHBOARD)
				.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_DASHBOARD_MAIN, RESPONSE, new String()));

		// Gas
		getMockMvc().perform(get(FETCH_LIST_DASHBOARD)
				.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.GAS)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_DASHBOARD_MAIN, RESPONSE, new String()));

		// Water
		getMockMvc().perform(get(FETCH_LIST_DASHBOARD)
				.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.WATER)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_DASHBOARD_MAIN, RESPONSE, new String()));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		getCustomSearchMock().setMode(ModeEnum.MODE_FAILURE);
		getWaterMeterMock().setMode(ModeEnum.MODE_FAILURE);

		getMockMvc().perform(
				get(FETCH_LIST_DASHBOARD)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_DASHBOARD_MAIN, RESPONSE, new String()));
	}

}