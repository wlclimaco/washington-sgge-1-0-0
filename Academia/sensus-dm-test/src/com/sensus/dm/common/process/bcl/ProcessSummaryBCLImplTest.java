package com.sensus.dm.common.process.bcl;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.process.dac.IProcessSummaryDAC;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class ProcessSummaryBCLImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/dm/common/process/processsummarybclimpltest.xml"})
public class ProcessSummaryBCLImplTest extends AbstractTestBaseBusiness
{

	/** The process summary bcl. */
	private IProcessSummaryBCL processSummaryBCL;

	/**
	 * Gets the process summary bcl.
	 * 
	 * @return the process summary bcl
	 */
	public IProcessSummaryBCL getProcessSummaryBCL()
	{
		return processSummaryBCL;
	}

	/**
	 * Sets the process summary bcl.
	 * 
	 * @param processSummaryBCL the new process summary bcl
	 */
	@Resource(name = "processSummaryBCLTarget")
	public void setProcessSummaryBCL(IProcessSummaryBCL processSummaryBCL)
	{
		this.processSummaryBCL = processSummaryBCL;
	}

	/**
	 * Sets the process area.
	 */
	public void setProcessArea()
	{
		setArea(getProcessSummaryBCL(), EPMAreaEnum.PROCESS);
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
		resetMocksData(getProcessSummaryBCL());
		setProcessArea();
	}

	/**
	 * Test fetch process items by connect disconnect.
	 */
	@Test
	public void testFetchProcessItemsByConnectDisconnect()
	{
		// Success situation

		InternalResultsResponse<ProcessItem> response =
				getProcessSummaryBCL().fetchProcessItemsByConnectDisconnect(
						new ProcessRequest(new DMProcess(new ProcessItem(new Device(DEVICE_ID))),
								TestBaseUtil.createUserContext()));

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch processes demand response status.
	 */
	@Test
	public void testFetchProcessesDemandResponseStatus()
	{
		// Success situation
		ProcessRequest request = TestBaseUtil.createProcessRequest();
		request.addProcessAsFirstElement(TestBaseUtil.createProcess());

		InternalResultsResponse<DMProcess> response =
				getProcessSummaryBCL().fetchProcessesDemandResponseStatus(request);

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch demand response summary.
	 */
	@Test
	public void testFetchDemandResponseSummary()
	{
		// Success situation
		ProcessRequest request = TestBaseUtil.createProcessRequest();
		request.addProcessAsFirstElement(new DMProcess());

		InternalResultsResponse<DMProcess> response =
				getProcessSummaryBCL().fetchDemandResponseSummary(request);

		TestBaseUtil.assertResultResponse(response);

		// Error situation

		this.setSituation(getProcessSummaryBCL(), SituationsEnum.ERROR, IProcessSummaryDAC.class);
		response = getProcessSummaryBCL().fetchDemandResponseSummary(request);
		this.assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();
	}

	/**
	 * Test fetch all demand response setup.
	 */
	@Test
	public void testFetchAllDemandResponseSetup()
	{
		// Success situation
		ProcessRequest request = TestBaseUtil.createProcessRequest();
		request.addProcessAsFirstElement(new DMProcess());

		InternalResultsResponse<DMProcess> response =
				getProcessSummaryBCL().fetchAllDemandResponseSetup(request);

		TestBaseUtil.assertResultResponse(response);

		// Error situation

		this.setSituation(getProcessSummaryBCL(), SituationsEnum.ERROR, IProcessSummaryDAC.class);
		response = getProcessSummaryBCL().fetchAllDemandResponseSetup(request);
		this.assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();
	}

	/**
	 * Test fetch demand response program participation.
	 */
	@Test
	public void testFetchDemandResponseProgramParticipation()
	{
		// Success situation

		InternalResultsResponse<DMProcess> response =
				getProcessSummaryBCL().fetchDemandResponseProgramParticipation(TestBaseUtil.createProcessRequest());

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch send han text message.
	 */
	@Test
	public void testFetchSendHanTextMessage()
	{
		// Success situation

		InternalResultsResponse<DMProcess> response =
				getProcessSummaryBCL().fetchProcessSendHanTextMessage(TestBaseUtil.createProcessRequest());

		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Fetch last tamper detect timeout.
	 */
	@Test
	public void fetchLastTamperDetectTimeout()
	{
		ProcessRequest processRequest = new ProcessRequest(new DMProcess(ONE));
		InternalResultsResponse<DMProcess> response =
				getProcessSummaryBCL().fetchLastTamperDetectTimeout(processRequest);
		TestBaseUtil.assertResponse(response);
	}
}
