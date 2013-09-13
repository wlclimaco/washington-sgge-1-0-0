package com.sensus.dm.common.process.bcl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMessageUtil;
import com.sensus.dm.common.process.model.CSVProcess;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessSearch;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class ProcessBCLImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/dm/common/process/processcsvbclimpltest.xml"})
public class ProcessCSVBCLImplTest extends AbstractTestBaseBusiness
{

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant NETWORK_ADDRESS_PROCESS_ITEM. */
	private static final String NETWORK_ADDRESS_PROCESS_ITEM = "00:00:00:00:00:00:03:f2";

	/** The Constant INITIATE_DEMAND_RESET_EVENT. */
	private static final String INITIATE_DEMAND_RESET_EVENT = "Initiate Demand Reset";

	/** The Constant INSERT_PROCESS. */
	private static final String INSERT_PROCESS = "insertProcess";

	/** The Constant FETCH_DEMAND_RESPONSE_SUMMARY. */
	private static final String FETCH_DEMAND_RESPONSE_SUMMARY = "fetchDemandResponseSummary";

	/** The Constant FETCH_PROCESS_BY_ID. */
	private static final String FETCH_PROCESS_BY_ID = "fetchProcessById";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The process bcl. */
	private IProcessCSVBCL processCSVBCL; // injected by Spring through setter

	/** The process today list all columns. */
	private List<CSVColumn> processTodayListAllColumns; // injected by Spring through setter

	/**
	 * Gets the process csvbcl.
	 * 
	 * @return the process csvbcl
	 */
	public IProcessCSVBCL getProcessCSVBCL()
	{
		return processCSVBCL;
	}

	/**
	 * Sets the process csvbcl.
	 * 
	 * @param processCSVBCL the new process csvbcl
	 */
	@Resource(name = "processCSVBCLTarget")
	public void setProcessCSVBCL(IProcessCSVBCL processCSVBCL)
	{
		this.processCSVBCL = processCSVBCL;
	}

	/**
	 * Gets the process today list all columns.
	 * 
	 * @return the process today list all columns
	 */
	public List<CSVColumn> getProcessTodayListAllColumns()
	{
		return processTodayListAllColumns;
	}

