package com.sensus.mlc.wui.group.unittest.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.group.action.GroupCreatePageAction;

/**
 * The Class GroupCreatePageActionTest.
 */
public class GroupCreatePageActionTest extends StrutsSpringTestCase
{

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

	/** The Constant MESSAGE_RESPONSE_OBJECT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

	/** The Constant EXCEPTION. */
	private static final String EXCEPTION = "EXCEPTION";

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts2.StrutsSpringTestCase#getContextLocations()
	 */
	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	@Test
	public void testInsertGroup() throws Exception
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

		ActionProxy proxy = getActionProxy("/group/insertGroup.action");

		GroupCreatePageAction action = (GroupCreatePageAction)proxy.getAction();

		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		GroupRequest groupRequest = new GroupRequest();
		action.setGroupRequest(groupRequest);
		// Create REQUEST mock ---- END

		GroupBCFMockImpl bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);

		/** Asserts **/
		String actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Failure", false, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/group/insertGroup.action");
		action = (GroupCreatePageAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		groupRequest = new GroupRequest();

		Group group = new Group();
		group.setName("Group");
		group.setDescription("Group Description");

		groupRequest.setGroup(group);

		action.setGroupRequest(groupRequest);
		// Create REQUEST mock ---- END

		bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING EXCEPTION
		 * 
		 ***********************************/

		logger.debug("TESTING EXCEPTION");

		proxy = getActionProxy("/group/insertGroup.action");
		action = (GroupCreatePageAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		groupRequest = null;
		action.setGroupRequest(groupRequest);

		// Create REQUEST mock ---- END

		bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, EXCEPTION, actionResponse);

	}

	@Test
	public void testUpdateGroup() throws Exception
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

		ActionProxy proxy = getActionProxy("/group/updateGroup.action");

		GroupCreatePageAction action = (GroupCreatePageAction)proxy.getAction();

		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		GroupRequest groupRequest = new GroupRequest();
		action.setGroupRequest(groupRequest);
		// Create REQUEST mock ---- END

		GroupBCFMockImpl bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);

		/** Asserts **/
		String actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Failure", false, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/group/updateGroup.action");
		action = (GroupCreatePageAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		groupRequest = new GroupRequest();

		Group group = new Group();
		group.setName("Group");
		group.setId(1);
		group.setDescription("Group Description");

		groupRequest.setGroup(group);

		action.setGroupRequest(groupRequest);
		// Create REQUEST mock ---- END

		bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING EXCEPTION
		 * 
		 ***********************************/

		logger.debug("TESTING EXCEPTION");

		proxy = getActionProxy("/group/updateGroup.action");
		action = (GroupCreatePageAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		groupRequest = null;
		action.setGroupRequest(groupRequest);

		// Create REQUEST mock ---- END

		bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, EXCEPTION, actionResponse);
	}

}