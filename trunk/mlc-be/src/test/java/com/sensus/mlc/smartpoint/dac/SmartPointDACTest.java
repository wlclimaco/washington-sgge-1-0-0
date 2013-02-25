package com.sensus.mlc.smartpoint.dac;

import static com.sensus.mlc.base.TestBaseUtil.NUMBER_RANGE;
import static com.sensus.mlc.base.TestBaseUtil.RANDOM;
import static com.sensus.mlc.base.TestBaseUtil.assertResponse;
import static com.sensus.mlc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.mlc.base.TestBaseUtil.createInquiryLightRequest;
import static com.sensus.mlc.base.TestBaseUtil.createInquiryPaginationRequest;
import static com.sensus.mlc.base.TestBaseUtil.createLightRequest;
import static com.sensus.mlc.base.TestBaseUtil.createLightTextSearch;
import static com.sensus.mlc.base.TestBaseUtil.createProcessFilter;
import static com.sensus.mlc.base.TestBaseUtil.createProcessRequest;
import static com.sensus.mlc.base.TestBaseUtil.createScheduleRequest;
import static com.sensus.mlc.base.TestBaseUtil.createUserContext;
import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;
import static com.sensus.mlc.base.util.LCPropertiesExtractorUtil.extractAuthorityId;
import static com.sensus.mlc.base.util.LCPropertiesExtractorUtil.extractLightId;
import static com.sensus.mlc.base.util.LCPropertiesExtractorUtil.extractSmartpointId;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.AbstractTestBaseDAC;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.ecomode.model.EcoModeFilterEnum;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessFilter;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.schedule.model.EventSchedule;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.smartpoint.csv.LightCSVDataSource;
import com.sensus.mlc.smartpoint.csv.LightHistoryCSVDataSource;
import com.sensus.mlc.smartpoint.csv.LightMapCSVDataSource;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.Filter;
import com.sensus.mlc.smartpoint.model.GeocodeSmartpointInfo;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightBlinkEnum;
import com.sensus.mlc.smartpoint.model.LightHistory;
import com.sensus.mlc.smartpoint.model.LightIntensityEnum;
import com.sensus.mlc.smartpoint.model.LightOrderByEnum;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.LightPropertyForSearchEnum;
import com.sensus.mlc.smartpoint.model.LightStateEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.LightTypeEnum;
import com.sensus.mlc.smartpoint.model.OperationalData;
import com.sensus.mlc.smartpoint.model.OperationalDataTypeEnum;
import com.sensus.mlc.smartpoint.model.OverrideEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.PropertyValidValue;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.SensusPartNumberConfiguration;
import com.sensus.mlc.smartpoint.model.SmartPointColumnEnum;
import com.sensus.mlc.smartpoint.model.SmartPointFilterEnum;
import com.sensus.mlc.smartpoint.model.SmartpointMiddleMap;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.StatusMessageCategoryEnum;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.request.GuaranteeLightExistenceRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightingConfigurationRequest;
import com.sensus.mlc.smartpoint.model.request.PropertyValidValuesRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.request.TenantRequest;
import com.sensus.mlc.user.model.User;

/**
 * The Class SmartPointDACTest.
 */
public class SmartPointDACTest extends AbstractTestBaseDAC
{
	/** The Constant TOP_RIGHT_MAP. */
	private static final double TOP_RIGHT_MAP = 200.00;

	/** The Constant BOTTOM_LEFT_MAP. */
	private static final double BOTTOM_LEFT_MAP = -100.00;

	/** The Constant ARBITRARY_TOP_RIGHT_MIN. */
	private static final Double ARBITRARY_TOP_RIGHT_MIN = 36.641904911354;

	/** The Constant ARBITRARY_TOP_RIGHT_LON. */
	private static final Double ARBITRARY_TOP_RIGHT_LON = -36.773377607361;

	/** The Constant ARBITRARY_BOTTOM_LEFT_LON. */
	private static final Double ARBITRARY_BOTTOM_LEFT_LON = -80.948182294694;

	/** The Constant ARBITRARY_BOTTOM_LEFT_LAT. */
	private static final Double ARBITRARY_BOTTOM_LEFT_LAT = -20.758948;

	/** The Constant ARBITRARY_MIDDLE_LON_MIN. */
	private static final Double ARBITRARY_MIDDLE_LON_MIN = -78.8801523969639;

	/** The Constant ARBITRARY_MIDDLE_LAT_MIN. */
	private static final Double ARBITRARY_MIDDLE_LAT_MIN = 35.8568783554023;

	/** The Constant ARBITRARY_MIDDLE_LON_MAX. */
	private static final Double ARBITRARY_MIDDLE_LON_MAX = -78.1415066626146;

	/** The Constant ARBITRARY_MIDDLE_LAT_MAX. */
	private static final Double ARBITRARY_MIDDLE_LAT_MAX = 35.9764879951401;

	/** The Constant ARBITRARY_PAGE_SIZE_15. */
	private static final Integer ARBITRARY_PAGE_SIZE_15 = 15;

	/** The Constant ARBITRARY_MAX_SMARTPOINT_COUNT_50. */
	private static final Integer ARBITRARY_MAX_SMARTPOINT_COUNT_50 = 50;

	/** The Constant ARBITRARY_USER_CONTEXT_ID. */
	private static final Integer ARBITRARY_USER_CONTEXT_ID = 5;

	/** The Constant ARBITRARY_AMOUNT_4. */
	private static final Integer ARBITRARY_AMOUNT_4 = 4;

	/** The Constant ARBITRARY_PAGE_SIZE_5. */
	private static final Integer ARBITRARY_PAGE_SIZE_5 = 5;

	/** The Constant ARBITRARY_PAGE_SIZE_25. */
	private static final Integer ARBITRARY_PAGE_SIZE_25 = 25;

	/** The Constant ARBITRARY_AMOUNT_10. */
	private static final Integer ARBITRARY_AMOUNT_10 = 10;

	/** The Constant ARBITRARY_PAGE_SIZE_10. */
	private static final Integer ARBITRARY_PAGE_SIZE_10 = 10;

	/** The Constant ARBITRARY_AMOUNT_3. */
	private static final Integer ARBITRARY_AMOUNT_3 = 3;

	/** The Constant ARBITRARY_AMOUNT_5. */
	private static final Integer ARBITRARY_AMOUNT_5 = 5;

	/** The Constant ARBITRARY_LIGHT_IN_TENANT_N_3. */
	private static final Integer ARBITRARY_LIGHT_IN_TENANT_N_3 = -3;

	/** The Constant ARBITRARY_LIGHT_IN_TENANT_N_2. */
	private static final Integer ARBITRARY_LIGHT_IN_TENANT_N_2 = -2;

	/** The Constant LOCATION_HAS_CHANGED_SHOULD_BE_NOT_NULL. */
	private static final String LOCATION_HAS_CHANGED_SHOULD_BE_NOT_NULL = "Location has changed should be not null";

	/** The Constant RESULT_AMOUNT_SHOULD_BE_EQUALS_SELECTED. */
	private static final String RESULT_AMOUNT_SHOULD_BE_EQUALS_SELECTED = "Result amount should be equals selected";

	/** The Constant _ONE. */
	private static final String ONE = "1";

	/** The Constant OPERATOR. */
	private static final String OPERATOR = "+";

	/** The Constant RNI_ID. */
	private static final String RNI_ID = "rni_id";

	/** The Constant ECOMODE. */
	private static final String ECOMODE = "ecomode";

	/** The Constant TWO. */
	private static final String TWO = "2";

	/** The Constant LIGHT_LIST_SHOULD_HAVE_ONLY_5_REGISTERS. */
	private static final String LIGHT_LIST_SHOULD_HAVE_ONLY_5_REGISTERS = "Light list should have only 5 registers";

	/** The Constant SIX. */
	private static final String SIX = "6";

	/** The Constant DEFAULT_ECO_MODE. */
	private static final Double DEFAULT_ECO_MODE = 100.00;

	/** The Constant ARBITRARY_STRING_DATE. */
	private static final String ARBITRARY_STRING_DATE = "2013-01-25 06:24:23";

	/** The Constant ARBITRARY_NUMBER_N10. */
	private static final Integer ARBITRARY_NUMBER_N10 = -10;

	/** The Constant ARBITRARY_NUMBER_N5. */
	private static final Integer ARBITRARY_NUMBER_N5 = -5;

	/** The Constant ARBITRARY_AC_VOLTAGE. */
	private static final Integer ARBITRARY_AC_VOLTAGE = 127;

	/** The Constant ARBITRARY_AC_VOLTAGE_MIN. */
	private static final Integer ARBITRARY_AC_VOLTAGE_MIN = 50;

	/** The Constant ARBITRARY_AC_VOLTAGE_MAX. */
	private static final Integer ARBITRARY_AC_VOLTAGE_MAX = 220;

	/** The Constant ARBITRARY_AC_CURRENT. */
	private static final Double ARBITRARY_AC_CURRENT = 110.0;

	/** The Constant ARBITRARY_AC_CURRENT_MIN. */
	private static final Double ARBITRARY_AC_CURRENT_MIN = 35.0;

	/** The Constant ARBITRARY_AC_CURRENT_MAX. */
	private static final Double ARBITRARY_AC_CURRENT_MAX = 250.0;

	/** The Constant ARBITRARY_CONSUMPTION. */
	private static final Double ARBITRARY_CONSUMPTION = 1000.0;

	/** The Constant ARBITRARY_CONSUMPTION_MIN. */
	private static final Double ARBITRARY_CONSUMPTION_MIN = 10.0;

	/** The Constant ARBITRARY_CONSUMPTION_MAX. */
	private static final Double ARBITRARY_CONSUMPTION_MAX = 1000.0;

	/** The Constant ARBITRARY_LATITUDE_TRUNC. */
	private static final Double ARBITRARY_LATITUDE_TRUNC = 17.0;

	/** The Constant ARBITRARY_LONGITUDE_TRUNC. */
	private static final Double ARBITRARY_LONGITUDE_TRUNC = 16.0;

	/** The light default. */
	private Light lightDefault;

	/** The user default. */
	private User userDefault;

	/** The PROPERTY VALID VALUES. */
	private static List<PropertyEnum> PROPERTY = new ArrayList<PropertyEnum>();

	static
	{
		PROPERTY.add(PropertyEnum.LAMP_TYPE);
		PROPERTY.add(PropertyEnum.WATTAGE_RATING);
		PROPERTY.add(PropertyEnum.INPUT_VOLTAGE_RANGE);
		PROPERTY.add(PropertyEnum.COLOR_TEMPERATURE);
		PROPERTY.add(PropertyEnum.FIRMWARE_VERSION);
		PROPERTY.add(PropertyEnum.LAMP_TYPE_WATTAGE_DIMMABLE);
		PROPERTY.add(PropertyEnum.DIMMABLE);
		PROPERTY.add(PropertyEnum.HOUSING);
		PROPERTY.add(PropertyEnum.HOUSING_COLOR);

	}

	/**
	 * Gets the light default.
	 *
	 * @return the light default
	 */
	public Light getLightDefault()
	{
		return lightDefault;
	}

	/**
	 * Sets the light default.
	 *
	 * @param lightDefault the new light default
	 */
	public void setLightDefault(Light lightDefault)
	{
		this.lightDefault = lightDefault;
	}

	/**
	 * Gets the user default.
	 *
	 * @return the user default
	 */
	public User getUserDefault()
	{
		return userDefault;
	}

	/**
	 * Sets the user default.
	 *
	 * @param userDefault the new user default
	 */
	public void setUserDefault(User userDefault)
	{
		this.userDefault = userDefault;
	}

