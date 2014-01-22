package com.sensus.lc.light.bcf;

import static com.sensus.lc.base.TestBaseUtil.createAlarmNotificationRequest;
import static com.sensus.lc.base.TestBaseUtil.createChannelSetupAuditRequest;
import static com.sensus.lc.base.TestBaseUtil.createLight;
import static com.sensus.lc.base.TestBaseUtil.createLightIntensityRequest;
import static com.sensus.lc.base.TestBaseUtil.createLightMaintenanceRequest;
import static com.sensus.lc.base.TestBaseUtil.createLightRequest;
import static com.sensus.lc.base.TestBaseUtil.createLightSetupNotificationRequest;
import static com.sensus.lc.base.TestBaseUtil.createLightStatusNotificationRequest;
import static com.sensus.lc.base.TestBaseUtil.createNotificationHistory;
import static com.sensus.lc.base.TestBaseUtil.createOperationalData;
import static com.sensus.lc.base.TestBaseUtil.createProcessLightsRequest;
import static com.sensus.lc.base.TestBaseUtil.createUpdateLightNotificationRequest;
import static com.sensus.lc.base.TestBaseUtil.createUserContext;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.Radio;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusDateUtil;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.bcl.ILightProcessorBCL;
import com.sensus.lc.light.model.BlinkStatusEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.OperationalData;
import com.sensus.lc.light.model.OverrideEnum;
import com.sensus.lc.light.model.criteria.ActionCriteria;
import com.sensus.lc.light.model.request.AlarmNotificationRequest;
import com.sensus.lc.light.model.request.ChannelSetupAuditRequest;
import com.sensus.lc.light.model.request.LightIntensityRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.LightSetupNotificationRequest;
import com.sensus.lc.light.model.request.LightStatusNotificationRequest;
import com.sensus.lc.light.model.request.ProcessLightsRequest;
import com.sensus.lc.light.model.request.UpdateLightNotificationRequest;
import com.sensus.lc.process.model.response.ProcessResponse;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class LightBCFTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/mlc/light/lightbcfimpletest.xml"})
public class LightProcessorBCFTest extends AbstractTestBaseBusiness
{
	/** Attributes. */
	private static final Double INVALID_LATITUDE = Double.valueOf(100);
	private static final Double INVALID_LONGITUDE = Double.valueOf(200);
	private static final Integer ARBITRARY_PERCENTAGE_25 = 25;

	/** Messages. */
	private static final String DEFAULT_EXCEPTION_MSG = "sensus.mlc.lightbcfimpl.defaultexception";
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";
	private static final String SENSUS_MLC_LIGHTVALIDATOR_PROPERTIES_REQUIRED =
			"sensus.mlc.lightvalidator.properties.required";
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LATITUDE_REQUIRED =
			"sensus.mlc.lightvalidator.latitude.required";
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LONGITUDE_REQUIRED =
			"sensus.mlc.lightvalidator.longitude.required";
	private static final String SENSUS_MLC_VALIDATOR_POLEID_REQUIRED =
			"sensus.mlc.validator.poleid.required";
	private static final String SENSUS_MLC_LIGHTVALIDATOR_FIRMWARE_REQUIRED =
			"sensus.mlc.lightvalidator.firmware.required";
	private static final String SENSUS_MLC_LIGHTVALIDATOR_TIME_ZONE_REQUIRED =
			"sensus.mlc.lightvalidator.time.zone.required";
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INVALID_LATITUDE =
			"sensus.mlc.lightvalidator.invalid.latitude";
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INVALID_LONGITUDE =
			"sensus.mlc.lightvalidator.invalid.longitude";
	private static final String SENSUS_MLC_LIGHTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED =
			"sensus.mlc.lightvalidator.schedule.properties.required";
	private static final String SENSUS_MLC_LIGHTVALIDATOR_SUNRISE_REQUIRED =
			"sensus.mlc.lightvalidator.sunrise.required";
	private static final String SENSUS_MLC_LIGHTVALIDATOR_SUNSET_REQUIRED =
			"sensus.mlc.lightvalidator.sunset.required";
	private static final String SENSUS_MLC_LIGHTVALIDATOR_SUNRISEOFFSET_REQUIRED =
			"sensus.mlc.lightvalidator.sunrise.offset.required";
	private static final String SENSUS_MLC_LIGHTVALIDATOR_SUNSETOFFSET_REQUIRED =
			"sensus.mlc.lightvalidator.sunset.offset.required";
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED =
			"sensus.mlc.lightvalidator.light.top.level.state.required";
	private static final String SENSUS_MLC_LIGHT_EXPIRE_PER_DATE_INVALID =
			"sensus.mlc.lightvalidator.expire.per.date.invalid";
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED =
			"sensus.mlc.lightvalidator.intensity.required";
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED =
			"sensus.mlc.lightvalidator.lightrniid.required";

