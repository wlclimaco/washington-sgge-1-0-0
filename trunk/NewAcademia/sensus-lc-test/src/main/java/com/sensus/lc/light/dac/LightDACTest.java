package com.sensus.lc.light.dac;

import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.lc.base.TestBaseUtil.createCommunicationFailureRequest;
import static com.sensus.lc.base.TestBaseUtil.createConfigurationMaintenanceRequest;
import static com.sensus.lc.base.TestBaseUtil.createFetchByIdRequest;
import static com.sensus.lc.base.TestBaseUtil.createLastOperationalDataMaintenanceRequest;
import static com.sensus.lc.base.TestBaseUtil.createLightMaintenanceRequest;
import static com.sensus.lc.base.TestBaseUtil.createLightRequest;
import static com.sensus.lc.base.TestBaseUtil.createOperationalData;
import static com.sensus.lc.base.TestBaseUtil.createOperationalDataValueMaintenanceRequest;
import static com.sensus.lc.base.TestBaseUtil.createScheduleMaintenanceRequest;
import static com.sensus.lc.base.TestBaseUtil.createUserContext;
import static com.sensus.lc.light.model.LightOrderByEnum.LAMP_TYPE_WATTAGE_DIMMABLE;
import static com.sensus.lc.light.model.LightOrderByEnum.LIGHT_STATE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.Radio;
import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.CachedResultsResponse;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.query.SearchDate;
import com.sensus.common.query.SearchDate.DateSearchType;
import com.sensus.common.util.SensusDateUtil;
import com.sensus.lc.base.AbstractTestBaseDAC;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.util.LCConvertUtil;
import com.sensus.lc.light.model.AlertClassification;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.Column;
import com.sensus.lc.light.model.Configuration;
import com.sensus.lc.light.model.CustomSearch;
import com.sensus.lc.light.model.DeleteLightReferenceEnum;
import com.sensus.lc.light.model.Filter;
import com.sensus.lc.light.model.FilterEnum;
import com.sensus.lc.light.model.GeocodeLightInfo;
import com.sensus.lc.light.model.LastOperationalData;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.LightColumnEnum;
import com.sensus.lc.light.model.LightHistory;
import com.sensus.lc.light.model.LightPropertyForSearchEnum;
import com.sensus.lc.light.model.LightSchedule;
import com.sensus.lc.light.model.LightTextSearch;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.NotificationTypeEnum;
import com.sensus.lc.light.model.OperationalData;
import com.sensus.lc.light.model.PrecedenceEnum;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.SearchParameter;
import com.sensus.lc.light.model.criteria.AlertCriteria;
import com.sensus.lc.light.model.criteria.ConfigurationCriteria;
import com.sensus.lc.light.model.criteria.GeoCodeCriteria;
import com.sensus.lc.light.model.criteria.GroupCriteria;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.criteria.LightCustomSearchCriteria;
import com.sensus.lc.light.model.criteria.NotificationHistoryCriteria;
import com.sensus.lc.light.model.criteria.ProcessCriteria;
import com.sensus.lc.light.model.criteria.ScheduleCriteria;
import com.sensus.lc.light.model.criteria.SearchTerm;
import com.sensus.lc.light.model.request.AlertClassificationMaintenanceRequest;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.request.CommunicationFailureRequest;
import com.sensus.lc.light.model.request.ConfigurationMaintenanceRequest;
import com.sensus.lc.light.model.request.CustomSearchRequest;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LastOperationalDataMaintenanceRequest;
import com.sensus.lc.light.model.request.LightDeleteRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.NotificationHistoryMaintenanceRequest;
import com.sensus.lc.light.model.request.NotificationHistoryRequest;
import com.sensus.lc.light.model.request.OperationalDataMaintenanceRequest;
import com.sensus.lc.light.model.request.ScheduleMaintenanceRequest;
import com.sensus.lc.process.model.LCActionCategoryEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessFilter;
import com.sensus.lc.schedule.model.EventSchedule;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.tag.model.Tag;
import com.sensus.lc.user.model.User;

/**
 * Unit test for Light DAC.
 * 
 * @author Thiago
 * 
 */
@ContextConfiguration(locations = {
		"classpath:sensus-slc-dim-table-config-context.xml", "classpath:sensus-slc-part-number-context.xml"})
public class LightDACTest extends AbstractTestBaseDAC
{

	/** The Constant ONE_THOUSAND. */
	private static final int ONE_THOUSAND = 1000;

	/** The Constant USER_ID. */
	private static final String USER_ID = "QAT";

	/** The Constant RESPONSE_SHOULD_NOT_CONTAIN_SOME_RESULTS. */
	private static final String RESPONSE_SHOULD_NOT_CONTAIN_SOME_RESULTS = "response should not contain some results";

	/** The Constant RANDOM. */
	public static final Random RANDOM = new Random();

	/** The Constant NUMBER_RANGE. */
	public static final Integer NUMBER_RANGE = Integer.MAX_VALUE;

	/** The Constant THREE. */
	private static final Integer NUMBER_THREE = 3;

	/** The Constant ONE_NEGATIVE. */
	private static final Integer ONE_NEGATIVE = -1;

	/** The Constant _ONE. */
	private static final Integer ONE = 1;

	/** The Constant TWO. */
	private static final Integer TWO = 2;

	/** The Constant ARBITRARY_PAGE_SIZE_25. */
	private static final Integer ARBITRARY_PAGE_SIZE_25 = 25;

	/** The Constant HOUSING_COLOR. */
	private static final String HOUSING_COLOR = "colortemp1";

	/** The Constant LIGHTS. */
	private static final String LIGHTS = " lights: ";

	/**
	 * Test insert lights.
	 */
	@Test
	public void testInsertLight()
	{
		// Success cases

		// Light object with all attributes filled
		Light light = insertLight();

		// Light object without configuration attribute
		light.setId(null);
		light.setConfiguration(null);

		LightMaintenanceRequest request = createLightMaintenanceRequest(createUserContext(), light);
		InternalResponse response = getLightDAC().insertLight(request);
		assertResponse(response);
		assertLight(light);

		// Light object without schedule attribute
		light.setId(null);
		light.setLightSchedule(null);
		response = getLightDAC().insertLight(request);
		assertResponse(response);
		assertLight(light);

		// Light object without lastOperationalData attribute
		light.setId(null);
		light.setLastOperationalData(null);
		response = getLightDAC().insertLight(request);
		assertResponse(response);
		assertLight(light);
	}

