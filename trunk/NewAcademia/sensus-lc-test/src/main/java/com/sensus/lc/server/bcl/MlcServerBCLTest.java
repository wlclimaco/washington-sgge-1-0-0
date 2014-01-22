package com.sensus.lc.server.bcl;

import static com.sensus.lc.base.TestBaseUtil.NUMBER_RANGE;
import static com.sensus.lc.base.TestBaseUtil.RANDOM;
import static com.sensus.lc.base.TestBaseUtil.createUserContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.light.bcf.ILightProcessorBCF;
import com.sensus.lc.light.bcl.ILightBCL;
import com.sensus.lc.process.bcf.IProcessBCF;
import com.sensus.lc.schedule.bcf.IScheduleBCF;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.mlc.mlcserver.type.AlarmWarningInfo;
import com.sensus.mlc.mlcserver.type.AlarmWarningNotification;
import com.sensus.mlc.mlcserver.type.AlarmWarningSubType;
import com.sensus.mlc.mlcserver.type.AlarmWarningType;
import com.sensus.mlc.mlcserver.type.AppCode11Info;
import com.sensus.mlc.mlcserver.type.AppCode71Info;
import com.sensus.mlc.mlcserver.type.AppCode75Info;
import com.sensus.mlc.mlcserver.type.AppCode85Info;
import com.sensus.mlc.mlcserver.type.AppCode86Info;
import com.sensus.mlc.mlcserver.type.AppCode90Info;
import com.sensus.mlc.mlcserver.type.AppCode94Info;
import com.sensus.mlc.mlcserver.type.ApplyDimmingConfigurationNotification;
import com.sensus.mlc.mlcserver.type.ApplyLightLevelNotification;
import com.sensus.mlc.mlcserver.type.ApplyScheduleNotification;
import com.sensus.mlc.mlcserver.type.ApplySmartpointPropertiesNotification;
import com.sensus.mlc.mlcserver.type.ChannelSetupAuditNotification;
import com.sensus.mlc.mlcserver.type.ClearAlarmsNotification;
import com.sensus.mlc.mlcserver.type.ClearScheduleNotification;
import com.sensus.mlc.mlcserver.type.DataType;
import com.sensus.mlc.mlcserver.type.InstallationInfo;
import com.sensus.mlc.mlcserver.type.LightBindingNotification;
import com.sensus.mlc.mlcserver.type.LightDetail;
import com.sensus.mlc.mlcserver.type.LightLevel;
import com.sensus.mlc.mlcserver.type.LightSetupNotification;
import com.sensus.mlc.mlcserver.type.LightState;
import com.sensus.mlc.mlcserver.type.LightStatusNotification;
import com.sensus.mlc.mlcserver.type.LightTopLevelState;
import com.sensus.mlc.mlcserver.type.MessageInfo;
import com.sensus.mlc.mlcserver.type.ReadLightStatusNotification;
import com.sensus.mlc.mlcserver.type.Smartpoint;
import com.sensus.mlc.mlcserver.type.SmartpointStateLevel;
import com.sensus.mlc.mlcserver.type.UpdateLightStatusNotification;

