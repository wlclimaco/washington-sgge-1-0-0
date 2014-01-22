package com.sensus.lc.process.bcl;

import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.lc.base.TestBaseUtil.createInquiryProcessRequest;
import static com.sensus.lc.base.TestBaseUtil.createLight;
import static com.sensus.lc.base.TestBaseUtil.createMLCSortExpression;
import static com.sensus.lc.base.TestBaseUtil.createProcess;
import static com.sensus.lc.base.TestBaseUtil.createProcessRequest;
import static com.sensus.lc.base.TestBaseUtil.createTenant;
import static com.sensus.lc.base.TestBaseUtil.createUser;
import static com.sensus.lc.base.TestBaseUtil.createUserContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.light.bcl.ILightBCL;
import com.sensus.lc.light.bcl.ILightCSVBCL;
import com.sensus.lc.light.model.Column;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.CSVInternalResponse;
import com.sensus.lc.process.dac.IProcessDAC;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.LCActionParameter;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessItem;
import com.sensus.lc.process.model.ProcessItemStatusEnum;
import com.sensus.lc.process.model.ProcessStatusReasonEnum;
import com.sensus.lc.process.model.request.InquiryProcessRequest;
import com.sensus.lc.process.model.request.ProcessCSVRequest;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.process.model.response.ProcessResponse;
import com.sensus.lc.schedule.bcl.IScheduleBCL;
import com.sensus.lc.server.bcf.IMlcServerBCF;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.user.model.User;

