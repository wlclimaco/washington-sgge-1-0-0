package com.sensus.dm.controller.settings.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.systemsettings.SettingsViewController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.SessionAuthenticationTestUtil;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

public class SettingsViewControllerTest extends AbstractTestBase
{

	/** The Constant RESPONSE. */
	private static final String PROPERTIES = "properties";

	/** The Constant SEARCH_SEARCH_MAIN. */
	private static final String SYSTEM_SETTINGS_MAIN = "/systemsettings/system_settings_general_main";

	/** The Constant SEARCH_SEARCH_MAIN. */
	private static final String USER_PROFILE_MAIN = "/profile/profile_main";

	/** The fetch list. */
	private final String PROFILE = "/settings/profile";

	/** The fetch list. */
	private final String SYSTEM = "/settings/system";

	/**
	 * Gets the settings mock.
	 * 
	 * @return the settings mock
	 */
	private SettingsBCFMockImpl getSettingsMock()
	{
		return (SettingsBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(SettingsViewController.class).getSettingsBCF();
	}

	/**
	 * profile.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void profile() throws Exception
	{

		// Success situation
		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(PROFILE)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(PROPERTIES, containsString("")))
				.equals(new ModelAndView(USER_PROFILE_MAIN, PROPERTIES, new String()));

		// Failure situation
		getSettingsMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(PROFILE)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(PROPERTIES, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(USER_PROFILE_MAIN, PROPERTIES, new String()));

	}

	/**
	 * system.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void system() throws Exception
	{

		// Success situation
		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(SYSTEM)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(PROPERTIES, containsString("")))
				.equals(new ModelAndView(SYSTEM_SETTINGS_MAIN, PROPERTIES, new String()));

		// Failure situation
		getSettingsMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(SYSTEM)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(PROPERTIES, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(SYSTEM_SETTINGS_MAIN, PROPERTIES, new String()));

	}

}