/**
 * The Class serverBCLTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/mlcserver/mlcserverbclimpltest.xml"})
public class MlcServerBCLTest extends AbstractTestBaseBusiness
{

	private static final String SENSUS_MLC_GATEWAY_LIGHT_ALARM_WARNING_NOTIFICATION_REQUIRED =
			"sensus.mlc.gateway.light.alarm.warning.notification.required";

	private static final String APPLY_LIGHT_LEVEL4 = "ApplyLightLevel4";

	/** The Constant ARBITRARY_SUNSET_OFFSET_20. */
	private static final Integer ARBITRARY_SUNSET_OFFSET_20 = 20;

	/** The Constant ARBITRARY_SUNRISE_OFFSET_10. */
	private static final Integer ARBITRARY_SUNRISE_OFFSET_10 = 10;

	/** The Constant ARBITRARY_CONSUPTION_100. */
	private static final Integer ARBITRARY_CONSUPTION_100 = 100;

	/** The Constant ARBITRARY_VOLTAGE_5. */
	private static final Integer ARBITRARY_VOLTAGE_5 = 5;

	/** The Constant ARBITRARY_CURRENT_30. */
	private static final Integer ARBITRARY_CURRENT_30 = 30;

	/** The Constant ARBITRARY_VOLTAGE_120. */
	private static final Integer ARBITRARY_VOLTAGE_120 = 120;

	/** The Constant ARBITRARY_SUNSET_MINUTE_4. */
	private static final Integer ARBITRARY_SUNSET_MINUTE_4 = 4;

	/** The Constant ARBITRARY_SUNSET_HOUR_3. */
	private static final Integer ARBITRARY_SUNSET_HOUR_3 = 3;

	/** The Constant ARBITRARY_CURRENT_10. */
	private static final Integer ARBITRARY_CURRENT_10 = 10;

	/** The Constant ARBITRARY_CONSUMPTION_100. */
	private static final Integer ARBITRARY_CONSUMPTION_100 = 100;

	/** The Constant ARBITRARY_LATITUDE_20. */
	private static final Double ARBITRARY_LATITUDE_20 = 20.0;

	/** The Constant ARBITRARY_LONGITUDE_30. */
	private static final Double ARBITRARY_LONGITUDE_30 = 30.0;

	/** The Constant GOOD. */
	private static final String GOOD = "GOOD";

	/** The Constant APPLY_LIGHT_LEVEL. */
	private static final String APPLY_LIGHT_LEVEL = "ApplyLightLevel";

	/** The Constant FETCH_PROCESS_BY_TRANSACTION_ID. */
	private static final String FETCH_PROCESS_BY_TRANSACTION_ID = "fetchProcessByTransactionId";

	/** The Constant PROCESS_LIGHT_STATUS_NOTIFICATION. */
	private static final String PROCESS_LIGHT_STATUS_NOTIFICATION = "processLightStatusNotification";

	/** The Constant UPDATE_LIGHT_PROPERTIES. */
	private static final String UPDATE_LIGHT_PROPERTIES = "UpdateLightProperties";

	/** The Constant READ_LIGHT_STATUS. */
	private static final String READ_LIGHT_STATUS = "processReadLightStatus";

	/** The Constant CLEAR_SCHEDULE. */
	private static final String CLEAR_SCHEDULE = "ClearSchedule";

	/** The Constant CLEAR_ONE_ALARM. */
	private static final String CLEAR_ONE_ALARM = "ClearOneAlarm";

	/** The Constant CLEAR_ALL_ALARMS. */
	private static final String CLEAR_ALL_ALARMS = "ClearAllAlarms";

	/** The Constant APPLY_SCHEDULE. */
	private static final String APPLY_SCHEDULE = "ApplySchedule";

	/** The Constant NOT_FAIL. */
	private static final String NOT_FAIL = "NotFail";

	/** The Constant LIGHT_CHANNEL_SETUP_AUDIT_NOTIFICATION_REQUIRED. */
	private static final String LIGHT_CHANNEL_SETUP_AUDIT_NOTIFICATION_REQUIRED =
			"sensus.mlc.gateway.channel.setup.audit.notification.required";

	/** The Constant PROCESS_REQUIRED. */
	private static final String PROCESS_REQUIRED = "sensus.mlc.gateway.process.required";

	/** The mlc server bcl. */
	private IMlcServerBCL serverBCL;

	/**
	 * Gets the mlc server bcl.
	 * 
	 * @return the mlc server bcl
	 */
	public IMlcServerBCL getserverBCL()
	{
		return serverBCL;
	}

	/**
	 * Sets the mlc server bcl.
	 * 
	 * @param serverBCL the new mlc server bcl
	 */
	@Resource(name = "mlcServerBCLTarget")
	public void setserverBCL(IMlcServerBCL serverBCL)
	{
		this.serverBCL = serverBCL;
	}

	/**
	 * Sets the mlc servers area.
	 */
	public void setserversArea()
	{
		setArea(getserverBCL(), LCAreaEnum.MLC_SERVER);
	}

	/**
	 * Reset mocks to mlc server area.
	 */
	@After
	public void resetMocksToserverArea()
	{
		resetMocksData(getserverBCL());
		setserversArea();
	}

	/**
	 * Test process apply smartpoint properties notification.
	 */
	@Test
	public void testProcessApplySmartpointPropertiesNotification()
	{
		ApplySmartpointPropertiesNotification notification = new ApplySmartpointPropertiesNotification();

		notification.setTransactionID(UPDATE_LIGHT_PROPERTIES);
		notification.setCustomerID(GOOD);

		Smartpoint smp = new Smartpoint();
		smp.setRniId(1);
		Smartpoint smp2 = new Smartpoint();
		smp2.setRniId(2);
		notification.getSuccededSmartpoint().add(smp);
		notification.getSuccededSmartpoint().add(smp2);

		InternalResponse response = getserverBCL().processApplySmartpointPropertiesNotification(notification);
		assertNotNull(response);
		assertFalse(response.isInError());

		// Error situation
		resetMocksToserverArea();
		setSituation(getserverBCL(), SituationsEnum.ERROR, IProcessBCF.class, FETCH_PROCESS_BY_TRANSACTION_ID);
		response = getserverBCL().processApplySmartpointPropertiesNotification(notification);
		assertMessages(response, PROCESS_REQUIRED);

		resetMocksToserverArea();
		setSituation(getserverBCL(), SituationsEnum.ERROR, ILightProcessorBCF.class,
				"processApplySmartpointPropertiesNotification");
		response = getserverBCL().processApplySmartpointPropertiesNotification(notification);
		assertNotNull(response);
		assertTrue(response.isInError());
	}

	/**
	 * Test process channel setup audit.
	 */
	@Test
	public void testProcessChannelSetupAudit()
	{
		// Success Situation
		ChannelSetupAuditNotification notification = new ChannelSetupAuditNotification();

		AppCode75Info value = new AppCode75Info();
		value.setLightTopLevelState(LightTopLevelState.IDLE);
		notification.setSmartpointDetail(value);

		Smartpoint smartPoint = new Smartpoint();
		smartPoint.setRniId(1);
		notification.setSmartpoint(smartPoint);

		InternalResponse response = getserverBCL().processChannelSetupAudit(notification);
		assertNotNull(response);
		assertFalse(response.isInError());

		setSituation(getserverBCL(), SituationsEnum.ERROR, ILightBCL.class);
		response = getserverBCL().processChannelSetupAudit(notification);
		assertNotNull(response);
		assertMessages(response, LIGHT_CHANNEL_SETUP_AUDIT_NOTIFICATION_REQUIRED);

		setSituation(getserverBCL(), SituationsEnum.ERROR, ILightProcessorBCF.class);
		response = getserverBCL().processChannelSetupAudit(notification);
		assertNotNull(response);
		assertTrue(response.isInError());
	}

	/**
	 * Test process light setup.
	 */
	@Test
	public void testProcessLightSetup()
	{
		LightSetupNotification notification = new LightSetupNotification();
		this.setSituation(getserverBCL(), SituationsEnum.SUCCESS, IMlcServerBCL.class);
		InternalResponse response = getserverBCL().processLightSetup(notification);

		Smartpoint smp = new Smartpoint();
		smp.setRniId(1);
		notification.setSmartpoint(smp);

		this.setSituation(getserverBCL(), SituationsEnum.SUCCESS, IMlcServerBCL.class);
		response = getserverBCL().processLightSetup(notification);
		assertNotNull(response);
		assertFalse(response.isInError());

		this.setSituation(getserverBCL(), SituationsEnum.ERROR, ILightProcessorBCF.class);
		response = getserverBCL().processLightSetup(notification);
		assertNotNull(response);
		assertTrue(response.isInError());

	}

	/**
	 * Test process light binding.
	 */
	@Test
	public void testProcessLightBinding()
	{
		LightBindingNotification notification = new LightBindingNotification();
		InternalResponse response = getserverBCL().processLightBinding(notification);

		Smartpoint smp = new Smartpoint();
		smp.setRniId(1);
		notification.setSmartpoint(smp);
		notification.setCustomerID(GOOD);

		AppCode86Info appCode86Info = new AppCode86Info();
		appCode86Info.setLatitude(ARBITRARY_LATITUDE_20);
		appCode86Info.setLongitude(ARBITRARY_LONGITUDE_30);
		appCode86Info.setPoleId("POLE1");
		appCode86Info.setDeviceVersionMajor(1);
		appCode86Info.setDeviceVersionMinor(1);
		appCode86Info.setDeviceVersionPatch(1);
		notification.setSmartpointDetail(appCode86Info);

		InstallationInfo installationInfo = new InstallationInfo();
		installationInfo.setTimezoneRegion("TimeZone");
		notification.setInstallationDetail(installationInfo);

		response = getserverBCL().processLightBinding(notification);
		assertNotNull(response);
		assertFalse(response.isInError());

		setSituation(getserverBCL(), SituationsEnum.ERROR, ILightProcessorBCF.class);
		response = getserverBCL().processLightBinding(notification);
		assertNotNull(response);
		assertTrue(response.isInError());

	}

	/**
	 * Test process light status.
	 */
	@Test
	public void testProcessLightStatus()
	{
		MessageInfo messageInfo = new MessageInfo();
		LightStatusNotification notification = new LightStatusNotification();
		notification.getMessage().add(messageInfo);
		InternalResponse response = getserverBCL().processLightStatus(notification);

		Smartpoint smp = new Smartpoint();
		smp.setRniId(1);
		notification.setSmartpoint(smp);
		notification.setCustomerID(GOOD);

		AppCode90Info appCode90Info = new AppCode90Info();
		appCode90Info.setConsumption(ARBITRARY_CONSUMPTION_100);
		appCode90Info.setCurrent(ARBITRARY_CURRENT_10);
		appCode90Info.setLightLevel(LightLevel.LEVEL_0);
		appCode90Info.setLightState(LightState.OFF);
		appCode90Info.setSunriseHour(1);
		appCode90Info.setSunriseMinute(2);
		appCode90Info.setSunsetHour(ARBITRARY_SUNSET_HOUR_3);
		appCode90Info.setSunsetMinute(ARBITRARY_SUNSET_MINUTE_4);
		appCode90Info.setVoltage(ARBITRARY_VOLTAGE_120);
		notification.setSmartpointDetail(appCode90Info);

		response = getserverBCL().processLightStatus(notification);
		assertNotNull(response);
		assertFalse(response.isInError());

		// Error
		setSituation(getserverBCL(), SituationsEnum.ERROR, ILightProcessorBCF.class);
		response = getserverBCL().processLightStatus(notification);
		assertNotNull(response);
		assertTrue(response.isInError());
	}

	/**
	 * Test process alarm warning.
	 * 
	 * @throws DatatypeConfigurationException
	 */
	@Test
	public void testProcessAlarmWarning() throws DatatypeConfigurationException
	{
		MessageInfo messageInfo = new MessageInfo();
		messageInfo.setMessageTime(DatatypeFactory.newInstance().newXMLGregorianCalendar());
		AlarmWarningNotification notification = new AlarmWarningNotification();
		notification.getMessage().add(messageInfo);

		InternalResponse response = getserverBCL().processAlarmWarning(notification);
		assertTrue(response.isInError());
		assertMessages(response, SENSUS_MLC_GATEWAY_LIGHT_ALARM_WARNING_NOTIFICATION_REQUIRED);

		Smartpoint smp = new Smartpoint();
		smp.setRniId(1);
		notification.setSmartpoint(smp);
		notification.setCustomerID(GOOD);

		AppCode11Info appCode11Info = new AppCode11Info();
		appCode11Info.setLightState(LightState.UNKNOWN);
		appCode11Info.setCurrent(ARBITRARY_CURRENT_30);
		appCode11Info.setVoltage(ARBITRARY_VOLTAGE_5);

		AlarmWarningInfo alarmWarningInfo = new AlarmWarningInfo();
		alarmWarningInfo.setAlarmWarningType(AlarmWarningType.ALARM);
		alarmWarningInfo.setAlarmWarningSubType(AlarmWarningSubType.LAMP_FAILURE);
		appCode11Info.getAlarmWarningMessages().add(alarmWarningInfo);
		notification.setSmartpointDetail(appCode11Info);

		AlarmWarningInfo alarmWarningInfo2 = new AlarmWarningInfo();
		alarmWarningInfo2.setAlarmWarningType(AlarmWarningType.ALARM);
		alarmWarningInfo2.setAlarmWarningSubType(AlarmWarningSubType.POWER_FAILURE);
		appCode11Info.getAlarmWarningMessages().add(alarmWarningInfo2);

		response = getserverBCL().processAlarmWarning(notification);
		assertNotNull(response);
		assertFalse(response.isInError());

		// Error
		this.setSituation(getserverBCL(), SituationsEnum.ERROR, ILightProcessorBCF.class);
		response = getserverBCL().processAlarmWarning(notification);
		assertNotNull(response);
		assertTrue(response.isInError());
	}

	/**
	 * Test process apply schedule.
	 */
	@Test
	public void testProcessApplySchedule()
	{
		ApplyScheduleNotification notification = new ApplyScheduleNotification();
		notification.setCustomerID(NOT_FAIL);
		notification.setTransactionID(APPLY_SCHEDULE);
		Smartpoint smp = new Smartpoint();
		smp.setRniId(1);
		notification.getSuccededSmartpoint().add(smp);

		InternalResponse response = getserverBCL().processApplySchedule(notification);
		assertNotNull(response);
		assertFalse(response.isInError());

		// For coverage of prepareScheduleRequestForFailure
		notification.setCustomerID("ScheduleRequestForFailure");
		notification.getFailedSmartpoint().add(smp);
		response = getserverBCL().processApplySchedule(notification);
		assertNotNull(response);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Error situation
		setSituation(getserverBCL(), SituationsEnum.ERROR, IProcessBCF.class, FETCH_PROCESS_BY_TRANSACTION_ID);
		response = getserverBCL().processApplySchedule(notification);
		assertMessages(response, PROCESS_REQUIRED);

		resetMocksToserverArea();
		setSituation(getserverBCL(), SituationsEnum.ERROR, IScheduleBCF.class);
		response = getserverBCL().processApplySchedule(notification);
		assertNotNull(response);
		assertTrue(response.isInError());
	}

	/**
	 * Test process clear schedule.
	 */
	@Test
	public void testProcessClearSchedule()
	{
		ClearScheduleNotification notification = new ClearScheduleNotification();
		notification.setTransactionID(CLEAR_SCHEDULE);
		Smartpoint smp = new Smartpoint();
		smp.setRniId(1);
		notification.getSuccededSmartpoint().add(smp);
		InternalResponse response = getserverBCL().processClearSchedule(notification);
		assertNotNull(response);
		assertFalse(response.isInError());

		resetMocksToserverArea();
		setSituation(getserverBCL(), SituationsEnum.ERROR, IScheduleBCF.class);
		response = getserverBCL().processClearSchedule(notification);
		assertNotNull(response);
		assertTrue(response.isInError());

		resetMocksToserverArea();
		setSituation(getserverBCL(), SituationsEnum.ERROR, IProcessBCF.class);
		response = getserverBCL().processClearSchedule(notification);
		assertNotNull(response);
		assertTrue(response.isInError());
	}

	/**
	 * Test apply light level.
	 */
	@Test
	public void testApplyLightLevel()
	{
		ApplyLightLevelNotification notification = new ApplyLightLevelNotification();
		notification.setTransactionID(APPLY_LIGHT_LEVEL);
		Smartpoint smp = new Smartpoint();
		smp.setRniId(1);
		smp.setLightState(LightState.BLINK);
		notification.getSuccededSmartpoint().add(smp);
		smp = new Smartpoint();
		smp.setRniId(2);
		smp.setLightState(LightState.BLINK);
		notification.getSuccededSmartpoint().add(smp);

		InternalResponse response = getserverBCL().processApplyLightLevel(notification);
		assertNotNull(response);
		assertFalse(response.isInError());

		// Error situation
		// Fail because Level_4 is invalid
		setSituation(getserverBCL(), SituationsEnum.SUCCESS, IProcessBCF.class, APPLY_LIGHT_LEVEL4);
		notification.setTransactionID(APPLY_LIGHT_LEVEL4);
		response = getserverBCL().processApplyLightLevel(notification);
		assertNotNull(response);
		assertTrue(response.isInError());

		SmartpointStateLevel smartpointStateLevel = new SmartpointStateLevel();
		smartpointStateLevel.setLightTopLevelState(LightTopLevelState.ACTIVE);
		notification.getSuccededSmartpoint().add(smp);

		setSituation(getserverBCL(), SituationsEnum.ERROR, ILightProcessorBCF.class,
				"processSetLightIntensityNotification");
		response = getserverBCL().processApplyLightLevel(notification);
		assertNotNull(response);
		assertTrue(response.isInError());
	}

	/**
	 * Test process update light status notification.
	 */
	@Test
	public void testProcessUpdateLightStatusNotification()
	{
		UpdateLightStatusNotification notification = new UpdateLightStatusNotification();
		notification.setTransactionID("Good");
		Smartpoint smp = new Smartpoint();
		smp.setRniId(1);

		SmartpointStateLevel smartpointStateLevel = new SmartpointStateLevel();
		smartpointStateLevel.setLightTopLevelState(LightTopLevelState.ACTIVE);
		notification.setSmartpointStateLevel(smartpointStateLevel);

		notification.getSuccededSmartpoint().add(smp);

		InternalResponse response = getserverBCL().processUpdateLightStatusNotification(notification);
		assertNotNull(response);
		assertFalse(response.isInError());

		// Exception
		resetMocksToserverArea();
		setSituation(getserverBCL(), SituationsEnum.ERROR, ILightProcessorBCF.class);
		setArea(getserverBCL(), LCAreaEnum.MLC_SERVER);
		response = getserverBCL().processUpdateLightStatusNotification(notification);
		assertNotNull(response);
		assertTrue(response.isInError());

		notification.setTransactionID("ApplyStatusLevel1");
		smp = new Smartpoint();
		smp.setRniId(2);

		notification.getSuccededSmartpoint().add(smp);

		smartpointStateLevel = new SmartpointStateLevel();
		smartpointStateLevel.setLightTopLevelState(LightTopLevelState.MAINTENANCE);
		notification.setSmartpointStateLevel(smartpointStateLevel);

		response = getserverBCL().processUpdateLightStatusNotification(notification);
		assertNotNull(response);
		assertTrue(response.isInError());

		// Fail because Level_4 is invalid
		notification.setTransactionID("ApplyStatusLevel4");

		smartpointStateLevel = new SmartpointStateLevel();
		smartpointStateLevel.setLightTopLevelState(LightTopLevelState.MAINTENANCE);
		notification.setSmartpointStateLevel(smartpointStateLevel);

		response = getserverBCL().processUpdateLightStatusNotification(notification);
		assertNotNull(response);
		assertTrue(response.isInError());

	}

	private void addAlarmWarningInfo(List<AlarmWarningInfo> alerts, AlarmWarningType type, AlarmWarningSubType subtype)
	{
		AlarmWarningInfo alarmWarningInfo = new AlarmWarningInfo();
		alarmWarningInfo.setAlarmWarningType(type);
		alarmWarningInfo.setAlarmWarningSubType(subtype);
		alerts.add(alarmWarningInfo);
	}

	/**
	 * Test process read light status.
	 */
	@Test
	public void testProcessReadLightStatus()
	{
		UserContext userContext = createUserContext();

		ReadLightStatusNotification notification = new ReadLightStatusNotification();
		notification.getDataType().add(DataType.CONFIGURATION_DATA);
		notification.setCustomerID(userContext.<Tenant> getTenant().getName());
		notification.setTransactionID(String.valueOf(RANDOM.nextInt(NUMBER_RANGE)));

		LightDetail detail = new LightDetail();
		detail.setRniId(RANDOM.nextInt(NUMBER_RANGE));

		AppCode11Info appCode11Data = new AppCode11Info();
		appCode11Data.setLightState(LightState.ON);
		List<AlarmWarningInfo> alerts = appCode11Data.getAlarmWarningMessages();
		addAlarmWarningInfo(alerts, AlarmWarningType.ALARM, AlarmWarningSubType.LAMP_FAILURE);
		addAlarmWarningInfo(alerts, AlarmWarningType.ALARM, AlarmWarningSubType.BOARD_FAILURE);
		addAlarmWarningInfo(alerts, AlarmWarningType.ALARM, AlarmWarningSubType.METROLOGY_ERROR);
		addAlarmWarningInfo(alerts, AlarmWarningType.ALARM, AlarmWarningSubType.METROLOGY_COM_FAILURE);
		addAlarmWarningInfo(alerts, AlarmWarningType.WARNING, AlarmWarningSubType.POWER_SURGE_DETECTED);
		addAlarmWarningInfo(alerts, AlarmWarningType.WARNING, AlarmWarningSubType.BROWN_OUT_DETECTED);
		addAlarmWarningInfo(alerts, AlarmWarningType.WARNING, AlarmWarningSubType.POWER_SURGE_DETECTED);
		addAlarmWarningInfo(alerts, AlarmWarningType.WARNING, AlarmWarningSubType.HIGH_CURRENT);
		addAlarmWarningInfo(alerts, AlarmWarningType.WARNING, AlarmWarningSubType.LOW_CURRENT);
		addAlarmWarningInfo(alerts, AlarmWarningType.WARNING, AlarmWarningSubType.REVERSE_ENERGY);
		addAlarmWarningInfo(alerts, AlarmWarningType.WARNING, AlarmWarningSubType.METROLOGY_RESET);
		detail.setAppCode11Data(appCode11Data);

		AppCode90Info appCode90Info = new AppCode90Info();
		appCode90Info.setConsumption(ARBITRARY_CONSUPTION_100);
		appCode90Info.setCurrent(ARBITRARY_CURRENT_10);
		appCode90Info.setLightLevel(LightLevel.LEVEL_6);
		appCode90Info.setLightState(LightState.ON);
		appCode90Info.setSunriseHour(1);
		appCode90Info.setSunriseMinute(2);
		appCode90Info.setSunsetHour(ARBITRARY_SUNSET_HOUR_3);
		appCode90Info.setSunsetMinute(ARBITRARY_SUNSET_MINUTE_4);
		appCode90Info.setVoltage(ARBITRARY_VOLTAGE_120);
		detail.setAppCode90Data(appCode90Info);

		AppCode85Info appCode85Info = new AppCode85Info();
		appCode85Info.setSunriseOffset(ARBITRARY_SUNRISE_OFFSET_10);
		appCode85Info.setSunsetOffset(ARBITRARY_SUNSET_OFFSET_20);
		detail.setAppCode85Data(appCode85Info);

		AppCode94Info appCode94Info = new AppCode94Info();
		appCode94Info.setBallastSerial(String.valueOf(RANDOM.nextInt(NUMBER_RANGE)));
		appCode94Info.setBulbSerial(String.valueOf(RANDOM.nextInt(NUMBER_RANGE)));
		appCode94Info.setLowerCaseSerial(String.valueOf(RANDOM.nextInt(NUMBER_RANGE)));
		appCode94Info.setUpperCaseSerial(String.valueOf(RANDOM.nextInt(NUMBER_RANGE)));
		appCode94Info.setProductNumber("53934901231314");
		detail.setAppCode94Data(appCode94Info);

		AppCode71Info appCode71Info = new AppCode71Info();
		appCode71Info.setCustomerSerial(String.valueOf(RANDOM.nextInt(NUMBER_RANGE)));
		detail.setAppCode71Data(appCode71Info);

		notification.getLightDetail().add(detail);

		setSituation(getserverBCL(), SituationsEnum.SUCCESS, IProcessBCF.class, READ_LIGHT_STATUS);
		InternalResponse response = getserverBCL().processReadLightStatus(notification);
		assertNotNull(response);
		assertFalse(response.isInError());

		// Test Light ON
		appCode11Data.setLightState(LightState.ON);
		response = getserverBCL().processReadLightStatus(notification);
		assertNotNull(response);
		assertFalse(response.isInError());

		// Test Light blink
		appCode11Data.setLightState(LightState.BLINK);
		response = getserverBCL().processReadLightStatus(notification);
		assertNotNull(response);
		assertFalse(response.isInError());

		// Test Light maintenance
		appCode11Data.setLightState(LightState.MAINTENANCE);
		response = getserverBCL().processReadLightStatus(notification);
		assertNotNull(response);
		assertFalse(response.isInError());

		// Error situation
		setSituation(getserverBCL(), SituationsEnum.ERROR, IProcessBCF.class, FETCH_PROCESS_BY_TRANSACTION_ID);
		response = getserverBCL().processReadLightStatus(notification);
		assertMessages(response, PROCESS_REQUIRED);

		resetMocksToserverArea();
		setSituation(getserverBCL(), SituationsEnum.ERROR, ILightProcessorBCF.class,
				PROCESS_LIGHT_STATUS_NOTIFICATION);
		response = getserverBCL().processReadLightStatus(notification);
		assertNotNull(response);
		assertTrue(response.isInError());
	}

	/**
	 * Test clear alarms.
	 */
	@Test
	public void testClearAlarms()
	{
		ClearAlarmsNotification notification = new ClearAlarmsNotification();
		notification.setCustomerID("PECO");
		notification.setTransactionID(CLEAR_ALL_ALARMS);

		Smartpoint smp = new Smartpoint();
		smp.setRniId(1);
		notification.getSuccededSmartpoint().add(smp);
		InternalResponse response = getserverBCL().processClearAlarms(notification);
		assertNotNull(response);
		assertTrue(!response.isInError());

		// This will clear one POWER_FAILURE from a light that has a POWER_FAILURE and a BROWNOUT
		notification.setTransactionID(CLEAR_ONE_ALARM);
		response = getserverBCL().processClearAlarms(notification);
		assertNotNull(response);
		assertFalse(response.isInError());

		resetMocksToserverArea();
		setSituation(getserverBCL(), SituationsEnum.ERROR, ILightProcessorBCF.class);
		response = getserverBCL().processClearAlarms(notification);
		assertNotNull(response);
		assertTrue(response.isInError());

		resetMocksToserverArea();
		setSituation(getserverBCL(), SituationsEnum.ERROR, IProcessBCF.class);
		response = getserverBCL().processClearAlarms(notification);
		assertNotNull(response);
		assertTrue(response.isInError());
	}

	/**
	 * Test process apply dimming configuration notification.
	 */
	@Test
	public void testProcessApplyDimmingConfigurationNotification()
	{
		ApplyDimmingConfigurationNotification notification = new ApplyDimmingConfigurationNotification();
		notification.setTransactionID(UPDATE_LIGHT_PROPERTIES);
		InternalResponse response = getserverBCL().processApplyDimmingConfigurationNotification(notification);
		assertNotNull(response);
		assertFalse(response.isInError());

		resetMocksToserverArea();
		setSituation(getserverBCL(), SituationsEnum.ERROR, IProcessBCF.class);
		response = getserverBCL().processApplyDimmingConfigurationNotification(notification);
		assertNotNull(response);
		assertTrue(response.isInError());
	}
}
