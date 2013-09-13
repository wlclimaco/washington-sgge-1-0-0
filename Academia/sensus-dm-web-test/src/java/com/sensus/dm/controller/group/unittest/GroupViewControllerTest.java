package com.sensus.dm.controller.group.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.group.GroupViewController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.SessionAuthenticationTestUtil;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

public class GroupViewControllerTest extends AbstractTestBase
{

	private static final String RESPONSE = "response";

	/** The Constant REFRESH. */
	private static final String REFRESH = "refresh";

	private static final String GROUP_GROUP_MAIN = "/group/group_main";

	/** The Constant VIEW_GROUP_CREATE. */
	private static final String VIEW_GROUP_CREATE = "/group/group_create";

	private final String FETCH_LIST = "/group";

	/** The fetch. */
	private final String FETCH_UPDATE = "/group/update";

	private GroupBCFMockImpl getGroupMock()
	{
		return (GroupBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(GroupViewController.class).getGroupBCF();
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
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);

		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(GROUP_GROUP_MAIN, RESPONSE, new String()));

		// Success refresh situation
		getMockMvc().perform(
				get(FETCH_LIST).param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(GROUP_GROUP_MAIN, RESPONSE, new String()));

		// Failure situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);

		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(GROUP_GROUP_MAIN, RESPONSE, new String()));
	}

	/**
	 * Fetch update.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchUpdate() throws Exception
	{

		// Success situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_UPDATE).param("id", "1")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.equals(new ModelAndView(VIEW_GROUP_CREATE, RESPONSE, new String()));

		// Failure situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_UPDATE).param("id", "1")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				// .andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_GROUP_CREATE, RESPONSE, new String()));

	}

}