package com.sensus.lc.controller.search.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.light.unittest.LightCustomSearchBCFMockImpl;
import com.sensus.lc.controller.search.SavedSearchAPIController;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.SessionAuthenticationTestUtil;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

/**
 * The Class SavedSearchAPIController.
 */
public class SavedSearchViewControllerTest extends AbstractTestBase
{
	/** The Constant VIEW_LIGHT_MAIN. */
	private static final String VIEW_SEARCH_MAIN = "/search/search_main";

	/** The Constant GROUPS. */
	private static final String RESPONSE = "response";

	/** The Constant REFRESH. */
	private static final String REFRESH = "refresh";

	/** The fetch all. */
	private final String FETCH_LIST = "/search";

	private LightCustomSearchBCFMockImpl getLightCustomSearchMock()
	{
		return (LightCustomSearchBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(SavedSearchAPIController.class).getLightCustomSearchBCF();
	}

	/**
	 * Fetch list.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void loadList() throws Exception
	{

		// Success situation
		getLightCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(VIEW_SEARCH_MAIN, RESPONSE, new String()));

		// Failure situation
		getLightCustomSearchMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_SEARCH_MAIN, RESPONSE, new String()));

		// Success refresh situation
		getLightCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST).param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(VIEW_SEARCH_MAIN, RESPONSE, new String()));

	}

}