	/**
	 * Setup test.
	 */
	@Before
	public void setupTest()
	{
		setCacheStatementScope(getSmartpointDAC());
		setLightDefault(insertLight());

		if (getUserDefault() == null)
		{
			setUserDefault(insertUser());
			createUserContext().setUserId(getUserDefault().getUserName());
		}
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
	 * Test insert lights.
	 */
	@Test
	public void testInsertLight()
	{
		insertLight();
	}

	/**
	 * Test insert smart point.
	 */
	@Test
	public void testInsertSmartPoint()
	{
		// Test Success
		List<Light> lightList = new ArrayList<Light>();
		Light light = TestBaseUtil.createLight();
		lightList.add(light);

		GuaranteeLightExistenceRequest guaranteeLightExistenceRequest =
				TestBaseUtil.createGuaranteeLightExistenceRequest();
		guaranteeLightExistenceRequest.setLights(lightList);

		Integer smartPointId = getSmartpointDAC().insertSmartPoint(guaranteeLightExistenceRequest);
		assertEquals(smartPointId > 0, true);
	}

	/**
	 * Test insert light configuration.
	 */
	@Test
	public void testInsertLightConfiguration()
	{
		// insert smartPoint
		List<Light> lightList = new ArrayList<Light>();
		Light light = TestBaseUtil.createLight();
		lightList.add(light);

		GuaranteeLightExistenceRequest guaranteeLightExistenceRequest =
				TestBaseUtil.createGuaranteeLightExistenceRequest();
		guaranteeLightExistenceRequest.setLights(lightList);

		Integer smartPointId = getSmartpointDAC().insertSmartPoint(guaranteeLightExistenceRequest);
		assertEquals(smartPointId > 0, true);

		// insert Light
		light.setSmartPointId(smartPointId);
		Integer lightId = getSmartpointDAC().insertLight(guaranteeLightExistenceRequest);
		assertEquals(lightId > 0, true);

		// insert lightConfiguration
		light.setId(lightId);
		getSmartpointDAC().insertLightConfiguration(guaranteeLightExistenceRequest);

	}

	/**
	 * Test insert light location.
	 */
	@Test
	public void testInsertLightLocation()
	{
		// insert smartPoint
		List<Light> lightList = new ArrayList<Light>();
		Light light = TestBaseUtil.createLight();
		lightList.add(light);

		GuaranteeLightExistenceRequest guaranteeLightExistenceRequest =
				TestBaseUtil.createGuaranteeLightExistenceRequest();
		guaranteeLightExistenceRequest.setLights(lightList);

		Integer smartPointId = getSmartpointDAC().insertSmartPoint(guaranteeLightExistenceRequest);
		assertEquals(smartPointId > 0, true);

		// insert Light
		light.setSmartPointId(smartPointId);
		Integer lightId = getSmartpointDAC().insertLight(guaranteeLightExistenceRequest);
		assertEquals(lightId > 0, true);

		// insert Light Location
		light.setId(lightId);
		getSmartpointDAC().insertLightLocation(guaranteeLightExistenceRequest);
	}

	/**
	 * Test fetch light to insert.
	 */
	@Test
	public void testFetchLightToInsert()
	{
		// necessary insert smartPoint first.
		// insert smartPoint
		List<Light> lightList = new ArrayList<Light>();
		Light light = TestBaseUtil.createLight();
		lightList.add(light);

		GuaranteeLightExistenceRequest guaranteeLightExistenceRequest =
				TestBaseUtil.createGuaranteeLightExistenceRequest();
		guaranteeLightExistenceRequest.setLights(lightList);

		Integer smartPointId = getSmartpointDAC().insertSmartPoint(guaranteeLightExistenceRequest);
		assertEquals(smartPointId > 0, true);

		// insert Light
		light.setSmartPointId(smartPointId);
		Integer lightId = getSmartpointDAC().insertLight(guaranteeLightExistenceRequest);
		assertEquals(lightId > 0, true);

		// insert lightConfiguration
		light.setId(lightId);
		getSmartpointDAC().insertLightConfiguration(guaranteeLightExistenceRequest);

		// Test fetchLightToInsert
		light = getSmartpointDAC().fetchLightToInsert(guaranteeLightExistenceRequest);
		assertNotNull("light should be not null ", light);
		assertLight(light);

		// Test coverage
		guaranteeLightExistenceRequest.getFirstLight().setId(new Random().nextInt(NUMBER_RANGE));
		light = getSmartpointDAC().fetchLightToInsert(guaranteeLightExistenceRequest);
		assertNull(light);

	}

	/**
	 * Test insert columns to custom search.
	 */
	@Test
	public void testInsertColumnsToCustomSearch()
	{
		User user = insertUser();
		ColumnFilterRequest columnFilterRequest = this.createColumnFilterRequest(user);
		List<Column> columns = new ArrayList<Column>();

		Column column = new Column();
		column.setColumnEnum(SmartPointColumnEnum.CITY_NAME);
		columns.add(column);

		column = new Column();
		column.setColumnEnum(SmartPointColumnEnum.FIRMWARE_VERSION);
		columns.add(column);

		column = new Column();
		column.setColumnEnum(SmartPointColumnEnum.LAMP_TYPE);
		columns.add(column);

		columnFilterRequest.setListColumn(columns);

		CustomSearch customSearch = this.insertCustomSearch(columnFilterRequest.getUserContext());

		columnFilterRequest.setCustomSearchId(customSearch.getId());
		UserContext userContext = new UserContext();
		userContext.setUserId("IGOR1");
		columnFilterRequest.setUserContext(userContext);
		getSmartpointDAC().insertColumnsToCustomSearch(columnFilterRequest);
	}

	/**
	 * Test insert filters to custom search.
	 */
	@Test
	public void testInsertFiltersToCustomSearch()
	{
		User user = insertUser();
		ColumnFilterRequest columnFilterRequest = this.createColumnFilterRequest(user);
		List<Filter> filters = new ArrayList<Filter>();
		Filter filter = new Filter();

		filter.setFilterEnumValue(SmartPointFilterEnum.ADDRESS.getValue());
		filters.add(filter);

		filter = new Filter();
		filter.setFilterEnumValue(SmartPointFilterEnum.DATE_ADDED.getValue());
		filters.add(filter);

		filter = new Filter();
		filter.setFilterEnumValue(SmartPointFilterEnum.GROUPS.getValue());
		filters.add(filter);

		columnFilterRequest.setFilters(filters);

		CustomSearch customSearch = this.insertCustomSearch(columnFilterRequest.getUserContext());
		columnFilterRequest.setCustomSearchId(customSearch.getId());

		UserContext userContext = new UserContext();
		userContext.setUserId("IGOR");
		columnFilterRequest.setUserContext(userContext);
		getSmartpointDAC().insertFiltersToCustomSearch(columnFilterRequest);
	}

	/**
	 * Test insert complete status message.
	 */
	@Test
	public void testUpdateLightProtected()
	{
		getLightDefault().setProtect(true);

		LightRequest request = createLightRequest();
		request.addLight(getLightDefault());

		InternalResultsResponse<Light> response = getSmartpointDAC().updateLightProtected(request);
		assertResponse(response);
	}

	/**
	 * Test update light lat lng.
	 */
	@Test
	public void testUpdateLightLatLng()
	{

		getLightDefault().setLightLocation(TestBaseUtil.createLightLocation());

		LightRequest request = createLightRequest();
		request.addLight(getLightDefault());

		InternalResponse response = getSmartpointDAC().updateLightLatLng(request);
		assertResponse(response);
	}

	/**
	 * Test fetch light configuration by id.
	 */
	@Test
	public void testFetchLightConfigurationById()
	{
		LightRequest lightRequest = new LightRequest();
		lightRequest.addLight(getLightDefault());
		InternalResponse response = getSmartpointDAC().fetchLightConfigurationById(lightRequest);
		assertResponse(response);
	}

	/**
	 * Test fetch light location by id.
	 */
	@Test
	public void testFetchLightLocationById()
	{
		LightRequest lightRequest = new LightRequest();
		lightRequest.addLight(getLightDefault());
		InternalResponse response = getSmartpointDAC().fetchLightLocationById(lightRequest);
		assertResponse(response);
	}

	/**
	 * Fetch light last operation data by id.
	 */
	@Test
	public void fetchLightLastOperationDataById()
	{
		LightRequest lightRequest = new LightRequest();
		lightRequest.addLight(getLightDefault());
		InternalResponse response = getSmartpointDAC().fetchLightLastOperationDataById(lightRequest);
		assertResponse(response);
	}

	/**
	 * Test fetch light schedule by id.
	 */
	@Test
	public void testFetchLightScheduleById()
	{
		LightRequest lightRequest = new LightRequest();
		lightRequest.addLight(getLightDefault());
		InternalResponse response = getSmartpointDAC().fetchLightScheduleById(lightRequest);
		assertResponse(response);
	}

	/**
	 * Test update light.
	 */
	@Test
	public void testUpdateLight()
	{
		Light light = getLightDefault();
		light.setCurrentLightStatusEnum(LightStatusEnum.MAINTENANCE);
		light.setProtect(true);
		light.setLightIntensityEnum(LightIntensityEnum.LEVEL_0);
		light.setLightBlinkEnum(LightBlinkEnum.FAST);
		light.setOverrideEnum(OverrideEnum.SCHEDULED);
		light.setOverridePerDate(getNewDateUTC());
		light.setOverrideCreateDate(getNewDateUTC());
		light.setLightStateEnum(LightStateEnum.BLINK);
		light.setPoleId(String.valueOf(RANDOM.nextInt(NUMBER_RANGE)));
		light.setEcoMode(DEFAULT_ECO_MODE);
		light.setLightTypeEnum(LightTypeEnum.INDUCTION);
		LightRequest lightRequest = createLightRequest();
		lightRequest.addLight(light);
		lightRequest.getFirstLight().setModifyUser(lightRequest.getUserContext().getUserId());
		InternalResponse response = getSmartpointDAC().updateLight(lightRequest);
		assertResponse(response);
	}

	/**
	 * Test update light schedule.
	 */
	@Test
	public void testUpdateLightSchedule()
	{
		Light light = getLightDefault();
		light.getLightSchedule().setSunriseTime(ARBITRARY_STRING_DATE);
		light.getLightSchedule().setSunriseOffset(ARBITRARY_NUMBER_N10);
		light.getLightSchedule().setSunsetTime(ARBITRARY_STRING_DATE);
		light.getLightSchedule().setSunsetOffset(ARBITRARY_NUMBER_N5);
		LightRequest lightRequest = createLightRequest();
		lightRequest.addLight(light);
		lightRequest.getFirstLight().setModifyUser(lightRequest.getUserContext().getUserId());
		InternalResponse response = getSmartpointDAC().updateLightSchedule(lightRequest);
		assertResponse(response);
	}

	/**
	 * Test update light location.
	 */
	@Test
	public void testUpdateLightLocation()
	{
		Light light = getLightDefault();
		light.getLightLocation().setTimeZone("UTC");
		light.getLightLocation().setLatitude(ARBITRARY_LATITUDE_TRUNC);
		light.getLightLocation().setLongitude(ARBITRARY_LONGITUDE_TRUNC);
		light.getLightLocation().setStreetName("SE Stark St.");
		light.getLightLocation().setCityName("Morrisville");
		light.getLightLocation().setCountyName("USA");
		light.getLightLocation().setStateName("Oregon");
		light.getLightLocation().setZipCode("553447");
		LightRequest lightRequest = createLightRequest();
		lightRequest.addLight(light);
		lightRequest.getFirstLight().setModifyUser(lightRequest.getUserContext().getUserId());
		InternalResponse response = getSmartpointDAC().updateLightLocation(lightRequest);
		assertResponse(response);

	}

	/**
	 * Test update light last operation data.
	 */
	@Test
	public void testUpdateLightLastOperationData()
	{
		Light light = getLightDefault();
		light.getLightLastOperationalData().setAcVoltage(ARBITRARY_AC_VOLTAGE);
		light.getLightLastOperationalData().setAcVoltageMin(ARBITRARY_AC_VOLTAGE_MIN);
		light.getLightLastOperationalData().setAcVoltageMinDate(getNewDateUTC());
		light.getLightLastOperationalData().setAcVoltageMax(ARBITRARY_AC_VOLTAGE_MAX);
		light.getLightLastOperationalData().setAcVoltageMaxDate(getNewDateUTC());
		light.getLightLastOperationalData().setAcCurrent(ARBITRARY_AC_CURRENT);
		light.getLightLastOperationalData().setAcCurrentMin(ARBITRARY_AC_CURRENT_MIN);
		light.getLightLastOperationalData().setAcCurrentMinDate(getNewDateUTC());
		light.getLightLastOperationalData().setAcCurrentMax(ARBITRARY_AC_CURRENT_MAX);
		light.getLightLastOperationalData().setAcCurrentMaxDate(getNewDateUTC());
		light.getLightLastOperationalData().setConsumption(ARBITRARY_CONSUMPTION);
		light.getLightLastOperationalData().setConsumptionMin(ARBITRARY_CONSUMPTION_MIN);
		light.getLightLastOperationalData().setConsumptionMax(ARBITRARY_CONSUMPTION_MAX);
		LightRequest lightRequest = createLightRequest();
		lightRequest.addLight(light);
		lightRequest.getFirstLight().setModifyUser(lightRequest.getUserContext().getUserId());
		InternalResponse response = getSmartpointDAC().updateLightLastOperationData(lightRequest);
		assertResponse(response);
	}

	/**
	 * Test update light configuration.
	 */
	@Test
	public void testUpdateLightConfiguration()
	{
		Light light = getLightDefault();
		light.getLightConfiguration().setHousing("Street");
		light.getLightConfiguration().setHousingColor("Bronze");
		light.getLightConfiguration().setDimmable(true);
		light.getLightConfiguration().setLampTypeWattageDimmable("80W Induction");
		light.getLightConfiguration().setWattageRating("80W");
		light.getLightConfiguration().setInputaWattageRange("90-300V");
		light.getLightConfiguration().setColorTemperature("4,000K");
		light.getLightConfiguration().setManufacturer("QAT");
		light.getLightConfiguration().setFirmwareVersion("2.0.0");
		light.getLightConfiguration().setModelNumber("539449037C2322");
		light.getLightConfiguration().setLowerAssemblySerial("Lower 4");
		light.getLightConfiguration().setUpperAssemblySerial("Upper 4");
		light.getLightConfiguration().setDateAdded(getNewDateUTC());
		light.getLightConfiguration().setBulbSerialNumber("Bulb 4");
		LightRequest lightRequest = createLightRequest();
		lightRequest.addLight(light);
		lightRequest.getFirstLight().setModifyUser(lightRequest.getUserContext().getUserId());
		InternalResponse response = getSmartpointDAC().updateLightConfiguration(lightRequest);
		assertResponse(response);
	}

	/**
	 * Test delete addr tags for light.
	 */
	@Test
	public void testDeleteAddrTagsForLight()
	{
		InternalResponse response = getSmartpointDAC().deleteAddrTagsForLight(getLightDefault().getRniId());
		assertResponse(response);
	}

	/**
	 * Test insert custom search.
	 */
	@Test
	public void testInsertCustomSearch()
	{
		Tag tag = this.insertTag();

		SearchParameter param = new SearchParameter();
		param.setPropertyEnum(PropertyEnum.TAG_ID);
		param.setValue(String.valueOf(tag.getId()));

		// insert without params
		User user = insertUser();
		UserContext userContext = createUserContext();
		userContext.setId(user.getId());
		userContext.setUserId(user.getUserName());
		this.insertCustomSearch(userContext);

		// insert with params
		this.insertCustomSearch(Arrays.asList(param), userContext);
	}

	/**
	 * Test delete custom search.
	 */
	@Test
	public void testDeleteCustomSearch()
	{
		ScheduleRequest scheduleRequest = createScheduleRequest();
		scheduleRequest.getUserContext().setId(insertUser().getId());
		EventSchedule schedule = insertScheduleEvent(scheduleRequest);

		SearchParameter param = new SearchParameter();
		param.setPropertyEnum(PropertyEnum.SCHEDULE_ID);
		param.setValue(String.valueOf(schedule.getId()));

		// insert a custom search
		CustomSearch customSearch = this.insertCustomSearch(Arrays.asList(param), scheduleRequest.getUserContext());

		CustomSearchRequest request = this.createCustomSearchRequest(null);
		request.setCustomSearch(customSearch);

		// delete de custom search that has been inserted
		InternalResponse response = getSmartpointDAC().deleteCustomSearch(request);
		assertResponse(response);
	}

	/**
	 * Test insert operational data.
	 */
	@Test
	public void testInsertOperationalData()
	{
		// Test Success
		StatusMessage message = this.insertStatusMessage(getLightDefault());
		insertOperationalData(message, new OperationalData(new Float(1), OperationalDataTypeEnum.VOLTAGE));
	}

	/**
	 * Test update light state.
	 */
	@Test
	public void testUpdateLightState()
	{
		// DEACTIVATED
		LightRequest lightRequest =
				createLightRequestState(LightStateEnum.DEACTIVATED, LightBlinkEnum.NONE,
						LightIntensityEnum.LEVEL_6,
						OverrideEnum.SCHEDULED, null);

		InternalResponse response =
				getSmartpointDAC().updateLightState(lightRequest);

		assertResponse(response);

		// LIGHT INTENSITY
		lightRequest =
				createLightRequestState(LightStateEnum.ON, LightBlinkEnum.NONE, LightIntensityEnum.LEVEL_3,
						OverrideEnum.SCHEDULED, null);
		response = getSmartpointDAC().updateLightState(lightRequest);

		// LIGHT BLINK
		lightRequest =
				createLightRequestState(LightStateEnum.ON, LightBlinkEnum.FAST, LightIntensityEnum.LEVEL_6,
						OverrideEnum.PER_DATE, LCDateUtil.getNewDateUTC());
		response = getSmartpointDAC().updateLightState(lightRequest);

		// CLEAR OVERRIDE
		lightRequest =
				createLightRequestState(LightStateEnum.ON, LightBlinkEnum.NONE, LightIntensityEnum.LEVEL_0,
						OverrideEnum.SCHEDULED, null);
		response = getSmartpointDAC().updateLightState(lightRequest);

		assertResponse(response);
	}

	/**
	 * Test delete light from all tag schedule group.
	 */
	@Test
	public void testDeleteLightFromAllTagScheduleGroup()
	{
		// Test Success
		InternalResponse response =
				getSmartpointDAC().deleteLightFromAllTagScheduleGroup(getLightDefault().getRniId());
		assertResponse(response);
	}

	/**
	 * Test location has changed.
	 */
	@Test
	public void testLocationHasChanged()
	{
		// Test Success
		Integer rniId = getLightDefault().getRniId();
		Double lat = getLightDefault().getLightLocation().getLatitude();
		Double lng = getLightDefault().getLightLocation().getLongitude();

		UserContext userContext = createUserContext();
		List<Integer> allowedGroupIdList = extractAuthorityId(userContext.getAuthorities());

		Boolean response = getSmartpointDAC().locationHasChanged(rniId, lat, lng, allowedGroupIdList);
		assertNotNull(LOCATION_HAS_CHANGED_SHOULD_BE_NOT_NULL, response);
		assertFalse("Location has changed should be false ", response);

		response = getSmartpointDAC().locationHasChanged(rniId, lat + 1, lng + 1, allowedGroupIdList);
		assertNotNull("Location has changed should be not null ", response);
		assertTrue("Location has changed should be true", response);

		// Restriction test
		Group group = insertGroup();
		this.setGroupsToUser(userContext, group);
		allowedGroupIdList = extractAuthorityId(userContext.getAuthorities());

		response = getSmartpointDAC().locationHasChanged(rniId, lat + 1, lng + 1, allowedGroupIdList);
		assertNotNull(LOCATION_HAS_CHANGED_SHOULD_BE_NOT_NULL, response);
		assertFalse("Location has changed should be false", response);
	}

	/**
	 * Test is light in tenant.
	 */
	@Test
	public void testIsLightInTenant()
	{
		UserContext userContext = createUserContext();
		Tenant tenant = userContext.<Tenant> getTenant();
		List<Integer> groupIdsList = extractAuthorityId(userContext.getAuthorities());

		Integer response =
				getSmartpointDAC()
				.isLightInTenant(RANDOM.nextInt(NUMBER_RANGE), tenant.getRniCode(), groupIdsList);
		assertNotNull("Light in Tenant should be not null ", response);
		assertEquals("Light in tenant should be equals -2", ARBITRARY_LIGHT_IN_TENANT_N_2, response);

		response = getSmartpointDAC().isLightInTenant(RANDOM.nextInt(NUMBER_RANGE), "JUNIT", groupIdsList);
		assertNotNull("Light in Tenant should be not null", response);
		assertEquals("Light in tenant should be equals -3", ARBITRARY_LIGHT_IN_TENANT_N_3, response);
	}

	/**
	 * Test can insert custom search.
	 */
	@Test
	public void testCanInsertCustomSearch()
	{
		// Test Success
		CustomSearch customSearch = createCustomSearch();
		CustomSearchRequest request = createCustomSearchRequest();
		request.setCustomSearch(customSearch);

		List<MessageInfo> messageList = new ArrayList<MessageInfo>();
		assertTrue(getSmartpointDAC().fetchCanInsertCustomSearch(request.getTenant(), customSearch,
				getUserDefault().getId(), messageList, request.getAllowedGroupIdList()));

		assertEquals("Can insert, should have no messages ", 0, messageList.size());

		request.getUserContext().setId(getUserDefault().getId());

		InternalResponse response = getSmartpointDAC().insertCustomSearch(request);
		assertResponse(response);

		messageList = new ArrayList<MessageInfo>();
		assertFalse(getSmartpointDAC().fetchCanInsertCustomSearch(request.getTenant(), customSearch,
				getUserDefault().getId(), messageList, request.getAllowedGroupIdList()));

		assertEquals("Can't insert, should have one message ", 1, messageList.size());
	}

	/**
	 * Test fetch all custom search.
	 *
	 * @return the internal results response
	 */
	@Test
	public void testFetchAllCustomSearch()
	{
		InquiryPaginationRequest inquiryPaginationRequest =
				createInquiryPaginationRequest(0, 2, null, true, "custom_search_id", false);

		User user = insertUser();
		UserContext userContext = inquiryPaginationRequest.getUserContext();
		userContext.setId(user.getId());

		insertCustomSearches(ARBITRARY_AMOUNT_5, userContext);

		InternalResultsResponse<CustomSearch> response =
				getSmartpointDAC().fetchAllCustomSearch(inquiryPaginationRequest);

		assertResultResponse(response);
	}

	/**
	 * Test fetch light by id.
	 */
	@Test
	public void testFetchLightById()
	{
		LightRequest request = createLightRequest();
		request.addLight(getLightDefault());

		// Test fetch single
		InternalResultsResponse<Light> response = getSmartpointDAC().fetchLightById(request);
		assertResultResponse(response);

		// Test restriction - without authorization
		Group group = insertGroup();
		this.setGroupsToUser(request.getUserContext(), group);
		response = getSmartpointDAC().fetchLightById(request);
		assertFalse(response.hasResults());

		// Test restriction - with authorization
		addLightToGroup(getLightDefault(), group);
		response = getSmartpointDAC().fetchLightById(request);
		assertResultResponse(response);
	}

	/**
	 * Test fetch all lights with list.
	 */
	@Test
	public void testFetchAllLightsWithList()
	{
		List<Light> lights = insertLights(ARBITRARY_AMOUNT_3);
		List<Integer> lightIds = extractSmartpointId(lights);
		List<LightStatusEnum> lightStatusList =
				createLightStatusList(LightStatusEnum.ACTIVE, LightStatusEnum.ALARM);

		SearchLight searchLight = new SearchLight();
		searchLight.setStatusList(lightStatusList);

		InquiryLightRequest inquiryLightRequest =
				createInquiryLightRequest(searchLight,
						0,
						null,
						lightIds,
						null,
						LightOrderByEnum.STATUS.getValue(),
						null);

		InternalResultsResponse<Light> response = getSmartpointDAC().fetchAllLights(inquiryLightRequest);
		assertResultResponse(response);

		lights = response.getResultsList();
		assertEquals(RESULT_AMOUNT_SHOULD_BE_EQUALS_SELECTED, lightIds.size(), response.getResultsList().size());

		for (Light light : lights)
		{
			if (!lightIds.contains(light.getSmartPointId()))
			{
				fail("Light should not be in list");
			}
		}
	}

	/**
	 * Test fetch all lights.
	 */
	@Test
	public void testFetchAllLights()
	{
		UserContext userContext = createUserContext();
		int lightAmount = ARBITRARY_AMOUNT_5;
		int pageSize = ARBITRARY_PAGE_SIZE_10;

		List<Integer> lightIdList = new ArrayList<Integer>();
		List<Light> lights = insertLights(lightAmount);
		Group group = insertGroup();
		addLightsToGroup(lights, group);

		for (Light light : lights)
		{
			this.insertStatusMessage(light, StatusMessageCategoryEnum.ALARM, LightStatusEnum.ALARM,
					StatusExceptionTypeEnum.POWER_FAILURE);
			lightIdList.add(light.getId());
		}

		// Test fetch all only
		List<SearchParameter> searchParameters = new ArrayList<SearchParameter>();
		InternalResultsResponse<Light> response =
				fetchAllLights(lightIdList, searchParameters, null, null, pageSize, null, null);
		assertResultResponse(response);

		// Restriction test
		this.setGroupsToUser(userContext, group);
		response = fetchAllLights(lightIdList, searchParameters, null, null, pageSize, null, null);
		assertResultResponse(response);
		assertTrue(LIGHT_LIST_SHOULD_HAVE_ONLY_5_REGISTERS, response.getResultsList().size() == lightAmount);
		resetGroupsToUser(userContext);

		List<LightStatusEnum> statusList = new ArrayList<LightStatusEnum>();
		statusList.add(LightStatusEnum.ALARM);
		statusList.add(LightStatusEnum.WARNING);

		// Test with status list
		response =
				fetchAllLights(lightIdList, searchParameters, null, null, pageSize, statusList, null);
		assertResultResponse(response);

		// Restriction test
		this.setGroupsToUser(userContext, group);
		response =
				fetchAllLights(lightIdList, searchParameters, null, null, pageSize, statusList, null);
		assertResultResponse(response);
		assertTrue(LIGHT_LIST_SHOULD_HAVE_ONLY_5_REGISTERS, response.getResultsList().size() == lightAmount);
		resetGroupsToUser(userContext);

		HashMap<String, List<Integer>> hashPropValidValues = new HashMap<String, List<Integer>>();
		hashPropValidValues.put("LAMP_TYPE", Arrays.asList(PropertyEnum.LAMP_TYPE.getValue()));
		searchParameters.add(new SearchParameter(PropertyEnum.PROTECTED, null));
		searchParameters.add(new SearchParameter(PropertyEnum.CURRENT_ALARM_WARNING_STATUS_SUBTYPE, ONE));
		searchParameters.add(new SearchParameter(PropertyEnum.CURRENT_ALARM_WARNING_STATUS_SUBTYPE, TWO));
		searchParameters.add(new SearchParameter(PropertyEnum.CURRENT_ALARM_WARNING_STATUS_SUBTYPE, SIX));
		searchParameters.add(new SearchParameter(PropertyEnum.LAMP_TYPE, SIX));
		// Test with status list and hash properties valid values
		response =
				fetchAllLights(lightIdList, searchParameters, false, null, pageSize, statusList, null);
		assertResultResponse(response);

		// Restriction test
		this.setGroupsToUser(userContext, group);
		response =
				fetchAllLights(lightIdList, searchParameters, false, null, pageSize, statusList, null);
		assertResultResponse(response);
		assertTrue(LIGHT_LIST_SHOULD_HAVE_ONLY_5_REGISTERS, response.getResultsList().size() == lightAmount);
		resetGroupsToUser(userContext);

		searchParameters.add(new SearchParameter(PropertyEnum.PROTECTED, null));
		searchParameters.add(new SearchParameter(PropertyEnum.CURRENT_ALARM_WARNING_STATUS_SUBTYPE, ONE));
		searchParameters.add(new SearchParameter(PropertyEnum.CURRENT_ALARM_WARNING_STATUS_SUBTYPE, TWO));
		searchParameters.add(new SearchParameter(PropertyEnum.CURRENT_ALARM_WARNING_STATUS_SUBTYPE, SIX));

		// Test with searchParameters
		response = fetchAllLights(lightIdList, searchParameters, null, null, pageSize, null, null);
		assertResultResponse(response);

		// Restriction test
		this.setGroupsToUser(userContext, group);
		response = fetchAllLights(lightIdList, searchParameters, null, null, pageSize, null, null);
		assertResultResponse(response);
		assertTrue(LIGHT_LIST_SHOULD_HAVE_ONLY_5_REGISTERS, response.getResultsList().size() == lightAmount);
		resetGroupsToUser(userContext);

		searchParameters = new ArrayList<SearchParameter>();
		searchParameters.add(new SearchParameter(PropertyEnum.GROUP_ID, String.valueOf(group.getId())));

		// Test searchParameters with GROUP ID
		response = fetchAllLights(lightIdList, searchParameters, null, null, pageSize, null, null);
		assertResultResponse(response);

		// Restriction test
		this.setGroupsToUser(userContext, group);
		response = fetchAllLights(lightIdList, searchParameters, null, null, pageSize, null, null);
		assertResultResponse(response);
		assertTrue(LIGHT_LIST_SHOULD_HAVE_ONLY_5_REGISTERS, response.getResultsList().size() == lightAmount);
		resetGroupsToUser(userContext);

		// Restriction test
		this.setGroupsToUser(userContext, insertGroup());
		response = fetchAllLights(lightIdList, searchParameters, null, null, pageSize, null, null);
		assertFalse("Light list should be empty ", response.hasResults());
	}

	/**
	 * Check between eco mode value.
	 *
	 * @param light the light
	 * @param ecoModeFilter the eco mode filter
	 * @return the int
	 */
	private int checkBetweenEcoModeValue(Light light, EcoModeFilterEnum ecoModeFilter)
	{
		Double ecoMode = light.getEcoMode();
		if ((ecoMode > ecoModeFilter.getInitialValue()) && (ecoMode <= ecoModeFilter.getEndValue()))
		{
			return 1;
		}
		return 0;
	}

	/**
	 * Test fetch all lights by eco mode.
	 */
	@Test
	public void testFetchAllLightsByEcoMode()
	{
		createUserContext();
		int lightAmount = ARBITRARY_AMOUNT_10;
		int pageSize = ARBITRARY_PAGE_SIZE_10;

		List<Light> lights = insertLights(lightAmount);
		int ecomodeEconomyAmount = 0;
		int ecomodeValueAmount = 0;
		int ecomodeSecurityAmount = 0;

		for (Light light : lights)
		{
			insertLightConsumption(light, 0);
			upsertEcoMode(light);
			ecomodeEconomyAmount += checkBetweenEcoModeValue(light, EcoModeFilterEnum.ECONOMY);
			ecomodeValueAmount += checkBetweenEcoModeValue(light, EcoModeFilterEnum.VALUE);
			ecomodeSecurityAmount += checkBetweenEcoModeValue(light, EcoModeFilterEnum.SECURITY);
		}

		// Extract lightIdlist
		List<Integer> lightIdlist = extractLightId(lights);

		// Filter by ecomode ECONOMY
		EcoModeFilterEnum economy = EcoModeFilterEnum.ECONOMY;
		List<SearchParameter> searchParameters =
				Arrays.asList(new SearchParameter(PropertyEnum.ECOMODE, economy.getValue()));

		InternalResultsResponse<Light> response =
				fetchAllLights(lightIdlist, searchParameters, null, null, pageSize, null, null);
		if (response.hasResults())
		{
			assertResultResponse(response);
		}
		assertEquals(ecomodeEconomyAmount, response.getResultsList().size());

		// Filter by ecomode VALUE
		EcoModeFilterEnum value = EcoModeFilterEnum.VALUE;
		searchParameters = Arrays.asList(new SearchParameter(PropertyEnum.ECOMODE, value.getValue()));

		response = fetchAllLights(lightIdlist, searchParameters, null, null, pageSize, null, null);
		if (response.hasResults())
		{
			assertResultResponse(response);
		}
		assertEquals(ecomodeValueAmount, response.getResultsList().size());

		// Filter by ecomode SECURITY
		EcoModeFilterEnum security = EcoModeFilterEnum.SECURITY;
		searchParameters = Arrays.asList(new SearchParameter(PropertyEnum.ECOMODE, security.getValue()));

		response = fetchAllLights(lightIdlist, searchParameters, null, null, pageSize, null, null);
		if (response.hasResults())
		{
			assertResultResponse(response);
		}
		assertEquals(ecomodeSecurityAmount, response.getResultsList().size());
	}

	/**
	 * Test fetch all lights by process.
	 */
	@Test
	public void testFetchAllLightsByProcess()
	{
		Process process = this.insertProcess(getLightDefault());
		ProcessRequest request = createProcessRequest();
		request.setProcess(process);

		// Test Success
		InternalResultsResponse<Light> response = getSmartpointDAC().fetchAllLightsByProcess(request);
		assertResultResponse(response);

		List<ProcessItem> processItemList = new ArrayList<ProcessItem>();
		ProcessItem processItem = new ProcessItem();

		processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.MLCFAILURE);
		processItem.setProcessStatusReasonEnum(ProcessStatusReasonEnum.ABORTED);
		processItemList.add(processItem);
		process.setProcessItems(processItemList);
		request.setProcess(process);

		response = getSmartpointDAC().fetchAllLightsByProcess(request);
		assertTrue("Status should to be no rows found error", Status.NoRowsFoundError == response.getStatus());
		assertFalse("Light list should be empty", response.hasResults());

		request.getProcess().setProcessItems(null);
		response = getSmartpointDAC().fetchAllLightsByProcess(request);
		assertResultResponse(response);

		process.setProcessItems(Arrays.asList(new ProcessItem(ProcessItemStatusEnum.MLCFAILURE)));
		request.setProcess(process);
		response = getSmartpointDAC().fetchAllLightsByProcess(request);
	}

