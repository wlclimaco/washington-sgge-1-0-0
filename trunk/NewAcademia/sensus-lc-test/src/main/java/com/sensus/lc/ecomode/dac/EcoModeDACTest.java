package com.sensus.lc.ecomode.dac;

import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.lc.base.TestBaseUtil.createEcoModeRequest;
import static com.sensus.lc.base.TestBaseUtil.createInquiryEcoModeRequest;
import static com.sensus.lc.base.TestBaseUtil.createUserContext;
import static com.sensus.lc.base.TestBaseUtil.getLightTypeRandom;
import static com.sensus.lc.base.TestBaseUtil.getWattageRandom;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusDateUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.AbstractTestBaseDAC;
import com.sensus.lc.ecomode.model.EcoModeBaseline;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.light.model.Consumption;
import com.sensus.lc.light.model.Light;

/**
 * The Class EcoModeDACTest.
 */
public class EcoModeDACTest extends AbstractTestBaseDAC
{
	/** Attributes. */
	private static final Integer ONE = 1;
	private static final Integer THREE = 3;
	private static final Integer NTHIRTH = -30;
	private static final int THIRTH = 30;
	private static final Integer NFORTY = -40;
	private static final Integer FORTY = 40;
	private Light lightDefault;

	/**
	 * Reset data.
	 */
	@After
	public void resetData()
	{
		resetGroupsToUser(createUserContext());
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setCacheStatementScope(getEcoModeDAC());
	}

	/**
	 * Gets the light default.
	 * 
	 * @return the light default
	 */
	public Light getLightDefault()
	{
		if (lightDefault == null)
		{
			lightDefault = insertLight();
		}
		return lightDefault;
	}

	/**
	 * Test upsert eco mode.
	 */
	@Test
	public void testUpsertEcoMode()
	{
		// Success situation
		insertLightConsumption(getLightDefault(), 1);
		upsertEcoMode(getLightDefault());
	}

	/**
	 * Test upsert light consumptions.
	 */
	@Test
	public void testUpsertLightConsumptions()
	{
		// Success situation
		insertLightConsumption(getLightDefault(), ONE);
		EcoModeRequest ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.addLight(getLightDefault());
		InternalResponse response = getEcoModeDAC().updateLightConsumption(ecoModeRequest);
		assertResponse(response);
	}

	/**
	 * Test update calculation retroactive ecomode.
	 */
	@Test
	public void testUpdateCalculationRetroactiveEcomode()
	{
		// Success situation
		Light light = getLightDefault();
		EcoModeBaseline baseline = new EcoModeBaseline(getLightTypeRandom(), getWattageRandom(), true);
		light.setEcoModeBaseline(baseline);
		EcoModeRequest ecoModeRequest = createEcoModeRequest();
		ecoModeRequest.addLight(light);
		InternalResponse response = getEcoModeDAC().updateCalculationRetroactiveEcomode(ecoModeRequest);
		assertResponse(response);
	}

	/**
	 * Test insert light consumption.
	 */
	@Test
	public void testInsertLightConsumption()
	{
		insertLightConsumption(getLightDefault(), 1);
	}

	@Test
	public void testFetchEcoModeByLight()
	{
		Light light = insertLight();
		insertLightConsumption(light, 1);
		upsertEcoMode(light);
		EcoModeBaseline baselinePersisted = light.getEcoModeBaseline();
		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setLight(light);

		InternalResultsResponse<Light> response = getEcoModeDAC().fetchEcoModeByLight(ecoModeRequest);
		assertResultResponse(response);

		// filter by pole id
		EcoModeBaseline baselineSearched = response.getFirstResult().getEcoModeBaseline();
		assertEquals(baselinePersisted.getReplacedType(), baselineSearched.getReplacedType());
		assertEquals(baselinePersisted.getReplacedWattage(), baselineSearched.getReplacedWattage());

		// filter by pole id and light id
		ecoModeRequest.setLight(light);
		response = getEcoModeDAC().fetchEcoModeByLight(ecoModeRequest);
		assertResultResponse(response);
		baselineSearched = response.getFirstResult().getEcoModeBaseline();
		assertEquals(baselinePersisted.getReplacedType(), baselineSearched.getReplacedType());
		assertEquals(baselinePersisted.getReplacedWattage(), baselineSearched.getReplacedWattage());

		// User access the light
		Group group1 = insertGroup();
		addLightToGroup(light, group1);

		setGroupsToUser(ecoModeRequest.getUserContext(), group1);
		response = getEcoModeDAC().fetchEcoModeByLight(ecoModeRequest);
		assertResultResponse(response);

		// User do not access the light
		resetGroupsToUser(ecoModeRequest.getUserContext());
		Group group2 = insertGroup();
		setGroupsToUser(ecoModeRequest.getUserContext(), group2);

		response = getEcoModeDAC().fetchEcoModeByLight(ecoModeRequest);
		assertResponse(response);
		assertFalse(response.hasResults());
	}

	/**
	 * Test fetch light consumptions to chart.
	 */
	@Test
	public void testFetchLightConsumptionsToChart()
	{
		// Setup situation
		Light light = getLightDefault();
		insertLightConsumption(light, FORTY);
		upsertEcoMode(light);

		// Setup condition 1
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, -1);
		Date endDate = calendar.getTime();

		calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, NTHIRTH);
		Date initialDate = calendar.getTime();

		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setLight(light);
		ecoModeRequest.setEndDate(endDate);
		ecoModeRequest.setInitialDate(initialDate);

		InternalResultsResponse<Consumption> response =
				getEcoModeDAC().fetchLightConsumptionsToChart(ecoModeRequest);
		assertResultResponse(response);
		assertEquals(THIRTH, response.getResultsList().size());

		// Setup condition 2
		calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, NFORTY);
		initialDate = calendar.getTime();
		ecoModeRequest.setInitialDate(initialDate);

		response = getEcoModeDAC().fetchLightConsumptionsToChart(ecoModeRequest);
		assertResultResponse(response);
		assertEquals(2, response.getResultsList().size());
	}

	/**
	 * Test fetch light consumptions by light id.
	 */
	@Test
	public void testFetchLightConsumptionsByLightId()
	{
		Light light = insertLight();
		insertLightConsumption(light, THREE);
		upsertEcoMode(light);

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 0);
		Date endDate = calendar.getTime();

		calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, NTHIRTH);
		Date initialDate = calendar.getTime();

		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setLight(light);
		ecoModeRequest.setEndDate(endDate);
		ecoModeRequest.setInitialDate(initialDate);

		InternalResultsResponse<Consumption> response =
				getEcoModeDAC().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertResultResponse(response);
		assertLightConsumptions(response);

		// User access the light
		Group group1 = insertGroup();
		addLightToGroup(light, group1);

		response = getEcoModeDAC().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertResultResponse(response);
		assertLightConsumptions(response);

		// User do not access the light
		resetGroupsToUser(ecoModeRequest.getUserContext());
		Group group2 = insertGroup();
		setGroupsToUser(ecoModeRequest.getUserContext(), group2);

		response = getEcoModeDAC().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertResponse(response);
		assertEquals(ecoModeRequest.getPageSize().intValue(), response.getResultsList().size());
	}

	@Test
	public void testFetchAllLightConsumptionsByLightId()
	{
		Light light = insertLight();
		insertLightConsumption(light, THREE);
		upsertEcoMode(light);

		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setLight(light);

		InternalResultsResponse<Consumption> response =
				getEcoModeDAC().fetchAllLightConsumptionsByLightId(ecoModeRequest);
		assertResultResponse(response);
		assertLightConsumptions(light, response);

		// User access the light
		Group group1 = insertGroup();
		addLightToGroup(light, group1);

		response = getEcoModeDAC().fetchAllLightConsumptionsByLightId(ecoModeRequest);
		assertResultResponse(response);
		assertLightConsumptions(light, response);

		// User do not access the light
		resetGroupsToUser(ecoModeRequest.getUserContext());
		Group group2 = insertGroup();
		setGroupsToUser(ecoModeRequest.getUserContext(), group2);

		response = getEcoModeDAC().fetchAllLightConsumptionsByLightId(ecoModeRequest);
		assertResponse(response);
		assertFalse(response.hasResults());
	}

	@Test
	public void fetchAllLightsToCalculateEcoMode()
	{
		Light light = insertLight();
		insertLightConsumption(light, THREE);
		upsertEcoMode(light);

		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setLight(light);

		Integer amount = getEcoModeDAC().fetchCountAllLightsToCalculateEcoMode(ecoModeRequest);
		assertTrue(amount > 0);

		InternalResultsResponse<Light> response =
				getEcoModeDAC().fetchAllLightsToCalculateEcoMode(ecoModeRequest);
		assertResultResponse(response);

		// User access the light
		Group group1 = insertGroup();
		addLightToGroup(light, group1);

		response = getEcoModeDAC().fetchAllLightsToCalculateEcoMode(ecoModeRequest);
		assertResultResponse(response);

		// User do not access the light
		resetGroupsToUser(ecoModeRequest.getUserContext());
		Group group2 = insertGroup();
		setGroupsToUser(ecoModeRequest.getUserContext(), group2);

		response = getEcoModeDAC().fetchAllLightsToCalculateEcoMode(ecoModeRequest);
		assertResponse(response);
		assertFalse(response.hasResults());
	}

	/**
	 * Test update analytics data.
	 */
	@Test
	public void testUpdateAnalyticsData()
	{
		// This is just void, without nothing assert
		getEcoModeDAC().updateAnalyticsData();
	}

	/**
	 * Assert light consumptions.
	 * 
	 * @param response the response
	 */
	private void assertLightConsumptions(InternalResultsResponse<Consumption> response)
	{
		assertLightConsumptions(getLightDefault(), response);
	}

	/**
	 * Assert light consumptions.
	 * 
	 * @param light the light
	 * @param response the response
	 */
	private void assertLightConsumptions(Light light, InternalResultsResponse<Consumption> response)
	{
		for (Consumption lightConsumption : light.getConsumptions())
		{
			if (!checkLightConsumption(response.getResultsList(), lightConsumption))
			{
				fail("The light consumption is out range date: " + lightConsumption.getDay());
			}
		}
	}

	/**
	 * Check light consumption.
	 * 
	 * @param lightConsumptions the light consumptions
	 * @param consumption the consumption
	 * @return true, if successful
	 */
	private boolean checkLightConsumption(List<Consumption> lightConsumptions, Consumption consumption)
	{
		if (ValidationUtil.isNullOrEmpty(lightConsumptions) || ValidationUtil.isNull(consumption))
		{
			return false;
		}

		Date consumptionDay = SensusDateUtil.truncateTime(consumption.getDay());
		Double wattage = consumption.getConsumption();
		for (Consumption lightConsumption : lightConsumptions)
		{
			Date day = SensusDateUtil.truncateTime(lightConsumption.getDay());
			if (consumptionDay.equals(day) && wattage.equals(lightConsumption.getConsumption()))
			{
				return true;
			}
		}
		return false;
	}

}
