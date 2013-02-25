package com.sensus.mlc.wui.process.unittest.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.Constants;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.process.action.ProcessAjaxAction;

/**
 * The Class ProcessAjaxActionTest.
 */
public class ProcessAjaxActionTest extends StrutsSpringTestCase
{

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant TEN. */
	private static final Integer TEN = 10;

	/** The Constant HUNDRED. */
	private static final Integer HUNDRED = 100;

	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MSG_STRUTS_OUTCOME = "Struts outcome";

	/** The Constant MSG_JSON_SUCCESS. */
	private static final String MSG_JSON_SUCCESS = "Json Success Message";

	/** The Constant STRUTS_OUTCOME. */
	private static final String STRUTS_OUTCOME = MSG_STRUTS_OUTCOME;

	/** The Constant TEST_SUCESS. */
	private static final String TEST_SUCCESS = "Testing success";

	/** The Constant TEST_Exception. */
	private static final String TEST_EXCEPTION = "Testing exception";

	/** The Constant MESSAGE_RESPONSE_OBJECT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

	/** The Constant MESSAGE_RESPONSE_OBJECT_NOT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_NOT_PRESENT = "Response object not present";

	/** The Constant MESSAGE_SUCCESS_STATUS. */
	private static final String MESSAGE_SUCCESS_STATUS = "Message Success Status";

	/** The Constant MESSAGE_MESSAGES_SET. */
	private static final String MESSAGE_MESSAGES_SET = "Messages Set";

	/** The Constant MESSAGE_MESSAGE_COUNT. */
	private static final String MESSAGE_MESSAGE_COUNT = "Message Count";

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
	 * Gets the action.
	 * 
	 * @param methodAction the method action
	 * @return the action
	 * @throws Exception the exception
	 */
	private ProcessAjaxAction getAction(String methodAction) throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		ProcessAjaxAction action =
				(ProcessAjaxAction)getActionProxy("/process/" + methodAction + ".action")
						.getAction();

		action.setServletRequest(request);

