package com.sensus.mlc.ecomode.dac;

import static com.sensus.mlc.base.TestBaseUtil.assertResponse;
import static com.sensus.mlc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.mlc.base.TestBaseUtil.createInquiryEcoModeRequest;
import static com.sensus.mlc.base.TestBaseUtil.createUserContext;
import static com.sensus.mlc.base.TestBaseUtil.getLightTypeRandom;
import static com.sensus.mlc.base.TestBaseUtil.getWattageRandom;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusDateUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.AbstractTestBaseDAC;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.ecomode.csv.EcoModeCSVDataSource;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.LightConsumption;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.smartpoint.model.Light;

/**
 * The Class EcoModeDACTest.
 */
public class EcoModeDACTest extends AbstractTestBaseDAC
{
	/** The Constant TWENTY_FIVE. */
	private static final Integer TWENTY_FIVE = 25;

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant FIVE. */
	private static final Integer FIVE = 5;

	/** The Constant NTHIRTH. */
	private static final Integer NTHIRTH = -30;

	/** The Constant THIRTH. */
	private static final int THIRTH = 30;

	/** The Constant NFORTY. */
	private static final Integer NFORTY = -40;

	/** The Constant FORTY. */
	private static final Integer FORTY = 40;

	/** The Constant TEN. */
	private static final Integer TEN = 10;

	/** The light default. */
	private Light lightDefault;

	/**
	 * Gets the light default.
	 * 
	 * @return the light default
	 */
	public Light getLightDefault()
	{
		if (this.lightDefault == null)
		{
			this.lightDefault = insertLight();
		}
		return this.lightDefault;
	}

	/**
	 * Reset data.
	 */
	@After
	public void resetData()
	{
		resetGroupsToUser(createUserContext());
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

		// Light not exist situation
		Light light = new Light();
		light.setId(-1);

		EcoModeBaseline baseline = new EcoModeBaseline(light, getLightTypeRandom(), getWattageRandom(), false);
		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.addEcoModeBaseline(baseline);

		InternalResultsResponse<EcoModeBaseline> response = getEcoModeDAC().upsertEcoMode(ecoModeRequest);
		assertFalse(response.hasResults());
		assertEquals(response.getStatus(), Status.NoRowsUpdatedError);
	}

