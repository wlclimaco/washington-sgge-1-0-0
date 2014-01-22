package com.sensus.lc.server.bcf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.Response;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.server.bcl.IMlcServerBCL;
import com.sensus.lc.server.model.request.MlcServerRequest;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.mlc.mlcserver.type.AlarmWarningInfo;
import com.sensus.mlc.mlcserver.type.AlarmWarningNotification;
import com.sensus.mlc.mlcserver.type.AlarmWarningSubType;
import com.sensus.mlc.mlcserver.type.AlarmWarningType;
import com.sensus.mlc.mlcserver.type.AppCode11Info;
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
import com.sensus.mlc.mlcserver.type.LightSetupNotification;
import com.sensus.mlc.mlcserver.type.LightState;
import com.sensus.mlc.mlcserver.type.LightStatusNotification;
import com.sensus.mlc.mlcserver.type.LightTopLevelState;
import com.sensus.mlc.mlcserver.type.ReadLightStatusNotification;
import com.sensus.mlc.mlcserver.type.Smartpoint;
import com.sensus.mlc.mlcserver.type.UpdateLightStatusNotification;

/**
 * The Class MlcServerBCFTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/mlcserver/mlcserverbcfimpltest.xml"})
public class MlcServerBCFTest extends AbstractTestBaseBusiness
{
	/** The Constant ARBITRARY_LONGITUDE_230. */
	private static final Double ARBITRARY_LONGITUDE_230 = 230.0;

	/** The Constant ARBITRARY_LATITUDE_230. */
	private static final Double ARBITRARY_LATITUDE_230 = 230.0;

	/** The Constant ARBITRARY_LATITUDE_23. */
	private static final Double ARBITRARY_LATITUDE_23 = 23.0;

	/** The Constant ARBITRARY_LONGITUDE_23. */
	private static final Double ARBITRARY_LONGITUDE_23 = 23.0;

	/** The Constant ARBITRARY_SUNSET_MINUTE_40. */
	private static final Integer ARBITRARY_SUNSET_MINUTE_40 = 40;

	/** The Constant ARBITRARY_SUNRISE_MINUTE_78. */
	private static final Integer ARBITRARY_SUNRISE_MINUTE_78 = 78;

	/** The Constant ARBITRARY_SUNRISE_HOUR_25. */
	private static final Integer ARBITRARY_SUNRISE_HOUR_25 = 25;

	/** The Constant ARBITRARY_CONSUMPTION_140. */
	private static final Integer ARBITRARY_CONSUMPTION_140 = 140;

	/** The Constant ARBITRARY_VOLTAGE_230. */
	private static final Integer ARBITRARY_VOLTAGE_230 = 230;

	/** The Constant ARBITRARY_SUNRISE_MINUTE_68. */
	private static final Integer ARBITRARY_SUNRISE_MINUTE_68 = 68;

	/** The Constant ARBITRARY_SUNRISE_HOUR_30. */
	private static final Integer ARBITRARY_SUNRISE_HOUR_30 = 30;

	/** The Constant ARBITRARY_CURRENT_23. */
	private static final Integer ARBITRARY_CURRENT_23 = 23;

	/** The Constant ARBITRARY_SUNSET_HOUR_18. */
	private static final Integer ARBITRARY_SUNSET_HOUR_18 = 18;

	/** The Constant ARBITRARY_SUNSET_HOUR_32. */
	private static final Integer ARBITRARY_SUNSET_HOUR_32 = 32;

	/** The Constant ARBITRARY_SUNSET_MINUTE_85. */
	private static final Integer ARBITRARY_SUNSET_MINUTE_85 = 85;

	/** The Constant ARBITRARY_SUNRISE_HOUR_7. */
	private static final Integer ARBITRARY_SUNRISE_HOUR_7 = 7;

	/** The Constant ARBITRARY_MESSAGE_INFO_LIST_SIZE_6. */
	private static final int ARBITRARY_MESSAGE_INFO_LIST_SIZE_6 = 6;

	/** The Constant ARBITRARY_MESSAGE_INFO_LIST_SIZE_7. */
	private static final int ARBITRARY_MESSAGE_INFO_LIST_SIZE_7 = 7;

	/** The Constant ARBITRARY_MESSAGE_INFO_LIST_SIZE_5. */
	private static final int ARBITRARY_MESSAGE_INFO_LIST_SIZE_5 = 5;

	/** The Constant ARBITRARY_TEMPERATURE_5. */
	private static final Integer ARBITRARY_TEMPERATURE_5 = 5;

	/** The Constant ARBITRARY_MESSAGE_INFO_LIST_SIZE_4. */
	private static final int ARBITRARY_MESSAGE_INFO_LIST_SIZE_4 = 4;

	/** The Constant ARBITRARY_MESSAGE_INFO_LIST_SIZE_3. */
	private static final int ARBITRARY_MESSAGE_INFO_LIST_SIZE_3 = 3;

	/** The Constant ARBITRARY_SUNRISE_HOUR_10. */
	private static final Integer ARBITRARY_SUNRISE_HOUR_10 = 10;

	/** The Constant ARBITRARY_VOLTAGE_10. */
	private static final Integer ARBITRARY_VOLTAGE_10 = 10;

	/** The Constant ARBITRARY_SUNRISE_MINUTE_10. */
	private static final Integer ARBITRARY_SUNRISE_MINUTE_10 = 10;

	/** The Constant ARBITRARY_CURRENT_10. */
	private static final Integer ARBITRARY_CURRENT_10 = 10;

	/** The Constant ARBITRARY_SUNSET_OFFSET_10. */
	private static final Integer ARBITRARY_SUNSET_OFFSET_10 = 10;

	/** The Constant ARBITRARY_SUNRISE_OFFSET_10. */
	private static final Integer ARBITRARY_SUNRISE_OFFSET_10 = 10;

	/** The Constant ARBITRARY_SUNSET_MINUTE_10. */
	private static final Integer ARBITRARY_SUNSET_MINUTE_10 = 10;

	/** The Constant ARBITRARY_SUNSET_HOUR_10. */
	private static final Integer ARBITRARY_SUNSET_HOUR_10 = 10;

	/** The Constant ACME. */
	private static final String ACME = "ACME";

	/** The Constant GOOD. */
	private static final String GOOD = "GOOD";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG =
			"sensus.mlc.serverbclimpl.defaultexception";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED =
			"sensus.mlc.lightvalidator.lightrniid.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED =
			"sensus.mlc.lightvalidator.schedule.properties.required";

	/** The Constant SENSUS_MLC_TRANSACTION_ID_REQUIRED. */
	private static final String SENSUS_MLC_TRANSACTION_ID_REQUIRED =
			"sensus.mlc.transactionid.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_PROPERTIES_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_PROPERTIES_REQUIRED =
			"sensus.mlc.lightvalidator.properties.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_LIGHT_STATE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LIGHT_STATE_REQUIRED =
			"sensus.mlc.lightvalidator.light.state.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED =
			"sensus.mlc.lightvalidator.light.top.level.state.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_VOLTAGE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_VOLTAGE_REQUIRED =
			"sensus.mlc.lightvalidator.voltage.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_TEMPERATURE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_TEMPERATURE_REQUIRED =
			"sensus.mlc.lightvalidator.temperature.required";

	/** The Constant SENSUS_MLC_GATEWAY_ALARM_WARNING_INVALID_COMBINATION. */
	private static final String SENSUS_MLC_GATEWAY_ALARM_WARNING_INVALID_COMBINATION =
			"sensus.mlc.gateway.alarm.warning.invalid.combination";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_SERIAL_NUMBERS_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_SERIAL_NUMBERS_REQUIRED =
			"sensus.mlc.lightvalidator.serial.numbers.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_LIGHT_STATUS_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LIGHT_STATUS_REQUIRED =
			"sensus.mlc.lightvalidator.light.status.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_HOUR_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_HOUR_REQUIRED =
			"sensus.mlc.lightvalidator.hour.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_MINUTE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_MINUTE_REQUIRED =
			"sensus.mlc.lightvalidator.minute.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_CONSUMPTION_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_CONSUMPTION_REQUIRED =
			"sensus.mlc.lightvalidator.consumption.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INVALID_HOUR. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INVALID_HOUR =
			"sensus.mlc.lightvalidator.invalid.hour";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INVALID_MINUTE. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INVALID_MINUTE =
			"sensus.mlc.lightvalidator.invalid.minute";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_SUNSETOFFSET_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_SUNSETOFFSET_REQUIRED =
			"sensus.mlc.lightvalidator.sunset.offset.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_LOWER_CASE_SERIAL_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LOWER_CASE_SERIAL_REQUIRED =
			"sensus.mlc.lightvalidator.lower.case.serial.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_UPPER_CASE_SERIAL_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_UPPER_CASE_SERIAL_REQUIRED =
			"sensus.mlc.lightvalidator.upper.case.serial.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_BULB_SERIAL_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_BULB_SERIAL_REQUIRED =
			"sensus.mlc.lightvalidator.bulb.serial.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_PRODUCT_NUMBER_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_PRODUCT_NUMBER_REQUIRED =
			"sensus.mlc.lightvalidator.product.number.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INSTALLATION_INFO_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INSTALLATION_INFO_REQUIRED =
			"sensus.mlc.lightvalidator.installation.info.required";

	/** The Constant SENSUS_MLC_VALIDATOR_POLEID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_POLEID_REQUIRED =
			"sensus.mlc.validator.poleid.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_FIRMWARE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_FIRMWARE_REQUIRED =
			"sensus.mlc.lightvalidator.firmware.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_LONGITUDE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LONGITUDE_REQUIRED =
			"sensus.mlc.lightvalidator.longitude.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_LATITUDE_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LATITUDE_REQUIRED =
			"sensus.mlc.lightvalidator.latitude.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INVALID_LATITUDE. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INVALID_LATITUDE =
			"sensus.mlc.lightvalidator.invalid.latitude";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INVALID_LONGITUDE. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INVALID_LONGITUDE =
			"sensus.mlc.lightvalidator.invalid.longitude";

	/** The mlc server bcf. */
	private IMlcServerBCF mlcServerBCF;

	/**
	 * Gets the mlc server bcf.
	 * 
	 * @return the mlc server bcf
	 */
	public IMlcServerBCF getMlcServerBCF()
	{
		return mlcServerBCF;
	}

	/**
	 * Sets the mlc server bcf.
	 * 
	 * @param mlcServerBCF the new mlc server bcf
	 */
	@Resource(name = "mlcServerBCFTarget")
	public void setMlcServerBCF(IMlcServerBCF mlcServerBCF)
	{
		this.mlcServerBCF = mlcServerBCF;
	}

	/**
	 * Sets the mlc servers area.
	 */
	public void setMlcServersArea()
	{
		setArea(getMlcServerBCF(), LCAreaEnum.MLC_SERVER);
	}

	/**
	 * Removes the eco mode area.
	 */
	@After
	public void resetMocksToMlcServerArea()
	{
		resetMocksData(getMlcServerBCF());
		setMlcServersArea();
	}

	/**
	 * Test process apply smartpoint properties notification.
	 */
	@Test
	public void testProcessApplySmartpointPropertiesNotification()
	{
		MlcServerRequest request = new MlcServerRequest(new UserContext());

		// create notification but do not set transactionId or customerId
		ApplySmartpointPropertiesNotification notification = new ApplySmartpointPropertiesNotification();

		// Create a new smartpoint but do not set RniId
		Smartpoint smartpoint = new Smartpoint();

		notification.getSuccededSmartpoint().add(smartpoint);

		request.setApplySmartpointPropertiesNotification(notification);
		Response response = getMlcServerBCF().processApplySmartpointPropertiesNotification(request);
		assertMessages(response, SENSUS_MLC_TRANSACTION_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED);

		notification.setCustomerID(ACME);
		request.setApplySmartpointPropertiesNotification(notification);
		response = getMlcServerBCF().processApplySmartpointPropertiesNotification(request);
		assertMessages(response, SENSUS_MLC_TRANSACTION_ID_REQUIRED);

		notification.setTransactionID(GOOD);
		request.setApplySmartpointPropertiesNotification(notification);
		response = getMlcServerBCF().processApplySmartpointPropertiesNotification(request);
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		smartpoint.setRniId(1);
		notification.getSuccededSmartpoint().add(smartpoint);
		response = getMlcServerBCF().processApplySmartpointPropertiesNotification(request);
		assertTrue(response.isOperationSuccess());

		setSituation(getMlcServerBCF(), SituationsEnum.ERROR, IMlcServerBCL.class);
		request.setApplySmartpointPropertiesNotification(notification);
		response = getMlcServerBCF().processApplySmartpointPropertiesNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

		// Test Exception
		setSituation(getMlcServerBCF(), SituationsEnum.EXCEPTION, IMlcServerBCL.class);
		request.setApplySmartpointPropertiesNotification(notification);
		response = getMlcServerBCF().processApplySmartpointPropertiesNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

	}

	/**
	 * Test process alarm warning notification.
	 */
	@Test
	public void testProcessAlarmWarningNotification()
	{
		MlcServerRequest request = new MlcServerRequest(new UserContext());

		// create notification but do not set transactionId or customerId
		AlarmWarningNotification notification = new AlarmWarningNotification();

		// Create a new smartpoint but do not set RniId
		Smartpoint smartpoint = new Smartpoint();
		notification.setSmartpoint(smartpoint);

		request.setAlarmWarningNotification(notification);
		Response response = getMlcServerBCF().processAlarmWarningNotification(request);

		// Expect 2 errors: No tenant, no RniId
		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		notification.setCustomerID(ACME);
		smartpoint.setRniId(1);

		AppCode11Info appCode11Data = new AppCode11Info();
		appCode11Data.setCurrent(ARBITRARY_CURRENT_10);
		notification.setSmartpointDetail(appCode11Data);
		response = getMlcServerBCF().processAlarmWarningNotification(request);
		// Expect 4 errors: No Voltage, Temperature,LightState,LightTopLevelState
		assertEquals(ARBITRARY_MESSAGE_INFO_LIST_SIZE_4, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_VOLTAGE_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_TEMPERATURE_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_STATE_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED);

		appCode11Data.setVoltage(ARBITRARY_VOLTAGE_10);
		response = getMlcServerBCF().processAlarmWarningNotification(request);
		// Expect 3 errors: Temperature,LightState,LightTopLevelState
		assertEquals(ARBITRARY_MESSAGE_INFO_LIST_SIZE_3, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_TEMPERATURE_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_STATE_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED);

		appCode11Data.setTemperature(ARBITRARY_SUNSET_HOUR_32);
		response = getMlcServerBCF().processAlarmWarningNotification(request);
		// Expect 2 errors: LightState,LightTopLevelState
		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_STATE_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED);

		appCode11Data.setLightState(LightState.ON);
		response = getMlcServerBCF().processAlarmWarningNotification(request);

		// Expect 1 errors:LightTopLevelState
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED);

		appCode11Data.setLightTopLevelState(LightTopLevelState.ACTIVE);
		response = getMlcServerBCF().processAlarmWarningNotification(request);

		notification.setTransactionID(GOOD);
		request.setAlarmWarningNotification(notification);
		notification.setSmartpoint(smartpoint);
		request.setAlarmWarningNotification(notification);
		response = getMlcServerBCF().processAlarmWarningNotification(request);

		// Expect 0 errors: no alarms is ok
		assertEquals(0, response.getMessageInfoList().size());

		AlarmWarningInfo alarmWarning = new AlarmWarningInfo();

		appCode11Data.getAlarmWarningMessages().add(alarmWarning);
		appCode11Data.getAlarmWarningMessages().get(0)
				.setAlarmWarningType(AlarmWarningType.WARNING);
		appCode11Data.getAlarmWarningMessages().get(0)
				.setAlarmWarningSubType(AlarmWarningSubType.POWER_FAILURE);

		response = getMlcServerBCF().processAlarmWarningNotification(request);

		// Expect 1 error: Invalid Alarm/Warning combination
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_GATEWAY_ALARM_WARNING_INVALID_COMBINATION);

		notification.getSmartpointDetail().getAlarmWarningMessages().get(0)
				.setAlarmWarningSubType(AlarmWarningSubType.POWER_SURGE_DETECTED);
		response = getMlcServerBCF().processAlarmWarningNotification(request);

		// Expect 0 errors
		assertTrue(response.isOperationSuccess());

		// Test Error
		setSituation(getMlcServerBCF(), SituationsEnum.ERROR, IMlcServerBCL.class);
		request.setAlarmWarningNotification(notification);
		response = getMlcServerBCF().processAlarmWarningNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

		// Test Exception
		setSituation(getMlcServerBCF(), SituationsEnum.EXCEPTION, IMlcServerBCL.class);
		request.setAlarmWarningNotification(notification);
		response = getMlcServerBCF().processAlarmWarningNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

	}

	/**
	 * Test process read light status notification.
	 */
	@Test
	public void testProcessReadLightStatusNotification()
	{
		MlcServerRequest request = new MlcServerRequest(new UserContext());
		// create notification but do not set transactionId or customerId
		ReadLightStatusNotification notification = new ReadLightStatusNotification();
		request.setReadLightStatusNotification(notification);
		Response response = getMlcServerBCF().processReadLightStatusNotification(request);

		// Expect 2 errors: No tenant, no transactionId
		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_TRANSACTION_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Expect 1 errors: no transactionId
		notification.setCustomerID(ACME);
		request.setReadLightStatusNotification(notification);
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_TRANSACTION_ID_REQUIRED);

		// Expect 3 errors: no properties (AppCode90 and AppCode85) and no serial numbers
		notification.setTransactionID(GOOD);
		request.setReadLightStatusNotification(notification);
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		LightDetail lightDetail = new LightDetail();
		lightDetail.setRniId(1);
		AppCode11Info appCode11Data = new AppCode11Info();
		appCode11Data.setCurrent(ARBITRARY_CURRENT_10);
		appCode11Data.setVoltage(ARBITRARY_VOLTAGE_10);
		appCode11Data.setTemperature(ARBITRARY_TEMPERATURE_5);
		appCode11Data.setLightState(LightState.ON);
		appCode11Data.setLightTopLevelState(LightTopLevelState.ACTIVE);
		lightDetail.setAppCode11Data(appCode11Data);
		notification.getLightDetail().add(lightDetail);
		request.setReadLightStatusNotification(notification);
		notification.getDataType().add(DataType.CONFIGURATION_DATA);
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertEquals(ARBITRARY_MESSAGE_INFO_LIST_SIZE_3, response.getMessageInfoList().size());
		assertMessages(response,
				SENSUS_MLC_LIGHTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED,
				SENSUS_MLC_LIGHTVALIDATOR_PROPERTIES_REQUIRED,
				SENSUS_MLC_LIGHTVALIDATOR_SERIAL_NUMBERS_REQUIRED);

		// Expect 7 errors / AppCode90
		AppCode90Info appCode90Info = new AppCode90Info();
		appCode90Info.setCurrent(ARBITRARY_CURRENT_23);
		appCode90Info.setSunriseHour(ARBITRARY_SUNRISE_HOUR_30);
		appCode90Info.setSunriseMinute(ARBITRARY_SUNRISE_MINUTE_68);
		notification.getLightDetail().get(0).setAppCode90Data(appCode90Info);
		request.setReadLightStatusNotification(notification);
		notification.getDataType().clear();
		notification.getDataType().add(DataType.STATUS_DATA);
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertEquals(ARBITRARY_MESSAGE_INFO_LIST_SIZE_7, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_STATUS_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_INVALID_HOUR
				, SENSUS_MLC_LIGHTVALIDATOR_INVALID_MINUTE
				, SENSUS_MLC_LIGHTVALIDATOR_HOUR_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_MINUTE_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_VOLTAGE_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_CONSUMPTION_REQUIRED);

		// Expect 6 errors
		notification.getLightDetail().get(0).getAppCode90Data().setVoltage(ARBITRARY_VOLTAGE_230);
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertEquals(ARBITRARY_MESSAGE_INFO_LIST_SIZE_6, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_STATUS_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_INVALID_HOUR
				, SENSUS_MLC_LIGHTVALIDATOR_INVALID_MINUTE
				, SENSUS_MLC_LIGHTVALIDATOR_HOUR_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_MINUTE_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_CONSUMPTION_REQUIRED);

		// Expect 5 errors
		notification.getLightDetail().get(0).getAppCode90Data().setConsumption(ARBITRARY_CONSUMPTION_140);
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertEquals(ARBITRARY_MESSAGE_INFO_LIST_SIZE_5, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_STATUS_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_INVALID_HOUR
				, SENSUS_MLC_LIGHTVALIDATOR_INVALID_MINUTE
				, SENSUS_MLC_LIGHTVALIDATOR_HOUR_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_MINUTE_REQUIRED);

		// Expect 4 errors
		notification.getLightDetail().get(0).getAppCode90Data().setSunriseHour(ARBITRARY_SUNRISE_HOUR_10);
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertEquals(ARBITRARY_MESSAGE_INFO_LIST_SIZE_4, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_STATUS_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_INVALID_MINUTE
				, SENSUS_MLC_LIGHTVALIDATOR_HOUR_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_MINUTE_REQUIRED);

		// Expect 3 errors
		notification.getLightDetail().get(0).getAppCode90Data().setSunriseMinute(ARBITRARY_SUNRISE_MINUTE_10);
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertEquals(ARBITRARY_MESSAGE_INFO_LIST_SIZE_3, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_STATUS_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_HOUR_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_MINUTE_REQUIRED);

		// Expect 2 errors
		notification.getLightDetail().get(0).getAppCode90Data().setSunsetHour(ARBITRARY_SUNSET_HOUR_10);
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_STATUS_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_MINUTE_REQUIRED);

		// Expect 1 errors
		notification.getLightDetail().get(0).getAppCode90Data().setSunsetMinute(ARBITRARY_SUNSET_MINUTE_10);
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_STATUS_REQUIRED);

		notification.getLightDetail().get(0).getAppCode90Data().setLightState(LightState.ON);
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertEquals(0, response.getMessageInfoList().size());

		// AppCode85
		AppCode85Info appCode85Info = new AppCode85Info();
		appCode85Info.setSunriseOffset(ARBITRARY_SUNRISE_OFFSET_10);
		notification.getLightDetail().get(0).setAppCode85Data(appCode85Info);
		notification.getDataType().add(DataType.CONFIGURATION_DATA);
		response = getMlcServerBCF().processReadLightStatusNotification(request);

		// Expect 2 errors (SunsetOffset and Serial Numbers)
		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_SERIAL_NUMBERS_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_SUNSETOFFSET_REQUIRED);

		// Expect 1 error (Serial Numbers)
		notification.getLightDetail().get(0).getAppCode85Data().setSunsetOffset(ARBITRARY_SUNSET_OFFSET_10);
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_SERIAL_NUMBERS_REQUIRED);

		// Expect 4 errors
		AppCode94Info appCode94Info = new AppCode94Info();
		appCode94Info.setBallastSerial("AAA");
		notification.getLightDetail().get(0).setAppCode94Data(appCode94Info);
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertEquals(ARBITRARY_MESSAGE_INFO_LIST_SIZE_4, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LOWER_CASE_SERIAL_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_UPPER_CASE_SERIAL_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_BULB_SERIAL_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_PRODUCT_NUMBER_REQUIRED);

		// Expect 3 errors
		appCode94Info.setBulbSerial("BBB");
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertEquals(ARBITRARY_MESSAGE_INFO_LIST_SIZE_3, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LOWER_CASE_SERIAL_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_UPPER_CASE_SERIAL_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_PRODUCT_NUMBER_REQUIRED);

		// Expect 2 errors
		appCode94Info.setLowerCaseSerial("CCC");
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_UPPER_CASE_SERIAL_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_PRODUCT_NUMBER_REQUIRED);

		// Expect 1 errors
		appCode94Info.setUpperCaseSerial("DDD");
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_PRODUCT_NUMBER_REQUIRED);

		// Expect 0 errors
		appCode94Info.setProductNumber("539-34-901-23131");
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertEquals(0, response.getMessageInfoList().size());

		testProcessReadLightStatusNotificationErrorException(request, response, notification);
	}

	/**
	 * Test process read light status notification error exception.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param notification the notification
	 */
	private void testProcessReadLightStatusNotificationErrorException(MlcServerRequest request, Response response,
			ReadLightStatusNotification notification)
	{
		// Error situation
		setSituation(getMlcServerBCF(), SituationsEnum.ERROR, IMlcServerBCL.class);
		request.setReadLightStatusNotification(notification);
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

		// Test Exception
		setSituation(getMlcServerBCF(), SituationsEnum.EXCEPTION, IMlcServerBCL.class);
		request.setReadLightStatusNotification(notification);
		response = getMlcServerBCF().processReadLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);
	}

	/**
	 * Test process clear alarms notification.
	 */
	@Test
	public void testProcessClearAlarmsNotification()
	{
		MlcServerRequest request = new MlcServerRequest(new UserContext());

		ClearAlarmsNotification notification = new ClearAlarmsNotification();

		// Create a new smartpoint but do not set RniId
		Smartpoint smartpoint = new Smartpoint();

		notification.getSuccededSmartpoint().add(smartpoint);

		// Expect 2 errors: Tenant, transaction
		request.setClearAlarmsNotification(notification);
		Response response = getMlcServerBCF().processClearAlarmsNotification(request);
		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_TRANSACTION_ID_REQUIRED
				, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Expect 1 errors: Transaction
		notification.setCustomerID(ACME);
		response = getMlcServerBCF().processClearAlarmsNotification(request);

		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_TRANSACTION_ID_REQUIRED);

		// Expect 1 errors: no RniId
		notification.setTransactionID(GOOD);
		response = getMlcServerBCF().processClearAlarmsNotification(request);

		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Expect Success
		smartpoint.setRniId(1);
		notification.getSuccededSmartpoint().add(smartpoint);

		response = getMlcServerBCF().processClearAlarmsNotification(request);
		assertTrue(response.isOperationSuccess());

		// Test Error
		setSituation(getMlcServerBCF(), SituationsEnum.ERROR, IMlcServerBCL.class);
		response = getMlcServerBCF().processClearAlarmsNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

		// Test Exception
		setSituation(getMlcServerBCF(), SituationsEnum.EXCEPTION, IMlcServerBCL.class);
		request.setClearAlarmsNotification(notification);
		response = getMlcServerBCF().processClearAlarmsNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);
	}

	/**
	 * Test process light status notification.
	 */
	@Test
	public void testProcessLightStatusNotification()
	{
		MlcServerRequest request = new MlcServerRequest(new UserContext());

		// create notification but do not set transactionId or customerId
		LightStatusNotification notification = new LightStatusNotification();

		// Create a new smartpoint but do not set RniId
		Smartpoint smartpoint = new Smartpoint();

		// No light details

		notification.setSmartpoint(smartpoint);

		request.setLightStatusNotification(notification);
		Response response = getMlcServerBCF().processLightStatusNotification(request);

		// Expect 2 errors: No tenant, no RniId
		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED
				, SENSUS_MLC_VALIDATOR_REQUIRED);

		notification.setCustomerID(ACME);
		request.setLightStatusNotification(notification);
		response = getMlcServerBCF().processLightStatusNotification(request);

		// Expect 1 errors: no RniId
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		request.setLightStatusNotification(notification);
		response = getMlcServerBCF().processLightStatusNotification(request);

		smartpoint.setRniId(1);
		notification.setSmartpoint(smartpoint);
		request.setLightStatusNotification(notification);
		response = getMlcServerBCF().processLightStatusNotification(request);

		// Expect 1 error: no properties
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED);

		notification = new LightStatusNotification();
		smartpoint.setRniId(1);
		notification.setSmartpoint(smartpoint);
		AppCode90Info appCode90Info = new AppCode90Info();
		notification.setSmartpointDetail(appCode90Info);
		request.setLightStatusNotification(notification);
		response = getMlcServerBCF().processLightStatusNotification(request);

		// Expect 1 errors No customer Id
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		appCode90Info.setLightState(LightState.ON);
		notification.setSmartpointDetail(appCode90Info);
		response = getMlcServerBCF().processLightStatusNotification(request);

		// Expect 1 errors: custumer id
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		notification.setCustomerID(ACME);
		notification.setSmartpointDetail(appCode90Info);
		response = getMlcServerBCF().processLightStatusNotification(request);

		// Success
		assertEquals(0, response.getMessageInfoList().size());

		appCode90Info.setSunriseHour(ARBITRARY_SUNRISE_HOUR_25);
		appCode90Info.setSunriseMinute(ARBITRARY_SUNRISE_MINUTE_78);
		notification.setSmartpointDetail(appCode90Info);
		response = getMlcServerBCF().processLightStatusNotification(request);

		// Expect 2 error: Invalid Sunrise Hour and Minute
		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INVALID_HOUR
				, SENSUS_MLC_LIGHTVALIDATOR_INVALID_MINUTE);

		appCode90Info.setSunriseHour(ARBITRARY_SUNRISE_HOUR_7);
		notification.setSmartpointDetail(appCode90Info);
		response = getMlcServerBCF().processLightStatusNotification(request);

		// Expect 1 error: Invalid SunriseMinute minute
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INVALID_MINUTE);

		appCode90Info.setSunriseMinute(ARBITRARY_SUNRISE_MINUTE_10);
		notification.setSmartpointDetail(appCode90Info);
		response = getMlcServerBCF().processLightStatusNotification(request);

		// Success Sunrise Hour and minute
		assertEquals(0, response.getMessageInfoList().size());

		appCode90Info.setSunsetHour(ARBITRARY_SUNSET_HOUR_32);
		appCode90Info.setSunsetMinute(ARBITRARY_SUNSET_MINUTE_85);
		notification.setSmartpointDetail(appCode90Info);
		response = getMlcServerBCF().processLightStatusNotification(request);

		// Expect 2 error: Invalid Sunset Hour and Minute
		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INVALID_HOUR
				, SENSUS_MLC_LIGHTVALIDATOR_INVALID_MINUTE);

		appCode90Info.setSunsetHour(ARBITRARY_SUNSET_HOUR_18);
		notification.setSmartpointDetail(appCode90Info);
		response = getMlcServerBCF().processLightStatusNotification(request);

		// Expect 1 error: Invalid Sunset Minute
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INVALID_MINUTE);

		appCode90Info.setSunsetMinute(ARBITRARY_SUNSET_MINUTE_40);
		notification.setSmartpointDetail(appCode90Info);
		response = getMlcServerBCF().processLightStatusNotification(request);

		// SUCCESS Sunset Hour and Minute
		assertEquals(0, response.getMessageInfoList().size());

		appCode90Info.setLightState(LightState.ON);
		notification.setSmartpointDetail(appCode90Info);
		response = getMlcServerBCF().processLightStatusNotification(request);

		// Expect 0 errors
		assertTrue(response.isOperationSuccess());

		setSituation(getMlcServerBCF(), SituationsEnum.ERROR, IMlcServerBCL.class);
		request.setLightStatusNotification(notification);
		response = getMlcServerBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

		// Test Exception
		setSituation(getMlcServerBCF(), SituationsEnum.EXCEPTION, IMlcServerBCL.class);
		request.setLightStatusNotification(notification);
		response = getMlcServerBCF().processLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);
	}

	/**
	 * Test process channel setup audit notification.
	 */
	@Test
	public void testProcessChannelSetupAuditNotification()
	{
		MlcServerRequest request = new MlcServerRequest(new UserContext());

		// create notification but do not set transactionId or customerId
		ChannelSetupAuditNotification notification = new ChannelSetupAuditNotification();

		// Create a new smartpoint but do not set RniId
		Smartpoint smartpoint = new Smartpoint();

		notification.setSmartpoint(smartpoint);

		request.setChannelSetupAuditNotification(notification);
		Response response = getMlcServerBCF().processChannelSetupAuditNotification(request);

		// No customer id, RniId
		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED
				, SENSUS_MLC_VALIDATOR_REQUIRED);

		notification.setCustomerID(ACME);
		request.setChannelSetupAuditNotification(notification);
		response = getMlcServerBCF().processChannelSetupAuditNotification(request);

		// No RniId
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		smartpoint.setRniId(1);
		notification.setSmartpoint(smartpoint);
		response = getMlcServerBCF().processChannelSetupAuditNotification(request);

		// No LightState
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_PROPERTIES_REQUIRED);

		AppCode75Info value = new AppCode75Info();
		value.setLightTopLevelState(LightTopLevelState.ACTIVE);
		notification.setSmartpointDetail(value);

		response = getMlcServerBCF().processChannelSetupAuditNotification(request);

		assertEquals(0, response.getMessageInfoList().size());

		setSituation(getMlcServerBCF(), SituationsEnum.ERROR, IMlcServerBCL.class);
		request.setChannelSetupAuditNotification(notification);
		response = getMlcServerBCF().processChannelSetupAuditNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

		// Test Exception
		setSituation(getMlcServerBCF(), SituationsEnum.EXCEPTION, IMlcServerBCL.class);
		request.setChannelSetupAuditNotification(notification);
		response = getMlcServerBCF().processChannelSetupAuditNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

	}

	/**
	 * Test process light setup notification.
	 */
	@Test
	public void testProcessLightSetupNotification()
	{
		MlcServerRequest request = new MlcServerRequest(new UserContext());

		// create notification but do not set transactionId or customerId
		LightSetupNotification notification = new LightSetupNotification();

		// Create a new smartpoint but do not set RniId
		Smartpoint smartpoint = new Smartpoint();

		notification.setSmartpoint(smartpoint);

		request.setLightSetupNotification(notification);
		Response response = getMlcServerBCF().processLightSetupNotification(request);

		// No customer id, RniId
		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED
				, SENSUS_MLC_VALIDATOR_REQUIRED);

		notification.setCustomerID(ACME);
		request.setLightSetupNotification(notification);
		response = getMlcServerBCF().processLightSetupNotification(request);

		// No RniId
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		smartpoint.setRniId(1);
		notification.setSmartpoint(smartpoint);

		response = getMlcServerBCF().processLightSetupNotification(request);
		assertTrue(response.isOperationSuccess());

		setSituation(getMlcServerBCF(), SituationsEnum.ERROR, IMlcServerBCL.class);
		request.setLightSetupNotification(notification);
		response = getMlcServerBCF().processLightSetupNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

		// Test Exception
		setSituation(getMlcServerBCF(), SituationsEnum.EXCEPTION, IMlcServerBCL.class);
		request.setLightSetupNotification(notification);
		response = getMlcServerBCF().processLightSetupNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

	}

	/**
	 * Test process light binding notification.
	 */
	@Test
	public void testProcessLightBindingNotification()
	{
		MlcServerRequest request = new MlcServerRequest(new UserContext());

		// create notification but do not set transactionId or customerId
		LightBindingNotification notification = new LightBindingNotification();

		// Create a new smartpoint but do not set RniId
		Smartpoint smartpoint = new Smartpoint();

		// No light details
		notification.setSmartpoint(smartpoint);

		request.setLightBindingNotification(notification);
		Response response = getMlcServerBCF().processLightBindingNotification(request);

		// Expect 4 errors: No tenant, no RniId
		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED
				, SENSUS_MLC_VALIDATOR_REQUIRED);

		notification.setCustomerID(ACME);
		request.setLightBindingNotification(notification);
		response = getMlcServerBCF().processLightBindingNotification(request);

		// Expect 1 errors: no RniId
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		notification.setTransactionID(GOOD);
		request.setLightBindingNotification(notification);
		response = getMlcServerBCF().processLightBindingNotification(request);

		smartpoint.setRniId(1);
		notification.setSmartpoint(smartpoint);
		request.setLightBindingNotification(notification);
		response = getMlcServerBCF().processLightBindingNotification(request);

		// Expect 2 errors: no properties, no timezone
		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_PROPERTIES_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_INSTALLATION_INFO_REQUIRED);

		InstallationInfo installationInfo = new InstallationInfo();
		installationInfo.setTimezoneRegion("TimeZone");
		notification.setInstallationDetail(installationInfo);
		request.setLightBindingNotification(notification);
		response = getMlcServerBCF().processLightBindingNotification(request);

		// Expect 1 errors: no properties
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_PROPERTIES_REQUIRED);

		AppCode86Info appCode86Info = new AppCode86Info();
		appCode86Info.setLatitude(ARBITRARY_LATITUDE_23);
		notification.setSmartpointDetail(appCode86Info);
		response = getMlcServerBCF().processLightBindingNotification(request);

		// Expect 4 errors: no firmware version (minor,major,patch), no pole id
		assertEquals(ARBITRARY_MESSAGE_INFO_LIST_SIZE_4, response.getMessageInfoList().size());
		assertMessages(response,
				SENSUS_MLC_VALIDATOR_POLEID_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_FIRMWARE_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_FIRMWARE_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_FIRMWARE_REQUIRED);

		appCode86Info.setLongitude(ARBITRARY_LONGITUDE_23);
		response = getMlcServerBCF().processLightBindingNotification(request);

		// Expect 4 errors: no firmware version (minor,major,patch), no pole id
		assertEquals(ARBITRARY_MESSAGE_INFO_LIST_SIZE_4, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_VALIDATOR_POLEID_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_FIRMWARE_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_FIRMWARE_REQUIRED
				, SENSUS_MLC_LIGHTVALIDATOR_FIRMWARE_REQUIRED);

		appCode86Info.setDeviceVersionMajor(1);
		appCode86Info.setDeviceVersionMinor(1);
		appCode86Info.setDeviceVersionPatch(1);
		response = getMlcServerBCF().processLightBindingNotification(request);

		// Expect 1 error: no pole id
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_VALIDATOR_POLEID_REQUIRED);

		appCode86Info.setPoleId("PoleId");

		// Expect 2 errors: bad lat/long
		appCode86Info.setLatitude(ARBITRARY_LATITUDE_230);
		appCode86Info.setLongitude(ARBITRARY_LONGITUDE_230);
		response = getMlcServerBCF().processLightBindingNotification(request);
		assertNotNull(response);
		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INVALID_LATITUDE
				, SENSUS_MLC_LIGHTVALIDATOR_INVALID_LONGITUDE);

		appCode86Info.setLatitude(null);
		appCode86Info.setLongitude(null);
		response = getMlcServerBCF().processLightBindingNotification(request);
		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LATITUDE_REQUIRED,
				SENSUS_MLC_LIGHTVALIDATOR_LONGITUDE_REQUIRED);

		appCode86Info.setLatitude(ARBITRARY_LATITUDE_23);
		appCode86Info.setLongitude(ARBITRARY_LONGITUDE_23);
		response = getMlcServerBCF().processLightBindingNotification(request);

		// Expect 0 errors
		assertTrue(response.isOperationSuccess());

		setSituation(getMlcServerBCF(), SituationsEnum.ERROR, IMlcServerBCL.class);
		request.setLightBindingNotification(notification);
		response = getMlcServerBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

		// Test Exception
		setSituation(getMlcServerBCF(), SituationsEnum.EXCEPTION, IMlcServerBCL.class);
		request.setLightBindingNotification(notification);
		response = getMlcServerBCF().processLightBindingNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);
	}

	/**
	 * Test process apply light level notification.
	 */
	@Test
	public void testProcessApplyLightLevelNotification()
	{
		MlcServerRequest request = new MlcServerRequest(new UserContext());

		// create notification but do not set transactionId or customerId
		ApplyLightLevelNotification notification = new ApplyLightLevelNotification();

		// Create a new smartpoint but do not set RniId
		Smartpoint smartpoint = new Smartpoint();

		// Expect 2 errors: no tenant, no transaction Id
		notification.getSuccededSmartpoint().add(smartpoint);
		request.setApplyLightLevelNotification(notification);
		Response response = getMlcServerBCF().processApplyLightLevelNotification(request);

		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_TRANSACTION_ID_REQUIRED
				, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Expect 1 errors: no Rni Id
		notification.setCustomerID(ACME);
		request.setApplyLightLevelNotification(notification);
		response = getMlcServerBCF().processApplyLightLevelNotification(request);

		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_TRANSACTION_ID_REQUIRED);

		// Expect 1 errors: no Rni Id
		notification.setTransactionID(GOOD);
		request.setApplyLightLevelNotification(notification);
		response = getMlcServerBCF().processApplyLightLevelNotification(request);

		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Expect Success
		smartpoint.setRniId(1);
		notification.getSuccededSmartpoint().add(smartpoint);
		response = getMlcServerBCF().processApplyLightLevelNotification(request);
		assertTrue(response.isOperationSuccess());

		// Test Error
		setSituation(getMlcServerBCF(), SituationsEnum.ERROR, IMlcServerBCL.class);
		request.setApplyLightLevelNotification(notification);
		response = getMlcServerBCF().processApplyLightLevelNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

		// Test Exception
		setSituation(getMlcServerBCF(), SituationsEnum.EXCEPTION, IMlcServerBCL.class);
		request.setApplyLightLevelNotification(notification);
		response = getMlcServerBCF().processApplyLightLevelNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

	}

	/**
	 * Test process update light status notification.
	 */
	@Test
	public void testProcessUpdateLightStatusNotification()
	{
		MlcServerRequest request = new MlcServerRequest(TestBaseUtil.createUserContext());

		// create notification but do not set transactionId or customerId
		UpdateLightStatusNotification updateStatusNotification = new UpdateLightStatusNotification();

		// Create a new smartpoint but do not set RniId
		Smartpoint smartpoint = new Smartpoint();

		updateStatusNotification.getSuccededSmartpoint().add(smartpoint);

		// No tenant
		request.setUpdateLightStatusNotification(updateStatusNotification);
		Response response = getMlcServerBCF().processUpdateLightStatusNotification(request);
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Rni ID
		updateStatusNotification.setCustomerID(GOOD);
		request.setUpdateLightStatusNotification(updateStatusNotification);
		response = getMlcServerBCF().processUpdateLightStatusNotification(request);
		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Success
		updateStatusNotification.setCustomerID(request.getUserContext().<Tenant> getTenant().getRniCode());
		smartpoint.setRniId(1);
		updateStatusNotification.getSuccededSmartpoint().add(smartpoint);

		response = getMlcServerBCF().processUpdateLightStatusNotification(request);
		assertTrue(response.isOperationSuccess());

		// Test Error
		setSituation(getMlcServerBCF(), SituationsEnum.ERROR, IMlcServerBCL.class);
		request.setUpdateLightStatusNotification(updateStatusNotification);
		response = getMlcServerBCF().processUpdateLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

		// Test Exception
		setSituation(getMlcServerBCF(), SituationsEnum.EXCEPTION, IMlcServerBCL.class);
		request.setUpdateLightStatusNotification(updateStatusNotification);
		response = getMlcServerBCF().processUpdateLightStatusNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

	}

	/**
	 * Test process clear schedule notification.
	 */
	@Test
	public void testProcessClearScheduleNotification()
	{
		MlcServerRequest request = new MlcServerRequest(new UserContext());

		// create notification but do not set transactionId or customerId
		ClearScheduleNotification notification = new ClearScheduleNotification();

		// Create a new smartpoint but do not set RniId
		Smartpoint smartpoint = new Smartpoint();

		notification.getSuccededSmartpoint().add(smartpoint);

		request.setClearScheduleNotification(notification);
		Response response = getMlcServerBCF().processClearScheduleNotification(request);

		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_TRANSACTION_ID_REQUIRED
				, SENSUS_MLC_VALIDATOR_REQUIRED);

		notification.setCustomerID(ACME);
		request.setClearScheduleNotification(notification);
		response = getMlcServerBCF().processClearScheduleNotification(request);

		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_TRANSACTION_ID_REQUIRED);

		notification.setTransactionID(GOOD);
		request.setClearScheduleNotification(notification);
		response = getMlcServerBCF().processClearScheduleNotification(request);

		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		smartpoint.setRniId(1);
		notification.getSuccededSmartpoint().add(smartpoint);

		response = getMlcServerBCF().processClearScheduleNotification(request);
		assertTrue(response.isOperationSuccess());

		// Test Error
		setSituation(getMlcServerBCF(), SituationsEnum.ERROR, IMlcServerBCL.class);
		request.setClearScheduleNotification(notification);
		response = getMlcServerBCF().processClearScheduleNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

		// Test Exception
		setSituation(getMlcServerBCF(), SituationsEnum.EXCEPTION, IMlcServerBCL.class);
		request.setClearScheduleNotification(notification);
		response = getMlcServerBCF().processClearScheduleNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

	}

	/**
	 * Test process apply schedule notification.
	 */
	@Test
	public void testProcessApplyScheduleNotification()
	{
		MlcServerRequest request = new MlcServerRequest(new UserContext());

		// create notification but do not set transactionId or customerId
		ApplyScheduleNotification notification = new ApplyScheduleNotification();

		// Create a new smartpoint but do not set RniId
		Smartpoint smartpoint = new Smartpoint();

		notification.getSuccededSmartpoint().add(smartpoint);

		request.setApplyScheduleNotification(notification);
		Response response = getMlcServerBCF().processApplyScheduleNotification(request);

		assertEquals(2, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED
				, SENSUS_MLC_TRANSACTION_ID_REQUIRED);

		notification.setCustomerID(ACME);
		request.setApplyScheduleNotification(notification);
		response = getMlcServerBCF().processApplyScheduleNotification(request);

		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_TRANSACTION_ID_REQUIRED);

		notification.setTransactionID(GOOD);
		request.setApplyScheduleNotification(notification);
		response = getMlcServerBCF().processApplyScheduleNotification(request);

		assertEquals(1, response.getMessageInfoList().size());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);

		smartpoint.setRniId(1);
		notification.getSuccededSmartpoint().add(smartpoint);

		response = getMlcServerBCF().processApplyScheduleNotification(request);
		assertNotNull(response);
		assertTrue(response.isOperationSuccess());

		// Test Error
		setSituation(getMlcServerBCF(), SituationsEnum.ERROR, IMlcServerBCL.class);
		request.setApplyScheduleNotification(notification);
		response = getMlcServerBCF().processApplyScheduleNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);

		// Test Exception
		setSituation(getMlcServerBCF(), SituationsEnum.EXCEPTION, IMlcServerBCL.class);
		request.setApplyScheduleNotification(notification);
		response = getMlcServerBCF().processApplyScheduleNotification(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);
	}

	/**
	 * Test process apply dimming configuration.
	 */
	@Test
	public void testProcessApplyDimmingConfiguration()
	{
		MlcServerRequest request = new MlcServerRequest(new UserContext());

		ApplyDimmingConfigurationNotification notification = new ApplyDimmingConfigurationNotification();
		request.setApplyDimmingConfigurationNotification(notification);
		Response response = getMlcServerBCF().processApplyDimmingConfiguration(request);
		assertNotNull(response);

		// Missing Customer ID
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);
		assertEquals(1, response.getMessageInfoList().size());

		Smartpoint smartpoint = new Smartpoint();

		notification.setCustomerID(ACME);
		notification.getSuccededSmartpoint().add(smartpoint);
		response = getMlcServerBCF().processApplyDimmingConfiguration(request);
		assertNotNull(response);

		// Missing RNI ID
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHT_RNIID_REQUIRED);
		assertEquals(1, response.getMessageInfoList().size());

		smartpoint.setRniId(1);
		response = getMlcServerBCF().processApplyDimmingConfiguration(request);
		assertNotNull(response);

		// SUCCESS
		assertEquals(0, response.getMessageInfoList().size());

		// Expect ERROR
		setSituation(getMlcServerBCF(), SituationsEnum.ERROR, IMlcServerBCL.class);
		response = getMlcServerBCF().processApplyDimmingConfiguration(request);
		assertNotNull(response);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);
		assertEquals(1, response.getMessageInfoList().size());

		// Expect Exception
		setSituation(getMlcServerBCF(), SituationsEnum.EXCEPTION, IMlcServerBCL.class);
		response = getMlcServerBCF().processApplyDimmingConfiguration(request);
		assertNotNull(response);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);
		assertEquals(1, response.getMessageInfoList().size());

	}

}
