package com.sensus.lc.process.bcf;

import static com.sensus.lc.base.TestBaseUtil.NUMBER_RANGE;
import static com.sensus.lc.base.TestBaseUtil.RANDOM;
import static com.sensus.lc.base.TestBaseUtil.createInquiryProcessRequest;
import static com.sensus.lc.base.TestBaseUtil.createLight;
import static com.sensus.lc.base.TestBaseUtil.createMLCSortExpression;
import static com.sensus.lc.base.TestBaseUtil.createProcess;
import static com.sensus.lc.base.TestBaseUtil.createProcessRequest;
import static com.sensus.lc.base.TestBaseUtil.createUserContext;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.UserContext;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.model.Column;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.CSVResponse;
import com.sensus.lc.process.bcl.IProcessBCL;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.request.InquiryProcessRequest;
import com.sensus.lc.process.model.request.ProcessCSVRequest;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.process.model.response.InquiryProcessResponse;
import com.sensus.lc.process.model.response.ProcessResponse;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class ProcessBCFTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/process/processbcfimpltest.xml"})
public class ProcessBCFTest extends AbstractTestBaseBusiness
{

	/** The Constant NUMBER_100. */
	private static final int NUMBER_100 = 100;

	/** The Constant ACTION. */
	private static final String ACTION = "Action";

	/** The Constant FILE_NAME_CSV. */
	private static final String FILE_NAME_CSV = "process.csv";

	/** The Constant ARBITRARY_NUMBER_100. */
	private static final int ARBITRARY_NUMBER_100 = 100;

	/** The Constant ARBITRARY_NUMBER_100. */
	private static final int ARBITRARY_NUMBER_25 = 25;

	/** The Constant GENERATE_FILE_CSV. */
	private static final String GENERATE_FILE_CSV = "generateFileCSV";

	/** The Constant FIELD_PROCESS_ID. */
	private static final String FIELD_PROCESS_ID = "process_id";

	/** The process bcf. */
	private IProcessBCF processBCF;

	/** The Constant DEFAULT_EXCEPTION. */
	private static final String DEFAULT_EXCEPTION = "sensus.mlc.processbcfimpl.defaultexception";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

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

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED =
			"sensus.mlc.lightvalidator.intensity.required";

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

	/** The Constant SENSUS_MLC_PROCESS_RESULT_LIGHT_REQUIRED. */
	private static final String SENSUS_MLC_PROCESS_RESULT_LIGHT_REQUIRED =
			"sensus.mlc.processvalidator.processresult.light.required";

	/** The Constant USER_CONTEXT_REQUIRED. */
	private static final String USER_CONTEXT_REQUIRED = "sensus.mlc.uservalidator.user.context.required";

