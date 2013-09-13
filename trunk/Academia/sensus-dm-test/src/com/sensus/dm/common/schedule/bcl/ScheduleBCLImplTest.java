package com.sensus.dm.common.schedule.bcl;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.scheduler.model.Frequency;
import com.sensus.common.scheduler.model.FrequencyTypeEnum;
import com.sensus.common.scheduler.model.ScheduleStatusEnum;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.schedule.bcl.impl.DMScheduleProcessor;
import com.sensus.dm.common.schedule.dac.IScheduleDAC;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.InquiryScheduleRequest;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.action.bcl.IActionBCL;
import com.sensus.dm.elec.action.model.DemandResetEventAction;
import com.sensus.dm.elec.action.model.DemandResponseEventAction;
import com.sensus.dm.elec.action.model.request.InquiryActionRequest;

/**
 * The Class ScheduleBCLImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/common/schedule/schedulebclimpltest.xml"})
public class ScheduleBCLImplTest extends AbstractTestBaseBusiness
{

	/** The Constant THREE_HUNDRED_FOURTEEN. */
	private static final Integer THREE_HUNDRED_FOURTEEN = 314;

	/** The Constant SCHEDULE_ONE. */
	private static final String SCHEDULE_ONE = "Schedule 1";

	/** The Constant TEST_SCHEDULE. */
	private static final String TEST_SCHEDULE = "Test Schedule";

	/** The Constant ONE_THOUSAND_ONE. */
	private static final String ONE_THOUSAND_ONE = "1001";

	/** The Constant TEST. */
	private static final String TEST = "Test";

	/** The Constant FETCH_ALL_SCHEDULE. */
	private static final String FETCH_ALL_SCHEDULE = "fetchAllSchedule";

	/** The Constant FETCH_SCHEDULE. */
	private static final String FETCH_SCHEDULE = "fetchSchedule";

	/** The Constant DELETE_SCHEDULE. */
	private static final String DELETE_SCHEDULE = "deleteSchedule";

	/** The Constant FETCH_CHECK_PROCESSING. */
	private static final String FETCH_CHECK_PROCESSING = "fetchCheckProcessing";

	/** The Constant UPDATE_SCHEDULE. */
	private static final String UPDATE_SCHEDULE = "updateSchedule";

	/** The Constant DELETE_ALL_SCHEDULE_PROPERTY. */
	private static final String DELETE_ALL_SCHEDULE_PROPERTY = "deleteAllScheduleProperty";

	/** The Constant INSERT_SCHEDULE. */
	private static final String INSERT_SCHEDULE = "insertSchedule";

	/** The Constant INSERT_ACTION. */
	private static final String INSERT_ACTION = "insertAction";

	/** The Constant INSERT_SCHEDULE_PROPERTY. */
	private static final String INSERT_SCHEDULE_PROPERTY = "insertScheduleProperty";

	/** The Constant INSERT_PROCESS. */
	private static final String INSERT_PROCESS = "insertProcess";

	/** The Constant TEMP_UPLOAD_FILE_PATH. */
	private static final String TEMP_UPLOAD_FILE_PATH = "/opt/flexnet-epm/upload/";

	/** The Constant FILE_IMPORT. */
	private static final String FILE_IMPORT = "device.csv";

	/** The Constant OTA_METER_FLEXNET_ID. */
	private static final BigInteger OTA_METER_FLEXNET_ID = new BigInteger("46549474");

	/** The Constant OTA_METER_DEVICE_ID. */
	private static final String OTA_METER_DEVICE_ID = "1N6028900474";

	/** The Constant ALL. */
	private static final String ALL = "All";

	// Spring injection points:

	/** The schedule bcl. */
	private IScheduleBCL scheduleBCL;

	/** The schedule processor. */
	private DMScheduleProcessor scheduleProcessor;

	/**
	 * Gets the schedule bcl.
	 * 
	 * @return the schedule bcl
	 */
	public IScheduleBCL getScheduleBCL()
	{
		return scheduleBCL;
	}

	/**
	 * Sets the schedule bcl.
	 * 
	 * @param scheduleBCL the new schedule bcl
	 */
	@Resource(name = "scheduleBCLTarget")
	public void setScheduleBCL(IScheduleBCL scheduleBCL)
	{
		this.scheduleBCL = scheduleBCL;
	}

	/**
	 * Gets the schedule processor.
	 * 
	 * @return the schedule processor
	 */
	public DMScheduleProcessor getScheduleProcessor()
	{
		return scheduleProcessor;
	}

	/**
	 * Sets the schedule processor.
	 * 
	 * @param scheduleProcessor the new schedule processor
	 */
	@Resource
	public void setScheduleProcessor(DMScheduleProcessor scheduleProcessor)
	{
		this.scheduleProcessor = scheduleProcessor;
	}

	// Test Settings:

	/**
	 * Sets the schedule area.
	 */
	public void setScheduleArea()
	{
		setArea(getScheduleBCL(), EPMAreaEnum.SCHEDULE);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setScheduleArea();
	}

	/**
	 * Removes the custom search area.
	 */
	@After
	public void resetMocksToActionArea()
	{
		resetMocksData(getScheduleBCL());
		setScheduleArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateFileCSV()
	{
		InquiryScheduleRequest inquiryScheduleRequest =
				new InquiryScheduleRequest(TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));

		resetMocksToActionArea();

		// success generate file csv
		inquiryScheduleRequest.setProcessId(ONE);
		inquiryScheduleRequest.setFileName(FILE_NAME);
		InternalResponse internalResponse = getScheduleBCL().generateFileCSV(inquiryScheduleRequest);
		TestBaseUtil.assertResponse(internalResponse);

		resetMocksToActionArea();

		// fail fetch all schedule
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class, FETCH_ALL_SCHEDULE);
		internalResponse = getScheduleBCL().generateFileCSV(inquiryScheduleRequest);
		assertEquals(Status.ExceptionError, internalResponse.getStatus());

		resetMocksToActionArea();
	}

	/**
	 * Test generate file csv schedule meters.
	 */
	@Test
	public void testGenerateFileCSVScheduleDevice()
	{
		DMSchedule schedule = new DMSchedule(ONE);
		ScheduleRequest scheduleRequest =
				new ScheduleRequest(schedule, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		// fail fetch schedule by id
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class, FETCH_SCHEDULE);
		InternalResponse internalResponse = getScheduleBCL().generateFileCSVScheduleDevice(scheduleRequest);
		assertEquals(Status.ExceptionError, internalResponse.getStatus());
		resetMocksToActionArea();

		// Success without device
		scheduleRequest.setFileName(FILE_NAME);
		scheduleRequest.setProcessId(ONE);
		internalResponse = getScheduleBCL().generateFileCSVScheduleDevice(scheduleRequest);
		TestBaseUtil.assertResponse(internalResponse);

		// Sucess with group
		schedule = new DMSchedule(ONE, new DMAction(new Group(TestBaseUtil.createDevice())));
		scheduleRequest.setSchedule(schedule);
		internalResponse = getScheduleBCL().generateFileCSVScheduleDevice(scheduleRequest);
		TestBaseUtil.assertResponse(internalResponse);

		// Sucess with tag
		schedule = new DMSchedule(ONE, new DMAction(new Tag(TestBaseUtil.createDevice())));
		scheduleRequest.setSchedule(schedule);
		internalResponse = getScheduleBCL().generateFileCSVScheduleDevice(scheduleRequest);
		TestBaseUtil.assertResponse(internalResponse);
	}

	/**
	 * Test fetch schedule.
	 */
	@Test
	public void testfetchSchedule()
	{
		// Success fetch schedule by id
		ScheduleRequest scheduleRequest = new ScheduleRequest(new DMSchedule(THREE_HUNDRED_FOURTEEN));

		InternalResultsResponse<DMSchedule> response = getScheduleBCL().fetchSchedule(scheduleRequest);
		TestBaseUtil.assertResultResponse(response);

		// Success fetch schedule by action
		scheduleRequest = new ScheduleRequest(new DMSchedule());
		response = getScheduleBCL().fetchSchedule(scheduleRequest);

		TestBaseUtil.assertResultResponse(response);

		// Success fetch schedule by name
		scheduleRequest = new ScheduleRequest(new DMSchedule(TEST_SCHEDULE, new DMAction(ONE)));
		response = getScheduleBCL().fetchSchedule(scheduleRequest);

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch schedulers to execute.
	 */
	@Test
	public void testFetchSchedulersToExecute()
	{
		// Success fetch schedules to execute
		ScheduleRequest scheduleRequest = new ScheduleRequest();
		InternalResultsResponse<DMSchedule> response = getScheduleBCL().fetchSchedulesToExecute(scheduleRequest);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch all schedules.
	 */
	@Test
	public void testFetchAllSchedules()
	{
		// Success fetch all schedule
		InternalResultsResponse<DMSchedule> response =
				getScheduleBCL().fetchAllSchedule(new InquiryScheduleRequest());
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test delete schedule.
	 */
	@Test
	public void testDeleteSchedule()
	{
		ScheduleRequest request = new ScheduleRequest();
		DMSchedule schedule = new DMSchedule(NINETY_NINE);
		request.setSchedule(schedule);

		// Fails canDelete
		InternalResponse response = getScheduleBCL().deleteSchedule(request);
		assertEquals(response.getStatus(), Status.ExceptionError);

		// Fails fetch message processing
		schedule.setId(ONE);
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IProcessBCL.class, FETCH_CHECK_PROCESSING);
		response = getScheduleBCL().deleteSchedule(request);
		assertEquals(response.getStatus(), Status.ExceptionError);
		resetMocksToActionArea();

		// Fails fetch schedule by id
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class, FETCH_SCHEDULE);
		response = getScheduleBCL().deleteSchedule(request);
		assertEquals(response.getStatus(), Status.ExceptionError);
		resetMocksToActionArea();

		// Fails delete schedule
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class, DELETE_SCHEDULE);
		response = getScheduleBCL().deleteSchedule(request);
		assertEquals(response.getStatus(), Status.ExceptionError);
		resetMocksToActionArea();

		// Success delete schedule
		response = getScheduleBCL().deleteSchedule(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test insert schedule.
	 */
	@Test
	public void testInsertSchedule()
	{
		DMSchedule schedule = new DMSchedule(TEST);
		ScheduleRequest request = new ScheduleRequest(schedule);

		// fail can schedule be update
		InternalResponse response = getScheduleBCL().insertSchedule(request);
		assertEquals(response.getStatus(), Status.ExceptionError);

		schedule = new DMSchedule(ONE, ScheduleStatusEnum.ENABLED, SCHEDULE_ONE, TEST_SCHEDULE,
				new Date(), TestBaseUtil.createFrequency());
		request.setSchedule(schedule);

		// create Action
		schedule.setDmAction(new DemandResetEventAction(Boolean.FALSE, Boolean.TRUE, new Date()));
		request.setSchedule(schedule);

		// fail insert action
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IActionBCL.class, INSERT_ACTION);
		response = getScheduleBCL().insertSchedule(request);
		assertEquals(response.getStatus(), Status.ExceptionError);
		resetMocksToActionArea();

		// fail insert schedule
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class, INSERT_SCHEDULE);
		response = getScheduleBCL().insertSchedule(request);
		assertEquals(response.getStatus(), Status.ExceptionError);
		resetMocksToActionArea();

		// fail insert schedule property
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class, INSERT_SCHEDULE_PROPERTY);
		response = getScheduleBCL().insertSchedule(request);
		assertEquals(response.getStatus(), Status.ExceptionError);
		resetMocksToActionArea();

		// fail insert schedule process
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IProcessBCL.class, INSERT_PROCESS);
		response = getScheduleBCL().insertSchedule(request);
		assertEquals(response.getStatus(), Status.ExceptionError);
		resetMocksToActionArea();

		// Success insert Schedule without device file
		response = getScheduleBCL().insertSchedule(request);
		TestBaseUtil.assertResponse(response);

		// Success insert Schedule with upload device
		request.setUploadIds(ONE_THOUSAND_ONE);
		request.setIdFileType(PropertyEnum.NETWORK_ADDRESS);
		response = getScheduleBCL().insertSchedule(request);
		TestBaseUtil.assertResponse(response);

		// Success insert Schedule with device file
		request.setIdsFile(createFile(ONE_THOUSAND_ONE.toString()));
		request.setFileName(FILE_IMPORT);
		request.setIdFileType(PropertyEnum.NETWORK_ADDRESS);
		response = getScheduleBCL().insertSchedule(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test update schedule.
	 */
	@Test
	public void testUpdateSchedule()
	{
		DMSchedule schedule = new DMSchedule(NINETY_NINE);
		ScheduleRequest request = new ScheduleRequest(schedule);

		// fail can schedule be update
		InternalResponse response = getScheduleBCL().updateSchedule(request);
		assertEquals(response.getStatus(), Status.ExceptionError);

		schedule.setId(ONE);

		// fail fetch message processing
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IProcessBCL.class, FETCH_CHECK_PROCESSING);
		response = getScheduleBCL().updateSchedule(request);
		assertEquals(response.getStatus(), Status.ExceptionError);
		resetMocksToActionArea();

		// fail update schedule
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class, UPDATE_SCHEDULE);
		response = getScheduleBCL().updateSchedule(request);
		assertEquals(response.getStatus(), Status.ExceptionError);
		resetMocksToActionArea();

		// create frequency
		Frequency frequency = new Frequency();
		frequency.setFrequencyTypeEnum(FrequencyTypeEnum.DAILY);
		frequency.setStartOnDate(new Date());
		frequency.setNeverEnds(Boolean.TRUE);
		frequency.setTimeToRepeat(1);

		schedule = new DMSchedule(ONE, ScheduleStatusEnum.ENABLED, SCHEDULE_ONE, TEST_SCHEDULE,
				new Date(), frequency);

		// insert Action
		schedule.setDmAction(new DemandResetEventAction(Boolean.FALSE, Boolean.TRUE, new Date()));
		request.setSchedule(schedule);

		// fail delete all schedule property
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class, DELETE_ALL_SCHEDULE_PROPERTY);
		response = getScheduleBCL().updateSchedule(request);
		assertEquals(response.getStatus(), Status.ExceptionError);
		resetMocksToActionArea();

		// fail insert schedule property
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class, INSERT_SCHEDULE_PROPERTY);
		response = getScheduleBCL().updateSchedule(request);
		assertEquals(response.getStatus(), Status.ExceptionError);
		resetMocksToActionArea();

		// update Schedule - success
		schedule.setDescription(TEST);
		request.setUploadIds(ONE_THOUSAND_ONE);
		request.setIdFileType(PropertyEnum.SCHEDULE_ID);
		response = getScheduleBCL().updateSchedule(request);
		TestBaseUtil.assertResponse(response);

		// update Schedule - success
		schedule.setDescription(TEST);
		request.setIdsFile(new File(ONE_THOUSAND_ONE));
		request.setIdFileType(PropertyEnum.SCHEDULE_ID);
		response = getScheduleBCL().updateSchedule(request);
		TestBaseUtil.assertResponse(response);

	}

	/**
	 * Test execute schedule processor.
	 */
	@Test
	public void testExecuteScheduleProcessor()
	{
		getScheduleProcessor().execute();
	}

	/**
	 * Test update schedule status.
	 */
	@Test
	public void testUpdateScheduleStatus()
	{
		// Success update schedule
		DMSchedule schedule = new DMSchedule(ONE);
		schedule.setStatusEnum(ScheduleStatusEnum.ENABLED);
		schedule.setDmAction(new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)));
		schedule.getDmAction().setActionTime(new Date());

		schedule.setFrequency(TestBaseUtil.createFrequency());

		InternalResponse response =
				getScheduleBCL().updateScheduleStatus(
						new ScheduleRequest(schedule, TestBaseUtil.createUserContext()));

		TestBaseUtil.assertResponse(response);

	}

	/**
	 * Test fetch schedule by group.
	 */
	@Test
	public void testFetchScheduleByGroup()
	{
		// Success fetch schedule by group
		ScheduleRequest scheduleRequest = new ScheduleRequest(new DMSchedule(new DMAction(new Group(ONE))));
		InternalResultsResponse<DMSchedule> response =
				getScheduleBCL().fetchScheduleByGroup(scheduleRequest);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch schedule by device.
	 */
	@Test
	public void testFetchScheduleByDevice()
	{
		// Success fetch schedule by device
		ScheduleRequest scheduleRequest =
				new ScheduleRequest(new DMSchedule(new DMAction(new ActionType(
						ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT),
						TestBaseUtil.createDevice())));
		InternalResultsResponse<DMSchedule> response =
				getScheduleBCL().fetchScheduleByDevice(scheduleRequest);
		TestBaseUtil.assertResultResponse(response);
	}

	@Test
	public void testInsertScheduleOnDemand()
	{
		// Demand Response Meter
		// only Meters and Enteks will actually be sent OTA
		//
		// successful test case for a Meter
		DemandResponseEventAction demandResponse = new DemandResponseEventAction(ONE, true, true, null);
		Calendar c = new GregorianCalendar();
		c.setTime(new Date());
		demandResponse.setActionTime(c.getTime());

		demandResponse.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET_ID,
				OTA_METER_DEVICE_ID));
		demandResponse.setEnrollmentCode(0);
		demandResponse.setDemandResponseDuration(FIVE);
		demandResponse.setRandomizeStart(Boolean.TRUE);
		demandResponse.setRandomizeEnd(Boolean.FALSE);
		demandResponse.setCooling(FIVE);
		demandResponse.setCriticalityLevel(ONE);
		demandResponse.addDeviceClass(ALL);

		InquiryActionRequest request =
				new InquiryActionRequest(demandResponse, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		request.setTenant(DM_TENANT);

		InternalResponse insertResponse = getScheduleBCL().insertScheduleOnDemand(request);
		TestBaseUtil.assertResponse(insertResponse);

		// // Demand Response Meter
		// // only Meters and Enteks will actually be sent OTA
		// //
		// // successful test case for a Entek LCM
		// demandResponse = new DemandResponseEventAction(ONE, true, true, null);
		// c = new GregorianCalendar();
		// c.setTime(new Date());
		// c.add(Calendar.DATE, 1);
		// demandResponse.setActionTime(c.getTime());
		//
		// demandResponse.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.LCM, OTA_ENTEK_FLEXNET_ID,
		// OTA_ENTEK_DEVICE_ID));
		// demandResponse.setEnrollmentCode(0);
		// demandResponse.setDemandResponseDuration(FIVE);
		// demandResponse.setRandomizeStart(Boolean.TRUE);
		// demandResponse.setRandomizeEnd(Boolean.FALSE);
		// demandResponse.setCooling(FIVE);
		// demandResponse.setCriticalityLevel(ONE);
		// demandResponse.addDeviceClass(DeviceClassEnum.EXTERIOR_LIGHTING.getDeviceClassDescription());
		// demandResponse.addDeviceClass(DeviceClassEnum.HVAC_COMPRESSOR.getDeviceClassDescription());
		// demandResponse.addDeviceClass(DeviceClassEnum.POOL_PUMP.getDeviceClassDescription());
		// demandResponse.addDeviceClass(DeviceClassEnum.SIMPLE_MISC.getDeviceClassDescription());
		// demandResponse.addDeviceClass(DeviceClassEnum.IRRIGATION_PUMP.getDeviceClassDescription());
		// demandResponse.addDeviceClass(DeviceClassEnum.WATER_HEATER.getDeviceClassDescription());
		//
		// request = new InquiryActionRequest(demandResponse, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		// request.setTenant(DM_TENANT);
		//
		// insertResponse = getActionBCL().applyDeviceToAction(request);
		// TestBaseUtil.assertResponse(insertResponse);
		//
		// // Demand Response Meter
		// // only Meters and Enteks will actually be sent OTA
		// //
		// // Fail test case for a HAN device
		// demandResponse = new DemandResponseEventAction(ONE, true, true, null);
		// demandResponse.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.HAN_DEVICE, OTA_HAN_FLEXNET_ID,
		// OTA_HAN_DEVICE_ID));
		// demandResponse.setEnrollmentCode(0);
		// demandResponse.setDemandResponseDuration(FIVE);
		// demandResponse.setRandomizeStart(Boolean.TRUE);
		// demandResponse.setRandomizeEnd(Boolean.FALSE);
		// demandResponse.setCooling(FIVE);
		// demandResponse.setCriticalityLevel(ONE);
		//
		// demandResponse.setDeviceClasses(new ArrayList<String>());
		// request = new InquiryActionRequest(demandResponse, TestBaseUtil.createUserContext(USER_CONTEXT_LOCALE));
		// request.setTenant(DM_TENANT);
		//
		// insertResponse = getActionBCL().applyDeviceToAction(request);
		// TestBaseUtil.assertResponse(insertResponse);
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// private

	/**
	 * Creates the file import device to action.
	 * 
	 * @param strDevice the str device
	 * @return the string
	 */
	private File createFile(String strDevice)
	{
		// Create file
		File file = new File(TEMP_UPLOAD_FILE_PATH + FILE_IMPORT);
		try
		{
			FileWriter fstream = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(strDevice);
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return file;
	}
}
