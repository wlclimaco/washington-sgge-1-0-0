package com.sensus.mlc.process.dac;

import static com.sensus.mlc.base.TestBaseUtil.assertResponse;
import static com.sensus.mlc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.mlc.base.TestBaseUtil.createInquiryProcessRequest;
import static com.sensus.mlc.base.TestBaseUtil.createLCActionParameter;
import static com.sensus.mlc.base.TestBaseUtil.createLcAction;
import static com.sensus.mlc.base.TestBaseUtil.createLightRequest;
import static com.sensus.mlc.base.TestBaseUtil.createProcessRequest;
import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractTestBaseDAC;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.process.csv.ProcessCSVDataSource;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessFilter;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightBlinkEnum;
import com.sensus.mlc.smartpoint.model.LightPropertyForSearchEnum;
import com.sensus.mlc.smartpoint.model.OverrideEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class ProcessDACTest.
 */
public class ProcessDACTest extends AbstractTestBaseDAC
{

	/** The Constant PROCESS_ID. */
	private static final String PROCESS_ID = "process_id";
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
				new LCActionParameter(PropertyEnum.LIGHT_BLINK, LightBlinkEnum.NONE.getValue().toString());
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

		Tenant tenant = new Tenant();
		tenant.setId(1);
		tenant.setRniCode("1");

		UserContext userContext = new UserContext(1);
		userContext.setUserId("rod");

		ProcessRequest processRequest = new ProcessRequest(process, tenant, userContext);
		InternalResultsResponse<Process> response =
				getProcessDAC().insertProcess(processRequest);
		assertResultResponse(response);

		// ---------------------------------------------------------------------------------------

		InquiryProcessRequest processInquiryRequest = createInquiryProcessRequest();

		response = getProcessDAC().fetchProcesses(processInquiryRequest);
		assertResponse(response);

		// Insert first one
		Process parentProcess = this.insertProcess(getLightDefault());

		// Insert second one with the old one as parent
		this.insertProcess(insertLight(), parentProcess);

		response = getProcessDAC().fetchProcesses(processInquiryRequest);

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
		Process newProcess = this.insertProcess(getLightDefault());

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
		Process newProcess = this.insertProcess(getLightDefault());

		ProcessRequest processRequest = createProcessRequest();
		processRequest.setProcess(newProcess);

