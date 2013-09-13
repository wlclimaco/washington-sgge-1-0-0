package com.sensus.dm.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Location;
import com.sensus.cbof.model.Note;
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
import com.sensus.common.scheduler.model.DaysOfWeekEnum;
import com.sensus.common.scheduler.model.Frequency;
import com.sensus.common.scheduler.model.FrequencyTypeEnum;
import com.sensus.common.scheduler.model.ScheduleStatusEnum;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.model.request.DeviceManagerInquiryRequest;
import com.sensus.dm.common.device.model.Alarm;
import com.sensus.dm.common.device.model.AlarmEnum;
import com.sensus.dm.common.device.model.AlarmsTypesCount;
import com.sensus.dm.common.device.model.CustomSearch;
import com.sensus.dm.common.device.model.DeviceColumnEnum;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.GeocodeDeviceInfo;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.ColumnFilterRequest;
import com.sensus.dm.common.device.model.request.CustomSearchRequest;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryCustomSearchRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.request.NoteRequest;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessCategory;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.InquiryScheduleRequest;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.request.TagRequest;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.elec.action.model.DemandResetEventAction;
import com.sensus.dm.elec.action.model.DemandResponseEventAction;
import com.sensus.dm.elec.action.model.RemoteArmConnectAction;
import com.sensus.dm.elec.action.model.RemoteConnectAction;
import com.sensus.dm.elec.action.model.RemoteDisconnectAction;
import com.sensus.dm.elec.action.model.SendHanTextMessageAction;
import com.sensus.dm.elec.action.model.request.ActionRequest;
import com.sensus.dm.elec.device.model.DeviceClassEnum;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterConfiguration;
import com.sensus.dm.elec.device.model.ElectricMeterLifecycleStateEnum;
import com.sensus.dm.elec.device.model.ElectricMeterLifecycleStatusEnum;
import com.sensus.dm.elec.device.model.ElectricMeterSearch;
import com.sensus.dm.elec.device.model.ElectricMeterTypeEnum;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanDeviceSearch;
import com.sensus.dm.elec.device.model.IntervalRead;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMConfiguration;
import com.sensus.dm.elec.device.model.LCMLifecycleStateEnum;
import com.sensus.dm.elec.device.model.LCMRelay;
import com.sensus.dm.elec.device.model.LCMSearch;
import com.sensus.dm.elec.device.model.LCMTypeEnum;
import com.sensus.dm.elec.device.model.LifecycleState;
import com.sensus.dm.elec.device.model.LoadProfile;
import com.sensus.dm.elec.device.model.PeakDemandOrderByEnum;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;
import com.sensus.dm.gas.device.model.GasMeter;
import com.sensus.dm.gas.device.model.GasMeterTypeEnum;
import com.sensus.dm.water.device.model.WaterGasMeterConfiguration;
import com.sensus.dm.water.device.model.WaterGasMeterStatusCount;
import com.sensus.dm.water.device.model.WaterGasMeterStatusEnum;
import com.sensus.dm.water.device.model.WaterLeak;
import com.sensus.dm.water.device.model.WaterMeter;
import com.sensus.dm.water.device.model.WaterMeterSearch;
import com.sensus.dm.water.device.model.WaterMeterTypeEnum;

/**
 * Utility methods for unit testing.
 * 
 * @author QAT Global.
 */
