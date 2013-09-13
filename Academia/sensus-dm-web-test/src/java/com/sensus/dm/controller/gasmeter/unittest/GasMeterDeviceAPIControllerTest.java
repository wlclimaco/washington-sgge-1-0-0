package com.sensus.dm.controller.gasmeter.unittest;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.gasmeter.GasMeterDeviceAPIController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

public class GasMeterDeviceAPIControllerTest extends AbstractTestBase
{
	private static final String FETCH = "/api/gasmeterdevice/fetch";

	private static final String FETCH_ALL = "/api/gasmeterdevice/fetchAll";

	private GasMeterBCFMockImpl getGasMeterMock()
	{
		return (GasMeterBCFMockImpl)SensusAppContext.getApplicationContext().getBean(GasMeterDeviceAPIController.class)
				.getGasMeterBCF();
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
		setData("{\"devices\":[{\"radio\":{\"flexNetId\":\"20008847\"},\"deviceType\":\"GAS_METER\"}]}");

		getGasMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH)
				.andExpect(jsonPath("$.devices", hasSize(1)))
				.andExpect(
						jsonPath("$.devices[0].deviceType", comparesEqualTo(DeviceTypeEnum.GAS_METER.toString())));

		// Failure, Empty and Exception Situation

		setData("{}");

		// Failure Situation
		getGasMeterMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getGasMeterMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.devices", nullValue()));

		// Exception Situation
		getGasMeterMock().setMode(ModeEnum.MODE_EXCEPTION);
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
		getGasMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALL).andExpect(jsonPath("$.devices", hasSize(1)))
				.andExpect(
						jsonPath("$.devices[0].deviceType", comparesEqualTo(DeviceTypeEnum.GAS_METER.toString())));

		// Failure Situation
		getGasMeterMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_ALL).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getGasMeterMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_ALL).andExpect(jsonPath("$.devices", nullValue()));

		// Exception Situation
		getGasMeterMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_ALL).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

}