/**
 * The Class ProcessBCLTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/process/processbclimpltest.xml"})
public class ProcessBCLTest extends AbstractTestBaseBusiness
{

	private static final String PROCESS_DELETION_SCHEDULE = "processDeletionSchedule";

	private static final String PROCESS_ADDITION_SMARTPOINT_SCHEDULE = "processAdditionSmartpointSchedule";

	private static final String PROCESS_DELETION_SMARTPOINT_SCHEDULE = "processDeletionSmartpointSchedule";

	private static final String OFFSET = "OFFSET";

	private static final String EVENT = "EVENT";

	private static final String PROCESS_ID = "process_id";

	/** The Constant TIME_ZONE. */
	private static final String TIME_ZONE = "US/Eastern";

	/** The Constant FILE_NAME_CSV. */
	private static final String FILE_NAME_CSV = "file.csv";

	/** The Constant ACTION. */
	private static final String ACTION = "Action";

	/** The Constant ARBITRARY_NUMBER_100. */
	private static final int ARBITRARY_NUMBER_100 = 100;

	/** The Constant FETCH_PROCESS_BY_ID. */
	private static final String FETCH_PROCESS_BY_ID = "fetchProcessById";

	/** The Constant INSERT_PROCESS. */
	private static final String INSERT_PROCESS = "insertProcess";

	/** The Constant UPDATE_PROCESS. */
	private static final String UPDATE_PROCESS = "updateProcess";

	/** The process bcl. */
	private IProcessBCL processBCL;

	/** The Constant SENSUS_MLC_PROCESSVALIDATOR_PROCESS_ALREADY_RETRY. */
	private static final String SENSUS_MLC_PROCESSVALIDATOR_PROCESS_ALREADY_RETRY =
			"sensus.mlc.processvalidator.process.already.retry";

	/** The Constant RNI_OFFLINE_EXCEPTION. */
	private static final String RNI_OFFLINE_EXCEPTION = "sensus.mlc.processbclimpl.rniofflineexception";

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
		ProcessRequest processRequest = createProcessRequest();
		InternalResultsResponse<Process> response =
				getProcessBCL().fetchProcessByFileName(processRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "fetchProcessByFileName");
		response = getProcessBCL().fetchProcessByFileName(processRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch process by id.
	 */
	@Test
	public void testFetchProcessById()
	{
		setTestControl(getProcessBCL(), IProcessDAC.class, "");

		// Success situation
		ProcessRequest processRequest = createProcessRequest();
		processRequest.setProcess(createProcess(createLight(), null));
		InternalResultsResponse<Process> response =
				getProcessBCL().fetchProcessById(processRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, FETCH_PROCESS_BY_ID);
		response = getProcessBCL().fetchProcessById(processRequest);
		assertMessages(response, ERROR_CODE);
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
		getProcessBCL().setGatewayActive(createTenant(), true);
		getProcessBCL().setGatewayActive(createTenant(), false);
	}

	/**
	 * Test fetch rni link status.
	 */
	@Test
	public void testFetchRniLinkStatus()
	{
		// Success situation
		Tenant tenant = createTenant();
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
		ProcessRequest processRequest = createProcessRequest();
		InternalResultsResponse<Process> response =
				getProcessBCL().fetchMonitoredProcesses(processRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "fetchMonitoredProcesses");
		response = getProcessBCL().fetchMonitoredProcesses(processRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch processes.
	 */
	@Test
	public void testFetchAllProcess()
	{
		// Success situation
		InquiryProcessRequest processRequest = createInquiryProcessRequest();
		InternalResultsResponse<Process> response = getProcessBCL().fetchAllProcess(processRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "fetchAllProcess");
		response = getProcessBCL().fetchAllProcess(processRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test insert process.
	 */
	@Test
	public void testInsertProcess()
	{
		// Success situation
		ProcessRequest processRequest = createProcessRequest();
		processRequest.setProcess(createProcess(createLight(), null));
		processRequest.getProcess().setIsMonitoredInstance(null);
		InternalResultsResponse<Process> response = getProcessBCL().insertProcess(processRequest);
		assertResultResponse(response);
		processRequest.getProcess().setIsMonitoredInstance(true);
		response = getProcessBCL().insertProcess(processRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, INSERT_PROCESS);
		processRequest = createProcessRequest();
		response = getProcessBCL().insertProcess(processRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test unmonitor process.
	 */
	@Test
	public void testUnmonitorProcess()
	{
		// Success situation
		ProcessRequest processRequest = createProcessRequest();
		InternalResponse response = getProcessBCL().unmonitorProcess(processRequest);
		assertResponse(response);

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "unmonitorProcess");
		response = getProcessBCL().unmonitorProcess(processRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test update process.
	 */
	@Test
	public void testUpdateProcess()
	{
		// Success situation
		ProcessRequest processRequest = createProcessRequest();
		InternalResponse response = getProcessBCL().updateProcess(processRequest);
		assertResponse(response);

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, UPDATE_PROCESS);
		response = getProcessBCL().updateProcess(processRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch process by rni id.
	 */
	@Test
	public void testFetchProcessByRniId()
	{
		// Success situation
		ProcessRequest processRequest = createProcessRequest();
		InternalResultsResponse<Process> response = getProcessBCL().fetchProcessByRniId(processRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "fetchProcessByRniId");
		response = getProcessBCL().fetchProcessByRniId(processRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test retry process.
	 */
	@Test
	public void testRetryProcess()
	{
		setTestControl(getProcessBCL(), IMlcServerBCF.class, "ALREADY_RETRY");

		// Success situation
		ProcessRequest processRequest = createProcessRequest();
		InternalResponse response = getProcessBCL().retryProcess(processRequest);
		assertResponse(response);

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, FETCH_PROCESS_BY_ID);
		response = getProcessBCL().retryProcess(processRequest);
		assertMessages(response, ERROR_CODE);

		processRequest.getUserContext().getTenant().setId(null);
		response = getProcessBCL().retryProcess(processRequest);
		assertEquals(InternalResponse.Status.ExceptionError, response.getStatus());

		// Success situation - Process already retry
		resetMocksToProcessArea();
		setTestControl(getProcessBCL(), IProcessDAC.class, "ALREADY_RETRY");

		processRequest = createProcessRequest();
		response = getProcessBCL().retryProcess(processRequest);
		assertTrue(response.isInError());
		assertMessages(response, SENSUS_MLC_PROCESSVALIDATOR_PROCESS_ALREADY_RETRY);

		// Situation - Simulate the RNI OFFLINE (Using a different tenant = PECO)
		resetMocksToProcessArea();
		setTestControl(getProcessBCL(), IProcessDAC.class, "");
		processRequest = new ProcessRequest();

		// Create a new user context with the TENANT = PECO
		User user = createUser();
		UserContext userContext = new UserContext();
		userContext.setId(user.getId());
		userContext.setUserId(user.getUserName());

		// Create a new tenant PECO
		Tenant tenant = new Tenant(1, "peco", "PECO Corporation LC Demo", "PECO",
				"http://localhost:8083/gateway-mlc/mlc-gateway-ws/");

		userContext.setTenant(tenant);
		processRequest.setUserContext(userContext);

		response = getProcessBCL().retryProcess(processRequest);
		assertResponse(response);

		// Create a new user context with the TENANT = TEST
		user = createUser();
		userContext = new UserContext();
		userContext.setId(user.getId());
		userContext.setUserId(user.getUserName());

		// Create a new tenant PECO
		tenant = new Tenant(1, "TEST", "TEST Corporation LC Demo", "TEST",
				"http://localhost:8083/gateway-mlc/mlc-gateway-ws/");

		userContext.setTenant(tenant);
		processRequest.setUserContext(userContext);

		response = getProcessBCL().retryProcess(processRequest);
		assertResponse(response);

	}

	/**
	 * Test abort process.
	 */
	@Test
	public void testAbortProcess()
	{
		// Success situation
		ProcessRequest processRequest = createProcessRequest();
		getProcessBCL().setGatewayActive(createTenant(), true);
		InternalResponse response = getProcessBCL().abortProcess(processRequest);
		assertResponse(response);

		processRequest.setProcessStatusReasonEnum(ProcessStatusReasonEnum.ABORTED);
		response = getProcessBCL().abortProcess(processRequest);
		assertResponse(response);

	}

	/**
	 * Test submit process setup dimming configuration.
	 */
	@Test
	public void testSubmitProcessSetupDimmingConfiguration()
	{
		ProcessRequest processRequest = createProcessRequest();
		processRequest.addSortExpressions(createMLCSortExpression(PROCESS_ID, false));

		// Success situation - LCActionTypeEnum.SETUP_DIMMING_CONFIGURATION
		LCAction action = new LCAction(LCActionTypeEnum.SETUP_DIMMING_CONFIGURATION);
		action.addActionParameter(new LCActionParameter(PropertyEnum.DIM_ON_DELAY, "0"));

		action.addActionParameter(new LCActionParameter(PropertyEnum.HARDWARE_SETTING_CONFIGURATION_1, "4"));
		action.addActionParameter(new LCActionParameter(PropertyEnum.CURRENT_SCALE_CONFIGURATION_1, "12"));
		action.addActionParameter(new LCActionParameter(PropertyEnum.FULL_ON_REQUIRED_CONFIGURATION_1, "0"));

		action.addActionParameter(new LCActionParameter(PropertyEnum.HARDWARE_SETTING_CONFIGURATION_2, "4"));
		action.addActionParameter(new LCActionParameter(PropertyEnum.CURRENT_SCALE_CONFIGURATION_2, "12"));
		action.addActionParameter(new LCActionParameter(PropertyEnum.FULL_ON_REQUIRED_CONFIGURATION_2, "0"));

		action.addActionParameter(new LCActionParameter(PropertyEnum.HARDWARE_SETTING_CONFIGURATION_3, "10"));
		action.addActionParameter(new LCActionParameter(PropertyEnum.CURRENT_SCALE_CONFIGURATION_3, "34"));
		action.addActionParameter(new LCActionParameter(PropertyEnum.FULL_ON_REQUIRED_CONFIGURATION_3, "0"));

		action.addActionParameter(new LCActionParameter(PropertyEnum.HARDWARE_SETTING_CONFIGURATION_4, "3"));
		action.addActionParameter(new LCActionParameter(PropertyEnum.CURRENT_SCALE_CONFIGURATION_4, "46"));
		action.addActionParameter(new LCActionParameter(PropertyEnum.FULL_ON_REQUIRED_CONFIGURATION_4, "0"));

		action.addActionParameter(new LCActionParameter(PropertyEnum.HARDWARE_SETTING_CONFIGURATION_5, "1"));
		action.addActionParameter(new LCActionParameter(PropertyEnum.CURRENT_SCALE_CONFIGURATION_5, "53"));
		action.addActionParameter(new LCActionParameter(PropertyEnum.FULL_ON_REQUIRED_CONFIGURATION_5, "0"));

		action.addActionParameter(new LCActionParameter(PropertyEnum.HARDWARE_SETTING_CONFIGURATION_6, "0"));
		action.addActionParameter(new LCActionParameter(PropertyEnum.CURRENT_SCALE_CONFIGURATION_6, "63"));
		action.addActionParameter(new LCActionParameter(PropertyEnum.FULL_ON_REQUIRED_CONFIGURATION_6, "0"));

		InternalResultsResponse<Process> response = getProcessBCL().submitProcess(processRequest, action);
		assertResponse(response);
	}

	@Test
	public void testSubmitProcessDeletionSchedule()
	{
		ProcessRequest processRequest = createProcessRequest();
		processRequest.addSortExpressions(createMLCSortExpression(PROCESS_ID, false));

		// Success situation - LCActionTypeEnum.DEL_SCHEDULE to EVENT
		setTestControl(SensusAppContext.getBean(PROCESS_DELETION_SCHEDULE), IScheduleBCL.class, EVENT);
		LCAction action = new LCAction(LCActionTypeEnum.DEL_SCHEDULE);
		InternalResultsResponse<Process> response = getProcessBCL().submitProcess(processRequest, action);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.DEL_SCHEDULE to OFFSET
		setTestControl(SensusAppContext.getBean(PROCESS_DELETION_SCHEDULE), IScheduleBCL.class, OFFSET);
		action = new LCAction(LCActionTypeEnum.DEL_SCHEDULE);
		response = getProcessBCL().submitProcess(processRequest, action);
		assertResponse(response);
	}

	@Test
	public void testSubmitProcessSetupLightIntensity()
	{
		ProcessRequest processRequest = createProcessRequest();
		processRequest.addSortExpressions(createMLCSortExpression(PROCESS_ID, false));

		// Success situation - LCActionTypeEnum.DIM
		LCAction action = new LCAction(LCActionTypeEnum.DIM);
		action.addActionParameter(new LCActionParameter(PropertyEnum.LIGHT_INTENSITY, "0"));
		InternalResultsResponse<Process> response = getProcessBCL().submitProcess(processRequest, action);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.SET_BLINK_BY_LIGHT
		action.setActionType(LCActionTypeEnum.SET_BLINK_BY_LIGHT);
		action.addActionParameter(new LCActionParameter(PropertyEnum.LIGHT_BLINK, "1"));
		response = getProcessBCL().submitProcess(processRequest, action);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.SET_CLEAR_OVERRIDE_BY_LIGHT
		action = new LCAction(LCActionTypeEnum.SET_CLEAR_OVERRIDE_BY_LIGHT);
		response = getProcessBCL().submitProcess(processRequest, action);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.TURN_ON
		action = new LCAction(LCActionTypeEnum.TURN_ON);
		action.addActionParameter(new LCActionParameter(PropertyEnum.LIGHT_INTENSITY, "100"));
		response = getProcessBCL().submitProcess(processRequest, action);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.TURN_OFF
		action = new LCAction(LCActionTypeEnum.TURN_OFF);
		action.addActionParameter(new LCActionParameter(PropertyEnum.LIGHT_INTENSITY, "0"));
		response = getProcessBCL().submitProcess(processRequest, action);
		assertResponse(response);
	}

	@Test
	public void testSubmitProcessDeletionSmartpointSchedule()
	{
		ProcessRequest processRequest = createProcessRequest();
		processRequest.addSortExpressions(createMLCSortExpression(PROCESS_ID, false));

		// Success situation - LCActionTypeEnum.DEL_LIGHT_FROM_SCHEDULE_EVENT
		setTestControl(SensusAppContext.getBean(PROCESS_DELETION_SMARTPOINT_SCHEDULE), IScheduleBCL.class, EVENT);
		LCAction action = new LCAction(LCActionTypeEnum.DEL_LIGHT_FROM_SCHEDULE_EVENT);
		InternalResultsResponse<Process> response = getProcessBCL().submitProcess(processRequest, action);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.DEL_LIGHT_FROM_SCHEDULE_OFFSET
		processRequest = createProcessRequest();
		processRequest.addSortExpressions(createMLCSortExpression(PROCESS_ID, false));
		setTestControl(SensusAppContext.getBean(PROCESS_DELETION_SMARTPOINT_SCHEDULE), IScheduleBCL.class, OFFSET);
		action = new LCAction(LCActionTypeEnum.DEL_LIGHT_FROM_SCHEDULE_OFFSET);
		response = getProcessBCL().submitProcess(processRequest, action);
		assertResponse(response);
	}

	@Test
	public void testSubmitProcessAdditionSmartpointSchedule()
	{
		ProcessRequest processRequest = createProcessRequest();
		processRequest.addSortExpressions(createMLCSortExpression(PROCESS_ID, false));

		// Success situation - LCActionTypeEnum.ADD_LIGHT_TO_SCHEDULE_EVENT
		setTestControl(SensusAppContext.getBean(PROCESS_ADDITION_SMARTPOINT_SCHEDULE), IScheduleBCL.class, EVENT);
		LCAction action = new LCAction(LCActionTypeEnum.ADD_LIGHT_TO_SCHEDULE_EVENT);
		InternalResultsResponse<Process> response = getProcessBCL().submitProcess(processRequest, action);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.ADD_LIGHT_TO_SCHEDULE_OFFSET
		setTestControl(SensusAppContext.getBean(PROCESS_ADDITION_SMARTPOINT_SCHEDULE), IScheduleBCL.class, OFFSET);
		action = new LCAction(LCActionTypeEnum.ADD_LIGHT_TO_SCHEDULE_OFFSET);
		response = getProcessBCL().submitProcess(processRequest, action);
		assertResponse(response);
	}

	/**
	 * Test submit process.
	 */
	@Test
	public void testSubmitProcess()
	{
		ProcessRequest processRequest = createProcessRequest();
		processRequest.addSortExpressions(createMLCSortExpression(PROCESS_ID, false));

		// Success situation - LCActionTypeEnum.UPDATE_LIGHT_LAT_LONG
		LCAction lCAction = new LCAction();
		lCAction.setActionType(LCActionTypeEnum.UPDATE_LIGHT_LAT_LONG);
		InternalResultsResponse<Process> response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.UPDATE_LIGHT_POLE_ID
		lCAction = new LCAction();
		lCAction.setActionType(LCActionTypeEnum.UPDATE_LIGHT_POLE_ID);
		response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.CLEAR_ALARM
		lCAction = new LCAction();
		lCAction.setActionType(LCActionTypeEnum.CLEAR_ALARM);
		response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.CLEAR_ALL_ALARMS
		lCAction = new LCAction();
		lCAction.setActionType(LCActionTypeEnum.CLEAR_ALL_ALARMS);
		response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.CLEAR_ALL_ALARMS - with an action parameters
		LCActionParameter lcActionParameter = new LCActionParameter();
		lcActionParameter.setActionValue("10");

		processRequest.getProcess().getLcAction().setActionParameters(Arrays.asList(lcActionParameter));

		lCAction = new LCAction();
		lCAction.setActionType(LCActionTypeEnum.CLEAR_ALL_ALARMS);
		response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.CLEAR_ALL_ALARMS - Communication failure
		lcActionParameter = new LCActionParameter();
		lcActionParameter.setActionValue("8");

		processRequest.getProcess().getLcAction().setActionParameters(Arrays.asList(lcActionParameter));

		lCAction = new LCAction();
		lCAction.setActionType(LCActionTypeEnum.CLEAR_ALARM);
		response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.UPDATE_LIGHT_STATUS (ACTIVE)
		lcActionParameter = new LCActionParameter();
		lcActionParameter.setActionValue("8");
		lcActionParameter.setProperty(PropertyEnum.ACTIVE);

		processRequest.getProcess().getLcAction().setActionParameters(Arrays.asList(lcActionParameter));

		lCAction = new LCAction();
		lCAction.setActionType(LCActionTypeEnum.UPDATE_LIGHT_STATUS);
		response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.UPDATE_LIGHT_STATUS (MAINTENANCE)
		lcActionParameter = new LCActionParameter();
		lcActionParameter.setActionValue("8");
		lcActionParameter.setProperty(PropertyEnum.MAINTENANCE);

		processRequest.getProcess().getLcAction().setActionParameters(Arrays.asList(lcActionParameter));

		lCAction = new LCAction();
		lCAction.setActionType(LCActionTypeEnum.UPDATE_LIGHT_STATUS);
		response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.UPDATE_LIGHT_STATUS (DEACTIVATED)
		lcActionParameter = new LCActionParameter();
		lcActionParameter.setActionValue("8");
		lcActionParameter.setProperty(PropertyEnum.DEACTIVATED);

		processRequest.getProcess().getLcAction().setActionParameters(Arrays.asList(lcActionParameter));

		lCAction = new LCAction();
		lCAction.setActionType(LCActionTypeEnum.UPDATE_LIGHT_STATUS);
		response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.UPDATE_LIGHT_STATUS (GET_LIGHT_STATUS / STATUS)
		lcActionParameter = new LCActionParameter();
		lcActionParameter.setActionValue("1"); // status
		lcActionParameter.setProperty(PropertyEnum.LIGHT_DETAIL_TYPE);

		processRequest.getProcess().getLcAction().setActionParameters(Arrays.asList(lcActionParameter));

		lCAction = new LCAction();
		lCAction.setActionType(LCActionTypeEnum.GET_LIGHT_STATUS);
		response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.UPDATE_LIGHT_STATUS (GET_LIGHT_STATUS / CONFIGURATION)
		lcActionParameter = new LCActionParameter();
		lcActionParameter.setActionValue("2"); // configuration
		lcActionParameter.setProperty(PropertyEnum.LIGHT_DETAIL_TYPE);

		processRequest.getProcess().getLcAction().setActionParameters(Arrays.asList(lcActionParameter));

		lCAction = new LCAction();
		lCAction.setActionType(LCActionTypeEnum.GET_LIGHT_STATUS);
		response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.SET_INTENSITY_BY_GRP (LEVEL 0)
		List<LCActionParameter> lstActionParameter = new ArrayList<LCActionParameter>();

		lcActionParameter = new LCActionParameter();
		lcActionParameter.setActionValue("2"); // configuration
		lcActionParameter.setProperty(PropertyEnum.LIGHT_DETAIL_TYPE);

		lstActionParameter.add(lcActionParameter);

		lcActionParameter = new LCActionParameter();
		lcActionParameter.setActionValue("0"); // level 0
		lcActionParameter.setProperty(PropertyEnum.LIGHT_DETAIL_TYPE);

		lstActionParameter.add(lcActionParameter);

		processRequest.getProcess().getLcAction().setActionParameters(lstActionParameter);

		lCAction = new LCAction();
		lCAction.setActionType(LCActionTypeEnum.SET_INTENSITY_BY_GRP);
		lCAction.setActionParameters(lstActionParameter);
		response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertResponse(response);

		// Success situation - LCActionTypeEnum.SET_INTENSITY_BY_GRP (LEVEL 5)
		lstActionParameter = new ArrayList<LCActionParameter>();

		lcActionParameter = new LCActionParameter();
		lcActionParameter.setActionValue("2"); // configuration
		lcActionParameter.setProperty(PropertyEnum.LIGHT_DETAIL_TYPE);

		lstActionParameter.add(lcActionParameter);

		lcActionParameter = new LCActionParameter();
		lcActionParameter.setActionValue("100"); // level 5 with percentage = 100%
		lcActionParameter.setProperty(PropertyEnum.LIGHT_DETAIL_TYPE);

		lstActionParameter.add(lcActionParameter);

		processRequest.getProcess().getLcAction().setActionParameters(lstActionParameter);

		lCAction = new LCAction();
		lCAction.setActionType(LCActionTypeEnum.SET_INTENSITY_BY_GRP);
		lCAction.setActionParameters(lstActionParameter);
		response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertResponse(response);

		//

		// Error situation
		lCAction = new LCAction();
		lCAction.setActionType(LCActionTypeEnum.ABORT);

		response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertEquals(InternalResponse.Status.ExceptionError, response.getStatus());

		// Error situation
		response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertEquals(InternalResponse.Status.ExceptionError, response.getStatus());

		// Error situation - simulate the RNI OFF-LINE
		processRequest.getUserContext().getTenant().setId(100);
		response = getProcessBCL().submitProcess(processRequest, lCAction);
		assertTrue(response.isInError());
		assertEquals(InternalResponse.Status.ExceptionError, response.getStatus());
		assertMessages(response, RNI_OFFLINE_EXCEPTION);

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

		InternalResultsResponse<Process> response = getProcessBCL().fetchProcessByLight(lightRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToProcessArea();

		setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "fetchProcessByLight");

		lightRequest = new LightRequest();
		lightRequest.setLightCriteria(lightCriteria);
		lightRequest.setUserContext(createUserContext());
		response = getProcessBCL().fetchProcessByLight(lightRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test update csv downloaded.
	 */
	@Test
	public void testUpdateCSVDownloaded()
	{
		ProcessRequest request = createProcessRequest();
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
		// Success situation (with MLCFAILURE)
		ProcessRequest processRequest = createProcessRequest();
		Process process = new Process();
		process.setId(1);
		processRequest.setProcess(process);
		processRequest.setFileName(FILE_NAME_CSV);
		processRequest.getUserContext().setLocaleString(TIME_ZONE);
		processRequest.setProcessId(new Integer(1));
		processRequest.getProcessList().get(0).setProcessItems(Arrays.asList(new ProcessItem()));
		processRequest.getProcessList().get(0).getProcessItems().get(0)
				.setProcessItemStatusEnum(ProcessItemStatusEnum.MLCFAILURE);

		ProcessResponse processResponse = getProcessBCL().generateSumaryFileCSV(processRequest);
		assertTrue(processResponse.isOperationSuccess());

		// Success situation (without MLCFAILURE)
		processRequest = createProcessRequest();
		process = new Process();
		process.setId(1);
		processRequest.setProcess(process);
		processRequest.setFileName(FILE_NAME_CSV);
		processRequest.getUserContext().setLocaleString(TIME_ZONE);
		processRequest.getProcessList().get(0).setProcessItems(Arrays.asList(new ProcessItem()));

		processResponse = getProcessBCL().generateSumaryFileCSV(processRequest);
		assertTrue(processResponse.isOperationSuccess());

		// Error situation on method fetchAllByRequest
		setSituation(getProcessBCL(), SituationsEnum.ERROR, ILightBCL.class,
				"fetchAllByRequest");
		processResponse = getProcessBCL().generateSumaryFileCSV(processRequest);
		assertFalse(processResponse.isOperationSuccess());
		assertMessages(processResponse, ERROR_CODE);
		resetMocksToProcessArea();

		// Errors situations
		setSituation(getProcessBCL(), SituationsEnum.ERROR, ILightCSVBCL.class,
				"generateLightSummaryFileCSV");
		processResponse = getProcessBCL().generateSumaryFileCSV(processRequest);
		assertFalse(processResponse.isOperationSuccess());
		assertMessages(processResponse, ERROR_CODE);
		resetMocksToProcessArea();
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
		inquiryProcessRequest.getUserContext().setLocaleString(TIME_ZONE);
		request.setTimezone(TIME_ZONE);
		List<Column> columns = Arrays.asList(new Column(ACTION));
		inquiryProcessRequest.setListColumn(columns);
		request.setProcessId(new Integer(1));
		request.setFileName(System.getProperty("java.io.tmpdir") + LCDateUtil.getNewDateUTC().getTime() + ".csv");
		request.setInquiryProcessRequest(inquiryProcessRequest);
		CSVInternalResponse response = getProcessBCL().generateFileCSV(request);
		assertTrue(response.getStatus().equals(Status.OperationSuccess));

		// Errors situations
		setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class,
				"fetchAllProcess");
		response = getProcessBCL().generateFileCSV(request);
		assertFalse(response.getStatus().equals(Status.OperationSuccess));
		assertMessages(response, ERROR_CODE);
		resetMocksToProcessArea();
	}

	/**
	 * Test fetch light history header.
	 */
	@Test
	public void testFetchLightHistoryHeader()
	{
		// Success situation
		ProcessRequest processRequest = createProcessRequest();
		Tenant tenant = createTenant();
		processRequest.getUserContext().setTenant(tenant);
		InternalResultsResponse<HashMap<String, Integer>> response =
				getProcessBCL().fetchCountMonitoredProcesses(processRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToProcessArea();
		setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, "fetchCountMonitoredProcesses");
		response = getProcessBCL().fetchCountMonitoredProcesses(processRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test insert csv process.
	 */
	@Test
	public void testInsertCSVProcess()
	{
		// Success situation
		InquiryProcessRequest inquiryProcessRequest = createInquiryProcessRequest();
		inquiryProcessRequest.setMonitored(true);
		ProcessResponse response = getProcessBCL().insertCSVProcess(inquiryProcessRequest);
		assertNotNull(response);
		assertTrue(response.isOperationSuccess());

		// Errors situations
		// ProcessDAC
		setSituation(getProcessBCL(), SituationsEnum.ERROR, IProcessDAC.class, INSERT_PROCESS);
		response = getProcessBCL().insertCSVProcess(inquiryProcessRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);
		resetMocksToProcessArea();
	}

	/**
	 * Test fetch summary by process id.
	 */
	@Test
	public void testFetchSummaryByProcessId()
	{
		ProcessRequest request = new ProcessRequest();
		request.setUserContext(createUserContext());

		InternalResultsResponse<Process> response = getProcessBCL().fetchSummaryByProcessId(request);
		assertResponse(response);

	}
}
