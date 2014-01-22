package com.sensus.lc.process.dac;

import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.lc.base.TestBaseUtil.createInquiryProcessRequest;
import static com.sensus.lc.base.TestBaseUtil.createLCActionParameter;
import static com.sensus.lc.base.TestBaseUtil.createMLCSortExpression;
import static com.sensus.lc.base.TestBaseUtil.createProcessItem;
import static com.sensus.lc.base.TestBaseUtil.createProcessRequest;
import static com.sensus.lc.base.util.LCDateUtil.getNewDateUTC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractTestBaseDAC;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.util.LCHelp;
import com.sensus.lc.light.model.BlinkStatusEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.LightPropertyForSearchEnum;
import com.sensus.lc.light.model.OverrideEnum;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.LCActionParameter;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessFilter;
import com.sensus.lc.process.model.ProcessItem;
import com.sensus.lc.process.model.ProcessItemStatusEnum;
import com.sensus.lc.process.model.ProcessOrderByEnum;
import com.sensus.lc.process.model.request.InquiryProcessRequest;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class ProcessDACTest.
 */
public class ProcessDACTest extends AbstractTestBaseDAC
{

	/** The Constant NUMBER_100. */
	private static final int NUMBER_100 = 100;

	/** The Constant PROCESS_ID. */
	private static final String PROCESS_ID = "999999";

	/** The light default. */
	private Light lightDefault;

	/**
	 * Gets the light default.
	 * 
	 * @return the light default
	 */
	public Light getLightDefault()
	{
		return lightDefault;
	}

	/**
	 * Sets the light default.
	 * 
	 * @param lightDefault the new light default
	 */
	public void setLightDefault(Light lightDefault)
	{
		this.lightDefault = lightDefault;
	}

	/**
	 * Setup test.
	 */
	@Before
	public void setupTest()
	{
		if (getLightDefault() == null)
		{
			setLightDefault(insertLight());
		}
	}

	/**
	 * Test insert process.
	 */
	@Test
	public void testInsertProcess()
	{
		LCAction action = new LCAction();

		action = new LCAction(LCActionTypeEnum.DIM);

		LCActionParameter actionParameter = new LCActionParameter();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();

		actionParameter = new LCActionParameter(PropertyEnum.LIGHT_INTENSITY, "50");
		actionParameters.add(actionParameter);

		actionParameter =
				new LCActionParameter(PropertyEnum.LIGHT_BLINK, BlinkStatusEnum.NONE.getValue().toString());
		actionParameters.add(actionParameter);

		actionParameter =
				new LCActionParameter(PropertyEnum.OVERRIDE, OverrideEnum.SCHEDULED.getValue().toString());
		actionParameters.add(actionParameter);

		action.setActionParameters(actionParameters);

		Process process = new Process();
		process.setStartTime(getNewDateUTC());
		process.setIsMonitoredInstance(false);
		process.setIsParent(true);
		process.setIsProcessComplete(false);
		process.setProcessItems(new ArrayList<ProcessItem>());
		process.setLcAction(action);

		ProcessItem processItem = new ProcessItem();
		Light light = insertLight();

		processItem.setLight(light);
		processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.PENDING);
		process.getProcessItems().add(processItem);

		process.setDescription("Set intensity of 1 Lights to 50%");
		process.getLcAction().setDescription("Turn On/Off");
		process.setRniCorrelationId(PROCESS_ID);
		process.setIsSubmitted(false);
		process.setEndTime(new Date());

		Tenant tenant = new Tenant();
		tenant.setId(1);
		tenant.setRniCode("1");

		UserContext userContext = new UserContext(1);
		userContext.setUserId("rod");
		userContext.setTenant(tenant);

		ProcessRequest processRequest = new ProcessRequest(userContext, process);
		InternalResultsResponse<Process> response =
				getProcessDAC().insertProcess(processRequest);
		assertResultResponse(response);

		// ---------------------------------------------------------------------------------------

		InquiryProcessRequest processInquiryRequest = createInquiryProcessRequest();

