package com.sensus.dm.common.process.dac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.device.model.DeviceColumnEnum;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemHistory;
import com.sensus.dm.common.process.model.ProcessItemHistoryStatusEnum;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessOrderByEnum;
import com.sensus.dm.common.process.model.ProcessSearch;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.util.AbstractTestBaseDAC;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.action.model.DemandResponseEventAction;
import com.sensus.dm.elec.action.model.request.ActionRequest;

/**
 * The Class ProcessDACImplTest.
 * 
 * @author QAT Global
 */
public class ProcessDACImplTest extends AbstractTestBaseDAC
{

	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(ProcessDACImplTest.class);

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant STATUS_SHOULD_BE_OPERATION_SUCCESS. */
	private static final String STATUS_SHOULD_BE_OPERATION_SUCCESS = "Status should be OperationSuccess";

	/** The Constant CONST_PROCESS_VIEW_ID. */
	private static final String CONST_PROCESS_VIEW_ID = "ProcessView id: ";

	/** The Constant CREATE_GROUP. */
	private static final String CREATE_GROUP = "Create group";

	/** The Constant FIRST_BILLING_CYCLE. */
	private static final String FIRST_BILLING_CYCLE = "1stBillingCycle";

	/** The Constant ALL. */
	private static final String ALL = "All";

	/** The Constant USER_ROD. */
	private static final String USER_ROD = "rod";

	// -------------------------------------------------------------------------
	// Getters and setters:

	/** The process default. */
	private DMProcess processDefault;

	/**
	 * Gets the process default.
	 * 
	 * @return the process default
	 */
	public DMProcess getProcessDefault()
	{
		return processDefault;
	}

	/**
	 * Sets the process default.
	 * 
	 * @param processDefault the new process default
	 */
	public void setProcessDefault(DMProcess processDefault)
	{
		this.processDefault = processDefault;
	}

