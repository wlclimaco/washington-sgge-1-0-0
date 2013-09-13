package com.sensus.dm.controller.map.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.device.unittest.DeviceBCFMockImpl;
import com.sensus.dm.controller.map.MapAPIController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class NoteAPIControllerTest.
 */
public class MapAPIControllerTest extends AbstractTestBase
{
	/** The Constant INSERT. */
	private static final String FETCH = "/api/maps/fetch";

	private static final String FETCH_DEVICE = "/api/maps/fetchDevice";

	/**
	 * Gets the device mock.
	 * 
	 * @return the device mock
	 */
	private DeviceBCFMockImpl getDeviceMock()
	{
		return (DeviceBCFMockImpl)SensusAppContext.getApplicationContext().getBean(MapAPIController.class)
				.getDeviceBCF();
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
		setData("{}");

		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.operationSuccess", equalTo(Boolean.TRUE)))
				.andExpect(jsonPath("$.geocodeDeviceInfo", hasSize(1)));

		// Failure situation
		getDeviceMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.andExpect(jsonPath("$.operationSuccess", equalTo(Boolean.FALSE)));

		// Empty situation
		getDeviceMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.operationSuccess", equalTo(Boolean.TRUE)));

		// Exception situation
		getDeviceMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())))
				.andExpect(jsonPath("$.operationSuccess", equalTo(Boolean.FALSE)));
	}

	@Test
	public void fetchDevice() throws Exception
	{
		// Success Situation
		setData("{\"timeZone\":\"-4\",\"dateFormat\":\"MM/dd/yyyy\",\"deviceSearch\":{\"electricMeterSearch\":{\"electricMeter\":{\"radio\":{},\"configuration\":{\"firmwareMeter\":null}},"
				+
				"\"remoteConnectStatusEnumList\":null,\"electricMeterLifecycleStateEnumList\":[\"INSTALLED\"]},\"groups\":null,\"tags\":null,\"deviceModels\":null,\"processId\":null,"
				+
				"\"startDate\":null,\"endDate\":null},\"deviceType\":\"ELECTRIC_METER\",\"bottomLeftLat\":36.39171974242871,"
				+
				"\"bottomLeftLon\":-122.87512969952031,\"topRightLat\":38.99917831926291,\"topRightLon\":-113.38843536396901,\"geoCodeTrunc\":0}");

		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_DEVICE).andExpect(jsonPath("$.operationSuccess", equalTo(Boolean.TRUE)))
				.andExpect(jsonPath("$.geocodeDeviceInfo", hasSize(1)));

		// Failure situation
		getDeviceMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_DEVICE).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())))
				.andExpect(jsonPath("$.operationSuccess", equalTo(Boolean.FALSE)));

		// Empty situation
		getDeviceMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_DEVICE).andExpect(jsonPath("$.operationSuccess", equalTo(Boolean.TRUE)));

		// Exception situation
		getDeviceMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_DEVICE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())))
				.andExpect(jsonPath("$.operationSuccess", equalTo(Boolean.FALSE)));

	}

}
