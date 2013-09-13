package com.sensus.dm.controller.devicedetail.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.device.detail.DeviceDetailViewController;
import com.sensus.dm.controller.device.unittest.DeviceBCFMockImpl;
import com.sensus.dm.controller.deviceoperation.unittest.DeviceReadingBCFMockImpl;
import com.sensus.dm.controller.electricmeter.unittest.ElectricMeterBCFMockImpl;
import com.sensus.dm.controller.gasmeter.unittest.GasMeterBCFMockImpl;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.SessionAuthenticationTestUtil;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;
import com.sensus.dm.controller.watermeter.unittest.WaterMeterBCFMockImpl;

/**
 * The Class DeviceDetailViewControllerTest.
 */
public class DeviceDetailViewControllerTest extends AbstractTestBase
{

	/** The Constant DEVICE_DETAIL. */
	private static final String DEVICE_DETAIL = "/device/detail";

	/** The Constant VIEW_DETAIL_MAIN. */
	private static final String VIEW_DETAIL_MAIN = "/device_detail/device_detail_main";

	/**
	 * Gets the electric meter mock.
	 * 
	 * @return the electric meter mock
	 */
	private ElectricMeterBCFMockImpl getElectricMeterMock()
	{
		return (ElectricMeterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DeviceDetailViewController.class).getElectricMeterBCF();
	}

	/**
	 * Gets the water meter mock.
	 * 
	 * @return the water meter mock
	 */
	private WaterMeterBCFMockImpl getWaterMeterMock()
	{
		return (WaterMeterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DeviceDetailViewController.class).getWaterMeterBCF();
	}

	/**
	 * Gets the gas meter mock.
	 * 
	 * @return the gas meter mock
	 */
	private GasMeterBCFMockImpl getGasMeterMock()
	{
		return (GasMeterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DeviceDetailViewController.class).getGasMeterBCF();
	}

	/**
	 * Gets the device reading mock.
	 * 
	 * @return the device reading mock
	 */
	private DeviceReadingBCFMockImpl getDeviceReadingMock()
	{
		return (DeviceReadingBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DeviceDetailViewController.class).getDeviceReadingBCF();
	}

	/**
	 * Gets the device mock.
	 * 
	 * @return the device mock
	 */
	private DeviceBCFMockImpl getDeviceMock()
	{
		return (DeviceBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DeviceDetailViewController.class).getDeviceBCF();
	}

	/**
	 * Load.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void load() throws Exception
	{

		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);

		/* Failure situations */
		// RequestParam invalid
		getMockMvc().perform(
				get(DEVICE_DETAIL)
						.param("id", "1")
						.param("deviceType", "INVALID_DEVICE_TYPE_ENUM")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isBadRequest());

		// RequestParam null
		getMockMvc().perform(
				get(DEVICE_DETAIL)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isBadRequest());

		// Controller throws an exception
		getDeviceReadingMock().setMode(ModeEnum.MODE_FAILURE);
		getElectricMeterMock().setMode(ModeEnum.MODE_FAILURE);

		getMockMvc().perform(
				get(DEVICE_DETAIL)
						.param("id", "2")
						.param("deviceType", "ELECTRIC_METER")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_DETAIL_MAIN, RESPONSE, new String()));

		/* Success situations */
		getDeviceReadingMock().setMode(ModeEnum.MODE_SUCCESS);

		/* Electric Service */
		// ELECTRIC
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);

		getMockMvc().perform(
				get(DEVICE_DETAIL)
						.param("id", "2")
						.param("deviceType", "ELECTRIC_METER")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.andExpect(model().size(5))
				.equals(new ModelAndView(VIEW_DETAIL_MAIN, RESPONSE, new String()));

		// HAN
		getMockMvc().perform(
				get(DEVICE_DETAIL)
						.param("id", "2")
						.param("deviceType", "HAN_DEVICE")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.andExpect(model().size(5))
				.equals(new ModelAndView(VIEW_DETAIL_MAIN, RESPONSE, new String()));

		// LCM
		getMockMvc().perform(
				get(DEVICE_DETAIL)
						.param("id", "2")
						.param("deviceType", "LCM")
						.param("deviceType", "LCM")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.andExpect(model().size(5))
				.equals(new ModelAndView(VIEW_DETAIL_MAIN, RESPONSE, new String()));

		// FLEXNET_LCM
		getMockMvc().perform(
				get(DEVICE_DETAIL)
						.param("id", "2")
						.param("deviceType", "LCM")
						.param("deviceType", "FLEXNET_LCM")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.andExpect(model().size(5))
				.equals(new ModelAndView(VIEW_DETAIL_MAIN, RESPONSE, new String()));

		/* Water Service */
		// WATER_METER
		getWaterMeterMock().setMode(ModeEnum.MODE_SUCCESS);

		getMockMvc().perform(
				get(DEVICE_DETAIL)
						.param("id", "2")
						.param("deviceType", "WATER_METER")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.WATER)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.andExpect(model().size(5))
				.equals(new ModelAndView(VIEW_DETAIL_MAIN, RESPONSE, new String()));

		/* Gas Service */
		// GAS_METER
		getGasMeterMock().setMode(ModeEnum.MODE_SUCCESS);

		getMockMvc().perform(
				get(DEVICE_DETAIL)
						.param("id", "2")
						.param("deviceType", "GAS_METER")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.GAS)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.andExpect(model().size(5))
				.equals(new ModelAndView(VIEW_DETAIL_MAIN, RESPONSE, new String()));
	}
}
