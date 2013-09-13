package com.sensus.dm.controller.watermeter.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.savedsearch.unittest.CustomSearchBCFMockImpl;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.SessionAuthenticationTestUtil;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;
import com.sensus.dm.controller.watermeter.WaterViewController;

public class WaterViewControllerTest extends AbstractTestBase
{
	/** The Constant WATERLIST. */
	private static final String WATERLIST = "/waterlist";

	/** The Constant VIEW_WATER_MAIN. */
	private static final String VIEW_WATER_MAIN = "/device/water_main";

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

	private CustomSearchBCFMockImpl getCustomSearchMock()
	{
		return (CustomSearchBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(WaterViewController.class).getCustomSearchBCF();
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
		getWaterMeterMock().setMode(ModeEnum.MODE_FAILURE);
		getCustomSearchMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(WATERLIST)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.WATER)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_WATER_MAIN, RESPONSE, new String()));

		/* Success situations */
		getWaterMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		getCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(WATERLIST)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.WATER)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_WATER_MAIN, RESPONSE, new String()));

		// Refresh situation
		getMockMvc().perform(
				get(WATERLIST).param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.WATER)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(VIEW_WATER_MAIN, RESPONSE, new String()));

		// Saved situation
		getMockMvc().perform(
				get(WATERLIST).param("saved", "true")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.WATER)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(VIEW_WATER_MAIN, RESPONSE, new String()));

		/* Default filters situations */
		// WATER Meter
		getMockMvc().perform(
				get(WATERLIST)
						.param("tag", "1|2|")
						.param("group", "1|2|")
						.param("processId", "1")
						.param("quarantine", "true|")
						.param("status_meter", "WALK_BY_DRIVE_BY|FIXED_BASE_LAT|")
						.param("alarm", "BACK_FLOW|BROWN_OUT|")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.WATER)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_WATER_MAIN, RESPONSE, new String()));

		// Invalid Filter
		getMockMvc().perform(
				get(WATERLIST)
						.param("status_meter", "INVALID_DEVICE_TYPE_ENUM|")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.WATER)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, isEmptyOrNullString()))
				.equals(new ModelAndView(VIEW_WATER_MAIN, RESPONSE, new String()));

	}
}