	// @Test
	/**
	 * Test update light base performance.
	 */
	public void testUpdateLightBasePerformance()
	{
		// Insert 25 lights
		for (int i = 0; i < ARBITRARY_PAGE_SIZE_25; i++)
		{
			insertLight();
		}

		UserContext userContext = createUserContext();
		LightRequest lightRequest = createLightRequest(userContext);
		lightRequest.setPageSize(ARBITRARY_PAGE_SIZE_25);
		// lightRequest.addColumnToReturn(LightColumnEnum.LIGHT_ID.getValue());

		long start = System.currentTimeMillis();

		InternalResultsResponse<Light> lightResponse = getLightDAC().fetchAllByRequest(lightRequest);

		if (!lightResponse.hasResults())
		{
			System.out.println("0 lights to update...");
		}

		String poleId = String.valueOf(RANDOM.nextInt(NUMBER_RANGE));
		int p = 0;
		for (Light light : lightResponse.getResultsList())
		{
			light.setPoleId(poleId);
			LightMaintenanceRequest request = createLightMaintenanceRequest(lightRequest.getUserContext(), light);

			light.setPoleId(String.valueOf(RANDOM.nextInt(NUMBER_RANGE)));
			getLightDAC().updateLightBase(request);

			p++;
			System.out.println(p);
		}

		System.out.println("[Using updateLightBase] - Total time to update 'pole id' to " + ARBITRARY_PAGE_SIZE_25
				+ LIGHTS
				+ (System.currentTimeMillis() - start) / ONE_THOUSAND + " seconds.");
	}

	/**
	 * Test update light base.
	 */
	@Test
	public void testUpdateLightBase()
	{
		// Change transaction cache to statement scope
		setCacheStatementScope(getLightDAC());

		// create new user instance
		UserContext userContext = createUserContext();

		// Change transaction cache to statement scope
		setCacheStatementScope(getLightDAC());

		// Light object with all attributes filled
		Light light = insertLight();

		// Change one value
		String newPoleIdValue = String.valueOf(RANDOM.nextInt(NUMBER_RANGE));
		Light newLight = new Light(light.getId());
		newLight.setModelAction(PersistanceActionEnum.UPDATE);
		newLight.setModifyUser(userContext.getUserId());
		newLight.setPoleId(newPoleIdValue);

		// Update success test
		LightMaintenanceRequest request = createLightMaintenanceRequest(userContext, newLight);
		InternalResponse response = getLightDAC().updateLightBase(request);
		assertResponse(response);

		// Fetch light to test persistence
		LightRequest lightRequest = createLightRequest(userContext);
		LightCriteria lightCriteria = new LightCriteria();
		lightCriteria.getLightIdList().add(light.getId());
		lightRequest.setLightCriteria(lightCriteria);
		InternalResultsResponse<Light> lightResponse = getLightDAC().fetchAllByRequest(lightRequest);
		assertResultResponse(lightResponse);

		Light lightUpdated = lightResponse.getFirstResult();
		assertEquals(newPoleIdValue, lightUpdated.getPoleId());

		// Test update without attributes
		newLight = new Light(light.getId());
		newLight.setModelAction(PersistanceActionEnum.UPDATE);
		request = createLightMaintenanceRequest(userContext, newLight);
		response = getLightDAC().updateLightBase(request);
		assertResponse(response);

		// Not changed
		lightResponse = getLightDAC().fetchAllByRequest(lightRequest);
		assertResultResponse(lightResponse);

		lightUpdated = lightResponse.getFirstResult();
		assertEquals(newPoleIdValue, lightUpdated.getPoleId());
	}

	/**
	 * Test update light.
	 */
	@Test
	public void testUpdateLight()
	{
		// Change transaction cache to statement scope
		setCacheStatementScope(getLightDAC());

		// create new user instance
		UserContext userContext = createUserContext();

		// Change transaction cache to statement scope
		setCacheStatementScope(getLightDAC());

		// Light object with all attributes filled
		Light light = insertLight();

		// Change one value
		String newPoleIdValue = String.valueOf(RANDOM.nextInt(NUMBER_RANGE));
		Float newCurrentValue = Float.valueOf(RANDOM.nextInt(NUMBER_RANGE));
		Integer newSunriseOffsetValue = RANDOM.nextInt(NUMBER_RANGE);
		String newSerialNumberValue = String.valueOf(RANDOM.nextInt(NUMBER_RANGE));

		light.setModelAction(PersistanceActionEnum.UPDATE);
		light.setModifyUser(userContext.getUserId());
		light.setPoleId(newPoleIdValue);
		light.getLastOperationalData().setModelAction(PersistanceActionEnum.UPDATE);
		light.getLastOperationalData().setAcCurrent(newCurrentValue);
		light.getLightSchedule().setModelAction(PersistanceActionEnum.UPDATE);
		light.getLightSchedule().setSunriseOffset(newSunriseOffsetValue);
		light.getConfiguration().setModelAction(PersistanceActionEnum.UPDATE);
		light.getConfiguration().setCustomerSerialNumber(newSerialNumberValue);

		// Update success test
		LightMaintenanceRequest request = createLightMaintenanceRequest(userContext, light);
		InternalResponse response = getLightDAC().updateLight(request);
		assertResponse(response);

		// Fetch light to test persistence
		LightRequest lightRequest = createLightRequest(userContext);
		LightCriteria lightCriteria = new LightCriteria();
		lightCriteria.getLightIdList().add(light.getId());
		lightRequest.setLightCriteria(lightCriteria);
		InternalResultsResponse<Light> lightResponse = getLightDAC().fetchAllByRequest(lightRequest);
		assertResultResponse(lightResponse);

		Light lightUpdated = lightResponse.getFirstResult();
		assertEquals(newPoleIdValue, lightUpdated.getPoleId());
		assertEquals(newCurrentValue, light.getLastOperationalData().getAcCurrent());
		assertEquals(newSunriseOffsetValue, light.getLightSchedule().getSunriseOffset());
		assertEquals(newSerialNumberValue, light.getConfiguration().getCustomerSerialNumber());

		// Test update without attributes
		light = new Light();
		light.setId(light.getId());
		light.setModelAction(PersistanceActionEnum.UPDATE);
		request = createLightMaintenanceRequest(userContext, light);
		response = getLightDAC().updateLight(request);
		assertResponse(response);

		// Not changed
		lightResponse = getLightDAC().fetchAllByRequest(lightRequest);
		assertResultResponse(lightResponse);

		lightUpdated = lightResponse.getFirstResult();
		assertEquals(newPoleIdValue, lightUpdated.getPoleId());
	}

