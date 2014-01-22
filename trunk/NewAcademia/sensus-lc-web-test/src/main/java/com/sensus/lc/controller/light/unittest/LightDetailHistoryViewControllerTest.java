package com.sensus.lc.controller.light.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.light.LightDetailHistoryViewController;
import com.sensus.lc.controller.user.UserViewController;
import com.sensus.lc.controller.user.unittest.UserBCFMockImpl;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.SessionAuthenticationTestUtil;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

public class LightDetailHistoryViewControllerTest extends AbstractTestBase
{

	private static final String LIGHT_DETAIL_MAIN = "/light/light_detail_history";

	private static final String RESPONSE = "response";

	private static final String REFRESH = "refresh";

	private final String FETCH_LIST = "/history?id=1";

	@Test
	public void fillDetailHistory() throws Exception
	{

		// Success situation
		getLightNotificationHistoryBCF().setMode(ModeEnum.MODE_SUCCESS);
		getUserBCF().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(LIGHT_DETAIL_MAIN, RESPONSE, new String()));

		// Failure situation
		getLightNotificationHistoryBCF().setMode(ModeEnum.MODE_FAILURE);
		getUserBCF().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(LIGHT_DETAIL_MAIN, RESPONSE, new String()));

		// Success refresh situation
		getUserBCF().setMode(ModeEnum.MODE_SUCCESS);
		getLightNotificationHistoryBCF().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST).param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(LIGHT_DETAIL_MAIN, RESPONSE, new String()));

	}

	private LightNotificationHistoryBCFMockImpl getLightNotificationHistoryBCF()
	{
		return (LightNotificationHistoryBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(LightDetailHistoryViewController.class).getLightNotificationHistoryBCF();
	}

	private UserBCFMockImpl getUserBCF()
	{
		return (UserBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(UserViewController.class).getUserBCF();
	}
}