		return action;
	}

	/**
	 * Test check long running process.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testCheckLongRunningProcess() throws Exception
	{
		/**
		 * Name of the Action of the Struts
		 */
		ProcessAjaxAction action = getAction("include.checkLongRunningProcess");

		action.setProcessRequest(new ProcessRequest());

		ProcessBCFMockImpl processBCFMock = (ProcessBCFMockImpl)action.getProcessBCF();

		SessionAuthenticationTestUtil.setMockSession();

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/
		logger.debug(TEST_SUCCESS);
		processBCFMock.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, action.checkLongRunningProcess());
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals(MESSAGE_SUCCESS_STATUS, true, action.getResponse().isOperationSuccess());

	}

	/**
	 * TestCheck rni.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testCheckRni() throws Exception
	{
		/**
		 * Name of the Action of the Struts
		 */
		ProcessAjaxAction action = getAction("checkRni");

		action.setProcessRequest(new ProcessRequest());

		ProcessBCFMockImpl processBCFMock = (ProcessBCFMockImpl)action.getProcessBCF();

		SessionAuthenticationTestUtil.setMockSession();

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/
		logger.debug(TEST_SUCCESS);
		processBCFMock.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, action.checkRni());
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals(MESSAGE_SUCCESS_STATUS, true, action.getResponse().isOperationSuccess());

	}

	/**
	 * Test remove long running process.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testRemoveLongRunningProcess() throws Exception
	{
		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/
		logger.debug(TEST_SUCCESS);

		ProcessAjaxAction action = getAction("longRunningProcessRemove");

		ProcessRequest processRequest = new ProcessRequest();

		action.setProcessRequest(processRequest);

		ProcessBCFMockImpl processBCFMock = (ProcessBCFMockImpl)action.getProcessBCF();
		processBCFMock.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, action.removeLongRunningProcess());
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		action = getAction("longRunningProcessRemove");

		processRequest = new ProcessRequest();

		action.setProcessRequest(processRequest);

		processBCFMock = (ProcessBCFMockImpl)action.getProcessBCF();
		processBCFMock.setMode(BaseMockImpl.MODE_FAILURE);

		/** Asserts **/
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, action.removeLongRunningProcess());
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Failure", false, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING EXCEPTION
		 * 
		 ***********************************/
		logger.debug(TEST_EXCEPTION);

		action = getAction("longRunningProcessRemove");
		processBCFMock = (ProcessBCFMockImpl)action.getProcessBCF();
		processBCFMock.setMode(BaseMockImpl.MODE_EXCEPTION);
		/** Asserts **/
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, action.removeLongRunningProcess());
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getResponse());
	}

	/**
	 * Test retry unreachable.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testRetryUnreachable() throws Exception
	{
		/**
		 * Name of the Action of the Struts
		 */
		ProcessAjaxAction action = getAction("retryUnreacheable");

		Process process = new Process(10);
		ProcessRequest processRequest = new ProcessRequest();
		processRequest.setProcess(process);

		action.setProcessRequest(processRequest);

		ProcessBCFMockImpl processBCFMock = (ProcessBCFMockImpl)action.getProcessBCF();

		SessionAuthenticationTestUtil.setMockSession();

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/
		logger.debug(TEST_SUCCESS);
		processBCFMock.setMode(BaseMockImpl.MODE_SUCCESS);

		assertEquals(MSG_STRUTS_OUTCOME, Action.SUCCESS, action.retryUnreachable());
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals(MSG_JSON_SUCCESS, true, action.getResponse().isOperationSuccess());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getResponse().getMessageList());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getResponse().getMessageList().size());

	}

	/**
	 * Test abort process url.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testAbortProcessUrl() throws Exception
	{
		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug(TEST_SUCCESS);

		ProcessAjaxAction action = getAction("abortProcessUrl");
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		Process processParam = new Process();
		processParam.setId(30);

		ProcessRequest processRequest = new ProcessRequest();
		processRequest.setProcess(processParam);

		action.setProcessRequest(processRequest);

		ProcessBCFMockImpl processBCFMock = (ProcessBCFMockImpl)action.getProcessBCF();
		processBCFMock.setMode(BaseMockImpl.MODE_SUCCESS);

		assertEquals(MSG_STRUTS_OUTCOME, Action.SUCCESS, action.abortProcessUrl());
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		action = getAction("abortProcessUrl");
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		processRequest = new ProcessRequest();
		processParam = new Process();
		processRequest.setProcess(processParam);

		action.setProcessRequest(processRequest);

		processBCFMock = (ProcessBCFMockImpl)action.getProcessBCF();
		processBCFMock.setMode(BaseMockImpl.MODE_FAILURE);

		assertEquals(MSG_STRUTS_OUTCOME, Action.SUCCESS, action.abortProcessUrl());
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Failure", false, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING EXCEPTION
		 * 
		 ***********************************/

		logger.debug(TEST_EXCEPTION);

		action = getAction("abortProcessUrl");
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);
		processBCFMock = (ProcessBCFMockImpl)action.getProcessBCF();
		processBCFMock.setMode(BaseMockImpl.MODE_EXCEPTION);

		/** Asserts **/
		assertEquals(MSG_STRUTS_OUTCOME, Action.SUCCESS, action.abortProcessUrl());
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getResponse());
	}

	/**
	 * Test remove all selected.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testRemoveAllSelected() throws Exception
	{
		/**
		 * Name of the Action of the Struts
		 */
		ProcessAjaxAction action = getAction("longRunningProcessRemoveAll");

		ProcessBCFMockImpl processBCFMock = (ProcessBCFMockImpl)action.getProcessBCF();

		SessionAuthenticationTestUtil.setMockSession();

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/
		logger.debug(TEST_SUCCESS);
		processBCFMock.setMode(BaseMockImpl.MODE_SUCCESS);

		action.setProcessRequest(new ProcessRequest());

		assertEquals(MSG_STRUTS_OUTCOME, Action.SUCCESS, action.removeAllSelected());
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals(MSG_JSON_SUCCESS, true, action.getResponse().isOperationSuccess());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getResponse().getMessageList());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getResponse().getMessageList().size());

	}

	/**
	 * Test generate summary file csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testGenerateSummaryFileCSV() throws Exception
	{
		/**
		 * Name of the Action of the Struts
		 */
		ProcessAjaxAction action = getAction("generateSummaryFileCSV");

		ProcessBCFMockImpl processBCFMock = (ProcessBCFMockImpl)action.getProcessBCF();

		SessionAuthenticationTestUtil.setMockSession();

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/
		logger.debug(TEST_SUCCESS);
		processBCFMock.setMode(BaseMockImpl.MODE_SUCCESS);

		action.setId(HUNDRED);
		action.setIsSucess(true);

		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getProcessSearchResult());
		assertEquals(MSG_STRUTS_OUTCOME, Action.SUCCESS, action.generateSummaryFileCSV());
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getProcessSearchResult());
		assertEquals(MSG_JSON_SUCCESS, Constants.JSON_OK, action.getProcessSearchResult().getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getProcessSearchResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getProcessSearchResult().getMessages().size());

		/***********************************
		 * 
		 * TESTING EXCEPTION
		 * 
		 ***********************************/
		logger.debug(TEST_EXCEPTION);
		processBCFMock.setMode(BaseMockImpl.MODE_EXCEPTION);

		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getProcessSearchResult());
		assertEquals(MSG_STRUTS_OUTCOME, Action.SUCCESS, action.generateSummaryFileCSV());
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getProcessSearchResult());
		assertEquals(MSG_JSON_SUCCESS, Constants.JSON_FAIL, action.getProcessSearchResult().getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getProcessSearchResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 0, action.getProcessSearchResult().getMessages().size());
	}

	/**
	 * Test delete file.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testDeleteFile() throws Exception
	{
		/**
		 * Name of the Action of the Struts
		 */
		ProcessAjaxAction action = getAction("deleteFile");

		ProcessBCFMockImpl processBCFMock = (ProcessBCFMockImpl)action.getProcessBCF();

		SessionAuthenticationTestUtil.setMockSession();

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/
		logger.debug(TEST_SUCCESS);
		processBCFMock.setMode(BaseMockImpl.MODE_SUCCESS);

		action.setFileName(getName());
		action.setUpdateCSVDownloaded(false);

		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getResult());
		assertEquals(MSG_STRUTS_OUTCOME, Action.SUCCESS, action.deleteFile());
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResult());
	}

	/**
	 * Test update csv downloaded.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testUpdateCSVDownloaded() throws Exception
	{

	}

	/**
	 * Test download csv file.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testDownloadCSVFile() throws Exception
	{
		/**
		 * Name of the Action of the Struts
		 */
		ProcessAjaxAction action = getAction("downloadCSVFile");

		ProcessBCFMockImpl processBCFMock = (ProcessBCFMockImpl)action.getProcessBCF();

		SessionAuthenticationTestUtil.setMockSession();

		/***********************************
		 * 
		 * TESTING EXCEPTION
		 * 
		 ***********************************/
		logger.debug(TEST_EXCEPTION);
		processBCFMock.setMode(BaseMockImpl.MODE_EXCEPTION);

		action.setFileName(getName());

		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getProcessSearchResult());
		assertEquals(MSG_STRUTS_OUTCOME, Action.SUCCESS, action.downloadCSVFile());
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getProcessSearchResult());
		assertEquals(MSG_JSON_SUCCESS, Constants.JSON_FAIL, action.getProcessSearchResult().getResult());

	}
}
