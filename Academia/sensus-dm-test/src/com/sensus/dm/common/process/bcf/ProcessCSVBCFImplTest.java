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
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.dm.common.base.model.SearchTypeEnum;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.process.bcl.IProcessCSVBCL;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessSearch;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
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
@ContextConfiguration(locations = {"classpath:com/sensus/dm/common/process/processcsvbcfimpltest.xml"})
public class ProcessCSVBCFImplTest extends AbstractTestBaseBusiness
{
	// -------------------------------------------------------------------------
	// Not i18n messages/words

	/** The Constant UPDATE_CSV_DOWNLOADED. */
	private static final String UPDATE_CSV_DOWNLOADED = "updateCSVDownloaded";

	/** The Constant SHOULD_HAVE_NO_MESSAGES. */
	private static final String SHOULD_HAVE_NO_MESSAGES = "should have no messages";

	/** The Constant SHOULD_BE_TRUE. */
	private static final String SHOULD_BE_TRUE = "should be true ";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant SENSUS_EPM_DEVICEVALIDATOR_PROCESS_ID_REQUIRED. */
	private static final String SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED =
			"sensus.epm.processvalidator.id.required";

	/** The Constant DEFAULT_PROCESS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_PROCESS_BCF_EXCEPTION_MSG = "sensus.epm.processbcfimpl.defaultexception";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The process bcf. */
	private IProcessCSVBCF processCSVBCF;

	/**
	 * Gets the process csvbcf.
	 * 
	 * @return the process csvbcf
	 */
	public IProcessCSVBCF getProcessCSVBCF()
	{
		return processCSVBCF;
	}

