package com.sensus.dm.common.process.dac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.util.AbstractTestBaseDAC;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.action.model.request.ActionRequest;

/**
 * The Class ProcessDACImplTest.
 * 
 * @author QAT Global
 */
@Transactional
public class ProcessSummaryDACImplTest extends AbstractTestBaseDAC
{

	/** The Constant STATUS_SHOULD_BE_OPERATION_SUCCESS. */
	private static final String STATUS_SHOULD_BE_OPERATION_SUCCESS = "Status should be OperationSuccess";

	/** The Constant CONST_TEST_FETCH_BY_ID_FETCHED_PROCESSES. */
	private static final String CONST_TEST_FETCH_BY_ID_FETCHED_PROCESSES = "### testFetchById - Fetched Processes: ";

	/** The Constant CONST_PROCESS_VIEW_ID. */
	private static final String CONST_PROCESS_VIEW_ID = "ProcessView id: ";

	/** The Constant PROCESS_ID. */
	private static final int PROCESS_ID = 405717830;

	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(ProcessSummaryDACImplTest.class);

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
		setCacheStatementScope(getProcessSummaryDAC());
		insertProcess(TestBaseUtil.createProcess(), ServiceTypeEnum.WATER);
		setProcessDefault(insertProcess(TestBaseUtil.createProcess()));
	}

	/**
	 * Fetch demand response program participation.
	 */
	@Test
	public void fetchDemandResponseProgramParticipation()
	{
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();

		// Insert process
		processRequest.addProcessAsFirstElement(insertProcess(TestBaseUtil.createProcess(ProcessStatusEnum.STARTED,
				TestBaseUtil.createAction(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT))));

		insertProcessItems(processRequest, DeviceTypeEnum.HAN_DEVICE);

		// fetch Demand Response Setup
		InternalResultsResponse<DMProcess> response =
				getProcessSummaryDAC().fetchDemandResponseProgramParticipation(processRequest);
		TestBaseUtil.assertResultResponse(response);

		processRequest = TestBaseUtil.createProcessRequest();

		// Insert process
		processRequest.addProcessAsFirstElement(insertProcess(TestBaseUtil.createProcess(ProcessStatusEnum.STARTED,
				TestBaseUtil.createAction(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT))));

		insertProcessItems(processRequest, DeviceTypeEnum.LCM);

		// fetch Demand Response Setup
		response =
				getProcessSummaryDAC().fetchDemandResponseProgramParticipation(processRequest);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Fetch demand response setup.
	 */
	@Test
	public void fetchDemandResponseSetup()
	{
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();

		// Insert process
		processRequest.addProcessAsFirstElement(insertProcess(TestBaseUtil.createProcess(ProcessStatusEnum.COMPLETED,
				TestBaseUtil.createAction(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_SETUP))));

		insertProcessItems(processRequest);

		// fetch Demand Response Setup
		InternalResultsResponse<DMProcess> response =
				getProcessSummaryDAC().fetchAllDemandResponseSetup(processRequest);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch process items by connect disconnect.
	 */
	@Test
	public void testFetchProcessItemsByConnectDisconnect()
	{
		ProcessRequest request = TestBaseUtil.createProcessRequest();
		request.addProcessAsFirstElement(insertProcess(TestBaseUtil.createProcess(ProcessStatusEnum.COMPLETED,
				TestBaseUtil.createAction(ActionTypeEnum.REMOTE_DISCONNECT))));

		// Insert process items
		insertProcessItems(request);

		InternalResultsResponse<ProcessItem> response =
				getProcessSummaryDAC().fetchProcessItemsByConnectDisconnect(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch processes demand response status.
	 */
	@Test
	public void testFetchProcessesDemandResponseStatus()
	{
		// Test Success
		ProcessRequest request = new ProcessRequest(new DMProcess(PROCESS_ID));
		request.getFirstProcess().addProcessItem(new ProcessItem(new Device(HAN_DEVICE_ID)));
		request.getFirstProcess().setAction(
				new DMAction(new ActionType(ActionTypeEnum.GET_DEMAND_RESPONSE_EVENT_STATUS)));

		InternalResultsResponse<DMProcess> response =
				getProcessSummaryDAC().fetchProcessesDemandResponseStatus(request);

		assertEquals(STATUS_SHOULD_BE_OPERATION_SUCCESS, Status.OperationSuccess, response.getStatus());

		LOG.info(response.getResultsList().size());

		for (DMProcess p : response.getResultsList())
		{
			LOG.info(CONST_PROCESS_VIEW_ID + p.getId());
		}
	}

	/**
	 * Fetch demand response summary.
	 */
	@Test
	public void fetchDemandResponseSummary()
	{
		// create action
		ActionRequest actionRequest =
				createActionRequest(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT));
		insertAction(actionRequest);

		ProcessRequest processRequest = createProcessRequest(ProcessStatusEnum.COMPLETED, Boolean.TRUE,
				Boolean.TRUE, Boolean.TRUE, null);

		processRequest.getFirstProcess().setAction(actionRequest.getAction());

		// Test Success
		InternalResultsResponse<DMProcess> internalResponse =
				insertProcess(processRequest);

		// fetch by process id
		processRequest = new ProcessRequest(internalResponse.getFirstResult());

		InternalResultsResponse<DMProcess> response =
				getProcessSummaryDAC().fetchDemandResponseSummary(processRequest);

		assertNotNull(response.getResultsList());

		LOG.debug(CONST_TEST_FETCH_BY_ID_FETCHED_PROCESSES + response.getResultsList().size());
		for (DMProcess p : response.getResultsList())
		{
			LOG.debug(p.getId());
		}
	}

	/**
	 * Fetch process send han text message.
	 */
	@Test
	public void fetchProcessSendHanTextMessage()
	{
		// create action
		ActionRequest actionRequest = createActionRequest(new ActionType(ActionTypeEnum.SEND_HAN_TEXT_MESSAGE));
		insertAction(actionRequest);

		ProcessRequest processRequest = createProcessRequest(ProcessStatusEnum.COMPLETED, Boolean.TRUE,
				Boolean.TRUE, Boolean.TRUE, null);

		processRequest.getFirstProcess().setAction(actionRequest.getAction());
		processRequest.setUserContext(TestBaseUtil.createUserContextWithLocale());

		// Insert process
		InternalResultsResponse<DMProcess> internalResponse = insertProcess(processRequest);
		processRequest = new ProcessRequest(internalResponse.getFirstResult());
		processRequest.setUserContext(TestBaseUtil.createUserContextWithLocale());

		// fetch Demand Response Setup
		InternalResultsResponse<DMProcess> response =
				getProcessSummaryDAC().fetchProcessSendHanTextMessage(processRequest);

		assertNotNull(response.getResultsList());

		LOG.debug(CONST_TEST_FETCH_BY_ID_FETCHED_PROCESSES + response.getResultsList().size());
		for (DMProcess p : response.getResultsList())
		{
			LOG.debug(p.getId());
		}

	}

	/**
	 * Fetch last tamper detect timeout.
	 */
	@Test
	public void fetchLastTamperDetectTimeout()
	{
		ProcessRequest processRequest =
				new ProcessRequest(new DMProcess(new ProcessItem(new Device(new Radio(FLEXNET_ID_1001)))));
		InternalResultsResponse<DMProcess> response =
				getProcessSummaryDAC().fetchLastTamperDetectTimeout(processRequest);
		TestBaseUtil.assertResponse(response);
	}

	// private

	/**
	 * Creates the process request.
	 * 
	 * @param processStatusEnum the process status enum
	 * @param isDashboardMonitored the is dashboard monitored
	 * @param isProcessComplete the is process complete
	 * @param isMonitoredInstance the is monitored instance
	 * @param processType the process type
	 * @return the process request
	 */
	private ProcessRequest createProcessRequest(ProcessStatusEnum processStatusEnum,
			Boolean isDashboardMonitored, Boolean isProcessComplete, Boolean isMonitoredInstance,
			ProcessType processType)
	{
		return createProcessRequest(processStatusEnum,
				isDashboardMonitored, isProcessComplete, isMonitoredInstance,
				processType, ServiceTypeEnum.ELECTRIC);
	}

	/**
	 * Creates the process request.
	 * 
	 * @param processStatusEnum the process status enum
	 * @param isDashboardMonitored the is dashboard monitored
	 * @param isProcessComplete the is process complete
	 * @param isMonitoredInstance the is monitored instance
	 * @param processType the process type
	 * @param serviceTypeEnum the service type enum
	 * @return the process request
	 */
	private ProcessRequest createProcessRequest(ProcessStatusEnum processStatusEnum,
			Boolean isDashboardMonitored, Boolean isProcessComplete, Boolean isMonitoredInstance,
			ProcessType processType, ServiceTypeEnum serviceTypeEnum)
	{
		ProcessRequest request = TestBaseUtil.createProcessRequest();

		DMProcess process = new DMProcess();
		process.setStartTime(new Date());
		process.setEndTime(new Date());

		process.setProcessStatusEnum(processStatusEnum);
		process.setIsDashboardMonitored(isDashboardMonitored);
		process.setIsProcessComplete(isProcessComplete);
		process.setIsMonitoredInstance(isMonitoredInstance);
		process.setProcessType(processType);
		request.addProcessAsFirstElement(process);
		request.setServiceTypeEnum(serviceTypeEnum);

		request.setTenant(TestBaseUtil.createTenant());

		return request;
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

}
