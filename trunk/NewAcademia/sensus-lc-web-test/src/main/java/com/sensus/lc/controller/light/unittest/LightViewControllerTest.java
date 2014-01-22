package com.sensus.lc.controller.light.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.filters.impl.FilterEventSchedules;
import com.sensus.lc.controller.filters.impl.FilterGroups;
import com.sensus.lc.controller.filters.impl.FilterLightTypes;
import com.sensus.lc.controller.filters.impl.FilterTags;
import com.sensus.lc.controller.groups.unittest.GroupBCFMockImpl;
import com.sensus.lc.controller.light.LightViewController;
import com.sensus.lc.controller.schedule.unittest.ScheduleBCFMockImpl;
import com.sensus.lc.controller.tags.unittest.TagBCFMockImpl;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.SessionAuthenticationTestUtil;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

public class LightViewControllerTest extends AbstractTestBase
{

	private static final String VIEW_LIGHT_MAIN = "/light/light_main";

	private final String FETCH_LIST =
			"/light?query=36|12&sd=true&failed=true&processId=8524&offset_schedule=333|&event_schedule=333|&tags=333|&groups=659|&lifecycle_state=5|&sort=ast.label_key|desc|&length=25&alerts=1|4|2|";

	private final String FETCH_LIST_SHORT_STRING = "/light?&?alerts=1";

	private static final String RESPONSE = "response";

	private static final String FILTERS_RESPONSE = "filtersResponse";

	private static final String REFRESH = "refresh";

	private ScheduleBCFMockImpl getScheduleMock()
	{
		return (ScheduleBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(FilterEventSchedules.class).getScheduleBCF();
	}

	private GroupBCFMockImpl getGroupMock()
	{
		return (GroupBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(FilterGroups.class).getGroupBCF();
	}

	private TagBCFMockImpl getTagMock()
	{
		return (TagBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(FilterTags.class).getTagBCF();
	}

	private LightCustomSearchBCFMockImpl getLightCustomSearchMock()
	{
		return (LightCustomSearchBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(FilterLightTypes.class).getLightCustomSearchBCF();
	}

	private LightBCFMockImpl getLightMock()
	{
		return (LightBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(LightViewController.class).getLightBCF();
	}

	@Test
	public void loadList() throws Exception
	{
		// Success situation
		getLightMock().setMode(ModeEnum.MODE_SUCCESS);
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		getLightCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST).session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(VIEW_LIGHT_MAIN, RESPONSE, new String())
						.equals(new ModelAndView(VIEW_LIGHT_MAIN, FILTERS_RESPONSE, new String())));

		// Success situation - low parameters
		getLightMock().setMode(ModeEnum.MODE_SUCCESS);
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		getLightCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST_SHORT_STRING).session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("\"operationSuccess\":true")))
				.equals(new ModelAndView(VIEW_LIGHT_MAIN, RESPONSE, new String())
						.equals(new ModelAndView(VIEW_LIGHT_MAIN, FILTERS_RESPONSE, new String())));

		// Failure situation
		getLightMock().setMode(ModeEnum.MODE_FAILURE);
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		getLightCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST).session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_LIGHT_MAIN, RESPONSE, new String())
						.equals(new ModelAndView(VIEW_LIGHT_MAIN, FILTERS_RESPONSE, new String())));

		// Success refresh situation
		getLightMock().setMode(ModeEnum.MODE_SUCCESS);
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		getLightCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST).param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(VIEW_LIGHT_MAIN, RESPONSE, new String())
						.equals(new ModelAndView(VIEW_LIGHT_MAIN, FILTERS_RESPONSE, new String())));

		// PRELOAD situation
		getLightMock().setMode(ModeEnum.MODE_SUCCESS);
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST).param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest()))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(VIEW_LIGHT_MAIN, RESPONSE, new String()));

	}

}