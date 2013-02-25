package com.sensus.mlc.wui.group.unittest.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.group.model.response.InquiryGroupResponse;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.group.action.GroupSearchAjaxAction;

/**
 * Test GroupSearchAction.
 * For some basic use examples of the Struts Test case, see
 * https://cwiki.apache.org/WW/struts-2-junit-plugin-tutorial.html.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class GroupSearchAjaxActionTest extends StrutsSpringTestCase
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

	/** The Constant MESSAGE_RESPONSE_OBJECT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

	/** The Constant MESSAGE_RESPONSE_OBJECT_NOT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_NOT_PRESENT = "Response object not present";

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Empty.
	 */
	@Test
	public void empty()
	{
		assertTrue(true);
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

		ActionProxy proxy = getActionProxy("/group/search.action");
		GroupSearchAjaxAction action = (GroupSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		InquiryPaginationRequest inquiryPaginationRequest = new InquiryPaginationRequest();

		inquiryPaginationRequest.setEndRow(0);
		inquiryPaginationRequest.setPageSize(15);
		inquiryPaginationRequest.setStartPage(0);

		action.setInquiryPaginationRequest(inquiryPaginationRequest);

		SessionAuthenticationTestUtil.setMockSession();

		GroupBCFMockImpl bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		String response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		InquiryGroupResponse inquiryGroupResponse = (InquiryGroupResponse)action.getResponse();

		assertTrue("Result Count", inquiryGroupResponse.getGroups().size() != 0);
		assertTrue("Result Count", inquiryGroupResponse.getGroups().size() == 15);

		/***********************************
		 * 
		 * TESTING SUCCESS - Other Page Size
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/group/search.action");
		action = (GroupSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		inquiryPaginationRequest = new InquiryLightRequest();

		inquiryPaginationRequest.setEndRow(0);
		inquiryPaginationRequest.setPageSize(5);
		inquiryPaginationRequest.setStartPage(0);

		action.setInquiryPaginationRequest(inquiryPaginationRequest);

		SessionAuthenticationTestUtil.setMockSession();

		bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		inquiryGroupResponse = (InquiryGroupResponse)action.getResponse();

		assertTrue("Result Count", inquiryGroupResponse.getGroups().size() != 0);
		assertTrue("Result Count", inquiryGroupResponse.getGroups().size() == 5);

		/***********************************
		 * 
		 * TESTING EMPTY RESULTS
		 * 
		 ***********************************/

		logger.debug("TESTING EMPTY RESULTS");

		proxy = getActionProxy("/group/search.action");
		action = (GroupSearchAjaxAction)proxy.getAction();

		inquiryPaginationRequest = new InquiryPaginationRequest();

		inquiryPaginationRequest.setEndRow(0);
		inquiryPaginationRequest.setPageSize(0);
		inquiryPaginationRequest.setStartPage(0);

		action.setInquiryPaginationRequest(inquiryPaginationRequest);

		SessionAuthenticationTestUtil.setMockSession();

		bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_EMPTY);
		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		inquiryGroupResponse = (InquiryGroupResponse)action.getResponse();

		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, inquiryGroupResponse.getGroups());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		proxy = getActionProxy("/group/search.action");
		action = (GroupSearchAjaxAction)proxy.getAction();

		inquiryPaginationRequest = new InquiryLightRequest();

		action.setInquiryPaginationRequest(inquiryPaginationRequest);

		bcf = (GroupBCFMockImpl)action.getGroupBCF();
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

		proxy = getActionProxy("/group/search.action");
		action = (GroupSearchAjaxAction)proxy.getAction();
		bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);
		response = proxy.execute();

		/** Asserts **/
		assertNull("No Object", action.getResponse());
	}
}