public final class TestBaseUtil
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant ZERO. */
	protected static final Integer ZERO = 0;

	/** The Constant ONE. */
	protected static final Integer ONE = 1;

	/** The Constant TWO. */
	protected static final Integer TWO = 2;

	/** The Constant THREE. */
	protected static final int THREE = 3;

	/** The Constant FOUR. */
	protected static final int FOUR = 4;

	/** The Constant FIVE. */
	protected static final int FIVE = 5;

	/** The Constant SIX. */
	protected static final int SIX = 6;

	/** The Constant SEVEN. */
	protected static final int SEVEN = 7;

	/** The Constant EIGHT. */
	protected static final int EIGHT = 8;

	/** The Constant NINE. */
	protected static final int NINE = 9;

	/** The Constant TEN. */
	protected static final int TEN = 10;

	/** The Constant FIFTEEN. */
	private static final Integer FIFTEEN = 15;

	/** The Constant SEVENTEEN. */
	protected static final int SEVENTEEN = 17;

	/** The Constant THIRTY. */
	private static final int THIRTY = 30;

	/** The Constant TWO_HUNDRED. */
	private static final Integer TWO_HUNDRED = 200;

	/** The USER. */
	public static final String USER = "rod";

	/** The Constant USER_PROVIDER. */
	public static final String USER_PROVIDER = "EPM.User";

	/** The Constant DEVICE_MODEL. */
	private static final String DEVICE_MODEL = "539 63 537 08002";

	/** The Constant MANUFACTURE. */
	private static final String MANUFACTURE = "Sensus/HAI";

	/** The Constant NUMBER_RANGE. */
	private static final Integer NUMBER_RANGE = Integer.MAX_VALUE;

	/** The Constant CUSTOMER_ID. */
	private static final String CUSTOMER_ID = "ACME";

	/** The Constant PAGE_SIZE. */
	private static final int PAGE_SIZE = 25;

	/** The Constant FLEXNET_ID. */
	private static final int FLEXNET_ID = 1010;

	/** The Constant FLEXNET_ID_1010. */
	private static final BigInteger FLEXNET_ID_1010 = new BigInteger("1010");

	/** The Constant DEVICE_ID. */
	private static final String DEVICE_ID = "1010M";

	/** The Constant MAC_ADDRESS. */
	private static final String MAC_ADDRESS = "2153943262073407";

	/** The Constant NUMBER_TO_REPEAT_150. */
	private static final int NUMBER_TO_REPEAT_150 = 150;

	/** The Constant NUMBER_TO_REPEAT_100. */
	private static final int NUMBER_TO_REPEAT_100 = 100;

	/** The Constant LIFECYCLE_STATE. */
	private static final String LIFECYCLE_STATE = "Install";

	/** The Constant LETTER_N. */
	private static final String LETTER_N = "n";

	/** The Constant LETTER_D. */
	private static final String LETTER_D = "d";

	/** The Constant NAME. */
	private static final String NAME = "name";

	/** The Constant LOCALE. */
	private static final String LOCALE = "en_US";

	/** The Constant ENROLLMENT_CODE. */
	private static final Integer ENROLLMENT_CODE = 0;

	/** The Constant CHANNEL_NAME_FORWARD. */
	private static final String CHANNEL_NAME_FORWARD = "Forward, Interval, Kwh";

	/** The Constant CHANNEL_NAME_REVERSE. */
	private static final String CHANNEL_NAME_REVERSE = "Reverse, Interval, Kwh";

	/** The Constant ZERO_READING. */
	private static final String ZERO_READING = "0.000";

	/** The Constant SEND_TEXT_HAN. */
	private static final String SEND_TEXT_HAN = "test";

	/** The Constant SCHEDULE_NAME. */
	private static final String SCHEDULE_NAME = "sch_test";

	/** The Constant SCHEDULE_DESCRIPTION. */
	private static final String SCHEDULE_DESCRIPTION = "sch_description";

	/** The Constant RANDOM. */
	private static final Random RANDOM = new Random();

	/** The Constant ZIP. */
	private static final String ZIP = "123456";

	/** The Constant NEW_YORK. */
	private static final String NEW_YORK = "New York";

	/** The Constant STREET. */
	private static final String STREET = "Street";

	/** The Constant DAILY_CONSUMPTION_AVERAGE. */
	private static final String DAILY_CONSUMPTION_AVERAGE = "144";

	/** The Constant PRIOR_CONSUMPTION_PERCENTUAL. */
	private static final String PRIOR_CONSUMPTION_PERCENTUAL = "2152.14";

	/** The Constant PRIOR_CONSUMPTION. */
	private static final String PRIOR_CONSUMPTION = "3112";

	/** The Constant RECENT_CONSUMPTION_PERCENTUAL. */
	private static final String RECENT_CONSUMPTION_PERCENTUAL = "1212.12";

	/** The Constant RECENT_CONSUMPTION. */
	private static final String RECENT_CONSUMPTION = "1000";

	/** The Constant DATE_TIME_LEAK_REPORT. */
	private static final String DATE_TIME_LEAK_REPORT = "Prior to 2013-05-05 01:05PM";

	/** The Constant STR_CHANNEL_NAME_1_427. */
	private static final String STR_CHANNEL_NAME_1_427 = "1.427";

	/** The Constant TOP_RIGHT_LON. */
	private static final double TOP_RIGHT_LON = 35.8269996643066;

	/** The Constant TOP_RIGHT_LAT. */
	private static final double TOP_RIGHT_LAT = 47.6317253112793;

	/** The Constant BOTTOM_LEFT_LON. */
	private static final double BOTTOM_LEFT_LON = -122.175392150879;

	/** The Constant BOTTOM_LEFT_LAT. */
	private static final double BOTTOM_LEFT_LAT = -78.8280029296875;

	/** The Constant DISPLAY_MINUTE_240. */
	private static final String DISPLAY_MINUTE_240 = "-240";

	/** The Constant TIME_ZONE_AMERICA_SAO_PAULO. */
	protected static final String TIME_ZONE_AMERICA_SAO_PAULO = "America/Sao_Paulo";

	/** The Constant TIME_ZONE_N3. */
	private static final String TIME_ZONE_N3 = "-3";

	/** The Constant FORMATTED_DATE. */
	private static final String FORMATTED_DATE = "yyyy-MM-dd";

	/** The Constant FILE_NAME. */
	private static final String FILE_NAME = "fileTest.csv";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant SENSUS_EPM_PROCESS_CATEGORY_TAG. */
	private static final String SENSUS_EPM_PROCESS_CATEGORY_TAG = "sensus.epm.process.category.tag";

	/** The Constant SENSUS_EPM_PROCESS_CATEGORY_GROUP. */
	private static final String SENSUS_EPM_PROCESS_CATEGORY_GROUP = "sensus.epm.process.category.group";

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
	 * Validate assert response.
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
	 * Creates the user context.
	 * 
	 * @return the user context
	 */
	public static UserContext createUserContext()
	{
		UserContext userContext = new UserContext(USER);
		userContext.setId(1);
		return userContext;
	}

	/**
	 * Creates the user context.
	 * 
	 * @param locale the locale
	 * @return the user context
	 */
	public static UserContext createUserContext(String locale)
	{
		UserContext userContext = createUserContext();
		userContext.setLocaleString(locale);
		return userContext;
	}

	/**
	 * Creates the user context with locale.
	 * 
	 * @return the user context
	 */
	public static UserContext createUserContextWithLocale()
	{
		UserContext userContext = new UserContext(USER);
		userContext.setId(1);
		userContext.setLocaleString("us");
		return userContext;
	}

	/**
	 * Creates the custom search.
	 * 
	 * @param deviceTypeEnum the device type enum
	 * @return the custom search
	 */
	public static CustomSearch createCustomSearch(DeviceTypeEnum deviceTypeEnum)
	{
		String name = StringUtils.repeat(LETTER_N, NUMBER_TO_REPEAT_100);
		String description = StringUtils.repeat(LETTER_D, NUMBER_TO_REPEAT_150);

		CustomSearch customSearch = new CustomSearch(name, description);
		customSearch.setDeviceType(deviceTypeEnum);
		customSearch.setId(1);

		switch (deviceTypeEnum)
		{
			case ELECTRIC_METER:
				customSearch
						.setElectricMeterSearch(new ElectricMeterSearch((ElectricMeter)createDevice(deviceTypeEnum)));
				break;
			case HAN_DEVICE:
				customSearch.setHanDeviceSearch(new HanDeviceSearch((HanDevice)createDevice(deviceTypeEnum)));
				break;
			case LCM:
				customSearch.setLcmSearch(new LCMSearch((LCM)createDevice(deviceTypeEnum)));
				break;
			case WATER_METER:
				customSearch.setWaterMeterSearch(new WaterMeterSearch((WaterMeter)createDevice(deviceTypeEnum)));
				break;
			default:
				break;
		}

		return customSearch;
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
	 * Creates the custom search request with locale on user context.
	 * 
	 * @return the custom search request
	 */
	public static CustomSearchRequest createCustomSearchRequestWithLocaleOnUserContext()
	{
		return new CustomSearchRequest(createUserContextWithLocale());
	}

	/**
	 * Creates the inquiry custom search request.
	 * 
	 * @return the inquiry custom search request
	 */
	public static InquiryCustomSearchRequest createInquiryCustomSearchRequest()
	{
		InquiryCustomSearchRequest inquiryCustomSearchRequest = new InquiryCustomSearchRequest(createUserContext());
		createInquiryRequest(inquiryCustomSearchRequest);
		return inquiryCustomSearchRequest;
	}

	/**
	 * Creates the device list.
	 * 
	 * @param deviceTypeEnum the device type enum
	 * @param amount the amount
	 * @return the list
	 */
	public static List<Device> createDeviceList(DeviceTypeEnum deviceTypeEnum, Integer amount)
	{
		if (amount == 0)
		{
			return null;
		}

		List<Device> deviceList = new ArrayList<Device>();
		for (int i = 1; i <= amount; i++)
		{
			Device device = createDevice(deviceTypeEnum);
			deviceList.add(device);
		}

		return deviceList;
	}

	/**
	 * Creates the device.
	 * 
	 * @param deviceTypeEnum the device type enum
	 * @return the device
	 */
	public static Device createDevice(DeviceTypeEnum deviceTypeEnum)
	{
		return createDevice(deviceTypeEnum, null, null);
	}

	/**
	 * Creates the water leak.
	 * 
	 * @return the water leak
	 */
	public static WaterLeak createWaterLeak()
	{
		return new WaterLeak(new WaterMeter(DEVICE_ID, new Radio(FLEXNET_ID_1010)),
				DATE_TIME_LEAK_REPORT, RECENT_CONSUMPTION,
				RECENT_CONSUMPTION_PERCENTUAL, PRIOR_CONSUMPTION, PRIOR_CONSUMPTION_PERCENTUAL,
				DAILY_CONSUMPTION_AVERAGE);
	}

	/**
	 * Creates the device.
	 * 
	 * @param deviceTypeEnum the device type enum
	 * @param flexNetId the flex net id
	 * @param deviceId the device id
	 * @return the device
	 */
	public static Device createDevice(DeviceTypeEnum deviceTypeEnum, BigInteger flexNetId, String deviceId)
	{

		switch (deviceTypeEnum)
		{
			case ELECTRIC_METER:

				ElectricMeter electricMeter = new ElectricMeter();
				electricMeter.setLifecycleStateEnum(ElectricMeterLifecycleStateEnum.INSTALLED);
				electricMeter.setLifecycleStatusEnum(ElectricMeterLifecycleStatusEnum.ACTIVE);
				electricMeter.setElectricMeterTypeEnum(ElectricMeterTypeEnum.RX);

				if (flexNetId == null)
				{
					electricMeter.setRadio(new Radio(BigInteger.valueOf(FLEXNET_ID)));
				}
				else
				{
					electricMeter.setRadio(new Radio(flexNetId));
				}
				electricMeter.setDeviceModel(new DeviceModel(MANUFACTURE, DEVICE_MODEL));
				electricMeter.setConfiguration(new ElectricMeterConfiguration());
				electricMeter.getConfiguration().setInstallDate(new Date());

				electricMeter.setDeviceId(deviceId);
				electricMeter.getRadio().setCustomerId(CUSTOMER_ID);
				electricMeter.getRadio().setLocation(new Location(STREET, NEW_YORK, ZIP));

				return electricMeter;

			case HAN_DEVICE:

				HanDevice hanDevice = new HanDevice();
				if (flexNetId == null)
				{
					hanDevice.setRadio(new Radio(BigInteger.valueOf(FLEXNET_ID)));
					hanDevice.setMacAddress(MAC_ADDRESS);
				}
				else
				{
					hanDevice.setRadio(new Radio(flexNetId));
					hanDevice.setMacAddress(flexNetId.toString());
					hanDevice.setElectricMeterFlexNetId(flexNetId);
				}

				hanDevice.setDeviceId(deviceId);
				hanDevice.getRadio().setCustomerId(CUSTOMER_ID);
				hanDevice.getRadio().setLocation(new Location(STREET, NEW_YORK, ZIP));

				return hanDevice;

			case LCM:
				LCM lcm = new LCM();
				lcm.setLifecycleStateEnum(LCMLifecycleStateEnum.JOINED);
				if (flexNetId == null)
				{
					lcm.setElectricMeterFlexNetId(BigInteger.valueOf(FLEXNET_ID));
					lcm.setRadio(new Radio(BigInteger.valueOf(FLEXNET_ID)));
				}
				else
				{
					lcm.setElectricMeterFlexNetId(flexNetId);
					lcm.setRadio(new Radio(flexNetId));
				}

				lcm.setDeviceModel(new DeviceModel(MANUFACTURE, DEVICE_MODEL));
				lcm.setConfiguration(new LCMConfiguration());
				lcm.getConfiguration().setCreateDate(new Date());
				lcm.setMacAddress("50410023");
				lcm.setLcmTypeEnum(LCMTypeEnum.FLEXNET_LCM);
				lcm.addAlarm(new Alarm(AlarmEnum.BACK_FLOW));

				lcm.setDeviceId(deviceId);
				lcm.getRadio().setCustomerId(CUSTOMER_ID);
				lcm.getRadio().setLocation(new Location(STREET, NEW_YORK, ZIP));

				return lcm;

			case WATER_METER:

				WaterMeter waterMeter = new WaterMeter();
				waterMeter.setStatus(WaterGasMeterStatusEnum.IDLE);
				waterMeter.setWaterMeterTypeEnum(WaterMeterTypeEnum.NA2W_WATER_SHUTOFF);

				if (flexNetId == null)
				{
					waterMeter.setRadio(new Radio(BigInteger.valueOf(FLEXNET_ID)));
				}
				else
				{
					waterMeter.setRadio(new Radio(flexNetId));
				}
				waterMeter.setDeviceModel(new DeviceModel(MANUFACTURE, DEVICE_MODEL));
				waterMeter.setConfiguration(new WaterGasMeterConfiguration());
				waterMeter.getConfiguration().setInstallDate(new Date());
				waterMeter.addAlarm(new Alarm(AlarmEnum.BROWN_OUT));

				waterMeter.setDeviceId(deviceId);
				waterMeter.getRadio().setCustomerId(CUSTOMER_ID);
				waterMeter.getRadio().setLocation(new Location(STREET, NEW_YORK, ZIP));

				return waterMeter;

			case GAS_METER:

				GasMeter gasMeter = new GasMeter();
				gasMeter.setStatus(WaterGasMeterStatusEnum.IDLE);
				gasMeter.setGasMeterTypeEnum(GasMeterTypeEnum.FLEXNET_1WAY_GAS);

				if (flexNetId == null)
				{
					gasMeter.setRadio(new Radio(BigInteger.valueOf(FLEXNET_ID)));
				}
				else
				{
					gasMeter.setRadio(new Radio(flexNetId));
				}
				gasMeter.setDeviceModel(new DeviceModel(MANUFACTURE, DEVICE_MODEL));
				gasMeter.setConfiguration(new WaterGasMeterConfiguration());
				gasMeter.getConfiguration().setInstallDate(new Date());
				gasMeter.addAlarm(new Alarm(AlarmEnum.CORRECTOR));

				gasMeter.setDeviceId(deviceId);
				gasMeter.getRadio().setCustomerId(CUSTOMER_ID);
				gasMeter.getRadio().setLocation(new Location(STREET, NEW_YORK, ZIP));

				return gasMeter;

			default:
				break;
		}

		return null;
	}

	/**
	 * Creates the device request.
	 * 
	 * @return the device request
	 */
	public static DeviceRequest createDeviceRequest()
	{
		return new DeviceRequest(createUserContext(), createTenant());
	}

	/**
	 * Creates the device request.
	 * 
	 * @param device the device
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 * @return the device request
	 */
	public static DeviceRequest createDeviceRequest(Device device, ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		return new DeviceRequest(device, serviceTypeEnum, tenant);
	}

	/**
	 * Creates the device reading request.
	 * 
	 * @return the device reading request
	 */
	public static DeviceReadingRequest createDeviceReadingRequest()
	{
		return new DeviceReadingRequest(createUserContext());
	}

	/**
	 * Creates the device reading request.
	 * 
	 * @param peakDemandOrderByEnum the peak demand order by enum
	 * @return the device reading request
	 */
	public static DeviceReadingRequest createDeviceReadingRequest(PeakDemandOrderByEnum peakDemandOrderByEnum)
	{
		DeviceReadingRequest request = new DeviceReadingRequest(createUserContext());
		request.addSortExpressions(new SortExpression(peakDemandOrderByEnum.getValue(),
				Direction.Ascending));
		return request;
	}

	/**
	 * Creates the device reading request.
	 * 
	 * @param serviceTypeEnum the service type enum
	 * @return the device reading request
	 */
	public static DeviceReadingRequest createDeviceReadingRequest(ServiceTypeEnum serviceTypeEnum)
	{
		return new DeviceReadingRequest(createUserContext(), serviceTypeEnum);
	}

	/**
	 * Creates the inquiry device request.
	 * 
	 * @return the inquiry device request
	 */
	public static InquiryDeviceRequest createInquiryDeviceRequest()
	{
		InquiryDeviceRequest request = new InquiryDeviceRequest(createUserContextWithLocale());
		request.setTenant(createTenant());
		return request;
	}

	/**
	 * Creates the inquiry device request with pagination.
	 * 
	 * @return the inquiry device request
	 */
	public static InquiryDeviceRequest createInquiryDeviceRequestWithPagination()
	{
		InquiryDeviceRequest request = createInquiryDeviceRequest();
		request.setPageSize(PAGE_SIZE);
		request.setStartRow(0);

		return request;
	}

	/**
	 * Createlifecycle state.
	 * 
	 * @return the lifecycle state
	 */
	public static LifecycleState createlifecycleState()
	{
		return new LifecycleState(LIFECYCLE_STATE, LIFECYCLE_STATE);
	}

	/**
	 * Creates the sort expression.
	 * 
	 * @return the sort expression
	 */
	public static SortExpression createSortExpression()
	{
		return createSortExpression(DeviceColumnEnum.DESCRIPTION.getValue());
	}

	/**
	 * Creates the sort expression.
	 * 
	 * @param field the field
	 * @return the sort expression
	 */
	public static SortExpression createSortExpression(String field)
	{
		SortExpression sortExpression =
				new SortExpression(field, Direction.Ascending);
		return sortExpression;
	}

	/**
	 * Creates the inquiry request.
	 * 
	 * @param request the request
	 */
	public static void createInquiryRequest(DeviceManagerInquiryRequest request)
	{
		request.addSortExpressions(createSortExpression());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(createTenant());
		request.setPageSize(FIFTEEN);
		request.setStartRow(0);
	}

	/**
	 * Creates the invalid page size start row.
	 * 
	 * @param request the request
	 */
	public static void createInvalidPageSizeStartRow(DeviceManagerInquiryRequest request)
	{
		request.setPageSize(TWO_HUNDRED);
		request.setStartRow(null);
	}

	/**
	 * Creates the inquiry tag request.
	 * 
	 * @return the inquiry tag request
	 */
	public static InquiryTagRequest createInquiryTagRequest()
	{
		InquiryTagRequest inquiryTagRequest = new InquiryTagRequest(createUserContext());
		createInquiryRequest(inquiryTagRequest);
		return inquiryTagRequest;
	}

	/**
	 * Creates the tag.
	 * 
	 * @return the tag
	 */
	public static Tag createTag()
	{
		String name = StringUtils.repeat(LETTER_N, NUMBER_TO_REPEAT_100);
		String description = StringUtils.repeat(LETTER_D, NUMBER_TO_REPEAT_150);

		// create the tag
		Tag tag = new Tag(1, name, description);

		return tag;
	}

	/**
	 * Creates the tag request.
	 * 
	 * @return the tag request
	 */
	public static TagRequest createTagRequest()
	{
		return new TagRequest(createTag());
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
	 * Creates the device.
	 * 
	 * @return the device
	 */
	public static Device createDevice()
	{
		return new Device(new Radio(BigInteger.valueOf(FLEXNET_ID)));
	}

	/**
	 * Creates the device model.
	 * 
	 * @return the device model
	 */
	public static DeviceModel createDeviceModel()
	{
		return new DeviceModel();
	}

	/**
	 * Creates the alarms types count.
	 * 
	 * @return the list
	 */
	public static List<AlarmsTypesCount> createAlarmsTypesCount()
	{
		List<AlarmsTypesCount> alarmsTypesCountList = new ArrayList<AlarmsTypesCount>();
		alarmsTypesCountList.add(new AlarmsTypesCount(AlarmEnum.BACK_FLOW, RANDOM.nextInt(NUMBER_RANGE)));
		alarmsTypesCountList.add(new AlarmsTypesCount(AlarmEnum.BROKEN_PIPE, RANDOM.nextInt(NUMBER_RANGE)));
		alarmsTypesCountList.add(new AlarmsTypesCount(AlarmEnum.CUT_WIRE, RANDOM.nextInt(NUMBER_RANGE)));
		alarmsTypesCountList.add(new AlarmsTypesCount(AlarmEnum.LEAK_DETECTED, RANDOM.nextInt(NUMBER_RANGE)));
		alarmsTypesCountList.add(new AlarmsTypesCount(AlarmEnum.RELAY_CURRENT_TAMPER, RANDOM.nextInt(NUMBER_RANGE)));

		return alarmsTypesCountList;
	}

	/**
	 * Creates the note.
	 * 
	 * @return the note
	 */
	public static Note createNote()
	{
		Integer id = RANDOM.nextInt(NUMBER_RANGE);

		Note note = new Note(new BigInteger(id.toString()), "QAT - Example Note text " + id);
		note.setCreateDate(new Date());
		note.setCreateUser(USER);

		return note;
	}

	/**
	 * Creates the note request.
	 * 
	 * @return the note request
	 */
	public static NoteRequest createNoteRequest()
	{
		return new NoteRequest(createUserContext());
	}

	/**
	 * Creates the property list.
	 * 
	 * @return the list
	 */
	public static List<Property> createPropertyList()
	{
		return createPropertyList(PropertyEnum.LANGUAGE);
	}

	/**
	 * Creates the property list.
	 * 
	 * @param propertyEnum the property enum
	 * @return the list
	 */
	public static List<Property> createPropertyList(PropertyEnum propertyEnum)
	{
		Property property = new Property(USER_PROVIDER, USER, propertyEnum.getValue(), LOCALE, null, null);

		List<Property> properties = new ArrayList<Property>();
		properties.add(property);

		return properties;
	}

	/**
	 * Creates the property request.
	 * 
	 * @return the property request
	 */
	public static PropertyRequest createPropertyRequest()
	{
		return new PropertyRequest(createUserContext(), createTenant());
	}

	/**
	 * Creates the inquiry process request.
	 * 
	 * @return the inquiry process request
	 */
	public static InquiryProcessRequest createInquiryProcessRequest()
	{
		InquiryProcessRequest inquiryProcessRequest = new InquiryProcessRequest(createUserContextWithLocale());
		createInquiryRequest(inquiryProcessRequest);
		return inquiryProcessRequest;
	}

	/**
	 * Creates the inquiry process request.
	 * 
	 * @param sortExpression the sort expression
	 * @return the inquiry process request
	 */
	public static InquiryProcessRequest createInquiryProcessRequest(DeviceColumnEnum sortExpression)
	{
		InquiryProcessRequest inquiryProcessRequest = createInquiryProcessRequest();
		inquiryProcessRequest.getSortExpressions().clear();
		inquiryProcessRequest.getSortExpressions().add(
				new SortExpression(sortExpression.getValue(), Direction.Ascending));

		inquiryProcessRequest.setTenant(createTenant());
		return inquiryProcessRequest;
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
	 * Creates the process request.
	 * 
	 * @return the process request
	 */
	public static ProcessRequest createProcessRequestWithTenant()
	{
		ProcessRequest processRequest = new ProcessRequest(createUserContext());
		processRequest.setTenant(createTenant());
		return processRequest;
	}

	/**
	 * Creates the process request.
	 * 
	 * @param processStatusEnum the process status enum
	 * @param isDashboardMonitored the is dashboard monitored
	 * @param isProcessComplete the is process complete
	 * @param isMonitoredInstance the is monitored instance
	 * @param processType the process type
	 * @return the process request
	 */
	public static ProcessRequest createProcessRequest(ProcessStatusEnum processStatusEnum,
			Boolean isDashboardMonitored, Boolean isProcessComplete, Boolean isMonitoredInstance,
			ProcessType processType)
	{
		return createProcessRequest(processStatusEnum,
				isDashboardMonitored, isProcessComplete, isMonitoredInstance,
				processType, ServiceTypeEnum.ELECTRIC);
	}

	/**
	 * Creates the process request.
	 * 
	 * @param processStatusEnum the process status enum
	 * @param isDashboardMonitored the is dashboard monitored
	 * @param isProcessComplete the is process complete
	 * @param isMonitoredInstance the is monitored instance
	 * @param processType the process type
	 * @param serviceTypeEnum the service type enum
	 * @return the process request
	 */
	public static ProcessRequest createProcessRequest(ProcessStatusEnum processStatusEnum,
			Boolean isDashboardMonitored, Boolean isProcessComplete, Boolean isMonitoredInstance,
			ProcessType processType, ServiceTypeEnum serviceTypeEnum)
	{
		ProcessRequest request = TestBaseUtil.createProcessRequest();

		DMProcess process = new DMProcess();
		process.setStartTime(new Date());
		process.setEndTime(new Date());

		process.setProcessStatusEnum(processStatusEnum);
		process.setIsDashboardMonitored(isDashboardMonitored);
		process.setIsProcessComplete(isProcessComplete);
		process.setIsMonitoredInstance(isMonitoredInstance);
		process.setProcessType(processType);
		request.addProcessAsFirstElement(process);
		request.setServiceTypeEnum(serviceTypeEnum);

		request.setTenant(TestBaseUtil.createTenant());

		return request;
	}

	/**
	 * Gets the date.
	 * 
	 * @param hoursToAdd the hours to add
	 * @return the date
	 */
	public static Date getDate(Integer hoursToAdd)
	{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR_OF_DAY, hoursToAdd);
		return c.getTime();
	}

	/**
	 * Creates the process.
	 * 
	 * @return the ePM process
	 */
	public static DMProcess createProcess()
	{
		return createProcess(ProcessStatusEnum.IN_PROCESS, null);
	}

	/**
	 * Creates the process.
	 * 
	 * @param processStatusEnum the process status enum
	 * @param action the action
	 * @return the ePM process
	 */
	public static DMProcess createProcess(ProcessStatusEnum processStatusEnum, DMAction action)
	{
		DMProcess process = new DMProcess(1, processStatusEnum);
		process.setDescription("Description 01");
		process.setStartTime(getDate(1));
		process.setIsMonitoredInstance(true);
		process.setIsDashboardMonitored(true);
		process.setProcessType(new ProcessType(1, "Create group"));
		process.setAction(action);
		process.setRniEventId(1);

		return process;
	}

	/**
	 * Creates the process item.
	 * 
	 * @param processItemStatusEnum the process item status enum
	 * @param message the message
	 * @param deviceTypeEnum the device type enum
	 * @return the process item
	 */
	public static ProcessItem createProcessItem(ProcessItemStatusEnum processItemStatusEnum, String message,
			DeviceTypeEnum deviceTypeEnum)
	{
		ProcessItem processItem = new ProcessItem(createDevice(deviceTypeEnum), processItemStatusEnum, message);
		processItem.setId(ONE);
		return processItem;
	}

	/**
	 * Creates the process item.
	 * 
	 * @param processItemStatusEnum the process item status enum
	 * @param message the message
	 * @param count the count
	 * @return the list
	 */
	public static List<ProcessItem> createProcessItem(ProcessItemStatusEnum processItemStatusEnum,
			String message, int count)
	{
		List<ProcessItem> processItemList = new ArrayList<ProcessItem>();

		List<Device> deviceList = createDeviceList(DeviceTypeEnum.HAN_DEVICE, count);

		for (Device device : deviceList)
		{
			processItemList.add(new ProcessItem(device, processItemStatusEnum, message));
		}

		return processItemList;
	}

	/**
	 * Creates the process category.
	 * 
	 * @return the list
	 */
	public static List<ProcessCategory> createProcessCategory()
	{
		List<ProcessCategory> processCategoryList = new ArrayList<ProcessCategory>();

		processCategoryList.add(new ProcessCategory(1, SENSUS_EPM_PROCESS_CATEGORY_GROUP));
		processCategoryList.add(new ProcessCategory(2, SENSUS_EPM_PROCESS_CATEGORY_TAG));

		return processCategoryList;
	}

	/**
	 * Creates the inquiry schedule request.
	 * 
	 * @return the inquiry schedule request
	 */
	public static InquiryScheduleRequest createInquiryScheduleRequest()
	{
		InquiryScheduleRequest inquiryScheduleRequest = new InquiryScheduleRequest();
		inquiryScheduleRequest.setUserContext(TestBaseUtil
				.createUserContext());

		inquiryScheduleRequest.getSortExpressions().add(new SortExpression(NAME, Direction.Ascending));

		inquiryScheduleRequest.setProcessId(FIFTEEN);

		inquiryScheduleRequest.getUserContext().setLocaleString(LOCALE);

		inquiryScheduleRequest.setFileName(FILE_NAME);

		return inquiryScheduleRequest;
	}

	/**
	 * Creates the schedule request.
	 * 
	 * @return the schedule request
	 */
	public static ScheduleRequest createScheduleRequest()
	{
		ScheduleRequest scheduleRequest = new ScheduleRequest();
		scheduleRequest.setUserContext(TestBaseUtil
				.createUserContext());

		scheduleRequest.setFileName(FILE_NAME);
		scheduleRequest.getUserContext().setLocaleString(LOCALE);
		scheduleRequest.setProcessId(FIFTEEN);

		// set the schedule and name for the request
		DMSchedule schedule = new DMSchedule(1);

		schedule.setName(NAME);
		schedule.setStatusEnum(ScheduleStatusEnum.ENABLED);

		// set the action and id
		schedule.setDmAction(new DemandResponseEventAction(1, true, true, new Date()));
		schedule.setStartTime(new Date());
		schedule.getDmAction().setActionType(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT));

		// set the group list and id
		schedule.getDmAction().addGroup(new Group(1));

		// set the device list and id
		schedule.getDmAction().addDevice(createDevice());

		// set the frequency
		schedule.setFrequency(new Frequency());
		schedule.getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.NEVER);

		// add the schedule to the request
		scheduleRequest.setSchedule(schedule);

		((DemandResponseEventAction)scheduleRequest.getSchedule().getDmAction()).setEnrollmentCode(ENROLLMENT_CODE);
		((DemandResponseEventAction)scheduleRequest.getSchedule().getDmAction()).setDemandResponseDuration(1);

		return scheduleRequest;
	}

	/**
	 * Creates the action.
	 * 
	 * @param actionTypeEnum the action type enum
	 * @return the ePM action
	 */
	public static DMAction createAction(ActionTypeEnum actionTypeEnum)
	{
		DMAction action = new DMAction(new ActionType(actionTypeEnum));
		return action;
	}

	/**
	 * Creates the action request.
	 * 
	 * @return the action request
	 */
	public static ActionRequest createActionRequest()
	{
		return new ActionRequest(createUserContext());
	}

	/**
	 * Creates the tou read.
	 * 
	 * @return the string[][]
	 */
	public static String[][] createTOURead()
	{
		return new String[][] { {"Tier Id", "Kwh", "KVar"}, {"Tier 1", "100", "101"}, {"Tier 2", "200", "202"}};
	}

	/**
	 * Creates the interval read.
	 * 
	 * @param serviceType the service type
	 * @return the interval read
	 */
	public static IntervalRead createIntervalRead(ServiceTypeEnum serviceType)
	{

		HashMap<String, String> channelMap = new HashMap<String, String>();

		if (serviceType.equals(ServiceTypeEnum.ELECTRIC))
		{
			channelMap.put(CHANNEL_NAME_FORWARD, STR_CHANNEL_NAME_1_427);
			channelMap.put(CHANNEL_NAME_REVERSE, ZERO_READING);
			channelMap.put("Forward, Summation, Kwh", "12.427");
			channelMap.put("Reverse, Summation, Kwh", ZERO_READING);
		}
		else if (serviceType.equals(ServiceTypeEnum.WATER))
		{
			channelMap.put("Read in Gallons", STR_CHANNEL_NAME_1_427);
		}

		IntervalRead ir = new IntervalRead();
		ir.setChannels(new ArrayList<HashMap<String, String>>());
		ir.getChannels().add(channelMap);
		ir.setChannelSize(channelMap.size());

		return ir;
	}

	/**
	 * Creates the snap shot.
	 * 
	 * @return the interval read
	 */
	public static IntervalRead createSnapshot()
	{

		HashMap<String, String> channelMap = new HashMap<String, String>();
		channelMap.put(CHANNEL_NAME_FORWARD, "0.001");
		channelMap.put(CHANNEL_NAME_REVERSE, "0.002");
		channelMap.put("Reverse/Forward, Interval, Kwh", "0.003");

		IntervalRead ir = new IntervalRead();
		ir.setChannels(new ArrayList<HashMap<String, String>>());
		ir.getChannels().add(channelMap);
		ir.setChannelSize(channelMap.size());

		return ir;
	}

	/**
	 * Creates the electric load profile.
	 * 
	 * @return the load profile
	 */
	public static LoadProfile createElectricLoadProfile()
	{
		LoadProfile profile = new LoadProfile();
		profile.setCreateDate(new Date());
		profile.setCurrentReading("10.000 Kwh");
		profile.setConsumptionPeakDemand("100.000 Kwh");
		profile.setCurrentMonthConsumption("1.000 Kwh");
		profile.setCurrentHourConsumption("0.001 Kwh");
		return profile;
	}

	/**
	 * Creates the water load profile.
	 * 
	 * @return the load profile
	 */
	public static LoadProfile createWaterLoadProfile()
	{
		LoadProfile profile = new LoadProfile();
		profile.setLastReadTime(new Date());
		profile.setLastReadValue("1.000 US Gallons");
		return profile;
	}

	/**
	 * Creates the column filter request.
	 * 
	 * @return the column filter request
	 */
	public static ColumnFilterRequest createColumnFilterRequest()
	{
		return new ColumnFilterRequest(createUserContextWithLocale());
	}

	/**
	 * Creates the group.
	 * 
	 * @return the group
	 */
	public static Group createGroup()
	{
		String name = StringUtils.repeat(LETTER_N, NUMBER_TO_REPEAT_100);
		String description = StringUtils.repeat(LETTER_D, NUMBER_TO_REPEAT_150);

		// create the group
		return new Group(1, name, description);
	}

	/**
	 * Insert schedule.
	 * 
	 * @param startTime the start time
	 * @param actionTypeName the action type name
	 * @param frequencyType the frequency type
	 * @return the schedule
	 */
	public static DMSchedule createSchedule(Date startTime, String actionTypeName, FrequencyTypeEnum frequencyType)
	{
		DMSchedule schedule = new DMSchedule(SCHEDULE_NAME, ScheduleStatusEnum.ENABLED);
		schedule.setDmAction(createAction(actionTypeName));
		schedule.setDescription(SCHEDULE_DESCRIPTION);
		schedule.setCreateDate(new Date());
		schedule.setStartTime(startTime);
		schedule.setFrequency(createFrequency(startTime, frequencyType));

		return schedule;
	}

	/**
	 * Creates the geocode device info.
	 * 
	 * @return the geocode device info
	 */
	public static GeocodeDeviceInfo createGeocodeDeviceInfo()
	{
		GeocodeDeviceInfo geocodeDeviceInfo = new GeocodeDeviceInfo();
		geocodeDeviceInfo.setBottomLeftLat(BOTTOM_LEFT_LAT);
		geocodeDeviceInfo.setBottomLeftLon(BOTTOM_LEFT_LON);
		geocodeDeviceInfo.setTopRightLat(TOP_RIGHT_LAT);
		geocodeDeviceInfo.setTopRightLon(TOP_RIGHT_LON);
		return geocodeDeviceInfo;
	}

	/**
	 * Creates the frequency.
	 * 
	 * @param startOnDate the start on date
	 * @param frequencyType the frequency type
	 * @return the frequency
	 */
	private static Frequency createFrequency(Date startOnDate, FrequencyTypeEnum frequencyType)
	{
		Frequency frequency = new Frequency();
		switch (frequencyType)
		{
			case NEVER:
				frequency.setFrequencyEnumValue(frequencyType.getValue());
				frequency.setNextExecution(startOnDate);
				break;
			case DAILY:
			case YEARLY:
				frequency.setFrequencyEnumValue(frequencyType.getValue());
				frequency.setStartOnDate(startOnDate);
				frequency.setNeverEnds(Boolean.TRUE);
				frequency.setNextExecution(startOnDate);
				frequency.setTimeToRepeat(1);
				break;
			case EVERY_WEEKDAY:
			case EVERY_MON_WED_FRI:
			case EVERY_TUE_THURS:
				frequency.setFrequencyEnumValue(frequencyType.getValue());
				frequency.setStartOnDate(startOnDate);
				frequency.setNeverEnds(Boolean.TRUE);
				frequency.setNextExecution(startOnDate);
				frequency.setDaysOfWeeks(new ArrayList<Integer>());
				frequency.getDaysOfWeeks().add(DaysOfWeekEnum.FRIDAY);
				break;
			case WEEKLY:
				frequency.setFrequencyEnumValue(frequencyType.getValue());
				frequency.setStartOnDate(startOnDate);
				frequency.setNeverEnds(Boolean.TRUE);
				frequency.setTimeToRepeat(1);
				frequency.setDayOfWeek(Boolean.TRUE);
				frequency.setDayOfMonth(Boolean.FALSE);
				frequency.setDaysOfWeeks(new ArrayList<Integer>());
				frequency.getDaysOfWeeks().add(DaysOfWeekEnum.FRIDAY);
				frequency.setNextExecution(startOnDate);
				break;
			case MONTHLY:
				frequency.setFrequencyEnumValue(frequencyType.getValue());
				frequency.setStartOnDate(startOnDate);
				frequency.setNeverEnds(Boolean.TRUE);
				frequency.setTimeToRepeat(1);
				frequency.setDayOfWeek(Boolean.TRUE);
				frequency.setDayOfMonth(Boolean.FALSE);
				frequency.setNextExecution(startOnDate);
				break;
			default:
				break;
		}

		return frequency;
	}

	/**
	 * Creates the tenant.
	 * 
	 * @return an empty dM tenant
	 */
	public static DMTenant createEmptyTenant()
	{
		return new DMTenant();
	}

	/**
	 * Creates the tenant.
	 * 
	 * @return the dM tenant
	 */
	public static DMTenant createTenant()
	{
		return new DMTenant(CUSTOMER_ID);
	}

	/**
	 * Creates the tenant.
	 * 
	 * @param customerId the customer id
	 * @return the dM tenant
	 */
	public static DMTenant createTenant(String customerId)
	{
		return new DMTenant(customerId);
	}

	/**
	 * Creates the lcm relay.
	 * 
	 * @return the list
	 */
	public static List<LCMRelay> createLCMRelay()
	{
		List<LCMRelay> lcmRelayList = new ArrayList<LCMRelay>();
		lcmRelayList.add(new LCMRelay(1, 1, DeviceClassEnum.HVAC_COMPRESSOR));
		lcmRelayList.add(new LCMRelay(2, 1, DeviceClassEnum.HVAC_COMPRESSOR));
		lcmRelayList.add(new LCMRelay(THREE, THIRTY, DeviceClassEnum.WATER_HEATER));

		return lcmRelayList;
	}

	/**
	 * Creates the default device reading request.
	 * 
	 * @param device the device
	 * @param request the request
	 */
	public static void createDefaultDeviceReadingRequest(Device device, DeviceReadingRequest request)
	{
		request.setFileName(FILE_NAME);
		request.setProcessId(1);
		request.setInitialDate(new Date());
		request.setEndDate(new Date());
		request.setDevice(device);
		request.setDateFormat(FORMATTED_DATE);
		request.setTimeZone(TIME_ZONE_N3);
	}

	/**
	 * Creates the time zone info.
	 * 
	 * @return the time zone info
	 */
	public static TimeZoneInfo createTimeZoneInfo()
	{
		TimeZoneInfo timeZoneInfo = new TimeZoneInfo();
		timeZoneInfo.setDisplayName(TIME_ZONE_AMERICA_SAO_PAULO);
		timeZoneInfo.setDisplayMinutes(DISPLAY_MINUTE_240);

		return timeZoneInfo;

	}

	/**
	 * Creates the frequency.
	 * 
	 * @return the frequency
	 */
	public static Frequency createFrequency()
	{
		Frequency frequency = new Frequency();
		frequency.setFrequencyTypeEnum(FrequencyTypeEnum.DAILY);
		frequency.setStartOnDate(new Date());
		frequency.setNeverEnds(Boolean.TRUE);
		frequency.setTimeToRepeat(ONE);
		return frequency;
	}

	/**
	 * Creates the water gas meter status count.
	 * 
	 * @return the water gas meter status count
	 */
	public static WaterGasMeterStatusCount createWaterGasMeterStatusCount()
	{
		WaterGasMeterStatusCount waterGasMeterStatusCount = new WaterGasMeterStatusCount();
		waterGasMeterStatusCount.setWaterGasMeterStatusEnum(WaterGasMeterStatusEnum.FIXED_BASE_LAT);
		waterGasMeterStatusCount.setAmount(TWO);

		return waterGasMeterStatusCount;
	}

	/**
	 * Creates the action.
	 * 
	 * @param actionTypeName the action type name
	 * @return the ePM action
	 */
	private static DMAction createAction(String actionTypeName)
	{
		DMAction dmAction = null;

		if (ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName().equals(actionTypeName))
		{
			dmAction = new DemandResetEventAction(Boolean.FALSE, Boolean.TRUE, new Date());
		}
		else if (ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT.getActionTypeName().equals(actionTypeName))
		{
			dmAction = new DemandResponseEventAction(Boolean.FALSE, Boolean.TRUE, new Date());
			((DemandResponseEventAction)dmAction).setEnrollmentCode(ZERO);
			((DemandResponseEventAction)dmAction).setDemandResponseDuration(ONE);
		}
		else if (ActionTypeEnum.SEND_HAN_TEXT_MESSAGE.getActionTypeName().equals(actionTypeName))
		{
			dmAction = new SendHanTextMessageAction(Boolean.FALSE, Boolean.TRUE, new Date());
			((SendHanTextMessageAction)dmAction).setTextMessage(SEND_TEXT_HAN);
			((SendHanTextMessageAction)dmAction).setDurationHANTextMessage(ONE);
		}
		else if (ActionTypeEnum.REMOTE_DISCONNECT.getActionTypeName().equals(actionTypeName))
		{
			dmAction = new RemoteDisconnectAction(Boolean.FALSE, Boolean.TRUE, new Date());
		}
		else if (ActionTypeEnum.REMOTE_CONNECT.getActionTypeName().equals(actionTypeName))
		{
			dmAction = new RemoteConnectAction(Boolean.FALSE, Boolean.TRUE, new Date());
		}
		else if (ActionTypeEnum.REMOTE_ARM_CONNECT.getActionTypeName().equals(actionTypeName))
		{
			dmAction = new RemoteArmConnectAction(Boolean.FALSE, Boolean.TRUE, new Date());
		}

		return dmAction;
	}
}