	/**
	 * Sets the process csvbcf.
	 * 
	 * @param processCSVBCF the new process csvbcf
	 */
	@Resource
	public void setProcessCSVBCF(IProcessCSVBCF processCSVBCF)
	{
		this.processCSVBCF = processCSVBCF;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Test Settings:

	/**
	 * Sets the process CSV area.
	 */
	public void setProcessCSVArea()
	{
		setArea(getProcessCSVBCF(), EPMAreaEnum.PROCESSCSV);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setProcessCSVArea();
	}

	/**
	 * Removes the process CSV area.
	 */
	@After
	public void resetMocksToProcessCSVArea()
	{
		resetMocksData(getProcessCSVBCF());
		setProcessCSVArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test update csv downloaded.
	 */
	@Test
	public void testUpdateCSVDownloaded()
	{
		// Validation situation - user context and process id is required.
		DMProcess process = new DMProcess();
		ProcessRequest request = new ProcessRequest(process);
		ProcessResponse response = getProcessCSVBCF().updateCSVDownloaded(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED);

		// Validation situation - process id is required.
		request = TestBaseUtil.createProcessRequest();
		request.addProcessAsFirstElement(process);
		response = getProcessCSVBCF().updateCSVDownloaded(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED);

		// Success situation
		request.addProcessAsFirstElement(process);
		request.setUserContext(TestBaseUtil.createUserContextWithLocale());
		process.setId(ONE);
		process.setDescription(UPDATE_CSV_DOWNLOADED);
		response = getProcessCSVBCF().updateCSVDownloaded(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getProcessCSVBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		response = getProcessCSVBCF().updateCSVDownloaded(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessCSVArea();

		// Exception situation
		setSituation(getProcessCSVBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		response = getProcessCSVBCF().updateCSVDownloaded(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test generate summary file csv.
	 */
	@Test
	public void testGenerateSummaryFileCSV()
	{
		// Validation situation - user context, file name and process id is required.
		InquiryProcessRequest request = new InquiryProcessRequest(new DMProcess());
		request.setTenant(TestBaseUtil.createTenant());
		ProcessResponse response = getProcessCSVBCF().generateFileCSVSummary(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, USER_CONTEXT_REQUIRED, FILE_NAME_REQUIRED,
				SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED);

		// Validation situation - file name and process id is required.
		request = TestBaseUtil.createInquiryProcessRequest();
		request.addProcess(new DMProcess());
		response = getProcessCSVBCF().generateFileCSVSummary(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED,
				SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED);

		// Validation situation - process id is required.
		request.setFileName(FILE_NAME);
		response = getProcessCSVBCF().generateFileCSVSummary(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED);

		// Success situation
		request.setProcessItemStatusEnum(ProcessItemStatusEnum.COMPLETED);
		request.getFirstProcess().setId(ONE);
		request.setProcessId(ONE);
		response = getProcessCSVBCF().generateFileCSVSummary(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getProcessCSVBCF(), SituationsEnum.ERROR, IProcessCSVBCL.class);
		response = getProcessCSVBCF().generateFileCSVSummary(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessCSVArea();

		// Exception situation
		setSituation(getProcessCSVBCF(), SituationsEnum.EXCEPTION, IProcessCSVBCL.class);
		response = getProcessCSVBCF().generateFileCSVSummary(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test generate device history csv.
	 */
	@Test
	public void testGenerateDeviceHistoryCSV()
	{
		// Validation situation - device is required.
		InquiryProcessRequest request = new InquiryProcessRequest();
		request.setTenant(TestBaseUtil.createTenant());
		ProcessResponse response = getProcessCSVBCF().generateFileCSVDeviceHistory(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_REQUIRED);

		// Validation situation - file name, process id and user context is required.
		List<Device> devices = new ArrayList<Device>();
		devices.add(new Device(ELECTRIC_FLEXNET_ID, DeviceTypeEnum.HAN_DEVICE));

		request.setDevices(devices);

		response = getProcessCSVBCF().generateFileCSVDeviceHistory(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED, USER_CONTEXT_REQUIRED);

		// Validation situation - file name and process is required.
		request.setUserContext(TestBaseUtil.createUserContextWithLocale());
		response = getProcessCSVBCF().generateFileCSVDeviceHistory(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED);

		// Test Success
		request.setFileName(FILE_NAME);
		request.setProcessId(ONE);
		request.addSortExpressions(TestBaseUtil.createSortExpression());

		response = getProcessCSVBCF().generateFileCSVDeviceHistory(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getProcessCSVBCF(), SituationsEnum.ERROR, IProcessCSVBCL.class);
		response = getProcessCSVBCF().generateFileCSVDeviceHistory(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessCSVArea();

		// Exception situation
		setSituation(getProcessCSVBCF(), SituationsEnum.EXCEPTION, IProcessCSVBCL.class);
		response = getProcessCSVBCF().generateFileCSVDeviceHistory(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test insert csv process.
	 */
	@Test
	public void testInsertCSVProcess()
	{
		// Validation situation - user context is required.
		InquiryProcessRequest request = new InquiryProcessRequest();
		ProcessResponse response = getProcessCSVBCF().insertCSVProcess(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, TENANT_REQUIRED);

		// Success situation
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setTenant(TestBaseUtil.createTenant());
		response = getProcessCSVBCF().insertCSVProcess(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());
		assertNotNull("should bring process ", response.getProcesses());
		assertNotNull("should bring file name ", response.getFileName());

		// Error situation
		setSituation(getProcessCSVBCF(), SituationsEnum.ERROR, IProcessCSVBCL.class);
		response = getProcessCSVBCF().insertCSVProcess(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessCSVArea();

		// Exception situation
		setSituation(getProcessCSVBCF(), SituationsEnum.EXCEPTION, IProcessCSVBCL.class);
		response = getProcessCSVBCF().insertCSVProcess(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test generate event history csv.
	 */
	@Test
	public void testGenerateEventHistoryCSV()
	{
		// Validation situation - user context, file name and process id is required.
		InquiryProcessRequest request = new InquiryProcessRequest();
		request.setTenant(TestBaseUtil.createTenant());
		ProcessResponse response = getProcessCSVBCF().generateFileCSVEventHistory(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, FILE_NAME_REQUIRED,
				PROCESS_ID_REQUIRED);

		// Validation situation - file name and process id is required.
		request.setUserContext(TestBaseUtil.createUserContextWithLocale());
		request.addProcess(new DMProcess(ONE));

		response = getProcessCSVBCF().generateFileCSVEventHistory(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, FILE_NAME_REQUIRED, PROCESS_ID_REQUIRED);

		// Success situation
		request.setFileName(FILE_NAME);
		request.setProcessId(ONE);
		request.addSortExpressions(TestBaseUtil.createSortExpression());

		response = getProcessCSVBCF().generateFileCSVEventHistory(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getProcessCSVBCF(), SituationsEnum.ERROR, IProcessCSVBCL.class);
		response = getProcessCSVBCF().generateFileCSVEventHistory(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessCSVArea();

		// Exception situation
		setSituation(getProcessCSVBCF(), SituationsEnum.EXCEPTION, IProcessCSVBCL.class);
		response = getProcessCSVBCF().generateFileCSVEventHistory(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test generate file csv communication summary.
	 */
	@Test
	public void testGenerateFileCSVCommunicationSummary()
	{

		// Validation situation - user context, file name and process id is required.
		InquiryProcessRequest request = new InquiryProcessRequest(new DMProcess(ONE));
		request.setTenant(TestBaseUtil.createTenant());
		ProcessResponse response = getProcessCSVBCF().generateFileCSVCommunicationSummary(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, USER_CONTEXT_REQUIRED, FILE_NAME_REQUIRED);

		// Validation situation - file name and process id is required.
		request.setUserContext(TestBaseUtil.createUserContextWithLocale());

		response = getProcessCSVBCF().generateFileCSVCommunicationSummary(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED);

		// Success situation
		request.setFileName(FILE_NAME);
		request.setProcessId(ONE);

		response = getProcessCSVBCF().generateFileCSVCommunicationSummary(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getProcessCSVBCF(), SituationsEnum.ERROR, IProcessCSVBCL.class);
		response = getProcessCSVBCF().generateFileCSVCommunicationSummary(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessCSVArea();

		// Exception situation
		setSituation(getProcessCSVBCF(), SituationsEnum.EXCEPTION, IProcessCSVBCL.class);
		response = getProcessCSVBCF().generateFileCSVCommunicationSummary(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test generate file csv demand response summary.
	 */
	@Test
	public void testGenerateFileCSVDemandResponseSummary()
	{
		// Validation situation - user context, process id and file name is required.
		InquiryProcessRequest request = new InquiryProcessRequest(new DMProcess(ONE));
		request.setTenant(TestBaseUtil.createTenant());
		ProcessResponse response = getProcessCSVBCF().generateFileCSVDemandResponseSummary(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, USER_CONTEXT_REQUIRED, FILE_NAME_REQUIRED);

		// Validation situation - file name and process id is required.
		request.setUserContext(TestBaseUtil.createUserContextWithLocale());

		response = getProcessCSVBCF().generateFileCSVDemandResponseSummary(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED);

		// Success situation
		request.setFileName(FILE_NAME);
		request.setProcessId(ONE);

		response = getProcessCSVBCF().generateFileCSVDemandResponseSummary(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getProcessCSVBCF(), SituationsEnum.ERROR, IProcessCSVBCL.class);
		response = getProcessCSVBCF().generateFileCSVDemandResponseSummary(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessCSVArea();

		// Exception situation
		setSituation(getProcessCSVBCF(), SituationsEnum.EXCEPTION, IProcessCSVBCL.class);
		response = getProcessCSVBCF().generateFileCSVDemandResponseSummary(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test generate file csv import han summary.
	 */
	@Test
	public void testGenerateFileCSVImportHanSummary()
	{
		// Validation situation - user context, process id and file name is required.
		InquiryProcessRequest request = new InquiryProcessRequest(new DMProcess(ONE));
		request.setTenant(TestBaseUtil.createTenant());
		ProcessResponse response = getProcessCSVBCF().generateFileCSVImportHanSummary(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, USER_CONTEXT_REQUIRED, FILE_NAME_REQUIRED);

		// Validation situation - file name is required.
		request.setUserContext(TestBaseUtil.createUserContextWithLocale());
		request.setProcessId(ONE);

		response = getProcessCSVBCF().generateFileCSVImportHanSummary(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, FILE_NAME_REQUIRED);

		// Success situation
		request.setFileName(FILE_NAME);

		response = getProcessCSVBCF().generateFileCSVImportHanSummary(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getProcessCSVBCF(), SituationsEnum.ERROR, IProcessCSVBCL.class);
		response = getProcessCSVBCF().generateFileCSVImportHanSummary(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessCSVArea();

		// Exception situation
		setSituation(getProcessCSVBCF(), SituationsEnum.EXCEPTION, IProcessCSVBCL.class);
		response = getProcessCSVBCF().generateFileCSVImportHanSummary(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test generate today csv.
	 */
	@Test
	public void testGenerateTodayCSV()
	{
		// Validation situation - user context, process id and file name is required.
		InquiryProcessRequest request = new InquiryProcessRequest(new DMProcess(ONE));
		request.setProcessSearch(new ProcessSearch(SearchTypeEnum.ADDRESS, ADDRESS));
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		request.setDateFormat(FORMATTED_DATE);

		ProcessResponse response = getProcessCSVBCF().generateFileCSVToday(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, FILE_NAME_REQUIRED, PROCESS_ID_REQUIRED);

		// Validation situation - file name is required.
		request.setUserContext(TestBaseUtil.createUserContextWithLocale());
		request.setProcessId(ONE);

		response = getProcessCSVBCF().generateFileCSVToday(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, FILE_NAME_REQUIRED);

		// Success situation
		request.setFileName(FILE_NAME);
		request.setProcessId(ONE);
		request.getProcessSearch().setStartDate(new Date());
		request.getProcessSearch().setEndDate(new Date());

		response = getProcessCSVBCF().generateFileCSVToday(request);

		assertNotNull(response);
		assertEquals(SHOULD_HAVE_NO_MESSAGES, ZERO, response.getMessageList().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getProcessCSVBCF(), SituationsEnum.ERROR, IProcessCSVBCL.class);
		response = getProcessCSVBCF().generateFileCSVToday(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessCSVArea();

		// Exception situation
		setSituation(getProcessCSVBCF(), SituationsEnum.EXCEPTION, IProcessCSVBCL.class);
		response = getProcessCSVBCF().generateFileCSVToday(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}
}
