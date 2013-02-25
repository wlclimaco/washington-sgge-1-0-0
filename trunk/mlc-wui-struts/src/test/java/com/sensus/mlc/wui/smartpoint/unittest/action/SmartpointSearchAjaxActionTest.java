package com.sensus.mlc.wui.smartpoint.unittest.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.ActionPaginationUtil;
import com.sensus.mlc.wui.base.util.Constants;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.smartpoint.action.SmartpointSearchAjaxAction;
import com.sensus.mlc.wui.smartpoint.model.SmartpointSearchTypeEnum;

/**
 * Test SmartpointSearchAjaxAction.
 * For some basic use examples of the Struts Test case, see
 * https://cwiki.apache.org/WW/struts-2-junit-plugin-tutorial.html.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class SmartpointSearchAjaxActionTest extends StrutsSpringTestCase
{

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts2.StrutsSpringTestCase#getContextLocations()
	 */
	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	/** The Constant MESSAGE_SUCCESS_STATUS. */
	private static final String MESSAGE_SUCCESS_STATUS = "Success Status";

	/** The Constant MESSAGE_MESSAGE_COUNT. */
	private static final String MESSAGE_MESSAGE_COUNT = "Message Count";

	/** The Constant MESSAGE_MESSAGES_SET. */
	private static final String MESSAGE_MESSAGES_SET = "Messages Set";

	/** The Constant MESSAGE_RESPONSE_OBJECT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

	/** The Constant MESSAGE_RESPONSE_OBJECT_NOT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_NOT_PRESENT = "Response object not present";

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	@Test
	public void testGenerateFileCSV() throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		request.setParameter(SmartpointSearchAjaxAction.HASH_TAB, "all");
		request.setParameter(SmartpointSearchAjaxAction.HASH_LONG_RUNNING_PROCESS, "0");
		request.setParameter(ActionPaginationUtil.DISPLAY_LENGTH, "10");
		request.setParameter(ActionPaginationUtil.DISPLAY_START, "0");
		request.setParameter(ActionPaginationUtil.SORT_COL, "0");
		request.setParameter(ActionPaginationUtil.SORT_DIR, "asc");
		request.setParameter(ActionPaginationUtil.TOTAL_ROWS, "10");
		request.setParameter("listColumn", "pole_id,rni_id,lamp_type,date_added,city_name,protected,status");

		ActionProxy proxy = getActionProxy("/smartpoint/generateFile.action");
		SmartpointSearchAjaxAction action = (SmartpointSearchAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		SessionAuthenticationTestUtil.setMockSession();

		SmartPointAccessorBCFMockImpl bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getSmartpointSearchResult());
		String response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getSmartpointSearchResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_OK, action.getSmartpointSearchResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getSmartpointSearchResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getSmartpointSearchResult().getMessages().size());

		// Test success with with motion sensor search
		logger.debug("Testing success with invalid parameters");
		request.setParameter(SmartpointSearchAjaxAction.HASH_TAB, SmartpointSearchTypeEnum.MOTIONSENSORS.getKey());
		request.setParameter(SmartpointSearchAjaxAction.HASH_LONG_RUNNING_PROCESS, "0");
		proxy = getActionProxy("/smartpoint/generateFile.action");
		action = (SmartpointSearchAjaxAction)proxy.getAction();
		bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getSmartpointSearchResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getSmartpointSearchResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_OK, action.getSmartpointSearchResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getSmartpointSearchResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 0, action.getSmartpointSearchResult().getMessages().size());
		assertEquals("Result Count", null, action.getSmartpointSearchResult().getResultValue());

		// Test success with with invalid parameters
		logger.debug("Testing success with invalid parameters");
		request.setParameter(SmartpointSearchAjaxAction.HASH_TAB, "x");
		request.setParameter(SmartpointSearchAjaxAction.HASH_LONG_RUNNING_PROCESS, "x");
		proxy = getActionProxy("/smartpoint/generateFile.action");
		action = (SmartpointSearchAjaxAction)proxy.getAction();
		bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getSmartpointSearchResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getSmartpointSearchResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_FAIL, action.getSmartpointSearchResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getSmartpointSearchResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 0, action.getSmartpointSearchResult().getMessages().size());

		// Testing empty results
		logger.debug("Testing success with no results");
		request.setParameter(SmartpointSearchAjaxAction.HASH_TAB, "all");
		request.setParameter(SmartpointSearchAjaxAction.HASH_LONG_RUNNING_PROCESS, "0");
		proxy = getActionProxy("/smartpoint/generateFile.action");
		action = (SmartpointSearchAjaxAction)proxy.getAction();
		bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_EMPTY);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getSmartpointSearchResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getSmartpointSearchResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_OK, action.getSmartpointSearchResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getSmartpointSearchResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getSmartpointSearchResult().getMessages().size());
		assertEquals("Result Count", null, action.getSmartpointSearchResult().getResultValue());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		// Test failure due to missing light intensity
		logger.debug("TESTING FAILURE");

		request.setParameter(SmartpointSearchAjaxAction.HASH_TAB, "all");
		request.setParameter(SmartpointSearchAjaxAction.HASH_LONG_RUNNING_PROCESS, "0");
		proxy = getActionProxy("/smartpoint/generateFile.action");
		action = (SmartpointSearchAjaxAction)proxy.getAction();
		bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getSmartpointSearchResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getSmartpointSearchResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_FAIL, action.getSmartpointSearchResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getSmartpointSearchResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 2, action.getSmartpointSearchResult().getMessages().size());
		assertEquals("Result Count", null, action.getSmartpointSearchResult().getResultValue());

		// Testing exception
		logger.debug("Testing exception");
		request.setParameter(SmartpointSearchAjaxAction.HASH_TAB, "all");
		request.setParameter(SmartpointSearchAjaxAction.HASH_LONG_RUNNING_PROCESS, "0");
		proxy = getActionProxy("/smartpoint/generateFile.action");
		action = (SmartpointSearchAjaxAction)proxy.getAction();
		bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getSmartpointSearchResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getSmartpointSearchResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_FAIL, action.getSmartpointSearchResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getSmartpointSearchResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 0, action.getSmartpointSearchResult().getMessages().size());
		assertEquals("Result Count", null, action.getSmartpointSearchResult().getResultValue());
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

		ActionProxy proxy = getActionProxy("/smartpoint/search.action");
		SmartpointSearchAjaxAction action = (SmartpointSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		InquiryLightRequest inquiryLightRequest = new InquiryLightRequest();

		inquiryLightRequest.setEndRow(0);
		inquiryLightRequest.setPageSize(15);
		inquiryLightRequest.setStartPage(0);

		action.setInquiryLightRequest(inquiryLightRequest);

		SessionAuthenticationTestUtil.setMockSession();

		SmartPointAccessorBCFMockImpl bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		String response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		InquiryLightResponse inquiryLightResponse = (InquiryLightResponse)action.getResponse();

		assertTrue("Result Count", inquiryLightResponse.getLights().size() != 0);
		assertTrue("Result Count", inquiryLightResponse.getLights().size() == 15);

		/***********************************
		 * 
		 * TESTING SUCCESS - Other Page Size
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/smartpoint/search.action");
		action = (SmartpointSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		inquiryLightRequest = new InquiryLightRequest();

		inquiryLightRequest.setEndRow(0);
		inquiryLightRequest.setPageSize(5);
		inquiryLightRequest.setStartPage(0);

		action.setInquiryLightRequest(inquiryLightRequest);

		SessionAuthenticationTestUtil.setMockSession();

		bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		inquiryLightResponse = (InquiryLightResponse)action.getResponse();

		assertTrue("Result Count", inquiryLightResponse.getLights().size() != 0);
		assertTrue("Result Count", inquiryLightResponse.getLights().size() == 5);

		/***********************************
		 * 
		 * TESTING EMPTY RESULTS
		 * 
		 ***********************************/

		logger.debug("TESTING EMPTY RESULTS");

		proxy = getActionProxy("/smartpoint/search.action");
		action = (SmartpointSearchAjaxAction)proxy.getAction();

		inquiryLightRequest = new InquiryLightRequest();

		inquiryLightRequest.setEndRow(0);
		inquiryLightRequest.setPageSize(0);
		inquiryLightRequest.setStartPage(0);

		action.setInquiryLightRequest(inquiryLightRequest);

		SessionAuthenticationTestUtil.setMockSession();

		bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_EMPTY);
		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		inquiryLightResponse = (InquiryLightResponse)action.getResponse();

		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, inquiryLightResponse.getLights());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		proxy = getActionProxy("/smartpoint/search.action");
		action = (SmartpointSearchAjaxAction)proxy.getAction();

		inquiryLightRequest = new InquiryLightRequest();

		action.setInquiryLightRequest(inquiryLightRequest);

		bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);
		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Failure", false, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING EXCEPTION
		 * 
		 ***********************************/

		logger.debug("TESTING EXCEPTION");

		proxy = getActionProxy("/smartpoint/search.action");
		action = (SmartpointSearchAjaxAction)proxy.getAction();
		bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);
		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNull("No Object", action.getResponse());
	}

}
