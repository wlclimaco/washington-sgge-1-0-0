package com.sensus.mlc.wui.schedule.unittest.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.schedule.action.CreateSchedulePageAction;

/**
 * For some basic use examples of the Struts Test case, see
 * https://cwiki.apache.org/WW/struts-2-junit-plugin-tutorial.html.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class CreateSchedulePageActionTest extends StrutsSpringTestCase
{

	/** The Constant STRUTS_OUTCOME. */
	private static final String STRUTS_OUTCOME = "Struts outcome";

	/** The Constant COUNT. */
	private static final String COUNT = "3";

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	/** The Constant SYSTEM_SETTINGS. */
	private static final String SYSTEM_SETTINGS = "/schedule/";

	/** The Constant ACTION. */
	private static final String ACTION = ".action";

	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	/**
	 * Gets the action.
	 * 
	 * @param methodAction the method action
	 * @return the action
	 */
	private CreateSchedulePageAction getAction(String methodAction)
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		CreateSchedulePageAction action =
				(CreateSchedulePageAction)getActionProxy(SYSTEM_SETTINGS + methodAction + ACTION).getAction();
		action.setServletRequest(request);

		return action;
	}

	public void testOpenUpdateOffSetSchedule() throws Exception
	{
		CreateSchedulePageAction actionUpdateOffsetSchedule = getAction("ajax.updateOffsetSchedule");
		SessionAuthenticationTestUtil.setMockSession();
		ScheduleBCFMockImpl scheduleBCF = (ScheduleBCFMockImpl)actionUpdateOffsetSchedule.getScheduleBCF();

		// Testing Fetch Schedule By Id Error
		logger.info("Testing Fetch Schedule By id error...");
		assertEquals(Action.ERROR, actionUpdateOffsetSchedule.openUpdateOffSetSchedule());

		// Testing Fetch Schedule By Id Exception
		logger.info("Testing Fetch Schedule By id exception...");
		actionUpdateOffsetSchedule.setId(0);
		scheduleBCF.setMode(BaseMockImpl.MODE_EXCEPTION);
		assertEquals(Action.ERROR, actionUpdateOffsetSchedule.openUpdateOffSetSchedule());

		// Testing Fetch Schedule By Id Success
		logger.info("Testing Fetch Schedule By id success...");
		actionUpdateOffsetSchedule.setId(0);
		scheduleBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		assertEquals(Action.SUCCESS, actionUpdateOffsetSchedule.openUpdateOffSetSchedule());

	}

	public void testOpenUpdateEventSchedule() throws Exception
	{
		CreateSchedulePageAction actionUpdateEventSchedule = getAction("ajax.updateEventSchedule");
		SessionAuthenticationTestUtil.setMockSession();
		ScheduleBCFMockImpl scheduleBCF = (ScheduleBCFMockImpl)actionUpdateEventSchedule.getScheduleBCF();

		// Testing Fetch Schedule By Id Error
		logger.info("Testing Fetch Schedule By id error...");
		assertEquals(Action.ERROR, actionUpdateEventSchedule.openUpdateEventSchedule());

		// Testing Fetch Schedule By Id Exception
		logger.info("Testing Fetch Schedule By id exception...");
		actionUpdateEventSchedule.setId(1);
		scheduleBCF.setMode(BaseMockImpl.MODE_EXCEPTION);
		assertEquals(Action.ERROR, actionUpdateEventSchedule.openUpdateEventSchedule());

		// Testing Fetch Schedule By Id Success
		logger.info("Testing Fetch Schedule By id success...");
		actionUpdateEventSchedule.setId(1);
		scheduleBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		assertEquals(Action.SUCCESS, actionUpdateEventSchedule.openUpdateEventSchedule());

	}
}
