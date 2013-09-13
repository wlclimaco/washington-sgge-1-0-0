package com.sensus.dm.common.process.bcf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.UserContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessSearch;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.InquiryProcessResponse;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class ProcessBCFImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/common/process/processbcfimpltest.xml"})
public class ProcessBCFImplTest extends AbstractTestBaseBusiness
{

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant FETCH_PROCESS_ITEMS_BY_PROCESS_ID. */
	private static final String FETCH_PROCESS_ITEMS_BY_PROCESS_ID = "fetchProcessItemsByProcessId";

	/** The Constant FETCH_PROCESS_ITEMS_BY_SCHEDULE. */
	private static final String FETCH_PROCESS_ITEMS_BY_SCHEDULE = "fetchProcessItemsBySchedule";

	/** The Constant FETCH_IMPORT_HAN_DEVICE_SUMMARY. */
	private static final String FETCH_IMPORT_HAN_DEVICE_SUMMARY = "fetchImportHanDeviceSummary";

	/** The Constant FETCH_DEMAND_READ_PING_SUMMARY. */
	private static final String FETCH_DEMAND_READ_PING_SUMMARY = "fetchDemandReadPingSummary";

	/** The Constant FETCH_COMMUNICATION_SUMMARY. */
	private static final String FETCH_COMMUNICATION_SUMMARY = "fetchCommunicationSummary";

	/** The Constant FETCH_MONITORED_PROCESS. */
	private static final String FETCH_MONITORED_PROCESS = "fetchMonitoredProcess";

	/** The Constant FETCH_TODAY_PROCESSES. */
	private static final String FETCH_TODAY_PROCESSES = "fetchTodayProcesses";

	/** The Constant FETCH_COUNT_MONITORED_PROCESSES. */
	private static final String FETCH_COUNT_MONITORED_PROCESSES = "fetchCountMonitoredProcesses";

	/** The Constant CHECK_LINK_STATUS. */
	private static final String CHECK_LINK_STATUS = "checkLinkStatus";

	/** The Constant UPDATE_PROCESS. */
	private static final String UPDATE_PROCESS = "updateProcess";

	/** The Constant FETCH_PROCESS_BY_ID. */
	private static final String FETCH_PROCESS_BY_ID = "fetchProcessById";

	/** The Constant FETCH_PROCESSES. */
	private static final String FETCH_PROCESSES = "fetchProcesses";

	/** The Constant SHOULD_BRING_THE_ID. */
	private static final String SHOULD_BRING_THE_ID = "should bring the ID";

	/** The Constant SHOULD_BRING_ONE_PROCESS. */
	private static final String SHOULD_BRING_ONE_PROCESS = "should bring one process ";

	/** The Constant SHOULD_BRING_FIVE_PROCESS. */
	private static final String SHOULD_BRING_FIVE_PROCESS = "should bring five processes ";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant SENSUS_EPM_PROCESSVALIDATOR_PROCESS_REQUIRED. */
	private static final String SENSUS_EPM_PROCESSVALIDATOR_PROCESS_REQUIRED =
			"sensus.epm.processvalidator.process.required";

	/** The Constant SENSUS_EPM_DEVICEVALIDATOR_PROCESS_ID_REQUIRED. */
	private static final String SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED =
			"sensus.epm.processvalidator.id.required";

	/** The Constant SENSUS_EPM_PROCESSVALIDATOR_IS_MONITORED_REQUIRED. */
	private static final String SENSUS_EPM_PROCESSVALIDATOR_IS_MONITORED_REQUIRED =
			"sensus.epm.processvalidator.is.monitored.required";

	/** The Constant SENSUS_EPM_PROCESSVALIDATOR_PROCESSSEARCH_REQUIRED. */
	private static final String SENSUS_EPM_PROCESSVALIDATOR_PROCESSSEARCH_REQUIRED =
			"sensus.epm.processvalidator.processsearch.required";

