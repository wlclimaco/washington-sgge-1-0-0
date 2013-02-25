package com.sensus.mlc.base;

import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import au.com.bytecode.opencsv.CSVReader;

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
import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.mlc.base.model.MLCSortExpression;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.EcoModeOrderByEnum;
import com.sensus.mlc.ecomode.model.LightConsumption;
import com.sensus.mlc.ecomode.model.LightEcoModeTypeEnum;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionCategoryEnum;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessFilter;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.schedule.model.DaysOfWeekEnum;
import com.sensus.mlc.schedule.model.Event;
import com.sensus.mlc.schedule.model.EventSchedule;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.schedule.model.ScheduleTypeEnum;
import com.sensus.mlc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.Filter;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightBlinkEnum;
import com.sensus.mlc.smartpoint.model.LightConfiguration;
import com.sensus.mlc.smartpoint.model.LightHistory;
import com.sensus.mlc.smartpoint.model.LightIntensityEnum;
import com.sensus.mlc.smartpoint.model.LightLastOperationalData;
import com.sensus.mlc.smartpoint.model.LightLocation;
import com.sensus.mlc.smartpoint.model.LightPropertyForSearchEnum;
import com.sensus.mlc.smartpoint.model.LightSchedule;
import com.sensus.mlc.smartpoint.model.LightStateEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.LightTextSearch;
import com.sensus.mlc.smartpoint.model.LightTypeEnum;
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
import com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ChannelSetupAuditRequest;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.request.ForcedLightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.GuaranteeLightExistenceRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightSetupNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightingConfigurationRequest;
import com.sensus.mlc.smartpoint.model.request.ProcessLightsRequest;
import com.sensus.mlc.smartpoint.model.request.PropertyValidValuesRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.request.TenantRequest;
import com.sensus.mlc.user.model.User;
import com.sensus.mlc.user.model.request.UserRequest;

/**
 * The Class TestBaseUtil.
 */
public final class TestBaseUtil
{

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

	/** The Constant ARBITRARY_HARDWARE_SETTING_11. */
	private static final Integer ARBITRARY_HARDWARE_SETTING_11 = 11;

	/** The Constant ARBITRARY_CURRENT_SCALE_12. */
	private static final Integer ARBITRARY_CURRENT_SCALE_12 = 12;

	/** The Constant ARBITRARY_SUNRISE_OFF_SET_MINUTES_20. */
	private static final Integer ARBITRARY_SUNRISE_OFF_SET_MINUTES_20 = 20;

	/** The Constant ARBITRARY_SUNSET_OFF_SET_MINUTES_15. */
	private static final Integer ARBITRARY_SUNSET_OFF_SET_MINUTES_15 = 15;

	/** The Constant ARBITRARY_MIDDLE_LAT_MAX. */
	private static final Double ARBITRARY_MIDDLE_LAT_MAX = 100.00;

	/** The Constant ARBITRARY_MIDDLE_LAT_MIN. */
	private static final Double ARBITRARY_MIDDLE_LAT_MIN = 10.00;

	/** The Constant ARBITRARY_MIDDLE_LON_MAX. */
	private static final Double ARBITRARY_MIDDLE_LON_MAX = 100.00;

	/** The Constant ARBITRARY_MIDDLE_LON_MIN. */
	private static final Double ARBITRARY_MIDDLE_LON_MIN = 10.00;

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

	/** The Constant ARBITRARY_NUMBER_N10. */
	private static final Integer ARBITRARY_NUMBER_N10 = -10;

	/** The Constant ONE_THOUSAND. */
	private static final Double ONE_THOUSAND = 1000.00;

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
		return new Tenant(1, "acme", "ACME Corporation LC Demo", "ACME",
				"http://localhost:8083/gateway-mlc/mlc-gateway-ws/");
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

