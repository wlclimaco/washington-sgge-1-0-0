package com.sensus.mlc.wui.group.unittest.action;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.group.action.GroupAjaxAction;

/**
 * Test the functionality of GroupAjaxAction.
 * 
 * For some basic use examples of the Struts Test case, see
 * https://cwiki.apache.org/WW/struts-2-junit-plugin-tutorial.html.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class GroupAjaxActionTest extends StrutsSpringTestCase
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
	 * Test adding SmartPoints to Groups.
	 * 
	 * @throws Exception the exception
	 */
	public void testAddSmartpoints() throws Exception
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

		ActionProxy proxy = getActionProxy("/group/addSmartPoints.action");

		GroupAjaxAction action = (GroupAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		GroupBCFMockImpl bcf = (GroupBCFMockImpl)action.getGroupBCF();
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

		proxy = getActionProxy("/group/addSmartPoints.action");
		action = (GroupAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		GroupRequest groupReq = new GroupRequest();

		Group group = new Group();
		group.setId(1);

		groupReq.setGroup(group);
		action.setGroupRequest(groupReq);
		// Create REQUEST mock ---- END

		bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

	}

	/**
	 * Test delete group.
	 * 
	 * @throws Exception the exception
	 */
	public void testDeleteGroup() throws Exception
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

		ActionProxy proxy = getActionProxy("/group/deleteGroup.action");

		GroupAjaxAction action = (GroupAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		GroupRequest groupReq = new GroupRequest();
		action.setGroupRequest(groupReq);
		// Create REQUEST mock ---- END

		GroupBCFMockImpl bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);

		/** Asserts **/
		String actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/group/deleteGroup.action");
		action = (GroupAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		groupReq = new GroupRequest();

		Group group = new Group();
		group.setId(1);

		groupReq.setSelectionPaginationIds(new ArrayList<Integer>());
		groupReq.setGroup(group);
		action.setGroupRequest(groupReq);
		// Create REQUEST mock ---- END

		bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

	}

	/**
	 * Test update list group protected.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testFetchLightByGroupToMap() throws Exception
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

		ActionProxy proxy = getActionProxy("/group/ajax.fetchLightByGroupToMap.action");

		GroupAjaxAction action = (GroupAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		GroupRequest groupReq = new GroupRequest();
		action.setGroupRequest(groupReq);
		// Create REQUEST mock ---- END

		GroupBCFMockImpl bcf = (GroupBCFMockImpl)action.getGroupBCF();
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

		proxy = getActionProxy("/group/ajax.fetchLightByGroupToMap.action");
		action = (GroupAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		groupReq = new GroupRequest();

		Group group = new Group();
		group.setId(1);

		groupReq.setSelectionPaginationIds(new ArrayList<Integer>());
		groupReq.setGroup(group);
		action.setGroupRequest(groupReq);
		// Create REQUEST mock ---- END

		bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());
	}

	@Test
	public void testRemoveSmartpoints() throws Exception
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

		ActionProxy proxy = getActionProxy("/group/removeSmartPoints.action");

		GroupAjaxAction action = (GroupAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		GroupBCFMockImpl bcf = (GroupBCFMockImpl)action.getGroupBCF();
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

		proxy = getActionProxy("/group/removeSmartPoints.action");
		action = (GroupAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		GroupRequest groupReq = new GroupRequest();

		Group group = new Group();
		group.setId(1);

		groupReq.setSelectionPaginationIds(new ArrayList<Integer>());
		groupReq.setGroup(group);
		action.setGroupRequest(groupReq);
		// Create REQUEST mock ---- END

		bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());
	}
}
