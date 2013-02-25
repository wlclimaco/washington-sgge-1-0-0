package com.sensus.mlc.wui.dashboard.unittest.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.response.AnalyticsResponse;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.wui.analytics.unittest.action.AnalyticsBCFMockImpl;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.dashboard.action.DashboardSearchAjaxAction;

/**
 * The Class DashboardSearchAjaxActionTest.
 * 
 * @author Alexandre Tiveron
 */
public class DashboardSearchAjaxActionTest extends StrutsSpringTestCase
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
		 * TESTING SUCCESS - LAMP FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		ActionProxy proxy = getActionProxy("/dashboard/search.action");
		DashboardSearchAjaxAction action = (DashboardSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);
		AnalyticsRequest analyticsRequest = new AnalyticsRequest();
		analyticsRequest.setStatusExceptionTypeEnum(StatusExceptionTypeEnum.LAMP_FAILURE);
		action.setAnalyticsRequest(analyticsRequest);

		AnalyticsBCFMockImpl bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		String response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		AnalyticsResponse analyticsResponse = (AnalyticsResponse)action.getResponse();

		assertTrue("Result Count", analyticsResponse.getLights().size() != 0);

		/***********************************
		 * 
		 * TESTING SUCCESS - POWER FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/dashboard/search.action");
		action = (DashboardSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);
		analyticsRequest = new AnalyticsRequest();
		analyticsRequest.setStatusExceptionTypeEnum(StatusExceptionTypeEnum.POWER_FAILURE);
		action.setAnalyticsRequest(analyticsRequest);

		bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		analyticsResponse = (AnalyticsResponse)action.getResponse();

		assertTrue("Result Count", analyticsResponse.getLights().size() != 0);

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		proxy = getActionProxy("/dashboard/search.action");
		action = (DashboardSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		analyticsRequest = new AnalyticsRequest();
		action.setAnalyticsRequest(analyticsRequest);

		bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
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

		proxy = getActionProxy("/dashboard/search.action");
		action = (DashboardSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);

		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNull("No Object", action.getResponse());
	}

	@Test
	public void testLoadHeaderData() throws Exception
	{
		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		ActionProxy proxy = getActionProxy("/dashboard/ajax.loadHeaderData.action");
		DashboardSearchAjaxAction action = (DashboardSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);
		AnalyticsRequest analyticsRequest = new AnalyticsRequest();
		action.setAnalyticsRequest(analyticsRequest);

		AnalyticsBCFMockImpl bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		String response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		AnalyticsResponse analyticsResponse = (AnalyticsResponse)action.getResponse();

		assertTrue("Result Count", analyticsResponse.getColumns().size() != 0);

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		proxy = getActionProxy("/dashboard/ajax.loadHeaderData.action");
		action = (DashboardSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		analyticsRequest = new AnalyticsRequest();
		action.setAnalyticsRequest(analyticsRequest);

		bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
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

		proxy = getActionProxy("/dashboard/ajax.loadHeaderData.action");
		action = (DashboardSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);

		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNull("No Object", action.getResponse());

	}

	@Test
	public void testLoadLightControlTable() throws Exception
	{
		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		ActionProxy proxy = getActionProxy("/dashboard/ajax.loadLightControlTable.action");
		DashboardSearchAjaxAction action = (DashboardSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);
		AnalyticsRequest analyticsRequest = new AnalyticsRequest();
		action.setAnalyticsRequest(analyticsRequest);

		AnalyticsBCFMockImpl bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		String response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		AnalyticsResponse analyticsResponse = (AnalyticsResponse)action.getResponse();

		assertTrue("Result Count", analyticsResponse.getColumns().size() != 0);

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		proxy = getActionProxy("/dashboard/ajax.loadLightControlTable.action");
		action = (DashboardSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		analyticsRequest = new AnalyticsRequest();
		action.setAnalyticsRequest(analyticsRequest);

		bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
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

		proxy = getActionProxy("/dashboard/ajax.loadLightControlTable.action");
		action = (DashboardSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);

		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNull("No Object", action.getResponse());

	}

	@Test
	public void testLoadHighchartsData() throws Exception
	{
		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		ActionProxy proxy = getActionProxy("/dashboard/ajax.loadHighchartsData.action");
		DashboardSearchAjaxAction action = (DashboardSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);
		AnalyticsRequest analyticsRequest = new AnalyticsRequest();
		action.setAnalyticsRequest(analyticsRequest);

		AnalyticsBCFMockImpl bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		String response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		AnalyticsResponse analyticsResponse = (AnalyticsResponse)action.getResponse();

		assertTrue("Result Count", analyticsResponse.getAlertsByType().size() != 0);

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		proxy = getActionProxy("/dashboard/ajax.loadHighchartsData.action");
		action = (DashboardSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		analyticsRequest = new AnalyticsRequest();
		action.setAnalyticsRequest(analyticsRequest);

		bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
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

		proxy = getActionProxy("/dashboard/ajax.loadHighchartsData.action");
		action = (DashboardSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);

		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNull("No Object", action.getResponse());
	}
}
