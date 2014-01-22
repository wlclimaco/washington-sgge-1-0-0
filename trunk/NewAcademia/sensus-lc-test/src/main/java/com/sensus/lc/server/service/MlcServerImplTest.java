package com.sensus.lc.server.service;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.sensus.lc.server.endpoint.MlcServerSpringWSEndpoint;
import com.sensus.mlc.mlcserver.type.AlarmWarningInfo;
import com.sensus.mlc.mlcserver.type.AlarmWarningNotification;
import com.sensus.mlc.mlcserver.type.AlarmWarningNotificationResult;
import com.sensus.mlc.mlcserver.type.AlarmWarningSubType;
import com.sensus.mlc.mlcserver.type.AlarmWarningType;
import com.sensus.mlc.mlcserver.type.AppCode11Info;
import com.sensus.mlc.mlcserver.type.AppCode85Info;
import com.sensus.mlc.mlcserver.type.AppCode86Info;
import com.sensus.mlc.mlcserver.type.AppCode90Info;
import com.sensus.mlc.mlcserver.type.ApplyLightLevelNotification;
import com.sensus.mlc.mlcserver.type.ApplyLightLevelNotificationResult;
import com.sensus.mlc.mlcserver.type.ApplyScheduleNotification;
import com.sensus.mlc.mlcserver.type.ApplyScheduleNotificationResult;
import com.sensus.mlc.mlcserver.type.ApplySmartpointPropertiesNotification;
import com.sensus.mlc.mlcserver.type.ApplySmartpointPropertiesNotificationResult;
import com.sensus.mlc.mlcserver.type.AttributeNotification;
import com.sensus.mlc.mlcserver.type.AttributeNotificationResult;
import com.sensus.mlc.mlcserver.type.ChannelSetupAuditNotification;
import com.sensus.mlc.mlcserver.type.ChannelSetupAuditNotificationResult;
import com.sensus.mlc.mlcserver.type.ClearAlarmsNotification;
import com.sensus.mlc.mlcserver.type.ClearAlarmsNotificationResult;
import com.sensus.mlc.mlcserver.type.ClearScheduleNotification;
import com.sensus.mlc.mlcserver.type.ClearScheduleNotificationResult;
import com.sensus.mlc.mlcserver.type.CustomerSerialNumberNotification;
import com.sensus.mlc.mlcserver.type.CustomerSerialNumberNotificationResult;
import com.sensus.mlc.mlcserver.type.LightBindingNotification;
import com.sensus.mlc.mlcserver.type.LightBindingNotificationResult;
import com.sensus.mlc.mlcserver.type.LightDetail;
import com.sensus.mlc.mlcserver.type.LightSetupNotification;
import com.sensus.mlc.mlcserver.type.LightSetupNotificationResult;
import com.sensus.mlc.mlcserver.type.LightState;
import com.sensus.mlc.mlcserver.type.LightStatusNotification;
import com.sensus.mlc.mlcserver.type.LightStatusNotificationResult;
import com.sensus.mlc.mlcserver.type.MessageInfo;
import com.sensus.mlc.mlcserver.type.ReadLightStatusNotification;
import com.sensus.mlc.mlcserver.type.ReadLightStatusNotificationResult;
import com.sensus.mlc.mlcserver.type.Smartpoint;
import com.sensus.mlc.mlcserver.type.Status;

