package com.sensus.dm.controller.filter.unittest;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.action.unittest.ActionBCFMockImpl;
import com.sensus.dm.controller.device.unittest.DeviceBCFMockImpl;
import com.sensus.dm.controller.filter.FilterAPIController;
import com.sensus.dm.controller.group.unittest.GroupBCFMockImpl;
import com.sensus.dm.controller.process.unittest.ProcessTypeBCFMockImpl;
import com.sensus.dm.controller.settings.unittest.SettingsBCFMockImpl;
import com.sensus.dm.controller.tag.unittest.TagBCFMockImpl;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;

/**
 * The Class FilterAPIControllerTest.
 */
public class FilterAPIControllerTest extends AbstractTestBase
{

	/** The Constant FILTER. */
	private static final String FILTER = "/api/filter/fetch";

	/**
	 * Gets the group mock.
	 * 
	 * @return the group mock
	 */
	private GroupBCFMockImpl getGroupMock()
	{
		return (GroupBCFMockImpl)SensusAppContext.getApplicationContext().getBean(FilterAPIController.class)
				.getGroupBCF();
	}

	/**
	 * Gets the tag mock.
	 * 
	 * @return the tag mock
	 */
	private TagBCFMockImpl getTagMock()
	{
		return (TagBCFMockImpl)SensusAppContext.getApplicationContext().getBean(FilterAPIController.class)
				.getTagBCF();
	}

	/**
	 * Gets the action mock.
	 * 
	 * @return the action mock
	 */
	private ActionBCFMockImpl getActionMock()
	{
		return (ActionBCFMockImpl)SensusAppContext.getApplicationContext().getBean(FilterAPIController.class)
				.getActionBCF();
	}

	/**
	 * Gets the settings mock.
	 * 
	 * @return the settings mock
	 */
	private SettingsBCFMockImpl getSettingsMock()
	{
		return (SettingsBCFMockImpl)SensusAppContext.getApplicationContext().getBean(FilterAPIController.class)
				.getSettingsBCF();
	}

	/**
	 * Gets the device mock.
	 * 
	 * @return the device mock
	 */
	private DeviceBCFMockImpl getDeviceMock()
	{
		return (DeviceBCFMockImpl)SensusAppContext.getApplicationContext().getBean(FilterAPIController.class)
				.getDeviceBCF();
	}

	/**
	 * Gets the process type mock.
	 * 
	 * @return the process type mock
	 */
	private ProcessTypeBCFMockImpl getProcessTypeMock()
	{
		return (ProcessTypeBCFMockImpl)SensusAppContext.getApplicationContext().getBean(FilterAPIController.class)
				.getProcessTypeBCF();
	}

	/**
	 * Filter.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void filter() throws Exception
	{
		// Success Situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		getActionMock().setMode(ModeEnum.MODE_SUCCESS);
		getProcessTypeMock().setMode(ModeEnum.MODE_SUCCESS);

		// ELETRIC Filters
		setData("{\"filter\":[\"group\",\"lifecycle_state\",\"description\",\"install_date\",\"address\",\"tag\",\"remote_disconnect\",\"quarantine\",\"meter_firmware\"],\"deviceType\":[\"ELECTRIC_METER\"]}");
		performTest(FILTER);

		// LCM Filters
		setData("{\"filter\":[\"group\",\"lifecycle_state\",\"device_subtype\",\"description\",\"install_date\",\"address\",\"tag\",\"alert\"],\"deviceType\":[\"LCM\"]}");
		performTest(FILTER);

		// Water Filter
		setData("{\"filter\":[\"group\",\"status_meter\",\"description\",\"alarm\",\"alert\",\"install_date\",\"address\",\"meter_firmware\",\"quarantine\",\"tag\"],\"deviceType\":[\"WATER_METER\"]}");
		performTest(FILTER);

		// Gas Filter
		setData("{\"filter\":[\"group\",\"status_meter\",\"description\",\"alarm\",\"alert\",\"install_date\",\"address\",\"meter_firmware\",\"quarantine\",\"tag\"],\"deviceType\":[\"GAS_METER\"]}");
		performTest(FILTER);

		// HAN Filters
		setData("{\"filter\":[\"group\",\"lifecycle_state\",\"device_subtype\",\"description\",\"install_date\",\"address\",\"tag\"],\"deviceType\":[\"HAN_DEVICE\"]}");
		performTest(FILTER);

		// Event History Filters
		setData("{\"filter\":[\"search\",\"date_filter\",\"all_action_categories\",\"users\"]}");
		performTest(FILTER);

		// Schedule Event Filters
		setData("{\"filter\":[\"query_search\",\"schedule_action_categories\",\"status_scheduled\",\"repeats\",\"users\"]}");
		performTest(FILTER);

		// Group Filters
		setData("{\"filter\":[\"query\",\"group_type\", \"group_device_type\"]}");
		performTest(FILTER);

		// Exception Situation
		setData("{\"filter\" : [\"group\"]}");
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
		getTagMock().setMode(ModeEnum.MODE_EXCEPTION);
		getSettingsMock().setMode(ModeEnum.MODE_EXCEPTION);
		getDeviceMock().setMode(ModeEnum.MODE_EXCEPTION);
		getActionMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FILTER);
	}
}