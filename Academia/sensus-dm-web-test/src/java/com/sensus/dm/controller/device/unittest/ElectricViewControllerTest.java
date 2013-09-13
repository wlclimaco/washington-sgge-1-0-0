package com.sensus.dm.controller.device.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.electricdevice.ElectricViewController;
import com.sensus.dm.controller.electricmeter.unittest.ElectricMeterBCFMockImpl;
import com.sensus.dm.controller.savedsearch.unittest.CustomSearchBCFMockImpl;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.SessionAuthenticationTestUtil;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class ElectricViewControllerTest.
 */
public class ElectricViewControllerTest extends AbstractTestBase
{

	/** The Constant ELECTRICLIST. */
	private static final String ELECTRICLIST = "/electriclist";

	/** The Constant VIEW_ELECTRIC_MAIN. */
	private static final String VIEW_ELECTRIC_MAIN = "/device/electric_main";

	/**
	 * Gets the electric meter mock.
	 * 
	 * @return the electric meter mock
	 */
	private CustomSearchBCFMockImpl getCustomSearchMock()
	{
		return (CustomSearchBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ElectricViewController.class).getCustomSearchBCF();
	}

	private ElectricMeterBCFMockImpl getElectricMeterMock()
	{
		return (ElectricMeterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ElectricViewController.class).getElectricMeterBCF();
	}

	/**
	 * Load list.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void loadList() throws Exception
	{

		// Failure situation
		getElectricMeterMock().setMode(ModeEnum.MODE_FAILURE);
		getCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(ELECTRICLIST)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_ELECTRIC_MAIN, RESPONSE, new String()));

		/* Success situations */
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		getCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(ELECTRICLIST)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_ELECTRIC_MAIN, RESPONSE, new String()));

		// Refresh situation
		getMockMvc().perform(
				get(ELECTRICLIST).param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(VIEW_ELECTRIC_MAIN, RESPONSE, new String()));

		// Saved situation
		getMockMvc().perform(
				get(ELECTRICLIST).param("saved", "true")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(VIEW_ELECTRIC_MAIN, RESPONSE, new String()));

		/* Default filters situations */
		// Electric Meter
		getMockMvc().perform(
				get(ELECTRICLIST)
						.param("tag", "1|2|")
						.param("group", "1|2|")
						.param("processId", "1")
						.param("device_type", "ELECTRIC_METER|")
						.param("quarantine", "true|")
						.param("lifecycle_state", "INSTALLED|")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_ELECTRIC_MAIN, RESPONSE, new String()));

		// HAN Device
		getMockMvc().perform(
				get(ELECTRICLIST)
						.param("device_type", "HAN_DEVICE|")
						.param("device_subtype", "IHD|")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_ELECTRIC_MAIN, RESPONSE, new String()));

		// LCM
		getMockMvc().perform(
				get(ELECTRICLIST)
						.param("device_type", "LCM|")
						.param("device_subtype", "FLEXNET_LCM|")
						.param("alarm", "BACK_FLOW|BROWN_OUT|")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_ELECTRIC_MAIN, RESPONSE, new String()));

		// Invalid Filter
		getMockMvc().perform(
				get(ELECTRICLIST)
						.param("device_type", "INVALID_DEVICE_TYPE_ENUM|")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, isEmptyOrNullString()))
				.equals(new ModelAndView(VIEW_ELECTRIC_MAIN, RESPONSE, new String()));

	}
}
