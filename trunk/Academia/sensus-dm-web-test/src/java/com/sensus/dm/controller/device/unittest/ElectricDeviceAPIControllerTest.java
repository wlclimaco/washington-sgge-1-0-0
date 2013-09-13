package com.sensus.dm.controller.device.unittest;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.electricdevice.ElectricDeviceAPIController;
import com.sensus.dm.controller.electricmeter.unittest.ElectricMeterBCFMockImpl;
import com.sensus.dm.controller.group.unittest.GroupBCFMockImpl;
import com.sensus.dm.controller.process.unittest.ProcessSummaryBCFMockImpl;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class ElectricDeviceAPIControllerTest.
 */
public class ElectricDeviceAPIControllerTest extends AbstractTestBase
{
	/** The Constant FETCH. */
	private static final String FETCH = "/api/electricdevice/fetch";

	/** The Constant FETCH_DEMAND_RESPONSE. */
	private static final String FETCH_DEMAND_RESPONSE = "/api/electricdevice/fetchDemandResponse";

	/**
	 * Gets the electric meter mock.
	 * 
	 * @return the electric meter mock
	 */
	private ElectricMeterBCFMockImpl getElectricMeterMock()
	{
		return (ElectricMeterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ElectricDeviceAPIController.class).getElectricMeterBCF();
	}

	/**
	 * Gets the device mock.
	 * 
	 * @return the device mock
	 */
	private DeviceBCFMockImpl getDeviceMock()
	{
		return (DeviceBCFMockImpl)SensusAppContext.getApplicationContext().getBean(ElectricDeviceAPIController.class)
				.getDeviceBCF();
	}

	/**
	 * Gets the group mock.
	 * 
	 * @return the group mock
	 */
	private GroupBCFMockImpl getGroupMock()
	{
		return (GroupBCFMockImpl)SensusAppContext.getApplicationContext().getBean(ElectricDeviceAPIController.class)
				.getGroupBCF();
	}

	/**
	 * Gets the process summary mock.
	 * 
	 * @return the process summary mock
	 */
	private ProcessSummaryBCFMockImpl getProcessSummaryMock()
	{
		return (ProcessSummaryBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ElectricDeviceAPIController.class).getProcessSummaryBCF();
	}

	/**
	 * Fetch.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetch() throws Exception
	{
		setData("{\"groupIds\":[1,2]}");
		// Success Situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.devices", hasSize(9)));

		setData("{\"premiseId\":\"1\"}");
		// Success Situation
		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.devices", hasSize(2)));

		setData("{\"id\":\"993917140\",\"type\":\"search\"}");
		// Success Situation
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.devices", hasSize(4)));

		setData("{\"typeId\":\"1\",\"type\":\"manufacture\"}");
		// Success Situation
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.devices", hasSize(10)));

		setData("{\"typeId\":\"2\",\"type\":\"manufacture\"}");
		// Success Situation
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.devices", hasSize(10)));

		setData("{\"manufacture\":\"manufacture\",\"type\":\"model\", \"typeId\":\"1\"}");
		// Success Situation
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.devices", hasSize(10)));

		setData("{\"manufacture\":\"manufacture\",\"type\":\"model\", \"typeId\":\"2\"}");
		// Success Situation
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.devices", hasSize(10)));

		setData("{\"id\":\"993917140\",\"deviceType\":\"LCM\"}");
		// Success Situation
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.devices", hasSize(1)));

		setData("{\"macAddress\":\"993917140\",\"deviceType\":\"LCM\"}");
		// Success Situation
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.devices", hasSize(1)));

		setData("{\"deviceId\":\"1010M\",\"deviceType\":\"LCM\"}");
		// Success Situation
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH).andExpect(jsonPath("$.devices", hasSize(1)));

		// Failure Situation
		getElectricMeterMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(false)));

		// Empty Situation
		getElectricMeterMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH).andExpect(jsonPath("$.devices", nullValue()));

		// Exception situation
		getElectricMeterMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	/**
	 * Fetch demand response.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchDemandResponse() throws Exception
	{
		/*
		 * FETCH DEMAND RESPONSE PROGRAM PARTICIPATION
		 */
		setData("{\"type\" : \"demandResponseProgramParticipation\", \"id\" : \"12345\"}");

		// Success Situation
		getProcessSummaryMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_DEMAND_RESPONSE).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true))).andExpect(
				jsonPath("$.processes", hasSize(1)));

		// Failure Situation
		getProcessSummaryMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_DEMAND_RESPONSE).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(false)));

		// Empty Situation
		getProcessSummaryMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_DEMAND_RESPONSE).andExpect(jsonPath("$.processes", nullValue()));

		// Exception situation
		getProcessSummaryMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_DEMAND_RESPONSE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		/*
		 * FETCH DEMAND RESPONSE SETUP
		 */
		setData("{\"type\" : \"demandResponseSetup\", \"id\" : \"12345\"}");

		// Success Situation
		getProcessSummaryMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_DEMAND_RESPONSE).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true))).andExpect(
				jsonPath("$.processes", hasSize(1)));

		// Failure Situation
		getProcessSummaryMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_DEMAND_RESPONSE).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(false)));

		// Empty Situation
		getProcessSummaryMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(FETCH_DEMAND_RESPONSE).andExpect(jsonPath("$.processes", nullValue()));

		// Exception situation
		getProcessSummaryMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_DEMAND_RESPONSE).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}
}
