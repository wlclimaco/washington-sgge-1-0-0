package com.sensus.dm.controller.summary.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.process.unittest.ProcessBCFMockImpl;
import com.sensus.dm.controller.process.unittest.ProcessSummaryBCFMockImpl;
import com.sensus.dm.controller.summary.SummaryAPIController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class SummaryAPIControllerTest.
 */
@ContextConfiguration(locations = {"classpath:sensus-dm-wui-unittest-context-test.xml"})
public class SummaryAPIControllerTest extends AbstractTestBase
{

	/** The Constant API_SUMMARY_FETCH. */
	private static final String API_SUMMARY_FETCH = "/api/summary/fetch";

	/** The data. */
	private String data;

	/**
	 * Gets the data.
	 * 
	 * @return the data
	 */
	@Override
	public String getData()
	{
		return data;
	}

	/**
	 * Sets the data.
	 * 
	 * @param data the data to set
	 */
	@Override
	public void setData(String data)
	{
		this.data = data;
	}

	/**
	 * Gets the process mock.
	 * 
	 * @return the process mock
	 */
	private ProcessBCFMockImpl getProcessMock()
	{
		return (ProcessBCFMockImpl)SensusAppContext.getApplicationContext().getBean(SummaryAPIController.class)
				.getProcessBCF();
	}

	/**
	 * Process summary bcf mock.
	 * 
	 * @return the process summary bcf mock impl
	 */
	private ProcessSummaryBCFMockImpl getProcessSummaryBCFMock()
	{
		return (ProcessSummaryBCFMockImpl)SensusAppContext.getApplicationContext().getBean(SummaryAPIController.class)
				.getProcessSummaryBCF();
	}

	/**
	 * Fetch.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetch() throws Exception
	{
		// =============================Import Han===============================
		// set...
		setData("{\"id\":\"220\",\"type\":\"importHan\"}");

		// Success Situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_SUMMARY_FETCH)
				.andExpect(jsonPath("$.processes", hasSize(1)))
				.andExpect(jsonPath("$.processes[0].processItems", hasSize(4)))
				.andExpect(
						jsonPath("$.processes[0].processItems[2].device.macAddress",
								containsString("00:00:00:00:00:b3:6d:ec")))
				.andExpect(
						jsonPath("$.processes[0].processItems[3].device.macAddress",
								containsString("00:07:a6:ff:ff:00:92:3f")));

		// Failure Situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_SUMMARY_FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(API_SUMMARY_FETCH).andExpect(jsonPath("$.processes", nullValue()));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_SUMMARY_FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// =============================Demand Response===============================
		setData("{\"id\":\"220\",\"type\":\"demandResponse\"}");

		// Success Situation
		getProcessSummaryBCFMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_SUMMARY_FETCH)
				.andExpect(jsonPath("$.processes", hasSize(1)))
				.andExpect(jsonPath("$.processes[0].processItems", hasSize(4)))
				.andExpect(
						jsonPath("$.processes[0].processItems[2].device.macAddress",
								containsString("00:00:00:00:00:b3:6d:ec")))
				.andExpect(
						jsonPath("$.processes[0].processItems[3].device.macAddress",
								containsString("00:07:a6:ff:ff:00:92:3f")));

		// Failure Situation
		getProcessSummaryBCFMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_SUMMARY_FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getProcessSummaryBCFMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(API_SUMMARY_FETCH).andExpect(jsonPath("$.processes", nullValue()));

		// Exception situation
		getProcessSummaryBCFMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_SUMMARY_FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// =============================Communication Summary ===============================
		setData("{\"id\":\"220\",\"type\":\"communicationSummary\"}");

		// Success Situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_SUMMARY_FETCH)
				.andExpect(jsonPath("$.processes", hasSize(1)))
				.andExpect(jsonPath("$.processes[0].processItems", hasSize(4)))
				.andExpect(
						jsonPath("$.processes[0].processItems[2].device.macAddress",
								containsString("00:00:00:00:00:b3:6d:ec")))
				.andExpect(
						jsonPath("$.processes[0].processItems[3].device.macAddress",
								containsString("00:07:a6:ff:ff:00:92:3f")));

		// Failure Situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_SUMMARY_FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(API_SUMMARY_FETCH).andExpect(jsonPath("$.processes", nullValue()));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_SUMMARY_FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// =============================Demand Read Ping ===============================
		setData("{\"id\":\"220\",\"type\":\"demandRead\"}");

		// Success Situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_SUMMARY_FETCH)
				.andExpect(jsonPath("$.processes", hasSize(1)))
				.andExpect(jsonPath("$.processes[0].processItems", hasSize(4)))
				.andExpect(
						jsonPath("$.processes[0].processItems[2].device.macAddress",
								containsString("00:00:00:00:00:b3:6d:ec")))
				.andExpect(
						jsonPath("$.processes[0].processItems[3].device.macAddress",
								containsString("00:07:a6:ff:ff:00:92:3f")));

		// Failure Situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_SUMMARY_FETCH).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(API_SUMMARY_FETCH).andExpect(jsonPath("$.processes", nullValue()));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_SUMMARY_FETCH).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}
}
