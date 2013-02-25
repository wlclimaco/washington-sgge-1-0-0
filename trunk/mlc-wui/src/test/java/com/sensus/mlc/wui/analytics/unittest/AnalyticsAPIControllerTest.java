package com.sensus.mlc.wui.analytics.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.mlc.wui.analytics.AnalyticsAPIController;
import com.sensus.mlc.wui.util.AbstractTestBase;
import com.sensus.mlc.wui.util.ModeEnum;
import com.sensus.mlc.wui.util.TestMessageEnum;

/**
 * The Class AnalyticsAPIControllerTest.
 */
public class AnalyticsAPIControllerTest extends AbstractTestBase
{

	/** The Constant FETCH. */
	private static final String FETCH = "/api/analytics/fetch";

	/** The Constant FETCH_CHART. */
	private static final String FETCH_CHART = "/api/analytics/fetchChart";

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
		setData("{\"group\":{\"id\":\"0\"},\"startRow\":0,\"endRow\":0,\"pageSize\":25,\"initialDate\":\"2013-01-10T05:00:00.000Z\",\"endDate\":\"2013-01-17T04:59:59.000Z\",\"analyticsTypeEnum\":\"LIGHTING_ALARM\",\"analyticsDateTypeEnum\":\"ONE_WEEK\"}");

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
		setData("{\"group\":null,\"initialDate\":\"2013-01-10T02:00:00.000Z\",\"endDate\":\"2013-01-17T01:59:59.000Z\",\"analyticsTypeEnum\":\"LIGHTING_ALARM\",\"analyticsDateTypeEnum\":\"ONE_WEEK\"}");

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

}