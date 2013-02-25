package com.sensus.mlc.process.bcl;

import static com.sensus.mlc.base.TestBaseUtil.assertResponse;
import static com.sensus.mlc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.mlc.base.TestBaseUtil.createTenant;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.process.dac.IProcessDAC;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.InquiryProcessResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class ProcessBCLTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/process/processbclimpltest.xml"})
public class ProcessBCLTest extends AbstractTestBaseBusiness
{

	/** The Constant FETCH_PROCESS_BY_ID. */
	private static final String FETCH_PROCESS_BY_ID = "fetchProcessById";

	/** The Constant INSERT_PROCESS. */
	private static final String INSERT_PROCESS = "insertProcess";

	/** The Constant UPDATE_PROCESS. */
	private static final String UPDATE_PROCESS = "updateProcess";
	/** The process bcl. */
	private IProcessBCL processBCL;

	/**
	 * Gets the process bcl.
	 *
	 * @return the process bcl
	 */
	public IProcessBCL getProcessBCL()
	{
		processBCL.setGatewayActive(createTenant(), true);
		return processBCL;
	}

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
	 * Sets the process area.
	 */
	public void setProcesssArea()
	{
		setArea(getProcessBCL(), LCAreaEnum.PROCESS);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setProcesssArea();
	}

	/**
	 * Reset the process area.
	 */
	@After
	public void resetMocksToProcessArea()
	{
		resetMocksData(getProcessBCL());
		setProcesssArea();
	}

	/**
	 * Removes the process area.
	 */
	@After
	public void removeProcessArea()
	{
		setArea(getProcessBCL(), LCAreaEnum.DEFAULT);
	}

