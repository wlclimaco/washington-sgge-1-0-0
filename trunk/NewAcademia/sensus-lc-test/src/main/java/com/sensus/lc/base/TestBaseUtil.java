package com.sensus.lc.base;

import static com.sensus.lc.base.util.LCDateUtil.getNewDateUTC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import au.com.bytecode.opencsv.CSVReader;

import com.sensus.cbof.model.Location;
import com.sensus.cbof.model.Radio;
import com.sensus.cbof.model.TimeZoneInfo;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.CSVDataSource;
import com.sensus.common.util.SensusDateUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.analytics.model.request.AnalyticsRequest;
import com.sensus.lc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.lc.base.model.MLCSortExpression;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.base.model.request.LightSelectionRequest;
import com.sensus.lc.base.model.request.LightingControlRequest;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.base.util.LCHelp;
import com.sensus.lc.ecomode.model.EcoModeBaseline;
import com.sensus.lc.ecomode.model.EcoModeOrderByEnum;
import com.sensus.lc.ecomode.model.LightEcoModeTypeEnum;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.group.model.request.GroupRequest;
import com.sensus.lc.light.model.AlertClassification;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.BlinkStatusEnum;
import com.sensus.lc.light.model.Configuration;
import com.sensus.lc.light.model.Consumption;
import com.sensus.lc.light.model.IntensityEnum;
import com.sensus.lc.light.model.LastOperationalData;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.LightDetailDataTypeEnum;
import com.sensus.lc.light.model.LightHistory;
import com.sensus.lc.light.model.LightOrderByEnum;
import com.sensus.lc.light.model.LightPropertyForSearchEnum;
import com.sensus.lc.light.model.LightSchedule;
import com.sensus.lc.light.model.LightStateEnum;
import com.sensus.lc.light.model.LightTextSearch;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.NotificationTypeEnum;
import com.sensus.lc.light.model.OperationalData;
import com.sensus.lc.light.model.OperationalDataTypeEnum;
import com.sensus.lc.light.model.PrecedenceEnum;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.SearchParameter;
import com.sensus.lc.light.model.criteria.AddressCriteria;
import com.sensus.lc.light.model.criteria.AlertCriteria;
import com.sensus.lc.light.model.criteria.ConfigurationCriteria;
import com.sensus.lc.light.model.criteria.GroupCriteria;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.criteria.OperationalDataCriteria;
import com.sensus.lc.light.model.criteria.ProcessCriteria;
import com.sensus.lc.light.model.criteria.ScheduleCriteria;
import com.sensus.lc.light.model.criteria.TagCriteria;
import com.sensus.lc.light.model.request.AlarmNotificationRequest;
import com.sensus.lc.light.model.request.AlertClassificationMaintenanceRequest;
import com.sensus.lc.light.model.request.ChannelSetupAuditRequest;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.request.CommunicationFailureRequest;
import com.sensus.lc.light.model.request.ConfigurationMaintenanceRequest;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.GuaranteeLightExistenceRequest;
import com.sensus.lc.light.model.request.LastOperationalDataMaintenanceRequest;
import com.sensus.lc.light.model.request.LightFilterRequest;
import com.sensus.lc.light.model.request.LightIntensityRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightMassUpdateRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.LightSetupNotificationRequest;
import com.sensus.lc.light.model.request.LightStatusNotificationRequest;
import com.sensus.lc.light.model.request.LocationMaintenanceRequest;
import com.sensus.lc.light.model.request.NotificationHistoryMaintenanceRequest;
import com.sensus.lc.light.model.request.NotificationHistoryRequest;
import com.sensus.lc.light.model.request.OperationalDataMaintenanceRequest;
import com.sensus.lc.light.model.request.ProcessLightsRequest;
import com.sensus.lc.light.model.request.ScheduleMaintenanceRequest;
import com.sensus.lc.light.model.request.UpdateLightNotificationRequest;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.LCActionParameter;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessFilter;
import com.sensus.lc.process.model.ProcessItem;
import com.sensus.lc.process.model.ProcessItemStatusEnum;
import com.sensus.lc.process.model.ProcessStatusReasonEnum;
import com.sensus.lc.process.model.request.InquiryProcessRequest;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.schedule.model.DaysOfWeekEnum;
import com.sensus.lc.schedule.model.Event;
import com.sensus.lc.schedule.model.EventSchedule;
import com.sensus.lc.schedule.model.OffsetSchedule;
import com.sensus.lc.schedule.model.ScheduleTypeEnum;
import com.sensus.lc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.settings.model.Setting;
import com.sensus.lc.settings.model.request.SettingsRequest;
import com.sensus.lc.tag.model.Tag;
import com.sensus.lc.tag.model.request.InquiryTagRequest;
import com.sensus.lc.tag.model.request.TagRequest;
import com.sensus.lc.tenant.model.ApiControl;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.tenant.model.request.TenantRequest;
import com.sensus.lc.user.model.User;
import com.sensus.lc.user.model.request.InquiryUserRequest;
import com.sensus.lc.user.model.request.UserRequest;

/**
 * The Class TestBaseUtil.
 */
public final class TestBaseUtil
{

	/** The Constant TIMEZONE. */
	private static final String TIMEZONE = "America/New_York";

	/** The Constant SORT. */
	private static final String SORT = LightOrderByEnum.ALERTS.getValue();

	/** The Constant ARBITRARY_LATITUDE. */
	private static final Double ARBITRARY_LATITUDE = 35.8708746778972;

	/** The Constant ARBITRARY_LONGITUDE. */
	private static final Double ARBITRARY_LONGITUDE = -78.8517758669835;

	/** The Constant UBERABA_BLACKOUT. */
	private static final String UBERABA_BLACKOUT = "Uberaba Blackout";

	/** The Constant ONE. */
	private static final String ONE = "1";

	/** The Constant RANDOM. */
	public static final Random RANDOM = new Random();

	/** The Constant NUMBER_RANGE. */
	public static final Integer NUMBER_RANGE = Integer.MAX_VALUE;

	/** The CREAT e_ user. */
	public static final String CREATE_USER = "QAT";

	/** The Constant FILE_NAME. */
	public static final String FILE_NAME = System.getProperty("java.io.tmpdir") + "csvtmp"
			+ RANDOM.nextInt(NUMBER_RANGE) + ".csv";

	/** The Constant ARBITRARY_INTENSITY_10. */
	private static final Integer ARBITRARY_INTENSITY_10 = 10;

	/** The Constant ARBITRARY_SUNRISE_OFF_SET_MINUTES_20. */
	private static final Integer ARBITRARY_SUNRISE_OFF_SET_MINUTES_20 = 20;

