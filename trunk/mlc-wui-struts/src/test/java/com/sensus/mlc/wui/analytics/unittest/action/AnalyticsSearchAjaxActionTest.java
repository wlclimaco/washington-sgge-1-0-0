package com.sensus.mlc.wui.analytics.unittest.action;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.analytics.model.AnalyticsDateTypeEnum;
import com.sensus.mlc.analytics.model.AnalyticsGroup;
import com.sensus.mlc.analytics.model.AnalyticsTypeEnum;
import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.response.AnalyticsResponse;
import com.sensus.mlc.wui.analytics.action.AnalyticsSearchAjaxAction;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.ActionPaginationUtil;
import com.sensus.mlc.wui.base.util.Constants;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;

public class AnalyticsSearchAjaxActionTest extends StrutsSpringTestCase
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
	private final Logger logger = Logger.getLogger(this.getClass());

	@Test
	public void testGenerateFileCSV() throws Exception
	{

		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		// Test success
		logger.debug("Testing success");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_TYPE, "1");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_GROUP, "1");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_END, "07/01/2011");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_INIT, "01/02/2011");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_TYPE, "1d");
		request.setParameter(ActionPaginationUtil.DISPLAY_LENGTH, "10");
		request.setParameter(ActionPaginationUtil.DISPLAY_START, "0");
		request.setParameter(ActionPaginationUtil.SORT_COL, "0");
		request.setParameter(ActionPaginationUtil.SORT_DIR, "asc");
		request.setParameter(ActionPaginationUtil.TOTAL_ROWS, "10");

		ActionProxy proxy = getActionProxy("/analytics/generateFile.action");
		AnalyticsSearchAjaxAction action = (AnalyticsSearchAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		SessionAuthenticationTestUtil.setMockSession();

		AnalyticsBCFMockImpl bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getAnalyticsSearchResult());
		String response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getAnalyticsSearchResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_OK, action.getAnalyticsSearchResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getAnalyticsSearchResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getAnalyticsSearchResult().getMessages().size());

		// Testing empty results
		logger.debug("Testing success with no results");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_TYPE, "1");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_GROUP, "1");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_END, "7/1/2000");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_INIT, "1/1/2000");

		proxy = getActionProxy("/analytics/generateFile.action");
		action = (AnalyticsSearchAjaxAction)proxy.getAction();
		bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_EMPTY);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getAnalyticsSearchResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getAnalyticsSearchResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_OK, action.getAnalyticsSearchResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getAnalyticsSearchResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getAnalyticsSearchResult().getMessages().size());
		assertEquals("Result Count", null, action.getAnalyticsSearchResult().getResultValue());

		// Testing failure
		logger.debug("Testing failure");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_GROUP, "0");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_TYPE, "1");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_GROUP, "1");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_END, "7/1/2000");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_INIT, "1/1/2000");

		proxy = getActionProxy("/analytics/generateFile.action");
		action = (AnalyticsSearchAjaxAction)proxy.getAction();
		bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getAnalyticsSearchResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getAnalyticsSearchResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_FAIL, action.getAnalyticsSearchResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getAnalyticsSearchResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 2, action.getAnalyticsSearchResult().getMessages().size());
		assertEquals("Result Count", null, action.getAnalyticsSearchResult().getResultValue());

		// Testing exception
		logger.debug("Testing exception");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_GROUP, "0");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_TYPE, "1");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_GROUP, "1");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_END, "7/1/2000");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_INIT, "1/1/2000");

		proxy = getActionProxy("/analytics/generateFile.action");
		action = (AnalyticsSearchAjaxAction)proxy.getAction();
		bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getAnalyticsSearchResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getAnalyticsSearchResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_FAIL, action.getAnalyticsSearchResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getAnalyticsSearchResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 0, action.getAnalyticsSearchResult().getMessages().size());
		assertEquals("Result Count", null, action.getAnalyticsSearchResult().getResultValue());
	}

	/**
	 * Test search.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testSearch() throws Exception
	{

		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		// Test success
		logger.debug("Testing success");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_TYPE, "1");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_GROUP, "1");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_END, "07/01/2011");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_INIT, "01/02/2011");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_TYPE, "1d");
		request.setParameter(ActionPaginationUtil.DISPLAY_LENGTH, "10");
		request.setParameter(ActionPaginationUtil.DISPLAY_START, "0");
		request.setParameter(ActionPaginationUtil.SORT_COL, "0");
		request.setParameter(ActionPaginationUtil.SORT_DIR, "asc");
		request.setParameter(ActionPaginationUtil.TOTAL_ROWS, "10");

		ActionProxy proxy = getActionProxy("/analytics/search.action");
		AnalyticsSearchAjaxAction action = (AnalyticsSearchAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		SessionAuthenticationTestUtil.setMockSession();

		AnalyticsBCFMockImpl bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getAnalyticsSearchResult());
		String response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getAnalyticsSearchResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_OK, action.getAnalyticsSearchResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getAnalyticsSearchResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getAnalyticsSearchResult().getMessages().size());
		assertEquals("Result Count", bcf.getPageSize().intValue(), action
				.getAnalyticsSearchResult().getAaData().length);

		// Test Succes type 4
		logger.debug("Testing success");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_TYPE, "4");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_GROUP, "1");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_END, "07/01/2011");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_INIT, "01/02/2011");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_TYPE, "1d");
		request.setParameter(ActionPaginationUtil.DISPLAY_LENGTH, "10");
		request.setParameter(ActionPaginationUtil.DISPLAY_START, "0");
		request.setParameter(ActionPaginationUtil.SORT_COL, "0");
		request.setParameter(ActionPaginationUtil.SORT_DIR, "asc");
		request.setParameter(ActionPaginationUtil.TOTAL_ROWS, "10");

		proxy = getActionProxy("/analytics/search.action");
		action = (AnalyticsSearchAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		SessionAuthenticationTestUtil.setMockSession();

		bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getAnalyticsSearchResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getAnalyticsSearchResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_OK, action.getAnalyticsSearchResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getAnalyticsSearchResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getAnalyticsSearchResult().getMessages().size());
		assertEquals("Result Count", bcf.getPageSize().intValue(), action
				.getAnalyticsSearchResult().getAaData().length);

		// Testing empty results
		logger.debug("Testing success with no results");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_TYPE, "1");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_GROUP, "1");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_END, "7/1/2000");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_INIT, "1/1/2000");

		proxy = getActionProxy("/analytics/search.action");
		action = (AnalyticsSearchAjaxAction)proxy.getAction();
		bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_EMPTY);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getAnalyticsSearchResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getAnalyticsSearchResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_OK, action.getAnalyticsSearchResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getAnalyticsSearchResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getAnalyticsSearchResult().getMessages().size());
		assertEquals("Result Count", 0, action.getAnalyticsSearchResult().getAaData().length);

		// Testing failure
		logger.debug("Testing failure");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_GROUP, "0");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_TYPE, "1");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_GROUP, "1");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_END, "7/1/2000");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_INIT, "1/1/2000");

		proxy = getActionProxy("/analytics/search.action");
		action = (AnalyticsSearchAjaxAction)proxy.getAction();
		bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getAnalyticsSearchResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getAnalyticsSearchResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_FAIL, action.getAnalyticsSearchResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getAnalyticsSearchResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 2, action.getAnalyticsSearchResult().getMessages().size());
		assertEquals("Result Count", 0, action.getAnalyticsSearchResult().getAaData().length);

		// Testing exception
		logger.debug("Testing exception");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_GROUP, "0");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_TYPE, "1");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_GROUP, "1");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_END, "7/1/2000");
		request.setParameter(AnalyticsSearchAjaxAction.HASH_DATE_INIT, "1/1/2000");

		proxy = getActionProxy("/analytics/search.action");
		action = (AnalyticsSearchAjaxAction)proxy.getAction();
		bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getAnalyticsSearchResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getAnalyticsSearchResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_FAIL, action.getAnalyticsSearchResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getAnalyticsSearchResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 0, action.getAnalyticsSearchResult().getMessages().size());
		assertEquals("Result Count", 0, action.getAnalyticsSearchResult().getAaData().length);
	}

	@Test
	public void testSearchGraphic() throws Exception
	{
		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		ActionProxy proxy = getActionProxy("/analytics/searchGraphic.action");
		AnalyticsSearchAjaxAction action = (AnalyticsSearchAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		AnalyticsRequest analyticsRequest = new AnalyticsRequest();

		analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_WEEK);
		analyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
		analyticsRequest.setEndDate(new Date());
		analyticsRequest.setInitialDate(new Date());
		AnalyticsGroup analyticsGroup = new AnalyticsGroup();

		analyticsGroup.setId(new Integer(777));

		analyticsRequest.setGroup(analyticsGroup);

		action.setAnalyticsRequest(analyticsRequest);

		AnalyticsBCFMockImpl bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		String response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		AnalyticsResponse analyticsResp = (AnalyticsResponse)action.getResponse();

		assertTrue("Result Count", analyticsResp.getColumns().size() != 0);

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		proxy = getActionProxy("/analytics/searchGraphic.action");
		action = (AnalyticsSearchAjaxAction)proxy.getAction();
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

		proxy = getActionProxy("/analytics/searchGraphic.action");
		action = (AnalyticsSearchAjaxAction)proxy.getAction();
		bcf = (AnalyticsBCFMockImpl)action.getAnalyticsBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);

		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getResponse());

	}
}
