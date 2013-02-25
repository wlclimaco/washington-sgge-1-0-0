package com.sensus.mlc.wui.tag.unittest.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.Constants;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.group.unittest.action.GroupBCFMockImpl;
import com.sensus.mlc.wui.systemsettings.tags.action.TagAjaxAction;

/**
 * The Class TagAjaxActionTest.
 */
public class TagAjaxActionTest extends StrutsSpringTestCase
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

	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

	/** The Constant MESSAGE_RESPONSE_OBJECT_NOT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_NOT_PRESENT = "No Response object";

	/** The Constant MESSAGE_RESPONSE_OBJECT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

	/** The Constant MESSAGE_MESSAGES_SET. */
	private static final String MESSAGE_MESSAGES_SET = "Has Response Messages";

	/** The Constant MESSAGE_MESSAGE_COUNT. */
	private static final String MESSAGE_MESSAGE_COUNT = "Count Response Messages";

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Test update auto group.
	 * 
	 * @throws Exception the exception
	 */
	public void testUpdateAutoGroup() throws Exception
	{

		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		// Test failure
		logger.debug("Testing failure due to missing request parameters");
		ActionProxy proxy = getActionProxy("/tag/updateAutoGroup.action");
		TagAjaxAction action =
				(TagAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		SessionAuthenticationTestUtil.setMockSession();

		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getTagResult());
		String response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, response, Action.SUCCESS);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getTagResult());
		assertEquals("Failure because of invalid status", Constants.JSON_FAIL, action.getTagResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getTagResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getTagResult().getMessages().size());

		// Test success
		logger.debug("Testing Success");
		request.setParameter("id", "180");
		request.setParameter("autoGroup", "false");

		proxy = getActionProxy("/tag/updateAutoGroup.action");
		action = (TagAjaxAction)proxy.getAction();

		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getTagResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getTagResult());
		assertEquals("Json Success Message", Constants.JSON_OK, action.getTagResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getTagResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getTagResult().getMessages().size());

		// Test Exception
		logger.debug("Testing Exception");
		request.setParameter("id", "180");
		request.setParameter("autoGroup", "false");

		proxy = getActionProxy("/tag/updateAutoGroup.action");
		action = (TagAjaxAction)proxy.getAction();
		TagBCFMockImpl bcf = (TagBCFMockImpl)action.getTagBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);

		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getTagResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getTagResult());
		assertEquals("Json Failure Message", Constants.JSON_FAIL, action.getTagResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getTagResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 0, action.getTagResult().getMessages().size());

	}

	/**
	 * Test exist group with tag name.
	 * 
	 * @throws Exception the exception
	 */
	public void testExistGroupWithTagName() throws Exception
	{

		// Test failure
		logger.debug("Testing failure due to missing request parameters");
		ActionProxy proxy = getActionProxy("/tag/existGroupWithTagName.action");
		TagAjaxAction action =
				(TagAjaxAction)proxy.getAction();

		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getSearchTagResult());
		String response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, response, Action.SUCCESS);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getSearchTagResult());
		assertEquals("Failure because of invalid status", Constants.JSON_FAIL, action.getSearchTagResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getSearchTagResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 2, action.getSearchTagResult().getMessages().size());

		// Test success
		logger.debug("Testing Success");
		request.setParameter("tagName", "Tag 1");

		proxy = getActionProxy("/tag/existGroupWithTagName.action");
		action = (TagAjaxAction)proxy.getAction();

		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getSearchTagResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getSearchTagResult());
		assertEquals("Json Success Message", Constants.JSON_OK, action.getSearchTagResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getSearchTagResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getSearchTagResult().getMessages().size());

		// Test Exception
		logger.debug("Testing Exception");
		request.setParameter("tagName", "Tag Name");

		proxy = getActionProxy("/tag/existGroupWithTagName.action");
		action = (TagAjaxAction)proxy.getAction();
		GroupBCFMockImpl bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);

		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getSearchTagResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getSearchTagResult());
		assertEquals("Json Failure Message", Constants.JSON_FAIL, action.getSearchTagResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getSearchTagResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 0, action.getSearchTagResult().getMessages().size());

	}

	/**
	 * Test create group.
	 * 
	 * @throws Exception the exception
	 */
	public void testCreateGroup() throws Exception
	{

		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		// Test failure
		logger.debug("Testing failure due to missing request parameters");
		ActionProxy proxy = getActionProxy("/tag/createGroup.action");
		TagAjaxAction action =
				(TagAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		SessionAuthenticationTestUtil.setMockSession();

		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getTagResult());
		String response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, response, Action.SUCCESS);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getTagResult());
		assertEquals("Failure because of invalid status", Constants.JSON_FAIL, action.getTagResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getTagResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getTagResult().getMessages().size());

		// Test success
		logger.debug("Testing Success");
		request.setParameter("tagName", "Tag 1");
		request.setParameter("groupDescription", "Description Tag 1");

		proxy = getActionProxy("/tag/createGroup.action");
		action = (TagAjaxAction)proxy.getAction();

		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getTagResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getTagResult());
		assertEquals("Json Success Message", Constants.JSON_OK, action.getTagResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getTagResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getTagResult().getMessages().size());

		// Test Exception
		logger.debug("Testing Exception");
		request.setParameter("tagName", "Tag Name");
		request.setParameter("groupDescription", "Description Tag 1");

		proxy = getActionProxy("/tag/createGroup.action");
		action = (TagAjaxAction)proxy.getAction();
		GroupBCFMockImpl bcf = (GroupBCFMockImpl)action.getGroupBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);

		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getTagResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getTagResult());
		assertEquals("Json Failure Message", Constants.JSON_FAIL, action.getTagResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getTagResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 0, action.getTagResult().getMessages().size());

	}

	/**
	 * Test delete tag.
	 * 
	 * @throws Exception the exception
	 */
	public void testDeleteTag() throws Exception
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

		ActionProxy proxy = getActionProxy("/tag/deleteTagUrl.action");

		TagAjaxAction action = (TagAjaxAction)proxy.getAction();

		// Create REQUEST mock ---- BEGIN
		TagRequest tagReq = new TagRequest();
		action.setTagRequest(tagReq);
		// Create REQUEST mock ---- END

		TagBCFMockImpl bcf = (TagBCFMockImpl)action.getTagBCF();
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

		proxy = getActionProxy("/tag/deleteTagUrl.action");
		action = (TagAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		tagReq = new TagRequest();

		Tag tag = new Tag();
		tag.setId(1);
		tagReq.setTag(tag);

		action.setTagRequest(tagReq);
		// Create REQUEST mock ---- END

		bcf = (TagBCFMockImpl)action.getTagBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());

	}

	/**
	 * Test add tag.
	 * 
	 * @throws Exception the exception
	 */
	public void testAddTag() throws Exception
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

		ActionProxy proxy = getActionProxy("/tag/addTag.action");
		TagAjaxAction action = (TagAjaxAction)proxy.getAction();

		TagRequest tagRequest = new TagRequest();
		action.setTagRequest(tagRequest);
		action.setServletRequest(request);

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

		proxy = getActionProxy("/tag/addTag.action");
		action = (TagAjaxAction)proxy.getAction();

		tagRequest = new TagRequest();

		Tag tag = new Tag();
		tag.setName("Tag Name Mock");

		tagRequest.setTag(tag);

		action.setTagRequest(tagRequest);

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

		proxy = getActionProxy("/tag/addTag.action");
		action = (TagAjaxAction)proxy.getAction();
		TagBCFMockImpl bcf = (TagBCFMockImpl)action.getTagBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);

		action.setTagRequest(null);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNull("Response object is not present", action.getResponse());

	}

}
