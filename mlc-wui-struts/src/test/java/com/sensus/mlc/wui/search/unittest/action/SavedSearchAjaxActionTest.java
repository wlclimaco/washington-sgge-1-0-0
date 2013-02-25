package com.sensus.mlc.wui.search.unittest.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.search.action.SavedSearchAjaxAction;
import com.sensus.mlc.wui.smartpoint.unittest.action.SmartPointAccessorBCFMockImpl;

/**
 * Test SavedSearchAjaxAction.
 * For some basic use examples of the Struts Test case, see
 * https://cwiki.apache.org/WW/struts-2-junit-plugin-tutorial.html.
 * 
 * @author Cristiane Cobo
 * 
 */
public class SavedSearchAjaxActionTest extends StrutsSpringTestCase
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

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

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

		ActionProxy proxy = getActionProxy("/search/savedSearch.action");
		SavedSearchAjaxAction action = (SavedSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		InquiryPaginationRequest inquiryPaginationRequest = new InquiryPaginationRequest();

		inquiryPaginationRequest.setEndRow(0);
		inquiryPaginationRequest.setPageSize(15);
		inquiryPaginationRequest.setStartPage(0);

		action.setInquiryPaginationRequest(inquiryPaginationRequest);

		SessionAuthenticationTestUtil.setMockSession();

		SmartPointAccessorBCFMockImpl bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		String response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		action.getResponse();

		/***********************************
		 * 
		 * TESTING SUCCESS - Other Page Size
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/search/savedSearch.action");
		action = (SavedSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		inquiryPaginationRequest = new InquiryLightRequest();

		inquiryPaginationRequest.setEndRow(0);
		inquiryPaginationRequest.setPageSize(5);
		inquiryPaginationRequest.setStartPage(0);

		action.setInquiryPaginationRequest(inquiryPaginationRequest);

		SessionAuthenticationTestUtil.setMockSession();

		bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING EMPTY RESULTS
		 * 
		 ***********************************/

		logger.debug("TESTING EMPTY RESULTS");

		proxy = getActionProxy("/search/savedSearch.action");
		action = (SavedSearchAjaxAction)proxy.getAction();

		inquiryPaginationRequest = new InquiryPaginationRequest();

		inquiryPaginationRequest.setEndRow(0);
		inquiryPaginationRequest.setPageSize(0);
		inquiryPaginationRequest.setStartPage(0);

		action.setInquiryPaginationRequest(inquiryPaginationRequest);

		SessionAuthenticationTestUtil.setMockSession();

		bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_EMPTY);
		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		proxy = getActionProxy("/search/savedSearch.action");
		action = (SavedSearchAjaxAction)proxy.getAction();

		inquiryPaginationRequest = new InquiryLightRequest();

		action.setInquiryPaginationRequest(inquiryPaginationRequest);

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

		proxy = getActionProxy("/search/savedSearch.action");
		action = (SavedSearchAjaxAction)proxy.getAction();
		bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);
		response = proxy.execute();

		/** Asserts **/
		assertNull("No Object", action.getResponse());
	}
}
