package com.sensus.mlc.wui.process.unittest.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.InquiryProcessResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.process.action.ProcessSearchAjaxAction;

/**
 * The Class ProcessSearchAjaxActionTest.
 */
public class ProcessSearchAjaxActionTest extends StrutsSpringTestCase
{

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	/** The Constant ZERO. */
	private static final String ZERO = "0";

	/** The Constant ONE. */
	private static final String ONE = "1";

	/** The Constant TEN. */
	private static final String TEN = "10";

	/** The Constant TWENTY. */
	private static final String TWENTY = "20";

	/** The Constant THOUSAND. */
	private static final String THOUSAND = "1000";

	private static final String DATE_2011 = "05/03/2011";

	private static final String ID = "id";

	/** The Constant MESSAGE_RESPONSE_OBJECT_NOT_PRESENT. */
	private static final String MSG_RESPONSE_OBJECT_NOT_PRESENT = "Response object not present";

	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MSG_STRUTS_OUTCOME = "Struts outcome";

	/** The Constant MESSAGE_RESPONSE_OBJECT_PRESENT. */
	private static final String MSG_RESPONSE_OBJECT_PRESENT = "Response object present";

	/** The Constant MESSAGE_MESSAGES_SET. */
	private static final String MSG_MESSAGES_SET = "Messages Set";

	/** The Constant MESSAGE_MESSAGE_COUNT. */
	private static final String MSG_MESSAGE_COUNT = "Message Count";

	/** The Constant MESSAGE_SUCCESS_STATUS. */
	private static final String MSG_SUCCESS_STATUS = "Success Status";

	/** The Constant RESULT_COUNT. */
	private static final String RESULT_COUNT = "Result Count";

	/** The Constant TESTING_SUCCESS. */
	private static final String TESTING_SUCCESS = "TESTING success";

	/** The Constant TESTING_FAILURE. */
	private static final String TESTING_FAILURE = "TESTING failure";

	/** The Constant TESTING_EXCEPTION. */
	private static final String TESTING_EXCEPTION = "TESTING exception";

	/** The Constant ACTION_STRUTS. */
	private static final String ACTION_STRUTS_LONG_RUNNING = "/process/longRunningProcessSearch.action";

	/** The Constant ACTION_STRUTS_SEARCH. */
	private static final String ACTION_STRUTS_SEARCH = "/process/searchProcess.action";

	private static final String ACTION_STRUTS_SUMMARY = "/process/include.summary.action";

	/** The Constant TESTING_SUCCESS_WITH_NO_RESULTS. */
	private static final String TESTING_SUCCESS_WITH_NO_RESULTS = "Testing success with no results";

	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

	/** The Constant MESSAGE_RESPONSE_OBJECT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts2.StrutsSpringTestCase#getContextLocations()
	 */
	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	/**
	 * Test search.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testSearch() throws Exception
	{
		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		ActionProxy proxy = getActionProxy("/process/search.action");
		ProcessSearchAjaxAction action = (ProcessSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		ProcessRequest processRequest = new ProcessRequest();

		action.setProcessRequest(processRequest);

		SessionAuthenticationTestUtil.setMockSession();

		ProcessBCFMockImpl bcf = (ProcessBCFMockImpl)action.getProcessBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		String response = proxy.execute();

		/** Asserts **/
		assertEquals(MSG_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MSG_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		ProcessResponse processResponse = (ProcessResponse)action.getResponse();

		assertTrue("Result Count", processResponse.getProcesses().size() != 0);
		assertTrue("Result Count", processResponse.getProcesses().size() == 15);
	}

	/**
	 * Test search process.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testSearchProcess() throws Exception
	{

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		ActionProxy proxy = getActionProxy("/process/searchProcess.action");
		ProcessSearchAjaxAction action = (ProcessSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		InquiryProcessRequest inquiryProcessRequest = new InquiryProcessRequest();

		inquiryProcessRequest.setEndRow(0);
		inquiryProcessRequest.setPageSize(15);
		inquiryProcessRequest.setStartPage(0);

		action.setInquiryProcessRequest(inquiryProcessRequest);

		SessionAuthenticationTestUtil.setMockSession();

		ProcessBCFMockImpl bcf = (ProcessBCFMockImpl)action.getProcessBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		String response = proxy.execute();

		/** Asserts **/
		assertEquals(MSG_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MSG_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		InquiryProcessResponse inquiryProcessResponse = (InquiryProcessResponse)action.getResponse();

		assertTrue("Result Count", inquiryProcessResponse.getProcesses().size() != 0);
		assertTrue("Result Count", inquiryProcessResponse.getProcesses().size() == 15);

	}

