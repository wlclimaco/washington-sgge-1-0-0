package com.sensus.dm.controller.device.unittest;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.device.DeviceAPIController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;

/**
 * The Class DeviceAPIControllerTest.
 */
public class DeviceAPIControllerTest extends AbstractTestBase
{

	/** The Constant FETCH_ALARM_HISTORY. */
	private static final String FETCH_ALARM_HISTORY = "/api/device/fetchAlarmHistory";

	/** The Constant FETCH_BY_SEARCH. */
	private static final String FETCH_BY_SEARCH = "/api/device/fetchBySearch";

	/**
	 * Gets the device mock.
	 * 
	 * @return the device mock
	 */
	private DeviceBCFMockImpl getDeviceMock()
	{
		return (DeviceBCFMockImpl)SensusAppContext.getApplicationContext().getBean(DeviceAPIController.class)
				.getDeviceBCF();
	}

	/**
	 * Fetch by search.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchBySearch() throws Exception
	{
		/*
		 * Success Situation
		 */

		// Set data to search device id
		setData("{\"DEVICE_ID\":\"100\"}");

		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_BY_SEARCH);

		// Set data to search network address
		setData("{\"NETWORK_ADDRESS\":\"10\"}");

		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_BY_SEARCH);

		// Set data to search premise id
		setData("{\"PREMISE_ID\":\"1\"}");

		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_BY_SEARCH);

		/*
		 * Failure, Empty and Exception Situation
		 */

		setData("{\"DEVICE_ID\":\"\"}");

		// Failure Situation
		getDeviceMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_BY_SEARCH);

		setData("{}");

		// Empty Situation
		getDeviceMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_BY_SEARCH);

		// Exception Situation
		getDeviceMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_BY_SEARCH);
	}

	/**
	 * Fetch.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchAlarmHistory() throws Exception
	{
		/*
		 * Success Situation
		 */

		// Set data to alarm history
		setData("{\"flexNetId\":1328,\"alarmEnum\":\"TILT\",\"alarmTime\":1366731314000}");

		setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALARM_HISTORY);

		setServiceTypeEnum(ServiceTypeEnum.WATER);
		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALARM_HISTORY);

		setServiceTypeEnum(ServiceTypeEnum.GAS);
		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALARM_HISTORY);

		/*
		 * Failure, Empty and Exception Situation
		 */

		setData("{\"alarmEnum\":\"TILT\",\"alarmTime\":\"1366731314000\"}");

		// Failure Situation
		getDeviceMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_ALARM_HISTORY);

		setData("{}");

		// Empty Situation
		getDeviceMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_ALARM_HISTORY);

		// Exception Situation
		getDeviceMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_ALARM_HISTORY);
	}

}