	/**
	 * Test insert complete status message.
	 */
	@Test
	public void testInsertCompleteStatusMessage()
	{
		// Test insert message without status exception
		this.insertStatusMessage(getLightDefault(), StatusMessageCategoryEnum.ALARM, LightStatusEnum.ACTIVE);

		// Test insert message without status exception
		this.insertStatusMessage(getLightDefault(), StatusMessageCategoryEnum.ALARM, LightStatusEnum.ACTIVE,
				StatusExceptionTypeEnum.LAMP_FAILURE);
	}

	/**
	 * Test fetch status message by light id.
	 */
	@Test
	public void testFetchStatusMessageByLightID()
	{
		List<Integer> allowedGroupIdList = extractAuthorityId(createUserContext().getAuthorities());
		InternalResultsResponse<StatusMessage> response = getSmartpointDAC().fetchStatusMessageByLightID(
				getLightDefault().getId(),
				allowedGroupIdList);
		assertResponse(response);
	}

	/**
	 * Test fetch status message id.
	 */
	@Test
	public void testFetchStatusMessageId()
	{
		// Test Success with existent status message
		LightRequest lightRequest = new LightRequest();
		List<StatusMessage> statusMessageList = new ArrayList<StatusMessage>();
		statusMessageList.add(insertStatusMessage(getLightDefault(), StatusMessageCategoryEnum.ALARM,
				LightStatusEnum.ACTIVE));
		List<Light> lightList = new ArrayList<Light>();
		lightRequest.setLights(lightList);
		lightRequest.setStatusMessages(statusMessageList);

		InternalResultsResponse<StatusMessage> response = getSmartpointDAC().fetchStatusMessageById(lightRequest);
		assertResultResponse(response);

		// Test Success with inexistent status message
		lightRequest.getFirstMessage().setId(RANDOM.nextInt(NUMBER_RANGE));
		response = getSmartpointDAC().fetchStatusMessageById(lightRequest);
		assertTrue(response.getStatus().equals(Status.NoRowsFoundError));

	}

