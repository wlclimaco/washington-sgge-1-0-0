package com.sensus.lc.controller.maps.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.light.unittest.LightBCFMockImpl;
import com.sensus.lc.controller.maps.MapsAPIController;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

public class MapsAPIControllerTest extends AbstractTestBase
{

	/** The Constant FETCH. */
	private static final String MAP_FETCH = "/api/maps/fetch";

	private static final String MAP_FETCH_BOUNDS = "/api/maps/fetchbounds";

	/**
	 * Fetch.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetch() throws Exception
	{
		setData("{\"lightCriteria\":{\"lifeCycleStateList\":[],\"geoCodeCriteria\":{\"bottomLeftLat\":34.47609962554774,\"bottomLeftLong\":-83.47552547872068,\"topRightLat\":37.1483771440977,\"topRightLong\":-74.17559872127933,\"trunc\":1},\"ecomodeFilter\":[]},\"groupCriteria\":{},\"alertCriteria\":{\"alertTypeList\":[],\"alertSubtypeList\":[]},\"addressCriteria\":{},\"processCriteria\":{},\"scheduleCriteria\":{\"lightIdList\":null,\"lightSchedule\":[]},\"configurationCriteria\":{},\"tagCriteria\":{},\"startRow\":0,\"endRow\":0,\"pageSize\":\"25\",\"sortExpressions\":[]}");

		// Success situation
		getMapsMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_FETCH).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Exception situation
		getMapsMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	@Test
	public void fetchBounds() throws Exception
	{
		setData("{\"lightCriteria\":{\"lifeCycleStateList\":[],\"geoCodeCriteria\":{\"bottomLeftLat\":34.47609962554774,\"bottomLeftLong\":-83.47552547872068,\"topRightLat\":37.1483771440977,\"topRightLong\":-74.17559872127933,\"trunc\":1},\"ecomodeFilter\":[]},\"groupCriteria\":{},\"alertCriteria\":{\"alertTypeList\":[],\"alertSubtypeList\":[]},\"addressCriteria\":{},\"processCriteria\":{},\"scheduleCriteria\":{\"lightIdList\":null,\"lightSchedule\":[]},\"configurationCriteria\":{},\"tagCriteria\":{},\"startRow\":0,\"endRow\":0,\"pageSize\":\"25\",\"sortExpressions\":[]}");

		// Success situation
		getMapsMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_FETCH_BOUNDS).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Exception situation
		getMapsMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_FETCH_BOUNDS).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	private LightBCFMockImpl getMapsMock()
	{
		return (LightBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(MapsAPIController.class).getLightBCF();
	}

}