	/**
	 * Test update last operational data.
	 */
	@Test
	public void testUpdateLastOperationalData()
	{
		// Change transaction cache to statement scope
		setCacheStatementScope(getLightDAC());

		// create new user instance
		UserContext userContext = createUserContext();

		// Change transaction cache to statement scope
		setCacheStatementScope(getLightDAC());

		// Light object with all attributes filled
		Light light = insertLight();

		// Change one value
		Float newConsumptionValue = Float.valueOf(RANDOM.nextInt(NUMBER_RANGE));
		LastOperationalData lod = new LastOperationalData();
		lod.setModelAction(PersistanceActionEnum.UPDATE);
		lod.setModifyUser(userContext.getUserId());
		lod.setConsumption(newConsumptionValue);

		// Update success test
		LastOperationalDataMaintenanceRequest request =
				createLastOperationalDataMaintenanceRequest(userContext, light.getId());
		request.setLastOperationalData(lod);
		InternalResponse response = getLightDAC().updateLastOperationalData(request);
		assertResponse(response);

		// Fetch light to test persistence
		LightRequest lightRequest = createLightRequest(userContext);
		ScheduleCriteria scheduleCriteria = new ScheduleCriteria();
		scheduleCriteria.getLightIdList().add(light.getId());
		lightRequest.setScheduleCriteria(scheduleCriteria);

		InternalResultsResponse<Light> lightResponse = getLightDAC().fetchAllByRequest(lightRequest);
		assertResultResponse(lightResponse);

		LastOperationalData lodUpdated = lightResponse.getFirstResult().getLastOperationalData();
		assertEquals(newConsumptionValue, lodUpdated.getConsumption());

		// Test update without attributes
		lod = new LastOperationalData();
		lod.setModelAction(PersistanceActionEnum.UPDATE);
		request = createLastOperationalDataMaintenanceRequest(userContext, light.getId());
		request.setLastOperationalData(lod);
		response = getLightDAC().updateLastOperationalData(request);
		assertResponse(response);

		// Not changed
		lightResponse = getLightDAC().fetchAllByRequest(lightRequest);
		assertResultResponse(lightResponse);

		lodUpdated = lightResponse.getFirstResult().getLastOperationalData();
		assertEquals(newConsumptionValue, lodUpdated.getConsumption());
	}

	/**
	 * Test update schedule.
	 */
	@Test
	public void testUpdateSchedule()
	{
		// Change transaction cache to statement scope
		setCacheStatementScope(getLightDAC());

		// create new user instance
		UserContext userContext = createUserContext();

		// Light object with all attributes filled
		Light light = insertLight();

		// Change one value
		Integer newSunriseOffsetValue = RANDOM.nextInt(NUMBER_RANGE);
		LightSchedule schedule = new LightSchedule();
		schedule.setModelAction(PersistanceActionEnum.UPDATE);
		schedule.setModifyUser(userContext.getUserId());
		schedule.setSunriseOffset(newSunriseOffsetValue);

		// Update success test
		ScheduleMaintenanceRequest request = createScheduleMaintenanceRequest(userContext, light.getId());
		request.setSchedule(schedule);
		InternalResponse response = getLightDAC().updateSchedule(request);
		assertResponse(response);

		// Fetch light to test persistence
		FetchByIdRequest lightRequest = createFetchByIdRequest(userContext, light.getId());
		InternalResultsResponse<Light> lightResponse = getLightDAC().fetchById(lightRequest);
		assertResultResponse(lightResponse);

		LightSchedule scheduleUpdated = lightResponse.getFirstResult().getLightSchedule();
		assertEquals(newSunriseOffsetValue, scheduleUpdated.getSunriseOffset());

		// Test update without attributes
		schedule = new LightSchedule();
		schedule.setModelAction(PersistanceActionEnum.UPDATE);
		request = createScheduleMaintenanceRequest(userContext, light.getId());
		request.setSchedule(schedule);
		response = getLightDAC().updateSchedule(request);
		assertResponse(response);

		// Not changed
		lightResponse = getLightDAC().fetchById(lightRequest);
		assertResultResponse(lightResponse);

		scheduleUpdated = lightResponse.getFirstResult().getLightSchedule();
		assertEquals(newSunriseOffsetValue, scheduleUpdated.getSunriseOffset());
	}

	/**
	 * Test update configuration.
	 */
	@Test
	public void testUpdateConfiguration()
	{
		// Change transaction cache to statement scope
		setCacheStatementScope(getLightDAC());

		// create new user instance
		UserContext userContext = createUserContext();

		// Light object with all attributes filled
		Light light = insertLight();

		// Change one value
		String newSerialNumberValue = String.valueOf(RANDOM.nextInt(NUMBER_RANGE));
		Configuration configuration = new Configuration();
		configuration.setModelAction(PersistanceActionEnum.UPDATE);
		configuration.setModifyUser(userContext.getUserId());
		configuration.setCustomerSerialNumber(newSerialNumberValue);

		// Update success test
		ConfigurationMaintenanceRequest request = createConfigurationMaintenanceRequest(userContext, light.getId());
		request.setConfiguration(configuration);
		InternalResponse response = getLightDAC().updateConfiguration(request);
		assertResponse(response);

		// Fetch light to test persistence
		LightRequest lightRequest = createLightRequest(userContext);
		LightCriteria lightCriteria = new LightCriteria();
		lightCriteria.getLightIdList().add(light.getId());
		lightRequest.setLightCriteria(lightCriteria);
		InternalResultsResponse<Light> lightResponse = getLightDAC().fetchAllByRequest(lightRequest);
		assertResultResponse(lightResponse);

		Configuration configurationUpdated = lightResponse.getFirstResult().getConfiguration();
		assertEquals(newSerialNumberValue, configurationUpdated.getCustomerSerialNumber());

		// Test update without attributes
		configuration = new Configuration();
		configuration.setModelAction(PersistanceActionEnum.UPDATE);
		request = createConfigurationMaintenanceRequest(userContext, light.getId());
		request.setConfiguration(configuration);
		response = getLightDAC().updateConfiguration(request);
		assertResponse(response);

		// Not changed
		lightResponse = getLightDAC().fetchAllByRequest(lightRequest);
		assertResultResponse(lightResponse);

		configurationUpdated = lightResponse.getFirstResult().getConfiguration();
		assertEquals(newSerialNumberValue, configurationUpdated.getCustomerSerialNumber());
	}

	/**
	 * Test fetch light by id rni.
	 */
	@Test
	public void testFetchLightById()
	{
		// Pre-conditions
		UserContext userContext = createUserContext();
		Light light = TestBaseUtil.createLight(userContext);
		insertLight(light);

		// Test success: light by id
		FetchByIdRequest request = createFetchByIdRequest(userContext, light.getId());
		InternalResultsResponse<Light> response = getLightDAC().fetchById(request);
		assertResultResponse(response);
		assertNotNull(response.getFirstResult().getConfiguration());

		// Test success: light by rni
		request.setLightId(null);
		request.setRniId(light.getRadio().getFlexNetId());
		response = getLightDAC().fetchById(request);
		assertResultResponse(response);

		// Test failure: light by id
		request.setLightId(ONE_NEGATIVE);
		request.setRniId(null);
		response = getLightDAC().fetchById(request);
		assertFalse(RESPONSE_SHOULD_NOT_CONTAIN_SOME_RESULTS, response.hasResults());
	}