	/** The Constant ARBITRARY_SUNSET_OFF_SET_MINUTES_15. */
	private static final Integer ARBITRARY_SUNSET_OFF_SET_MINUTES_15 = 15;

	/** The Constant ARBITRARY_NUMBER_150. */
	private static final Integer ARBITRARY_NUMBER_150 = 150;

	/** The Constant ARBITRARY_NUMBER_10. */
	private static final Integer ARBITRARY_NUMBER_10 = 10;

	/** The Constant ARBITRARY_NUMBER_200. */
	private static final Integer ARBITRARY_NUMBER_200 = 200;

	/** The Constant ARBITRARY_NUMBER_100. */
	private static final Integer ARBITRARY_NUMBER_100 = 100;

	/** The Constant ARBITRARY_PAGE_SIZE_15. */
	private static final Integer ARBITRARY_PAGE_SIZE_15 = 15;

	/** The Constant ARBITRARY_NUMBER_3. */
	private static final Integer ARBITRARY_NUMBER_3 = 3;

	/** The Constant ARBITRARY_NUMBER_20. */
	private static final Integer ARBITRARY_NUMBER_20 = 20;

	/** The Constant ARBITRARY_NUMBER_N5. */
	private static final Integer ARBITRARY_NUMBER_N5 = -5;

	/** The Constant ARBITRARY_NUMBER_N5. */
	private static final Integer ARBITRARY_NUMBER_5 = 5;

	/** The Constant ARBITRARY_NUMBER_N10. */
	private static final Integer ARBITRARY_NUMBER_N10 = -10;

	/** The Constant ONE_THOUSAND. */
	private static final Float ONE_THOUSAND = 1000.00F;

	/**
	 * Instantiates a new test base util.
	 */
	private TestBaseUtil()
	{
	}

	/**
	 * Validate assert response.
	 *
	 * @param response the response
	 */
	public static void assertResultResponse(InternalResultsResponse<?> response)
	{
		assertResponse(response);
		assertEquals("Status should to be success", Status.OperationSuccess, response.getStatus());
		assertTrue("Result list should not to be empty", response.hasResults());
	}

	/**
	 * Validate assert group.
	 *
	 * @param response the response
	 */
	public static void assertResponse(InternalResponse response)
	{
		assertNotNull("Response object should not to be null", response);
		assertNotNull("Message list object should not to be null", response.getMessageInfoList());

		List<MessageInfo> messagesInfo = response.getMessageInfoList();
		for (MessageInfo messageInfo : messagesInfo)
		{
			if (messageInfo.getSeverity() != MessageSeverity.Info)
			{
				fail("Message severity should not to be: " + messageInfo.getSeverity());
			}
		}
	}

	/**
	 * Creates the tenant.
	 *
	 * @return the tenant
	 */
	public static Tenant createTenant()
	{
		Tenant tenant = new Tenant(1, "acme", "ACME Corporation LC Demo", "ACME",
				"http://localhost:8083/gateway-mlc/mlc-gateway-ws/");
		tenant.setMinSmartpointCommTime(ARBITRARY_NUMBER_3);
		tenant.setLightTimeZone(TIMEZONE);
		tenant.setNumberApiAccessHour(ARBITRARY_NUMBER_5);

		return tenant;
	}

	/**
	 * Creates the api control.
	 *
	 * @return the api control
	 */
	public static ApiControl createApiControl()
	{
		ApiControl apiControl = new ApiControl();
		apiControl.setCount(6);
		apiControl.setTenantId(1);

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(LCDateUtil.getNewDateUTC());

		apiControl.setFirstDate(calendar.getTime());

		return apiControl;
	}

	/**
	 * Creates the user context.
	 *
	 * @return the user context
	 */
	public static UserContext createUserContext()
	{
		User user = createUser();
		UserContext userContext = new UserContext();
		userContext.setId(user.getId());
		userContext.setUserId(user.getUserName());
		userContext.setTenant(createTenant());
		return userContext;
	}

	/**
	 * Insert user.
	 *
	 * @return the user
	 */
	public static User createUser()
	{
		User user = new User();
		user.setUserName("junit" + RANDOM.nextInt(NUMBER_RANGE));
		user.setCreateDate(LCDateUtil.getNewDateUTC());
		user.setCreateUser(CREATE_USER);
		user.setEmail("junit@test.com");
		user.setFirstName("JUnit");
		user.setLastName("Test");
		user.setPassword("QAT123");
		user.setRole("Admin");
		user.setAllLightsAuth(false);
		user.setEnabled(true);
		return user;
	}

	/**
	 * Creates the light.
	 *
	 * @return the light
	 */
	public static Light createLight()
	{
		Integer id = RANDOM.nextInt(NUMBER_RANGE);
		Integer idRadio = RANDOM.nextInt(NUMBER_RANGE);

		Light light = new Light();
		light.setId(id);
		light.setTenant(createTenant());
		light.setRadio(createRadio(id));
		light.setPoleId(String.valueOf(RANDOM.nextInt(NUMBER_RANGE)));
		light.setLightType(com.sensus.lc.light.model.LightTypeEnum.INDUCTION);
		light.setDeviceLifeCycleState(LightStateEnum.UNKNOWN);
		light.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		light.setIntensity(IntensityEnum.LEVEL_0);
		light.setEcoMode(getEcoModeRandom());
		light.setLastConsumption(createConsumption(0));
		light.setBlinkStatus(BlinkStatusEnum.NONE);

		Radio radio = createRadio(idRadio);
		radio.setLocation(createLocation());
		light.setRadio(radio);

		return light;
	}

	/**
	 * Creates the light.
	 *
	 * @param userContext the user context
	 * @return the com.sensus.lc.light.model. light
	 */
	public static Light createLight(UserContext userContext)
	{
		Light light = createLight();
		light.setCreateUser(userContext.getUserId());
		light.setTenant(userContext.<Tenant> getTenant());
		light.setConfiguration(createConfiguration(userContext));
		light.setLastOperationalData(createLastOperationalData(userContext));
		light.setLightSchedule(createLight2Schedule(userContext));
		return light;
	}

	/**
	 * Creates the notification history.
	 *
	 * @param userContext the user context
	 * @param light the light
	 * @return the notification history
	 */
	public static NotificationHistory createNotificationHistory(UserContext userContext, Light light)
	{
		Date date = SensusDateUtil.getMinDate();
		NotificationHistory notificationHistory = new NotificationHistory();
		notificationHistory.setId(RANDOM.nextInt(NUMBER_RANGE));
		notificationHistory.setCreateDate(date);
		notificationHistory.setMessageDate(date);
		notificationHistory.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		notificationHistory.setNotificationType(NotificationTypeEnum.ALARM);
		notificationHistory.setTrasactionId(String.valueOf(RANDOM.nextInt(NUMBER_RANGE)));
		notificationHistory.setUpdateDate(date);
		notificationHistory.setCreateUser(userContext.getUserId());
		notificationHistory.setLightId(light.getId());
		notificationHistory.setPrecedence(PrecedenceEnum.ACTIVE);
		light.setLastNotificationHistory(notificationHistory);
		return notificationHistory;
	}