	/** The Constant SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED = "sensus.mlc.page.size.required";

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
		light.getRadio().setFlexNetId(BigInteger.valueOf(ARBITRARY_NUMBER_100));
		request.setProcess(createProcess(light, null));
		ProcessResponse response = getProcessBCF().insertProcess(request);
		assertTrue(response.isOperationSuccess());

		// Validation situation - user context null
		request = new ProcessRequest();
		light = createLight();
		light.getRadio().setFlexNetId(BigInteger.valueOf(ARBITRARY_NUMBER_100));
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().insertProcess(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - user id null
		request = new ProcessRequest();
		request.setUserContext(new UserContext());
		light = createLight();
		light.getRadio().setFlexNetId(BigInteger.valueOf(ARBITRARY_NUMBER_100));
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().insertProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new ProcessRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		light = createLight();
		light.getRadio().setFlexNetId(BigInteger.valueOf(ARBITRARY_NUMBER_100));
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().insertProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		light.getRadio().setFlexNetId(BigInteger.valueOf(ARBITRARY_NUMBER_100));
		request.setProcess(createProcess(light, null));
		request.getProcess().getLcAction().setActionType(LCActionTypeEnum.DIM);
		request.getProcess().getLcAction().getActionParameters().get(0).setActionValue(null);
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		light.getRadio().setFlexNetId(BigInteger.valueOf(ARBITRARY_NUMBER_100));
		request.setProcess(createProcess(light, null));
		request.getProcess().getLcAction().setActionType(LCActionTypeEnum.TURN_OFF);
		request.getProcess().getLcAction().getActionParameters().get(0).setActionValue(null);
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		light.getRadio().setFlexNetId(BigInteger.valueOf(ARBITRARY_NUMBER_100));
		request.setProcess(createProcess(light, null));
		request.getProcess().getLcAction().setActionType(LCActionTypeEnum.TURN_ON);
		request.getProcess().getLcAction().getActionParameters().get(0).setActionValue(null);
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		light.getRadio().setFlexNetId(BigInteger.valueOf(ARBITRARY_NUMBER_100));
		request.setProcess(createProcess(light, null));
		request.getProcess().getLcAction().setActionType(LCActionTypeEnum.TURN_OFF);
		request.getProcess().getLcAction().getActionParameters().get(0).setActionValue(null);
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		light.getRadio().setFlexNetId(BigInteger.valueOf(ARBITRARY_NUMBER_100));
		request.setProcess(createProcess(light, null));
		request.getProcess().getLcAction().setActionType(LCActionTypeEnum.SET_INTENSITY_BY_GRP);
		request.getProcess().getLcAction().getActionParameters().get(1).setActionValue(null);
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		light.getRadio().setFlexNetId(BigInteger.valueOf(ARBITRARY_NUMBER_100));
		request.setProcess(createProcess(light, null));
		request.getProcess().setLcAction(null);
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_PROCESS_MLC_ACTION_REQUIRED,
				SENSUS_MLC_PROCESS_MLC_ACTION_REQUIRED);

		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		light.getRadio().setFlexNetId(BigInteger.valueOf(ARBITRARY_NUMBER_100));
		request.setProcess(createProcess(light, null));
		request.getProcess().setProcessItems(null);
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_PROCESS_MIN_ONE_PROCESS_REQUIRED);

		// Validation Situation - Light is required
		resetMocksToProcessArea();
		request = createProcessRequest();
		light = createLight();
		light.getRadio().setFlexNetId(null);
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_PROCESS_RESULT_LIGHT_REQUIRED);

		// Error situation - No Id
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		light.getRadio().setFlexNetId(BigInteger.valueOf(ARBITRARY_NUMBER_100));
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
		light.getRadio().setFlexNetId(BigInteger.valueOf(ARBITRARY_NUMBER_100));
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().insertProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createProcessRequest();
		light = createLight();
		light.getRadio().setFlexNetId(BigInteger.valueOf(ARBITRARY_NUMBER_100));
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

		// Validation situation - user context null
		request = new ProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().updateProcess(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - user id null
		request = new ProcessRequest();
		request.setUserContext(new UserContext());
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().updateProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new ProcessRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().updateProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation - Process ID is null
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		request.setProcess(process);
		response = getProcessBCF().updateProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Process END TIME is null
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		process.setEndTime(null);
		request.setProcess(process);
		response = getProcessBCF().updateProcess(request);
		assertMessages(response, SENSUS_MLC_PROCESS_ENDDATETIME_REQUIRED);

		// Validation situation - Is monitored is null
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		process.setIsMonitoredInstance(null);
		request.setProcess(process);
		response = getProcessBCF().updateProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED);

		// Validation situation - Is process complete is null
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		process.setIsProcessComplete(null);
		request.setProcess(process);
		response = getProcessBCF().updateProcess(request);
		assertMessages(response, SENSUS_MLC_PROCESS_IS_PROCESS_COMPLETE);

		// Validation situation - Is Light Intensity null
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		process.getLcAction().setActionType(LCActionTypeEnum.DIM);
		process.getLcAction().getActionParameters().get(0).setActionValue(null);
		request.setProcess(process);
		response = getProcessBCF().updateProcess(request);
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED);

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

		// Validation situation - user context null
		request = new ProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().unmonitorProcess(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - user id null
		request = new ProcessRequest();
		request.setUserContext(new UserContext());
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().unmonitorProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new ProcessRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().unmonitorProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation - Process ID null
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		request.setProcess(process);
		response = getProcessBCF().unmonitorProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

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

		// Validation situation - user context null
		request = new ProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().fetchProcessById(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - user id null
		request = new ProcessRequest();
		request.setUserContext(new UserContext());
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().fetchProcessById(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new ProcessRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().fetchProcessById(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation - Process ID null
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		request.setProcess(process);
		response = getProcessBCF().fetchProcessById(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

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

		// Validation situation - user context null
		request = new ProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchMonitoredProcesses(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - user id null
		request = new ProcessRequest();
		request.setUserContext(new UserContext());
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchMonitoredProcesses(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new ProcessRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchMonitoredProcesses(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

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
	public void testFetchAllProcess()
	{
		// Success situation
		InquiryProcessRequest request = createInquiryProcessRequest();
		request.setPageSize(ARBITRARY_NUMBER_25);
		request.addSortExpressions(createMLCSortExpression(FIELD_PROCESS_ID, false));
		InquiryProcessResponse response = getProcessBCF().fetchAllProcess(request);
		assertTrue(response.isOperationSuccess());

		// Validation situation - user context null
		request = new InquiryProcessRequest();
		request.setPageSize(ARBITRARY_NUMBER_25);
		request.addSortExpressions(createMLCSortExpression(FIELD_PROCESS_ID, false));
		response = getProcessBCF().fetchAllProcess(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - user id null
		request = new InquiryProcessRequest();
		request.setUserContext(new UserContext());
		request.setPageSize(ARBITRARY_NUMBER_25);
		request.addSortExpressions(createMLCSortExpression(FIELD_PROCESS_ID, false));
		response = getProcessBCF().fetchAllProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new InquiryProcessRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		request.setPageSize(ARBITRARY_NUMBER_25);
		request.addSortExpressions(createMLCSortExpression(FIELD_PROCESS_ID, false));
		response = getProcessBCF().fetchAllProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation Situation - Pagination size is required
		request = createInquiryProcessRequest();
		request.addSortExpressions(createMLCSortExpression(FIELD_PROCESS_ID, false));
		response = getProcessBCF().fetchAllProcess(request);
		assertMessages(response, SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED);

		// Validation Situation - Sort expression is required
		request = createInquiryProcessRequest();
		request.setPageSize(ARBITRARY_NUMBER_25);
		response = getProcessBCF().fetchAllProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createInquiryProcessRequest();
		request.setUserContext(createUserContext());
		request.setPageSize(ARBITRARY_NUMBER_25);
		request.addSortExpressions(createMLCSortExpression(FIELD_PROCESS_ID, false));
		response = getProcessBCF().fetchAllProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		request = createInquiryProcessRequest();
		request.setUserContext(createUserContext());
		request.setPageSize(ARBITRARY_NUMBER_25);
		request.addSortExpressions(createMLCSortExpression(FIELD_PROCESS_ID, false));
		response = getProcessBCF().fetchAllProcess(request);
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

		// Validation situation - user context null
		request = new ProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchProcessByTransactionId(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - user id null
		request = new ProcessRequest();
		request.setUserContext(new UserContext());
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchProcessByTransactionId(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new ProcessRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchProcessByTransactionId(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation - RNI ID null
		request = createProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		request.getProcess().setRniCorrelationId(null);
		response = getProcessBCF().fetchProcessByTransactionId(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

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

		// Validation situation - user context null
		request = new ProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().retryProcess(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - user id null
		request = new ProcessRequest();
		request.setUserContext(new UserContext());
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().retryProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new ProcessRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().retryProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation - Process ID null
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		request.setProcess(process);
		response = getProcessBCF().retryProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

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

		// Validation situation - user context null
		request = new ProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().abortProcess(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - user id null
		request = new ProcessRequest();
		request.setUserContext(new UserContext());
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().abortProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new ProcessRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().abortProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

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
		LightCriteria lightCriteria = new LightCriteria();
		lightCriteria.setLightIdList(Arrays.asList(ARBITRARY_NUMBER_100));

		LightRequest lightRequest = new LightRequest();
		lightRequest.setLightCriteria(lightCriteria);
		lightRequest.setUserContext(createUserContext());

		ProcessResponse response = getProcessBCF().fetchProcessByLight(lightRequest);
		assertTrue(response.isOperationSuccess());

		// Validation Situation - Light Criteria null
		lightRequest = new LightRequest();
		lightRequest.setUserContext(createUserContext());

		response = getProcessBCF().fetchProcessByLight(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation Situation - User Context null
		lightRequest = new LightRequest();
		lightRequest.setLightCriteria(lightCriteria);

		response = getProcessBCF().fetchProcessByLight(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation Situation - User id null
		lightRequest = new LightRequest();
		lightRequest.setLightCriteria(lightCriteria);
		lightRequest.setUserContext(new UserContext());

		response = getProcessBCF().fetchProcessByLight(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation Situation - Tenant null
		lightRequest = new LightRequest();
		lightRequest.setLightCriteria(lightCriteria);
		lightRequest.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));

		response = getProcessBCF().fetchProcessByLight(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class);

		lightRequest = new LightRequest();
		lightRequest.setLightCriteria(lightCriteria);
		lightRequest.setUserContext(createUserContext());

		response = getProcessBCF().fetchProcessByLight(lightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToProcessArea();
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class);
		lightRequest = new LightRequest();
		lightRequest.setLightCriteria(lightCriteria);
		lightRequest.setUserContext(createUserContext());

		response = getProcessBCF().fetchProcessByLight(lightRequest);
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

		// Validation situation - user context null
		request = new ProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchRniLinkStatus(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - user id null
		request = new ProcessRequest();
		request.setUserContext(new UserContext());
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchRniLinkStatus(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new ProcessRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchRniLinkStatus(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

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

		// Validation situation - user context null
		request = new ProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().updateCSVDownloaded(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - user id null
		request = new ProcessRequest();
		request.setUserContext(new UserContext());
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().updateCSVDownloaded(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new ProcessRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		light = createLight();
		process = createProcess(light, null);
		process.setId(1);
		request.setProcess(process);
		response = getProcessBCF().updateCSVDownloaded(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation - Process ID null
		request = createProcessRequest();
		light = createLight();
		process = createProcess(light, null);
		request.setProcess(process);
		response = getProcessBCF().updateCSVDownloaded(request);
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

		// Validation situation - user context null
		request = new ProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().generateSummaryFileCSV(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - user id null
		request = new ProcessRequest();
		request.setUserContext(new UserContext());
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().generateSummaryFileCSV(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new ProcessRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().generateSummaryFileCSV(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

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

		// Validation situation - user context null
		request = new ProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchCountMonitoredProcesses(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - user id null
		request = new ProcessRequest();
		request.setUserContext(new UserContext());
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchCountMonitoredProcesses(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new ProcessRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().fetchCountMonitoredProcesses(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

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

		// Validation situation - user context null
		request = new ProcessRequest();
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().insertCSVProcess(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - user id null
		request = new ProcessRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().insertCSVProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation - Tenant null
		request = new ProcessRequest();
		request.setUserContext(new UserContext());
		light = createLight();
		request.setProcess(createProcess(light, null));
		response = getProcessBCF().insertCSVProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

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
		// Success situation
		ProcessCSVRequest request = new ProcessCSVRequest();
		InquiryProcessRequest inquiryProcessRequest = createInquiryProcessRequest();
		inquiryProcessRequest.setPageSize(ARBITRARY_NUMBER_25);
		List<Column> columns = Arrays.asList(new Column(ACTION));
		inquiryProcessRequest.setListColumn(columns);
		inquiryProcessRequest.addSortExpressions(createMLCSortExpression(FIELD_PROCESS_ID, false));
		request.setProcessId(1);
		request.setFileName(FILE_NAME_CSV);
		request.setUserContext(inquiryProcessRequest.getUserContext());
		request.setInquiryProcessRequest(inquiryProcessRequest);
		CSVResponse response = getProcessBCF().generateFileCSV(request);
		assertTrue(response.isOperationSuccess());

		// Validation situation - Process ID Required
		request = new ProcessCSVRequest();
		inquiryProcessRequest = createInquiryProcessRequest();
		columns = Arrays.asList(new Column(ACTION));
		inquiryProcessRequest.setListColumn(columns);
		inquiryProcessRequest.setPageSize(ARBITRARY_NUMBER_25);
		inquiryProcessRequest.addSortExpressions(createMLCSortExpression(FIELD_PROCESS_ID, false));
		request.setFileName(FILE_NAME_CSV);
		request.setUserContext(inquiryProcessRequest.getUserContext());
		request.setInquiryProcessRequest(inquiryProcessRequest);
		response = getProcessBCF().generateFileCSV(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - CSV File Name Required
		request = new ProcessCSVRequest();
		inquiryProcessRequest = createInquiryProcessRequest();
		columns = Arrays.asList(new Column(ACTION));
		inquiryProcessRequest.setListColumn(columns);
		inquiryProcessRequest.setPageSize(ARBITRARY_NUMBER_25);
		inquiryProcessRequest.addSortExpressions(createMLCSortExpression(FIELD_PROCESS_ID, false));
		request.setProcessId(1);
		request.setUserContext(inquiryProcessRequest.getUserContext());
		request.setInquiryProcessRequest(inquiryProcessRequest);
		response = getProcessBCF().generateFileCSV(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_NAME_REQUIRED);

		// Validation situation - Page size null
		request = new ProcessCSVRequest();
		inquiryProcessRequest = createInquiryProcessRequest();
		columns = Arrays.asList(new Column(ACTION));
		inquiryProcessRequest.setListColumn(columns);
		request.setProcessId(1);
		inquiryProcessRequest.addSortExpressions(createMLCSortExpression(FIELD_PROCESS_ID, false));
		request.setFileName(FILE_NAME_CSV);
		request.setUserContext(inquiryProcessRequest.getUserContext());
		request.setInquiryProcessRequest(inquiryProcessRequest);
		response = getProcessBCF().generateFileCSV(request);
		assertMessages(response, SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED);

		// Validation situation - Sort expression null
		request = new ProcessCSVRequest();
		inquiryProcessRequest = createInquiryProcessRequest();
		columns = Arrays.asList(new Column(ACTION));
		inquiryProcessRequest.setPageSize(ARBITRARY_NUMBER_25);
		inquiryProcessRequest.setListColumn(columns);
		request.setProcessId(1);
		request.setFileName(FILE_NAME_CSV);
		request.setUserContext(inquiryProcessRequest.getUserContext());
		request.setInquiryProcessRequest(inquiryProcessRequest);
		response = getProcessBCF().generateFileCSV(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation - User context null
		request = new ProcessCSVRequest();
		inquiryProcessRequest = createInquiryProcessRequest();
		inquiryProcessRequest.setUserContext(null);
		inquiryProcessRequest.setPageSize(ARBITRARY_NUMBER_25);
		inquiryProcessRequest.addSortExpressions(createMLCSortExpression(FIELD_PROCESS_ID, false));
		columns = Arrays.asList(new Column(ACTION));
		inquiryProcessRequest.setListColumn(columns);
		request.setProcessId(1);
		request.setFileName(FILE_NAME_CSV);
		request.setInquiryProcessRequest(inquiryProcessRequest);
		response = getProcessBCF().generateFileCSV(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - User ID null
		request = new ProcessCSVRequest();
		inquiryProcessRequest = createInquiryProcessRequest();
		inquiryProcessRequest.setUserContext(new UserContext());
		inquiryProcessRequest.setPageSize(ARBITRARY_NUMBER_25);
		inquiryProcessRequest.addSortExpressions(createMLCSortExpression(FIELD_PROCESS_ID, false));
		columns = Arrays.asList(new Column(ACTION));
		inquiryProcessRequest.setListColumn(columns);
		request.setProcessId(1);
		request.setFileName(FILE_NAME_CSV);
		request.setUserContext(inquiryProcessRequest.getUserContext());
		request.setInquiryProcessRequest(inquiryProcessRequest);
		response = getProcessBCF().generateFileCSV(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new ProcessCSVRequest();
		inquiryProcessRequest = createInquiryProcessRequest();
		inquiryProcessRequest.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		inquiryProcessRequest.setPageSize(ARBITRARY_NUMBER_25);
		inquiryProcessRequest.addSortExpressions(createMLCSortExpression(FIELD_PROCESS_ID, false));
		columns = Arrays.asList(new Column(ACTION));
		inquiryProcessRequest.setListColumn(columns);
		request.setProcessId(1);
		request.setFileName(FILE_NAME_CSV);
		request.setUserContext(inquiryProcessRequest.getUserContext());
		request.setInquiryProcessRequest(inquiryProcessRequest);
		response = getProcessBCF().generateFileCSV(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Error situation
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class,
				GENERATE_FILE_CSV);
		request = new ProcessCSVRequest();
		inquiryProcessRequest = createInquiryProcessRequest();
		columns = Arrays.asList(new Column(ACTION));
		inquiryProcessRequest.setListColumn(columns);
		inquiryProcessRequest.setPageSize(ARBITRARY_NUMBER_25);
		request.setProcessId(1);
		inquiryProcessRequest.addSortExpressions(createMLCSortExpression(FIELD_PROCESS_ID, false));
		request.setFileName(FILE_NAME_CSV);
		request.setUserContext(inquiryProcessRequest.getUserContext());
		request.setInquiryProcessRequest(inquiryProcessRequest);
		response = getProcessBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class,
				GENERATE_FILE_CSV);
		request = new ProcessCSVRequest();
		inquiryProcessRequest = createInquiryProcessRequest();
		columns = Arrays.asList(new Column(ACTION));
		inquiryProcessRequest.setListColumn(columns);
		inquiryProcessRequest.setPageSize(ARBITRARY_NUMBER_25);
		request.setProcessId(1);
		inquiryProcessRequest.addSortExpressions(createMLCSortExpression(FIELD_PROCESS_ID, false));
		request.setFileName(FILE_NAME_CSV);
		request.setUserContext(inquiryProcessRequest.getUserContext());
		request.setInquiryProcessRequest(inquiryProcessRequest);
		response = getProcessBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_BCL_EXCEPTION);
	}

	/**
	 * Test fetch summary by process id.
	 */
	@Test
	public void testFetchSummaryByProcessId()
	{
		// Success situation
		ProcessRequest request = TestBaseUtil.createProcessRequest();
		request.setProcess(new Process());
		request.getProcess().setId(NUMBER_100);

		ProcessResponse response = getProcessBCF().fetchSummaryByProcessId(request);
		assertTrue(response.isOperationSuccess());

		// Validation situation - request without process
		request = TestBaseUtil.createProcessRequest();

		response = getProcessBCF().fetchSummaryByProcessId(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation - request without user context
		request = new ProcessRequest();

		response = getProcessBCF().fetchSummaryByProcessId(request);
		response = getProcessBCF().insertProcess(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - request without user ID
		request = new ProcessRequest();
		request.setUserContext(new UserContext());

		response = getProcessBCF().fetchSummaryByProcessId(request);
		response = getProcessBCF().insertProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - request without tenant
		request = new ProcessRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));

		response = getProcessBCF().fetchSummaryByProcessId(request);
		response = getProcessBCF().insertProcess(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Error situation
		setSituation(getProcessBCF(), SituationsEnum.ERROR, IProcessBCL.class,
				"fetchSummaryByProcessId");

		request = TestBaseUtil.createProcessRequest();
		request.setProcess(new Process());
		request.getProcess().setId(NUMBER_100);

		response = getProcessBCF().fetchSummaryByProcessId(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		setSituation(getProcessBCF(), SituationsEnum.EXCEPTION, IProcessBCL.class,
				"fetchSummaryByProcessId");

		request = TestBaseUtil.createProcessRequest();
		request.setProcess(new Process());
		request.getProcess().setId(NUMBER_100);

		response = getProcessBCF().fetchSummaryByProcessId(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION);

	}
}
