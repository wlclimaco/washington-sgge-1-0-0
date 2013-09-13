package com.sensus.dm.controller.savedsearch.unittest;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.savedsearch.SavedSearchAPIController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class SavedSearchAPIControllerTest.
 */
public class SavedSearchAPIControllerTest extends AbstractTestBase
{
	/** The Constant DELETE. */
	private static final String DELETE = "/api/search/delete";

	/** The Constant FETCH. */
	private static final String FETCH = "/api/search/fetch";

	/** The Constant FETCH_COLUMN_FILTER. */
	private static final String FETCH_COLUMN_FILTER = "/api/search/fetchColumnFilter";

	/** The Constant INSERT. */
	private static final String INSERT = "/api/search/insert";

	/** The Constant UPDATE_COLUMN_FILTER. */
	private static final String UPDATE_COLUMN_FILTER = "/api/search/updateColumnFilter";

	/**
	 * Gets the group mock.
	 * 
	 * @return the group mock
	 */
	private CustomSearchBCFMockImpl getCustomSearchMock()
	{
		return (CustomSearchBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(SavedSearchAPIController.class).getCustomSearchBCF();
	}

	/**
	 * Fetch.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetch() throws Exception
	{
		// Set Data...
		setData("{\"startRow\":0,\"endRow\":0,\"pageSize\":25," +
				"\"sortExpressions\":[{\"field\":\"name\",\"direction\":\"Ascending\"}]}");

		// Success Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH)
				.andExpect(jsonPath("$.customSearches", hasSize(25)))
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)))
				.andExpect(jsonPath("$.messageList[0].text", containsString(TestMessageEnum.MESSAGE_INFO.getValue())));

		// Failure Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(false)))
				.andExpect(jsonPath("$.messageList[0].text", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH)
				.andExpect(jsonPath("$.customSearches", hasSize(0)))
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		getCustomSearchMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(false)))
				.andExpect(jsonPath("$.messageList[0].text",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Insert.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void insert() throws Exception
	{
		// Set Data...
		setData("{\"customSearch\":{\"electricMeterSearch\":{\"electricMeter\":{\"radio\":{},\"configuration\":{"
				+
				"\"firmwareMeter\":null}},\"remoteConnectStatusEnumList\":null,\"electricMeterLifecycleStateEnumList\":"
				+
				"[\"INSTALLED\"]},\"groups\":null,\"tags\":null,\"deviceModels\":null,\"startDate\":null,\"endDate\":null,"
				+
				"\"deviceType\":\"ELECTRIC_METER\",\"name\":\"Test\",\"description\":\"\",\"listColumn\":null,\"listFilters\":"
				+
				"[\"GROUP\",\"LIFECYCLE_STATE\",\"DESCRIPTION\",\"INSTALL_DATE\",\"ADDRESS\",\"TAG\",\"METER_FIRMWARE\"]}}");

		// Success Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(INSERT)
				.andExpect(jsonPath("$.customSearches", hasSize(1)))
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)))
				.andExpect(jsonPath("$.messageList[0].text", containsString(TestMessageEnum.MESSAGE_INFO.getValue())));

		// Failure Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(INSERT)
				.andExpect(jsonPath("$.customSearches", nullValue()))
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(false)))
				.andExpect(jsonPath("$.messageList[0].text", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(INSERT)
				.andExpect(jsonPath("$.customSearches", nullValue()))
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Exception Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(INSERT)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(false)))
				.andExpect(jsonPath("$.messageList[0].text",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Delete.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void delete() throws Exception
	{
		// Set Data...
		setData("{\"customSearch\":{\"id\":1}}");

		// Success Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(DELETE)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)))
				.andExpect(jsonPath("$.messageList[0].text", containsString(TestMessageEnum.MESSAGE_INFO.getValue())));

		// Failure Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(DELETE)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(false)))
				.andExpect(jsonPath("$.messageList[0].text", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(DELETE)
				.andExpect(jsonPath("$.customSearches", nullValue()))
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Exception Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(DELETE)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(false)))
				.andExpect(jsonPath("$.messageList[0].text",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Fetch column filter.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchColumnFilter() throws Exception
	{
		// Set Data...
		setData("{\"propertyEnum\":\"DEVICE_ID\"}");

		// Success Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_COLUMN_FILTER)
				.andExpect(jsonPath("$.additionalColumns", hasSize(15)))
				.andExpect(jsonPath("$.additionalFilters", hasSize(10)))
				.andExpect(jsonPath("$.filters", hasSize(3)))
				.andExpect(jsonPath("$.listColumn", hasSize(8)))
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_COLUMN_FILTER)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(false)))
				.andExpect(jsonPath("$.messageList[0].text", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_COLUMN_FILTER)
				.andExpect(jsonPath("$.additionalColumns", nullValue()))
				.andExpect(jsonPath("$.additionalFilters", nullValue()))
				.andExpect(jsonPath("$.filters", nullValue()))
				.andExpect(jsonPath("$.listColumn", nullValue()))
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Exception Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_COLUMN_FILTER)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(false)))
				.andExpect(jsonPath("$.messageList[0].text",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Update column filter.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void updateColumnFilter() throws Exception
	{
		/*
		 * Set Data with filter type...
		 * Update Customize Filter
		 */
		setData("{\"page\":\"smartpointlist\",\"type\":\"filters\",\"filters\":[\"GROUP\",\"LIFECYCLE_STATE\",\"DEVICE_TYPE\",\"DESCRIPTION\",\"INSTALL_DATE\",\"ADDRESS\",\"METER_FIRMWARE\"],\"propertyEnum\":\"DEVICE_ID\"}");

		// Success Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(UPDATE_COLUMN_FILTER)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)))
				.andExpect(jsonPath("$.messageList[0].text", containsString(TestMessageEnum.MESSAGE_INFO.getValue())));

		/*
		 * Set Data with column type...
		 * Update Customize Filter
		 */
		setData("{\"page\":\"smartpointlist\",\"type\":\"columns\",\"filters\":[\"DEVICE_ID\"],\"sortExpressions\":[{\"field\":\"device_id\",\"direction\":\"Ascending\"}],\"propertyEnum\":\"ELECTRIC_METER_COLUMN\"}");

		// Success Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(UPDATE_COLUMN_FILTER)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)))
				.andExpect(jsonPath("$.messageList[0].text", containsString(TestMessageEnum.MESSAGE_INFO.getValue())));

		// Failure Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(UPDATE_COLUMN_FILTER)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(false)))
				.andExpect(jsonPath("$.messageList[0].text", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(UPDATE_COLUMN_FILTER)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Exception Situation
		getCustomSearchMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(UPDATE_COLUMN_FILTER)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(false)))
				.andExpect(jsonPath("$.messageList[0].text",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}
}