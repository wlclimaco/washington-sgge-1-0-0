package com.sensus.mlc.wui.search.unittest.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.search.action.SearchAjaxAction;
import com.sensus.mlc.wui.smartpoint.unittest.action.SmartPointUpdaterBCFMockImpl;

/**
 * Test the functionality of SearchAjaxAction.
 * 
 * For some basic use examples of the Struts Test case, see
 * https://cwiki.apache.org/WW/struts-2-junit-plugin-tutorial.html.
 * 
 * @author Cristiane Cobo
 * 
 */
public class SearchAjaxActionTest extends StrutsSpringTestCase
{

	/**
	 * Load the Spring configuration.
	 * 
	 * @return the context locations
	 */
	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

	/** The Constant MESSAGE_RESPONSE_OBJECT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Test delete search.
	 * 
	 * @throws Exception the exception
	 */
	public void testDeleteSearch() throws Exception
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

		ActionProxy proxy = getActionProxy("/search/deleteSearch.action");

		SearchAjaxAction action = (SearchAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		SmartPointUpdaterBCFMockImpl bcf = (SmartPointUpdaterBCFMockImpl)action.getSmartPointUpdaterBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);

		/** Asserts **/
		String actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/search/deleteSearch.action");
		action = (SearchAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		CustomSearchRequest request = new CustomSearchRequest();

		CustomSearch search = new CustomSearch();
		search.setId(1);

		request.setCustomSearch(search);

		action.setCustomSearchRequest(request);
		// Create REQUEST mock ---- END

		bcf = (SmartPointUpdaterBCFMockImpl)action.getSmartPointUpdaterBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

	}
}
