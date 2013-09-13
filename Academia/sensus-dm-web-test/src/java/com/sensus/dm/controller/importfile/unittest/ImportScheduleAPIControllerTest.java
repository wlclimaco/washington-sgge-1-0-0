package com.sensus.dm.controller.importfile.unittest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.controller.importfile.ImportScheduleAPIController;
import com.sensus.dm.controller.schedule.unittest.ScheduleBCFMockImpl;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.SessionAuthenticationTestUtil;

/**
 * The Class ImportScheduleAPIControllerTest.
 */
public class ImportScheduleAPIControllerTest extends AbstractTestBase
{

	/** The Constant ACTION_ID. */
	private static final String ACTION_ID = "actionId";

	/** The Constant ACTION_NAME. */
	private static final String ACTION_NAME = "actionName";

	/** The Constant ACTION_TEST. */
	private static final String ACTION_TEST = "actionTest";

	/** The Constant DESCRIPTION_SCHEDULE. */
	private static final String DESCRIPTION_SCHEDULE = "scheduledEventDescription";

	/** The Constant EVENT_TIME. */
	private static final String EVENT_TIME = "scheduleEventTime";

	/** The Constant EVENT_WHEN. */
	private static final String EVENT_WHEN = "scheduledEventWhen";

	/** The Constant IMPORT_BY. */
	private static final String IMPORT_BY = "importBy";

	/** The Constant NAME_SCHEDULE. */
	private static final String NAME_SCHEDULE = "scheduledEventName";

	/** The Constant UPLOAD. */
	private static final String UPLOAD = "/schedule/upload";

	/**
	 * Gets the schedule mock.
	 * 
	 * @return the schedule mock
	 */
	private ScheduleBCFMockImpl getScheduleMock()
	{
		return (ScheduleBCFMockImpl)SensusAppContext.getApplicationContext().getBean(ImportScheduleAPIController.class)
				.getScheduleBCF();
	}

	/**
	 * Upload.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void upload() throws Exception
	{
		/*
		 * Success Situation
		 * Insert Schedule with file and upload type equals "Device ID"
		 */
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);

		// Open File
		File file = generateTestFile();

		MockMultipartHttpServletRequestBuilder mockRequestBuilder = fileUpload(UPLOAD, new Object[] {});

		MockMultipartFile mockMultipartFile = new MockMultipartFile("upload",
				"FileName.csv", "multipart/form-data",
				FileUtils.readFileToByteArray(file));

		mockRequestBuilder.file(mockMultipartFile);
		mockRequestBuilder.param(IMPORT_BY, "DEVICE_ID");
		mockRequestBuilder.param(NAME_SCHEDULE, "Schedule Name");
		mockRequestBuilder.param(DESCRIPTION_SCHEDULE, "Schedule Description");
		mockRequestBuilder.param(ACTION_TEST, "");
		mockRequestBuilder.param(ACTION_NAME, "sensus.epm.action.initiate.demand.reset");
		mockRequestBuilder.param(EVENT_WHEN, "10/10/2013");
		mockRequestBuilder.param(EVENT_TIME, "10:00am");
		mockRequestBuilder.param(ACTION_ID, "1");

		getMockMvc().perform(
				mockRequestBuilder.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk());

		/*
		 * Success Situation
		 * Insert Schedule with meter list and upload type equals "NetWork Address"
		 */
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);

		mockRequestBuilder = fileUpload(UPLOAD, new Object[] {});

		mockRequestBuilder.param(IMPORT_BY, "NETWORK_ADDRESS");
		mockRequestBuilder.param(NAME_SCHEDULE, "Schedule Name");
		mockRequestBuilder.param(DESCRIPTION_SCHEDULE, "Schedule Description");
		mockRequestBuilder.param(ACTION_NAME, "sensus.epm.action.demand.response");
		mockRequestBuilder.param("demandResponseWhen", "10/10/2013");
		mockRequestBuilder.param("demand_response_time", "10:00am");
		mockRequestBuilder.param(ACTION_ID, "1");
		mockRequestBuilder.param("meterList", "1001,1002,1003");
		mockRequestBuilder.param(ACTION_TEST, " ");
		mockRequestBuilder.param("demandResponseDuration", "10");
		mockRequestBuilder.param("enrollmentCode", "10");
		mockRequestBuilder.param("demandResponseDate", "10/10/2013");
		mockRequestBuilder.param("demandResponseTime", "10:00am");
		mockRequestBuilder.param("repeatCheckBox", "true");
		mockRequestBuilder.param("repeatType", "DAILY");
		mockRequestBuilder.param("repeatsEveryValueDaily", "1");
		mockRequestBuilder.param("bMonitored", "true");
		mockRequestBuilder.param("endsDaily", "never");
		mockRequestBuilder
				.param("hancheck",
						"HVACCompressor, StripHeaters, WaterHeater, PoolPump, SmartAppliances, IrrigationPump, IndustrialLoads, SimpleMiscLoads, ExteriorLighting, InteriorLighting, ElectricVehicle, GenerationSystemss");

		getMockMvc().perform(
				mockRequestBuilder.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk());

		/*
		 * Success Situation
		 * Update Schedule with group list and upload type equals "NetWork Address"
		 */
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);

		mockRequestBuilder = fileUpload(UPLOAD, new Object[] {});

		mockRequestBuilder.param(IMPORT_BY, "NETWORK_ADDRESS");
		mockRequestBuilder.param("scheduledEventId", "1");
		mockRequestBuilder.param(NAME_SCHEDULE, "Schedule Name");
		mockRequestBuilder.param(DESCRIPTION_SCHEDULE, "Schedule Description");
		mockRequestBuilder.param(ACTION_TEST, "sensus.epm.action.send.han.text.message");
		mockRequestBuilder.param(ACTION_NAME, "sensus.epm.action.send.han.text.message");
		mockRequestBuilder.param(EVENT_WHEN, "10/10/2013");
		mockRequestBuilder.param(EVENT_TIME, "10:00am");
		mockRequestBuilder.param("sendMessagesDate", "10/10/2013");
		mockRequestBuilder.param("sendMessagesTime", "10:00am");
		mockRequestBuilder.param(ACTION_ID, "1");
		mockRequestBuilder.param("textMessageDuration", "10");
		mockRequestBuilder.param("textMessageHan", "Han Text Message");
		mockRequestBuilder.param("repeatCheckBox", "true");
		mockRequestBuilder.param("repeatType", "EVERY_WEEKDAY");
		mockRequestBuilder.param("repeatsEveryValueDaily", "1");
		mockRequestBuilder.param("bMonitored", "true");
		mockRequestBuilder.param("endsWeekday", "after");
		mockRequestBuilder.param("occurrencesWeekday", "10");
		mockRequestBuilder.param("preProgram", "sendLater");
		mockRequestBuilder.param("groupList", "1,2,3");

		getMockMvc().perform(
				mockRequestBuilder.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(status().isOk());

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		getMockMvc().perform(
				mockRequestBuilder.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(
						status().isOk());

		// Exception Situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		getMockMvc().perform(
				mockRequestBuilder.session(SessionAuthenticationTestUtil.getSessionTest(ServiceTypeEnum.ELECTRIC)))
				.andExpect(
						status().isOk());

		file.delete();

	}

	/**
	 * Generate test file.
	 * 
	 * @return the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private File generateTestFile() throws IOException
	{
		File file = new File("C:/fileTest.csv");
		BufferedWriter br = new BufferedWriter(new FileWriter(file));
		br.write("test");
		br.close();

		return file;
	}
}