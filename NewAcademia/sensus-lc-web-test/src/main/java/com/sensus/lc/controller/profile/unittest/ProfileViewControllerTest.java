package com.sensus.lc.controller.profile.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.profile.ProfileViewController;
import com.sensus.lc.controller.settings.unittest.SettingsBCFMockImpl;
import com.sensus.lc.controller.user.unittest.UserBCFMockImpl;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.SessionAuthenticationTestUtil;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

/**
 * The Class SystemSettingsViewControllerTest.
 */
public class ProfileViewControllerTest extends AbstractTestBase
{

	/** The fetch. */
	private final String FETCH = "/profile";

	/** The Constant RESPONSE. */
	private static final String RESPONSE = "response";

	private static final String REFRESH = "refresh";

	/** The Constant VIEW_PROFILE_MAIN. */
	private static final String VIEW_PROFILE_MAIN = "/profile/profile_main";

	/**
	 * Gets the settings bcf.
	 * 
	 * @return the settings bcf
	 */
	private SettingsBCFMockImpl getSettingsMOCK()
	{
		return (SettingsBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ProfileViewController.class).getSettingsBCF();
	}

	private UserBCFMockImpl getUserMOCK()
	{
		return (UserBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ProfileViewController.class).getUserBCF();
	}

	/**
	 * Fetch all.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void loadList() throws Exception
	{
		// Success situation
		getSettingsMOCK().setMode(ModeEnum.MODE_SUCCESS);
		getUserMOCK().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(VIEW_PROFILE_MAIN, RESPONSE, new String()));

		// Failure situation
		getSettingsMOCK().setMode(ModeEnum.MODE_FAILURE);
		getUserMOCK().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_PROFILE_MAIN, RESPONSE, new String()));

		// PRELOAD situation
		getSettingsMOCK().setMode(ModeEnum.MODE_SUCCESS);
		getUserMOCK().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH).param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(VIEW_PROFILE_MAIN, RESPONSE, new String()));
	}
}