		Light light = new Light();
		light.setId(id);
		light.setRniId(id);
		light.setPoleId(String.valueOf(RANDOM.nextInt(NUMBER_RANGE)));
		light.setLightConfiguration(createLightConfiguration());
		light.setLightTypeEnum(LightTypeEnum.INDUCTION);
		light.setLightStateEnum(LightStateEnum.ON);
		light.setLightIntensityEnum(LightIntensityEnum.LEVEL_0);
		light.setLightLocation(createLightLocation());
		light.setLightLastOperationalData(createLightLastOperationalData());
		light.setLightConfiguration(createLightConfiguration());
		light.setLightSchedule(createLightSchedule());
		light.setLightLastOperationalData(createLightLastOperationalData());
		light.setEcoMode(getEcoModeRandom());
		light.setLastConsumption(createLightConsumption(light, 0));
		light.setCurrentLightStatusEnum(LightStatusEnum.ACTIVE);
		light.setCurrentAlarmWarningMessageList(createCurrentAlarmWarningMessageList());

		return light;
	}

	/**
	 * Creates the light last operational data.
	 *
	 * @return the light last operational data
	 */
	public static LightLastOperationalData createLightLastOperationalData()
	{
		LightLastOperationalData lightLastOperationalData = new LightLastOperationalData();
		lightLastOperationalData.setConsumption(ONE_THOUSAND);
		lightLastOperationalData.setConsumptionMin(ONE_THOUSAND);
		lightLastOperationalData.setConsumptionMax(ONE_THOUSAND);

		return lightLastOperationalData;
	}

	/**
	 * Creates the light schedule.
	 *
	 * @return the light schedule
	 */
	public static LightSchedule createLightSchedule()
	{
		LightSchedule lightSchedule = new LightSchedule();
		lightSchedule.setSunriseTime("2013-01-18 06:24:23");
		lightSchedule.setSunsetTime("2013-01-18 20:24:23");
		lightSchedule.setSunriseOffset(ARBITRARY_NUMBER_N10);
		lightSchedule.setSunsetOffset(ARBITRARY_NUMBER_N5);

		return lightSchedule;
	}

	/**
	 * Creates the light location.
	 *
	 * @return the light location
	 */
	public static LightLocation createLightLocation()
	{
		LightLocation lightLocation = new LightLocation();
		lightLocation.setTimeZone("America/New_York");
		lightLocation.setLatitude(ARBITRARY_LATITUDE);
		lightLocation.setLongitude(ARBITRARY_LONGITUDE);
		lightLocation.setStreetName("SE Stark St.");
		lightLocation.setCityName("Morrisville");
		lightLocation.setCountyName("USA");
		lightLocation.setStateName("Oregon");
		lightLocation.setZipCode("553447");

		return lightLocation;

	}

	/**
	 * Creates the light configuration.
	 *
	 * @return the light configuration
	 */
	public static LightConfiguration createLightConfiguration()
	{
		LightConfiguration lightConfiguration = new LightConfiguration();
		lightConfiguration.setHousing("High Bay");
		lightConfiguration.setHousingColor("Gray");
		lightConfiguration.setDimmable(true);
		lightConfiguration.setLampTypeWattageDimmable("120W Induction");
		lightConfiguration.setWattageRating("120W");
		lightConfiguration.setInputaWattageRange("90-300V");
		lightConfiguration.setColorTemperature("3,000K");
		lightConfiguration.setManufacturer("CREE");
		lightConfiguration.setFirmwareVersion("1.0.0");
		lightConfiguration.setModelNumber("539449037C2321");
		lightConfiguration.setLowerAssemblySerial("Lower 2");
		lightConfiguration.setUpperAssemblySerial("Upper 2");
		lightConfiguration.setBulbSerialNumber("Bulb 2");
		lightConfiguration.setBallastSerialNumber(String.valueOf(new Random().nextInt(NUMBER_RANGE)));

		return lightConfiguration;
	}

	/**
	 * Creates the current alarm warning message list.
	 *
	 * @return the list
	 */
	public static List<CurrentAlarmWarningMessage> createCurrentAlarmWarningMessageList()
	{
		List<CurrentAlarmWarningMessage> currentAlarmWarningList = new ArrayList<CurrentAlarmWarningMessage>();
		currentAlarmWarningList.add(createCurrentAlarmWarningMessage());
		return currentAlarmWarningList;

	}

	/**
	 * Creates the eco mode baseline.
	 *
	 * @param light the light
	 * @return the eco mode baseline
	 */
	public static EcoModeBaseline createEcoModeBaseline(Light light)
	{
		return new EcoModeBaseline(light, getLightTypeRandom(), getWattageRandom(), false);
	}

	/**
	 * Creates the eco mode baseline.
	 *
	 * @return the eco mode baseline
	 */
	public static EcoModeBaseline createEcoModeBaseline()
	{
		return new EcoModeBaseline(createLight(), getLightTypeRandom(), getWattageRandom(), false);
	}

	/**
	 * Creates the light consumption.
	 *
	 * @param light the light
	 * @param daysAmountAgo the days amount ago
	 * @return the light consumption
	 */
	public static LightConsumption createLightConsumption(Light light, int daysAmountAgo)
	{
		Date dateUTC = LCDateUtil.getNewDateUTC();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateUTC);
		calendar.set(Calendar.DATE, -daysAmountAgo);
		Date dateNew = calendar.getTime();

		LightConsumption lightConsumption = new LightConsumption(
				dateNew,
				getEcoModeRandom(),
				getConsumptionRandom(),
				getConsumptionRandom(),
				light);

		return lightConsumption;
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
		lcActionParameter.setActionValue("FileName");
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
		event.setLightBlinkEnum(LightBlinkEnum.FAST);
		return event;
	}

	/**
	 * Creates the schedule.
	 *
	 * @return the event schedule
	 */
	public static EventSchedule createSchedule()
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
	 * Creates the custom search.
	 *
	 * @return the custom search
	 */
	public static CustomSearch createCustomSearch()
	{
		CustomSearch custom = new CustomSearch();

		List<Column> listColumns = new ArrayList<Column>();

		List<SmartPointFilterEnum> listFilters = new ArrayList<SmartPointFilterEnum>();

		listFilters.add(SmartPointFilterEnum.valueOf("GROUPS"));
		listFilters.add(SmartPointFilterEnum.STATUS);
		listFilters.add(SmartPointFilterEnum.ADDRESS);

		Column column = new Column();
		column.setColumnEnum(SmartPointColumnEnum.POLE_ID);
		column.setFieldName("POLE_ID");
		listColumns.add(column);

		column = new Column();
		column.setColumnEnum(SmartPointColumnEnum.RNI_ID);
		column.setFieldName("FLEXNET_ID");
		listColumns.add(column);

		column = new Column();
		column.setColumnEnum(SmartPointColumnEnum.LAMP_TYPE);
		column.setFieldName("LIGHT_TYPE");
		listColumns.add(column);

		column = new Column();
		column.setColumnEnum(SmartPointColumnEnum.DATE_ADDED);
		column.setFieldName("DATE_ADDED");
		listColumns.add(column);

		custom.setId(RANDOM.nextInt(NUMBER_RANGE));
		custom.setName("Custom Search + " + RANDOM.nextInt(NUMBER_RANGE));
		custom.setDescription("Description." + RANDOM.nextInt(NUMBER_RANGE));
		custom.setCreateDate(new Date());
		custom.setListColumn(listColumns);

		return custom;
	}

	/**
	 * Creates the search parameter.
	 *
	 * @return the search parameter
	 */
	public static SearchParameter createSearchParameter()
	{
		SearchParameter param = new SearchParameter();
		param.setPropertyEnum(PropertyEnum.SORT);
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
	 * Creates the status message.
	 *
	 * @return the status message
	 */
	public static StatusMessage createStatusMessage()
	{
		StatusMessage sm = new StatusMessage();
		sm.setCreateDate(new Date());
		sm.setDate(new Date());
		sm.setLightStatusEnum(LightStatusEnum.MAINTENANCE);
		return sm;
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
	 * Creates the sensus part number configuration.
	 *
	 * @return the sensus part number configuration
	 */
	public static SensusPartNumberConfiguration createSensusPartNumberConfiguration()
	{
		SensusPartNumberConfiguration config = new SensusPartNumberConfiguration();

		config.setIntensityLevel(1);
		config.setHardwareSetting(ARBITRARY_HARDWARE_SETTING_11);
		config.setCurrentScale(ARBITRARY_CURRENT_SCALE_12);
		config.setFullOnRequired(true);

		return config;
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
		return settings;
	}

	/**
	 * Creates the current alarm warning message.
	 *
	 * @return the current alarm warning message
	 */
	public static CurrentAlarmWarningMessage createCurrentAlarmWarningMessage()
	{
		CurrentAlarmWarningMessage currentMessage = new CurrentAlarmWarningMessage();
		currentMessage.setLightId(RANDOM.nextInt(NUMBER_RANGE));
		currentMessage.setStatusMessageSubtype(StatusExceptionTypeEnum.POWER_FAILURE);
		currentMessage.setCreateDate(getNewDateUTC());
		currentMessage.setCreateUser(CREATE_USER);
		currentMessage.setStatusMessageId(RANDOM.nextInt(NUMBER_RANGE));
		currentMessage.setStatusMessage(LightStatusEnum.ACTIVE);
		currentMessage.setStatusMessageType(StatusMessageCategoryEnum.ALARM);
		currentMessage.setStatusMessageSubtype(StatusExceptionTypeEnum.COMMUNICATION_FAIL);
		currentMessage.setMessageDate(getNewDateUTC());
		currentMessage.setTenantId(createTenant().getId());

		return currentMessage;
	}

	/**
	 * Creates the property valid value.
	 *
	 * @return the property valid value
	 */
	public static PropertyValidValue createPropertyValidValue()
	{
		PropertyValidValue property = new PropertyValidValue();
		property.setId(1);
		property.setValue("300W LED");
		return property;
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
		process.setRniCorrelationId(new LCHelp().generateRniCorrelationId());

		if (!ValidationUtil.isNull(light))
		{
			process.setProcessItems(Arrays.asList(createProcessItem(light, ProcessItemStatusEnum.PENDING)));
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
	 * Creates the smartpoint middle map.
	 *
	 * @return the smartpoint middle map
	 */
	public static SmartpointMiddleMap createSmartpointMiddleMap()
	{
		SmartpointMiddleMap middleMap = new SmartpointMiddleMap();
		middleMap.setMiddleLatMax(ARBITRARY_MIDDLE_LAT_MAX);
		middleMap.setMiddleLatMin(ARBITRARY_MIDDLE_LAT_MIN);
		middleMap.setMiddleLonMax(ARBITRARY_MIDDLE_LON_MAX);
		middleMap.setMiddleLonMin(ARBITRARY_MIDDLE_LON_MIN);
		return middleMap;
	}

	/**
	 * Creates the process item.
	 *
	 * @param light the light
	 * @param processItemStatus the process item status
	 * @return the process item
	 */
	public static ProcessItem createProcessItem(Light light, ProcessItemStatusEnum processItemStatus)
	{
		ProcessItem processItem = new ProcessItem(processItemStatus);
		processItem.setLight(light);
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
		lcAction.setActionType(LCActionTypeEnum.ADD_SMP_TO_SCHEDULE_EVENT);
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
		filter.setActionCategoryList(Arrays.asList(LCActionCategoryEnum.MANAGE_SCHEDULES));
		filter.setUserIds(Arrays.asList(String.valueOf(userId)));

		return filter;
	}

	/**
	 * Creates the column filter response.
	 *
	 * @return the column filter response
	 */
	public static ColumnFilterResponse createColumnFilterResponse()
	{
		ColumnFilterResponse columnfilter = new ColumnFilterResponse();

		List<Column> listColumn = new ArrayList<Column>();
		Column column = new Column();
		column.setColumnEnum(SmartPointColumnEnum.CITY_NAME);
		listColumn.add(column);

		column = new Column();
		column.setColumnEnum(SmartPointColumnEnum.RNI_ID);
		listColumn.add(column);
		columnfilter.setListColumn(listColumn);

		List<Filter> listFilter = new ArrayList<Filter>();
		Filter filter = new Filter();
		filter.setFilterEnum(SmartPointFilterEnum.GROUPS);
		listFilter.add(filter);

		columnfilter.setFilters(listFilter);

		return columnfilter;
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
		return new TenantRequest(createUserContext());
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
	 * Creates the alarm notification request.
	 *
	 * @return the alarm notification request
	 */
	public static AlarmNotificationRequest createAlarmNotificationRequest()
	{
		return new AlarmNotificationRequest(createUserContext());
	}

	/**
	 * Creates the forced light status notification request.
	 *
	 * @return the forced light status notification request
	 */
	public static ForcedLightStatusNotificationRequest createForcedLightStatusNotificationRequest()
	{
		return new ForcedLightStatusNotificationRequest(createUserContext());
	}

	/**
	 * Creates the process lights request request.
	 *
	 * @return the process lights request
	 */
	public static ProcessLightsRequest createProcessLightsRequestRequest()
	{
		return new ProcessLightsRequest(createUserContext());
	}

	/**
	 * Creates the process lights request.
	 *
	 * @return the process lights request
	 */
	public static ProcessLightsRequest createProcessLightsRequest()
	{
		return new ProcessLightsRequest(createUserContext());
	}

	/**
	 * Creates the channel setup audit request.
	 *
	 * @return the channel setup audit request
	 */
	public static ChannelSetupAuditRequest createChannelSetupAuditRequest()
	{
		return new ChannelSetupAuditRequest(createUserContext());
	}

	/**
	 * Creates the custom search request.
	 *
	 * @return the custom search request
	 */
	public static CustomSearchRequest createCustomSearchRequest()
	{
		return new CustomSearchRequest(createUserContext());
	}

	/**
	 * Creates the light setup notification.
	 *
	 * @return the light setup notification request
	 */
	public static LightSetupNotificationRequest createLightSetupNotification()
	{
		return new LightSetupNotificationRequest(createUserContext());
	}

	/**
	 * Creates the guarantee light existence request.
	 *
	 * @return the guarantee light existence request
	 */
	public static GuaranteeLightExistenceRequest createGuaranteeLightExistenceRequest()
	{
		return new GuaranteeLightExistenceRequest(createUserContext());
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
	 * Creates the inquiry light request.
	 *
	 * @param userContext the user context
	 * @return the inquiry light request
	 */
	public static InquiryLightRequest createInquiryLightRequest(UserContext userContext)
	{
		return new InquiryLightRequest(userContext);
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
		ecoModeRequest.setSortExpression(sortExpression.toString());
		ecoModeRequest.setPageSize(ARBITRARY_PAGE_SIZE_15);
		return ecoModeRequest;
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
	 * Creates the inquiry light request.
	 *
	 * @param searchLight the search light
	 * @param startRow the start row
	 * @param pageSize the page size
	 * @param selectionPaginationIds the selection pagination ids
	 * @param paginationAllSelected the pagination all selected
	 * @param orderBy the order by
	 * @param isProperty the is property
	 * @return the inquiry light request
	 */
	public static InquiryLightRequest createInquiryLightRequest(
			SearchLight searchLight,
			Integer startRow,
			Integer pageSize,
			List<Integer> selectionPaginationIds,
			Boolean paginationAllSelected,
			String orderBy,
			Boolean isProperty)
	{
		InquiryLightRequest inquiryLightRequest = createInquiryLightRequest(createUserContext());
		inquiryLightRequest.setSortExpression(createMLCSortExpression(orderBy, isProperty));
		inquiryLightRequest.setStartRow(startRow);
		inquiryLightRequest.setPageSize(pageSize);
		inquiryLightRequest.setPaginationAllSelected(paginationAllSelected);
		inquiryLightRequest.setSelectionPaginationIds(selectionPaginationIds);
		inquiryLightRequest.setSearch(searchLight);

		return inquiryLightRequest;
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
	 * Creates the lighting configuration request.
	 *
	 * @return the lighting configuration request
	 */
	public static LightingConfigurationRequest createLightingConfigurationRequest()
	{
		return new LightingConfigurationRequest(createUserContext());
	}

	/**
	 * Creates the property valid values request.
	 *
	 * @return the property valid values request
	 */
	public static PropertyValidValuesRequest createPropertyValidValuesRequest()
	{
		return new PropertyValidValuesRequest(createUserContext());
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
		return new Double((RANDOM.nextInt(NUMBER_RANGE) % ARBITRARY_NUMBER_200) + ARBITRARY_NUMBER_100);
	}

	/**
	 * Gets the current random.
	 *
	 * @return the current random
	 */
	public static Double getCurrentRandom()
	{
		return new Double((RANDOM.nextInt(NUMBER_RANGE) % ARBITRARY_NUMBER_20) + ARBITRARY_NUMBER_10);
	}

	/**
	 * Gets the latitude random.
	 *
	 * @return the latitude random
	 */
	public static Double getLatitudeRandom()
	{
		return new Double("-47." + (RANDOM.nextInt(NUMBER_RANGE)));
	}

	/**
	 * Gets the longitude random.
	 *
	 * @return the longitude random
	 */
	public static Double getLongitudeRandom()
	{
		return new Double("-19." + (RANDOM.nextInt(NUMBER_RANGE)));
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
