package com.sensus.mlc.wui.user.unittest.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.user.model.User;
import com.sensus.mlc.user.model.request.UserRequest;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.group.unittest.action.GroupBCFMockImpl;
import com.sensus.mlc.wui.user.action.UserAjaxAction;

/**
 * Action handling User-related Ajax callbacks.
 * 
 * @author Alex Tiveron
 * 
 */
public class UserAjaxActionTest extends StrutsSpringTestCase
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

	/**
	 * Test fetch lights by group.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testFetchLightsByGroup() throws Exception
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

		ActionProxy proxy = getActionProxy("/user/fetchLightsByGroup.action");

		UserAjaxAction action = (UserAjaxAction)proxy.getAction();

		// Create REQUEST mock ---- BEGIN
		GroupRequest groupReq = new GroupRequest();
		action.setGroupRequest(groupReq);
		// Create REQUEST mock ---- END

		GroupBCFMockImpl bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);

		/** Asserts **/
		String actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getLights());

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/user/fetchLightsByGroup.action");
		action = (UserAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		groupReq = new GroupRequest();

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
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getLights());
	}

	@Test
	public void testDeleteUser() throws Exception
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

		ActionProxy proxy = getActionProxy("/user/deleteUser.action");

		UserAjaxAction action = (UserAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		UserRequest userReq = new UserRequest();
		action.setUserRequest(userReq);
		// Create REQUEST mock ---- END

		UserBCFMockImpl bcf = (UserBCFMockImpl)action.getUserBCF();
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

		proxy = getActionProxy("/user/deleteUser.action");
		action = (UserAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		userReq = new UserRequest();

		User user = new User();
		user.setId(1);

		userReq.setUser(user);
		action.setUserRequest(userReq);
		// Create REQUEST mock ---- END

		bcf = (UserBCFMockImpl)action.getUserBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());
	}

	@Test
	public void testCreateUser() throws Exception
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

		ActionProxy proxy = getActionProxy("/user/createUser.action");

		UserAjaxAction action = (UserAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		UserRequest userReq = new UserRequest();
		action.setUserRequest(userReq);
		// Create REQUEST mock ---- END

		UserBCFMockImpl bcf = (UserBCFMockImpl)action.getUserBCF();
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

		proxy = getActionProxy("/user/createUser.action");
		action = (UserAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		userReq = new UserRequest();

		User user = new User();
		user.setId(1);

		userReq.setUser(user);
		action.setUserRequest(userReq);
		// Create REQUEST mock ---- END

		bcf = (UserBCFMockImpl)action.getUserBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());
	}

	@Test
	public void testUpdateUser() throws Exception
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

		ActionProxy proxy = getActionProxy("/user/updateUser.action");

		UserAjaxAction action = (UserAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		UserRequest userReq = new UserRequest();
		action.setUserRequest(userReq);
		// Create REQUEST mock ---- END

		UserBCFMockImpl bcf = (UserBCFMockImpl)action.getUserBCF();
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

		proxy = getActionProxy("/user/updateUser.action");
		action = (UserAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		userReq = new UserRequest();

		User user = new User();
		user.setId(1);

		userReq.setUser(user);

		action.setUserRequest(userReq);
		// Create REQUEST mock ---- END

		bcf = (UserBCFMockImpl)action.getUserBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());
	}

	@Test
	public void testFetchLightByUserToMap() throws Exception
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

		ActionProxy proxy = getActionProxy("/user/ajax.fetchLightByUserToMap.action");

		UserAjaxAction action = (UserAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		GroupRequest groupReq = null;
		action.setGroupRequest(groupReq);
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

		proxy = getActionProxy("/user/ajax.fetchLightByUserToMap.action");
		action = (UserAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		groupReq = new GroupRequest();

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

	@Test
	public void testFillUserPage() throws Exception
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

		ActionProxy proxy = getActionProxy("/user/fillUserPage.action");

		UserAjaxAction action = (UserAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		UserRequest userReq = new UserRequest();
		action.setUserRequest(userReq);
		// Create REQUEST mock ---- END

		UserBCFMockImpl bcf = (UserBCFMockImpl)action.getUserBCF();
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

		proxy = getActionProxy("/user/fillUserPage.action");
		action = (UserAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		userReq = new UserRequest();

		User user = new User();
		user.setId(1);

		userReq.setUser(user);

		action.setUserRequest(userReq);
		// Create REQUEST mock ---- END

		bcf = (UserBCFMockImpl)action.getUserBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());
	}
}
