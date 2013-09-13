package com.sensus.dm.controller.watermeter.unittest;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;
import com.sensus.dm.controller.watermeter.WaterMeterDeviceAPIController;

/**
 * The Class WaterMeterDeviceAPIControllerTest.
 */
public class WaterMeterDeviceAPIControllerTest extends AbstractTestBase
{
	/** The Constant FETCH. */
	private static final String FETCH = "/api/watermeterdevice/fetch";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/api/watermeterdevice/fetchAll";

	/** The Constant FETCH_LEAK_REPORT. */
	private static final String FETCH_LEAK_REPORT = "/api/watermeterdevice/fetchLeakReport";

	/**
	 * Gets the water meter mock.
	 * 
	 * @return the water meter mock
	 */
	private WaterMeterBCFMockImpl getWaterMeterMock()
	{
		return (WaterMeterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(WaterMeterDeviceAPIController.class)
				.getWaterMeterBCF();
	}

	/**
	 * Fetch.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetch() throws Exception
	{
		// Success Situation

		// Set data to manufacture type
		setData("{\"devices\":[{\"radio\":{\"flexNetId\":\"20008847\"},\"deviceType\":\"WATER_METER\"}]}");

		getWaterMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH)
				.andExpect(jsonPath("$.devices", hasSize(1)))
				.andExpect(
						jsonPath("$.devices[0].deviceType",
								comparesEqualTo(com.sensus.cbof.model.DeviceTypeEnum.WATER_METER.toString())));

		// Failure, Empty and Exception Situation

		setData("{}");

		// Failure Situation
		getWaterMeterMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getWaterMeterMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.devices", nullValue()));

		// Exception Situation
		getWaterMeterMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Fetch All.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchAll() throws Exception
	{
		setData("{}");

		// Success Situation
		getWaterMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALL)
				.andExpect(jsonPath("$.devices", hasSize(1)))
				.andExpect(
						jsonPath("$.devices[0].deviceType", comparesEqualTo(DeviceTypeEnum.WATER_METER.toString())));

		// Failure Situation
		getWaterMeterMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_ALL).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getWaterMeterMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_ALL).andExpect(jsonPath("$.devices", nullValue()));

		// Exception Situation
		getWaterMeterMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_ALL).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Fetch leak report.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchLeakReport() throws Exception
	{
		setData("{}");

		// Success Situation
		getWaterMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_LEAK_REPORT)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)))
				.andExpect(jsonPath("$.leakList", hasSize(1)));

		// Failure Situation
		getWaterMeterMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_LEAK_REPORT).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getWaterMeterMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_LEAK_REPORT).andExpect(jsonPath("$.leakList", nullValue()));

		// Exception Situation
		getWaterMeterMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_LEAK_REPORT).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}
}