		InternalResponse updateCSVResponse = getProcessDAC().updateCSVDownloaded(processRequest);
		assertResponse(updateCSVResponse);
	}

	/**
	 * Test fetch all tenant.
	 */
	@Test
	public void testFetchAllTenant()
	{
		InternalResultsResponse<Tenant> response = getProcessDAC().fetchAllTenant();
		assertResponse(response);

		for (Tenant t : response.getResultsList())
		{
			assertNotNull(t.getId());
			assertNotNull(t.getName());
			assertNotNull(t.getGatewayURL());
		}
	}

	/**
	 * Test fetch unsubmitted processes.
	 */
	@Test
	public void testFetchUnsubmittedProcesses()
	{
		// Insert Process
		this.insertProcess(getLightDefault());
		InternalResultsResponse<Process> response = getProcessDAC().fetchUnsubmittedProcesses();
		assertResultResponse(response);
	}

	/**
	 * Test fetch process.
	 * 
	 */
	@Test
	public void testFetchProcess()
	{
		// Find Process
		this.insertProcess(getLightDefault());

		InquiryProcessRequest processRequest = createInquiryProcessRequest();

		InternalResultsResponse<Process> response =
				getProcessDAC().fetchProcesses(processRequest);

		assertResultResponse(response);

		String column = PROCESS_ID;

		// Not Find Process - UserRole / ProcessFilter / RNI_ID / EVENT_ID / POLE_ID
		ProcessFilter processFilter = TestBaseUtil.createProcessFilter(1);
		processFilter.setLightTextSearch(TestBaseUtil.createLightTextSearch(LightPropertyForSearchEnum.RNI_ID,
				column));
		processRequest.setProcessFilter(processFilter);
		processRequest.getSortExpressions().add(TestBaseUtil.createMLCSortExpression(column, true));
		processRequest.getUserContext().setUserRole("ROLE_Role.NotAdmin");

		response = getProcessDAC().fetchProcesses(processRequest);

		assertResponse(response);

		processFilter.setLightTextSearch(TestBaseUtil.createLightTextSearch(LightPropertyForSearchEnum.EVENT_ID,
				column));

		response = getProcessDAC().fetchProcesses(processRequest);
		assertResponse(response);

		processFilter.setLightTextSearch(TestBaseUtil.createLightTextSearch(LightPropertyForSearchEnum.POLE_ID,
				column));

		response = getProcessDAC().fetchProcesses(processRequest);
		assertResponse(response);
		assertEquals("Status should to be no rows found", Status.NoRowsFoundError, response.getStatus());
		assertFalse("Result list should to be empty", response.hasResults());
	}

	/**
	 * Test fetch monitored process.
	 */
	@Test
	public void testFetchMonitoredProcess()
	{
		this.insertProcess(getLightDefault());
		ProcessRequest processRequest = createProcessRequest();
		UserContext uc = new UserContext();
		uc.setId(1);
		uc.setTenant(new com.sensus.common.model.Tenant(2));
		processRequest.setUserContext(uc);
		InternalResultsResponse<Process> response = getProcessDAC().fetchMonitoredProcesses(processRequest);
		assertResponse(response);

		// With Order By Parameters
		processRequest.getSortExpressions().add(TestBaseUtil.createMLCSortExpression(PROCESS_ID, true));
		response = getProcessDAC().fetchMonitoredProcesses(processRequest);
		assertResponse(response);
	}

	/**
	 * Test unmonitor process.
	 */
	@Test
	public void testUnmonitorProcess()
	{
		Process parentProcess = this.insertProcess(getLightDefault());

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
		processRequest.setProcess(this.insertProcess(getLightDefault()));

		// get the process that war inserted by id
		InternalResultsResponse<Process> response = getProcessDAC().fetchProcessById(processRequest);
		assertResultResponse(response);

		Process process = response.getFirstResult();

		LCAction lcAction = new LCAction();
		lcAction.getActionParameters().add(createLCActionParameter());

		process.setLcAction(lcAction);

		process.setProcessItems(TestBaseUtil.createProcessItem(getLightDefault()));

		processRequest = createProcessRequest();
		processRequest.setProcess(process);

		// update the process file the file name
		InternalResponse internalResponse = getProcessDAC().updateProcess(processRequest);

		assertResponse(internalResponse);
		assertEquals("should update the process with file name", true,
				internalResponse.getStatus().equals(Status.OperationSuccess));

		// fetch the process with the filename
		response =
				getProcessDAC().fetchProcessByFileName(processRequest);

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
		Process parentProcess = this.insertProcess(getLightDefault());
		Process childProcess = this.insertProcess(getLightDefault(), parentProcess);
		Process grandChildProcess = this.insertProcess(getLightDefault(), childProcess);

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
	 * Test fetch tenant by id.
	 */
	@Test
	public void testFetchTenantById()
	{
		Tenant tenant = TestBaseUtil.createTenant();
		InternalResultsResponse<Tenant> response =
				getProcessDAC().fetchTenantById(tenant.getId());
		assertResultResponse(response);
		assertNotNull(tenant.getId());
		assertNotNull(tenant.getName());
		assertNotNull(tenant.getDescription());
		assertNotNull(tenant.getRniCode());
	}

	/**
	 * Test fetch tenant by rni code.
	 */
	@Test
	public void testFetchTenantByRniCode()
	{
		Tenant tenant = TestBaseUtil.createTenant();
		InternalResultsResponse<Tenant> response =
				getProcessDAC().fetchTenantByRniCode(tenant.getRniCode());
		assertResultResponse(response);
		assertNotNull(tenant.getId());
		assertNotNull(tenant.getName());
		assertNotNull(tenant.getDescription());
		assertNotNull(tenant.getRniCode());
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
		LightRequest lightRequest = createLightRequest();

		// Insert new Process
		ProcessRequest request = createProcessRequest();

		Process process = new Process();

		process.setRniCorrelationId(new LCHelp().generateRniCorrelationId());
		process.setDescription("xpto");
		process.setStartTime(getNewDateUTC());
		process.setIsMonitoredInstance(true);
		process.setIsSubmitted(false);

		process.setIsProcessComplete(false);
		process.setDescription("desc1");

		// Add a new Action
		process.setLcAction(createLcAction());
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

		lightRequest.addLight(processItem.getLight());

		InternalResultsResponse<Process> responseProcessByLight =
				getProcessDAC().fetchProcessByLight(lightRequest);
		assertResponse(responseProcessByLight);
	}

	/**
	 * Test generate file csv.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGenerateFileCSV() throws IOException
	{
		this.insertProcess(getLightDefault());

		InquiryProcessRequest inquiryProcessRequest = TestBaseUtil.createInquiryProcessRequest();
		inquiryProcessRequest.setFileName(TestBaseUtil.FILE_NAME);
		inquiryProcessRequest.setTimezone(TimeZone.getDefault().getID());

		InternalResponse response = getProcessDAC().generateFileCSV(inquiryProcessRequest);
		assertResponse(response);

		// check dimensions...
		List<Process> processList = getProcessDAC().fetchProcesses(inquiryProcessRequest).getResultsList();
		ProcessCSVDataSource ds = new ProcessCSVDataSource(inquiryProcessRequest, processList);
		TestBaseUtil.checkCSVFileDimensions(processList.size(), ds);
	}
}