	/**
	 * Test fetch status message by light id message type.
	 */
	@Test
	public void testFetchStatusMessageByLightIDMessageType()
	{
		// Test Success without status message
		InternalResultsResponse<StatusMessage> response =
				getSmartpointDAC().fetchStatusMessageByLightIDMessageType(getLightDefault().getId(),
						StatusMessageCategoryEnum.ALARM, null);
		assertTrue(response.getStatus().equals(Status.NoRowsFoundError));

		// Test Success with status message
		insertStatusMessage(getLightDefault(), StatusMessageCategoryEnum.ALARM, LightStatusEnum.ACTIVE);
		response =
				getSmartpointDAC().fetchStatusMessageByLightIDMessageType(getLightDefault().getId(),
						StatusMessageCategoryEnum.ALARM, null);
		assertResultResponse(response);

	}

	/**
	 * Test fetch light id by rni id.
	 */
	@Test
	public void testFetchLightByRniId()
	{
		LightRequest lightRequest = createLightRequest();
		lightRequest.addLight(getLightDefault());
		InternalResultsResponse<Light> response = getSmartpointDAC().fetchLightByRniId(lightRequest);
		assertResultResponse(response);
		assertLight(response.getFirstResult());

		Group group = insertGroup();
		this.setGroupsToUser(lightRequest.getUserContext(), group);
		response = getSmartpointDAC().fetchLightByRniId(lightRequest);
		assertFalse(response.hasResults());

		addLightsToGroup(Arrays.asList(getLightDefault()), group);
		response = getSmartpointDAC().fetchLightByRniId(lightRequest);
		assertResultResponse(response);
		assertLight(response.getFirstResult());
	}