	/**
	 * Test upsert light consumptions.
	 */
	@Test
	public void testUpsertLightConsumptions()
	{
		// Success situation
		insertLightConsumption(getLightDefault(), TEN);
		List<LightConsumption> lightConsumptions = getLightDefault().getConsumptions();
		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setLight(getLightDefault());
		ecoModeRequest.setLightConsumptions(lightConsumptions);
		InternalResponse response = getEcoModeDAC().updateLightConsumptions(ecoModeRequest);
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
		EcoModeBaseline baseline = new EcoModeBaseline(light, getLightTypeRandom(), getWattageRandom(), true);
		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.addEcoModeBaseline(baseline);
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

	/**
	 * Test fetch eco mode by pole id.
	 */
	@Test
	public void testFetchEcoModeByPoleId()
	{
		Light light = insertLight();
		insertLightConsumption(light, 1);
		EcoModeBaseline baselinePersisted = upsertEcoMode(light);
		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.addEcoModeBaseline(baselinePersisted);

		InternalResultsResponse<EcoModeBaseline> response = getEcoModeDAC().fetchEcoModeByPoleId(ecoModeRequest);
		assertResultResponse(response);

		// filter by pole id
		EcoModeBaseline baselineSearched = response.getFirstResult();
		assertEquals(baselinePersisted.getLightId().getId(), baselineSearched.getLightId().getId());
		assertEquals(baselinePersisted.getLightId().getEcoMode(), baselineSearched.getLightId().getEcoMode());
		assertEquals(baselinePersisted.getReplacedType(), baselineSearched.getReplacedType());
		assertEquals(baselinePersisted.getReplacedWattage(), baselineSearched.getReplacedWattage());

		// filter by pole id and light id
		ecoModeRequest.setLight(light);
		response = getEcoModeDAC().fetchEcoModeByPoleId(ecoModeRequest);
		assertResultResponse(response);
		baselineSearched = response.getFirstResult();
		assertEquals(baselinePersisted.getLightId().getId(), baselineSearched.getLightId().getId());
		assertEquals(baselinePersisted.getLightId().getEcoMode(), baselineSearched.getLightId().getEcoMode());
		assertEquals(baselinePersisted.getReplacedType(), baselineSearched.getReplacedType());
		assertEquals(baselinePersisted.getReplacedWattage(), baselineSearched.getReplacedWattage());

		// User access the light
		Group group1 = insertGroup();
		addLightToGroup(light, group1);

		this.setGroupsToUser(ecoModeRequest.getUserContext(), group1);
		response = getEcoModeDAC().fetchEcoModeByPoleId(ecoModeRequest);
		assertResultResponse(response);

		// User do not access the light
		resetGroupsToUser(ecoModeRequest.getUserContext());
		Group group2 = insertGroup();
		this.setGroupsToUser(ecoModeRequest.getUserContext(), group2);

		response = getEcoModeDAC().fetchEcoModeByPoleId(ecoModeRequest);
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

		InternalResultsResponse<LightConsumption> response =
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

		// Setup condition 3
		Group group = insertGroup();
		this.setGroupsToUser(ecoModeRequest.getUserContext(), group);

		response = getEcoModeDAC().fetchLightConsumptionsToChart(ecoModeRequest);
		assertFalse(response.hasResults());
	}

	/**
	 * Test fetch light consumptions by light id.
	 */
	@Test
	public void testFetchLightConsumptionsByLightId()
	{
		Light light = getLightDefault();
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

		InternalResultsResponse<LightConsumption> response =
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
		this.setGroupsToUser(ecoModeRequest.getUserContext(), group2);

		response = getEcoModeDAC().fetchLightConsumptionsByLightId(ecoModeRequest);
		assertResponse(response);
		assertEquals(ecoModeRequest.getPageSize().intValue(), response.getResultsList().size());
	}

	/**
	 * Test fetch all lights to calculate eco mode.
	 */
	@Test
	public void testFetchAllLightsToCalculateEcoMode()
	{
		int pageSize = TEN;
		int lightsAmount = TWENTY_FIVE;

		List<Light> lights = insertLights(lightsAmount);
		for (Light light : lights)
		{
			upsertEcoMode(light);
		}

		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		ecoModeRequest.setPageSize(pageSize);

		Integer resultAmount = getEcoModeDAC().fetchCountAllLightsToCalculateEcoMode(ecoModeRequest);
		assertTrue((resultAmount != null) && (resultAmount >= lightsAmount));

		int pageAmount = resultAmount / pageSize;
		int mod = resultAmount % pageSize;

		InternalResultsResponse<EcoModeBaseline> response = null;

		for (int i = 0; i <= pageAmount; i++)
		{
			ecoModeRequest.setStartPage(i);
			response = getEcoModeDAC().fetchAllLightsToCalculateEcoMode(ecoModeRequest);

			if ((i == pageAmount) && (mod == 0))
			{
				break;
			}

			assertResultResponse(response);
			if (i != pageAmount)
			{
				assertEquals(pageSize, response.getResultsList().size());
			}
		}

		// User do not access the light
		resetGroupsToUser(ecoModeRequest.getUserContext());
		Group group = insertGroup();
		this.setGroupsToUser(ecoModeRequest.getUserContext(), group);

		response = getEcoModeDAC().fetchAllLightsToCalculateEcoMode(ecoModeRequest);
		assertFalse(response.hasResults());
	}

	/**
	 * Test fetch count all lights to calculate eco mode.
	 */
	@Test
	public void testFetchCountAllLightsToCalculateEcoMode()
	{
		int lightsAmount = FIVE;
		List<Light> lights = insertLights(lightsAmount);
		for (Light light : lights)
		{
			upsertEcoMode(light);
		}

		InquiryEcoModeRequest ecoModeRequest = createInquiryEcoModeRequest();
		Integer amount = getEcoModeDAC().fetchCountAllLightsToCalculateEcoMode(ecoModeRequest);
		assertTrue((amount != null) && (amount >= lightsAmount));

		// User do not access the light
		resetGroupsToUser(ecoModeRequest.getUserContext());
		Group group = insertGroup();
		this.setGroupsToUser(ecoModeRequest.getUserContext(), group);

		amount = getEcoModeDAC().fetchCountAllLightsToCalculateEcoMode(ecoModeRequest);
		assertTrue((amount == null) || (amount == 0));
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
	 * Test generate file csv.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGenerateFileCSV() throws IOException
	{
		// Test success without light consumptions
		InquiryEcoModeRequest ecoModeRequest = TestBaseUtil.createInquiryEcoModeRequest();
		ecoModeRequest.setFileName(TestBaseUtil.FILE_NAME);

		InternalResponse response = getEcoModeDAC().generateFileCSV(ecoModeRequest);
		assertResponse(response);

		// check dimensions...
		EcoModeCSVDataSource ds = new EcoModeCSVDataSource(ecoModeRequest);
		TestBaseUtil.checkCSVFileDimensions(0, ds);

		// Test success with light consumptions
		Light light = insertLight();
		insertLightConsumption(light, FIVE);
		upsertEcoMode(light);

		List<LightConsumption> lightConsumptions = light.getConsumptions();
		ecoModeRequest.setLightConsumptions(lightConsumptions);

		response = getEcoModeDAC().generateFileCSV(ecoModeRequest);
		assertResponse(response);

		// check dimensions...
		TestBaseUtil.checkCSVFileDimensions(lightConsumptions.size(), ds);

	}

	/**
	 * Assert light consumptions.
	 * 
	 * @param response the response
	 */
	private void assertLightConsumptions(InternalResultsResponse<LightConsumption> response)
	{
		List<LightConsumption> lightConsumptions = getLightDefault().getConsumptions();
		for (LightConsumption lightConsumption : lightConsumptions)
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
	private boolean checkLightConsumption(List<LightConsumption> lightConsumptions, LightConsumption consumption)
	{
		if (ValidationUtil.isNullOrEmpty(lightConsumptions) || ValidationUtil.isNull(consumption))
		{
			return false;
		}

		Date consumptionDay = SensusDateUtil.truncateTime(consumption.getDay());
		Double wattage = consumption.getConsumption();
		for (LightConsumption lightConsumption : lightConsumptions)
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