	/**
	 * Setup test.
	 */
	@Before
	public void setupTest()
	{
		setCacheStatementScope(getProcessDAC());
		insertProcess(TestBaseUtil.createProcess(), ServiceTypeEnum.WATER);
		setProcessDefault(insertProcess(TestBaseUtil.createProcess()));
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test fetch processes.
	 * 
	 */
	@Test
	public void testFetchProcesses()
	{
		InquiryProcessRequest processRequest = TestBaseUtil.createInquiryProcessRequest(DeviceColumnEnum.STATUS);
		processRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		// Test order by Status
		processRequest.addSortExpressions(new SortExpression(ProcessOrderByEnum.STATUS.getValue(),
				Direction.Descending));
		InternalResultsResponse<DMProcess> response = getProcessDAC().fetchProcesses(processRequest);
		TestBaseUtil.assertResultResponse(response);

		processRequest.setServiceTypeEnum(ServiceTypeEnum.WATER);
		response = getProcessDAC().fetchProcesses(processRequest);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch by id.
	 * 
	 */
	@Test
	public void testFetchProcessById()
	{
		// Fetch process by id
		ProcessRequest processRequest = new ProcessRequest(getProcessDefault(), TestBaseUtil.createUserContext());

		InternalResultsResponse<DMProcess> response = getProcessDAC().fetchProcessById(processRequest);
		TestBaseUtil.assertResultResponse(response);

		// Fetch process with import HAN device action
		processRequest.addProcessAsFirstElement(insertProcess(TestBaseUtil.createProcess(ProcessStatusEnum.COMPLETED,
				TestBaseUtil.createAction(ActionTypeEnum.IMPORT_HAN_DEVICE))));

		response = getProcessDAC().fetchProcessById(processRequest);
		TestBaseUtil.assertResultResponse(response);

		// Fetch process with delete HAN device action
		processRequest.addProcessAsFirstElement(insertProcess(TestBaseUtil.createProcess(ProcessStatusEnum.COMPLETED,
				TestBaseUtil.createAction(ActionTypeEnum.DELETE_HAN_DEVICE))));

		response = getProcessDAC().fetchProcessById(processRequest);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch process failed.
	 */
	@Test
	public void testFetchProcessFailed()
	{
		// Fetch process by id
		ProcessRequest processRequest =
				new ProcessRequest(getProcessDefault(), TestBaseUtil.createUserContext(), ProcessItemStatusEnum.FAILED);

		InternalResultsResponse<DMProcess> response = getProcessDAC().fetchProcessById(processRequest);
		TestBaseUtil.assertResultResponse(response);

		// Fetch process with import HAN device action
		processRequest.getProcessList().set(0, insertProcess(TestBaseUtil.createProcess(ProcessStatusEnum.COMPLETED,
				TestBaseUtil.createAction(ActionTypeEnum.IMPORT_HAN_DEVICE))));

		response = getProcessDAC().fetchProcessById(processRequest);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch process items by process id and flexnet id.
	 */
	@Test
	public void testFetchProcessItemIdsByProcess()
	{
		ProcessRequest request = new ProcessRequest(getProcessDefault(), TestBaseUtil.createUserContext());

		// Insert process items
		insertProcessItems(request);

		InternalResultsResponse<ProcessItem> internalResponse =
				getProcessDAC().fetchProcessItemsByProcessId(request);

		TestBaseUtil.assertResultResponse(internalResponse);
	}

	/**
	 * Test fetch monitored process.
	 * 
	 */
	@Test
	public void testFetchMonitoredProcess()
	{
		InquiryProcessRequest inquiryProcessRequest = TestBaseUtil.createInquiryProcessRequest(DeviceColumnEnum.STATUS);
		inquiryProcessRequest.addProcess(getProcessDefault());

		inquiryProcessRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		InternalResultsResponse<DMProcess> response = getProcessDAC().fetchMonitoredProcess(inquiryProcessRequest);
		TestBaseUtil.assertResultResponse(response);

		inquiryProcessRequest = TestBaseUtil.createInquiryProcessRequest(DeviceColumnEnum.STATUS);
		inquiryProcessRequest.addProcess(insertProcess(TestBaseUtil.createProcess(), ServiceTypeEnum.WATER));

		inquiryProcessRequest.setServiceTypeEnum(ServiceTypeEnum.WATER);
		response = getProcessDAC().fetchMonitoredProcess(inquiryProcessRequest);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test insert process.
	 */
	@Test
	public void testInsertProcess()
	{
		insertProcess(TestBaseUtil.createProcess(ProcessStatusEnum.COMPLETED,
				TestBaseUtil.createAction(ActionTypeEnum.IMPORT_HAN_DEVICE)));
	}

	/**
	 * Test insert process property.
	 */
	@Test
	public void testInsertProcessProperty()
	{
		ProcessRequest request = TestBaseUtil.createProcessRequest();
		request.addProcessAsFirstElement(getProcessDefault());
		request.getFirstProcess().addProperty(new Property(PropertyEnum.GROUP_NAME.getValue(), FIRST_BILLING_CYCLE));

		insertProcessProperty(request);
	}

	/**
	 * Test update process.
	 */
	@Test
	public void testUpdateProcess()
	{
		// Prepare update process
		DMProcess process = getProcessDefault();
		process.setAction(insertAction(TestBaseUtil.createAction(ActionTypeEnum.IMPORT_HAN_DEVICE)));
		process.setEndTime(Calendar.getInstance().getTime());
		process.setIsDashboardMonitored(Boolean.FALSE);
		process.setIsProcessComplete(Boolean.FALSE);
		process.setIsMonitoredInstance(Boolean.FALSE);
		process.setDescription("File Downloaded");

		// Update process
		ProcessRequest request = new ProcessRequest(process, TestBaseUtil.createUserContext());
		InternalResponse response = getProcessDAC().updateProcess(request);
		TestBaseUtil.assertResponse(response);

		// Update process
		process.setAction(null);
		process.setProcessType(new ProcessType(1, "Create Group"));
		response = getProcessDAC().updateProcess(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test insert process items.
	 */
	@Test
	public void testInsertProcessItems()
	{
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		processRequest.addProcessAsFirstElement(getProcessDefault());

		insertProcessItems(processRequest);
	}

	/**
	 * Test update process items.
	 */
	@Test
	public void testUpdateProcessItems()
	{
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		processRequest.addProcessAsFirstElement(getProcessDefault());

		insertProcessItems(processRequest);

		processRequest.getFirstProcess().getFirstProcessItem()
				.setProcessItemStatusEnum(ProcessItemStatusEnum.COMPLETED);

		InternalResponse response = getProcessDAC().updateProcessItems(processRequest);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fecth check processing.
	 */
	@Test
	public void testFecthCheckProcessing()
	{
		// Test error
		ProcessRequest request = TestBaseUtil.createProcessRequest();
		request.addProcessAsFirstElement(getProcessDefault());
		insertProcessItems(request);

		InternalResponse internalResponse = getProcessDAC().fetchCheckProcessing(request);
		assertEquals("Status should be Exception Error", Status.ExceptionError, internalResponse.getStatus());

		// Test success
		request.addProcessAsFirstElement(new DMProcess(new ProcessItem()));
		internalResponse = getProcessDAC().fetchCheckProcessing(request);
		TestBaseUtil.assertResponse(internalResponse);
	}

	/**
	 * Test fetch count monitored processes.
	 */
	@Test
	public void testFetchCountMonitoredProcesses()
	{
		ProcessRequest processRequest =
				new ProcessRequest(getProcessDefault(), TestBaseUtil.createUserContextWithLocale());

		processRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		InternalResultsResponse<HashMap<String, Integer>> response =
				getProcessDAC().fetchCountMonitoredProcesses(processRequest);
		TestBaseUtil.assertResultResponse(response);

		processRequest =
				new ProcessRequest(insertProcess(TestBaseUtil.createProcess(), ServiceTypeEnum.WATER),
						TestBaseUtil.createUserContextWithLocale());
		processRequest.setServiceTypeEnum(ServiceTypeEnum.WATER);
		response = getProcessDAC().fetchCountMonitoredProcesses(processRequest);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch today processes.
	 * 
	 */
	@Test
	public void testFetchTodayProcesses()
	{
		InquiryProcessRequest request = TestBaseUtil.createInquiryProcessRequest(DeviceColumnEnum.STATUS);
		request.addProcess(getProcessDefault());

		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		InternalResultsResponse<DMProcess> response = getProcessDAC().fetchTodayProcesses(request);
		TestBaseUtil.assertResultResponse(response);

		request.setServiceTypeEnum(ServiceTypeEnum.WATER);
		response = getProcessDAC().fetchTodayProcesses(request);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch scheduled processes.
	 * 
	 */
	@Test
	public void testFetchScheduledProcesses()
	{
		InquiryProcessRequest request = TestBaseUtil.createInquiryProcessRequest();
		request.setProcessSearch(new ProcessSearch());
		request.getProcessSearch().setStartDate(getProcessDefault().getStartTime());
		request.getProcessSearch().setEndDate(new Date());

		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		InternalResultsResponse<DMProcess> response = getProcessDAC().fetchScheduledProcesses(request);
		TestBaseUtil.assertResponse(response);

		request.setServiceTypeEnum(ServiceTypeEnum.WATER);
		response = getProcessDAC().fetchScheduledProcesses(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch started processes.
	 * 
	 */
	@Test
	public void testFetchStartedProcesses()
	{
		InquiryProcessRequest inquiryProcessRequest = TestBaseUtil.createInquiryProcessRequest(DeviceColumnEnum.STATUS);
		inquiryProcessRequest.setProcessSearch(new ProcessSearch());

		DemandResponseEventAction demandResponse = new DemandResponseEventAction(ONE, true, true, null);
		Calendar c = new GregorianCalendar();
		c.setTime(new Date());
		c.add(Calendar.HOUR, 1);
		demandResponse.setActionTime(c.getTime());

		demandResponse.setEnrollmentCode(0);
		demandResponse.setDemandResponseDuration(FIVE);
		demandResponse.setRandomizeStart(Boolean.TRUE);
		demandResponse.setRandomizeEnd(Boolean.FALSE);
		demandResponse.setCooling(FIVE);
		demandResponse.setCriticalityLevel(ONE);
		demandResponse.addDeviceClass(ALL);

		// Test
		ProcessRequest request = TestBaseUtil.createProcessRequest();
		request.addProcessAsFirstElement(insertProcess(TestBaseUtil.createProcess(ProcessStatusEnum.IN_PROCESS,
				insertAction(demandResponse)), ServiceTypeEnum.ELECTRIC));
		request.getFirstProcess()
				.addProperty(
						new Property(PropertyEnum.ACTION_DATE.getValue(), String.valueOf(
								TestBaseUtil.getDate(FIVE).getTime()).substring(ZERO, TEN)));
		insertProcessProperty(request);

		inquiryProcessRequest.getProcessSearch().setStartDate(request.getFirstProcess().getStartTime());
		inquiryProcessRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		InternalResultsResponse<DMProcess> response = getProcessDAC().fetchStartedProcesses(inquiryProcessRequest);
		TestBaseUtil.assertResultResponse(response);

	}

	/**
	 * Test fetch processes by device.
	 * 
	 */
	@Test
	public void testFetchProcessesByDevice()
	{
		ProcessRequest processRequest = new ProcessRequest(getProcessDefault(), TestBaseUtil.createUserContext());

		insertProcessItems(processRequest);

		InternalResponse response =
				getProcessDAC().fetchProcessItemsByDevice(processRequest);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test summarize process.
	 */
	@Test
	public void testSummarizeProcess()
	{
		ProcessRequest request = new ProcessRequest(getProcessDefault(), TestBaseUtil.createUserContext());

		// Insert process items
		insertProcessItems(request);

		InternalResponse response = getProcessDAC().summarizeProcess(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch process items by device.
	 */
	@Test
	public void testFetchProcessItemsByDevice()
	{
		ProcessRequest request =
				new ProcessRequest(insertProcess(TestBaseUtil.createProcess(ProcessStatusEnum.COMPLETED,
						TestBaseUtil.createAction(ActionTypeEnum.CONNECT_HAN_DEVICE))),
						TestBaseUtil.createUserContext());

		// Insert process items
		insertProcessItems(request);

		InternalResultsResponse<ProcessItem> response = getProcessDAC().fetchProcessItemsByDevice(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch process items by schedule.
	 */
	@Test
	public void testFetchProcessItemsBySchedule()
	{
		ProcessRequest request = new ProcessRequest(getProcessDefault(), TestBaseUtil.createUserContext());

		InternalResultsResponse<ProcessItem> internalResponse =
				getProcessDAC().fetchProcessItemsBySchedule(request);
		TestBaseUtil.assertResponse(internalResponse);
	}

	/**
	 * Test fetch processes in processing.
	 */
	@Test
	public void testFetchProcessesInProcessing()
	{
		// Test Success
		InquiryProcessRequest request = TestBaseUtil.createInquiryProcessRequest(DeviceColumnEnum.STATUS);
		request.setProcessSearch(new ProcessSearch());
		request.getProcessSearch().setStartDate(TestBaseUtil.getDate(NEGATIVE_TWO));
		request.getProcessSearch().setProcessStatusEnums(new ArrayList<ProcessStatusEnum>());
		request.getProcessSearch().getProcessStatusEnums().add(ProcessStatusEnum.IN_PROCESS);

		// Electric Service
		insertProcess(TestBaseUtil.createProcess(ProcessStatusEnum.IN_PROCESS,
				TestBaseUtil.createAction(ActionTypeEnum.REMOTE_DISCONNECT)), ServiceTypeEnum.ELECTRIC);

		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		InternalResultsResponse<DMProcess> response = getProcessDAC().fetchProcessesInProcessing(request);
		TestBaseUtil.assertResultResponse(response);

		// Water Service
		insertProcess(TestBaseUtil.createProcess(ProcessStatusEnum.IN_PROCESS,
				TestBaseUtil.createAction(ActionTypeEnum.DEMAND_READ)), ServiceTypeEnum.WATER);

		request.setServiceTypeEnum(ServiceTypeEnum.WATER);
		response = getProcessDAC().fetchProcessesInProcessing(request);
		TestBaseUtil.assertResultResponse(response);

		// Gas Service
		insertProcess(TestBaseUtil.createProcess(ProcessStatusEnum.IN_PROCESS,
				TestBaseUtil.createAction(ActionTypeEnum.DEMAND_READ)), ServiceTypeEnum.GAS);

		request.setServiceTypeEnum(ServiceTypeEnum.GAS);
		response = getProcessDAC().fetchProcessesInProcessing(request);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch process property.
	 */
	@Test
	public void testFetchProcessProperty()
	{
		ProcessRequest request = new ProcessRequest(getProcessDefault(), TestBaseUtil.createUserContext());
		request.getFirstProcess().addProperty(new Property(PropertyEnum.GROUP_NAME.getValue(), FIRST_BILLING_CYCLE));

		insertProcessProperty(request);

		InternalResultsResponse<Property> response = getProcessDAC().fetchProcessProperty(request);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch process item processing.
	 */
	@Test
	public void testFetchProcessItemProcessing()
	{
		ProcessRequest request = new ProcessRequest(getProcessDefault(), TestBaseUtil.createUserContext());

		insertProcessItems(request);

		InternalResultsResponse<ProcessItem> response = getProcessDAC().fetchProcessItemsByProcessId(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test insert dr event reports.
	 */
	@Test
	public void testInsertDrEventReports()
	{
		// Insert process
		InternalResultsResponse<DMProcess> responseProcess =
				insertProcess(TestBaseUtil.createProcessRequest(ProcessStatusEnum.IN_PROCESS, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, new ProcessType(ONE, CREATE_GROUP)));

		// Prepare process items
		ProcessRequest request = new ProcessRequest(responseProcess.getResultsList(), TestBaseUtil.createUserContext());
		addProcessItems(request, ProcessItemStatusEnum.RUNNING, FLEXNET_ID_1001, DeviceTypeEnum.ELECTRIC_METER);
		request.getFirstProcess().getFirstProcessItem()
				.addProcessItemHistory(new ProcessItemHistory(ProcessItemHistoryStatusEnum.EVENTSTARTED, new Date()));

		// Insert process items
		insertProcessItems(request);
	}

	/**
	 * Test fetch by start end date.
	 * 
	 * @throws ParseException the parse exception
	 */
	public void testFetchByStartEndDate() throws ParseException
	{
		LOG.debug("### testFetchByStartEndDate");

		Calendar cInit = Calendar.getInstance();
		// cInit.add(Calendar.DAY_OF_MONTH, -1);
		cInit.set(Calendar.HOUR_OF_DAY, ZERO);
		cInit.set(Calendar.MINUTE, ZERO);

		Calendar cEnd = Calendar.getInstance();
		// cEnd.add(Calendar.DAY_OF_MONTH, -1);
		// cEnd.add(Calendar.HOUR_OF_DAY, 1);
		cEnd.set(Calendar.HOUR_OF_DAY, TWENTY_THREE);
		cEnd.set(Calendar.MINUTE, FIFTY_NINE);

		// fetch by process id
		InquiryProcessRequest processRequest = new InquiryProcessRequest();
		processRequest.setStartRow(ZERO);
		processRequest.setPageSize(TEN);
		processRequest.addSortExpressions(new SortExpression("process_id", Direction.Ascending));
		processRequest.setProcessSearch(new ProcessSearch());

		processRequest.getProcessSearch().setStartDate(cInit.getTime());
		processRequest.getProcessSearch().setEndDate(cEnd.getTime());

		LOG.debug("### startDate " + processRequest.getProcessSearch().getStartDate());
		LOG.debug("### endDate " + processRequest.getProcessSearch().getEndDate());

		InternalResultsResponse<DMProcess> response = getProcessDAC().fetchProcesses(processRequest);

		assertNotNull(response.getResultsList());

		LOG.debug("### testFetchByStartEndDate - Fetched Processes: " + response.getResultsList().size());
		LOG.debug("### testFetchByStartEndDate - Pag Tot Rows: " + response.getResultsSetInfo().getTotalRowsAvailable());

	}

	/**
	 * Test fetch process by parent process.
	 */
	@Test
	public void testFetchProcessByRniEventId()
	{
		// create action
		ActionRequest actionRequest =
				createActionRequest(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT));
		insertAction(actionRequest);

		ProcessRequest processRequest = TestBaseUtil.createProcessRequest(ProcessStatusEnum.IN_PROCESS, Boolean.TRUE,
				Boolean.TRUE, Boolean.TRUE, null);

		processRequest.getFirstProcess().setAction(actionRequest.getAction());
		processRequest.getFirstProcess().setRniEventId(actionRequest.getAction().getId());

		insertProcess(processRequest);

		processRequest.getFirstProcess().setId(actionRequest.getAction().getId());

		InternalResultsResponse<DMProcess> response =
				getProcessDAC().fetchProcessByRniEventId(processRequest);

		assertEquals(STATUS_SHOULD_BE_OPERATION_SUCCESS, Status.OperationSuccess, response.getStatus());

		LOG.info(response.getResultsList().size());

		for (DMProcess p : response.getResultsList())
		{
			LOG.info(CONST_PROCESS_VIEW_ID + p.getId());
		}
	}

	/**
	 * Test fetch process items by process id.
	 */
	@Test
	public void testFetchProcessItemsByProcessId()
	{
		// Insert Process
		InternalResultsResponse<DMProcess> response =
				insertProcess(TestBaseUtil.createProcessRequest(ProcessStatusEnum.COMPLETED, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, new ProcessType(1, CREATE_GROUP)));

		// Prepare process items
		ProcessRequest request = new ProcessRequest(response.getResultsList(), TestBaseUtil.createUserContext());
		addProcessItems(request, ProcessItemStatusEnum.COMPLETED, FLEXNET_ID_1001, DeviceTypeEnum.ELECTRIC_METER);

		// Insert process items
		insertProcessItems(request);

		InternalResultsResponse<ProcessItem> responseItems = getProcessDAC().fetchProcessItemsByProcessId(request);
		assertProcessItemsResponse(responseItems, 0);

		// Success Situation - fetch processes item demand response.
		DMProcess process = new DMProcess();
		process.setRniEventId(ONE);
		request = new ProcessRequest(process);
		request.getFirstProcess().addProcessItem(
				new ProcessItem(new Device(HAN_DEVICE_ID), ProcessItemStatusEnum.RUNNING));

		responseItems = getProcessDAC().fetchProcessItemsByProcessId(request);

		assertEquals(STATUS_SHOULD_BE_OPERATION_SUCCESS, Status.OperationSuccess, responseItems.getStatus());

		LOG.info(response.getResultsList().size());

		for (ProcessItem p : responseItems.getResultsList())
		{
			LOG.info("ProcessItem: " + p.getId() + " - " + p.getDevice().getRadio().getFlexNetId());
		}
	}

	/**
	 * Test fetch all process items.
	 */
	@Test
	public void testFetchAllProcessItems()
	{
		// Success - Fetch by Process Id
		ProcessRequest processRequest =
				new ProcessRequest(new DMProcess(ONE));
		InternalResultsResponse<ProcessItem> responseItems =
				getProcessDAC().fetchAllProcessItems(processRequest);
		TestBaseUtil.assertResponse(responseItems);

		// Success - Fetch by Process Item Id
		processRequest = new ProcessRequest(new DMProcess(new ProcessItem(ONE)));
		responseItems = getProcessDAC().fetchAllProcessItems(processRequest);
		TestBaseUtil.assertResponse(responseItems);

		// Success - Fetch by Flexnet Id
		processRequest = new ProcessRequest(new DMProcess(new ProcessItem(new Device(new Radio(GAS_FLEXNET_ID)))));
		responseItems = getProcessDAC().fetchAllProcessItems(processRequest);
		TestBaseUtil.assertResponse(responseItems);

		// Success - Fetch by Status and Process Item Id
		processRequest = new ProcessRequest(new DMProcess(new ProcessItem(ONE, ProcessItemStatusEnum.RUNNING)));
		responseItems = getProcessDAC().fetchAllProcessItems(processRequest);
		TestBaseUtil.assertResponse(responseItems);

		// Success - Fetch by Modify User
		processRequest.getFirstProcess().getFirstProcessItem().setModifyUser(USER_ROD);
		responseItems = getProcessDAC().fetchAllProcessItems(processRequest);
		TestBaseUtil.assertResponse(responseItems);
	}

	// private

	/**
	 * Assert process items response.
	 * 
	 * @param response the response
	 * @param countMessageErro the count message erro
	 */
	private void assertProcessItemsResponse(InternalResultsResponse<ProcessItem> response, int countMessageErro)
	{
		assertNotNull(response);
		assertNotNull(response.getResultsList());
		assertEquals(STATUS_SHOULD_BE_OPERATION_SUCCESS, Status.OperationSuccess, response.getStatus());
		assertEquals(countMessageErro, response.getMessageInfoList().size());
	}

	/**
	 * Insert process.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	private InternalResultsResponse<DMProcess> insertProcess(ProcessRequest request)
	{
		InternalResultsResponse<DMProcess> response = getProcessDAC().insertProcess(request);
		assertNotNull(response);
		assertFalse(response.isInError());
		assertEquals(0, response.getMessageInfoList().size());

		return response;
	}

	/**
	 * Creates the action request.
	 * 
	 * @param actionType the action type
	 * @return the action request
	 */
	private ActionRequest createActionRequest(ActionType actionType)
	{
		return createActionRequest(null, actionType);
	}

	/**
	 * Creates the action request.
	 * 
	 * @param actionId the action id
	 * @param actionType the action type
	 * @return the action request
	 */
	private ActionRequest createActionRequest(Integer actionId, ActionType actionType)
	{
		DMAction ea = new DMAction(actionId, actionType);
		return new ActionRequest(ea, TestBaseUtil.createUserContextWithLocale());
	}

	/**
	 * Insert action.
	 * 
	 * @param actionRequest the action request
	 */
	private void insertAction(ActionRequest actionRequest)
	{
		getActionDAC().insertAction(actionRequest);
	}

	/**
	 * Creates the process items.
	 * 
	 * @param request the request
	 * @param processItemStatusEnum the process item status enum
	 * @param flexNetId the flex net id
	 * @param deviceTypeEnumParam the device type enum param
	 * @return the process request
	 */
	private void addProcessItems(ProcessRequest request, ProcessItemStatusEnum processItemStatusEnum,
			BigInteger flexNetId, DeviceTypeEnum deviceTypeEnumParam)
	{
		request.getFirstProcess().addProcessItem(
				new ProcessItem(new Device(flexNetId, deviceTypeEnumParam), processItemStatusEnum, null));
	}
}
