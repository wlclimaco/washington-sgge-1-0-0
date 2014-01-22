package com.sensus.lc.controller.filters.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.filters.impl.FilterEventSchedules;
import com.sensus.lc.controller.filters.impl.FilterGroups;
import com.sensus.lc.controller.filters.impl.FilterLightTypes;
import com.sensus.lc.controller.filters.impl.FilterTags;
import com.sensus.lc.controller.groups.unittest.GroupBCFMockImpl;
import com.sensus.lc.controller.light.unittest.LightCustomSearchBCFMockImpl;
import com.sensus.lc.controller.schedule.unittest.ScheduleBCFMockImpl;
import com.sensus.lc.controller.tags.unittest.TagBCFMockImpl;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

/**
 * The Class GroupAPIControllerTest.
 */
public class FiltersAPIControllerTest extends AbstractTestBase
{

	private final String FETCH = "/api/filters/fetch";

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

	@Test
	public void fetch() throws Exception
	{
		// set request object
		getTagMock().setMode(ModeEnum.MODE_SUCCESS);
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		getLightCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);

		setData("{\"filters\":[\"GROUPS\",\"LIFECYCLE_STATE\",\"ALERTS\",\"ALARM_TYPE\",\"WARNING_TYPE\",\"LIGHT_TYPES\",\"EVENT_SCHEDULE\",\"OFFSET_SCHEDULE\",\"TAGS\",\"ADDRESS\",\"CONFIGURATION\",\"ECOMODE\",\"VOLTAGE_RANGE\",\"MODEL_NUMBER\",\"BULB_SERIAL_NUMBER\",\"COLOR_TEMPERATURE\",\"HOUSING_COLOR\",\"LOWER_ASSEMBLY_SERIAL_NUMBER\",\"LIGHT_DRIVER_SERIAL_NUMBER\",\"UPPER_ASSEMBLY_SERIAL_NUMBER\",\"DATE_ADDED\",\"FIRMWARE_VERSION\",\"IDISPLAYSTART\",\"CUSTOM_FILTERS\"],\"action\":\"build\",\"locale\":\"en_US\",\"page\":\"light\"}");
		performTest(FETCH).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Test default search filters.
		setData("{\"filters\":[\"GROUPS\",\"LIFECYCLE_STATE\",\"ALERTS\",\"ALARM_TYPE\",\"WARNING_TYPE\",\"LIGHT_TYPES\",\"EVENT_SCHEDULE\",\"OFFSET_SCHEDULE\",\"TAGS\",\"ADDRESS\",\"CONFIGURATION\",\"ECOMODE\",\"VOLTAGE_RANGE\",\"MODEL_NUMBER\",\"BULB_SERIAL_NUMBER\",\"COLOR_TEMPERATURE\",\"HOUSING_COLOR\",\"LOWER_ASSEMBLY_SERIAL_NUMBER\",\"LIGHT_DRIVER_SERIAL_NUMBER\",\"UPPER_ASSEMBLY_SERIAL_NUMBER\",\"DATE_ADDED\",\"FIRMWARE_VERSION\",\"IDISPLAYSTART\",\"CUSTOM_FILTERS\",\"SEARCH\",\"METER_FIRMWARE\",\"INSTALL_DATE\"],\"action\":\"build\",\"locale\":\"en_US\",\"page\":\"\"}");
		performTest(FETCH).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		setData("{\"action\":\"fetchCustomize\",\"listTypeEnum\":\"SMARTPOINTLIST\",\"locale\":\"en_US\"}");
		performTest(FETCH).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Exception situation
		getTagMock().setMode(ModeEnum.MODE_EXCEPTION);
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		getLightCustomSearchMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

}
