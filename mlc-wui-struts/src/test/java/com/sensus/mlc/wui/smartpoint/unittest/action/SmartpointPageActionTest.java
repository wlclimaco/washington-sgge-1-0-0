package com.sensus.mlc.wui.smartpoint.unittest.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.sensus.mlc.wui.base.model.IdValuePair;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.group.unittest.action.GroupBCFMockImpl;
import com.sensus.mlc.wui.schedule.unittest.action.ScheduleBCFMockImpl;
import com.sensus.mlc.wui.smartpoint.action.SmartpointPageAction;
import com.sensus.mlc.wui.tag.unittest.action.TagBCFMockImpl;

/**
 * Test SmartpointPageAction.
 * For some basic use examples of the Struts Test case, see
 * https://cwiki.apache.org/WW/struts-2-junit-plugin-tutorial.html.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class SmartpointPageActionTest extends StrutsSpringTestCase
{

	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	/** The Constant STRUTS_OUTCOME. */
	private static final String STRUTS_OUTCOME = "Struts outcome";

	/** The Constant RESULT_COUNT. */
	private static final String RESULT_COUNT = "Result Count";

	/** The Constant RESULT_VALUE. */
	private static final String RESULT_VALUE = "Result Value";

	/** The Constant DEFAULT_MESSAGE. */
	private static final String DEFAULT_MESSAGE = "Default Message";

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts2.StrutsSpringTestCase#getContextLocations()
	 */
	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	/**
	 * Gets the action.
	 * 
	 * @return the action
	 */
	private SmartpointPageAction getAction()
	{

		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		SmartpointPageAction action = (SmartpointPageAction)getActionProxy("/smartpoint/ajax.list.action").getAction();
		action.setServletRequest(request);

		return action;

	}

	/**
	 * Test execute.
	 */
	public void testExecute()
	{
		SmartpointPageAction action = getAction();
		assertEquals(STRUTS_OUTCOME, Action.SUCCESS, action.execute());
	}

	/**
	 * Test schedule list.
	 * 
	 * @throws Exception the exception
	 */
	public void testEventScheduleList() throws Exception
	{
		SmartpointPageAction action = getAction();

		SessionAuthenticationTestUtil.setMockSession();

		ScheduleBCFMockImpl scheduleBCF = (ScheduleBCFMockImpl)action.getScheduleBCF();

		// Test Success
		logger.info("Testing success...");
		scheduleBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		List<IdValuePair> schedules = action.getEventScheduleList();
		assertEquals(RESULT_COUNT, 10 + 1, schedules.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_DESCRIPTION_KEY), schedules.get(0)
				.getValue());

		// Test No Records
		logger.info("Testing no records returned...");
		scheduleBCF.setMode(BaseMockImpl.MODE_EMPTY);
		schedules = action.getEventScheduleList();
		assertEquals(RESULT_COUNT, 1, schedules.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_DESCRIPTION_KEY), schedules.get(0)
				.getValue());

		// Test Fail
		logger.info("Testing Failure...");
		scheduleBCF.setMode(BaseMockImpl.MODE_FAILURE);
		schedules = action.getEventScheduleList();
		assertEquals(RESULT_COUNT, 1, schedules.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_ERROR_KEY), schedules.get(0)
				.getValue());

		// Test Fail
		logger.info("Testing Exception...");
		scheduleBCF.setMode(BaseMockImpl.MODE_EXCEPTION);
		schedules = action.getEventScheduleList();
		assertEquals(RESULT_COUNT, 1, schedules.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_ERROR_KEY), schedules.get(0)
				.getValue());

	}

	/**
	 * Test schedule list.
	 * 
	 * @throws Exception the exception
	 */
	public void testOffsetScheduleList() throws Exception
	{
		SmartpointPageAction action = getAction();

		SessionAuthenticationTestUtil.setMockSession();

		ScheduleBCFMockImpl scheduleBCF = (ScheduleBCFMockImpl)action.getScheduleBCF();

		// Test Success
		logger.info("Testing success...");
		scheduleBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		List<IdValuePair> schedules = action.getOffsetScheduleList();
		assertEquals(RESULT_COUNT, 10 + 1, schedules.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_DESCRIPTION_KEY), schedules.get(0)
				.getValue());

		// Test No Records
		logger.info("Testing no records returned...");
		scheduleBCF.setMode(BaseMockImpl.MODE_EMPTY);
		schedules = action.getOffsetScheduleList();
		assertEquals(RESULT_COUNT, 1, schedules.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_DESCRIPTION_KEY), schedules.get(0)
				.getValue());

		// Test Fail
		logger.info("Testing Failure...");
		scheduleBCF.setMode(BaseMockImpl.MODE_FAILURE);
		schedules = action.getOffsetScheduleList();
		assertEquals(RESULT_COUNT, 1, schedules.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_ERROR_KEY), schedules.get(0)
				.getValue());

		// Test Fail
		logger.info("Testing Exception...");
		scheduleBCF.setMode(BaseMockImpl.MODE_EXCEPTION);
		schedules = action.getOffsetScheduleList();
		assertEquals(RESULT_COUNT, 1, schedules.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_ERROR_KEY), schedules.get(0)
				.getValue());

	}

	/**
	 * Test group list.
	 * 
	 * @throws Exception the exception
	 */
	public void testGroupList() throws Exception
	{
		SmartpointPageAction action = getAction();

		SessionAuthenticationTestUtil.setMockSession();

		GroupBCFMockImpl groupBCF = (GroupBCFMockImpl)action.getGroupBCF();

		// Test Success
		logger.info("Testing success...");
		groupBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		List<IdValuePair> groups = action.getGroupList();
		assertEquals(RESULT_COUNT, GroupBCFMockImpl.GROUP_COUNT + 1, groups.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_DESCRIPTION_KEY), groups.get(0)
				.getValue());

		// Test No Records
		logger.info("Testing no records returned...");
		groupBCF.setMode(BaseMockImpl.MODE_EMPTY);
		groups = action.getGroupList();
		assertEquals(RESULT_COUNT, 1, groups.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_DESCRIPTION_KEY), groups.get(0)
				.getValue());

		// Test Fail
		logger.info("Testing Failure...");
		groupBCF.setMode(BaseMockImpl.MODE_FAILURE);
		groups = action.getGroupList();
		assertEquals(RESULT_COUNT, 1, groups.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_ERROR_KEY), groups.get(0)
				.getValue());

		// Test Fail
		logger.info("Testing Exception...");
		groupBCF.setMode(BaseMockImpl.MODE_EXCEPTION);
		groups = action.getGroupList();
		assertEquals(RESULT_COUNT, 1, groups.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_ERROR_KEY), groups.get(0)
				.getValue());

	}

	/**
	 * Test tag list.
	 * 
	 * @throws Exception the exception
	 */
	public void testTagList() throws Exception
	{
		SmartpointPageAction action = getAction();

		SessionAuthenticationTestUtil.setMockSession();

		TagBCFMockImpl tagBCF = (TagBCFMockImpl)action.getTagBCF();

		// Test Success
		logger.info("Testing success...");
		tagBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		List<IdValuePair> tags = action.getTagList();
		assertEquals(RESULT_COUNT, 20, tags.size());

		// Test No Records
		logger.info("Testing no records returned...");
		tagBCF.setMode(BaseMockImpl.MODE_EMPTY);
		tags = action.getTagList();
		assertEquals(RESULT_COUNT, 0, tags.size());

		// Test Fail
		logger.info("Testing Failure...");
		tagBCF.setMode(BaseMockImpl.MODE_FAILURE);
		tags = action.getTagList();
		assertEquals(RESULT_COUNT, 1, tags.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_ERROR_KEY), tags.get(0)
				.getValue());

		// Test Fail
		logger.info("Testing Exception...");
		tagBCF.setMode(BaseMockImpl.MODE_EXCEPTION);
		tags = action.getTagList();
		assertEquals(RESULT_COUNT, 1, tags.size());
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_ERROR_KEY), tags.get(0)
				.getValue());
	}

	/**
	 * Test status list.
	 */
	public void testStatusList()
	{
		SmartpointPageAction action = getAction();
		logger.info("Testing success...");

		List<IdValuePair> statusList = action.getStatusList();
		assertEquals(RESULT_COUNT, statusList.size(), 6);
		assertEquals(DEFAULT_MESSAGE, action.getText(SmartpointPageAction.DEFAULT_DESCRIPTION_KEY), statusList.get(0)
				.getValue());
	}

	@Test
	public void testActionList()
	{
		SmartpointPageAction action = getAction();
		logger.info("Testing success...");

		List<IdValuePair> statusList = action.getActionList();
		assertEquals(RESULT_COUNT, statusList.size(), 4);

	}

	/**
	 * Test actions list.
	 */
	public void testActionsList()
	{
		SmartpointPageAction action = getAction();
		logger.info("Testing success...");

		List<IdValuePair> actionList = action.getStatusList();
		assertEquals(RESULT_COUNT, actionList.size(), 6);
	}
}