	/**
	 * Test get process by id.
	 */
	@Test
	public void testGetProcessById() throws Exception
	{
		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		ActionProxy proxy = getActionProxy("/process/getProcessById.action");
		ProcessSearchAjaxAction action = (ProcessSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		ProcessRequest processRequest = new ProcessRequest();

		List<Process> processList = new ArrayList<Process>();
		Process process = new Process();
		process.setId(1);
		processList.add(process);

		processRequest.setProcessList(processList);

		SessionAuthenticationTestUtil.setMockSession();

		ProcessBCFMockImpl bcf = (ProcessBCFMockImpl)action.getProcessBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		proxy.execute();

		/** Asserts **/
		assertNotNull(MSG_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		proxy = getActionProxy("/process/getProcessById.action");
		action = (ProcessSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		processRequest = null;

		SessionAuthenticationTestUtil.setMockSession();

		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		proxy.execute();

		/** Asserts **/
		assertNotNull(MSG_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

	}

	/**
	 * Test retry unreachable.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testSummaryProcess() throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();
		SessionAuthenticationTestUtil.setMockSession();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		ActionProxy proxy = getActionProxy("/process/include.summary.action");

		ProcessSearchAjaxAction action = (ProcessSearchAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		ProcessBCFMockImpl bcf = (ProcessBCFMockImpl)action.getProcessBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);

		/** Asserts **/
		String actionResponse = proxy.execute();
		assertNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/process/include.summary.action");
		action = (ProcessSearchAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		ProcessRequest request = new ProcessRequest();

		Process process = new Process();
		process.setId(1);

		request.setProcess(process);
		action.setProcessRequest(request);
		// Create REQUEST mock ---- END

		bcf = (ProcessBCFMockImpl)action.getProcessBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());
	}

	@Test
	public void testInsertProcess() throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();
		SessionAuthenticationTestUtil.setMockSession();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		ActionProxy proxy = getActionProxy("/process/insertProcess.action");

		ProcessSearchAjaxAction action = (ProcessSearchAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		ProcessBCFMockImpl bcf = (ProcessBCFMockImpl)action.getProcessBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);

		/** Asserts **/
		String actionResponse = proxy.execute();
		assertNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/process/insertProcess.action");
		action = (ProcessSearchAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		LightSelectionRequest request = new LightSelectionRequest();

		ArrayList<Integer> ids = new ArrayList<Integer>();
		ids.add(0);
		ids.add(1);
		ids.add(2);

		request.setSelectionPaginationIds(ids);
		action.setLightSelectionRequest(request);
		// Create REQUEST mock ---- END

		bcf = (ProcessBCFMockImpl)action.getProcessBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);

	}

	@Test
	public void testGenerateFileCSV() throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();
		SessionAuthenticationTestUtil.setMockSession();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		ActionProxy proxy = getActionProxy("/process/generateFile.action");

		ProcessSearchAjaxAction action = (ProcessSearchAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		ProcessBCFMockImpl bcf = (ProcessBCFMockImpl)action.getProcessBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);

		/** Asserts **/
		String actionResponse = proxy.execute();
		assertNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/process/generateFile.action");
		action = (ProcessSearchAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		// Create REQUEST mock ---- BEGIN
		InquiryProcessRequest inquiryProcessRequest = new InquiryProcessRequest();

		inquiryProcessRequest.setEndRow(0);
		inquiryProcessRequest.setPageSize(15);
		inquiryProcessRequest.setStartPage(0);

		action.setInquiryProcessRequest(inquiryProcessRequest);
		// Create REQUEST mock ---- END

		bcf = (ProcessBCFMockImpl)action.getProcessBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);

	}
}
