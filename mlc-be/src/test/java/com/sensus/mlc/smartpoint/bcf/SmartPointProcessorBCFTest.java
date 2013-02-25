package com.sensus.mlc.smartpoint.bcf;

import static com.sensus.mlc.base.TestBaseUtil.createChannelSetupAuditRequest;
import static com.sensus.mlc.base.TestBaseUtil.createForcedLightStatusNotificationRequest;
import static com.sensus.mlc.base.TestBaseUtil.createLight;
import static com.sensus.mlc.base.TestBaseUtil.createLightRequest;
import static com.sensus.mlc.base.TestBaseUtil.createLightStatusNotificationRequest;
import static com.sensus.mlc.base.TestBaseUtil.createProcessLightsRequest;
import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightBlinkEnum;
import com.sensus.mlc.smartpoint.model.LightDetailDataTypeEnum;
import com.sensus.mlc.smartpoint.model.LightIntensityEnum;
import com.sensus.mlc.smartpoint.model.LightLocation;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.LightSchedule;
import com.sensus.mlc.smartpoint.model.LightStateEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.OperationalData;
import com.sensus.mlc.smartpoint.model.OperationalDataTypeEnum;
import com.sensus.mlc.smartpoint.model.OverrideEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.StatusException;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ChannelSetupAuditRequest;
import com.sensus.mlc.smartpoint.model.request.ForcedLightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightSetupNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ProcessLightsRequest;
import com.sensus.mlc.smartpoint.model.response.AlarmNotificationResponse;