	/**
	 * Test Fetch All Lights By Request.
	 * 
	 * @see ILightDAC (fetchAllByRequest)
	 */
	@Test
	public void testFetchAllLightsByRequestToLightCriteria()
	{
		// Pre-conditions
		UserContext userContext = createUserContext();
		Light light = TestBaseUtil.createLight(userContext);
		light.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		insertLight(light);

		// Light conditions
		LightCriteria lightCriteria = new LightCriteria();
		lightCriteria.getLightIdList().add(light.getId());
		LightRequest request = createLightRequest(userContext);
		request.setLightCriteria(lightCriteria);
		InternalResultsResponse<Light> response = getLightDAC().fetchAllByRequest(request);
		assertResultResponse(response);

		// Light order by light state desc
		lightCriteria = new LightCriteria();
		lightCriteria.getLightIdList().add(light.getId());
		request = createLightRequest(userContext);
		request.getSortExpressions().clear();
		request.addSortExpressions(new SortExpression(LIGHT_STATE.getValue(), Direction.Descending));
		request.setLightCriteria(lightCriteria);
		response = getLightDAC().fetchAllByRequest(request);
		assertResultResponse(response);

		// Light order by lamp type wattage dimmable
		lightCriteria = new LightCriteria();
		lightCriteria.getLightIdList().add(light.getId());
		request = createLightRequest(userContext);
		request.getSortExpressions().clear();
		request.addSortExpressions(new SortExpression(LAMP_TYPE_WATTAGE_DIMMABLE.getValue(), Direction.Descending));
		request.setLightCriteria(lightCriteria);
		response = getLightDAC().fetchAllByRequest(request);
		assertResultResponse(response);

		// Filter by not alert
		lightCriteria = new LightCriteria();
		lightCriteria.getLightIdList().add(light.getId());
		AlertCriteria alertCriteria = new AlertCriteria();
		alertCriteria.setNotAlert(true);
		request = createLightRequest(userContext);
		request.setLightCriteria(lightCriteria);
		response = getLightDAC().fetchAllByRequest(request);
		assertResultResponse(response);

		// Filter by flexnetId
		SearchTerm flexnetIdFilter = new SearchTerm(light.getRadio().getFlexNetId());
		lightCriteria = new LightCriteria();
		lightCriteria.getLightIdList().add(light.getId());
		lightCriteria.setFlexnetId(flexnetIdFilter);
		request = createLightRequest(userContext);
		request.setLightCriteria(lightCriteria);
		response = getLightDAC().fetchAllByRequest(request);
		assertResultResponse(response);

		// Filter by poleId
		SearchTerm poleIdFilter = new SearchTerm(light.getPoleId());
		lightCriteria = new LightCriteria();
		lightCriteria.getLightIdList().add(light.getId());
		lightCriteria.setPoleId(poleIdFilter);
		request = createLightRequest(userContext);
		request.setLightCriteria(lightCriteria);
		response = getLightDAC().fetchAllByRequest(request);
		assertResultResponse(response);

		// Pre-conditions by alerts
		AlertSubTypeEnum alertSubtype = AlertSubTypeEnum.POWER_FAILURE;
		NotificationHistory notification = light.getLastNotificationHistory();
		insertAlertClassification(userContext, notification.getId(), alertSubtype);

		// Filter by alertsubtype
		lightCriteria = new LightCriteria();
		lightCriteria.getLightIdList().add(light.getId());
		alertCriteria = new AlertCriteria();
		alertCriteria.getAlertSubtypeList().add(alertSubtype);
		request = createLightRequest(userContext);
		request.setLightCriteria(lightCriteria);
		response = getLightDAC().fetchAllByRequest(request);
		assertResultResponse(response);

		// Filter by alert
		lightCriteria = new LightCriteria();
		lightCriteria.getLightIdList().add(light.getId());
		alertCriteria = new AlertCriteria();
		alertCriteria.getAlertTypeList().add(alertSubtype.getAlertType());
		request = createLightRequest(userContext);
		request.setLightCriteria(lightCriteria);
		response = getLightDAC().fetchAllByRequest(request);
		assertResultResponse(response);

		// Filter by life cycle state
		lightCriteria = new LightCriteria();
		lightCriteria.getLightIdList().add(light.getId());
		lightCriteria.getLifeCycleStateList().add(notification.getLifeCycleState());
		request = createLightRequest(userContext);
		request.setLightCriteria(lightCriteria);
		response = getLightDAC().fetchAllByRequest(request);
		assertResultResponse(response);

		// Filter by modify date
		lightCriteria = new LightCriteria();
		lightCriteria.getLightIdList().add(light.getId());
		lightCriteria.setModifyDate(new SearchDate(SensusDateUtil.getMinDate(), DateSearchType.GREATER_THAN));
		request = createLightRequest(userContext);
		request.setLightCriteria(lightCriteria);
		response = getLightDAC().fetchAllByRequest(request);
		assertResultResponse(response);

	}

	/**
	 * Test Fetch All Lights By Request.
	 * 
	 * @see ILightDAC (fetchAllByRequest)
	 */
	@Test
	public void testFetchAllLightsByRequestToConfigurationCriteria()
	{
		// Pre-conditions
		UserContext userContext = createUserContext();
		Light light = TestBaseUtil.createLight(userContext);
		light.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		light.getConfiguration().setColorTemperature(HOUSING_COLOR);
		insertLight(light);

		// Lifecycle conditions
		LightCriteria lightCriteria = new LightCriteria();
		lightCriteria.addLifeCycleState(LifeCycleStateEnum.ACTIVE);
		lightCriteria.getLightIdList().add(light.getId());

		// Configuration criteria
		ConfigurationCriteria configurationCriteria = new ConfigurationCriteria();
		configurationCriteria.setHousingColor(Arrays.asList("Gray"));

		LightRequest lightRequest = createLightRequest(userContext);
		lightRequest.setLightCriteria(lightCriteria);
		lightRequest.setUserContext(userContext);
		lightRequest.setConfigurationCriteria(configurationCriteria);

		InternalResultsResponse<Light> response = getLightDAC().fetchAllByRequest(lightRequest);
		assertResultResponse(response);
	}

	/**
	 * Test fetch all lights by request to schedule criteria.
	 */
	@Test
	public void testFetchAllLightsByRequestToScheduleCriteria()
	{
		// Pre-conditions
		UserContext userContext = createUserContext();
		Light light = TestBaseUtil.createLight(userContext);
		insertLight(light);

		// Schedule conditions
		LightRequest lightRequest = createLightRequest(userContext);
		ScheduleCriteria scheduleCriteria = new ScheduleCriteria();
		scheduleCriteria.getLightIdList().add(light.getId());
		lightRequest.setScheduleCriteria(scheduleCriteria);

		InternalResultsResponse<Light> response = getLightDAC().fetchAllByRequest(lightRequest);
		assertResultResponse(response);

		// Schedule conditions with lightSchedule
		ScheduleRequest scheduleRequest = new ScheduleRequest();
		scheduleRequest.setUserContext(TestBaseUtil.createUserContext());
		EventSchedule eventSchedule = insertScheduleEvent(scheduleRequest);
		applyLightToSchedule(eventSchedule, Arrays.asList(light));

		lightRequest = createLightRequest(userContext);
		scheduleCriteria = new ScheduleCriteria();
		scheduleCriteria.getLightSchedule().add(eventSchedule.getId());
		lightRequest.setScheduleCriteria(scheduleCriteria);

		response = getLightDAC().fetchAllByRequest(lightRequest);
		assertResultResponse(response);

	}