	/**
	 * Test count lights.
	 */
	@Test
	public void testCountLights()
	{
		// Test without light
		LightRequest lightRequest = createLightRequest();
		InternalResultsResponse<Integer> response = getSmartpointDAC().countLights(lightRequest);
		assertResultResponse(response);

		// Test with light
		lightRequest.addLight(getLightDefault());
		response = getSmartpointDAC().countLights(lightRequest);
		assertResultResponse(response);

		// Test validation
		InquiryLightRequest inquiryLightRequest = createInquiryLightRequest(createUserContext());
		response = getSmartpointDAC().countLights(inquiryLightRequest);
		assertResultResponse(response);

		assertEquals(response.getFirstResult().intValue() > 0, true);
	}

	/**
	 * Test fetch valid values.
	 */
	@Test
	public void testFetchPropertyValidValues()
	{
		List<LightParameter> lightParameters = new ArrayList<LightParameter>();
		lightParameters.add(new LightParameter(PropertyEnum.LAMP_TYPE, ONE));
		lightParameters.add(new LightParameter(PropertyEnum.WATTAGE_RATING, TWO));
		lightParameters.add(new LightParameter(PropertyEnum.INPUT_VOLTAGE_RANGE, "3"));

		List<Light> lights = insertLights(ARBITRARY_AMOUNT_3);
		for (Light light : lights)
		{
			upsertLightProperty(light, lightParameters);
		}

		PropertyValidValuesRequest request = createPropertyValidValuesRequest();
		request.setProperties(PROPERTY);
		List<PropertyValidValue> propertyValidValues = getSmartpointDAC().fetchPropertyValidValues(request);

		assertNotNull("Property valid values ​​should be not null", propertyValidValues);
		assertTrue("Property valid values should be greater than 0", propertyValidValues.size() > 0);
	}

	/**
	 * Test fetch light intensity percentage by light.
	 */
	@Test
	public void testFetchLightIntensityPercentageByLight()
	{
		// Test Success
		InternalResultsResponse<SensusPartNumberConfiguration> response =
				getSmartpointDAC().fetchLightIntensityPercentageByLight(getLightDefault().getId());
		assertResultResponse(response);
	}

	/**
	 * Test fetch ligthing configurations by part number.
	 */
	@Test
	public void testFetchLigthingConfigurationsByPartNumber()
	{
		// Test Success
		LightingConfigurationRequest request = createLightingConfigurationRequest();
		request.setLight(getLightDefault());

		InternalResultsResponse<SensusPartNumberConfiguration> response =
				getSmartpointDAC().fetchLigthingConfigurationsByPartNumber(request);
		assertResultResponse(response);

		for (SensusPartNumberConfiguration conf : response.getResultsList())
		{
			assertSensusPartNumberConfiguration(conf);
		}
	}

	/**
	 * Fetch last status message calendar from light test.
	 */
	@Test
	public void testFetchLastStatusMessageCalendarFromLight()
	{
		// Test Success
		insertStatusMessage(getLightDefault());

		Calendar lastStatusMessage =
				getSmartpointDAC().fetchLastStatusMessageCalendarFromLight(getLightDefault().getId());
		assertNotNull("Last status message should be not null", lastStatusMessage);

		lastStatusMessage =
				getSmartpointDAC().fetchLastStatusMessageCalendarFromLight(new Random().nextInt(NUMBER_RANGE));
		assertTrue(ValidationUtil.isNull(lastStatusMessage));

	}

	/**
	 * Fetch last status subtype id from light test.
	 */
	@Test
	public void testFetchLastStatusSubtypeIDFromLight()
	{
		// Test Success
		insertStatusMessage(getLightDefault(), StatusMessageCategoryEnum.ALARM, LightStatusEnum.ACTIVE,
				StatusExceptionTypeEnum.LAMP_FAILURE);

		Integer lastSubtypeMessageID =
				getSmartpointDAC().fetchLastStatusSubtypeIDFromLight(getLightDefault().getId());
		assertNotNull(lastSubtypeMessageID);
		assertTrue(lastSubtypeMessageID > 0);
	}

	/**
	 * Test insert status message status subtype.
	 */
	@Test
	public void testInsertStatusMessageStatusSubtype()
	{
		// insert a new Status Message
		StatusMessage statusMessage =
				createStatusMessage(StatusMessageCategoryEnum.ALARM, LightStatusEnum.ACTIVE,
						StatusExceptionTypeEnum.LAMP_FAILURE);

		UserContext userContext = createUserContext();
		Integer statusMessageId = getSmartpointDAC().insertStatusMessage(
				statusMessage,
				userContext.getUserId(),
				userContext.getTenant().getId(),
				getLightDefault().getId(),
				false);
		assertTrue(statusMessageId > 0);

		// fetch in status message sub-type.
		Integer responseSubtype =
				getSmartpointDAC().fetchStatusMessageStatusSubtype(
						statusMessageId,
						StatusExceptionTypeEnum.LAMP_FAILURE.getValue(),
						getUserDefault().getCreateUser());
		assertEquals(responseSubtype == 0, true);

		// insert a new Status Message Status Subtype
		getSmartpointDAC().insertStatusMessageStatusSubtype(statusMessageId,
				StatusExceptionTypeEnum.LAMP_FAILURE.getValue(),
				getUserDefault().getCreateUser(), false);

		// fetch in status message sub-type.
		responseSubtype =
				getSmartpointDAC().fetchStatusMessageStatusSubtype(
						statusMessageId,
						StatusExceptionTypeEnum.BOARD_FAILURE.getValue(),
						getUserDefault().getCreateUser());
		assertEquals(responseSubtype == 0, true);

		// insert another Status Message Status Subtype
		getSmartpointDAC().insertStatusMessageStatusSubtype(statusMessageId,
				StatusExceptionTypeEnum.BOARD_FAILURE.getValue(),
				getUserDefault().getCreateUser(), true);
	}

	/**
	 * Test fetch status message status subtype.
	 */
	@Test
	public void testFetchStatusMessageStatusSubtype()
	{
		//Test Success
		// insert a new Status Message
		StatusMessage statusMessage =
				createStatusMessage(StatusMessageCategoryEnum.ALARM, LightStatusEnum.ACTIVE,
						StatusExceptionTypeEnum.BOARD_FAILURE);

		UserContext userContext = createUserContext();
		Integer statusMessageId = getSmartpointDAC().insertStatusMessage(
				statusMessage,
				getUserDefault().getCreateUser(),
				userContext.getTenant().getId(),
				getLightDefault().getId(),
				false);
		assertTrue(statusMessageId > 0);

		// fetch in status message sub-type.
		Integer responseSubtype =
				getSmartpointDAC().fetchStatusMessageStatusSubtype(
						statusMessageId,
						StatusExceptionTypeEnum.BOARD_FAILURE.getValue(),
						getUserDefault().getCreateUser());
		assertEquals(responseSubtype == 0, true);

		// insert a new Status Message Status Subtype
		getSmartpointDAC().insertStatusMessageStatusSubtype(statusMessageId,
				StatusExceptionTypeEnum.BOARD_FAILURE.getValue(),
				getUserDefault().getCreateUser(), false);

		//fetch in status message sub-type.
		responseSubtype =
				getSmartpointDAC().fetchStatusMessageStatusSubtype(
						statusMessageId,
						StatusExceptionTypeEnum.BOARD_FAILURE.getValue(),
						getUserDefault().getCreateUser());
		assertEquals(responseSubtype > 0, true);

	}