		response = getProcessDAC().fetchAllProcess(processInquiryRequest);
		assertResponse(response);

		// Insert first one
		Process parentProcess = insertProcess(getLightDefault());

		// Insert second one with the old one as parent
		insertProcess(insertLight(), parentProcess);

		response = getProcessDAC().fetchAllProcess(processInquiryRequest);

		// Needs to return more one record
		assertResultResponse(response);
	}

	/**
	 * Test update process.
	 */
	@Test
	public void testUpdateProcess()
	{
		// Insert Process
		Process newProcess = insertProcess(getLightDefault());

		ProcessRequest request = createProcessRequest();

		newProcess.setEndTime(getNewDateUTC());
		newProcess.setIsMonitoredInstance(false);
		newProcess.setIsProcessComplete(true);
		newProcess.setIsSubmitted(true);
		newProcess.setRniCorrelationId("2");

		request.setProcess(newProcess);

		InternalResponse response = getProcessDAC().updateProcess(request);
		assertResponse(response);
	}

	/**
	 * Test update csv downloaded.
	 */
	@Test
	public void testUpdateCSVDownloaded()
	{
		Process newProcess = insertProcess(getLightDefault());

		ProcessRequest processRequest = createProcessRequest();
		processRequest.setProcess(newProcess);

		InternalResponse updateCSVResponse = getProcessDAC().updateCSVDownloaded(processRequest);
		assertResponse(updateCSVResponse);
	}

	/**
	 * Test fetch unsubmitted processes.
	 */
	@Test
	public void testFetchUnsubmittedProcesses()
	{
		// Insert Process
		insertProcess(getLightDefault());
		InternalResultsResponse<Process> response = getProcessDAC().fetchUnsubmittedProcesses();
		assertResultResponse(response);
	}

	/**
	 * Test fetch all process.
	 */
	@Test
	public void testFetchAllProcess()
	{
		// Pre conditions
		insertProcess(getLightDefault());

		// Find All Process
		InquiryProcessRequest processRequest = createInquiryProcessRequest();
		InternalResultsResponse<Process> response = getProcessDAC().fetchAllProcess(processRequest);
		assertResultResponse(response);

		// Not Find Process - (UserRole / ProcessFilter / RNI_ID / EVENT_ID / POLE_ID)
		ProcessFilter processFilter = TestBaseUtil.createProcessFilter(processRequest.getUserContext().getId());
		processFilter.setLightTextSearch(TestBaseUtil.createLightTextSearch(LightPropertyForSearchEnum.RNI_ID,
				PROCESS_ID));
		processRequest.setProcessFilter(processFilter);
		processRequest.getSortExpressions().add(createMLCSortExpression(ProcessOrderByEnum.ID_COLUMN.getValue(),
				true));
		processRequest.getUserContext().setUserRole("ROLE_Role.NotAdmin");

		response = getProcessDAC().fetchAllProcess(processRequest);
		assertResponse(response);

		processFilter.setLightTextSearch(TestBaseUtil.createLightTextSearch(LightPropertyForSearchEnum.EVENT_ID,
				PROCESS_ID));
		response = getProcessDAC().fetchAllProcess(processRequest);
		assertResponse(response);

		processFilter.setLightTextSearch(TestBaseUtil.createLightTextSearch(LightPropertyForSearchEnum.POLE_ID,
				PROCESS_ID));
		response = getProcessDAC().fetchAllProcess(processRequest);
		assertResponse(response);
		assertEquals("Status should to be no rows found", Status.NoRowsFoundError, response.getStatus());
		assertFalse("Result list should to be empty", response.hasResults());

		// Assert process with failure
		Process process = insertProcess(getLightDefault(), null, true);
		processFilter.setLightTextSearch(
				TestBaseUtil.createLightTextSearch(
						LightPropertyForSearchEnum.EVENT_ID,
						String.valueOf(process.getId())));
		response = getProcessDAC().fetchAllProcess(processRequest);
		assertResponse(response);
		assertNotNull(response.getFirstResult().getProcessItemFailedAmount());
		assertTrue(response.getFirstResult().getProcessItemFailedAmount() > 0);
	}

	/**
	 * Test fetch monitored process.
	 */
	@Test
	public void testFetchMonitoredProcess()
	{
		insertProcess(getLightDefault());
		ProcessRequest processRequest = createProcessRequest();
		processRequest.setUserContext(TestBaseUtil.createUserContext());
		processRequest.getUserContext().setId(1);
		InternalResultsResponse<Process> response = getProcessDAC().fetchMonitoredProcesses(processRequest);
		assertResponse(response);

		// With Order By Parameters
		processRequest.getSortExpressions().add(createMLCSortExpression(ProcessOrderByEnum.ID_COLUMN.getValue(), true));
		response = getProcessDAC().fetchMonitoredProcesses(processRequest);
		assertResponse(response);
	}

	/**
	 * Test unmonitor process.
	 */
	@Test
	public void testUnmonitorProcess()
	{
		Process parentProcess = insertProcess(getLightDefault());

		ProcessRequest processRequest = createProcessRequest();
		List<Process> processList = new ArrayList<Process>();
		processList.add(parentProcess);
		processRequest.setProcessList(processList);

		InternalResponse response = getProcessDAC().unmonitorProcess(processRequest);
		assertResponse(response);
	}

	/**
	 * Test fetch process by id.
	 */
	@Test
	public void testFetchProcessById()
	{
		Process parentProcess = insertProcess(getLightDefault());
		Process childProcess = insertProcess(getLightDefault(), parentProcess);
		Process grandChildProcess = insertProcess(getLightDefault(), childProcess);

		ProcessRequest processRequest = createProcessRequest();
		processRequest.setProcess(grandChildProcess);

		InternalResultsResponse<Process> response =
				getProcessDAC().fetchProcessById(processRequest);
		assertResultResponse(response);

		Process process = response.getFirstResult();

		// Process Should have one parent
		assertNotNull("Process should have a parent ", process.getParentProcess());
		Process parent = process.getParentProcess();

		// Process should have one grandparent
		assertNotNull("Process should have a grandparent ", parent.getParentProcess());

		// Process should NOT have one greatgrandparent
		Process grandparent = parent.getParentProcess();
		assertNull("Process should NOT have a greatgrandparent ", grandparent.getParentProcess());
	}

	/**
	 * Test fetch process by file name.
	 */
	@Test
	public void testFetchProcessByFileName()
	{
		ProcessRequest processRequest = createProcessRequest();
		processRequest.setProcess(insertProcess(getLightDefault()));

		// get the process that war inserted by id
		InternalResultsResponse<Process> response = getProcessDAC().fetchProcessById(processRequest);
		assertResultResponse(response);

		Process process = response.getFirstResult();

		LCAction lcAction = new LCAction();
		lcAction.getActionParameters().add(createLCActionParameter());

		process.setLcAction(lcAction);

		process.setProcessItems(createProcessItem(getLightDefault()));

		processRequest = createProcessRequest();
		processRequest.setProcess(process);

		// update the process file the file name
		InternalResponse internalResponse = getProcessDAC().updateProcess(processRequest);

		assertResponse(internalResponse);
		assertEquals("should update the process with file name", true,
				internalResponse.getStatus().equals(Status.OperationSuccess));

		// fetch the process with the filename
		response = getProcessDAC().fetchProcessByFileName(processRequest);

		assertResultResponse(response);

		Process processFetch = response.getFirstResult();

		assertEquals("should be the same", process.getId(), processFetch.getId());

		// should not found
		processRequest.getProcess().getLcAction().getActionParameters().get(0).setActionValue("FileName1");

		response = getProcessDAC().fetchProcessByFileName(processRequest);

		assertEquals("no messages", 0, response.getMessageInfoList().size());
		assertEquals("should not bring the process ", 0, response.getResultsList().size());
		assertFalse("Should be false", response.getStatus().equals(Status.OperationSuccess));
	}

	/**
	 * Test fetch process by rni id.
	 */
	@Test
	public void testFetchProcessByRniId()
	{
		Process parentProcess = insertProcess(getLightDefault());
		Process childProcess = insertProcess(getLightDefault(), parentProcess);
		Process grandChildProcess = insertProcess(getLightDefault(), childProcess);

		ProcessRequest processRequest = createProcessRequest();
		processRequest.setProcess(grandChildProcess);

		InternalResultsResponse<Process> response =
				getProcessDAC().fetchProcessByRniId(processRequest);
		assertResultResponse(response);
		assertEquals(1, response.getResultsList().size());

		Process process = response.getResultsList().get(0);

		// Process Should have one parent
		assertNotNull("Process should have a parent", process.getParentProcess());
		Process parent = process.getParentProcess();

		// Process should have one grandparent
		assertNotNull("Process should have a grandparent", parent.getParentProcess());

		// Process should NOT have one greatgrandparent
		Process grandparent = parent.getParentProcess();
		assertNull("Process should NOT have a greatgrandparent", grandparent.getParentProcess());

		// should not found
		processRequest.getProcess().setRniCorrelationId("RniIDNotFound");
		response = getProcessDAC().fetchProcessByRniId(processRequest);
		assertEquals("No messages", 0, response.getMessageInfoList().size());
		assertEquals("should not bring the process", 0, response.getResultsList().size());
		assertFalse("should be false", response.getStatus().equals(Status.OperationSuccess));
	}

	/**
	 * Test fetch count monitored processes.
	 */
	@Test
	public void testFetchCountMonitoredProcesses()
	{
		ProcessRequest processRequest = createProcessRequest();
		InternalResultsResponse<HashMap<String, Integer>> response =
				getProcessDAC().fetchCountMonitoredProcesses(processRequest);
		assertResultResponse(response);

		System.out.println("Count Monitored: " + response.getFirstResult().get("count_monitored"));
		System.out.println("Count Processing: " + response.getFirstResult().get("count_processing"));
	}

	/**
	 * Test fetch process by light.
	 */
	@Test
	public void testFetchProcessByLight()
	{
		LightCriteria lightCriteria = new LightCriteria();
		lightCriteria.setLightIdList(Arrays.asList(getLightDefault().getId()));

		LightRequest lightRequest = new LightRequest();
		lightRequest.setUserContext(TestBaseUtil.createUserContext());

		// Insert new Process
		ProcessRequest request = createProcessRequest();

		Process process = new Process();

		process.setRniCorrelationId(LCHelp.generateRniCorrelationId());
		process.setDescription("xpto");
		process.setStartTime(getNewDateUTC());
		process.setIsMonitoredInstance(true);
		process.setIsSubmitted(false);

		process.setIsProcessComplete(false);
		process.setDescription("desc1");

		// Add a new Action
		process.setLcAction(TestBaseUtil.createLcAction());
		process.getLcAction().getActionParameters().get(0).setProperty(PropertyEnum.GROUP_ID);

		process.setIsProcessComplete(false);
		process.setDescription("desc");

		List<ProcessItem> processResultList = new ArrayList<ProcessItem>();

		// Add one Process Item
		ProcessItem processItem = new ProcessItem();
		processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.PENDING);
		processItem.setLight(getLightDefault());
		processResultList.add(processItem);

		process.setProcessItems(processResultList);

		request.setProcess(process);
		InternalResultsResponse<Process> response =
				getProcessDAC().insertProcess(request);
		assertResultResponse(response);

		// lightRequest.addLight(processItem.getLight());
		lightRequest.setLightCriteria(lightCriteria);

		InternalResultsResponse<Process> responseProcessByLight =
				getProcessDAC().fetchProcessByLight(lightRequest);
		assertResponse(responseProcessByLight);
	}

	/**
	 * Test fetch summary by process id.
	 */
	@Test
	public void testFetchSummaryByProcessId()
	{
		ProcessRequest request = new ProcessRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setProcessId(NUMBER_100);

		InternalResultsResponse<Process> response = getProcessDAC().fetchSummaryByProcessId(request);
		TestBaseUtil.assertResponse(response);
	}
}