	/**
	 * Test fetch process by file name.
	 */
	@Test
	public void testFetchProcessByFileName()
	{
		// Success situation
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		InternalResultsResponse<Process> response =
				getProcessBCL().fetchProcessByFileName(processRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToProcessArea();
		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "fetchProcessByFileName");
		response = getProcessBCL().fetchProcessByFileName(processRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch process by id.
	 */
	@Test
	public void testFetchProcessById()
	{
		// Success situation
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		processRequest.setProcess(TestBaseUtil.createProcess(TestBaseUtil.createLight(), null));
		InternalResultsResponse<Process> response =
				getProcessBCL().fetchProcessById(processRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToProcessArea();
		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, FETCH_PROCESS_BY_ID);
		response = getProcessBCL().fetchProcessById(processRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test check rni link status.
	 */
	@Test
	public void testCheckRNILinkStatus()
	{
		getProcessBCL().checkRniStatus();
	}

	/**
	 * Test set gateway active.
	 */
	@Test
	public void testSetGatewayActive()
	{
		getProcessBCL().setGatewayActive(TestBaseUtil.createTenant(), true);
		getProcessBCL().setGatewayActive(TestBaseUtil.createTenant(), false);
	}

	/**
	 * Test fetch rni link status.
	 */
	@Test
	public void testFetchRniLinkStatus()
	{
		// Success situation
		Tenant tenant = TestBaseUtil.createTenant();
		InternalResultsResponse<Boolean> response = getProcessBCL().fetchRniLinkStatus(tenant);
		assertResultResponse(response);

		// False situation
		tenant.setId(null);
		response = getProcessBCL().fetchRniLinkStatus(tenant);
		assertResultResponse(response);

	}

	/**
	 * Test fetch monitored processes.
	 */
	@Test
	public void testFetchMonitoredProcesses()
	{
		// Success situation
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		InternalResultsResponse<Process> response =
				getProcessBCL().fetchMonitoredProcesses(processRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToProcessArea();
		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "fetchMonitoredProcesses");
		response = getProcessBCL().fetchMonitoredProcesses(processRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch processes.
	 */
	@Test
	public void testFetchProcesses()
	{
		// Success situation
		InquiryProcessRequest processRequest = TestBaseUtil.createInquiryProcessRequest();
		InternalResultsResponse<Process> response = getProcessBCL().fetchProcesses(processRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToProcessArea();
		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "fetchProcesses");
		response = getProcessBCL().fetchProcesses(processRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test insert process.
	 */
	@Test
	public void testInsertProcess()
	{
		// Success situation
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		processRequest.setProcess(TestBaseUtil.createProcess(TestBaseUtil.createLight(), null));
		processRequest.getProcess().setIsMonitoredInstance(null);
		InternalResultsResponse<Process> response = getProcessBCL().insertProcess(processRequest);
		assertResultResponse(response);
		processRequest.getProcess().setIsMonitoredInstance(true);
		response = getProcessBCL().insertProcess(processRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToProcessArea();
		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, INSERT_PROCESS);
		processRequest = TestBaseUtil.createProcessRequest();
		response = getProcessBCL().insertProcess(processRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test unmonitor process.
	 */
	@Test
	public void testUnmonitorProcess()
	{
		// Success situation
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		InternalResponse response = getProcessBCL().unmonitorProcess(processRequest);
		assertResponse(response);

		// Error situation
		resetMocksToProcessArea();
		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "unmonitorProcess");
		response = getProcessBCL().unmonitorProcess(processRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test update process.
	 */
	@Test
	public void testUpdateProcess()
	{
		// Success situation
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		InternalResponse response = getProcessBCL().updateProcess(processRequest);
		assertResponse(response);

		// Error situation
		resetMocksToProcessArea();
		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, UPDATE_PROCESS);
		response = getProcessBCL().updateProcess(processRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch all tenant.
	 */
	@Test
	public void testFetchAllTenant()
	{
		// Success situation
		InternalResultsResponse<Tenant> response = getProcessBCL().fetchAllTenant();
		assertResponse(response);

		// Error situation
		resetMocksToProcessArea();
		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "fetchAllTenant");
		response = getProcessBCL().fetchAllTenant();
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch process by rni id.
	 */
	@Test
	public void testFetchProcessByRniId()
	{
		// Success situation
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		InternalResultsResponse<Process> response = getProcessBCL().fetchProcessByRniId(processRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToProcessArea();
		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "fetchProcessByRniId");
		response = getProcessBCL().fetchProcessByRniId(processRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test retry process.
	 */
	@Test
	public void testRetryProcess()
	{
		// Success situation
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		InternalResponse response = getProcessBCL().retryProcess(processRequest);
		assertResponse(response);

		// Error situation
		resetMocksToProcessArea();
		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, FETCH_PROCESS_BY_ID);
		response = getProcessBCL().retryProcess(processRequest);
		this.assertMessages(response, ERROR_CODE);

		processRequest.getUserContext().getTenant().setId(null);
		response = getProcessBCL().retryProcess(processRequest);
		assertEquals(InternalResponse.Status.ExceptionError, response.getStatus());
	}

	/**
	 * Test abort process.
	 */
	@Test
	public void testAbortProcess()
	{
		// Success situation
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		getProcessBCL().setGatewayActive(TestBaseUtil.createTenant(), true);
		InternalResponse response = getProcessBCL().abortProcess(processRequest);
		assertResponse(response);

		processRequest.setProcessStatusReasonEnum(ProcessStatusReasonEnum.ABORTED);
		response = getProcessBCL().abortProcess(processRequest);
		assertResponse(response);

	}

	/**
	 * Test submit process.
	 */
	@Test
	public void testSubmitProcess()
	{
		ProcessRequest processRequest = new ProcessRequest();

		// Error situation
		LCAction lCAction = new LCAction();
		lCAction.setActionType(LCActionTypeEnum.ABORT);

		InternalResultsResponse<Process> response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertEquals(InternalResponse.Status.ExceptionError, response.getStatus());

		// Error situation
		processRequest.setTenant(createTenant());
		response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertEquals(InternalResponse.Status.ExceptionError, response.getStatus());

		// Success situation
		lCAction.setActionType(LCActionTypeEnum.ADD_SMP_TO_SCHEDULE_EVENT);
		response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertResponse(response);

	}

	/**
	 * Test fetch process by light.
	 */
	@Test
	public void testFetchProcessByLight()
	{
		// Success situation
		LightRequest lightRequest = TestBaseUtil.createLightRequest();
		lightRequest.addLight(TestBaseUtil.createLight());
		InternalResultsResponse<Process> response = getProcessBCL().fetchProcessByLight(lightRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToProcessArea();
		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "fetchProcessByLight");
		lightRequest = TestBaseUtil.createLightRequest();
		lightRequest.addLight(TestBaseUtil.createLight());
		response = getProcessBCL().fetchProcessByLight(lightRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test update csv downloaded.
	 */
	@Test
	public void testUpdateCSVDownloaded()
	{
		ProcessRequest request = TestBaseUtil.createProcessRequest();
		InternalResponse response = getProcessBCL().updateCSVDownloaded(request);
		assertNotNull(response);
		assertFalse(response.isInError());
	}

	/**
	 * Test generate sumary file csv.
	 */
	@Test
	public void testGenerateSumaryFileCSV()
	{
		// Success situation
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		Process process = new Process();
		process.setId(1);
		processRequest.setProcess(process);
		ProcessResponse processResponse = getProcessBCL().generateSumaryFileCSV(processRequest);
		assertTrue(processResponse.isOperationSuccess());

		// Errors situations
		// SmartPointAccessorBCL
		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class,
				"generateSummaryFileCSV");
		processResponse = getProcessBCL().generateSumaryFileCSV(processRequest);
		assertFalse(processResponse.isOperationSuccess());
		this.assertMessages(processResponse, ERROR_CODE);
		resetMocksToProcessArea();
	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateFileCSV()
	{
		// Success situation
		InquiryProcessRequest inquiryProcessRequest = TestBaseUtil.createInquiryProcessRequest();
		inquiryProcessRequest.setProcessId(new Integer(1));
		inquiryProcessRequest.setFileName("C:\\Users\\QATEmployee\\Desktop\\file\\testPOIWrite"
				+ LCDateUtil.getNewDateUTC().getTime() + ".csv");
		InquiryProcessResponse response = getProcessBCL().generateFileCSV(inquiryProcessRequest);
		assertNotNull(response);
		assertNotNull(response.getFileName());
		assertTrue(response.isOperationSuccess());

		// Errors situations
		// ISmartPointDAC
		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class,
				"generateFileCSV");
		response = getProcessBCL().generateFileCSV(inquiryProcessRequest);
		assertFalse(response.isOperationSuccess());
		this.assertMessages(response, ERROR_CODE);
		resetMocksToProcessArea();

		// IProcessBCL
		// Return operation success because is set at implementation
		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, FETCH_PROCESS_BY_ID);
		response = getProcessBCL().generateFileCSV(inquiryProcessRequest);
		assertNotNull(response);
		assertNull(response.getFileName());
		assertFalse(response.isOperationSuccess());
		resetMocksToProcessArea();

		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, UPDATE_PROCESS);
		response = getProcessBCL().generateFileCSV(inquiryProcessRequest);
		assertNotNull(response);
		assertNull(response.getFileName());
		assertFalse(response.isOperationSuccess());
		resetMocksToProcessArea();

	}

	/**
	 * Test fetch tenant by rni code.
	 */
	@Test
	public void testFetchTenantByRniCode()
	{
		// Success situation
		InternalResultsResponse<Tenant> response =
				getProcessBCL().fetchTenantByRniCode(createTenant().getRniCode());
		assertResultResponse(response);

		// Error situation
		resetMocksToProcessArea();
		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "fetchTenantByRniCode");
		response = getProcessBCL().fetchTenantByRniCode(createTenant().getRniCode());
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch light history header.
	 */
	@Test
	public void testFetchLightHistoryHeader()
	{
		// Success situation
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		Tenant tenant = createTenant();
		processRequest.setTenant(tenant);
		InternalResultsResponse<HashMap<String, Integer>> response =
				getProcessBCL().fetchCountMonitoredProcesses(processRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToProcessArea();
		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "fetchCountMonitoredProcesses");
		response = getProcessBCL().fetchCountMonitoredProcesses(processRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test insert csv process.
	 */
	@Test
	public void testInsertCSVProcess()
	{
		// Success situation
		InquiryProcessRequest inquiryProcessRequest = TestBaseUtil.createInquiryProcessRequest();
		inquiryProcessRequest.setIsMonitored(true);
		ProcessResponse response = getProcessBCL().insertCSVProcess(inquiryProcessRequest);
		assertNotNull(response);
		assertTrue(response.isOperationSuccess());

		// Errors situations
		// ProcessDAC
		this.setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, INSERT_PROCESS);
		response = getProcessBCL().insertCSVProcess(inquiryProcessRequest);
		assertFalse(response.isOperationSuccess());
		this.assertMessages(response, ERROR_CODE);
		resetMocksToProcessArea();
	}
}
