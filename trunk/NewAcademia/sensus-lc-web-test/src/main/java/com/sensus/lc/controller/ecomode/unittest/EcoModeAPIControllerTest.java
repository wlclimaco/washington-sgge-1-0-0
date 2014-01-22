package com.sensus.lc.controller.ecomode.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.ecomode.EcoModeAPIController;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

/**
 * The Class AnalyticsAPIControllerTest.
 */
public class EcoModeAPIControllerTest extends AbstractTestBase
{
	/** The Constant MAP_UPSERT. */
	private static final String MAP_UPSERT = "/api/ecomode/upsert";

	/** The Constant MAP_FETCH. */
	private static final String MAP_FETCH = "/api/ecomode/fetch";

	private EcoModeBCFMockImpl getEcoModeMock()
	{
		return (EcoModeBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(EcoModeAPIController.class).getEcoModeBCF();
	}

	@Test
	public void fetchAll() throws Exception
	{
		// set request object
		setData("{\"datePattern\":\"mm/dd/yy\",\"timezone\":\"US/Eastern\",\"action\":\"map\",\"pageSize\":25,\"selectionPaginationIds\":[\"1\"],\"initialDate\":\"2013-05-23-00-00-00-000\",\"endDate\":\"2013-08-23-23-59-59-999\",\"sortExpressions\":[{\"field\":\"CONSUMPTION_DAY\",\"direction\":\"Descending\"}],\"startRow\":0,\"endRow\":0}");

		// Success situation
		getEcoModeMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_FETCH).andExpect(jsonPath("$.lightConsumptions", hasSize(1)));

		// Failure situation
		getEcoModeMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getEcoModeMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(MAP_FETCH).andExpect(jsonPath("$.lightConsumptions", nullValue()));

		// Exception situation
		getEcoModeMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// set request object
		setData("{\"datePattern\":\"mm/dd/yy\",\"timezone\":\"US/Eastern\",\"pageSize\":25,\"selectionPaginationIds\":[\"1\"],\"initialDate\":\"2013-05-23-00-00-00-000\",\"endDate\":\"2013-08-23-23-59-59-999\",\"sortExpressions\":[{\"field\":\"CONSUMPTION_DAY\",\"direction\":\"Descending\"}],\"startRow\":0,\"endRow\":0}");

		// Success situation
		getEcoModeMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_FETCH).andExpect(jsonPath("$.lightConsumptions", hasSize(1)));

		// Failure situation
		getEcoModeMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getEcoModeMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(MAP_FETCH).andExpect(jsonPath("$.lightConsumptions", nullValue()));

		// Exception situation
		getEcoModeMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	@Test
	public void updateBaseLine() throws Exception
	{
		// set request object
		setData("{\"lights\":[{\"id\":1009,\"protect\":null,\"poleId\":\"POLEA_5\",\"ecoModeBaseline\":{\"replacedType\":\"MH\",\"replacedWattage\":\"200\"}}]}");
		// Success situation
		getEcoModeMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(MAP_UPSERT).andExpect(jsonPath("$.lights[0].ecoModeBaseline", notNullValue()));

		// Failure situation
		getEcoModeMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(MAP_UPSERT).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getEcoModeMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(MAP_UPSERT).andExpect(jsonPath("$.lights", nullValue()));

		// Exception situation
		getEcoModeMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(MAP_UPSERT).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}
}