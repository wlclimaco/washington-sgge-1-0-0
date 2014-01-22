package com.sensus.lc.controller.search.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.light.unittest.LightCustomSearchBCFMockImpl;
import com.sensus.lc.controller.search.SavedSearchAPIController;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

/**
 * The Class SavedSearchAPIController.
 */
public class SavedSearchAPIControllerTest extends AbstractTestBase
{

	/*
	 * URLs Mapping
	 */
	/** The Constant MAP_FETCH. */
	private static final String MAP_FETCH = "/api/search/fetch";

	/** The Constant INSERT. */
	private static final String INSERT = "/api/search/insert";

	/** The Constant DELETE. */
	private static final String DELETE_SAVED_SEARCH = "/api/search/delete";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "SavedSearchAPIController";

	private LightCustomSearchBCFMockImpl getLightCustomSearchMock()
	{
		return (LightCustomSearchBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(SavedSearchAPIController.class).getLightCustomSearchBCF();
	}

	@Test
	public void fetch() throws Exception
	{
		// set request object
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":50,\"sortExpressions\":[{\"field\":\"CS.CUSTOM_SEARCH_NAME\",\"direction\":\"Ascending\"}]}");

		// Success situation
		getLightCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_FETCH).andExpect(jsonPath("$.customSearchList", hasSize(25)));

		// Failure situation
		getLightCustomSearchMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getLightCustomSearchMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(MAP_FETCH).andExpect(jsonPath("$.customSearchList", nullValue()));

		// Exception situation
		getLightCustomSearchMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	@Test
	public void delete() throws Exception
	{
		// set request object
		setData("{\"customSearch\":{\"id\":33,\"name\":null,\"description\":null,\"searchParameters\":null,\"listColumn\":null,\"listFilters\":null}}");

		// Success situation
		getLightCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(DELETE_SAVED_SEARCH).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getLightCustomSearchMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(DELETE_SAVED_SEARCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getLightCustomSearchMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(DELETE_SAVED_SEARCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void saveSearch() throws Exception
	{
		// set request object
		setData("{\"customSearch\":{\"name\":\"new\",\"description\":\"\",\"searchParameters\":[{\"propertyEnum\":\"LIFECYCLE_STATE\",\"value\":\"5\"},{\"propertyEnum\":\"SORT\",\"value\":\"label_key desc\"}],\"listColumn\":[{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"columnEnum\":\"POLE_ID\",\"fieldName\":null,\"labelKey\":null,\"ordered\":false,\"displayOrder\":0,\"columnEnumValue\":\"pole_id\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"columnEnum\":\"FLEXNET_ID\",\"fieldName\":null,\"labelKey\":null,\"ordered\":false,\"displayOrder\":1,\"columnEnumValue\":\"flexnet_id\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"columnEnum\":\"LAMP_TYPE_WATTAGE_DIMMABLE\",\"fieldName\":null,\"labelKey\":null,\"ordered\":false,\"displayOrder\":2,\"columnEnumValue\":\"lamp_type_wattage_dimmable\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"columnEnum\":\"DATE_ADDED\",\"fieldName\":null,\"labelKey\":null,\"ordered\":false,\"displayOrder\":3,\"columnEnumValue\":\"date_added\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"columnEnum\":\"CITY\",\"fieldName\":null,\"labelKey\":null,\"ordered\":false,\"displayOrder\":4,\"columnEnumValue\":\"city\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"columnEnum\":\"MAP_IT\",\"fieldName\":null,\"labelKey\":null,\"ordered\":false,\"displayOrder\":5,\"columnEnumValue\":\"map_it\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"columnEnum\":\"PROTECTED\",\"fieldName\":null,\"labelKey\":null,\"ordered\":false,\"displayOrder\":6,\"columnEnumValue\":\"protected\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"columnEnum\":\"ECOMODE\",\"fieldName\":null,\"labelKey\":null,\"ordered\":false,\"displayOrder\":7,\"columnEnumValue\":\"ecomode\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"columnEnum\":\"LIFECYCLE_STATE\",\"fieldName\":null,\"labelKey\":null,\"ordered\":false,\"displayOrder\":8,\"columnEnumValue\":\"lifecycle_state\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"columnEnum\":\"ALERTS\",\"fieldName\":null,\"labelKey\":null,\"ordered\":false,\"displayOrder\":9,\"columnEnumValue\":\"alerts\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"columnEnum\":\"FIRMWARE_VERSION\",\"fieldName\":null,\"labelKey\":null,\"ordered\":false,\"displayOrder\":10,\"columnEnumValue\":\"firmware_version\"}],\"listFilters\":[{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"filterEnum\":\"GROUPS\",\"displayOrder\":0,\"filterEnumValue\":\"GROUPS\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"filterEnum\":\"LIFECYCLE_STATE\",\"displayOrder\":1,\"filterEnumValue\":\"LIFECYCLE_STATE\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"filterEnum\":\"ALERTS\",\"displayOrder\":2,\"filterEnumValue\":\"ALERTS\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"filterEnum\":\"ALARM_TYPE\",\"displayOrder\":3,\"filterEnumValue\":\"ALARM_TYPE\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"filterEnum\":\"WARNING_TYPE\",\"displayOrder\":4,\"filterEnumValue\":\"WARNING_TYPE\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"filterEnum\":\"LIGHT_TYPES\",\"displayOrder\":5,\"filterEnumValue\":\"LIGHT_TYPES\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"filterEnum\":\"EVENT_SCHEDULE\",\"displayOrder\":6,\"filterEnumValue\":\"EVENT_SCHEDULE\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"filterEnum\":\"OFFSET_SCHEDULE\",\"displayOrder\":7,\"filterEnumValue\":\"OFFSET_SCHEDULE\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"filterEnum\":\"TAGS\",\"displayOrder\":8,\"filterEnumValue\":\"TAGS\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"filterEnum\":\"ADDRESS\",\"displayOrder\":9,\"filterEnumValue\":\"ADDRESS\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"filterEnum\":\"CONFIGURATION\",\"displayOrder\":10,\"filterEnumValue\":\"CONFIGURATION\"},{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"filterEnum\":\"ECOMODE\",\"displayOrder\":11,\"filterEnumValue\":\"ECOMODE\"}]}}");

		// Success situation
		getLightCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(INSERT).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getLightCustomSearchMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(INSERT).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getLightCustomSearchMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(INSERT).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}
}
