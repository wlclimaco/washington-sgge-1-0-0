package com.sensus.dm.common.process.bcl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.EditGroupAction;
import com.sensus.dm.common.process.dac.IProcessDAC;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessSearch;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.action.model.DemandResponseEventAction;

/**
 * The Class ProcessBCLImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/dm/common/process/processbclimpltest.xml"})
public class ProcessBCLImplTest extends AbstractTestBaseBusiness
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant SEVENTY. */
	private static final int SEVENTY = 70;

	/** The Constant FIFTY. */
	private static final int FIFTY = 50;

	/** The Constant SIXTY. */
	private static final int SIXTY = 60;

	/** The Constant THIRTY. */
	private static final int THIRTY = 30;

	/** The Constant INSERT_PROCESS. */
	private static final String INSERT_PROCESS = "insertProcess";

	/** The Constant FETCH_PROCESS_BY_ID. */
	private static final String FETCH_PROCESS_BY_ID = "fetchProcessById";

	/** The Constant START_TIME. */
	private static final String START_TIME = "start_time";

	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The process bcl. */
	private IProcessBCL processBCL;

	/**
	 * Sets the process bcl.
	 * 
	 * @param processBCL the new process bcl
	 */
	@Resource(name = "processBCLTarget")
	public void setProcessBCL(IProcessBCL processBCL)
	{
		this.processBCL = processBCL;
	}

	/**
	 * Gets the process bcl.
	 * 
	 * @return the process bcl
	 */
	public IProcessBCL getProcessBCL()
	{
		return processBCL;
	}

	/**
	 * Sets the process area.
	 */
	public void setProcessArea()
	{
		setArea(getProcessBCL(), EPMAreaEnum.PROCESS);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setProcessArea();
	}

	/**
	 * Removes the process area.
	 */
	@After
	public void resetMocksToProcessArea()
	{
		resetMocksData(getProcessBCL());
		setProcessArea();
	}

	/**
	 * Test fetch processes.
	 */
	@Test
	public void testFetchProcesses()
	{
		// Success situation - Fetch Monitored Process Success
		InquiryProcessRequest inquiryProcessRequest = TestBaseUtil.createInquiryProcessRequest();

		inquiryProcessRequest.setSelectionPaginationIds(new ArrayList<BigInteger>());
		inquiryProcessRequest.getSelectionPaginationIds().add(new BigInteger(STRING_ONE));
		inquiryProcessRequest.getSelectionPaginationIds().add(new BigInteger(STRING_TWO));
		inquiryProcessRequest.setPaginationAllSelected(true);

		InternalResultsResponse<DMProcess> response = getProcessBCL().fetchProcesses(inquiryProcessRequest);

		TestBaseUtil.assertResultResponse(response);

		// Success situation - Fetch processes generate description
		inquiryProcessRequest = TestBaseUtil.createInquiryProcessRequest();
		inquiryProcessRequest.setProcessId(NINETY_NINE);

		response = getProcessBCL().fetchProcesses(inquiryProcessRequest);

		TestBaseUtil.assertResultResponse(response);

	}

	/**
	 * Test fetch process failed.
	 */
	@Test
	public void testFetchProcessFailed()
	{
		// Success situation

		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		processRequest.setProcessId(ONE);
		processRequest.setProcessItemStatusEnum(ProcessItemStatusEnum.FAILED);

		InternalResultsResponse<DMProcess> response = getProcessBCL().fetchProcessById(processRequest);

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch monitored process.
	 */
	@Test
	public void testFetchMonitoredProcess()
	{
		InquiryProcessRequest inquiryProcessRequest = TestBaseUtil.createInquiryProcessRequest();
		inquiryProcessRequest.addProcess(new DMProcess());
		inquiryProcessRequest.getFirstProcess().setIsDashboardMonitored(true);

		// Success situation - Monitored Dashboard
		InternalResultsResponse<DMProcess> response = getProcessBCL().fetchMonitoredProcess(inquiryProcessRequest);

		TestBaseUtil.assertResultResponse(response);

		// Success situation - Recent Request
		inquiryProcessRequest.getFirstProcess().setIsMonitoredInstance(true);

		response = getProcessBCL().fetchMonitoredProcess(inquiryProcessRequest);

		TestBaseUtil.assertResultResponse(response);

		// Success situation - None Monitored
		inquiryProcessRequest = TestBaseUtil.createInquiryProcessRequest();
		inquiryProcessRequest.addProcess(new DMProcess());
		inquiryProcessRequest.getFirstProcess().setIsDashboardMonitored(false);
		inquiryProcessRequest.getFirstProcess().setIsMonitoredInstance(false);

		response = getProcessBCL().fetchMonitoredProcess(inquiryProcessRequest);

		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test insert process.
	 */
	@Test
	public void testInsertProcess()
	{
		// Success situation - insert with process type
		ProcessRequest request = new ProcessRequest(new DMProcess());

		DMProcess parentProcess = new DMProcess();
		parentProcess.setProcessType(new ProcessType(EditGroupAction.ACTION));

		request.addProcessAsFirstElement(parentProcess);

		InternalResultsResponse<DMProcess> response =
				getProcessBCL().insertProcess(request);

		TestBaseUtil.assertResultResponse(response);

		// Error situation - processType

		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessTypeBCL.class, "fetchProcessTypeByDescription");
		response = getProcessBCL().insertProcess(request);
		this.assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Success situation - insert
		request = new ProcessRequest();
		List<ProcessItem> processItems = new ArrayList<ProcessItem>();
		DMProcess process = new DMProcess();

		for (int i = ZERO; i <= TEN; i++)
		{
			processItems.add(new ProcessItem(new Device(new Radio(new BigInteger(String.valueOf(i)))),
					ProcessItemStatusEnum.COMPLETED));
		}

		parentProcess = new DMProcess(FIVE);
		parentProcess.setId(ONE);
		process.setParentProcess(parentProcess);

		// Success situation - create ActionType -> Demand Response
		ActionType actionType = new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT);

		DemandResponseEventAction dr = new DemandResponseEventAction();
		dr.setId(new Integer(ONE));
		dr.setCriticalityLevel(FIVE);
		dr.setDemandResponseDuration(THIRTY);
		dr.setDutyCycleRate(SIXTY);
		dr.setEnrollmentCode(2);
		dr.setHeating(FIFTY);
		dr.setLoadAdjustment(SEVENTY);
		dr.setRandomizeEnd(false);
		dr.setRandomizeStart(true);

		dr.setActionType(actionType);

		process.setAction(dr);

		process.setProcessItems(processItems);
		request.addProcessAsFirstElement(process);
		response = getProcessBCL().insertProcess(request);

		TestBaseUtil.assertResultResponse(response);

		// Error situation - Insert Process Items

		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "insertProcessItems");
		response = getProcessBCL().insertProcess(request);
		this.assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Error situation - Insert Process

		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, INSERT_PROCESS);
		response = getProcessBCL().insertProcess(request);
		this.assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Error situation - Insert Process

		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, INSERT_PROCESS);
		response = getProcessBCL().insertProcess(request);
		this.assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();
	}

	/**
	 * Test update process items.
	 */
	@Test
	public void testUpdateProcessItems()
	{
		// Success situation

		DMProcess parentProcess = new DMProcess(ONE);

		DMProcess process = new DMProcess(FIVE);
		process.setParentProcess(parentProcess);

		List<ProcessItem> processItemList = TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED, null, TEN);
		process.setProcessItems(processItemList);

		ProcessRequest request = TestBaseUtil.createProcessRequest();
		request.addProcessAsFirstElement(process);

		InternalResultsResponse<DMProcess> response =
				getProcessBCL().insertProcess(request);

		TestBaseUtil.assertResultResponse(response);

		for (ProcessItem item : processItemList)
		{
			item.setProcessItemStatusEnum(ProcessItemStatusEnum.CANCELLED);
		}
		process.setId(response.getFirstResult().getId());
		process.setProcessItems(processItemList);
		getProcessBCL().updateProcessItems(request);

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test update process.
	 */
	@Test
	public void testUpdateProcess()
	{
		// Success situation

		Property property = new Property(PropertyEnum.ACTION_ID.toString(), STRING_ONE);
		List<Property> properties = new ArrayList<Property>();
		properties.add(property);

		DMProcess process = new DMProcess(ONE);
		process.setProperties(properties);

		InternalResponse response = getProcessBCL().updateProcess(new ProcessRequest(process));

		TestBaseUtil.assertResponse(response);

		// Error situation - Insert Process

		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class);
		response = getProcessBCL().updateProcess(new ProcessRequest(process));
		this.assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();
	}

	// removed while not connected with Orchestration
	//
	/**
	 * Test check link status.
	 */
	@Test
	public void testCheckLinkStatus()
	{
		// Success situation - should return true
		InternalResultsResponse<Boolean> response = getProcessBCL().checkLinkStatus(new ProcessRequest());

		TestBaseUtil.assertResultResponse(response);
		assertNotNull(response.getFirstResult());
		assertTrue(response.getFirstResult());
	}

	/**
	 * Test fetch count monitored processes.
	 */
	@Test
	public void testFetchCountMonitoredProcesses()
	{
		// Success situation
		InternalResultsResponse<HashMap<String, Integer>> response =
				getProcessBCL().fetchCountMonitoredProcesses(new ProcessRequest());

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch today processes.
	 */
	@Test
	public void testFetchTodayProcesses()
	{
		// Success situation

		InquiryProcessRequest inquiryProcessRequest = TestBaseUtil.createInquiryProcessRequest();

		inquiryProcessRequest.setProcessSearch(new ProcessSearch(new Date(), new Date()));

		inquiryProcessRequest.addSortExpressions(TestBaseUtil.createSortExpression(START_TIME));

		InternalResultsResponse<DMProcess> response =
				getProcessBCL().fetchTodayProcesses(inquiryProcessRequest);

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch process items by device.
	 */
	@Test
	public void testFetchProcessItemsByDevice()
	{
		// Success situation

		InternalResultsResponse<ProcessItem> response =
				getProcessBCL().fetchProcessItemsByDevice(
						new ProcessRequest(new DMProcess(new ProcessItem(new Device(DEVICE_ID))),
								TestBaseUtil.createUserContext()));

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch process items by schedule.
	 */
	@Test
	public void testFetchProcessItemsBySchedule()
	{
		// Success situation

		ProcessRequest request = new ProcessRequest(new DMProcess(ONE));

		InternalResultsResponse<DMProcess> response = getProcessBCL().fetchProcessItemsBySchedule(request);

		TestBaseUtil.assertResultResponse(response);

		// Error situation

		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class);
		response = getProcessBCL().fetchProcessItemsBySchedule(request);
		this.assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();
	}

	/**
	 * Test fetch process items by process id.
	 */
	@Test
	public void testFetchProcessItemsByProcessId()
	{
		// Test Success
		ProcessRequest request = TestBaseUtil.createProcessRequest();
		request.addProcessAsFirstElement(new DMProcess(ONE));

		InternalResponse response = getProcessBCL().fetchProcessItemsByProcessId(request);

		TestBaseUtil.assertResponse(response);

		// Error situation

		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class);
		response = getProcessBCL().fetchProcessItemsByProcessId(request);
		this.assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();
	}

	/**
	 * Test fetch started processes.
	 */
	@Test
	public void testFetchStartedProcesses()
	{
		// Success situation

		InquiryProcessRequest request = TestBaseUtil.createInquiryProcessRequest();

		InternalResultsResponse<DMProcess> response = getProcessBCL().fetchStartedProcesses(request);

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test summarize process.
	 */
	@Test
	public void testSummarizeProcess()
	{
		// Success situation
		ProcessRequest request = TestBaseUtil.createProcessRequest();

		InternalResponse response = getProcessBCL().summarizeProcess(request);

		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch processes with process item in processing.
	 */
	@Test
	public void testFetchProcessesWithProcessItemInProcessing()
	{
		// Success situation
		ProcessRequest request = TestBaseUtil.createProcessRequest();
		request.addProcessAsFirstElement(TestBaseUtil.createProcess());

		InternalResultsResponse<DMProcess> response =
				getProcessBCL().fetchProcessesWithProcessItemInProcessing(request);

		TestBaseUtil.assertResultResponse(response);

		// Error situation - fetch process by id

		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, FETCH_PROCESS_BY_ID);
		response = getProcessBCL().fetchProcessesWithProcessItemInProcessing(request);
		this.assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Error situation - fetch process property

		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "fetchProcessProperty");
		response = getProcessBCL().fetchProcessesWithProcessItemInProcessing(request);
		this.assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Error situation - fetch process item in processing

		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "fetchProcessItemsByProcessId");
		response = getProcessBCL().fetchProcessesWithProcessItemInProcessing(request);
		this.assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();
	}

	/**
	 * Test fetch check processing.
	 */
	@Test
	public void testFetchCheckProcessing()
	{
		// Success situation

		ProcessRequest request = TestBaseUtil.createProcessRequest();
		request.addProcessAsFirstElement(TestBaseUtil.createProcess());

		InternalResponse response = getProcessBCL().fetchCheckProcessing(request);

		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch communication summary.
	 */
	@Test
	public void testFetchCommunicationSummary()
	{
		// Success situation

		InternalResultsResponse<DMProcess> response =
				getProcessBCL().fetchCommunicationSummary(TestBaseUtil.createProcessRequest());

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch import han device summary.
	 */
	@Test
	public void testFetchImportHanDeviceSummary()
	{
		// Success situation

		InternalResultsResponse<DMProcess> response =
				getProcessBCL().fetchImportHanDeviceSummary(TestBaseUtil.createProcessRequest());

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch demand read ping summary.
	 */
	@Test
	public void testFetchDemandReadPingSummary()
	{
		// Success situation
		InternalResultsResponse<DMProcess> response =
				getProcessBCL().fetchDemandReadPingSummary(TestBaseUtil.createProcessRequest());

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch process by rni event id.
	 */
	@Test
	public void testFetchProcessByRNIEventId()
	{
		// Success situation

		InternalResultsResponse<DMProcess> response =
				getProcessBCL().fetchProcessByRniEventId(TestBaseUtil.createProcessRequest());

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch all process items.
	 */
	@Test
	public void testFetchAllProcessItems()
	{
		// Success situation
		ProcessRequest request = TestBaseUtil.createProcessRequest();
		InternalResultsResponse<ProcessItem> response = getProcessBCL().fetchAllProcessItems(request);

		TestBaseUtil.assertResultResponse(response);

		// Error situation
		setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class);
		response = getProcessBCL().fetchAllProcessItems(request);

		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test update process items to expire.
	 */
	@Test
	public void testUpdateProcessItemsToExpire()
	{
		ProcessRequest processRequest = TestBaseUtil.createProcessRequestWithTenant();

		processRequest.addProcessAsFirstElement(TestBaseUtil.createProcess());
		processRequest.getFirstProcess().addProcessItem(new ProcessItem());

		InternalResponse response = getProcessBCL().updateProcessItemsToExpire(processRequest);
		assertNotNull(response);

	}

	/**
	 * Test fetch relays.
	 */
	@Test
	public void testFetchRelays()
	{
		ProcessRequest processRequest = TestBaseUtil.createProcessRequestWithTenant();
		processRequest.setProcessId(NINETY_NINE);

		processRequest.addProcessAsFirstElement(TestBaseUtil.createProcess());
		processRequest.getFirstProcess().addProcessItem(new ProcessItem());

		InternalResponse response = getProcessBCL().fetchRelays(processRequest);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch relays by process id.
	 */
	@Test
	public void testFetchRelaysByProcessId()
	{
		ProcessRequest processRequest = TestBaseUtil.createProcessRequestWithTenant();
		processRequest.setProcessId(NINETY_NINE);

		processRequest.addProcessAsFirstElement(TestBaseUtil.createProcess());
		processRequest.getFirstProcess().addProcessItem(new ProcessItem());

		InternalResponse response = getProcessBCL().fetchRelaysByProcessId(processRequest);
		TestBaseUtil.assertResponse(response);

	}

	/**
	 * Test insert process property.
	 */
	@Test
	public void testInsertProcessProperty()
	{
		InternalResponse response = getProcessBCL().insertProcessProperty(new ProcessRequest());
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test update process status.
	 */
	@Test
	public void testUpdateProcessStatus()
	{
		InternalResponse response =
				getProcessBCL().updateProcessStatus(new ProcessRequest(new DMProcess(new ProcessItem(new Device()))));
		TestBaseUtil.assertResponse(response);
	}
}
