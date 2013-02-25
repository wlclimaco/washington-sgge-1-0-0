package com.sensus.mlc.wui.schedule.unittest.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.common.model.UserContext;
import com.sensus.mlc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.schedule.action.ScheduleSearchAjaxAction;

/**
 * For some basic use examples of the Struts Test case, see
 * https://cwiki.apache.org/WW/struts-2-junit-plugin-tutorial.html.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class ScheduleSearchAjaxActionTest extends StrutsSpringTestCase
{

	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

	private static final String MESSAGE_RESPONSE_OBJECT_NOT_PRESENT = "Response object not present";

	private Logger logger = Logger.getLogger(this.getClass());

	@Test
	public void testSearch() throws Exception
	{
		UserContext context = new UserContext();
		InquiryScheduleRequest request = new InquiryScheduleRequest();

		request.setUserContext(context);
		SessionAuthenticationTestUtil.setAuthenticationTest();
		SessionAuthenticationTestUtil.setMockSession();

		// Test success
		logger.debug("Testing success");

		ActionProxy proxy = getActionProxy("/schedule/search.action");
		ScheduleSearchAjaxAction action = (ScheduleSearchAjaxAction)proxy.getAction();

		action.setInquiryScheduleRequest(request);
		SessionAuthenticationTestUtil.setMockSession();

		ScheduleBCFMockImpl bcf = (ScheduleBCFMockImpl)action.getScheduleBCF();
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

		proxy = getActionProxy("/schedule/search.action");
		action = (ScheduleSearchAjaxAction)proxy.getAction();

		SessionAuthenticationTestUtil.setMockSession();

		bcf = (ScheduleBCFMockImpl)action.getScheduleBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);
		response = proxy.execute();
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getResponse());
	}
}