	/**
	 * Creates the alert classification.
	 *
	 * @param userContext the user context
	 * @param notificationHistoryId the notification history id
	 * @param alertSubType the alert sub type
	 * @return the alert classification
	 */
	public static AlertClassification createAlertClassification(UserContext userContext, Integer notificationHistoryId,
			AlertSubTypeEnum alertSubType)
	{
		Date date = new Date();
		AlertClassification alertClassification = new AlertClassification();
		alertClassification.setNotificationHistoryId(notificationHistoryId);
		alertClassification.setAlertType(alertSubType.getAlertType());
		alertClassification.setAlertSubType(alertSubType);
		alertClassification.setMessageDate(date);
		alertClassification.setCreateUser(userContext.getUserId());
		alertClassification.setCreateDate(date);
		alertClassification.setUpdateDate(date);

		return alertClassification;
	}

	/**
	 * Creates the operational data.
	 *
	 * @param notification the notification
	 * @return the operational data
	 */
	public static OperationalData createOperationalData(NotificationHistory notification)
	{
		OperationalData operational = new OperationalData();
		operational.setCreateDate(new Date());
		operational.setValue(new Float(RANDOM.nextInt(NUMBER_RANGE)));
		operational.setOperationalDataType(OperationalDataTypeEnum.CUMULATIVECONSUMPTION);
		operational.setNotificationHistoryId(notification.getId());
		return operational;
	}

	/**
	 * Creates the radio.
	 *
	 * @return the radio
	 */
	public static Radio createRadio()
	{
		return createRadio(RANDOM.nextInt(NUMBER_RANGE));
	}

	/**
	 * Creates the radio.
	 *
	 * @param id the id
	 * @return the radio
	 */
	public static Radio createRadio(Integer id)
	{
		Radio radio = new Radio(BigInteger.valueOf(id));
		radio.setFlexNetId(BigInteger.valueOf(id));
		radio.setLocation(createLocation());
		return radio;
	}

	/**
	 * Creates the last operational data.
	 *
	 * @param userContext the user context
	 * @return the last operational data
	 */
	public static LastOperationalData createLastOperationalData(UserContext userContext)
	{
		LastOperationalData lastOperationalData = new LastOperationalData();
		lastOperationalData.setCreateUser(userContext.getUserId());
		lastOperationalData.setCreateDate(getNewDateUTC());
		lastOperationalData.setModifyDate(getNewDateUTC());
		lastOperationalData.setConsumption(ONE_THOUSAND);
		lastOperationalData.setConsumptionMin(ONE_THOUSAND);
		lastOperationalData.setConsumptionMax(ONE_THOUSAND);

		return lastOperationalData;
	}

	/**
	 * Creates the light schedule.
	 *
	 * @return the light schedule
	 */
	public static LightSchedule createLightSchedule()
	{
		LightSchedule schedule = new LightSchedule();
		schedule.setSunriseTime("06:25");
		schedule.setSunsetTime("20:25");
		schedule.setSunriseOffset(ARBITRARY_NUMBER_N10);
		schedule.setSunsetOffset(ARBITRARY_NUMBER_N5);

		return schedule;
	}

	/**
	 * Creates the light schedule.
	 *
	 * @param userContext the user context
	 * @return the light schedule
	 */
	public static LightSchedule createLight2Schedule(UserContext userContext)
	{
		LightSchedule schedule = createLightSchedule();
		schedule.setCreateUser(userContext.getUserId());
		return schedule;
	}

	/**
	 * Creates the location.
	 *
	 * @return the location
	 */
	public static Location createLocation()
	{
		Location location = new Location();
		location.setLatitude(ARBITRARY_LATITUDE);
		location.setLongitude(ARBITRARY_LONGITUDE);
		location.setTimeZoneInfo(new TimeZoneInfo(TIMEZONE));
		location.setAddress("SE Stark St.");
		location.setCity("Morrisville");
		location.setState("Oregon");
		location.setCountry("USA");
		location.setZip("553447");

		return location;
	}

	/**
	 * Creates the configuration.
	 *
	 * @param userContext the user context
	 * @return the configuration
	 */
	public static Configuration createConfiguration(UserContext userContext)
	{
		Configuration configuration = new Configuration();
		configuration.setCreateUser(userContext.getUserId());
		configuration.setHousing("High Bay");
		configuration.setHousingColor("Gray");
		configuration.setDimmable(true);
		configuration.setLampTypeWattageDimmable("120W Induction");
		configuration.setWattageRating("120W");
		configuration.setInputVoltageRange("90-300V");
		configuration.setColorTemperature("3,000K");
		configuration.setManufacturer("CREE");
		configuration.setFirmwareVersion("1.0.0");
		configuration.setModelNumber("539449037C2321");
		configuration.setLowerAssemblySerial("Lower 2");
		configuration.setUpperAssemblySerial("Upper 2");
		configuration.setBulbSerialNumber("Bulb 2");
		configuration.setBallastSerialNumber(String.valueOf(new Random().nextInt(NUMBER_RANGE)));

		return configuration;
	}

	/**
	 * Creates the eco mode baseline.
	 *
	 * @param light the light
	 * @return the eco mode baseline
	 */
	public static void createEcoModeBaselineToLight(Light light)
	{
		light.setEcoModeBaseline(new EcoModeBaseline(getLightTypeRandom(), getWattageRandom(), false));
	}

	/**
	 * Creates the eco mode baseline.
	 *
	 * @return the eco mode baseline
	 */
	public static Light createLightWithEcoModeBaseline()
	{
		Light light = createLight();
		createEcoModeBaselineToLight(light);
		return light;
	}

	/**
	 * Creates the light consumption.
	 *
	 * @param daysAmountAgo the days amount ago
	 * @return the light consumption
	 */
	public static Consumption createLightConsumption(int daysAmountAgo)
	{
		Date dateUTC = LCDateUtil.getNewDateUTC();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateUTC);
		calendar.set(Calendar.DATE, -daysAmountAgo);
		Date dateNew = calendar.getTime();

		Consumption lightConsumption = new Consumption(
				dateNew,
				getEcoModeRandom(),
				getConsumptionRandom(),
				getConsumptionRandom());

