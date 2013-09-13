package com.sensus.dm.controller.savedsearch.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.savedsearch.SavedSearchViewController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.SessionAuthenticationTestUtil;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class SavedSearchViewControllerTest.
 */
public class SavedSearchViewControllerTest extends AbstractTestBase
{

	/** The Constant RESPONSE. */
	private static final String RESPONSE = "response";

	/** The Constant SEARCH_SEARCH_MAIN. */
	private static final String SEARCH_SEARCH_MAIN = "/saved_search/saved_search_main";

	/** The fetch list. */
	private final String FETCH_LIST = "/savedSearch";

	/**
	 * Gets the custom search mock.
	 * 
	 * @return the custom search mock
	 */
	private CustomSearchBCFMockImpl getCustomSearchMock()
	{
		return (CustomSearchBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(SavedSearchViewController.class).getCustomSearchBCF();
	}

	/**
	 * Fetch list.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchList() throws Exception
	{

		// Success situation
		getCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(SEARCH_SEARCH_MAIN, RESPONSE, new String()));

		// Failure situation
		getCustomSearchMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(SEARCH_SEARCH_MAIN, RESPONSE, new String()));
	}
}