	/**
	 * Test update analytics alarm warning.
	 */
	@Test
	public void testUpdateAnalyticsAlarmWarning()
	{
		UserContext userContext = createUserContext();
		StatusMessage statusMessage =
				insertStatusMessage(getLightDefault(), StatusMessageCategoryEnum.ALARM, LightStatusEnum.ACTIVE,
						StatusExceptionTypeEnum.LAMP_FAILURE);

		// Test update warning with tenantId
		InternalResponse internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(
						userContext.getTenant().getId(),
						getLightDefault().getId(), StatusExceptionTypeEnum.LAMP_FAILURE.getValue(),
						userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse = getSmartpointDAC().updateAnalyticsAlarmWarning(userContext.getTenant().getId(),
				getLightDefault().getId(), StatusExceptionTypeEnum.POWER_FAILURE.getValue(),
				userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse = getSmartpointDAC().updateAnalyticsAlarmWarning(userContext.getTenant().getId(),
				getLightDefault().getId(), StatusExceptionTypeEnum.BOARD_FAILURE.getValue(),
				userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse = getSmartpointDAC().updateAnalyticsAlarmWarning(userContext.getTenant().getId(),
				getLightDefault().getId(), StatusExceptionTypeEnum.METROLOGY_COM_FAILURE.getValue(),
				userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse = getSmartpointDAC().updateAnalyticsAlarmWarning(userContext.getTenant().getId(),
				getLightDefault().getId(), StatusExceptionTypeEnum.METROLOGY_ERROR.getValue(),
				userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		// Test update warning without tenantId
		internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(StatusExceptionTypeEnum.LAMP_FAILURE.getValue(),
						statusMessage.getId(),
						userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(StatusExceptionTypeEnum.POWER_FAILURE.getValue(),
						statusMessage.getId(),
						userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(StatusExceptionTypeEnum.BOARD_FAILURE.getValue(),
						statusMessage.getId(),
						userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(
						StatusExceptionTypeEnum.METROLOGY_COM_FAILURE.getValue(), statusMessage.getId(),
						userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(StatusExceptionTypeEnum.METROLOGY_ERROR.getValue(),
						statusMessage.getId(),
						userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		// Test update alarm
		internalResponse = getSmartpointDAC().updateAnalyticsAlarmWarning(userContext.getTenant().getId(),
				getLightDefault().getId(), StatusExceptionTypeEnum.BROWN_OUT_DETECTED.getValue(),
				userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse = getSmartpointDAC().updateAnalyticsAlarmWarning(userContext.getTenant().getId(),
				getLightDefault().getId(), StatusExceptionTypeEnum.POWER_SURGE_DETECTED.getValue(),
				userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse = getSmartpointDAC().updateAnalyticsAlarmWarning(userContext.getTenant().getId(),
				getLightDefault().getId(), StatusExceptionTypeEnum.HIGH_CURRENT.getValue(),
				userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse = getSmartpointDAC().updateAnalyticsAlarmWarning(userContext.getTenant().getId(),
				getLightDefault().getId(), StatusExceptionTypeEnum.LOW_CURRENT.getValue(),
				userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse = getSmartpointDAC().updateAnalyticsAlarmWarning(userContext.getTenant().getId(),
				getLightDefault().getId(), StatusExceptionTypeEnum.REVERSE_ENERGY.getValue(),
				userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse = getSmartpointDAC().updateAnalyticsAlarmWarning(userContext.getTenant().getId(),
				getLightDefault().getId(), StatusExceptionTypeEnum.METROLOGY_RESET.getValue(),
				userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse = getSmartpointDAC().updateAnalyticsAlarmWarning(userContext.getTenant().getId(),
				getLightDefault().getId(), StatusExceptionTypeEnum.COMMUNICATION_FAIL.getValue(),
				userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		// Test update alarm without tenantId
		internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(StatusExceptionTypeEnum.BROWN_OUT_DETECTED.getValue(),
						statusMessage.getId(),
						userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(StatusExceptionTypeEnum.POWER_SURGE_DETECTED.getValue(),
						statusMessage.getId(),
						userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(StatusExceptionTypeEnum.HIGH_CURRENT.getValue(),
						statusMessage.getId(),
						userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(StatusExceptionTypeEnum.LOW_CURRENT.getValue(),
						statusMessage.getId(),
						userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(StatusExceptionTypeEnum.REVERSE_ENERGY.getValue(),
						statusMessage.getId(),
						userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(StatusExceptionTypeEnum.METROLOGY_RESET.getValue(),
						statusMessage.getId(),
						userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

		internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(StatusExceptionTypeEnum.COMMUNICATION_FAIL.getValue(),
						statusMessage.getId(),
						userContext.getUserId(), OPERATOR);
		assertResponse(internalResponse);

	}

	/**
	 * Fetch last status message date from light test.
	 */
	@Test
	public void testFetchLastStatusMessageDateFromLight()
	{
		// Test Success
		insertStatusMessage(getLightDefault());
		Date lastStatusMessage =
				getSmartpointDAC().fetchLastStatusMessageDateFromLight(getLightDefault().getId());
		assertNotNull(lastStatusMessage);
	}

	/**
	 * Test fetch light history header.
	 */
	@Test
	public void testFetchLightHistoryHeader()
	{
		// Test Success
		LightRequest lightRequest = createLightRequest();
		lightRequest.addLight(getLightDefault());
		InternalResultsResponse<HashMap<String, Integer>> response =
				getSmartpointDAC().fetchLightHistoryHeader(lightRequest);
		assertResultResponse(response);
	}

	/**
	 * Fetch lights by tenant test.
	 */
	@Test
	public void testFetchLightsByTenant()
	{
		// Test Success
		insertLights(ARBITRARY_AMOUNT_5);
		List<Light> lights = getSmartpointDAC().fetchLightsByTenant(createUserContext().getTenant().getId());
		assertNotNull(lights);
		assertTrue(lights.size() > ARBITRARY_AMOUNT_5);
	}

	/*
	 * Test reset min max values.
	 */
	/**
	 * Test reset min max.
	 */
	@Test
	public void testResetMinMax()
	{
		// Test Success
		getLightDefault().setProtect(true);

		LightRequest request = createLightRequest();
		request.addLight(getLightDefault());

		InternalResponse response = getSmartpointDAC().resetMinMaxValue(request);
		assertResponse(response);
	}

	/**
	 * Test generate file csv.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGenerateFileCSV() throws IOException
	{
		HashMap<String, String> lights = new HashMap<String, String>();

		lights.put(RNI_ID, "3863");
		lights.put("pole_id", "123-4546-987");
		lights.put("light_type", "40W Induction");
		lights.put("date_added", "04/16/2010");
		lights.put("city", "Portland");
		lights.put("protect", "true");
		lights.put("light_status", "ACTIVE");
		lights.put("address", "SE Stark St. USA Portland Oregon 553447");
		lights.put("latitude", "45.4957997966828");
		lights.put("longitude", "-122.800027741732");
		lights.put("last_message_received", "2012-01-11 18:54:23.765");
		lights.put("lightState", "ON");
		lights.put("light_voltage", "0.001");
		lights.put("light_voltage_monthly", "119.52");
		lights.put("light_current", "0.1");
		lights.put("light_current_monthly", "0.00");
		lights.put("light_consumption", "0.0");
		lights.put("light_consumption_monthly", "96.0");
		lights.put("alarm", "");
		lights.put("warnings", "");
		lights.put("sunset_time", "20:24:23");
		lights.put("sunrise_time", "06:24:23");
		lights.put("offset_schedule", "-11");
		lights.put("event_schedule", "-10");
		lights.put("schedule_offset", "");
		lights.put("schedule_event", "");
		lights.put("input_voltage", "90V AC to 300V AC");
		lights.put("color_temperature", "3,000K");
		lights.put("housing_color", "Silver");
		lights.put("manufacturer", "QATBOT");
		lights.put("model_number", "539449037C232134");
		lights.put("bulb_number", "");
		lights.put("light_driver_number", "");
		lights.put("lower_assembly", "");
		lights.put("upper_assembly", "");
		lights.put("firmware_version", "1.0.0");
		lights.put(ECOMODE, "11");

		List<HashMap<String, String>> lightList = new ArrayList<HashMap<String, String>>();
		lightList.add(lights);

		// Test success with column name - "ecomode"
		Column column = new Column();
		column.setColumnEnum(SmartPointColumnEnum.ECOMODE);
		column.setFieldName(ECOMODE);

		InquiryLightRequest lightRequest = createInquiryLightRequest(createUserContext());
		lightRequest.setFileName(TestBaseUtil.FILE_NAME);
		lightRequest.setLightsToCSV(lightList);
		lightRequest.setListColumn(Arrays.asList(column));

		InquiryLightResponse response = getSmartpointDAC().generateFileCSV(lightRequest);
		assertNotNull(response);
		assertTrue(response.isOperationSuccess());

		// check dimensions...
		LightMapCSVDataSource ds = new LightMapCSVDataSource(lightRequest);
		TestBaseUtil.checkCSVFileDimensions(lightList.size(), ds);

		// Test success with column name - "rni_id"
		column.setColumnEnum(SmartPointColumnEnum.RNI_ID);
		column.setFieldName(RNI_ID);
		response = getSmartpointDAC().generateFileCSV(lightRequest);
		assertNotNull(response);
		assertTrue(response.isOperationSuccess());

		// check dimensions...
		TestBaseUtil.checkCSVFileDimensions(lightList.size(), ds);

		// Test success with unknown column name
		column.setColumnEnum(SmartPointColumnEnum.CITY_NAME);
		column.setFieldName("unknownName");
		response = getSmartpointDAC().generateFileCSV(lightRequest);
		assertNotNull(response);
		assertTrue(response.isOperationSuccess());

		// check dimensions...
		TestBaseUtil.checkCSVFileDimensions(lightList.size(), ds);

	}

	/**
	 * Test generate summary file csv.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGenerateSummaryFileCSV() throws IOException
	{
		InquiryLightRequest inquiryLightRequest = TestBaseUtil.createInquiryLightRequest(createUserContext());
		inquiryLightRequest.setFileName(TestBaseUtil.FILE_NAME);
		inquiryLightRequest.setLight(insertLight());

		InquiryLightResponse response = getSmartpointDAC().generateSummaryFileCSV(inquiryLightRequest);
		assertNotNull(response);
		assertTrue(response.isOperationSuccess());

		// check dimensions...
		LightCSVDataSource ds = new LightCSVDataSource(inquiryLightRequest);
		TestBaseUtil.checkCSVFileDimensions(inquiryLightRequest.getLights().size(), ds);
	}

	/**
	 * Test generate light history file csv.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGenerateLightHistoryFileCSV() throws IOException
	{
		InquiryLightRequest inquiryLightRequest =
				createInquiryLightRequest(null, 0, ARBITRARY_PAGE_SIZE_25, null, null, null, null);
		inquiryLightRequest.setLight(getLightDefault());
		inquiryLightRequest.setFileName(TestBaseUtil.FILE_NAME);
		inquiryLightRequest.setTimezone(TimeZone.getDefault().getID());
		insertGroup();

		InternalResponse response = getSmartpointDAC().generateLightHistoryFileCSV(inquiryLightRequest);
		assertResponse(response);

		// check dimensions...
		List<LightHistory> lightHistoryList =
				getSmartpointDAC().fetchLightHistory(inquiryLightRequest).getResultsList();
		LightHistoryCSVDataSource ds = new LightHistoryCSVDataSource(inquiryLightRequest, lightHistoryList);
		TestBaseUtil.checkCSVFileDimensions(lightHistoryList.size(), ds);
	}

	/**
	 * Test fetch all lights.
	 */
	@Test
	public void testFetchAllLightsToCSV()
	{
		Tag tag = this.insertTag();

		List<Light> lights = insertLights(ARBITRARY_AMOUNT_10);

		for (Light light : lights)
		{
			if ((light.getId() % 2) == 0)
			{
				this.insertStatusMessage(light, StatusMessageCategoryEnum.SETUP, LightStatusEnum.ALARM);
			}
			else
			{
				this.insertStatusMessage(light, StatusMessageCategoryEnum.SETUP, LightStatusEnum.WARNING);
			}

			upsertLightProperty(light, Arrays.asList(
					new LightParameter(PropertyEnum.PROTECTED, null),
					new LightParameter(PropertyEnum.TAG_ID, String.valueOf(tag.getId()))));
		}

		// Test success without filter
		InquiryLightRequest inquiryLightRequest =
				createInquiryLightRequest(null, 0, ARBITRARY_PAGE_SIZE_10, null, null,
						LightOrderByEnum.STATUS.getValue(), null);
		inquiryLightRequest.getUserContext().setId(ARBITRARY_USER_CONTEXT_ID); // 5 is UserId of the superuser.

		InternalResultsResponse<HashMap<String, String>> response =
				getSmartpointDAC().fetchAllLightsToCSV(inquiryLightRequest);
		assertResultResponse(response);

		// Test success with status list filter
		SearchLight searchLight = new SearchLight();
		searchLight.setStatusList(Arrays.asList(LightStatusEnum.ALARM, LightStatusEnum.WARNING));

		searchLight.addSearchParameter(new SearchParameter(PropertyEnum.LAMP_TYPE, LightTypeEnum.INDUCTION.getValue().toString()));

		inquiryLightRequest =
				createInquiryLightRequest(searchLight, 0, ARBITRARY_PAGE_SIZE_10, null, null,
						LightOrderByEnum.STATUS.getValue(), null);
		inquiryLightRequest.getUserContext().setId(ARBITRARY_USER_CONTEXT_ID); // 5 is UserId of the superuser.
		inquiryLightRequest.setSearch(searchLight);

		response = getSmartpointDAC().fetchAllLightsToCSV(inquiryLightRequest);
		assertResultResponse(response);
	}

	/**
	 * Test fetch all lights.
	 */
	@Test
	public void testFetchAllLightsAdvanced()
	{
		List<LightParameter> lightParameters = new ArrayList<LightParameter>();
		lightParameters.add(new LightParameter(PropertyEnum.CURRENT_ALARM_WARNING_STATUS_SUBTYPE, ONE));

		lightParameters.add(new LightParameter(PropertyEnum.PAGE_SIZE, "2001"));
		lightParameters.add(new LightParameter(PropertyEnum.PAGE_SIZE, "2002"));

		lightParameters.add(new LightParameter(PropertyEnum.ALL_ALARMS, ONE));
		lightParameters.add(new LightParameter(PropertyEnum.ALL_OFFSETS, ""));
		lightParameters.add(new LightParameter(PropertyEnum.SORT, LightOrderByEnum.POLE_ID.getValue()));

		List<Light> lights = insertLights(ARBITRARY_AMOUNT_10);

		// Set status message
		for (Light light : lights)
		{
			this.insertStatusMessage(light, StatusMessageCategoryEnum.ALARM, LightStatusEnum.ALARM,
					StatusExceptionTypeEnum.LAMP_FAILURE);
		}

		ScheduleRequest scheduleRequest = createScheduleRequest();
		OffsetSchedule schedule = insertScheduleOffset(scheduleRequest);
		schedule.setLights(lights);

		applySmartPointToSchedule(schedule, lights);

		for (Light light : lights)
		{
			upsertLightProperty(light, lightParameters);
		}

		List<SearchParameter> searchParameters = new ArrayList<SearchParameter>();
		searchParameters.add(new SearchParameter(PropertyEnum.PAGE_SIZE, "2000"));
		searchParameters.add(new SearchParameter(PropertyEnum.ALL_ALARMS, ONE));
		searchParameters.add(new SearchParameter(PropertyEnum.ALL_WARNINGS, TWO));
		searchParameters.add(new SearchParameter(PropertyEnum.ALL_OFFSETS, ""));
		searchParameters.add(new SearchParameter(PropertyEnum.SORT, LightOrderByEnum.POLE_ID.getValue() + " asc "));

		SearchLight searchLight = new SearchLight();
		searchLight.setSearchParameters(searchParameters);

		InquiryLightRequest inquiryLightRequest =
				createInquiryLightRequest(searchLight, 0, ARBITRARY_PAGE_SIZE_5, null, null,
						LightOrderByEnum.POLE_ID.getValue() + " asc", true);

		InternalResultsResponse<Light> response = getSmartpointDAC().fetchAllLights(inquiryLightRequest);
		assertResultResponse(response);
	}

	/**
	 * Test fetch property valid values.
	 */
	@Test
	public void testFetchAllLightsToApplySchedule()
	{
		List<Light> lights = insertLights(ARBITRARY_AMOUNT_4);
		List<Integer> lightIdList = extractLightId(lights);

		ScheduleRequest request = createScheduleRequest();
		request.setLightIdList(lightIdList);
		InternalResultsResponse<Light> response = getSmartpointDAC().fetchAllLightsToApplySchedule(request);
		assertResultResponse(response);

		Group group = insertGroup();
		this.setGroupsToUser(request.getUserContext(), group);
		response = getSmartpointDAC().fetchAllLightsToApplySchedule(request);
		assertFalse(response.hasResults());

		addLightsToGroup(lights, group);
		response = getSmartpointDAC().fetchAllLightsToApplySchedule(request);
		assertResultResponse(response);
	}

	/**
	 * Test fetch light history.
	 */
	@Test
	public void testFetchLightHistory()
	{
		createUserContext();

		// Test Fail
		Process process = this.insertProcess(getLightDefault());
		ProcessFilter processFilter = createProcessFilter(getUserDefault().getId());
		processFilter.setLightTextSearch(createLightTextSearch(LightPropertyForSearchEnum.RNI_ID,
				process.getLcAction().getDescription()));

		InquiryLightRequest inquiryLightRequest =
				createInquiryLightRequest(null, 0, ARBITRARY_PAGE_SIZE_15, null, null, null, null);
		inquiryLightRequest.setLight(getLightDefault());
		inquiryLightRequest.setProcessFilter(processFilter);

		InternalResultsResponse<LightHistory> response = getSmartpointDAC().fetchLightHistory(inquiryLightRequest);
		assertEquals(0, response.getResultsList().size());

		// Test Success
		inquiryLightRequest =
				createInquiryLightRequest(null, 0, ARBITRARY_PAGE_SIZE_25, null, null, null, null);
		inquiryLightRequest.setLight(getLightDefault());
		insertGroup();
		response = getSmartpointDAC().fetchLightHistory(inquiryLightRequest);
		assertResultResponse(response);

		// Test Validation
		processFilter.getLightTextSearch().setLightProperty(LightPropertyForSearchEnum.ACTION_ID);
		processFilter.getLightTextSearch().setSearchText(String.valueOf(new Random().nextInt(NUMBER_RANGE)));

		inquiryLightRequest =
				createInquiryLightRequest(null, 0, ARBITRARY_PAGE_SIZE_15, null, null, null, null);
		inquiryLightRequest.setLight(getLightDefault());
		inquiryLightRequest.setProcessFilter(processFilter);

		response = getSmartpointDAC().fetchLightHistory(inquiryLightRequest);
		assertEquals(0, response.getResultsList().size());

		// Test Validation
		processFilter.getLightTextSearch().setLightProperty(LightPropertyForSearchEnum.ACTION_ID);
		processFilter.getLightTextSearch().setLightProperty(LightPropertyForSearchEnum.EVENT_ID);
		processFilter.setUserIds(null);

		inquiryLightRequest =
				createInquiryLightRequest(null, 0, ARBITRARY_PAGE_SIZE_15, null, null, null, null);
		inquiryLightRequest.setLight(getLightDefault());
		inquiryLightRequest.setProcessFilter(processFilter);

		response = getSmartpointDAC().fetchLightHistory(inquiryLightRequest);
		assertEquals(0, response.getResultsList().size());

		// Test Validation
		processFilter.setLightTextSearch(null);

		inquiryLightRequest =
				createInquiryLightRequest(null, 0, ARBITRARY_PAGE_SIZE_15, null, null, null, null);
		inquiryLightRequest.setLight(getLightDefault());
		inquiryLightRequest.setProcessFilter(processFilter);

		response = getSmartpointDAC().fetchLightHistory(inquiryLightRequest);
		assertEquals(0, response.getResultsList().size());

		// Test Validation
		SortExpression sortExpression = new SortExpression();
		sortExpression.setField("process_id");

		processFilter.setActionCategoryList(null);

		inquiryLightRequest =
				createInquiryLightRequest(null, 0, ARBITRARY_PAGE_SIZE_15, null, null, null, null);
		inquiryLightRequest.setLight(getLightDefault());
		inquiryLightRequest.setProcessFilter(processFilter);
		inquiryLightRequest.addSortExpressions(sortExpression);

		response = getSmartpointDAC().fetchLightHistory(inquiryLightRequest);
		assertResultResponse(response);

	}

	/**
	 * Test fetch all column filters.
	 */
	@Test
	public void testFetchAllColumnFilters()
	{
		User user = insertUser();
		ColumnFilterRequest columnFilterRequest = createColumnFilterRequest(user);
		InternalResultsResponse<ColumnFilterResponse> response =
				getSmartpointDAC().fetchAllColumnFilters(columnFilterRequest);

		assertResultResponse(response);
	}

	/**
	 * Test fetch current light messages by tenant.
	 */
	@Test
	public void testFetchCurrentLightMessagesByTenant()
	{
		Tenant tenant = TestBaseUtil.createTenant();
		tenant.setMinSmartpointCommTime(1);

		TenantRequest tenantRequest = TestBaseUtil.createTenantRequest();
		tenantRequest.setTenant(tenant);

		InternalResultsResponse<Light> response =
				getSmartpointDAC().fetchCurrentLightMessagesByTenant(tenantRequest);

		assertResponse(response);
	}

	/**
	 * Test update current status message.
	 */
	@Test
	public void testUpdateCurrentStatusMessage()
	{
		LightRequest request = createLightRequest();
		request.addLight(getLightDefault());
		StatusMessage message = insertStatusMessage(getLightDefault());
		request.getFirstLight().setCurrentStatusMessage(message);

		InternalResponse response = getSmartpointDAC().updateCurrentStatusMessage(request);
		assertResponse(response);
	}

	/**
	 * Test fetch count smartpoints to map.
	 */
	@Test
	public void testFetchCountSmartpointsToMap()
	{
		InquiryLightRequest inquiryLightRequest = getLatLonToInquiryLightRequest();
		inquiryLightRequest.setSelectionPaginationIds(Arrays.asList(getLightDefault().getId()));

		InternalResultsResponse<Integer> response =
				getSmartpointDAC().fetchCountSmartpointsToMap(inquiryLightRequest);

		assertEquals(RESULT_AMOUNT_SHOULD_BE_EQUALS_SELECTED, new Integer(1), response.getFirstResult());
		assertResultResponse(response);
	}

	/**
	 * Test fetch smartpoints to map.
	 */
	@Test
	public void testFetchSmartpointsToMap()
	{
		InquiryLightRequest inquiryLightRequest = getLatLonToInquiryLightRequest();
		inquiryLightRequest.setTenant(TestBaseUtil.createTenant());
		inquiryLightRequest.setGeoCodeTrunc(1);
		inquiryLightRequest.setBottomLeftLat(BOTTOM_LEFT_MAP);
		inquiryLightRequest.setTopRightLat(TOP_RIGHT_MAP);
		inquiryLightRequest.setBottomLeftLon(BOTTOM_LEFT_MAP);
		inquiryLightRequest.setTopRightLon(TOP_RIGHT_MAP);

		InternalResultsResponse<GeocodeSmartpointInfo> response =
				getSmartpointDAC().fetchSmartpointsToMap(inquiryLightRequest);
		assertResultResponse(response);

		List<Integer> lightIds = new ArrayList<Integer>();
		lightIds.add(getLightDefault().getId());
		inquiryLightRequest.setSelectionPaginationIds(lightIds);

		response = getSmartpointDAC().fetchSmartpointsToMap(inquiryLightRequest);
		assertResultResponse(response);
	}

	/**
	 * Test fetch limited smartpoints to map.
	 */
	@Test
	public void testFetchLimitedSmartpointsToMap()
	{
		InquiryLightRequest inquiryLightRequest = getLatLonToInquiryLightRequest();
		List<Integer> lightIds = new ArrayList<Integer>();
		lightIds.add(getLightDefault().getId());
		inquiryLightRequest.setSelectionPaginationIds(lightIds);
		inquiryLightRequest.setMaxSmartpointCount(ARBITRARY_MAX_SMARTPOINT_COUNT_50);

		InternalResultsResponse<Light> response =
				getSmartpointDAC().fetchLimitedSmartpointsToMap(inquiryLightRequest);

		for (Light light : response.getResultsList())
		{
			System.out.println(light.getId());
		}

		assertResultResponse(response);
	}

	/**
	 * Test fetch middle map.
	 */
	@Test
	public void testFetchMiddleMap()
	{
		insertLight();

		InquiryLightRequest inquiryLightRequest = getLatLonToInquiryLightRequest();
		inquiryLightRequest.setMaxSmartpointCount(ARBITRARY_MAX_SMARTPOINT_COUNT_50);

		InternalResultsResponse<SmartpointMiddleMap> response =
				getSmartpointDAC().fetchMiddleMap(inquiryLightRequest);
		assertResultResponse(response);
	}

	/**
	 * Test fetch middle smartpoint to map.
	 */
	@Test
	public void testFetchMiddleSmartpointToMap()
	{
		InquiryLightRequest inquiryLightRequest =
				TestBaseUtil.createInquiryLightRequest(TestBaseUtil.createUserContext());

		SmartpointMiddleMap smartpointMiddleMap = new SmartpointMiddleMap();
		smartpointMiddleMap.setMiddleLatMax(ARBITRARY_MIDDLE_LAT_MAX);
		smartpointMiddleMap.setMiddleLonMax(ARBITRARY_MIDDLE_LON_MAX);
		smartpointMiddleMap.setMiddleLatMin(ARBITRARY_MIDDLE_LAT_MIN);
		smartpointMiddleMap.setMiddleLonMin(ARBITRARY_MIDDLE_LON_MIN);

		inquiryLightRequest.setMaxSmartpointCount(ARBITRARY_MAX_SMARTPOINT_COUNT_50);
		inquiryLightRequest.setSmartpointMiddleMap(smartpointMiddleMap);

		InternalResultsResponse<Light> response =
				getSmartpointDAC().fetchMiddleSmartpointsToMap(inquiryLightRequest);
		assertResponse(response);
	}

	/**
	 * Test delete current alarm warning message by light id.
	 */
	@Test
	public void testDeleteCurrentAlarmWarningMessageByLightID()
	{
		getSmartpointDAC().deleteCurrentAlarmWarningMessageByLightID(insertLight().getId());
	}

	/**
	 * Test insert current alarm status message.
	 */
	@Test
	public void testInsertCurrentAlarmStatusMessage()
	{
		StatusMessage statusMessage = insertStatusMessage(getLightDefault());

		CurrentAlarmWarningMessage currentAlarmWarningMessage = TestBaseUtil.createCurrentAlarmWarningMessage();
		currentAlarmWarningMessage.setStatusMessageId(statusMessage.getId());
		currentAlarmWarningMessage.setLightId(getLightDefault().getId());

		getSmartpointDAC().insertCurrentAlarmStatusMessage(currentAlarmWarningMessage);
	}

	/**
	 * Test fetch current alarm status messages by light.
	 */
	@Test
	public void testFetchCurrentAlarmStatusMessagesByLight()
	{
		List<CurrentAlarmWarningMessage> currentAlarmWarningMessage =
				getSmartpointDAC().fetchCurrentAlarmStatusMessagesByLight(getLightDefault().getId());
		assertNotNull(currentAlarmWarningMessage);
	}

	/**
	 * Test insert communication failure warnings.
	 */
	@Test
	public void testInsertCommunicationFailureWarnings()
	{
		LightRequest request = TestBaseUtil.createLightRequest();
		request.setLights(Arrays.asList(getLightDefault()));

		getSmartpointDAC().insertCommunicationFailureWarnings(request);
	}

	/**
	 * Gets the lat lon to inquiry light request.
	 *
	 * @return the lat lon to inquiry light request
	 */
	private InquiryLightRequest getLatLonToInquiryLightRequest()
	{
		InquiryLightRequest request = new InquiryLightRequest(createUserContext());
		request.setBottomLeftLat(ARBITRARY_BOTTOM_LEFT_LAT);
		request.setBottomLeftLon(ARBITRARY_BOTTOM_LEFT_LON);
		request.setTopRightLat(ARBITRARY_TOP_RIGHT_MIN);
		request.setTopRightLon(ARBITRARY_TOP_RIGHT_LON);

		return request;
	}

	/**
	 * Fetch all lights.
	 *
	 * @param selectionPaginationIds the selection pagination ids
	 * @param params the params
	 * @param isProperty the is property
	 * @param paginationAllSelected the pagination all selected
	 * @param pageSize the page size
	 * @param statusList the status list
	 * @param propertyValues the property values
	 * @return the internal results response
	 */
	private InternalResultsResponse<Light> fetchAllLights(
			List<Integer> selectionPaginationIds,
			List<SearchParameter> params,
			Boolean isProperty,
			Boolean paginationAllSelected,
			Integer pageSize,
			List<LightStatusEnum> statusList,
			HashMap<String,
			List<Integer>> propertyValues)
			{
		SearchLight searchLight = new SearchLight();
		searchLight.setStatusList(statusList);
		searchLight.setPropertyValues(propertyValues);
		searchLight.setSearchParameters(params);

		InquiryLightRequest inquiryLightRequest =
				createInquiryLightRequest(searchLight,
						0,
						pageSize
						,
						selectionPaginationIds,
						paginationAllSelected,
						LightOrderByEnum.STATUS.getValue(),
						isProperty);

		InternalResultsResponse<Light> response = getSmartpointDAC().fetchAllLights(inquiryLightRequest);

		assertResponse(response);
		return response;
			}

	/**
	 * Assert sensus part number configuration.
	 *
	 * @param configuration the configuration
	 */
	private void assertSensusPartNumberConfiguration(SensusPartNumberConfiguration configuration)
	{
		assertNotNull("Sensus part number configuration should be not null", configuration);
		assertNotNull("Intensity level should be not null", configuration.getIntensityLevel());
		assertNotNull("Hardware settings should be not null", configuration.getHardwareSetting());
		assertNotNull("Current scale should be not null", configuration.getCurrentScale());
		assertNotNull("Full on required should be not null", configuration.getFullOnRequired());
		assertNotNull("Dim on delay should be not null", configuration.getDimOnDelay());
	}

	/**
	 * Creates the lighting configuration request.
	 *
	 * @return the lighting configuration request
	 */
	private LightingConfigurationRequest createLightingConfigurationRequest()
	{
		return new LightingConfigurationRequest(createUserContext());
	}

	/**
	 * Creates the light status list.
	 *
	 * @param lightStatus the light status
	 * @return the list
	 */
	private List<LightStatusEnum> createLightStatusList(LightStatusEnum... lightStatus)
	{
		List<LightStatusEnum> statusList = new ArrayList<LightStatusEnum>();
		for (int i = 0; i < lightStatus.length; i++)
		{
			statusList.add(lightStatus[i]);
		}
		return statusList;
	}

	/**
	 * Creates the column filter request.
	 *
	 * @return the column filter request
	 */
	private ColumnFilterRequest createColumnFilterRequest()
	{
		return new ColumnFilterRequest(createUserContext());
	}

	/**
	 * Creates the column filter request.
	 *
	 * @param user the user
	 * @return the column filter request
	 */
	private ColumnFilterRequest createColumnFilterRequest(User user)
	{
		ColumnFilterRequest request = this.createColumnFilterRequest();
		request.getUserContext().setId(user.getId());
		request.getUserContext().setUserId(user.getUserName());
		return request;
	}

	/**
	 * Creates the property valid values request.
	 *
	 * @return the property valid values request
	 */
	private PropertyValidValuesRequest createPropertyValidValuesRequest()
	{
		return new PropertyValidValuesRequest(createUserContext());
	}

	/**
	 * Insert operational data.
	 *
	 * @param message the message
	 * @param operationalData the operational data
	 */
	private void insertOperationalData(StatusMessage message, OperationalData... operationalData)
	{
		InternalResponse response =
				getSmartpointDAC().insertOperationalData(createUserContext().getUserId(), message.getId(),
						Arrays.asList(operationalData));

		assertResponse(response);
	}

	/**
	 * Insert custom searches.
	 *
	 * @param amount the amount
	 * @param userContext the user context
	 * @return the list
	 */
	private List<CustomSearch> insertCustomSearches(int amount, UserContext userContext)
	{
		if (amount == 0)
		{
			return null;
		}

		List<CustomSearch> customSearches = new ArrayList<CustomSearch>();
		for (int i = 0; i < amount; i++)
		{
			customSearches.add(this.insertCustomSearch(userContext));
		}
		return customSearches;
	}

	/**
	 * Insert custom search.
	 *
	 * @param userContext the user context
	 * @return the custom search
	 */
	private CustomSearch insertCustomSearch(UserContext userContext)
	{
		return this.insertCustomSearch(null, userContext);
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
		CustomSearchRequest request = this.createCustomSearchRequest(parameters);
		request.setUserContext(userContext);
		InternalResultsResponse<CustomSearch> response = getSmartpointDAC().insertCustomSearch(request);
		assertResultResponse(response);
		CustomSearch customSearch = response.getFirstResult();
		assertCustomSearch(customSearch);
		return customSearch;
	}

	/**
	 * Creates the custom search request.
	 *
	 * @return the custom search request
	 */
	private CustomSearchRequest createCustomSearchRequest()
	{
		return this.createCustomSearchRequest(null);
	}

	/**
	 * Creates the custom search request.
	 *
	 * @param parameters the parameters
	 * @return the custom search request
	 */
	private CustomSearchRequest createCustomSearchRequest(List<SearchParameter> parameters)
	{
		CustomSearchRequest request = new CustomSearchRequest(createUserContext());
		request.setCustomSearch(this.createCustomSearch(parameters));
		return request;
	}

	/**
	 * Creates the custom search.
	 *
	 * @return the custom search
	 */
	private CustomSearch createCustomSearch()
	{
		return this.createCustomSearch(null);
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

	/**
	 * Assert custom search.
	 *
	 * @param customSearch the custom search
	 */
	private void assertCustomSearch(CustomSearch customSearch)
	{
		assertNotNull("Custom search should be not null", customSearch);
		assertNotNull("Custom search identifier should be not null", customSearch.getId());
	}

	/**
	 * Creates the light request state.
	 *
	 * @param lightStateEnum the light state enum
	 * @param lightBlinkEnum the light blink enum
	 * @param lightIntensityEnum the light intensity enum
	 * @param overrideEnum the override enum
	 * @param overridePerDate the override per date
	 * @return the light request
	 */
	private LightRequest createLightRequestState(LightStateEnum lightStateEnum, LightBlinkEnum lightBlinkEnum,
			LightIntensityEnum lightIntensityEnum, OverrideEnum overrideEnum, Date overridePerDate)
	{
		LightRequest lightRequest = createLightRequest();
		getLightDefault().setLightStateEnum(lightStateEnum);
		getLightDefault().setLightBlinkEnum(lightBlinkEnum);
		getLightDefault().setLightIntensityEnum(lightIntensityEnum);
		getLightDefault().setRniId(getLightDefault().getRniId());
		getLightDefault().setOverrideEnum(overrideEnum);
		getLightDefault().setOverridePerDate(overridePerDate);
		getLightDefault().setOverrideCreateDate(getNewDateUTC());
		lightRequest.addLight(getLightDefault());
		return lightRequest;
	}

}