	/**
	 * Test fetch all lights by request to process criteria.
	 */
	@Test
	public void testFetchAllLightsByRequestToProcessCriteria()
	{
		// Pre-conditions
		UserContext userContext = createUserContext();
		Light light = TestBaseUtil.createLight(userContext);
		insertLight(light);
		Process process = insertProcess(light, null, true);

		// Process conditions
		ProcessCriteria processCriteria = new ProcessCriteria();
		processCriteria.setProcessId(process.getId());
		LightRequest lightRequest = createLightRequest(userContext);
		lightRequest.setProcessCriteria(processCriteria);

		InternalResultsResponse<Light> response = getLightDAC().fetchAllByRequest(lightRequest);
		assertResultResponse(response);
		assertEquals(light.getId(), response.getFirstResult().getId());

		// Process conditions with failed
		processCriteria.setFailed(true);
		response = getLightDAC().fetchAllByRequest(lightRequest);
		assertEquals(light.getId(), response.getFirstResult().getId());
	}

	/**
	 * Test Fetch All Lights By Request - Map Feature.
	 * 
	 * @see ILightDAC (fetchAllByRequest)
	 */
	@Test
	public void testFetchAllLightsByRequestToMap()
	{
		// Pre-conditions
		UserContext userContext = createUserContext();
		Light light = TestBaseUtil.createLight(userContext);
		light.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		insertLight(light);

		// Lifecycle conditions
		LightCriteria lightCriteria = new LightCriteria();
		lightCriteria.addLifeCycleState(LifeCycleStateEnum.ACTIVE);
		lightCriteria.getLightIdList().add(light.getId());

		LightRequest lightRequest = createLightRequest(userContext);
		lightRequest.setLightCriteria(lightCriteria);

		InternalResultsResponse<Light> response = getLightDAC().fetchAllByRequest(lightRequest);
		assertResultResponse(response);
	}

	/**
	 * Test fetch all to map by request.
	 */
	@Test
	public void testFetchAllToMapByRequest()
	{
		// Pre-conditions
		UserContext userContext = createUserContext();
		Light light = TestBaseUtil.createLight(userContext);
		insertLight(light);

		// Light conditions
		LightCriteria lightCriteria = new LightCriteria();
		lightCriteria.getLightIdList().add(light.getId());

		// Geocode criteria
		GeoCodeCriteria geoCodeCriteria = new GeoCodeCriteria();
		geoCodeCriteria.setTrunc(NUMBER_THREE);

		LightRequest lightRequest = createLightRequest(userContext);
		lightCriteria.setGeoCodeCriteria(geoCodeCriteria);
		lightRequest.setLightCriteria(lightCriteria);

		CachedResultsResponse<GeocodeLightInfo> response = getLightDAC().fetchAllToMapByRequest(lightRequest);
		assertTrue(response.hasResults());
	}

	/**
	 * Test fetch map bounds by request.
	 */
	@Test
	public void testFetchMapBoundsByRequest()
	{
		// Pre-conditions
		UserContext userContext = createUserContext();
		Light light = TestBaseUtil.createLight(userContext);
		insertLight(light);

		// Light conditions
		LightRequest lightRequest = createLightRequest(userContext);
		LightCriteria lightCriteria = new LightCriteria();
		lightCriteria.getLightIdList().add(light.getId());
		lightRequest.setLightCriteria(lightCriteria);

		InternalResultsResponse<GeocodeLightInfo> response = getLightDAC().fetchMapBoundsByRequest(lightRequest);
		assertResultResponse(response);
	}

	/**
	 * Test fetch notification history by id.
	 */
	@Test
	public void testFetchNotificationHistoryByRequest()
	{
		NotificationHistoryRequest request = new NotificationHistoryRequest();

		// Notification History Criteria
		NotificationHistoryCriteria historyCriteria = new NotificationHistoryCriteria();

		NotificationHistory notification = insertNotificationHistory(createUserContext(), insertLight());

		historyCriteria.setNotificationHistoryId(notification.getId());
		historyCriteria.setNotificationType(NotificationTypeEnum.ALARM);

		request.setNotificationHistoryCriteria(historyCriteria);

		InternalResultsResponse<NotificationHistory> response =
				getNotificationHistoryDAC().fetchNotificationHistoryByRequest(request);
		assertResultResponse(response);
	}

	/**
	 * Test count all by request.
	 */
	@Test
	public void testCountAllByRequest()
	{
		// Pre-conditions
		UserContext userContext = createUserContext();
		Light light = TestBaseUtil.createLight(userContext);
		light.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		insertLight(light);

		// Lifecycle conditions
		LightCriteria lightCriteria = new LightCriteria();
		lightCriteria.addLifeCycleState(LifeCycleStateEnum.ACTIVE);
		lightCriteria.getLightIdList().add(light.getId());

		LightRequest lightRequest = createLightRequest(userContext);
		lightRequest.setLightCriteria(lightCriteria);

		InternalResultsResponse<Integer> response = getLightDAC().countAllByRequest(lightRequest);
		assertResponse(response);
		assertTrue(response.getFirstResult() > 0);
	}

