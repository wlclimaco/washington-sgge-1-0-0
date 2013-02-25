package com.sensus.mlc.process.bcf;

import static com.sensus.mlc.base.TestBaseUtil.createInquiryProcessRequest;
import static com.sensus.mlc.base.TestBaseUtil.createLight;
import static com.sensus.mlc.base.TestBaseUtil.createLightRequest;
import static com.sensus.mlc.base.TestBaseUtil.createProcess;
import static com.sensus.mlc.base.TestBaseUtil.createProcessRequest;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.InquiryProcessResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.response.TenantResponse;

/**
 * The Class ProcessBCFTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/process/processbcfimpltest.xml"})
public class ProcessBCFTest extends AbstractTestBaseBusiness
{

	/** The Constant GENERATE_FILE_CSV. */
	private static final String GENERATE_FILE_CSV = "generateFileCSV";

	/** The process bcf. */
	private IProcessBCF processBCF;

	/** The Constant DEFAULT_EXCEPTION. */
	private static final String DEFAULT_EXCEPTION = "sensus.mlc.processbcfimpl.defaultexception";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED =
			"sensus.mlc.smartpointvalidator.id.required";

	/** The Constant SENSUS_MLC_PROCESS_ENDDATETIME_REQUIRED. */
	private static final String SENSUS_MLC_PROCESS_ENDDATETIME_REQUIRED =
			"sensus.mlc.processvalidator.end.date.time.required";

	/** The Constant SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED =
			"sensus.mlc.validator.is.monitored.required";

	/** The Constant SENSUS_MLC_PROCESS_IS_PROCESS_COMPLETE. */
	private static final String SENSUS_MLC_PROCESS_IS_PROCESS_COMPLETE =
			"sensus.mlc.processvalidator.is.process.complete.required";

	/** The Constant DEFAULT_BCL_EXCEPTION. */
	private static final String DEFAULT_BCL_EXCEPTION = "sensus.mlc.processbclimpl.defaultexception";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED =
			"sensus.mlc.smartpointvalidator.intensity.required";

	/** The Constant SENSUS_MLC_PROCESS_MLC_ACTION_REQUIRED. */
	private static final String SENSUS_MLC_PROCESS_MLC_ACTION_REQUIRED =
			"sensus.mlc.processvalidator.mlc.action.required";

	/** The Constant SENSUS_MLC_PROCESS_MIN_ONE_PROCESS_REQUIRED. */
	private static final String SENSUS_MLC_PROCESS_MIN_ONE_PROCESS_REQUIRED =
			"sensus.mlc.processvalidator.min.one.process.required";

	/** The Constant SENSUS_MLC_VALIDATOR_NAME_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_NAME_REQUIRED = "sensus.mlc.validator.name.required";

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

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
	@Resource(name = "processBCFTarget")
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	/**
	 * Sets the processs area.
	 */
	public void setProcessArea()
	{
		setArea(getProcessBCF(), LCAreaEnum.PROCESS);
	}

	/**
	 * Removes the process area.
	 */
	@After
	public void removeProcessArea()
	{
		setArea(getProcessBCF(), LCAreaEnum.DEFAULT);
	}

	/**
	 * Removes the eco mode area.
	 */
	@After
	public void resetMocksToProcessArea()
	{
		resetMocksData(getProcessBCF());
		setProcessArea();
	}

	/**
	 * Test insert process.
	 */
	@Test
	public void testInsertProcess()
	{
		// Success situation
		ProcessRequest request = createProcessRequest();
		Light light = createLight();
		request.setProcess(createProcess(light, null));
		ProcessResponse response = getProcessBCF().insertProcess(request);
		assertTrue(response.isOperationSuccess());

		// Validation situation
		request = new ProcessRequest();
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		request.getProcess().getLcAction().setActionType(LCActionTypeEnum.DIM);
		request.getProcess().getLcAction().getActionParameters().get(0).setActionValue(null);
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		request.getProcess().getLcAction().setActionType(LCActionTypeEnum.TURN_OFF);
		request.getProcess().getLcAction().getActionParameters().get(0).setActionValue(null);
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		request.getProcess().getLcAction().setActionType(LCActionTypeEnum.TURN_ON);
		request.getProcess().getLcAction().getActionParameters().get(0).setActionValue(null);
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		request.getProcess().getLcAction().setActionType(LCActionTypeEnum.TURN_OFF);
		request.getProcess().getLcAction().getActionParameters().get(0).setActionValue(null);
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		request.getProcess().getLcAction().setActionType(LCActionTypeEnum.SET_INTENSITY_BY_GRP);
		request.getProcess().getLcAction().getActionParameters().get(1).setActionValue(null);
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		request.getProcess().setLcAction(null);
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_PROCESS_MLC_ACTION_REQUIRED,
				SENSUS_MLC_PROCESS_MLC_ACTION_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		request.getProcess().setProcessItems(null);
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_PROCESS_MIN_ONE_PROCESS_REQUIRED);

		// Error situation - No Id
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		Process process = createProcess(light, null);
		process.setId(null);
		request.setProcess(process);
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);
	}

	/**
	 * Test update process.
	 */
	@Test
	public void testUpdateProcess()
	{
		// Success situation
		ProcessRequest request = createProcessRequest();
		Light light = createLight();
		Process process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		ProcessResponse response = getProcessBCF().updateProcess(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().updateProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Validation situation
		request = new ProcessRequest();
		response = getProcessBCF().updateProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(null);
		request.setProcess(process);
		response = getProcessBCF().updateProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setEndTime(null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().updateProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_PROCESS_ENDDATETIME_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setIsMonitoredInstance(null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().updateProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setIsProcessComplete(null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().updateProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_PROCESS_IS_PROCESS_COMPLETE);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setIsProcessComplete(null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().updateProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_PROCESS_IS_PROCESS_COMPLETE);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setEndTime(null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().updateProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_PROCESS_ENDDATETIME_REQUIRED);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().updateProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);

	}

	/**
	 * Test unmonitor process.
	 */
	@Test
	public void testUnmonitorProcess()
	{

		// Success situation
		ProcessRequest request = createProcessRequest();
		Light light = createLight();
		Process process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		ProcessResponse response = getProcessBCF().unmonitorProcess(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createProcessRequest();
		request.setProcess(process);
		response = getProcessBCF().unmonitorProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Validation situation
		request = new ProcessRequest();
		response = getProcessBCF().unmonitorProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(null);
		request.setProcess(process);
		response = getProcessBCF().unmonitorProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createProcessRequest();
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().unmonitorProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);
	}

	/**
	 * Test fetch by id process.
	 */
	@Test
	public void testFetchByIdProcess()
	{

		// Success situation
		ProcessRequest request = createProcessRequest();
		Light light = createLight();
		Process process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		ProcessResponse response = getProcessBCF().fetchProcessById(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createProcessRequest();
		request.setProcess(process);
		response = getProcessBCF().fetchProcessById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Validation situation
		request = new ProcessRequest();
		response = getProcessBCF().fetchProcessById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(null);
		request.setProcess(process);
		response = getProcessBCF().fetchProcessById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createProcessRequest();
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().fetchProcessById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);

	}

	/**
	 * Test fetch monitored process.
	 */
	@Test
	public void testFetchMonitoredProcess()
	{

		// Success situation
		ProcessRequest request = createProcessRequest();
		Light light = createLight();
		request.setProcess(createProcess(light, null));
		ProcessResponse response = getProcessBCF().fetchMonitoredProcesses(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchMonitoredProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Validation situation
		request = new ProcessRequest();
		response = getProcessBCF().fetchMonitoredProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchMonitoredProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);

	}

	/**
	 * Test fetch process.
	 */
	@Test
	public void testFetchProcess()
	{

		// Success situation
		InquiryProcessRequest request = createInquiryProcessRequest();
		InquiryProcessResponse response = getProcessBCF().fetchProcesses(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createInquiryProcessRequest();
		response = getProcessBCF().fetchProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Validation situation
		request = new InquiryProcessRequest();
		response = getProcessBCF().fetchProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createInquiryProcessRequest();
		response = getProcessBCF().fetchProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);

	}

	/**
	 * Test fetch process by transaction id.
	 */
	@Test
	public void testFetchProcessByTransactionId()
	{
		// Success situation
		ProcessRequest request = createProcessRequest();
		Light light = createLight();
		request.setProcess(createProcess(light, null));
		ProcessResponse response = getProcessBCF().fetchProcessByTransactionId(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchProcessByTransactionId(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Validation situation
		request = new ProcessRequest();
		response = getProcessBCF().fetchProcessByTransactionId(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		request.getUserContext().setTenant(null);
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchProcessByTransactionId(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchProcessByTransactionId(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);
	}

	/**
	 * Test retry process.
	 */
	@Test
	public void testRetryProcess()
	{
		// Success situation
		ProcessRequest request = createProcessRequest();
		Light light = createLight();
		Process process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		ProcessResponse response = getProcessBCF().retryProcess(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createProcessRequest();
		request.setProcess(process);
		response = getProcessBCF().retryProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Validation situation
		request = new ProcessRequest();
		response = getProcessBCF().retryProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(null);
		request.setProcess(process);
		response = getProcessBCF().retryProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().retryProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);

	}

	/**
	 * Test abort process.
	 */
	@Test
	public void testAbortProcess()
	{

		// Success situation
		ProcessRequest request = createProcessRequest();
		Light light = createLight();
		Process process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		ProcessResponse response = getProcessBCF().abortProcess(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createProcessRequest();
		request.setProcess(process);
		response = getProcessBCF().abortProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Validation situation
		request = new ProcessRequest();
		response = getProcessBCF().abortProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(null);
		request.setProcess(process);
		response = getProcessBCF().abortProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createProcessRequest();
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().abortProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);

	}

	/**
	 * Test fetch process by light.
	 */
	@Test
	public void testFetchProcessByLight()
	{
		// Success situation
		LightRequest request = createLightRequest();
		request.setLights(new ArrayList<Light>());
		Light light = createLight();
		light.setId(1);
		request.getLights().add(light);
		ProcessResponse response = getProcessBCF().fetchProcessByLight(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request.setLights(new ArrayList<Light>());
		request.getLights().add(light);
		response = getProcessBCF().fetchProcessByLight(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Validation situation
		request = new LightRequest();
		response = getProcessBCF().fetchProcessByLight(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		resetMocksToProcessArea();
		request = createLightRequest();
		request.setLights(new ArrayList<Light>());
		light = createLight();
		light.setId(null);
		request.getLights().add(light);
		response = getProcessBCF().fetchProcessByLight(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createLightRequest();
		request.setLights(new ArrayList<Light>());
		light = createLight();
		request.getLights().add(light);
		response = getProcessBCF().fetchProcessByLight(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);

	}

	/**
	 * Test fetch rni link status.
	 */
	@Test
	public void testFetchRniLinkStatus()
	{

		// Success situation
		ProcessRequest request = createProcessRequest();
		Light light = createLight();
		request.setProcess(createProcess(light, null));
		ProcessResponse response = getProcessBCF().fetchRniLinkStatus(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchRniLinkStatus(request);
		assertFalse(response.isOperationSuccess());

		// Validation situation
		request = new ProcessRequest();
		response = getProcessBCF().fetchRniLinkStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchRniLinkStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);

	}

	/**
	 * Test update csv downloaded.
	 */
	@Test
	public void testUpdateCSVDownloaded()
	{
		// Success situation
		ProcessRequest request = createProcessRequest();
		Light light = createLight();
		Process process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		ProcessResponse response = getProcessBCF().updateCSVDownloaded(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createProcessRequest();
		request.setProcess(process);
		response = getProcessBCF().updateCSVDownloaded(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Validation situation
		request = new ProcessRequest();
		response = getProcessBCF().updateCSVDownloaded(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(null);
		request.setProcess(process);
		response = getProcessBCF().updateCSVDownloaded(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createProcessRequest();
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().updateCSVDownloaded(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);

	}

	/**
	 * Test fetch tenant by rni code.
	 */
	@Test
	public void testFetchTenantByRniCode()
	{
		// Success situation
		ProcessRequest request = createProcessRequest();
		Light light = createLight();
		request.setProcess(createProcess(light, null));
		TenantResponse response = getProcessBCF().fetchTenantByRniCode(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchTenantByRniCode(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Validation situation
		request = new ProcessRequest();
		response = getProcessBCF().fetchTenantByRniCode(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchTenantByRniCode(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);
	}

	/**
	 * Test generate summary file csv.
	 */
	@Test
	public void testGenerateSummaryFileCSV()
	{
		// Success situation
		ProcessRequest request = createProcessRequest();
		Light light = createLight();
		request.setProcess(createProcess(light, null));
		ProcessResponse response = getProcessBCF().generateSummaryFileCSV(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().generateSummaryFileCSV(request);
		assertFalse(response.isOperationSuccess());

		// Validation situation
		request = new ProcessRequest();
		response = getProcessBCF().generateSummaryFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().generateSummaryFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);

	}

	/**
	 * Test fetch count monitored processes.
	 */
	@Test
	public void testFetchCountMonitoredProcesses()
	{
		// Success situation
		ProcessRequest request = createProcessRequest();
		request.getUserContext().setTenant(new Tenant(1, "acme", "ACME Corporation LC Demo", "ACME"));
		Light light = createLight();
		request.setProcess(createProcess(light, null));
		ProcessResponse response = getProcessBCF().fetchCountMonitoredProcesses(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchCountMonitoredProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Validation situation
		request = new ProcessRequest();
		response = getProcessBCF().fetchCountMonitoredProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchCountMonitoredProcesses(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);

	}

	/**
	 * Test insert csv process.
	 */
	@Test
	public void testInsertCSVProcess()
	{
		// Success situation
		ProcessRequest request = createProcessRequest();
		Light light = createLight();
		request.setProcess(createProcess(light, null));
		ProcessResponse response = getProcessBCF().insertCSVProcess(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().insertCSVProcess(request);
		assertFalse(response.isOperationSuccess());

		// Validation situation
		request = new ProcessRequest();
		response = getProcessBCF().insertCSVProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().insertCSVProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_BCL_EXCEPTION);
	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateFileCSV()
	{
		// Validation situation - Process ID Required
		InquiryProcessRequest request = createInquiryProcessRequest();
		InquiryProcessResponse response = getProcessBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - CSV File Name Required
		request.setProcessId(1);
		response = getProcessBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_NAME_REQUIRED);

		// Success situation
		request.setFileName("csvFileName");
		response = getProcessBCF().generateFileCSV(request);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response.getFileName());

		// Error situation
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class,
				GENERATE_FILE_CSV);
		response = getProcessBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class,
				GENERATE_FILE_CSV);
		response = getProcessBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_BCL_EXCEPTION);
	}
}
