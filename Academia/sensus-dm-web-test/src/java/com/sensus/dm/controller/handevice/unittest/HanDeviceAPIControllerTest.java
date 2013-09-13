package com.sensus.dm.controller.handevice.unittest;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.electricmeter.unittest.ElectricMeterBCFMockImpl;
import com.sensus.dm.controller.handevice.HanDeviceAPIController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class HanDeviceAPIControllerTest.
 */
public class HanDeviceAPIControllerTest extends AbstractTestBase
{
	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/api/handevice/fetchAll";

	/**
	 * Gets the electric meter mock.
	 * 
	 * @return the electric meter mock
	 */
	private ElectricMeterBCFMockImpl getElectricMeterMock()
	{
		return (ElectricMeterBCFMockImpl)SensusAppContext.getApplicationContext().getBean(HanDeviceAPIController.class)
				.getElectricMeterBCF();
	}

	/**
	 * Fetch All.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchAllHanDevices() throws Exception
	{
		// SUCCESS SITUATION
		setData("{\"startRow\":0, \"endRow\":0, \"pageSize\":25,\"deviceSearch\":{\"hanDeviceSearch\":{\"hanDevice\":{\"macAddress\":\"123456\"}}},\"deviceType\":\"HAN_DEVICE\"}");

		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALL).andExpect(jsonPath("$.devices", hasSize(1)))
				.andExpect(
						jsonPath("$.devices[0].deviceType", comparesEqualTo(DeviceTypeEnum.HAN_DEVICE.toString())));

		// Failure, Empty and Exception Situation
		setData("{\"startRow\":0, \"endRow\":0, \"pageSize\":25,\"deviceSearch\":{\"hanDeviceSearch\":{\"hanDevice\":{\"macAddress\":\"123456\"}}},\"deviceType\":\"HAN_DEVICE\"}");

		getElectricMeterMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_ALL).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getElectricMeterMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_ALL).andExpect(jsonPath("$.devices", nullValue()));

		// Exception Situation
		getElectricMeterMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_ALL).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

}
