package com.sensus.dm.controller.device.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.device.detail.HistoryDeviceViewController;
import com.sensus.dm.controller.process.unittest.ProcessBCFMockImpl;
import com.sensus.dm.controller.process.unittest.ProcessTypeBCFMockImpl;
import com.sensus.dm.controller.settings.unittest.SettingsBCFMockImpl;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.SessionAuthenticationTestUtil;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class HistoryDeviceViewControllerTest.
 */
public class HistoryDeviceViewControllerTest extends AbstractTestBase
{

	/** The Constant RESPONSE. */
	private static final String RESPONSE = "response";

	/** The Constant REFRESH. */
	private static final String REFRESH = "refresh";

	/** The Constant FETCH_LIST_History. */
	public static final String FETCH_LIST_History = "/device/tab/history";

	/** The Constant VIEW_DEVICE_History_MAIN. */
	private static final String VIEW_DEVICE_History_MAIN = "/device_detail/device_detail_history";

	/**
	 * Gets the process mock.
	 * 
	 * @return the process mock
	 */
	private ProcessBCFMockImpl getProcessMock()
	{
		return (ProcessBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(HistoryDeviceViewController.class).getProcessBCF();
	}

	/**
	 * Gets the process type mock.
	 * 
	 * @return the process type mock
	 */
	private ProcessTypeBCFMockImpl getProcessTypeMock()
	{
		return (ProcessTypeBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(HistoryDeviceViewController.class).getProcessTypeBCF();
	}

	/**
	 * Gets the settings mock.
	 * 
	 * @return the settings mock
	 */
	private SettingsBCFMockImpl getSettingsMock()
	{
		return (SettingsBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(HistoryDeviceViewController.class).getSettingsBCF();
	}

	/**
	 * Gets the device mock.
	 * 
	 * @return the device mock
	 */
	private DeviceBCFMockImpl getDeviceMock()
	{
		return (DeviceBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(HistoryDeviceViewController.class).getDeviceBCF();
	}

	/**
	 * Load list interval reads.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void load() throws Exception
	{

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		getProcessTypeMock().setMode(ModeEnum.MODE_SUCCESS);
		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);

		getMockMvc().perform(
				get(FETCH_LIST_History)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_DEVICE_History_MAIN, RESPONSE, new String()));

		// Success refresh situation
		getMockMvc().perform(
				get(FETCH_LIST_History)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX")
						.param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(VIEW_DEVICE_History_MAIN, RESPONSE, new String()));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		getProcessTypeMock().setMode(ModeEnum.MODE_SUCCESS);
		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);

		getMockMvc().perform(
				get(FETCH_LIST_History)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_DEVICE_History_MAIN, RESPONSE, new String()));
	}

}