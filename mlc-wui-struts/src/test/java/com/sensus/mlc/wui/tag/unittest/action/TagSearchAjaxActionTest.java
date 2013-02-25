package com.sensus.mlc.wui.tag.unittest.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.ActionPaginationUtil;
import com.sensus.mlc.wui.base.util.Constants;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.systemsettings.tags.action.TagSearchAjaxAction;

public class TagSearchAjaxActionTest extends StrutsSpringTestCase
{

	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	private static final String MESSAGE_SUCCESS_STATUS = "Success Status";

	private static final String MESSAGE_MESSAGE_COUNT = "Message Count";

	private static final String MESSAGE_MESSAGES_SET = "Messages Set";

	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

	private static final String MESSAGE_RESPONSE_OBJECT_NOT_PRESENT = "Response object not present";

	private Logger logger = Logger.getLogger(this.getClass());

	@Test
	public void testSearch() throws Exception
	{

		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		// Test success
		logger.debug("Testing success");

		request.setParameter(ActionPaginationUtil.DISPLAY_LENGTH, "20");
		request.setParameter(ActionPaginationUtil.DISPLAY_START, "0");
		request.setParameter(ActionPaginationUtil.TOTAL_ROWS, "2");

		ActionProxy proxy = getActionProxy("/tag/tagSearch.action");
		TagSearchAjaxAction action = (TagSearchAjaxAction)proxy.getAction();

		action.setServletRequest(request);
		SessionAuthenticationTestUtil.setMockSession();

		TagBCFMockImpl bcf = (TagBCFMockImpl)action.getTagBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getTagResult());
		String response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getTagResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_OK, action.getTagResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getTagResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getTagResult().getMessages().size());
		assertEquals("Result Count", TagBCFMockImpl.TAG_COUNT.intValue() + 1, action
				.getTagResult()
				.getAaData().length);

		// Testing empty results
		logger.debug("Testing success with no results");
		proxy = getActionProxy("/tag/tagSearch.action");
		action = (TagSearchAjaxAction)proxy.getAction();
		bcf = (TagBCFMockImpl)action.getTagBCF();
		bcf.setMode(BaseMockImpl.MODE_EMPTY);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getTagResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getTagResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_OK, action.getTagResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getTagResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getTagResult().getMessages().size());
		assertEquals("Result Count", 0, action.getTagResult().getAaData().length);

		// Testing failure
		logger.debug("Testing failure");
		proxy = getActionProxy("/tag/tagSearch.action");
		action = (TagSearchAjaxAction)proxy.getAction();
		bcf = (TagBCFMockImpl)action.getTagBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getTagResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getTagResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_FAIL, action.getTagResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getTagResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 2, action.getTagResult().getMessages().size());
		assertEquals("Result Count", 0, action.getTagResult().getAaData().length);

		// Testing exception
		logger.debug("Testing exception");
		proxy = getActionProxy("/tag/tagSearch.action");
		action = (TagSearchAjaxAction)proxy.getAction();
		bcf = (TagBCFMockImpl)action.getTagBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getTagResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getTagResult());
		assertEquals(MESSAGE_SUCCESS_STATUS, Constants.JSON_FAIL, action.getTagResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getTagResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 0, action.getTagResult().getMessages().size());
		assertEquals("Result Count", 0, action.getTagResult().getAaData().length);
	}

}