/**
 * The Class SmartPointBCFTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/smartpoint/smartpointbcfimpltest.xml"})
public class SmartPointProcessorBCFTest extends AbstractTestBaseBusiness
{

	/** The Constant TWO_HUNDRED_DOUBLE. */
	private static final double TWO_HUNDRED_DOUBLE = 200D;

	/** The Constant ONE_HUNDRED_DOUBLE. */
	private static final double ONE_HUNDRED_DOUBLE = 100D;

	/** The Constant TWO_HUNDRED_ONE_DOUBLE. */
	private static final double TWO_HUNDRED_ONE_DOUBLE = 201D;

	/** The Constant ONE_HUNDRED_ONE_DOUBLE. */
	private static final double ONE_HUNDRED_ONE_DOUBLE = 101D;

	/** The Constant ARBITRARY_PERCENTAGE_25. */
	private static final Integer ARBITRARY_PERCENTAGE_25 = 25;

	/** The Constant ARBITRARY_OPERATIONAL_DATA_VALUE_30. */
	private static final Integer ARBITRARY_OPERATIONAL_DATA_VALUE_30 = 30;

	/** The Constant ARBITRARY_OPERATIONAL_DATA_VALUE_120. */
	private static final Integer ARBITRARY_OPERATIONAL_DATA_VALUE_120 = 120;

	/** The Constant ARBITRARY_OPERATIONAL_DATA_VALUE_200. */
	private static final Integer ARBITRARY_OPERATIONAL_DATA_VALUE_200 = 200;

	/** The Constant ONE. */
	private static final String ONE = "1";

	/** The Constant PROCESS_CHANNEL_SETUP_AUDIT_NOTIFICATION. */
	private static final String PROCESS_CHANNEL_SETUP_AUDIT_NOTIFICATION = "processChannelSetupAuditNotification";

	/** The Constant INITIATE_UPDATE_LIGHT_STATUS. */
	private static final String INITIATE_UPDATE_LIGHT_STATUS = "initiateUpdateLightStatus";

	/** The Constant PROCESS_CLEAR_ALARM_NOTIFICATION. */
	private static final String PROCESS_CLEAR_ALARM_NOTIFICATION = "processClearAlarmNotification";

	/** The Constant INITIATE_UPSERT_LIGHT_PROPERTY. */
	private static final String INITIATE_UPSERT_LIGHT_PROPERTY = "initiateUpsertLightProperty";

	/** The Constant PROCESS_SET_LIGHT_INTENSITY_NOTIFICATION. */
	private static final String PROCESS_SET_LIGHT_INTENSITY_NOTIFICATION = "processSetLightIntensityNotification";

	/** The Constant FLEX_NET_ID. */
	private static final String FLEX_NET_ID = "FlexNet ID";

	/** The Constant PROCESS_APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION. */
	private static final String PROCESS_APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION =
			"processApplySmartpointPropertiesNotification";
	// The constant values that correspond to properties
	/** The Constant SUNRISE_TIME. */
	private static final Integer SUNRISE_TIME = 1;

	/** The Constant SUNRISE_OFFSET. */
	private static final Integer SUNRISE_OFFSET = 2;

	/** The Constant SUNSET_TIME. */
	private static final Integer SUNSET_TIME = 3;

	/** The Constant SUNSET_OFFSET. */
	private static final Integer SUNSET_OFFSET = 4;

	/** The Constant POLE_ID. */
	private static final Integer POLE_ID = 12;

	/** The Constant FIRMWARE_VERSION. */
	private static final Integer FIRMWARE_VERSION = 15;

	/** The Constant LATITUDE. */
	private static final Integer LATITUDE = 16;

	/** The Constant LONGITUDE. */
	private static final Integer LONGITUDE = 17;

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sensus.mlc.smartpointbcfimpl.defaultexception";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED =
			"sensus.mlc.smartpointvalidator.id.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED =
			"sensus.mlc.smartpointvalidator.lightrniid.required";
	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LATITUDE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LATITUDE_REQUIRED =
			"sensus.mlc.smartpointvalidator.latitude.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LONGITUDE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LONGITUDE_REQUIRED =
			"sensus.mlc.smartpointvalidator.longitude.required";

	/** The Constant SENSUS_MLC_VALIDATOR_POLEID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_POLEID_REQUIRED =
			"sensus.mlc.validator.poleid.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_FIRMWARE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_FIRMWARE_REQUIRED =
			"sensus.mlc.smartpointvalidator.firmware.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_TIME_ZONE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_TIME_ZONE_REQUIRED =
			"sensus.mlc.smartpointvalidator.time.zone.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED =
			"sensus.mlc.smartpointvalidator.light.top.level.state.required";

	/** The Constant SENSUS_MLC_SMARTPOINT_EXPIRE_PER_DATE_INVALID. */
	private static final String SENSUS_MLC_SMARTPOINT_EXPIRE_PER_DATE_INVALID =
			"sensus.mlc.smartpointvalidator.expire.per.date.invalid";

	/** The Constant SENSUS_MLC_SMARTPOINT_BLINK_LEVEL_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINT_BLINK_LEVEL_REQUIRED =
			"sensus.mlc.smartpointvalidator.blink.level.required";

	/** The Constant SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED =
			"sensus.mlc.validator.property.required";

	/** The Constant SENSUS_MLC_GATEWAY_ALARM_WARNING_SUBTYPE_REQUIRED. */
	private static final String SENSUS_MLC_GATEWAY_ALARM_WARNING_SUBTYPE_REQUIRED =
			"sensus.mlc.gateway.alarm.warning.subtype.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_STATE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_STATE_REQUIRED =
			"sensus.mlc.smartpointvalidator.light.state.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_PROPERTIES_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_PROPERTIES_REQUIRED =
			"sensus.mlc.smartpointvalidator.properties.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LATITUDE. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LATITUDE =
			"sensus.mlc.smartpointvalidator.invalid.latitude";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LONGITUDE. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LONGITUDE =
			"sensus.mlc.smartpointvalidator.invalid.longitude";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_SUNRISE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_SUNRISE_REQUIRED =
			"sensus.mlc.smartpointvalidator.sunrise.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_SUNSET_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_SUNSET_REQUIRED =
			"sensus.mlc.smartpointvalidator.sunset.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_SUNRISEOFFSET_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_SUNRISEOFFSET_REQUIRED =
			"sensus.mlc.smartpointvalidator.sunrise.offset.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_SUNSETOFFSET_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_SUNSETOFFSET_REQUIRED =
			"sensus.mlc.smartpointvalidator.sunset.offset.required";

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/** The Constant SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED =
			"sensus.mlc.validator.selection.pagination.id.required";

	/** The Constant SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED =
			"sensus.mlc.validator.is.monitored.required";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHTLIST_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHTLIST_REQUIRED =
			"sensus.mlc.smartpointvalidator.lightlist.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED =
			"sensus.mlc.smartpointvalidator.intensity.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED =
			"sensus.mlc.smartpointvalidator.schedule.properties.required";

	/** The smart point processor bcf. */
	private ISmartPointProcessorBCF smartPointProcessorBCF;

	/**
	 * Gets the smart point processor bcf.
	 * 
	 * @return the smart point processor bcf
	 */
	public ISmartPointProcessorBCF getSmartPointProcessorBCF()
	{
		return this.smartPointProcessorBCF;
	}

	/**
	 * Sets the smart point processor bcf.
	 * 
	 * @param smartPointProcessorBCF the new smart point processor bcf
	 */
	@Resource(name = "smartPointProcessorBCFTarget")
	public void setSmartPointProcessorBCF(ISmartPointProcessorBCF smartPointProcessorBCF)
	{
		this.smartPointProcessorBCF = smartPointProcessorBCF;
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setSmartPointArea();
	}

	/**
	 * Sets the smart point area.
	 */
	public void setSmartPointArea()
	{
		setArea(getSmartPointProcessorBCF(), LCAreaEnum.SMARTPOINT);
	}

	/**
	 * Removes the smart point area.
	 */
	@After
	public void removeSmartPointArea()
	{
		setArea(getSmartPointProcessorBCF(), LCAreaEnum.DEFAULT);
	}

	/**
	 * Reset mocks to smart point area.
	 */
	@After
	public void resetMocksToSmartPointArea()
	{
		resetMocksData(getSmartPointProcessorBCF());
		setSmartPointArea();
	}

	/**
	 * Test initiate light status.
	 */
	@Test
	public void testInitiateLightStatus()
	{
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);

		// Success situation
		LightRequest request = createLightRequest();
		request.setSelectionPaginationIds(ids);
		request.setIsMonitored(true);
		ProcessResponse response = getSmartPointProcessorBCF().initiateGetLightStatus(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		request = createLightRequest();
		request.setSelectionPaginationIds(ids);
		request.setIsMonitored(true);
		response = getSmartPointProcessorBCF().initiateGetLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - No ids
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		request = createLightRequest();
		request.setIsMonitored(true);
		response = getSmartPointProcessorBCF().initiateGetLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED);

		// Error situation - No isMonitored attribute
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		request = createLightRequest();
		request.setSelectionPaginationIds(ids);
		response = getSmartPointProcessorBCF().initiateGetLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.EXCEPTION, ISmartPointProcessorBCL.class);
		request = createLightRequest();
		request.setSelectionPaginationIds(ids);
		request.setIsMonitored(true);
		response = getSmartPointProcessorBCF().initiateGetLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test process alarm warning notification.
	 */
	@Test
	public void testProcessAlarmWarningNotification()
	{
		// Validation situation - Light Top Level State Required / Light RNI ID Required
		AlarmNotificationRequest request = new AlarmNotificationRequest();
		AlarmNotificationResponse response = getSmartPointProcessorBCF().processAlarmWarningNotification(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Light Top Level State Required / Light RNI ID Required
		request = TestBaseUtil.createAlarmNotificationRequest();
		Light light = TestBaseUtil.createLight();
		light.setRniId(null);
		light.setLightStateEnum(null);
		request.setLight(light);
		StatusException statusException = new StatusException();
		statusException.setId(2);
		statusException.setLabelKey(FLEX_NET_ID);
		statusException.setStatusExceptionTypeEnum(null);
		request.setLightStatusEnum(LightStatusEnum.ACTIVE);
		request.getStatusException().add(statusException);
		response = getSmartPointProcessorBCF().processAlarmWarningNotification(request);
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED,
				SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_STATE_REQUIRED);

		// Validation situation - Light Top Level State Required / Light RNI ID Required
		request = TestBaseUtil.createAlarmNotificationRequest();
		light = TestBaseUtil.createLight();
		request.setLight(light);
		statusException = new StatusException();
		statusException.setId(2);
		statusException.setLabelKey(FLEX_NET_ID);
		statusException.setStatusExceptionTypeEnum(null);
		request.setLightStatusEnum(null);
		request.getStatusException().add(statusException);
		response = getSmartPointProcessorBCF().processAlarmWarningNotification(request);
		assertMessages(response,
				SENSUS_MLC_GATEWAY_ALARM_WARNING_SUBTYPE_REQUIRED,
				SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED);

		// Success situation
		request = TestBaseUtil.createAlarmNotificationRequest();
		light = TestBaseUtil.createLight();
		request.setLight(light);
		request.setLightStatusEnum(LightStatusEnum.ACTIVE);
		response = getSmartPointProcessorBCF().processAlarmWarningNotification(request);
		assertEquals(0, response.getMessageList().size());
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		response = getSmartPointProcessorBCF().processAlarmWarningNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.EXCEPTION, ISmartPointProcessorBCL.class);
		response = getSmartPointProcessorBCF().processAlarmWarningNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test process light status notification.
	 */
	@Test
	public void testProcessLightStatusNotification()
	{
		Light light = new Light();
		light.setRniId(1);
		light.setId(1);

		List<LightParameter> params = new ArrayList<LightParameter>();

		params.add(new LightParameter(SUNRISE_TIME, "10:30"));
		params.add(new LightParameter(SUNSET_TIME, "11:30"));

		List<OperationalData> data = new ArrayList<OperationalData>();

		data.add(new OperationalData(new Float(ARBITRARY_OPERATIONAL_DATA_VALUE_200),
				OperationalDataTypeEnum.CUMULATIVECONSUMPTION));
		data.add(new OperationalData(new Float(ARBITRARY_OPERATIONAL_DATA_VALUE_120), OperationalDataTypeEnum.VOLTAGE));
		data.add(new OperationalData(new Float(ARBITRARY_OPERATIONAL_DATA_VALUE_30), OperationalDataTypeEnum.CURRENT));

		// Success situation
		LightStatusNotificationRequest request = createLightStatusNotificationRequest();
		request.setOperationalData(data);
		request.setLight(light);
		request.setLightParameters(params);
		Response response = getSmartPointProcessorBCF().processLightStatusNotification(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		request = createLightStatusNotificationRequest();
		request.setOperationalData(data);
		request.setLightParameters(params);
		request.setLight(light);
		response = getSmartPointProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - no Light
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		request = createLightStatusNotificationRequest();
		request.setOperationalData(data);
		request.setLightParameters(params);
		response = getSmartPointProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Error situation - no Light Parameters
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		request = createLightStatusNotificationRequest();
		request.setOperationalData(data);
		request.setLight(light);
		request.setLightParameters(null);
		response = getSmartPointProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_PROPERTIES_REQUIRED);

		// Error situation - no operational data
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		request = createLightStatusNotificationRequest();
		request.setLight(light);
		request.setLightParameters(params);
		request.setOperationalData(null);
		response = getSmartPointProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_PROPERTIES_REQUIRED);

		// Exception situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.EXCEPTION, ISmartPointProcessorBCL.class);
		request = createLightStatusNotificationRequest();
		request.setOperationalData(data);
		request.setLightParameters(params);
		request.setLight(light);
		response = getSmartPointProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test process light binding notification.
	 */
	@Test
	public void testProcessLightBindingNotification()
	{
		Light light = new Light();
		light.setRniId(1);
		light.setId(1);

		List<LightParameter> params;

		LightParameter latitude = new LightParameter(LATITUDE, "50");
		LightParameter longitude = new LightParameter(LONGITUDE, "-50");
		LightParameter poleId = new LightParameter(POLE_ID, "12");
		LightParameter firmware = new LightParameter(FIRMWARE_VERSION, "123");
		LightParameter timezone = new LightParameter(PropertyEnum.TIME_ZONE, "1234");

		// Success situation
		LightStatusNotificationRequest request = createLightStatusNotificationRequest();
		params = new ArrayList<LightParameter>();
		params.add(latitude);
		params.add(longitude);
		params.add(poleId);
		params.add(firmware);
		params.add(timezone);
		request.setLight(light);
		request.setLightParameters(params);
		Response response = getSmartPointProcessorBCF().processLightBindingNotification(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		request = createLightStatusNotificationRequest();
		params = new ArrayList<LightParameter>();
		params.add(latitude);
		params.add(longitude);
		params.add(poleId);
		params.add(firmware);
		params.add(timezone);
		request.setLightParameters(params);
		request.setLight(light);
		response = getSmartPointProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Tenant Required
		String rniCode = request.getTenant().getRniCode();
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		request = createLightStatusNotificationRequest();
		params = new ArrayList<LightParameter>();
		params.add(latitude);
		params.add(longitude);
		params.add(poleId);
		params.add(firmware);
		params.add(timezone);
		request.setLightParameters(params);
		request.setLight(light);
		request.getTenant().setRniCode(null);
		response = getSmartPointProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);
		request.getTenant().setRniCode(rniCode);

		// Error situation - no Light
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		request = createLightStatusNotificationRequest();
		params = new ArrayList<LightParameter>();
		params.add(latitude);
		params.add(longitude);
		params.add(poleId);
		params.add(firmware);
		params.add(timezone);
		request.setLightParameters(params);
		response = getSmartPointProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Error situation - no latitude
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		request = createLightStatusNotificationRequest();
		request.setLight(light);
		params = new ArrayList<LightParameter>();
		params.add(longitude);
		params.add(poleId);
		params.add(firmware);
		params.add(timezone);
		request.setLightParameters(params);
		response = getSmartPointProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LATITUDE_REQUIRED);

		// Error situation - no longitude
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		request = createLightStatusNotificationRequest();
		request.setLight(light);
		params = new ArrayList<LightParameter>();
		params.add(latitude);
		params.add(poleId);
		params.add(firmware);
		params.add(timezone);
		request.setLightParameters(params);
		response = getSmartPointProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LONGITUDE_REQUIRED);

		// Error situation - no pole id
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		request = createLightStatusNotificationRequest();
		request.setLight(light);
		params = new ArrayList<LightParameter>();
		params.add(latitude);
		params.add(longitude);
		params.add(firmware);
		params.add(timezone);
		request.setLightParameters(params);
		response = getSmartPointProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_POLEID_REQUIRED);

		// Error situation - no firmware
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		request = createLightStatusNotificationRequest();
		request.setLight(light);
		params = new ArrayList<LightParameter>();
		params.add(latitude);
		params.add(longitude);
		params.add(poleId);
		params.add(timezone);
		request.setLightParameters(params);
		response = getSmartPointProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_FIRMWARE_REQUIRED);

		// Error situation - no timezone
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		request = createLightStatusNotificationRequest();
		request.setLight(light);
		params = new ArrayList<LightParameter>();
		params.add(latitude);
		params.add(longitude);
		params.add(poleId);
		params.add(firmware);
		request.setLightParameters(params);
		response = getSmartPointProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_TIME_ZONE_REQUIRED);

		// Exception situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.EXCEPTION, ISmartPointProcessorBCL.class);
		request = createLightStatusNotificationRequest();
		params = new ArrayList<LightParameter>();
		params.add(latitude);
		params.add(longitude);
		params.add(poleId);
		params.add(firmware);
		params.add(timezone);
		request.setLightParameters(params);
		request.setLight(light);
		response = getSmartPointProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test process light setup notification.
	 */
	@Test
	public void testProcessLightSetupNotification()
	{
		// Validation situation - Tenant Required / Light RNI ID Required
		LightSetupNotificationRequest request = new LightSetupNotificationRequest();
		Response response = getSmartPointProcessorBCF().processLightSetupNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Light RNI ID Required
		request = TestBaseUtil.createLightSetupNotification();
		Light light = TestBaseUtil.createLight();
		light.setRniId(null);
		request.setLight(light);
		request.getLight().setLightLocation(new LightLocation(ONE_HUNDRED_ONE_DOUBLE, TWO_HUNDRED_ONE_DOUBLE));
		response = getSmartPointProcessorBCF().processLightSetupNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Validation situation - Latitude and Longitude Invalid
		request = TestBaseUtil.createLightSetupNotification();
		light = TestBaseUtil.createLight();
		request.setLight(light);
		request.getLight().setLightLocation(new LightLocation(ONE_HUNDRED_DOUBLE, TWO_HUNDRED_DOUBLE));
		response = getSmartPointProcessorBCF().processLightSetupNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LATITUDE,
				SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LONGITUDE);

		// Success situation
		request = TestBaseUtil.createLightSetupNotification();
		light = TestBaseUtil.createLight();
		request.setLight(light);
		response = getSmartPointProcessorBCF().processLightSetupNotification(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(0, response.getMessageList().size());

		// Error situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		response = getSmartPointProcessorBCF().processLightSetupNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.EXCEPTION, ISmartPointProcessorBCL.class);
		response = getSmartPointProcessorBCF().processLightSetupNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test process forced light status notification.
	 */
	@Test
	public void testProcessForcedLightStatusNotification()
	{
		Light light = TestBaseUtil.createLight();
		light.getLightSchedule().setSunriseTime("09:00");
		light.getLightSchedule().setSunsetTime("10:00");
		light.getLightSchedule().setSunriseOffset(SUNRISE_OFFSET);
		light.getLightSchedule().setSunsetOffset(SUNSET_OFFSET);

		ForcedLightStatusNotificationRequest request = createForcedLightStatusNotificationRequest();
		request.setLight(light);
		List<StatusException> statusExceptions = new ArrayList<StatusException>();
		statusExceptions.add(new StatusException(StatusExceptionTypeEnum.LAMP_FAILURE));
		request.setStatusException(statusExceptions);
		request.setLightStatusEnum(LightStatusEnum.ALARM);
		request.getLight().setLightStateEnum(LightStateEnum.OFF);
		Response response = getSmartPointProcessorBCF().processForcedLightStatusNotification(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		response = getSmartPointProcessorBCF().processForcedLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.EXCEPTION, ISmartPointProcessorBCL.class);
		response = getSmartPointProcessorBCF().processForcedLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);

		// Error situation - no light schedule
		resetMocksToSmartPointArea();
		light.setLightSchedule(null);

		request = createForcedLightStatusNotificationRequest();
		request.setLight(light);
		statusExceptions = new ArrayList<StatusException>();
		statusExceptions.add(new StatusException(StatusExceptionTypeEnum.LAMP_FAILURE));
		request.setStatusException(statusExceptions);
		request.setLightStatusEnum(LightStatusEnum.ALARM);
		request.getLight().setLightStateEnum(LightStateEnum.OFF);
		response = getSmartPointProcessorBCF().processForcedLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED);

		// Error situation - no sunrise time
		resetMocksToSmartPointArea();
		light.setLightSchedule(new LightSchedule());
		light.getLightSchedule().setSunsetTime("11:00");
		light.getLightSchedule().setSunriseOffset(SUNRISE_OFFSET);
		light.getLightSchedule().setSunsetOffset(SUNSET_OFFSET);

		request = createForcedLightStatusNotificationRequest();
		request.setLight(light);
		statusExceptions = new ArrayList<StatusException>();
		statusExceptions.add(new StatusException(StatusExceptionTypeEnum.LAMP_FAILURE));
		request.setStatusException(statusExceptions);
		request.setLightStatusEnum(LightStatusEnum.ALARM);
		request.getLight().setLightStateEnum(LightStateEnum.OFF);
		response = getSmartPointProcessorBCF().processForcedLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_SUNRISE_REQUIRED);

		// Error situation - no sunset time
		resetMocksToSmartPointArea();
		light.getLightSchedule().setSunriseTime("12:00");
		light.getLightSchedule().setSunsetTime(null);

		request = createForcedLightStatusNotificationRequest();
		request.setLight(light);
		statusExceptions = new ArrayList<StatusException>();
		statusExceptions.add(new StatusException(StatusExceptionTypeEnum.LAMP_FAILURE));
		request.setStatusException(statusExceptions);
		request.setLightStatusEnum(LightStatusEnum.ALARM);
		request.getLight().setLightStateEnum(LightStateEnum.OFF);
		response = getSmartPointProcessorBCF().processForcedLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_SUNSET_REQUIRED);

		// Error situation - no sunrise offset
		resetMocksToSmartPointArea();
		light.getLightSchedule().setSunsetTime("13:00");
		light.getLightSchedule().setSunriseOffset(null);

		request = createForcedLightStatusNotificationRequest();
		request.getLightDetailDataType().add(LightDetailDataTypeEnum.CONFIGURATION);
		request.setLight(light);
		statusExceptions = new ArrayList<StatusException>();
		statusExceptions.add(new StatusException(StatusExceptionTypeEnum.LAMP_FAILURE));
		request.setStatusException(statusExceptions);
		request.setLightStatusEnum(LightStatusEnum.ALARM);
		request.getLight().setLightStateEnum(LightStateEnum.OFF);
		response = getSmartPointProcessorBCF().processForcedLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_SUNRISEOFFSET_REQUIRED);

		// Error situation - no sunset offset
		resetMocksToSmartPointArea();
		light.getLightSchedule().setSunriseOffset(SUNRISE_OFFSET);
		light.getLightSchedule().setSunsetOffset(null);

		request = createForcedLightStatusNotificationRequest();
		request.getLightDetailDataType().add(LightDetailDataTypeEnum.CONFIGURATION);
		request.setLight(light);
		statusExceptions = new ArrayList<StatusException>();
		statusExceptions.add(new StatusException(StatusExceptionTypeEnum.LAMP_FAILURE));
		request.setStatusException(statusExceptions);
		request.setLightStatusEnum(LightStatusEnum.ALARM);
		request.getLight().setLightStateEnum(LightStateEnum.OFF);
		response = getSmartPointProcessorBCF().processForcedLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_SUNSETOFFSET_REQUIRED);

		// Success situation - No status exception
		resetMocksToSmartPointArea();
		light.getLightSchedule().setSunriseTime("14:00");
		light.getLightSchedule().setSunsetTime("15:00");
		light.getLightSchedule().setSunriseOffset(SUNRISE_OFFSET);
		light.getLightSchedule().setSunsetOffset(SUNSET_OFFSET);

		request = createForcedLightStatusNotificationRequest();
		request.setLight(light);
		request.setLightStatusEnum(LightStatusEnum.ALARM);
		request.getLight().setLightStateEnum(LightStateEnum.OFF);
		StatusException statusException = new StatusException();
		statusException.setStatusExceptionTypeEnum(null);
		request.getStatusException().add(statusException);
		response = getSmartPointProcessorBCF().processForcedLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_GATEWAY_ALARM_WARNING_SUBTYPE_REQUIRED);

		// Success situation - No status
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		request = createForcedLightStatusNotificationRequest();
		request.setLight(light);
		statusExceptions = new ArrayList<StatusException>();
		statusExceptions.add(new StatusException(StatusExceptionTypeEnum.LAMP_FAILURE));
		request.setStatusException(statusExceptions);
		request.getLight().setLightStateEnum(LightStateEnum.OFF);
		response = getSmartPointProcessorBCF().processForcedLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED);

		// Success situation - No state
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		request = createForcedLightStatusNotificationRequest();
		request.setLight(light);
		statusExceptions = new ArrayList<StatusException>();
		statusExceptions.add(new StatusException(StatusExceptionTypeEnum.LAMP_FAILURE));
		request.setStatusException(statusExceptions);
		request.setLightStatusEnum(LightStatusEnum.ALARM);
		request.getLight().setLightStateEnum(null);
		response = getSmartPointProcessorBCF().processForcedLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_STATE_REQUIRED);
	}

	/**
	 * Test process set light intensity notification.
	 */
	@Test
	public void testProcessSetLightIntensityNotification()
	{
		// Validation situation
		ProcessLightsRequest request = new ProcessLightsRequest();
		Response response = getSmartPointProcessorBCF().processSetLightIntensityNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		request = createProcessLightsRequest();
		Light light = createLight();
		light.setRniId(null);
		request.getLights().add(light);
		response = getSmartPointProcessorBCF().processSetLightIntensityNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Success situation
		request = createProcessLightsRequest();
		light = createLight();
		request.getLights().add(light);
		response = getSmartPointProcessorBCF().processSetLightIntensityNotification(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(0, response.getMessageList().size());

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class,
				PROCESS_SET_LIGHT_INTENSITY_NOTIFICATION);
		request = createProcessLightsRequest();
		light = createLight();
		request.getLights().add(light);
		response = getSmartPointProcessorBCF().processSetLightIntensityNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.EXCEPTION, ISmartPointProcessorBCL.class,
				PROCESS_SET_LIGHT_INTENSITY_NOTIFICATION);
		request = createProcessLightsRequest();
		light = createLight();
		request.getLights().add(light);
		response = getSmartPointProcessorBCF().processSetLightIntensityNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test initiate update light intensity.
	 */
	@Test
	public void testInitiateUpdateLightIntensity()
	{
		// Validation situation - Tenant / User Required
		LightRequest lightRequest = new LightRequest();
		ProcessResponse response = getSmartPointProcessorBCF().initiateUpdateLightIntensity(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Is Monitored Required
		lightRequest = createLightRequest();
		lightRequest.setIsMonitored(null);
		response = getSmartPointProcessorBCF().initiateUpdateLightIntensity(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED,
				SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED);

		// Validation situation - Per Date Invalid
		lightRequest = createLightRequest();
		lightRequest.setPaginationAllSelected(true);
		lightRequest.setPercentage(ARBITRARY_PERCENTAGE_25);
		Light light = new Light();
		light.setId(1);
		lightRequest.addLight(light);
		lightRequest.getFirstLight().setLightIntensityEnum(LightIntensityEnum.LEVEL_4);
		lightRequest.setOverrideEnum(OverrideEnum.PER_DATE);
		lightRequest.setOverridePerDate(null);
		lightRequest.setLightBlinkEnum(LightBlinkEnum.FAST);
		response = getSmartPointProcessorBCF().initiateUpdateLightIntensity(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINT_EXPIRE_PER_DATE_INVALID);

		// Validation situation - Blink Level Required
		lightRequest.setOverridePerDate(getNewDateUTC());
		lightRequest.setLightBlinkEnum(null);
		response = getSmartPointProcessorBCF().initiateUpdateLightIntensity(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINT_BLINK_LEVEL_REQUIRED);

		// Validation situation - Intensity Required
		lightRequest.setLightBlinkEnum(LightBlinkEnum.FAST);
		lightRequest.setPercentage(null);
		response = getSmartPointProcessorBCF().initiateUpdateLightIntensity(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED);

		// Success situation
		lightRequest = createLightRequest();
		lightRequest.setPaginationAllSelected(true);
		lightRequest.setPercentage(ARBITRARY_PERCENTAGE_25);
		light = new Light();
		light.setId(1);
		lightRequest.addLight(light);
		lightRequest.getFirstLight().setLightIntensityEnum(LightIntensityEnum.LEVEL_4);
		lightRequest.setOverrideEnum(OverrideEnum.PER_DATE);
		lightRequest.setOverridePerDate(getNewDateUTC());
		lightRequest.setLightBlinkEnum(LightBlinkEnum.FAST);
		response = getSmartPointProcessorBCF().initiateUpdateLightIntensity(lightRequest);
		assertTrue(response.isOperationSuccess());
		assertEquals(0, response.getMessageInfoList().size());

		// Error situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		response = getSmartPointProcessorBCF().initiateUpdateLightIntensity(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.EXCEPTION, ISmartPointProcessorBCL.class);
		response = getSmartPointProcessorBCF().initiateUpdateLightIntensity(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test initiate delete alert.
	 */
	@Test
	public void testInitiateDeleteAlert()
	{
		// Validation situation
		LightRequest lightRequest = createLightRequest();
		ProcessResponse response = getSmartPointProcessorBCF().initiateDeleteAlert(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED);

		// Success situation
		Light light = new Light();
		light.setId(1);
		lightRequest.addLight(light);
		lightRequest.setPaginationAllSelected(true);
		response = getSmartPointProcessorBCF().initiateDeleteAlert(lightRequest);
		assertTrue(response.isOperationSuccess());
		assertEquals(0, response.getMessageInfoList().size());

		// Error situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class);
		response = getSmartPointProcessorBCF().initiateDeleteAlert(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.EXCEPTION, ISmartPointProcessorBCL.class);
		response = getSmartPointProcessorBCF().initiateDeleteAlert(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test initiate upsert light property.
	 */
	@Test
	public void testInitiateUpsertLightProperty()
	{
		// Validation situation - ID Required
		LightRequest request = createLightRequest();
		Light light = createLight();
		light.setId(null);
		light.setPoleId(ONE);
		request.addLight(light);
		ProcessResponse response = getSmartPointProcessorBCF().initiateUpsertLightProperty(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED);

		// Validation situation - ID Required
		request = createLightRequest();
		request.setPaginationAllSelected(true);
		light = createLight();
		light.setId(null);
		light.setPoleId(ONE);
		request.addLight(light);
		response = getSmartPointProcessorBCF().initiateUpsertLightProperty(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED);

		// Validation situation - Property Required
		request = createLightRequest();
		request.setPaginationAllSelected(true);
		light = createLight();
		light.setPoleId(null);
		request.addLight(light);
		response = getSmartPointProcessorBCF().initiateUpsertLightProperty(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED);

		// Success situation
		request = createLightRequest();
		request.setPaginationAllSelected(true);
		light = createLight();
		light.setPoleId(ONE);
		request.addLight(light);
		response = getSmartPointProcessorBCF().initiateUpsertLightProperty(request);
		assertTrue(response.isOperationSuccess());

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class,
				INITIATE_UPSERT_LIGHT_PROPERTY);
		request = createLightRequest();
		request.setPaginationAllSelected(true);
		light = createLight();
		light.setPoleId(ONE);
		request.addLight(light);
		response = getSmartPointProcessorBCF().initiateUpsertLightProperty(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.EXCEPTION, ISmartPointProcessorBCL.class,
				INITIATE_UPSERT_LIGHT_PROPERTY);
		request = createLightRequest();
		request.setPaginationAllSelected(true);
		light = createLight();
		light.setPoleId(ONE);
		request.addLight(light);
		response = getSmartPointProcessorBCF().initiateUpsertLightProperty(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test initiate update light status.
	 */
	@Test
	public void testInitiateUpdateLightStatus()
	{
		// Validation situation - ID Required / Is Monitored Null
		LightRequest request = createLightRequest();
		request.addLight(TestBaseUtil.createLight());
		request.setSelectionPaginationIds(null);
		request.setPaginationAllSelected(null);
		request.setIsMonitored(null);
		ProcessResponse response = getSmartPointProcessorBCF().initiateUpdateLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED);

		// Success situation
		request = createLightRequest();
		request.addLight(TestBaseUtil.createLight());
		request.setPaginationAllSelected(true);
		request.setIsMonitored(true);
		response = getSmartPointProcessorBCF().initiateUpdateLightStatus(request);
		assertTrue(response.isOperationSuccess());

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class,
				INITIATE_UPDATE_LIGHT_STATUS);
		response = getSmartPointProcessorBCF().initiateUpdateLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.EXCEPTION, ISmartPointProcessorBCL.class,
				INITIATE_UPDATE_LIGHT_STATUS);
		response = getSmartPointProcessorBCF().initiateUpdateLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test process clear alarm notification.
	 */
	@Test
	public void testProcessClearAlarmNotification()
	{
		// Validation situation - Tenant Required
		AlarmNotificationRequest request = TestBaseUtil.createAlarmNotificationRequest();
		Light light = TestBaseUtil.createLight();
		light.setRniId(null);
		request.setTenant(null);
		request.setLight(light);
		request.setLightStatusEnum(null);
		AlarmNotificationResponse response = getSmartPointProcessorBCF().processClearAlarmNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation - RNI ID Required
		request = TestBaseUtil.createAlarmNotificationRequest();
		light = TestBaseUtil.createLight();
		light.setRniId(null);
		request.setLight(light);
		request.setLightStatusEnum(null);
		response = getSmartPointProcessorBCF().processClearAlarmNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Validation situation - Light State Required
		request = TestBaseUtil.createAlarmNotificationRequest();
		light = TestBaseUtil.createLight();
		light.setLightStateEnum(null);
		request.setLight(light);
		response = getSmartPointProcessorBCF().processClearAlarmNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_STATE_REQUIRED);

		// Validation situation - Alarm Warning Subtype Required / Light Top Level State Required
		request = TestBaseUtil.createAlarmNotificationRequest();
		light = TestBaseUtil.createLight();
		request.setLight(light);
		StatusException statusException = new StatusException();
		statusException.setId(2);
		statusException.setLabelKey(FLEX_NET_ID);
		statusException.setStatusExceptionTypeEnum(null);
		request.getStatusException().add(statusException);
		request.setLightStatusEnum(null);
		response = getSmartPointProcessorBCF().processClearAlarmNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_GATEWAY_ALARM_WARNING_SUBTYPE_REQUIRED,
				SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED);

		// Success situation
		request = TestBaseUtil.createAlarmNotificationRequest();
		request.setLight(TestBaseUtil.createLight());
		request.setLightStatusEnum(LightStatusEnum.ACTIVE);
		response = getSmartPointProcessorBCF().processClearAlarmNotification(request);
		assertTrue(response.isOperationSuccess());

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class,
				PROCESS_CLEAR_ALARM_NOTIFICATION);
		request = TestBaseUtil.createAlarmNotificationRequest();
		request.setLight(TestBaseUtil.createLight());
		request.setLightStatusEnum(LightStatusEnum.ACTIVE);
		response = getSmartPointProcessorBCF().processClearAlarmNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.EXCEPTION, ISmartPointProcessorBCL.class,
				PROCESS_CLEAR_ALARM_NOTIFICATION);
		request = TestBaseUtil.createAlarmNotificationRequest();
		request.setLight(TestBaseUtil.createLight());
		request.setLightStatusEnum(LightStatusEnum.ACTIVE);
		response = getSmartPointProcessorBCF().processClearAlarmNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test process channel setup audit notification.
	 */
	@Test
	public void testProcessChannelSetupAuditNotification()
	{
		// Validation situation - RNI Code Required
		ChannelSetupAuditRequest request = createChannelSetupAuditRequest();
		Light light = createLight();
		request.setLight(light);
		request.setLightStatusEnum(LightStatusEnum.ACTIVE);
		String rniCode = request.getTenant().getRniCode();
		request.getTenant().setRniCode(null);
		Response response = getSmartPointProcessorBCF().processChannelSetupAuditNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		request.getTenant().setRniCode(rniCode);

		// Validation situation - Light RNI ID Required
		request = createChannelSetupAuditRequest();
		light = createLight();
		light.setRniId(null);
		request.setLight(light);
		request.setLightStatusEnum(LightStatusEnum.ACTIVE);
		response = getSmartPointProcessorBCF().processChannelSetupAuditNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Validation situation - Light Status Enum Required
		request = createChannelSetupAuditRequest();
		light = createLight();
		request.setLight(light);
		request.setLightStatusEnum(null);
		response = getSmartPointProcessorBCF().processChannelSetupAuditNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED);

		// Success situation
		request = createChannelSetupAuditRequest();
		light = createLight();
		request.setLight(light);
		request.setLightStatusEnum(LightStatusEnum.ACTIVE);
		response = getSmartPointProcessorBCF().processChannelSetupAuditNotification(request);
		assertTrue(response.isOperationSuccess());

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class,
				PROCESS_CHANNEL_SETUP_AUDIT_NOTIFICATION);
		request = createChannelSetupAuditRequest();
		light = createLight();
		request.setLight(light);
		request.setLightStatusEnum(LightStatusEnum.ACTIVE);
		response = getSmartPointProcessorBCF().processChannelSetupAuditNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.EXCEPTION, ISmartPointProcessorBCL.class,
				PROCESS_CHANNEL_SETUP_AUDIT_NOTIFICATION);
		request = createChannelSetupAuditRequest();
		light = createLight();
		request.setLight(light);
		request.setLightStatusEnum(LightStatusEnum.ACTIVE);
		response = getSmartPointProcessorBCF().processChannelSetupAuditNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test process apply smartpoint properties notification.
	 */
	@Test
	public void testProcessApplySmartpointPropertiesNotification()
	{
		// Validation situation - Tenant / User Required
		ProcessLightsRequest request = new ProcessLightsRequest();
		Response response = getSmartPointProcessorBCF().processApplySmartpointPropertiesNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Light Required
		request = createProcessLightsRequest();
		request.setLights(null);
		response = getSmartPointProcessorBCF().processApplySmartpointPropertiesNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHTLIST_REQUIRED);

		// Validation situation - RNI Code Required
		request = createProcessLightsRequest();
		Light light = createLight();
		light.setRniId(null);
		request.setLights(Arrays.asList(light));
		response = getSmartPointProcessorBCF().processApplySmartpointPropertiesNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Success situation
		request = createProcessLightsRequest();
		light = createLight();
		request.setLights(Arrays.asList(light));
		response = getSmartPointProcessorBCF().processApplySmartpointPropertiesNotification(request);
		assertTrue(response.isOperationSuccess());

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.ERROR, ISmartPointProcessorBCL.class,
				PROCESS_APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION);
		request = createProcessLightsRequest();
		request.setLights(Arrays.asList(light));
		response = getSmartPointProcessorBCF().processApplySmartpointPropertiesNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointProcessorBCF(), SituationsEnum.EXCEPTION, ISmartPointProcessorBCL.class,
				PROCESS_APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION);
		request = createProcessLightsRequest();
		light = createLight();
		request.setLights(Arrays.asList(light));
		response = getSmartPointProcessorBCF().processApplySmartpointPropertiesNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}
}