/**
 * The Class serverImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/mlc/mlcserver/mlcserverimpltest.xml",
		"classpath*:web/conf/sensus-lc-resourcebundles-context.xml"})
public class MlcServerImplTest extends AbstractJUnit4SpringContextTests
{
	/** The Constant ARBITRARY_LONGITUDE. */
	private static final Double ARBITRARY_LONGITUDE = -12.77585858;

	/** The Constant ARBITRARY_LATITUDE. */
	private static final Double ARBITRARY_LATITUDE = 10.2345678;

	/** The Constant ARBITRARY_SUNSET_OFFSET_N15. */
	private static final Integer ARBITRARY_SUNSET_OFFSET_N15 = -15;

	/** The Constant ARBITRARY_SUNRISE_OFFSET_15. */
	private static final Integer ARBITRARY_SUNRISE_OFFSET_15 = 15;

	/** The Constant ARBITRARY_VOLTAGE_20. */
	private static final Integer ARBITRARY_VOLTAGE_20 = 20;

	/** The Constant ARBITRARY_CURRENT_10. */
	private static final Integer ARBITRARY_CURRENT_10 = 10;

	/** The Constant ARBITRARY_SUNSET_MINUTE_15. */
	private static final Integer ARBITRARY_SUNSET_MINUTE_15 = 15;

	/** The Constant ARBITRARY_SUNSET_HOUR_10. */
	private static final Integer ARBITRARY_SUNSET_HOUR_10 = 10;

	/** The Constant ARBITRARY_SUNRISE_MINUTE_12. */
	private static final Integer ARBITRARY_SUNRISE_MINUTE_12 = 12;

	/** The Constant ARBITRARY_SUNRISE_HOUR_11. */
	private static final Integer ARBITRARY_SUNRISE_HOUR_11 = 11;

	/** The Constant BAD. */
	private static final String BAD = "BAD";

	/** The Constant ACME. */
	private static final String ACME = "ACME";

	/** The mlc server. */
	@Resource
	private MlcServerSpringWSEndpoint server;

	/**
	 * Gets the mlc server.
	 * 
	 * @return the mlc server
	 */
	public MlcServerSpringWSEndpoint getserver()
	{
		return server;
	}

	/**
	 * Sets the mlc server.
	 * 
	 * @param server the new mlc server
	 */
	public void setserver(MlcServerSpringWSEndpoint server)
	{
		this.server = server;
	}

	/**
	 * Test get light status.
	 */
	@Test
	public void testGetLightStatus()
	{
		ReadLightStatusNotification notification = new ReadLightStatusNotification();
		notification.setTransactionID("GOOD");
		notification.setCustomerID("GOOD1");

		LightDetail ld = new LightDetail();

		ld.setRniId(1);

		AppCode90Info app90 = new AppCode90Info();
		app90.setLightState(LightState.ON);

		app90.setSunriseHour(ARBITRARY_SUNRISE_HOUR_11);
		app90.setSunriseMinute(ARBITRARY_SUNRISE_MINUTE_12);
		app90.setSunsetHour(ARBITRARY_SUNSET_HOUR_10);
		app90.setSunsetMinute(ARBITRARY_SUNSET_MINUTE_15);
		app90.setCurrent(ARBITRARY_CURRENT_10);
		app90.setVoltage(ARBITRARY_VOLTAGE_20);
		ld.setAppCode90Data(app90);

		AppCode85Info app85 = new AppCode85Info();
		app85.setSunriseOffset(ARBITRARY_SUNRISE_OFFSET_15);
		app85.setSunsetOffset(ARBITRARY_SUNSET_OFFSET_N15);
		ld.setAppCode85Data(app85);

		AlarmWarningInfo al1 = new AlarmWarningInfo();
		al1.setAlarmWarningType(AlarmWarningType.ALARM);
		al1.setAlarmWarningSubType(AlarmWarningSubType.POWER_FAILURE);
		AppCode11Info appCode11Data = new AppCode11Info();
		appCode11Data.getAlarmWarningMessages().add(al1);
		ld.setAppCode11Data(appCode11Data);
		notification.getLightDetail().add(ld);

		ReadLightStatusNotificationResult result = getserver().readLightStatusNotification(notification);

		/*
		 * for (com.sensus.lc.server.type.MessageInfo ii : result.getMessage())
		 * {
		 * System.out.println(ii.getMessageText());
		 * }
		 */

		assertEquals(Status.SUCCESS, result.getStatus());

		notification.setTransactionID(BAD);
		result = getserver().readLightStatusNotification(notification);
		assertEquals(Status.FAIL, result.getStatus());
	}

	/**
	 * Test clear alarms notification.
	 */
	@Test
	public void testClearAlarmsNotification()
	{
		ClearAlarmsNotification notification = new ClearAlarmsNotification();

		Smartpoint sp = new Smartpoint();
		sp.setRniId(1);
		notification.getSuccededSmartpoint().add(sp);
		notification.setTransactionID("Light1");
		notification.setCustomerID(ACME);

		ClearAlarmsNotificationResult result =
				getserver().clearAlarmsNotification(notification);

		for (MessageInfo ii : result.getMessage())
		{
			System.out.println(ii.getMessageText());
		}

		assertEquals(Status.SUCCESS, result.getStatus());

		notification.setTransactionID(BAD);
		result = getserver().clearAlarmsNotification(notification);
		assertEquals(Status.FAIL, result.getStatus());
	}

	/**
	 * Test set light intensity.
	 */
	@Test
	public void testSetLightIntensity()
	{
		ApplyLightLevelNotification notification = new ApplyLightLevelNotification();

		Smartpoint sp = new Smartpoint();
		sp.setRniId(1);
		notification.getSuccededSmartpoint().add(sp);
		notification.setTransactionID("Light");
		notification.setCustomerID(ACME);

		ApplyLightLevelNotificationResult result =
				getserver().applyLightLevelNotification(notification);

		for (MessageInfo ii : result.getMessage())
		{
			System.out.println(ii.getMessageText());
		}

		assertEquals(Status.SUCCESS, result.getStatus());

		notification.setTransactionID(BAD);
		result = getserver().applyLightLevelNotification(notification);
		assertEquals(Status.FAIL, result.getStatus());
	}

	/**
	 * Test light binding notification.
	 */
	@Test
	public void testLightBindingNotification()
	{
		LightBindingNotification notification = new LightBindingNotification();

		AppCode86Info lb = new AppCode86Info();
		lb.setLatitude(ARBITRARY_LATITUDE);
		lb.setLongitude(ARBITRARY_LONGITUDE);
		lb.setPoleId("1234");
		lb.setDeviceVersionMajor(1);
		lb.setDeviceVersionMinor(1);
		lb.setDeviceVersionPatch(1);

		Smartpoint sp = new Smartpoint();
		sp.setRniId(1);
		notification.setSmartpoint(sp);

		notification.setSmartpointDetail(lb);
		notification.setTransactionID("ABCDE");
		notification.setCustomerID(ACME);
		LightBindingNotificationResult result = getserver().lightBindingNotification(notification);

		assertEquals(Status.SUCCESS, result.getStatus());

		notification.setTransactionID(BAD);
		result = getserver().lightBindingNotification(notification);
		assertEquals(Status.FAIL, result.getStatus());
	}

	/**
	 * Test clear schedule notification.
	 */
	@Test
	public void testClearScheduleNotification()
	{
		ClearScheduleNotification notification = new ClearScheduleNotification();
		ClearScheduleNotificationResult result = getserver().clearScheduleNotification(notification);

		assertEquals(Status.SUCCESS, result.getStatus());

		notification.setTransactionID(BAD);
		result = getserver().clearScheduleNotification(notification);
		assertEquals(Status.FAIL, result.getStatus());
	}

	/**
	 * Test light setup notification.
	 */
	@Test
	public void testLightSetupNotification()
	{
		LightSetupNotification notification = new LightSetupNotification();

		Smartpoint sp = new Smartpoint();
		sp.setRniId(1);
		notification.setSmartpoint(sp);
		notification.setTransactionID("ABCDF");
		notification.setCustomerID(ACME);

		LightSetupNotificationResult result = getserver().lightSetupNotification(notification);

		assertEquals(Status.SUCCESS, result.getStatus());

		notification.setTransactionID(BAD);
		result = getserver().lightSetupNotification(notification);
		assertEquals(Status.FAIL, result.getStatus());
	}

	/**
	 * Test light status notification.
	 */
	@Test
	public void testLightStatusNotification()
	{
		LightStatusNotification notification = new LightStatusNotification();

		AppCode90Info app90 = new AppCode90Info();
		app90.setLightState(LightState.ON);

		app90.setSunriseHour(ARBITRARY_SUNRISE_HOUR_11);
		app90.setSunriseMinute(ARBITRARY_SUNRISE_MINUTE_12);
		app90.setSunsetHour(ARBITRARY_SUNSET_HOUR_10);
		app90.setSunsetMinute(ARBITRARY_SUNSET_MINUTE_15);
		app90.setCurrent(ARBITRARY_CURRENT_10);
		app90.setVoltage(ARBITRARY_VOLTAGE_20);
		notification.setSmartpointDetail(app90);

		Smartpoint sp = new Smartpoint();
		sp.setRniId(1);
		notification.setSmartpoint(sp);
		notification.setTransactionID("ABCDI");
		notification.setCustomerID(ACME);

		LightStatusNotificationResult result = getserver().lightStatusNotification(notification);

		assertEquals(Status.SUCCESS, result.getStatus());

		notification.setTransactionID(BAD);
		result = getserver().lightStatusNotification(notification);
		assertEquals(Status.FAIL, result.getStatus());
	}

	/**
	 * Test alarm warning notification.
	 */
	@Test
	public void testAlarmWarningNotification()
	{
		AlarmWarningNotification notification = new AlarmWarningNotification();

		AlarmWarningInfo al1 = new AlarmWarningInfo();
		al1.setAlarmWarningType(AlarmWarningType.ALARM);
		al1.setAlarmWarningSubType(AlarmWarningSubType.POWER_FAILURE);
		AppCode11Info appCode11Info = new AppCode11Info();
		appCode11Info.getAlarmWarningMessages().add(al1);
		notification.setSmartpointDetail(appCode11Info);

		Smartpoint sp = new Smartpoint();
		sp.setRniId(1);
		notification.setSmartpoint(sp);
		notification.setTransactionID("ABCD1");
		notification.setCustomerID(ACME);

		AlarmWarningNotificationResult result = getserver().alarmWarningNotification(notification);

		assertEquals(Status.SUCCESS, result.getStatus());

		notification.setTransactionID(BAD);
		result = getserver().alarmWarningNotification(notification);
		assertEquals(Status.FAIL, result.getStatus());
	}

	/**
	 * Test apply schedule notification.
	 */
	@Test
	public void testApplyScheduleNotification()
	{
		ApplyScheduleNotification notification = new ApplyScheduleNotification();
		notification.setCustomerID("dfsdfg");
		notification.setStatus(Status.SUCCESS);
		notification.setTransactionID("ApplyScheduleNotification");

		ApplyScheduleNotificationResult response = getserver().applyScheduleNotification(notification);

		notification.setTransactionID(BAD);
		response = getserver().applyScheduleNotification(notification);
		assertEquals(Status.FAIL, response.getStatus());
	}

	/**
	 * Test apply smartpoint properties notification.
	 */
	@Test
	public void testApplySmartpointPropertiesNotification()
	{
		ApplySmartpointPropertiesNotification notification = new ApplySmartpointPropertiesNotification();
		notification.setCustomerID("dfsdfh");
		notification.setStatus(Status.SUCCESS);
		notification.setTransactionID("ApplySmartpointPropertiesNotification");

		ApplySmartpointPropertiesNotificationResult response =
				getserver().applySmartpointPropertiesNotification(notification);

		notification.setTransactionID(BAD);
		response = getserver().applySmartpointPropertiesNotification(notification);
		assertEquals(Status.FAIL, response.getStatus());
	}

	/**
	 * Test channel setup audit notification.
	 */
	@Test
	public void testChannelSetupAuditNotification()
	{
		ChannelSetupAuditNotification notification = new ChannelSetupAuditNotification();
		notification.setCustomerID("dfsdf");
		notification.setStatus(Status.SUCCESS);
		notification.setTransactionID("ChannelSetupAuditNotification");

		ChannelSetupAuditNotificationResult response =
				getserver().channelSetupAuditNotification(notification);

		notification.setTransactionID(BAD);
		response = getserver().channelSetupAuditNotification(notification);
		assertEquals(Status.FAIL, response.getStatus());
	}

	/**
	 * Test customer serial number notification.
	 */
	@Test
	public void testCustomerSerialNumberNotification()
	{
		CustomerSerialNumberNotification notification = new CustomerSerialNumberNotification();
		CustomerSerialNumberNotificationResult response =
				getserver().customerSerialNumberNotification(notification);
		assertEquals(Status.SUCCESS, response.getStatus());
	}

	/**
	 * Test attribute notification.
	 */
	@Test
	public void testAttributeNotification()
	{
		AttributeNotification notification = new AttributeNotification();
		AttributeNotificationResult response =
				getserver().attributeNotification(notification);
		assertEquals(Status.SUCCESS, response.getStatus());
	}
}
