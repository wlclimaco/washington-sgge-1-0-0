package com.sensus.dm.common.process.bcf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.UserContext;
import com.sensus.dm.common.process.bcl.IProcessSummaryBCL;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.InquiryProcessResponse;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.device.model.HanDevice;

/**
 * The Class ProcessSummaryBCFImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/common/process/processsummarybcfimpltest.xml"})
public class ProcessSummaryBCFImplTest extends AbstractTestBaseBusiness
{
	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant FETCH_PROCESS_SEND_HAN_TEXT_MESSAGE. */
	private static final String FETCH_PROCESS_SEND_HAN_TEXT_MESSAGE = "fetchProcessSendHanTextMessage";

	/** The Constant FETCH_ALL_DEMAND_RESPONSE_SETUP. */
	private static final String FETCH_ALL_DEMAND_RESPONSE_SETUP = "fetchAllDemandResponseSetup";

	/** The Constant FETCH_DEMAND_RESPONSE_PROGRAM_PARTICIPATION. */
	private static final String FETCH_DEMAND_RESPONSE_PROGRAM_PARTICIPATION = "fetchDemandResponseProgramParticipation";

	/** The Constant FETCH_DEMAND_RESPONSE_SUMMARY. */
	private static final String FETCH_DEMAND_RESPONSE_SUMMARY = "fetchDemandResponseSummary";

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

	/** The Constant SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ITEMS_REQUIRED. */
	private static final String SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ITEMS_REQUIRED =
			"sensus.epm.processvalidator.process_items.required";

	/** The Constant HAN_DEVICES_REQUIRED. */
	private static final String HAN_DEVICES_REQUIRED = "sensus.epm.handevicevalidator.han.device.required";

	/** The Constant MAC_ADDRESS_REQUIRED. */
	private static final String MAC_ADDRESS_REQUIRED = "sensus.epm.devicevalidator.device.mac.address.required";

	/** The Constant DEFAULT_PROCESS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_PROCESS_BCF_EXCEPTION_MSG = "sensus.epm.processbcfimpl.defaultexception";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The process summary bcf. */
	private IProcessSummaryBCF processSummaryBCF;

	/**
	 * Gets the process summary bcf.
	 * 
	 * @return the process summary bcf
	 */
	public IProcessSummaryBCF getProcessSummaryBCF()
	{
		return processSummaryBCF;
	}

	/**
	 * Sets the process summary bcf.
	 * 
	 * @param processSummaryBCF the new process summary bcf
	 */
	@Resource
	public void setProcessSummaryBCF(IProcessSummaryBCF processSummaryBCF)
	{
		this.processSummaryBCF = processSummaryBCF;
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
		setArea(getProcessSummaryBCF(), EPMAreaEnum.PROCESS);
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
		resetMocksData(getProcessSummaryBCF());
		setProcessArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test fetch demand response summary.
	 */
	@Test
	public void testFetchDemandResponseSummary()
	{
		// Validation Situation - user context and process required
		ProcessRequest request = new ProcessRequest();
		ProcessResponse response = getProcessSummaryBCF().fetchDemandResponseSummary(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_REQUIRED);

		// Validation Situation - user id, locale and process id required
		request.setUserContext(new UserContext());
		request.addProcessAsFirstElement(new DMProcess());
		response = getProcessSummaryBCF().fetchDemandResponseSummary(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED, LOCALE_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED);

		// Success situation
		request.setUserContext(TestBaseUtil.createUserContextWithLocale());
		request.addProcessAsFirstElement(TestBaseUtil.createProcess());
		response = getProcessSummaryBCF().fetchDemandResponseSummary(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_BRING_ONE_PROCESS, 1, response.getProcesses().size());
		assertNotNull(SHOULD_BRING_THE_ID, response.getProcesses().get(0).getId());

		// Error situation
		setSituation(getProcessSummaryBCF(), SituationsEnum.ERROR, IProcessSummaryBCL.class,
				FETCH_DEMAND_RESPONSE_SUMMARY);
		response = getProcessSummaryBCF().fetchDemandResponseSummary(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessSummaryBCF(), SituationsEnum.EXCEPTION, IProcessSummaryBCL.class,
				FETCH_DEMAND_RESPONSE_SUMMARY);
		response = getProcessSummaryBCF().fetchDemandResponseSummary(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch demand response program participation.
	 */
	@Test
	public void testFetchDemandResponseProgramParticipation()
	{
		// Validation Situation - process required
		ProcessRequest request = new ProcessRequest();
		ProcessResponse response = getProcessSummaryBCF().fetchDemandResponseProgramParticipation(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_REQUIRED);

		// Validation Situation - process items required
		request.addProcessAsFirstElement(new DMProcess());
		response = getProcessSummaryBCF().fetchDemandResponseProgramParticipation(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ITEMS_REQUIRED);

		// Validation Situation - device required
		request.getFirstProcess().addProcessItem(new ProcessItem());
		response = getProcessSummaryBCF().fetchDemandResponseProgramParticipation(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, HAN_DEVICES_REQUIRED);

		// Validation Situation - device mac address required
		request.getFirstProcess().getFirstProcessItem().setDevice(new HanDevice());
		response = getProcessSummaryBCF().fetchDemandResponseProgramParticipation(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, MAC_ADDRESS_REQUIRED);

		// Success situation
		((HanDevice)request.getFirstProcess().getFirstProcessItem().getDevice()).setMacAddress("macAddress");
		response = getProcessSummaryBCF().fetchDemandResponseProgramParticipation(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_BRING_FIVE_PROCESS, FIVE, response.getProcesses().size());
		assertNotNull(SHOULD_BRING_THE_ID, response.getProcesses().get(0).getId());

		// Error situation
		setSituation(getProcessSummaryBCF(), SituationsEnum.ERROR, IProcessSummaryBCL.class,
				FETCH_DEMAND_RESPONSE_PROGRAM_PARTICIPATION);
		response = getProcessSummaryBCF().fetchDemandResponseProgramParticipation(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessSummaryBCF(), SituationsEnum.EXCEPTION, IProcessSummaryBCL.class,
				FETCH_DEMAND_RESPONSE_PROGRAM_PARTICIPATION);
		response = getProcessSummaryBCF().fetchDemandResponseProgramParticipation(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch demand response setup.
	 */
	@Test
	public void testFetchDemandResponseSetup()
	{
		// Validation Situation - process required
		ProcessRequest request = new ProcessRequest();
		ProcessResponse response = getProcessSummaryBCF().fetchAllDemandResponseSetup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_REQUIRED);

		// Validation Situation - process items required
		request.addProcessAsFirstElement(new DMProcess());
		response = getProcessSummaryBCF().fetchAllDemandResponseSetup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ITEMS_REQUIRED);

		// Validation Situation - device required
		request.getFirstProcess().addProcessItem(new ProcessItem());
		response = getProcessSummaryBCF().fetchAllDemandResponseSetup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_REQUIRED);

		// Validation Situation - device flexnet id required
		request.getFirstProcess().getFirstProcessItem().setDevice(new Device());
		response = getProcessSummaryBCF().fetchAllDemandResponseSetup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, FLEXNET_ID_REQUIRED);

		// Success situation
		request.getFirstProcess().getFirstProcessItem().getDevice().setRadio(new Radio(new BigInteger("5")));
		response = getProcessSummaryBCF().fetchAllDemandResponseSetup(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_BRING_FIVE_PROCESS, FIVE, response.getProcesses().size());
		assertNotNull(SHOULD_BRING_THE_ID, response.getProcesses().get(0).getId());

		// Error situation
		setSituation(getProcessSummaryBCF(), SituationsEnum.ERROR, IProcessSummaryBCL.class,
				FETCH_ALL_DEMAND_RESPONSE_SETUP);
		response = getProcessSummaryBCF().fetchAllDemandResponseSetup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessSummaryBCF(), SituationsEnum.EXCEPTION, IProcessSummaryBCL.class,
				FETCH_ALL_DEMAND_RESPONSE_SETUP);
		response = getProcessSummaryBCF().fetchAllDemandResponseSetup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch process send han text message.
	 */
	@Test
	public void testFetchProcessSendHanTextMessage()
	{
		// Validation Situation - user context and process required
		ProcessRequest request = new ProcessRequest();
		InquiryProcessResponse response = getProcessSummaryBCF().fetchProcessSendHanTextMessage(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_REQUIRED);

		// Validation Situation - user id and process id required
		request.setUserContext(new UserContext());
		request.addProcessAsFirstElement(new DMProcess());
		response = getProcessSummaryBCF().fetchProcessSendHanTextMessage(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED, SENSUS_EPM_PROCESSVALIDATOR_PROCESS_ID_REQUIRED);

		// Success situation
		request.setUserContext(TestBaseUtil.createUserContextWithLocale());
		request.addProcessAsFirstElement(TestBaseUtil.createProcess());
		response = getProcessSummaryBCF().fetchProcessSendHanTextMessage(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_BRING_FIVE_PROCESS, FIVE, response.getProcesses().size());
		assertNotNull(SHOULD_BRING_THE_ID, response.getProcesses().get(0).getId());

		// Error situation
		setSituation(getProcessSummaryBCF(), SituationsEnum.ERROR, IProcessSummaryBCL.class,
				FETCH_PROCESS_SEND_HAN_TEXT_MESSAGE);
		response = getProcessSummaryBCF().fetchProcessSendHanTextMessage(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessSummaryBCF(), SituationsEnum.EXCEPTION, IProcessSummaryBCL.class,
				FETCH_PROCESS_SEND_HAN_TEXT_MESSAGE);
		response = getProcessSummaryBCF().fetchProcessSendHanTextMessage(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}

}
