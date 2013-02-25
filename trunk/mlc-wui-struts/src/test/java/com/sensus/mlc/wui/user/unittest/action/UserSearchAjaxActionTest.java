package com.sensus.mlc.wui.user.unittest.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.common.model.UserContext;
import com.sensus.mlc.user.model.request.InquiryUserRequest;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.user.action.UserSearchAjaxAction;

/**
 * Action for searching users.
 * 
 * @author Alex Tiveron
 */
public class UserSearchAjaxActionTest extends StrutsSpringTestCase
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
	 * AJAX callback for searching groups. At this point, the search doesn't
	 * take any parameters. It populates <code>groupSearchResult</code> which is
	 * returned as JSON. Error messages are communicated via the JSON object.
	 * 
	 * @return always "SUCCESS"
	 */

	@Test
	public void testSearch() throws Exception
	{
		UserContext context = new UserContext();
		InquiryUserRequest request = new InquiryUserRequest();

		request.setUserContext(context);
		SessionAuthenticationTestUtil.setAuthenticationTest();
		SessionAuthenticationTestUtil.setMockSession();

		// Test success
		logger.debug("Testing success");

		ActionProxy proxy = getActionProxy("/user/search.action");
		UserSearchAjaxAction action = (UserSearchAjaxAction)proxy.getAction();

		action.setInquiryUserRequest(request);
		SessionAuthenticationTestUtil.setMockSession();

		UserBCFMockImpl bcf = (UserBCFMockImpl)action.getUserBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getResponse());
		String response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		proxy = getActionProxy("/user/search.action");
		action = (UserSearchAjaxAction)proxy.getAction();

		SessionAuthenticationTestUtil.setMockSession();

		bcf = (UserBCFMockImpl)action.getUserBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);
		response = proxy.execute();
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getResponse());

	}
}