		return lightConsumption;
	}

	/**
	 * Creates the consumption.
	 *
	 * @param daysAmountAgo the days amount ago
	 * @return the consumption
	 */
	public static Consumption createConsumption(int daysAmountAgo)
	{
		Date dateUTC = LCDateUtil.getNewDateUTC();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateUTC);
		calendar.set(Calendar.DATE, -daysAmountAgo);
		Date dateNew = calendar.getTime();

		Consumption consumption = new Consumption(
				dateNew,
				getEcoModeRandom(),
				getConsumptionRandom(),
				getConsumptionRandom());

		return consumption;
	}

	/**
	 * Creates the lc action parameter.
	 *
	 * @return the lC action parameter
	 */
	public static LCActionParameter createLCActionParameter()
	{
		LCActionParameter lcActionParameter = new LCActionParameter();
		lcActionParameter.setProperty(PropertyEnum.FILE_NAME);
		lcActionParameter.setActionValue("FileName"
				+ RANDOM.nextInt(NUMBER_RANGE)
				+ LCDateUtil.convertDateToString(new Date()));
		return lcActionParameter;
	}

	/**
	 * Creates the process item.
	 *
	 * @param light the light
	 * @return the list
	 */
	public static List<ProcessItem> createProcessItem(Light light)
	{
		ProcessItem processItem = new ProcessItem();
		processItem.setLight(light);
		processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.SUCCESS);

		return Arrays.asList(processItem);
	}

	/**
	 * Creates the days of week.
	 *
	 * @return the list
	 */
	public static List<DaysOfWeekEnum> createDaysOfWeek()
	{
		List<DaysOfWeekEnum> daysOfWeek = new ArrayList<DaysOfWeekEnum>();
		daysOfWeek.add(DaysOfWeekEnum.SUNDAY);
		daysOfWeek.add(DaysOfWeekEnum.FRIDAY);
		daysOfWeek.add(DaysOfWeekEnum.SATURDAY);
		return daysOfWeek;
	}

	/**
	 * Creates the event.
	 *
	 * @param daysOfWeek the days of week
	 * @param intensity the intensity
	 * @return the event
	 */
	public static Event createEvent(List<DaysOfWeekEnum> daysOfWeek, Integer intensity)
	{
		Event event = new Event();
		event.setIntensity(intensity);
		event.setTime(getNewDateUTC());
		event.setDays(daysOfWeek);
		event.setLightBlinkEnum(BlinkStatusEnum.FAST);
		return event;
	}

	/**
	 * Creates the schedule.
	 *
	 * @return the event schedule
	 */
	public static EventSchedule createEventSchedule()
	{
		EventSchedule eventSchedule = new EventSchedule();

		eventSchedule.setId(RANDOM.nextInt(NUMBER_RANGE));
		eventSchedule.setName(UBERABA_BLACKOUT);
		eventSchedule.setDescription(UBERABA_BLACKOUT);
		eventSchedule.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);

		// Add event
		List<DaysOfWeekEnum> daysOfWeek = new ArrayList<DaysOfWeekEnum>();
		List<Event> events = new ArrayList<Event>();

		Event event = new Event();

		daysOfWeek.add(DaysOfWeekEnum.WEDNESDAY);

		event.setTime(new Date());
		event.setDays(daysOfWeek);
		event.setIntensity(ARBITRARY_INTENSITY_10);
		events.add(event);

		eventSchedule.setEvents(events);

		return eventSchedule;
	}

	/**
	 * Creates the tag.
	 *
	 * @return the tag
	 */
	public static Tag createTag()
	{
		Tag tag = new Tag();
		tag.setId(RANDOM.nextInt(NUMBER_RANGE));
		tag.setName("Tag name " + RANDOM.nextInt(NUMBER_RANGE));
		tag.setAutoGroup(false);
		return tag;
	}

	/**
	 * Creates the search parameter.
	 *
	 * @return the search parameter
	 */
	public static SearchParameter createSearchParameter()
	{
		SearchParameter param = new SearchParameter();
		// param.setPropertyEnum(PropertyEnum.SORT);
		param.setValue("Value " + RANDOM.nextInt(NUMBER_RANGE));
		return param;
	}

	/**
	 * Creates the light history.
	 *
	 * @return the light history
	 */
	public static LightHistory createLightHistory()
	{
		LightHistory lightHistory = new LightHistory();
		lightHistory.setProcessId(RANDOM.nextInt(NUMBER_RANGE));
		lightHistory.setName("Name " + RANDOM.nextInt(NUMBER_RANGE));
		lightHistory.setDescription("Description " + RANDOM.nextInt(NUMBER_RANGE));
		return lightHistory;
	}

	/**
	 * Creates the group.
	 *
	 * @return the group
	 */
	public static Group createGroup()
	{
		Group group = new Group();
		group.setId(RANDOM.nextInt(NUMBER_RANGE));
		group.setName("Group name" + RANDOM.nextInt(NUMBER_RANGE));
		group.setDescription("Group name " + RANDOM.nextInt(NUMBER_RANGE));
		return group;
	}

	/**
	 * Creates the groups.
	 *
	 * @param amount the amount
	 * @return the list
	 */
	public static List<Group> createGroups(Integer amount)
	{
		if (amount == 0)
		{
			return null;
		}
		Group group = new Group();
		List<Group> groups = new ArrayList<Group>();
		for (int i = 1; i <= amount; i++)
		{
			group.setId(i);
			groups.add(group);
			group = new Group();
		}

		return groups;
	}

	/**
	 * Creates the off set schedule.
	 *
	 * @return the offset schedule
	 */
	public static OffsetSchedule createOffSetSchedule()
	{
		OffsetSchedule offSetSchedule = new OffsetSchedule();
		offSetSchedule.setId(RANDOM.nextInt(NUMBER_RANGE));
		offSetSchedule.setName(UBERABA_BLACKOUT);
		offSetSchedule.setDescription(UBERABA_BLACKOUT);
		offSetSchedule.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
		offSetSchedule.setSunriseOffsetMinutes(ARBITRARY_SUNRISE_OFF_SET_MINUTES_20);
		offSetSchedule.setSunsetOffsetMinutes(ARBITRARY_SUNSET_OFF_SET_MINUTES_15);
		offSetSchedule.setSunriseBefore(true);
		offSetSchedule.setSunsetBefore(true);
		offSetSchedule.setIntensity(ARBITRARY_INTENSITY_10);
		return offSetSchedule;
	}

	/**
	 * Creates the setting.
	 *
	 * @param property the property
	 * @param value the value
	 * @return the setting
	 */
	public static Setting createSetting(PropertyEnum property, String value)
	{
		Setting setting = new Setting();
		setting.setPropertyEnum(property);
		setting.setPropertyValue(value);
		return setting;
	}

	/**
	 * Creates the settings.
	 *
	 * @return the list
	 */
	public static List<Setting> createSettings()
	{
		List<Setting> settings = new ArrayList<Setting>();
		settings.add(createSetting(PropertyEnum.LANGUAGE, "en_US"));
		settings.add(createSetting(PropertyEnum.TIME_ZONE, "-10"));
		settings.add(createSetting(PropertyEnum.PAGE_SIZE, "40"));
		settings.add(createSetting(PropertyEnum.SUBSCRIBE_ALARM_BOARD_FAILURE, "n"));
		settings.add(createSetting(PropertyEnum.SUBSCRIBE_ALARM_LAMP_FAILURE, "y"));
		settings.add(createSetting(PropertyEnum.SUBSCRIBE_ALARM_POWER_FAILURE, "n"));
		settings.add(createSetting(PropertyEnum.SUBSCRIBE_ALARM_METROLOGY_COM_FAILURE, "n"));
		settings.add(createSetting(PropertyEnum.SUBSCRIBE_ALARM_METROLOGY_ERROR, "n"));
		settings.add(createSetting(PropertyEnum.SUBSCRIBE_WARN_BROWNOUT_DETECTED, "y"));
		settings.add(createSetting(PropertyEnum.SUBSCRIBE_WARN_COMMN_FAIL, "y"));
		settings.add(createSetting(PropertyEnum.SUBSCRIBE_WARN_LIGHT_QUALITY, "y"));
		settings.add(createSetting(PropertyEnum.SUBSCRIBE_WARN_POWER_SURGE, "n"));
		settings.add(createSetting(PropertyEnum.SUBSCRIBE_WARN_HIGH_CURRENT, "n"));
		settings.add(createSetting(PropertyEnum.SUBSCRIBE_WARN_LOW_CURRENT, "n"));
		settings.add(createSetting(PropertyEnum.SUBSCRIBE_WARN_METROLOGY_RESET, "n"));
		settings.add(createSetting(PropertyEnum.SUBSCRIBE_WARN_METROLOGY_RESET, "n"));
		settings.add(createSetting(PropertyEnum.SUBSCRIBE_WARN_REVERSE_ENERGY, "n"));

		return settings;
	}

	/**
	 * Creates the process.
	 *
	 * @param light the light
	 * @param parentProcess the parent process
	 * @return the process
	 */
	public static Process createProcess(Light light, Process parentProcess)
	{
		return createProcess(light, parentProcess, false);
	}

	/**
	 * Creates the process.
	 *
	 * @param light the light
	 * @param parentProcess the parent process
	 * @param isFailure the is failure
	 * @return the process
	 */
	public static Process createProcess(Light light, Process parentProcess, boolean isFailure)
	{
		Process process = new Process();
		process.setDescription("process" + RANDOM.nextInt(NUMBER_RANGE));
		process.setStartTime(LCDateUtil.getNewDateUTC());
		process.setEndTime(getNewDateUTC());
		process.setIsMonitoredInstance(true);
		process.setIsSubmitted(false);
		process.setIsProcessComplete(false);
		process.setLcAction(createLcAction());
		process.setCreateUser(CREATE_USER);
		process.setIsParent(true);
		process.getLcAction().getActionParameters().get(0).setProperty(PropertyEnum.GROUP_ID);
		process.setRniCorrelationId(LCHelp.generateRniCorrelationId());

		if (!ValidationUtil.isNull(light))
		{
			process.setProcessItems(Arrays.asList(createProcessItem(light, ProcessItemStatusEnum.PENDING, isFailure)));
		}

		if (ValidationUtil.isNull(parentProcess))
		{
			process.setRniCorrelationId(ONE);
		}

		if (!ValidationUtil.isNull(parentProcess))
		{
			process.setParentProcess(parentProcess);
		}

		return process;
	}

	/**
	 * Creates the process item.
	 *
	 * @param light the light
	 * @param processItemStatus the process item status
	 * @param isFailure the is failure
	 * @return the process item
	 */
	public static ProcessItem createProcessItem(Light light, ProcessItemStatusEnum processItemStatus, boolean isFailure)
	{
		ProcessItem processItem = new ProcessItem(processItemStatus);
		processItem.setLight(light);

		if (isFailure)
		{
			processItem.setProcessStatusReasonEnum(ProcessStatusReasonEnum.RNI_SYNC_FAILURE);
		}
		return processItem;
	}

	/**
	 * Creates the lc action.
	 *
	 * @return the lC action
	 */
	public static LCAction createLcAction()
	{
		LCAction lcAction = new LCAction();
		lcAction.setActionType(LCActionTypeEnum.ADD_LIGHT_TO_SCHEDULE_EVENT);
		lcAction.setDescription("Test Description " + RANDOM.nextInt(NUMBER_RANGE));
		List<LCActionParameter> lcActionParameter = new ArrayList<LCActionParameter>();
		lcActionParameter.add(new LCActionParameter(PropertyEnum.SCHEDULE_ID, ONE));
		lcActionParameter.add(new LCActionParameter(PropertyEnum.GROUP_ID, "2"));
		lcAction.setActionParameters(lcActionParameter);
		return lcAction;
	}

	/**
	 * Creates the process filter.
	 *
	 * @param userId the user id
	 * @return the process filter
	 */
	public static ProcessFilter createProcessFilter(Integer userId)
	{
		ProcessFilter filter = new ProcessFilter();
		filter.setStartDate(SensusDateUtil.getMinDate());
		filter.setEndDate(getNewDateUTC());
		filter.setUserIds(Arrays.asList(userId));

		return filter;
	}

	/**
	 * Creates the light text search.
	 *
	 * @param lightPropertyForSearch the light property for search
	 * @param searchText the search text
	 * @return the light text search
	 */
	public static LightTextSearch createLightTextSearch(LightPropertyForSearchEnum lightPropertyForSearch,
			String searchText)
	{
		LightTextSearch lightTextSearch = new LightTextSearch();
		lightTextSearch.setLightProperty(lightPropertyForSearch);
		lightTextSearch.setSearchText(searchText);
		return lightTextSearch;
	}

	/**
	 * Creates the lighting control request.
	 *
	 * @return the lighting control request
	 */
	public static LightingControlRequest createLightingControlRequest()
	{
		return new LightingControlRequest(createUserContext());
	}

	/**
	 * Creates the light request.
	 *
	 * @return the light request
	 */
	public static LightRequest createLightRequest()
	{
		return new LightRequest(createUserContext());
	}

	/**
	 * Creates the tenant request.
	 *
	 * @return the tenant request
	 */
	public static TenantRequest createTenantRequest()
	{
		TenantRequest request = new TenantRequest(createUserContext());
		request.setTenant(createTenant());
		return request;
	}

	/**
	 * Creates the communication failure request.
	 *
	 * @return the communication failure request
	 */
	public static CommunicationFailureRequest createCommunicationFailureRequest()
	{
		return new CommunicationFailureRequest(createTenant());
	}

	/**
	 * Creates the light status notification request.
	 *
	 * @return the light status notification request
	 */
	public static LightStatusNotificationRequest createLightStatusNotificationRequest()
	{
		return new LightStatusNotificationRequest(createUserContext());
	}

	/**
	 * Creates the light status notification request.
	 *
	 * @param userContext the user context
	 * @return the com.sensus.lc.light.model.request. light status notification request
	 */
	public static com.sensus.lc.light.model.request.LightStatusNotificationRequest createLightStatusNotificationRequest(
			UserContext userContext)
	{
		com.sensus.lc.light.model.request.LightStatusNotificationRequest request =
				new com.sensus.lc.light.model.request.LightStatusNotificationRequest();
		request.setUserContext(userContext);
		request.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		request.getLightDetailDataType().add(LightDetailDataTypeEnum.CONFIGURATION);
		request.getLightDetailDataType().add(LightDetailDataTypeEnum.STATUS);
		request.setAlertSubTypes(Arrays.asList(AlertSubTypeEnum.BOARD_FAILURE, AlertSubTypeEnum.HIGH_CURRENT));
		return request;
	}

	/**
	 * Creates the alarm notification request.
	 *
	 * @param userContext the user context
	 * @return the alarm notification request
	 */
	public static AlarmNotificationRequest createAlarmNotificationRequest(UserContext userContext)
	{
		AlarmNotificationRequest request = new AlarmNotificationRequest(userContext);
		request.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		request.setAlertSubTypes(Arrays.asList(AlertSubTypeEnum.BOARD_FAILURE, AlertSubTypeEnum.HIGH_CURRENT));
		return request;
	}

	/**
	 * Creates the light setup notification request.
	 *
	 * @param userContext the user context
	 * @return the light setup notification request
	 */
	public static LightSetupNotificationRequest createLightSetupNotificationRequest(UserContext userContext)
	{
		return new LightSetupNotificationRequest(userContext);
	}

	/**
	 * Creates the light intensity request.
	 *
	 * @param userContext the user context
	 * @return the light intensity request
	 */
	public static LightIntensityRequest createLightIntensityRequest(UserContext userContext)
	{
		LightIntensityRequest request = new LightIntensityRequest(userContext);
		return request;
	}

	/**
	 * Creates the channel setup audit request.
	 *
	 * @param userContext the user context
	 * @return the channel setup audit request
	 */
	public static ChannelSetupAuditRequest createChannelSetupAuditRequest(UserContext userContext)
	{
		ChannelSetupAuditRequest request = new ChannelSetupAuditRequest(userContext);
		request.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		return request;
	}

	/**
	 * Creates the process lights request.
	 *
	 * @param userContext the user context
	 * @return the process lights request
	 */
	public static ProcessLightsRequest createProcessLightsRequest(UserContext userContext)
	{
		return new ProcessLightsRequest(userContext);
	}

	/**
	 * Creates the guarantee light existence request.
	 *
	 * @return the guarantee light existence request
	 */
	public static GuaranteeLightExistenceRequest createGuaranteeLightExistenceRequest()
	{
		GuaranteeLightExistenceRequest request = new GuaranteeLightExistenceRequest();
		request.setUserContext(createUserContext());

		return request;
	}

	/**
	 * Creates the fetch by id request.
	 *
	 * @param userContext the user context
	 * @param lightId the light id
	 * @return the fetch by id request
	 */
	public static FetchByIdRequest createFetchByIdRequest(UserContext userContext, Integer lightId)
	{
		FetchByIdRequest request = new FetchByIdRequest(lightId);
		request.setUserContext(userContext);
		return request;
	}

	/**
	 * Creates the light maintenance request.
	 *
	 * @param userContext the user context
	 * @param light the light
	 * @return the light maintenance request
	 */
	public static LightMaintenanceRequest createLightMaintenanceRequest(
			UserContext userContext,
			com.sensus.lc.light.model.Light light)
	{
		LightMaintenanceRequest request = new LightMaintenanceRequest(light);
		request.setUserContext(userContext);
		return request;
	}

	/**
	 * Creates the light maintenance request.
	 *
	 * @param userContext the user context
	 * @return the com.sensus.lc.light.model.request. light request
	 */
	public static LightRequest createLightRequest(UserContext userContext)
	{
		LightRequest request = new LightRequest();
		request.setUserContext(userContext);
		request.addSortExpressions(new SortExpression(SORT, Direction.Ascending));
		request.setLightCriteria(new LightCriteria());
		request.setGroupCriteria(new GroupCriteria());
		request.setAlertCriteria(new AlertCriteria());
		request.setAddressCriteria(new AddressCriteria());
		request.setProcessCriteria(new ProcessCriteria());
		request.setScheduleCriteria(new ScheduleCriteria());
		request.setConfigurationCriteria(new ConfigurationCriteria());
		request.setOperationalDataCriteria(new OperationalDataCriteria());
		request.setTagCriteria(new TagCriteria());
		return request;
	}

	/**
	 * Creates the update light notification request.
	 *
	 * @param userContext the user context
	 * @return the light request
	 */
	public static UpdateLightNotificationRequest createUpdateLightNotificationRequest(UserContext userContext)
	{
		UpdateLightNotificationRequest request = new UpdateLightNotificationRequest();
		request.setUserContext(userContext);
		request.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		return request;
	}

	/**
	 * Creates the notification history maintenance request.
	 *
	 * @param userContext the user context
	 * @param notification the notification
	 * @return the notification history maintenance request
	 */
	public static NotificationHistoryMaintenanceRequest createNotificationHistoryMaintenanceRequest(
			UserContext userContext, NotificationHistory notification)
	{
		NotificationHistoryMaintenanceRequest request = new NotificationHistoryMaintenanceRequest();
		request.setUserContext(userContext);
		request.setNotificationHistory(notification);
		return request;
	}

	/**
	 * Creates the alert classification maintenance request.
	 *
	 * @param userContext the user context
	 * @param alertClassification the alert classification
	 * @return the alert classification maintenance request
	 */
	public static AlertClassificationMaintenanceRequest createAlertClassificationMaintenanceRequest(
			UserContext userContext, AlertClassification alertClassification)
	{
		AlertClassificationMaintenanceRequest request = new AlertClassificationMaintenanceRequest();
		request.setUserContext(userContext);
		request.setAlertClassification(alertClassification);
		return request;
	}

	/**
	 * Creates the operational data value maintenance request.
	 *
	 * @param userContext the user context
	 * @param operationalData the operational data
	 * @return the operational data value maintenance request
	 */
	public static OperationalDataMaintenanceRequest createOperationalDataValueMaintenanceRequest(
			UserContext userContext, OperationalData operationalData)
	{
		return new OperationalDataMaintenanceRequest(userContext, operationalData);

	}

	/**
	 * Creates the light mass update request.
	 *
	 * @param userContext the user context
	 * @param light the light
	 * @return the light mass update request
	 */
	public static LightMassUpdateRequest createLightMassUpdateRequest(
			UserContext userContext,
			com.sensus.lc.light.model.Light light)
	{
		LightMassUpdateRequest request = new LightMassUpdateRequest(light);
		request.setUserContext(userContext);
		request.setSort(Arrays.asList(SORT));
		request.setLightCriteria(new LightCriteria());
		request.setGroupCriteria(new GroupCriteria());
		request.setAlertCriteria(new AlertCriteria());
		request.setAddressCriteria(new AddressCriteria());
		request.setProcessCriteria(new ProcessCriteria());
		request.setScheduleCriteria(new ScheduleCriteria());
		request.setConfigurationCriteria(new ConfigurationCriteria());
		request.setOperationalDataCriteria(new OperationalDataCriteria());
		request.setTagCriteria(new TagCriteria());
		return request;
	}

	/**
	 * Creates the last operational data maintenance request.
	 *
	 * @param userContext the user context
	 * @param lightId the light id
	 * @return the last operational data maintenance request
	 */
	public static LastOperationalDataMaintenanceRequest createLastOperationalDataMaintenanceRequest(
			UserContext userContext,
			Integer lightId)
	{
		LastOperationalDataMaintenanceRequest request = new LastOperationalDataMaintenanceRequest();
		request.setUserContext(userContext);
		request.setLightId(lightId);
		return request;
	}

	/**
	 * Creates the schedule maintenance request.
	 *
	 * @param userContext the user context
	 * @param lightId the light id
	 * @return the schedule maintenance request
	 */
	public static ScheduleMaintenanceRequest createScheduleMaintenanceRequest(
			UserContext userContext,
			Integer lightId)
	{
		ScheduleMaintenanceRequest request = new ScheduleMaintenanceRequest();
		request.setUserContext(userContext);
		request.setLightId(lightId);
		return request;
	}

	/**
	 * Creates the configuration maintenance request.
	 *
	 * @param userContext the user context
	 * @param lightId the light id
	 * @return the configuration maintenance request
	 */
	public static ConfigurationMaintenanceRequest createConfigurationMaintenanceRequest(
			UserContext userContext,
			Integer lightId)
	{
		ConfigurationMaintenanceRequest request = new ConfigurationMaintenanceRequest();
		request.setUserContext(userContext);
		request.setLightId(lightId);
		return request;
	}

	/**
	 * Creates the location maintenance request.
	 *
	 * @param userContext the user context
	 * @param lightId the light id
	 * @return the location maintenance request
	 */
	public static LocationMaintenanceRequest createLocationMaintenanceRequest(
			UserContext userContext,
			Integer lightId)
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		request.setUserContext(userContext);
		request.setLightId(lightId);
		return request;
	}

	/**
	 * Creates the inquiry pagination request.
	 *
	 * @return the inquiry pagination request
	 */
	public static InquiryPaginationRequest createInquiryPaginationRequest()
	{
		return createInquiryPaginationRequest(0, ARBITRARY_PAGE_SIZE_15, null, true, null, false);
	}

	/**
	 * Creates the inquiry pagination request.
	 *
	 * @param startRow the start row
	 * @param pageSize the page size
	 * @param selectionPaginationIds the selection pagination ids
	 * @param paginationAllSelected the pagination all selected
	 * @param orderBy the order by
	 * @param isProperty the is property
	 * @return the inquiry pagination request
	 */
	public static InquiryPaginationRequest createInquiryPaginationRequest(int startRow, int pageSize,
			List<Integer> selectionPaginationIds, boolean paginationAllSelected, String orderBy, boolean isProperty)
	{
		InquiryPaginationRequest inquiryPaginationRequest = new InquiryPaginationRequest(createUserContext());
		inquiryPaginationRequest.addSortExpressions(createMLCSortExpression(orderBy, isProperty));
		inquiryPaginationRequest.setStartRow(startRow);
		inquiryPaginationRequest.setPageSize(pageSize);
		inquiryPaginationRequest.setPaginationAllSelected(paginationAllSelected);
		inquiryPaginationRequest.setSelectionPaginationIds(selectionPaginationIds);
		return inquiryPaginationRequest;
	}

	/**
	 * Creates the group request.
	 *
	 * @return the group request
	 */
	public static GroupRequest createGroupRequest()
	{
		return new GroupRequest(createUserContext());
	}

	/**
	 * Creates the light selection request.
	 *
	 * @return the light selection request
	 */
	public static LightSelectionRequest createLightSelectionRequest()
	{
		return new LightSelectionRequest(createUserContext());
	}

	/**
	 * Creates the inquiry tag request.
	 *
	 * @return the inquiry tag request
	 */
	public static InquiryTagRequest createInquiryTagRequest()
	{
		InquiryTagRequest inquiryTagRequest = new InquiryTagRequest(createUserContext());
		inquiryTagRequest.setStartRow(0);
		inquiryTagRequest.setPageSize(ARBITRARY_PAGE_SIZE_15);
		return inquiryTagRequest;
	}

	/**
	 * Creates the inquiry eco mode request.
	 *
	 * @return the inquiry eco mode request
	 */
	public static InquiryEcoModeRequest createInquiryEcoModeRequest()
	{
		InquiryEcoModeRequest ecoModeRequest = new InquiryEcoModeRequest(createUserContext());
		SortExpression sortExpression = new SortExpression(EcoModeOrderByEnum.DATE.getValue(), Direction.Descending);
		ecoModeRequest.addSortExpressions(sortExpression);
		ecoModeRequest.setPageSize(ARBITRARY_PAGE_SIZE_15);
		return ecoModeRequest;
	}

	/**
	 * Creates the notification history request.
	 *
	 * @return the notification history request
	 */
	public static NotificationHistoryRequest createNotificationHistoryRequest()
	{
		NotificationHistoryRequest notificationHistoryRequest = new NotificationHistoryRequest();
		notificationHistoryRequest.setUserContext(createUserContext());

		SortExpression sortExpression = new SortExpression(EcoModeOrderByEnum.DATE.getValue(), Direction.Descending);
		notificationHistoryRequest.addSortExpressions(sortExpression);
		notificationHistoryRequest.setPageSize(ARBITRARY_PAGE_SIZE_15);
		return notificationHistoryRequest;
	}

	/**
	 * Creates the eco mode request.
	 *
	 * @return the eco mode request
	 */
	public static EcoModeRequest createEcoModeRequest()
	{
		return new EcoModeRequest(createUserContext());
	}

	/**
	 * Creates the tag request.
	 *
	 * @return the tag request
	 */
	public static TagRequest createTagRequest()
	{
		return new TagRequest(createUserContext());
	}

	/**
	 * Creates the column filter request.
	 *
	 * @return the column filter request
	 */
	public static ColumnFilterRequest createColumnFilterRequest()
	{
		return new ColumnFilterRequest(createUserContext());
	}

	/**
	 * Creates the user request.
	 *
	 * @param user the user
	 * @return the user request
	 */
	public static UserRequest createUserRequest(User user)
	{
		UserContext userContext = createUserContext();
		userContext.setUserId(user.getUserName());
		UserRequest userRequest = new UserRequest(userContext);
		userRequest.setUser(user);
		return userRequest;
	}

	/**
	 * Creates the schedule request.
	 *
	 * @return the schedule request
	 */
	public static ScheduleRequest createScheduleRequest()
	{
		return new ScheduleRequest(createUserContext());
	}

	/**
	 * Creates the inquiry schedule request.
	 *
	 * @return the inquiry schedule request
	 */
	public static InquiryScheduleRequest createInquiryScheduleRequest()
	{
		return new InquiryScheduleRequest(createUserContext());
	}

	/**
	 * Creates the unknown offset schedule request.
	 *
	 * @return the schedule request
	 */
	public static ScheduleRequest createUnknownOffsetScheduleRequest()
	{
		return new ScheduleRequest(createUserContext());
	}

	/**
	 * Creates the setting request.
	 *
	 * @return the settings request
	 */
	public static SettingsRequest createSettingRequest()
	{
		return new SettingsRequest(createUserContext());
	}

	/**
	 * Creates the process request.
	 *
	 * @return the process request
	 */
	public static ProcessRequest createProcessRequest()
	{
		return new ProcessRequest(createUserContext());
	}

	/**
	 * Creates the inquiry process request.
	 *
	 * @return the inquiry process request
	 */
	public static InquiryProcessRequest createInquiryProcessRequest()
	{
		return new InquiryProcessRequest(createUserContext());
	}

	/**
	 * Creates the mlc sort expression.
	 *
	 * @param orderBy the order by
	 * @param isProperty the is property
	 * @return the mLC sort expression
	 */
	public static MLCSortExpression createMLCSortExpression(String orderBy, Boolean isProperty)
	{
		if (isProperty == null)
		{
			isProperty = false;
		}

		MLCSortExpression sortExpression = new MLCSortExpression();
		sortExpression.setIsProperty(isProperty);
		sortExpression.setField(orderBy);
		return sortExpression;
	}

	/**
	 * Creates the analytics request.
	 *
	 * @return the analytics request
	 */
	public static AnalyticsRequest createAnalyticsRequest()
	{
		return new AnalyticsRequest(createUserContext());

	}

	/**
	 * Creates the inquiry analytics request.
	 *
	 * @return the inquiry analytics request
	 */
	public static InquiryAnalyticsRequest createInquiryAnalyticsRequest()
	{
		return new InquiryAnalyticsRequest(createUserContext());
	}

	/**
	 * Creates the inquiry user request.
	 *
	 * @return the inquiry user request
	 */
	public static InquiryUserRequest createInquiryUserRequest()
	{
		return new InquiryUserRequest(createUserContext());
	}

	/**
	 * Creates the light filter values request.
	 *
	 * @return the light filter request
	 */
	public static LightFilterRequest createLightFilterRequest()
	{
		return new LightFilterRequest(createUserContext());
	}

	/**
	 * Gets the light type random.
	 *
	 * @return the light type random
	 */
	public static LightEcoModeTypeEnum getLightTypeRandom()
	{
		int i = RANDOM.nextInt(ARBITRARY_NUMBER_3);
		return LightEcoModeTypeEnum.enumForValue(i + 1);
	}

	/**
	 * Gets the wattage random.
	 *
	 * @return the wattage random
	 */
	public static Double getWattageRandom()
	{
		return new Double(RANDOM.nextInt(ARBITRARY_NUMBER_150) + ARBITRARY_NUMBER_10);
	}

	/**
	 * Gets the voltage random.
	 *
	 * @return the voltage random
	 */
	public static Double getVoltageRandom()
	{
		return new Double(RANDOM.nextInt(NUMBER_RANGE) % ARBITRARY_NUMBER_200 + ARBITRARY_NUMBER_100);
	}

	/**
	 * Gets the current random.
	 *
	 * @return the current random
	 */
	public static Double getCurrentRandom()
	{
		return new Double(RANDOM.nextInt(NUMBER_RANGE) % ARBITRARY_NUMBER_20 + ARBITRARY_NUMBER_10);
	}

	/**
	 * Gets the latitude random.
	 *
	 * @return the latitude random
	 */
	public static Double getLatitudeRandom()
	{
		return new Double("-47." + RANDOM.nextInt(NUMBER_RANGE));
	}

	/**
	 * Gets the longitude random.
	 *
	 * @return the longitude random
	 */
	public static Double getLongitudeRandom()
	{
		return new Double("-19." + RANDOM.nextInt(NUMBER_RANGE));
	}

	/**
	 * Gets the kwh consumption random.
	 *
	 * @return the kwh consumption random
	 */
	public static Double getConsumptionRandom()
	{
		return new Double(RANDOM.nextInt(ARBITRARY_NUMBER_100) + ARBITRARY_NUMBER_10);
	}

	/**
	 * Gets the eco mode random.
	 *
	 * @return the eco mode random
	 */
	public static Double getEcoModeRandom()
	{
		return new Double(RANDOM.nextInt(NUMBER_RANGE) % ARBITRARY_NUMBER_100);
	}

	/**
	 * Check csv file dimensions.
	 *
	 * @param size the size
	 * @param ds the ds
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void checkCSVFileDimensions(Integer size, @SuppressWarnings("rawtypes") CSVDataSource ds)
			throws IOException
	{

		File tmp = new File(FILE_NAME);
		FileReader fr = new FileReader(tmp);
		CSVReader reader = new CSVReader(fr);
		List<String[]> data = reader.readAll();
		fr.close();
		reader.close();

		Integer lineHeader = 1;
		assertEquals(size + lineHeader, data.size());
		assertEquals(data.get(0).length, ds.getNumColumns());
	}
}