	/** The Constant SENSUS_EPM_PROCESSVALIDATOR_DASHBOARD_REQUIRED. */
	private static final String SENSUS_EPM_PROCESSVALIDATOR_DASHBOARD_REQUIRED =
			"sensus.epm.processvalidator.dashboard.required";

	/** The Constant STARTDATETIME_REQUIRED. */
	private static final String STARTDATETIME_REQUIRED =
			"sensus.epm.rangedatevalidator.start.date.time.required";

	/** The Constant ENDDATETIME_REQUIRED. */
	private static final String ENDDATETIME_REQUIRED =
			"sensus.epm.rangedatevalidator.end.date.time.required";

	/** The Constant DEFAULT_PROCESS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_PROCESS_BCF_EXCEPTION_MSG = "sensus.epm.processbcfimpl.defaultexception";

	/** The Constant SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ITEMS_REQUIRED. */
	private static final String SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ITEMS_REQUIRED =
			"sensus.epm.processvalidator.process_items.required";

	/** The Constant SENSUS_EPM_TENANTREQUESTVALIDATOR_USER_CONTEXT_REQUIRED. */
	private static final String SENSUS_EPM_TENANTREQUESTVALIDATOR_USER_CONTEXT_REQUIRED =
			"sensus.epm.tenantrequestvalidator.user.context.required";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The process bcf. */
	private IProcessBCF processBCF;

	/**
	 * Gets the process bcf.
	 * 
	 * @return the process bcf
	 */
	public IProcessBCF getProcessBCF()
	{
		return processBCF;
	}

	/**
	 * Sets the process bcf.
	 * 
	 * @param processBCF the new process bcf
	 */
	@Resource
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Test Settings:

	/**
	 * Sets the process area.
	 */
	public void setProcessArea()
	{
		setArea(getProcessBCF(), EPMAreaEnum.PROCESS);
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
		resetMocksData(getProcessBCF());
		setProcessArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test fetch processes.
	 */
	@Test
	public void testFetchProcesses()
	{
		// Validation Situation - user context required
		InquiryProcessRequest request = new InquiryProcessRequest();
		request.setTenant(TestBaseUtil.createTenant());
		InquiryProcessResponse response = getProcessBCF().fetchProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, SERVICE_TYPE_ENUM_REQUIRED);

		// Validation Situation - user id and locale required
		request.setUserContext(new UserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getProcessBCF().fetchProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED);

		// Validation Situation - sort expression required
		request = new InquiryProcessRequest(TestBaseUtil.createUserContextWithLocale());
		request.setTenant(TestBaseUtil.createTenant());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getProcessBCF().fetchProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ORDERBY_REQUIRED);
		assertNotNull(response);

		// Validation Situation - page size and start row
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		TestBaseUtil.createInvalidPageSizeStartRow(request);
		response = getProcessBCF().fetchProcesses(request);
		assertMessages(response, START_ROW_INVALID, PAGE_SIZE_INVALID);

		// Success Situation
		request = TestBaseUtil.createInquiryProcessRequest();
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getProcessBCF().fetchProcesses(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_BRING_FIVE_PROCESS, FIVE, response.getProcesses().size());
		assertNotNull(SHOULD_BRING_THE_ID, response.getProcesses().get(0).getId());