	/**
	 * Test delete light.
	 */
	@Test
	public void testDeleteLight()
	{
		// Creating the new objects: Light and Radio
		Light light = new Light();
		Radio radio = new Radio();
		radio.setFlexNetId(BigInteger.valueOf(RANDOM.nextInt(NUMBER_RANGE)));
		light.setRadio(radio);

		// Success Situation - Delete all light references
		LightDeleteRequest request = new LightDeleteRequest(light);
		request.setDeleteLightReference(DeleteLightReferenceEnum.DELETE_LIGHT_REFERENCES);

		InternalResponse response = getLightDAC().deleteLightReference(request);
		TestBaseUtil.assertResponse(response);

		// Success Situation - Delete light addr tag
		request = new LightDeleteRequest(light);
		request.setDeleteLightReference(DeleteLightReferenceEnum.DELETE_ADDR_TAGS);

		request.setLight(light);
		response = getLightDAC().deleteLightReference(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch notification history alert by id.
	 */
	@Test
	public void testFetchNotificationHistoryAlertById()
	{
		UserContext userContext = createUserContext();
		NotificationHistory notificationHistory = insertNotificationHistory(userContext, insertLight());
		insertAlertClassification(userContext, notificationHistory.getId(), AlertSubTypeEnum.LAMP_FAILURE);

		NotificationHistoryRequest request = new NotificationHistoryRequest();
		NotificationHistoryCriteria notificationHistoryCriteria = new NotificationHistoryCriteria();
		notificationHistoryCriteria.setNotificationHistoryId(notificationHistory.getId());
		request.setNotificationHistoryCriteria(notificationHistoryCriteria);
		InternalResultsResponse<AlertClassification> response =
				getNotificationHistoryDAC().fetchNotificationHistoryAlertById(request);
		TestBaseUtil.assertResponse(response);

	}

	/**
	 * Test update notification history.
	 */
	@Test
	public void testUpdateNotificationHistory()
	{
		// Insert a new notification history in order to do a update after
		NotificationHistory notificationHistory = insertNotificationHistory(createUserContext(), insertLight());

		// Update the notification history
		NotificationHistoryMaintenanceRequest request = new NotificationHistoryMaintenanceRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setNotificationHistory(notificationHistory);
		request.getNotificationHistory().setPrecedence(PrecedenceEnum.DEACTIVATED);

		InternalResponse response = getNotificationHistoryDAC().updateNotificationHistory(request);
		TestBaseUtil.assertResponse(response);

		// Verify the precedence value in order to make sure the value was updated.
		NotificationHistoryCriteria historyCriteria = new NotificationHistoryCriteria();
		historyCriteria.setNotificationHistoryId(notificationHistory.getId());

		NotificationHistoryRequest notificationRequest = new NotificationHistoryRequest();

		notificationRequest.setNotificationHistoryCriteria(historyCriteria);

		InternalResultsResponse<NotificationHistory> notificationResponse =
				getNotificationHistoryDAC().fetchNotificationHistoryById(notificationRequest);

		assertEquals(notificationResponse.getFirstResult().getPrecedence(), PrecedenceEnum.DEACTIVATED);

	}

	/**
	 * Test update alert classification.
	 */
	@Test
	public void testUpdateAlertClassification()
	{
		UserContext userContext = TestBaseUtil.createUserContext();

		// Insert a new Notification History and a new Alert Classification as well, in order to test the update after

		// -- Notification History
		NotificationHistory notificationHistory = insertNotificationHistory(userContext, insertLight());

		// -- Alert Classification
		AlertClassification alertClassification =
				insertAlertClassification(userContext, notificationHistory.getId(), AlertSubTypeEnum.LAMP_FAILURE);

		Date messageDate = alertClassification.getMessageDate();

		Calendar messageDateNew = new GregorianCalendar();
		messageDateNew.setTime(messageDate);
		messageDateNew.add(Calendar.DAY_OF_WEEK, 1);

		// -- Update the Alert Classification
		AlertClassificationMaintenanceRequest request = new AlertClassificationMaintenanceRequest();
		request.setAlertClassification(alertClassification);
		request.getAlertClassification().setMessageDate(messageDateNew.getTime());

		InternalResponse response = getNotificationHistoryDAC().updateAlertClassification(request);
		assertResponse(response);

		// -- Verify if the Message Date was updated
		NotificationHistoryRequest notificationRequest = new NotificationHistoryRequest();
		NotificationHistoryCriteria notificationHistoryCriteria = new NotificationHistoryCriteria();
		notificationHistoryCriteria.setNotificationHistoryId(notificationHistory.getId());
		notificationRequest.setNotificationHistoryCriteria(notificationHistoryCriteria);
		InternalResultsResponse<AlertClassification> alertResponse =
				getNotificationHistoryDAC().fetchNotificationHistoryAlertById(notificationRequest);

		assertResponse(alertResponse);
		assertNotNull(alertResponse.getFirstResult().getMessageDate());

	}

	/**
	 * Test fetch light notification history.
	 */
	@Test
	public void testFetchLightNotificationHistory()
	{
		NotificationHistoryRequest notificationHistoryRequest = new NotificationHistoryRequest();

		// Notification History Criteria
		NotificationHistoryCriteria notificationHistoryCriteria = new NotificationHistoryCriteria();
		notificationHistoryCriteria.setLightId(ONE);
		notificationHistoryCriteria.setTenantId(TWO);
		notificationHistoryCriteria.setUserIds("1");
		notificationHistoryCriteria.setActionId(ONE);
		notificationHistoryCriteria.setSearchText("QAT1");
		notificationHistoryCriteria.setActionCategoryList(LCActionCategoryEnum.MANAGE_LIGHTS.getActionDescriptions());
		notificationHistoryRequest.setNotificationHistoryCriteria(notificationHistoryCriteria);
		notificationHistoryRequest.setStartRow(1);

		// Grouping conditions
		GroupCriteria groupCriteria = new GroupCriteria();
		groupCriteria.getGroupIdList().add(ONE);
		groupCriteria.getNotInGroupIdList().add(TWO);
		notificationHistoryRequest.setGroupCriteria(groupCriteria);

		InternalResultsResponse<LightHistory> response =
				getNotificationHistoryDAC().fetchLightNotificationHistory(notificationHistoryRequest);
		TestBaseUtil.assertResponse(response);

		// Test with Process Filter
		notificationHistoryRequest = new NotificationHistoryRequest();

		notificationHistoryCriteria = new NotificationHistoryCriteria();
		notificationHistoryCriteria.setLightId(ONE);
		notificationHistoryCriteria.setTenantId(TWO);
		notificationHistoryCriteria.setUserIds("1");
		notificationHistoryCriteria.setActionId(ONE);
		notificationHistoryCriteria.setSearchText("QAT1");
		notificationHistoryCriteria.setActionCategoryList(LCActionCategoryEnum.MANAGE_LIGHTS.getActionDescriptions());

		// -- Process Filter
		ProcessFilter processFilter = new ProcessFilter();
		processFilter.setActionCategoryList(Arrays.asList(LCActionCategoryEnum.UNKNOWN));

		notificationHistoryCriteria.setProcessFilter(processFilter);
		notificationHistoryRequest.setNotificationHistoryCriteria(notificationHistoryCriteria);
		notificationHistoryRequest.setStartRow(1);

		response = getNotificationHistoryDAC().fetchLightNotificationHistory(notificationHistoryRequest);
		TestBaseUtil.assertResponse(response);

		// -- Process Filter (with user id)
		processFilter = new ProcessFilter();
		processFilter.setActionCategoryList(Arrays.asList(LCActionCategoryEnum.UNKNOWN));
		processFilter.setUserIds(Arrays.asList(1));

		notificationHistoryCriteria.setProcessFilter(processFilter);
		notificationHistoryRequest.setNotificationHistoryCriteria(notificationHistoryCriteria);
		notificationHistoryRequest.setStartRow(1);

		response = getNotificationHistoryDAC().fetchLightNotificationHistory(notificationHistoryRequest);
		TestBaseUtil.assertResponse(response);

		// -- Process Filter (by Action ID)
		processFilter = new ProcessFilter();
		processFilter.setActionCategoryList(Arrays.asList(LCActionCategoryEnum.UNKNOWN));

		LightTextSearch lightTextSearch = new LightTextSearch();
		lightTextSearch.setLightProperty(LightPropertyForSearchEnum.ACTION_ID);
		lightTextSearch.setSearchText("1");
		processFilter.setLightTextSearch(lightTextSearch);

		notificationHistoryCriteria.setProcessFilter(processFilter);
		notificationHistoryRequest.setNotificationHistoryCriteria(notificationHistoryCriteria);
		notificationHistoryRequest.setStartRow(1);

		response = getNotificationHistoryDAC().fetchLightNotificationHistory(notificationHistoryRequest);
		TestBaseUtil.assertResponse(response);

		// -- Process Filter (by Action Name)
		processFilter = new ProcessFilter();
		processFilter.setActionCategoryList(Arrays.asList(LCActionCategoryEnum.UNKNOWN));

		lightTextSearch = new LightTextSearch();
		lightTextSearch.setLightProperty(LightPropertyForSearchEnum.ACTION_NAME);
		lightTextSearch.setSearchText("Turn");
		processFilter.setLightTextSearch(lightTextSearch);

		notificationHistoryCriteria.setProcessFilter(processFilter);
		notificationHistoryRequest.setNotificationHistoryCriteria(notificationHistoryCriteria);
		notificationHistoryRequest.setStartRow(1);

		response = getNotificationHistoryDAC().fetchLightNotificationHistory(notificationHistoryRequest);
		TestBaseUtil.assertResponse(response);

	}

	/**
	 * Test fetch light notification history header.
	 */
	@Test
	public void testFetchLightNotificationHistoryHeader()
	{
		NotificationHistoryRequest notificationHistoryRequest = new NotificationHistoryRequest();

		// Notification History Criteria
		NotificationHistoryCriteria notificationHistoryCriteria = new NotificationHistoryCriteria();
		notificationHistoryCriteria.setLightId(ONE);
		notificationHistoryRequest.setNotificationHistoryCriteria(notificationHistoryCriteria);

		// Grouping conditions
		GroupCriteria groupCriteria = new GroupCriteria();
		groupCriteria.getGroupIdList().add(ONE);
		groupCriteria.getNotInGroupIdList().add(2);
		notificationHistoryRequest.setGroupCriteria(groupCriteria);

		InternalResultsResponse<HashMap<String, Integer>> response =
				getNotificationHistoryDAC().fetchLightHistoryHeader(notificationHistoryRequest);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch all custom search.
	 */
	@Test
	public void testFetchAllCustomSearch()
	{
		// InquiryPaginationRequest inquiryPaginationRequest = new InquiryPaginationRequest();
		LightRequest lightRequest = new LightRequest();

		LightCustomSearchCriteria lightCustomSearchCriteria = new LightCustomSearchCriteria();

		lightCustomSearchCriteria.setTenantId(ONE);
		lightCustomSearchCriteria.setUserId(ONE);

		// Grouping conditions
		GroupCriteria groupCriteria = new GroupCriteria();
		groupCriteria.getGroupIdList().add(1);
		groupCriteria.getNotInGroupIdList().add(2);
		lightRequest.setGroupCriteria(groupCriteria);

		lightRequest.setLightCustomSearchCriteria(lightCustomSearchCriteria);
		lightRequest.setPageSize(ARBITRARY_PAGE_SIZE_25);

		InternalResultsResponse<CustomSearch> response =
				getLightCustomSearchDAC().fetchAllCustomSearch(lightRequest);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test insert custom search.
	 */
	@Test
	public void testInsertCustomSearch()
	{
		// insert without params
		User user = insertUser();
		UserContext userContext = createUserContext();
		userContext.setId(user.getId());
		userContext.setUserId(user.getUserName());
		insertCustomSearch(userContext);

		Tag tag = insertTag();

		SearchParameter param = new SearchParameter();
		param.setPropertyEnum(PropertyEnum.TAG_ID);
		param.setValue(String.valueOf(tag.getId()));

		// insert with params
		insertCustomSearch(Arrays.asList(param), userContext);
	}

	/**
	 * Test delete custom search.
	 */
	@Test
	public void testDeleteCustomSearch()
	{
		// insert a custom search to delete
		User user = insertUser();
		UserContext userContext = createUserContext();
		userContext.setId(user.getId());
		userContext.setUserId(user.getUserName());
		CustomSearch customSearch = insertCustomSearch(userContext);

		// prepare request
		CustomSearchRequest request = new CustomSearchRequest();
		request.setCustomSearch(customSearch);

		// perform delete
		InternalResponse response = getLightCustomSearchDAC().deleteCustomSearch(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test insert notification history.
	 */
	@Test
	public void testInsertNotificationHistory()
	{
		insertNotificationHistory(createUserContext(), insertLight());
	}

	/**
	 * Creates the custom search.
	 * 
	 * @return the custom search
	 * 
	 *         Test insert columns to custom search.
	 */
	@Test
	public void testInsertColumnsToCustomSearch()
	{
		User user = insertUser();
		ColumnFilterRequest columnFilterRequest = new ColumnFilterRequest();
		columnFilterRequest.setUserContext(TestBaseUtil.createUserContext());
		columnFilterRequest.getUserContext().setId(user.getId());
		columnFilterRequest.getUserContext().setUserId(user.getUserName());

		List<Column> columns = new ArrayList<Column>();

		Column column = new Column();
		column.setColumnEnum(LightColumnEnum.CITY);
		columns.add(column);

		column = new Column();
		column.setColumnEnum(LightColumnEnum.FIRMWARE_VERSION);
		columns.add(column);

		column = new Column();
		column.setColumnEnum(LightColumnEnum.LAMP_TYPE_WATTAGE_DIMMABLE);
		columns.add(column);

		columnFilterRequest.setListColumn(columns);

		CustomSearch customSearch = insertCustomSearch(columnFilterRequest.getUserContext());

		columnFilterRequest.setCustomSearchId(customSearch.getId());
		UserContext userContext = new UserContext();
		userContext.setUserId(USER_ID);
		columnFilterRequest.setUserContext(userContext);
		getLightCustomSearchDAC().insertColumnsToCustomSearch(columnFilterRequest);
	}

	/**
	 * Test reset min max.
	 */
	@Test
	public void testResetMinMax()
	{
		// Test Success
		LastOperationalData lastOperationalData = new LastOperationalData();
		lastOperationalData.setParentId(TWO);
		lastOperationalData.setModifyUser(USER_ID);
		LastOperationalDataMaintenanceRequest request = new LastOperationalDataMaintenanceRequest();
		request.setLastOperationalData(lastOperationalData);

		InternalResponse response = getLightDAC().resetMinMaxValue(request);
		assertResponse(response);
	}

	/**
	 * Test insert operational data.
	 */
	@Test
	public void testInsertOperationalData()
	{
		UserContext userContext = createUserContext();
		NotificationHistory notification = insertNotificationHistory(createUserContext(), insertLight());
		OperationalData operationalData = createOperationalData(notification);
		OperationalDataMaintenanceRequest request =
				createOperationalDataValueMaintenanceRequest(userContext, operationalData);
		InternalResponse response = getLightDAC().insertOperationalData(request);
		assertResponse(response);
	}

	/**
	 * Test fetch lights to add communication failure.
	 */
	@Test
	public void testFetchLightsToAddCommunicationFailure()
	{
		// Pre-conditions
		UserContext userContext = createUserContext();
		Light light = insertLight(userContext);
		insertNotificationHistory(userContext, light);

		// Fetch light to apply communication failure
		CommunicationFailureRequest request = createCommunicationFailureRequest();
		InternalResultsResponse<Integer> response = getLightDAC().fetchLightsToAddCommunicationFailure(request);
		assertResponse(response);

		for (Integer lightId : response.getResultsList())
		{
			if (light.getId().equals(lightId))
			{
				break;
			}
		}
	}

	@Test
	public void testFetchLightsInCommunicationFailure()
	{
		// Pre-conditions
		UserContext userContext = createUserContext();
		Light light = insertLight(userContext);
		NotificationHistory notificationHistory = insertNotificationHistory(userContext, light);
		AlertSubTypeEnum communicationFail = AlertSubTypeEnum.COMMUNICATION_FAIL;
		insertAlertClassification(userContext, notificationHistory.getId(), communicationFail);

		// Fetch light in communication failure
		CommunicationFailureRequest request = createCommunicationFailureRequest();
		InternalResultsResponse<Integer> response = getLightDAC().fetchLightsInCommunicationFailure(request);
		assertResponse(response);

	}

	@Test
	public void testCalculateLightConsumptionInCommunicationFailure()
	{

		// Pre-conditions
		UserContext userContext = createUserContext();
		Light light = insertLight(userContext);
		NotificationHistory notificationHistory = insertNotificationHistory(userContext, light);
		AlertSubTypeEnum communicationFail = AlertSubTypeEnum.COMMUNICATION_FAIL;
		insertAlertClassification(userContext, notificationHistory.getId(), communicationFail);

		// Fetch light in communication failure
		CommunicationFailureRequest request = createCommunicationFailureRequest();
		InternalResultsResponse<Integer> response = getLightDAC().fetchLightsInCommunicationFailure(request);
		assertResponse(response);

		// calculate consumption to lights in communication failure
		request.setLightIds(LCConvertUtil.getIdsToString(response.getResultsList(), ","));
		InternalResponse internalResponse = getLightDAC().calculateLightConsumptionInCommunicationFailure(request);
		assertResponse(internalResponse);

	}

	/**
	 * Test fetch notification history by id.
	 */
	@Test
	public void testFetchNotificationHistoryById()
	{
		// insert a new notification history to do a fetch after
		NotificationHistory notificationHistory = insertNotificationHistory(createUserContext(), insertLight());

		// Notification History Criteria
		NotificationHistoryCriteria historyCriteria = new NotificationHistoryCriteria();

		historyCriteria.setNotificationHistoryId(notificationHistory.getId());

		NotificationHistoryRequest request = new NotificationHistoryRequest();

		request.setNotificationHistoryCriteria(historyCriteria);

		InternalResultsResponse<NotificationHistory> response =
				getNotificationHistoryDAC().fetchNotificationHistoryById(request);
		assertResultResponse(response);

	}

	/**
	 * Test insert filters to custom search.
	 */
	@Test
	public void testInsertFiltersToCustomSearch()
	{
		User user = insertUser();

		ColumnFilterRequest columnFilterRequest = new ColumnFilterRequest();
		columnFilterRequest.setUserContext(TestBaseUtil.createUserContext());
		columnFilterRequest.getUserContext().setId(user.getId());
		columnFilterRequest.getUserContext().setUserId(user.getUserName());

		List<Filter> filters = new ArrayList<Filter>();

		Filter filter = new Filter();
		filter.setFilterEnum(FilterEnum.BULB_SERIAL_NUMBER);
		filters.add(filter);

		filter = new Filter();
		filter.setFilterEnum(FilterEnum.HOUSING_COLOR);
		filters.add(filter);

		filter = new Filter();
		filter.setFilterEnum(FilterEnum.EVENT_SCHEDULE);
		filters.add(filter);

		columnFilterRequest.setFilters(filters);

		CustomSearch customSearch = insertCustomSearch(columnFilterRequest.getUserContext());

		columnFilterRequest.setCustomSearchId(customSearch.getId());
		UserContext userContext = new UserContext();
		userContext.setUserId(USER_ID);
		columnFilterRequest.setUserContext(userContext);
		getLightCustomSearchDAC().insertFiltersToCustomSearch(columnFilterRequest);

	}

	@Test
	public void testFetchAttributeChanges()
	{
		// Sucess situations
		Light light = insertLight();
		light.setLastOperationalData(null);
		light.setLightSchedule(null);
		light.setConfiguration(null);
		light.setPoleId(String.valueOf(RANDOM.nextInt(NUMBER_RANGE)));
		light.setModelAction(PersistanceActionEnum.UPDATE);
		LightMaintenanceRequest maintenanceRequest = createLightMaintenanceRequest(createUserContext(), light);
		InternalResponse maintenanceResponse = getLightDAC().updateLight(maintenanceRequest);
		assertResponse(maintenanceResponse);
		Date date = SensusDateUtil.getMinDate();
		SearchDate searchDate = new SearchDate(date, DateSearchType.GREATER_THAN);
		LightRequest request = createLightRequest();
		request.setLightCriteria(new LightCriteria());
		request.getLightCriteria().setModifyDate(searchDate);
		InternalResultsResponse<BigInteger> response = getLightDAC().fetchAttributeChanges(request);
		assertResultResponse(response);
	}

	/**
	 * Insert custom search.
	 * 
	 * @param userContext the user context
	 * @return the custom search
	 */
	private CustomSearch insertCustomSearch(UserContext userContext)
	{
		return insertCustomSearch(null, userContext);
	}

	/**
	 * Insert custom search.
	 * 
	 * @param parameters the parameters
	 * @param userContext the user context
	 * @return the custom search
	 */
	private CustomSearch insertCustomSearch(List<SearchParameter> parameters, UserContext userContext)
	{
		CustomSearch customSearch = createCustomSearch(parameters);
		customSearch.setSearchParameters(parameters);

		CustomSearchRequest customSearchRequest = new CustomSearchRequest();
		customSearchRequest.setUserContext(userContext);
		customSearchRequest.setCustomSearch(customSearch);
		InternalResultsResponse<CustomSearch> response =
				getLightCustomSearchDAC().insertCustomSearch(customSearchRequest);
		assertResultResponse(response);
		assertNotNull("Custom search should be not null", customSearch);
		assertNotNull("Custom search identifier should be not null", customSearch.getId());
		return customSearch;
	}

	/**
	 * Creates the custom search.
	 * 
	 * @param parameters the param
	 * @return the custom search
	 */
	private CustomSearch createCustomSearch(List<SearchParameter> parameters)
	{
		CustomSearch customSearch = new CustomSearch();
		customSearch.setName("CustomSearch" + RANDOM.nextInt(NUMBER_RANGE));
		customSearch.setDescription("Description Custom Search " + RANDOM.nextInt(NUMBER_RANGE));
		customSearch.setSearchParameters(parameters);
		return customSearch;
	}

}
