package com.sensus.dm.controller.tag.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.tag.TagViewController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.SessionAuthenticationTestUtil;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

public class TagViewControllerTest extends AbstractTestBase
{

	/** The Constant RESPONSE. */
	private static final String TAG = "tags";

	/** The Constant SEARCH_SEARCH_MAIN. */
	private static final String SEARCH_SEARCH_MAIN = "/tag/tag_main";

	/** The fetch list. */
	private final String FETCH_LIST = "/tag";

	/**
	 * Gets the custom search mock.
	 * 
	 * @return the custom search mock
	 */
	private TagBCFMockImpl getTagMock()
	{
		return (TagBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(TagViewController.class).getTagBCF();
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
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(TAG, containsString("")))
				.equals(new ModelAndView(SEARCH_SEARCH_MAIN, TAG, new String()));

		// Failure situation
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(TAG, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(SEARCH_SEARCH_MAIN, TAG, new String()));

	}

}
