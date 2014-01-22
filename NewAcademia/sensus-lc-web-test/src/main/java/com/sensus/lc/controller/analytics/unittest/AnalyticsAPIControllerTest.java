package com.sensus.lc.controller.analytics.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.analytics.AnalyticsAPIController;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

/**
 * The Class AnalyticsAPIControllerTest.
 */
public class AnalyticsAPIControllerTest extends AbstractTestBase
{

	/** The Constant FETCH. */
	private static final String FETCH = "/api/analytics/fetch";

	/** The Constant FETCH_CHART. */
	private static final String FETCH_CHART = "/api/analytics/fetchChart";

	/** The Constant FETCH_ANALYTICS_GROUP. */
	private static final String FETCH_ANALYTICS_GROUP = "/api/analytics/fetchAllGroup";

	/**
	 * Gets the analytics mock.
	 * 
	 * @return the analytics mock
	 */
	private AnalyticsBCFMockImpl getAnalyticsMock()
	{
		return (AnalyticsBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(AnalyticsAPIController.class).getAnalyticsBCF();
	}

	/**
	 * Fetch.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetch() throws Exception
	{
		// set request object
		setData("{\"group\":{\"id\":\"0\"},\"startRow\":0,\"endRow\":0,\"pageSize\":null,\"initialDate\":\"2013-08-16-04-00-00-000\",\"endDate\":\"2013-08-23-03-59-59-000\",\"analyticsTypeEnum\":\"LIGHTING_ALARM\",\"analyticsDateTypeEnum\":\"ONE_WEEK\"}");

		// Success situation
		getAnalyticsMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.groups", hasSize(25)));

		// Failure situation
		getAnalyticsMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getAnalyticsMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.groups", nullValue()));

		// Exception situation
		getAnalyticsMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Fetch chart.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchChart() throws Exception
	{
		// set request object
		setData("{\"group\":null,\"initialDate\":\"2013-08-16-04-00-00-000\",\"endDate\":\"2013-08-23-03-59-59-000\",\"analyticsTypeEnum\":\"LIGHTING_ALARM\",\"analyticsDateTypeEnum\":\"ONE_WEEK\"}");

		// Success situation
		getAnalyticsMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_CHART).andExpect(jsonPath("$.analyticsGroups", hasSize(5)));

		// Failure situation
		getAnalyticsMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_CHART).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getAnalyticsMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_CHART).andExpect(jsonPath("$.analyticsGroups", nullValue()));

		// Exception situation
		getAnalyticsMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_CHART).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void fetchGroup() throws Exception
	{
		// set request object
		setData("{\"group\":null,\"initialDate\":\"2013-08-16-04-00-00-000\",\"endDate\":\"2013-08-23-03-59-59-000\",\"analyticsTypeEnum\":\"LIGHTING_ALARM\",\"analyticsDateTypeEnum\":\"ONE_WEEK\"}");

		// Success situation
		getAnalyticsMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ANALYTICS_GROUP).andExpect(jsonPath("$.analyticsGroups", hasSize(5)));

		// Failure situation
		getAnalyticsMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_ANALYTICS_GROUP).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getAnalyticsMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_ANALYTICS_GROUP).andExpect(jsonPath("$.analyticsGroups", nullValue()));

		// Exception situation
		getAnalyticsMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_ANALYTICS_GROUP).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

}