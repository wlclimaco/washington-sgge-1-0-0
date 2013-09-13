package com.sensus.dm.controller.gasmeter.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.gasmeter.GasViewController;
import com.sensus.dm.controller.savedsearch.unittest.CustomSearchBCFMockImpl;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.SessionAuthenticationTestUtil;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

public class GasViewControllerTest extends AbstractTestBase
{
	/** The Constant GASLIST. */
	private static final String GASLIST = "/gaslist";

	/** The Constant VIEW_WATER_MAIN. */
	private static final String VIEW_WATER_MAIN = "/device/gas_main";

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
	 * Gets the custom search mock.
	 * 
	 * @return the custom search mock
	 */
	private CustomSearchBCFMockImpl getCustomSearchMock()
	{
		return (CustomSearchBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(GasViewController.class).getCustomSearchBCF();
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
		getGasMeterMock().setMode(ModeEnum.MODE_FAILURE);
		getCustomSearchMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(GASLIST)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.WATER)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_WATER_MAIN, RESPONSE, new String()));

		/* Success situations */
		getGasMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		getCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(GASLIST)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.WATER)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(VIEW_WATER_MAIN, RESPONSE, new String()));

		// Refresh situation
		getMockMvc().perform(
				get(GASLIST).param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.WATER)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(VIEW_WATER_MAIN, RESPONSE, new String()));

		// Saved situation
		getMockMvc().perform(
				get(GASLIST).param("saved", "true")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.WATER)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(VIEW_WATER_MAIN, RESPONSE, new String()));

		/* Default filters situations */
		// WATER Meter
		getMockMvc().perform(
				get(GASLIST)
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
				get(GASLIST)
						.param("status_meter", "INVALID_DEVICE_TYPE_ENUM|")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.WATER)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, isEmptyOrNullString()))
				.equals(new ModelAndView(VIEW_WATER_MAIN, RESPONSE, new String()));

	}
}
