package com.sensus.mlc.wui.schedule.unittest.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.ScheduleTypeEnum;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.schedule.action.CreateScheduleAjaxAction;

/**
 * For some basic use examples of the Struts Test case, see
 * https://cwiki.apache.org/WW/struts-2-junit-plugin-tutorial.html.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class CreateScheduleAjaxActionTest extends StrutsSpringTestCase
{
	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

	/** The Constant MESSAGE_RESPONSE_OBJECT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	@Test
	public void testCreateSchedule() throws Exception
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

		ActionProxy proxy = getActionProxy("/schedule/createSchedule.action");

		CreateScheduleAjaxAction action = (CreateScheduleAjaxAction)proxy.getAction();

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

		proxy = getActionProxy("/schedule/createSchedule.action");
		action = (CreateScheduleAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		ScheduleRequest request = new ScheduleRequest();

		Schedule schedule = new Schedule();
		schedule.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
		schedule.setName("Schedule Offset");
		schedule.setModelAction(PersistanceActionEnum.INSERT);
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

	public void testUpdateSchedule() throws Exception
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

		ActionProxy proxy = getActionProxy("/schedule/updateSchedule.action");

		CreateScheduleAjaxAction action = (CreateScheduleAjaxAction)proxy.getAction();

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

		proxy = getActionProxy("/schedule/updateSchedule.action");
		action = (CreateScheduleAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		ScheduleRequest request = new ScheduleRequest();

		Schedule schedule = new Schedule();
		schedule.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
		schedule.setName("Schedule Offset");
		schedule.setId(1);
		schedule.setModelAction(PersistanceActionEnum.UPDATE);
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
}
