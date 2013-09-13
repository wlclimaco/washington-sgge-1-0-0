package com.sensus.dm.controller.electricmeter.unittest;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.electricmeter.ElectricMeterDeviceAPIController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class ElectricMeterDeviceAPIControllerTest.
 */
public class ElectricMeterDeviceAPIControllerTest extends AbstractTestBase
{
	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/api/electricmeterdevice/fetchAll";

	/**
	 * Gets the device mock.
	 * 
	 * @return the device mock
	 */
	private ElectricMeterBCFMockImpl getElectricMeterMock()
	{
		return (ElectricMeterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ElectricMeterDeviceAPIController.class).getElectricMeterBCF();
	}

	/**
	 * Fetch All.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchAll() throws Exception
	{
		// Success Situation
		setData("{\"timeZone\":\"9\",\"dateFormat\":\"dd/MM/yyyy\",\"startRow\":0,\"endRow\":0,\"pageSize\":50,"
				+ "\"sortExpressions\":[{\"field\":\"device_id\",\"direction\":\"Ascending\"}],\"deviceSearch\":"
				+ "{\"electricMeterSearch\":{\"electricMeter\":{\"radio\":{},\"configuration\":{\"firmwareMeter\":null}},"
				+ "\"remoteConnectStatusEnumList\":null,\"electricMeterLifecycleStateEnumList\":[\"INSTALLED\"]},"
				+ "\"groups\":null,\"tags\":null,\"deviceModels\":null,\"processId\":null,\"startDate\":null,\"endDate\":null}}");

		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_ALL)
				.andExpect(jsonPath("$.devices", hasSize(1)))
				.andExpect(jsonPath("$.devices[0].deviceType", comparesEqualTo("ELECTRIC_METER")))
				.andExpect(jsonPath("$.devices[0].radio.flexNetId", comparesEqualTo(254453223)));

		// Failure, Empty and Exception Situation
		setData("{}");

		// Failure Situation
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
