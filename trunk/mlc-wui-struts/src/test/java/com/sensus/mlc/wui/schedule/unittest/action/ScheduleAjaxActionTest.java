package com.sensus.mlc.wui.schedule.unittest.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.ScheduleTypeEnum;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.Constants;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.schedule.action.ScheduleAjaxAction;

/**
 * For some basic use examples of the Struts Test case, see
 * https://cwiki.apache.org/WW/struts-2-junit-plugin-tutorial.html.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class ScheduleAjaxActionTest extends StrutsSpringTestCase
{

	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";
	private static final String MESSAGE_RESPONSE_OBJECT_NOT_PRESENT = "No Response object";
	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";
	private static final String MESSAGE_MESSAGES_SET = "Has Response Messages";
	private static final String MESSAGE_MESSAGE_COUNT = "Count Response Messages";

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	public void testApplyEventSchedule() throws Exception
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

		ActionProxy proxy = getActionProxy("/schedule/applyEventSchedule.action");

		ScheduleAjaxAction action = (ScheduleAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		ScheduleBCFMockImpl bcf = (ScheduleBCFMockImpl)action.getScheduleBCF();
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

		proxy = getActionProxy("/schedule/applyEventSchedule.action");
		action = (ScheduleAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		ScheduleRequest request = new ScheduleRequest();

		Schedule schedule = new Schedule();
		schedule.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);
		schedule.setId(1);
		schedule.setName("Schedule Event");
		schedule.setDescription("Schedule Event Description");

		request.setSchedule(schedule);
		request.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);
		action.setScheduleRequest(request);
		// Create REQUEST mock ---- END

		bcf = (ScheduleBCFMockImpl)action.getScheduleBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());
	}

	public void testApplyOffsetSchedule() throws Exception
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

		ActionProxy proxy = getActionProxy("/schedule/applyOffsetSchedule.action");

		ScheduleAjaxAction action = (ScheduleAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		ScheduleBCFMockImpl bcf = (ScheduleBCFMockImpl)action.getScheduleBCF();
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

		proxy = getActionProxy("/schedule/applyOffsetSchedule.action");
		action = (ScheduleAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		ScheduleRequest request = new ScheduleRequest();

		Schedule schedule = new Schedule();
		schedule.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
		schedule.setId(1);
		schedule.setName("Schedule Offset");
		schedule.setDescription("Schedule Offset Description");

		request.setSchedule(schedule);
		request.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
		action.setScheduleRequest(request);
		// Create REQUEST mock ---- END

		bcf = (ScheduleBCFMockImpl)action.getScheduleBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());
	}

	public void testClearEventSchedule() throws Exception
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

		ActionProxy proxy = getActionProxy("/schedule/clearEventSchedule.action");

		ScheduleAjaxAction action = (ScheduleAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		ScheduleBCFMockImpl bcf = (ScheduleBCFMockImpl)action.getScheduleBCF();
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

		proxy = getActionProxy("/schedule/clearEventSchedule.action");
		action = (ScheduleAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		ScheduleRequest request = new ScheduleRequest();

		Schedule schedule = new Schedule();
		schedule.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);
		schedule.setId(1);
		schedule.setName("Schedule Event");
		schedule.setDescription("Schedule Event Description");

		request.setSchedule(schedule);
		request.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);
		action.setScheduleRequest(request);
		// Create REQUEST mock ---- END

		bcf = (ScheduleBCFMockImpl)action.getScheduleBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());
	}

	public void testClearOffsetSchedule() throws Exception
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

		ActionProxy proxy = getActionProxy("/schedule/clearOffsetSchedule.action");

		ScheduleAjaxAction action = (ScheduleAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		ScheduleBCFMockImpl bcf = (ScheduleBCFMockImpl)action.getScheduleBCF();
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

		proxy = getActionProxy("/schedule/clearOffsetSchedule.action");
		action = (ScheduleAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		ScheduleRequest request = new ScheduleRequest();

		Schedule schedule = new Schedule();
		schedule.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
		schedule.setId(1);
		schedule.setName("Schedule Offset");
		schedule.setDescription("Schedule Offset Description");

		request.setSchedule(schedule);
		request.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
		action.setScheduleRequest(request);
		// Create REQUEST mock ---- END

		bcf = (ScheduleBCFMockImpl)action.getScheduleBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());
	}

	/**
	 * Test delete schedule.
	 * 
	 * @throws Exception the exception
	 */
	public void testDeleteSchedule() throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		// Test failure
		logger.debug("Testing failure due to missing request parameters");
		ActionProxy proxy = getActionProxy("/schedule/deleteSchedule.action");
		ScheduleAjaxAction action =
				(ScheduleAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		SessionAuthenticationTestUtil.setMockSession();

		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getResult());
		String response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, response, Action.SUCCESS);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getResult().getMessages());

		// Test success
		logger.debug("Testing Success");
		request.setParameter("id", "4");
		proxy = getActionProxy("/schedule/deleteSchedule.action");
		action = (ScheduleAjaxAction)proxy.getAction();

		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResult());
		assertEquals("Json Success Message", Constants.JSON_OK, action.getResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 1, action.getResult().getMessages().size());

		// Test Exception
		logger.debug("Testing Exception");
		request.setParameter("id", "4");

		proxy = getActionProxy("/schedule/deleteSchedule.action");
		action = (ScheduleAjaxAction)proxy.getAction();
		ScheduleBCFMockImpl bcf = (ScheduleBCFMockImpl)action.getScheduleBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);

		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getResult());
		response = proxy.execute();

		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResult());
		assertEquals("Json Failure Message", Constants.JSON_FAIL, action.getResult()
				.getResult());
		assertNotNull(MESSAGE_MESSAGES_SET, action.getResult().getMessages());
		assertEquals(MESSAGE_MESSAGE_COUNT, 0, action.getResult().getMessages().size());
	}

	public void testInitiateDeleteSchedule() throws Exception
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

		ActionProxy proxy = getActionProxy("/schedule/initiateDeleteSchedule.action");

		ScheduleAjaxAction action = (ScheduleAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		ScheduleBCFMockImpl bcf = (ScheduleBCFMockImpl)action.getScheduleBCF();
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

		proxy = getActionProxy("/schedule/initiateDeleteSchedule.action");
		action = (ScheduleAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		ScheduleRequest request = new ScheduleRequest();

		Schedule schedule = new Schedule();
		schedule.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
		schedule.setId(1);
		schedule.setName("Schedule Offset");
		schedule.setDescription("Schedule Offset Description");

		request.setSchedule(schedule);
		request.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
		action.setScheduleRequest(request);
		// Create REQUEST mock ---- END

		bcf = (ScheduleBCFMockImpl)action.getScheduleBCF();
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
	public void testFetchfetchLightByScheduleToMap() throws Exception
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

		ActionProxy proxy = getActionProxy("/schedule/ajax.fetchLightByScheduleToMap.action");

		ScheduleAjaxAction action = (ScheduleAjaxAction)proxy.getAction();

		// Create REQUEST mock ---- BEGIN
		ScheduleRequest scheduleReq = new ScheduleRequest();
		action.setScheduleRequest(scheduleReq);
		// Create REQUEST mock ---- END

		ScheduleBCFMockImpl bcf = (ScheduleBCFMockImpl)action.getScheduleBCF();
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

		proxy = getActionProxy("/schedule/ajax.fetchLightByScheduleToMap.action");
		action = (ScheduleAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		scheduleReq = new ScheduleRequest();

		Schedule schedule = new Schedule();
		schedule.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);

		action.setScheduleRequest(scheduleReq);
		// Create REQUEST mock ---- END

		bcf = (ScheduleBCFMockImpl)action.getScheduleBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);

	}

}
