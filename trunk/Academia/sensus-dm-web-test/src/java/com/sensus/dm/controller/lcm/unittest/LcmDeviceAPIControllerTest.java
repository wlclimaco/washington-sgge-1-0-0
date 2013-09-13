package com.sensus.dm.controller.lcm.unittest;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.electricmeter.unittest.ElectricMeterBCFMockImpl;
import com.sensus.dm.controller.lcm.LcmDeviceAPIController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class LcmDeviceAPIControllerTest.
 */
public class LcmDeviceAPIControllerTest extends AbstractTestBase
{
	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/api/lcmdevice/fetchAll";

	/**
	 * Gets the electric meter mock.
	 * 
	 * @return the electric meter mock
	 */
	private ElectricMeterBCFMockImpl getElectricMeterMock()
	{
		return (ElectricMeterBCFMockImpl)SensusAppContext.getApplicationContext().getBean(LcmDeviceAPIController.class)
				.getElectricMeterBCF();
	}

	/**
	 * Fetch All.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchAllLcmDevices() throws Exception
	{
		// SUCCESS SITUATION
		setData("{\"startRow\":0, \"endRow\":0, \"pageSize\":25,\"deviceSearch\":{\"lcmSearch\":{\"lcm\":{\"macAddress\":\"123456\"}}},\"deviceType\":\"LCM\"}");

		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALL).andExpect(jsonPath("$.devices", hasSize(1)))
				.andExpect(
						jsonPath("$.devices[0].deviceType", comparesEqualTo(DeviceTypeEnum.LCM.toString())));

		// Failure, Empty and Exception Situation
		setData("{\"startRow\":0, \"endRow\":0, \"pageSize\":25,\"deviceSearch\":{\"lcmSearch\":{\"lcm\":{\"macAddress\":\"123456\"}}},\"deviceType\":\"LCM\"}");

		getElectricMeterMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_ALL).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		setData("{}");

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
