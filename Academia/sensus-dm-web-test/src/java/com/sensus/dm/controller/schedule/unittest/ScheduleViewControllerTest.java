package com.sensus.dm.controller.schedule.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.device.unittest.DeviceBCFMockImpl;
import com.sensus.dm.controller.group.unittest.GroupBCFMockImpl;
import com.sensus.dm.controller.schedule.ScheduleViewController;
import com.sensus.dm.controller.settings.unittest.SettingsBCFMockImpl;
import com.sensus.dm.controller.tag.unittest.TagBCFMockImpl;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.SessionAuthenticationTestUtil;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class ScheduleViewControllerTest.
 */
public class ScheduleViewControllerTest extends AbstractTestBase
{

	/** The Constant RESPONSE. */
	private static final String RESPONSE = "response";

	/** The Constant REFRESH. */
	private static final String REFRESH = "refresh";

	/** The Constant SCHEDULE_MAIN. */
	private static final String SCHEDULE_MAIN = "/schedule/schedule_main";

	/** The Constant SCHEDULE_CREATE_MAIN. */
	private static final String SCHEDULE_CREATE_MAIN = "/schedule/schedule_create_main";

	/** The fetch list. */
	private final String FETCH_LIST = "/tab/schedule";

	/** The fetch create. */
	private final String FETCH_CREATE = "/schedule/create";

	/** The fetch update. */
	private final String FETCH_UPDATE = "/schedule/edit";

	/**
	 * Gets the schedule mock.
	 * 
	 * @return the schedule mock
	 */
	private ScheduleBCFMockImpl getScheduleMock()
	{
		return (ScheduleBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ScheduleViewController.class).getScheduleBCF();
	}

	/**
	 * Gets the device mock.
	 * 
	 * @return the device mock
	 */
	private DeviceBCFMockImpl getDeviceMock()
	{
		return (DeviceBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ScheduleViewController.class).getDeviceBCF();
	}

	/**
	 * Gets the settings mock.
	 * 
	 * @return the settings mock
	 */
	private SettingsBCFMockImpl getSettingsMock()
	{
		return (SettingsBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ScheduleViewController.class).getSettingsBCF();
	}

	/**
	 * Gets the group mock.
	 * 
	 * @return the group mock
	 */
	private GroupBCFMockImpl getGroupMock()
	{
		return (GroupBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ScheduleViewController.class).getGroupBCF();
	}

	/**
	 * Gets the tag mock.
	 * 
	 * @return the tag mock
	 */
	private TagBCFMockImpl getTagMock()
	{
		return (TagBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ScheduleViewController.class).getTagBCF();
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
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.equals(new ModelAndView(SCHEDULE_MAIN, RESPONSE, new String()));

		// Failure situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		getDeviceMock().setMode(ModeEnum.MODE_FAILURE);
		getSettingsMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_LIST)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(SCHEDULE_MAIN, RESPONSE, new String()));

		// Success refresh situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_LIST).param("initialLoad", "false")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(REFRESH, containsString(REFRESH)))
				.equals(new ModelAndView(SCHEDULE_MAIN, RESPONSE, new String()));
	}

	/**
	 * Fetch create.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void loadCreate() throws Exception
	{

		// Success situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_CREATE)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.equals(new ModelAndView(SCHEDULE_CREATE_MAIN, RESPONSE, new String()));

		// Failure situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		getDeviceMock().setMode(ModeEnum.MODE_FAILURE);
		getSettingsMock().setMode(ModeEnum.MODE_FAILURE);
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_CREATE)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.equals(new ModelAndView(SCHEDULE_CREATE_MAIN, RESPONSE, new String()));

	}

	/**
	 * Fetch create.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void loadUpdate() throws Exception
	{

		// Success situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_UPDATE).param("id", "1").param("editType", "edit")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.equals(new ModelAndView(SCHEDULE_CREATE_MAIN, RESPONSE, new String()));

		// Success situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		getMockMvc().perform(
				get(FETCH_UPDATE).param("id", "1").param("editType", "action")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.equals(new ModelAndView(SCHEDULE_CREATE_MAIN, RESPONSE, new String()));

		// Failure situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		getDeviceMock().setMode(ModeEnum.MODE_FAILURE);
		getSettingsMock().setMode(ModeEnum.MODE_FAILURE);
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				get(FETCH_UPDATE).param("id", "1").param("editType", "action")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.equals(new ModelAndView(SCHEDULE_CREATE_MAIN, RESPONSE, new String()));

	}

}