	/** Name classes. */
	private static final String RADIO_CLASS_NAME = Radio.class.getSimpleName();
	private static final String TENANT_NAME = Tenant.class.getSimpleName();

	/** BCFs. */
	private ILightProcessorBCF lightProcessorBCF;

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setLightArea();
	}

	/**
	 * Sets the light area.
	 */
	public void setLightArea()
	{
		setArea(getLightProcessorBCF(), LCAreaEnum.LIGHT);
	}

	/**
	 * Reset mocks to light area.
	 */
	@After
	public void resetMocksToLightArea()
	{
		resetMocksData(getLightProcessorBCF());
		setLightArea();
	}

	/**
	 * Gets the light processor bcf.
	 * 
	 * @return the light processor bcf
	 */
	public ILightProcessorBCF getLightProcessorBCF()
	{
		return lightProcessorBCF;
	}

	/**
	 * Sets the light processor bcf.
	 * 
	 * @param lightProcessorBCF the new light processor bcf
	 */
	@Resource(name = "lightProcessorBCFTarget")
	public void setLightProcessorBCF(ILightProcessorBCF lightProcessorBCF)
	{
		this.lightProcessorBCF = lightProcessorBCF;
	}

	/**
	 * Test process light binding notification.
	 */
	@Test
	public void testProcessLightBindingNotification()
	{
		UserContext userContext = createUserContext();

		// Success situation
		LightStatusNotificationRequest request = createLightStatusNotificationRequest(userContext);
		request.setLight(createLight(userContext));
		Response response = getLightProcessorBCF().processLightBindingNotification(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Tenant Required
		String rniCode = request.getTenant().getRniCode();
		request = createLightStatusNotificationRequest();
		request.setLight(createLight(userContext));
		request.getTenant().setRniCode(null);
		response = getLightProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(TENANT_NAME));
		request.getTenant().setRniCode(rniCode);

		// Error situation - no Light
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.ERROR, ILightProcessorBCL.class);
		request = createLightStatusNotificationRequest();
		request.getLight().setRadio(null);
		response = getLightProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(RADIO_CLASS_NAME));

		// Error situation - no Flexnet
		resetMocksToLightArea();
		request = createLightStatusNotificationRequest(userContext);
		request.setLight(createLight(userContext));
		request.getLight().getRadio().setFlexNetId(null);
		response = getLightProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Error situation - no latitude
		resetMocksToLightArea();
		request = createLightStatusNotificationRequest();
		request.setLight(createLight(userContext));
		request.getLight().getRadio().getLocation().setLatitude(null);
		response = getLightProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LATITUDE_REQUIRED);

		// Error situation - invalid latitude
		resetMocksToLightArea();
		request = createLightStatusNotificationRequest();
		request.setLight(createLight(userContext));
		request.getLight().getRadio().getLocation().setLatitude(INVALID_LATITUDE);
		response = getLightProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INVALID_LATITUDE);

		// Error situation - no longitude
		resetMocksToLightArea();
		request = createLightStatusNotificationRequest();
		request.setLight(createLight(userContext));
		request.getLight().getRadio().getLocation().setLongitude(null);
		response = getLightProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LONGITUDE_REQUIRED);

		// Error situation - invalid longitude
		resetMocksToLightArea();
		request = createLightStatusNotificationRequest();
		request.setLight(createLight(userContext));
		request.getLight().getRadio().getLocation().setLongitude(INVALID_LONGITUDE);
		response = getLightProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INVALID_LONGITUDE);

		// Error situation - no pole id
		resetMocksToLightArea();
		request = createLightStatusNotificationRequest();
		request.setLight(createLight(userContext));
		request.getLight().setPoleId(null);
		response = getLightProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_POLEID_REQUIRED);

		// Error situation - no firmware
		resetMocksToLightArea();
		request = createLightStatusNotificationRequest();
		request.setLight(createLight(userContext));
		request.getLight().getConfiguration().setFirmwareVersion(null);
		response = getLightProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_FIRMWARE_REQUIRED);

		// Error situation - no timezone
		resetMocksToLightArea();
		request = createLightStatusNotificationRequest();
		request.setLight(createLight(userContext));
		request.getLight().getRadio().getLocation().setTimeZoneInfo(null);
		response = getLightProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_TIME_ZONE_REQUIRED);

		// Exception situation
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.EXCEPTION, ILightProcessorBCL.class);
		request = createLightStatusNotificationRequest();
		request.setLight(createLight(userContext));
		response = getLightProcessorBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test process light status notification.
	 */
	@Test
	public void testProcessLightStatusNotification()
	{
		UserContext userContext = createUserContext();
		Light light = TestBaseUtil.createLight(userContext);

		NotificationHistory notification = createNotificationHistory(userContext, light);
		List<OperationalData> operationalDataList = new ArrayList<>();
		operationalDataList.add(createOperationalData(notification));
		operationalDataList.add(createOperationalData(notification));
		operationalDataList.add(createOperationalData(notification));

		// Success situation
		LightStatusNotificationRequest request = createLightStatusNotificationRequest(userContext);
		request.setLight(light);
		request.setOperationalData(operationalDataList);
		Response response = getLightProcessorBCF().processLightStatusNotification(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Tenant Required
		String rniCode = request.getTenant().getRniCode();
		request = createLightStatusNotificationRequest();
		request.setLight(createLight(userContext));
		request.getTenant().setRniCode(null);
		response = getLightProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(TENANT_NAME));
		request.getTenant().setRniCode(rniCode);

		// Error situation
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.ERROR, ILightProcessorBCL.class);
		request = createLightStatusNotificationRequest(userContext);
		request.setLight(light);
		request.setOperationalData(operationalDataList);
		response = getLightProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - no Radio
		resetMocksToLightArea();
		request = createLightStatusNotificationRequest(userContext);
		request.getLight().setRadio(null);
		request.setOperationalData(operationalDataList);
		response = getLightProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(RADIO_CLASS_NAME));

		// Error situation - no Flexnet
		resetMocksToLightArea();
		request = createLightStatusNotificationRequest(userContext);
		request.setLight(TestBaseUtil.createLight(userContext));
		request.getLight().getRadio().setFlexNetId(null);
		request.setOperationalData(operationalDataList);
		response = getLightProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Error situation - no operational data
		resetMocksToLightArea();
		request = createLightStatusNotificationRequest(userContext);
		request.setLight(light);
		response = getLightProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_PROPERTIES_REQUIRED);

		// Exception situation
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.EXCEPTION, ILightProcessorBCL.class);
		request = createLightStatusNotificationRequest(userContext);
		request.setLight(light);
		request.setOperationalData(operationalDataList);
		response = getLightProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test process forced light status notification.
	 */
	@Test
	public void testProcessForcedLightStatusNotification()
	{
		UserContext userContext = createUserContext();

		// Success situation
		LightStatusNotificationRequest request = createLightStatusNotificationRequest(userContext);
		request.setForced(true);
		request.setLight(createLight(userContext));
		Response response = getLightProcessorBCF().processLightStatusNotification(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Tenant Required
		String rniCode = request.getTenant().getRniCode();
		request = createLightStatusNotificationRequest();
		request.setForced(true);
		request.setLight(createLight(userContext));
		request.getTenant().setRniCode(null);
		response = getLightProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(TENANT_NAME));
		request.getTenant().setRniCode(rniCode);

		// Error situation - no light schedule
		request = createLightStatusNotificationRequest(userContext);
		request.setForced(true);
		request.setLight(createLight(userContext));
		request.getLight().setLightSchedule(null);
		response = getLightProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED);

		// Error situation - no sunrise time
		request = createLightStatusNotificationRequest(userContext);
		request.setForced(true);
		request.setLight(createLight(userContext));
		request.getLight().getLightSchedule().setSunriseTime(null);
		response = getLightProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_SUNRISE_REQUIRED);

		// Error situation - no sunset time
		request = createLightStatusNotificationRequest(userContext);
		request.setForced(true);
		request.setLight(createLight(userContext));
		request.getLight().getLightSchedule().setSunsetTime(null);
		response = getLightProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_SUNSET_REQUIRED);

		// Error situation - no sunrise offset
		request = createLightStatusNotificationRequest(userContext);
		request.setForced(true);
		request.setLight(createLight(userContext));
		request.getLight().getLightSchedule().setSunriseOffset(null);
		response = getLightProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_SUNRISEOFFSET_REQUIRED);

		// Error situation - no sunset offset
		request = createLightStatusNotificationRequest(userContext);
		request.setForced(true);
		request.setLight(createLight(userContext));
		request.getLight().getLightSchedule().setSunsetOffset(null);
		response = getLightProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_SUNSETOFFSET_REQUIRED);

		// Error situation - no life cycle state
		request = createLightStatusNotificationRequest(userContext);
		request.setForced(true);
		request.setLight(createLight(userContext));
		request.setLifeCycleState(null);
		response = getLightProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED);

		// Exception situation
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.EXCEPTION, ILightProcessorBCL.class);
		request = createLightStatusNotificationRequest(userContext);
		request.setForced(true);
		request.setLight(createLight(userContext));
		response = getLightProcessorBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test process alarm warning notification.
	 */
	@Test
	public void testProcessAlarmWarningNotification()
	{
		UserContext userContext = createUserContext();

		// Success situation
		AlarmNotificationRequest request = createAlarmNotificationRequest(userContext);
		request.setLight(createLight(userContext));
		Response response = getLightProcessorBCF().processAlarmWarningNotification(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Tenant Required
		resetMocksToLightArea();
		String rniCode = request.getTenant().getRniCode();
		request = createAlarmNotificationRequest(userContext);
		request.setLight(createLight(userContext));
		request.getTenant().setRniCode(null);
		response = getLightProcessorBCF().processAlarmWarningNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(TENANT_NAME));
		request.getTenant().setRniCode(rniCode);

		// Error situation - no Radio
		resetMocksToLightArea();
		request = createAlarmNotificationRequest(userContext);
		request.getLight().setRadio(null);
		response = getLightProcessorBCF().processAlarmWarningNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(RADIO_CLASS_NAME));

		// Error situation - no Flexnet
		resetMocksToLightArea();
		request = createAlarmNotificationRequest(userContext);
		request.setLight(createLight(userContext));
		request.getLight().getRadio().setFlexNetId(null);
		response = getLightProcessorBCF().processAlarmWarningNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Error situation - no Life cycle state
		request = createAlarmNotificationRequest(userContext);
		request.setLight(createLight(userContext));
		request.setLifeCycleState(null);
		response = getLightProcessorBCF().processAlarmWarningNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED);

		// Exception situation
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.EXCEPTION, ILightProcessorBCL.class);
		request = createAlarmNotificationRequest(userContext);
		request.setLight(createLight(userContext));
		response = getLightProcessorBCF().processAlarmWarningNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);

	}

	/**
	 * Test process light setup notification.
	 */
	@Test
	public void testProcessLightSetupNotification()
	{
		UserContext userContext = createUserContext();

		// Success situation
		LightSetupNotificationRequest request = createLightSetupNotificationRequest(userContext);
		request.setLight(createLight(userContext));
		Response response = getLightProcessorBCF().processLightSetupNotification(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Tenant Required
		resetMocksToLightArea();
		String rniCode = request.getTenant().getRniCode();
		request = createLightSetupNotificationRequest(userContext);
		request.setLight(createLight(userContext));
		request.getTenant().setRniCode(null);
		response = getLightProcessorBCF().processLightSetupNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(TENANT_NAME));
		request.getTenant().setRniCode(rniCode);

		// Error situation - no Radio
		resetMocksToLightArea();
		request = createLightSetupNotificationRequest(userContext);
		request.getLight().setRadio(null);
		response = getLightProcessorBCF().processLightSetupNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(RADIO_CLASS_NAME));

		// Error situation - no Flexnet
		resetMocksToLightArea();
		request = createLightSetupNotificationRequest(userContext);
		request.setLight(createLight(userContext));
		request.getLight().getRadio().setFlexNetId(null);
		response = getLightProcessorBCF().processLightSetupNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Exception situation
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.EXCEPTION, ILightProcessorBCL.class);
		request = createLightSetupNotificationRequest(userContext);
		request.setLight(createLight(userContext));
		response = getLightProcessorBCF().processLightSetupNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test process clear alarm notification.
	 */
	@Test
	public void testProcessClearAlarmNotification()
	{
		UserContext userContext = createUserContext();

		// Success situation
		AlarmNotificationRequest request = createAlarmNotificationRequest(userContext);
		request.setLight(createLight(userContext));
		Response response = getLightProcessorBCF().processClearAlarmNotification(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Tenant Required
		resetMocksToLightArea();
		String rniCode = request.getTenant().getRniCode();
		request = createAlarmNotificationRequest(userContext);
		request.setLight(createLight(userContext));
		request.getTenant().setRniCode(null);
		response = getLightProcessorBCF().processClearAlarmNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(TENANT_NAME));
		request.getTenant().setRniCode(rniCode);

		// Error situation - no Radio
		resetMocksToLightArea();
		request = createAlarmNotificationRequest(userContext);
		request.getLight().setRadio(null);
		response = getLightProcessorBCF().processClearAlarmNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(RADIO_CLASS_NAME));

		// Error situation - no Flexnet
		resetMocksToLightArea();
		request = createAlarmNotificationRequest(userContext);
		request.setLight(createLight(userContext));
		request.getLight().getRadio().setFlexNetId(null);
		response = getLightProcessorBCF().processClearAlarmNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Exception situation
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.EXCEPTION, ILightProcessorBCL.class);
		request = createAlarmNotificationRequest(userContext);
		request.setLight(createLight(userContext));
		response = getLightProcessorBCF().processClearAlarmNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test process set light intensity notification.
	 */
	@Test
	public void testProcessSetLightIntensityNotification()
	{
		UserContext userContext = createUserContext();
		Light light = createLight(userContext);

		// Success situation
		LightIntensityRequest request = createLightIntensityRequest(userContext);
		request.setLightList(Arrays.asList(light));
		Response response = getLightProcessorBCF().processSetLightIntensityNotification(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Tenant Required
		resetMocksToLightArea();
		String rniCode = request.getUserContext().<Tenant> getTenant().getRniCode();
		request = createLightIntensityRequest(userContext);
		light = createLight(userContext);
		request.setLightList(Arrays.asList(light));
		request.getUserContext().<Tenant> getTenant().setRniCode(null);
		response = getLightProcessorBCF().processSetLightIntensityNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(TENANT_NAME));
		request.getUserContext().<Tenant> getTenant().setRniCode(rniCode);

		// Error situation - no Radio
		resetMocksToLightArea();
		request = createLightIntensityRequest(userContext);
		light = createLight(userContext);
		light.setRadio(null);
		request.setLightList(Arrays.asList(light));
		response = getLightProcessorBCF().processSetLightIntensityNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(RADIO_CLASS_NAME));

		// Error situation - no Flexnet
		resetMocksToLightArea();
		request = createLightIntensityRequest(userContext);
		light = createLight(userContext);
		light.getRadio().setFlexNetId(null);
		request.setLightList(Arrays.asList(light));
		response = getLightProcessorBCF().processSetLightIntensityNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Exception situation
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.EXCEPTION, ILightProcessorBCL.class);
		request = createLightIntensityRequest(userContext);
		light = createLight(userContext);
		request.setLightList(Arrays.asList(light));
		response = getLightProcessorBCF().processSetLightIntensityNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test process channel setup audit notification.
	 */
	@Test
	public void testProcessChannelSetupAuditNotification()
	{
		UserContext userContext = createUserContext();

		// Success situation
		ChannelSetupAuditRequest request = createChannelSetupAuditRequest(userContext);
		request.setLight(createLight(userContext));
		Response response = getLightProcessorBCF().processChannelSetupAuditNotification(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Tenant Required
		resetMocksToLightArea();
		String rniCode = request.getTenant().getRniCode();
		request = createChannelSetupAuditRequest(userContext);
		request.setLight(createLight(userContext));
		request.getTenant().setRniCode(null);
		response = getLightProcessorBCF().processChannelSetupAuditNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(TENANT_NAME));
		request.getTenant().setRniCode(rniCode);

		// Error situation - no Radio
		resetMocksToLightArea();
		request = createChannelSetupAuditRequest(userContext);
		request.getLight().setRadio(null);
		response = getLightProcessorBCF().processChannelSetupAuditNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(RADIO_CLASS_NAME));

		// Error situation - no Flexnet
		resetMocksToLightArea();
		request = createChannelSetupAuditRequest(userContext);
		request.setLight(createLight(userContext));
		request.getLight().getRadio().setFlexNetId(null);
		response = getLightProcessorBCF().processChannelSetupAuditNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Error situation - no Life cycle state
		request = createChannelSetupAuditRequest(userContext);
		request.setLight(createLight(userContext));
		request.setLifeCycleState(null);
		response = getLightProcessorBCF().processChannelSetupAuditNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED);

		// Exception situation
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.EXCEPTION, ILightProcessorBCL.class);
		request = createChannelSetupAuditRequest(userContext);
		request.setLight(createLight(userContext));
		response = getLightProcessorBCF().processChannelSetupAuditNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test process apply smartpoint properties notification.
	 */
	@Test
	public void testProcessApplySmartpointPropertiesNotification()
	{
		UserContext userContext = createUserContext();
		Light light = createLight(userContext);

		// Success situation
		ProcessLightsRequest request = createProcessLightsRequest(userContext);
		request.setLights(Arrays.asList(light));
		Response response = getLightProcessorBCF().processApplySmartpointPropertiesNotification(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Tenant Required
		resetMocksToLightArea();
		String rniCode = request.getUserContext().<Tenant> getTenant().getRniCode();
		request = createProcessLightsRequest(userContext);
		light = createLight(userContext);
		request.setLights(Arrays.asList(light));
		request.getUserContext().<Tenant> getTenant().setRniCode(null);
		response = getLightProcessorBCF().processApplySmartpointPropertiesNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(TENANT_NAME));
		request.getUserContext().<Tenant> getTenant().setRniCode(rniCode);

		// Error situation - no Radio
		resetMocksToLightArea();
		request = createProcessLightsRequest(userContext);
		light = createLight(userContext);
		light.setRadio(null);
		request.setLights(Arrays.asList(light));
		response = getLightProcessorBCF().processApplySmartpointPropertiesNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(RADIO_CLASS_NAME));

		// Error situation - no Flexnet
		resetMocksToLightArea();
		request = createProcessLightsRequest(userContext);
		light = createLight(userContext);
		light.getRadio().setFlexNetId(null);
		request.setLights(Arrays.asList(light));
		response = getLightProcessorBCF().processApplySmartpointPropertiesNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Exception situation
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.EXCEPTION, ILightProcessorBCL.class);
		request = createProcessLightsRequest(userContext);
		light = createLight(userContext);
		request.setLights(Arrays.asList(light));
		response = getLightProcessorBCF().processApplySmartpointPropertiesNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test initiate update light intensity.
	 */
	@Test
	public void testProcessUpdateLightStatusNotification()
	{
		UserContext userContext = createUserContext();

		// Success situation
		UpdateLightNotificationRequest request = createUpdateLightNotificationRequest(userContext);
		Response response = getLightProcessorBCF().processUpdateLightStatusNotification(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Tenant Required
		resetMocksToLightArea();
		String rniCode = request.getUserContext().<Tenant> getTenant().getRniCode();
		request = createUpdateLightNotificationRequest(userContext);
		request.getUserContext().<Tenant> getTenant().setRniCode(null);
		response = getLightProcessorBCF().processUpdateLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(TENANT_NAME));
		request.getUserContext().<Tenant> getTenant().setRniCode(rniCode);

		// Exception situation
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.EXCEPTION, ILightProcessorBCL.class);
		request = createUpdateLightNotificationRequest(userContext);
		response = getLightProcessorBCF().processUpdateLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	@Test
	public void testInitiateUpdateLightIntensity()
	{
		// Success situation
		LightRequest request = createLightRequestToUpdateLightIntensity();
		ProcessResponse response = getLightProcessorBCF().initiateUpdateLightIntensity(request);
		assertTrue(response.isOperationSuccess());

		// Success situation (getting DEFAULT values to OVERRIDE and BLINK STATUS)
		request = createLightRequestToUpdateLightIntensity();

		// set NULL to OVERRIDE and BLINK STATUS in order to get the default values
		request.getActionCriteria().setOverride(null);
		request.getActionCriteria().setBlinkStatus(null);

		response = getLightProcessorBCF().initiateUpdateLightIntensity(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Tenant Required
		resetMocksToLightArea();
		String rniCode = request.getUserContext().<Tenant> getTenant().getRniCode();
		request = createLightRequestToUpdateLightIntensity();
		request.getUserContext().<Tenant> getTenant().setRniCode(null);
		response = getLightProcessorBCF().initiateUpdateLightIntensity(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(TENANT_NAME));
		request.getUserContext().<Tenant> getTenant().setRniCode(rniCode);

		// Validation situation - Per Date Required
		request = createLightRequestToUpdateLightIntensity();
		request.getActionCriteria().setOverridePerDate(null);
		response = getLightProcessorBCF().initiateUpdateLightIntensity(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHT_EXPIRE_PER_DATE_INVALID);

		// Validation situation - Per Date Invalid
		request = createLightRequestToUpdateLightIntensity();
		request.getActionCriteria().setOverridePerDate(SensusDateUtil.getMinDate());
		response = getLightProcessorBCF().initiateUpdateLightIntensity(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHT_EXPIRE_PER_DATE_INVALID);

		// Validation situation - Intensity Required
		request = createLightRequestToUpdateLightIntensity();
		request.getActionCriteria().setPercentage(null);
		response = getLightProcessorBCF().initiateUpdateLightIntensity(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED);

		// Exception situation
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.EXCEPTION, ILightProcessorBCL.class);
		request = createLightRequestToUpdateLightIntensity();
		response = getLightProcessorBCF().initiateUpdateLightIntensity(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test initiate get light status.
	 */
	@Test
	public void testInitiateGetLightStatus()
	{
		UserContext userContext = createUserContext();

		// Success situation
		LightRequest request = createLightRequest(userContext);
		Response response = getLightProcessorBCF().initiateGetLightStatus(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Tenant Required
		resetMocksToLightArea();
		String rniCode = request.getUserContext().<Tenant> getTenant().getRniCode();
		request = createLightRequest(userContext);
		request.getUserContext().<Tenant> getTenant().setRniCode(null);
		response = getLightProcessorBCF().initiateGetLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(TENANT_NAME));
		request.getUserContext().<Tenant> getTenant().setRniCode(rniCode);

		// Exception situation
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.EXCEPTION, ILightProcessorBCL.class);
		request = createLightRequest(userContext);
		response = getLightProcessorBCF().initiateGetLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test initiate delete alert.
	 */
	@Test
	public void testInitiateDeleteAlert()
	{
		UserContext userContext = createUserContext();

		// Success situation
		LightRequest request = createLightRequest(userContext);
		Response response = getLightProcessorBCF().initiateDeleteAlert(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Tenant Required
		resetMocksToLightArea();
		String rniCode = request.getUserContext().<Tenant> getTenant().getRniCode();
		request = createLightRequest(userContext);
		request.getUserContext().<Tenant> getTenant().setRniCode(null);
		response = getLightProcessorBCF().initiateDeleteAlert(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(TENANT_NAME));
		request.getUserContext().<Tenant> getTenant().setRniCode(rniCode);

		// Exception situation
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.EXCEPTION, ILightProcessorBCL.class);
		request = createLightRequest(userContext);
		response = getLightProcessorBCF().initiateDeleteAlert(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test initiate upsert light pole id.
	 */
	@Test
	public void testInitiateUpsertLightPoleId()
	{
		UserContext userContext = createUserContext();

		// Success situation
		LightMaintenanceRequest request = createLightMaintenanceRequest(userContext, createLight());
		Response response = getLightProcessorBCF().initiateUpsertLightPoleId(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Tenant Required
		resetMocksToLightArea();
		String rniCode = request.getUserContext().<Tenant> getTenant().getRniCode();
		request = createLightMaintenanceRequest(userContext, createLight());
		request.setLight(createLight(userContext));
		request.getUserContext().<Tenant> getTenant().setRniCode(null);
		response = getLightProcessorBCF().initiateUpsertLightPoleId(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(TENANT_NAME));
		request.getUserContext().<Tenant> getTenant().setRniCode(rniCode);

		// Error situation - no PoleId
		resetMocksToLightArea();
		request = createLightMaintenanceRequest(userContext, createLight());
		request.getLight().setPoleId(null);
		response = getLightProcessorBCF().initiateUpsertLightPoleId(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_POLEID_REQUIRED);

		// Exception situation
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.EXCEPTION, ILightProcessorBCL.class);
		request = createLightMaintenanceRequest(userContext, createLight());
		response = getLightProcessorBCF().initiateUpsertLightPoleId(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test initiate upsert light lat lng.
	 */
	@Test
	public void testInitiateUpsertLightLatLng()
	{
		UserContext userContext = createUserContext();

		// Success situation
		LightMaintenanceRequest request = createLightMaintenanceRequest(userContext, createLight());
		Response response = getLightProcessorBCF().initiateUpsertLightLatLng(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Tenant Required
		resetMocksToLightArea();
		String rniCode = request.getUserContext().<Tenant> getTenant().getRniCode();
		request = createLightMaintenanceRequest(userContext, createLight());
		request.setLight(createLight(userContext));
		request.getUserContext().<Tenant> getTenant().setRniCode(null);
		response = getLightProcessorBCF().initiateUpsertLightLatLng(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(TENANT_NAME));
		request.getUserContext().<Tenant> getTenant().setRniCode(rniCode);

		// Error situation - no latitude
		resetMocksToLightArea();
		request = createLightMaintenanceRequest(userContext, createLight());
		request.setLight(createLight(userContext));
		request.getLight().getRadio().getLocation().setLatitude(null);
		response = getLightProcessorBCF().initiateUpsertLightLatLng(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LATITUDE_REQUIRED);

		// Error situation - invalid latitude
		resetMocksToLightArea();
		request = createLightMaintenanceRequest(userContext, createLight());
		request.setLight(createLight(userContext));
		request.getLight().getRadio().getLocation().setLatitude(INVALID_LATITUDE);
		response = getLightProcessorBCF().initiateUpsertLightLatLng(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INVALID_LATITUDE);

		// Error situation - no longitude
		resetMocksToLightArea();
		request = createLightMaintenanceRequest(userContext, createLight());
		request.setLight(createLight(userContext));
		request.getLight().getRadio().getLocation().setLongitude(null);
		response = getLightProcessorBCF().initiateUpsertLightLatLng(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LONGITUDE_REQUIRED);

		// Error situation - invalid longitude
		resetMocksToLightArea();
		request = createLightMaintenanceRequest(userContext, createLight());
		request.setLight(createLight(userContext));
		request.getLight().getRadio().getLocation().setLongitude(INVALID_LONGITUDE);
		response = getLightProcessorBCF().initiateUpsertLightLatLng(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INVALID_LONGITUDE);

		// Exception situation
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.EXCEPTION, ILightProcessorBCL.class);
		request = createLightMaintenanceRequest(userContext, createLight());
		response = getLightProcessorBCF().initiateUpsertLightLatLng(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test initiate update light status.
	 */
	@Test
	public void testInitiateUpdateLightStatus()
	{
		UserContext userContext = createUserContext();

		// Success situation
		LightMaintenanceRequest request = createLightMaintenanceRequest(userContext, createLight());
		Response response = getLightProcessorBCF().initiateUpdateLightStatus(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Tenant Required
		resetMocksToLightArea();
		String rniCode = request.getUserContext().<Tenant> getTenant().getRniCode();
		request = createLightMaintenanceRequest(userContext, createLight());
		request.setLight(createLight(userContext));
		request.getUserContext().<Tenant> getTenant().setRniCode(null);
		response = getLightProcessorBCF().initiateUpdateLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList(TENANT_NAME));
		request.getUserContext().<Tenant> getTenant().setRniCode(rniCode);

		// Error situation - no life cycle state
		request = createLightMaintenanceRequest(userContext, createLight());
		request.setLight(createLight(userContext));
		request.getLight().setLifeCycleState(null);
		response = getLightProcessorBCF().initiateUpdateLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED);

		// Exception situation
		resetMocksToLightArea();
		setSituation(getLightProcessorBCF(), SituationsEnum.EXCEPTION, ILightProcessorBCL.class);
		request = createLightMaintenanceRequest(userContext, createLight());
		response = getLightProcessorBCF().initiateUpdateLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Creates the light request to update light intensity.
	 * 
	 * @return the light request
	 */
	private LightRequest createLightRequestToUpdateLightIntensity()
	{
		UserContext userContext = createUserContext();
		LightRequest request = createLightRequest(userContext);
		request.setActionCriteria(new ActionCriteria());
		request.getActionCriteria().setPercentage(ARBITRARY_PERCENTAGE_25);
		request.getActionCriteria().setOverride(OverrideEnum.PER_DATE);
		request.getActionCriteria().setOverridePerDate(new Date());
		request.getActionCriteria().setBlinkStatus(BlinkStatusEnum.FAST);

		return request;
	}
}
