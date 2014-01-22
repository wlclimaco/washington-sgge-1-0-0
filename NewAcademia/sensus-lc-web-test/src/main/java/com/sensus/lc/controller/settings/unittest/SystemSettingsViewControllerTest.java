package com.sensus.lc.controller.settings.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.settings.SystemSettingsAPIController;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.SessionAuthenticationTestUtil;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

/**
 * The Class SystemSettingsViewController.
 */
public class SystemSettingsViewControllerTest extends AbstractTestBase
{

	/** The fetch. */
	private final String FETCH = "/generalsettings";

	/** The Constant RESPONSE. */
	private static final String RESPONSE = "response";

	private static final String REFRESH = "refresh";

	/** The Constant SYSTEM_SETTINGS. */
	private static final String SYSTEM_SETTINGS = "/systemsettings/general_settings";

	/**
	 * Gets the settings bcf.
	 * 
	 * @return the settings bcf
	 */
	private SettingsBCFMockImpl getSettingsMOCK()
	{
		return (SettingsBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(SystemSettingsAPIController.class).getSettingsBCF();
	}

	/**
	 * Fetch all.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetch() throws Exception
	{
		// Success situation
		getSettingsMOCK().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(SYSTEM_SETTINGS, RESPONSE, new String()));

		// Failure situation
		getSettingsMOCK().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(SYSTEM_SETTINGS, RESPONSE, new String()));

		// PRELOAD situation
		getSettingsMOCK().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH).param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(SYSTEM_SETTINGS, RESPONSE, new String()));
	}
}