		// Error situation
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class,
				FETCH_PROCESSES);
		response = getProcessBCF().fetchProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class,
				FETCH_PROCESSES);
		response = getProcessBCF().fetchProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch process by id.
	 */
	@Test
	public void testFetchProcessById()
	{
		// Validation Situation - user context required
		ProcessRequest request = new ProcessRequest();
		request.addProcessAsFirstElement(TestBaseUtil.createProcess());
		InquiryProcessResponse response = getProcessBCF().fetchProcessById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation Situation - locale and process id required
		request.setUserContext(new UserContext());
		request.setTenant(TestBaseUtil.createTenant());
		request.addProcessAsFirstElement(TestBaseUtil.createProcess());
		response = getProcessBCF().fetchProcessById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, LOCALE_REQUIRED, USER_ID_REQUIRED);

		// Success situation
		request.setUserContext(TestBaseUtil.createUserContextWithLocale());
		request.addProcessAsFirstElement(TestBaseUtil.createProcess());
		response = getProcessBCF().fetchProcessById(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class,
				FETCH_PROCESS_BY_ID);
		response = getProcessBCF().fetchProcessById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class,
				FETCH_PROCESS_BY_ID);
		response = getProcessBCF().fetchProcessById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test update process.
	 */
	@Test
	public void testUpdateProcess()
	{
		// Validation Situation - user context and process required
		ProcessRequest request = new ProcessRequest();
		ProcessResponse response = getProcessBCF().updateProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_REQUIRED);

		// Validation Situation - user id, process id and is monitored required
		request.setUserContext(new UserContext());
		request.addProcessAsFirstElement(new DMProcess());
		response = getProcessBCF().updateProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED,
				SENSUS_EPM_PROCESSVALIDATOR_IS_MONITORED_REQUIRED);

		// Success situation
		request.setUserContext(TestBaseUtil.createUserContext());
		request.addProcessAsFirstElement(TestBaseUtil.createProcess());
		response = getProcessBCF().updateProcess(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class,
				UPDATE_PROCESS);
		response = getProcessBCF().updateProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class,
				UPDATE_PROCESS);
		response = getProcessBCF().updateProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test check link status.
	 */
	@Test
	public void testCheckLinkStatus()
	{
		// Success situation
		ProcessRequest request = new ProcessRequest();
		ProcessResponse response = getProcessBCF().checkLinkStatus(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class,
				CHECK_LINK_STATUS);
		response = getProcessBCF().checkLinkStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class,
				CHECK_LINK_STATUS);
		response = getProcessBCF().checkLinkStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch count monitored processes.
	 */
	@Test
	public void testFetchCountMonitoredProcesses()
	{
		// Validation Situation - user context required
		ProcessRequest request = new ProcessRequest();
		request.setTenant(TestBaseUtil.createTenant());
		ProcessResponse response = getProcessBCF().fetchCountMonitoredProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, SERVICE_TYPE_ENUM_REQUIRED);

		// Success Situation
		request = TestBaseUtil.createProcessRequestWithTenant();
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getProcessBCF().fetchCountMonitoredProcesses(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_BRING_ONE_PROCESS, 1, response.getCountMonitoredProcess().size());
		assertNotNull(SHOULD_BRING_THE_ID, response.getCountMonitoredProcess().containsKey("count_monitored"));

		// Error situation
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class,
				FETCH_COUNT_MONITORED_PROCESSES);
		response = getProcessBCF().fetchCountMonitoredProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class,
				FETCH_COUNT_MONITORED_PROCESSES);
		response = getProcessBCF().fetchCountMonitoredProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch today processes.
	 */
	@Test
	public void testFetchTodayProcesses()
	{
		// Validation Situation - process search and user context required
		InquiryProcessRequest request = new InquiryProcessRequest();
		request.setTenant(TestBaseUtil.createTenant());
		InquiryProcessResponse response = getProcessBCF().fetchTodayProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_PROCESSVALIDATOR_PROCESSSEARCH_REQUIRED, USER_CONTEXT_REQUIRED,
				SERVICE_TYPE_ENUM_REQUIRED);

		// Validation Situation - user id and locale required
		request.setUserContext(new UserContext());
		request.setProcessSearch(new ProcessSearch());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getProcessBCF().fetchTodayProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED, LOCALE_REQUIRED);

		// Validation Situation - page size and start row
		request.setUserContext(TestBaseUtil.createUserContextWithLocale());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		TestBaseUtil.createInvalidPageSizeStartRow(request);
		response = getProcessBCF().fetchTodayProcesses(request);
		assertMessages(response, START_ROW_INVALID, PAGE_SIZE_INVALID);

		// Validation Situation - range date validation
		request = TestBaseUtil.createInquiryProcessRequest();
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setProcessSearch(new ProcessSearch());
		response = getProcessBCF().fetchTodayProcesses(request);
		assertMessages(response, STARTDATETIME_REQUIRED, ENDDATETIME_REQUIRED);

		// Success Situation
		request.getProcessSearch().setStartDate(new Date());
		request.getProcessSearch().setEndDate(new Date());
		response = getProcessBCF().fetchTodayProcesses(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_BRING_FIVE_PROCESS, FIVE, response.getProcesses().size());
		assertNotNull(SHOULD_BRING_THE_ID, response.getProcesses().get(0).getId());

		// Error situation
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class,
				FETCH_TODAY_PROCESSES);
		response = getProcessBCF().fetchTodayProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class,
				FETCH_TODAY_PROCESSES);
		response = getProcessBCF().fetchTodayProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch monitored process.
	 */
	@Test
	public void testFetchMonitoredProcess()
	{
		// Validation Situation - user context required / tenant required / service required
		InquiryProcessRequest request = new InquiryProcessRequest();
		InquiryProcessResponse response = getProcessBCF().fetchMonitoredProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, TENANT_REQUIRED, SERVICE_TYPE_ENUM_REQUIRED);

		// Validation Situation - process required
		request = TestBaseUtil.createInquiryProcessRequest();
		response = getProcessBCF().fetchMonitoredProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_REQUIRED);

		// Validation Situation - process is monitored and dash board required
		request = TestBaseUtil.createInquiryProcessRequest();
		request.addProcess(new DMProcess());
		response = getProcessBCF().fetchMonitoredProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_PROCESSVALIDATOR_IS_MONITORED_REQUIRED,
				SENSUS_EPM_PROCESSVALIDATOR_DASHBOARD_REQUIRED);

		// Success Situation
		request.getProcesses().set(0, TestBaseUtil.createProcess());
		response = getProcessBCF().fetchMonitoredProcess(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_BRING_FIVE_PROCESS, FIVE, response.getProcesses().size());
		assertNotNull(SHOULD_BRING_THE_ID, response.getProcesses().get(0).getId());

		// Error situation
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class,
				FETCH_MONITORED_PROCESS);
		response = getProcessBCF().fetchMonitoredProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class,
				FETCH_MONITORED_PROCESS);
		response = getProcessBCF().fetchMonitoredProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch communication summary.
	 */
	@Test
	public void testFetchCommunicationSummary()
	{
		// Validation Situation - user context and process required
		ProcessRequest request = new ProcessRequest();
		ProcessResponse response = getProcessBCF().fetchCommunicationSummary(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_REQUIRED);

		// Validation Situation - user id, locale and process id required
		request.setUserContext(new UserContext());
		request.addProcessAsFirstElement(new DMProcess());
		response = getProcessBCF().fetchCommunicationSummary(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED, LOCALE_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED);

		// Success situation
		request.setUserContext(TestBaseUtil.createUserContextWithLocale());
		request.addProcessAsFirstElement(TestBaseUtil.createProcess());
		response = getProcessBCF().fetchCommunicationSummary(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_BRING_FIVE_PROCESS, FIVE, response.getProcesses().size());
		assertNotNull(SHOULD_BRING_THE_ID, response.getProcesses().get(0).getId());

		// Error situation
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class,
				FETCH_COMMUNICATION_SUMMARY);
		response = getProcessBCF().fetchCommunicationSummary(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class,
				FETCH_COMMUNICATION_SUMMARY);
		response = getProcessBCF().fetchCommunicationSummary(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch import han device summary.
	 */
	@Test
	public void testFetchImportHanDeviceSummary()
	{
		// Validation Situation - user context and process required
		ProcessRequest request = new ProcessRequest();
		ProcessResponse response = getProcessBCF().fetchImportHanDeviceSummary(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_REQUIRED);

		// Validation Situation - user id, locale and process id required
		request.setUserContext(new UserContext());
		request.addProcessAsFirstElement(new DMProcess());
		response = getProcessBCF().fetchImportHanDeviceSummary(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED, LOCALE_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED);

		// Success situation
		request.setUserContext(TestBaseUtil.createUserContextWithLocale());
		request.addProcessAsFirstElement(TestBaseUtil.createProcess());
		response = getProcessBCF().fetchImportHanDeviceSummary(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_BRING_FIVE_PROCESS, FIVE, response.getProcesses().size());
		assertNotNull(SHOULD_BRING_THE_ID, response.getProcesses().get(0).getId());

		// Error situation
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class,
				FETCH_IMPORT_HAN_DEVICE_SUMMARY);
		response = getProcessBCF().fetchImportHanDeviceSummary(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class,
				FETCH_IMPORT_HAN_DEVICE_SUMMARY);
		response = getProcessBCF().fetchImportHanDeviceSummary(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch demand read ping summary.
	 */
	@Test
	public void testFetchDemandReadPingSummary()
	{
		// Validation Situation - user context and process required
		ProcessRequest request = new ProcessRequest();
		ProcessResponse response = getProcessBCF().fetchDemandReadPingSummary(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_REQUIRED);

		// Validation Situation - user id, locale and process id required
		request.setUserContext(new UserContext());
		request.addProcessAsFirstElement(new DMProcess());
		response = getProcessBCF().fetchDemandReadPingSummary(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED, LOCALE_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED);

		// Success situation
		request.setUserContext(TestBaseUtil.createUserContextWithLocale());
		request.addProcessAsFirstElement(TestBaseUtil.createProcess());
		response = getProcessBCF().fetchDemandReadPingSummary(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_BRING_ONE_PROCESS, ONE, response.getProcesses().size());
		assertNotNull(SHOULD_BRING_THE_ID, response.getProcesses().get(0).getId());

		// Error situation
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class,
				FETCH_DEMAND_READ_PING_SUMMARY);
		response = getProcessBCF().fetchDemandReadPingSummary(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class,
				FETCH_DEMAND_READ_PING_SUMMARY);
		response = getProcessBCF().fetchDemandReadPingSummary(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch process items by schedule.
	 */
	@Test
	public void testFetchProcessItemsBySchedule()
	{
		// Validation Situation - user context and process required
		ProcessRequest request = new ProcessRequest();
		ProcessResponse response = getProcessBCF().fetchProcessItemsBySchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_REQUIRED);

		// Validation Situation - user id and process id required
		request.setUserContext(new UserContext());
		request.addProcessAsFirstElement(new DMProcess());
		response = getProcessBCF().fetchProcessItemsBySchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED);

		// Success situation
		request.setUserContext(TestBaseUtil.createUserContextWithLocale());
		request.addProcessAsFirstElement(TestBaseUtil.createProcess());
		response = getProcessBCF().fetchProcessItemsBySchedule(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_BRING_FIVE_PROCESS, FIVE, response.getProcesses().size());
		assertNotNull(SHOULD_BRING_THE_ID, response.getProcesses().get(0).getId());

		// Error situation
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class,
				FETCH_PROCESS_ITEMS_BY_SCHEDULE);
		response = getProcessBCF().fetchProcessItemsBySchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class,
				FETCH_PROCESS_ITEMS_BY_SCHEDULE);
		response = getProcessBCF().fetchProcessItemsBySchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch process items by process id.
	 */
	@Test
	public void testFetchProcessItemsByProcessId()
	{
		// Validation Situation - user context and process required
		ProcessRequest request = new ProcessRequest();
		ProcessResponse response = getProcessBCF().fetchProcessItemsByProcessId(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_REQUIRED);

		// Validation Situation - user id and process id required
		request.setUserContext(new UserContext());
		request.addProcessAsFirstElement(new DMProcess());
		response = getProcessBCF().fetchProcessItemsByProcessId(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED);

		// Success situation
		request.setUserContext(TestBaseUtil.createUserContextWithLocale());
		request.addProcessAsFirstElement(TestBaseUtil.createProcess());
		response = getProcessBCF().fetchProcessItemsByProcessId(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_BRING_FIVE_PROCESS, FIVE, response.getProcesses().size());
		assertNotNull(SHOULD_BRING_THE_ID, response.getProcesses().get(0).getId());

		// Error situation
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class,
				FETCH_PROCESS_ITEMS_BY_PROCESS_ID);
		response = getProcessBCF().fetchProcessItemsByProcessId(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class,
				FETCH_PROCESS_ITEMS_BY_PROCESS_ID);
		response = getProcessBCF().fetchProcessItemsByProcessId(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch all process items.
	 */
	@Test
	public void testFetchAllProcessItems()
	{
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();

		// Success Situation
		ProcessResponse response = getProcessBCF().fetchAllProcessItems(processRequest);
		assertNotNull(response);
		assertTrue(response.isOperationSuccess());

		// Error Situation
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		response = getProcessBCF().fetchAllProcessItems(processRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		response = getProcessBCF().fetchAllProcessItems(processRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test expire process items.
	 */
	@Test
	public void testExpireProcessItems()
	{
		ProcessRequest processRequest = new ProcessRequest();
		ProcessResponse response = getProcessBCF().updateProcessItemsToExpire(processRequest);
		assertNotNull(response);
		assertMessages(response, SENSUS_EPM_TENANTREQUESTVALIDATOR_USER_CONTEXT_REQUIRED);

		processRequest = TestBaseUtil.createProcessRequest();
		response = getProcessBCF().updateProcessItemsToExpire(processRequest);
		assertNotNull(response);
		assertMessages(response, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_REQUIRED);

		// missing process items
		DMProcess process = new DMProcess();
		processRequest.addProcessAsFirstElement(process);

		response = getProcessBCF().updateProcessItemsToExpire(processRequest);
		assertMessages(response, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ITEMS_REQUIRED);

		// create process item - SUCCESS
		List<ProcessItem> processItems = new ArrayList<ProcessItem>();
		ProcessItem processItem = new ProcessItem();
		processItem.setId(1);

		processItems.add(processItem);

		process = new DMProcess(processItems);
		processRequest.addProcessAsFirstElement(process);

		response = getProcessBCF().updateProcessItemsToExpire(processRequest);
		assertNotNull(response);

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		response = getProcessBCF().updateProcessItemsToExpire(processRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test fetch relay.
	 */
	@Test
	public void testFetchRelay()
	{

		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		processRequest.setTenant(TestBaseUtil.createTenant());
		ProcessResponse response = getProcessBCF().fetchRelays(processRequest);
		assertNotNull(response);
		assertMessages(response, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_REQUIRED);

		// missing process items
		DMProcess process = new DMProcess();
		processRequest.addProcessAsFirstElement(process);

		response = getProcessBCF().fetchRelays(processRequest);
		assertMessages(response, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ITEMS_REQUIRED);

		// create process item - SUCCESS
		List<ProcessItem> processItems = new ArrayList<ProcessItem>();
		ProcessItem processItem = new ProcessItem(new Device(new Radio(ENTEK_FLEXNET_ID)));

		processItems.add(processItem);

		process = new DMProcess(processItems);
		processRequest.addProcessAsFirstElement(process);

		response = getProcessBCF().fetchRelays(processRequest);
		assertNotNull(response);

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		response = getProcessBCF().fetchRelays(processRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);

	}

}
