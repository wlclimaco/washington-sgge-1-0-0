package com.sensus.lc.controller.light.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.groups.unittest.GroupBCFMockImpl;
import com.sensus.lc.controller.light.LightDetailAboutViewController;
import com.sensus.lc.controller.schedule.unittest.ScheduleBCFMockImpl;
import com.sensus.lc.controller.tags.unittest.TagBCFMockImpl;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.SessionAuthenticationTestUtil;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

public class LightDetailAboutViewControllerTest extends AbstractTestBase
{

	private static final String LIGHT_DETAIL_MAIN = "/light/light_detail_main";

	private static final String RESPONSE = "response";

	private final String FETCH_LIST = "/lightDetail/about?id=16";

	@Test
	public void fillDetailAbout() throws Exception
	{

		// Success situation
		getLightMock().setMode(ModeEnum.MODE_SUCCESS);
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(LIGHT_DETAIL_MAIN, RESPONSE, new String()));

		// Failure situation
		getLightMock().setMode(ModeEnum.MODE_FAILURE);
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);

		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(LIGHT_DETAIL_MAIN, RESPONSE, new String()));

	}

	private LightBCFMockImpl getLightMock()
	{
		return (LightBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(LightDetailAboutViewController.class).getLightBCF();
	}

	private ScheduleBCFMockImpl getScheduleMock()
	{
		return (ScheduleBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(LightDetailAboutViewController.class).getScheduleBCF();
	}

	private GroupBCFMockImpl getGroupMock()
	{
		return (GroupBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(LightDetailAboutViewController.class).getGroupBCF();
	}

	private TagBCFMockImpl getTagMock()
	{
		return (TagBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(LightDetailAboutViewController.class).getTagBCF();
	}
}