	/**
	 * Sets the process today list all columns.
	 * 
	 * @param processTodayListAllColumns the new process today list all columns
	 */
	@Resource(name = "processCSVBCLTarget")
	public void setProcessTodayListAllColumns(List<CSVColumn> processTodayListAllColumns)
	{
		this.processTodayListAllColumns = processTodayListAllColumns;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Test Settings:

	/**
	 * Sets the process csv area.
	 */
	public void setProcessCSVArea()
	{
		setArea(getProcessCSVBCL(), EPMAreaEnum.PROCESSCSV);
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
	 * Removes the process csv area.
	 */
	@After
	public void resetMocksToProcessCSVArea()
	{
		resetMocksData(getProcessCSVBCL());
		setProcessCSVArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test insert csv process.
	 */
	@Test
	public void testInsertCSVProcess()
	{
		// Success situation

		InquiryProcessRequest request = TestBaseUtil.createInquiryProcessRequest();
		request.addProcess(TestBaseUtil.createProcess());

		InternalResultsResponse<CSVProcess> response = getProcessCSVBCL().insertCSVProcess(request);

		TestBaseUtil.assertResultResponse(response);
		assertNotNull(response.getFirstResult().getFileName());
		assertNotNull(response.getFirstResult().getProcessList());

		// Error situation - insert Process

		this.setSituation(getProcessCSVBCL(), SituationsEnum.ERROR, IProcessBCL.class, INSERT_PROCESS);
		response = getProcessCSVBCL().insertCSVProcess(request);
		this.assertMessages(response, ERROR_CODE);
		resetMocksToProcessCSVArea();
	}

	/**
	 * Test generate device history csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testGenerateDeviceHistoryCSV() throws Exception
	{
		// Success situation.
		InquiryProcessRequest request = TestBaseUtil.createInquiryProcessRequest();
		setDefaultProcessExportCSV(request);
		request.setDevices(TestBaseUtil.createDeviceList(DeviceTypeEnum.ELECTRIC_METER, TWO));

		InternalResponse response = getProcessCSVBCL().generateFileCSVDeviceHistory(request);
		assertProcessFileReader(request.getFileName());
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test generate summary file csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testGenerateSummaryFileCSV() throws Exception
	{
		// Success situation - Export Process Completed
		InquiryProcessRequest request = TestBaseUtil.createInquiryProcessRequest();
		request.addProcess(new DMProcess(ONE));
		request.setProcessItemStatusEnum(ProcessItemStatusEnum.COMPLETED);
		setDefaultProcessExportCSV(request);

		InternalResponse response = getProcessCSVBCL().generateFileCSVSummary(request);

		assertFileReader(request.getFileName());

		TestBaseUtil.assertResponse(response);

		// Success situation - Export Process Failed
		request.setProcessItemStatusEnum(ProcessItemStatusEnum.FAILED);

		response = getProcessCSVBCL().generateFileCSVSummary(request);

		assertFileReader(request.getFileName());

		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test generate event history csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testGenerateEventHistoryCSV() throws Exception
	{
		// Success situation - test Today
		InquiryProcessRequest request =
				createInquiryProcessRequestExportCSV(FILE_NAME, TIME_ZONE_N3,
						new ProcessSearch(new Date(), new Date()),
						true);
		request.setProcessId(ONE);
		InternalResponse response = getProcessCSVBCL().generateFileCSVEventHistory(request);

		assertProcessFileReader(request.getFileName());
		TestBaseUtil.assertResponse(response);

		// Success situation - test Processes
		request.setSelectionPaginationIds(new ArrayList<BigInteger>());
		request.getSelectionPaginationIds().add(ELECTRIC_FLEXNET_ID);
		request.getSelectionPaginationIds().add(new BigInteger(FLEXNET_ID_1002));
		request.setPaginationAllSelected(true);

		response = getProcessCSVBCL().generateFileCSVEventHistory(request);

		assertProcessFileReader(request.getFileName());
		TestBaseUtil.assertResponse(response);

		// Success situation - without Process search
		request.setProcessSearch(null);

		response = getProcessCSVBCL().generateFileCSVEventHistory(request);

		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test generate today csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testGenerateTodayCSV() throws Exception
	{
		// Success situation
		InquiryProcessRequest request =
				createInquiryProcessRequestExportCSV(FILE_NAME, TIME_ZONE_N3, new ProcessSearch(new Date(),
						new Date()), true);

		request.setProcessId(1);
		InternalResponse response = getProcessCSVBCL().generateFileCSVToday(request);

		assertProcessFileReader(request.getFileName());
		TestBaseUtil.assertResponse(response);

		// Success situation (with null processSearch for complete coverage)
		InquiryProcessRequest request2 =
				createInquiryProcessRequestExportCSV(FILE_NAME, TIME_ZONE_N3, null, true);

		request2.setProcessId(1);
		InternalResponse response2 = getProcessCSVBCL().generateFileCSVToday(request2);

		assertProcessFileReader(request.getFileName());
		TestBaseUtil.assertResponse(response2);

	}

	/**
	 * Test generate file csv communication summary.
	 */
	@Test
	public void testGenerateFileCSVCommunicationSummary()
	{
		InquiryProcessRequest request = TestBaseUtil.createInquiryProcessRequest();
		request.addProcess(TestBaseUtil.createProcess());
		request.setSelectionPaginationIds(new ArrayList<BigInteger>());
		setDefaultProcessExportCSV(request);

		// Exception situation - insert Process
		// used to test sending a message without a space, actually _SUCCEEDS_,
		// just does not return an i16ed message
		this.setSituation(getProcessCSVBCL(), SituationsEnum.EXCEPTION, IProcessBCL.class, FETCH_PROCESS_BY_ID);
		InternalResponse response = getProcessCSVBCL().generateFileCSVCommunicationSummary(request);
		assertEquals(Status.ExceptionError, response.getStatus());
		resetMocksToProcessCSVArea();

		// Success situation
		this.setSituation(getProcessCSVBCL(), SituationsEnum.SUCCESS, IProcessBCL.class, FETCH_PROCESS_BY_ID);
		response = getProcessCSVBCL().generateFileCSVCommunicationSummary(request);
		assertEquals(Status.OperationSuccess, response.getStatus());
	}

	/**
	 * Test generate file csv demand response summary.
	 */
	@Test
	public void testGenerateFileCSVDemandResponseSummary()
	{
		// Success situation
		InquiryProcessRequest request = TestBaseUtil.createInquiryProcessRequest();
		request.addProcess(TestBaseUtil.createProcess());
		setDefaultProcessExportCSV(request);

		InternalResponse response = getProcessCSVBCL().generateFileCSVDemandResponseSummary(request);

		TestBaseUtil.assertResponse(response);

		// Error situation - insert Process
		this.setSituation(getProcessCSVBCL(), SituationsEnum.ERROR, IProcessSummaryBCL.class,
				FETCH_DEMAND_RESPONSE_SUMMARY);
		response = getProcessCSVBCL().generateFileCSVDemandResponseSummary(request);

		assertEquals(Status.ExceptionError, response.getStatus());
	}

	/**
	 * Test generate file csv demand read summary.
	 */
	@Test
	public void testGenerateFileCSVDemandReadSummary()
	{
		// Success situation
		InquiryProcessRequest request = TestBaseUtil.createInquiryProcessRequest();
		request.addProcess(TestBaseUtil.createProcess());
		setDefaultProcessExportCSV(request);

		InternalResponse response = getProcessCSVBCL().generateFileCSVDemandReadSummary(request);

		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test generate file csv import han summary.
	 */
	@Test
	public void testGenerateFileCSVImportHanSummary()
	{
		// Success situation
		InquiryProcessRequest request = TestBaseUtil.createInquiryProcessRequest();
		request.addProcess(TestBaseUtil.createProcess());
		setDefaultProcessExportCSV(request);

		InternalResponse response = getProcessCSVBCL().generateFileCSVImportHanSummary(request);

		TestBaseUtil.assertResponse(response);

		// Error situation - insert Process

		this.setSituation(getProcessCSVBCL(), SituationsEnum.EXCEPTION, IProcessBCL.class, FETCH_PROCESS_BY_ID);
		response = getProcessCSVBCL().generateFileCSVImportHanSummary(request);
		assertEquals(Status.ExceptionError, response.getStatus());
	}

	/**
	 * Test generate file csv tamper detect summary.
	 */
	@Test
	public void testGenerateFileCSVTamperDetectSummary()
	{
		// Success situation
		InquiryProcessRequest request = TestBaseUtil.createInquiryProcessRequest();
		request.addProcess(TestBaseUtil.createProcess());
		request.getFirstProcess().setId(NINETY_EIGHT);
		setDefaultProcessExportCSV(request);
		setDefaultProcessExportCSV(request);

		InternalResponse response = getProcessCSVBCL().generateFileCSVTamperDetectSummary(request);

		TestBaseUtil.assertResponse(response);

		// Error situation - insert Process

		this.setSituation(getProcessCSVBCL(), SituationsEnum.EXCEPTION, IProcessBCL.class, FETCH_PROCESS_BY_ID);
		response = getProcessCSVBCL().generateFileCSVImportHanSummary(request);
		assertEquals(Status.ExceptionError, response.getStatus());
	}

	@Test
	public void testGenerateFileCSVDemandResponseSetupSummary()
	{
		// Success situation
		InquiryProcessRequest request = TestBaseUtil.createInquiryProcessRequest();
		request.addProcess(TestBaseUtil.createProcess());
		request.getFirstProcess().setId(NINETY_NINE);
		setDefaultProcessExportCSV(request);

		InternalResponse response = getProcessCSVBCL().generateFileCSVDemandResponseSetupSummary(request);

		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Creates the inquiry process request export csv.
	 * 
	 * @param fileName the file name
	 * @param timeZone the time zone
	 * @param processSearch the process filter
	 * @param isToday the is today
	 * @return the inquiry process request
	 */
	private InquiryProcessRequest createInquiryProcessRequestExportCSV(String fileName, String timeZone,
			ProcessSearch processSearch, Boolean isToday)
	{
		InquiryProcessRequest request = TestBaseUtil.createInquiryProcessRequest();
		request.setFileName(fileName);
		request.setDateFormat(FORMATTED_DATE);
		request.setTimeZone(timeZone);
		request.setProcessSearch(processSearch);
		return request;
	}

	/**
	 * Sets the default process export csv.
	 * 
	 * @param request the new default process export csv
	 */
	private void setDefaultProcessExportCSV(InquiryProcessRequest request)
	{
		request.setProcessId(ONE);
		request.setFileName(FILE_NAME);
		request.setTimeZone(TIME_ZONE_N3);
		request.setDateFormat(FORMATTED_DATE);
	}

	/**
	 * Assert file reader.
	 * 
	 * @param fileName the file name
	 * @throws Exception the exception
	 */
	private void assertFileReader(String fileName) throws Exception
	{
		List<String[]> data = fileReader(fileName);

		assertEquals(SensusMessageUtil.getMessage(DEVICE_ID_HEADER, null, null, Locale.getDefault()),
				data.get(ZERO)[ZERO]);

		assertEquals(SensusMessageUtil.getMessage(NETWORK_ADDRESS_HEADER, null, null, Locale.getDefault()),
				data.get(ZERO)[ONE]);

		assertEquals(NETWORK_ADDRESS_PROCESS_ITEM, data.get(ONE)[ONE]);
	}

	/**
	 * Assert process file reader.
	 * 
	 * @param fileName the file name
	 * @throws Exception the exception
	 */
	private void assertProcessFileReader(String fileName) throws Exception
	{
		List<String[]> data = fileReader(fileName);

		assertEquals(SensusMessageUtil.getMessage(ACTION_TYPE, null, null, Locale.getDefault()),
				data.get(ZERO)[ZERO]);

		assertEquals(SensusMessageUtil.getMessage(ACTION_NAME, null, null, Locale.getDefault()),
				data.get(ZERO)[ONE]);

		assertEquals(INITIATE_DEMAND_RESET_EVENT, data.get(ONE)[ONE]);

	}

}
