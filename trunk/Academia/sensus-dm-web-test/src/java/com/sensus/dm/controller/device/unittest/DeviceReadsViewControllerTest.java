package com.sensus.dm.controller.device.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.device.detail.DeviceReadsViewController;
import com.sensus.dm.controller.deviceoperation.unittest.DeviceReadingBCFMockImpl;
import com.sensus.dm.controller.electricmeter.unittest.ElectricMeterBCFMockImpl;
import com.sensus.dm.controller.gasmeter.GasViewController;
import com.sensus.dm.controller.gasmeter.unittest.GasMeterBCFMockImpl;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.SessionAuthenticationTestUtil;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;
import com.sensus.dm.controller.watermeter.WaterViewController;
import com.sensus.dm.controller.watermeter.unittest.WaterMeterBCFMockImpl;

/**
 * The Class DeviceReadsViewControllerTest.
 */
public class DeviceReadsViewControllerTest extends AbstractTestBase
{

	/** The Constant RESPONSE. */
	private static final String RESPONSE = "response";

	/** The Constant REFRESH. */
	private static final String REFRESH = "refresh";

	/** The Constant FETCH_LIST_INTERVALREADS. */
	public static final String FETCH_LIST_INTERVALREADS = "/device/tab/intervalReads";

	/** The Constant FETCH_LIST_LOADPROFILE. */
	public static final String FETCH_LIST_LOADPROFILE = "/device/tab/loadProfiles";

	/** The Constant FETCH_LIST_SNAPSHOT. */
	public static final String FETCH_LIST_SNAPSHOT = "/device/tab/snapshot";

	/** The Constant FETCH_LIST_DATAREAD. */
	public static final String FETCH_LIST_DATAREAD = "/device/tab/readData";

	/** The Constant FETCH_LIST_TOUREAD. */
	public static final String FETCH_LIST_TOUREAD = "/device/tab/tou";

	/** The Constant VIEW_DEVICE_MAIN. */
	private static final String VIEW_DEVICE_MAIN = "/device_detail/device_detail_readings";

	/**
	 * Gets the device reading mock.
	 * 
	 * @return the device reading mock
	 */
	private DeviceReadingBCFMockImpl getDeviceReadingMock()
	{
		return (DeviceReadingBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DeviceReadsViewController.class).getDeviceReadingBCF();
	}

	/**
	 * Gets the electric meter mock.
	 * 
	 * @return the electric meter mock
	 */
	private ElectricMeterBCFMockImpl getElectricMeterMock()
	{
		return (ElectricMeterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DeviceReadsViewController.class).getElectricMeterBCF();
	}

	/**
	 * Gets the water meter mock.
	 * 
	 * @return the water meter mock
	 */
	private WaterMeterBCFMockImpl getWaterMeterMock()
	{
		return (WaterMeterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(WaterViewController.class).getWaterMeterBCF();
	}

	/**
	 * Gets the gas meter mock.
	 * 
	 * @return the gas meter mock
	 */
	private GasMeterBCFMockImpl getGasMeterMock()
	{
		return (GasMeterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(GasViewController.class).getGasMeterBCF();
	}

	/**
	 * Load list interval reads.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void loadListIntervalReads() throws Exception
	{

		// Success situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_SUCCESS);
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);

		getMockMvc().perform(
				get(FETCH_LIST_INTERVALREADS)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_DEVICE_MAIN, RESPONSE, new String()));

		// Success refresh situation
		getMockMvc().perform(
				get(FETCH_LIST_INTERVALREADS)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX")
						.param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(VIEW_DEVICE_MAIN, RESPONSE, new String()));

		// Failure situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_FAILURE);
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);

		getMockMvc().perform(
				get(FETCH_LIST_INTERVALREADS)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_DEVICE_MAIN, RESPONSE, new String()));
	}

	/**
	 * Load list load profiles.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void loadListLoadProfiles() throws Exception
	{

		// Success situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_SUCCESS);
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);

		getMockMvc().perform(
				get(FETCH_LIST_LOADPROFILE)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_DEVICE_MAIN, RESPONSE, new String()));

		// Success refresh situation
		getMockMvc().perform(
				get(FETCH_LIST_LOADPROFILE)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX")
						.param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(VIEW_DEVICE_MAIN, RESPONSE, new String()));

		// Failure situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_FAILURE);
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);

		getMockMvc().perform(
				get(FETCH_LIST_LOADPROFILE)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_DEVICE_MAIN, RESPONSE, new String()));
	}

	/**
	 * Load list snapshot.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void loadListSnapshot() throws Exception
	{

		// Success situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_SUCCESS);
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);

		getMockMvc().perform(
				get(FETCH_LIST_SNAPSHOT)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_DEVICE_MAIN, RESPONSE, new String()));

		// Success refresh situation
		getMockMvc().perform(
				get(FETCH_LIST_SNAPSHOT)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX")
						.param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(VIEW_DEVICE_MAIN, RESPONSE, new String()));

		// Failure situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_FAILURE);
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);

		getMockMvc().perform(
				get(FETCH_LIST_SNAPSHOT)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_DEVICE_MAIN, RESPONSE, new String()));
	}

	/**
	 * Load list data reads.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void loadListDataReads() throws Exception
	{

		// Success situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_SUCCESS);
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		getWaterMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		getGasMeterMock().setMode(ModeEnum.MODE_SUCCESS);

		getMockMvc().perform(
				get(FETCH_LIST_DATAREAD)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_DEVICE_MAIN, RESPONSE, new String()));

		getMockMvc().perform(
				get(FETCH_LIST_DATAREAD)
						.param("id", "234561296")
						.param("deviceType", "WATER_METER")
						.param("typeEnum", "RX")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.WATER)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_DEVICE_MAIN, RESPONSE, new String()));

		getMockMvc().perform(
				get(FETCH_LIST_DATAREAD)
						.param("id", "1327")
						.param("deviceType", "GAS_METER")
						.param("typeEnum", "RX")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.GAS)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_DEVICE_MAIN, RESPONSE, new String()));

		// Success refresh situation
		getMockMvc().perform(
				get(FETCH_LIST_DATAREAD)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX")
						.param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(VIEW_DEVICE_MAIN, RESPONSE, new String()));

		// Failure situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_FAILURE);
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST_DATAREAD)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_DEVICE_MAIN, RESPONSE, new String()));
	}

	/**
	 * Load list tou reads.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void loadListTouReads() throws Exception
	{

		// Success situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_SUCCESS);
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);

		getMockMvc().perform(
				get(FETCH_LIST_TOUREAD)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_DEVICE_MAIN, RESPONSE, new String()));

		// Success refresh situation
		getMockMvc().perform(
				get(FETCH_LIST_TOUREAD)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX").param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(VIEW_DEVICE_MAIN, RESPONSE, new String()));

		// Failure situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_FAILURE);
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);

		getMockMvc().perform(
				get(FETCH_LIST_TOUREAD)
						.param("id", "1001")
						.param("deviceType", "ELECTRIC_METER")
						.param("typeEnum", "RX")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_DEVICE_MAIN, RESPONSE, new String()));
	}

}