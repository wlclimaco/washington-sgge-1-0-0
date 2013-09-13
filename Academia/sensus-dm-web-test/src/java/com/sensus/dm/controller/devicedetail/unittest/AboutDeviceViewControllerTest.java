package com.sensus.dm.controller.devicedetail.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.device.detail.AboutDeviceViewController;
import com.sensus.dm.controller.device.unittest.DeviceBCFMockImpl;
import com.sensus.dm.controller.electricmeter.unittest.ElectricMeterBCFMockImpl;
import com.sensus.dm.controller.group.unittest.GroupBCFMockImpl;
import com.sensus.dm.controller.note.unittest.NoteBCFMockImpl;
import com.sensus.dm.controller.process.unittest.ProcessSummaryBCFMockImpl;
import com.sensus.dm.controller.schedule.unittest.ScheduleBCFMockImpl;
import com.sensus.dm.controller.tag.unittest.TagBCFMockImpl;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.SessionAuthenticationTestUtil;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

public class AboutDeviceViewControllerTest extends AbstractTestBase
{

	private static final String DEVICE_TAB_ABOUT_VIEW = "/device/tab/about";

	private static final String VIEW_ABOUT_DEVICE_MAIN = "/device_detail/device_detail_about";

	private ElectricMeterBCFMockImpl getElectricMeterMock()
	{
		return (ElectricMeterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(AboutDeviceViewController.class).getElectricMeterBCF();
	}

	private DeviceBCFMockImpl getDeviceMock()
	{
		return (DeviceBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(AboutDeviceViewController.class).getDeviceBCF();
	}

	private GroupBCFMockImpl getGroupMock()
	{
		return (GroupBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(AboutDeviceViewController.class).getGroupBCF();
	}

	private TagBCFMockImpl getTagMock()
	{
		return (TagBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(AboutDeviceViewController.class).getTagBCF();
	}

	private ProcessSummaryBCFMockImpl getProcessSummaryBCFMock()
	{
		return (ProcessSummaryBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(AboutDeviceViewController.class).getProcessSummaryBCF();
	}

	private NoteBCFMockImpl getNoteMock()
	{
		return (NoteBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(AboutDeviceViewController.class).getNoteBCF();
	}

	private ScheduleBCFMockImpl getScheduleMock()
	{
		return (ScheduleBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(AboutDeviceViewController.class).getScheduleBCF();
	}

	@Test
	public void load() throws Exception
	{

		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);

		/* Failure situations */
		// RequestParam invalid
		getMockMvc().perform(
				get(DEVICE_TAB_ABOUT_VIEW)
						.param("id", "1")
						.param("deviceType", "INVALID_DEVICE_TYPE_ENUM")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isBadRequest());

		// RequestParam null
		getMockMvc().perform(
				get(DEVICE_TAB_ABOUT_VIEW)
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isBadRequest());

		// Controller throws an exception
		getElectricMeterMock().setMode(ModeEnum.MODE_FAILURE);
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		getTagMock().setMode(ModeEnum.MODE_FAILURE);
		getProcessSummaryBCFMock().setMode(ModeEnum.MODE_FAILURE);
		getNoteMock().setMode(ModeEnum.MODE_FAILURE);
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);

		getMockMvc().perform(
				get(DEVICE_TAB_ABOUT_VIEW)
						.param("id", "2")
						.param("deviceType", "ELECTRIC_METER")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.equals(new ModelAndView(VIEW_ABOUT_DEVICE_MAIN, RESPONSE, new String()));

		/* Success situations */
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		getProcessSummaryBCFMock().setMode(ModeEnum.MODE_SUCCESS);
		getNoteMock().setMode(ModeEnum.MODE_SUCCESS);
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);

		/* Electric Service */
		// ELECTRIC
		getMockMvc().perform(
				get(DEVICE_TAB_ABOUT_VIEW)
						.param("id", "2")
						.param("deviceType", "ELECTRIC_METER")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.andExpect(model().size(3))
				.equals(new ModelAndView(VIEW_ABOUT_DEVICE_MAIN, RESPONSE, new String()));

		// HAN
		getMockMvc().perform(
				get(DEVICE_TAB_ABOUT_VIEW)
						.param("id", "2")
						.param("deviceType", "HAN_DEVICE")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.andExpect(model().size(3))
				.equals(new ModelAndView(VIEW_ABOUT_DEVICE_MAIN, RESPONSE, new String()));

		// LCM
		getMockMvc().perform(
				get(DEVICE_TAB_ABOUT_VIEW)
						.param("id", "2")
						.param("deviceType", "LCM")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.andExpect(model().size(3))
				.equals(new ModelAndView(VIEW_ABOUT_DEVICE_MAIN, RESPONSE, new String()));

		// FLEXNET_LCM
		getMockMvc().perform(
				get(DEVICE_TAB_ABOUT_VIEW)
						.param("id", "2")
						.param("deviceType", "LCM")
						.param("deviceType", "FLEXNET_LCM")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.andExpect(model().size(3))
				.equals(new ModelAndView(VIEW_ABOUT_DEVICE_MAIN, RESPONSE, new String()));

		// LCM RELAY
		getMockMvc().perform(
				get(DEVICE_TAB_ABOUT_VIEW)
						.param("id", "1")
						.param("deviceType", "LCM")
						.param("deviceType", "LCM_RELAY")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.andExpect(model().size(3))
				.equals(new ModelAndView(VIEW_ABOUT_DEVICE_MAIN, RESPONSE, new String()));

		/* Water Service */
		// WATER_METER
		getMockMvc().perform(
				get(DEVICE_TAB_ABOUT_VIEW)
						.param("id", "2")
						.param("deviceType", "WATER_METER")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.WATER)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.andExpect(model().size(3))
				.equals(new ModelAndView(VIEW_ABOUT_DEVICE_MAIN, RESPONSE, new String()));

		/* Gas Service */
		// GAS_METER
		getMockMvc().perform(
				get(DEVICE_TAB_ABOUT_VIEW)
						.param("id", "2")
						.param("deviceType", "GAS_METER")
						.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.GAS)))
				.andExpect(status().isOk())
				.andExpect(model().attribute(RESPONSE, containsString("")))
				.andExpect(model().size(3))
				.equals(new ModelAndView(VIEW_ABOUT_DEVICE_MAIN, RESPONSE, new String()));
	}
}
